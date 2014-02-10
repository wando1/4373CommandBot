package edu.afs.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    // Drive Subsystem
    // Digital Sidecar PWM outputs
    public static final int DRIVE_LEFT_MOTOR_CHANNEL = 2;
    public static final int DRIVE_RIGHT_MOTOR_CHANNEL = 3;
   
    // Analog Breakout
    public static final int GYRO_CHANNEL = 1;
    // Driver Station
    public static final int DRIVE_JOYSTICK_PORT = 1;
    public static final int FORKLIFT_JOYSTICK_PORT = 2;
    //Buttons for Joystick, these numbers are temporary
    public static final int BUMP_LEFT_BUTTON = 4;
    public static final int BUMP_RIGHT_BUTTON = 3;
    public static final int BUMP_FORWARD_BUTTON = 2;
    public static final int BUMP_REVERSE_BUTTON = 1;
    public static final int JOG_FORKLIFT_UP = 11;
    public static final int JOG_FORKLIFT_DOWN = 10;
////////////////////////////////////////////////////////////////////////////////
    
    // Auto-Ranger Subsystem
    // Analog Breakout
    public static final int ULTRASONIC_RANGER_CHANNEL = 2;
    
    // Digital Sidecar GPIO outputs
    public static final int RANGE_BEACON_RED_CHANNEL = 1;
    public static final int RANGE_BEACON_YELLOW_CHANNEL = 2;
    public static final int RANGE_BEACON_GREEN_CHANNEL = 3;
////////////////////////////////////////////////////////////////////////////////
    
    // Launcher Subsystem
    // Digital Sidecar PWM outputs
    public static final int LAUNCHER_LEFT_MOTOR_CHANNEL = 6;
    public static final int LAUNCHER_RIGHT_MOTOR_CHANNEL = 5;
    // Digital Sidecar GPIO Inputs
    public static final int LAUNCHER_ENCODER_A_CHANNEL = 4;
    public static final int LAUNCHER_ENCODER_B_CHANNEL = 5;
    public static final int LAUNCHER_ENCODER_MODULE = 1;
//////////////////////////////////////////////////////////////////////////////// 
    
    // Forklift Subsystem
    // Digital Sidecar PWM outputs
    public static final int KICKER_MOTOR_CHANNEL = 7;
    public static final int FORKLIFT_MOTOR_CHANNEL = 8;
    // Digital Sidecar GPIO Inputs
    public static final int KICKER_ENCODER_A_CHANNEL = 6;
    public static final int KICKER_ENCODER_B_CHANNEL = 7;
    public static final int KICKER_ENCODER_MODULE = 2;
    public static final int FORKLIFT_ENCODER_A_CHANNEL = 8;
    public static final int FORKLIFT_ENCODER_B_CHANNEL = 9;
    public static final int FORKLIFT_ENCODER_MODULE = 3;
////////////////////////////////////////////////////////////////////////////////
    // Keys Used for the Smart Dashboard
    public static final String SMARTDASHBOARD_INVERTED_DRIVE = "Inverted Controls?";
    public static final String SMARTDASHBOARD_AUTORANGER_VALUE = "AutoRanger Value";
    public static final String SMARTDASHBOARD_AUTORANGER_VALUE_INCHES = "AutoRanger Value in Inches";
    public static final String SMARTDASHBOARD_AUTORANGER_AT_DESIRED_VALUE = "In Position?";

    
    
    
    
    
    
}
