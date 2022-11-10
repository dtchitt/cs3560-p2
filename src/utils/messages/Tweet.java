package src.utils.messages;

import src.utils.entity.User;

public class Tweet extends Message {
	public Tweet(String message, User user) {
		super(message, user);
	}

	public String format() {
		return this.getTime().getCreationDate() + " " + this.getUser().getIdName() + " tweeted: " + this.getMessage();
	}
}
