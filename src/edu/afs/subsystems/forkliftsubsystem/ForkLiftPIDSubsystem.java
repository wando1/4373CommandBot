/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.subsystems.forkliftsubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.afs.robot.RobotMap;

/**
 *
 * @author User
 */
public class ForkLiftPIDSubsystem extends PIDSubsystem {

    
    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    private static final double DEFAULT_FORKLIFT_SPEED = 0.5;
    private static final double DEFAULT_KICKER_SPEED = 0.5;
    private static
    Victor m_forkliftMotor;
    Victor m_kickerMotor;
    AnalogChannel m_forkliftEncoderA;
    AnalogChannel m_forkliftEncoderB;
    AnalogChannel m_kickerEncoderA;
    AnalogChannel m_kickerEncoderB;
    Encoder m_forkliftEncoder;
    Encoder m_kickerEncoder;
    
    static ForkLiftPIDSubsystem instance = null;
      
    public static ForkLiftPIDSubsystem getInstance () {
        if (instance == null) {
            instance = new ForkLiftPIDSubsystem();
        }
        return instance;
    }

    // Initialize your subsystem here
    private ForkLiftPIDSubsystem() {
        super("ForkLiftPIDSubsystem", Kp, Ki, Kd);
        m_forkliftMotor = new Victor(RobotMap.FORKLIFT_MOTOR_CHANNEL);
        m_kickerMotor = new Victor(RobotMap.KICKER_MOTOR_CHANNEL);
        m_kickerEncoder = new Encoder(RobotMap.KICKER_ENCODER_A_CHANNEL,
                                      RobotMap.KICKER_ENCODER_B_CHANNEL,
                                      false);
        m_forkliftEncoder = new Encoder(RobotMap.FORKLIFT_ENCODER_A_CHANNEL,
                                        RobotMap.FORKLIFT_ENCODER_B_CHANNEL,
                                        false);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
    
    public void jogForkliftUdp(){
        m_forkliftMotor.set(DEFAULT_FORKLIFT_SPEED);
    }
    
    public void jogForkliftDown(){
        m_forkliftMotor.set(-1.0*DEFAULT_FORKLIFT_SPEED);
    }
    
    public void jogKickerUp(){
        m_kickerMotor.set(DEFAULT_KICKER_SPEED);
    }
    
    public void jogKickerDown(){
        m_kickerMotor.set(-1.0*DEFAULT_KICKER_SPEED);
    }
    
    public int getForkliftPosition(){
        return 0;
    }
    
    public int getKickerPosition(){
        return 0;
    }
}
