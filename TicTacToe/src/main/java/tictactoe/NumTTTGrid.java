package tictactoe;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * this class defines the abstract methods of the parent classes. Contains methods that run
 * specialized win check and validation methods for NumTTT.
 * @author Nabhan Anwar
 */
public class NumTTTGrid extends GameGrid{

    private String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private ArrayList<String> odds = new ArrayList<>(Arrays.asList("1", "3", "5", "7", "9")); 
    private ArrayList<String> evens = new ArrayList<>(Arrays.asList("0", "2", "4", "6", "8")); 

    public NumTTTGrid(int across, int down){
        super(across,down);
    }
    
    /**
     * checks for horizontal matches in all rows that sum over 15
     * @return true if result is above 15
     * @author Nabhan Anwar
     */
    public boolean horizontalWin(){
        for(int row = 1; row <= getHeight(); row++){
            if(rowCheck(row) >= 15){
                return true;
            }
        }
        return false;
    }

    /**
     * checks for vertical matches in all columns that sum over 15
     * @return true if result is above 15
     * @author Nabhan Anwar
     */
    public boolean verticalWin(){
        for(int col = 1; col <= getHeight(); col++){
            if(columnCheck(col) >= 15){
                return true;
            }
        }
        return false; 
    }

    /**
     * checks for diagonal matches in the grid that sum over 15
     * @return true if result is above 15
     * @author Nabhan Anwar
     */
    public boolean diagonalWin(){
        if(diagonalCheckForOdd() >= 15){
            return true;
        }
        if(diagonalCheckForEven() >= 15){
            return true;
        }
        return false;
    }

