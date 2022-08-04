#include<bits/stdc++.h>
#include <chrono>
#include <sys/time.h>
#include <ctime>
using namespace std;
using std::chrono::duration_cast;
using std::chrono::milliseconds;
using std::chrono::seconds;
using std::chrono::system_clock;


class Process {
private:
	string processId;
	long startTime;
	long endTime;

public:
	Process(){

	}

	Process(string processId, long startTime){
		this->processId = processId;
		this->startTime = startTime;
		this->endTime = -1;
	}

	void setEndTime(long endTime){
		this->endTime = endTime;
	}

	string getId(){
		return processId;
	}

	long getStartTime(){
		return startTime;
	}

	long getEndTime(){
		return endTime;
	}
};

class Logger {
	
public:
	unordered_map<string,Process> processes;
	map<long,Process> queue;

	void start(string processId,int count){
		auto t_ime = duration_cast<milliseconds>(system_clock::now().time_since_epoch()).count();
		long now = (long)t_ime+count;
		Process p(processId,now);
		processes[processId] = (Process)p;
		queue[now] = (Process)p;
	}

	void end(string processId){
		auto t_ime = duration_cast<milliseconds>(system_clock::now().time_since_epoch()).count();
		long now = (long)t_ime;
		processes[processId].setEndTime(now);
		Process p = processes[processId];
		queue[p.getStartTime()].setEndTime(now);
	}

	void poll(){
		if(queue.size()==0){
			cout<<"No Running Processes in the queue\n";
			return;
		}
		auto it = queue.begin();
		Process p = it->second;
		if(p.getEndTime()!=-1){
			cout<<p.getId()<<" started at "<<p.getStartTime()<<" and ended at "<<p.getEndTime()<<"\n";
			processes.erase(p.getId());
			queue.erase(p.getStartTime());
		} else {
			cout<<"Currently Running tasks: "<<queue.size()<<"\n";
		}
	}
};

int main(){
	Logger log;
	log.poll();
	log.start("1",1);
	log.poll();
	log.start("4",2);
	log.poll();
	log.end("1");
	log.poll();
	log.start("3",3);
	log.poll();
	log.end("3");
	log.poll();
	log.end("4");
	log.poll();
	log.poll();
}
