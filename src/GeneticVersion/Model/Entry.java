package GeneticVersion.Model;

public class Entry {

    String movement;
    int time;

    public Entry(String movement, int time){
        this.time = time;
        this.movement = movement;

    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
