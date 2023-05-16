package tictactoe;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The TextUI class handles all the input and output for the game. Note: there are some methods
 * were used to test NumericTTT nut are not used as it is not required.
 * @author Nabhan Anwar
 */
public class TextUI{
    private TicTacToe game = new TicTacToe(3,3);
    private Scanner input = new Scanner(System.in);
    private int acrossVal;
    private int downVal;
    private ArrayList<String> odds = new ArrayList<>(Arrays.asList("1", "3", "5", "7", "9")); 
    private ArrayList<String> evens = new ArrayList<>(Arrays.asList("0", "2", "4", "6", "8"));

    public TextUI(){        
        game = new TicTacToe(3,3);
        System.out.println(game.getGameStateMessage());
        game.setGrid(TicTacToe.newGrid(1,3,3));
    }


    //This method was used to test if loading works.
    public boolean askToLoad(){
        System.out.println("Would you like to load a board?");
        char answer = '?';
        int i = 0;
        //uses a loop to ask user for input until it is a valid input.
        while(i == 0){
            try{
                System.out.print("Type Y/N: ");
                answer = input.next().charAt(0);
                if(answer == 'Y' || answer == 'y'){
                    return true;
                } else if(answer == 'N' || answer == 'n'){
                    return false;
                } else{
                    throw new Exception();
                } 
            } catch (Exception e){
                System.out.println("Invalid input. Try again!");
            }
        }
        return true;
    }
    
    //This method was used to test if loading works.
    public String getFileName(){
        String file = "";
        System.out.print("Enter file name to load from: ");
        file = input.next();
        return file;
    }

    /**
     * This method is the part that runs the XO game on the terminal.
     * @author Nabhan Anwar
     */
    public void playXO(){
        while(!game.isDone()){
            getPosition();
            if(acrossVal == 0){
                game.setGameOver(true);
                break;
            }
            try{
                game.takeTurn(acrossVal,downVal,getToken());
                System.out.println(game);
                System.out.println(game.getGameStateMessage());
            }catch(RuntimeException e){
                System.out.println(game.getGameStateMessage());
            }
        }
    }

    //This method was used to test NumTTT.
    public void playNum(){
        String playerChoice = "-1";
        while(!game.isDone()){
            try{
                playerChoice = getNum();
                getPosition();
                game.takeTurn(acrossVal,downVal,playerChoice);
                if(acrossVal == 0){
                    game.setGameOver(true);
                    break;
                }
                System.out.println(game);
                System.out.println(game.getGameStateMessage());
            }catch(RuntimeException e){
                System.out.println(game.getGameStateMessage());
                /* this only works if the game sets the gameStateMessage when
                there is an exception caught*/
            }
        }
    }


    /**
     * get input for choice of position for player and validate to make sure its integer
     * @author Nabhan Anwar
     */
    private void getPosition(){
        while(true){
            try{
                System.out.println("Which column would you like to choose? (0 to quit)");
                acrossVal = input.nextInt();
                input.nextLine();
                System.out.println("Which row would you like to choose?");
                downVal = input.nextInt();
                break;
            } catch(Exception e){
                System.out.println("Invalid Input, try again.");
                input.next();
            }
        }
    }

    /**
     * Gets the token (X or O) of the current player
     * @return the current player's token
     */
    private String getToken(){
        String token = "X";
        if(game.getCurrentPlayer() == 2){
            token = "O";
        }
        return token;
    }

    //this method was used to test NumTTT
    private String getNum(){
        String num = "-1";
        if(game.getCurrentPlayer() == 1){
            System.out.println("Enter odd number. Your choices are " + odds);
            num = input.next();
            odds.remove(num);
        } else if(game.getCurrentPlayer() == 2){
            System.out.println("Enter even number. Your choices are " + evens);
            num = input.next();
            evens.remove(num);
        }
        return num;

    }

    //main method to run XO on terminal
    public static void main(String[] args){
        TextUI toPlay = new TextUI();
        toPlay.playXO();
    }
}