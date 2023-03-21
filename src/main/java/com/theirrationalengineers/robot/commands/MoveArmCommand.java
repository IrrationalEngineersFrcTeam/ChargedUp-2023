package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class MoveArmCommand extends CommandBase {
    private final double goal;
    private final ArmSubsystem arm;

    public MoveArmCommand(double goal, ArmSubsystem subsystem) {
        this.goal = goal;
        arm = subsystem;
        addRequirements(arm);
    }
    
    @Override
    public void initialize() {
        arm.setPosition(goal);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return arm.atSetpoint();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
