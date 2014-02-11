/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.afs.subsystems.autoranger;

import edu.afs.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalOutput;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Class to drive indicator lights on bot to aid drivers when setting up for 
 * a shot on the 10-point goal.
 * 
 * @author RAmato
 */

public class RangeBeacon {
       
    // Set up three digital outputs to drive red, yellow, and green lights
    // mounted on the bot.
    //
    // Red - auto-ranging is disabled.
    // Yellow - auto-ranging is in progress.  Bot moving to shooting range.
    // Green - bot at shooting range.  
    
    private DigitalOutput m_redLight;
    private DigitalOutput m_yellowLight;
    private DigitalOutput m_greenLight;
    
    
    // Implement Singleton design pattern.  There 
    // can be only one instance of this class.
    private static RangeBeacon instance = null;  
    public static RangeBeacon getInstance () {
        if (instance == null) {
            instance = new RangeBeacon();
        }
        return instance;
    }
   // Constructor is private to enforce Singelton.
    private RangeBeacon(){
               
        m_redLight = new DigitalOutput(RobotMap.RANGE_BEACON_RED_CHANNEL);
        m_yellowLight = new DigitalOutput(RobotMap.RANGE_BEACON_YELLOW_CHANNEL);
        m_greenLight = new DigitalOutput(RobotMap.RANGE_BEACON_GREEN_CHANNEL);
        
        // Initialize the outputs - RED ON, YELLOW, GREEN - OFF
        m_redLight.set(true);
        m_yellowLight.set(false);
        m_greenLight.set(false);
    }
    
 
    public void SetAtShotRangeIndicator(boolean atShotRange){
        if(atShotRange== true){
            // Set GREEN-ON
            m_greenLight.set(true);
        } else {
            // Set GREEN-OFF
            m_greenLight.set(false);
        }
    }
     
    public void setRangeBeaconState (RangeBeaconState state){
        if(state.equals(RangeBeaconState.AUTO_RANGE_DISABLED)){
            // Set RED - ON
            m_redLight.set(true);
            // Set YELLOW - OFF
            m_yellowLight.set(false);
        } else if (state.equals(RangeBeaconState.AUTO_RANGE_ENABLED)){
            // Set RED - OFF
            m_redLight.set(false);
            // Set YELLOW - ON
            m_yellowLight.set(true);
            // Set GREEN - OFF
            m_greenLight.set(false);
        } else { // state.equals(RangeBeaconState.AUTO_RANGE_COMPLETE)
            // Set GREEN - ON 
            m_greenLight.set(true);
        } 
    }
    
}
