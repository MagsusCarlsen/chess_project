package chess.pieces;
import chess.board.Board;
import chess.utils.Pair;

import javax.swing.*;
import java.util.LinkedList;

abstract public class Piece {
    protected Board board;
    protected final Team team;
    protected ImageIcon icon;
    protected Pair<Integer, Integer> position;
    public Piece(Board board, Team team, Pair<Integer, Integer> position){
        this.board = board;
        this.team = team;
        this.position = position;
    }
    abstract public LinkedList<Pair<Integer, Integer>> get_moves();
    abstract public Boolean move(int x, int y);

    public Team get_team(){
        return team;
    }
    public ImageIcon get_icon(){
        return icon;
    }

    public LinkedList<Pair<Integer, Integer>> diagonal(){
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        //minus x, minus y
        for(int i = 1; position.get_val1() - i >= 0 && position.get_val2() - i >= 0; i++){
            if(board.get_piece(position.get_val1() - i, position.get_val2() - i) != null){
                if (board.get_piece(position.get_val1() - i, position.get_val2() - i).team != this.team) {
                    moves.add(new Pair<>(position.get_val1() - i, position.get_val2() - i));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1() - i, position.get_val2() - i));
        }
        //minus x, plus y
        for(int i = 1; position.get_val1() - i >= 0 && position.get_val2() + i < 8; i++){
            if(board.get_piece(position.get_val1() - i, position.get_val2() + i) != null){
                if (board.get_piece(position.get_val1() - i, position.get_val2() + i).team != this.team) {
                    moves.add(new Pair<>(position.get_val1() - i, position.get_val2() + i));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1() - i, position.get_val2() + i));
        }
        //plus x, minus y
        for(int i = 1; position.get_val1() + i < 8 && position.get_val2() - i >= 0; i++){
            if(board.get_piece(position.get_val1() + i, position.get_val2() - i) != null){
                if (board.get_piece(position.get_val1() + i, position.get_val2() - i).team != this.team) {
                    moves.add(new Pair<>(position.get_val1() + i, position.get_val2() - i));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1() + i, position.get_val2() - i));
        }
        //plus x, plus y
        for(int i = 1; position.get_val1() + i < 8 && position.get_val2() + i < 8; i++){
            if(board.get_piece(position.get_val1() + i, position.get_val2() + i) != null){
                if (board.get_piece(position.get_val1() + i, position.get_val2() + i).team != this.team) {
                    moves.add(new Pair<>(position.get_val1() + i, position.get_val2() + i));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1() + i, position.get_val2() + i));
        }
        return moves;
    }
    public LinkedList<Pair<Integer, Integer>> straight(){
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        //minus x
        for(int i = 1; position.get_val1() - i >= 0; i++){
            if(board.get_piece(position.get_val1() - i, position.get_val2()) != null){
                if (board.get_piece(position.get_val1() - i, position.get_val2()).team != this.team) {
                    moves.add(new Pair<>(position.get_val1() - i, position.get_val2()));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1() - i, position.get_val2()));
        }
        //plus x
        for(int i = 1; position.get_val1() + i < 8; i++){
            if(board.get_piece(position.get_val1() + i, position.get_val2()) != null){
                if (board.get_piece(position.get_val1() + i, position.get_val2()).team != this.team) {
                    moves.add(new Pair<>(position.get_val1() + i, position.get_val2()));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1() + i, position.get_val2()));
        }
        //minus y
        for(int i = 1; position.get_val2() - i >= 0; i++){
            if(board.get_piece(position.get_val1(), position.get_val2() - i) != null){
                if (board.get_piece(position.get_val1(), position.get_val2() - i).team != this.team) {
                    moves.add(new Pair<>(position.get_val1(), position.get_val2() - i));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1(), position.get_val2() - i));
        }
        //plus y
        for(int i = 1; position.get_val2() + i < 8; i++){
            if(board.get_piece(position.get_val1(), position.get_val2() + i) != null){
                if (board.get_piece(position.get_val1(), position.get_val2() + i).team != this.team) {
                    moves.add(new Pair<>(position.get_val1(), position.get_val2() + i));
                }
                break;
            }
            moves.add(new Pair<>(position.get_val1(), position.get_val2() + i));
        }
        return moves;
    }
}
