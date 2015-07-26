import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 7/26/15.
 */
public class Testmodule {
    public static void main(String[] args) {
        DBConnection db = new DBConnection("jdbc:mysql://db4free.net:3306/mcsdb2015", "huerba", "shalmot");

        ResultSet results = db.getDBS();
        try {
            while (results.next()) {
                System.out.println(results.getString("TABLE_CAT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
