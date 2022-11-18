package src.utils.visitor;

import src.utils.composite.User;
import src.utils.composite.UserGroup;

/**
 * interface for the visitor part of the visitor pattern
 */
public interface Visitor {
	public int visit(User user);
	public int visit(UserGroup group);
}
