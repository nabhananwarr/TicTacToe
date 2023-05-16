package tictactoe;

/**
 * This TicTacToe class defines BoardGame's abstract methods. Contains methods
 * that are used to run the game and rules of the game, allows to be used
 * for both XO and Numeric TicTacToe.
 * @author Nabhan Anwar  
 */
public class TicTacToe extends boardgame.BoardGame implements boardgame.Saveable{
 private int currentPlayer = 1;
 private String gameStateMessage;
 private boolean done = false;

    /**
     * This method calls the superclass to start the initiation of the TicTacToe game
     * @param wide
     * @param tall
     * @author Nabhan Anwar
     */
    public TicTacToe(int wide, int tall){
        super(wide, tall);
        setGameStateMessage("Welcome to TicTacToe");
    }

    /**
     * Returns the value of which player is currently playing (player 1 or 2)
     * @return currentPlayer
     * @author Nabhan Anwar
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Method to indicate that the game is over when done = true.
     * @param state indicates whether game is over
     * @author Nabhan Anwar
     */
    public void setGameOver(boolean state){
        done = state;
    }

    /* overridden methods from BoardGame*/
    /**
     * Validates input from the user about their choices, and then places their choice
     * into the correct position of the grid using across and tall as the column and row.
     * Calls methods to appropriately set the game message if someone wins or ties.
     * @param across 
     * @param tall
     * @param input
     * @author Nabhan Anwar
     */
    @Override
    public boolean takeTurn(int across, int down, String input){

        try{
            //validate location
            grid().validateLocation(across, down);
            //validate input
            grid().validateInput(input);
            //set token
            setValue(across, down, input);

            //if no winner, swtich to next player
            if(getWinner() == -1){
                switchPlayer();
                setGameStateMessage(nextPlayerMessage());
            } else{
                setGameStateMessage(gameOverMessage());
            }
        }catch(Exception e){
            setGameStateMessage(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return false;
    }

    /**
     * Left as stub as this method was not used.
     * @param across 
     * @param tall
     * @param input
     * @author Nabhan Anwar
     */
    @Override
    public  boolean takeTurn(int across, int down, int input){
        return false;
    }

    /**
     * Method that calls its superclass to set the grid.
     * @param grid
     * @author Nabhan Anwar
     */
    @Override
    public void setGrid(boardgame.Grid grid){
        super.setGrid(grid);
        setGameOver(false); 
    }


    /**
     * Boolean method to check if the game is done or not.
     * @return done
     * @author Nabhan Anwar
     */
    @Override
    public boolean isDone(){
        if(!done){
            //row win
            done = grid().horizontalWin();
        } 
        if(!done){
            //column win
            done = grid().verticalWin();
        } 
        if(!done){
            //diagonal win
            done = grid().diagonalWin();
        }
        if(!done){
            //tie
            done = grid().isFull();
        }
        
        return done;
    }

    /* get Winner needs to use the currentPlayer, the isFull() method (to identify tie game)
    to decide what to send back.   Has some duplicate functionality with isDone()
    because the original design was meant to allow lots of flexibility. */

    /**
     * Uses the win conditions to see if the current player won, if so, the current player is returned.
     * If the board is full and no one wins, then neither player is the winner
     * @return winner
     * @author Nabhan Anwar
     */
    @Override
    public int getWinner(){
        int winner = -1;
        //if win detected, set winner to current player
        if(grid().horizontalWin() || grid().verticalWin() || grid().diagonalWin()){
            winner = getCurrentPlayer();
        } else if(grid().isFull()){
            winner = 0;
        }
        return winner;
    }

    /**
     * Returns a string that represents the current state of the game
     * @return gameStateMessage
     * @author Nabhan Anwar
     */
    @Override
    public String getGameStateMessage(){
        return gameStateMessage;
    }

    //private helper methods
    /**
     * This method checks whose turn it is, then switches the turn
     * @author Nabhan Anwar
     */
    private void switchPlayer(){
        if(getCurrentPlayer() == 1){
            currentPlayer = 2;
        }else{
            currentPlayer = 1;
        }
    }

    /**
     * Method to cast Gamegrid and simplify code, written by Prof. Judi  
     * @return (GameGrid) getGrid())
     */
    private GameGrid grid(){
        return (GameGrid) getGrid();
    }

    /**
     * Sets the gameStateMessage to the a message that describes the state of the game.
     * @param msg
     * @author Nabhan Anwar
     */
    private void setGameStateMessage(String msg){
        gameStateMessage = msg;
    }

    /**
     * Returns the message to indicate whose turn it is
     * @return player + " please indicate where you would like to put your token."
     * @author Nabhan Anwar
     */
    private String nextPlayerMessage(){
        String player = "Player 1";
        if(currentPlayer == 2){
            player = "Player 2";
        }
        return(player + " please indicate where you would like to put your token.");
    }

    /**
     * Returns the message that indicates if the game was a tie or if a player won.
     * @return appropriate string to describe state
     * @author Nabhan Anwar
     */
    private String gameOverMessage(){
        if(getWinner() == 0){
            return("The Game was a tie!");
        } else{ 
            return("Congrats! Player " + getWinner() + " won the game!");
        }
    }

    //implementation methods
    /**
     * Provides a string representation of the game so it can be saved to a file
     * @return the last token that was played and the grid
     * @author Nabhan Anwar
     */
    @Override
    public String getStringToSave(){
        String token = "X";
        if(getCurrentPlayer() == 2){
            token = "O";
            return token + "\n" + grid().toString();
        }
        return token + "\n" + grid().toString();
    }

    /**
     * Takes a string representation loaded from a file so it can be put into a grid 
     * @param toLoad
     * @author Nabhan Anwar
     */
    @Override
    public void loadSavedString(String toLoad){
        if(toLoad.charAt(0) == 'X'){
            currentPlayer = 1;
        } else{
            currentPlayer = 2;
        }
        int row = 1;
        int col = 1;
        for(int i = 1; i < toLoad.length(); i++){
            grid().setValue(row, col, toLoad.charAt(i));
            row++;
            if(row == 3){
                row = 0;
                col++;
            }
        }
    }

    /**
     * Static method to facilitate changing the grid type without adding coupling
     * This is used by the user interfaces to select the game, written by Prof. Judi
     * @param kind
     * @param wide
     * @param tall
     * @return the game to play
     * @author Nabhan Anwar
     */
    public static GameGrid newGrid(int kind, int wide, int tall){
        if(kind == 1){
            return new XOGrid(wide,tall);
        }else{
            return new NumTTTGrid(wide,tall);
        }
    }

}