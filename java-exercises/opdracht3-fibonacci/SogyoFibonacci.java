import java.util.Scanner;
 
class SogyoFibonacci {

   public static void main(String args[]) {
        int oldNr = 0;
        int newNr = 1;
        int fibonacci;
        int input;

        Scanner in = new Scanner(System.in);

        System.out.println("Enter an integer");
        input = in.nextInt();

        for (int i = 1; i <= 10; i++) {
            fibonacci = oldNr + newNr;

            if(i+1 == input){
                System.out.print("The "+ input + " number in the Fibonacci sequence is: " + fibonacci);
            }

            oldNr = newNr;
            newNr = fibonacci;

        }
   }

}