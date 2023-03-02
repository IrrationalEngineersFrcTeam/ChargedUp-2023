package com.theirrationalengineers.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final DoubleSolenoid doubleSolenoid;
    private final Compressor compressor;
    private boolean isCompressorEnabled;

    public ArmSubsystem() {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 7, 0);
        compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void extendPiston() {
        doubleSolenoid.set(Value.kForward);
        System.out.println("extended piston");
    }

    public void retractPiston() {
        doubleSolenoid.set(Value.kReverse);
        System.out.println("retracted piston");
    }

    public void enableCompressor() {
        isCompressorEnabled = true;
        compressor.enableDigital();
    }

    public void toggleCompressor() {
        if (isCompressorEnabled) {
            compressor.disable();
        } else {
            compressor.enableDigital();
        }
        
        isCompressorEnabled = !isCompressorEnabled;
    }
}
