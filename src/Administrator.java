import java.io.Serializable;

public class Administrator extends Person implements Serializable {
    private String Username,Password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Administrator(String name, String cnic, int phonenumber, String email, String username, String username1, String password) {
        super(name, cnic, phonenumber, email, username);
        Username = username1;
        Password = password;
    }
}
