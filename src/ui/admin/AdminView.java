package src.ui.admin;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.plaf.DimensionUIResource;

public class AdminView extends JFrame {

	public AdminView(String string) {
		super(string);
		//Admin Panel closes & closes all other windows on exit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Center Admin Panel on screen when starting
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new DimensionUIResource(600, 500));
		//Set up window icon
		URL iconURL = this.getClass().getResource("/media/myspaceIcon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		//Build main ui components
		this.add(this.buildSplitPane());
		//Pack components then show window
		this.pack();
		this.setVisible(true);
	}

	private JSplitPane buildSplitPane() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(140);

		ScrollPane scrollPane = new ScrollPane();
		splitPane.setLeftComponent(scrollPane);
		AdminControlPanel controlPane = new AdminControlPanel();
		splitPane.setRightComponent(controlPane);

		return splitPane;
	}
}
