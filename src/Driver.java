package src;

import src.ui.admin.AdminView;

/**
 * Daniel Chittenden
 * CS3560.03 Homework #2
 * Minitwitter
 */
public class Driver {
	public static void main(String[] args) {
		try {
			new AdminView("Admin Panel - MySpaceTom");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
