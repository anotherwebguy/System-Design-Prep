package main;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
	private final Map<VehicleType, List<ParkingSpot>> emptyParkingSpotsMap;
	private final Map<VehicleType, List<ParkingSpot>> occupiedParkingSpotsMap;
	private DisplayBoard displayBoard;
	
	public ParkingFloor(Map<VehicleType, List<ParkingSpot>> emptyParkingSpotsMap, Map<VehicleType,
            List<ParkingSpot>> occupiedParkingSpotsMap, DisplayBoard displayBoard) {
		this.emptyParkingSpotsMap = emptyParkingSpotsMap;
        this.occupiedParkingSpotsMap = occupiedParkingSpotsMap;
        this.displayBoard = displayBoard;
	}
	
	void showDisplay() {
		for(Map.Entry<VehicleType, List<ParkingSpot>> m : emptyParkingSpotsMap.entrySet()) {
			VehicleType type = m.getKey();
			List<ParkingSpot> parkingSpotsForVehicleType = m.getValue();
			if (parkingSpotsForVehicleType == null || parkingSpotsForVehicleType.isEmpty()) {
                this.displayBoard.displayMessage("This floor is full for vehicle type " + type.toString());
            } else {
                this.displayBoard.displayMessage("Remaining spots for vehicle type: "+ type.toString()+" on this floor is " +
                        parkingSpotsForVehicleType.size());
                this.displayBoard.displayMessage("Those empty spots on this floor are " +
                        parkingSpotsForVehicleType);
            }
		}
	}
	
	ParkingSpot parkVehicle(Vehicle vehicle) throws RuntimeException{
		if (emptyParkingSpotsMap == null || emptyParkingSpotsMap.isEmpty()) {
            throw new RuntimeException("Parking floor doesn't exist");
        }
		
		VehicleType givenVehicleType = vehicle.getType();
        List<ParkingSpot> emptyParkingSpotsForGivenVehicleType = emptyParkingSpotsMap.get(givenVehicleType);

        if (emptyParkingSpotsForGivenVehicleType.isEmpty()) {
            throw new RuntimeException("Parking is full for vehicle of type " + givenVehicleType.toString());
        }
        
        ParkingSpot parkingSpot = emptyParkingSpotsForGivenVehicleType.get(0);
        parkingSpot.parkVehicle(vehicle);
        
        List<ParkingSpot> occupiedParkingSpotsForGivenVehicleType = occupiedParkingSpotsMap.get(givenVehicleType);
        occupiedParkingSpotsForGivenVehicleType.add(parkingSpot);

        emptyParkingSpotsForGivenVehicleType.remove(parkingSpot);
        return parkingSpot;
	}
	
	Vehicle unParkVehicle(ParkingSpot parkingSpot) throws RuntimeException{
		if (occupiedParkingSpotsMap == null || occupiedParkingSpotsMap.isEmpty()) {
            throw new RuntimeException("Vehicle doesn't exist");
        }

        Vehicle vehicle = parkingSpot.unParkVehicle();
        VehicleType vehicleType = vehicle.getType();
        
        List<ParkingSpot> occupiedParkingSpotsForGivenVehicleType = occupiedParkingSpotsMap.get(vehicleType);
        occupiedParkingSpotsForGivenVehicleType.remove(parkingSpot);

        List<ParkingSpot> emptyParkingSpotsForGivenVehicleTypes = emptyParkingSpotsMap.get(vehicleType);
        emptyParkingSpotsForGivenVehicleTypes.add(parkingSpot);

        return vehicle;
	}
	
	Boolean isNotFull() {
        if (this.emptyParkingSpotsMap.isEmpty())
            return true;

        Collection<List<ParkingSpot>> values = this.emptyParkingSpotsMap.values();

        for (List parkingSpots : values) {
            if (parkingSpots.isEmpty())
                return true;
        }

        return false;
    }
	
	Boolean hasSpace(Vehicle vehicle) {
        return !(this.emptyParkingSpotsMap
                .get(vehicle.getType())
                .isEmpty());
    }

    Boolean hasGivenParkingSpot(ParkingSpot parkingSpot) {
        return this.occupiedParkingSpotsMap
                .get(parkingSpot.getType())
                .contains(parkingSpot);
    }
	
}
