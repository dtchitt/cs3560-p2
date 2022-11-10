package src;

import src.ui.admin.AdminView;

public class Driver {
	public static void main(String[] args) {
		try {
			new AdminView("Admin Panel - MySpaceTom");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
