/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.MainController;

import UTTT.gui.model.MacroboardPopulator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class MainViewController implements Initializable {

    @FXML
    private GridPane macroGridPane;
    @FXML
    private Label lblCurrentPlayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MacroboardPopulator macroPop = new MacroboardPopulator(macroGridPane);
        macroPop.getCurrentPlayerLabel(lblCurrentPlayer);
    }    
    
}
