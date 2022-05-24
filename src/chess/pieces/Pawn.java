package chess.pieces;

import chess.board.Board;
import chess.utils.Pair;

import java.util.LinkedList;

import static java.lang.Math.*;

public class Pawn extends Piece {
    public Pawn(Color color, Board board, Pair<Integer, Integer> position) {
        super(board, color, position);
        this.type = Pieces.PAWN;
        board.set_piece(position.get_val1(), position.get_val2(), this);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> get_moves() {
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        //Collect all moves, filter later
        if (color == Color.BLACK) {
            moves.add(new Pair<>(position.get_val1(), position.get_val2() + 1)); //remove if occupied
            moves.add(new Pair<>(position.get_val1(), position.get_val2() + 2)); //remove if not on starting position or occupied
            moves.add(new Pair<>(position.get_val1() + 1, position.get_val2() + 1)); //remove if no capture
            moves.add(new Pair<>(position.get_val1() - 1, position.get_val2() + 1)); //remove if no capture
        } else {
            moves.add(new Pair<>(position.get_val1(), position.get_val2() - 1)); //remove if occupied
            moves.add(new Pair<>(position.get_val1(), position.get_val2() - 2)); //remove if not on starting position or occupied
            moves.add(new Pair<>(position.get_val1() - 1, position.get_val2() - 1)); //remove if no capture
            moves.add(new Pair<>(position.get_val1() + 1, position.get_val2() - 1)); //remove if no capture
        }

        //filter moves
        //out of bounds
        moves.removeIf(i ->
            i.get_val1() > 7 || i.get_val1() < 0 ||
            i.get_val2() > 7 || i.get_val2() < 0
        );

        moves.removeIf(i ->
            board.get_piece(i.get_val1(), i.get_val2()) != null && board.get_piece(i.get_val1(), i.get_val2()).color == this.color
        );

        moves.removeIf(i ->
            abs(i.get_val1() - position.get_val1()) == 1 && board.get_piece(i.get_val1(), i.get_val2()) == null
        );

        for(Pair<Integer, Integer> i : moves){
            if(i.equals(new Pair<>(position.get_val1(), position.get_val2() + 2)) && !moves.contains(new Pair<>(position.get_val1(), position.get_val2() + 1))){
                moves.remove(i);
            }
        }

        if(color == Color.WHITE){
            if(!moves.contains(new Pair<>(position.get_val1(), position.get_val2() - 1)) || position.get_val2() != 6){
                moves.remove(new Pair<>(position.get_val1(), position.get_val2() - 2));
            }
        }
        System.out.println(position.get_val1() + " - " + position.get_val2() + "\n");
        return moves;
    }

    @Override
    public void move(int x, int y) {
        Pair<Integer, Integer> move = new Pair<>(x, y);
        if (get_moves().contains(move)) {
            board.set_piece(position.get_val1(), position.get_val2(), null);
            this.position = move;
            board.set_piece(position.get_val1(), position.get_val2(), this);
        }
    }
}