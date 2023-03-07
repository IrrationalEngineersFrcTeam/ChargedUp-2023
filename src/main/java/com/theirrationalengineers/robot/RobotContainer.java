package com.theirrationalengineers.robot;

import com.theirrationalengineers.robot.Constants.ArmConstants;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final ArmSubsystem arm = new ArmSubsystem(true);
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final CommandXboxController robotController = new CommandXboxController(0);
  private final SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    configureButtonBindings();
    configureDashboard();
  }

  private void configureButtonBindings() {
    // Increase max output of drivetrain
    robotController.povRight().onTrue(Commands.runOnce(
            drivetrain::increaseMaxOutput, drivetrain));

    // Decrease max output of drivetrain
    robotController.povLeft().onTrue(Commands.runOnce(
            drivetrain::decreaseMaxOutput, drivetrain));

    // Raise arm
    robotController.povUp().onTrue(Commands.runOnce(
            arm::raiseArm, arm));

    // Lower arm
    robotController.povDown().onTrue(Commands.runOnce(
            arm::lowerArm, arm));
    
    // Position arm at low node
    robotController.a().onTrue(Commands.runOnce(() -> {
      arm.positionArm(ArmConstants.LOW_GOAL);
    }, arm));

    // Position arm at mid node
    robotController.b().onTrue(Commands.runOnce(() -> {
      arm.positionArm(ArmConstants.MID_GOAL);
    }, arm));

    // Position arm at high node
    robotController.y().onTrue(Commands.runOnce(() -> {
      arm.positionArm(ArmConstants.HIGH_GOAL);
    }, arm));

    // Toggle lower and raise intake
    robotController.x().onTrue(Commands.runOnce(
            arm::toggleIntakePosition, arm));

    // Grab game piece
    robotController.rightBumper().onTrue(Commands.runOnce(
            arm::grabGamePiece, arm));

    // Release game piece
    robotController.leftBumper().onTrue(Commands.runOnce(
            arm::releaseGamePiece, arm));
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

  public DrivetrainSubsystem getDrivetrain() {
    return drivetrain;
  }

  public CommandXboxController getRobotController() {
    return robotController;
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
