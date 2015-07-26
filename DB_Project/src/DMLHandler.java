import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



public class DMLHandler implements Handler {
	private DMLSyntaxValidator dmlSyntaxValidator;
	private Connection connection;
	
	public DMLHandler(Connection c) {
		this.connection = c;
	
	}
	@Override
	public void handle(String query) {
		if(this.isTheQueryLegal(query)) {
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
		} else {
			// send error message to the user
			JOptionPane.showMessageDialog(null, "WRONG QUERY STRUCTURE");
		}
	}
	@Override
	public boolean isTheQueryLegal(String query) {
		this.dmlSyntaxValidator = new DMLSyntaxValidator(query);
		// check if is a legal DML query
		return dmlSyntaxValidator.validateQuery();
	}
}
