package GeneticVersion.Model;

import java.util.ArrayList;

public class CarGeneration extends ArrayList<CarTuple> {

    //private ArrayList<CarTuple> cars = new ArrayList<CarTuple>();
    private int generationSize = 300;


    public CarGeneration(){
        for(int i=0; i<generationSize; i++){

            this.add(new CarTuple());
        }
    }

}
