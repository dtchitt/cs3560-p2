package src.utils.observer;

/**
 * Observer interface
 * An observer waits (non blocking) for a subject to tell it to update.
 * On update, the observer will run its implementation of the update method.
 */
public interface Observer {
	/**
	 * The method called when the subject notifies its observers
	 */
	public void update();
}
