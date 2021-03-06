package xl.XLImporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import com.jcraft.jsch.SftpException;

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
//		importUI.println(dateFormat.format(date));
		
		String tableName =  "new_table" + dateFormat.format(date);
		importUI.println(tableName);
		//dbcon
		Runnable executeSql = new Runnable() {
			
			@Override
			public void run() {
				LeadDao leadDao = new LeadDao(log);
				leadDao.createTable(tableName, cTestDriver);
				leadDao.insert(tableName, cTestDriver,leads);
//				leadDao.saleCheckProcedure1(10, "Eng", cTestDriver);
//				leadDao.showCountriesProcedure(cTestDriver);
//				leadDao.buyerPreviewProcedure(1, cTestDriver);
//				leadDao.addBuyer("AnitherBuyer", "Hello", cTestDriver);
				leadDao.salePreview(48, "Eng", 100, cTestDriver);
				

				
				String SFTPWORKINGDIR = "../var/lib/mysql-files/";
				String FILETOTRANSFER = "E:\\Android\\Leads.csv"; // 
				String WWW = "../var/www/sub.noblecapitalhouse.com/html/public/data/";
	            File f = new File(FILETOTRANSFER);
	            try {
	            	System.out.println(f);
	            	System.out.println(cTestDriver.channelSftp);
//					cTestDriver.channelSftp.put(new FileInputStream(f), f.getName());
//					cTestDriver.channelSftp.put(FILETOTRANSFER, SFTPWORKINGDIR);
//					cTestDriver.channelSftp.put(FILETOTRANSFER, SFTPWORKINGDIR+"Leads1.csv");
					cTestDriver.channelSftp.get(SFTPWORKINGDIR+"Leads1.csv", FILETOTRANSFER+"1");
					cTestDriver.channelSftp.put(FILETOTRANSFER +"1",WWW+"Leads1.csv");
					cTestDriver.channelSftp.get(WWW+"Leads1.csv", FILETOTRANSFER+"2");
					cTestDriver.channelSftp.put(FILETOTRANSFER+"2", SFTPWORKINGDIR+"Leads1import.csv");
				} catch (SftpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
				cTestDriver.channelSftp.disconnect();
				
				
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
