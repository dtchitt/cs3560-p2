package src.utils.entity;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserGroup extends Entity {
	private Set<UserGroup> groups;
	private Set<User> users;

	public UserGroup(String uniqueID) {
		super(uniqueID);

		this.groups = new HashSet<UserGroup>();
		this.users = new LinkedHashSet<User>();
	}

	public boolean isUserGroup() {
		return this instanceof UserGroup;
	}

	public void addUser(User user) {
		this.add(user);
		this.addUserToList(user);
	}

	private void addUserToList(User user) {
		this.users.add(user);
	}

	public void addAllUsers(Set<User> users) {
		this.users.addAll(users);
	}

	public Set<User> getUsers() {
		Set<User> userSet = new LinkedHashSet<User>(this.users);
		return userSet;
	}

	public void clearUsers() {
		this.users.clear();
	}

	public void addGroup(UserGroup group) {
		this.groups.add(group);
	}

	public void addAllGroups(Set<UserGroup> groups) {
		this.groups.addAll(groups);
	}

	public Set<UserGroup> getGroups() {
		Set<UserGroup> groupSet = new HashSet<UserGroup>(this.groups);
		return groupSet;
	}

	public void clearGroups() {
		this.groups.clear();
	}
}
