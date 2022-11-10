package src.utils.observer;

import java.util.ArrayList;
import java.util.List;

import src.utils.messages.Tweet;

public class Feed extends Subject {
	private List<Tweet> tweets;

	public Feed() {
		super();
		this.tweets = new ArrayList<Tweet>();
	}

	public void addTweet(Tweet tweet) {
		this.tweets.add(tweet);
		this.notifyObervers();
	}

	public void deleteTweet(Tweet tweet) {
		this.tweets.remove(tweet);
	}

	public List<Tweet> getTweets() {
		return tweets;
	}
}
