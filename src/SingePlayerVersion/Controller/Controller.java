package SingePlayerVersion.Controller;

import SingePlayerVersion.Model.Car;
import SingePlayerVersion.Model.RaceMap;

import java.awt.*;

public class Controller {

    Car car;
    RaceMap raceMap;
    static Controller instance = null;


    boolean rotateLeft = false;
    boolean rotateRight = false;
    boolean accelerating = false;
    boolean reversing = false;

    private Controller(){
        car = new Car();
        raceMap = new RaceMap();
    }

    public static synchronized Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }


    public void handleMovement(){


        if(accelerating){
            car.move("FA"); //foward
            reversing = false;
        }
        if(reversing){
            car.move("R"); //backward
        }
        if(rotateLeft){
            car.rotate(-car.getRotationSpeed());
        }
        if(rotateRight){
            car.rotate(car.getRotationSpeed());
        }

        if(accelerating || reversing){
            if(is_collided()){
                //car = new Car();
            }
        }
        else{
            car.reduceMomentum();
        }




    }


    public boolean is_collided(){

        Boolean collided = false;
        for(int x=0; x<30;x++){
            for(int y=0; y<30; y++){

                int color = raceMap.getMapimg().getRGB((int)car.getPosx()-15 +x, (int)car.getPosy()-15+y);
                //System.out.println(color);
                if(color == -16777216){
                    Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                    //System.out.println("x");
                    if(car.getHitBox().contains(point)){
                        collided = true;
                    }
                }
            }
        }

        return collided;
        //16777216 is black
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public RaceMap getRaceMap() {
        return raceMap;
    }

    public void setRaceMap(RaceMap raceMap) {
        this.raceMap = raceMap;
    }

    public boolean isRotateLeft() {
        return rotateLeft;
    }

    public void setRotateLeft(boolean rotateLeft) {
        this.rotateLeft = rotateLeft;
    }

    public boolean isRotateRight() {
        return rotateRight;
    }

    public void setRotateRight(boolean rotateRight) {
        this.rotateRight = rotateRight;
    }

    public boolean isAccelerating() {
        return accelerating;
    }

    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

    public boolean isReversing() {
        return reversing;
    }

    public void setReversing(boolean reversing) {
        this.reversing = reversing;
    }
}
