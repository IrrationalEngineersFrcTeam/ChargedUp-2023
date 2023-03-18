package com.theirrationalengineers.robot;

import com.theirrationalengineers.robot.Constants.ArmConstants;
import com.theirrationalengineers.robot.Constants.DriveMode;
import com.theirrationalengineers.robot.subsystems.ArmSubsystem;
import com.theirrationalengineers.robot.subsystems.DrivetrainSubsystem;
import com.theirrationalengineers.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
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
    private Joystick leftJoystick;
    private Joystick rightJoystick;
    private Command autonomousCommand;
    private String driveMode;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
        arm = robotContainer.getArm();
        drivetrain = robotContainer.getDrivetrain();
        intake = robotContainer.getIntake();
        robotController = robotContainer.getRobotController();
        leftJoystick = robotContainer.getLeftJoystick();
        rightJoystick = robotContainer.getRightJoystick();

        //intake.close();
        //intake.enableCompressor();
        CameraServer.startAutomaticCapture(0);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        //intake.close();
        //arm.setPosition(ArmConstants.LOW_GOAL);
    }

    @Override
    public void disabledPeriodic() {
        //if (intake.isOpen()) {
            //intake.close();
        //}
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();

        //intake.close();
        drivetrain.resetEncoderPosition();

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        driveMode = robotContainer.getDriveMode();

        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }

        //intake.close();
    }

    @Override
    public void teleopPeriodic() {
        switch (driveMode) {
            case DriveMode.ARCADE_DRIVE:
            drivetrain.arcadeDrive(-robotController.getLeftY(), -robotController.getRightX());
            break;
            case DriveMode.TANK_DRIVE:
            drivetrain.tankDrive(-leftJoystick.getY(), -rightJoystick.getY());
            break;
        }
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
        //intake.close();
    }

    @Override
    public void testPeriodic() {}
}
