package com.theirrationalengineers.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.theirrationalengineers.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private final CANSparkMax leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
    private final CANSparkMax leftRearMotor = new CANSparkMax(2, MotorType.kBrushless);
    private final CANSparkMax rightFrontMotor = new CANSparkMax(3, MotorType.kBrushless);
    private final CANSparkMax rightRearMotor = new CANSparkMax(4, MotorType.kBrushless);
  
    private final MotorControllerGroup leftSide = new MotorControllerGroup(leftFrontMotor, leftRearMotor); 
    private final MotorControllerGroup rightSide = new MotorControllerGroup(rightFrontMotor, rightRearMotor);
  
    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftSide, rightSide);

    public DriveSubsystem() {
        leftFrontMotor.restoreFactoryDefaults();
        leftRearMotor.restoreFactoryDefaults();
        rightFrontMotor.restoreFactoryDefaults();
        rightFrontMotor.restoreFactoryDefaults();

        leftSide.setInverted(false);
        rightSide.setInverted(true);

        leftRearMotor.follow(leftFrontMotor);
        rightRearMotor.follow(rightFrontMotor);

        differentialDrive.setMaxOutput(Constants.DriveConstants.kMaxDriveSpeed);
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
        differentialDrive.arcadeDrive(forwardSpeed, rotationSpeed);
    }

    public CANSparkMax getLeftFrontMotor() {
        return leftFrontMotor;
    }
}
