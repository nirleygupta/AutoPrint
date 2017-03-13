package com.nirley.frontEnd;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nirley.frontEnd.listeners.CustomActionListener;

public class CustomFrame extends JFrame{

	JPanel panel;
	JButton startButton;
	JComboBox<String> printerList;
	String dir;
	String[] printersArray;
	
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void baseSetup()
	{
		
		panel=new JPanel();
				
		final JFileChooser fileChooser = new JFileChooser();	
		JLabel printerLabel=new JLabel("Printer");
		JLabel dirLabel=new JLabel("Directory : ");
		JButton  dirButton=new JButton ("Select");
		final JLabel statusbar = 
                new JLabel(dir);
		printersArray=findPrinterNames();
		printerList = new JComboBox<String>(printersArray);
		startButton=new JButton("Start");
		JButton stopButton=new JButton("Stop");
		CustomActionListener actionListener=new CustomActionListener(this);
		
		
		
		printerLabel.setBounds(5,30,40,30);
		printerLabel.setVisible(true);
		
		printerList.setBounds(55,30,200,30);
		printerList.setVisible(true);
		
		dirLabel.setBounds(5,90,70,30);
		dirLabel.setVisible(true);
		
		statusbar.setBounds(75,90,250,30);
		statusbar.setVisible(true);
		
		startButton.setBounds(20, 150, 100, 50);
		startButton.setVisible(true);
		startButton.addActionListener(actionListener);
		
		stopButton.setBounds(150, 150, 100, 50);
		stopButton.setVisible(true);
		stopButton.addActionListener(actionListener);
		
		
		dirButton.setBounds(280,90,30,30);
		dirButton.setVisible(true);
		dirButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setDialogTitle("Download Directory");
				 int option=fileChooser.showOpenDialog(CustomFrame.this);
				 
				 if(option==JFileChooser.APPROVE_OPTION)
				 { statusbar.setText( fileChooser.getSelectedFile().getPath());
				 	dir=statusbar.getText();
				 } 
			}
		});
		
		panel.add(printerLabel);
		panel.add(printerList);
		panel.add(dirLabel);
		panel.add(statusbar);
		panel.add(dirButton);
		panel.setVisible(true);
		
		panel.setBounds(0, 0, 350, 150);
		getContentPane().add(panel);
		
		add(startButton);
		add(stopButton);
		
		
		
		setTitle("Auto Print");
		setSize(400, 400);
		setLayout(null);
		setVisible(true);
		
	}
	
	private static String[] findPrinterNames()
	{
		PrintService[] printServices=PrintServiceLookup.lookupPrintServices(null, null);
		String[] names=new String[printServices.length];
		int i=0;
		
		for(PrintService printSer:printServices)
			names[i++]= 
			printSer.getName();
		
		
		return names;
	}
	
	
	public void panelChange(boolean enable)
	{
		
		panel.setEnabled(enable);
		
		Component[] components=panel.getComponents();
		
		for(Component comp:components)
			comp.setEnabled(enable);
		
		
		startButton.setEnabled(enable);
	}

	public JComboBox<String> getPrinterList() {
		return printerList;
	}

	public void setPrinterList(JComboBox<String> printerList) {
		this.printerList = printerList;
	}

	
	public void setPrinterArray(String print)
	{
		
		for(int i=0;i<printersArray.length;i++)
		{
			if(printersArray[i].equalsIgnoreCase(print))
				printerList.setSelectedIndex(i);
		}
	}
	
}
