# Project Title
XO and Numeric TicTacToe with GUI

Simple overview of use/purpose.
The purpose of the program is to allow players to play XO and Numeric TicTacToe using GUI on the same computer.

## Description

An in-depth paragraph about your project and overview of use.
This object-oriented project is a TicTacToe simulator that allows users to play XO and Numeric TicTacToe with GUI. There is a vital use of polymorphism used in this project as XO and NumTTT are two types of games. Therefore, the BoardGame and Grid classes provide abstract methods that are to be defined by their descendants. The TicTacToe class defines BoardGame's abstract methods and contains methods that are used to run the game and common rules of both games, allows to be used for both XO and Numeric TicTacToe. The XOGrid class and NumTTTGrid class have methods that run specialized win checks and validation methods for their respective games. The Saveable interface is used by FileSaveLoad class to save either a string represntation of the grid or details the player. The Player class is for defining player name and their stats so it can be saved/loaded. The TextUI class has extra methods to play NumTTT on terminal but is not used as it was not required. The main mehod of TextUI simply runs the XO game and allows players to play by inputting their desired column and row, then outputs the grid. The GameUI class is responsible for the creation of the GUI of the TicTacToe games. Using the imported JFrame, JPanel, JLabel, JOptionPane, JFileChooser, JMenuBar, and JMenu classes, the GUI is desgined to be simply and operates to allow the users to switch between the two games. Using the help of TicTacToeView and NumTTTView classes, the GUI has a grid that the players can click on to play on the GUI itself rather than on CLI.

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.

The program should run on Windows, Mac, and Linux computers. It can be run in the terminal after starting scioer and using scioer shell. Java must be installed in your computer. The program uses imports from java.util. Note: My java version is 1.8 so my gradle build file is set to 1.8. 

### Executing program

* How to build and run the program
* Step-by-step bullets
```
-Firstly, cd into the A3 folder, 
-Next build and run using he following commands:
    >gradle build
    >gradle run
-Then, copy the line that is output from grade run. It must be run on terminal.
 It should read:
    >java -jar build/libs/A3.jar
-You may also open it from build/libs/A3.jar using file explorer

-To run XO from the command line (using TextUI):
    >java -cp build/classes/java/main tictactoe.TextUI
```
* include the expected output
The program should build and run without errors. The GUI should popup allowing you to play the two games. For the command line XO, you must input the column and row you want to insert your token and it prints the grid with your token.

## Limitations

What isn't done? What things cause errors?
Errors are handled, but when using the GUI, input is not completely validated when playing Numeric TicTacToe. Saving and loading files do not work as intended using the GUI, but the code for the functionality is there, just not implemented in the GUI.

## Author Information

Nabhan Anwar, 437-984-8842, nanwar@uoguelph.ca

## Development History

Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
* course content 
* course resources
* snippets from past workshops


