package frc.team5115.robot.statemachines;

import frc.team5115.robot.robot.Robot;

public class VacuumStateMachine extends StateMachineBase {
    public static final int ON = 1;
    public static final int INPUT = 2;
    public static final int OFF = 0;
    public void update() {
        switch(state) {
            case INPUT:
                if(Robot.joy.getSuccOn()) {
                    state = ON;
                } else if(Robot.joy.getSuccOff()) {
                    state = OFF;
                }
            case ON:
                Robot.vacSuc.startSucc();
            case OFF:
                Robot.vacSuc.stopSucc();
        }
    }
}
