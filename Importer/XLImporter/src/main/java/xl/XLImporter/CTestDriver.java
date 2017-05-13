package xl.XLImporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


public class CTestDriver
{
	Connection con = null;

	public CTestDriver() {
		try
		{
			String strSshUser = "root"; // SSH loging username
			String strSshPassword = "5gBiQu96VRig"; // SSH login password
			String strSshHost = "138.68.72.237"; // hostname or ip or SSH server
			int nSshPort = 22; // remote SSH host port number
			String strRemoteHost = "127.0.0.1"; // hostname or ip of your database server
			int nLocalPort = 13306; // local port number use to bind SSH tunnel
			int nRemotePort = 3306; // remote port number of your database
			String strDbUser = "excel1"; // database loging username
			String strDbPassword = "mypass"; // database login password

			CTestDriver.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);

			//Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:"+nLocalPort, strDbUser, strDbPassword);
			System.out.println("Connected.");


		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void doSshTunnel( String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort ) throws JSchException
	{
		final JSch jsch = new JSch();
		Session session = jsch.getSession( strSshUser, strSshHost, 22 );
		session.setPassword( strSshPassword );

		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no" );
		session.setConfig( config );

		session.connect();
		session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	}
	void testQuery(){
		//STEP 4: Execute a query
		System.out.println("Creating statement...");
		try(	Statement stmt = con.createStatement()) {

			String sql;
			sql = "SELECT IsoCode, Alpha2, Alpha3 FROM main_base.countries limit 5";
			//STEP 5: Extract data from result set
			try(ResultSet rs = stmt.executeQuery(sql)) {
				while(rs.next()){
					//Retrieve by column name
					int id  = rs.getInt("IsoCode");
					String first = rs.getString("Alpha2");
					String last = rs.getString("Alpha3");

					//Display values
					System.out.print("ID: " + id);
					System.out.print(", First: " + first);
					System.out.println(", Last: " + last);
				}
			} 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	void executeQuery(String sql){
		//STEP 4: Execute a query
		System.out.println("Creating statement...");
		try(	Statement stmt = con.createStatement()) {
			
			//STEP 5: Extract data from result set
			try(ResultSet rs = stmt.executeQuery(sql)) {
				while(rs.next()){
					//Retrieve by column name
					int id  = rs.getInt("IsoCode");
					String first = rs.getString("Alpha2");
					String last = rs.getString("Alpha3");

					//Display values
					System.out.print("ID: " + id);
					System.out.print(", First: " + first);
					System.out.println(", Last: " + last);
				}
			} 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	void executeUpdate(String sql){
		//STEP 4: Execute a query
		System.out.println("Creating statement...");
		try(	Statement stmt = con.createStatement()) {

			//STEP 5: Extract data from result set
			try {
				int rs = stmt.executeUpdate(sql);
				System.out.println("update: " + rs);
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args)
	{
		CTestDriver cTestDriver = new CTestDriver();
//		cTestDriver.testQuery();
		String sql = "SELECT IsoCode, Alpha2, Alpha3 FROM main_base.countries limit 5";
		cTestDriver.executeQuery(sql);
		cTestDriver.close();
		System.exit(0);
	}
}