package com.theirrationalengineers.robot;

import com.theirrationalengineers.robot.Constants.ArmConstants;
import com.theirrationalengineers.robot.Constants.DriveMode;
import com.theirrationalengineers.robot.Constants.OIConstants;
import com.theirrationalengineers.robot.commands.DriveDistanceCommand;
import com.theirrationalengineers.robot.commands.TurnAngleCommand;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;
import com.theirrationalengineers.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private final ArmSubsystem arm = new ArmSubsystem();
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final CommandXboxController robotController = new CommandXboxController(OIConstants.ROBOT_CONTROLLER_PORT);
  private final Joystick leftJoystick = new Joystick(OIConstants.LEFT_JOYSTICK_PORT);
  private final Joystick rightJoystick = new Joystick(OIConstants.RIGHT_JOYSTICK_PORT);
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();
  private final SendableChooser<String> driveChooser = new SendableChooser<>();

  public RobotContainer() {
    configureXboxBindings();
    configureJoystickBindings();
    configureChoosers();
    configureDashboard();
  }

  private void configureXboxBindings() {
    // Increase max output of drivetrain
    robotController.povRight().onTrue(Commands.runOnce(
      drivetrain::increaseMaxOutput, drivetrain));

    // Decrease max output of drivetrain
    robotController.povLeft().onTrue(Commands.runOnce(
      drivetrain::decreaseMaxOutput, drivetrain));

      /*
    // Raise arm
    robotController.povUp().onTrue(Commands.runOnce(
      arm::raise, arm));

    // Lower arm
    robotController.povDown().onTrue(Commands.runOnce(
      arm::lower, arm));
      
    // Position arm at low node
    robotController.a().onTrue(Commands.runOnce(() -> {
      arm.setPosition(ArmConstants.LOW_GOAL);
      System.out.println("A button pressed. Goal: " + ArmConstants.LOW_GOAL);
    }, arm));

    // Position arm at mid node
    robotController.b().onTrue(Commands.runOnce(() -> {
      arm.setPosition(ArmConstants.MID_GOAL);
    }, arm));

    // Position arm at high node
    robotController.y().onTrue(Commands.runOnce(() -> {
      arm.setPosition(ArmConstants.HIGH_GOAL);
    }, arm));

    // Grab game piece
    robotController.rightBumper().onTrue(Commands.runOnce(
      intake::close, intake));

    // Release game piece
    robotController.leftBumper().onTrue(Commands.runOnce(
      intake::open, intake));
      */
  }

  private void configureJoystickBindings() {
    final JoystickButton left4 = new JoystickButton(leftJoystick, 4);
    final JoystickButton left3 = new JoystickButton(leftJoystick, 3);
    final JoystickButton right4 = new JoystickButton(rightJoystick, 4);
    final JoystickButton right3 = new JoystickButton(rightJoystick, 3);
    final JoystickButton right8 = new JoystickButton(rightJoystick, 8);
    final JoystickButton right9 = new JoystickButton(rightJoystick, 9);
    final JoystickButton right10 = new JoystickButton(rightJoystick, 10);
    final JoystickButton right1 = new JoystickButton(rightJoystick, 1);
    final JoystickButton left1 = new JoystickButton(leftJoystick, 1);

    // Increase max output of drivetrain
    left4.onTrue(Commands.runOnce(drivetrain::increaseMaxOutput, drivetrain));

    // Decrease max output of drivetrain
    left3.onTrue(Commands.runOnce(drivetrain::decreaseMaxOutput, drivetrain));
      
    // Raise arm
    right4.onTrue(Commands.runOnce(arm::raise, arm));

    // Lower arm
    right3.onTrue(Commands.runOnce(arm::lower, arm));
      
    // Position arm at low node
    right8.onTrue(Commands.runOnce(() -> {
      arm.setPosition(ArmConstants.LOW_GOAL);
    }, arm));

    // Position arm at mid node
    right9.onTrue(Commands.runOnce(() -> {
      arm.setPosition(ArmConstants.MID_GOAL);
    }, arm));

    // Position arm at high node
    right10.onTrue(Commands.runOnce(() -> {
      arm.setPosition(ArmConstants.HIGH_GOAL);
    }, arm));

    // Grab game piece
    right1.onTrue(Commands.runOnce(intake::close, intake));

    // Release game piece
    left1.onTrue(Commands.runOnce(intake::open, intake));
  }

  private void configureChoosers() {
    autoChooser.addOption("Drive Distance", new DriveDistanceCommand(-12.0, drivetrain));
    autoChooser.addOption("Turn Angle", new TurnAngleCommand(90.0, drivetrain));

    driveChooser.setDefaultOption("Arcade Drive", DriveMode.ARCADE_DRIVE);
    driveChooser.addOption("Arcade Drive", DriveMode.ARCADE_DRIVE);
    driveChooser.addOption("Tank Drive", DriveMode.TANK_DRIVE);
  }

  private void configureDashboard() {
    SmartDashboard.putData(autoChooser);
    SmartDashboard.putData(driveChooser);
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

  public Joystick getLeftJoystick() {
    return leftJoystick;
  }

  public Joystick getRightJoystick() {
    return rightJoystick;
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public String getDriveMode() {
    return driveChooser.getSelected();
  }
}
