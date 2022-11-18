package src.utils.message;

import src.utils.composite.User;
import src.utils.visitor.Visitable;
import src.utils.visitor.Visitor;

/**
 * Tweets are a type of message, tweets are formattable and can be visited to be checked for "positivity"
 */
public class Tweet extends Message {
	public Tweet(String message, User user) {
		super(message, user);
	}

	public String format() {
		return this.getTime().getCreationDate() + " " + this.getUser().getIdName() + " tweeted: " + this.getMessage();
	}
}
