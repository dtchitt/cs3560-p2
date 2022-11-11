package src.utils.message;

import src.utils.TimeStamp;
import src.utils.entity.User;

/**
 * This class was made to help be more OOP, every message time will have a string, timestap, and user
 * Every message (tweet,direct message) can extend this class
 */
public abstract class Message {
	private String message;
	private TimeStamp timeStamp;
	private User user;

	public Message(String message, User user) {
		this.message = message;
		this.user = user;
		this.timeStamp = new TimeStamp();
	}

	public String getMessage() {
		return this.message;
	}

	public TimeStamp getTime() {
		return this.timeStamp;
	}

	public User getUser() {
		return this.user;
	}
}