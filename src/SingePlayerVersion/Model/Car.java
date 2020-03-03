package SingePlayerVersion.Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Car {


    private BufferedImage carimg;
    private double posx;
    private double posy;
    private double rotation;
    private double speedx, speedy;
    private double rotationSpeed;
    private Shape hitBox;
    private boolean is_dead;

    private double driftanglex;
    private double driftangley;
    private boolean drifting;
    private int driftTime;
    private final int driftMaxTime = 20;




    public Car(){
        initImage();

        posx=128;
        posy=132;
        rotation = 1.1;
        speedx = 0;
        speedy = 0;
        rotationSpeed = 0.2;
        is_dead = false;




        //setup hitbox rectangle shape
        hitBox = new Rectangle(0,0,carimg.getWidth(),carimg.getHeight());
        AffineTransform at = new AffineTransform();
        at.translate(posx,posy);
        at.rotate(rotation);
        at.translate(-hitBox.getBounds().width/2, -hitBox.getBounds().height/2); //sets rotation axis to center of image
        hitBox = at.createTransformedShape(hitBox);

    }

    public void initImage(){
        carimg = null;
        try {
            //String localDir = System.getProperty("user.dir") + "/";
            carimg = ImageIO.read(new File("C:/Users/Genesis/Documents/GitHub/RaceCarGame/src/Resources/car.png"));
        } catch (IOException e) {
        }
    }

    public void rotate(double d){
        fixRads();
        rotation += d;
        fixHitBox();
    }
    public void move(String movement){
        fixRads();
        double degree = java.lang.Math.toDegrees(rotation);
        double x,y;

        double rox,roy;
        rox = Math.sin(rotation);
        roy = Math.cos(rotation);

        //acceleration momentum...
        if(movement.equals("FA")){
            if(speedx == 0){
                drifting = false;
                speedx = 2;
            }
            if(speedy == 0){
                drifting = false;
                speedy = 2;
            }
            speedx = speedx*1.2;
            speedy = speedy*1.2;

            if(speedx > 8){
                speedx = 8;
            }
            if(speedy >8){
                speedy = 8;
            }
        }


        //actual movement displacement
        x = rox*speedx;
        y = roy*speedy;




        //System.out.println("sin"+Math.sin(rotation));
        //System.out.println("cos"+Math.cos(rotation));

        //performing displacement
        if(movement.equals("F") || movement.equals("FA")){
            posx += x;
            posy -= y;

        }
        if(movement.equals("R")){
            //backward
            posx -= x;
            posy += y;
        }


        fixHitBox();
    }

    @SuppressWarnings("Duplicates")
    public void reduceMomentum(){

        if(speedx > 0){
            speedx = speedx/1.05;
        }
        if(speedx <0.5){
            speedx = 0;
        }
        if(speedy > 0){
            speedy = speedy/1.05;
        }
        if(speedy <0.5){
            speedy = 0;
        }



        move("F");
    }






    public void fixRads(){
        if(rotation >=6.28319){
            rotation = 0.0;
        }
        if(rotation <= -6.28319){
            rotation = 0.0;
        }
    }
    public void fixHitBox(){
        hitBox = new Rectangle(0,0,carimg.getWidth(),carimg.getHeight());
        AffineTransform at = new AffineTransform();
        at.translate(posx,posy);
        at.rotate(rotation);
        at.translate(-hitBox.getBounds().width/2, -hitBox.getBounds().height/2); //sets rotation axis to center of image
        hitBox = at.createTransformedShape(hitBox);
    }



    public double getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public double getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public BufferedImage getCarimg() {
        return carimg;
    }

    public double getRotation() {
        return rotation;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public Shape getHitBox() {
        return hitBox;
    }


    public boolean isIs_dead() {
        return is_dead;
    }

    public void setIs_dead(boolean is_dead) {
        this.is_dead = is_dead;
    }

}
