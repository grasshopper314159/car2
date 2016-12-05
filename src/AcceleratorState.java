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
 * Represents the accelerating state
 *
 */
public class AcceleratorState extends AutomobileState
		implements AccelerateListener, BrakeListener, TimerTickedListener, PowerOnListener {
	private static AcceleratorState instance;

	private AcceleratorState() {
		instance = this;
	}

	/**
	 * When the state is changed, this method is called to remove listeners
	 */
	
	@Override
	public void leave() {
		AcceleratorManager.instance().removeAccelerateListener(this);
		// TimerRanOutManager.instance().removeTimerRanOutListener(this);
		TimerTickedManager.instance().removeTimerTickedListener(this);
		PowerOnManager.instance().removePowerOnListener(this);
	}

	/**
	 * For the singleton pattern
	 * 
	 * @return the object
	 */
	public static AcceleratorState instance() {
		if (instance == null) {
			instance = new AcceleratorState();
		}
		return instance;
	}

	/**
	 * Process accelerating event
	 */
	@Override
	public void accelerate(AccelerateEvent event) {

		context.changeCurrentState(AcceleratorState.instance());

		display.displayTimeRemaining(Timer.instance().getTimeValue());

	}

	/**
	 * This method changes the state from this to BrakeState
	 * @param event: BrakeEvent
	 */
	@Override
	public void brake(BrakeEvent event) {
		context.changeCurrentState(BrakeState.instance());
	}

	/**
	 * timer ticked method
	 * @param event: TimerTickedEvent
	 */
	@Override
	public void timerTicked(TimerTickedEvent event) {
		display.displayTimeRemaining(Timer.instance().getTimeValue());
		if (context.getSpeed() < 50) {
			context.updateSpeed(context.getSpeed() + 5);
		} else {
			context.changeCurrentState(DrivingState.instance());

		}
		display.displayTimeRemaining(context.getSpeed());
	}

	/**
	 * Process clock ticks Generates the timer runs out event
	 */
	// @Override
	// public void timerRanOut(TimerRanOutEvent event) {
	// display.displayTimeRemaining(Timer.instance().getTimeValue());
	//
	// }

	@Override
	public void powerOn(PowerOnEvent event) {
		// Power on not valid from accelerating state

	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {

		BrakeManager.instance().addBrakeListener(this);
		display.accelerate();
		display.displayTimeRemaining(context.getSpeed());
		// TimerRanOutManager.instance().addTimerRanOutListener(this);
		TimerTickedManager.instance().addTimerTickedListener(this);
	}

}
