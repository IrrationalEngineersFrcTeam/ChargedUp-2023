package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistanceCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrainSubsystem;

    public DriveDistanceCommand(DrivetrainSubsystem subsystem) {
        drivetrainSubsystem = subsystem;
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
