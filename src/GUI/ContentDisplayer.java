package GUI;

import GUIControls.ImageDisplay;
import Models.ActivityContent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ContentDisplayer extends JPanel {
    JTextPane contentText;
    ImageDisplay imageDisplay;

    public ContentDisplayer() {
        this.setLayout(new BorderLayout());
        setBorder(new EmptyBorder(3, 3, 3, 3));

        contentText = new JTextPane();
        contentText.setText("");
        contentText.setEditable(false);
        contentText.setContentType("text/html");
        contentText.setBackground(getBackground());
        this.add(contentText, BorderLayout.NORTH);

        imageDisplay = new ImageDisplay(null);
        // JScrollPane imageScroll = new JScrollPane(imageDisplay);
        this.add(imageDisplay, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                imageDisplay.repaint();
            }
        });
    }

    public void setContent(ActivityContent activityContent) {
        contentText.setText(String.format("<h1>%s</h1><h2>%s</h2>%s",
                activityContent.getDisplayName(),
                activityContent.getActivityTitle(),
                activityContent.getActivityDescription()));

        imageDisplay.setImage("./images/" + activityContent.getImageName());
        imageDisplay.repaint();
    }
}
