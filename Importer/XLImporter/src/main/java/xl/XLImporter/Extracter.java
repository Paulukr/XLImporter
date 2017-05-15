package xl.XLImporter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Extracter {
	public final static int ev = 1;

	String fileAddress;
	String fileName;
	XSSFWorkbook wb;
	public	ArrayDeque<ArrayDeque<Object>> data = new ArrayDeque<>();
	
	public Extracter() {
		// TODO Auto-generated constructor stub
	}

	public static XSSFWorkbook readWorkBook(String file){

		try(FileInputStream inputStream = new FileInputStream(file))
		{
			XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
			return wb_template;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	protected void pass() {
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
	protected void run() {
		String fileAddress1 = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
		String fileName1 = "\\mytemplate.xlsx";
		init(fileAddress1, fileName1);
//		pass();
		
		extract(true);
		//System.out::println
		data.forEach(a->a.forEach(b->System.out.println(b.toString())));
		System.out.println(data.size());
	}

	public void init(String fileAddress, String fileName) {
		this.fileAddress = fileAddress;
		this.fileName = fileName;
		wb = readWorkBook(fileAddress + fileName);
	}
//	public static void main(String[] args) {
//		Extracter ex2 = new Extracter();
//		ex2.run();
//	}
	public void extract(boolean silent) {
		if (wb == null) {
			return;
		}
		
		
		
	    DataFormatter formatter = new DataFormatter();
	    XSSFSheet sheet1 = wb.getSheet("Sheet2");
	    boolean header = true;
	    for (Row row : sheet1) {
	    	if (header) {
	    		header = false;
	    		continue;//header	
			}
	    	data.add(new ArrayDeque<>());
	        for (Cell cell : row) {
	     //   	data.getLast().add(new Object());
	       // 	Object currentCell = data.getLast().getLast();
	        	
	            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            if(!silent) System.out.print(cellRef.formatAsString());
	            if(!silent) System.out.print(" - ");

	            // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
	            String text = formatter.formatCellValue(cell);
	            if(!silent) System.out.println(text);

	            // Alternatively, get the value and format it yourself
	            switch (cell.getCellTypeEnum()) {
	                case STRING:
	                    if(!silent) System.out.println(cell.getRichStringCellValue().getString());
	                    //currentCell = cell.getRichStringCellValue().getString();
	    	        	data.getLast().add(cell.getRichStringCellValue().getString());
	                    break;
	                case NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        if(!silent) System.out.println(cell.getDateCellValue());
	                        //currentCell = cell.getDateCellValue();
	                    } else {
	                        if(!silent) System.out.println(cell.getNumericCellValue());
	                        //currentCell = cell.getNumericCellValue();
	                        data.getLast().add(cell.getNumericCellValue());
	                    }
	                    break;
	                case BOOLEAN:
	                    if(!silent) System.out.println(cell.getBooleanCellValue());
	                    //currentCell = cell.getBooleanCellValue();
	                    data.getLast().add(cell.getBooleanCellValue());
	                    break;
	                case FORMULA:
	                    if(!silent) System.out.println(cell.getCellFormula());
	                    //currentCell = cell.getCellFormula();
	                    data.getLast().add(cell.getCellFormula());
	                    break;
	                case BLANK:
	                    System.out.println();
	                    //currentCell = "";
	                    data.getLast().add("");
	                    break;
	                default:
	                	//currentCell = "";
	                	data.getLast().add("");
	            }
	        }
	    }
	}
	public void dangConv(Deque<LeadShort> leads){
		data.forEach(row->{
			leads.add(new LeadShort());
			ArrayList<Object> rowArray = new ArrayList<>(row);
//			Double temp = (Double) rowArray.get(1);
//			leads.getLast().firstName = ((Double)rowArray.get(1)).toString();
			
//			String string = (String) rowArray.get(0);
			
//			leads.getLast().firstName = ((String)rowArray.get(0)).toString();
//			System.out.println(temp);

			leads.getLast().firstName = ((String)rowArray.get(0)).toString();
//			System.out.println(leads.getLast().firstName);
			leads.getLast().countryISO = ((Double)rowArray.get(1)).intValue();
			leads.getLast().phone = ((Double)rowArray.get(2)).longValue();
			leads.getLast().email = ((String)rowArray.get(3)).toString();
		});
	}

}
