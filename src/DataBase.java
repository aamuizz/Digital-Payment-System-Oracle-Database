import sun.management.Agent;

import javax.swing.*;
import java.sql.*;

public class DataBase {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    DataBase() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/pdborcl", "hr", "hr");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isAdmin(String username, String password) {
        try {
            String query = "SELECT PASSWORD FROM ADMIN where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            String pass= "";
            while(resultSet.next()) {
                pass = resultSet.getString(1);
            }
            if (pass.equals(password))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAgent(String username, String password) {
        try {
            String query = "SELECT PASSWORD FROM AGENT where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String pass = resultSet.getString(1);
            if (pass.equals(password))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAgent(String username) {
        try {
            String query = "SELECT username FROM AGENT";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(username)) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAdmin(String username) {
        try {
            String query = "SELECT username FROM ADMIN";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(username)) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getName(String username) {
        try {
            String query = "SELECT NAME FROM PERSON where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString(1);
            return name;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmail(String username) {
        try {
            String query = "SELECT EMAIL FROM PERSON where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String email = resultSet.getString(1);
            return email;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPhoneNumber(String username) {
        try {
            String query = "SELECT PHONENO FROM PERSON where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int phoneNumber = resultSet.getInt(1);
            return phoneNumber;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getCNIC(String username) {
        try {
            String query = "SELECT CNIC FROM PERSON where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String cnic = resultSet.getString(1);
            return cnic;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTransactionHistory(String username) {
        String history = "";
        try {

            // If you are sender
            String query = "SELECT SENDERUSERNAME,RECEIVERUSERNAME,AMOUNT FROM TRANSACTION where SENDERUSERNAME = ? or RECEIVERUSERNAME = ? order by TRANSACTIONID";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String line = "Sender username : " + resultSet.getString(1) + "\t\t\t";
                line = line.concat("Receiver username : " + resultSet.getString(2) + "\t\t\t");
                line = line.concat("Amount : " + resultSet.getInt(3) + "\t\t\t\n");

                history = history.concat(line);
            }
            history+="\n_________________________________________________________________________________\n";
            String query2 = "select * FROM UTILITYBILL order by BILLNO";
            Statement s2 = connection.createStatement();
            ResultSet resultSet3 = s2.executeQuery(query2);
            while (resultSet3.next()){
                if(resultSet3.getString("USERNAME").equals(username)){
                    history+="\nYou Have Paid "+resultSet3.getString("BILLTYPE")+" Bill "+ "of "+ "Rs "+resultSet3.getString("AMOUNT")+ " of"+" Customer ID "+resultSet3.getString("CUSTOMERID");

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
    public void actionqueryinsert(String querytext,String username) throws SQLException {
        String query = "INSERT into QUERY(QUERYID,QUERYTEXT,USERNAME)VALUES (SEQ_QUERYID.nextval,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,querytext);
        preparedStatement.setString(2,username);
        preparedStatement.execute();
        JOptionPane.showMessageDialog(null,"Query Sent Successfully to Admininstrator");


    }
    public String getQuerieshistory() throws SQLException {
        String userqueryhistory="";
        String query = "Select * from Query";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            userqueryhistory+=
                    "\n"+"Username:"+resultSet.getString("USERNAME")+
                    "\n"+resultSet.getString("QUERYTEXT")+
                            "\n"+"____________________________________________________________________________________";
        }
        return userqueryhistory;


    }
    public boolean havenoughmoneyagent(String username,int amount) throws SQLException {
        String query = "SELECT WALLETMONEY from AGENT where USERNAME=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,username);
        preparedStatement.executeQuery();
        resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return(resultSet.getInt("WALLETMONEY")>=amount);


    }

    public void updateMoney(String username, int amount) {
        try {
            String query = "";
            int balance = 0;

            // For agent
            if (isAgent(username)) {
                query = "select WALLETMONEY from AGENT where USERNAME = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                balance = resultSet.getInt("WALLETMONEY");
                query = "update AGENT set WALLETMONEY = ? where USERNAME = ?";
                balance += amount;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, balance);
                preparedStatement.setString(2, username);
                resultSet = preparedStatement.executeQuery();
                if(isAgent(username)) {
                    AgentManagement.totalbalancelabel.setText(" Balance: " + balance + " PKR");
                }
            }

            // For member
            else {
                query = "select WALLETMONEY from MEMBER where USERNAME = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                balance = resultSet.getInt(1);
                query = "update MEMBER set WALLETMONEY = ? where USERNAME = ?";
                balance += amount;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, balance);
                preparedStatement.setString(2, username);
                resultSet = preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendMoney(String sender, String reciever, int amount) {
        updateMoney(reciever, amount);
        updateMoney(sender, -amount);


        addTransaction(sender, reciever, amount);
        AgentManagement.transactionhistory.setText(getTransactionHistory(sender));
    }

    public void addTransaction(String sender, String reciever, int amount) {
        try {
            String query = "insert into TRANSACTION (TRANSACTIONID, SENDERUSERNAME, RECEIVERUSERNAME,DATEANDTIME, AMOUNT) values (SEQ_TRANSID.nextval,?,?,sysdate,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, reciever);
            preparedStatement.setInt(3, amount);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserExist(String username) {
        try {
            String query = "select * from PERSON where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("USERNAME").equals(username)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isMemberExist(String username) {
        try {
            String query = "select * from MEMBER where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("USERNAME").equals(username)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int getMoney(String username) {
        try {
            if (isAgent(username)) {
                String query = "select WALLETMONEY from AGENT where USERNAME = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                return resultSet.getInt(1);
            } else {
                String query = "select WALLETMONEY from Member where USERNAME = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteUser(String username) {
        try {
            // Delete from agent
            String query = "delete from AGENT where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            // Delete from member
            query = "delete from MEMBER where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            // Delete from admin
            query = "delete from ADMIN where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            // Delete from CreditCard
            query = "delete from CREDITCARD where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            //Delete from utilitybill
            query = "delete from UTILITYBILL where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            //Delete from Transactionhistory
            query = "delete from TRANSACTION where SENDERUSERNAME = ? or RECEIVERUSERNAME= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,username);
            resultSet = preparedStatement.executeQuery();

            // Delete from PERSON
            query = "delete from PERSON where USERNAME = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerAgent(String username, String name, String email, int phone_no, String cnic, String password) {
        try {
            // Fot the Person
            String query = "insert into PERSON values (?,?,?,?,?,SEQ_PERSONID.nextval)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, phone_no);
            preparedStatement.setString(5, cnic);
            resultSet = preparedStatement.executeQuery();

            // For the Agent
            query = "insert into AGENT (USERNAME, PASSWORD,WALLETMONEY) values (?,?,100)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void payBill(String username, int amount, String type,String consumerid) {
        try {
            String query = "";
            int balance = 0;

            // For admin
            if (isAdmin(username)) {
                addutilitybill(username,consumerid,type,amount);
                AdminManagement.transactionhistory.setText(getTransactionHistory(username));
                JOptionPane.showMessageDialog(null, "Bill Paid");

            }

            // For agent
            else if (isAgent(username)) {
                query = "select WALLETMONEY from AGENT where USERNAME = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                balance = resultSet.getInt("WALLETMONEY");
                if(balance>=amount) {
                    query = "update AGENT set WALLETMONEY = ? where USERNAME = ?";
                    balance -= amount;
                    AgentManagement.totalbalancelabel.setText(" Balance: " + balance + " PKR");
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, balance);
                    preparedStatement.setString(2, username);
                    resultSet = preparedStatement.executeQuery();
                    addutilitybill(username, consumerid,type, amount);
                    AgentManagement.transactionhistory.setText(getTransactionHistory(username));
                    JOptionPane.showMessageDialog(null,"Bill Paid");
                }
                else{
                    JOptionPane.showMessageDialog(null,"You don't have Enough Balance");
                }
            }
            // Adding transaction

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addutilitybill(String username, String customerid,String type, int amount) throws SQLException {
        String query3 ="insert into utilitybill (billno, customerid, amount, billtype, username) values \n" +
                "  (seq_utilitybill.nextval,?,?,?,?)";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
            preparedStatement2.setString(1,customerid);
        preparedStatement2.setInt(2,amount);
        preparedStatement2.setString(3,type);
        preparedStatement2.setString(4,username);
        preparedStatement2.execute();
        preparedStatement2.close();


    }
}
