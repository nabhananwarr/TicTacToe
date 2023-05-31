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
import java.io.File;


/**
 * FileChooser class to use in GameGui to make it easy to select file to load
 * @author Nabhan Anwar
 */
public class FileChooser extends JPanel{

    private JButton loadFileButton;
    private JTextArea outputArea;
    private JFileChooser fileChooser;


    public FileChooser() {
        super();

        setLayout(new BorderLayout());
        fileChooser = new JFileChooser();
        createLoadFileButton();
        createOutputArea();
        
    }

    private void createLoadFileButton() {
        loadFileButton = new JButton("Load file");
        loadFileButton.addActionListener(e->loadFile());
        add(loadFileButton, BorderLayout.EAST);
    }

    // text areas
    private void createOutputArea() {
        outputArea = new JTextArea(10,10);
        outputArea.setEditable(false);
        outputArea.setText("This will show the file name\n after you select a file with the chooser");
        add(outputArea,BorderLayout.CENTER);

    }

/* the listener methods */

    private void loadFile(){

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().canRead()) {
                try {
                    //open file
                    // call game.loadSavedString();
                    //do whatever else needs doing to load
                    outputArea.setText(fileChooser.getSelectedFile().getName());
                    outputArea.append("\nThe Full Path\n");
                    outputArea.append(fileChooser.getSelectedFile().getAbsolutePath());
                } catch (Exception ex) {
                    outputArea.append("\n"+ ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {

        FileChooser gui = new FileChooser();
        gui.setVisible(true);
    }

}
