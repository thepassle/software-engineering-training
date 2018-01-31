package src.main.java.nl.sogyo.mancala;

public class Player {
    private Player enemy;
    private String name;
    private boolean hasTurn;

    /** p1 */
    public Player(String _name) {
        this.enemy = new Player("2", this);
        this.name = _name;
        this.hasTurn = true;
    }

    /** p2 */
    public Player(String _name, Player _player1) {
        this.enemy = _player1;
        this.name = _name;
        this.hasTurn = false;
    }

    /** Getters */
    public Player getEnemy(){
        return this.enemy;
    }
    public String getPlayerName(){
        return this.name;
    }
    public boolean getHasTurn(){
        return this.hasTurn;
    }

    /** Methods */
    public void switchTurns() {
        this.hasTurn = !this.hasTurn;
        this.enemy.hasTurn = !this.enemy.hasTurn;
    }
}