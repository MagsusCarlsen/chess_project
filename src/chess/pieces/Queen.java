package chess.pieces;

import chess.board.*;
import chess.utils.*;

import java.util.LinkedList;
public class Queen extends Piece{
    public Queen(Team team, Board board, Pair<Integer, Integer> position){
        super(board, team, position);
        board.set_piece(position.get_val1(), position.get_val2(), this);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> get_moves() {
        LinkedList<Pair<Integer, Integer>> diagonal = new LinkedList<>();
        LinkedList<Pair<Integer, Integer>> straight = new LinkedList<>();
        for(int i = 0; i < 7; i++){
            diagonal.add(new Pair<>(position.get_val1() + i, position.get_val2() + i));
            diagonal.add(new Pair<>(position.get_val1() + i, position.get_val2() - i));
            diagonal.add(new Pair<>(position.get_val1() - i, position.get_val2() + i));
            diagonal.add(new Pair<>(position.get_val1() - i, position.get_val2() - i));

            straight.add(new Pair<>(position.get_val1(), position.get_val2() + i));
            straight.add(new Pair<>(position.get_val1() + i, position.get_val2()));
            straight.add(new Pair<>(position.get_val1() - i, position.get_val2()));
            straight.add(new Pair<>(position.get_val1(), position.get_val2() - i));
        }
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        moves.addAll(diagonal);
        moves.addAll(straight);

        moves.removeIf(i ->
                i.get_val1() > 7 || i.get_val1() < 0 || i.get_val2() > 7 || i.get_val2() < 0
        );
        return moves;
    }

    @Override
    public void move(int x, int y) {
        Pair<Integer, Integer> move = new Pair<>(x, y);
        if(get_moves().contains(move)){
            board.set_piece(position.get_val1(), position.get_val2(), null);
            position = move;
            board.set_piece(position.get_val1(), position.get_val2(), this);
        }
    }
}
