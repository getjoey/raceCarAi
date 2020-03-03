package GeneticVersion.Controller;

import GeneticVersion.Model.Sequence;
import SingePlayerVersion.Model.Car;
import GeneticVersion.Model.CarGeneration;
import GeneticVersion.Model.Entry;
import SingePlayerVersion.Model.RaceMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GeneticController {

    private static GeneticController instance = null;
    private CarGeneration carGeneration;
    private RaceMap raceMap;
    private Random ran;
    private boolean all_dead = false;
    private int time = 0;


    private GeneticController(){
        carGeneration = new CarGeneration();
        raceMap = new RaceMap();
        ran = new Random();

        initializeGeneration();
    }

    public static synchronized GeneticController getInstance(){
        if(instance == null){
            instance = new GeneticController();
        }
        return instance;
    }



    @SuppressWarnings("Duplicates")
    //for each car, we add 100 random entries. and unit will be a random of 1-10 units
    public void initializeGeneration(){
        int entriesToDo = 100;
        for(int i=0; i< carGeneration.size();i++){

            for(int entries = 0; entries<entriesToDo; entries++){
                int random = ran.nextInt(3);
                switch(random){
                    case 0: {
                        Entry entry = new Entry("FA",1);
                        //System.out.println("A");
                        for(int x=0; x<ran.nextInt((i%20)+1)+1; x++){
                            carGeneration.get(i).getS().add(entry);
                        }
                    }
                    case 1: {
                        Entry entry = new Entry("FR",1);
                        //System.out.println("B");
                        for(int x=0; x<ran.nextInt((i%20)+1)+1; x++){
                            carGeneration.get(i).getS().add(entry);
                        }
                    }
                    case 2: {
                        Entry entry = new Entry("FL",1);
                        //System.out.println("C");
                        for(int x=0; x<ran.nextInt((i%20)+1)+1; x++){
                            carGeneration.get(i).getS().add(entry);
                        }
                    }
                }
            }
        }
    }

    //move each car 1 entry.
    public void move(){
        time++;

        if(!all_dead){
            for(int i=0; i< carGeneration.size();i++){

                int index = carGeneration.get(i).getS().getCurrentIndex();
                Entry e = carGeneration.get(i).getS().get(index);

                Car currentCar = carGeneration.get(i).getCar();

                if(!currentCar.isIs_dead()){
                    if(e.getMovement().equals("FA")){
                        currentCar.move("FA");
                    }
                    if(e.getMovement().equals("FR")){
                        currentCar.rotate(currentCar.getRotationSpeed());
                        currentCar.move("FA");
                    }
                    if(e.getMovement().equals("FL")){
                        currentCar.rotate(-currentCar.getRotationSpeed());
                        currentCar.move("FA");
                    }
                }


                //increment index
                carGeneration.get(i).getS().incrementIndex();

                //System.out.println("Reward = "+carGeneration.get(i).getS().getRewards());


            }
            checkCollisions();
            check_reward();


        }
        else{
            System.out.println("ALL DEAD");
            setNextGeneration();
            all_dead = false;
            time = 0;
        }

    }

    public void checkCollisions(){

        for(int i=0; i< carGeneration.size(); i++){
            if(!carGeneration.get(i).getCar().isIs_dead() && is_collided(carGeneration.get(i).getCar())){
                carGeneration.get(i).getCar().setIs_dead(true);
                carGeneration.get(i).getS().setDeathPoint(carGeneration.get(i).getS().getCurrentIndex());
            }
        }

        all_dead = true;
        for(int i=0; i<carGeneration.size(); i++){
            if(!carGeneration.get(i).getCar().isIs_dead()){
                all_dead = false;
            }
        }

    }

    @SuppressWarnings("Duplicates")
    public boolean is_collided(Car car){

        Boolean collided = false;
        for(int x=0; x<30;x++){
            for(int y=0; y<30; y++){
                int color = raceMap.getMapimg().getRGB((int)car.getPosx()-15 +x, (int)car.getPosy()-15+y);
                if(color == -16777216){
                    Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                    if(car.getHitBox().contains(point)){
                        collided = true;
                    }
                }
            }
        }
        return collided;
        //16777216 is black
    }

    @SuppressWarnings("Duplicates")
    public void check_reward(){

        for(int i=0; i<carGeneration.size(); i++)
        {
            Car car = carGeneration.get(i).getCar();
            for(int x=0; x<30;x++)
            {
                for(int y=0; y<30; y++)
                {
                    int color = raceMap.getMapimg().getRGB((int)car.getPosx()-15 +x, (int)car.getPosy()-15+y);

                    if(color == -2241843 && carGeneration.get(i).getS().getCheckPointIndex() % 22 == 0 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -7864299 && carGeneration.get(i).getS().getCheckPointIndex() % 22 == 1 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -1237980 && carGeneration.get(i).getS().getCheckPointIndex() % 22 == 2 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -32985 && carGeneration.get(i).getS().getCheckPointIndex() % 22 == 3 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -3584 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 4 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -14503604 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 5 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -16735512 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 6 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -12629812 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 7 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -6075996 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 8 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -4621737 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 9 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -20791 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 10 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -14066 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 11 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -1055568 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 12 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -4856291 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 13 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -6694422 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 14 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -9399618 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 15 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -3620889 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 16 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -4722502 && carGeneration.get(i).getS().getCheckPointIndex()% 22 == 17 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -4535059 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 18 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -1850146 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 19 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -4722453 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 20 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }

                    if(color == -2965528 && carGeneration.get(i).getS().getCheckPointIndex() % 22== 21 ){
                        Point point = new Point(x+(int)car.getPosx()-15,y+(int)car.getPosy()-15);
                        if(car.getHitBox().contains(point)){
                            carGeneration.get(i).getS().addReward(1);
                            carGeneration.get(i).getS().incrementCheckPointIndex();
                            carGeneration.get(i).getS().setTimestamp(time);
                        }
                    }



                }
            }
        }


    }


    @SuppressWarnings("Duplicates")
    public void setNextGeneration(){

        int bestPerformer = best_performer();
        Sequence bestSequence = carGeneration.get(bestPerformer).getS();

        //we get all movements up to 10 movements before death... gives chance to correct it
        Sequence realBest = new Sequence();
        for(int i=0; i < bestSequence.getDeathPoint();i++){
            if(i < bestSequence.getDeathPoint() - 10){
                realBest.add(bestSequence.get(i));
            }
        }

        //remake the next generation
        carGeneration = new CarGeneration();
        //add in initial sequence to be realBest sequence
        for(int i=0; i < carGeneration.size();i++){
            Sequence bestS = (Sequence)realBest.clone();
            carGeneration.get(i).setS(bestS);
            carGeneration.get(i).getS().resetIndex();
            //System.out.println(""+carGeneration.get(i).getS().clone());
        }

        //modify sequence slightly


        for(int i=0; i<carGeneration.size(); i++){
            for(int j=0; j<carGeneration.get(i).getS().size();j++){


                int rando = ran.nextInt(-1*j+carGeneration.get(i).getS().size()*5);
                if(rando == 0)
                { //1% chance of change
                    System.out.println("change "+rando);
                    int choice = ran.nextInt(3);
                    Entry entry = new Entry("FA",1);
                    if(choice == 0) {
                        entry = new Entry("FA",1);
                    }
                    if(choice == 1) {
                        entry = new Entry("FR",1);
                    }
                    if(choice == 2) {
                        entry = new Entry("FL",1);
                    }
                    carGeneration.get(i).getS().set(j,entry);

                 }

             }
         }




        initializeGeneration();



    }

    //determine best performer

    public int best_performer(){
        int bestReward = 0;
        int index = 0;
        for(int i=0; i<carGeneration.size();i++){
            if(carGeneration.get(i).getS().getRewards() > bestReward){
                bestReward = carGeneration.get(i).getS().getRewards();
                index = i;
            }
            if(carGeneration.get(i).getS().getRewards() == bestReward && bestReward != 0){

                if(carGeneration.get(i).getS().getTimestamp() < carGeneration.get(index).getS().getTimestamp()){
                    index = i;
                }
            }
        }

        return index;
    }

    public ArrayList<Integer> best_performers(){
        ArrayList<Integer> indexs = new ArrayList<Integer>();

        for(int j=0; j<22; j++){
            int index = 0;
            int bestTime = 10000000;
            for(int i=0; i<carGeneration.size();i++){
                if(bestTime > carGeneration.get(i).getS().getTimestampIndex(j)){
                    bestTime = carGeneration.get(i).getS().getTimestampIndex(j);
                    index = i;
                }
            }
            indexs.add(index);
        }

        return indexs;
    }


    public CarGeneration getCarGeneration() {
        return carGeneration;
    }

    public RaceMap getRaceMap() {
        return raceMap;
    }
}
