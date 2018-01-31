package src.main.java.nl.sogyo.mancala;

public class NormalPit extends Pit {

    public NormalPit() {
        super();
    }
    public NormalPit(NormalPit root, int pitsLeftToFill, Player _player) {
        super(root, pitsLeftToFill, _player);
        this.isKalaha = false;
        this.contents = 4;
    }

    /** Getters */
    @Override
    public Kalaha getKalaha(){
        return this.getNeighbour().getKalaha();
    }
    @Override
    public Pit getOpposite(){
        return this.getNeighbour().getOpposite().getNeighbour();
    }


    /** Methods */
    public void doEndGameSweep(int stones){
        stones += this.getContents();
        this.getNeighbour().doEndGameSweep(stones);
    }
    public void continueMove(int stones){
        this.contents++;
        stones--;
        if (stones > 0) {
            this.getNeighbour().continueMove(stones);
        } else {
            this.capture();

            //alles begint vanuit het vakje dat waar je het uit callt
            // en gaat ook terug naar het vakje waar je het uit callt
            if( this.getKalaha().getNeighbour().sideIsEmpty() ){
                this.getOpposite().getKalaha().getNeighbour().doEndGameSweep();
            }

            this.getPlayer().switchTurns();
        }
    }

}