package src.utils.message;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;

/**
 * This class is used to control and maintain the news feed of a user
 * It is a JList<Tweets> so it implicitly has a list of tweets
 */
public class Feed extends DefaultListModel<String> {
	private Set<Message> messages;

	public Feed() {
		super();
		this.messages = new LinkedHashSet<Message>();
	}

	public void addTweet(Tweet tweet) {
		this.messages.add(tweet);
		this.addElement(tweet.format());
	}

	public List<Tweet> getTweets() {
		List<Tweet> tweetList = new ArrayList<Tweet>();

		for (Message message : messages) {
			if (message instanceof Tweet) {
				tweetList.add((Tweet) message);
			}
		}

		return tweetList;
	}
}
