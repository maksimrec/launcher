package net.minecraft;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegPanel extends JDialog {
	private static final long serialVersionUID = 1L;
	JTextField userName = new JTextField(20);
	JPasswordField password = new JPasswordField(20);
	JPasswordField password2 = new JPasswordField(20);

	JLabel errorLabel = new JLabel("", 0);
	
	

	public RegPanel(final Frame parent) {
		super(parent);

		
		
		
		setModal(true);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doReg(userName.getText(), new String(password.getPassword()),
						new String(password2.getPassword()));
			}
		};

		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Регистрация", 0);
		label.setBorder(new EmptyBorder(0, 0, 16, 0));
		label.setFont(new Font("Default", 1, 16));
		panel.add(label, "North");

		errorLabel.setForeground(new Color(16728128));
		errorLabel.setBorder(new EmptyBorder(8, 0, 0, 0));
		errorLabel.setFont(new Font("Default", 0, 10));

		JPanel optionsPanel = new JPanel(new BorderLayout());
		JPanel labelPanel = new JPanel(new GridLayout(0, 1));
		JPanel fieldPanel = new JPanel(new GridLayout(0, 1));
		JPanel errorPanel = new JPanel(new GridLayout(0, 1));
		optionsPanel.add(labelPanel, "West");
		optionsPanel.add(fieldPanel, "Center");
		optionsPanel.add(errorPanel, "South");

		errorPanel.add(errorLabel);

		final JButton doneButton = new JButton("Отмена");
		final JButton OkButton = new JButton("Зарегистрировать");

		OkButton.addActionListener(al);
		userName.addActionListener(al);
		password.addActionListener(al);
		password2.addActionListener(al);

		labelPanel.add(new JLabel("Логин: ", 2));
		fieldPanel.add(userName);

		labelPanel.add(new JLabel("Пароль: ", 2));
		fieldPanel.add(password);
		labelPanel.add(new JLabel("Пароль еще раз: ", 4));
		fieldPanel.add(password2);

		panel.add(optionsPanel, "Center");

		JPanel buttonsPanel = new JPanel(new BorderLayout());
		buttonsPanel.add(new JPanel(), "Center");

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
			}
		});

		buttonsPanel.add(doneButton, "East");
		buttonsPanel.add(OkButton, "Center");
		buttonsPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

		panel.add(buttonsPanel, "South");

		add(panel);

		panel.setBorder(new EmptyBorder(24, 24, 24, 24));
		pack();
		setLocationRelativeTo(parent);

	}

	public void doReg(String login, String pass, String pass2) {

		try {
			String parameters = "login=" + URLEncoder.encode(login, "UTF-8")
					+ "&pass=" + URLEncoder.encode(pass, "UTF-8") + "&pass2="
					+ URLEncoder.encode(pass2, "UTF-8");

			String result = Util.excutePost(
					"http://srv1.dcserver.ru:8050/auth/reg0", parameters);

			if (result == null) {
				
				errormessage(result);
				
			} else {
				errormessage(result);

			}
		} catch (Exception e) {
			// TODO: handle exception

		}

	}

	public void errormessage(String result) throws UnsupportedEncodingException {

		String newString = new String(result.getBytes("Cp1251"), "UTF-8");
		errorLabel.setText(newString);
		// errorLabel.setText(result);

	}

}
