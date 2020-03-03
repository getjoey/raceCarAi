package GeneticVersion.GUI;

import GameSettings.GameSettings;
import GeneticVersion.Controller.GeneticController;
import SingePlayerVersion.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GeneticGamePanel extends JPanel implements Runnable {

    GameSettings gs;
    GeneticController gc;
    int counter = 0;

    public GeneticGamePanel(){
        gs = new GameSettings();
        this.setSize(gs.getSizex(),gs.getSizey());
        this.setVisible(true);

        gc = GeneticController.getInstance();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw map
        BufferedImage img = gc.getRaceMap().getMapimg();//controller.getRaceMap().getMapimg();
        g.drawImage(img, 0, 0, null);

        //draw cars
        for (int i = 0; i < gc.getCarGeneration().size(); i++) {
            img = gc.getCarGeneration().get(i).getCar().getCarimg();

            AffineTransform at = new AffineTransform();

            at.translate(gc.getCarGeneration().get(i).getCar().getPosx(), gc.getCarGeneration().get(i).getCar().getPosy());
            at.rotate(gc.getCarGeneration().get(i).getCar().getRotation());
            at.translate(-img.getWidth() / 2, -img.getHeight() / 2); //sets rotation axis to center of image
            // draw the image
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, at, null);

            //hitbox
            g.setColor(Color.cyan);
            ((Graphics2D) g).draw(gc.getCarGeneration().get(i).getCar().getHitBox());
        }
    }


    public void run(){
        while(true){
            repaint();

            try
            {
                Thread.sleep(10); //speed of movement
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();//tells you what happened
            }
        }
    }


}
