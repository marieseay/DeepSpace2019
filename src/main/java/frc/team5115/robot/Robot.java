package frc.team5115.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team5115.robot.PID;
import frc.team5115.robot.commands.SpecialArmVacuum;
import frc.team5115.robot.commands.VacuumCommand;
import frc.team5115.robot.joysticks.Controller;
import frc.team5115.robot.joysticks.ThrustMaster;
import frc.team5115.robot.statemachines.ArmStateMachine;
import frc.team5115.robot.statemachines.DriveStateMachine;
import frc.team5115.robot.statemachines.VacuumStateMachine;
import frc.team5115.robot.subsystems.Arm;
import frc.team5115.robot.subsystems.DriveTrain;
import frc.team5115.robot.subsystems.Vacuum;

public class Robot extends TimedRobot {
    public static DriveTrain drivetrain;
    public static Arm arm;
    public static Controller joy;
    public static Vacuum vacSubsystem;
    public static DriveStateMachine driveStateMachine;
    public static ArmStateMachine armStateMachine;
    public static VacuumStateMachine vacStateMachine;

    double targetl;
    double targetr;
    int esketit = 0;


    PID PIDLoop;

    public void robotInit() {
        /**COMMANDS**/
        joy = new ThrustMaster(0);
        JoystickButton fifteen = new JoystickButton(joy.getJoy(), 15);
        fifteen.toggleWhenPressed(new VacuumCommand());

        JoystickButton sixteen = new JoystickButton(joy.getJoy(), 16);
        sixteen.toggleWhenPressed(new SpecialArmVacuum());


        drivetrain = new DriveTrain();
        arm = new Arm();
        vacSubsystem = new Vacuum();
        PIDLoop = new PID("Value");
        driveStateMachine = new DriveStateMachine();
        armStateMachine = new ArmStateMachine();
        vacStateMachine = new VacuumStateMachine();
    }

    public void teleopInit() {
        driveStateMachine.setState(DriveStateMachine.GO);
        armStateMachine.setState(ArmStateMachine.INPUT);
//        vacStateMachine.setState(VacuumStateMachine.INPUT);
    }

    public void teleopPeriodic() {
        driveStateMachine.update();
        armStateMachine.update();
        vacStateMachine.update();
        Scheduler.getInstance().run();
        if (joy.ExterminatePressed()) {
            driveStateMachine.setState(DriveStateMachine.STOP);
            vacStateMachine.setState(VacuumStateMachine.STOP);
            System.out.println("stop pressed");
        }
        if (joy.RevivalPressed()) {
            driveStateMachine.setState(DriveStateMachine.GO);
            vacStateMachine.setState(VacuumStateMachine.OFF);
            System.out.println("revived");
        }
    }

    public void autonomousInit() {
        drivetrain.reset();
        esketit = 0;
        targetl = distance() + 10;
        targetr = distance() + 10;
    }

    public void autonomousPeriodic() {
        switch (esketit) {
          case 0:
              drivetrain.driveRight(PIDLoop.getPID(targetl, distance(), drivetrain.returnVelocityRight())*.7);
              drivetrain.driveLeft(PIDLoop.getPID(targetr, distance(), drivetrain.returnVelocityRight())*.7);
              if(PIDLoop.isFinished(.2,20)) {
                  esketit = 1;
              }
              break;
          case 1:
              System.out.println("Goal Met.");
              drivetrain.driveLeft(0);
              drivetrain.driveRight(0);//ONE REVOLUTION == 12.5663706144 inches OR 1.0471975512 feet olivia's numbers 0.013
              break;
      }
        /** FOR DEBUGGING **/
//      drivetrain.driveLeft(.5);
//      drivetrain.driveRight(.5);

        }
        /** FOR PID TUNING **/
//  public void robotPeriodic() {
//      System.out.println("this is the distance: "+distance());
//  }
    public double distance() {
        System.out.println((drivetrain.returnPositionRight()/ 1440 * 6 * Math.PI / 12));
        return (drivetrain.returnPositionRight() / 1440 * 6 * Math.PI / 12);
    }
    }