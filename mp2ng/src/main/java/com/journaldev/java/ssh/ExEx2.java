package com.journaldev.java.ssh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



 import org.apache.poi.ss.usermodel.*;
public class ExEx2 {

	public ExEx2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String fileAddress = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
		String fileName = "\\mytemplate.xlsx";
		XSSFWorkbook wb = null;
		try(FileInputStream inputStream = new FileInputStream(fileAddress+fileName))
		{
			wb = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (wb == null) {
			return;
		}
	    DataFormatter formatter = new DataFormatter();
	    XSSFSheet sheet1 = wb.getSheet("Sheet1");
	    
	    for (Row row : sheet1) {
	        for (Cell cell : row) {
	            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            System.out.print(cellRef.formatAsString());
	            System.out.print(" - ");

	            // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
	            String text = formatter.formatCellValue(cell);
	            System.out.println(text);

	            // Alternatively, get the value and format it yourself
	            switch (cell.getCellTypeEnum()) {
	                case STRING:
	                    System.out.println(cell.getRichStringCellValue().getString());
	                    break;
	                case NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        System.out.println(cell.getDateCellValue());
	                    } else {
	                        System.out.println(cell.getNumericCellValue());
	                    }
	                    break;
	                case BOOLEAN:
	                    System.out.println(cell.getBooleanCellValue());
	                    break;
	                case FORMULA:
	                    System.out.println(cell.getCellFormula());
	                    break;
	                case BLANK:
	                    System.out.println();
	                    break;
	                default:
	                    System.out.println();
	            }
	        }
	    }
	}

}
