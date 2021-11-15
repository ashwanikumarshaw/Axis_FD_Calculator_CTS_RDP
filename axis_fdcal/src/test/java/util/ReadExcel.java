package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static String readExcel(String filePath, String fileName, String sheetName,int c) throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);


		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook excelbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			excelbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			excelbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name

		Sheet excelsheet = excelbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = excelsheet.getLastRowNum() - excelsheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it
		
		return String.valueOf(excelsheet.getRow(0).getCell(c).getNumericCellValue());

//		for (int i = 0; i < rowCount + 1; i++) {
//row.getCell(1).getNumericCellValue()
//			Row row = excelsheet.getRow(i);
//
//			// Create a loop to print cell values in a row
//
//			for (int j = 0; j < row.getLastCellNum(); j++) {
//
//				// Print Excel data in console
//				//System.out.print(String.valueOf(row.getCell(j)) + " || ");
//				//System.out.println(row.getCell(j) );
//				return  String.valueOf(row.getCell(j));
//			
//
//			}
//
//			
//			
//		}
//		return null;

	}

}
