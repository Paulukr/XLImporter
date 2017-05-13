package com.journaldev.java.ssh.importer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;

public class Controller {
	Extracter extracter;
	Deque<LeadShort> leads = new ArrayDeque<>();
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.doimport();
	}
	public void doimport() {
		
		String fileAddress = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
		String fileName = "\\Leads.xlsx";
		
		extracter = new Extracter();
		extracter.init(fileAddress, fileName);
		extracter.extract(true);
		
//		extracter.data.forEach(a->a.forEach(b->System.out.println(b.toString())));
		System.out.println(extracter.data.size());
		
		System.out.println("dang conv");
		dangConv();
		LeadDao leadDao = new LeadDao();
		CTestDriver cTestDriver = new CTestDriver();
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
		String tableName =  "new_table" + dateFormat.format(date);
		leadDao.createTable(tableName, cTestDriver);
		leadDao.insert(tableName, cTestDriver,leads);
		
		
		
//		cTestDriver.testQuery();
		System.exit(0);
		//SXSSFReader reader = new SXSSFReader(fileAddress + fileName);
		//ArrayDeque<ArrayDeque<String>> result = reader.doSimpleStringIterate();
		//result.forEach(a->a.forEach(b->System.out.println(b)));
	}
	
	public void dangConv(){
		extracter.data.forEach(row->{
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
