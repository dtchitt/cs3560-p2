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
		int count = 0;

		for (Tweet tweet : user.getTweets()) {
			for (int i = 0; i < POSITIVE_WORDS.length; i++) {
				if (tweet.getMessage().contains(POSITIVE_WORDS[i])) {
					count++;
				}
			}
		}

		return count;
	}

	@Override
	public int visit(UserGroup group) {
		return 0;
	}
}
