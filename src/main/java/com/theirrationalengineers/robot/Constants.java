package com.theirrationalengineers.robot;

public class Constants {
    public static final class ArmConstants {
        public static final int MOTOR_ID = 8;
        public static final double S_VOLTS = 0.0;
        public static final double G_VOLTS = 0.0;
        public static final double V_VOLT_SECOND_PER_RAD = 0.25;
        public static final double A_VOLT_SECOND_SQUARED_PER_RAD = 0.1;
        public static final double MAX_VELOCITY_RAD_PER_SECOND = 1.5; //used to be 3.0 - comp change
        public static final double MAX_ACCELERATION_RAD_PER_SEC_SQUARED = 5.0; //used to be 10.0 - comp change
        public static final double P = 4.0;
        public static final double POSITION_CONVERSION_FACTOR = 1.0;
        public static final double GEARBOX_RATIO = 100.0;
        public static final double OFFSET = Math.toRadians(-80.0);
        public static final double LOW_GOAL = Math.toRadians(-43.0);
        public static final double MID_GOAL = Math.toRadians(-30.0);
        public static final double HIGH_GOAL = Math.toRadians(-10.0);
        public static final double MOVE_ARM_DELTA = Math.toRadians(20.0);
    }

    public static final class DrivetrainConstants {
        public static final int FRONT_LEFT_MOTOR_ID = 1;
        public static final int REAR_LEFT_MOTOR_ID = 2;
        public static final int FRONT_RIGHT_MOTOR_ID = 3;
        public static final int REAR_RIGHT_MOTOR_ID = 4;
        public static final double INITIAL_MAX_OUTPUT = 0.5;
        public static final double SLEW_RATE_LIMIT = 0.5;
    }

    public static final class IntakeConstants {
        public static final int DOUBLE_SOLENOID_FWD_CHANNEL = 4;
        public static final int DOUBLE_SOLENOID_REV_CHANNEL = 5;
        public static final int COMPRESSOR_MODULE_ID = 0;
    }

    public static final class OIConstants {
        public static final int ROBOT_CONTROLLER_PORT = 0;
    }
}
