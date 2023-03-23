package com.theirrationalengineers.robot;

public class Constants {
    public static final class ArmConstants {
        public static final int MOTOR_ID = 8;
        public static final int CURRENT_LIMIT = 40;
        public static final double S_VOLTS = 0.0;
        public static final double G_VOLTS = 1.3;
        public static final double V_VOLT_SECOND_PER_RAD = 0.0;
        public static final double A_VOLT_SECOND_SQUARED_PER_RAD = 0.0;
        public static final double MAX_VELOCITY_RAD_PER_SECOND = 3.0; //used to be 3.0 - comp change
        public static final double MAX_ACCELERATION_RAD_PER_SEC_SQUARED = 10.0; //used to be 10.0 - comp change
        public static final double P = 4.0;
        public static final double POSITION_CONVERSION_FACTOR = 1.0;
        public static final double GEARBOX_RATIO = 100.0;
        public static final double OFFSET = Math.toRadians(103.0);
        public static final double LOW_GOAL = Math.toRadians(20.0);
        public static final double MID_GOAL = Math.toRadians(37.5);
        public static final double DOUBLE_SUBSTATION_GOAL = Math.toRadians(55.0);
        public static final double MOVE_ARM_DELTA = Math.toRadians(20.0);
    }

    public static final class DrivetrainConstants {
        public static final int LEFT_LEADER_MOTOR_ID = 1;
        public static final int LEFT_FOLLOWER_MOTOR_ID = 2;
        public static final int RIGHT_LEADER_MOTOR_ID = 3;
        public static final int RIGHT_FOLLOWER_MOTOR_ID = 4;
        public static final int CURRENT_LIMIT = 40;
        public static final double INITIAL_MAX_OUTPUT = 0.2;
        public static final double P = 1.0;
        public static final double GEARBOX_RATIO = 10.7;
        public static final double WHEEL_CIRCUMFERENCE_INCHES = 6.0 * Math.PI;
        public static final double LEAVE_COMMUNITY_INCHES = 12.0; //204.0 old
    }

    public static final class IntakeConstants {
        public static final int DOUBLE_SOLENOID_FWD_CHANNEL = 4;
        public static final int DOUBLE_SOLENOID_REV_CHANNEL = 5;
        public static final int COMPRESSOR_MODULE_ID = 0;
    }

    public static final class OIConstants {
        public static final int ROBOT_CONTROLLER_PORT = 0;
        public static final int LEFT_JOYSTICK_PORT = 1;
        public static final int RIGHT_JOYSTICK_PORT = 2;
    }

    public static final class DriveMode {
        public static final String ARCADE_DRIVE = "Arcade Drive";
        public static final String TANK_DRIVE = "Tank Drive";
    }
}
