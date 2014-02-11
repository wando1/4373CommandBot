
package edu.afs.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.DigitalIOButton; <-- As of now this is not being used so I commented it out
import edu.wpi.first.wpilibj.Joystick;
import edu.afs.commands.DriveBumpLeftCommand;
import edu.afs.commands.DriveBumpRightCommand;
import edu.afs.commands.DriveBumpForwardCommand;
import edu.afs.commands.DriveBumpReverseCommand;
import edu.afs.commands.ManualForkliftCommand;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());public static final int JOYSTICK_PORT = 1;
      
    
    private static OI instance = null;
    private Joystick driveJoystick;
    private JoystickButton bumpLeftButton;
    private JoystickButton bumpRightButton;
    private JoystickButton bumpForwardButton;
    private JoystickButton bumpReverseButton;
    private JoystickButton jogForkliftUp;
    private JoystickButton jogForkliftDown;
    
    public static OI getInstance () {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }
    
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }
    
    public JoystickButton getJogUpButton() {
        return jogForkliftUp;
    }
    
    public JoystickButton getJogDownButton() {
        return jogForkliftDown;
        
    }
    
    private OI() {
        driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK_PORT);
        bumpLeftButton = new JoystickButton(driveJoystick, RobotMap.BUMP_LEFT_BUTTON);
        bumpRightButton = new JoystickButton(driveJoystick, RobotMap.BUMP_RIGHT_BUTTON);
        bumpForwardButton = new JoystickButton(driveJoystick, RobotMap.BUMP_FORWARD_BUTTON);
        bumpReverseButton = new JoystickButton(driveJoystick, RobotMap.BUMP_REVERSE_BUTTON);
        
        jogForkliftUp = new JoystickButton(driveJoystick, RobotMap.JOG_FORKLIFT_UP);
        jogForkliftDown = new JoystickButton(driveJoystick, RobotMap.JOG_FORKLIFT_DOWN);
        
        bumpLeftButton.whenPressed(new DriveBumpLeftCommand());
        bumpRightButton.whenPressed(new DriveBumpRightCommand());
        bumpForwardButton.whenPressed(new DriveBumpForwardCommand());
        bumpReverseButton.whenPressed(new DriveBumpReverseCommand());
        
        //jogForkliftUp.whileHeld(new ManualForkliftCommand(true));
        //jogForkliftDown.whileHeld(new ManualForkliftCommand(false));
    }
    
}
