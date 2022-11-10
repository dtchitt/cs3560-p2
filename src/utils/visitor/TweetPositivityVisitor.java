package src.utils.visitor;

import java.util.Arrays;

import src.utils.message.Tweet;

public class TweetPositivityVisitor implements Visitor {
	public static final String[] POSITIVE_WORDS = {"awesome", "nice", "good", "love", "happy", "lit"};

	@Override
	public boolean visit(Tweet tweet) {
		String[] words = tweet.getMessage().split(" ");

		for (String word : words) {
			if (Arrays.asList(POSITIVE_WORDS).contains(word.toLowerCase())) {
				return true;
			}
		}

		return false;
	}
	
}
