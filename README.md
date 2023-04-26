# Favorite Activity Viewer

## Application Details

This is a GUI application that allows the user to click on a name in the list in order to see an image of their 
favorite activity along with a brief description.

This was written in Java and uses the Java Swing library for the UI components.

## Requirements

- Java 8 or above

## How to Run Application

The main method is in the **Main.java file**, which is located in the default package.

## How to add a new entry to the activity viewer

This program was set up in such a way that a new entry can be added without having to edit any code!

To add a new entry, first a new `.txt` file must be added to the `names` directory.
The file's name should be the name of the user that is creating the entry.
For example, there is a text file called `alex_thimineur.txt` (my name) for my own entry.

The file should be formatted like this:
```
<Display Name Here>
<Image File Name Here>
<Activity Name Here>
<Activity Description Here>
```

Below is an example of how this file could look filled out:
```
Billy Bob
coding.png
Coding
I love coding, it is very fun! The project I am most proud of creating is an app that prints out Hello World!
```

The actual image to be displayed must be placed in the `images` folder. 
So in the above example, an image file named `coding.png` should exist in the `images` folder.
The app will take care of the linking between a name file and an image file.