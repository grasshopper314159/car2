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
 * Represents the brake state
 *
 */
public class BrakeState extends AutomobileState
		implements AccelerateListener, ParkListener, DriveRequestListener, TimerTickedListener {
	private static BrakeState instance;

	private BrakeState() {
		instance = this;
	}
	/**
	 * leave()
	 * When the state is changed, this method is called to remove listeners
	 */
	@Override
	public void leave() {
		AcceleratorManager.instance().removeAccelerateListener(this);
		TimerTickedManager.instance().removeTimerTickedListener(this);
		ParkManager.instance().removeParkListener(this);
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
	public void accelerate(AccelerateEvent event) {
		context.changeCurrentState(AcceleratorState.instance());
	}
	
	/**
	 * timer ticked method
	 * The car's speed decreases by 5 mph every tick 
	 * of the timer.  If the speed is at 0, it won't
	 * subract any more
	 * 
	 * @param event: TimerTickedEvent
	 */
	@Override
	public void timerTicked(TimerTickedEvent event) {
		if (context.getSpeed() > 0) {
			context.updateSpeed(context.getSpeed() - 5);
		} 
		display.displaySpeed(context.getSpeed());
	}

	/**
	 * handle park event
	 * 
	 */
	@Override
	public void park(ParkEvent event) {
		if (context.getSpeed() == 0) {
		context.changeCurrentState(ParkState.instance());
		}
	}

	/**
	 * handle driveRequested event
	 * 
	 */
	@Override
	public void driveRequested(DriveRequestEvent event) {
		// TODO Auto-generated method stub		context.changeCurrentState(DrivingState.instance());
	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {

		AcceleratorManager.instance().addAccelerateListener(this);
		ParkManager.instance().addParkListener(this);
		display.brake();
		display.displaySpeed(context.getSpeed());
		TimerTickedManager.instance().addTimerTickedListener(this);

	}

}


