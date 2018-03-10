import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class Main {

    static JLabel BackLbl = new JLabel();

    static JButton BTNStart = new JButton("Begin");
    static JButton  Normal = new JButton("Normal");
    static JButton Medium = new JButton("Medium");
    static JButton Hard = new JButton("Hard");
    static JButton Hardcore = new JButton("Hardcore");
    static JButton BTNHelp = new JButton("Help");
    static JButton ExitBTN = new JButton("Exit");
    ImageIcon BackIcon = new ImageIcon("Pics\\Back.png");


    Main() {

        Start.frame.setResizable(false);
        Start.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setSize(1100, 900);
        Start.frame.setVisible(true);
        Start.frame.setLayout(null);
        Start.frame.getContentPane().removeAll();


        BTNStart.setFont(new Font("Arial", Font.PLAIN, 100));
        BTNStart.setBackground(Color.WHITE);
        BTNStart.setBorder(new LineBorder(new Color(153, 153, 153)));
        BTNStart.setFocusable(false);
        BTNHelp.setFont(new Font("Arial", Font.PLAIN, 100));
        BTNHelp.setBackground(Color.WHITE);
        BTNHelp.setBorder(new LineBorder(new Color(153, 153, 153)));
        BTNHelp.setFocusable(false);
        Normal.setFont(new Font("Arial", Font.PLAIN, 40));
        Normal.setBackground(Color.RED);
        Normal.setBorder(new LineBorder(new Color(153, 153, 153)));
        Normal.setFocusable(false);
        Medium.setFont(new Font("Arial", Font.PLAIN, 40));
        Medium.setBackground(Color.WHITE);
        Medium.setBorder(new LineBorder(new Color(153, 153, 153)));
        Medium.setFocusable(false);
        Hard.setFont(new Font("Arial", Font.PLAIN, 40));
        Hard.setBackground(Color.WHITE);
        Hard.setBorder(new LineBorder(new Color(153, 153, 153)));
        Hard.setFocusable(false);
        Hardcore.setFont(new Font("Arial", Font.PLAIN, 40));
        Hardcore.setBackground(Color.WHITE);
        Hardcore.setBorder(new LineBorder(new Color(153, 153, 153)));
        Hardcore.setFocusable(false);
        ExitBTN.setFont(new Font("Arial", Font.PLAIN, 40));
        ExitBTN.setBackground(Color.WHITE);
        ExitBTN.setBorder(new LineBorder(new Color(153, 153, 153)));
        ExitBTN.setFocusable(false);
        BTNStart.setBounds(200, 50, 700, 200);
        Normal.setBounds(200, 275, 330, 120);
        Medium.setBounds(570, 275, 330, 120);
        Hard.setBounds(200, 420, 330, 120);
        Hardcore.setBounds(570, 420, 330, 120);
        ExitBTN.setBounds(20, 820, 100, 70);
        BTNHelp.setBounds(200, 565, 700, 200);
        BackLbl.setBounds(0, 0, 1100, 900);
        BackLbl.setIcon(BackIcon);


        Start.frame.add(BTNStart);
        Start.frame.add(Normal);
        Start.frame.add(Medium);
        Start.frame.add(Hard);
        Start.frame.add(Hardcore);
        Start.frame.add(BTNHelp);
        Start.frame.add(ExitBTN);
        Start.frame.add(BackLbl);

        BTNStart.addActionListener(e -> {
            Start.frame.getContentPane().removeAll();
            Game a = new Game();
            Start.frame.add(a);
        });
        ExitBTN.addActionListener(e -> System.exit(0));
        Normal.addActionListener(e -> {
            Game.maximum = 400;
            Game.minimum = 300;
            Hardcore.setBackground(Color.WHITE);
            Normal.setBackground(Color.RED);
            Medium.setBackground(Color.WHITE);
            Hard.setBackground(Color.WHITE);
        });
        Medium.addActionListener(e -> {
            Game.maximum = 300;
            Game.minimum = 200;
            Hardcore.setBackground(Color.WHITE);
            Normal.setBackground(Color.WHITE);
            Medium.setBackground(Color.RED);
            Hard.setBackground(Color.WHITE);
        });
        Hard.addActionListener(e -> {
            Game.maximum = 200;
            Game.minimum = 100;
            Hardcore.setBackground(Color.WHITE);
            Normal.setBackground(Color.WHITE);
            Medium.setBackground(Color.WHITE);
            Hard.setBackground(Color.RED);
        });
        Hardcore.addActionListener(e -> {
            Game.maximum = 75;
            Game.minimum = 50;
            Hardcore.setBackground(Color.RED);
            Normal.setBackground(Color.WHITE);
            Medium.setBackground(Color.WHITE);
            Hard.setBackground(Color.WHITE);
        });
        BTNHelp.addActionListener(e -> {
            Start.frame.getContentPane().removeAll();
            Help a = new Help();
        });

    }

}