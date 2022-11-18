package src.utils.composite;

import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.TreeNode;

import src.utils.visitor.Visitor;

/**
 * UserGroup functions as a non leaf node in the tree and as the composite part of the composite pattern.
 * I do not explicitly keep a list of users and groups. I feel that due to this being a tree, it implicity already has children that are entities, thus this is not a leaf.
 * I can collect the users and groups within this group on hand by iterating over the children (entities) of this node.
 * This class also provides an easy way to get users and groups separately
 */
public class UserGroup extends Entity {

	public UserGroup(String uniqueID) {
		super(uniqueID);
	}

	public Set<User> getUsers() {
		Set<User> users = new HashSet<>();

		for (int i = 0; i < this.getChildCount() - 1; i++) {
			TreeNode child = this.getChildAt(i);

			if (!this.getAllowsChildren()) {
				users.add((User) child);
			}
		}

		return users;
	}

	public Set<UserGroup> getGroups() {
		Set<UserGroup> groups = new HashSet<>();

		for (int i = 0; i < this.getChildCount() - 1; i++) {
			TreeNode child = this.getChildAt(i);

			if (this.getAllowsChildren()) {
				groups.add((UserGroup) child);
			}
		}

		return groups;
	}

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
