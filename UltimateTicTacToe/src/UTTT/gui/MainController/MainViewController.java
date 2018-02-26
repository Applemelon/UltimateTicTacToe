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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class MainViewController implements Initializable {

    @FXML
    private GridPane MacroGridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MacroboardPopulator macroPop = new MacroboardPopulator(MacroGridPane, Color.DARKBLUE, Color.RED);
    }    
    
}
