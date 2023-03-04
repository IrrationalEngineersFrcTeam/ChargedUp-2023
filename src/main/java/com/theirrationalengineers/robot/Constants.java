package com.theirrationalengineers.robot;

public class Constants {
    public static final class ArmConstants {
        public static final int MOTOR_ID = 5;
        public static final double MAX_VELOCITY_RAD_PER_SECOND = 3.0;
        public static final double MAX_ACCELERATION_RAD_PER_SEC_SQUARED = 10.0;
        public static final double P = 4.0;
        public static final double S_VOLTS = 0.0;
        public static final double G_VOLTS = 0.0;
        public static final double V_VOLT_SECOND_PER_RAD = 0.0;
        public static final double A_VOLT_SECOND_SQUARED_PER_RAD = 0.0;
        public static final double POSITION_CONVERSION_FACTOR = 1.0;
        public static final double GEARBOX_RATIO = 10.0;
        public static final double OFFSET = 45.0 / 360.0 * GEARBOX_RATIO;
        public static final double LOW_GOAL = 0;
        public static final double MID_GOAL = 90.0 / 360.0 * GEARBOX_RATIO;
        public static final double HIGH_GOAL = 135.0 / 360.0 * GEARBOX_RATIO;
        public static final double MOVE_ARM_DELTA = 5.0 / 360.0 * ArmConstants.GEARBOX_RATIO;
    }

    public static final class DrivetrainConstants {
        public static final double INITIAL_MAX_OUTPUT = 0.2;
        public static final int FRONT_LEFT_MOTOR_ID = 1;
        public static final int REAR_LEFT_MOTOR_ID = 2;
        public static final int FRONT_RIGHT_MOTOR_ID = 3;
        public static final int REAR_RIGHT_MOTOR_ID = 4;
    }
}
