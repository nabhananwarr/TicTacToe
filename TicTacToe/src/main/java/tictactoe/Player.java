package tictactoe;

/**
 * This class is for defining player name and their stats so it can be saved/loaded
 * @author Nabhan Anwar
 */
public class Player implements boardgame.Saveable {
    private String playerName;
    private int wins = 0;
    private int losses = 0;
    private int played = 0;

    public void setName(String name){
        playerName = name;
    }
    public String getName(){
        return playerName;
    }

    public void addWin(){
        wins++;
    }
    public void addlosses(){
        losses++;
    }
    public void addPlayed(){
        played++;
    }

    /* Interface Implementation */
    @Override
    public String getStringToSave(){
        return getName() + "," + wins + "," + losses + "," + played;
    }

    @Override
    public void loadSavedString(String toLoad){
        String[] parts = toLoad.split(",");
        setName(parts[0]);
        wins = Integer.parseInt(parts[1]);
        losses = Integer.parseInt(parts[2]);
        played = Integer.parseInt(parts[3]);
    }
}