/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import edu.afs.robot.RobotMap;
import edu.afs.subsystems.autoranger.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This command will move the bot straight forward under auto-ranger and
 * bearing stabilizer control.  NOTE: Shooter-side forward.
 * 
 * The command ends under three conditions:
 *
 *  1) The auto-ranger indicates that the bot has reached shot range.
 *  2) The safety timer expires.
 *  3) The operator makes manual control inputs via the joystick.
 *
 * @author RAmato
 */
public class AutoDriveToShotRangeCommand extends CommandBase {

    private static final double DESIRED_RANGE = 24.0; // Range in inches.
    private static final double RANGE_TOLERANCE = 12.0; // In inches.
    private static final double STOP = 0.0;

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Pick a timeout that allows the bot time to move the maximum
    // expected distance to reach shot range (during autonomous mode???).
    // We may have to clone this command so that we can specify a shorter
    // timeout during tele-op mode.  As a consequence, the bot will have
    // to be positioned closer to shot range before this command is engaged.
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    protected static double DRIVE_SAFETY_TIMEOUT = 4.0; //Time in seconds.
    private boolean m_isAutoRangeDone;
    boolean m_driveControlsInverted;

    public AutoDriveToShotRangeCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.drive);
        requires(CommandBase.autoRanger);
        setTimeout(DRIVE_SAFETY_TIMEOUT);
        m_isAutoRangeDone = false;
        m_driveControlsInverted = false;
    }
    
    

    // Called just before this Command runs the first time
    protected void initialize() {
        drive.initBearingStabilizer();
        autoRanger.setSetpoint(DESIRED_RANGE);
        autoRanger.initAutoRanger();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        m_driveControlsInverted = SmartDashboard.getBoolean(RobotMap.SMARTDASHBOARD_INVERTED_DRIVE);
        drive.driveStraightGyroStabilized(autoRanger.getAutoRangerOutput(), m_driveControlsInverted);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (autoRanger.onTarget() == true){
            m_isAutoRangeDone = true;
            //Turn on the green indicator.
            RangeBeacon.getInstance().
                    setRangeBeaconState(RangeBeaconState.AUTO_RANGE_COMPLETE);
        }else if (isTimedOut()== true){
            m_isAutoRangeDone = true;
        }
        // m_isAutoRangeDone can be set true by timeout.
        return m_isAutoRangeDone;
    }

    // Called once after isFinished returns true
    protected void end() {
        autoRanger.disableAutoRanger();
        drive.driveStraightGyroStabilized(STOP, m_driveControlsInverted);
        drive.disableBearingStabilizer();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        // Manual joystick input will override auto mode.
        System.out.println("AutoDriveToShotRangeCommand interrupted.");
        drive.driveStraightGyroStabilized(STOP, m_driveControlsInverted);
        autoRanger.disableAutoRanger();
        drive.disableBearingStabilizer();
    }
    
    public static double GetDesiredRange(){
        return DESIRED_RANGE;
    }
}
