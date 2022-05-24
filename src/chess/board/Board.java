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
    public void set_piece(int x, int y, Piece piece) { board[x][y].set_piece(piece);}

    public void print_board(){
        char char_board[8][8] = new char[][];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                switch(board[i][j].piece){
                    case Piece.PAWN:
                        char_board[i][j] = 'P';
                        break;
                    case Piece.KNIGHT:
                        char_board[i][j] = 'N';
                        break;
                    case Piece.BISHOP:
                        char_board[i][j] = 'B';
                        break;
                    case Piece.ROOK:
                        char_board[i][j] = 'R';
                        break;
                    case Piece.QUEEN:
                        char_board[i][j] = 'Q';
                        break;
                    case Piece.KING:
                        char_board[i][j] = 'K';
                        break;
                    case default:
                        if(board[i][j] == Color.WHITE){
                            char_board[i][j] = "0";
                        }else{
                            char_board[i][j]  = "1";
                        }
                }
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.println(board[i][j]);
            }
            System.out.println();
        }
    }
}
