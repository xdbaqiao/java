package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceExample {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/datashare";
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
		} catch (ClassNotFoundException e2) {
			System.out.println("Helo1");
			e2.printStackTrace();
		}

		Connection co = null;
		Statement st = null;

		co = DriverManager.getConnection(url, "test", "test");
		st = co.createStatement();
		ResultSet rs =  st.executeQuery("select * from usercode limit 10; ");
		while(rs.next()) {
			for(int i=1;i<rs.getMetaData().getColumnCount();i++){
				System.out.println("coloumn :" + i);
				System.out.println(rs.getString(i));
			}
		}
		
	}

}

