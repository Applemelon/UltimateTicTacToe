/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.model;

import UTTT.bll.bllManager;
import javafx.event.ActionEvent;
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
    private final static Color BACKGROUND = Color.gray(0,1);
    private int test = 0;
    private bllManager bll;

    

    public MacroboardPopulator(GridPane MacroGridPane) {
        bll = new bllManager();
        
        makeMicroGrids();
        
        setMircoGridsinMacroGrid(MacroGridPane);
        
        MacroGridPane.setVgap(MACRO_GAP);
        MacroGridPane.setHgap(MACRO_GAP);
        MacroGridPane.setBackground(new Background(new BackgroundFill(BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));
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
                        microGrids[x][y].add(getButton(i, j, x, y), i, j);

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
    private Button getButton(int xMicro, int yMicro, int xMakro, int yMakro) {
        int Xposition = xMakro*3 + xMicro;
        int Yposition = yMakro*3 + yMicro;
        
        Button button = new Button();
        
        setButtonAction(button, Xposition, Yposition);
        
        button.textProperty().bind(bll.getValue(Xposition, Yposition));
        
        button.setPrefSize(10000, 10000);
        button.getStylesheets().add("/UTTT/gui/view/css/gridCSS.css");
        return button;
    }
    
    /**
     * Sets what each button should do on a press.
     * @param button
     * @param the x position a value from 0 to 8
     * @param the y position a value from 0 to 8
     */
    private void setButtonAction(Button button, int Xposition, int Yposition) {
        button.setOnAction((ActionEvent event) -> {
            bll.tryMove(Xposition, Yposition);
            System.out.println(bll.getValue(Xposition, Yposition));
            if (bll.getValue(Xposition, Yposition).get().equals("0")) {
                button.setStyle("-fx-background-color: orange");
            }
            else if (bll.getValue(Xposition, Yposition).get().equals("1")) {
                button.setStyle("-fx-background-color: red");
            }
        });
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
