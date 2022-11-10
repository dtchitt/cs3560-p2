package src.utils.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import src.utils.observer.Feed;

public class User extends Entity {
	private Set<User> followers;
	private Set<User> following;
	private Feed feed;
	
	public User(String uniqueID) {
		super(uniqueID);

		this.followers = new LinkedHashSet<>();
		this.following = new LinkedHashSet<>();
		this.feed = new Feed();
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

	public Feed getFeed() {
		return feed;
	}
}
