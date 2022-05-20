package chess.board;

import chess.pieces.Color;
import chess.pieces.Piece;

public class Board {
    private static class Tile{
        private Piece piece;
        private Color color;
        public Tile(Piece piece, Color color){
            this.piece = piece;
            this.color = color;
        }

        public Piece get_piece() {
            return piece;
        }

        public void set_piece(Piece piece) {
            this.piece = piece;
        }

        public Color get_color() {
            return color;
        }

        public void set_color(Color color){
            this.color = color;
        }
    }
    final private Tile[][] board;

    public Board(){
        board = new Tile[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new Tile(null, j % 2 == 0 ? Color.BLACK : Color.WHITE);
            }
        }
    }

    public Piece get_piece(int x, int y){
        return board[x][y].get_piece();
    }
}
