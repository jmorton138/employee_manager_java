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
        // System.out.println("main menu");

        JButton viewEmpButton = new JButton("View all employees");
        viewEmpButton.setBounds(400, 200, 300, 40);
        viewEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        viewEmpButton.setFocusPainted(false);
        viewEmpButton.addActionListener(actionEvent -> {
            try {
                viewEmployee();
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
            try {
                addEmployee();
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
            try {
                editEmployee();
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
            deleteEmployee();
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

    public void viewEmployee() throws SQLException {
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

        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

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

    public void editEmployee() throws SQLException {

    }

    public void deleteEmployee() {

    }

}
