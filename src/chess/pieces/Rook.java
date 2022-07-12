package chess.pieces;

import chess.utils.*;
import chess.board.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
public class Rook extends Piece{
    public Rook(Team team, Board board, Pair<Integer, Integer> position){
        super(board, team, position);
        board.set_piece(position.get_val1(), position.get_val2(), this);
        String path = "./src/chess/pieces/resources/";
        if (team == Team.BLACK) {
            path = path + "black/";
        }else{
            path = path + "white/";
        }
        Image image = null;
        try {
            image = ImageIO.read(new File(path + "rook.png"));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        image = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
    }
    @Override
    public LinkedList<Pair<Integer, Integer>> get_moves() {
        return straight();
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
        return "R";
    }
}
