package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class Tachometer {
    private final DigitalInput beamBreak = new DigitalInput(0);
    private int breakCount = 0;

    public Tachometer() {

    }

    public void update() {
        if (isBroken()) {
            breakCount += 1;
        }
    }

    public int getBreakCount() {
        return breakCount;
    }

    public boolean isBroken() {
        return !beamBreak.get();
    }
    
}
