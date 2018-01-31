import java.util.Scanner;
import java.util.Random;

class SogyoGuess {

    public static void main(String args[]) {

        Boolean win = false;
        int tries = 0;

        Random rand = new Random();
        Scanner in = new Scanner(System.in);

        int randomNr = rand.nextInt(50) + 1;
        System.out.println(randomNr);

        while(!win){
            System.out.println("\n\nTries:");
            System.out.println(tries+"\n\n");

            if( tries >= 10){
                System.out.println("You lose");
                break;
            }

            System.out.println("Take a guess:");
            int guess = in.nextInt();

            if( guess > randomNr ){
                System.out.println("Lower.");
                tries++;
            } else if ( guess < randomNr ){
                System.out.println("Higher.");
                tries++;
            } else {
                System.out.println("You got it!");
                win = true;
            }
        }

    }

}