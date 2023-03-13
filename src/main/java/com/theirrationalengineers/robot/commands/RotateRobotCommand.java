package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateRobotCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrainSubsystem;
    private double rotationSpeed;
    private double rotationDegrees;
    private double rotationRadians;
    private double encoderCountsPerRev;
    private double encoderDistancePerPulse;
    private double encoderCountsToRotate;
    private double initialEncoderPos;
    private double currentEncoderPos;

    public RotateRobotCommand(DrivetrainSubsystem subsystem, double rotationDegrees) {
        drivetrainSubsystem = subsystem;
        addRequirements(drivetrainSubsystem);

        if (Math.signum(rotationDegrees) < 0) {
            rotationSpeed = 1.0;
        } else if (Math.signum(rotationDegrees) > 0) {
            rotationSpeed = -1.0;
        }

        this.rotationDegrees = rotationDegrees;
        rotationRadians = rotationDegrees * Math.PI / 180;
        encoderCountsPerRev = 42;
        encoderDistancePerPulse = 2.0 * Math.PI / encoderCountsPerRev;
        encoderCountsToRotate = rotationRadians / encoderDistancePerPulse * 145 / 180;
        initialEncoderPos = 0;
        drivetrainSubsystem.getFrontLeftMotor().getEncoder().setPosition(0);
    }

    @Override
    public void initialize() {
        drivetrainSubsystem.getFrontLeftMotor().getEncoder().setPosition(0);
        System.out.println("RotateRobotCommand started");
        System.out.println("rotation degrees: " + rotationDegrees);
        System.out.println("initial encoder position: " + initialEncoderPos);
        System.out.println("CPR: " + encoderCountsPerRev);
        System.out.println("encoder counts to rotate: " + encoderCountsToRotate);
    }

    @Override
    public void execute() {
        drivetrainSubsystem.curvatureDrive(0, rotationSpeed, true);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.curvatureDrive(0, 0, true);
    }

    @Override
    public boolean isFinished() {
        currentEncoderPos = drivetrainSubsystem.getFrontLeftMotor().getEncoder().getPosition();

        System.out.println("current encoder position: " + currentEncoderPos);

        return currentEncoderPos > encoderCountsToRotate + initialEncoderPos;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
