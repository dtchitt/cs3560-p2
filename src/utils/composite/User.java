package src.utils.composite;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import src.utils.message.Feed;
import src.utils.message.Tweet;
import src.utils.observer.Observer;
import src.utils.observer.Subject;
import src.utils.visitor.Visitor;

/**
 * A user is an entity in the system and is a leaf in the composite pattern
 * The user has a feed which contains tweets.
 * The user has a SET of followers and following, this is to garuntee no
 * duplicates
 */
public class User extends Entity implements Subject, Observer {
	private Set<User> followers; // observers
	private Set<User> following; // subjects
	private List<Tweet> tweets; // tweets by this user
	private Feed feed;

	public User(String uniqueID) {
		super(uniqueID);

		this.followers = new LinkedHashSet<>();
		this.following = new LinkedHashSet<>();
		this.tweets = new ArrayList<>();
		this.feed = new Feed();

		// Forces Users to be a leaf in the tree
		this.allowsChildren = false;
	}

	/**
	 * Adds this user to the follow list of another
	 * This user will become an observer of the subject passed in
	 * 
	 * @param user
	 */
	public void followUser(User user) {
		this.following.add(user);
		user.addFollower(this);
	}

	/**
	 * Checks if a user is being followed by this user
	 * @param user the user to check if following
	 * @return true if following passed in user
	 */
	public boolean isFollowing(User user) {
		return this.following.contains(user);
	}

	/**
	 * Gets all the users this user is following
	 * @return A set of all users being followed
	 */
	public Set<User> getFollowing() {
		return following;
	}

	/**
	 * Adds a user to the list of users following this user
	 * 
	 * @param user the user that is now following this user
	 */
	public void addFollower(User user) {
		this.followers.add(user);
		this.attach(user);
	}

	/**
	 * Handle adding a tweet to users feed
	 * @param msg the message of the tweet
	 * @return the added tweet
	 */
	public Tweet addTweet(String msg) {
		Tweet tweet = new Tweet(msg, this);
		this.tweets.add(tweet);
		this.feed.addTweet(tweet);
		this.notifyObservers();

		return tweet;
	}

	/**
	 * Gets the feed instance owned by this user
	 * 
	 * @return the users feed
	 */
	public Feed getFeed() {
		return this.feed;
	}

	/**
	 * Gets the list of tweets made by this user
	 * @return a list of tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * Attach an observer to this subject
	 */
	@Override
	public void attach(Observer observer) {
		this.followers.add((User) observer);
	}

	/**
	 * Notify all observers of this subject
	 */
	@Override
	public void notifyObservers() {
		for (User observer : followers) {
			observer.update(this);
		}
	}

	/**
	 * Add the subjects new tweet to observers feed
	 */
	@Override
	public void update(Subject subject) {
		List<Tweet> subjectTweets =  ((User) subject).getTweets();
		Tweet tweet = subjectTweets.get(subjectTweets.size() - 1);
		this.feed.addTweet(tweet);
	}

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
