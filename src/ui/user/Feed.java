package src.ui.user;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.plaf.DimensionUIResource;

import java.awt.Color;

import src.utils.message.Message;
import src.utils.message.Tweet;

/**
 * This class is used to control and maintain the news feed of a user
 * It is a JList<Tweets> so it implicitly has a list of tweets
 */
public class Feed extends JList<Message> {
	private Set<Message> messages;

	public Feed() {
		super();
		this.messages = new LinkedHashSet<Message>();
		this.setModel(new DefaultListModel<>());
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
	}

	public void addTweet(Tweet tweet) {
		((DefaultListModel<Message>) this.getModel()).addElement(tweet);
	}

	public List<Tweet> getTweets() {
		List<Tweet> tweetList = new ArrayList<Tweet>();

		for (Message message : messages) {
			tweetList.add((Tweet) message);
		}

		return tweetList;
	}
}
