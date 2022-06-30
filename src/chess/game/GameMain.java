package chess.game;

import chess.pieces.*;
import chess.board.*;
import chess.utils.Pair;

import java.util.LinkedList;

public class GameMain {
    public static void main(String[] args){
        Board board = new Board();
        board.fill_board();
        System.out.println(board.game() + " won the game");
    }
}