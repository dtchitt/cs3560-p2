package src.models;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.TreePath;

import src.controllers.EntityTree;
import src.ui.user.UserView;
import src.utils.composite.User;
import src.utils.composite.UserGroup;
import src.utils.visitor.MessageCountVisitor;
import src.utils.visitor.TweetPositivityVisitor;

import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * AdminModel handles the logic of the admin view
 * It is also where the rootgroup and tree is stored
 */
public class AdminModel {
	private final UserGroup rootGroup;
	private final EntityTree tree;
	private final Set<UserGroup> groups;
	private final Set<User> users;

	public AdminModel() {
		this.rootGroup = new UserGroup("root");
		this.tree = new EntityTree(this.rootGroup);
		this.groups = new HashSet<UserGroup>();
		this.users = new HashSet<User>();

		this.groups.add(rootGroup);
		addUserToTree(tree, rootGroup, new User("Tom"));
		this.tree.render(rootGroup);
		this.tree.expandPath(new TreePath(rootGroup.getPath()));
	}

	public UserGroup rootGroup() {
		return this.rootGroup;
	}

	public EntityTree tree() {
		return this.tree;
	}

	/**
	 * Logic for adding an entity to the system.
	 * 
	 * @param inputArea
	 * @return
	 */
	public ActionListener addEntityAction(JTextField inputArea) {
		return event -> {
			// Validate text by making sure there is no illegal chars
			if (!this.validateInput(inputArea.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid input!", "", JOptionPane.WARNING_MESSAGE);
				inputArea.setText("Enter Entity ID");
				return;
			}

			// get the node or its parent
			UserGroup rootGroup = this.rootGroup;
			Object object = getSelectedNode();

			if (EntityTree.nodeFromObject(object) == null) {
				rootGroup = this.rootGroup;
			} else {
				rootGroup = (UserGroup) object;
			}

			// Add group or user based on button used
			// I could remove this switch with OOP, but seems overkill for this project
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

			inputArea.setText("Enter Entity ID");
		};
	}

	private Object getSelectedNode() {
		Object object = this.tree.getLastSelectedPathComponent();

		if (object == null) {
			return null;
		}

		if (!EntityTree.nodeFromObject(object).getAllowsChildren()) {
			object = EntityTree.nodeFromObject(object).getParent();
		}

		return object;
	}

	/**
	 * Logic for opening a users view
	 * 
	 * @return
	 */
	public ActionListener openUserAction() {
		return event -> {
			Object object = this.tree.getLastSelectedPathComponent();

			if (object instanceof User) {
				new UserView((User) object);
			} else {
				JOptionPane.showMessageDialog(null, "No User Selected!", "", JOptionPane.WARNING_MESSAGE);
				return;
			}
		};
	}

	/**
	 * Logic to get user count
	 * 
	 * @return
	 */
	public ActionListener getUserCount() {
		return event -> {
			int count = this.users.size();

			JOptionPane.showMessageDialog(null, "Total Users: " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	/**
	 * Logic to get group count
	 * 
	 * @return
	 */
	public ActionListener getGroupCount() {
		return event -> {
			int count = this.groups.size();

			JOptionPane.showMessageDialog(null, "Total Groups: " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	/**
	 * logic to get message count
	 * 
	 * @return
	 */
	public ActionListener getMessageCount() {
		return event -> {
			MessageCountVisitor visitor = new MessageCountVisitor();

			int count = 0;

			for (User user : this.users) {
				count += user.accept(visitor);
			}

			JOptionPane.showMessageDialog(null, "Total Messages: " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	/**
	 * logic to check tweets for positivity
	 * 
	 * @return
	 */
	public ActionListener getPositivityPercent() {
		return event -> {
			TweetPositivityVisitor visitor = new TweetPositivityVisitor();

			double count = 0;
			int totalTweets = 0;

			for (User user : this.users) {
				totalTweets += user.getTweetCount();
				count += user.accept(visitor);
			}

			count /= totalTweets;
			count *= 100;

			JOptionPane.showMessageDialog(null, "Positive Tweet % " + count, "", JOptionPane.INFORMATION_MESSAGE);
		};
	}

	/**
	 * This will validate all entities
	 * Entities are valid under 2 conditions
	 * #1 They have no spaces in their unique ID (this is already taken care of upon User creation)
	 * #2 There is not copies of any entities. We know there is no copies of entities because all users/groups that are created are also stored in a Set
	 * Just to be safe, I count the total nodes in tree and compare that to the set of user and user of usergroups sizes added together
	 * @return
	 */
	public ActionListener validateEntities() {
		return event -> {
			boolean isValid = true;

			for (UserGroup userGroup : groups) {
				if (this.hasSpaces(userGroup.getUniqueID())) {
					isValid = false;
				}

				for (User user : userGroup.getUsers()) {
					if (this.hasSpaces(user.getUniqueID())) {
						isValid = false;
					}
				}
			}

			int nodeCount = 0;
			Enumeration e = this.rootGroup.breadthFirstEnumeration();
			while (e.hasMoreElements()) {
				nodeCount++;
				e.nextElement();
			}

			if (nodeCount != (this.groups.size() + this.users.size())) {
				isValid = false;
			}

			JOptionPane.showMessageDialog(null, "Are Entities Valid: " + isValid, "", JOptionPane.INFORMATION_MESSAGE);
		};
    }

    public ActionListener getLastUpdatedUser() {
		return event -> {
			User lastUpdated = null;

			for (User user : this.users) {
				if (lastUpdated == null) {
					lastUpdated = user;
				}

				long currTime = System.currentTimeMillis();
				if (user.getLastUpdate() - currTime > lastUpdated.getLastUpdate() - currTime) {
					lastUpdated = user;
				}
			}

			JOptionPane.showMessageDialog(null, "Last updated User: " + lastUpdated.getUniqueID(), "", JOptionPane.INFORMATION_MESSAGE);
		};
    }

	private boolean hasSpaces(String str) {
		return str.contains(" ");
	}

	/**
	 * validates entity entrys are correct, they can only contain numbers and
	 * letters
	 * 
	 * @param str
	 * @return
	 */
	private boolean validateInput(String str) {
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
		this.users.add(user);
		rootGroup.add(user);
		this.tree.render(rootGroup);
	}

	private void addGroupToTree(EntityTree tree, UserGroup rootGroup, UserGroup uGroup) {
		this.groups.add(uGroup);
		rootGroup.add(uGroup);
		this.tree.render(rootGroup);
	}
}
