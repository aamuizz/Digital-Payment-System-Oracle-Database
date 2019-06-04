import javafx.scene.layout.BackgroundImage;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class UserAccountFrame {
    static JLabel totalbalancelabel, welcomelabel, usernamesendmoneylabel, amountsendmoneylabel, consumernolabel, consumernolabel1, customernolabel,
            creditfirstnamel, creditlastnamel, creditcardtypel, creditcardnol, creditexpirationl, creditsecurityl, creditamountl, hilabel, transactionlabel;
    static JTextField usernamesendmoneyfield, amountsendmoneyfield, consumernofield,
            consumernofield1, customernofield, creditfirstnamef, creditlastnamef, creditcardnof,
            creditsecurityf, creditamountf;
    static JButton sendmoneybutton, Paybutton, Paybutton1, paybuttoninternet, creditbutton,
            logoutbutton;
    static TextArea transactionhistory;
    static Connection connection;

    public static void FrontUserDisplay(User loggineduser) throws SQLException {
        MainFrame.mainframe.setVisible(false);
        JFrame userframe = new JFrame("My Account");
        userframe.getContentPane().setLayout(new GridLayout());
        userframe.setLocationRelativeTo(null);
        userframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Color alphaZero = new Color(204, 204, 204, 32);
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.setOpaque(false);
        jTabbedPane.setBackground(alphaZero);
        jTabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            }
        });

        JPanel homepanel = new JPanel();
        ImageIcon image1 = new ImageIcon("hiimage2.png");
        ImageIcon image2 = new ImageIcon("usericon1.png");


        homepanel.setBackground(Color.WHITE);

        homepanel.setLayout(null);

        JPanel sendmoneypanel = new JPanel();
        Font mainfont = new Font("Arial", Font.PLAIN, 20);
        JLabel userlabel = new JLabel(image2);
        userlabel.setBounds(0, 10, 100, 100);
        hilabel = new JLabel(" " + loggineduser.getUsername());
        hilabel.setFont(new Font("ArialBlack", Font.BOLD, 25));
        hilabel.setBounds(95, 38, 1200, 50);
        hilabel.setForeground(Color.BLACK);
        homepanel.add(userlabel);

        JLabel hiimagelabel = new JLabel(image1);
        hiimagelabel.setBounds(0, 100, 100, 100);
        homepanel.add(hiimagelabel);
        welcomelabel = new JLabel(" " + loggineduser.getName());
        welcomelabel.setBounds(95, 105, 1200, 100);
        welcomelabel.setFont(new Font("ArialBlack", Font.BOLD, 25));
        welcomelabel.setForeground(Color.BLACK);

        ImageIcon wallet = new ImageIcon("walleticon1.png");
        JLabel walleticon = new JLabel(wallet);
        walleticon.setBounds(0, 200, 100, 100);
        homepanel.add(walleticon);
        totalbalancelabel = new JLabel(" Balance: " + loggineduser.getWalletmoney() + " PKR");
        totalbalancelabel.setFont(new Font("LiSong Pro", Font.BOLD, 25));
        totalbalancelabel.setForeground(Color.BLACK);
        totalbalancelabel.setBounds(95, 210, 1200, 80);
        homepanel.add(hilabel);
        homepanel.add(totalbalancelabel);
        homepanel.add(welcomelabel);

        transactionlabel = new JLabel("Transaction History");
        transactionlabel.setBounds(5, 280, 1200, 80);
        transactionlabel.setFont(new Font("Arial", Font.BOLD, 22));
        transactionhistory = new TextArea();
        transactionhistoryupdate(loggineduser);

        transactionhistory.setFont(new Font("Arial", Font.ITALIC, 20));
        transactionhistory.setEditable(false);
        transactionhistory.setBounds(0, 350, 1600, 450);
        transactionhistory.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        transactionhistory.setBackground(Color.WHITE);
        transactionhistory.setForeground(Color.BLACK);

        homepanel.add(transactionhistory);
        homepanel.add(transactionlabel);

        logoutbutton = new JButton();
        logoutbutton.setBounds(1500, 0, 100, 100);
        logoutbutton.setIcon(new ImageIcon("logout1.png"));
        logoutbutton.setBorder(null);
        logoutbutton.setBorderPainted(false);
        logoutbutton.setContentAreaFilled(false);
        logoutbutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        logoutbutton.setDoubleBuffered(true);
        logoutbutton.setFocusPainted(false);
        logoutbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homepanel.add(logoutbutton);

        logoutbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userframe.setVisible(false);
                MainFrame.mainframe.setVisible(true);

            }
        });


        userframe.add(jTabbedPane);
        jTabbedPane.add("Home", homepanel);
        ////////////////////////////////////////////////////////
        sendmoneypanel.setLayout(null);
        usernamesendmoneylabel = new JLabel("Username");
        usernamesendmoneylabel.setBounds(20, 0, 100, 100);
        usernamesendmoneylabel.setFont(mainfont);
        sendmoneypanel.add(usernamesendmoneylabel);

        usernamesendmoneyfield = new RoundJTextfield("Enter Sender Username");
        usernamesendmoneyfield.setBounds(120, 37, 300, 30);
        sendmoneypanel.add(usernamesendmoneyfield);

        amountsendmoneylabel = new JLabel("Amount");
        amountsendmoneylabel.setBounds(20, 55, 100, 100);
        amountsendmoneylabel.setFont(mainfont);
        sendmoneypanel.add(amountsendmoneylabel);

        amountsendmoneyfield = new RoundJTextfield("Enter Amount");
        amountsendmoneyfield.setBounds(120, 90, 100, 30);
        sendmoneypanel.add(amountsendmoneyfield);

        sendmoneybutton = new JButton("Send Money");
        sendmoneybutton.setBounds(20, 140, 170, 75);
        sendmoneybutton.setIcon(new ImageIcon("sendmoneybutton1.png"));
        sendmoneybutton.setBorder(null);
        sendmoneybutton.setBorderPainted(false);
        sendmoneybutton.setContentAreaFilled(false);
        sendmoneybutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        sendmoneybutton.setDoubleBuffered(true);
        sendmoneybutton.setFocusPainted(false);
        sendmoneybutton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        sendmoneypanel.add(sendmoneybutton);
        sendmoneypanel.setForeground(Color.WHITE);

        jTabbedPane.add("Send Money", sendmoneypanel);


        sendmoneybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendmoneyuser(loggineduser);
            }

        });
        //////////////////////////////////////////////////////////
        JPanel electricitybill = new JPanel();
        electricitybill.setLayout(null);
        electricitybill.setForeground(Color.WHITE);
        consumernolabel = new JLabel("Consumer no");
        consumernolabel.setBackground(Color.BLACK);
        consumernolabel.setBounds(20, 0, 200, 100);
        consumernolabel.setFont(mainfont);
        consumernofield = new RoundJTextfield("Consumer Number here");
        consumernofield.setBounds(155, 35, 300, 30);
        Paybutton = new JButton("Pay");
        Paybutton.setBounds(20, 60, 150, 100);
        Paybutton.setIcon(new ImageIcon("paybutton1.png"));
        Paybutton.setBorder(null);
        Paybutton.setBorderPainted(false);
        Paybutton.setContentAreaFilled(false);
        Paybutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        Paybutton.setDoubleBuffered(true);
        Paybutton.setFocusPainted(false);
        Paybutton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        electricitybill.add(Paybutton);
        electricitybill.add(consumernolabel);
        electricitybill.add(consumernofield);
        jTabbedPane.add("Electricity Bill Payment", electricitybill);
        //////////////////////////////////////////////////////////

        JPanel gasbill = new JPanel();
        gasbill.setLayout(null);
        consumernolabel1 = new JLabel("Consumer no");
        consumernolabel1.setBackground(Color.BLACK);
        consumernolabel1.setBounds(20, 0, 200, 100);
        consumernolabel1.setFont(mainfont);
        consumernofield1 = new RoundJTextfield("Consumer Number here");
        consumernofield1.setBounds(155, 35, 300, 30);

        Paybutton1 = new JButton("Pay");
        Paybutton1.setBounds(20, 60, 150, 100);
        Paybutton1.setIcon(new ImageIcon("paybutton1.png"));
        Paybutton1.setBorder(null);
        Paybutton1.setBorderPainted(false);
        Paybutton1.setContentAreaFilled(false);
        Paybutton1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        Paybutton1.setDoubleBuffered(true);
        Paybutton1.setFocusPainted(false);
        Paybutton1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gasbill.add(Paybutton1);
        gasbill.add(consumernolabel1);
        gasbill.add(consumernofield1);
        jTabbedPane.add("Gas Bill Payment", gasbill);

        JPanel internetbill = new JPanel();
        internetbill.setLayout(null);
        JLabel internetproviderlabel = new JLabel("ISP");
        internetproviderlabel.setBounds(20, 0, 150, 100);
        internetproviderlabel.setFont(mainfont);
        internetbill.add(internetproviderlabel);
        String[] internetproviders = new String[]{"Nayatel", "Storm Fiber", "PTCL"};
        JComboBox<String> internetbox = new JComboBox<String>(internetproviders);
        internetbox.setBounds(155, 38, 120, 20);
        internetbill.add(internetbox);

        customernolabel = new JLabel("Consumer no");
        customernolabel.setBackground(Color.BLACK);
        customernolabel.setBounds(20, 40, 200, 100);
        customernolabel.setFont(mainfont);
        internetbill.add(customernolabel);

        customernofield = new RoundJTextfield("Consumer Number here");
        customernofield.setBounds(155, 75, 300, 30);
        internetbill.add(customernofield);

        paybuttoninternet = new JButton("Pay");
        paybuttoninternet.setBounds(20, 85, 150, 100);
        paybuttoninternet.setIcon(new ImageIcon("paybutton1.png"));
        paybuttoninternet.setBorder(null);
        paybuttoninternet.setBorderPainted(false);
        paybuttoninternet.setContentAreaFilled(false);
        paybuttoninternet.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        paybuttoninternet.setDoubleBuffered(true);
        paybuttoninternet.setFocusPainted(false);
        paybuttoninternet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        internetbill.add(paybuttoninternet);

        jTabbedPane.add("Internet Bill Payment", internetbill);
        userframe.setVisible(true);
        Paybutton.addActionListener(e -> {
            if(Checkings.isNumeric(consumernofield.getText())) {
                try {
                    payutilitybills("Electricity",loggineduser);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Something Wrong Please Try Again");
            }
        });
        Paybutton1.addActionListener(e -> {
            if(Checkings.isNumeric(consumernofield1.getText())) {
                try {
                    payutilitybills("Gas",loggineduser);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Something Wrong Please Try Again");
            }
        });
        paybuttoninternet.addActionListener(e -> {
            if(Checkings.isNumeric(customernofield.getText())) {
                try {
                    payutilitybills(internetbox.getSelectedItem().toString(),loggineduser);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Something Wrong Please Try Again");
            }
        });
        depositmoneypanel(jTabbedPane, loggineduser);
        queriespanel(jTabbedPane,loggineduser);
    }
    public static void payutilitybills(String billtype, User user) throws SQLException {
        int dialogbutton = JOptionPane.YES_NO_OPTION;
        int range = (2000 - 1000) + 1;
        int randombill = (int) (Math.random() * range) + 100;
        int dialogresult = JOptionPane.showConfirmDialog(null, "Your total bill for the " + billtype + " is " + randombill + "\n" +
                "Are you Sure You want to Pay?", "Bill Payment", dialogbutton);
        if (dialogresult == 0) {
            if (user.getWalletmoney()>=randombill) {
                connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/pdborcl", "hr", "hr");
                user.setWalletmoney(user.getWalletmoney()-randombill);
                totalbalancelabel.setText(" Balance: " + user.getWalletmoney() + " PKR");
                String query3 ="insert into utilitybill (billno, customerid, amount, billtype, username) values \n" +
                        "  (seq_utilitybill.nextval,?,?,?,?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
                if(billtype.contains("Electricity")){
                   preparedStatement2.setString(1,consumernofield.getText());
                }
                else if(billtype.contains("Gas")){
                    preparedStatement2.setString(1,consumernofield1.getText());
                }
                else{
                    preparedStatement2.setString(1,customernofield.getText());
                }
                consumernofield.setText("");
                consumernofield1.setText("");
                customernofield.setText("");
                preparedStatement2.setInt(2,randombill);
                preparedStatement2.setString(3,billtype);
                preparedStatement2.setString(4,user.getUsername());
                preparedStatement2.execute();
                preparedStatement2.close();
                JOptionPane.showMessageDialog(null, "Bill Paid");
                transactionhistoryupdate(user);
                connection.close();

            } else {
                JOptionPane.showMessageDialog(null, "You don't have enough money in your account");
            }
        }
    }

    public static void sendmoneyuser(User loggineduser){
        if(loggineduser.getWalletmoney()>=Integer.parseInt(amountsendmoneyfield.getText().toString())&&Integer.parseInt(amountsendmoneyfield.getText())>0){
            int dialogbutton = JOptionPane.YES_NO_OPTION;
            int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want" +
                    " to send money to " + usernamesendmoneyfield.getText(), "Confirmation", dialogbutton);
            if (dialogresult == 0) {
                try {
                    DataBase dataBase = new DataBase();
                    if(dataBase.isMemberExist(usernamesendmoneyfield.getText())) {

                        connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/pdborcl", "hr", "hr");
                        String query = "Update MEMBER set walletmoney=? where username =?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, (int) loggineduser.getWalletmoney() - Integer.parseInt(amountsendmoneyfield.getText().toString()));
                        preparedStatement.setString(2, loggineduser.getUsername());
                        preparedStatement.execute();
                        preparedStatement.close();
                        String query2 = "Update MEMBER set walletmoney=walletmoney+? where username =?";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
                        preparedStatement1.setInt(1, Integer.parseInt(amountsendmoneyfield.getText()));
                        preparedStatement1.setString(2, usernamesendmoneyfield.getText());
                        preparedStatement1.execute();
                        preparedStatement1.close();
                        loggineduser.setWalletmoney(loggineduser.getWalletmoney() - Integer.parseInt(amountsendmoneyfield.getText().toString()));
                        totalbalancelabel.setText(" Balance: " + loggineduser.getWalletmoney() + " PKR");
                        String query3 = "insert into transaction(transactionid, senderusername, receiverusername, DATEANDTIME,Amount) VALUES (seq_transid.nextval,?,?,SYSDATE,?)";
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
                        preparedStatement2.setString(1, loggineduser.getUsername());
                        preparedStatement2.setString(2, usernamesendmoneyfield.getText());
                        preparedStatement2.setInt(3, Integer.parseInt(amountsendmoneyfield.getText()));
                        preparedStatement2.execute();
                        preparedStatement2.close();
                        transactionhistoryupdate(loggineduser);
                        connection.close();
                        JOptionPane.showMessageDialog(null, "Amount of Rs " + amountsendmoneyfield.getText() + " Has Sent to " + usernamesendmoneyfield.getText());
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"User doesn't exists");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Something Wrong. Please Try Again");
        }
    }



    public static void depositmoneypanel(JTabbedPane jTabbedPane, User user) {
        JPanel depositmoneypanel = new JPanel();
        Font font = new Font("Arial", Font.PLAIN, 20);
        depositmoneypanel.setLayout(null);
        JLabel frontimg = new JLabel(new ImageIcon("safecheckout1.png"));
        frontimg.setBounds(500, 10, 600, 100);
        depositmoneypanel.add(frontimg);


        creditfirstnamel = new JLabel("First Name");
        creditfirstnamel.setFont(font);
        creditfirstnamel.setBounds(400, 100, 100, 100);
        depositmoneypanel.add(creditfirstnamel);
        creditfirstnamef = new RoundJTextfield("Enter First Name");
        creditfirstnamef.setBounds(510, 135, 300, 30);
        depositmoneypanel.add(creditfirstnamef);

        creditlastnamel = new JLabel("Last Name");
        creditlastnamel.setBounds(400, 150, 100, 100);
        creditlastnamel.setFont(font);
        depositmoneypanel.add(creditlastnamel);
        creditlastnamef = new RoundJTextfield("Enter Last Name");
        creditlastnamef.setBounds(510, 185, 300, 30);
        depositmoneypanel.add(creditlastnamef);

        creditcardtypel = new JLabel("Card Type");
        creditcardtypel.setBounds(400, 200, 100, 100);
        creditcardtypel.setFont(font);
        depositmoneypanel.add(creditcardtypel);

        String[] cardtypes = new String[]{"VISA", "MasterCard", "Discover", "American Express"};
        JComboBox<String> cardtypesbox = new JComboBox<String>(cardtypes);
        cardtypesbox.setBounds(510, 235, 100, 30);
        depositmoneypanel.add(cardtypesbox);

        creditcardnol = new JLabel("Card No");
        creditcardnol.setBounds(400, 250, 100, 100);
        creditcardnol.setFont(font);
        depositmoneypanel.add(creditcardnol);

        creditcardnof = new RoundJTextfield("Enter Card No");
        creditcardnof.setBounds(510, 285, 300, 30);
        depositmoneypanel.add(creditcardnof);

        ImageIcon credit1 = new ImageIcon("creditcardimg1.png");
        JLabel creditcardimg = new JLabel(credit1);
        creditcardimg.setBounds(800, 250, 250, 100);
        depositmoneypanel.add(creditcardimg);

        creditexpirationl = new JLabel("Expiration");
        creditexpirationl.setBounds(400, 300, 120, 100);
        creditexpirationl.setFont(font);
        depositmoneypanel.add(creditexpirationl);
        String[] date1 = new String[]{"01", "02", "03", "04", "05", "06", "07"
                , "08", "09", "10", "11", "12"};
        JComboBox<String> carddate1 = new JComboBox<String>(date1);
        carddate1.setBounds(510, 335, 50, 30);
        depositmoneypanel.add(carddate1);

        String[] date2 = new String[]{"2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025",
                "2026"};
        JComboBox<String> carddate2 = new JComboBox<String>(date2);
        carddate2.setBounds(570, 335, 70, 30);
        depositmoneypanel.add(carddate2);

        creditsecurityl = new JLabel("Card Security Code");
        creditsecurityl.setBounds(320, 350, 200, 100);
        creditsecurityl.setFont(font);
        depositmoneypanel.add(creditsecurityl);

        creditsecurityf = new RoundJTextfield("Enter Security Code");
        creditsecurityf.setBounds(510, 385, 300, 30);
        depositmoneypanel.add(creditsecurityf);

        ImageIcon credits = new ImageIcon("CVV1.png");
        JLabel creditsimg = new JLabel(credits);
        creditsimg.setBounds(780, 350, 250, 100);
        depositmoneypanel.add(creditsimg);


        creditamountl = new JLabel("Amount");
        creditamountl.setBounds(400, 400, 100, 100);
        creditamountl.setFont(font);
        depositmoneypanel.add(creditamountl);

        creditamountf = new RoundJTextfield("Enter Amount");
        creditamountf.setBounds(510, 435, 300, 30);
        depositmoneypanel.add(creditamountf);

        creditbutton = new JButton("Deposit");
        creditbutton.setBounds(505, 475, 200, 100);
        creditbutton.setIcon(new ImageIcon("depositbutton1.png"));
        creditbutton.setBorder(null);
        creditbutton.setBorderPainted(false);
        creditbutton.setContentAreaFilled(false);
        creditbutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        creditbutton.setDoubleBuffered(true);
        creditbutton.setFocusPainted(false);
        creditbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        depositmoneypanel.add(creditbutton);
        depositmoneypanel.setVisible(true);
        jTabbedPane.add("Deposit Money", depositmoneypanel);
        creditbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/pdborcl", "hr", "hr");
                    String query = "insert into creditcard (cardno, expirydate, cardtype, cvv, username,amount) VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,creditcardnof.getText());
                    preparedStatement.setString(2,carddate1.getSelectedItem().toString()+"-"+carddate2.getSelectedItem().toString());
                    preparedStatement.setString(3,cardtypesbox.getSelectedItem().toString());
                    preparedStatement.setInt(4,Integer.parseInt(creditsecurityf.getText()));
                    preparedStatement.setString(5,user.getUsername());
                    preparedStatement.setInt(6,Integer.parseInt(creditamountf.getText()));
                    preparedStatement.execute();
                    preparedStatement.close();
                    user.setWalletmoney(user.getWalletmoney()+Integer.parseInt(creditamountf.getText()));
                    totalbalancelabel.setText(" Balance: " + user.getWalletmoney() + " PKR");
                    String query2= "Update MEMBER set walletmoney=walletmoney+? where username =?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
                    preparedStatement1.setInt(1,Integer.parseInt(creditamountf.getText()));
                    preparedStatement1.setString(2,user.getUsername());
                    preparedStatement1.execute();
                    preparedStatement1.close();
                    String query3 ="insert into transaction(transactionid, senderusername, receiverusername, DATEANDTIME,Amount) VALUES (seq_transid.nextval,?,?,SYSDATE,?)";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
                    preparedStatement2.setString(1,"Credit/Debit Card "+creditcardnof.getText().substring(0,2)+"X-XXXX-XXXX-XXXX");
                    preparedStatement2.setString(2,user.getUsername());
                    preparedStatement2.setInt(3,Integer.parseInt(creditamountf.getText()));
                    preparedStatement2.execute();
                    preparedStatement2.close();
                    transactionhistoryupdate(user);
                    JOptionPane.showMessageDialog(null,"Amount Deposited into your Account");
                    connection.close();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });
    }


    public static void transactionhistoryupdate(User loggineduser) throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@//localhost:1521/pdborcl", "hr", "hr");
        String query1 = "select * FROM TRANSACTION order by TRANSACTIONID";
        Statement s1 = connection.createStatement();
        ResultSet resultSet2 = s1.executeQuery(query1);
        String transhistory = "";


        while (resultSet2.next()){
            if(resultSet2.getString("SENDERUSERNAME").equals(loggineduser.getUsername())){
                transhistory+="\n"+resultSet2.getString("DATEANDTIME")+"    You Have Sent Rs "+resultSet2.getString("AMOUNT")+" to "+
                        resultSet2.getString("RECEIVERUSERNAME");
            }
            else if(resultSet2.getString("RECEIVERUSERNAME").equals(loggineduser.getUsername())){
                transhistory+="\n"+resultSet2.getString("DATEANDTIME")+"    You Have Received Rs "+resultSet2.getString("AMOUNT")+" FROM "+
                        resultSet2.getString("SENDERUSERNAME");

            }
        }
        transhistory+="\n____________________________________________________\n";
        String query2 = "select * FROM UTILITYBILL order by BILLNO";
        Statement s2 = connection.createStatement();
        ResultSet resultSet3 = s2.executeQuery(query2);
        while (resultSet3.next()){
            if(resultSet3.getString("USERNAME").equals(loggineduser.getUsername())){
             transhistory+="\nYou Have Paid "+resultSet3.getString("BILLTYPE")+" Bill "+ "of "+ "Rs "+resultSet3.getString("AMOUNT")+ " of"+" Customer ID "+resultSet3.getString("CUSTOMERID");

            }
        }
        s1.close();
        resultSet2.close();
        connection.close();
        transactionhistory.setText(transhistory);

    }
    public static void queriespanel(JTabbedPane jTabbedPane,User loggineduser){
        JPanel queriespanel = new JPanel();
        queriespanel.setLayout(null);
        queriespanel.setForeground(Color.WHITE);
        JLabel querylabel = new JLabel("Enter your Query");
        querylabel.setBackground(Color.BLACK);
        querylabel.setBounds(20, 0, 200, 100);
        RoundJTextfield queryfield = new RoundJTextfield("Enter your Query");
        queryfield.setBounds(155, 35, 800, 30);
        JButton submitbutton = new JButton("Submit");
        submitbutton.setBounds(20, 60, 180, 100);
        submitbutton.setIcon(new ImageIcon("submitbutton2.png"));
        submitbutton.setBorder(null);
        submitbutton.setBorderPainted(false);
        submitbutton.setContentAreaFilled(false);
        submitbutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        submitbutton.setDoubleBuffered(true);
        submitbutton.setFocusPainted(false);
        submitbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        queriespanel.add(querylabel);
        queriespanel.add(queryfield);
        queriespanel.add(submitbutton);
        jTabbedPane.add("Queries",queriespanel);
        submitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase dataBase = new DataBase();
                try {
                    dataBase.actionqueryinsert(queryfield.getText(),loggineduser.getUsername());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                queryfield.setText("");
            }
        });
    }
}



