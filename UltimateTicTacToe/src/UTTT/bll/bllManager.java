/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.bll;

import UTTT.bll.move.IMove;
import UTTT.bll.move.Move;
import javafx.beans.property.StringProperty;

/**
 *
 * @author janvanzetten
 */
public class bllManager {
    
    
    

    public void tryMove(int Xposition, int Yposition) {
        IMove move = new Move(Xposition, Yposition);
        
        //TODO send to game manager validate move and make move
    }
    
    public StringProperty getValueProperty(int x, int y){
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        //TODO add listener on board text on the given koordinats
        return null;
    }
    
}
