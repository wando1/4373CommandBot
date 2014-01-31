/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.afs.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command group for autonomous operation.
 * 
 * @author RAmato
 */
public class DriveAndShootCommandGroup extends CommandGroup {
    
    public DriveAndShootCommandGroup() {
        addSequential(new AutoDriveToShotRangeCommand());
        //TODO: Position forklift for shooting;
        addSequential(new FireLauncherCommand());
     
    }
}
