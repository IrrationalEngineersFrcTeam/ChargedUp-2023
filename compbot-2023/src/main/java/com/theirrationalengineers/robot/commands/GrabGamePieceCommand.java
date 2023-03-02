package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class GrabGamePieceCommand extends CommandBase {
    private final ArmSubsystem m_armSystem;

    public GrabGamePieceCommand(ArmSubsystem subsystem) {
        m_armSystem = subsystem;
        addRequirements(m_armSystem);
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
