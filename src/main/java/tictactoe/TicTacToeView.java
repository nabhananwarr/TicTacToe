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
import boardgame.ui.PositionAwareButton;
import java.awt.GridLayout;

//Nabhan Anwar
public class TicTacToeView extends JPanel{
    //connection between GUI and the game itself
    private TicTacToe game;
    private JPanel buttonPanel = new JPanel();
    private JLabel messageLabel;
    private PositionAwareButton[][] buttons;
    private GameUI root;

    private JTextArea output;

    public TicTacToeView(int wide, int tall, GameUI gameFrame){
        super();
        setLayout(new BorderLayout());
        root = gameFrame;

        game  = new TicTacToe(3, 3);  

        output = new JTextArea(20,40);
        add(output,BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
        makeButtonPanel();
        add(makeNewGameButton(),BorderLayout.EAST);
        add(makeButtonGrid(tall,wide), BorderLayout.CENTER);
    }

    private void makeButtonPanel(){
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        //first button starts a new game
        JButton b1 = new JButton("New game");
        b1.addActionListener(e->newGame());
        buttonPanel.add(b1);

    }

    private JButton makeNewGameButton(){
        JButton button = new JButton("New Game");
        button.addActionListener(e->newGame());
        return button;
    }

    private String getToken(){
        String token = "X";
        if(game.getCurrentPlayer() == 2){
            token = "O";
        }
        return token;
    }

    private JPanel makeButtonGrid(int tall, int wide){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
        for (int y=0; y<wide; y++){
            for (int x=0; x<tall; x++){ 
                //Create buttons and link each button back to a coordinate on the grid
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1); //made the choice to be 1-based
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                        setToken(e);
                                        checkGameState();
                                        });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

    //controller methods
    private void checkGameState(){
        int selection= 0;
        JOptionPane gameOver = new JOptionPane();
        if(game.isDone()){
            selection = gameOver.showConfirmDialog(null,
            game.getGameStateMessage(), "Play Again?", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
        }
    
    }   
    
    protected void updateView(){
        //update the labels on the buttons according to the model
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
            }
        }

    }

    protected void newGame(){
        game.newGame();
        game.setGrid(TicTacToe.newGrid(1,3,3));
        updateView();
    }

    private void setToken(ActionEvent e){

        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        game.takeTurn(clicked.getAcross(), clicked.getDown(),getToken());
        clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
        output.setText(game.toString());
        output.append(game.getGameStateMessage());
    }
    
}