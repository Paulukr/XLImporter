package xl.XLImporter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ImportUI {

	String filepath = "" ;
	String title  = "Importer v0u" +ImportUI.uv+"e"+Extracter.ev+"d"+LeadDao.dv+"c" + Controller.cv;
	JFrame jFrame = new JFrame(title);
	
	JPanel jPanelFile = new JPanel();

	JPanel jPanelControl = new JPanel();
	JTextArea jTextArea;

	String currentDirectoryPath;
	JFileChooser jFileChooser;

	public final static int uv = 1;
	public ImportUI() {
//		jTextArea.setSize(100, 500);
		jTextArea = new JTextArea("Importer start");
		jTextArea.setBounds(10,30, 400,360);
//		jTextArea.setText("Set Text");
		
		
		
//		jPanelControl.add(jTextArea);
		
//		jFrame.add(jPanelControl);
		
		jFrame.add(jTextArea);
		
		jFrame.setSize(400, 400);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		
//		TextAreaExample textAreaExample = new TextAreaExample();
	}
	public void println(Object string){
		DateFormat dateFormat = new SimpleDateFormat("mm_ss"); //yyyy_MM_dd_HH_mm_ss"
		Date date = new Date();
//		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		jTextArea.append("\n" + dateFormat.format(date) + " " + string.toString());
	}
	public String getPath() {
		
		currentDirectoryPath = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
		jFileChooser = new JFileChooser(currentDirectoryPath);

		int result = jFileChooser.showOpenDialog(jPanelFile);
		if (result == JFileChooser.APPROVE_OPTION) {
			System.out.println("FileChoosed");
			File file = jFileChooser.getSelectedFile();
			filepath =file.getAbsolutePath();

		} else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel was selected");
		}
		return filepath;
	}
}
class TextAreaExample  
{  
     TextAreaExample(){  
        JFrame f= new JFrame();  
        JTextArea area=new JTextArea("Welcome to javatpoint");  
        area.setBounds(10,30, 200,200);  
        f.add(area);  
        f.setSize(300,300);  
        f.setLayout(null);  
        f.setVisible(true);  
     }  
}
