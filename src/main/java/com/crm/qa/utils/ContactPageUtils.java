package com.crm.qa.utils;

import java.io.IOException;
import java.util.ArrayList;



public class ContactPageUtils {
	
	static String sheetname="Contacts";
	
	public static ArrayList<Object[]> getDatafromExcel() throws IOException
	{
		Excel excel = null;
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		//Reading excel file location and sheetname
		try {
			 excel = new Excel(System.getProperty("user.dir")+
					"src//main//java//com//crm//qa//testdata//CRM.xlsx", 
					 sheetname);
		} catch (IOException e) {
			System.out.println("File Not found");
		}
		
		//Getting data from excel
		for(int i=1;i<=Excel.getRowCount()-1;i++)
		{
			String title= excel.getCellData(sheetname, 0, i);
			String firstname=excel.getCellData(sheetname, 1, i);
			String lastname= excel.getCellData(sheetname, 2, i);
			String company= excel.getCellData(sheetname, 3, i);
			
			Object obj[] ={title, firstname, lastname, company};
			myData.add(obj);
		}
		return myData;
	}
	

}
