package src.main.java.nl.sogyo.mancala;

public class Kalaha extends Pit {

    public Kalaha(Pit root, int pitsLeftToFill, Player _player) {
        super(root, pitsLeftToFill, _player);
        this.isKalaha = true;
        this.contents = 0;
    }

    /** Getters */
    @Override
    public Kalaha getKalaha(){
        return this;
    }
    @Override
    public Kalaha getOpposite(){
        return this;
    }
    @Override
    public boolean sideIsEmpty() {
        return true;
    }

    /** Methods */
    public void doEndGameSweep(int stones){
        int enemyStones = this.getOpposite().getContents();
        this.setContents(stones);
        if(enemyStones > stones){
            System.out.println("Enemy won");
        } else {
            System.out.println("You won");
        }
    }
    public void continueMove(int stones){
        if( this.getPlayer().getHasTurn() ){
            this.contents++;
            stones--;
        }

        if( stones > 0 ){
            this.getNeighbour().continueMove(stones);
        }
    }
}
