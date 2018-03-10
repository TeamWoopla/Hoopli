import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Hoops implements ActionListener {

    private static int numOfObjects = 0;
    static int speed = 2;
    static int speedDely1 = 0;
    static int speedDely2 = 0;
    static int speedDely3 = 0;

    private Timer t = new Timer(speed, this);

    ImageIcon HoopIcon = new ImageIcon("pics\\HoopRed.png");

    private JLabel TheHoop = new JLabel();
    static ArrayList<JLabel> PowerDraw = new ArrayList<>();

    private int Left, Right;
    private int serialNum = numOfObjects;
    private int x;
    private int y = -10;
    private int BackupSize;
    private int size = 200;

    private boolean Eatable = false;
    private boolean Worked = false;

    Hoops(){
        numOfObjects++;
        t.start();
        Random rand = new Random();
        while (240 > size && size > 160){
            size = rand.nextInt(300) + 10;
        }
        int i10 = rand.nextInt(20) + 1;
        if (i10 == 1){
            size = rand.nextInt(450) + 400;
        }
        if (size < Game.ThePlayer.Width){
            Eatable = true;
        }

        BackupSize = size;

        x = rand.nextInt(1050 - size) + 20;

        int i9 = rand.nextInt(3) + 1;
        switch (i9){
            case 1:
                HoopIcon = new ImageIcon("pics\\HoopGreen.png");
                break;
            case 2:
                HoopIcon = new ImageIcon("pics\\HoopBlue.png");
                break;
        }

        ChangePic();

    }

    static void DrawPowerUPS(){
        int PowerX = Start.frame.getWidth() - 70;
        for (JLabel aPowerDraw : PowerDraw) {
            boolean draw = true;
            switch (aPowerDraw.getText()){
                case " ":
                    if(speedDely1 < 800 && speedDely1 % 6 > 2){
                        draw = false;
                    }
                case "  ":
                    if(speedDely1 < 800 && speedDely2 % 6 > 2){
                        draw = false;
                    }
                case "   ":
                    if(speedDely1 < 800 && speedDely3 % 6 > 2){
                        draw = false;
                    }
                    break;
            }
            if(draw) {
                aPowerDraw.setBounds(PowerX, 15, 50, 50);
                Start.frame.add(aPowerDraw);
            }
            PowerX -= 52;
        }
    }

    void Draw() {
        TheHoop.setBounds(x, y, size, 20);
        Start.frame.add(TheHoop);
    }

    private boolean didBoost1 = true;
    private boolean didBoost2 = true;
    private boolean didBoost3 = true;


    public void actionPerformed(ActionEvent e) {
        if (size < Game.ThePlayer.Width){
            Eatable = true;
        }else {
            Eatable = false;
        }
        if(speedDely1 == 0) {
            didBoost1 = true;
            if (size != BackupSize) {
                size = BackupSize;
                for (int i = 0; i < PowerDraw.size(); i++) {
                    if(PowerDraw.get(i).getText().equals(" ")){
                        PowerDraw.remove(i);
                    }
                }
                ChangePic();
            }

        } else {
            if(didBoost1) {
                didBoost1 = false;

                boolean DrawThePowerUPNigga = true;
                for (JLabel aPowerDraw : PowerDraw) {
                    if (aPowerDraw.getText().equals(" ")){
                        DrawThePowerUPNigga = false;
                    }
                }
                if(DrawThePowerUPNigga) {
                    PowerDraw.add(new JLabel(" "));
                    ImageIcon DrawPowerUPIcon = new ImageIcon("Pics\\EverythingSmall.png");
                    PowerDraw.get(PowerDraw.size() - 1).setIcon(DrawPowerUPIcon);
                }
                size = 20;
                ChangePic();
            }
            speedDely1--;
        }

        if(speedDely2 == 0) {
            Game.ThePlayer.CheangSizeBack();
            for (int i = 0; i < PowerDraw.size(); i++) {
                if(PowerDraw.get(i).getText().equals("  ")){
                    PowerDraw.remove(i);
                }
            }

        } else {
            if(didBoost2) {
                didBoost2 = false;
                boolean DrawThePowerUPNigga = true;
                for (JLabel aPowerDraw : PowerDraw) {
                    if (aPowerDraw.getText().equals("  ")){
                        DrawThePowerUPNigga = false;
                    }
                }
                if(DrawThePowerUPNigga) {
                    PowerDraw.add(new JLabel("  "));
                    ImageIcon DrawPowerUPIcon = new ImageIcon("Pics\\YouAreBigger.png");
                    PowerDraw.get(PowerDraw.size() - 1).setIcon(DrawPowerUPIcon);
                }
                Game.ThePlayer.CheangSize();
            }
            speedDely2--;
        }

        if(speedDely3 == 0) {
            didBoost3 = true;
            if(t.getDelay() != speed) {
                t.setDelay(speed);
            }
            for (int i = 0; i < PowerDraw.size(); i++) {
                if(PowerDraw.get(i).getText().equals("   ")){
                    PowerDraw.remove(i);
                }
            }
        } else {
            if(didBoost3) {
                didBoost3 = false;
                t.setDelay(speed + 5);
                boolean DrawThePowerUPNigga = true;
                for (JLabel aPowerDraw : PowerDraw) {
                    if (aPowerDraw.getText().equals("   ")){
                        DrawThePowerUPNigga = false;
                    }
                }
                if(DrawThePowerUPNigga) {
                    PowerDraw.add(new JLabel("   "));
                    ImageIcon DrawPowerUPIcon = new ImageIcon("Pics\\SlowsTime.png");
                    PowerDraw.get(PowerDraw.size() - 1).setIcon(DrawPowerUPIcon);
                }
            }
            speedDely3--;
        }

        if (y < Start.frame.getHeight()) {
            y += 1;
        } else {
            for (int i = Game.ArrayHoops.size()-1; i > -1; i--){
                if(Game.ArrayHoops.get(i).serialNum == this.serialNum){
                    Game.ArrayHoops.remove(i);
                    t.stop();
                    if(!Worked){
                        Game.Health--;
                        SoundBuzzer();
                    }
                }
            }
        }

        Left = x;
        Right = x + size;

        if(Eatable){
            if(EatingPlayer2(Game.ThePlayer)) {
                for (int i = Game.ArrayHoops.size()-1; i > -1; i--){
                    if(Game.ArrayHoops.get(i).serialNum == this.serialNum){
                        if(!Worked) {
                            Game.ArrayHoops.remove(i);
                            Game.Health--;
                            SoundBuzzer();
                            Worked = true;
                        }
                    }
                }
            }
            if(EatenByPlayer(Game.ThePlayer)){
                for (int i = Game.ArrayHoops.size()-1; i > -1; i--){
                    if(Game.ArrayHoops.get(i).serialNum == this.serialNum){
                        if(!Worked) {
                            Game.ArrayHoops.remove(i);
                            t.stop();
                            Game.scoreInt += 10;
                            Worked = true;
                            SoundPlusScore();
                        }
                    }
                }
            }
        } else {
            if(EatingPlayer(Game.ThePlayer)) {
                for (int i = Game.ArrayHoops.size()-1; i > -1; i--){
                    if(Game.ArrayHoops.get(i).serialNum == this.serialNum){
                        if(!Worked) {
                            Game.ArrayHoops.remove(i);
                            Game.Health--;
                            SoundBuzzer();
                            Worked = true;
                        }
                    }
                }
            }
            if(PlayerSafe(Game.ThePlayer)){
                for (int i = Game.ArrayHoops.size()-1; i > -1; i--){
                    if(Game.ArrayHoops.get(i).serialNum == this.serialNum){
                        if(!Worked) {
                            Game.scoreInt += 10;
                            SoundPlusScore();
                            Worked = true;
                        }
                    }
                }
            }
        }
    }

    boolean EatenByPlayer(Player Player){
        return (Player.Left < this.Left) && (Player.Right > this.Right) && y == 825;
    }

    boolean EatingPlayer(Player Player){
        return  ((Player.Left < this.Left && this.Left < Player.Right) || (Player.Left < this.Right && this.Right < Player.Right)) && y == 825;
    }

    boolean EatingPlayer2(Player Player){
        return  ((Player.Left < this.Right && this.Right < Player.Right && Player.Left > this.Left) || (Player.Left < this.Left && this.Left < Player.Right && Player.Right < this.Right)) && y == 825;
    }

    boolean PlayerSafe(Player Player){
        return (Player.Left > this.Left) && (Player.Right < this.Right) && y == 825;
    }

    static void SoundPlusScore() {
        try {
            File file = new File("Sounds\\Ding.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void SoundBuzzer() {
        try {
            File file = new File("Sounds\\Buzzer.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    void ChangePic(){
        Image image = HoopIcon.getImage();
        Image newimg = image.getScaledInstance(size, 20,  java.awt.Image.SCALE_SMOOTH);
        HoopIcon = new ImageIcon(newimg);
        TheHoop.setIcon(HoopIcon);
    }

}
