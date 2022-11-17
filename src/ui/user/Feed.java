package src.ui.user;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Color;

import src.utils.message.Tweet;

/**
 * This class is used to control and maintain the news feed of a user
 * It is a JList<Tweets> so it implicitly has a list of tweets
 */
public class Feed extends JList<Tweet> {
	private Set<Tweet> tweets;

	public Feed() {
		super();
		// this.tweets = new LinkedHashSet<Tweet>();
		this.setModel(new DefaultListModel<>());
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		// DefaultListModel<String> model = new DefaultListModel<>();
		// JList<String> list = new JList<>( model );

		// for ( int i = 0; i < customers.length; i++ ){
		// model.addElement( customers[i].getName() );
		// }
	}

	public void addTweet(Tweet tweet) {
		((DefaultListModel<Tweet>) this.getModel()).addElement(tweet);
	}
	// public void addTweet(Tweet tweet) {
	// this.tweets.add(tweet);
	// }

	// public Set<Tweet> getTweets() {
	// return tweets;
	// }
}
