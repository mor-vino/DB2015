import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DDLHandler implements Handler {
	
	private Connection connection;
	
	public DDLHandler(Connection c) {
		this.connection = c;
		int f;

	
	}


	@Override
	public void handle(String query) {
		// send to the remote data base
		try {
			Statement st = this.connection.createStatement();
			ResultSet resultSet = st.executeQuery(query); 
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + ":" + 
				resultSet.getString("lastName") ); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
