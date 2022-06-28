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
}
