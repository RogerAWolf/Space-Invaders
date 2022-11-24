
package smiley.Characters;

import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;


public class Ship extends Character {
    private double speed;
    private int numberOfLives;
            
    public Ship(int x, int y){
        super(new Polygon(-30, 0, -30, -20, -28, -20, -28, -22, -26, -22, -26, -24, -6, -24, -6, -30, -2, -30, -2, -38, 2, -38, 2, -30, 6, -30, 6, -24, 26, -24, 26, -22, 28, -22, 28, -20, 30, -20, 30, 0), x, y);
        this.speed = 5;
        this.setColor(Color.AQUAMARINE);
        this.numberOfLives = 3;
        
    }
    
    public double getSpeed(){
        return this.speed;
    }
    
    public void shipDeath(){
        this.numberOfLives--;
    }
    
    public int getNumberOfLives(){
        return this.numberOfLives;
    }
    
}
