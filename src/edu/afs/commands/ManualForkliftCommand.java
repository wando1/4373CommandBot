/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import edu.afs.robot.OI;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.afs.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.afs.subsystems.forkliftsubsystem.*;
/**
 *
 * @author 15BenjaminH
 */
public class ManualForkliftCommand extends CommandBase {
    
    private Button m_upButton;
    private Button m_downButton;
    private boolean m_direction;
    private ForkLiftPIDSubsystem forklift;
    
    public ManualForkliftCommand(boolean direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //true = up, false = down
        m_direction = direction;
        requires(CommandBase.forkLift);
        m_upButton = OI.getInstance().getJogUpButton();
        m_downButton = OI.getInstance().getJogDownButton();
            
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putBoolean("Forklift Disabled?", forklift.getEnabled());
        if (m_direction == true){
            //make Forklift go up
        }
        else {
            //make Forklift go down
        }
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
