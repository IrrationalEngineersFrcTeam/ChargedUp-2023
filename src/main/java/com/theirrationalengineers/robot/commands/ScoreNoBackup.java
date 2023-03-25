package com.theirrationalengineers.robot.commands;

import com.theirrationalengineers.robot.Constants.ArmConstants;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreNoBackup extends SequentialCommandGroup {
    public ScoreNoBackup(ArmSubsystem arm, IntakeSubsystem intake) {
        addCommands(
            new MoveArmCommand(ArmConstants.MID_GOAL, arm),
            Commands.waitSeconds(3.0),
            Commands.runOnce(intake::open, intake),
            Commands.waitSeconds(3.0),
            new MoveArmCommand(ArmConstants.OFFSET, arm)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
