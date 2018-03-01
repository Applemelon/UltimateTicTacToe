/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.MainController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Asbamz
 */
public class GameOverController implements Initializable
{
    @FXML
    private Label lblMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void setGameOverMessage(String message)
    {
        this.lblMessage.setText(message);
    }

    @FXML
    private void handleQuit(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {

    }

}
