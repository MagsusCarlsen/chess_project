package chess.board;

import chess.pieces.*;

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
        Color tile_color = Color.BLACK;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(tile_color == Color.BLACK){
                    board[i][j] = new Tile(null, j % 2 == 0 ? tile_color : Color.WHITE);
                }
                if(tile_color == Color.WHITE){
                    board[i][j] = new Tile(null, j % 2 == 0 ? tile_color : Color.BLACK);
                }
            }
            if(tile_color == Color.BLACK){
                tile_color = Color.WHITE;
            }else{
                tile_color = Color.BLACK;
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
                } /*else if (board[i][j].piece instanceof Bishop) {
                    char_board[i][j] = 'B';
                } else if (board[i][j].piece instanceof Rook) {
                    char_board[i][j] = 'R';
                } else if (board[i][j].piece instanceof Queen) {
                    char_board[i][j] = 'Q';
                } else if (board[i][j].piece instanceof King) {
                    char_board[i][j] = 'K';
                }*/ else {
                    if (board[i][j].color == Color.WHITE) {
                        char_board[i][j] = '0';
                    } else {
                        char_board[i][j] = '1';
                    }
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
