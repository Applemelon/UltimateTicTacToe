/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.bll.game;

import UTTT.bll.field.IField;

import UTTT.dal.DALManager;

/**
 *
 * @author alexl
 */
public class GameState implements IGameState{
    
    private DALManager dMan;

    @Override
    public IField getField() {
        return dMan.getField();
    }

    @Override
    public int getMoveNumber() {
        return dMan.getMoveNumber();
    }

    @Override
    public void setMoveNumber(int moveNumber) {
        dMan.setMoveNumber(moveNumber);
    }

    @Override
    public int getRoundNumber() {
        return dMan.getRoundNumber();
    }

    @Override
    public void setRoundNumber(int roundNumber) {
        dMan.setRoundNumber(roundNumber);
    }
    
}
