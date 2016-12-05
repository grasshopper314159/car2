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
import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * The Manager classes maintains a list of EventListener objects who want to be notified when this
 * type of event occurs.  The eventState objects call the add/remove listener methods in this
 * class to join or leave the list of eventlisteners for this type of event.  
 * 
 * The Managers are singletons and so are accessed as someManager.instance().someMethod()
 */

public class PowerOnManager {
	private EventListenerList listenerList = new EventListenerList();
	private static PowerOnManager instance;

	private PowerOnManager() {
	}

	/**
	 * Create instance of PowerOnManager
	 * 
	 * @return instance
	 */
	public static PowerOnManager instance() {
		if (instance == null) {
			instance = new PowerOnManager();
		}
		return instance;
	}

	/**
	 * Add PowerOnListener
	 * 
	 * @param listener
	 */
	public void addPowerOnListener(PowerOnListener listener) {
		listenerList.add(PowerOnListener.class, listener);
	}

	/**
	 * Remove PowerOnListener
	 * 
	 * @param listener
	 */
	public void removePowerOnListener(PowerOnListener listener) {
		listenerList.remove(PowerOnListener.class, listener);
	}

	/**
	 * Process PowerOn event
	 * 
	 * @param event
	 */
	public void processEvent(PowerOnEvent event) {
		EventListener[] listeners = listenerList.getListeners(PowerOnListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((PowerOnListener) listeners[index]).powerOn(event);
		}
	}
}