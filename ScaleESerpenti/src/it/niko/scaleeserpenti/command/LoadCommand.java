package it.niko.scaleeserpenti.command;

import it.niko.scaleeserpenti.observer.Game;
import javax.swing.*;
import java.io.File;

public class LoadCommand implements Command {

    private final JFrame frame;
    private final Game game;

    public LoadCommand(JFrame frame, Game game) {
        this.frame = frame;
        this.game = game;
    }

    @Override
    public boolean execute() {
        JFileChooser fileChooser = new JFileChooser();
        try {
            if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().exists()) {
                File f = fileChooser.getSelectedFile();
                game.load(f.getAbsolutePath());
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null,"File not exists.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e) {
            JOptionPane.showConfirmDialog(null, "Game not loaded!", "ERROR", JOptionPane.DEFAULT_OPTION);
        }
        return false;
    }
}