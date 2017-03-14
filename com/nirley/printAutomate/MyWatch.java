package com.nirley.printAutomate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class MyWatch{
	
	PrintPDF printpdf;
	String directory; 
	int number;
	WatchService watcher;
	
	public void fileMonitor()
	{
		Path dir=Paths.get(directory);
		 watcher=null;
		
		try {
			watcher = dir.getFileSystem().newWatchService();
			dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		if(watcher==null)
			return;
		
		boolean valid=true;
		
		while(valid)
		{
			WatchKey key=null;
			
			//
			try {
				key=watcher.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			if(key==null)
				return;
			
				for(WatchEvent<?> event:key.pollEvents())
				{
						WatchEvent.Kind<?> kind = event.kind();
					
						if (kind == StandardWatchEventKinds.OVERFLOW) continue;

						WatchEvent<Path> ev = (WatchEvent<Path>)event;
						Path filename = ev.context();
						 
						 try {
							 Path child = dir.resolve(filename);
							 if (!filename.toString().endsWith("pdf")) 
								 {
									 System.err.format("New file '%s'" +
							                    " is not a pdf file.%n", filename);
							                continue;	 
								 }
						 } catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							continue;
						 	}
					
						 System.out.format("Printing file %s%n", filename);
						 
						 try {
							printpdf.printPDF(directory + "\\" + filename.toString(),number);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 

				}
				
				valid = key.reset();
		}
		
		
		
	}
	
	
	public MyWatch(String printerName,String directory,int number) throws Exception {
		printpdf=new PrintPDF(printerName);
		this.directory=directory;
		this.number=number;
	}
	
	
	public void closeWatch()
	{
		try {
			watcher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
