package parking;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sam on 15/2/17.
 */
public class ParkingLot {

    private List<Parking> parkingLotList;
    private int totalNumberOfParkingSpace;

    public ParkingLot(int totalNumberOfParkingSpace) {
        this.totalNumberOfParkingSpace = totalNumberOfParkingSpace;
        parkingLotList = new LinkedList<>();
    }

    public List<Parking> getParkingLotList() {
        return parkingLotList;
    }

    public void addParkingInList(Parking parking) {
        this.parkingLotList.add(parking);
    }

    public boolean getIsParkingAvailable() {
        return (parkingLotList.size() < totalNumberOfParkingSpace);
    }

}
