package GeneticVersion.GUI;

import GameSettings.GameSettings;

import javax.swing.*;


public class GeneticMainFrame extends JFrame {

    GameSettings gs;
    GeneticGamePanel gp;

    public GeneticMainFrame(){
        init();
        gp.run();
    }


    public void init(){
        gs = new GameSettings();

        this.setBounds(200, 200, gs.getSizex()+10, gs.getSizey()+35);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gp = new GeneticGamePanel();
        this.add(gp);
        gp.setFocusable(true);

        this.setVisible(true);
    }

}
