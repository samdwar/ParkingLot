package parking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vehical.Vehicle;

import java.util.Comparator;

/**
 * Created by sam on 15/2/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking implements Comparator {
    private Vehicle vehicle;

    @Override
    public int compare(Object o1, Object o2) {
        Parking p1 = (Parking) o1;
        Parking p2 = (Parking) o2;
        int result = -1;
        Vehicle v1 = p1.getVehicle();
        Vehicle v2 = p2.getVehicle();
        if (v1.getSlotNumber() < v2.getSlotNumber()) {
            result = -1;
        } else if (v1.getSlotNumber() == v2.getSlotNumber()) {
            result = 0;
        } else {
            result = 1;
        }
        return result;
    }
}
