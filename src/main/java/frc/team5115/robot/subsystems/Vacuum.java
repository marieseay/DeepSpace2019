package frc.team5115.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Spark;

import javax.naming.ldap.Control;

public class Vacuum {
//    public Spark mrSucc;
    public TalonSRX mrSucc;

    public Vacuum() {
        mrSucc = new TalonSRX(0);
    }

    public void succ() {
        mrSucc.set(ControlMode.PercentOutput, -.3);
    }

    public void stop() {
        mrSucc.set(ControlMode.PercentOutput, 0);
    }

    public void blow() {mrSucc.set(ControlMode.PercentOutput, .3);}
}