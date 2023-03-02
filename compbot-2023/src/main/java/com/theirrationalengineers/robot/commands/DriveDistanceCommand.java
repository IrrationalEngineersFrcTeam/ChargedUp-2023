package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistanceCommand extends CommandBase {
    private final DriveSubsystem m_driveSystem;

    public DriveDistanceCommand(DriveSubsystem subsystem) {
        m_driveSystem = subsystem;
        addRequirements(m_driveSystem);
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
