package com.theirrationalengineers.robot.subsystems;

import com.theirrationalengineers.robot.Constants.IntakeConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final DoubleSolenoid doubleSolenoid;
    private final Compressor compressor;

    public IntakeSubsystem() {
        doubleSolenoid = new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM, 
        IntakeConstants.DOUBLE_SOLENOID_FWD_CHANNEL, 
        IntakeConstants.DOUBLE_SOLENOID_REV_CHANNEL);

        compressor = new Compressor(
            IntakeConstants.COMPRESSOR_MODULE_ID, PneumaticsModuleType.CTREPCM);

        doubleSolenoid.set(Value.kOff); //competition change
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void enableCompressor() {
        compressor.enableDigital();
    }

    public void grabGamePiece() {
        doubleSolenoid.set(Value.kReverse);
    }

    public void releaseGamePiece() {
        doubleSolenoid.set(Value.kForward);
    }
}
