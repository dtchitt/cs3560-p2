package src.ui.user;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

import src.utils.entity.User;

public class UserView extends JFrame {

	public UserView(User user) {
		super(user.getIdName());
		//User view will only close the user view window on exit
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Center Admin Panel on screen when starting
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new DimensionUIResource(420, 400));
		this.setResizable(false);
		//Create admin splitPane
		UserControlPanel splitPane = new UserControlPanel(user);
		this.add(splitPane);

		URL iconURL = getClass().getResource("/media/myspaceIcon.png");
		// iconURL is null when not found
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		
		this.pack();
		this.setVisible(true);
	}
	
}
