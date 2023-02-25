// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ArmSystem extends SubsystemBase {

    private final DoubleSolenoid doubleSolenoid;
    private final Compressor compressor;
    private boolean isCompressorEnabled;

    public ArmSystem() {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 7, 0);
        compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

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
