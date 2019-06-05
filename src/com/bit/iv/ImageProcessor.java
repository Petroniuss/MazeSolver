package com.bit.iv;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageProcessor {

    public static final Color pathColor = Color.GREEN;
    public static final int GRADIENT_FROM = 0x2193b0;
    public static final int GRADIENT_TO = 0x6dd5ed;

    private static Random random = new Random();

    public static BufferedImage readImage(String path) throws IOException {
        BufferedImage image = null;
        image = ImageIO.read(new File(path));
        return image;
    }

    public static void saveImage(BufferedImage img, String path) {
        File file = new File(path);
        try {
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            System.out.println("Unable to save given image");
        }
    }

    public static BufferedImage createImage(boolean[][] grid, int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_3BYTE_BGR);
        for(int j = 0; j < size; j++) {
            for(int i = 0; i < size; i++) {
                if(grid[j][i])
                    img.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
        return img;
    }

    public static Color generateRandomColor() {
        int r = random.nextInt(0xff);
        int g = random.nextInt(0xff);
        int b = random.nextInt(0xff);

        return new Color(r, g, b);
    }

}
