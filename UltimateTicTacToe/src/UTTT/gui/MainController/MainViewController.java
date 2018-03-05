/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.MainController;

import UTTT.bll.bot.IBot;
import UTTT.bll.game.GameManager.GameMode;
import UTTT.gui.model.MacroboardPopulator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class MainViewController implements Initializable
{

    @FXML
    private GridPane macroGridPane;
    @FXML
    private Label lblCurrentPlayer;
    private MacroboardPopulator macroPop;
    @FXML
    private Pane currentplayerPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    public void setup(GameMode gameMode, IBot bot1, IBot bot2)
    {
        macroPop = new MacroboardPopulator(macroGridPane, gameMode, bot1, bot2);
        macroPop.getCurrentPlayerLabel(lblCurrentPlayer, currentplayerPane);
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/UTTT/gui/view/MainView.fxml"));
            Parent root = fxLoader.load();
            Scene scene = new Scene(root);
            Stage stage = macroPop.getMainStage();
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not restart", ButtonType.OK);
            alert.showAndWait();
        }
    }

}
