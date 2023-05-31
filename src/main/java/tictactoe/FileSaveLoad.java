package tictactoe;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;
/**
 * FileSaveLoad class to use in GameGui to save or load onto a file
 * @author Nabhan Anwar
 */
public class FileSaveLoad{
    public static void save(boardgame.Saveable toSave, String filename, String relativePath){
        Path path = FileSystems.getDefault().getPath(relativePath,filename);
        try{
            Files.writeString(path, toSave.getStringToSave());
        }catch(IOException e){
            throw(new RuntimeException(e.getMessage()));
        }
    }

    public static void load(boardgame.Saveable toLoad, String filename, String relativePath){
        Path path = FileSystems.getDefault().getPath(relativePath,filename);
        try{
            String fileLines = String.join("\n",Files.readAllLines(path));
            fileLines = fileLines.replace(",", "");
            //System.out.println(fileLines);
            toLoad.loadSavedString(fileLines);
        }catch(IOException e){
            System.out.println("Incorrect file format");
        }
    }

}