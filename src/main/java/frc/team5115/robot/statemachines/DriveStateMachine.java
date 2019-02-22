package frc.team5115.robot.statemachines;

import frc.team5115.robot.Robot;

public class DriveStateMachine extends StateMachineBase{
    public static final int GO = 1;
    public void update(){
        switch(state){
            case STOP:
                Robot.drivetrain.Exterminate();
                System.out.println("not going");
                break;
            case GO:
                Robot.drivetrain.Drive();
//                System.out.println("drive");
                break;
        }
    }

}
