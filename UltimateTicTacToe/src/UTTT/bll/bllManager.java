/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.bll;

import UTTT.bll.game.GameManager;
import UTTT.bll.game.GameState;
import UTTT.bll.move.IMove;
import UTTT.bll.move.Move;
import UTTT.dal.MyGameField;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author janvanzetten
 */
public class bllManager {
    GameManager gameManager;
    MyGameField myfield;

    public bllManager() {
        this.myfield = new MyGameField();
        this.gameManager = new GameManager(new GameState(myfield));
    }
    
    

    public void tryMove(int Xposition, int Yposition) {
        IMove move = new Move(Xposition, Yposition);
        
        gameManager.UpdateGame(move);
 
    }
    
    public StringProperty getValue(int x, int y){
        StringProperty[][] board = myfield.getPropertyBoard();
        
        return board[x][y];
    }
    
    public int getCurrentPlayer() {
        return gameManager.getCurrentPlayer();
    }
    
}
