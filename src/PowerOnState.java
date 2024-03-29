package src;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */

/**
 * Represents the powerOn state
 *
 */
public class PowerOnState extends AutomobileState implements DriveRequestListener, PowerOffListener {
	private static PowerOnState instance;

	private PowerOnState() {
		instance = this;
	}

	/**
	 * Remove listeners
	 */
	@Override
	public void leave() {
		DriveRequestManager.instance().removeDriveRequestListener(this);
		PowerOffManager.instance().removePowerOffListener(this);
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static PowerOnState instance() {
		if (instance == null) {
			instance = new PowerOnState();
		}
		return instance;
	}

	/**
	 * handle powerOff event
	 * 
	 */
	@Override
	public void powerOff(PowerOffEvent event) {
		context.changeCurrentState(PowerOffState.instance());
	}

	/**
	 * handle driveRequested event
	 * 
	 */
	@Override
	public void driveRequested(DriveRequestEvent event) {
		context.changeCurrentState(DrivingState.instance());
	}

	/**
	 * initialize the state
	 * 
	 */
	@Override
	public void run() {
		DriveRequestManager.instance().addDriveRequestListener(instance);
		PowerOffManager.instance().addPowerOffListener(instance);
		display.powerOn();
	}

}