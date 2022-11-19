package src.controllers;

import java.awt.event.ActionListener;
import src.models.AdminModel;
import javax.swing.JTextField;

/**
 * This is a middleman class to help clean up my code
 * AdminController is a singleton so it can be accessed globally.
 * AdminController also holds the admin model which has the tree and rootgroup stored
 * It can be used to  access the mmost important data from anywhere
 */
public class AdminController {
	private static AdminController instance;
	private final AdminModel model;

	private AdminController() {
		this.model = new AdminModel();
	}

	public static AdminController get() {
		if (instance == null) {
			synchronized (AdminController.class) {
				if (AdminController.instance == null) {
					instance = new AdminController();
				}
			}
		}
		return instance;
	}

	public AdminModel model() {
		return this.model;
	}

	public ActionListener addEntityAction(JTextField inputArea) {
		return this.model.addEntityAction(inputArea);
	}

	public ActionListener openUserAction() {
		return this.model.openUserAction();
	}

	public ActionListener getUserCount() {
		return this.model.getUserCount();
	}

	public ActionListener getGroupCount() {
		return this.model.getGroupCount();
	}

	public ActionListener getMessageCount() {
		return this.model.getMessageCount();
	}

	public ActionListener getPositivityPercent() {
		return this.model.getPositivityPercent();
	}

	public ActionListener validateEntities() {
		return this.model.validateEntities();
	}

	public ActionListener getLastUpdatedUser() {
		return this.model.getLastUpdatedUser();
	}
}
