package src.utils.message;

import src.utils.entity.User;
import src.utils.visitor.Visitable;
import src.utils.visitor.Visitor;

public class Tweet extends Message implements Visitable {
	public Tweet(String message, User user) {
		super(message, user);
	}

	public String format() {
		return this.getTime().getCreationDate() + " " + this.getUser().getIdName() + " tweeted: " + this.getMessage();
	}

	@Override
	public boolean accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
