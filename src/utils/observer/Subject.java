package src.utils.observer;

/**
 * The subject has a list of observers. Observers can be "attached" (added) or "detatched" (removed) from the list.
 * The subject will notify all observers in its list when the notifyObservers function is called.
 * When the notify function is called, the observers update themselves. 
 */
public interface Subject {
    /**
     * Add an observer to the list.
     * @param observer
     */
    public void attach(Observer observer);

    /**
     * Remove an observer from the list.
     * @param observer
     */
    public void detach(Observer observer);

    /**
     * Notify all attached observers to update.
     */
    public void notifyObervers();
}
