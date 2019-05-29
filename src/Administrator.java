import java.io.Serializable;

public class Administrator extends Person implements Serializable {
    private String Username,Password,transactionhistory;

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

    public String getTransactionhistory() {
        return transactionhistory;
    }

    public void setTransactionhistory(String transactionhistory) {
        this.transactionhistory = transactionhistory;
    }

    public Administrator(String name, String cnic, String phonenumber, String username, String password, String transactionhistory) {
        super(name, cnic, phonenumber);
        Username = username;
        Password = password;
        this.transactionhistory=transactionhistory;


    }

}
