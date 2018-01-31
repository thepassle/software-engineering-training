import java.util.*;
import java.util.concurrent.TimeUnit;


public class SudokuSchoon {
    int boardSize;
    ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();

    SudokuSchoon(String input) {
        this.boardSize = 9;
        this.board = this.initialize(input);
    }

    public ArrayList<ArrayList<Cell>> initialize(String input){
        
        char[] inputArr = input.toCharArray();
        for(int i = 0; i < this.boardSize; i++){
            this.board.add( new ArrayList<Cell>() );
        }

        int index = 0;
        for(int i = 0; i < inputArr.length; i++){
            if(i % 9 == 0){
                if(i != 0) {
                    index+=1;
                }
            }

            Cell newCell = new Cell();
            newCell.setXpos(index); 
            newCell.setYpos(i % 9); 
            newCell.setVal(Character.getNumericValue(inputArr[i]));
            this.board.get(index).add( newCell );
        }
        this.display();
        return this.board;
    }

    public boolean isLegalMove(Cell cell, int val) {

        for (int i = 0; i < this.boardSize; i++) {
            if (this.board.get( cell.getXpos() ).get(i).getVal() == val) {
                return false;
            }
        }

        for (int j = 0; j < this.boardSize; j++) {
            if (this.board.get(j).get(cell.getYpos()).getVal() == val) {
                return false;
            }
        }

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

        outerloop: for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                cell = this.board.get(row).get(col);
                if ( cell.getIsEmpty() ) {
                  break outerloop;
                }
            }
        }
        if ( !cell.getIsEmpty() ) {
            return true;
        } 

        for (int val = 1; val < this.boardSize+1; val++) {
            if ( this.isLegalMove(cell, val) ) {
                cell.setVal(val);

                
                if ( this.backtrackSolve() ) {
                    return true;
                }
                cell.setVal(0); 
            }
        }
        return false; 
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