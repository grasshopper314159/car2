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
 * The context maintains the speed, tracks the current state, handles leaving 
 * and initializing the states, and maintains a reference to the display 
 *
 */
public class AutomobileContext {
	private static AutomobileDisplay automobileDisplay;
	private AutomobileState currentState;
	private static AutomobileContext instance;
	private static int speed;

	/**
	 * Make it a singleton
	 */
	private AutomobileContext() {
		instance = this;
		automobileDisplay = AutomobileDisplay.instance();
		currentState = PowerOffState.instance();
		speed = 0;
	}

	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static AutomobileContext instance() {
		if (instance == null) {
			instance = new AutomobileContext();
		}
		return instance;
	}

	/**
	 * this is called by AutomobileDisplay to set the state to PowerOffState
	 * when the program begins
	 */
	public void initialize() {
		instance.changeCurrentState(PowerOffState.instance());
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState
	 *            the next state
	 */
	public void changeCurrentState(AutomobileState nextState) {
		currentState.leave();
		currentState = nextState;
		currentState.run();
	}

	/**
	 * getSpeed() returns the current speed
	 * @return the current speed: int
	 */
	
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * updateSpeed() changes the current speed
	 * @param the new speed: int
	 */
	
	public void updateSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * Gets the display
	 * 	 * @return the display
	 */
	public AutomobileDisplay getDisplay() {
		return automobileDisplay;
	}
}