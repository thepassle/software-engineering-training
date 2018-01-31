import java.util.Random;
import java.util.*;

class SogyoList {

    public static void main(String args[]) {
        Random rand = new Random();

        int[] anArray;

        anArray = new int[10];

        for (int i = 0; i < 10; i++) {
            anArray[i] = rand.nextInt(100) + 1; 
        }

        printList(anArray);

        max(anArray);
        findSecondSmallest(anArray);

        filterEvenNumbers(anArray);
        split(anArray);
        bubbleSort(anArray);

    }

    public static void bubbleSort(int[] anArray){     
        int arrlen = anArray.length;

        //loop door de array
        for (int i = 0; i < arrlen-1; i++){
            System.out.println("i: " + i);

            for (int j = 0; j < arrlen-i-1; j++){
                System.out.println("    j: " + j);


                if (anArray[j] > anArray[j+1]){
                    int temp = anArray[j];

                    //switcheroo
                    anArray[j] = anArray[j+1];
                    anArray[j+1] = temp;
                }

            }
        }
        
        System.out.println("BubbledSorted: ");
        for(int i = 0; i < anArray.length; i++){
            System.out.println(anArray[i]);
        }
    }

    public static void split(int[] anArray){     
        System.out.println("Filtered even numbers:");
   
        List<Integer> twos = new ArrayList<Integer>();
        List<Integer> threes = new ArrayList<Integer>();
        List<Integer> fives = new ArrayList<Integer>();
        List<Integer> leftover = new ArrayList<Integer>();

        for(int i = 0; i < anArray.length; i++){
            if(anArray[i] % 2 == 0){
                twos.add(anArray[i]);
            } else if(anArray[i] % 3 == 0){
                threes.add(anArray[i]);
            } else if(anArray[i] % 5 == 0){
                fives.add(anArray[i]);
            } else {
                leftover.add(anArray[i]);
            }
        }

        System.out.println("Twos:");
        for(int i = 0; i < twos.size(); i++){
            System.out.println(twos.get(i));
        }

        System.out.println("Threes:");
        for(int i = 0; i < threes.size(); i++){
            System.out.println(threes.get(i));
        }

        System.out.println("Fives:");
        for(int i = 0; i < fives.size(); i++){
            System.out.println(fives.get(i));
        }

        System.out.println("Leftover:");
        for(int i = 0; i < leftover.size(); i++){
            System.out.println(leftover.get(i));
        }
    }

    public static void filterEvenNumbers(int[] anArray){     
        System.out.println("Filtered even numbers:");
   
        for(int i = 0; i < anArray.length; i++){
            if(anArray[i] % 2 == 0){
                System.out.println(anArray[i]);
            }
        }
    }

    public static int findSecondSmallest(int[] anArray) {

        int smallest = max(anArray);
        int smaller = max(anArray);

        for (int i = 0; i < anArray.length; i++) {

            if (anArray[i] < smallest) {
                smaller = smallest;
                smallest = anArray[i];

            } else if (anArray[i] < smaller && anArray[i] > smallest) {
                smaller = anArray[i];
            }
        }

        System.out.println("Smallest is " + smallest);
        System.out.println("Smaller is " + smaller);
        System.out.println("\n");

        //return two values?
        return smaller;
    }


    public static int max(int[] anArray){
        int highest = 0;
        
        for(int i = 0; i < anArray.length; i++){
            
            if(anArray[i] > highest){
                highest = anArray[i];
            }
        }
        System.out.println("The highest nr is:");
        System.out.println(highest);
        System.out.println("\n");

        return highest;
    }

    public static int min(int[] anArray){
        int lowest = max(anArray);
        
        for(int i = 0; i < anArray.length; i++){
            
            if(anArray[i] < lowest){
                lowest = anArray[i];
            }
        }
        System.out.println("The lowest nr is:");
        System.out.println(lowest);
        System.out.println("\n");

        return lowest;
    }


    public static void printList(int[] anArray){
        System.out.println("Entire array:");
        for(int i = 0; i < anArray.length; i++){
            System.out.println(anArray[i]);
        }
        System.out.println("\n");
    }
}


// http://www.java2novice.com/images/bubblesort.jpeg