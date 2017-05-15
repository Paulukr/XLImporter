package xl.XLImporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

public class Controller {
	public final static int cv = 1;
	
	ImportUI importUI;
	
	Extracter extracter;
	Deque<LeadShort> leads = new ArrayDeque<>();
	
	Consumer<Object> log;
	public Controller() {
//		log = importUI::println;
		log = (s) ->{
			System.out.println(s);
			importUI.println(s);
		};
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.run();
	}
	public void run() {
		importUI = new ImportUI();
		String path = importUI.getPath();
		importUI.println(path);
		
		if (promptYesNo("Proceed")) {
			doimport(path);
		}
//		
//		if (promptYesNo("Proceed")) {
//			System.exit(0);
//		}
		
	}
	public void doimport(String path) {
//		String fileAddress = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
//		String fileName = "\\Leads.xlsx";

		extracter = new Extracter();
		extracter.init(path,"");
		extracter.extract(true);
		
//		extracter.data.forEach(a->a.forEach(b->System.out.println(b.toString())));
		log.accept("Leads obtained: " + extracter.data.size());
		log.accept("dang conv");
		extracter.dangConv(leads);

		CTestDriver cTestDriver = new CTestDriver(log);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		importUI.println(dateFormat.format(date));
		
		String tableName =  "new_table" + dateFormat.format(date);
		//dbcon
		Runnable executeSql = new Runnable() {
			
			@Override
			public void run() {
				LeadDao leadDao = new LeadDao(log);
				leadDao.createTable(tableName, cTestDriver);
				leadDao.insert(tableName, cTestDriver,leads);
				
			}
		};
		Thread dbcon = new Thread(executeSql);
		dbcon.start();
		if (!dbcon.isAlive()) {
			dbcon.interrupt();	
		}
		
//		System.exit(0);
		//\dbcon
		
//		cTestDriver.testQuery();

		//SXSSFReader reader = new SXSSFReader(fileAddress + fileName);
		//ArrayDeque<ArrayDeque<String>> result = reader.doSimpleStringIterate();
		//result.forEach(a->a.forEach(b->System.out.println(b)));
	}

    public boolean promptYesNo(String str){
        Object[] options={ "yes", "no" };
        int foo=JOptionPane.showOptionDialog(null, 
               str,
               "Warning", 
               JOptionPane.DEFAULT_OPTION, 
               JOptionPane.WARNING_MESSAGE,
               null, options, options[0]);
         return foo==0;
      }
}
