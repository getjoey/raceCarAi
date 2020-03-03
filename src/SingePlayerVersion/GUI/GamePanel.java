package SingePlayerVersion.GUI;

import SingePlayerVersion.Controller.Controller;
import GameSettings.GameSettings;
import SingePlayerVersion.GameLoop.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements KeyListener {

    GameSettings gs;
    Controller controller;
    GameLoop gameLoop;
    int counter = 0;

    public GamePanel(){
        gs = new GameSettings();
        this.setSize(gs.getSizex(),gs.getSizey());
        this.setVisible(true);

        controller = Controller.getInstance();

        this.addKeyListener(this);

        gameLoop = new GameLoop(this);
        Thread t = new Thread(gameLoop);
        t.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        BufferedImage img = controller.getRaceMap().getMapimg();
        g.drawImage(img,0,0,null);


        //draw car
        img = controller.getCar().getCarimg();
        AffineTransform at = new AffineTransform();

        at.translate(controller.getCar().getPosx(), controller.getCar().getPosy());
        at.rotate(controller.getCar().getRotation());
        at.translate(-img.getWidth()/2, -img.getHeight()/2); //sets rotation axis to center of image
        // draw the image
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, at, null);

        g.setColor(Color.cyan);
        ((Graphics2D) g).draw(controller.getCar().getHitBox());

    }

    public void paint(){
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == 37){
            controller.setRotateLeft(true);
        }
        if(e.getKeyCode() == 39){
            controller.setRotateRight(true);
        }
        if(e.getKeyCode() == 38){
            controller.setAccelerating(true);
        }
        if(e.getKeyCode() == 40){
            controller.setReversing(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 37){
            controller.setRotateLeft(false);
        }
        if(e.getKeyCode() == 39){
            controller.setRotateRight(false);
        }
        if(e.getKeyCode() == 38){
            controller.setAccelerating(false);
        }
        if(e.getKeyCode() == 40){
            controller.setReversing(false);
        }

    }

}
