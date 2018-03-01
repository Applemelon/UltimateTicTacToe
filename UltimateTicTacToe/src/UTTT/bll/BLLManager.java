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

/**
 *
 * @author janvanzetten
 */
public class BLLManager
{
    GameManager gameManager;
    MyGameField myfield;

    public BLLManager()
    {
        this.myfield = new MyGameField();
        this.gameManager = new GameManager(new GameState(myfield));
    }

    /**
     * try to move
     * @param xPosition
     * @param yPosition
     */
    public void tryMove(int xPosition, int yPosition)
    {
        IMove move = new Move(xPosition, yPosition);

        gameManager.UpdateGame(move);
    }

    /**
     * Check if any players has won.
     * @return boolean expressing rather or not it is game over.
     */
    public int isGameOver()
    {
        return gameManager.isGameOver();
    }

    /**
     * get the value of the given coordinats
     * @param x
     * @param y
     * @return Stringproperty with the value
     */
    public StringProperty getValue(int x, int y)
    {
        StringProperty[][] board = myfield.getPropertyBoard();

        StringProperty value = board[x][y];

        return value;
    }

}
