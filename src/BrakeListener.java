package src;

import java.util.EventListener;

public interface BrakeListener extends EventListener {
	public void brake(BrakeEvent event);
}
