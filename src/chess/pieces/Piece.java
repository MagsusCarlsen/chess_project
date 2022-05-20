package chess.pieces;
import chess.board.Board;
import chess.utils.Pair;

import java.util.LinkedList;

abstract public class Piece {
    protected Pieces type;
    protected Board board;
    protected final Color color;
    protected Pair<Integer, Integer> position;
    public Piece(Board board, Color color, Pair<Integer, Integer> position){
        this.board = board;
        this.color = color;
        this.position = position;
    }
    abstract public LinkedList<Pair<Integer, Integer>> get_moves();
    abstract public void move(int x, int y);
}
