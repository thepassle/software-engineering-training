import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import src.main.java.nl.sogyo.mancala.Kalaha;
import src.main.java.nl.sogyo.mancala.NormalPit;

public class KalahaTest {
    public Kalaha kalaha;
    public NormalPit pit;

    @Before
    public void setup(){
        this.pit = new NormalPit();
        this.kalaha = pit.getKalaha();
    }

    @Test
    @DisplayName("Test if the kalaha returns its amount of stones")
    public void testKalahaContent(){
        Assert.assertEquals("Kalaha should have 0 stones", 0, kalaha.getContents() );
    }

    @Test
    @DisplayName("Test if the kalaha can carry stones to the next pit")
    public void testKalahaCanMove(){
        pit.move(5).startMove();

        Assert.assertEquals("Kalaha should have 1 stone", 1, pit.move(6).getContents() );
        Assert.assertEquals("Next pit should have 1 stone", 5, pit.move(7).getContents() );

    }

}
