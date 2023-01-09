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

public class ViewEmployeesPage extends FrameTemplate {
    Connection con;

    ViewEmployeesPage(Connection con) {
        this.con = con;
    }

    public void viewEmployees() throws SQLException {
        this.setVisible(false);

        JFrame empRecords = new JFrame("Employee Records");
        JPanel panel = new JPanel();

        Statement statement = this.con.createStatement();
        String query = "select * from employee";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            JPanel employeeCard = new JPanel();
            JLabel idLabel = new JLabel("ID");
            idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel idVal = new JLabel(String.valueOf(resultSet.getInt(1)));
            idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameVal = new JLabel(String.valueOf(resultSet.getString(2)));
            nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel genderLabel = new JLabel("gender");
            genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel genderVal = new JLabel(String.valueOf(resultSet.getString(3)));
            genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel phoneLabel = new JLabel("phone");
            phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel phoneVal = new JLabel(String.valueOf(resultSet.getString(4)));
            phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailLabel = new JLabel("email");
            emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailVal = new JLabel(String.valueOf(resultSet.getString(5)));
            emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel designationLabel = new JLabel("designation");
            designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel designationVal = new JLabel(String.valueOf(resultSet.getString(6)));
            designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel salaryLabel = new JLabel("salary");
            salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel salaryVal = new JLabel(String.valueOf(resultSet.getDouble(7)));
            salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            employeeCard.add(idLabel);
            employeeCard.add(idVal);
            employeeCard.add(nameLabel);
            employeeCard.add(nameVal);
            employeeCard.add(genderLabel);
            employeeCard.add(genderVal);
            employeeCard.add(phoneLabel);
            employeeCard.add(phoneVal);
            employeeCard.add(emailLabel);
            employeeCard.add(emailVal);
            employeeCard.add(designationLabel);
            employeeCard.add(designationVal);
            employeeCard.add(salaryLabel);
            employeeCard.add(salaryVal);

            employeeCard.setSize(1000, 400);
            employeeCard.setBackground(new Color(166, 209, 230));
            employeeCard.setBorder(new EmptyBorder(20, 50, 50, 50));
            GridLayout cardLayout = new GridLayout(0, 2);
            cardLayout.setHgap(5);
            cardLayout.setVgap(10);
            employeeCard.setLayout(cardLayout);
            panel.add(employeeCard);
        }

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200, 50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            empRecords.dispose();
            this.setVisible(true);
        });
        buttonPanel.add(backButton);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(254, 251, 246));
        panel.add(buttonPanel);

        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(30);
        panel.setLayout(layout);
        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 0, 50, 0));

        JScrollPane scrollBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        empRecords.add(scrollBar);
        empRecords.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        empRecords.pack();
        empRecords.setVisible(true);

    }

}
