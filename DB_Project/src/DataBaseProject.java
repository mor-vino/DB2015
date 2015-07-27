import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DataBaseProject {

	public static void main(String[] args) {
		DataBaseProject dbp = new DataBaseProject();
		Connection connection = dbp.makeConnection();
		MainGui mainGui = new MainGui(connection);
		mainGui.runGUI();
	}
	
	public Connection makeConnection() {
		// check the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("the JDBC Driver is not in this folder");
			e.printStackTrace();
			return null;
		}
		// initialize connection
		Connection connection = null;	 
		try {
			// read the adress from a file
			// TODO
			connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/mcsdb2015","huberpa", "shalmot");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	 
		if (connection != null) {
			return connection;
		} else {
			System.out.println("Failed to make connection!");
			return null;
		}		
	}
}