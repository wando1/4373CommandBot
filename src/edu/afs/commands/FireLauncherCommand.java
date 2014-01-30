/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

/**
 *
 * Launch ball and reset launcher.
 * 
 * @author RAmato
 */
public class FireLauncherCommand extends CommandBase {
    
    // True when first half of launch/reset cylce is compete.
    private boolean m_wasLauncherFired;
    
    public FireLauncherCommand() {
        m_wasLauncherFired = false;
        requires(launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        // Insure start in reset position.
        if(launcher.isLauncherReset() == false){
            launcher.resetLauncher();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(launcher.isLauncherFired() == false){
            // Keep moving the launcher upward until we hit the upper
            // stopping point.
            launcher.fireLauncher();
            
        } else {
            // Launcher reached the upper stopping point.  Now bring it back
            // to the reset position.
            m_wasLauncherFired = true;
            launcher.resetLauncher();
        }
    }

    // Checks to see if launch-reset cycle is complete.
    protected boolean isFinished() {
        if ((m_wasLauncherFired == true) && 
            (launcher.isLauncherReset() == true)){
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        m_wasLauncherFired = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
