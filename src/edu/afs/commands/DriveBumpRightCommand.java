/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Make the robot "bump" right a small amount for button-controlled fine
 * position adjustments.
 * 
 * @author RAmato
 */
public class DriveBumpRightCommand extends CommandBase {
    
    private static final int BUMP_TIMEOUT = 50; //Milliseconds
    private static final double BUMP_ROTATE = 0.5;
    private static final double BUMP_SPEED = 1;
    private boolean m_isBumpDone;
    
    public DriveBumpRightCommand() {
        requires(CommandBase.drive);
        setTimeout(BUMP_TIMEOUT);
        m_isBumpDone = false;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.driveTurn(BUMP_SPEED, BUMP_ROTATE);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_isBumpDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("DriveBumpRightCommmand was interrupted!");
    }
}
