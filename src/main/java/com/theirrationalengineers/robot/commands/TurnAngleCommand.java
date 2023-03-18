package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.Constants.DrivetrainConstants;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class TurnAngleCommand extends PIDCommand {
    public TurnAngleCommand(double angle, DrivetrainSubsystem drivetrain)  {
        super(
            new PIDController(DrivetrainConstants.P, 0.0, 0.0),
            drivetrain::getEncoderPositionRad,
            angle * (2.0 * Math.PI / DrivetrainConstants.GEARBOX_RATIO) / DrivetrainConstants.WHEEL_CIRCUMFERENCE_INCHES,
            output -> drivetrain.arcadeDrive(0.0, output * DrivetrainConstants.INITIAL_MAX_OUTPUT),
            drivetrain
        );

        drivetrain.resetEncoderPosition();

        // Set the controller to be continuous (because it is an angle controller)
        getController().enableContinuousInput(Math.toRadians(-180), Math.toRadians(180));
        // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
        // setpoint before it is considered as having reached the reference
        getController().setTolerance(Math.toRadians(5), 10);
    }

    @Override
    public boolean isFinished() {
      return getController().atSetpoint();
    }
}
