package com.theirrationalengineers.robot.subsystems;

import com.theirrationalengineers.robot.Constants.IntakeConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM, 
        IntakeConstants.DOUBLE_SOLENOID_FWD_CHANNEL, 
        IntakeConstants.DOUBLE_SOLENOID_REV_CHANNEL);

    private final Compressor compressor = new Compressor(
        IntakeConstants.COMPRESSOR_MODULE_ID, PneumaticsModuleType.CTREPCM);

    private boolean isLowered;

    public IntakeSubsystem() {
        isLowered = false;

        compressor.enableDigital();
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void raise() {
        isLowered = false;
    }

    public void lower() {
        isLowered = true;
    }

    public void togglePosition() {
        if (isLowered) {
            raise();
        } else {
            lower();
        }

        isLowered = !isLowered;
    }

    public void grabGamePiece() {
        doubleSolenoid.set(Value.kForward);
    }

    public void releaseGamePiece() {
        doubleSolenoid.set(Value.kReverse);
    }
}
