package src.utils.entity;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A usergroup is an entity in the system.
 * usergroups have a set of groups and a set of users to avoid duplicates of course
 */
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
		this.users.add(user);
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void addGroup(UserGroup group) {
		this.groups.add(group);
	}

	public Set<UserGroup> getGroups() {
		return this.groups;
	}
}
