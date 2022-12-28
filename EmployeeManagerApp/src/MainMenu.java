import javax.swing.JLabel;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends FrameTemplate {
    MainMenu() {
        JLabel welcomeLabel = new JLabel("Weclome to Employee Management");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        welcomeLabel.setBounds(250, 50, 800, 50);
        this.add(welcomeLabel);
        this.setSize(1100, 750);
        System.out.println("main menu");
    }
}
