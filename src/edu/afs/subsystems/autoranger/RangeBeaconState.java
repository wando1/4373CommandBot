/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.afs.subsystems.autoranger;

/**
 *
 * @author RAmato
 */

    // Work-around for lack of enum support in FRC version of Java (1.3).
public class RangeBeaconState {
    
    public final int value;

        protected static final int DISABLED = 0;
        protected static final int ENABLED = 1;
        protected static final int COMPLETE = 2;

        public static final RangeBeaconState AUTO_RANGE_DISABLED = new RangeBeaconState(DISABLED);
        public static final RangeBeaconState AUTO_RANGE_ENABLED = new RangeBeaconState(ENABLED);
        public static final RangeBeaconState AUTO_RANGE_COMPLETE = new RangeBeaconState(COMPLETE);

        private RangeBeaconState(int value){
            this.value = value;
        }
    
}
