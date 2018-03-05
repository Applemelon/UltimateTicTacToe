/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.MainController;

import UTTT.bll.game.GameManager;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asbamz
 */
public class NewGameController implements Initializable
{
    @FXML
    private Button btnPvP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handlePlayerVersusPlayer(ActionEvent event)
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/UTTT/gui/view/MainView.fxml"));
            Parent root;
            root = fxLoader.load();

            MainViewController mwc = (MainViewController) fxLoader.getController();
            mwc.setup(GameManager.GameMode.HumanVsHuman, null, null);

            Scene scene = new Scene(root);
            Stage stage = (Stage) btnPvP.getScene().getWindow();
            stage.setScene(scene);
            stage.setMinHeight(660.0);
            stage.setMinWidth(600.0);
            stage.centerOnScreen();
            stage.setTitle("The Ultimate Tic Tac Toe - PvP");
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not start game!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handlePlayerVersusAI(ActionEvent event)
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/UTTT/gui/view/MainView.fxml"));
            Parent root;
            root = fxLoader.load();

            MainViewController mwc = (MainViewController) fxLoader.getController();
            mwc.setup(GameManager.GameMode.HumanVsBot, null, null);

            Scene scene = new Scene(root);
            Stage stage = (Stage) btnPvP.getScene().getWindow();
            stage.setScene(scene);
            stage.setMinHeight(660.0);
            stage.setMinWidth(600.0);
            stage.centerOnScreen();
            stage.setTitle("The Ultimate Tic Tac Toe - PvP");
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not start game!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAIVersusAI(ActionEvent event)
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/UTTT/gui/view/MainView.fxml"));
            Parent root;
            root = fxLoader.load();

            MainViewController mwc = (MainViewController) fxLoader.getController();
            mwc.setup(GameManager.GameMode.BotVsBot, null, null);

            Scene scene = new Scene(root);
            Stage stage = (Stage) btnPvP.getScene().getWindow();
            stage.setScene(scene);
            stage.setMinHeight(660.0);
            stage.setMinWidth(600.0);
            stage.centerOnScreen();
            stage.setTitle("The Ultimate Tic Tac Toe - PvP");
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not start game!", ButtonType.OK);
            alert.showAndWait();
        }
    }

}
