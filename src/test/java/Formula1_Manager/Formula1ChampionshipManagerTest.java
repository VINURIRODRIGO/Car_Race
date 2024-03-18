package Formula1_Manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Formula1ChampionshipManagerTest {

    @Test
    public void checkCapitalize() {
        assertEquals(Formula1ChampionshipManager.wordCapitalize("sam kmi"),"Sam Kmi");
        assertEquals(Formula1ChampionshipManager.wordCapitalize("Peter samson"), "Peter Samson");
        assertEquals(Formula1ChampionshipManager.wordCapitalize("David Copper"), "David Copper");
        assertEquals(Formula1ChampionshipManager.wordCapitalize("william Shakier"), "William Shakier");
        assertEquals(Formula1ChampionshipManager.wordCapitalize("diam10"), "Diam10");
    }

    @Test
    void checkIsValidName() {
        assertTrue(new Formula1ChampionshipManager().isValidName("sam kmi"));
        assertTrue(new Formula1ChampionshipManager().isValidName("sam Kmi"));
        assertTrue(new Formula1ChampionshipManager().isValidName("Sam Kmi"));
        assertFalse(new Formula1ChampionshipManager().isValidName("sam k21"));
        assertFalse(new Formula1ChampionshipManager().isValidName(""));
        assertFalse(new Formula1ChampionshipManager().isValidName(" "));
        assertFalse(new Formula1ChampionshipManager().isValidName("   "));
        assertFalse(new Formula1ChampionshipManager().isValidName("s"));
        assertFalse(new Formula1ChampionshipManager().isValidName("\n"));
    }

    @Test
    void checkIsInvalidTeam(){
        assertFalse(new Formula1ChampionshipManager().isInvalidTeam("Swe10"));
        assertTrue(new Formula1ChampionshipManager().isInvalidTeam("Swe10 Well"));
        assertTrue(new Formula1ChampionshipManager().isInvalidTeam(""));
        assertTrue(new Formula1ChampionshipManager().isInvalidTeam(" "));
        assertTrue(new Formula1ChampionshipManager().isInvalidTeam("   "));
        assertTrue(new Formula1ChampionshipManager().isInvalidTeam("s"));
        assertTrue(new Formula1ChampionshipManager().isInvalidTeam("\n"));
    }
//
//    @Test
//    void driverStatistics() {
//    }
//
//    @Test
//    void comparison() {
//    }
}