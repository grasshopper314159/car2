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
 * Represents the driving state.
 *
 */
public class DrivingState extends AutomobileState implements BrakeListener, AccelerateListener {
	private static DrivingState instance;

	/**
	 * Private for the singleton pattern
	 */
	private DrivingState() {
		instance = this;
	}

	/**
	 * Removes as a listener from all managers
	 * 
	 */
	@Override
	public void leave() {
		AcceleratorManager.instance().removeAccelerateListener(this);
		BrakeManager.instance().removeBrakeListener(this);
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static DrivingState instance() {
		if (instance == null) {
			instance = new DrivingState();
		}
		return instance;
	}

	@Override
	public void brake(BrakeEvent event) {
		// TODO Auto-generated method stub
		context.changeCurrentState(BrakeState.instance());
	}
	
	@Override
	public void accelerate(AccelerateEvent event) {
		// TODO Auto-generated method stub
		context.changeCurrentState(AcceleratorState.instance());
	}

	@Override
	public void run() {

		Timer.instance().setTimeValue(0);
		AcceleratorManager.instance().addAccelerateListener(this);
		BrakeManager.instance().addBrakeListener(this);
		display.gearInDrive();

	}

}