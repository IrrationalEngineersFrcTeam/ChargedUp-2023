package com.theirrationalengineers.robot;

import com.theirrationalengineers.robot.Constants.OIConstants;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;
import com.theirrationalengineers.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final ArmSubsystem arm = new ArmSubsystem();
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final CommandXboxController robotController = new CommandXboxController(OIConstants.ROBOT_CONTROLLER_PORT);
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
      arm::raise, arm));

    // Lower arm
    robotController.povDown().onTrue(Commands.runOnce(
      arm::lower, arm));
    
    // Position arm at low node
    //robotController.a().onTrue(Commands.runOnce(() -> {
      //arm.setPosition(ArmConstants.LOW_GOAL);
    //}, arm));

    // Position arm at mid node
    //robotController.b().onTrue(Commands.runOnce(() -> {
      //arm.setPosition(ArmConstants.MID_GOAL);
    //}, arm));

    // Position arm at high node
    //robotController.y().onTrue(Commands.runOnce(() -> {
      //arm.setPosition(ArmConstants.HIGH_GOAL);
    //}, arm));

    // Grab game piece
    robotController.rightBumper().onTrue(Commands.runOnce(
      intake::close, intake));

    // Release game piece
    robotController.leftBumper().onTrue(Commands.runOnce(
      intake::open, intake));
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

  public ArmSubsystem getArm() {
    return arm;
  }

  public DrivetrainSubsystem getDrivetrain() {
    return drivetrain;
  }

  public IntakeSubsystem getIntake() {
    return intake;
  }

  public CommandXboxController getRobotController() {
    return robotController;
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
