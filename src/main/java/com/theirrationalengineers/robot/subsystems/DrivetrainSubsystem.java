package com.theirrationalengineers.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.theirrationalengineers.robot.Constants.DrivetrainConstants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    private final CANSparkMax leftLeaderMotor = new CANSparkMax(
        DrivetrainConstants.LEFT_LEADER_MOTOR_ID, MotorType.kBrushless);

    private final CANSparkMax leftFollowerMotor = new CANSparkMax(
        DrivetrainConstants.LEFT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);

    private final CANSparkMax rightLeaderMotor = new CANSparkMax(
        DrivetrainConstants.RIGHT_LEADER_MOTOR_ID, MotorType.kBrushless);

    private final CANSparkMax rightFollowerMotor = new CANSparkMax(
        DrivetrainConstants.RIGHT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);

    private final DifferentialDrive differentialDrive = new DifferentialDrive(
        leftLeaderMotor, rightLeaderMotor);
    
    private final RelativeEncoder encoder = leftLeaderMotor.getEncoder();
        
    private double maxOutput;

    public DrivetrainSubsystem() {
        maxOutput = DrivetrainConstants.INITIAL_MAX_OUTPUT;

        leftLeaderMotor.restoreFactoryDefaults();
        leftFollowerMotor.restoreFactoryDefaults();
        rightLeaderMotor.restoreFactoryDefaults();
        rightFollowerMotor.restoreFactoryDefaults();

        leftLeaderMotor.setInverted(false);
        rightLeaderMotor.setInverted(true);

        leftFollowerMotor.follow(leftLeaderMotor);
        rightFollowerMotor.follow(rightLeaderMotor);

        differentialDrive.setMaxOutput(DrivetrainConstants.INITIAL_MAX_OUTPUT);
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void arcadeDrive(double forward, double rotation) {
        differentialDrive.arcadeDrive(forward, rotation);
        SmartDashboard.putNumber("forward", forward);
        SmartDashboard.putNumber("rotation", rotation);
    }

    public void tankDrive(double left, double right) {
        differentialDrive.tankDrive(left, right);
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

    public CANSparkMax getLeftLeaderMotor() {
        return leftLeaderMotor;
    }
    
    public double getEncoderPosition() {
        return encoder.getPosition();
    }
}
