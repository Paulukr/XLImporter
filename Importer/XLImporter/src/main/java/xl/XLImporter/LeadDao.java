package xl.XLImporter;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		int result = cTestDriver.executeUpdate(query);
		log.accept("db responce: " + result);
	}
	public void insertTest(String tableName, CTestDriver cTestDriver) {
		StringBuilder query = new StringBuilder();
		query.append("insert into");
		query.append(" toimport." + tableName);
		query.append(" (First_name, EMail ,Phone, CountryISO) values ");
		query.append(" ('aa', 'ee', 10, 20);");
		int result = cTestDriver.executeUpdate(query.toString());
		log.accept("db responce: " + result);
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
		int result = cTestDriver.executeUpdate(query.toString());
		log.accept("db responce: " + result);
	}
	void saleCheckProcedure(int buyer, String region, CTestDriver cTestDriver){
		String procedure = "SaleCheckProcedure(?,?)";
		Consumer<CallableStatement> setter = cs ->{
			try {
				cs.setInt(1, buyer);
				cs.setString(2, region);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		Consumer<ResultSet> getter = rs ->{
			try {
				String result = rs.getString(1);
				log.accept("call responce: " + result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		cTestDriver.call(procedure, setter, getter);
	}
	void saleCheckProcedure1(int buyer, String region, CTestDriver cTestDriver){
		String procedure = "`invo`.`invo.sale_check_procedure`(?,?)";
		Consumer<CallableStatement> setter = cs ->{
			try {
				cs.setInt(1, buyer);
				cs.setString(2, region);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		Consumer<ResultSet> getter = rs ->{
			try {
				String result2 = rs.getString(1);
				log.accept("call responce: " + " 2) " + result2 + " 3) ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		cTestDriver.call(procedure, setter, getter);
	}
	void showCountriesProcedure(CTestDriver cTestDriver){
		String procedure = "`invo`.`show_countries`()";
		Consumer<CallableStatement> setter = cs ->{};
		Consumer<ResultSet> getter = rs ->{
			try {
				String result2 = rs.getString(1);
				log.accept("call responce: " + " 2) " + result2 + " 3) ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		cTestDriver.call(procedure, setter, getter);
	}
	void buyerPreviewProcedure(int buyer, CTestDriver cTestDriver){
		String procedure = "`invo`.`BuyerPreview`(?)";
		Consumer<CallableStatement> setter = cs ->{
			try {
				cs.setInt(1, buyer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		Consumer<ResultSet> getter = rs ->{
			try {
				String result2 = rs.getString(1);
				String result3 = rs.getString(2);
				log.accept("call responce: " + result2  + "  " + result3 + " ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		cTestDriver.call(procedure, setter, getter);
	}
	void addBuyer(String buyerName, String description, CTestDriver cTestDriver){
		String procedure = "`invo`.`add_buyer`(?,?)";
		Consumer<CallableStatement> setter = cs ->{
			try {
				cs.setString(1, buyerName);
				cs.setString(2, buyerName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		Consumer<ResultSet> getter = rs ->{
			try {
				String result2 = rs.getString(1);
				String result3 = rs.getString(2);
				String result4 = rs.getString(3);
				log.accept("call responce: " + result2  + "  " + result3 + "  " + result3);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		cTestDriver.call(procedure, setter, getter);
	}
	void salePreview(int buyerid, String region, int count, CTestDriver cTestDriver){
		String procedure = "`invo`.`sale_preview_procedure`(?,?,?)";
		Consumer<CallableStatement> setter = cs ->{
			try {
				cs.setInt(1, buyerid);
				cs.setString(2, region);
				cs.setInt(3, count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		Consumer<ResultSet> getter = rs ->{
			try {
				String result2 = rs.getString(1);
				String result3 = rs.getString(2);
				String result4 = rs.getString(3);
				log.accept("call responce: " + result2  + "  " + result3 + "  " + result3);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		};
		cTestDriver.call(procedure, setter, getter);
	}
}

