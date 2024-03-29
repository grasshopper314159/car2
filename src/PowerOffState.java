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
 * Represents the powerOff state
 *
 */
public class PowerOffState extends AutomobileState implements PowerOnListener {
	private static PowerOffState instance;

	private PowerOffState() {
		instance = this;
	}

	/**
	 * Remove listeners
	 */
	@Override
	public void leave() {
		PowerOnManager.instance().removePowerOnListener(this);
	}

	/**
	 * For the singleton pattern
	 * 
	 * @return the object
	 */
	public static PowerOffState instance() {
		if (instance == null) {
			instance = new PowerOffState();
		}
		return instance;
	}

	/**
	 * Process powerOn event
	 */
	@Override
	public void powerOn(PowerOnEvent event) {
		context.changeCurrentState(PowerOnState.instance());
	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {
		PowerOnManager.instance().addPowerOnListener(this);
		display.powerOff();
		display.displaySpeed(0);
	}

}