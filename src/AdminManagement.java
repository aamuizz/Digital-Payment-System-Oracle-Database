import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdminManagement {
    static JLabel totalbalancelabel, welcomelabel, usernamesendmoneylabel, amountsendmoneylabel, consumernolabel, consumernolabel1, customernolabel,
            creditfirstnamel, creditlastnamel, creditcardtypel, creditcardnol, creditexpirationl, creditsecurityl, creditamountl, hilabel, transactionlabel;
    static JTextField usernamesendmoneyfield, amountsendmoneyfield, consumernofield,
            consumernofield1, customernofield, nameagenttextfield, cnicagenttextfield, phonenumberagenttextfield, usernameagenttextfield,
            passwordagenttextfield, amountsendmoneyagentfield;
    static JButton sendmoneybutton, Paybutton, Paybutton1, paybuttoninternet, creditbutton,
            logoutbutton;
    static TextArea transactionhistory;
    static String username;

    public static void FrontUserDisplay(String uname) throws SQLException {
        DataBase dataBase = new DataBase();
        username = uname;
        String name = dataBase.getName(username);

        Adminagentloginframe.adminagentframe.setVisible(false);
        JFrame adminframe = new JFrame("My Account");
        adminframe.getContentPane().setLayout(new GridLayout());
        adminframe.setLocationRelativeTo(null);
        adminframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        adminframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
        homepanel.setLayout(new BorderLayout());

        homepanel.setLayout(null);

        JPanel sendmoneypanel = new JPanel();
        Font mainfont = new Font("Arial", Font.PLAIN, 20);
        JLabel userlabel = new JLabel(image2);
        userlabel.setBounds(0, 10, 100, 100);
        hilabel = new JLabel(" " + username);
        hilabel.setFont(new Font("ArialBlack", Font.BOLD, 25));
        hilabel.setBounds(95, 38, 1200, 50);
        hilabel.setForeground(Color.BLACK);
        homepanel.add(userlabel);

        JLabel hiimagelabel = new JLabel(image1);
        hiimagelabel.setBounds(0, 100, 100, 100);
        homepanel.add(hiimagelabel);
        welcomelabel = new JLabel(" " + name);
        welcomelabel.setBounds(95, 105, 1200, 100);
        welcomelabel.setFont(new Font("ArialBlack", Font.BOLD, 25));
        welcomelabel.setForeground(Color.BLACK);

        ImageIcon wallet = new ImageIcon("walleticon1.png");
        JLabel walleticon = new JLabel(wallet);
        walleticon.setBounds(0, 200, 100, 100);
        homepanel.add(walleticon);
        totalbalancelabel = new JLabel(" Balance: " + "Unlimited" + " PKR");
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
        transactionhistory.setText(dataBase.getTransactionHistory(username));
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
                int dialogbutton = JOptionPane.YES_NO_OPTION;
                int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out? ", "Confirmation", dialogbutton);
                if (dialogresult == 0) {

                    adminframe.setVisible(false);
                    Adminagentloginframe.adminagentframe.setVisible(true);
                    Adminagentloginframe.passwordloginadmintextfield.setText("");
                    Adminagentloginframe.usernameloginadmintextfield.setText("");
                } else {

                }
            }
        });


        adminframe.add(jTabbedPane);
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
        sendmoneypanel.setForeground(Color.WHITE);
        sendmoneypanel.add(sendmoneybutton);
        jTabbedPane.add("Add Money to User Account", sendmoneypanel);


        sendmoneybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogbutton = JOptionPane.YES_NO_OPTION;
                int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want" +
                        " to send money to " + usernamesendmoneyfield.getText(), "Confirmation", dialogbutton);
                if (dialogresult == 0) {

                    if (Checkings.usernamecheck(usernamesendmoneyfield.getText())) {
                        if (Checkings.isNumeric(amountsendmoneyfield.getText())) {
                            if (Double.parseDouble(amountsendmoneyfield.getText()) > 0) {
                                sendmoneymain(usernamesendmoneyfield.getText(),
                                        Integer.parseInt(amountsendmoneyfield.getText()),
                                        username);

                            } else {
                                JOptionPane.showMessageDialog(null, "Amount can't be less than or equal to zero");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Amount must be in numbers");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username cannot contain spaces");
                    }
                } else {
                    //Do Nothing
                }
            }
        });
        //////////////////////////////////////////////////////////
        JPanel sendmoneyagentpanel = new JPanel();
        sendmoneyagentpanel.setLayout(null);
        JLabel agentnamesendmoneylabel = new JLabel("Username");
        agentnamesendmoneylabel.setBounds(20, 0, 100, 100);
        agentnamesendmoneylabel.setFont(mainfont);
        sendmoneyagentpanel.add(agentnamesendmoneylabel);

        JTextField agentnamesendmoneyfield = new RoundJTextfield("Enter Sender Username");
        agentnamesendmoneyfield.setBounds(120, 37, 300, 30);
        sendmoneyagentpanel.add(agentnamesendmoneyfield);

        JLabel amountsendmoneyagentlabel = new JLabel("Amount");
        amountsendmoneyagentlabel.setBounds(20, 55, 100, 100);
        amountsendmoneyagentlabel.setFont(mainfont);
        sendmoneyagentpanel.add(amountsendmoneyagentlabel);

        amountsendmoneyagentfield = new RoundJTextfield("Enter Amount");
        amountsendmoneyagentfield.setBounds(120, 90, 100, 30);
        sendmoneyagentpanel.add(amountsendmoneyagentfield);

        JButton sendmoneyagentbutton = new JButton("Send Money");
        sendmoneyagentbutton.setBounds(20, 140, 170, 75);
        sendmoneyagentbutton.setIcon(new ImageIcon("sendmoneybutton1.png"));
        sendmoneyagentbutton.setBorder(null);
        sendmoneyagentbutton.setBorderPainted(false);
        sendmoneyagentbutton.setContentAreaFilled(false);
        sendmoneyagentbutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        sendmoneyagentbutton.setDoubleBuffered(true);
        sendmoneyagentbutton.setFocusPainted(false);
        sendmoneyagentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendmoneyagentpanel.setForeground(Color.WHITE);
        sendmoneyagentpanel.add(sendmoneyagentbutton);
        jTabbedPane.add("Add Money to Agent Account", sendmoneyagentpanel);


        sendmoneyagentbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogbutton = JOptionPane.YES_NO_OPTION;
                int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want" +
                        " to send money to " + agentnamesendmoneyfield.getText(), "Confirmation", dialogbutton);
                if (dialogresult == 0) {

                    if (Checkings.usernamecheck(agentnamesendmoneyfield.getText())) {
                        if (Checkings.isNumeric(amountsendmoneyagentfield.getText())) {
                            if (Double.parseDouble(amountsendmoneyagentfield.getText()) > 0) {
                                addmoneytoagentaccount(agentnamesendmoneyfield.getText(),
                                        Integer.parseInt(amountsendmoneyagentfield.getText()),
                                        uname);

                            } else {
                                JOptionPane.showMessageDialog(null, "Amount can't be less than or equal to zero");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Amount must be in numbers");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username cannot contain spaces");
                    }
                } else {
                    //Do Nothing
                }
            }
        });
        /////////////////////////////////////////////////////////
        JPanel addagent = new JPanel();
        addagent.setLayout(null);
        JLabel agentreg = new JLabel(new ImageIcon("Registernow1.png"));
        agentreg.setBounds(400, 10, 400, 150);
        addagent.add(agentreg);

        JLabel nameagentlabel = new JLabel("Name");
        nameagentlabel.setBounds(400, 140, 100, 100);
        addagent.add(nameagentlabel);
        JLabel cnicagentlabel = new JLabel("CNIC");
        cnicagentlabel.setBounds(400, 190, 100, 100);
        addagent.add(cnicagentlabel);
        JLabel phonenumberagentlabel = new JLabel("Phone No");
        phonenumberagentlabel.setBounds(400, 240, 100, 100);
        addagent.add(phonenumberagentlabel);

        //Main Login
        JLabel usernameagentlabel = new JLabel("Username");
        usernameagentlabel.setBounds(400, 290, 100, 100);
        addagent.add(usernameagentlabel);
        JLabel passwordagentlabel = new JLabel("Password");
        passwordagentlabel.setBounds(400, 340, 100, 100);
        addagent.add(passwordagentlabel);
