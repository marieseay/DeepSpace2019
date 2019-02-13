package frc.team5115.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

public class Vacuum {
    public Spark mrSucc;

    public Vacuum() {
        mrSucc = new Spark(1);
    }

    public void startSucc() {
        mrSucc.set(1.0);
    }

    public void stopSucc() {
        mrSucc.set(0);
    }
}
