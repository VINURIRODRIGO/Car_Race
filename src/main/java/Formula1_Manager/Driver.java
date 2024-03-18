package Formula1_Manager;

import lombok.Getter;
import lombok.Setter;

/**
 * Driver details including driver's and Car Manufacturer's name, and the location
 */
@Getter
@Setter
public abstract class Driver {
    private String driverName;
    private String carManufacturer;
    private String location;

    public abstract void changeDriver();

    @Override
    public String toString() {
        return String.format("%s,%s",getDriverName(), getLocation());
    }
}
