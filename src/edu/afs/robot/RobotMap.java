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
    public static final int DRIVE_LEFT_MOTOR_CHANNEL = 1;
    public static final int DRIVE_RIGHT_MOTOR_CHANNEL = 2;
   
    // Analog Breakout
    public static final int GYRO_CHANNEL = 1;
    // Driver Station
    public static final int JOYSTICK_PORT = 1;
////////////////////////////////////////////////////////////////////////////////
    
    // Auto-Ranger Subsystem
    // Analog Breakout
    public static final int ULTRASONIC_RANGER_CHANNEL = 2;
////////////////////////////////////////////////////////////////////////////////
    
    // Launcher Subsystem
    // Digital Sidecar PWM outputs
    public static final int LAUNCHER_LEFT_MOTOR_CHANNEL = 5;
    public static final int LAUNCHER_RIGHT_MOTOR_CHANNEL = 6;
    // Digital Sidecar GPIO Inputs
    public static final int LAUNCHER_ENCODER_A_CHANNEL = 1;
    public static final int LAUNCHER_ENCODER_B_CHANNEL = 2;
    public static final int LAUNCHER_ENCODER_MODULE = 1;
//////////////////////////////////////////////////////////////////////////////// 
    
    // Forklift Subsystem
    // Digital Sidecar PWM outputs
    public static final int CLAMPER_MOTOR_CHANNEL = 7;
    public static final int FORKLIFT_MOTOR_CHANNEL = 8;
    // Digital Sidecar GPIO Inputs
    public static final int CLAMPER_ENCODER_A_CHANNEL = 3;
    public static final int CLAMPER_ENCODER_B_CHANNEL = 4;
    public static final int CLAMPER_ENCODER_MODULE = 2;
    public static final int FORKLIFT_ENCODER_A_CHANNEL = 5;
    public static final int FORKLIFT_ENCODER_B_CHANNEL = 6;
    public static final int FORKLIFT_ENCODER_MODULE = 3;
////////////////////////////////////////////////////////////////////////////////
    

    
    
    
    
    
    
}
