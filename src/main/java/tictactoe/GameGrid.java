package tictactoe;

import java.util.Iterator;
/* the creation of a parent class is what lets use the same TTT game
to provide both games */

public abstract class GameGrid extends boardgame.Grid{

public GameGrid(int across, int down){
    super(across,down);
}   
public abstract boolean horizontalWin();

public abstract boolean verticalWin();

public abstract boolean diagonalWin();

public  abstract void validateInput(String input) throws Exception;

public  abstract void validateLocation(int across, int down)throws Exception;


public boolean isFull(){
    
    Iterator<String> iter = iterator();
    int count = 0;
        while(iter.hasNext()){
            if(!iter.next().equals(" ")){
            count++;
            }
        }
    if(count == getWidth()*getHeight()){
        return true;
    }
    return false;

}




}