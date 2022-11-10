package src.controllers;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import src.utils.entity.User;
import src.utils.entity.UserGroup;

import java.awt.Component;

public class EntityTree extends JTree {
	public EntityTree(UserGroup rootGroup) {
		super();

		this.setModel(new DefaultTreeModel(rootGroup));
		// Add Tom, good ol myspace
		rootGroup.addUser(new User("Tom"));
		this.getDefaultModel().nodesWereInserted(rootGroup, new int[] { rootGroup.getChildCount() - 1 });
		this.expandPath(new TreePath(rootGroup.getPath()));

		this.setCellRenderer(new DefaultTreeCellRenderer() {
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean isLeaf, int row, boolean hasFocus) {
				Component result = super.getTreeCellRendererComponent(tree, value, isSelected, expanded, isLeaf, row, hasFocus);

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

				if (node instanceof UserGroup) {
					this.setIcon(UIManager.getIcon("FileView.directoryIcon"));
				}

				if (node instanceof User) {
					this.setIcon(UIManager.getIcon("FileView.fileIcon"));
				}

				return result;
			}
		});
	}

	public static DefaultMutableTreeNode objectToNode(Object object) {
		return (DefaultMutableTreeNode) object;
	}

	public static DefaultMutableTreeNode treePathToNode(TreePath path) {
		return (DefaultMutableTreeNode) path.getLastPathComponent();
	}

	private DefaultTreeModel getDefaultModel() {
		return ((DefaultTreeModel) this.getModel());
	}

	public void render(UserGroup rootNode) {
		this.getDefaultModel().nodesWereInserted(rootNode, new int[] { rootNode.getChildCount() - 1 });
	}

	public Object getSelectedObject() {
		return this.getLastSelectedPathComponent();
	}

	public User getUser(String id) {
		TreePath path = this.getNextMatch(id, 0, Position.Bias.Forward);
		return (User) EntityTree.treePathToNode(path);
	}
}
