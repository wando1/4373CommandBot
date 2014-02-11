/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import edu.afs.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * Make the robot "bump" left a small amount for button-controlled fine
 * position adjustments.
 * 
 * @author RAmato
 */
public class DriveBumpLeftCommand extends CommandBase {
    
    private static final double BUMP_TIMEOUT = 0.2; // Seconds
    private static final double BUMP_ROTATE = -0.5;
    public static final double BUMP_SPEED = 1;
    private static final double STOP = 0.0;
    private boolean m_driveControlsInverted;
    
    public DriveBumpLeftCommand() {
        requires(CommandBase.drive);
        setTimeout(BUMP_TIMEOUT);
        m_driveControlsInverted = false;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        m_driveControlsInverted = SmartDashboard.getBoolean(RobotMap.SMARTDASHBOARD_INVERTED_DRIVE);
        drive.driveTurn(BUMP_SPEED, BUMP_ROTATE, m_driveControlsInverted);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("DriveBumpLeftCommand was interrupted!");
        drive.driveTurn(STOP, STOP, m_driveControlsInverted);
    }
}
