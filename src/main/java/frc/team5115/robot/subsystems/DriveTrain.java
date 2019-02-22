package frc.team5115.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.team5115.robot.Robot;

public class DriveTrain {
    int FrontRightMotorID = 2;
    int FrontLeftMotorID = 1;
    int BackRightMotorID = 3;
    int BackLeftMotorID = 4;
    double throttle;

    TalonSRX FrontRight;
    TalonSRX FrontLeft;
    TalonSRX BackRight;
    TalonSRX BackLeft;


    public static ShuffleboardTab tab = Shuffleboard.getTab("PID Values");
    NetworkTableEntry throttleValue;

    public DriveTrain() {
        FrontRight = new TalonSRX(FrontRightMotorID);
        FrontLeft = new TalonSRX(FrontLeftMotorID);
        BackRight = new TalonSRX(BackRightMotorID);
        BackLeft = new TalonSRX(BackLeftMotorID);


        BackRight.set(ControlMode.Follower, FrontRightMotorID);
        BackLeft.set(ControlMode.Follower, FrontLeftMotorID);

        FrontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,5);
        BackLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,5);
        throttleValue = tab.add("throttle2", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    }
    public void Exterminate(){
        FrontRight.set(ControlMode.PercentOutput, 0);
        FrontLeft.set(ControlMode.PercentOutput, 0);
    }
    public void Drive() {
        throttle = Robot.joy.processThrottle();
        double xValue = Robot.joy.getTurn();
        double yValue = Robot.joy.getForward();
        FrontRight.set(ControlMode.PercentOutput, (yValue + xValue) * throttle);
        FrontLeft.set(ControlMode.PercentOutput, (-yValue + xValue) * throttle);
        throttleValue.setDouble(throttle);
    }
    public double returnVelocityLeft(){
//        System.out.println("Velocity Left: "+ BackLeft.getSelectedSensorVelocity(0));
        return BackLeft.getSelectedSensorVelocity(0);
    }
    public double returnVelocityRight(){
//        System.out.println("Velocity Right: "+FrontRight.getSelectedSensorVelocity(0));
        return FrontRight.getSelectedSensorVelocity(0);
    }

    public double returnPositionLeft(){
//        System.out.println("Position Left: "+-BackLeft.getSelectedSensorPosition(0));
        return BackLeft.getSelectedSensorPosition(0);
    }
    public double returnPositionRight(){
  //      System.out.print("Position Right: "+FrontRight.getSelectedSensorPosition(0));
        return FrontRight.getSelectedSensorPosition(0);
    }

    public void reset(){
        FrontRight.setSelectedSensorPosition(0);
        FrontLeft.setSelectedSensorPosition(0);
    }
    public void driveLeft(double leftValue) {
        BackLeft.set(ControlMode.PercentOutput, leftValue);
    }
    public void driveRight(double rightValue) {
        BackRight.set(ControlMode.PercentOutput, rightValue);
    }


}
