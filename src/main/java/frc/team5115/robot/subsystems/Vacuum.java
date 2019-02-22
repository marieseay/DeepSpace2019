package frc.team5115.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Spark;

import javax.naming.ldap.Control;

public class Vacuum {

    public TalonSRX mrSucc;
    public Vacuum() {
        mrSucc = new TalonSRX(0);
    }

    public void succ() {
        mrSucc.set(ControlMode.PercentOutput, 1);
    }

    public void stop() {
        mrSucc.set(ControlMode.PercentOutput, 0);
    }

    /**TESTING PURPOSES ONLY**/
    public void armDown() {
        mrSucc.set(ControlMode.PercentOutput, -.5);
    }
}