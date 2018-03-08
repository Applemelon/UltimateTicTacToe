/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author janvanzetten
 */
public class UTTTMain extends Application
{

    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/UTTT/gui/view/NewGameView.fxml"));
        Parent root = fxLoader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("The Ultimate Tic Tac Toe");
        stage.getIcons().add(new Image("/UTTT/main/UltimateTicTatToeLogo.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
