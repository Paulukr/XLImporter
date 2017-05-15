package xl.XLImporter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SXSSFReader {

	String fullPath;
	XSSFWorkbook wbx;
	SXSSFWorkbook wbs;
	SXSSFSheet sh;
	
	public SXSSFReader(String fullPath) {
		this.fullPath = fullPath;
	}

	public ArrayDeque<ArrayDeque<String>> doSimpleStringIterate(){
		XSSFWorkbook wbTemplate = readWorkBook(fullPath);
		@SuppressWarnings("resource")
		SXSSFWorkbook wb = new SXSSFWorkbook(wbTemplate); 
		wb.setCompressTempFiles(true);

		SXSSFSheet sh = (SXSSFSheet) wb.getSheetAt(0);
		sh.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk
		ArrayDeque<ArrayDeque<String>> result;
		result =  simpleStringIterate(sh);;
		// dispose of temporary files backing this workbook on disk
		wb.dispose();
		return result;
	}
	
	public static void soleTest() throws Throwable {
		example();
	}
	public static XSSFWorkbook readWorkBook(String file){

		try(FileInputStream inputStream = new FileInputStream(file))
		{
			XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
			return wb_template;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void writeWorkBook(String file, SXSSFWorkbook wb ){

		try( FileOutputStream out = new FileOutputStream(file))
		{
			wb.write(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void iteratorTemplate(SXSSFSheet sh) {
		Iterator<Row> rowIterator = sh.rowIterator();

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
	}

	@SuppressWarnings("unused")
	private static void iteratorTemplate2(SXSSFSheet sh) {
		int h = 2;// 100000;
		int w = 2;// 10;
		for(int rownum = 4; rownum < h + 4; rownum++){
			Row row = sh.createRow(rownum);
			for(int cellnum = 0; cellnum < w; cellnum++){
				Cell cell = row.createCell(cellnum);
				String address = new CellReference(cell).formatAsString();
				cell.setCellValue(address);
			}
		}
	}
	private static void example() {
		String fileAddress = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
		String fileName = "\\mytemplate.xlsx";
		XSSFWorkbook wbTemplate = readWorkBook(fileAddress + fileName);
		SXSSFWorkbook wb = new SXSSFWorkbook(wbTemplate); 
		wb.setCompressTempFiles(true);

		SXSSFSheet sh = (SXSSFSheet) wb.getSheetAt(0);
		sh.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk

		//iteratorTemplate2(sh);
		iteratorTemplate(sh);

		writeWorkBook(fileAddress + fileName,wb);
		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}

	private static ArrayDeque<ArrayDeque<String>> simpleStringIterate(SXSSFSheet sh) {
		Iterator<Row> rowIterator = sh.rowIterator();
		ArrayDeque<ArrayDeque<String>> result = new ArrayDeque<ArrayDeque<String>>();
		while(rowIterator.hasNext())
		{
			SXSSFRow row = (SXSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			result.add(new ArrayDeque<String>());
			while(cellIterator.hasNext())
			{
				SXSSFCell cell = (SXSSFCell) cellIterator.next();
//				cell.setCellValue(1);
				result.getLast().add(cell.getStringCellValue());
			}
		}
		return result;
	}
}