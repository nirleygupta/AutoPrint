package com.nirley.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.nirley.frontEnd.CustomFrame;

public class LetsGo {
	
	
	public static void main(String[] args)
	{
		
		CustomFrame customPanel=new CustomFrame();
		
		LetsGo go=new LetsGo();
		HashMap<String,String> map=go.loadProp();
		customPanel.setDir(map.get("directory"));
		customPanel.setNumber(Integer.parseInt(map.get("number")));
		customPanel.baseSetup();	
		customPanel.setPrinterArray(map.get("printer"));
		
		customPanel.addWindowListener
		(	new WindowAdapter() 
			{
			
		
			public void windowClosing(WindowEvent e) {
			        System.exit(0);
			    }
			}
		);
		
		
	}
	
	
	private HashMap<String, String> loadProp()
	{
		Properties prop = new Properties();
		HashMap<String,String> propMap=new HashMap<String,String>();
		InputStream in=getClass().getClassLoader().getResourceAsStream("com/nirley/resources/DefaultProp.properties");
		try {
			prop.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
			propMap.put(key,value);
		}
		
		
		return propMap;
		
	}
}
