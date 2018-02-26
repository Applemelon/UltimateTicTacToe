/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.model;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author janvanzetten
 */
public class MacroboardPopulator {

    private GridPane[][] microGrids = new GridPane[3][3];
    private final static Double MACRO_GAP = 10.0;
    private final static Double MICRO_GAP = 2.0;

    public MacroboardPopulator(GridPane MacroGridPane) {
        makeMicroGrids();

        setMircoGridsinMacroGrid(MacroGridPane);
        
        MacroGridPane.setVgap(MACRO_GAP);
        MacroGridPane.setHgap(MACRO_GAP);
        MacroGridPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * sets microgrids with content in the microGrid field
     */
    private void makeMicroGrids() {

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                microGrids[x][y] = new GridPane();
                microGrids[x][y].setVgap(MICRO_GAP);
                microGrids[x][y].setHgap(MICRO_GAP);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        microGrids[x][y].add(getButton(i, j), i, j);

                    }
                }
            }
        }
        System.out.println("Done microgrid making");

    }

    /**
     * returns a button for in the micro grids
     *
     * @return button
     */
    private Button getButton(int i, int j) {
        Button button = new Button();
        button.setText("Test");
        button.setPrefSize(10000, 10000);
        return button;
    }

    /**
     * Puts the microgrids from the microGrid field in the given MacroGridPane
     *
     * @param MacroGridPane the MacroGridPane
     */
    private void setMircoGridsinMacroGrid(GridPane MacroGridPane) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                MacroGridPane.add(microGrids[i][j], i, j);
            }
        }
    }

}
