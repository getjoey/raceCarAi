package SingePlayerVersion.GUI;

import GameSettings.GameSettings;

import javax.swing.*;


public class MainFrame extends JFrame {

    GameSettings gs;
    GamePanel gp;

    public MainFrame(){
        init();
    }


    public void init(){
        gs = new GameSettings();

        this.setBounds(200, 200, gs.getSizex()+10, gs.getSizey()+35);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gp = new GamePanel();
        this.add(gp);
        gp.setFocusable(true);

        this.setVisible(true);
    }

    public GamePanel getGp() {
        return gp;
    }
}
