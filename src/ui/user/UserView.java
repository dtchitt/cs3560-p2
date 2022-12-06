package src.ui.user;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

import src.utils.composite.User;

/**
 * This creates the user view window when the open view button is clicked
 */
public class UserView extends JFrame {

	public UserView(User user) {
		super(user.getIdName());
		//User view will only close the user view window on exit
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new DimensionUIResource(420, 400));
		this.setResizable(false);

		UserControlPanel splitPane = new UserControlPanel(user);
		this.add(splitPane);

		URL iconURL = getClass().getResource("/media/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		
		this.pack();
		this.setVisible(true);
	}
	
}
