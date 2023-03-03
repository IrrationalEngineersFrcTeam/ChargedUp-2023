package com.theirrationalengineers.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.theirrationalengineers.robot.Constants.ArmConstants;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;

public class ArmSubsystem extends ProfiledPIDSubsystem {
    private final CANSparkMax motor = new CANSparkMax(
        ArmConstants.MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final RelativeEncoder encoder = motor.getEncoder();

    private final ArmFeedforward feedForward =
        new ArmFeedforward(
            ArmConstants.KS_VOLTS, ArmConstants.KG_VOLTS,
            ArmConstants.KV_VOLT_SECOND_PER_RAD, ArmConstants.KA_VOLT_SECOND_SQUARED_PER_RAD);

    private boolean useFeedForward;
    private boolean isIntakeLowered;

    public ArmSubsystem(boolean useFeedForward) {
        super(
            new ProfiledPIDController(
                ArmConstants.KP,
                0,
                0,
                new TrapezoidProfile.Constraints(
                    ArmConstants.MAX_VELOCITY_RAD_PER_SECOND,
                    ArmConstants.MAX_ACCELERATION_RAD_PER_SEC_SQUARED)),
            0);
            

        // ProfiledPIDControler constructor calls setGoal with initialPostion
        // Question : Should initialPositon be set to ArmConstants.kArmOffestRads in above vice
        //            calling setGoal(ArmConstants.kArmOffsetRads) below?

        // Set if feedfoward is needed for the armSystem
        this.useFeedForward = useFeedForward;
        
        isIntakeLowered = false;

        // Set the position conversastion a factor to return radians and not encoder ticks
        encoder.setPositionConversionFactor(ArmConstants.ENCODER_DISTANCE_PER_PULSE);
        
        // Is this needed to convert velocity?
        //m_encoder.setVelocityConversionFactor(ArmConstants.kEncoderDistancePerPulse/60);
        

        // Set the position of the motor encoder to be inital resting postion of the arm
        // Start arm at rest in neutral position
        setGoal(ArmConstants.ARM_OFFSET_RADS);
        
        // Enable the arm at the start
        //enable();
        System.out.println("Is Enabled? : " + this.isEnabled());
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
      // Calculate the feedforward from the sepoint
      double feedforward = feedForward.calculate(setpoint.position, setpoint.velocity);
      // Add the feedforward to the PID output to get the motor output
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
      //return m_encoder.getDistance() + ArmConstants.kArmOffsetRads;
      return encoder.getPosition() + ArmConstants.ARM_OFFSET_RADS;
    }

    public void positionArm(double goal) {
        setGoal(goal);
        enable();
    }

    public void raiseArm() {}

    public void lowerArm() {}

    public void raiseIntake() {}

    public void lowerIntake() {}

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
