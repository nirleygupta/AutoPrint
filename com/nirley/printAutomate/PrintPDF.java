package com.nirley.printAutomate;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class PrintPDF {

	PrintService defaultPrintService;
	
	public PrintPDF(String printerName) throws Exception {
		
		defaultPrintService=findPrinter(printerName);
		
		if(defaultPrintService==null)
			throw new Exception("Printer Not Found");
		
	}
	
	
	
	private  PrintService findPrinter(String printName)
	{
		PrintService[] printServices=PrintServiceLookup.lookupPrintServices(null, null);
		
		for(PrintService printSer:printServices)
		{
			String name=printSer.getName();
			if(name.trim().equals(printName))
				return printSer;
		}
		
		
		return null;
	
	}
	
	

	public boolean printPDF(String filePath) throws Exception
	{
		PDDocument doc=null;
		boolean status = false;
		
		try
		{
			
			doc=PDDocument.load(new File(filePath));
			PrinterJob job=PrinterJob.getPrinterJob();
			job.setPageable(new PDFPageable(doc));
			job.setPrintService(defaultPrintService);
			job.print();
			status=true;
		}
		catch(IOException e)
		{
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("Printing Failed " + e );
		}
		finally
		{
			if(doc!=null)
				doc.close();
		}
		
		return status;
		
	}
	
	
}
