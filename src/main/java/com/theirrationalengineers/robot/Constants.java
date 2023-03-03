package com.theirrationalengineers.robot;

public class Constants {
    public static final class ArmConstants {
        public static final int MOTOR_PORT = 6;
        public static final int LOW_ARM_GOAL = 0;
        public static final int MID_ARM_GOAL = 0;
        public static final int HIGH_ARM_GOAL = 0;
        public static final double MAX_VELOCITY_RAD_PER_SECOND = 3.0;
        public static final double MAX_ACCELERATION_RAD_PER_SEC_SQUARED = 10.0;
        public static final double P = 4.0;
        public static final double S_VOLTS = 0.0;
        public static final double G_VOLTS = 0.0;
        public static final double V_VOLT_SECOND_PER_RAD = 0.5;
        public static final double A_VOLT_SECOND_SQUARED_PER_RAD = 0.1;
        public static final double ARM_GEARBOX_RATIO = 1.0;
        public static final double ENCODER_PCF = 2.0 * Math.PI / ARM_GEARBOX_RATIO;
        public static final double ARM_OFFSET_RADS = 0.5;
    }

    public static final class DrivetrainConstants {
        public static final double INITIAL_MAX_OUTPUT = 0.2;
        public static final int FRONT_LEFT_MOTOR_PORT = 1;
        public static final int REAR_LEFT_MOTOR_PORT = 2;
        public static final int FRONT_RIGHT_MOTOR_PORT = 3;
        public static final int REAR_RIGHT_MOTOR_PORT = 4;
    }
}
