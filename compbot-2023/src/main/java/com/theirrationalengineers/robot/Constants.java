package com.theirrationalengineers.robot;

public class Constants {
    public static final class MotorConstants {
        public static final double kEncoderCPR = 4096;
        public static final double kEncoderDistancePerPulse = 2.0 * Math.PI / kEncoderCPR;
    }

    public static final class DriveConstants {
        public static final double kMaxDriveSpeed = 0.2;
    }
}
