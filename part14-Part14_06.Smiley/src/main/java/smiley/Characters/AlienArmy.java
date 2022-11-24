package smiley.Characters;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class AlienArmy {

    private ArrayList<Alien> aliens;
    private String direction;
    private String previousDirection;
    private double downCounter;
    private double averageSpeed;

    public AlienArmy() {
        this.aliens = new ArrayList<>();
        this.direction = "left";
        this.previousDirection = "left";
        this.downCounter = 0.01;

    }

    public void createArmy(int currentLevel) {
        ArrayList<String> levels = new ArrayList<>();
        
        try ( Scanner scanner = new Scanner(Paths.get("levels.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                levels.add(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        String[] parts = levels.get(currentLevel-1).split(",");
        
        double totalSpeed = 0;
        for(String part: parts){
            totalSpeed = totalSpeed + Integer.valueOf(part);
        }
        
        this.averageSpeed = totalSpeed / 5;
        
        for (int j = 1; j <= 5; j++) {
            for (int i = 0; i < 11; i++) {
                Alien alien = new Alien(212 + (60 * i), 450 - (60 * j), Integer.valueOf(parts[j-1]), j, i + 1);
                aliens.add(alien);
            }
        }
    }

    public ArrayList<Alien> getArmy() {
        return this.aliens;
    }

    public void removeFromArmy(Alien alien) {
        this.aliens.remove(alien);
    }

    public double getSpeed() {
        return this.averageSpeed/2 + 0.02 * (55 - this.aliens.size());
    }

    public double getLeftMostX() {
        double leftMostX = 1000;

        for (Alien alien : this.aliens) {
            if (alien.getCharacter().getTranslateX() < leftMostX) {
                leftMostX = alien.getCharacter().getTranslateX();
            }
        }

        return leftMostX;
    }

    public double getRightMostX() {
        double rightMostX = 0;

        for (Alien alien : this.aliens) {
            if (alien.getCharacter().getTranslateX() > rightMostX) {
                rightMostX = alien.getCharacter().getTranslateX();
            }
        }

        return rightMostX;
    }

    public double getLowestShip() {
        double lowestShipY = 0;

        for (Alien alien : this.aliens) {
            if (alien.getCharacter().getTranslateY() > lowestShipY) {
                lowestShipY = alien.getCharacter().getTranslateY();
            }
        }

        return lowestShipY;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getPreviousDirection() {
        return this.previousDirection;
    }

    public void direct() {
        if (this.getLeftMostX() < 48) {
            this.direction = "down";
            this.previousDirection = "left";
        }

        if (this.getRightMostX() > 976) {
            this.direction = "down";
            this.previousDirection = "right";
        }

        if (this.getDirection().equals("down")) {
            this.downCounter = this.downCounter + this.getSpeed();
            if (this.downCounter > 30) {
                this.downCounter = 0;
            }
        }

        if (this.downCounter == 0) {
            if (this.getPreviousDirection().equals("right")) {
                this.direction = "left";
            } else if (this.getPreviousDirection().equals("left")) {
                this.direction = "right";
            }
        }
    }
}
