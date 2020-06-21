package com.crm.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;

public class Excel {

	private static final int CELL_TYPE_STRING = 0;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static File filename;

public Excel(String excelfile, String sheetname) throws IOException {
		new File(excelfile);
		FileInputStream fis = new FileInputStream(excelfile);
		// System.out.println("I am dealing with " +fis);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		System.out.println("I am dealing with this sheet " + sheet.getSheetName());
	//	fis.close();
	}

	public static int getRowCount() throws IOException
	{
       String sheetname=Excel.getSheetName();
		int	 rowlastnumber = sheet.getPhysicalNumberOfRows();
		//System.out.println("Row Count is "+rowlastnumber);
		return rowlastnumber;
	}
   public static int getColumnCount(int row)
   {
	   int columnNumber=sheet.getRow(row).getPhysicalNumberOfCells();
	  
	   //System.out.println("Column Count is "+columnNumber);
	   return columnNumber;
   }
   
   public static String getSheetName()
   {
	   String sheetname =sheet.getSheetName();
	   return sheetname;
   }
   
   public static String getCellData(String sheetName,int colNum,int rowNum)
   {
       try
       {
           sheet = workbook.getSheet(sheetName);
           row = sheet.getRow(rowNum);
           
           cell = row.getCell(colNum);
           if(cell.getCellTypeEnum() == CellType.STRING)
               return cell.getStringCellValue();
           else if(cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
           {
               String cellValue  = String.valueOf(cell.getNumericCellValue());
               if (HSSFDateUtil.isCellDateFormatted(cell))
               {
                   DateFormat df = new SimpleDateFormat("dd/MM/yy");
                   Date date = cell.getDateCellValue();
                   cellValue = df.format(date);
               }
               return cellValue;
           }else if(cell.getCellTypeEnum() == CellType.BLANK)
               return "";
           else
               return String.valueOf(cell.getBooleanCellValue());
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
       }
   }
}
   

