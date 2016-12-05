package src;

/**
 * ICS 172 Group Project 3 Fall 2016
 * @author Suzy Xiong, Nick Lanners, John Rivera, Nate Johnson
 * Based on Class Project 2 Version 2 (Microwave code)/
 * by 
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
 * The Managers are singletons and so are accessed as someManager.instance().method
 */

public class AcceleratorManager {
	private EventListenerList listenerList = new EventListenerList();
	private static AcceleratorManager instance;

	private AcceleratorManager() {
	}


	/**
	 * Create instance of AccelerateManager
	 * 
	 * @return instance
	 */
	
	public static AcceleratorManager instance() {
		if (instance == null) {
			instance = new AcceleratorManager();
		}
		return instance;
	}

	/**
	 * Add AccelerateListener
	 * 
	 * @param listener
	 */
	
	public void addAccelerateListener(AccelerateListener listener) {
		listenerList.add(AccelerateListener.class, listener);
	}

	
	


	/**
	 * Remove AccelerateListener
	 * 
	 * @param listener
	 */
	
	public void removeAccelerateListener(AccelerateListener listener) {
		listenerList.remove(AccelerateListener.class, listener);
	}
	
	/**
	 * Process AccelerateListener event
	 * 
	 * @param event
	 */
	
	public void processEvent(AccelerateEvent event) {
		EventListener[] listeners = listenerList.getListeners(AccelerateListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((AccelerateListener) listeners[index]).accelerate(event);
		}
	}
}
