package frc.team5115.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {

    Joystick stick;
    int port;

    public int forwardAxis;
    public int turnAxis;

    double throttle;
    int throttleAxis;
    int throttleIncreaseAxis;
    int throttleDecreaseAxis;
    int throttleIncrease;
    int throttleDecrease;
    int armUp;
    int armDown;

    int ExterminateBind;
    int RevivalBind;

    int SuccOn;
    int SuccOff;

    public Controller(){ this(0); }

    public Controller(int port){
        this.port = port;
        stick = new Joystick(this.port);


        forwardAxis = 0;
        turnAxis = 1;
        throttleIncrease = 1;
        throttleDecrease = 2;
        ExterminateBind = 13;
        RevivalBind = 12;

        //Vacuum
        SuccOn = 9;
        SuccOff = 8;
    }

    public boolean controllerExists(){
        return stick.getButtonCount() > 0;
    }

    public double processThrottle(){
        if(stick.getRawButton(throttleIncrease)){
            throttle += 0.005;
        } else if (stick.getRawButton(throttleDecrease)){
            throttle -= 0.005;
        }

        if (throttle > 1){
            throttle = 1;
        } else if(throttle < 0){
            throttle = 0;
        }

        return throttle;
    }

    public double getForward(){
        return -stick.getRawAxis(forwardAxis);
    }

    public double getTurn(){
        return -stick.getRawAxis(turnAxis);
    }

    public boolean ExterminatePressed(){
        return stick.getRawButton(ExterminateBind);
    }

    public boolean RevivalPressed(){return stick.getRawButton(RevivalBind);}

    public boolean getArmUp(){return stick.getRawButton(armUp);}

    public boolean getArmDown(){return stick.getRawButton(armDown);}

    public boolean getSuccOn() { return stick.getRawButton(SuccOn);}

    public boolean getSuccOff() { return stick.getRawButton(SuccOff);}

    public Joystick getJoy() {return stick;}

}