package src.models;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.controllers.EntityTree;
import src.ui.user.UserView;
import src.utils.entity.User;
import src.utils.entity.UserGroup;

import java.awt.event.ActionListener;

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

	public ActionListener getUserCount() {
		return event -> {

		};
	}

	public ActionListener getGroupCount() {
		return null;
	}

	public ActionListener getMessageCount() {
		return null;
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
