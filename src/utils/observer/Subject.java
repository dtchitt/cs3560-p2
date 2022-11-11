package src.utils.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * A subject to be observed
 */
public abstract class Subject {
	private List<Observer> observers;

	public Subject() {
		this.observers = new ArrayList<Observer>();
	}

	public void attach(Observer observer) {
		this.observers.add(observer);
	}

	public void detach(Observer observer) {
		this.observers.remove(observer);
	}

	public void notifyObervers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public List<Observer> getObsList() {
		return this.observers;
	}
}
