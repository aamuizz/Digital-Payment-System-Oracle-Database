import sun.rmi.runtime.Log;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    static JFrame mainframe;
    static JLabel usernameuserlabel, passworduserlabel, nameuserlabel, cnicuserlabel, phonenumberuserlabel, usernameloginuserlabel, passwordloginuserlabel,
            Registerdummy;
    static JTextField usernameusertextfield,
            nameusertextfield, cnicusertextfield, phonenumberusertextfield, usernameloginusertextfield;
    static JPasswordField passwordloginusertextfield, passwordusertextfield;
    static JButton RegisterButton, LoginButton;
    static JButton Administratorlogin, AgentLogin;
    static JPanel panel1, panel2;

    MainFrame() {
    }

    public static void MainFrame() {
        mainframe = new JFrame("Digital Payment System");
        mainframe.setLocationRelativeTo(null);
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainframe.setLayout(new BorderLayout());
        mainframe.setContentPane(new JLabel(new ImageIcon("5100222.png")));
        mainframe.getContentPane().setLayout(null);

    }

    public static void FrontRegisterDisplay() {
        //Front Interface login of User
        ///Personclass
        Registerdummy = new JLabel("Register Now it's Free");
        Registerdummy.setBounds(1100, 80, 300, 100);
        Registerdummy.setFont(new Font("Helvetica", Font.ITALIC, 25));
        Registerdummy.setBackground(Color.white);
        mainframe.add(Registerdummy);

        nameuserlabel = new JLabel("Name");
        nameuserlabel.setBounds(1100, 140, 100, 100);
        mainframe.add(nameuserlabel);
        cnicuserlabel = new JLabel("CNIC");
        cnicuserlabel.setBounds(1100, 190, 100, 100);
        mainframe.add(cnicuserlabel);
        phonenumberuserlabel = new JLabel("Phone No");
        phonenumberuserlabel.setBounds(1100, 240, 100, 100);
        mainframe.add(phonenumberuserlabel);

        //Main Login
        usernameuserlabel = new JLabel("Username");
        usernameuserlabel.setBounds(1100, 290, 100, 100);
        mainframe.add(usernameuserlabel);
        passworduserlabel = new JLabel("Password");
        passworduserlabel.setBounds(1100, 340, 100, 100);
        mainframe.add(passworduserlabel);
        //////////////////////////////////////////////////////////
        //TextField for Person
        nameusertextfield = new RoundJTextfield("Enter Name");
        nameusertextfield.setBounds(1180, 175, 300, 30);
        mainframe.add(nameusertextfield);

        cnicusertextfield = new RoundJTextfield("Enter CNIC");
        cnicusertextfield.setBounds(1180, 225, 300, 30);
        mainframe.add(cnicusertextfield);


        phonenumberusertextfield = new RoundJTextfield("Enter Phonenumber");
        phonenumberusertextfield.setBounds(1180, 275, 300, 30);
        mainframe.add(phonenumberusertextfield);

        //Textfield for user
        usernameusertextfield = new RoundJTextfield("Enter Username");
        usernameusertextfield.setBounds(1180, 325, 300, 30);
        mainframe.add(usernameusertextfield);
        passwordusertextfield = new RountJPasswordField("Enter Password");
        passwordusertextfield.setBounds(1180, 375, 300, 30);
        mainframe.add(passwordusertextfield);

        ///Register Button
        RegisterButton = new JButton("Sign Up");
        RegisterButton.setBounds(1180, 440, 200, 35);
        RegisterButton.setIcon(new ImageIcon("RegisterButtonmain.png"));
        RegisterButton.setBorder(null);
        RegisterButton.setBorderPainted(false);
        RegisterButton.setContentAreaFilled(false);
        RegisterButton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        RegisterButton.setDoubleBuffered(true);
        RegisterButton.setFocusPainted(false);
        RegisterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        mainframe.getContentPane().add(RegisterButton);

        BottomDisplay();

        mainframe.setVisible(true);
        Management.registerbuttonuserAction();


    }

    public static void FrontLoginDisplay() {
        usernameloginuserlabel = new JLabel("Username");
        usernameloginuserlabel.setBounds(700, 10, 100, 100);
        mainframe.add(usernameloginuserlabel);
        passwordloginuserlabel = new JLabel("Password");
        passwordloginuserlabel.setBounds(1050, 10, 100, 100);
        mainframe.add(passwordloginuserlabel);
        //TextField

        usernameloginusertextfield = new RoundJTextfield("Enter Username");
        usernameloginusertextfield.setBounds(770, 45, 250, 30);
        mainframe.add(usernameloginusertextfield);

        passwordloginusertextfield = new RountJPasswordField("Enter Password");
        passwordloginusertextfield.setBounds(1120, 45, 250, 30);
        mainframe.add(passwordloginusertextfield);

        //Log in Button
        LoginButton = new JButton("Login");
        LoginButton.setBounds(1370, 35, 200, 50);
        LoginButton.setIcon(new ImageIcon("Loginbuttonmain1.png"));
        LoginButton.setBorder(null);
        LoginButton.setBorderPainted(false);
        LoginButton.setContentAreaFilled(false);
        LoginButton.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        LoginButton.setDoubleBuffered(true);
        LoginButton.setFocusPainted(false);
        LoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        mainframe.add(LoginButton);
        Management.loginbuttonaction();


    }

    public static void BottomDisplay() {
        Administratorlogin = new JButton("Administrator Login");
        Administratorlogin.setBounds(1100, 510, 250, 80);
        Administratorlogin.setIcon(new ImageIcon("Adminloginmain1.png"));
        Administratorlogin.setBorder(null);
        Administratorlogin.setBorderPainted(false);
        Administratorlogin.setContentAreaFilled(false);
        Administratorlogin.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        Administratorlogin.setDoubleBuffered(true);
        Administratorlogin.setFocusPainted(false);
        Administratorlogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Administratorlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.setVisible(false);
                Adminagentloginframe.adminagentmainframe();
                Adminagentloginframe.accounttypesbox.setSelectedIndex(0);
            }
        });

        AgentLogin = new JButton("Agent Login");
        AgentLogin.setBounds(1300, 510, 250, 80);
        AgentLogin.setIcon(new ImageIcon("AgentLoginmain1.png"));
        AgentLogin.setBorder(null);
        AgentLogin.setBorderPainted(false);
        AgentLogin.setContentAreaFilled(false);
        AgentLogin.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        AgentLogin.setDoubleBuffered(true);
        AgentLogin.setFocusPainted(false);
        AgentLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainframe.add(Administratorlogin);
        mainframe.add(AgentLogin);
        AgentLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.setVisible(false);
                Adminagentloginframe.adminagentmainframe();
                Adminagentloginframe.accounttypesbox.setSelectedIndex(1);

            }
        });

    }


}
