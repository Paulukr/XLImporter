package xl.XLImporter;

import java.util.Deque;
import java.util.function.Consumer;

public class LeadDao {
	public final static int dv = 1;
	Consumer<Object> log;
	public LeadDao(Consumer<Object> log) {
		this.log = log;
	}
	public void createTable(String name, CTestDriver cTestDriver) {
		String query = "CREATE TABLE toimport." + name + " (" +
    "LeadID int NOT NULL AUTO_INCREMENT,"+
//	ID varchar(255),
//	FormName varchar(255),
    "First_name varchar(255),"+
//	Last_name varchar(255),
	"EMail varchar(255),"+
	"Phone varchar(255),"+
//	Country varchar(255),	
	"CountryISO varchar(255),"+
//	campaign_id varchar(255),
//	Commentary varchar(255),
//	Currency varchar(255),
    "PRIMARY KEY (LeadID)"+
");";
//		System.out.println(query);
		log.accept(query);
		cTestDriver.executeUpdate(query);
	}
	public void insertTest(String tableName, CTestDriver cTestDriver) {
		StringBuilder query = new StringBuilder();
		query.append("insert into");
		query.append(" toimport." + tableName);
		query.append(" (First_name, EMail ,Phone, CountryISO) values ");
		query.append(" ('aa', 'ee', 10, 20);");
		cTestDriver.executeUpdate(query.toString());
	}
	public void insert(String tableName, CTestDriver cTestDriver, Deque<LeadShort> leads) {
		StringBuilder query = new StringBuilder();
		query.append("insert into");
		query.append(" toimport." + tableName);
		query.append(" (First_name, EMail ,Phone, CountryISO) values ");
		boolean first = true;
		for (LeadShort lead : leads) {
			if (first) {
				first = false;
			}
			else {
				query.append(", \n");
			}
			query.append(" ( ");
			query.append("'"+lead.firstName+"', ");
			query.append("'"+lead.email+"', ");
			query.append("'"+lead.phone+"', ");
			query.append("'"+lead.countryISO+"') ");
		}
		query.append(";");
//		query.append(" ('aa', 'ee', 10, 20);");
		cTestDriver.executeUpdate(query.toString());
	}
}
