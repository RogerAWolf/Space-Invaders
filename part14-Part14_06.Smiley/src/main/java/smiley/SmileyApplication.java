package smiley;

import smiley.Application.GameInitialisation;
import smiley.Application.StartMenu;
import smiley.Application.NextLevelScreen;
import smiley.Characters.Alien;
import smiley.Characters.Laser;

import javafx.animation.AnimationTimer;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SmileyApplication extends Application {

    @Override
    public void start(Stage window) {
        ArrayList<Laser> friendlyLasers = new ArrayList();
        ArrayList<Laser> hostileLasers = new ArrayList();

        StartMenu startMenu = new StartMenu();
        startMenu.createStartMenu();
        GameInitialisation gameInit = new GameInitialisation();
        NextLevelScreen nextLevelScreen = new NextLevelScreen();

        //1.2 Create scenes and add first scene (start menu) to window
        Scene menu = new Scene(startMenu.getStartMenu());
        Scene game = new Scene(gameInit.getGame());
        Scene nextLevel = new Scene(nextLevelScreen.getNextLevelScreen());
        window.setTitle("Space Invaders");
        window.setScene(menu);
        window.show();

        HashMap<KeyCode, Boolean> pressedKeys = new HashMap<>();

        menu.setOnKeyPressed(event -> {
            gameInit.setGameState("gameplay");
            gameInit.createGame();
            window.setScene(game);
        });

        game.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        game.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });

        nextLevel.setOnKeyPressed(event -> {
            gameInit.getGame().getChildren().clear();
            gameInit.setEndMessage("");
            gameInit.createGame();
            gameInit.setGameState("gameplay");
            window.setScene(game);
        });
        
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (gameInit.getGameState().equals("gameplay")) {
                    if (pressedKeys.getOrDefault(KeyCode.LEFT, false) && gameInit.getShip().getCharacter().getTranslateX() > 48) {
                        gameInit.getShip().moveLeft(gameInit.getShip().getSpeed());
                    }

                    if (pressedKeys.getOrDefault(KeyCode.RIGHT, false) && gameInit.getShip().getCharacter().getTranslateX() < 976) {
                        gameInit.getShip().moveRight(gameInit.getShip().getSpeed());
                    }

                    if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && friendlyLasers.size() < 1) {
                        Laser laser = new Laser((int) gameInit.getShip().getCharacter().getTranslateX(), (int) gameInit.getShip().getCharacter().getTranslateY() - 41, true);
                        friendlyLasers.add(laser);
                        gameInit.getGame().getChildren().add(laser.getCharacter());
                    }

                    if (Math.random() < gameInit.getLevel()*0.01) {
                        int bottomRow = 5;
                        for (Alien alien : gameInit.getAliens()) {
                            if (alien.getRow() < bottomRow) {
                                bottomRow = alien.getRow();
                            }
                        }

                        ArrayList<Integer> existing = new ArrayList<>();

                        for (Alien alien : gameInit.getAliens()) {
                            if (alien.getRow() == bottomRow) {
                                existing.add(alien.getColumn());
                            }
                        }

                        int randomShip = 0;

                        while (!existing.contains(randomShip)) {
                            randomShip = (int) (Math.floor(Math.random() * 11) + 1);
                        }

                        for (Alien alien : gameInit.getAliens()) {
                            if (alien.getColumn() == randomShip && alien.getCharacter().getTranslateY() == gameInit.getAlienArmy().getLowestShip()) {
                                Laser laser = new Laser((int) alien.getCharacter().getTranslateX(), (int) alien.getCharacter().getTranslateY() + 25, false);
                                hostileLasers.add(laser);
                                gameInit.getGame().getChildren().add(laser.getCharacter());
                            }
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        Polygon polygon = new Polygon(-30, 0, -30, -20, -28, -20, -28, -22, -26, -22, -26, -24, -6, -24, -6, -30, -2, -30, -2, -38, 2, -38, 2, -30, 6, -30, 6, -24, 26, -24, 26, -22, 28, -22, 28, -20, 30, -20, 30, 0);
                        polygon.setTranslateX(100 + 80 * i);
                        polygon.setTranslateY(49);
                        if (i < gameInit.getShip().getNumberOfLives()) {
                            polygon.setFill(Color.AQUAMARINE);
                        }
                        gameInit.getGame().getChildren().add(polygon);
                    }

                    gameInit.getAlienArmy().direct();

                    gameInit.getAliens().forEach(alien -> {
                        switch (gameInit.getAlienArmy().getDirection()) {
                            case "left":
                                alien.moveLeft(gameInit.getAlienArmy().getSpeed());
                                break;
                            case "right":
                                alien.moveRight(gameInit.getAlienArmy().getSpeed());
                                break;
                            case "down":
                                alien.moveDown(gameInit.getAlienArmy().getSpeed());
                                break;
                        }

                        if (alien.getCharacter().getTranslateY() > 640) {
                            gameInit.getPixels().forEach(pixel -> {
                                if (alien.collide(pixel)) {
                                    pixel.setAlive(false);
                                }
                            });
                        }
                    });

                    friendlyLasers.forEach(laser -> {
                        laser.moveUp(laser.getSpeed());
                        if (laser.getCharacter().getTranslateY() < 0) {
                            laser.setAlive(false);
                        }

                        gameInit.getAliens().forEach(alien -> {
                            if (laser.collide(alien)) {
                                laser.setAlive(false);
                                alien.setAlive(false);
                                gameInit.getAlienArmy().removeFromArmy(alien);
                            }
                        });

                        gameInit.getPixels().forEach(pixel -> {
                            if (laser.collide(pixel)) {
                                laser.setAlive(false);
                                pixel.setAlive(false);
                            }
                        });
                    });

                    hostileLasers.forEach(laser -> {
                        laser.moveDown(laser.getSpeed());
                        if (laser.getCharacter().getTranslateY() > 832) {
                            laser.setAlive(false);
                        }

                        if (laser.collide(gameInit.getShip())) {
                            hostileLasers.forEach(hostileLaser -> {
                                hostileLaser.setAlive(false);
                            });
                            gameInit.getShip().shipDeath();
                        }

                        if (laser.getCharacter().getTranslateY() > 660 && laser.getCharacter().getTranslateY() < 730) {
                            gameInit.getPixels().forEach(pixel -> {
                                if (laser.collide(pixel)) {
                                    laser.setAlive(false);
                                    pixel.setAlive(false);
                                }
                            });
                        }
                    });

                    hostileLasers.stream()
                            .filter(laser -> !laser.isAlive())
                            .forEach(laser -> gameInit.getGame().getChildren().remove(laser.getCharacter()));
                    hostileLasers.removeAll(hostileLasers.stream()
                            .filter(laser -> !laser.isAlive())
                            .collect(Collectors.toList()));

                    friendlyLasers.stream()
                            .filter(laser -> !laser.isAlive())
                            .forEach(laser -> gameInit.getGame().getChildren().remove(laser.getCharacter()));
                    friendlyLasers.removeAll(friendlyLasers.stream()
                            .filter(laser -> !laser.isAlive())
                            .collect(Collectors.toList()));

                    gameInit.getAliens().stream()
                            .filter(alien -> !alien.isAlive())
                            .forEach(alien -> {
                                gameInit.getGame().getChildren().remove(alien.getCharacter());
                                gameInit.setPoints(alien);
                            });
                    gameInit.getAliens().removeAll(gameInit.getAliens().stream()
                            .filter(alien -> !alien.isAlive())
                            .collect(Collectors.toList()));

                    hostileLasers.stream()
                            .filter(laser -> !laser.isAlive())
                            .forEach(laser -> gameInit.getGame().getChildren().remove(laser.getCharacter()));
                    hostileLasers.removeAll(hostileLasers.stream()
                            .filter(laser -> !laser.isAlive())
                            .collect(Collectors.toList()));

                    gameInit.getPixels().stream()
                            .filter(pixel -> !pixel.isAlive())
                            .forEach(pixel -> gameInit.getGame().getChildren().remove(pixel.getCharacter()));
                    gameInit.getPixels().removeAll(gameInit.getPixels().stream()
                            .filter(pixel -> !pixel.isAlive())
                            .collect(Collectors.toList()));

                    if (gameInit.getAlienArmy().getLowestShip() > 740 || gameInit.getShip().getNumberOfLives() <= 0) {
                        gameInit.setEndMessage("GAME OVER");
                        gameInit.setGameState("game over");
                    }

                    if (gameInit.getAliens().isEmpty()) {
                        gameInit.setEndMessage("YOU WIN!");
                        gameInit.setGameState("next level");
                        gameInit.increaseLevel();
                        nextLevelScreen.createNextLevelScreen(gameInit.getLevel(), gameInit.getPoints());
                        window.setScene(nextLevel);
                    }
                }
            }
        }
                .start();
    }

    public static void main(String[] args) {
        launch(SmileyApplication.class);
    }

}
