package smiley.Characters;

import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;

public class Alien extends Character {

    private double speed;
    private int pointValue;
    private int row;
    private int column;
    

    public Alien(int x, int y, int alienLevel, int row, int column) {        
        super(new Polygon(-20, -20, -20, 20, 20, 20, 20, -20), x, y);
        this.speed = alienLevel;
        this.pointValue = 5*alienLevel;
        switch (alienLevel) {
            case 1: this.setColor(Color.LIGHTYELLOW);   break;
            case 2: this.setColor(Color.YELLOW);        break;
            case 3: this.setColor(Color.ORANGE);        break;
            case 4: this.setColor(Color.RED);           break;
            case 5: this.setColor(Color.DARKRED);       break;
        }
        
        this.row = row;
        this.column = column;
    }

    public int getPointValue() {
        return this.pointValue;
    }
    
    public double getSpeed() {
        return this.speed;
    }
    
    public void setSpeed() {
        
    }
    
    public int getColumn(){
        return this.column;
    }
    
    public int getRow(){
        return this.row;
    }
}
