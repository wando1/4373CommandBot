/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.subsystems.autoranger;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.AnalogChannel;

import edu.afs.robot.RobotMap;
import edu.afs.subsystems.drivesubsystem.DrivePIDSubsystem;

/**
 *
 * @author User
 */
public class AutoRangerPIDSubsystem extends PIDSubsystem {

    // PID tuning parameters
    //TODO: Pick tuning parameters.
    private static final double K_PROPORTIONAL = 1.0;
    private static final double K_INTEGRAL = 0.0;
    private static final double K_DERIVATIVE = 0.0;
  
    private static final double ABSOLUE_RANGER_TOLERANCE = 6.0; // In inches.
    private static final double MAX_ULTRASONIC_RANGER_VOLTAGE = 10.0;
    private static final double MIN_ULTRASONIC_RANGER_VOLTAGE = -10.0;
    private static final double VOLTS_PER_INCH = 0.009766;
   
    private double m_autoRangeOutput;
    private AnalogChannel m_ultraSonicRanger;
    private double m_autoRangeSetPoint;
    private RangeBeacon m_rangeBeacon;
    
    // Implement Singleton design pattern.  There 
    // can be only one instance of this subsystem.
    private static AutoRangerPIDSubsystem instance = null;  
    public static AutoRangerPIDSubsystem getInstance () {
        if (instance == null) {
            instance = new AutoRangerPIDSubsystem();
        }
        return instance;
    }
   // Constructor is private to enforce Singelton.
    private AutoRangerPIDSubsystem() {
        super("autoRangerPIDSubsystem", 
              K_PROPORTIONAL,
              K_INTEGRAL, 
              K_DERIVATIVE);

        getPIDController().disable();
        m_autoRangeOutput = 0.0;
        m_autoRangeSetPoint = 0.0;
        
        m_ultraSonicRanger = new AnalogChannel
                                        (RobotMap.ULTRASONIC_RANGER_CHANNEL);
        m_rangeBeacon = RangeBeacon.getInstance();
        // Set beacon to indicate AUTO_RANGE_DISABLED
        m_rangeBeacon.setRangeBeaconState(RangeBeaconState.AUTO_RANGE_DISABLED);
    }
    
     public void initAutoRanger(){
        // Add Ultrasonic Ranger and PID set-up code.
        // TODO: Determine correct tolerance for "close enough" to set point.
        getPIDController().setAbsoluteTolerance(ABSOLUE_RANGER_TOLERANCE);
        
        // Input range is not continuous.
        getPIDController().setContinuous(false);
        
        //TODO: Verify output range from US Ranger (-10.0 to 10.0 volts?????)
        getPIDController().setInputRange(MIN_ULTRASONIC_RANGER_VOLTAGE, 
                                         MAX_ULTRASONIC_RANGER_VOLTAGE);
        
        // Set output range to match Drive.
        getPIDController().setOutputRange(DrivePIDSubsystem.DRIVE_MIN_INPUT, 
                                          DrivePIDSubsystem.DRIVE_MAX_INPUT);
        
        // Establish set point for auto-ranging.
        // Assume that bot is oriented perpendicular to 10-point goal.
        //TODO: Verify that this makes sense given PID input range/units.
        getPIDController().setSetpoint(m_autoRangeSetPoint); 
        getPIDController().enable();
        
         // Set beacon to AUTO_RANGE_ENABLED.
        m_rangeBeacon.setRangeBeaconState(RangeBeaconState.AUTO_RANGE_ENABLED);
    }
    
     public void disableAutoRanger(){
         getPIDController().reset();
         getPIDController().disable();
         // Set beacon to AUTO_RANGE_DISABLED.
         m_rangeBeacon.setRangeBeaconState
                                    (RangeBeaconState.AUTO_RANGE_DISABLED);
     }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getAutoRangerOutput(){
        return m_autoRangeOutput;
    }
    
    //TODO: Make sure that units and range of values make sense for PID inputs
    // and outputs!!!!!!!!!!!!!!
    // Called every 20 ms by PID Controller.
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return (m_ultraSonicRanger.getAverageVoltage()/
                MAX_ULTRASONIC_RANGER_VOLTAGE);   
    }

    //TODO: Make sure that units and range of values make sense for PID inputs
    // and outputs!!!!!!!!!!!!!!
    // Called every 20 ms by PID Controller.
    protected void usePIDOutput(double output) {
        // Use output to drive your system.
        m_autoRangeOutput = output;
    }
    
    public double GetRange(){
        //TODO: Verify return is range in TBD units.
        return (m_ultraSonicRanger.getAverageVoltage()
                ///MAX_ULTRASONIC_RANGER_VOLTAGE
                );   
    }
    
    public double GetRangeInches(){
        return (GetRange()/VOLTS_PER_INCH);
    }
}
