package com.tecacet.games.sudoku.gui.action;

import com.tecacet.games.sudoku.gui.SudokuController;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveGameAction extends AbstractAction {

    private JFileChooser fc;

    private final SudokuController controller;
    private final Component parent;

    public SaveGameAction(SudokuController controller, Component parent) {
        super("Save Game");
        this.controller = controller;
        this.parent = parent;
        try {
            fc = new JFileChooser();
            // fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setCurrentDirectory(new File("./games"));
        } catch (SecurityException se) {
            // for applets
            setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showSaveDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (file.exists()) {
                int response = JOptionPane.showConfirmDialog(parent, "Overwrite existing file?", "Confirm Overwrite",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }
            controller.saveGame(file.getAbsolutePath());
        }
    }

}
