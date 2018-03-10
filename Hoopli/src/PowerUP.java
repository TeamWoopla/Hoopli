import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PowerUP implements ActionListener {

    private static int numOfObjects = 0;

    private Timer t = new Timer(2, this);

    private JLabel ThePowerUP = new JLabel();

    private int Left, Right;
    private int serialNum = numOfObjects;
    private int x;
    private int y = -10;
    private String Type = "BP";

    PowerUP() {
        numOfObjects++;
        t.start();
        Random rand = new Random();

        int help = rand.nextInt(10) + 6;
        Game.NextPowerUP += help * 10;
        System.out.println(Game.NextPowerUP);

        x = rand.nextInt(1000) + 20;

        ImageIcon PowerUPIcon = new ImageIcon("pics\\YouAreBigger.png");

        int i9 = rand.nextInt(4) + 1;
        switch (i9) {
            case 1:
                Type = "ST";
                PowerUPIcon = new ImageIcon("pics\\SlowsTime.png");
                break;
            case 2:
                Type = "ES";
                PowerUPIcon = new ImageIcon("pics\\EverythingSmall.png");
                break;
            case 3:
                Type = "HP";
                PowerUPIcon = new ImageIcon("pics\\HP.png");
                break;

        }

        ThePowerUP.setIcon(PowerUPIcon);
    }

    void Draw() {
        ThePowerUP.setBounds(x, y, 50, 50);
        Start.frame.add(ThePowerUP);
    }

    public void actionPerformed(ActionEvent e) {
        if (y < Start.frame.getHeight()) {
            y += 1;
        } else {
             Remove();
        }

        Left = x;
        Right = x + 50;
        if (EatenByPlayer(Game.ThePlayer)) {
            for (int i = Game.ArrayPowerUP.size() - 1; i > -1; i--) {
                if (Game.ArrayPowerUP.get(i).serialNum == this.serialNum) {
                    switch (Type) {
                        case "BP":
                            Hoops.SoundPlusScore();
                            Hoops.speedDely2 = 5000;
                            Remove();
                            break;
                        case "ST":
                            System.out.println(1);
                            Hoops.SoundPlusScore();
                            Hoops.speedDely3 = 5000;
                            Remove();
                            break;
                        case "ES":
                            System.out.println(1);
                            Hoops.SoundPlusScore();
                            Hoops.speedDely1 = 5000;
                            Remove();
                            break;
                        case "HP":
                            System.out.println(1);
                            Hoops.SoundPlusScore();
                            Game.Health++;
                            Remove();
                            break;
                    }
                }
            }
        }

    }

    boolean EatenByPlayer(Player Player){
        return (Player.Left < this.Left) && (Player.Right > this.Right) && y == 825;
    }

    void Remove(){
        for (int i = Game.ArrayPowerUP.size() - 1; i > -1; i--) {
            if (Game.ArrayPowerUP.get(i).serialNum == this.serialNum) {
                Game.ArrayPowerUP.remove(i);
                t.stop();
            }
        }
    }
}