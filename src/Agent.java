import java.io.Serializable;

public class Agent extends Person implements Serializable {
    private String username,password,transactionhistory;
    private double walletmoney;

    public Agent(String name, String cnic, String phonenumber, String username, String password, double walletmoney,
                 String transactionhistory) {
        super(name, cnic, phonenumber);
        this.username = username;
        this.password = password;
        this.walletmoney = walletmoney;
        this.transactionhistory=transactionhistory;
    }

    public String getTransactionhistory() {
        return transactionhistory;
    }

    public void setTransactionhistory(String transactionhistory) {
        this.transactionhistory = transactionhistory;
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
