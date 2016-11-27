package src;
/**
 *
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010

 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - the use is for academic purpose only
 * - Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * - Neither the name of Brahma Dathan or Sarnath Ramnath
 * may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in
 this module
 * and are not responsible for any loss or damage resulting from its use.
 */

/**
 * Represents the door opened state
 *
 */
public class BrakeState extends AutomobileState implements AccelerateListener, ParkListener, DriveRequestListener,
		BrakeListener, TimerRanOutListener, TimerTickedListener {
	private static BrakeState instance;

	private BrakeState() {
		instance = this;
	}

	@Override
	public void leave() {

		AcceleratorManager.instance().removeAccelerateListener(this);
		BrakeManager.instance().removeBrakeListener(this);
		TimerRanOutManager.instance().removeTimerRanOutListener(this);
		TimerTickedManager.instance().removeTimerTickedListener(this);
	}

	/**
	 * For the singleton pattern
	 *
	 * @return the object
	 */
	public static BrakeState instance() {
		if (instance == null) {
			instance = new BrakeState();
		}
		return instance;
	}

	/**
	 * handle accelerate event
	 * 
	 */

	@Override
	public void brake(BrakeEvent event) {
		context.changeCurrentState(BrakeState.instance());
	}

	@Override
	public void accelerate(AccelerateEvent event) {
		context.changeCurrentState(AcceleratorState.instance());
	}

	@Override
	public void timerTicked(TimerTickedEvent event) {
		// display.displayTimeRemaining(Timer.instance().getTimeValue());
		if (context.getSpeed() > 0) {
			context.updateSpeed(context.getSpeed() - 5);
		} else {
			context.changeCurrentState(DrivingState.instance());
		}
		display.displayTimeRemaining(context.getSpeed());
	}

	@Override
	public void timerRanOut(TimerRanOutEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * handle park event
	 * 
	 */
	@Override
	public void park(ParkEvent event) {
		context.changeCurrentState(ParkState.instance());
	}

	@Override
	public void driveRequested(DriveRequestEvent event) {
		// TODO Auto-generated method stub
		context.changeCurrentState(DrivingState.instance());
	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {

		AcceleratorManager.instance().addAccelerateListener(this);
		// Timer.instance().setTimeValue(Timer.instance().getTimeValue() - 5);
		// display.displayTimeRemaining(Timer.instance().getTimeValue());
		display.brake();
		display.displayTimeRemaining(context.getSpeed());
		TimerTickedManager.instance().addTimerTickedListener(this);
		ParkManager.instance().addParkListener(this);
	}

}

// TEST
