package src.utils.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import src.utils.observer.Feed;
import src.utils.message.Tweet;
import src.utils.observer.Observer;
import src.utils.observer.Subject;

/**
 * A user is an entity in the system.
 * The user has a feed which contains tweets.
 * The user has a SET of followers and following, this is to garuntee no duplicates
 */
public class User extends Entity implements Subject, Observer {
	private Set<User> followers;
	private Set<User> following;
	private Set<Observer> observers;
	private Feed feed;
	
	public User(String uniqueID) {
		super(uniqueID);

		this.followers = new LinkedHashSet<>();
		this.following = new LinkedHashSet<>();
		this.feed = new Feed();

		//Forces Users to be a leaf in the tree
		this.allowsChildren = false;
	}

	public boolean isUser() {
		return this instanceof User;
	}

	public void addFollower(User who) {
		this.followers.add(who);
	}

	public List<User> getFollowers() {
		List<User> result = new ArrayList<User>();

		for (User user : this.followers) {
			result.add(user);
		}

		return result;
	}

	public void addfollowing(User who) {
		this.following.add(who);
	}

	public List<User> getFollowing() {
		List<User> result = new ArrayList<User>();

		for (User user : this.following) {
			result.add(user);
		}

		return result;
	}

	public void tweet(Tweet tweet) {
		this.feed.addTweet(tweet);
	}

	public List<Tweet> getTweets() {
		return this.feed.getTweets();
	}

	public Feed getFeed() {
		return this.feed;
	}

	public int getTweetCount() {
		return this.getTweets().size();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void attach(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void detach(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObervers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}
