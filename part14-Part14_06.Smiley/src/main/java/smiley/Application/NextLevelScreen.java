package smiley.Application;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class NextLevelScreen {

    private Text nextRoundText;
    private Text levelText;
    private BorderPane nextLevelPane;
    private Text pointsText;

    public NextLevelScreen() {
        this.nextLevelPane = new BorderPane();
        this.nextRoundText = new Text("NEXT ROUND:");
        this.nextRoundText.setStyle("-fx-fill: white; -fx-font-size: 100; -fx-stroke: blue; -fx-stroke-width: 2");
        this.levelText = new Text("");
        this.levelText.setStyle("-fx-fill: white; -fx-font-size: 200; -fx-stroke: blue; -fx-stroke-width: 2");
        this.pointsText = new Text("");
        this.pointsText.setStyle("-fx-font-size: 40; -fx-stroke: white; -fx-stroke-width: 1");
    }

    public void createNextLevelScreen(int level, int points) {
        //1.1 set style and size for start menu window
        this.nextLevelPane.getChildren().clear();

        this.nextLevelPane.setStyle("-fx-background-color: black");
        this.nextLevelPane.setPrefSize(1024, 952);

        this.levelText.setText(Integer.toString(level));
        this.pointsText.setText("Points so far: " + points);

        VBox levelTextBox = new VBox();
        levelTextBox.setPrefHeight(832);
        levelTextBox.setAlignment(Pos.CENTER);
        levelTextBox.getChildren().addAll(this.nextRoundText, this.levelText, this.pointsText);

        VBox pointsTextBox = new VBox();
        pointsTextBox.setPrefHeight(120);
        pointsTextBox.setAlignment(Pos.CENTER);
        Text continueText = new Text("Press SPACE to continue");
        continueText.setStyle("-fx-fill: white; -fx-font-size: 30");
        pointsTextBox.getChildren().add(continueText);

        this.nextLevelPane.setTop(levelTextBox);
        this.nextLevelPane.setBottom(pointsTextBox);
    }

    public BorderPane getNextLevelScreen() {
        return this.nextLevelPane;
    }
}
