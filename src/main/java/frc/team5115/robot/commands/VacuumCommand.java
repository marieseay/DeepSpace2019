package frc.team5115.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5115.robot.Robot;

public class VacuumCommand extends Command {

    protected boolean isFinished() {return false;}

    protected void initialize() {
        System.out.println("things have started");
        Robot.vacStateMachine.setState(Robot.vacStateMachine.SUCKING);
    }

    protected void end() {
        System.out.println("terminated");
        Robot.vacStateMachine.setState(Robot.vacStateMachine.STOP);
    }
}