package com.theirrationalengineers.robot;

public class Constants {
    public static final class ArmConstants {
        public static final int MOTOR_PORT = 6;
        public static final int LOW_ARM_GOAL = 0;
        public static final int MID_ARM_GOAL = 0;
        public static final int HIGH_ARM_GOAL = 0;
        public static double KS_VOLTS;
        public static double KG_VOLTS;
        public static double KV_VOLT_SECOND_PER_RAD;
        public static double KA_VOLT_SECOND_SQUARED_PER_RAD;
        public static double KP;
        public static double MAX_VELOCITY_RAD_PER_SECOND;
        public static double MAX_ACCELERATION_RAD_PER_SEC_SQUARED;
        public static double ENCODER_DISTANCE_PER_PULSE;
        public static double ARM_OFFSET_RADS;
    }

    public static final class DrivetrainConstants {
        public static final double INITIAL_MAX_DRIVE_SPEED = 0.2;
        public static final int FRONT_LEFT_MOTOR_PORT = 1;
        public static final int REAR_LEFT_MOTOR_PORT = 2;
        public static final int FRONT_RIGHT_MOTOR_PORT = 3;
        public static final int REAR_RIGHT_MOTOR_PORT = 4;
    }
}
