package utils;

import vehical.Vehicle;

import java.util.*;

/**
 * Created by sam on 15/2/17.
 */
public class ParkingUtils {
    public Vehicle parkVehicle(String registrationNumber, String color, PriorityQueue<Integer> emptySlots) {
        Vehicle vehicle = null;
        if (emptySlots != null && emptySlots.size() > 0) {
            int assignedSlotNumber = emptySlots.poll();
            vehicle = new Vehicle(registrationNumber, color, assignedSlotNumber);
            System.out.println("Allocated slot number: " + assignedSlotNumber);
        }
        return vehicle;
    }

    public boolean leaveParking(List<Vehicle> parkingLot, int requestedSlotToLeave) {
        Iterator<Vehicle> iterator = parkingLot.listIterator();
        boolean isRemoved = false;
        while (iterator.hasNext()) {
            Vehicle v = iterator.next();
            if (v.getSlotNumber() == requestedSlotToLeave) {
                iterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    public List<String> getRegistrationNoForCarWithColor(List<Vehicle> parkingLot, String color) {
        List<String> registrationNumbers = new ArrayList<>();
        for (Vehicle vehicleInParkingLot : parkingLot) {
            if (vehicleInParkingLot.getColor().equalsIgnoreCase(color)) {
                registrationNumbers.add(vehicleInParkingLot.getRegistrationNumber());
            }
        }
        return registrationNumbers;

    }

    public List<Integer> getSlotNoForCarWithColor(List<Vehicle> parkingLot, String color) {
        List<Integer> slotNumbers = new ArrayList<>();
        for (Vehicle vehicleInParkingLot : parkingLot) {
            if (vehicleInParkingLot.getColor().equalsIgnoreCase(color)) {
                slotNumbers.add(vehicleInParkingLot.getSlotNumber());
            }
        }
        return slotNumbers;
    }

    public List<Integer> getSlotNoForRegistrationNumber(List<Vehicle> parkingLot, String registrationNumber) {
        List<Integer> slotNumbers = new ArrayList<>();
        for (Vehicle vehicleInParkingLot : parkingLot) {
            if (vehicleInParkingLot.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                slotNumbers.add(vehicleInParkingLot.getSlotNumber());
            }
        }
        return slotNumbers;

    }
}
