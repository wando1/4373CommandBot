package edu.afs.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.afs.robot.OI;
import edu.afs.subsystems.drivesubsystem.*;
import edu.afs.subsystems.forkliftsubsystem.*;
import edu.afs.subsystems.launcherSubsystem.*;
import edu.afs.subsystems.autoranger.*;


/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    // Create a single static instance of all of your subsystems
    //public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    protected static OI oi;
    protected static AutoRangerPIDSubsystem autoRanger;
    protected static DrivePIDSubsystem drive;  
    protected static ForkLiftPIDSubsystem forkLift;
    protected static LauncherSubsystem launcher;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = OI.getInstance();
        autoRanger = AutoRangerPIDSubsystem.getInstance();
        drive = DrivePIDSubsystem.getInstance();  
        forkLift = ForkLiftPIDSubsystem.getInstance();
        launcher = LauncherSubsystem.getInstance();

        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData(exampleSubsystem);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
