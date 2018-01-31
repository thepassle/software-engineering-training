import java.util.Scanner;

//Player/computer turn order could be better.
class Game {

    int turn = 0;
    int matches = 11;

    int player = 1;
    int players;

    Scanner in = new Scanner(System.in);

    void startGame() {

        while(true){
            System.out.println("How many players will be playing? (1/2)");
            players = in.nextInt();

            if(players > 2 || players <= 0){
                System.out.println("Please enter a valid number.");
            } else {
                break;
            }
        }
        
        while(true){
            System.out.println("Starting game");

            handleInput();


            if(matches <= 0){
                if(player == 1){
                    System.out.println("Player 2 has lost.");
                    break;
                } else {
                    System.out.println("Player 1 has lost.");
                    break;
                }
            }

        }
    }

    int computerMove(int matches){
        int move = 0;

        switch(matches){  
            case 11: 
                move = 1;
                break;  
            case 10: 
                move = 4;
                break; 
            case 9: 
                move = 3;
                break;  
            case 8: 
                move = 2;
                break;  
            case 7: 
                move = 1;
                break;  
            case 6: 
                move = 1;
                break;  
            case 5: 
                move = 4;
                break;  
            case 4: 
                move = 3;
                break;  
            case 3: 
                move = 2;
                break;  
            case 2: 
                move = 1;
                break;  
            case 1: 
                move = 1;
                break;  
        }  

        return move;
    }

    void handleInput(){
        int move = 0;

        if(players == 1 && (turn % 2 == 0) ){
            move = computerMove(matches);
            System.out.println("\nComputer took: " + move + " matches.");
            matches = matches-move;
            turn += 1;

            if(matches <= 0){
                System.out.println("\nComputer lost.");
                System.exit(0);
            }
        }

        System.out.println("\nThere are " + matches +" matches.\nPlayer "+player+", how many do you want to take?");
        move = in.nextInt();

        if(move > 4 || move > matches){
            System.out.println("No");
        } else {
            matches = matches-move;
            turn += 1;
            player = (turn % 2 == 0) ? 1 : 2;
        }
    }

}


public class SogyoNim {
    public static void main(String[] args){
        Game game = new Game();
        game.startGame();
    }
}