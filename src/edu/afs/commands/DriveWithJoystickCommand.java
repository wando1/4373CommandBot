/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import edu.afs.subsystems.drivesubsystem.*;
import edu.afs.subsystems.autoranger.*;
import edu.afs.commands.AutoDriveToShotRangeCommand;
import edu.afs.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author User
 */
public class DriveWithJoystickCommand extends CommandBase {
    
    private static double RANGE_TOLERANCE = 12.0;  // In inches.
    RangeBeacon m_rangeBeacon;
    double m_desiredRange;
    boolean m_driveControlsInverted;
    
    public DriveWithJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.drive); // reserve the chassis subsystem
        m_rangeBeacon = RangeBeacon.getInstance();
        m_desiredRange = AutoDriveToShotRangeCommand.GetDesiredRange();
        m_driveControlsInverted = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Check the range to target.
        double range = autoRanger.GetRange();
        double rangeInInches = autoRanger.GetRangeInches();
        SmartDashboard.putNumber(RobotMap.SMARTDASHBOARD_AUTORANGER_VALUE, range);
        SmartDashboard.putNumber(RobotMap.SMARTDASHBOARD_AUTORANGER_VALUE_INCHES, rangeInInches);
        double absError = Math.abs(m_desiredRange - range);
        if(absError <= RANGE_TOLERANCE){
            // At shooting range - set beaon indicator.
            m_rangeBeacon.SetAtShotRangeIndicator(true);
            SmartDashboard.putBoolean(RobotMap.SMARTDASHBOARD_AUTORANGER_AT_DESIRED_VALUE, true);
        } else {
            m_rangeBeacon.SetAtShotRangeIndicator(false);
            SmartDashboard.putBoolean(RobotMap.SMARTDASHBOARD_AUTORANGER_AT_DESIRED_VALUE, true);
        }
        //Whether or not the Controls should be inverted is taken Directly from the Dashboard, Defaulted to False.
        m_driveControlsInverted = SmartDashboard.getBoolean(RobotMap.SMARTDASHBOARD_INVERTED_DRIVE);
        drive.driveWithJoystick(m_driveControlsInverted);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}