//////////////////////////////////////////////////////////
        //TextField for Person

        nameagenttextfield = new RoundJTextfield("Enter Name");
        nameagenttextfield.setBounds(510, 175, 300, 30);
        addagent.add(nameagenttextfield);

        cnicagenttextfield = new RoundJTextfield("Enter CNIC");
        cnicagenttextfield.setBounds(510, 225, 300, 30);
        addagent.add(cnicagenttextfield);


        phonenumberagenttextfield = new RoundJTextfield("Enter Phonenumber");
        phonenumberagenttextfield.setBounds(510, 275, 300, 30);
        addagent.add(phonenumberagenttextfield);

        //Textfield for agent
        usernameagenttextfield = new RoundJTextfield("Enter Username");
        usernameagenttextfield.setBounds(510, 325, 300, 30);
        addagent.add(usernameagenttextfield);
        passwordagenttextfield = new RountJPasswordField("Enter Password");
        passwordagenttextfield.setBounds(510, 375, 300, 30);
        addagent.add(passwordagenttextfield);

        ///Register Button
        JButton RegisterButton = new JButton("Sign Up");
        RegisterButton.setBounds(505, 440, 200, 35);
        RegisterButton.setIcon(new ImageIcon("RegisterButtonmain.png"));
        RegisterButton.setBorder(null);
        RegisterButton.setBorderPainted(false);
        RegisterButton.setContentAreaFilled(false);
        RegisterButton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        RegisterButton.setDoubleBuffered(true);
        RegisterButton.setFocusPainted(false);
        RegisterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addagent.add(RegisterButton);
        jTabbedPane.add("Register Agent", addagent);
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = AdminManagement.usernameagenttextfield.getText();
                if (!dataBase.isUserExist(username)) {
                    JOptionPane.showMessageDialog(null, "Registration Successfull");
                    RegistrationofAgent();
                } else {
                    JOptionPane.showMessageDialog(null, "Username Already taken");
                    usernameagenttextfield.setText("");
                }
            }

        });
