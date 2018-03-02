/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.gui.model;

import UTTT.bll.BLLManager;
import UTTT.gui.MainController.GameOverController;
import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author janvanzetten
 */
public class MacroboardPopulator {

    private GridPane macroGridPane;
    private GridPane[][] microGrids = new GridPane[3][3];
    private final static Double MACRO_GAP = 10.0;
    private final static Double MICRO_GAP = 2.0;
    private final static Color BACKGROUND = Color.gray(0, 1);
    private final static String PLAYER1_COLOR = "EECE66";
    private final static String PLAYER2_COLOR = "#F67B5E";
    private final static String light_COLOR = "#F3F1E8";
    private final static String medium_COLOR = "#BEB1A4";
    private final static String darkish_COLOR = "#776E65";
    private final static String DRAW_COLOR = "gray";
    private final static String PLAYER1 = "O";
    private final static String PLAYER2 = "X";
    private int test = 0;
    private BLLManager bll;
    @FXML
    private Label lblCurrentPlayer;

    public MacroboardPopulator(GridPane macroGridPane) {
        this.macroGridPane = macroGridPane;
        bll = new BLLManager();

        makeMicroGrids();

        setMircoGridsinMacroGrid(macroGridPane);

        macroGridPane.setVgap(MACRO_GAP);
        macroGridPane.setHgap(MACRO_GAP);
        macroGridPane.setBackground(new Background(new BackgroundFill(BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));
        macroGridPane.setStyle("-fx-border-color:black;"
                + "-fx-border-width: 5px;");
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
        int Xposition = xMakro * 3 + xMicro;
        int Yposition = yMakro * 3 + yMicro;

        Button button = new Button();
        button.setPrefSize(10000, 10000);
        button.getStylesheets().add("/UTTT/gui/view/css/gridCSS.css");

        setButtonAction(button, Xposition, Yposition);
        buttonAvailabilityListener(button, Xposition, Yposition, xMakro, yMakro);
        
        return button;
    }
    
    /**
     * Changes color of buttons according to whether they can be pressed or not.
     * @param button
     * @param Xposition
     * @param Yposition
     * @param xMakro
     * @param yMakro 
     */
    private void buttonAvailabilityListener(Button button, int Xposition, int Yposition, int xMakro, int yMakro) {
        StringProperty value = bll.getMicroValue(Xposition, Yposition);

        value.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)
                -> {
            if (newValue.equals("1")) {
                button.setText("X");
            } else if (newValue.equals("0")) {
                button.setText("O");
            }
        });

        StringProperty macroValue = bll.getMacroValue(xMakro, yMakro);

        macroValue.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (value.get().equals(".")) {
                if (newValue.equals("-1")) {
                    button.setStyle("-fx-background-color: " + darkish_COLOR);
                } else if (newValue.equals(".")) {
                    button.setStyle("-fx-background-color: " + medium_COLOR);
                }
            }
        });
    }

    /**
     * Sets what each button should do on a press.
     *
     * @param button
     * @param the x position a value from 0 to 8
     * @param the y position a value from 0 to 8
     */
    private void setButtonAction(Button button, int xPosition, int yPosition) {
        button.setOnAction((ActionEvent event)
                -> {
            bll.tryMove(xPosition, yPosition);

            if (bll.getMicroValue(xPosition, yPosition).get().equals("0")) {
                if (bll.isMicroGridWon() == true) {
                    setMacroVictory(PLAYER1_COLOR, xPosition, yPosition);
                    bll.setMicroGridWon();
                } else if (bll.isMicroGridDraw() == true) {
                    setMacroVictory(DRAW_COLOR, xPosition, yPosition);
                    bll.setMicroGridDraw();
                } else {
                    button.setStyle("-fx-background-color: " + PLAYER1_COLOR);
                }
                lblCurrentPlayer.setText("Current player: " + PLAYER2);

            } else if (bll.getMicroValue(xPosition, yPosition).get().equals("1")) {
                if (bll.isMicroGridWon() == true) {
                    setMacroVictory(PLAYER2_COLOR, xPosition, yPosition);
                    bll.setMicroGridWon();
                } else if (bll.isMicroGridDraw() == true) {
                    setMacroVictory(DRAW_COLOR, xPosition, yPosition);
                    bll.setMicroGridDraw();
                } else {
                    button.setStyle("-fx-background-color: " + PLAYER2_COLOR);
                }
                lblCurrentPlayer.setText("Current player: " + PLAYER1);
            }

            if (bll.getMicroValue(xPosition, yPosition).get().equals("0")) {
                button.setStyle("-fx-background-color: " + PLAYER1_COLOR);
            } else if (bll.getMicroValue(xPosition, yPosition).get().equals("1")) {
                button.setStyle("-fx-background-color: " + PLAYER2_COLOR);
            }

            int result = bll.isGameOver();
            if (result != -1) {
                openGameOverView(result);
            }
        });
    }

    /**
     * Puts the microgrids from the microGrid field in the given MacroGridPane
     *
     * @param MacroGridPane the MacroGridPane
     */
    private void setMircoGridsinMacroGrid(GridPane macroGridPane) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                macroGridPane.add(microGrids[i][j], i, j);
            }
        }
    }

    private void openGameOverView(int player) {
        try {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/UTTT/gui/view/GameOverView.fxml"));
            Parent root = fxLoader.load();

            GameOverController cont = fxLoader.getController();

            cont.setGameOverMessage(player);
            cont.setBll(bll);
            cont.setMainWindow((Stage) macroGridPane.getScene().getWindow());

            Scene scene = new Scene(root);
            newStage.setTitle("Game Over!");
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Could not open Game Over View", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void setMacroVictory(String playerColor, int xPosition, int yPosition) {
        Label label = new Label();
        label.setStyle("-fx-background-color: " + playerColor + ";"
                + "-fx-font-size: 100px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + light_COLOR + ";"
                + "-fx-alignment: center;");
        label.setMaxSize(5000, 5000);

        if (playerColor.equals(PLAYER1_COLOR)) {
            label.setText("O");
        } else if (playerColor.equals(PLAYER2_COLOR)) {
            label.setText("X");
        } else if (playerColor.equals(DRAW_COLOR)) {
            label.setText("-");
        }
        macroGridPane.add(label, (xPosition / 3), (yPosition / 3));
    }

    public void getCurrentPlayerLabel(Label lblCurrentPlayer) {
        this.lblCurrentPlayer = lblCurrentPlayer;
        lblCurrentPlayer.setText("Current player: " + PLAYER1);
    }
}
