/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Make the robot "bump" left a small amount for button-controlled fine
 * position adjustments.
 * 
 * @author RAmato
 */
public class DriveBumpLeftCommand extends CommandBase {
    
    private static final int BUMP_TIMEOUT = 50; //Milliseconds
    private static final double BUMP_ROTATE = -0.5;
    private Timer m_bumpTimer;
    private boolean m_isBumpDone;
    
    public DriveBumpLeftCommand() {
        requires(drive);
        Timer m_bumpTimer = new Timer();
        m_isBumpDone = false;
    }
    
        class BumpTimerTask extends TimerTask {
        public void run() {
            m_isBumpDone = true;
            m_bumpTimer.cancel(); //Terminate the timer thread
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        m_bumpTimer.schedule(new BumpTimerTask(), BUMP_TIMEOUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.driveTurn(BUMP_ROTATE);
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
    }
}
