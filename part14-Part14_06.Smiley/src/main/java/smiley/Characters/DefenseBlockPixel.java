
package smiley.Characters;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class DefenseBlockPixel extends Character {
    
    public DefenseBlockPixel(int x, int y){
        super(new Polygon(-2, -2, -2, 2, 2, 2, 2, -2), x, y);
        this.setColor(Color.MAGENTA);
    }
    
}
