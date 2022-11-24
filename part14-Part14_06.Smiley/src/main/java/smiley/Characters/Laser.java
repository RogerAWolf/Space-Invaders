
package smiley.Characters;

import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;

public class Laser extends Character {
    private double speed;
    
    public Laser(int x, int y, boolean friendly){
        super(new Polygon(-1, -5, -1, 5, 1, 5, 1, -5), x, y);
        if(friendly){
            this.speed = 10;
            this.setColor(Color.BLUE);
        } else {
            this.speed = 5;
            this.setColor(Color.GREEN);
        }
    }
    
    public double getSpeed(){
        return this.speed;
    }
}
