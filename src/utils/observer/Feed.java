package src.utils.observer;

import java.util.ArrayList;
import java.util.List;

import src.utils.message.Tweet;

/**
 * Feeds serve as a users list of visable tweets on the users dash board
 * Feeds can be observed by a userviews.
 * I figured instead of subscripting to a user, I can have my observer pattern more general purpose in case I needed to add to it
 */
public class Feed {
	private List<Tweet> tweets;

	public Feed() {
		super();
		this.tweets = new ArrayList<Tweet>();
	}

	public void addTweet(Tweet tweet) {
		this.tweets.add(tweet);
	}

	public List<Tweet> getTweets() {
		return tweets;
	}
}
