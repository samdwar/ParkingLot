package utils;

import parking.Parking;
import parking.ParkingLot;
import vehical.Vehicle;

import java.util.*;

/**
 * Created by sam on 15/2/17.
 */
public class ParkingUtils {
    public Vehicle parkVehicle(String registrationNumber, String color, PriorityQueue<Integer> emptySlots) {
        Vehicle vehicle = null;
        if (emptySlots != null && emptySlots.size() > 0) {
            /*Get nearest parking space*/
            int assignedSlotNumber = emptySlots.poll();
            vehicle = new Vehicle(registrationNumber, color, assignedSlotNumber);
            System.out.println("Allocated slot number: " + assignedSlotNumber);
        }
        return vehicle;
    }

    public boolean leaveParking(ParkingLot parkingLot, int requestedSlotToLeave) {
        List<Parking> parkingList = parkingLot.getParkingLotList();
        Iterator<Parking> iterator = parkingList.listIterator();
        boolean isRemoved = false;
        while (iterator.hasNext()) {
            Parking p = iterator.next();
            if (p.getVehicle().getSlotNumber() == requestedSlotToLeave) {
                iterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    public List<String> getRegistrationNoForCarWithColor(ParkingLot parkingLot, String color) {
        List<Parking> parkingList = parkingLot.getParkingLotList();
        List<String> registrationNumbers = new ArrayList<>();
        for (Parking parking : parkingList) {
            if (parking.getVehicle().getColor().equalsIgnoreCase(color)) {
                registrationNumbers.add(parking.getVehicle().getRegistrationNumber());
            }
        }
        return registrationNumbers;

    }

    public List<Integer> getSlotNoForCarWithColor(ParkingLot parkingLot, String color) {
        List<Parking> parkingList = parkingLot.getParkingLotList();
        List<Integer> slotNumbers = new ArrayList<>();
        for (Parking parking : parkingList) {
            if (parking.getVehicle().getColor().equalsIgnoreCase(color)) {
                slotNumbers.add(parking.getVehicle().getSlotNumber());
            }
        }
        return slotNumbers;
    }

    public List<Integer> getSlotNoForRegistrationNumber(ParkingLot parkingLot, String registrationNumber) {
        List<Parking> parkingLists = parkingLot.getParkingLotList();
        List<Integer> slotNumbers = new ArrayList<>();
        for (Parking parking : parkingLists) {
            if (parking.getVehicle().getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                slotNumbers.add(parking.getVehicle().getSlotNumber());
            }
        }
        return slotNumbers;

    }
}
