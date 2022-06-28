package chess.pieces;

import chess.utils.*;
import chess.board.*;
import java.util.LinkedList;
public class Rook extends Piece{
    public Rook(Team team, Board board, Pair<Integer, Integer> position){
        super(board, team, position);
        board.set_piece(position.get_val1(), position.get_val2(), this);
    }
    @Override
    public LinkedList<Pair<Integer, Integer>> get_moves() {
        return straight();
    }

    @Override
    public void move(int x, int y) {
        Pair<Integer, Integer> move = new Pair<>(x, y);
        if(get_moves().contains(move)){
            board.set_piece(position.get_val1(), position.get_val2(), null);
            position = move;
            board.set_piece(position.get_val1(), position.get_val2(), null);
        }
    }
}
