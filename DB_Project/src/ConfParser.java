import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Itay on 7/26/15.
 */
public class ConfParser {
    private String confString;
    private String url;
    private String username;
    private String password;

    public ConfParser() {
        try {
            this.confString = new String(Files.readAllBytes(Paths.get("conf.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No conf.txt file found. BYE!");
            System.exit(1);
        }
        this.url = this.confString.split("\n")[0];
        this.username = this.confString.split("\n")[1];
        this.password = this.confString.split("\n")[2];
    }

    public String getUrl() {
        return this.url;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
