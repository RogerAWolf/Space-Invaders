
package smiley.Application;

import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

public class StartMenu {
    private Text startText;
    private BorderPane menuPane;
    
    public StartMenu() {
        this.startText = new Text("Press any key to start the game.");
        this.menuPane = new BorderPane();
    }

    public void createStartMenu() {
        //1.1 set style and size for start menu window
        this.menuPane.setStyle("-fx-background-color: black");
        this.menuPane.setPrefSize(1024, 952);

        //1.1 Creation of UI elements to be added to start menu
        this.startText.setStyle("-fx-fill: white; -fx-font-size: 30; -fx-stroke: blue; -fx-stroke-width: 1");
        this.menuPane.setCenter(this.startText);
    }
    
    public BorderPane getStartMenu(){
        return this.menuPane;
    }
}
