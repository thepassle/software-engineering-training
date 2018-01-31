import java.util.*;

class Robot {

    private int xPos;
    private int yPos;
    private String direction;
    private int speed;
    
    ArrayList<String> commands = new ArrayList<String>();

    Robot(int xPosTemp, int yPosTemp, String directionTemp, int speedTemp){
        xPos = xPosTemp;
        yPos = yPosTemp;
        direction = directionTemp;
        speed = speedTemp;


        if(speed >= 4 || speed < 0) {
            throw new IllegalArgumentException("Speed must be between 1 and 3.");
        }
    }

    Robot(int xPosTemp, int yPosTemp, String directionTemp){
        xPos = xPosTemp;
        yPos = yPosTemp;
        direction = directionTemp;
        speed = 1;
    }
    
    void turnLeft(){
        commands.add("West");
    }
    void turnRight(){
        commands.add("East");
    }
    void forward(){
        commands.add("North");
    }
    void backward(){
        commands.add("South");
    }

    void execute(){

        for (int i = 0; i < commands.size(); i++){
            System.out.println(commands.get(i));

            switch(commands.get(i)){  
                case "North": 
                    yPos += 1;
                    break;  
                case "South": 
                    yPos -= 1;
                    break;
                case "East":
                    xPos += 1;
                    break;
                case "West":
                    xPos -= 1;
                    break;
            }
        }

        System.out.print("xPos: " + xPos +", yPos: " + yPos + "\n\n");
    }

    void printState(){
        System.out.println("\nRobot is facing: " + direction);
        System.out.print("xPos: " + xPos +", yPos: " + yPos + "\n\n");
    }


}

public class SogyoRobot {
    public static void main(String[] args){
        Robot my_first_robot = new Robot(0, 0, "East");
        Robot my_second_robot = new Robot(1, 0, "West", 3);
       
        my_first_robot.turnLeft();
        my_first_robot.forward();
        my_second_robot.backward();
        // my_first_robot.printState();
        // my_second_robot.printState();
        my_first_robot.execute();

    }
}
