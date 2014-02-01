/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.subsystems.drivesubsystem;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.PIDSource;

import edu.afs.commands.AutoDriveToShotRangeCommand;
import edu.afs.commands.DriveWithJoystickCommand;
import edu.afs.robot.OI;
import edu.afs.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author User
 */
public class DrivePIDSubsystem extends PIDSubsystem {

    // PID tuning parameters.
    //TODO: Pick tuning parameters.
    private static final double K_PROPORTIONAL = 0.03;
    private static final double K_INTEGRAL = 0.0;
    private static final double K_DERIVATIVE = 0.0;
    
    private static final double STRAIGHT_AHEAD_ROTATION_VALUE = 0.0;
    private static final double BUMP_SPEED = 0.5;
    public static final double DRIVE_MAX_INPUT = 1.0;
    public static final double DRIVE_MIN_INPUT = -1.0;
    RobotDrive m_drive;
    //Talon m_leftMotors;
    //Talon m_rightMotors;
    Victor m_leftMotors;
    Victor m_rightMotors;
    Gyro m_gyro;
    AnalogChannel m_gyroChannel;
    double m_steerAngle;
    double m_steeringSetPoint;
    
    // Implement Singleton design pattern.  There 
    // can be only one instance of this subsystem.
    static DrivePIDSubsystem instance = null; 
    public static DrivePIDSubsystem getInstance () {
        if (instance == null) {
            instance = new DrivePIDSubsystem();
        }
        return instance;
    }
    // Constructor is private to enforce Singelton.
    private DrivePIDSubsystem() {
        super("DrivePIDSubsystem", 
              K_PROPORTIONAL,
              K_INTEGRAL,
              K_DERIVATIVE);

        // Drive set-up.
        //m_leftMotors = new Talon(RobotMap.DRIVE_LEFT_MOTOR_CHANNEL);
        //m_rightMotors = new Talon(RobotMap.DRIVE_RIGHT_MOTOR_CHANNEL);
        m_leftMotors = new Victor(RobotMap.DRIVE_LEFT_MOTOR_CHANNEL);
        m_rightMotors = new Victor(RobotMap.DRIVE_RIGHT_MOTOR_CHANNEL);
        m_drive = new RobotDrive(m_leftMotors, m_rightMotors);
        m_drive.setSafetyEnabled(false);
        
        // Gyro stabilization set-up.
        m_gyroChannel = new AnalogChannel (RobotMap.GYRO_CHANNEL);
        m_gyro = new Gyro(m_gyroChannel); 
        getPIDController().disable();
        m_steerAngle = 0.0;
        m_steeringSetPoint = 0.0;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoystickCommand());
    }
    
    public void driveWithJoystick(boolean invertDrivingDirection) {
        // Invert the sense of the joystick controls to drive with the launcher
        // in front of the forklift in front.
        //m_drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        //m_drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        m_drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, invertDrivingDirection);
        m_drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, invertDrivingDirection);
        m_drive.arcadeDrive(OI.getInstance().getJoystick());
    }
    
    // Move bot forward or backwards at specified speed.
    // No gyro-stabilization of bearing is used.
    public void driveStraight (double speed) {
        // TODO: Do any speed value translation here.
        // Speed input ranges from 1.0 (max forward) to -1.0
        // (max reverse). 
        m_drive.drive(speed, STRAIGHT_AHEAD_ROTATION_VALUE);
    }
    
    // Move bot forward or backwards at specified speed.
    // No gyro-stabilization of bearing is used.
    public void driveTurn (double speed, double rotate) {
        // Steer rotation input ranges from 1.0 (max CW) to -1.0
        // (max CCW).
        m_drive.drive(speed ,rotate);
    }
    
    // Move bot forward or backwards at specified speed.
    // Bearing is gyro-stabilized.
    public void driveStraightGyroStabilized (double speed) {
        // TODO: Do any speed value and steer angle translation here.
        // m_steerAngle is set by PID Controller.
        // Speed input ranges from 1.0 (max forward) to -1.0
        // (max reverse).  Steer angle input ranges from 1.0 (max CW) to -1.0
        // (max CCW).
        m_drive.drive(speed, m_steerAngle);
    }
    
    public void initBearingStabilizer(){
        // Add gyro and PID set-up code.
        // TODO: Determine correct tolerance for "close enough" to set point.
        //getPIDController().setAbsoluteTolerance(???);
        // Treat input range as continuous so PID controller will correct
        // angle by shortest path.
        getPIDController().setContinuous(true);
        //TODO: Determine output range from gyro (-360.0 to 360.0 degrees?????)
        //getPIDController().setInputRange(???, ???);
        getPIDController().setOutputRange(DRIVE_MIN_INPUT, DRIVE_MAX_INPUT);
        m_gyro.setPIDSourceParameter(PIDSource.PIDSourceParameter.kAngle);
        //TODO: Set Gyro sensitivity
        //m_gyro.setSensitivity(?????????????????);
        //Set gyro zero point.
        m_gyro.reset();
        
        // Establish set point for straight-ahead driving.
        // Assume that bot is oriented perpendicular to 10-point goal.
        // Use the current reading of the gyro.
        //TODO: Verify that pidGet returns the value we want for comparison.
        m_steeringSetPoint = m_gyro.pidGet();
        getPIDController().setSetpoint(m_steeringSetPoint);
        
        getPIDController().enable();
    }
    
    public void disableBearingStabilizer(){
         getPIDController().reset();
         m_gyro.reset();
         getPIDController().disable();
     }
    
    //TODO: Make sure that units and range of values make sense for PID inputs
    // and outputs!!!!!!!!!!!!!!
    // Called every 20 ms by PID Controller.
    protected double returnPIDInput() {
        // Return your input value for the PID loop.
      
        //TODO: Determine if any value conversion is needed.
        return m_gyro.pidGet();
    }
    
    //TODO: Make sure that units and range of values make sense for PID inputs
    // and outputs!!!!!!!!!!!!!!
    // Called every 20 ms by PID Controller.
    protected void usePIDOutput(double output) {
        // Use output to drive your system.
        m_steerAngle = output;
    }
}
