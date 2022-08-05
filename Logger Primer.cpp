#include<bits/stdc++.h>
using namespace std;

enum MessageType
{
	ERROR,
	WARNING,
	GENERAL
};

class Message
{
private:     //encapsulation
	MessageType type;
	string message;
	time_t t;

public:
	Message() {

	}

	Message(MessageType mtype, string msg){
		type = mtype;
		message == msg;
		t = time(NULL);
	}

	string getMessage(){
		return message;
	}

	MessageType getType(){
		return type;
	}

	time_t getTime(){
		return t;
	}
};

class Observer
{
public:
	virtual void push(int id, Message msg) = 0;    //polymorphism
	virtual void pop(int id) = 0;
	virtual void notifier(int id, bool isEnded) = 0;
};

class Logger: public Observer  //inheritance
{
	map<int,Message> messages;
public:
	void push(int id, Message msg) {    //abstraction
		messages[id] = msg;
		notifier(id,false);
	}

	void pop(int id) {
		if(messages.find(id)==messages.end()){
			cout<<"No current process with this id is running\n";
			return;
		}
		notifier(id,true);
		messages.erase(id);
	}

	void notifier(int id, bool isEnded) {
		string ErrorTypes[] =
		{
		    "ERROR",
		    "WARNING",
		    "GENERAL"
		};
		Message data = messages[id];
		string msg = data.getMessage();
		MessageType type = data.getType();
		time_t time = data.getTime();
		if(isEnded){
			cout<<ErrorTypes[type]<<": Process with process id {"<<id<<"} "<<"ended at "<<ctime(&time);
		} else {
			cout<<ErrorTypes[type]<<": Process with process id {"<<id<<"} "<<"started at "<<ctime(&time);
		}
	}
};

int main(){
	Logger console;
	console.push(1,Message(MessageType::GENERAL,"started Logger"));
	console.push(2,Message(MessageType::WARNING,"It's already Late"));
	console.push(3,Message(MessageType::ERROR,"Code fatt gaya"));
	console.pop(1);
}
