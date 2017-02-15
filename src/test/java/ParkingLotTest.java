import org.junit.Test;
import parking.Parking;
import parking.ParkingLot;
import utils.ParkingUtils;
import vehical.Vehicle;

import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

/**
 * Created by sam on 15/2/17.
 */
public class ParkingLotTest {
    @Test
    public void testParkVehicle() {
        ParkingUtils parkingUtils = new ParkingUtils();
        String regNo = "ka-23456";
        String color = "White";
        Vehicle vehicle = parkingUtils.parkVehicle(regNo, color, null);
        assertNull("Not able to park vehicle", vehicle);
        PriorityQueue<Integer> emptySlots = new PriorityQueue<>();
        vehicle = parkingUtils.parkVehicle(regNo, color, emptySlots);
        assertNull("Not able to park vehicle", vehicle);
        emptySlots.add(1);
        emptySlots.add(2);
        vehicle = parkingUtils.parkVehicle(regNo, color, emptySlots);
        assertNotNull("Not able to park vehicle", vehicle);

    }

    @Test
    public void testLeaveParking() {
        ParkingUtils parkingUtils = new ParkingUtils();
        PriorityQueue<Integer> emptySlots = new PriorityQueue<>();
        emptySlots.add(1);
        emptySlots.add(2);
        Vehicle vehicle = new Vehicle("ka-01", "Black", emptySlots.poll());
        Parking parking = new Parking(vehicle);
        ParkingLot parkingLot = new ParkingLot(4);
        parkingLot.addParkingInList(parking);
        vehicle = new Vehicle("ka-02", "Blue", emptySlots.poll());
        parking = new Parking(vehicle);
        parkingLot.addParkingInList(parking);
        assertTrue("Not able to vacate space from parking", parkingUtils.leaveParking(parkingLot, 2));
        assertTrue("Not able to vacate space from parking", parkingUtils.leaveParking(parkingLot, 1));
        assertFalse("Not able to vacate space from parking", parkingUtils.leaveParking(parkingLot, 1));
        assertFalse("Not able to vacate space from parking", parkingUtils.leaveParking(parkingLot, 3));

    }

    @Test
    public void testGetRegistrationNoForCarWithColor() {
        ParkingUtils parkingUtils = new ParkingUtils();
        PriorityQueue<Integer> emptySlots = new PriorityQueue<>();
        emptySlots.add(1);
        emptySlots.add(2);

        Vehicle vehicle = new Vehicle("ka-01", "Black", emptySlots.poll());
        ParkingLot parkingLot = new ParkingLot(4);
        Parking parking = new Parking(vehicle);
        parkingLot.addParkingInList(parking);
        vehicle = new Vehicle("ka-02", "Blue", emptySlots.poll());
        parking = new Parking(vehicle);
        parkingLot.addParkingInList(parking);

        List<String> list = parkingUtils.getRegistrationNoForCarWithColor(parkingLot, "White");
        assertEquals("Wrong no of vehicle found with that color", 0, list.size());
        list = parkingUtils.getRegistrationNoForCarWithColor(parkingLot, "Black");
        assertEquals("Wrong no of vehicle found with that color", 1, list.size());
        list = parkingUtils.getRegistrationNoForCarWithColor(parkingLot, "Blue");
        assertEquals("Wrong no of vehicle found with that color", 1, list.size());
    }

    @Test
    public void testGetSlotNoForCarWithColor() {
        ParkingUtils parkingUtils = new ParkingUtils();
        PriorityQueue<Integer> emptySlots = new PriorityQueue<>();
        emptySlots.add(1);
        emptySlots.add(2);

        Vehicle vehicle = new Vehicle("ka-01", "Black", emptySlots.poll());
        ParkingLot parkingLot = new ParkingLot(4);
        Parking parking = new Parking(vehicle);
        parkingLot.addParkingInList(parking);
        vehicle = new Vehicle("ka-02", "Blue", emptySlots.poll());
        parking = new Parking(vehicle);
        parkingLot.addParkingInList(parking);

        List<Integer> list = parkingUtils.getSlotNoForCarWithColor(parkingLot, "White");
        assertEquals("Wrong no of vehicle found with that color", 0, list.size());
        list = parkingUtils.getSlotNoForCarWithColor(parkingLot, "Black");
        assertEquals("Wrong no of vehicle found with that color", 1, list.size());
        list = parkingUtils.getSlotNoForCarWithColor(parkingLot, "Blue");
        assertEquals("Wrong no of vehicle found with that color", 1, list.size());
    }

    @Test
    public void testGetSlotNoForRegistrationNumber() {
        ParkingUtils parkingUtils = new ParkingUtils();
        PriorityQueue<Integer> emptySlots = new PriorityQueue<>();
        emptySlots.add(1);
        emptySlots.add(2);

        Vehicle vehicle = new Vehicle("ka-01", "Black", emptySlots.poll());
        ParkingLot parkingLot = new ParkingLot(4);
        Parking parking = new Parking(vehicle);
        parkingLot.addParkingInList(parking);
        vehicle = new Vehicle("ka-02", "Blue", emptySlots.poll());
        parking = new Parking(vehicle);
        parkingLot.addParkingInList(parking);

        List<Integer> list = parkingUtils.getSlotNoForRegistrationNumber(parkingLot, "ka-11");
        assertEquals("Wrong no of vehicle found with that registration number", 0, list.size());
        list = parkingUtils.getSlotNoForRegistrationNumber(parkingLot, "ka-01");
        assertEquals("Wrong no of vehicle found with that registration number", 1, list.size());
        list = parkingUtils.getSlotNoForRegistrationNumber(parkingLot, "ka-02");
        assertEquals("Wrong no of vehicle found with that registration number", 1, list.size());
    }
}
