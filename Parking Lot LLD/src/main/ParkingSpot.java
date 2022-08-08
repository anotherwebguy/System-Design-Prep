package main;

import java.util.Objects;

public class ParkingSpot {
	private final Double id;
	private final ParkingSpotType type;
	private Vehicle vehicle;
	
	public ParkingSpot(ParkingSpotType type){
		this.id = Math.random();
		this.type = type;
	}
	
	public ParkingSpotType getType() {
        return type;
    }
	
	void parkVehicle(Vehicle vehicle) {
		if(Objects.nonNull(this.vehicle)) {
			System.out.println("This Parking spot is already occupied");
			return;
		}
		this.vehicle = vehicle;
	}
	
	Vehicle unParkVehicle() {
		if(Objects.nonNull(this.vehicle)) {
			Vehicle vehicle = this.vehicle;
			this.vehicle = null;
			return vehicle;
		}
		return null;
	}
	
	@Override
    public String toString() {
        return "{id= " + id + ", type= " + type + "}";
    }
	
	
	
}
