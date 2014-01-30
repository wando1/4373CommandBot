/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.subsystems.forkliftsubsystem;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.afs.robot.RobotMap;

/**
 *
 * @author User
 */
public class ForkLiftPIDSubsystem extends PIDSubsystem {

    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
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
}
