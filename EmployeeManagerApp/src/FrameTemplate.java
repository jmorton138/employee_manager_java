import java.awt.*;
import javax.swing.*;

public class FrameTemplate extends JFrame {

    FrameTemplate() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.pack();
        this.setVisible(true);

    }

}
