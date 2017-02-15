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
public class Vehicle {
    private String registrationNumber;
    private String color;
    private int slotNumber;
}

