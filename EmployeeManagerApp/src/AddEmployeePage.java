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

public class AddEmployeePage extends FrameTemplate {
    Connection con;

    AddEmployeePage(Connection con) {
        this.con = con;
    }

    public void addEmployee() throws SQLException {
        this.setVisible(false);
        JFrame addEmpFrame = new JFrame("Add Employee Record");
        JPanel panel = new JPanel();

        JLabel idLabel = new JLabel("Enter ID");
        idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField idVal = new JTextField("");
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // if it's not a number, ignore the event

                }
            }
        });

        JLabel nameLabel = new JLabel("Enter Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField("");
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel genderLabel = new JLabel("Enter Gender");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JComboBox<String> genderVal = new JComboBox<>(new String[] { "Male", "Female" });
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel phoneLabel = new JLabel("Enter Phone Number");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField phoneVal = new JTextField("");
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel emailLabel = new JLabel("Enter Email Address");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField("");
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel designationLabel = new JLabel("Enter Designation");
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField designationVal = new JTextField("");
        designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel salaryLabel = new JLabel("Enter Salary ($)");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField salaryVal = new JTextField("");
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // if it's not a number, ignore the event
                }
            }
        });

        panel.add(idLabel);
        panel.add(idVal);
        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(designationLabel);
        panel.add(designationVal);
        panel.add(salaryLabel);
        panel.add(salaryVal);

        GridLayout grid = new GridLayout(0, 2);
        grid.setHgap(60);
        grid.setVgap(40);
        panel.setLayout(grid);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200, 50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            addEmpFrame.dispose();
            this.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(450, 30, 200, 50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            Statement statement = null;
            try {
                statement = this.con.createStatement();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            int id = Integer.parseInt(idVal.getText());
            String name = nameVal.getText();
            String gender = (String) genderVal.getSelectedItem();
            String phoneNumber = phoneVal.getText();
            String email = emailVal.getText();
            String designation = designationVal.getText();
            double salary = Double.parseDouble(salaryVal.getText());

            try {
                String query = String.format(
                        "insert into employee (id, name, gender, phoneNum, email, designation, salary) values (%d, '%s', '%s', '%s', '%s','%s', %f)",
                        id,
                        name, gender, phoneNumber, email, designation, salary);

                statement.executeUpdate(query);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            this.setVisible(true);
            addEmpFrame.dispose();
        });
        panel.add(submitButton);

        addEmpFrame.add(panel);
        addEmpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addEmpFrame.pack();
        addEmpFrame.setVisible(true);

    }
}
