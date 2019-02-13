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
    int esketit = 0;


    PID PIDLoop; //PID Loop

    public void robotInit() {
        joy = new ThrustMaster(0);
        budgerobot = new DriveTrain();
        arm = new Arm();
        PIDLoop = new PID("mr. loopy");
        drivedomination = new DriveStateMachine();
        armdomination = new ArmStateMachine();
//      budgerobot.reset();
    }

    public void teleopInit() {
        drivedomination.setState(DriveStateMachine.GO);
        armdomination.setState(ArmStateMachine.INPUT);
    }

    public void teleopPeriodic() {
        drivedomination.update();
        armdomination.update();
        if (joy.ExterminatePressed()) {
            drivedomination.setState(DriveStateMachine.STOP);
            System.out.println("stop pressed");
        }
        if (joy.RevivalPressed()) {
            drivedomination.setState(DriveStateMachine.GO);
            System.out.println("go pressed");
        }
    }

    public void autonomousInit() {
        //instead of resetting the encoders in the robot
        budgerobot.reset();
        esketit = 0;
        targetl = distance() + 10;
        targetr = distance() + 10;
    }

    public void autonomousPeriodic() {
        switch (esketit) {
          case 0:
              budgerobot.driveRight(PIDLoop.getPID(targetl, distance(), budgerobot.returnVelocityRight())*.7);
              budgerobot.driveLeft(PIDLoop.getPID(targetr, distance(), budgerobot.returnVelocityRight())*.7);
              if(PIDLoop.isFinished(.2,20)) {
                  esketit = 1;
              }
              break;
          case 1:
              System.out.println("Done");
              budgerobot.driveLeft(0);
              budgerobot.driveRight(0);//ONE REVOLUTION == 12.5663706144 inches OR 1.0471975512 feet olivia's numbers 0.013
              break;
      }

//      budgerobot.driveLeft(.5);
//      budgerobot.driveRight(.5);

        }
  public void robotPeriodic() {
      System.out.println("this is the distance: "+distance());
  }
    public double distance() {
        System.out.println((budgerobot.returnPositionRight()/ 1440 * 6 * Math.PI / 12));
        return (budgerobot.returnPositionRight() / 1440 * 6 * Math.PI / 12);
    }
    }