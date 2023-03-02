package com.theirrationalengineers.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.theirrationalengineers.robot.Constants.DriveConstants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private final CANSparkMax leftFrontMotor = new CANSparkMax(DriveConstants.LEFT_FRONT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax leftRearMotor = new CANSparkMax(DriveConstants.LEFT_REAR_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax rightFrontMotor = new CANSparkMax(DriveConstants.RIGHT_FRONT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax rightRearMotor = new CANSparkMax(DriveConstants.RIGHT_REAR_MOTOR_PORT, MotorType.kBrushless);
  
    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    public DriveSubsystem() {
        leftFrontMotor.restoreFactoryDefaults();
        leftRearMotor.restoreFactoryDefaults();
        rightFrontMotor.restoreFactoryDefaults();
        rightRearMotor.restoreFactoryDefaults();

        leftFrontMotor.setInverted(false);
        rightFrontMotor.setInverted(true);

        leftRearMotor.follow(leftFrontMotor);
        rightRearMotor.follow(rightFrontMotor);

        differentialDrive.setMaxOutput(DriveConstants.MAX_DRIVE_SPEED);
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
