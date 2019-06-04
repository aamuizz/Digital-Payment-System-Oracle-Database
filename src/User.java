
import java.io.Serializable;

public class User extends Person {
 private String username,password;
 private double walletmoney;

    public User(String name, String cnic, int phonenumber, String email, String username, String username1, String password, double walletmoney) {
        super(name, cnic, phonenumber, email, username);
        this.username = username1;
        this.password = password;
        this.walletmoney = walletmoney;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWalletmoney() {
        return walletmoney;
    }

    public void setWalletmoney(double walletmoney) {
        this.walletmoney = walletmoney;
    }
}
