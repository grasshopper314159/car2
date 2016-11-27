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
import java.util.Observer;

public class Timer implements Observer {
	private static Timer instance;
	private int timeValue;

	private Timer() {
		instance = this;
		Clock.instance().addObserver(instance);
	}

	public static Timer instance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}

	public void setTimeValue(int value) {
		this.timeValue = value;
	}

	public void addTimeValue(int value) {
		timeValue += value;
	}

	public int getTimeValue() {
		return timeValue;
	}

	@Override
	public void update(Observable clock, Object value) {
		if (value.toString().equals("CLOCK_ACCELERATE_EVENT")) {
			TimerTickedManager.instance().processEvent(new TimerTickedEvent(instance));

			if (!(getTimeValue() >= 50)) {
				addTimeValue(5);
			}

		}
		if (value.toString().equals("CLOCK_DECELERATE_EVENT")) {
			TimerTickedManager.instance().processEvent(new TimerTickedEvent(instance));
			timeValue -= 5;

			if (getTimeValue() == 0) {
				// notify that the system can switch to park
			}

		}

		// if (--timeValue == 0) {
		// TimerRanOutManager.instance().processEvent(new
		// TimerRanOutEvent(instance));
		// } else {
		// TimerTickedManager.instance().processEvent(new
		// TimerTickedEvent(instance));
		// }

	}
}