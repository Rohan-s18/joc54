package com.rohan.myProject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardPlayer {
	private JFrame myFrame;
	private JButton nextButton;
	private JTextArea display;
	private JTextArea answer;
	private QuizCard currentCard;
	private ArrayList<QuizCard> cardList = new ArrayList<QuizCard>();
	private int currentCardIndex;
	private boolean isShowAnswer;
	
	public void go() {
		//Build GUI
		
		myFrame = new JFrame("Quiz Card Player");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif",Font.BOLD, 24);
		
		display = new JTextArea(10,20);
		display.setFont(bigFont);
		display.setLineWrap(true);
		display.setEditable(false);
		
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		nextButton = new JButton("Show Question");
		nextButton.addActionListener(new nextCardListener());
	
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
		loadMenuItem.addActionListener(new openMenuListener());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		
		myFrame.setJMenuBar(menuBar);
		myFrame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		myFrame.setSize(640,500);
		myFrame.setVisible(true);
	}
	
	class nextCardListener implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			if(isShowAnswer) {
				display.setText(currentCard.getAnswer());
				nextButton.setText("Next Card");
				isShowAnswer = false;
			} else {
				if(currentCardIndex < cardList.size()) {
					showNextCard();
				} else {
					display.setText("There are no more cards :(");
					nextButton.setEnabled(false);
				}
			}
		}
	}
	
	class openMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent b) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(myFrame);
			loadFile(fileOpen.getSelectedFile());
		}
	}
	
	public void loadFile(File file) {
		cardList = new ArrayList<QuizCard>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null) {
				makeCard(line);
			}
			reader.close();
			} catch(Exception ex) {
				System.out.println("Couldn't read file :(");
				ex.printStackTrace();
		}
		showNextCard();
	}
	
	public void makeCard(String lineToParse) {
		String[] result = lineToParse.split("/");
		QuizCard card = new QuizCard(result[0],result[1]);
		cardList.add(card);
		System.out.println("Made a card");
	}
	
	public void showNextCard() {
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		display.setText(currentCard.getQuestion());
		nextButton.setText("Show Answer");
		isShowAnswer = true;
	}
}
