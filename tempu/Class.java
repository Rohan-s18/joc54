import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

public class Class {
	JFrame myFrame;
	JTextPane myText;
	
	public static void main(String[] args) {
		new Class().go();
	}
	
	public void go() {
		myFrame = new JFrame();
		myFrame.setSize(200,200);
		myFrame.setVisible(true);
		myFrame.setLayout(new GridLayout(2,1));
		
		myText = new JTextPane();
		StyledDocument doc = myText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		myText.setFont(new Font("BOLD",Font.BOLD,20));
		myText.setText("I wanna do");
		myFrame.getContentPane().add(myText);
		
		JPanel buttonPanel = new JPanel();
		JButton button1 = new JButton("Button 1");
		button1.addActionListener(new button1Listener());
		buttonPanel.add(button1);
		JButton button2 = new JButton("Button 2");
		button2.addActionListener(new button2Listener());
		buttonPanel.add(button2);
		myFrame.getContentPane().add(buttonPanel);
		
	}
	
	class button1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			myText.setText("Susu");
		}
	}
	
	class button2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			myText.setText("Potty");
		}
	}
	
}
