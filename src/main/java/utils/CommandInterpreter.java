package utils;

import constants.Constants;
import vehical.Vehicle;

import java.util.*;

/**
 * Created by sam on 15/2/17.
 */
public class CommandInterpreter {
    private List<Vehicle> parkingLot = new LinkedList<>();
    private int numberOfParkingSlots = 0;
    private int noOfOccupiedSlots = 0;
    private PriorityQueue<Integer> emptySlots = new PriorityQueue<>();

    public void interpretCommand(String line) {
        String[] inputs = line.split(" ");

        if (inputs.length > 0) {
            String command = inputs[0];
            switch (command) {
                case Constants.CREATE_PARKING_LOT:
                    numberOfParkingSlots = Integer.parseInt(inputs[1]);
                    System.out.println("Created a parking lot with " + numberOfParkingSlots + " slots");
                    for (int i = 1; i <= numberOfParkingSlots; i++) {
                        emptySlots.add(i);
                    }
                    break;

                case Constants.PARK:
                    if (noOfOccupiedSlots < numberOfParkingSlots) {
                        String registrationNumber = inputs[1];
                        String color = inputs[2];
                        int slotNumber = emptySlots.poll();
                        Vehicle vehicle = new Vehicle(registrationNumber, color, slotNumber);
                        parkingLot.add(vehicle);
                        System.out.println("Allocated slot number: " + slotNumber);
                        noOfOccupiedSlots++;
                    } else {
                        System.out.println("Sorry, parking lot is full");
                    }

                    break;

                case Constants.STATUS:
                    System.out.println("Slot No.\t" + "Registration No\t" + "Colour");
                    Collections.sort(parkingLot, new Vehicle());
                    for (Vehicle vehicleInParkingLot : parkingLot) {
                        System.out.println(vehicleInParkingLot.getSlotNumber() + "\t" + vehicleInParkingLot.getRegistrationNumber()
                                + "\t" + vehicleInParkingLot.getColor());
                    }
                    break;

                case Constants.LEAVE:
                    int requestedSlotToLeave = Integer.parseInt(inputs[1]);
                    Iterator<Vehicle> iterator = parkingLot.listIterator();
                    while (iterator.hasNext()) {
                        Vehicle v = iterator.next();
                        if (v.getSlotNumber() == requestedSlotToLeave) {
                            iterator.remove();
                        }
                    }
                    System.out.println("Slot number " + requestedSlotToLeave + " is free");
                    emptySlots.add(requestedSlotToLeave);
                    noOfOccupiedSlots--;
                    break;
                case Constants.REG_NO_FOR_CAR_WITH_COLOR:
                    boolean isFound = false;
                    String color = inputs[1];
                    List<String> registrationNumbers = new ArrayList<>();
                    for (Vehicle vehicleInParkingLot : parkingLot) {
                        if (vehicleInParkingLot.getColor().equalsIgnoreCase(color)) {
                            registrationNumbers.add(vehicleInParkingLot.getRegistrationNumber());
                            isFound = true;
                        }
                    }
                    if (isFound) {
                        System.out.println(registrationNumbers);
                    } else {
                        System.out.println("Not found");
                    }
                    break;
                case Constants.SLOT_NO_FOR_CAR_WITH_COLOR:
                    color = inputs[1];
                    isFound = false;
                    List<Integer> slotNumbers = new ArrayList<>();
                    for (Vehicle vehicleInParkingLot : parkingLot) {
                        if (vehicleInParkingLot.getColor().equalsIgnoreCase(color)) {
                            slotNumbers.add(vehicleInParkingLot.getSlotNumber());
                            isFound = true;
                        }
                    }
                    if (isFound) {
                        System.out.println(slotNumbers);
                    } else {
                        System.out.println("Not found");
                    }
                    break;
                case Constants.SLOT_NUMBER_FOR_REG_NUMBER:
                    String registrationNumber = inputs[1];
                    slotNumbers = new ArrayList<>();
                    isFound = false;
                    for (Vehicle vehicleInParkingLot : parkingLot) {
                        if (vehicleInParkingLot.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                            slotNumbers.add(vehicleInParkingLot.getSlotNumber());
                            isFound = true;
                        }
                    }
                    if (isFound) {
                        System.out.println(slotNumbers);
                    } else {
                        System.out.println("Not found");
                    }
                    break;
            }
        }
    }
}
