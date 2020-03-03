package GeneticVersion.GameLoop;

import GeneticVersion.Controller.GeneticController;

public class GeneticGameLoop implements Runnable {

        GeneticController controller;
        int threadTimer;

        public GeneticGameLoop(){
            controller = GeneticController.getInstance();
            threadTimer = 0;
        }

        public void run(){

            while(true){
                controller.move();

                try{
                    Thread.sleep(threadTimer);
                }
                catch(Exception e){

                }
            }

        }

}

