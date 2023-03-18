package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.Constants.DrivetrainConstants;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class TurnAngleCommand extends PIDCommand {
    public TurnAngleCommand(double angle, DrivetrainSubsystem drivetrain)  {
        super(
            new PIDController(DrivetrainConstants.P, 0.0, 0.0),
            drivetrain::getEncoderPosition,
            angle * (2.0 * Math.PI / DrivetrainConstants.GEARBOX_RATIO) / DrivetrainConstants.WHEEL_CIRCUMFERENCE_INCHES,
            output -> drivetrain.arcadeDrive(0.0, output * DrivetrainConstants.INITIAL_MAX_OUTPUT),
            drivetrain
        );

        drivetrain.resetEncoderPosition();
    }

    @Override
    public boolean isFinished() {
      return getController().atSetpoint();
    }
}
