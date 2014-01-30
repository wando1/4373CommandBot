/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import java.util.Timer;
import java.util.TimerTask;

import edu.afs.subsystems.autoranger.*;
/**
 * This command will move the bot straight forward under auto-ranger and
 * bearing stabilizer control.  The command ends under three conditions:
 *
 *  1) The auto-ranger indicates that the bot has reached shot range.
 *  2) The safety timer expires.
 *  3) The operator makes manual control inputs via the joystick.
 *
 * @author RAmato
 */
public class AutoDriveToShotRangeCommand extends CommandBase {

    private static final double DESIRED_RANGE = 120.0; // Range in inches.
    private static final double STOP = 0.0;

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Pick a timeout that allows the bot time to move the maximum
    // expected distance to reach shot range (during autonomous mode???).
    // We may have to clone this command so that we can specify a shorter
    // timeout during tele-op mode.  As a consequence, the bot will have
    // to be positioned closer to shot range before this command is engaged.
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    public static long DRIVE_SAFETY_TIMEOUT = 2500; //Time in milliseconds.
    private Timer m_safetyTimer;
    private boolean m_isAutoRangeDone;

    public AutoDriveToShotRangeCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(drive);
        requires(autoRanger);
        Timer m_safetyTimer = new Timer();
        m_isAutoRangeDone = false;
    }
    
    // Safety timer is intended to stop this command and keep bot from crashing
    // into a wall if the auto-ranger fails to detect that bot has passed shot
    // range.  Note that the drive's default command is to move under joystick
    // control.  Selecting the timeout is tricky. Assuming the auto-ranger wiggs
    // out, if the timeout is too long, the bot will continue forward until it
    // hits the wall.  If it is too short, this command will time out before it
    // can reach shot range (and have to be re-started).
    class SafetyTimerTask extends TimerTask {
        public void run() {
            m_isAutoRangeDone = true;
            System.out.println("AutoDriveToShotRangeCommand::SafetyTimerTask" +
                               "expired.");
            m_safetyTimer.cancel(); //Terminate the timer thread
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drive.initBearingStabilizer();
        autoRanger.setSetpoint(DESIRED_RANGE);
        autoRanger.initAutoRanger();
        // Saftey timer ends command if timeout occurs.
        // This keeps bot from crashing into wall if auto-ranger fails.
        m_safetyTimer.schedule(new SafetyTimerTask(), DRIVE_SAFETY_TIMEOUT);
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.driveStraightGyroStabilized(autoRanger.getAutoRangerOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (autoRanger.onTarget() == true){
            m_isAutoRangeDone = true;
            //Turn on the green indicator.
            RangeBeacon.getInstance().
                    setRangeBeaconState(RangeBeaconState.AUTO_RANGE_COMPLETE);
            // Disable timer.
            m_safetyTimer.cancel();
        }
        // m_isAutoRangeDone can be set true by Safety Timer timeout.
        return m_isAutoRangeDone;
    }

    // Called once after isFinished returns true
    protected void end() {
        autoRanger.disableAutoRanger();
        drive.driveStraightGyroStabilized(STOP);
        drive.disableBearingStabilizer();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        // Manual joystick input will override auto mode.
        System.out.println("AutoDriveToShotRangeCommand interrupted.");
        drive.driveStraightGyroStabilized(STOP);
        m_safetyTimer.cancel();
        autoRanger.disableAutoRanger();
        drive.disableBearingStabilizer();
    }
}
