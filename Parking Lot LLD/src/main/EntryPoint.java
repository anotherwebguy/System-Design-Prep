package main;

import java.time.LocalDateTime;

public class EntryPoint {
	private final Double id;

    public EntryPoint() {
        this.id = Math.random();
    }

    ParkingTicket generateTicket(Vehicle vehicle) {
        VehicleType type = vehicle.getType();
        int rate = 0;
        if(type==VehicleType.SMALL) rate = 50;
        else if(type==VehicleType.MEDIUM) rate = 100;
        else rate = 150;

        return new ParkingTicket(LocalDateTime.now(), rate);
    }
}
