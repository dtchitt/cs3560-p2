package src.utils.visitor;

import src.utils.message.Tweet;

/**
 * interface for the visitor part of the visitor pattern
 */
public interface Visitor {
	public boolean visit(Tweet tweet);
}
