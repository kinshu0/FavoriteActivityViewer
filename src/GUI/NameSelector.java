package GUI;

import Listeners.NameSelectorListener;
import Models.ActivityContent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NameSelector extends JPanel {
    private ArrayList<ActivityContent> activityContent;
    private ArrayList<NameSelectorListener> listeners = new ArrayList<>();
    private JList<String> namesList;

    public NameSelector(NameSelectorListener listener) {
        this.setLayout(new BorderLayout(0, 5));
        this.setBorder(new EmptyBorder(3, 3, 3, 3 ));
        this.setPreferredSize(new Dimension(200, 100));
        listeners.add(listener);

        activityContent = new ArrayList<>();

        namesList = new JList();
        JScrollPane namesListScroll = new JScrollPane(namesList);

        File namesDirectory = new File("./names/");
        for (File file : namesDirectory.listFiles()) {
            if (file.isFile()) {
                try {
                    Scanner fileInput = new Scanner(file);
                    String displayName = fileInput.nextLine();
                    String imageName = fileInput.nextLine();
                    String imageTitle = fileInput.nextLine();
                    String imageDescription = fileInput.nextLine();
                    activityContent.add(new ActivityContent(file.getAbsolutePath(), displayName, imageName, imageTitle, imageDescription));
                } catch (IOException e) {
                    System.err.println("Failed to read in file " + file.getAbsolutePath());
                }
            }
        }

        ArrayList<String> names = getAllDisplayNamesAlphabetically();

        DefaultListModel<String> namesListModel = new DefaultListModel<>();
        for (String name : names) {
            namesListModel.addElement(name);
        }

        namesList.setModel(namesListModel);
        namesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                for (NameSelectorListener listener : listeners) {
                    listener.onNameSelected(getSelectedActivityContent());
                }
            }
        });

        this.add(namesListScroll, BorderLayout.CENTER);
    }

    public void selectName(int index) {
        if (namesList.getModel().getSize() > 0) {
            namesList.setSelectedIndex(index);
        }
    }

    public void addListener(NameSelectorListener listener) {
        listeners.add(listener);
    }

    private ActivityContent getSelectedActivityContent() {
        return activityContent
            .stream()
            .filter(activityContent -> activityContent.getDisplayName().equals(namesList.getSelectedValue()))
            .findFirst()
            .orElse(null);
    }

    private ArrayList<String> getAllDisplayNamesAlphabetically() {
        return activityContent.stream()
            .map(ActivityContent::getDisplayName)
            .sorted(String::compareToIgnoreCase)
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
