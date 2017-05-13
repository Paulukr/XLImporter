package com.journaldev.java.ssh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SXSSFExample {


    public static void main(String[] args) throws Throwable {
    	String fileAddress = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
        FileInputStream inputStream = new FileInputStream(fileAddress + "\\mytemplate.xlsx");
        XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
        inputStream.close();

        SXSSFWorkbook wb = new SXSSFWorkbook(wb_template); 
        wb.setCompressTempFiles(true);

        SXSSFSheet sh = (SXSSFSheet) wb.getSheet("Sheet1");
        sh.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk
int h = 2;// 100000;
int w = 2;// 10;
//        for(int rownum = 4; rownum < h + 4; rownum++){
//        Row row = sh.createRow(rownum);
//        for(int cellnum = 0; cellnum < w; cellnum++){
//            Cell cell = row.createCell(cellnum);
//            String address = new CellReference(cell).formatAsString();
//            cell.setCellValue(address);
//        }
//
//    }

Iterator<Row> rowIterator = sh.rowIterator();
System.out.println(sh.getPhysicalNumberOfRows());
while(rowIterator.hasNext())
{
	SXSSFRow row = (SXSSFRow) rowIterator.next();
	SXSSFCell cell1 = (SXSSFCell) row.createCell(15);
	cell1.setCellValue("a");
	Iterator<Cell> cellIterator = row.cellIterator();

	while(cellIterator.hasNext())
	{

		SXSSFCell cell = (SXSSFCell) cellIterator.next();
		cell.setCellValue(1);
	}
}
//	Row row1 = sh.getRow(0);
//	Cell cell1 = row1.getCell(0);
//	cell1.setCellValue(1000);
    FileOutputStream out = new FileOutputStream(fileAddress + "\\tempsxssf.xlsx");
    wb.write(out);
    out.close();
    // dispose of temporary files backing this workbook on disk
    wb.dispose();
}

}