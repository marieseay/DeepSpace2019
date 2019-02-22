package frc.team5115.robot.statemachines;

import frc.team5115.robot.Robot;

public class VacuumStateMachine extends StateMachineBase {

    public static final int BLOWING = 2;
    public static final int SUCKING = 1;
    public static final int OFF = 0;

    public void update() {
        switch(state) {
//            case INPUT:
//                Robot.vacSubsystem.stop();
//                if(Robot.joy.getSuccOn()) {
//                    state = SUCKING;
//                }
//                if(Robot.joy.getSuccOff()) {
//                    state = OFF;
//                }
//                break;
            case BLOWING:
                Robot.vacSubsystem.armDown();
                break;
            case SUCKING:
                Robot.vacSubsystem.succ();
//                if(!Robot.joy.getSuccOn()) {
//                    state = INPUT;
//                }
                break;
            case OFF:
                Robot.vacSubsystem.stop();
//                if(!Robot.joy.getSuccOff()) {
//                    state = INPUT;
//                }
                break;
        }
    }
}
