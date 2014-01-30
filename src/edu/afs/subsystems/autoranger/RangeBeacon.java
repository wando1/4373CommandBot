/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.afs.subsystems.autoranger;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Class to drive indicator lights on bot to aid drivers when setting up for 
 * a shot on the 10-point goal.
 * 
 * @author RAmato
 */

public class RangeBeacon {
       
    // Set up three analog outputs to drive red, yellow, and green lights
    // mounted on the bot.
    //
    // Red - auto-ranging is disabled.
    // Yellow - auto-ranging is in progress.  Bot moving to shooting range.
    // Green - bot at shooting range.  The green indicator is latched by a timer 
    // to keep it lit for five seconds after 
    
    public static long GREEN_INDICATOR_TIMEOUT = 5000; //Time in milliseconds.
    private Timer m_greenTimer;
    
    
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
        //TODO: Initialize the outputs - RED ON, YELLOW, GREEN - OFF
        Timer m_greenTimer = new Timer();
    }
    
    class GreenTimerTask extends TimerTask {
        public void run() {
            //TODO: GREEN-OFF.
            m_greenTimer.cancel(); //Terminate the timer thread
        }
    }
    
    public void SetAtShotRangeIndicator(boolean atShotRange){
        if(atShotRange== true){
            //TODO: GREEN-ON
        } else {
            //TODO: GREEN-OFF
        }
    }
     
    public void setRangeBeaconState (RangeBeaconState state){
        if(state.equals(RangeBeaconState.AUTO_RANGE_DISABLED)){
            //TODO: Set outputs:
            //RED - ON
            //YELLOW - OFF
        } else if (state.equals(RangeBeaconState.AUTO_RANGE_ENABLED)){
            //TODO: Set outputs:
            //RED - OFF
            //YELLOW - ON
            //GREEN - OFF
        } else { // state.equals(RangeBeaconState.AUTO_RANGE_COMPLETE)
            //TODO: Set outputs:
            //GREEN - ON for five seconds.
            m_greenTimer.schedule(new GreenTimerTask(), GREEN_INDICATOR_TIMEOUT);
            
            
        } 
    }
    
}
