import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Management {
    public static void registerbuttonuserAction() {
        MainFrame.RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Code Here
            }
        });
    }

    public static void loginbuttonaction() {
        MainFrame.LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Code Here
            }
        });
    }

    public static boolean checkuseravailable(String username, String Password) {
        //Code Here
        return false;
    }

    public static User loginofuser(String username, String Password) {

        //Code Here
        return null;
    }


    public static void Registrationofuser() {
        //Code Here
    }

    public static ArrayList<User> readalldata() {
        //Code Here

        return null;
    }

    public static ArrayList<Administrator> readalldataadmin() {
        //Code Here
        return null;
    }

    public static ArrayList<Agent> readalldataagent() {
        ///Code here


        return null;
    }

    public static void RegistrationofAgent() {
        //Code Here


    }
}


