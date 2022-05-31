package chess.pieces;
import chess.board.Board;
import chess.utils.Pair;

import java.util.LinkedList;

abstract public class Piece {
    protected Board board;
    protected final Team team;
    protected Pair<Integer, Integer> position;
    public Piece(Board board, Team team, Pair<Integer, Integer> position){
        this.board = board;
        this.team = team;
        this.position = position;
    }
    abstract public LinkedList<Pair<Integer, Integer>> get_moves();
    abstract public void move(int x, int y);

    public LinkedList<Pair<Integer, Integer>> block_moves(LinkedList<Pair<Integer, Integer>> moves){
        LinkedList<Pair<Integer, Integer>> new_moves = new LinkedList<>();
        for(Pair<Integer, Integer> i : moves){
        }
        return new_moves;
    }
}
