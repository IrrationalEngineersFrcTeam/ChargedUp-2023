package com.theirrationalengineers.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.theirrationalengineers.robot.Constants.ArmConstants;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;

public class ArmSubsystem extends ProfiledPIDSubsystem {
    private final CANSparkMax motor = new CANSparkMax(
        ArmConstants.MOTOR_ID, MotorType.kBrushless);

    private final RelativeEncoder encoder = motor.getEncoder();

    private final ArmFeedforward m_feedforward = new ArmFeedforward(
        ArmConstants.S_VOLTS, ArmConstants.G_VOLTS, 
        ArmConstants.V_VOLT_SECOND_PER_RAD, ArmConstants.A_VOLT_SECOND_SQUARED_PER_RAD);

    public ArmSubsystem() {
        super(
            new ProfiledPIDController(
                ArmConstants.P, 0.0, 0.0, 
                new TrapezoidProfile.Constraints(
                    ArmConstants.MAX_VELOCITY_RAD_PER_SECOND, 
                    ArmConstants.MAX_ACCELERATION_RAD_PER_SEC_SQUARED)), 
            0);

        motor.restoreFactoryDefaults();
        motor.setSmartCurrentLimit(ArmConstants.CURRENT_LIMIT);
        motor.setIdleMode(IdleMode.kBrake);
        encoder.setPositionConversionFactor(ArmConstants.POSITION_CONVERSION_FACTOR);
        getController().setTolerance(Math.toRadians(3.0));
        setGoal(ArmConstants.OFFSET);
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
      double feedforward = m_feedforward.calculate(setpoint.position, setpoint.velocity);

      //SmartDashboard.putNumber("Arm motor output", output);
      //SmartDashboard.putNumber("Arm motor feedforward", feedforward);
      //SmartDashboard.putNumber("Arm encoder velocity", encoder.getVelocity());
      motor.setVoltage(output + feedforward);
    }

    @Override
    public double getMeasurement() {
      return (encoder.getPosition() * (2.0 * Math.PI / ArmConstants.GEARBOX_RATIO) + ArmConstants.OFFSET);
    }

    public boolean isAtSetpoint() {
        return this.getController().atSetpoint();
    }

    public void resetEncoder() {
        encoder.setPosition(0.0);
    }

    public void setPosition(double goal) {
        if ((goal <= ArmConstants.OFFSET) && (goal >= ArmConstants.LOW_GOAL)) {
            setGoal(goal);
            enable();
            //SmartDashboard.putNumber("Arm goal", goal);
        } 
        //else {
            //System.out.println("Arm went out of bounds! (goal: " + goal + ")");
        //}
    }

    public void raise() {
        double currentPosition = getMeasurement();
        
        //if (currentPosition < (ArmConstants.OFFSET - ArmConstants.MOVE_ARM_DELTA)) {
            setGoal(currentPosition + ArmConstants.MOVE_ARM_DELTA);
            enable();
        //}
    }

    public void lower() {
        double currentPosition = getMeasurement();

        if (currentPosition > (ArmConstants.LOW_GOAL + ArmConstants.MOVE_ARM_DELTA)) {
            setGoal(currentPosition - ArmConstants.MOVE_ARM_DELTA);
            enable();
        }
    }
}
