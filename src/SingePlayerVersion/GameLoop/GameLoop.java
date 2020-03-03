package SingePlayerVersion.GameLoop;

import SingePlayerVersion.Controller.Controller;
import SingePlayerVersion.GUI.GamePanel;

public class GameLoop implements Runnable {

    Controller controller;
    GamePanel gp;
    int threadTimer;

    public GameLoop(GamePanel drawPanel){
        gp = drawPanel;
        controller = Controller.getInstance();
        threadTimer = 25;
    }

    public void run(){

        while(true){
            controller.handleMovement();
            gp.paint();


            try{
                Thread.sleep(threadTimer);
            }
            catch(Exception e){

            }
        }

    }

}
