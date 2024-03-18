package Formula1_Manager;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
  * Race Date and end positions including Manufacture names,
  */
@Getter
// https://stackoverflow.com/questions/23469784/com-fasterxml-jackson-databind-exc-unrecognizedpropertyexception-unrecognized-f
@Setter
public class GetDateAndStatistics {
    private String Manufacture;
    private LocalDate date;
    private int point;
    private String strDate;

    // To load the data in a RaceDate.
    public GetDateAndStatistics() {
    }

    public GetDateAndStatistics(String manufacture, LocalDate date, int point) {
        Manufacture = manufacture;
        this.date = date;
        this.point = point;
    }
    public GetDateAndStatistics (String manufacture, String strDate, int point) {
        Manufacture = manufacture;
        this.strDate = strDate;
        this.point = point;
    }

    @Override
    public String toString() {
        return this.Manufacture+", "+ this.strDate+", "+ this.point;
    }
}

