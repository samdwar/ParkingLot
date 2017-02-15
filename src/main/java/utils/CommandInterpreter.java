package utils;

import constants.Constants;
import vehical.Vehicle;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by sam on 15/2/17.
 */
public class CommandInterpreter {
    private List<Vehicle> parkingLot = new LinkedList<>();
    private int numberOfParkingSlots = 0;
    private int noOfOccupiedSlots = 0;
    private PriorityQueue<Integer> emptySlots = new PriorityQueue<>();
    private ParkingUtils parkingUtils = new ParkingUtils();

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
                        Vehicle vehicle = parkingUtils.parkVehicle(registrationNumber, color, emptySlots);
                        parkingLot.add(vehicle);
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
                    boolean isLeaveSuccess = parkingUtils.leaveParking(parkingLot, requestedSlotToLeave);
                    if (isLeaveSuccess) {
                        emptySlots.add(requestedSlotToLeave);
                        noOfOccupiedSlots--;
                        System.out.println("Slot number " + requestedSlotToLeave + " is free");
                    } else {
                        System.out.println("Not found");
                    }
                    break;

                case Constants.REG_NO_FOR_CAR_WITH_COLOR:
                    String color = inputs[1];
                    List<String> regNoList = parkingUtils.getRegistrationNoForCarWithColor(parkingLot, color);
                    if (regNoList != null && regNoList.size() > 0) {
                        StringBuilder registrationNos = new StringBuilder();
                        String commaDelimiter = ", ";
                        for (String registrationNo : regNoList) {
                            registrationNos.append(registrationNo).append(commaDelimiter);
                        }

                        System.out.println(registrationNos.deleteCharAt(registrationNos.length() - 2));
                    } else {
                        System.out.println("Not found");
                    }
                    break;

                case Constants.SLOT_NO_FOR_CAR_WITH_COLOR:
                    color = inputs[1];
                    List<Integer> slotList = parkingUtils.getSlotNoForCarWithColor(parkingLot, color);
                    if (slotList != null && slotList.size() > 0) {
                        String commaSeparatedSlotList = getCommaSeparateList(slotList);
                        System.out.println(commaSeparatedSlotList);
                    } else {
                        System.out.println("Not found");
                    }
                    break;

                case Constants.SLOT_NUMBER_FOR_REG_NUMBER:
                    String registrationNumber = inputs[1];
                    slotList = parkingUtils.getSlotNoForRegistrationNumber(parkingLot, registrationNumber);
                    if (slotList != null && slotList.size() > 0) {
                        String commaSeparatedList = getCommaSeparateList(slotList);
                        System.out.println(commaSeparatedList);
                    } else {
                        System.out.println("Not found");
                    }
                    break;
            }
        }
    }

    private String getCommaSeparateList(List<Integer> slotList) {
        StringBuilder slots = new StringBuilder();
        String commaDelimiter = ", ";
        for (int slot : slotList) {
            slots.append(slot).append(commaDelimiter);
        }
        return slots.deleteCharAt(slots.length() - 2).toString();
    }
}
