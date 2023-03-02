package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateRobotCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private double rotationSpeed;
    private double rotationDegrees;
    private double rotationRadians;
    private double encoderCountsPerRev;
    private double encoderDistancePerPulse;
    private double encoderCountsToRotate;
    private double initialEncoderPos;
    private double currentEncoderPos;

    public RotateRobotCommand(DriveSubsystem subsystem, double rotationDegrees) {
        driveSubsystem = subsystem;
        addRequirements(driveSubsystem);

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
        driveSubsystem.getLeftFrontMotor().getEncoder().setPosition(0);
    }

    @Override
    public void initialize() {
        driveSubsystem.getLeftFrontMotor().getEncoder().setPosition(0);
        System.out.println("RotateRobotCommand started");
        System.out.println("rotation degrees: " + rotationDegrees);
        System.out.println("initial encoder position: " + initialEncoderPos);
        System.out.println("CPR: " + encoderCountsPerRev);
        System.out.println("encoder counts to rotate: " + encoderCountsToRotate);
    }

    @Override
    public void execute() {
        driveSubsystem.arcadeDrive(0, rotationSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        currentEncoderPos = driveSubsystem.getLeftFrontMotor().getEncoder().getPosition();
        System.out.println("current encoder position: " + currentEncoderPos);

        if (currentEncoderPos > encoderCountsToRotate + initialEncoderPos) {
            return true;
        }

        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
