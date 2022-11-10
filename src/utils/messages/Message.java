package src.utils.messages;

import src.utils.TimeStamp;
import src.utils.entity.User;

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