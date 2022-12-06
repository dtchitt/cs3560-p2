package src.ui.user;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Timer;
import java.util.TimerTask;

import src.controllers.AdminController;
import src.utils.composite.User;

public class UserControlPanel extends JPanel {
	private User user;
	private JTextArea following;
	private JList<String> feedArea;
	private JLabel timeStampLabel;

	public UserControlPanel(User user) {
		super();
		this.user = user;

		int height = 420;
		this.setMinimumSize(new DimensionUIResource(420, height));

		GridBagLayout layout = new GridBagLayout();
		layout.rowHeights = new int[] {
				(int) (height * 0.07),
				(int) (height * 0.40),
				(int) (height * 0.13),
				(int) (height * 0.40),
				(int) (height * 0.07),
		};

		this.setLayout(layout);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;

		this.buildFollowUserSection(layout, constraints);
		this.buildFollowingView(layout, constraints);
		this.buildTweetControls(layout, constraints);
		this.buildTimeStampLabel(layout, constraints, user);
		this.buildNewsFeed(layout, constraints);
	}

	private void buildFollowUserSection(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 0;
		constraints.gridx = 0;
		JPanel panel = new JPanel();
		layout.setConstraints(panel, constraints);
		this.add(panel);

		String defaultString = "Enter Entity ID";
		JTextArea inputArea = new JTextArea(defaultString);
		inputArea.setPreferredSize(new DimensionUIResource(285, layout.rowHeights[0] / 4));
		inputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		inputArea.setRows(1);

		inputArea.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (inputArea.getText().equalsIgnoreCase(defaultString)) {
					inputArea.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (inputArea.getText().isEmpty()) {
					inputArea.setText(defaultString);
				}
			}
		});

		JButton button = new JButton("Follow User");

		button.addActionListener(event -> {
			try {
				User targetUser = AdminController.get().model().tree().getUser(inputArea.getText());

				if (canFollowUser(targetUser)) {
					this.user.followUser(targetUser);
					this.following.append(targetUser.getIdName() + "\n");
					this.following.repaint();
				}

			} catch (Exception e) {
				e.printStackTrace();
				this.warningMessage("Invalid Entity");
			}
		});

		panel.add(inputArea);
		panel.add(button);
	}

	private boolean canFollowUser(User targetUser) {
		boolean result = true;

		if (this.user.isFollowing(targetUser)) {
			this.warningMessage("You are already following that user!");
			result = false;
		}

		if (this.user.equals(targetUser)) {
			this.warningMessage("You cannot follow yourself!");
			result = false;
		}

		return result;
	}

	private void warningMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "", JOptionPane.WARNING_MESSAGE);
	}

	private void buildFollowingView(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 1;
		constraints.gridx = 0;

		this.following = new JTextArea("Following:\n");

		for (User user : this.user.getFollowing()) {
			this.following.append(user.getIdName() + "\n");
		}

		layout.setConstraints(following, constraints);
		following.setBackground(Color.WHITE);
		following.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

		this.add(following);
	}

	private void buildTweetControls(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 2;
		constraints.gridx = 0;
		JPanel panel = new JPanel();
		layout.setConstraints(panel, constraints);
		this.add(panel);

		String defaultString = "Enter Your Tweet...";
		JTextArea inputArea = new JTextArea(defaultString);
		inputArea.setPreferredSize(new DimensionUIResource(285, 50));
		inputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		inputArea.setRows(1);

		inputArea.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (inputArea.getText().equalsIgnoreCase(defaultString)) {
					inputArea.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (inputArea.getText().isEmpty()) {
					inputArea.setText(defaultString);
				}
			}
		});

		JButton button = new JButton("Post Tweet");

		button.addActionListener(event -> {
			try {
				this.user.addTweet(inputArea.getText());

				inputArea.setText(defaultString);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		});

		panel.add(inputArea);
		panel.add(button);
	}

	private void buildNewsFeed(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 3;
		constraints.gridx = 0;

		this.feedArea = new JList<String>(this.user.getFeed());

		layout.setConstraints(this.feedArea, constraints);
		this.feedArea.setBackground(Color.WHITE);
		this.feedArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

		this.add(this.feedArea);
	
	}

	private void buildTimeStampLabel(GridBagLayout layout, GridBagConstraints constraints, User user) {
		constraints.gridy = 4;
		constraints.gridx = 0;

		this.timeStampLabel = new JLabel("");
		//this.timeStampLabel.setText(this.setTimeUpdateText());

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				timeStampLabel.setText("Created @ " + user.getCreationDate() + "    Updated @ " + user.getUpdateDate());
				System.out.println("ya");
			}
			
		}, 0, 100);

		layout.setConstraints(this.timeStampLabel, constraints);
		this.timeStampLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(this.timeStampLabel);
	}
}
