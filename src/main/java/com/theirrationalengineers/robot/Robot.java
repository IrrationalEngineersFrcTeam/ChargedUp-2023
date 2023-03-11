package com.theirrationalengineers.robot;

import com.theirrationalengineers.robot.Constants.ArmConstants;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;
import com.theirrationalengineers.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;
    private ArmSubsystem arm;
    private DrivetrainSubsystem drivetrain;
    private IntakeSubsystem intake;
    private CommandXboxController robotController;
    private Command autonomousCommand;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
        arm = robotContainer.getArm();
        drivetrain = robotContainer.getDrivetrain();
        intake = robotContainer.getIntake();
        robotController = robotContainer.getRobotController();

        intake.disableSolenoids();
        intake.enableCompressor();
        CameraServer.startAutomaticCapture(0);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        intake.close();
        arm.setPosition(ArmConstants.LOW_GOAL);
    }

    @Override
    public void disabledPeriodic() {
        if (intake.isOpen()) {
            intake.close();
        }
    }

    @Override
    public void autonomousInit() {
        intake.disableSolenoids();

        autonomousCommand = robotContainer.getAutonomousCommand();

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        intake.disableSolenoids();

        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
        drivetrain.drive(-robotController.getLeftY(), -robotController.getRightX());
    }

    @Override
    public void testInit() {
        intake.disableSolenoids();

        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}
}
