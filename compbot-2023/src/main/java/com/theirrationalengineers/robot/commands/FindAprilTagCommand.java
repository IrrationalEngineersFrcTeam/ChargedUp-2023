package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FindAprilTagCommand extends CommandBase {
    private final VisionSubsystem m_visionSystem;

    public FindAprilTagCommand(VisionSubsystem subsystem) {
        m_visionSystem = subsystem;
        addRequirements(m_visionSystem);
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
