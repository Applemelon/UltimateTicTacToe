/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.MainController;

import UTTT.bll.BLLManager;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asbamz
 */
public class GameOverController implements Initializable
{
    private final static String[] PLAYERS =
    {
        "Player One", "Player Two"
    };
    private final String WINNING_PRETEXT = "The Winner is ";
    private final String DRAW_TEXT = "It ended as a draw";
    private final int DRAW = -2;

    @FXML
    private Label lblMessage;

    private BLLManager bll;
    private Stage mainStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void setGameOverMessage(int player)
    {
        if (player > -1 && player < PLAYERS.length)
        {
            this.lblMessage.setText(WINNING_PRETEXT + PLAYERS[player]);
        }
        else if (player == DRAW)
        {
            this.lblMessage.setText(DRAW_TEXT);
        }
    }

    public void setBll(BLLManager bll)
    {
        this.bll = bll;
    }

    @FXML
    private void handleQuit(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/UTTT/gui/view/MainView.fxml"));
            Parent root = fxLoader.load();
            Scene scene = new Scene(root);
            Stage stage = mainStage;
            stage.setScene(scene);
            Stage st = (Stage) lblMessage.getScene().getWindow();
            st.close();
            stage.show();
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not open Game Over View", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void setMainWindow(Stage mainStage)
    {
        this.mainStage = mainStage;
    }
}
