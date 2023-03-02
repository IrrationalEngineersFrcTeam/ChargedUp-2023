package com.theirrationalengineers.robot;

import com.theirrationalengineers.robot.commands.AutonomousCommand;
import com.theirrationalengineers.robot.commands.RotateRobotCommand;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.DriveSubsystem;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final CommandXboxController robotController = new CommandXboxController(0);

  private final InstantCommand extendPistonCommand = new InstantCommand(armSubsystem::extendPiston, armSubsystem);
  private final InstantCommand retractPistonCommand = new InstantCommand(armSubsystem::retractPiston, armSubsystem);
  private final InstantCommand toggleCompressorCommand = new InstantCommand(armSubsystem::toggleCompressor, armSubsystem);

  private final SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    ShuffleboardTab rotateRobotTab = Shuffleboard.getTab("Rotate robot");
    GenericEntry rotationDegreesEntry = rotateRobotTab.add("Rotation degrees", 1).getEntry();

    SmartDashboard.putData("Rotate robot", new RotateRobotCommand(driveSubsystem, rotationDegreesEntry.getDouble(0)));
    SmartDashboard.putNumber("Rotation degrees", rotationDegreesEntry.getDouble(0));

    chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("Auto Mode", chooser);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    robotController.a().onTrue(extendPistonCommand);
    robotController.b().onTrue(retractPistonCommand);
    robotController.x().onTrue(toggleCompressorCommand);
  }

  public DriveSubsystem getDriveSubsystem() {
    return driveSubsystem;
  }

  public CommandXboxController getRobotController() {
    return robotController;
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
