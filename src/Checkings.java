import javax.swing.*;
import java.util.ArrayList;

public class Checkings {
    public static boolean checkRegistration() {
        String name = MainFrame.nameusertextfield.getText();
        String cnic = MainFrame.cnicusertextfield.getText();
        String phoneno = MainFrame.phonenumberusertextfield.getText();
        boolean checkall = true;
        checkall = isalphabetic(name);
        if (checkall) {
            checkall = isNumeric(phoneno);
            if (checkall) {
                if (cnic.length() == 13) {
                    checkall = isNumeric(cnic);
                } else
                    checkall = false;
                if (checkall) {
                    checkall = usernamecheck(MainFrame.usernameusertextfield.getText());
                }
            }
        }
        return checkall;

    }

    public static boolean checkRegistrationagent() {
        String name = AdminManagement.nameagenttextfield.getText();
        String cnic = AdminManagement.cnicagenttextfield.getText();
        String phoneno = AdminManagement.phonenumberagenttextfield.getText();
        boolean checkall = true;
        checkall = isalphabetic(name);
        if (checkall) {
            checkall = isNumeric(phoneno);
            if (checkall) {
                if (cnic.length() == 13) {
                    checkall = isNumeric(cnic);
                } else
                    checkall = false;
                if (checkall) {
                    checkall = usernamecheck(AdminManagement.usernameagenttextfield.getText());
                }
            }
        }
        return checkall;

    }

    public static boolean usernamecheck(String username) {
        boolean b = true;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (c == ' ') {
                b = false;
                JOptionPane.showMessageDialog(null, "Username cannot contain spaces");
            }
        }
        return b;
    }

    public static boolean isNumeric(String num) {
        try {
            Double no = Double.parseDouble(num);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isalphabetic(String alpha) {
        boolean allcheck = true;
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if ((c >= 'A' && c <= 'Z')) {
            } else if ((c >= 'a' && c <= 'z') || c == ' ') {
            } else
                allcheck = false;
        }
        return allcheck;
    }

    public static boolean Usernamealreadycheck() {
        ArrayList<User> users = Management.readalldata();
        boolean check = false;
        for (int i = 0; i < users.size(); i++) {
            if (MainFrame.usernameusertextfield.getText().toLowerCase().equals(users.get(i).getUsername().toLowerCase())) {
                check = true;
                break;
            }
        }
        return check;

    }

    public static boolean Usernamealreadycheckagent() {
        ArrayList<Agent> agents = Management.readalldataagent();
        boolean check = false;
        for (int i = 0; i < agents.size(); i++) {
            if (AdminManagement.usernameagenttextfield.getText().toLowerCase().equals(agents.get(i).getUsername().toLowerCase())) {
                check = true;
                break;
            }
        }
        return check;

    }
}
