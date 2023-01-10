import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class App {
    public static void main(String[] args) throws Exception {
        // gets the required driver
        Class.forName("com.mysql.jdbc.Driver");
        // creates connection with sql
        String dbName = "ems";
        String db_username = "root";
        String db_password = "password";
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + dbName, db_username, db_password);
        System.out.println(con);
        LoginPage loginPage = new LoginPage(con);
    }
}
