/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.subsystems.launcherSubsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.afs.robot.RobotMap;

/**
 *
 * @author User
 */
public class LauncherSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    //TODO: Determine thresholds on launcher hardware.
    private static final int UPPER_STOP_POSITION = 120;
    private static final int UPPER_SLOW_POSITION = 115;
    private static final int UPPER_MEDIUM_POSITION = 110;

    private static final int LOWER_MEDIUM_POSITION = 15;
    private static final int LOWER_SLOW_POSITION = 10;
    private static final int LOWER_STOP_POSITION = 5;
    
    private static final int MIN_ENCODER_VALUE = 0;
    private static final int MAX_ENCODER_VALUE = 1023;
    
    private static final double STOP = 0.0;
    private static final double LOW_SPEED = 0.1;
    private static final double MEDIUM_SPEED = 0.5;
    private static final double HIGH_SPEED = 1.0;
    
    static LauncherSubsystem instance = null;
    Victor m_leftMotor;
    Victor m_rightMotor;   
    Encoder m_launcherEncoder;
      
    public static LauncherSubsystem getInstance () {
        if (instance == null) {
            instance = new LauncherSubsystem();
        }
        return instance;
    }
    
    private LauncherSubsystem () {
        
        m_leftMotor = new Victor(RobotMap.LAUNCHER_LEFT_MOTOR_CHANNEL);
        m_rightMotor = new Victor(RobotMap.LAUNCHER_RIGHT_MOTOR_CHANNEL);
        m_launcherEncoder = new Encoder(RobotMap.LAUNCHER_ENCODER_A_CHANNEL,
                                        RobotMap.LAUNCHER_ENCODER_B_CHANNEL,
                                        false);
        
        // Assume launcher starts in reset position. Set encoder count to zero.
        m_launcherEncoder.reset();
        // Start encoder count.
        m_launcherEncoder.start();
        resetLauncher();  
    }
    
    public void fireLauncher(){
        if(m_launcherEncoder.get() <= UPPER_STOP_POSITION){
            // Determine motor speed on both launcher motors - ramp up.
            double motorSpeed = getSpeed(getLauncherPosition(), true);
            // Move upward.
            m_leftMotor.set(motorSpeed);
            m_rightMotor.set(motorSpeed); 
        } 
    }
    
    public void resetLauncher(){
        if(m_launcherEncoder.get() >= LOWER_STOP_POSITION){
            // Determine motor speed on both launcher motors - ramp up.
            double motorSpeed = getSpeed(getLauncherPosition(), false);
            // Move downward.
            m_leftMotor.set(motorSpeed);
            m_rightMotor.set(motorSpeed); 
        } 
        
    }
    
    // Returns value from 1.0 (max upward speed) to -1.0 (max downward speed)
    // based on launcher arm position reported by shaft encoder (0 - 1023).
    // We ramp the motor speed up and down to avoid over-stressing 
    // the launch mechanism.
    private double getSpeed(int position, boolean isMovingUp){
        double speed = 0.0;
        
        if ((position >= MIN_ENCODER_VALUE) && 
            (position <= MAX_ENCODER_VALUE)) {
            //***LAUNCH***
            // Map speed to valid position as launcher moves between reset 
            // position and top position.
            if (isMovingUp == true){
                // Ramp speed down as launcher approaches top position.
                if (position >= UPPER_STOP_POSITION){
                    speed = STOP;
                } else if(position >= UPPER_SLOW_POSITION){
                    speed = LOW_SPEED;
                } else if (position >= UPPER_MEDIUM_POSITION){
                    speed = MEDIUM_SPEED;
                } 
                
                // Ramp speed up as launcher leaves reset position moving up.
                else if (position <= LOWER_MEDIUM_POSITION){
                    speed = MEDIUM_SPEED;
                } else if (position <= LOWER_SLOW_POSITION){
                    speed = LOW_SPEED;
                } 
                
                // Set speed to maximum between the two transition regions.
                else {
                    speed = HIGH_SPEED;
                }
            //***RESET*** 
            } else {
                // Ramp speed up as launcher leaves top position moving down.
                if(position >= UPPER_SLOW_POSITION){
                    speed = LOW_SPEED;
                } else if (position >= UPPER_MEDIUM_POSITION){
                    speed = MEDIUM_SPEED;
                } 
                
                // Ramp speed down as launcher approaches reset position.
                else if (position <= LOWER_MEDIUM_POSITION){
                    speed = MEDIUM_SPEED;
                } else if (position <= LOWER_SLOW_POSITION){
                    speed = LOW_SPEED;
                } else if (position <= LOWER_STOP_POSITION){
                    speed = STOP;
                } 
                
                // Set speed to maximum between the two transition regions.
                else {
                    speed = HIGH_SPEED;
                }
                
                // We have the correct magnitude, now set direction.
                speed = -speed;
            }  // End of if (isMovingUp == true) - else...
            
        } else {
            System.out.println("LauncherSubsystem::getSpeed::" + 
                               "position arg out of range: " +
                               position);
        }
       
        return speed;
    }
    
    private int getLauncherPosition(){
       return m_launcherEncoder.get();
    }
    
    public boolean isLauncherReset(){
        if (m_launcherEncoder.get() <= LOWER_STOP_POSITION){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isLauncherFired(){
        if (m_launcherEncoder.get() >= UPPER_STOP_POSITION){
            return true;
        } else {
            return false;
        }
    }

    public void initDefaultCommand() {
        //TODO:Send launcher position to OI.
    }
}
