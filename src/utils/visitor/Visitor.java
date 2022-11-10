package src.utils.visitor;

import src.utils.message.Tweet;

public interface Visitor {
	public boolean visit(Tweet tweet);
}
