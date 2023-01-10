
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

public class DeleteEmployeePage extends FrameTemplate {
    Connection con;

    DeleteEmployeePage(Connection con) {
        this.con = con;
    }

    public void deleteEmployee() {
        // take employee id and check it's valid
        this.setVisible(false);

        JFrame delEmpFrame = new JFrame("Edit Employee");

        JLabel enterIdLabel = new JLabel("Enter ID");
        enterIdLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        enterIdLabel.setBounds(250, 200, 200, 50);
        delEmpFrame.add(enterIdLabel);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        idVal.setBounds(500, 200, 200, 50);
        delEmpFrame.add(idVal);

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
            delEmpFrame.dispose();
            this.setVisible(true);
        });
        delEmpFrame.add(backButton);

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
                    deleteEmployeeHelper(id, delEmpFrame);

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
        delEmpFrame.add(submitButton);

        delEmpFrame.setLayout(null);
        delEmpFrame.setSize(1100, 750);
        delEmpFrame.setVisible(true);
    }

    public void deleteEmployeeHelper(int empID, JFrame parentFrame) {
        Statement deleteStatement = null;
        try {
            deleteStatement = this.con.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String deleteEmpQuery = String.format(
                    "delete from employee where id=%d", empID);
            deleteStatement.executeUpdate(deleteEmpQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // TODO: fix success popup message
        // JFrame popup = new JFrame("Employee successfully deleted");
        // JLabel popupMsg = new JLabel(String.format("Employee %s record deleted",
        // empID));
        // popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        // popupMsg.setBounds(20, 10, 300, 50);
        // popup.add(popupMsg);

        // JButton button = new JButton("OK");
        // button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        // button.addActionListener(actionEvent2 -> {
        // popup.dispose();
        // });
        // button.setBounds(120, 60, 70, 20);

        // popup.add(button);

        // popup.setLayout(null);
        // popup.setVisible(true);
        // popup.setSize(500, 300);

        parentFrame.dispose();
        this.setVisible(true);
    }

}
