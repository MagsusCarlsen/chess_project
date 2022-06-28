package chess.game;

import chess.pieces.*;
import chess.board.*;
import chess.utils.Pair;

public class GameMain {
    public static void main(String[] args){
        Board board = new Board();
        King king = new King(Team.BLACK, board, new Pair<>(4, 4));
        for(Pair<Integer, Integer> i : king.get_moves()){
            System.out.println(i.get_val1() + " - " + i.get_val2());
        }
    }
}