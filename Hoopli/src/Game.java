import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

class Game extends JPanel implements ActionListener {
    static int minimum = 300;
    static int maximum = 400;
    static ArrayList<Hoops> ArrayHoops = new ArrayList<>();
    static ArrayList<PowerUP> ArrayPowerUP = new ArrayList<>();
    static Player ThePlayer = new Player();


    private JLabel BackLbl = new JLabel();
    private JLabel Score = new JLabel("Score : " + scoreInt);
    private JLabel[] ArrayHealth = new JLabel[100];

    private Timer t = new Timer(5, this);
    static int scoreInt = 0;
    static int Health = 3;
    static int NextPowerUP = 50;

    private int Time = 1;


    Game() {
        Start.frame.setResizable(false);
        Start.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setSize(1100, 900);
        Start.frame.setVisible(true);
        Start.frame.setLayout(null);

        this.setPreferredSize(new Dimension(1000, 1000));
        t.start();

        ImageIcon BackIcon = new ImageIcon("Pics\\Back.png");
        BackLbl.setBounds(0, 0, 1100, 900);
        BackLbl.setIcon(BackIcon);
        Score.setFont(new Font("Arial", Font.PLAIN, 25));
        Score.setBounds(5, 25, 200, 100);

    }

    private void View() {
        Score.setText("Score : " + scoreInt);
        Start.frame.setResizable(false);
        Start.frame.setLayout(new FlowLayout());
        Start.frame.setLayout(null);
        Start.frame.getContentPane().removeAll();

        // draw
        int HealthX = 2;
        for (int i = 0; i < Health; i++){
            ArrayHealth[i] = new JLabel("");
            ImageIcon HealthIcon = new ImageIcon("Pics\\HP.png");
            ArrayHealth[i].setBounds(HealthX, 15, 50, 50);
            ArrayHealth[i].setIcon(HealthIcon);
            HealthX += 52;
            Start.frame.add(ArrayHealth[i]);
        }
        Start.frame.add(Score);
        for (Hoops Hoops : ArrayHoops) {
            Hoops.Draw();
        }
        for (PowerUP PowerUP : ArrayPowerUP) {
            PowerUP.Draw();
        }
        ThePlayer.Draw();
        Hoops.DrawPowerUPS();
        Start.frame.add(BackLbl);
        //Start.frame.add(BackLbl);
    }


    public void actionPerformed(ActionEvent e) {

        if(Time <= 1) {
            ArrayHoops.add(new Hoops());
            Random rand = new Random();
            Time = rand.nextInt(maximum) + minimum;
        }else {
            Time--;
        }

        if(scoreInt >= NextPowerUP){
            ArrayPowerUP.add(new PowerUP());
        }

        if (Health == 0){
            try {
                File file = new File("Sounds\\GameOver.wav");
                AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.start();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            t.stop();
        }

        View();

    }
}
