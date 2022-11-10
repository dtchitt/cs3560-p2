package src.controllers;

import java.awt.event.ActionListener;
import src.models.AdminModel;
import javax.swing.JTextField;

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
}
