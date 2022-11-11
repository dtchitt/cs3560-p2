package src.ui.admin;

import javax.swing.JScrollPane;
import javax.swing.plaf.DimensionUIResource;
import src.controllers.AdminController;

/**
 * This is the class that holds the Jtree and makes it scrollable
 */
public class ScrollPane extends JScrollPane {
	public ScrollPane() {
		super();
		//Set up pane
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setMinimumSize(new DimensionUIResource(140, 200));
		//build JTree
		this.add(AdminController.get().model().tree());
		this.setViewportView(AdminController.get().model().tree());
	}
}
