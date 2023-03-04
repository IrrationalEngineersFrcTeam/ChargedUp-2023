package com.theirrationalengineers.robot;

import com.theirrationalengineers.robot.Constants.ArmConstants;
import com.theirrationalengineers.robot.commands.AutonomousCommand;
import com.theirrationalengineers.robot.commands.RotateRobotCommand;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final ArmSubsystem armSubsystem = new ArmSubsystem(true);
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private final CommandXboxController robotController = new CommandXboxController(0);
  private final SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    configureButtonBindings();
    configureDashboard();
  }

  private void configureButtonBindings() {
    // Increase max output of drivetrain
    robotController.povRight().onTrue(Commands.runOnce(() -> {
      drivetrainSubsystem.increaseMaxOutput();
    }, drivetrainSubsystem));

    // Decrease max output of drivetrain
    robotController.povLeft().onTrue(Commands.runOnce(() -> {
      drivetrainSubsystem.decreaseMaxOutput();
    }, drivetrainSubsystem));

    // Raise arm
    robotController.povUp().onTrue(Commands.runOnce(() -> {
      armSubsystem.raiseArm();
    }, armSubsystem));

    // Lower arm
    robotController.povDown().onTrue(Commands.runOnce(() -> {
      armSubsystem.lowerArm();
    }, armSubsystem));
    
    // Position arm at low node
    robotController.a().onTrue(Commands.runOnce(() -> {
      armSubsystem.positionArm(ArmConstants.LOW_GOAL);
    }, armSubsystem));

    // Position arm at mid node
    robotController.b().onTrue(Commands.runOnce(() -> {
      armSubsystem.positionArm(ArmConstants.MID_GOAL);
    }, armSubsystem));

    // Position arm at high node
    robotController.y().onTrue(Commands.runOnce(() -> {
      armSubsystem.positionArm(ArmConstants.HIGH_GOAL);
    }, armSubsystem));

    // Toggle lower and raise intake
    robotController.x().onTrue(Commands.runOnce(() -> {
      armSubsystem.toggleIntakePosition();
    }, armSubsystem));

    // Grab game piece
    robotController.rightBumper().onTrue(Commands.runOnce(() -> {
      armSubsystem.grabGamePiece();
    }, armSubsystem));

    // Release game piece
    robotController.leftBumper().onTrue(Commands.runOnce(() -> {
      armSubsystem.releaseGamePiece();
    }, armSubsystem));
  }

  private void configureDashboard() {
    // Needs reworking - should be replaced with Shuffleboard
    //ShuffleboardTab rotateRobotTab = Shuffleboard.getTab("Rotate robot");
    //GenericEntry rotationDegreesEntry = rotateRobotTab.add("Rotation degrees", 1).getEntry();

    //SmartDashboard.putData("Rotate robot", new RotateRobotCommand(drivetrainSubsystem, rotationDegreesEntry.getDouble(0)));
    //SmartDashboard.putNumber("Rotation degrees", rotationDegreesEntry.getDouble(0));

    //chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
    //SmartDashboard.putData("Auto Mode", chooser);
  }

  public DrivetrainSubsystem getDrivetrainSubsystem() {
    return drivetrainSubsystem;
  }

  public CommandXboxController getRobotController() {
    return robotController;
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
