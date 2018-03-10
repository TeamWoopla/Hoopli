import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Player implements ActionListener {

    JLabel PlayerLbl = new JLabel();

    int x = 430;
    int Left, Right;
    boolean MoveOn = false;
    boolean MovePluse = true;

    int Width = 200;

    private Timer t = new Timer(4, this);

    Player(){
        Start.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    if (x > 0) {
                        MoveOn = true;
                        MovePluse = false;
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_D) {
                    if (x < Start.frame.getWidth() - PlayerLbl.getWidth() - 5) {
                        MoveOn = true;
                        MovePluse = true;
                    }
                }
            }
        });
        Start.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    MoveOn = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_D) {
                    MoveOn = false;
                }
            }
        });
        CheangPic();
        t.start();
    }

    void Draw(){
        PlayerLbl.setBounds(x, 825, Width, 125);
        Start.frame.add(PlayerLbl);
    }

    public void actionPerformed(ActionEvent e) {
        if (MoveOn){
            if(MovePluse){
                if (x < Start.frame.getWidth() - PlayerLbl.getWidth() - 5) {
                    x += 3;
                }
            } else {
                if (x > 0) {
                    x -= 3;
                }
            }
        }
        Left = x;
        Right = x + Width;
    }

    void CheangSize(){
        Width = 400;
        CheangPic();
    }

    void CheangSizeBack() {
        if (Width != 200){
            Width = 200;
            CheangPic();
        }
    }

    void CheangPic(){
        ImageIcon PlayerIcon = new ImageIcon("Pics\\PlayerHoop.png");
        Image image = PlayerIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(Width, 125,  java.awt.Image.SCALE_SMOOTH);
        PlayerIcon = new ImageIcon(newimg);
        PlayerLbl.setIcon(PlayerIcon);
    }
}