    /**
     * method to validate if there are only numbers in the grid
     * @author Nabhan Anwar
     */
    public void validateInput(String input)throws Exception{ 
        for(String possible: nums){
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
    public void validateLocation(int across, int down)throws Exception{

        if(across > 3 || across < 1){
            throw new Exception("Invalid row. Please Try again!");
        }
        if(down > 3 || down < 1){
            throw new Exception("Invalid column. Please Try again!");
        }
        if(!getValue(across, down).equals(" ")){
            throw new Exception("Position is already taken. Try again!");
        }
        
    }

    //private helper methods
    /**
     * checks if number is odd
     * @param num
     * @return true if odd
     */
    private boolean isOdd(int num){
        if(num%2 == 1){
            return true;
        }
        return false;
    }
    /**
     * checks if number is even
     * @param num
     * @return true if even
     */
    private boolean isEven(int num){
        if(num%2 == 0){
            return true;
        }
        return false;
    }

    /**
     * checks for horizontal matches in a column and adds up the values if they all match
     * @return variable 'fifteen' that indicates if the total reached 15
     * @author Nabhan Anwar
     */
    private int rowCheck(int row){
        int fifteen = 0;
        //if the spaces in the row are occupied and numbers are odd
        if(!getValue(1, row).equals(" ") && isOdd(Integer.parseInt(getValue(1, row)))){
            fifteen = fifteen + Integer.parseInt(getValue(1, row));
            if(!getValue(2, row).equals(" ") && isOdd(Integer.parseInt(getValue(2, row)))){
                fifteen = fifteen + Integer.parseInt(getValue(2, row));
                if(!getValue(3, row).equals(" ") && isOdd(Integer.parseInt(getValue(3, row)))){
                    fifteen = fifteen + Integer.parseInt(getValue(3, row));
                } else{
                    fifteen = 0;
                }
            }
        }
        //if the spaces in the row are occupied and numbers are even
        if(!getValue(1, row).equals(" ") && isEven(Integer.parseInt(getValue(1, row)))){
            fifteen = fifteen + Integer.parseInt(getValue(1, row));
            if(!getValue(2, row).equals(" ") && isEven(Integer.parseInt(getValue(2, row)))){
                fifteen = fifteen + Integer.parseInt(getValue(2, row));
                if(!getValue(3, row).equals(" ") && isEven(Integer.parseInt(getValue(3, row)))){
                    fifteen = fifteen + Integer.parseInt(getValue(3, row));
                } else{
                    fifteen = 0;
                }
            }
        }

        return fifteen;
    }

    /**
     * checks for vertical matches in a column and adds up the values if they all match
     * @return variable 'fifteen' that indicates if the total reached 15
     * @author Nabhan Anwar
     */
    private int columnCheck(int col){
        int fifteen = 0;
        //if the spaces in the column are occupied and numbers are odd
        if(!getValue(col, 1).equals(" ") && isOdd(Integer.parseInt(getValue(col, 1)))){
            fifteen = fifteen + Integer.parseInt(getValue(col, 1));
            if(!getValue(col, 2).equals(" ") && isOdd(Integer.parseInt(getValue(col, 2)))){
                fifteen = fifteen + Integer.parseInt(getValue(col, 2));
                if(!getValue(col, 3).equals(" ") && isOdd(Integer.parseInt(getValue(col, 3)))){
                    fifteen = fifteen + Integer.parseInt(getValue(col, 3));
                } else{
                    fifteen = 0;
                }
            }
        }
        //if the spaces in the column are occupied and numbers are even
        if(!getValue(col, 1).equals(" ") && isEven(Integer.parseInt(getValue(col, 1)))){
            fifteen = fifteen + Integer.parseInt(getValue(col, 1));
            if(!getValue(col, 2).equals(" ") && isEven(Integer.parseInt(getValue(col, 2)))){
                fifteen = fifteen + Integer.parseInt(getValue(col, 2));
                if(!getValue(col, 3).equals(" ") && isEven(Integer.parseInt(getValue(col, 3)))){
                    fifteen = fifteen + Integer.parseInt(getValue(col, 3));
                } else{
                    fifteen = 0;
                }
            }
        }
        return fifteen;
    }

    /**
     * checks for diagonal matches in a column and adds up the values if they all are odd
     * @return variable 'fifteen' that indicates if the total reached 15
     * @author Nabhan Anwar
     */
    private int diagonalCheckForOdd(){
        int fifteen = 0;
        //if the spaces diagonally are occupied and numbers are odd
        if(!getValue(1, 1).equals(" ") && isOdd(Integer.parseInt(getValue(1, 1)))){
            fifteen = fifteen + Integer.parseInt(getValue(1, 1));
            if(!getValue(2, 2).equals(" ") && isOdd(Integer.parseInt(getValue(2, 2)))){
                fifteen = fifteen + Integer.parseInt(getValue(2, 2));
                if(!getValue(3, 3).equals(" ") && isOdd(Integer.parseInt(getValue(3, 3)))){
                    fifteen = fifteen + Integer.parseInt(getValue(3, 3));
                    if(fifteen >= 15){
                        return fifteen;
                    } 
                }
            }
        } 
        if(!getValue(3, 1).equals(" ") && isOdd(Integer.parseInt(getValue(3, 1)))){
            fifteen = fifteen + Integer.parseInt(getValue(3, 1));
            if(!getValue(2, 2).equals(" ") && isOdd(Integer.parseInt(getValue(2, 2)))){
                fifteen = fifteen + Integer.parseInt(getValue(2, 2));
                if(!getValue(1, 3).equals(" ") && isOdd(Integer.parseInt(getValue(1, 3)))){
                    fifteen = fifteen + Integer.parseInt(getValue(1, 3));
                    if(fifteen >= 15){
                        return fifteen;
                    } 
                }
            }
        } 
        
        return fifteen;
    }

    /**
     * checks for diagonal matches in a column and adds up the values if they all are even
     * @return variable 'fifteen' that indicates if the total reached 15
     * @author Nabhan Anwar
     */
    private int diagonalCheckForEven(){
        int fifteen = 0;
        //if the spaces in the column are occupied and numbers are even
        if(!getValue(1, 1).equals(" ") && isEven(Integer.parseInt(getValue(1, 1)))){
            fifteen = fifteen + Integer.parseInt(getValue(1, 1));
            if(!getValue(2, 2).equals(" ") && isEven(Integer.parseInt(getValue(2, 2)))){
                fifteen = fifteen + Integer.parseInt(getValue(2, 2));
                if(!getValue(3, 3).equals(" ") && isEven(Integer.parseInt(getValue(3, 3)))){
                    fifteen = fifteen + Integer.parseInt(getValue(3, 3));
                    if(fifteen >= 15){
                        return fifteen;
                    } 
                }
            }
        } 
        if(!getValue(3, 1).equals(" ") && isEven(Integer.parseInt(getValue(3, 1)))){
            fifteen = fifteen + Integer.parseInt(getValue(3, 1));
            if(!getValue(2, 2).equals(" ") && isEven(Integer.parseInt(getValue(2, 2)))){
                fifteen = fifteen + Integer.parseInt(getValue(2, 2));
                if(!getValue(1, 3).equals(" ") && isEven(Integer.parseInt(getValue(1, 3)))){
                    fifteen = fifteen + Integer.parseInt(getValue(1, 3));
                    if(fifteen >= 15){
                        return fifteen;
                    } 
                }
            }
        } 
        return fifteen;
    }

}

