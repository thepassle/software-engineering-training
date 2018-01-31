import java.util.*;
import java.util.concurrent.TimeUnit;

// EXTREME DIFFICULTY:
// 040000000000001803037060500060010000005306400000090020008050760906800000000000010
// input van de opdracht
// 000820090500000000308040007100000040006402503000090010093004000004035200000700900

public class Sudoku {
    int boardSize;
    ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();

    Sudoku(String input) {
        this.boardSize = 9;
        this.board = this.initialize(input);
    }

    public ArrayList<ArrayList<Cell>> initialize(String input){
        
        char[] inputArr = input.toCharArray();

        // maak in het bord 9 ArrayLists<Cell> aan
        for(int i = 0; i < this.boardSize; i++){
            this.board.add( new ArrayList<Cell>() );
        }

        // loop dan door de input heen
        int index = 0;
        for(int i = 0; i < inputArr.length; i++){
            /** beetje hacky maar 0 % 9 == ook 0, en we willen pas incrementen als ie 
            daadwerkelijk een veelvoud van  9 is: */
            if(i % boardSize == 0){
                if(i != 0) {
                    index+=1;
                }
            }

            // vul het grid met nieuwe cells
            Cell newCell = new Cell();

            newCell.setXpos(index); // row
            newCell.setYpos(i % boardSize); // col
            newCell.setVal(Character.getNumericValue(inputArr[i]));

            this.board.get(index).add( newCell );
        }

        this.display();
        return this.board;
    }

    public boolean isLegalMove(Cell cell, int val) {
        // loop horizontal
        // {1,2,3,4,5,6,7,8,9}
        for (int i = 0; i < this.boardSize; i++) {
            if (this.board.get( cell.getXpos() ).get(i).getVal() == val) {
                return false;
            }
        }

        // loop vertical
        // {1}
        // {2}
        // {3}
        for (int j = 0; j < this.boardSize; j++) {
            if (this.board.get(j).get(cell.getYpos()).getVal() == val) {
                return false;
            }
        }

        // check unit van 3x3
        // {1,2,3}
        // {1,2,3}
        // {1,2,3}
        int boxRow = cell.getXpos() - (cell.getXpos() % 3);
        int boxColumn = cell.getYpos() - (cell.getYpos() % 3);

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (this.board.get(boxRow + j).get(boxColumn + i).getVal() == val) {
                    return false;
                }
            }
        }

        return true;
    }

    public void solve() {
        // start de chain, als het false returned is het onmogelijk om te solven
        if (!backtrackSolve()) {
            System.out.println("impossible");
            System.exit(0);
        } else {
            this.display();
            System.out.println("yay :-)");
        }
    }

    public boolean backtrackSolve() {
        Cell cell = new Cell();
        // this.display();

        // loop door alle cells, en begin bij de eerste lege cell die we vinden
        outerloop: for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                cell = this.board.get(row).get(col);
                if ( cell.getIsEmpty() ) {
                    // als deze cell leeg is, gaan we met deze cell werken

                    // als we niet breaken gaat ie pas solven vanaf de laatste lege tile
                    // wat opzich geen probleem zou moeten zijn
                    break outerloop;
                }
            }
        }

        // als alle vals gevuld zijn, dan hebben we het einde gehaald
        // als de laatste cell NIET leeg is, zijn er dus geen lege cells meer
        if ( !cell.getIsEmpty() ) {
            return true;
        } // else, probeer values

        // loop 1-9
        for (int val = 1; val < this.boardSize+1; val++) {
            // probeer alle mogelijkheden op deze cell en kijk of het legal is
            if ( this.isLegalMove(cell, val) ) {
                cell.setVal(val);

                // probeer de rest van het bord op te lossen met de huidige zet
                // als dat lukt, return true, lukt het niet, zet de val op 0
                // dit is de bottleneck
                if ( this.backtrackSolve() ) {
                    return true;
                }
                // als de move niet uitkomt op een goede oplossing, reset, try again
                cell.setVal(0); 
            }

        }
        return false; //backtrack
    }

    public void display(){
        System.out.println("\n-------------------");
        for(int i = 0; i < this.board.size(); i++){
            for(int j = 0; j < this.board.get(i).size(); j++){
                String icon = this.board.get(i).get(j).getVal() == 0 ? " " : Integer.toString(this.board.get(i).get(j).getVal());
                if(j == 8){
                    System.out.print("|" + icon + "|");
                } else {
                    System.out.print("|" + icon);
                }
            }
            System.out.println("\n-------------------");
        }
    }
}