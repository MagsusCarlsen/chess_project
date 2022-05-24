package chess.game;

import chess.pieces.*;
import chess.board.*;
import chess.utils.Pair;

public class GameMain {
    public static void main(String[] args){
        Board board = new Board();
        Pawn pawn = new Pawn(Color.BLACK, board, new Pair<>(1, 5));
        Pawn pawn2 = new Pawn(Color.WHITE, board, new Pair<>(1, 6));
        for(Pair<Integer, Integer> i : pawn.get_moves()){
            System.out.println(i.get_val1() + " - " + i.get_val2());
        }
    }
}
