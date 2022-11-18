package src.utils.visitor;

import src.utils.composite.User;
import src.utils.composite.UserGroup;
import src.utils.message.Tweet;

/**
 * Visitor that is used to validate tweets
 */
public class TweetPositivityVisitor implements Visitor {
	public static final String[] POSITIVE_WORDS = {"awesome", "nice", "good", "love", "happy", "lit"};

	@Override
	public int visit(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int visit(UserGroup group) {
		// TODO Auto-generated method stub
		return 0;
	}

	// @Override
	// public int visit(Tweet tweet) {
	// 	String[] words = tweet.getMessage().split(" ");

	// 	for (String word : words) {
	// 		if (Arrays.asList(POSITIVE_WORDS).contains(word.toLowerCase())) {
	// 			return true;
	// 		}
	// 	}

	// 	return false;
	// }
	
}
