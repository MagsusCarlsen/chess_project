package chess.pieces;

import chess.board.*;
import chess.utils.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class King extends Piece{
    public King(Team team, Board board, Pair<Integer, Integer> position){
        super(board, team, position);
        board.set_piece(position.get_val1(), position.get_val2(), this);
        board.set_king(team, position);
        String path = "./src/chess/pieces/resources/";
        if (team == Team.BLACK) {
            path = path + "black/";
        }else{
            path = path + "white/";
        }
        Image image = null;
        try {
            image = ImageIO.read(new File(path + "king.png"));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        image = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
    }
    @Override
    public LinkedList<Pair<Integer, Integer>> get_moves() {
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();
        moves.add(new Pair<>(position.get_val1() + 1, position.get_val2() + 1));
        moves.add(new Pair<>(position.get_val1() + 1, position.get_val2() - 1));
        moves.add(new Pair<>(position.get_val1() + 1, position.get_val2()));
        moves.add(new Pair<>(position.get_val1(), position.get_val2() + 1));
        moves.add(new Pair<>(position.get_val1(), position.get_val2() - 1));
        moves.add(new Pair<>(position.get_val1() - 1, position.get_val2() + 1));
        moves.add(new Pair<>(position.get_val1() - 1, position.get_val2() - 1));
        moves.add(new Pair<>(position.get_val1() - 1, position.get_val2()));

        moves.removeIf(i ->
                i.get_val1() < 0 || i.get_val1() > 7 ||
                        i.get_val2() < 0 || i.get_val2() > 7
        );
        moves.removeIf(i ->
                board.get_piece(i.get_val1(), i.get_val2()) != null
        );
        return moves;
    }

    @Override
    public Boolean move(int x, int y) {
        Pair<Integer, Integer> move = new Pair<>(x, y);
        if(get_moves().contains(move)){
            board.set_piece(position.get_val1(), position.get_val2(), null);
            position = move;
            board.set_piece(position.get_val1(), position.get_val2(), null);
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return "K";
    }
}