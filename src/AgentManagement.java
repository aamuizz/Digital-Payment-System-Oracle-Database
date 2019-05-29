import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AgentManagement {
    static JLabel totalbalancelabel, welcomelabel, usernamesendmoneylabel, amountsendmoneylabel, consumernolabel, consumernolabel1, customernolabel,
            creditfirstnamel, creditlastnamel, creditcardtypel, creditcardnol, creditexpirationl, creditsecurityl, creditamountl, hilabel, transactionlabel;
    static JTextField usernamesendmoneyfield, amountsendmoneyfield, consumernofield,
            consumernofield1, customernofield, creditfirstnamef, creditlastnamef, creditcardnof,
            creditsecurityf, creditamountf;
    static JButton sendmoneybutton, Paybutton, Paybutton1, paybuttoninternet, creditbutton,
            logoutbutton;
    static TextArea transactionhistory;

    public static void FrontUserDisplay(Agent loggineduser) {
        MainFrame.mainframe.setVisible(false);
        JFrame agentframe = new JFrame("My Account");
        agentframe.getContentPane().setLayout(new GridLayout());
        agentframe.setLocationRelativeTo(null);
        agentframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        agentframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
        transactionhistory.setText(loggineduser.getTransactionhistory());
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

                    agentframe.setVisible(false);
                    Adminagentloginframe.adminagentmainframe();
                    Adminagentloginframe.accounttypesbox.setSelectedIndex(1);

                } else {
                    //Do Nothing

                }
            }
        });


        agentframe.add(jTabbedPane);
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

        jTabbedPane.add("Send Money to User Account", sendmoneypanel);


        sendmoneybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogbutton = JOptionPane.YES_NO_OPTION;
                int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want" +
                        " to send money to " + usernamesendmoneyfield.getText(), "Confirmation", dialogbutton);
                if (dialogresult == 0) {

                    Double walletmoney = 0.0;
                    ArrayList<Agent> agents = Management.readalldataagent();
                    for (int i = 0; i < agents.size(); i++) {
                        if (agents.get(i).getUsername().toLowerCase().equals(loggineduser.getUsername().toLowerCase())) {
                            walletmoney = agents.get(i).getWalletmoney();
                            break;
                        }
                    }
                    if (Checkings.usernamecheck(usernamesendmoneyfield.getText())) {
                        if (Checkings.isNumeric(amountsendmoneyfield.getText())) {
                            if (Double.parseDouble(amountsendmoneyfield.getText()) > 0) {
                                if (walletmoney >= Double.parseDouble(amountsendmoneyfield.getText())) {
                                    sendmoneymain(usernamesendmoneyfield.getText(),
                                            Double.parseDouble(amountsendmoneyfield.getText()),
                                            loggineduser.getUsername());
                                } else {
                                    JOptionPane.showMessageDialog(null, "You don't have enough money");
                                }
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

        JTextField amountsendmoneyagentfield = new RoundJTextfield("Enter Amount");
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
        jTabbedPane.add("Send Money to Agent Account", sendmoneyagentpanel);


        sendmoneyagentbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code will come here #########################################
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
        agentframe.setVisible(true);
        Paybutton.addActionListener(e -> {
            // Code will come here #########################################
        });
        Paybutton1.addActionListener(e -> {
            // Code will come here #########################################
        });
        paybuttoninternet.addActionListener(e -> {
            // Code will come here #########################################
        });
        depositmoneypanel(jTabbedPane, loggineduser);
    }

    public static void sendmoneymain(String username, double amount, String senderusername) {
        // Code will come here #########################################

    }

    public static void sendmoneytoagentaccount(String username, double amount, String senderusername) {
        // Code will come here #########################################
    }

    public static void writeAllListtoFile(ArrayList<User> users) {
        // Code will come here #########################################
    }

    public static void writeAllListtoFileAgent(ArrayList<Agent> Agents) {
        // Code will come here #########################################
    }


    public static void payutilitybills(String billtype, Agent user) {
        // Code will come here #########################################


    }

    public static void depositmoneypanel(JTabbedPane jTabbedPane, Agent user) {
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
                // Code will come here #########################################
            }
        });
    }

    private static void Creditcardamountdeposit(double amount, Agent user, String creditcardno) {
        // Code will come here #########################################
    }

}
