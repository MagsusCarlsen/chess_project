package chess.pieces;

import chess.board.Board;
import chess.utils.Pair;

import java.util.LinkedList;

public class Pawn extends Piece {
    public Pawn(Pieces type, Color color, Board board, Pair<Integer, Integer> position) {
        super(board, color, position);
        this.type = type;
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
            i.get_val1() > 8 || i.get_val1() < 0 ||
            i.get_val2() > 8 || i.get_val2() < 0
        );

        moves.removeIf(i ->
            board.get_piece(i.get_val1(), i.get_val2()).color == this.color
        );

        if(color == Color.BLACK){
            if(!moves.contains(new Pair<>(position.get_val1() + 1, position.get_val2() + 1)) || board.get_piece(position.get_val1() + 1, position.get_val2() + 1) == null){ //order is important
                moves.remove(new Pair<>(position.get_val1() + 1, position.get_val2() + 1));
            }
            if(!moves.contains(new Pair<>(position.get_val1() - 1, position.get_val2() + 1)) || board.get_piece(position.get_val1() - 1, position.get_val2() + 1) == null){ //order is important
                moves.remove(new Pair<>(position.get_val1() - 1, position.get_val2() + 1));
            }
            if(!moves.contains(new Pair<>(position.get_val1(), position.get_val2() + 1)) || board.get_piece(position.get_val1(), position.get_val2() + 1) != null){
                moves.remove(new Pair<>(position.get_val1(), position.get_val2() + 1));
                moves.remove(new Pair<>(position.get_val1(), position.get_val2() + 2));
            }
            if(!moves.contains(new Pair<>(position.get_val1(), position.get_val2() + 2)) || position.get_val2() != 1 || board.get_piece(position.get_val1(), position.get_val2() + 2) != null){
                moves.remove(new Pair<>(position.get_val1(), position.get_val2() + 2));
            }
        } else {
            if(!moves.contains(new Pair<>(position.get_val1() - 1, position.get_val2() - 1)) || board.get_piece(position.get_val1() - 1, position.get_val2() - 1) == null){ //order is important
                moves.remove(new Pair<>(position.get_val1() - 1, position.get_val2() - 1));
            }
            if(!moves.contains(new Pair<>(position.get_val1() + 1, position.get_val2() - 1)) || board.get_piece(position.get_val1() + 1, position.get_val2() - 1) == null){ //order is important
                moves.remove(new Pair<>(position.get_val1() + 1, position.get_val2() - 1));
            }

            if(!moves.contains(new Pair<>(position.get_val1(), position.get_val2() - 1)) || board.get_piece(position.get_val1(), position.get_val2() - 1) != null){
                moves.remove(new Pair<>(position.get_val1(), position.get_val2() - 1));
                moves.remove(new Pair<>(position.get_val1(), position.get_val2() - 2));
            }

            if(!moves.contains(new Pair<>(position.get_val1(), position.get_val2() - 2)) || position.get_val2() != 1 || board.get_piece(position.get_val1(), position.get_val2() - 2) != null){
                moves.remove(new Pair<>(position.get_val1(), position.get_val2() - 2));
            }
        }
        return moves;
    }

    @Override
    public void move(int x, int y) {
        Pair<Integer, Integer> move = new Pair<>(x, y);
        if (get_moves().contains(move)) {
            this.position = move;
        }
    }
}