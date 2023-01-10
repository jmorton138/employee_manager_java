
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

public class EditEmployeePage extends FrameTemplate {
    Connection con;

    EditEmployeePage(Connection con) {
        this.con = con;
    }

    public void editEmployee() throws SQLException {
        // take employee id and check it's valid
        this.setVisible(false);

        JFrame editEmpFrame = new JFrame("Edit Employee");

        JLabel enterIdLabel = new JLabel("Enter ID");
        enterIdLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        enterIdLabel.setBounds(250, 200, 200, 50);
        editEmpFrame.add(enterIdLabel);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        idVal.setBounds(500, 200, 200, 50);
        editEmpFrame.add(idVal);

        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // if it's not a number, ignore the event
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(300, 400, 200, 50);

        backButton.addActionListener(backActionListener -> {
            editEmpFrame.dispose();
            this.setVisible(true);
        });
        editEmpFrame.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(500, 400, 200, 50);

        submitButton.addActionListener(actionEvent -> {
            Statement statement = null;
            try {
                statement = this.con.createStatement();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            int id = Integer.parseInt(idVal.getText());
            try {
                String query = String.format("select * from employee where id=%d", id);
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    // render edit page filled in with employee's details
                    System.out.println(resultSet.getString(2));
                    editEmployeeHelper(id, editEmpFrame);

                } else {
                    // invalid id pop up
                    JFrame popup = new JFrame("Invalid Employee ID");
                    JLabel popupMsg = new JLabel("Employee ID doesn't exist");
                    popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    popupMsg.setBounds(20, 10, 300, 50);
                    popup.add(popupMsg);

                    JButton button = new JButton("OK");
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                    button.addActionListener(actionEvent2 -> {
                        popup.dispose();
                    });
                    button.setBounds(120, 60, 70, 20);

                    popup.add(button);

                    popup.setLayout(null);
                    popup.setVisible(true);
                    popup.setSize(500, 300);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        editEmpFrame.add(submitButton);

        editEmpFrame.setLayout(null);
        editEmpFrame.setSize(1100, 750);
        editEmpFrame.setVisible(true);

    }

    public void editEmployeeHelper(int empID, JFrame parentFrame) throws SQLException {
        JFrame editEmpHelperFrame = new JFrame("Edit Employee Record");
        JPanel panel = new JPanel();

        Statement statement = null;
        try {
            statement = this.con.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String query = String.format("select * from employee where id=%d", empID);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        JLabel nameLabel = new JLabel("Enter Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField(resultSet.getString(2));
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel genderLabel = new JLabel("Enter Gender");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        String gender = resultSet.getString(3);
        JComboBox<String> genderVal = new JComboBox<>(new String[] { "Male", "Female" });
        genderVal.setSelectedIndex(gender.equals("Male") ? 0 : 1);
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel phoneLabel = new JLabel("Enter Phone Number");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField phoneVal = new JTextField(resultSet.getString(4));
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel emailLabel = new JLabel("Enter Email Address");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField(resultSet.getString(5));
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel designationLabel = new JLabel("Enter Designation");
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField designationVal = new JTextField(resultSet.getString(6));
        designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel salaryLabel = new JLabel("Enter Salary ($)");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField salaryVal = new JTextField(resultSet.getString(7));
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // if it's not a number, ignore the event
                }
            }
        });

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
        JButton backButton = new JButton("Back");
        backButton.addActionListener(backActionListener -> {
            editEmpHelperFrame.dispose();
            this.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(450, 30, 200, 50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            Statement submitStatement = null;
            try {
                submitStatement = this.con.createStatement();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String name = nameVal.getText();
            String genderSelected = (String) genderVal.getSelectedItem();
            String phoneNumber = phoneVal.getText();
            String email = emailVal.getText();
            String designation = designationVal.getText();
            double salary = Double.parseDouble(salaryVal.getText());
            try {
                String editEmpQuery = String.format(
                        "update employee set name='%s', gender='%s', phoneNum='%s', email='%s', designation='%s', salary=%f where id=%d",
                        name, genderSelected, phoneNumber, email, designation, salary, empID);
                submitStatement.execute(editEmpQuery);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            editEmpHelperFrame.dispose();
            this.setVisible(true);
        });

        panel.add(submitButton);
        GridLayout grid = new GridLayout(0, 2);
        grid.setHgap(60);
        grid.setVgap(40);
        panel.setLayout(grid);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));
        editEmpHelperFrame.add(panel);
        editEmpHelperFrame.setSize(1100, 750);
        editEmpHelperFrame.setVisible(true);
        parentFrame.dispose();
    }

}
