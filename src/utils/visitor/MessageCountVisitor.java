package src.utils.visitor;

import src.utils.composite.User;
import src.utils.composite.UserGroup;

public class MessageCountVisitor implements Visitor {

	@Override
	public int visit(User user) {
		return user.getTweetCount();
	}

	@Override
	public int visit(UserGroup group) {
		return 0;
	}
    
}
