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
import java.util.Observable;

/**
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.  A superclass for GUIDisplay
 */
public abstract class AutomobileDisplay extends Observable {
	protected static AutomobileContext context;
	protected static AutomobileDisplay instance;

	/**
	 * Initializes the display and instance
	 */
	protected AutomobileDisplay() {
		instance = this;
		context = AutomobileContext.instance();
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static AutomobileDisplay instance() {
		return instance;
	}

	/**
	 * Do the initializations to make the context an observer
	 */
	public void initialize() {
		context.initialize();
	}
	
	/**
	 * Display the speed
	 * @param speed: int
	 */
	public abstract void displaySpeed(int speed);

	/**
	 * Indicate that the car is in drive
	 */
	public abstract void gearInDrive();

	/**
	 * Indicate that the car is in park
	 */
	public abstract void gearInPark();

	/**
	 * Indicate that the power is on
	 */
	public abstract void powerOn();

	/**
	 * Indicate that the power is off
	 */
	public abstract void powerOff();

	/**
	 * indicate that the speed is zero
	 */
	public abstract void stopped();
	
	/**
	 * indicate that the the car is braking
	 */
	public abstract void brake();
	
	/**
	 * indicate that the the car is accelerating
	 */
	public abstract void accelerate();
}