package Formula1_Manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Formula1DriverTest {
    @Test
    void getSetDriverName(){
        Formula1Driver fd = new Formula1Driver("Ben","Italy",10);
        assertEquals("Ben", fd.getDriverName());
        fd.setDriverName("Jonathon");
        assertEquals("Jonathon",fd.getDriverName());
    }
    @Test
    void getSetLocation(){
        Formula1Driver fd = new Formula1Driver("Ben","Italy",10);
        assertEquals("Italy", fd.getLocation());
        fd.setLocation("Korea");
        assertEquals("Korea", fd.getLocation());
    }
    @Test
    void getSetCarManufacturer(){
        Formula1Driver fd = new Formula1Driver();
        fd.setCarManufacturer("Toyota");
        assertEquals("Toyota", fd.getCarManufacturer());
    }
    @Test
    void getSetPoints(){
        Formula1Driver fd = new Formula1Driver("Ben","Italy",10);
        assertEquals(10, fd.getPoints());
        assertEquals(10, fd.getPoints());
    }
    @Test
    void getToString(){
        Formula1Driver fd = new Formula1Driver("Ben","Italy",10);
        assertEquals("Ben,Italy,10", fd.toString());
    }

}