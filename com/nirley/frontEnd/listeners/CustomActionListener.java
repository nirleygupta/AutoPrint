package com.nirley.frontEnd.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nirley.frontEnd.CustomFrame;
import com.nirley.printAutomate.MyWatch;

public class CustomActionListener implements ActionListener {
	
	CustomFrame frame;
	MyWatch myWatchInst;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Start"))
		{
			frame.panelChange(false);
			try {
				myWatchInst=new MyWatch(frame.getPrinterList().getSelectedItem().toString(), frame.getDir());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
			
			myWatchInst.fileMonitor();
		}	
			
		else if(e.getActionCommand().equals("Stop"))
		{
			frame.panelChange(true);
			myWatchInst.closeWatch();
		}

	}
	
	public CustomActionListener(CustomFrame frame)
	{
		this.frame=frame;
		//myWatchInst=new MyWatch(printerName, directory)
	}
	

}
