package frc.team5115.robot.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.team5115.robot.PID;
import frc.team5115.robot.joysticks.Controller;
import frc.team5115.robot.joysticks.ThrustMaster;
import frc.team5115.robot.statemachines.ArmStateMachine;
import frc.team5115.robot.statemachines.DriveStateMachine;
import frc.team5115.robot.subsystems.Arm;
import frc.team5115.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  public static DriveTrain budgerobot;
  public static Arm arm;
  public static Controller joy;
  public static DriveStateMachine drivedomination;
  public static ArmStateMachine armdomination;

  double targetl;
  double targetr;

  PID PIDLoop; //PID Loop
  public void robotInit() {
      joy = new ThrustMaster(0);
      budgerobot = new DriveTrain();
      arm = new Arm();
      PIDLoop = new PID("mr. loopy");
  }

  public void teleopInit(){
      drivedomination.setState(DriveStateMachine.GO);
      armdomination.setState(ArmStateMachine.INPUT);
  }

  public void teleopPeriodic() {
      drivedomination.update();
      armdomination.update();
      if(joy.ExterminatePressed()){
          drivedomination.setState(DriveStateMachine.STOP);
      }
  }
  public void autonomousInit(){
      //instead of resetting the encoders in the robot
      targetl = budgerobot.returnPositionLeft() + 5000;
        targetr = budgerobot.returnPositionRight() + 5000;
  }

  public void autonomousPeriodic() {
      budgerobot.driveLeft(PIDLoop.getPID(targetl,
              budgerobot.returnPositionLeft(),
              budgerobot.returnVelocityLeft())*.3);
      budgerobot.driveRight(-PIDLoop.getPID(targetr,
              budgerobot.returnPositionRight(),
              budgerobot.returnVelocityRight())*.3);
      if(PIDLoop.getError() <= 250) {
          System.out.println("Done");
          budgerobot.driveLeft(0);
          budgerobot.driveRight(0);//ONE REVOLUTION == 12.5663706144 inches OR 1.0471975512 feet
        }
  }
//  public void robotPeriodic() {
//      System.out.println("Right position: " + budgerobot.returnPositionRight());
//      System.out.println("Left position: " + budgerobot.returnPositionLeft());
//      System.out.println("Right velocity: " + budgerobot.returnVelocityRight());
//      System.out.println("Left velocity: " + budgerobot.returnVelocityLeft());
//  }
}