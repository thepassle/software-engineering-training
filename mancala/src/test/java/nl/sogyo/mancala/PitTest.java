package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import src.main.java.nl.sogyo.mancala.Kalaha;
import src.main.java.nl.sogyo.mancala.NormalPit;
import src.main.java.nl.sogyo.mancala.Player;
import src.main.java.nl.sogyo.mancala.Pit;

// Test logica, niet code
public class PitTest {
    public Pit pit;
    public Player player1;

    @Before
    public void setup(){
        pit = new NormalPit();
        player1 = new Player("1");
    }

    @Test
    public void testGetAantalSteentjes(){
        Assert.assertEquals("Contents of pit should be 4", 4, pit.getContents());
    }

    @Test
    @DisplayName("Test pit has neighbour")
    public void testPitHasNeighbour(){
        Assert.assertNotNull("Pit must have neighbour", pit.getNeighbour());
    }

    @Test
    @DisplayName("Test if the kalaha is in the right position")
    public void testKalahaIsInRightPosition(){
        Assert.assertEquals("Kalaha must be after 6 moves", true, pit.move(6).getIsKalaha());
    }

    @Test
    @DisplayName("Test if we can remove some stones")
    public void testRemoveAantalSteentjes(){
        pit.removeOne();
        pit.removeOne();
        Assert.assertEquals("Contents of pit after 2 removeOne() method calls should be 2", 2, pit.getContents());
    }

    @Test
    @DisplayName("Test if we can clear a single pit")
    public void testClearPit(){
        pit.clearPit();
        Assert.assertEquals("Pit should be emptied by clearPit() method",0, pit.getContents());
    }

    @Test
    @DisplayName("Test if we can check if a side is completely empty")
    public void testSideIsempty(){
        Assert.assertEquals("Pit should NOT be empty at this point in time", false, pit.sideIsEmpty());
        Pit pit = new NormalPit();
        for(int i = 0; i <= 5; i++){
            pit.move(i).clearPit();
        }
        Assert.assertEquals("The side should be completely empty",true, pit.sideIsEmpty() );
    }

    @Test
    @DisplayName("Test if we can move some pits")
    public void testMove(){
        Assert.assertNotNull("Should return a pit", pit.move(1) );
    }

    @Test
    @DisplayName("Test if our check for an empty row actually works")
    public void testRowIsEmpty() {
        // clean the opposite row
        for(int i = 7; i <= 12; i++){
            this.pit.move(i).clearPit();
        }

        Assert.assertEquals("Should return true if the side is truly empty", true, this.pit.getKalaha().getNeighbour().sideIsEmpty());
    }

    @Test
    @DisplayName("Test playing an actual turn")
    public void testStartMove(){
        Assert.assertEquals(4, this.pit.getContents() );
        this.pit.startMove();
        Assert.assertEquals(0, this.pit.getContents() );
        Assert.assertEquals(5, this.pit.move(1).getContents() );
        /** dit kan niet, omdat die speler dus niet aan de beurt is
         this.pit.move(1).startMove();
         Assert.assertEquals(0, this.pit.move(1).getContents() ); */
    }

    @Test
    @DisplayName("Test if it adds a stone to our own kalaha, and skips enemy kalaha")
    public void testKalahaAddsOrIgnoresStones(){
        //this really needs a better name
        this.pit.move(5).setContents(9);
        this.pit.move(5).startMove();
        Assert.assertEquals(1, this.pit.move(6).getContents() );
    }

    @Test
    @DisplayName("Test if we can get the kalaha")
    public void testGetKalaha(){
        Kalaha kalaha = pit.getKalaha();
        Assert.assertEquals("This pit should be a Kalaha", true, kalaha.getIsKalaha());
    }

    @Test
    @DisplayName("Test if we can get the opposite tile without counting steps")
    public void testGetOpposite(){
        this.pit.move(12).setContents(0);
        Assert.assertEquals("The pit 12 'tiles' away from the first pit should be empty", 0, this.pit.move(12).getContents() );
        Assert.assertEquals("We should find the pit we just emptied", 0, this.pit.getOpposite().getContents() );
    }

    @Test
    @DisplayName("Test if we can capture opposite")
    public void testCapture(){
        this.pit.move(4).setContents(0);
        this.pit.startMove();
        Assert.assertEquals(0, pit.move(4).getContents() );
        // not done
    }


    @Test
    @DisplayName("Test end of game scenario")
    public void testEndOfGame() {
        // clean the opposite row
        for(int i = 7; i <= 12; i++){
            this.pit.move(i).clearPit();
        }

        this.pit.startMove();
        this.pit.move(1).setContents(10);
        this.pit.move(1).getContents();
        // not done
    }

    @Test
    @DisplayName("Test if the Capture method works")
    public void testCaptureOpposite(){
        this.pit.move(4).setContents(0);
        this.pit.startMove();
        Assert.assertEquals("The captured tile should be 0", 0, this.pit.move(8).getContents());
        Assert.assertEquals("The Kalaha should have 5 stones", 5, this.pit.getKalaha().getContents() );
    }

    @Test
    @DisplayName("Test if the continueMove method works")
    public void testContinueMove(){
        this.pit.startMove();

        Assert.assertEquals("Pit 1 should contain 5 stones after startMove", 5, this.pit.move(1).getContents());
        Assert.assertEquals("Pit 2 should contain 5 stones after startMove", 5, this.pit.move(2).getContents());
        Assert.assertEquals("Pit 3 should contain 5 stones after startMove", 5, this.pit.move(3).getContents());
        Assert.assertEquals("Pit 4 should contain 5 stones after startMove", 5, this.pit.move(4).getContents());
    }

    @Test
    @DisplayName("Test if the doEndGameSweep method works")
    public void testDoEndOfGameSweep(){
        for(int i = 7; i <= 12; i++){
            this.pit.move(i).clearPit();
        }

        this.pit.startMove();
        Assert.assertEquals("The Kalaha should have 24 stones after the endGameSweep", 24,this.pit.getKalaha().getContents());
    }

}
