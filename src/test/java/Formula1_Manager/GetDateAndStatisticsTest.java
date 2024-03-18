package Formula1_Manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetDateAndStatisticsTest {
@Test
void getToString(){
    GetDateAndStatistics gdas = new GetDateAndStatistics("Suzuki","2015-12-10",10);
    assertEquals("Suzuki, 2015-12-10, 10", gdas.toString());
}
}