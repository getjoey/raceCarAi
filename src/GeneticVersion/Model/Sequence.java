package GeneticVersion.Model;

import java.util.ArrayList;

public class Sequence extends ArrayList<Entry> {

    private int currentIndex;
    private int rewards;
    private int checkPointIndex;
    private int deathPoint;
    private int timestamp;
    private ArrayList<Integer> timestampall;

    public Sequence(){
        currentIndex = 0;
        rewards = 0;
        checkPointIndex = 0;
        deathPoint = 0;
        timestamp = 0;
        timestampall = new ArrayList<Integer>();
    }


    public String toString(){
        String s = "";

        for(int i=0; i<this.size();i++){
            s = s+" "+this.get(i).getMovement();
        }

        return s;
    }

    public int getDeathPoint() {
        return deathPoint;
    }

    public void setDeathPoint(int deathPoint) {
        this.deathPoint = deathPoint;
    }

    public void incrementIndex(){
        currentIndex++;
    }

    public void resetIndex(){
        currentIndex = 0;
    }

    public void incrementCheckPointIndex(){
        checkPointIndex++;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void addReward(int reward){
        rewards = rewards + reward;
    }

    public int getCheckPointIndex() {
        return checkPointIndex;
    }

    public int getRewards() {
        return rewards;
    }

    public int getTimestampIndex(int index) {
        return timestampall.get(index);
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
