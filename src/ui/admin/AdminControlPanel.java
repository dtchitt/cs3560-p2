package src.ui.admin;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import src.controllers.AdminController;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;

public class AdminControlPanel extends JPanel {
	public AdminControlPanel() {
		super();
		int height = 500;
		this.setMinimumSize(new DimensionUIResource(350, height));

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		layout.rowHeights = new int[] { (int) (height * 0.20), (int) (height * 0.60), (int) (height * 0.20) };
		this.setLayout(layout);
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 0;
		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.BOTH;

		this.buildTopSection(layout, constraints);
		this.buildMiddleSection(layout, constraints);
		this.buildBottomSection(layout, constraints);
	}

	private void buildTopSection(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 0;
		JPanel panel = new JPanel();
		layout.setConstraints(panel, constraints);
		this.add(panel);

		String defaultString = "Enter Entity ID";
		JTextField inputArea = new JTextField(defaultString);

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

		inputArea.setPreferredSize(new DimensionUIResource(285, layout.rowHeights[0] / 4));
		inputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

		JPanel buttons = new JPanel();
		JButton b1 = new JButton("Create Group");

		b1.addActionListener(AdminController.get().addEntityAction(inputArea));
		buttons.add(b1);

		JButton b2 = new JButton("Create User");
		b2.addActionListener(AdminController.get().addEntityAction(inputArea));
		buttons.add(b2);

		JButton b3 = new JButton("Open");
		b3.addActionListener(AdminController.get().openUserAction());
		buttons.add(b3);


		panel.add(inputArea);
		panel.add(buttons);
	}

	private void buildMiddleSection(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 1;
		JPanel panel = new JPanel();
		layout.setConstraints(panel, constraints);
		this.add(panel);
	}

	private void buildBottomSection(GridBagLayout layout, GridBagConstraints constraints) {
		constraints.gridy = 2;
		JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
		layout.setConstraints(panel, constraints);
		this.add(panel);

		JButton b1 = new JButton("Show User Total");
		b1.addActionListener((AdminController.get().getUserCount()));
		JButton b2 = new JButton("Show Group Total");
		JButton b3 = new JButton("Show Message Total");
		JButton b4 = new JButton("Show Positive Percentage");
		//JButton b5 = new JButton("Validate Entities");
		//JButton b6 = new JButton("Get Last Updated User");
		//panel.add(b6);
		//panel.add(b5);
		panel.add(b4);
		panel.add(b3);
		panel.add(b2);
		panel.add(b1);
	}
}
