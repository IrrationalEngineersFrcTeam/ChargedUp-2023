package com.theirrationalengineers.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.theirrationalengineers.robot.Constants.ArmConstants;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;

public class ArmSubsystem extends ProfiledPIDSubsystem {
    private static final Constraints CONSTRAINTS = new TrapezoidProfile.Constraints(
        ArmConstants.MAX_VELOCITY_RAD_PER_SECOND, ArmConstants.MAX_ACCELERATION_RAD_PER_SEC_SQUARED);

    private static final ProfiledPIDController PID = new ProfiledPIDController(
        ArmConstants.P, 0, 0, CONSTRAINTS);

    private final ArmFeedforward armFeedForward = new ArmFeedforward(
            ArmConstants.S_VOLTS, ArmConstants.G_VOLTS, ArmConstants.V_VOLT_SECOND_PER_RAD, ArmConstants.A_VOLT_SECOND_SQUARED_PER_RAD);

    private final CANSparkMax motor = new CANSparkMax(
        ArmConstants.MOTOR_PORT, MotorType.kBrushless);

    private final RelativeEncoder encoder = motor.getEncoder();

    private boolean useFeedForward;
    private boolean isIntakeLowered;

    public ArmSubsystem(boolean useFeedForward) {
        super(PID, 0);

        this.useFeedForward = useFeedForward;
        isIntakeLowered = false;
        encoder.setPositionConversionFactor(ArmConstants.ENCODER_DISTANCE_PER_PULSE);
        setGoal(ArmConstants.ARM_OFFSET_RADS);
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
      double feedforward = armFeedForward.calculate(setpoint.position, setpoint.velocity);
      SmartDashboard.putNumber("Motor output", output);
      SmartDashboard.putNumber("Motor feedforward", feedforward);
  
      if (useFeedForward) {
        motor.setVoltage(output + feedforward);
      } else {
        motor.setVoltage(output);
      }
    }

    @Override
    public double getMeasurement() {
      return encoder.getPosition() + ArmConstants.ARM_OFFSET_RADS;
    }

    public void positionArm(double goal) {
        setGoal(goal);
        enable();
    }

    public void raiseArm() {}

    public void lowerArm() {}

    public void raiseIntake() {
        isIntakeLowered = false;
    }

    public void lowerIntake() {
        isIntakeLowered = true;
    }

    public void toggleIntakePosition() {
        if (isIntakeLowered) {
            raiseIntake();
        } else {
            lowerIntake();
        }

        isIntakeLowered = !isIntakeLowered;
    }

    public void grabGamePiece() {}

    public void releaseGamePiece() {}

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Encoder Postion", encoder.getPosition());
        SmartDashboard.putNumber("Encoder Velocity", encoder.getVelocity());
    }
}
