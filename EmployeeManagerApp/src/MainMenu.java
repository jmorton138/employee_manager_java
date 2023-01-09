import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.sql.*;

public class MainMenu extends FrameTemplate {
    Connection con;

    MainMenu(Connection con) {
        this.con = con;
        JLabel welcomeLabel = new JLabel("Weclome to Employee Management");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        welcomeLabel.setBounds(250, 50, 800, 50);
        this.add(welcomeLabel);
        JButton viewEmpButton = new JButton("View all employees");
        viewEmpButton.setBounds(400, 200, 300, 40);
        viewEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        viewEmpButton.setFocusPainted(false);
        viewEmpButton.addActionListener(actionEvent -> {
            ViewEmployeesPage viewEmployeesPage = new ViewEmployeesPage(this.con);
            try {
                viewEmployeesPage.viewEmployees();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.add(viewEmpButton);
        JButton addEmpButton = new JButton("Add an employee");
        addEmpButton.setBounds(400, 270, 300, 40);
        addEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addEmpButton.setFocusPainted(false);
        addEmpButton.addActionListener(actionEvent -> {
            AddEmployeePage addEmployeePage = new AddEmployeePage(this.con);

            try {
                addEmployeePage.addEmployee();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.add(addEmpButton);

        JButton editEmpButton = new JButton("Edit an employee");
        editEmpButton.setBounds(400, 340, 300, 40);
        editEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        editEmpButton.setFocusPainted(false);
        editEmpButton.addActionListener(actionEvent -> {
            EditEmployeePage editEmployeePage = new EditEmployeePage(this.con);

            try {
                editEmployeePage.editEmployee();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.add(editEmpButton);

        JButton deleteEmpButton = new JButton("Delete an employee");
        deleteEmpButton.setBounds(400, 410, 300, 40);
        deleteEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        deleteEmpButton.setFocusPainted(false);
        deleteEmpButton.addActionListener(deleteEvent -> {
            DeleteEmployeePage deleteEmployeePage = new DeleteEmployeePage(this.con);
            deleteEmployeePage.deleteEmployee();
        });
        this.add(deleteEmpButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(400, 480, 300, 40);
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(actionEvent -> {
            this.dispose();
        });
        this.add(exitButton);
        this.setSize(1100, 750);

    }

}
