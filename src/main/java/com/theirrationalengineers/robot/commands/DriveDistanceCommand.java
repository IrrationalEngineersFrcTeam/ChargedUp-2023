package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.Constants.DrivetrainConstants;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class DriveDistanceCommand extends PIDCommand {
    public DriveDistanceCommand(double distanceInches, DrivetrainSubsystem drivetrain) {
        super(
            new PIDController(DrivetrainConstants.P, 0.0, 0.0),
            drivetrain::getEncoderPosition,
            distanceInches * DrivetrainConstants.GEARBOX_RATIO / DrivetrainConstants.WHEEL_CIRCUMFERENCE_INCHES,
            output -> drivetrain.arcadeDrive(output * DrivetrainConstants.INITIAL_MAX_OUTPUT, 0.0),
            drivetrain
        );
    }

    @Override
    public boolean isFinished() {
      return getController().atSetpoint();
    }
}
