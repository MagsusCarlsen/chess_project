package chess.board;

import chess.pieces.*;
import chess.utils.*;

import java.util.LinkedList;

public class Board {
    private static class Tile{
        private Piece piece;
        private Team team;
        public Tile(Piece piece, Team team){
            this.piece = piece;
            this.team = team;
        }

        public Piece get_piece() {
            return piece;
        }

        public void set_piece(Piece piece) {
            this.piece = piece;
        }

        public Team get_color() {
            return team;
        }

        public void set_color(Team team){
            this.team = team;
        }
    }
    final private Tile[][] board;

    public Board(){
        board = new Tile[8][8];
        Team tile_team = Team.BLACK;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(tile_team == Team.BLACK){
                    board[i][j] = new Tile(null, j % 2 == 0 ? tile_team : Team.WHITE);
                }
                if(tile_team == Team.WHITE){
                    board[i][j] = new Tile(null, j % 2 == 0 ? tile_team : Team.BLACK);
                }
            }
            if(tile_team == Team.BLACK){
                tile_team = Team.WHITE;
            }else{
                tile_team = Team.BLACK;
            }
        }
    }

    public Piece get_piece(int x, int y){
        return board[x][y].get_piece();
    }
    public void set_piece(int x, int y, Piece piece) { board[x][y].set_piece(piece);}

    public void print_board(){
        char[][] char_board = new char[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].piece instanceof Pawn) {
                    char_board[i][j] = 'P';
                } else if (board[i][j].piece instanceof Knight) {
                    char_board[i][j] = 'N';
                } else if (board[i][j].piece instanceof Bishop) {
                    char_board[i][j] = 'B';
                } else if (board[i][j].piece instanceof Rook) {
                    char_board[i][j] = 'R';
                } else if (board[i][j].piece instanceof Queen) {
                    char_board[i][j] = 'Q';
                } /*else if (board[i][j].piece instanceof King) {
                    char_board[i][j] = 'K';
                }*/ else {
                    if (board[i][j].team == Team.WHITE) {
                        char_board[i][j] = '0';
                    } else {
                        char_board[i][j] = '1';
                    }
                }
            }
        }

        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                if(board[i][j].get_piece() == null){
                    continue;
                }
                LinkedList<Pair<Integer, Integer>> moves = board[i][j].get_piece().get_moves();
                for(Pair<Integer, Integer> move : moves){
                    char_board[move.get_val1()][move.get_val2()] = '-';
                }
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(char_board[j][i]);
            }
            System.out.println();
        }
    }
}
