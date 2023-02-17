// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.photonvision.PhotonCamera;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  CANSparkMax m_leftFront = new CANSparkMax(7, MotorType.kBrushless);
  CANSparkMax m_leftBack = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax m_rightFront = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax m_rightBack = new CANSparkMax(6, MotorType.kBrushless);

  MotorControllerGroup m_leftSide = new MotorControllerGroup(m_leftFront, m_leftBack); 
  MotorControllerGroup m_rightSide = new MotorControllerGroup(m_rightFront, m_rightBack);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftSide, m_rightSide);

  Accelerometer accelerometer = new BuiltInAccelerometer();

  LinearFilter xAccelFilter = LinearFilter.movingAverage(10);
  LinearFilter yAccelFilter = LinearFilter.movingAverage(10);
  LinearFilter zAccelFilter = LinearFilter.movingAverage(10);
  
  XboxController xboxController = new XboxController(0);

  PhotonCamera photonCamera = new PhotonCamera("photonvision");
  
  double forwardSpeed;
  double rotationSpeed;

  double prevXAccel = 0;
  double prevYAccel = 0;
  double prevZAccel = 0;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    m_leftFront.restoreFactoryDefaults();
    m_leftBack.restoreFactoryDefaults();
    m_rightFront.restoreFactoryDefaults();
    m_rightBack.restoreFactoryDefaults();

    m_leftSide.setInverted(true);
    m_rightSide.setInverted(false);

    m_leftBack.follow(m_leftFront);
    m_rightBack.follow(m_rightFront);

    m_drive.setMaxOutput(0.1);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Gets the current accelerations in the X, Y, and Z directions
    double xAccel = accelerometer.getX();
    double yAccel = accelerometer.getY();
    double zAccel = accelerometer.getZ();

    // Calculates the jerk in the X and Y directions
    // Divides by .02 because default loop timing is 20ms
    double xJerk = (xAccel - prevXAccel)/.02;
    double yJerk = (yAccel - prevYAccel)/.02;
    double zJerk = (zAccel - prevYAccel)/.02;

    prevXAccel = xAccel;
    prevYAccel = yAccel;
    prevZAccel = zAccel;

    // Get the filtered X and Y acceleration
    double filteredXAccel = xAccelFilter.calculate(accelerometer.getX());
    double filteredYAccel = yAccelFilter.calculate(accelerometer.getY());
    double filteredZAccel = zAccelFilter.calculate(accelerometer.getZ());

    double frameAngle = (Math.atan(filteredXAccel/filteredZAccel))*(180/Math.PI);

    // Puts accelerometer data on SmartDashboard
    SmartDashboard.putNumber("xAccel", xAccel);
    SmartDashboard.putNumber("yAccel", yAccel);
    SmartDashboard.putNumber("zAccel", zAccel);
    SmartDashboard.putNumber("xJerk", xJerk);
    SmartDashboard.putNumber("yJerk", yJerk);
    SmartDashboard.putNumber("zJerk", zJerk);
    SmartDashboard.putNumber("filteredXAccel", filteredXAccel);
    SmartDashboard.putNumber("filteredYAccel", filteredYAccel);
    SmartDashboard.putNumber("filteredZAccel", filteredZAccel);
    SmartDashboard.putNumber("frameAngle", frameAngle);

    if (xboxController.getAButtonPressed()) {
      var result = photonCamera.getLatestResult();
      System.out.println(result.getTargets());
    }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    forwardSpeed = xboxController.getLeftY();
    rotationSpeed = xboxController.getRightX();

    m_drive.arcadeDrive(forwardSpeed, rotationSpeed);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
