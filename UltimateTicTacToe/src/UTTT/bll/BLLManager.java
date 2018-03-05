/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.bll;

import UTTT.bll.bot.IBot;
import UTTT.bll.game.GameManager;
import UTTT.bll.game.GameManager.GameMode;
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

    public BLLManager(GameMode gameMode, IBot bot1, IBot bot2)
    {
        this.myfield = new MyGameField();
        switch (gameMode)
        {
            case HumanVsHuman:
                this.gameManager = new GameManager(new GameState(myfield));
                break;
            case HumanVsBot:
                this.gameManager = new GameManager(new GameState(myfield), bot1);
                break;
            case BotVsBot:
                this.gameManager = new GameManager(new GameState(myfield), bot1, bot2);
                break;
            default:
                this.gameManager = new GameManager(new GameState(myfield));
                break;
        }
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
    public StringProperty getMicroValue(int x, int y)
    {
        StringProperty[][] board = myfield.getPropertyBoard();

        StringProperty value = board[x][y];

        return value;
    }

    /**
     * @return true if a microgrid has been won.
     */
    public boolean isMicroGridWon()
    {
        return (gameManager.isMicroGridWon());
    }

    /**
     * Resets the microgridWon value to false.
     */
    public void setMicroGridWon()
    {
        gameManager.setMicroGridWon(false);
    }

    /**
     * @return true if a microgrid has been won.
     */
    public boolean isMicroGridDraw()
    {
        return (gameManager.isMicroGridDraw());
    }

    /**
     * Resets the microgridWon value to false.
     */
    public void setMicroGridDraw()
    {
        gameManager.setMicroGridDraw(false);
    }

    /**
     * get the value of the given coordinats
     * @param x 0 to 2
     * @param y 0 to 2
     * @return Stringproperty with the value
     */
    public StringProperty getMacroValue(int x, int y)
    {
        StringProperty[][] board = myfield.getPropertyMacroBoard();

        StringProperty value = board[x][y];

        return value;
    }

}
