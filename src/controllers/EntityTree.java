package src.controllers;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import src.utils.composite.User;
import src.utils.composite.UserGroup;

import java.awt.Component;

/**
 * The jtree that will contain the user and group nodes in the system.
 */
public class EntityTree extends JTree {
	public EntityTree(UserGroup rootGroup) {
		super();

		this.setModel(new DefaultTreeModel(rootGroup));
		// Add Tom, good ol myspace
		rootGroup.add(new User("Tom"));
		this.render(rootGroup);
		this.expandPath(new TreePath(rootGroup.getPath()));

		this.setRenderer();
	}

	public static DefaultMutableTreeNode nodeFromObject(Object object) {
		return (DefaultMutableTreeNode) object;
	}

	public static DefaultMutableTreeNode nodeFromPath(TreePath path) {
		return (DefaultMutableTreeNode) path.getLastPathComponent();
	}

	// private DefaultTreeModel getDefaultModel() {
	// 	return ((DefaultTreeModel) this.getModel());
	// }

	public void render(UserGroup rootNode) {
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		treeModel.nodesWereInserted(rootNode, new int[] { rootNode.getChildCount() - 1 });
	}

	public User getUser(String id) {
		TreePath path = this.getNextMatch(id, 0, Position.Bias.Forward);

		if (path != null) {
			DefaultMutableTreeNode node = EntityTree.nodeFromPath(path);

			if (!node.getAllowsChildren()) {
				return (User) node;
			}
		}

		return null;
	}

	/**
	 * Set up the renderer that is used to draw node icons
	 */
	private void setRenderer() {
		this.setCellRenderer(new DefaultTreeCellRenderer() {
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean isLeaf, int row, boolean hasFocus) {
				Component result = super.getTreeCellRendererComponent(tree, value, isSelected, expanded, isLeaf, row, hasFocus);

				DefaultMutableTreeNode node = EntityTree.nodeFromObject(value);

				if (node.getAllowsChildren()) {
					this.setIcon(UIManager.getIcon("FileView.directoryIcon"));
				}

				if (!node.getAllowsChildren()) {
					this.setIcon(UIManager.getIcon("FileView.fileIcon"));
				}

				return result;
			}
		});
	}
}