///////////////////////////////////////////////////////////////
        JPanel deleteuser = new JPanel();
        deleteuser.setLayout(null);
        deleteuser.setForeground(Color.WHITE);
        JLabel deleteuserlabel = new JLabel("Username");
        deleteuserlabel.setBackground(Color.BLACK);
        deleteuserlabel.setBounds(20, 0, 200, 100);
        deleteuserlabel.setFont(mainfont);
        JTextField deleteuserfield = new RoundJTextfield("Enter Username");
        deleteuserfield.setBounds(155, 35, 300, 30);

        JButton Deletebutton = new JButton("Delete");
        Deletebutton.setBounds(0, 80, 200, 50);
        Deletebutton.setIcon(new ImageIcon("deleteuser11.png"));
        Deletebutton.setBorder(null);
        Deletebutton.setBorderPainted(false);
        Deletebutton.setContentAreaFilled(false);
        Deletebutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        Deletebutton.setDoubleBuffered(true);
        Deletebutton.setFocusPainted(false);
        Deletebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        deleteuser.add(Deletebutton);
        deleteuser.add(deleteuserlabel);
        deleteuser.add(deleteuserfield);
        jTabbedPane.add("Delete User", deleteuser);
        Deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Checkings.usernamecheck(deleteuserfield.getText())) {
                    deleteusermethod(deleteuserfield.getText());
                }

            }
        });

        JPanel deleteagent = new JPanel();
        deleteagent.setLayout(null);
        deleteagent.setForeground(Color.WHITE);
        JLabel deleteagentlabel = new JLabel("Username");
        deleteagentlabel.setBackground(Color.BLACK);
        deleteagentlabel.setBounds(20, 0, 200, 100);
        deleteagentlabel.setFont(mainfont);
        JTextField deleteagentfield = new RoundJTextfield("Enter Username");
        deleteagentfield.setBounds(155, 35, 300, 30);

        JButton Deleteagentbutton = new JButton("Delete");
        Deleteagentbutton.setBounds(0, 80, 200, 50);
        Deleteagentbutton.setIcon(new ImageIcon("deleteagent11.png"));
        Deleteagentbutton.setBorder(null);
        Deleteagentbutton.setBorderPainted(false);
        Deleteagentbutton.setContentAreaFilled(false);
        Deleteagentbutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        Deleteagentbutton.setDoubleBuffered(true);
        Deleteagentbutton.setFocusPainted(false);
        Deleteagentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        deleteagent.add(Deleteagentbutton);
        deleteagent.add(deleteagentlabel);
        deleteagent.add(deleteagentfield);
        jTabbedPane.add("Delete Agent", deleteagent);
        Deleteagentbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Checkings.usernamecheck(deleteagentfield.getText())) {
                    deleteagentmethod(deleteagentfield.getText());
                }

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
        adminframe.setVisible(true);
        Paybutton.addActionListener(e -> {
            if (Checkings.isNumeric(consumernofield.getText())) {
                payutilitybills("Electricity",consumernofield.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Something Wrong Please Try Again");
            }
        });
        Paybutton1.addActionListener(e -> {
            if (Checkings.isNumeric(consumernofield1.getText())) {
                payutilitybills("Gas",consumernofield1.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Something Wrong Please Try Again");
            }
        });
        paybuttoninternet.addActionListener(e -> {
            String balance = totalbalancelabel.getText();
            String main = balance.substring(0, balance.length() - 5);
            if (Checkings.isNumeric(customernofield.getText())) {
                payutilitybills(internetbox.getSelectedItem().toString(),customernofield.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Something Wrong Please Try Again");
            }
        });
        viewQuery(jTabbedPane);
        userview(jTabbedPane);
    }

    public static void deleteusermethod(String username) {
        DataBase dataBase = new DataBase();
        if (dataBase.isUserExist(username)) {
            dataBase.deleteUser(username);
            JOptionPane.showMessageDialog(null, "User removed successfully");
        } else JOptionPane.showMessageDialog(null, "User doesnot exist");
    }
    public static void userview(JTabbedPane jTabbedPane) throws SQLException {
        JPanel querypanel = new JPanel();
        querypanel.setLayout(null);
        JLabel querylabel = new JLabel("Queries");
        querylabel.setBounds(5, 5, 250, 50);
        querylabel.setFont(new Font("Arial", Font.BOLD, 22));
        TextArea queryhistory = new TextArea();
        DataBase dataBase = new DataBase();
        queryhistory.setText(dataBase.getuserhistory());
        queryhistory.setFont(new Font("Arial", Font.ITALIC, 20));
        queryhistory.setEditable(false);
        queryhistory.setBounds(0, 60, 1600, 800);
        queryhistory.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        queryhistory.setBackground(Color.WHITE);
        queryhistory.setForeground(Color.BLACK);
        querypanel.add(querylabel);
        querypanel.add(queryhistory);
        jTabbedPane.add("User List",querypanel);

    }

    public static void deleteagentmethod(String username) {
        DataBase dataBase = new DataBase();
        if (dataBase.isUserExist(username)) {
            dataBase.deleteUser(username);
            JOptionPane.showMessageDialog(null, "Agent removed successfully");
        } else JOptionPane.showMessageDialog(null, "Agent doesnot exist");
    }

    public static void sendmoneymain(String username, int amount, String senderusername) {
        DataBase dataBase = new DataBase();
        if (dataBase.isUserExist(username)) {
            dataBase.updateMoney(username, amount,0);
            DataBase dataBase1 = new DataBase();
            dataBase1.addTransaction(senderusername,username,amount);
            DataBase dataBase2 = new DataBase();
            transactionhistory.setText(dataBase2.getTransactionHistory(senderusername));
            JOptionPane.showMessageDialog(null, "Amount of " + amount + " has send successfully to " + username);
        } else JOptionPane.showMessageDialog(null, "The User you want to send money not found");
    }

    public static void payutilitybills(String billtype,String consumerno) {
        int dialogbutton = JOptionPane.YES_NO_OPTION;
        int range = (20 - 10) + 1;
        int randombill = (int) (Math.random() * range) + 500;
        int dialogresult = JOptionPane.showConfirmDialog(null, "Your total bill for the " + billtype + " is " + randombill + "\n" +
                "Are you Sure You want to Pay?", "Bill Payment", dialogbutton);
        if (dialogresult == 0) {
            DataBase dataBase = new DataBase();
            dataBase.payBill(username,randombill,billtype,consumerno);
        } else {
            JOptionPane.showMessageDialog(null, "Bill Not Paid");
        }
    }

    public static void RegistrationofAgent() {
        String name = nameagenttextfield.getText();
        String cnic = cnicagenttextfield.getText();
        String password = passwordagenttextfield.getText();
        int phone_no = Integer.parseInt(phonenumberagenttextfield.getText());
        String username = usernameagenttextfield.getText();

        DataBase dataBase = new DataBase();
        dataBase.registerAgent(username, name, username + "@gmail.com", phone_no, cnic, password);
    }

    public static void addmoneytoagentaccount(String username, int amount, String senderusername) {
        DataBase dataBase = new DataBase();
        if (dataBase.isAgent(username)) {
            dataBase.updateMoney(username, amount,0);
            dataBase.addTransaction(senderusername,username,amount);
            transactionhistory.setText(dataBase.getTransactionHistory(senderusername));
            JOptionPane.showMessageDialog(null, "Amount of " + amount + " has send successfully to " + username);
        } else JOptionPane.showMessageDialog(null, "The User you want to send money not found");
    }
    public static void viewQuery(JTabbedPane jTabbedPane) throws SQLException {
        JPanel querypanel = new JPanel();
        querypanel.setLayout(null);
        JLabel querylabel = new JLabel("Queries");
        querylabel.setBounds(5, 5, 250, 50);
        querylabel.setFont(new Font("Arial", Font.BOLD, 22));
        TextArea queryhistory = new TextArea();
        DataBase dataBase = new DataBase();
        queryhistory.setText(dataBase.getQuerieshistory());
        queryhistory.setFont(new Font("Arial", Font.ITALIC, 20));
        queryhistory.setEditable(false);
        queryhistory.setBounds(0, 60, 1600, 800);
        queryhistory.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        queryhistory.setBackground(Color.WHITE);
        queryhistory.setForeground(Color.BLACK);
        querypanel.add(querylabel);
        querypanel.add(queryhistory);
        jTabbedPane.add("View Queries",querypanel);
    }

}
