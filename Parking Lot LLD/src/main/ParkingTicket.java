package main;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingTicket {
	private final Double id;
    private final LocalDateTime entryTime;
    private final int rate;
    
    public ParkingTicket(LocalDateTime entryTime,int rate) {
    	this.id = Math.random();
    	this.entryTime = entryTime;
    	this.rate = rate;
    }
    
    BigDecimal calculateAmount() {
    	LocalDateTime currTime = LocalDateTime.now();
    	int totalHours = currTime.compareTo(entryTime);
    	String finalCost = Integer.toString(totalHours * this.rate);
    	return new BigDecimal(finalCost);
    }
}
