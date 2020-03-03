package GeneticVersion.Model;

import SingePlayerVersion.Model.Car;

public class CarTuple {

    private Sequence s;
    private Car car;

    public CarTuple(){
        s = new Sequence();
        car = new Car();
    }

    public Sequence getS() {
        return s;
    }

    public void setS(Sequence s) {
        this.s = s;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
