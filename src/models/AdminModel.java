package src.models;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.controllers.EntityTree;
import src.ui.user.UserView;
import src.utils.entity.User;
import src.utils.entity.UserGroup;
import src.utils.message.Tweet;
import src.utils.visitor.TweetPositivityVisitor;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AdminModel {
	private final UserGroup rootGroup;
	private final EntityTree tree;

	public AdminModel() {
		this.rootGroup = new UserGroup("root");
		this.tree = new EntityTree(this.rootGroup);
	}

	public UserGroup rootGroup() {
		return this.rootGroup;
	}

	public EntityTree tree() {
		return this.tree;
	}

	public ActionListener addEntityAction(JTextField inputArea) {
		return event -> {
			// Validate text by making sure there is no illegal chars
			if (!this.validateUniqueID(inputArea.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid input!", "", JOptionPane.WARNING_MESSAGE);
				inputArea.setText("Enter Entity ID");
				return;
			}

			Object object = this.tree.getSelectedObject();
			if (EntityTree.objectToNode(object) instanceof User) {
				object = EntityTree.objectToNode(object).getParent();
			}

			UserGroup rootGroup = this.rootGroup;
			if (EntityTree.objectToNode(object) == null) {
				rootGroup = this.rootGroup;
			} else {
				rootGroup = (UserGroup) object;
			}

			// Add group or user based on button used
			JButton buttonPressed = (JButton) event.getSource();
			switch (buttonPressed.getText()) {
				case "Create Group":
					UserGroup uGroup = new UserGroup(inputArea.getText().trim());
					addGroupToTree(tree, rootGroup, uGroup);
					break;
				case "Create User":
					User user = new User(inputArea.getText().trim());
					addUserToTree(tree, rootGroup, user);
					break;
				default:
					break;
			}
		};
	}

	public ActionListener openUserAction() {
		return event -> {
			Object object = this.tree.getSelectedObject();

			if (object instanceof User) {
				new UserView((User) object);
			} else {
				JOptionPane.showMessageDialog(null, "No User Selected!", "", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		};
	}

	public Set<User> getUsers(UserGroup group) {
		Set<User> allUsers = new HashSet<User>();

		for (User u : group.getUsers()) {
			allUsers.add(u);
		}

		for (UserGroup subGroup : group.getGroups()) {
			allUsers.addAll(this.getUsers(subGroup));
		}

		return allUsers;
	}

	public Set<UserGroup> getUserGroups(UserGroup group) {
		Set<UserGroup> allGroups = new HashSet<UserGroup>();
		allGroups.add(group);

		for (UserGroup subGroups : group.getGroups()) {
			allGroups.addAll(this.getUserGroups(subGroups));
		}

		return allGroups;
	}

	public ActionListener getUserCount() {
		return event -> {
			int count = this.getUsers(this.rootGroup).size();
			JOptionPane.showMessageDialog(null, "Total Users: " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	public ActionListener getGroupCount() {
		return event -> {
			int count = this.getUserGroups(this.rootGroup).size();
			JOptionPane.showMessageDialog(null, "Total Groups: " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	public ActionListener getMessageCount() {
		return event -> {
			Set<User> users = this.getUsers(this.rootGroup);

			int count = 0;
			for (User user : users) {
				count += user.getTweets().size();
			}

			JOptionPane.showMessageDialog(null, "Total Messages: " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	public ActionListener getPositivityPercent() {
		return event -> {
			Set<User> users = this.getUsers(this.rootGroup);
			Set<Tweet> tweets = new HashSet<Tweet>();
			TweetPositivityVisitor visitor = new TweetPositivityVisitor();

			double count = 0;
			for (User user : users) {
				tweets.addAll(user.getTweets());
			}

			for (Tweet tweet : tweets) {
				if (tweet.accept(visitor)) {
					count++;	
				}
			}

			System.out.println(count);
			System.out.println(tweets.size());
			count /= tweets.size();
			count *= 100;

			JOptionPane.showMessageDialog(null, "Tweet Positivity Percent: " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	private boolean validateUniqueID(String str) {
		boolean result = true;
		str = str.trim();

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				result = false;
			}
		}

		return result;
	}

	private void addUserToTree(EntityTree tree, UserGroup rootGroup, User user) {
		rootGroup.addUser(user);
		rootGroup.add(user);
		this.tree.render(rootGroup);
	}

	private void addGroupToTree(EntityTree tree, UserGroup rootGroup, UserGroup uGroup) {
		rootGroup.addGroup(uGroup);
		rootGroup.add(uGroup);
		this.tree.render(rootGroup);
	}
}
