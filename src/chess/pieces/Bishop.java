package chess.pieces;

import chess.utils.*;
import chess.board.*;

import java.util.LinkedList;

public class Bishop extends Piece {
    public Bishop(Team team, Board board, Pair<Integer, Integer> position){
        super(board, team, position);
        board.set_piece(position.get_val1(), position.get_val2(), this);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> get_moves() {
        return diagonal();
    }

    @Override
    public Boolean move(int x, int y) {
        Pair<Integer, Integer> move = new Pair<>(x, y);
        if(get_moves().contains(move)){
            board.set_piece(position.get_val1(), position.get_val2(), null);
            this.position = move;
            board.set_piece(position.get_val1(), position.get_val2(), null);
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "B";
    }
}
