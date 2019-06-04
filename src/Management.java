import jdk.nashorn.internal.scripts.JO;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Management {
    static Connection connection = null;
    static User user;

    public static void registerbuttonuserAction() {
        MainFrame.RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase dataBase = new DataBase();
                if(!dataBase.isMemberExist(MainFrame.usernameusertextfield.getText())) {
                    User p = new User(MainFrame.nameusertextfield.getText(), MainFrame.cnicusertextfield.getText()
                            , Integer.parseInt(MainFrame.phonenumberusertextfield.getText()), MainFrame.emailusertextfied.getText(),
                            MainFrame.usernameusertextfield.getText(), MainFrame.usernameusertextfield.getText(), MainFrame.passwordusertextfield.getText(), 100);


                    try {
                        connection = DriverManager.getConnection(
                                "jdbc:oracle:thin:@//localhost:1521/pdborcl", "hr", "hr");
                        connection.setAutoCommit(false);
                    } catch (SQLException e1) {
                        System.out.println("Connection Error");
                        e1.printStackTrace();
                    }
                    try {

                        String query = "insert into PERSON(Username,Name,Email,Phoneno,CNIC,Personid)"
                                + " values (?, ?, ?, ?, ?, SEQ_PERSONID.nextval)";
                        String memberquery = "insert into Member(username, password,WALLETMONEY) VALUES (?,?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, p.getUsername());
                        preparedStatement.setString(2, p.getName());
                        preparedStatement.setString(3, p.getEmail());
                        preparedStatement.setInt(4, p.getPhonenumber());
                        preparedStatement.setString(5, p.getCnic());
                        preparedStatement.execute();
                        preparedStatement.close();
                        PreparedStatement preparedStatement1 = connection.prepareStatement(memberquery);
                        preparedStatement1.setString(1, p.getUsername());
                        preparedStatement1.setString(2, p.getPassword());
                        preparedStatement1.setInt(3, (int) p.getWalletmoney());
                        preparedStatement1.execute();
                        preparedStatement1.close();
                        connection.close();
                        JOptionPane.showMessageDialog(null, "Sign Up Successful");


                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Something Wrong. Please Try Again");
                        e1.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"User Already Registered");
                }
            }
        });
    }

    public static void loginbuttonaction() {
        MainFrame.LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase dataBase = new DataBase();

                String username = MainFrame.usernameloginusertextfield.getText();
                String password = MainFrame.passwordloginusertextfield.getText();
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@//localhost:1521/pdborcl", "hr", "hr");
                    String query = "select *from Member";
                    Statement s = connection.createStatement();

                    ResultSet resultSet = s.executeQuery(query);
                    boolean found = false;

                    while (resultSet.next()){
                        if(resultSet.getString("USERNAME").equals(username)&&resultSet.getString("PASSWORD").equals(password)){
                            break;
                        }
                    }

                        String query1 = "select *from Person natural join MEMBER";
                        Statement s1 = connection.createStatement();
                        ResultSet resultSet1 = s1.executeQuery(query1);
                        
                        while (resultSet1.next()){
                            if(resultSet1.getString("USERNAME").equals(username)){
                                user = new User(resultSet1.getString("NAME"),
                                        resultSet1.getString("CNIC"),resultSet1.getInt("PHONENO"),
                                        resultSet1.getString("EMAIL"),resultSet1.getString("USERNAME"),resultSet1.getString("USERNAME"),"1234",resultSet1.getInt("WALLETMONEY"));
                                found=true;
                                break;
                            }
                        }
                    if(found){
                        JOptionPane.showMessageDialog(null,"Success");
                        s.close();
                        connection.close();
                        resultSet.close();
                        UserAccountFrame.FrontUserDisplay(user);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Username or Password not Found");
                        s.close();
                        resultSet.close();
                        connection.close();
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }



            }
        });
    }
}


