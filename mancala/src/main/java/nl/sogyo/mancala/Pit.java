package src.main.java.nl.sogyo.mancala;

public abstract class Pit {
    private int boardSize = 13;
    protected int contents;
    protected boolean isKalaha;

    protected Pit neighbour;
    protected Player player;

    public Pit() {
        this.player = new Player("1");
        this.neighbour = new NormalPit((NormalPit) this, this.boardSize, this.player);
        this.isKalaha = false;
        this.contents = 4;
    }
    public Pit(Pit root, int pitsLeftToFill, Player _player){
        this.player = _player;
        this.isKalaha = false;
        this.contents = 4;

        if( pitsLeftToFill == 8 ){
            _player = _player.getEnemy();
        }

        pitsLeftToFill--;

        if( pitsLeftToFill >= 1 ) {
            if ( pitsLeftToFill == 8 ){
                this.neighbour = new Kalaha(root, pitsLeftToFill, _player);
            } else if (pitsLeftToFill == 1) {
                this.neighbour = new Kalaha(root, pitsLeftToFill, _player);
                this.neighbour.setNeighbour(root);
            } else {
                this.neighbour = new NormalPit((NormalPit) root, pitsLeftToFill, _player);
            }
        }
    }

    /** Abstract Methods */
    public abstract Kalaha getKalaha();
    public abstract Pit getOpposite();
    public abstract void doEndGameSweep(int stones);
    public abstract void continueMove(int stones);

    /** Getters */
    public int getContents(){
        return this.contents;
    }
    public Pit getNeighbour(){
        return this.neighbour;
    }
    public Player getPlayer() {
        return this.player;
    }
    public boolean getIsKalaha() {
        return this.isKalaha;
    }
    public boolean sideIsEmpty() {
        return (this.contents != 0) ? false : this.neighbour.sideIsEmpty();
    }

    /** Setters */
    public void setNeighbour(Pit _pit){
        this.neighbour = _pit;
    }
    public void setContents(int _amount){
        this.contents = _amount;
    }
    public void removeOne(){
        this.contents--;
    }
    public void clearPit(){
        this.contents = 0;
    }

    /** Methods */
    public void capture(){
        if( this.getContents() == 1 && this.getOpposite().getContents() > 0) {
            int capturedStones = this.getOpposite().getContents() + this.getContents();
            this.getOpposite().clearPit();
            this.clearPit();
            this.getKalaha().setContents(capturedStones);
        }
    }
    public void startMove(){
        if( this.getPlayer().getHasTurn() & !this.isKalaha ) {
            int stones = this.getContents();
            this.clearPit();
            this.getNeighbour().continueMove(stones);
        }
    }
    public void doEndGameSweep(){
        int stones = this.getContents();
        this.getNeighbour().doEndGameSweep(stones);
    }

    /** Utilities */
    public Pit move(int steps){
        return (steps > 0) ? this.getNeighbour().move(--steps) : this;
    }
}