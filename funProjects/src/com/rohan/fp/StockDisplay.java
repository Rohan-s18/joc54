package com.rohan.fp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class StockDisplay {
	private JFrame myFrame;
	private JButton addButton;
	private ArrayList<Stock> stockList;
	private JTextArea stockName;
	private JTextArea stockCode;
	private JTextArea stockPrice;
	
	public void go() {
		myFrame = new JFrame("Stonks");
		myFrame.setSize(600,600);
		myFrame.setVisible(true);
		
		stockList = new ArrayList<Stock>();
		stockList.add(new Stock("Apple","AAPL",150));
		stockList.add(new Stock("Amazon","AMZN",1250));
		
		//JPanel displayPanel = new JPanel();
		
		String[] col = {"Company","Code","Quote"};
		JTable stockTable = new JTable(makeTableArray(stockList),col);
		
		myFrame.getContentPane().add(new JScrollPane(stockTable),BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		
		stockName = new JTextArea();
		stockName.setText("Enter Stock Name Here");
		stockName.setEditable(true);
		bottomPanel.add(stockName);
		
		stockCode = new JTextArea();
		stockCode.setText("Enter Stock Code Here");
		stockCode.setEditable(true);
		bottomPanel.add(stockCode);
		
		stockPrice = new JTextArea();
		stockPrice.setText("Enter Stock Price Here");
		stockPrice.setEditable(true);
		bottomPanel.add(stockPrice);
		
		addButton = new JButton("Add New Stock");
		addButton.addActionListener(new AddButtonListener());
		bottomPanel.add(addButton);
		
		myFrame.getContentPane().add(bottomPanel,BorderLayout.SOUTH);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object[][] makeTableArray(ArrayList<Stock> l){
		ArrayList temp = new ArrayList();
		Object[] temp1 = new Object[3];
		int max = l.size();
		for(int i = 0; i < max; i++) {
			temp1[0] = l.get(i).getName();
			temp1[1] = l.get(i).getCode();
			temp1[2] = l.get(i).getQuote();
			temp.add(temp1);
		}
		Object[][] tableArray = (Object[][])temp.toArray(new Object[0][0]);	
		return tableArray;
	}
	
	class AddButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
}




