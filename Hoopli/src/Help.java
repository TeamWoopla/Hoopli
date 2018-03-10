import javax.swing.*;
import java.awt.*;

public class Help {
    Help() {
        Start.frame.setResizable(false);
        Start.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setSize(1100, 900);
        Start.frame.setVisible(true);
        Start.frame.setLayout(null);
        Start.frame.getContentPane().removeAll();
        Main.BackLbl.setBounds(0, 0, 1100, 900);

        Start.frame.add(Main.BTNStart);
        Start.frame.add(Main.BackLbl);
    }
}
