package src.ui.admin;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.plaf.DimensionUIResource;

import src.controllers.AdminController;

/**
 * The main window of the program, which is the admin control panel.
 * This class is composed of sub-frames. The sub frames allow the admin to
 * control the program.
 */
public class AdminView extends JFrame {

	public AdminView(String string) {
		super(string);
		// Admin Panel closes & closes all other windows on exit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center Admin Panel on screen when starting
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new DimensionUIResource(600, 500));
		// Set up window icon
		URL iconURL = this.getClass().getResource("/media/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		// Build main ui components
		this.add(this.buildSplitPane());
		// Pack components then show window
		this.pack();
		this.setVisible(true);
	}

	/**
	 * handles building the split pane portion of the window
	 * @return
	 */
	private JSplitPane buildSplitPane() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(140);

		JScrollPane scrollPane = buildScrollPane();
		splitPane.setLeftComponent(scrollPane);
		AdminControlPanel controlPane = new AdminControlPanel();
		splitPane.setRightComponent(controlPane);

		return splitPane;
	}

	private JScrollPane buildScrollPane() {
		JScrollPane pane = new JScrollPane();

		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.setMinimumSize(new DimensionUIResource(140, 200));
		pane.add(AdminController.get().model().tree());
		pane.setViewportView(AdminController.get().model().tree());

		return pane;
	}
}
