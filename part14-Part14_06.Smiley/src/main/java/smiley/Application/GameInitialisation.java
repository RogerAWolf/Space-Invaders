package smiley.Application;

import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import smiley.Characters.Alien;
import smiley.Characters.AlienArmy;
import smiley.Characters.DefenseBlockPixel;
import smiley.Characters.DefenseBlocks;
import smiley.Characters.Ship;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class GameInitialisation {

    private ArrayList<Alien> aliens;
    private ArrayList<DefenseBlockPixel> pixels;
    private Ship ship;
    private BorderPane gamePane;
    private AlienArmy army;
    private Text endMessage;
    private Text pointsText;
    private String gameState;
    private int points;
    private int level;

    public GameInitialisation() {
        this.aliens = new ArrayList();
        this.pixels = new ArrayList();
        this.ship = new Ship(512, 800);
        this.gamePane = new BorderPane();
        this.army = new AlienArmy();
        this.endMessage = new Text("");
        this.endMessage.setStyle("-fx-font-size: 100; -fx-stroke: blue; -fx-stroke-width: 2");
        this.endMessage.setFill(Color.WHITE);
        this.points = 0;
        this.pointsText = new Text(425, 906, "Points: " + this.points);
        this.gameState = "menu";
        this.level = 1;
    }

    public void createGame() {

        //2 Creation of game pane
        this.gamePane.setStyle("-fx-background-color: black");
        this.gamePane.setPrefSize(1024, 952);

        //2.1 Add this.ship to game
        this.gamePane.getChildren().add(this.ship.getCharacter());

        army.createArmy(this.level);
        army.getArmy().forEach(alien -> {
            this.addToGame(alien);
            aliens.add(alien);
        });

        //2.3 Create defense blocks and add to game and this.pixels ArrayList
        DefenseBlocks blocks = new DefenseBlocks();
        blocks.createBlocks();
        blocks.getBlocks().forEach(pixel -> {
            this.gamePane.getChildren().add(pixel.getCharacter());
            this.pixels.add(pixel);
        });

        //2.4 Create UI elements (bottom bar, points, end message)
        Polygon bottomBar = new Polygon(0, 832, 0, 952, 1024, 952, 1024, 832);
        bottomBar.setFill(Color.DARKGRAY);

        this.pointsText.setStyle("-fx-font-size: 40; -fx-stroke: white; -fx-stroke-width: 1");
        this.pointsText.setFill(Color.BLACK);

        VBox endTextBox = new VBox();
        endTextBox.setAlignment(Pos.CENTER);
        endTextBox.setPrefHeight(832);
        endTextBox.getChildren().add(this.endMessage);

        VBox pointsTextBox = new VBox();
        pointsTextBox.setAlignment(Pos.CENTER);
        pointsTextBox.setPrefHeight(120);
        pointsTextBox.getChildren().add(this.pointsText);
        
        
        //and add all of them to game
        this.gamePane.getChildren().add(bottomBar);
        this.gamePane.setTop(endTextBox);
        this.gamePane.setBottom(pointsTextBox);
    }

    public Ship getShip() {
        return this.ship;
    }

    public BorderPane getGame() {
        return this.gamePane;
    }

    public ArrayList<Alien> getAliens() {
        return this.aliens;
    }

    public ArrayList<DefenseBlockPixel> getPixels() {
        return this.pixels;
    }

    public AlienArmy getAlienArmy() {
        return this.army;
    }

    public Text getEndMessage() {
        return this.endMessage;
    }

    public void setEndMessage(String message) {
        this.endMessage.setText(message);
    }

    public Text getPointsText() {
        return this.pointsText;
    }

    public void setPoints(Alien alien) {
        this.points = this.points + alien.getPointValue();
        this.pointsText.setText("Points: " + this.points);
    }

    public String getGameState() {
        return this.gameState;
    }

    public void setGameState(String state) {
        this.gameState = state;
    }

    public int getLevel() {
        return this.level;
    }

    public void increaseLevel() {
        this.level++;
    }

    public int getPoints() {
        return this.points;
    }

    public void addToGame(Alien alien) {
        this.gamePane.getChildren().add(alien.getCharacter());
    }

    public void removeFromGame(Alien alien) {
        this.gamePane.getChildren().remove(alien.getCharacter());
    }

}
