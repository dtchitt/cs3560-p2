package src.ui.user;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.plaf.DimensionUIResource;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.DefaultListModel;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import src.controllers.AdminController;
import src.utils.composite.User;
import src.utils.message.Tweet;

/**
 * Okay honestly I was rushing and this is really messy, sorry
 * This is pretty much an all in one for user UI and logic.
 * The control panel is what actually observes users feeds so it knows when to
 * update
 */
public class UserControlPanel extends JPanel {
	private User user;
	private JTextArea following;
	private JTextArea feedArea;

	public UserControlPanel(User user) {
		super();
		this.user = user;

		int height = 400;
		this.setMinimumSize(new DimensionUIResource(420, height));

		GridBagLayout layout = new GridBagLayout();
		layout.rowHeights = new int[] {
				(int) (height * 0.07),
				(int) (height * 0.40),
				(int) (height * 0.13),
				(int) (height * 0.40)
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

		// button.addActionListener(event -> {
		// try {
		// Tweet tweet = new Tweet(inputArea.getText(), this.user);
		// this.user.tweet(tweet);
		// inputArea.setText(defaultString);
		// this.update();
		// } catch (Exception e) {
		// e.printStackTrace();
		// return;
		// }
		// });

		panel.add(inputArea);
		panel.add(button);
	}

	private void buildNewsFeed(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 3;
		constraints.gridx = 0;

		this.feedArea = new JTextArea();
		this.updateFeed();

		layout.setConstraints(this.feedArea, constraints);
		this.feedArea.setBackground(Color.WHITE);
		this.feedArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		;
		this.add(this.feedArea);
	}

	private void updateFeed() {
		String feed = "Feed:\n";

		List<Tweet> tweets = this.user.getTweets();

		// for (User u : this.user.getFollowing()) {
		// for (Tweet uTweet : u.getTweets()) {
		// if (this.user.getFollowing().contains(uTweet.getUser())) {
		// tweets.add(uTweet);
		// }

		// }
		// }

		Set<Tweet> uniqueTweets = new LinkedHashSet<Tweet>();
		uniqueTweets.addAll(tweets);
		tweets.clear();
		tweets.addAll(uniqueTweets);

		Collections.reverse(tweets);
		for (Tweet tweet : this.user.getTweets()) {
			feed += tweet.format() + "\n";
		}

		this.feedArea.setText(feed);
		// this.feedArea.repaint();
		this.feedArea.update(this.feedArea.getGraphics());
	}
}
