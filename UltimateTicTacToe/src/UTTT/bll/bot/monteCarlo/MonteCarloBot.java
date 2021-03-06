/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.bll.bot.monteCarlo;

import UTTT.bll.bot.IBot;
import UTTT.bll.field.IField;
import UTTT.bll.game.IGameState;
import UTTT.bll.move.IMove;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author janvanzetten
 */
public class MonteCarloBot implements IBot{
    
    List<Integer[]> results; //int[0] is total tries int[1] is score 1 for each win 0 for each draw and -1 for each lose
    List<IMove> myMoves;

    @Override
    public IMove doMove(IGameState state) {
        int player = state.getMoveNumber()%2;
        
        myMoves = state.getField().getAvailableMoves();
        
        results = new ArrayList<>();
        
        fillResults(player, state.getField(), myMoves);
        
        return getBestMove(state);
    }

    private IMove getBestMove(IGameState state) {

        //TODO find the best move from the results list
        
        return myMoves.get(0); //some random move 
    }

    private int selectMove(List<IMove> myMoves) {
        Random random = new Random();
        return random.nextInt(myMoves.size());
    }

    private void fillResults(int player, IField field, List<IMove> myMoves) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
