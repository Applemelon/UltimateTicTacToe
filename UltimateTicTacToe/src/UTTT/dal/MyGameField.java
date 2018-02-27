/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.dal;

import UTTT.bll.field.IField;
import static UTTT.bll.field.IField.EMPTY_FIELD;
import UTTT.bll.move.IMove;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author janvanzetten
 */
public class MyGameField implements IField{

    private StringProperty[][] board;
    private String[][] macroBoard;

    public MyGameField() {
        board = new StringProperty[9][9];
        macroBoard = new String[3][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SimpleStringProperty(EMPTY_FIELD);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                macroBoard[i][j] = AVAILABLE_FIELD;
            }
        }
        
    }
    

    @Override
    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j].set(EMPTY_FIELD);
            }
        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPlayerId(int column, int row) {
        return board[column][row].get();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board[i][j].get().equals(EMPTY_FIELD)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].get().equals(EMPTY_FIELD)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        int macroX = x/3;
        int macroY = y/3;
        
        return macroBoard[macroX][macroY].equals(AVAILABLE_FIELD);
    }

    @Override
    public String[][] getBoard() {
        String[][] stringBoard = new String[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                stringBoard[i][j] = board[i][j].get();
            }
        }
        return stringBoard;
    }

    @Override
    public String[][] getMacroboard() {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j].set(board[i][j]);
            }
        }
    }

    @Override
    public void setMacroboard(String[][] macroboard) {
        this.macroBoard = macroboard;
    }
    
    
    /**
     * get the board as a string property 2 dimensional array
     * @return StringProperty[9][9]
     */
    public StringProperty[][] getPropertyBoard(){
        return board;
    }
}
