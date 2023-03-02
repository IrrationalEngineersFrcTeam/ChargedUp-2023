package com.theirrationalengineers.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.networktables.GenericEntry;

import com.theirrationalengineers.robot.commands.*;
import com.theirrationalengineers.robot.subsystems.*;

public class RobotContainer {
  private static final RobotContainer m_robotContainer = new RobotContainer();
  
  public final VisionSubsystem m_visionSystem = new VisionSubsystem();
  public final ArmSubsystem m_armSystem = new ArmSubsystem();
  public final DriveSubsystem m_driveSystem = new DriveSubsystem();

  private final CommandXboxController xboxController = new CommandXboxController(0);

  private final InstantCommand extendPistonCommand = new InstantCommand(m_armSystem::extendPiston, m_armSystem);
  private final InstantCommand retractPistonCommand = new InstantCommand(m_armSystem::retractPiston, m_armSystem);
  private final InstantCommand toggleCompressorCommand = new InstantCommand(m_armSystem::toggleCompressor, m_armSystem);

  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  private RobotContainer() {
    ShuffleboardTab rotateRobotTab = Shuffleboard.getTab("Rotate robot");
    GenericEntry rotationDegreesEntry = rotateRobotTab.add("Rotation degrees", 1).getEntry();

    SmartDashboard.putData("Rotate robot", new RotateRobotCommand(m_driveSystem, rotationDegreesEntry.getDouble(0)));
    SmartDashboard.putNumber("Rotation degrees", rotationDegreesEntry.getDouble(0));

    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("Auto Mode", m_chooser);

    configureButtonBindings();
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  private void configureButtonBindings() {
        xboxController.a().onTrue(extendPistonCommand);
        xboxController.b().onTrue(retractPistonCommand);
        xboxController.x().onTrue(toggleCompressorCommand);
  }

  public CommandXboxController getXboxController() {
    return xboxController;
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
