package com.theirrationalengineers.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.theirrationalengineers.robot.Constants.DrivetrainConstants;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    private final CANSparkMax frontLeftMotor = new CANSparkMax(
        DrivetrainConstants.FRONT_LEFT_MOTOR_ID, MotorType.kBrushless);

    private final CANSparkMax rearLeftMotor = new CANSparkMax(
        DrivetrainConstants.REAR_LEFT_MOTOR_ID, MotorType.kBrushless);

    private final CANSparkMax frontRightMotor = new CANSparkMax(
        DrivetrainConstants.FRONT_RIGHT_MOTOR_ID, MotorType.kBrushless);

    private final CANSparkMax rearRightMotor = new CANSparkMax(
        DrivetrainConstants.REAR_RIGHT_MOTOR_ID, MotorType.kBrushless);

    private final DifferentialDrive differentialDrive = new DifferentialDrive(
        frontLeftMotor, frontRightMotor);

    private final SlewRateLimiter forwardFilter = new SlewRateLimiter(
        DrivetrainConstants.SLEW_RATE_LIMIT);

    private final SlewRateLimiter rotationFilter = new SlewRateLimiter(
        DrivetrainConstants.SLEW_RATE_LIMIT);
        
    private double maxOutput;

    public DrivetrainSubsystem() {
        maxOutput = DrivetrainConstants.INITIAL_MAX_OUTPUT;

        frontLeftMotor.restoreFactoryDefaults();
        rearLeftMotor.restoreFactoryDefaults();
        frontRightMotor.restoreFactoryDefaults();
        rearRightMotor.restoreFactoryDefaults();

        frontLeftMotor.setInverted(false);
        frontRightMotor.setInverted(true);

        rearLeftMotor.follow(frontLeftMotor);
        rearRightMotor.follow(frontRightMotor);

        differentialDrive.setMaxOutput(DrivetrainConstants.INITIAL_MAX_OUTPUT);
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void drive(double forward, double rotation) {
        differentialDrive.arcadeDrive(forward, rotation);
    }

    public void increaseMaxOutput() {
        if (maxOutput < 1.0) {
            maxOutput += 0.05;
            differentialDrive.setMaxOutput(maxOutput);
        }
    }

    public void decreaseMaxOutput() {
        if (maxOutput > 0.1) {
            maxOutput -= 0.05;
            differentialDrive.setMaxOutput(maxOutput);
        }
    }

    public CANSparkMax getFrontLeftMotor() {
        return frontLeftMotor;
    }
}
