package Formula1_Manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class Formula1RunnerTest{
@Nested
class DateValidation {
    @Test
    @DisplayName("Should check valid dates")
    void isDateValidation_true() {
        assertTrue(Formula1Runner.dateValidation("2021-02-14"));
        assertTrue(Formula1Runner.dateValidation("2020-01-31"));
        assertTrue(Formula1Runner.dateValidation("2020-03-31"));
        assertTrue(Formula1Runner.dateValidation("2020-04-30"));
        assertTrue(Formula1Runner.dateValidation("2020-05-31"));
        assertTrue(Formula1Runner.dateValidation("2020-05-31"));
        assertTrue(Formula1Runner.dateValidation("2021-06-30"));
        assertTrue(Formula1Runner.dateValidation("2017-07-31"));
        assertTrue(Formula1Runner.dateValidation("2019-08-31"));
        assertTrue(Formula1Runner.dateValidation("2013-10-31"));
        assertTrue(Formula1Runner.dateValidation("2013-11-30"));
        assertTrue(Formula1Runner.dateValidation("2020-02-29"));
    }

    @Test
    @DisplayName("Should check invalid dates")
    void isDateValidation_false() {
        assertFalse(Formula1Runner.dateValidation("2021-20-14"));
        assertFalse(Formula1Runner.dateValidation("2018-04-31"));
        assertFalse(Formula1Runner.dateValidation("2021-02-29"));
        assertFalse(Formula1Runner.dateValidation("2022-02-28"));
        assertFalse(Formula1Runner.dateValidation("2021-06-31"));
        assertFalse(Formula1Runner.dateValidation("2019-09-31"));
        assertFalse(Formula1Runner.dateValidation("2019-08-36"));
        assertFalse(Formula1Runner.dateValidation("2013-11-31"));
        assertFalse(Formula1Runner.dateValidation("2013/12/31"));
        assertFalse(Formula1Runner.dateValidation("2013-i2-31"));
        assertFalse(Formula1Runner.dateValidation("2021-12-31"));
    }
}
    @Test
    void isStartPosition_true(){
        Formula1Runner fr = new Formula1Runner();
        assertEquals(fr.selectingWinner(1),1);
        assertEquals(fr.selectingWinner(40),1);
        assertEquals(fr.selectingWinner(41),2);
        assertEquals(fr.selectingWinner(70),2);
        assertEquals(fr.selectingWinner(71),3);
        assertEquals(fr.selectingWinner(80),3);
        assertEquals(fr.selectingWinner(81),4);
        assertEquals(fr.selectingWinner(90),4);
        assertEquals(fr.selectingWinner(91),5);
        assertEquals(fr.selectingWinner(92),5);
        assertEquals(fr.selectingWinner(93),6);
        assertEquals(fr.selectingWinner(94),6);
        assertEquals(fr.selectingWinner(95),7);
        assertEquals(fr.selectingWinner(96),7);
        assertEquals(fr.selectingWinner(97),8);
        assertEquals(fr.selectingWinner(98),8);
        assertEquals(fr.selectingWinner(99),9);
        assertEquals(fr.selectingWinner(100),9);
    }


    ArrayList<String> winnerOrder(int winnerIndex, ArrayList<String>carTeam){
        int position;
        do {
            position = new Formula1Runner().selectingWinner(winnerIndex);
        }while (position>carTeam.size()-1);
        String winner = carTeam.get(position - 1);
        Collections.shuffle(carTeam, new Random());
        int winner_index = carTeam.indexOf(winner);
        String changePosition = carTeam.get(0);
        carTeam.set(0, winner);
        carTeam.set(winner_index, changePosition);
        return carTeam;
    }


    void checkWinner(ArrayList<String>carTeam){
        String winner;
        ArrayList<String>carTeamChange;

        winner = carTeam.get(0);
        carTeamChange= new Formula1RunnerTest().winnerOrder(1, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(0);
        carTeamChange= new Formula1RunnerTest().winnerOrder(40, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(1);
        carTeamChange= new Formula1RunnerTest().winnerOrder(41, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(1);
        carTeamChange= new Formula1RunnerTest().winnerOrder(70, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(2);
        carTeamChange= new Formula1RunnerTest().winnerOrder(71, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(2);
        carTeamChange= new Formula1RunnerTest().winnerOrder(80, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(3);
        carTeamChange= new Formula1RunnerTest().winnerOrder(81, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(3);
        carTeamChange= new Formula1RunnerTest().winnerOrder(90, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(4);
        carTeamChange= new Formula1RunnerTest().winnerOrder(91, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(4);
        carTeamChange= new Formula1RunnerTest().winnerOrder(92, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(5);
        carTeamChange= new Formula1RunnerTest().winnerOrder(93, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(5);
        carTeamChange= new Formula1RunnerTest().winnerOrder(94, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(6);
        carTeamChange= new Formula1RunnerTest().winnerOrder(95, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(6);
        carTeamChange= new Formula1RunnerTest().winnerOrder(96, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(7);
        carTeamChange= new Formula1RunnerTest().winnerOrder(97, carTeam);
        assertEquals(carTeamChange.get(0), winner);

        winner = carTeam.get(7);
        carTeamChange= new Formula1RunnerTest().winnerOrder(98, carTeam);
        assertEquals(carTeamChange.get(0), winner);
    }
    @Test
    void checkGuiSecondRace(){
        List<String> keys = List.of("Toyota", "Suzuki", "Honda", "BMW", "Chery", "Jaguar", "Maserati", "Ferrari", "Alpine", "McLaren", "Lancia","Fiat");
        ArrayList<String>carTeam = new ArrayList<>(keys);
        Collections.shuffle(carTeam, new Random());
        checkWinner(carTeam);
    }
//https://www.arhohuttunen.com/junit-5-nested-tests/
//@Test
@ParameterizedTest
@DisplayName("Should check the GUI first race")
@ValueSource(ints = {2,3,8,4,1,5,1,6,7})
    void testManualRace(int expectedInputs){
        List<String> race = new ArrayList<>(List.of("Toyota", "Suzuki", "Honda", "BMW", "Chery", "Jaguar", "Maserati"));
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<String> endPositions = new ArrayList<>();
        ArrayList<String> endPositionsOrder = new ArrayList<>(List.of("Suzuki","Honda","BMW","Toyota","Chery","Jaguar","Maserati"));
        List <Integer> points = new ArrayList<>(List.of(2,3,8,4,1,5,1,6,7));
        int count = 0;
        int f = 0;
        while (count<race.size()){
            // Getting race's end position of each player
            int position=expectedInputs;
            if (position<=race.size()){
                if(!pos.contains(position-1)){
                    String d= race.get(position-1);
                    pos.add(position-1);
                    endPositions.add(d);//Adding the race's end position to an  endPositions array
                    count++;
                    //assertEquals("","");

                }
                else {
                    System.out.printf("You have already provide a position for %d", expectedInputs).println("\n");
                }
            }
            else{
            System.out.printf("Only %d are in the competition", race.size()).println("\n");
            }f++;
        }
        assertEquals(endPositions,endPositionsOrder);
    }

}