import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Adminagentloginframe {
    static JFrame adminagentframe;
    static JLabel usernameloginadminlabel, passwordloginadminlabel, accounttypelabel;
    static JTextField usernameloginadmintextfield, passwordloginadmintextfield;
    static JButton LoginButton;
    static JComboBox<String> accounttypesbox;

    public static void adminagentmainframe() {
        adminagentframe = new JFrame("Digital Payment System");
        adminagentframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        adminagentframe.setSize(700, 700);
        adminagentframe.setResizable(false);
        adminagentframe.setLayout(new BorderLayout());
        adminagentframe.setContentPane(new JLabel(new ImageIcon("510022.jpg")));
        adminagentframe.getContentPane().setLayout(null);
        adminagentframe.setLocationRelativeTo(null);
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        adminagentframe.getRootPane().setDefaultButton(LoginButton);
        LoginFrame();
    }

    public static void LoginFrame() {
        ImageIcon frontimg = new ImageIcon("admin1.png");
        JLabel frontimgl = new JLabel(frontimg);
        frontimgl.setBounds(210, 10, 300, 300);
        adminagentframe.add(frontimgl);

        JButton Backbutton;
        Backbutton = new JButton("Go Back");
        Backbutton.setBounds(0, 0, 150, 100);
        Backbutton.setIcon(new ImageIcon("backiconimg1.png"));
        Backbutton.setBorder(null);
        Backbutton.setBorderPainted(false);
        Backbutton.setContentAreaFilled(false);
        Backbutton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        Backbutton.setDoubleBuffered(true);
        Backbutton.setFocusPainted(false);
        Backbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminagentframe.add(Backbutton);
        Backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminagentframe.setVisible(false);
                usernameloginadmintextfield.setText("");
                passwordloginadmintextfield.setText("");
                MainFrame.mainframe.setVisible(true);
            }
        });


        usernameloginadminlabel = new JLabel("Username");
        usernameloginadminlabel.setBounds(140, 300, 100, 100);
        adminagentframe.add(usernameloginadminlabel);
        passwordloginadminlabel = new JLabel("Password");
        passwordloginadminlabel.setBounds(140, 350, 100, 100);
        adminagentframe.add(passwordloginadminlabel);

        accounttypelabel = new JLabel("Account Type");
        accounttypelabel.setBounds(130, 400, 100, 100);
        adminagentframe.add(accounttypelabel);

        //TextField

        usernameloginadmintextfield = new RoundJTextfield("Enter Username");
        usernameloginadmintextfield.setBounds(220, 335, 250, 30);
        adminagentframe.add(usernameloginadmintextfield);

        passwordloginadmintextfield = new RountJPasswordField("Enter Password");
        passwordloginadmintextfield.setBounds(220, 385, 250, 30);
        adminagentframe.add(passwordloginadmintextfield);

        String[] accounttypes = new String[]{"ADMIN", "AGENT"};
        accounttypesbox = new JComboBox<String>(accounttypes);
        accounttypesbox.setBounds(224, 440, 100, 25);
        accounttypesbox.setEnabled(false);
        adminagentframe.add(accounttypesbox);

        LoginButton = new JButton("Login");
        LoginButton.setBounds(230, 490, 200, 50);
        LoginButton.setIcon(new ImageIcon("Loginbuttonmain1.png"));
        LoginButton.setBorder(null);
        LoginButton.setBorderPainted(false);
        LoginButton.setContentAreaFilled(false);
        LoginButton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        LoginButton.setDoubleBuffered(true);
        LoginButton.setFocusPainted(false);
        LoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        adminagentframe.add(LoginButton);
        adminagentframe.setVisible(true);
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (accounttypesbox.getSelectedIndex() == 0) {
                    DataBase adminDataBase = new DataBase();
                    String username = usernameloginadmintextfield.getText().toLowerCase();
                    String password = passwordloginadmintextfield.getText();
                    if (adminDataBase.isAdmin(username, password)) {
                        adminagentframe.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        try {
                            AdminManagement.FrontUserDisplay(username);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Administrator not found");
                } else {
                    DataBase adminDataBase = new DataBase();
                    String username = usernameloginadmintextfield.getText().toLowerCase();
                    String password = passwordloginadmintextfield.getText();

                    if (adminDataBase.isAgent(username, password)) {
                        adminagentframe.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        AgentManagement.FrontUserDisplay(username);
                    } else
                        JOptionPane.showMessageDialog(null, "Agent not found");
                }


            }

        });


    }
}