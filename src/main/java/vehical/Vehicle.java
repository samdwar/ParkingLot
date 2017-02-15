package vehical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

/**
 * Created by sam on 15/2/17.
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Vehicle implements Comparator {
    private String registrationNumber;
    private String color;
    private int slotNumber;

    @Override
    public int compare(Object o1, Object o2) {
        int result = -1;
        Vehicle v1 = (Vehicle) o1;
        Vehicle v2 = (Vehicle) o2;
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

