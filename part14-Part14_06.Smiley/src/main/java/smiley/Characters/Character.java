package smiley.Characters;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {

    private Polygon character;
    private boolean isAlive;

    public Character(Polygon polygon, int x, int y) {
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.isAlive = true;
    }

    public Polygon getCharacter() {
        return this.character;
    }

    public void moveLeft(double speed) {
        this.character.setTranslateX(this.character.getTranslateX() - speed);
    }

    public void moveRight(double speed) {
        this.character.setTranslateX(this.character.getTranslateX() + speed);
    }

    public void moveDown(double speed) {
        this.character.setTranslateY(this.character.getTranslateY() + speed);
    }

    public void moveUp(double speed) {
        this.character.setTranslateY(this.character.getTranslateY() - speed);
    }

    public void setColor(Paint color) {
        this.character.setFill(color);
    }

    public boolean collide(Character other) {
        Shape collisionArea = Shape.intersect(this.character, other.getCharacter());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
    
    public boolean isAlive(){
        return this.isAlive;
    }
    
    public void setAlive(boolean alive){
        this.isAlive = alive;
    }

}
