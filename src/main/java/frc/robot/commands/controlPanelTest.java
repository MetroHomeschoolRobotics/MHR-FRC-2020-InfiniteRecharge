package frc.robot.commands;

import java.util.*;

public class controlPanelTest {

    static int halfRevCounter;
    static int transitionCounter;
    static int currentColor = 0;
    static int lastColor = 0;
    static int controlPanelDeployDirection=0;
    static int taskToDo = 0;
    static int deployCommand; //test bit to simulate signal from controller
    static int taskCompleted = 0;
    static int myCommand;
    static Scanner console = new Scanner(System.in);
    static int allDone = 0;
    static int firstColor = 1;
    static int myTargetColor = 1;
    static int modifiedTargetColor=3;

    public static int deployControlPanelControl(int direction) {
        // drive the control panel control wheel into position
        if (direction == 1) {
            // move the control controller into position
            initiatePixy(); // should be a 1, 2, 3, or 5;
        }
        else if (direction == 2) {
            // move the motor to home
        }
        else {
            // do nothing for all other cases
        }
        return 1;
    }

    public static void initiatePixy() {
        // simulate the pixy getting the current color
       // myBlock = (int) (4 * (Math.random())) + 1;
        firstColor = getBlock();
        currentColor = firstColor;
        lastColor = firstColor;
        halfRevCounter = 0;
        transitionCounter = 0;
        myTargetColor = (int) (4 * (Math.random())) + 1;
    }

    public static int getBlock() {
        // simulate the pixy getting the current color
        int myBlock;
        myBlock = (int) (4 * (Math.random())) + 1;
        return myBlock;
    }

    public static int turnThreeTimes() {
        // simulate the pixy getting the current color
        System.out.println("In the method");
        int inPosition = 0;
        while (/*transitionCounter >= 1 && */halfRevCounter < 7){
            // turn the motor on
            currentColor = getBlock();
            System.out.println(currentColor);
            if (currentColor != lastColor) {
                transitionCounter++;
                lastColor = currentColor;
                if (currentColor == firstColor) {
                    halfRevCounter++;
                    System.out.println("Rev Count is " + halfRevCounter);
                }
            }

        }
        System.out.println("Finished");
        // stop the motor
        inPosition = 1;
        return inPosition;
    }


    public static int positionPanel() {
        // simulate the pixy getting the current color
        System.out.println("In the position method");
        int inPosition = 0;
        while (/*transitionCounter >= 1 && */inPosition != 1) {
            switch (myTargetColor){
                case 1: 
                    modifiedTargetColor=3;
                    break;
                case 2: 
                    modifiedTargetColor=4;
                    break;
                case 3: 
                    modifiedTargetColor=1;
                    break;
                case 4: 
                    modifiedTargetColor=2;
                    break;
                default: 
                    modifiedTargetColor=myTargetColor + 2;
                    break;
            }

            // turn the motor on
            currentColor = getBlock();
            System.out.println(currentColor);
            if (currentColor == modifiedTargetColor) {
                inPosition=1;
                System.out.println("Target color is " + myTargetColor + " and I am on " + currentColor);
                
            }

        }
        System.out.println("Finished");
        // stop the motor
        inPosition = 1;
        return inPosition;
    }



    public static void runIt (int depCom) {
        // deploy the control panel controller deployControlPanelControl();

        // choose the task to do... turns or position

        // get the initial color to establish current position

        // do appropriate task
        // turn the wheel 3.5 to 4.5 turns
        // position the wheel at the appropriate color
        // turn the wheel to get the color rate and establish the location of the
        // transitions.
        // calculate where to put the wheel
        // reposition the wheel

        // retract the controller
        deployCommand = depCom;

        //if (deployCommand!=4){

        switch (deployCommand) {
            case 1: taskCompleted = deployControlPanelControl(1);
                if (taskCompleted == 1) {
                    System.out.println("Deployed");
                    taskCompleted = 0;
                }
                break;
    
            case 2: System.out.println("Turn 3 times");
                taskCompleted = turnThreeTimes();
                taskCompleted = deployControlPanelControl(2);
                if (taskCompleted == 1) {
                    System.out.println("Retract");
                    taskCompleted = 0;
                    allDone = 1;
                    System.out.println("All Done");
                }
                break;
        
            case 3: System.out.println("Position the Wheel");
                taskCompleted = positionPanel();       
                taskCompleted = deployControlPanelControl(2);
                    if (taskCompleted == 1) {
                        System.out.println("Retract");
                        taskCompleted = 0;
                        allDone = 1;
                        System.out.println("All Done");
                    }
                break;
            
            case 4: taskCompleted = deployControlPanelControl(2);
                if (taskCompleted == 1) {
                    System.out.println("Retract");
                    taskCompleted = 0;
                    allDone = 1;
                }
                break;
            case 5: System.out.println("All Done");
                allDone = 1;
                break;        
            default: System.out.println("Something Else");
                break;
        }
    //}

    }

    }
