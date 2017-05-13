package xl.XLImporter;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImportUI {
	public final static int uv = 1;
	public ImportUI() {

	}
	public String getPath() {
		 String filepath = "" ;
		String title  = "Importer v0u" +ImportUI.uv+"e"+Extracter.ev+"d"+LeadDao.dv+"c" + Controller.cv;
		JFrame jFrame = new JFrame(title);
		JPanel jPanel = new JPanel();

		String currentDirectoryPath = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\target\\classes\\com\\journaldev\\java\\ssh";
		JFileChooser jFileChooser = new JFileChooser(currentDirectoryPath);
//		jFileChooser.addActionListener(l);
		jPanel.add(jFileChooser);
		jFrame.setSize(500, 500);
		jFrame.add(jPanel);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		
//		int result = jFileChooser.showSaveDialog(this);
		int result = jFileChooser.showOpenDialog(jPanel);
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
//class FOListener implements ActionListener{
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		  if (e.getSource() == jFileChooser.) {
//			  
//		  }
//		
//	}
	
//}
