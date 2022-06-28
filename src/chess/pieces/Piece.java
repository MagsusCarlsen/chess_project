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

    public LinkedList<Pair<Integer, Integer>> diagonal(){
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        //minus x, minus y
        for(int i = 1; position.get_val1() - i >= 0 && position.get_val2() - i >= 0; i++){
            if(board.get_piece(position.get_val1() - i, position.get_val2() - i) != null){
                if(board.get_piece(position.get_val1() - i, position.get_val2() - i).team == this.team){
                    break;
                }else{
                    moves.add(new Pair<>(position.get_val1() - i, position.get_val2() - i));
                    break;
                }
            }
            moves.add(new Pair<>(position.get_val1() - i, position.get_val2() - i));
        }
        //minus x, plus y
        for(int i = 1; position.get_val1() - i >= 0 && position.get_val2() + i < 8; i++){
            if(board.get_piece(position.get_val1() - i, position.get_val2() + i) != null){
                if(board.get_piece(position.get_val1() - i, position.get_val2() + i).team == this.team){
                    break;
                }else{
                    moves.add(new Pair<>(position.get_val1() - i, position.get_val2() + i));
                    break;
                }
            }
            moves.add(new Pair<>(position.get_val1() - i, position.get_val2() + i));
        }
        //plus x, minus y
        for(int i = 1; position.get_val1() + i < 8 && position.get_val2() - i >= 0; i++){
            if(board.get_piece(position.get_val1() + i, position.get_val2() - i) != null){
                if(board.get_piece(position.get_val1() + i, position.get_val2() - i).team == this.team){
                    break;
                }else{
                    moves.add(new Pair<>(position.get_val1() + i, position.get_val2() - i));
                    break;
                }
            }
            moves.add(new Pair<>(position.get_val1() + i, position.get_val2() - i));
        }
        //plus x, plus y
        for(int i = 1; position.get_val1() + i < 8 && position.get_val2() + i < 8 && board.get_piece(position.get_val1() + i, position.get_val2() + i) == null; i++){
            if(board.get_piece(position.get_val1() + i, position.get_val2() + i) != null){
                if(board.get_piece(position.get_val1() + i, position.get_val2() + i).team == this.team){
                    break;
                }else{
                    moves.add(new Pair<>(position.get_val1() + i, position.get_val2() + i));
                    break;
                }
            }
            moves.add(new Pair<>(position.get_val1() + i, position.get_val2() + i));
        }
        return moves;
    }
    public LinkedList<Pair<Integer, Integer>> straight(){
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        //minus x
        for(int i = 1; position.get_val1() - i >= 0 && board.get_piece(position.get_val1() - i, position.get_val2()) == null; i++){
            moves.add(new Pair<>(position.get_val1() - i, position.get_val2()));
        }
        //plus x
        for(int i = 1; position.get_val1() + i < 8 && board.get_piece(position.get_val1() + i, position.get_val2()) == null; i++){
            moves.add(new Pair<>(position.get_val1() + i, position.get_val2()));
        }
        //minus y
        for(int i = 1; position.get_val2() - i >= 0 && board.get_piece(position.get_val1(), position.get_val2() - i) == null; i++){
            moves.add(new Pair<>(position.get_val1(), position.get_val2() - i));
        }
        //plus y
        for(int i = 1; position.get_val2() + i < 8 && board.get_piece(position.get_val1(), position.get_val2() + i) == null; i++){
            moves.add(new Pair<>(position.get_val1(), position.get_val2() + i));
        }
        return moves;
    }
}
