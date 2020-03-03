package SingePlayerVersion.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RaceMap {
    private BufferedImage mapimg;

    public RaceMap(){
        init();
    }


    public void init(){
        mapimg = null;
        try {
            //String localDir = System.getProperty("user.dir") + "/";
            //mapimg = ImageIO.read(new File("C:/Users/Genesis/Documents/GitHub/RaceCarGame/src/Resources/map.png"));
            mapimg = ImageIO.read(new File("src/Resources/GeneticMarkedMap.png"));
        } catch (IOException e) {
        }
    }


    public BufferedImage getMapimg() {
        return mapimg;
    }


}
