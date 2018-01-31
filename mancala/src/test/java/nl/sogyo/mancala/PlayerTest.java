package src.test.java.nl.sogyo.mancala;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import src.main.java.nl.sogyo.mancala.Player;

public class PlayerTest {
    public Player player1;

    @Before
    public void setup(){
        player1 = new Player("1");
    }

    @Test
    @DisplayName("Test if we can set up the two players and link them")
    public void testPlayerHasPlayer(){
        Assert.assertNotNull("Player needs to have another play object as enemy", player1.getEnemy());
    }

    @Test
    @DisplayName("Test if we can set up the two players and link them")
    public void testPlayerInitialization(){

        Assert.assertEquals(player1.getPlayerName(), "1");
        Assert.assertEquals(player1.getHasTurn(), true);

        Assert.assertEquals(player1.getEnemy().getPlayerName(), "2");
        Assert.assertEquals(player1.getEnemy().getHasTurn(), false);
    }

    @Test
    @DisplayName("Test if we can succesfully switch turns")
    public void testPlayerSwitchTurns(){

        Assert.assertEquals("Player1 should have the turn on the start of the game", player1.getHasTurn(), true);
        Assert.assertEquals("Player1.getEnemy() should not have the turn on the start of the game", player1.getEnemy().getHasTurn(), false);

        this.player1.switchTurns();

        Assert.assertEquals("Player1 should not have the turn anymore, because we switched it", player1.getHasTurn(), false);
        Assert.assertEquals("Plater1.getEnemy() should have the turn now", player1.getEnemy().getHasTurn(), true);

    }
}
