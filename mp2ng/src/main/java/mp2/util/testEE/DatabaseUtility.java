package mp2.util.testEE;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DatabaseUtility
{
	public static ComboPooledDataSource getDataSource() throws PropertyVetoException
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/mydb");
		cpds.setUser("postgres");
		cpds.setPassword("postgresql");

		// Optional Settings
cpds.setInitialPoolSize(5);
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
cpds.setMaxStatements(100);

		return cpds;
	}

	public static void main(String[] args) throws SQLException
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try
		{
			ComboPooledDataSource dataSource = DatabaseUtility.getDataSource();
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM account");
			
			System.out.println("\n\nHey YOU \n try");
			
			System.out.println("The Connection Object is of Class: " + connection.getClass());
			System.out.println("\n\nHey YOU \n class");
			resultSet = pstmt.executeQuery();
			while (resultSet.next())
			{
				System.out.println("\n\nHey YOU \n while");
				System.out.println("\n\nHey YOU \n" + resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3) + "\n");
			}

		}
		catch (Exception e)
		{
			System.out.println("\n\nHey YOU \n exception");
			connection.rollback();
			e.printStackTrace();
		}
	}
}

