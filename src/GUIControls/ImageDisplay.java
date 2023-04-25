package GUIControls;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDisplay extends JLabel {
    private BufferedImage image;

    public ImageDisplay(String imageFilePath) {
        super(new ImageIcon(imageFilePath));
        setImage(imageFilePath);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    public void setImage(String imageFilePath) {
        try {
            image = ImageIO.read(new File(imageFilePath));
        } catch (IOException | NullPointerException e) {
        }
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            int newWidth = image.getWidth();
            int newHeight = image.getHeight();
            int boundWidth = this.getWidth();
            int boundHeight = this.getHeight();

            if (originalWidth > boundWidth) {
                newWidth = boundWidth;
                newHeight = (newWidth * originalHeight) / originalWidth;
            }

            if (newHeight > boundHeight) {
                newHeight = boundHeight;
                newWidth = (newHeight * originalWidth) / originalHeight;
            }
            
            g.drawImage(image, 0, 0, newWidth, newHeight, null);
        }

    }
}
