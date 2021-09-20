package com.rohan.fp.browser;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.awt.event.*;

public class BrowserGUI {
	JFrame myFrame;
	JTextArea urlArea;
	JButton enterButton;
	
	public void go() {
		myFrame = new JFrame("Rohan's Browser");
		myFrame.setVisible(true);
		myFrame.setSize(400,300);
		
		Font bigFont = new Font("Times New Roman", Font.BOLD, 36);
		
		JPanel mainPanel = new JPanel();
		
		JPanel topPanel = new JPanel();
		topPanel.repaint();
		JTextArea title = new JTextArea(1,1);
		title.setFont(bigFont);
		title.setEditable(false);
		title.setText("RS Browser");
		topPanel.add(title);
		
		urlArea = new JTextArea();
		urlArea.setEditable(true);
		urlArea.setSize(100,100);
		urlArea.setText("Enter the website name here");
		mainPanel.add(urlArea,BorderLayout.EAST);
		
		enterButton = new JButton("Enter");
		enterButton.setSize(50,50);
		enterButton.addActionListener(new enterButtonListener());
		mainPanel.add(enterButton,BorderLayout.WEST);
		
		myFrame.getContentPane().add(topPanel,BorderLayout.NORTH);
		myFrame.getContentPane().add(mainPanel,BorderLayout.CENTER);
		
		
	}
	
	class enterButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String myUrl = urlArea.getText();
			try {
				URI uri = new URI(myUrl);
				java.awt.Desktop.getDesktop().browse(uri);
			} catch (URISyntaxException e1) {
				urlArea.setText("OOPS that website doesn't exist :/ ");
			} catch (IOException e1) {
				urlArea.setText("OOPS something went wrong :/ ");
			}
			
			
			
		}
	}
	

}







