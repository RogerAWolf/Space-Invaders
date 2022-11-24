package smiley.Characters;

import java.util.ArrayList;

public class DefenseBlocks {

    private ArrayList<DefenseBlockPixel> pixels;

    public DefenseBlocks() {
        this.pixels = new ArrayList<>();
    }

    public void createBlocks() {
        int xOffset = 120;
        int yOffset = 670;
        for (int k = 0; k < 4; k++) {
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 20; i++) {
                    if (i > 16) {
                        DefenseBlockPixel pixel = new DefenseBlockPixel(xOffset + i * 5 + k * 230, yOffset + j * 5 + (i - 16) * 5);
                        pixels.add(pixel);
                    } else if (i < 3) {
                        DefenseBlockPixel pixel = new DefenseBlockPixel(xOffset + i * 5 + k * 230, yOffset + j * 5 + ((3-i)*5));
                        pixels.add(pixel);
                    } else {
                        DefenseBlockPixel pixel = new DefenseBlockPixel(xOffset + i * 5 + k * 230, yOffset + j * 5);
                        pixels.add(pixel);
                    }

                }
            }

        }
    }

    public ArrayList<DefenseBlockPixel> getBlocks() {
        return this.pixels;
    }
}
