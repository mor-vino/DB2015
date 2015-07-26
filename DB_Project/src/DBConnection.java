import java.sql.*;

/**
 * Created by user on 7/26/15.
 */
public class DBConnection {
    private String link;
    private String username;
    private String password;
    private Connection connection;

    public DBConnection() {
        ConfParser conf = new ConfParser();
        this.link = conf.getUrl();
        this.username = conf.getUsername();
        this.password = conf.getPassword();
        this.connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBConnection(String url, String username, String password) {
        this.link = url;
        this.username = username;
        this.password = password;
        this.connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            this.connection = DriverManager.getConnection(this.link, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet runQuery(String query) {
        if (this.connection == null) {
            this.connect();
        }

        ResultSet results = null;
        try {
            Statement statement = this.connection.createStatement();
            results =statement.executeQuery(query);
            statement.closeOnCompletion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.disconnect();
        return results;
    }

    public ResultSet getDBS() {
        DatabaseMetaData meta = null;
        try {
            meta = this.connection.getMetaData();
            return meta.getCatalogs();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
