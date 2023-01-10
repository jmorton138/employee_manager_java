import java.sql.SQLException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class LoginPage extends JFrame {
    JFrame loginFrame;
    JLabel usernameLabel;
    JTextField usernameField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton submitButton;
    Connection con;

    LoginPage(Connection con) {
        this.con = con;
        loginFrame = new JFrame("Log In");
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(200, 150, 220, 50);
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        loginFrame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        usernameField.setBounds(450, 150, 420, 50);
        loginFrame.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(200, 250, 220, 50);
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        loginFrame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordField.setBounds(450, 250, 420, 50);
        loginFrame.add(passwordField);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        submitButton.setBounds(450, 400, 250, 50);
        loginFrame.add(submitButton);
        // MainMenu mainMenu = new MainMenu();

        submitButton.addActionListener(actionEvent -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            try {
                boolean auth = login(username, password);
                if (auth) {
                    loginFrame.dispose();
                    MainMenu mainMenu = new MainMenu(this.con);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        loginFrame.setSize(1100, 750);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public boolean login(String username, String password) throws SQLException {
        Statement statement = this.con.createStatement();
        String q = String.format("select password from admin where username = '%s';", username);
        ResultSet resultSet = statement.executeQuery(q);
        // System.out.println(resultSet.getString(0));
        if (resultSet.next()) {
            if (resultSet.getString(1).equals(password)) {
                return true;
            } else {
                JFrame popup = new JFrame("Invalid password");
                JLabel popupMsg = new JLabel("The password you entered was incorrect");
                popupMsg.setBounds(20, 10, 300, 50);
                popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                popup.add(popupMsg);

                JButton button = new JButton("OK");
                button.setBounds(120, 60, 70, 20);
                button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                button.addActionListener(actionEvent2 -> {
                    popup.dispose();
                });
                popup.add(button);

                popup.setLayout(null);
                popup.setSize(350, 150);
                popup.setVisible(true);
                return false;
            }
        } else {
            JFrame popup = new JFrame("Invalid username");
            JLabel popupMsg = new JLabel("The username you entered does not exist.");
            popupMsg.setBounds(20, 10, 500, 50);
            popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            popup.add(popupMsg);

            JButton button = new JButton("OK");
            button.setBounds(170, 60, 70, 20);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            button.addActionListener(actionEvent2 -> {
                popup.dispose();
            });
            popup.setLayout(null);
            popup.setSize(450, 150);
            popup.setVisible(true);
            return false;
        }

    }
}
