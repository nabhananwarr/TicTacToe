package tictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

/**
 * This class is the UI for the game and allows players to play the two TicTacToe games using a GUI.
 * @author Nabhan Anwar
 */
public class GameUI extends JFrame{
    private TicTacToe game;
    private Player player = new Player();
    private JPanel gamePanel = new JPanel();
    private JLabel messageLabel;
    private JMenuBar menuBar;

    public GameUI(){
        super();
        setTitle("Welcome to TicTacToe!"); //parameter from constructor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        makeMenu();
        setJMenuBar(menuBar);

        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(makeButtonPanel(),BorderLayout.EAST);
        start();
    }

    private JPanel makeButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeXOButton());
        buttonPanel.add(makeNumTTTButton());
        buttonPanel.add(makeFileChooserButton());
        return buttonPanel;
    }

    private JButton makeXOButton(){
        JButton button = new JButton("Play XO TicTacToe");
        button.addActionListener(e->xoTicTacToe());
        return button;
    }

    private JButton makeNumTTTButton(){
        JButton button = new JButton("Play Numeric TicTacToe");
        button.addActionListener(e->numTTT());
        return button;
    }

    private JButton makeFileChooserButton(){
        JButton button = new JButton("Load from file");
        button.addActionListener(e->fileChooser());
        return button;
    }

    private JPanel startupMessage(){
        JPanel temp = new JPanel();
        temp.add(new JLabel("Starting the game!"));
        return temp;
    }

    public void makeMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Save game to file");
        menu.add(item);
        menuBar.add(menu);
        item.addActionListener(e->saveFile());

        item = new JMenuItem("Save player profile");
        menu.add(item);
        menuBar.add(menu);
        item.addActionListener(e->savePlayer());
    }

    public void start(){
        gamePanel.removeAll();
        gamePanel.add(startupMessage());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    protected void saveFile(){
        String fileName = JOptionPane.showInputDialog("Enter file name");
        FileSaveLoad.save(game, fileName, "assets");
    }
    protected void savePlayer(){
        String name = JOptionPane.showInputDialog("Enter Player name");
        String fileName = JOptionPane.showInputDialog("Enter file name");
        player.setName(name);
        FileSaveLoad.save(player, fileName, "assets");
    }

    protected void xoTicTacToe(){
        gamePanel.removeAll();
        gamePanel.add(new TicTacToeView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    protected void numTTT(){
        gamePanel.removeAll();
        gamePanel.add(new NumTTTView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
         
    }

    protected void fileChooser(){
        gamePanel.removeAll();
        gamePanel.add(new FileChooser());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
         
    }

    public static void main(String[] args){
        GameUI ui = new GameUI();
        ui.setVisible(true);
    }
}
