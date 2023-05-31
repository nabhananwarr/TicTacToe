package tictactoe;


/**
 * this class defines the abstract methods of the parent classes. Contains methods that run
 * the XO TicTacToe and its rules.
 * @author Nabhan Anwar
 */
public class XOGrid extends GameGrid{

    private static String[] symbols ={"X","O"};

    public XOGrid(int across, int down){
        super(across,down);
    }
    
    /**
     * checks for horizontal matches in all rows
     * @return true if match found
     * @author Nabhan Anwar
     */
    public boolean horizontalWin(){
        for(int row = 1; row <= getHeight(); row++){
            if(rowCheck(row)){
                return true;
            }
        }
        return false;
    }

    /**
     * checks for vertical matches in all columns
     * @return true if match found
     * @author Nabhan Anwar
     */
    public boolean verticalWin(){
        for(int col = 1; col <= getWidth(); col++){
            if(columnCheck(col)){
                return true;
            }
        }
        return false;    
    }

    /**
     * checks for diagonal matches in the grid
     * @return true if match found
     * @author Nabhan Anwar
     */
    public boolean diagonalWin(){
        if(diagonalCheck()){
            return true;
        }
        return false;    
    }

    /**
     * method to validate if there is only X or O in the grid
     * @author Nabhan Anwar
     */
    @Override
    public void validateInput(String input) throws Exception{ 
        for(String possible: symbols){
            if(input.equals(possible)){
                return;
            }
        }
        throw new Exception("Invalid input");
    }

    /**
     * method checks to see if positions are out of bounds and if the position is already full
     * @author Nabhan Anwar
     */
    @Override
    public void validateLocation(int across, int down)throws Exception{

        if(across > 3 || across < 0){
            throw new Exception("Invalid row. Please Try again!");
        }
        if(down > 3 || down < 0){
            throw new Exception("Invalid column. Please Try again!");
        }
        if(!getValue(across, down).equals(" ")){
            throw new Exception("Position is already taken. Try again!");
        }

    }

    /* private helper methods*/
    /**
     * checks for horizontal matches in a column
     * @return true if match found
     * @author Nabhan Anwar
     */
    private boolean rowCheck(int row){
        boolean match = false;
        if(!getValue(1, row).equals(" ")){
            match = getValue(1,row).equals(getValue(2, row));
            if(match){
                match = getValue(2,row).equals(getValue(3, row));
            }
        }
        return match;
    }

    /**
     * checks for vertical matches in a column
     * @return true if match found
     * @author Nabhan Anwar
     */
    private boolean columnCheck(int col){
        boolean match = false;
        if(!getValue(col, 1).equals(" ")){
            match = getValue(col, 1).equals(getValue(col, 2));
            if(match){
                match = getValue(col, 2).equals(getValue(col, 3));
            }
        }
        return match;
    }

    /**
     * checks for diagonal matches in the grid
     * @return true if match found
     * @author Nabhan Anwar
     */
    private boolean diagonalCheck(){
        boolean match = false;
        if(!getValue(1, 1).equals(" ")){
            match = getValue(1, 1).equals(getValue(2, 2));
            if(match){
                match = getValue(2, 2).equals(getValue(3, 3));
                if(match){
                    return match;
                }
            }
        }
        if(!getValue(1, 3).equals(" ")){
            match = getValue(1, 3).equals(getValue(2, 2));
            if(match){
                match = getValue(2, 2).equals(getValue(3, 1));
                if(match){
                    return match;
                }
            }
        }

        return match;
    }

}