package chess.board;

import chess.pieces.*;
import chess.utils.*;

import java.util.LinkedList;
import java.util.Scanner;

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
    private Pair<Integer, Integer> king_black;
    private Pair<Integer, Integer> king_white;

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

    public void set_king(Team team, Pair<Integer, Integer> position){
        if(team == Team.BLACK){
            king_black = position;
        }else{
            king_white = position;
        }
    }

    public void fill_board(){
        //Pawns
        for(int i = 0; i < 8; i++){
            new Pawn(Team.BLACK, this, new Pair<>(i, 1));
            new Pawn(Team.WHITE, this, new Pair<>(i, 6));
        }
        //Rooks
        new Rook(Team.BLACK, this, new Pair<>(0, 0));
        new Rook(Team.BLACK, this, new Pair<>(7, 0));
        new Rook(Team.WHITE, this, new Pair<>(0, 7));
        new Rook(Team.WHITE, this, new Pair<>(7, 7));
        //Bishops
        new Bishop(Team.BLACK, this, new Pair<>(2, 0));
        new Bishop(Team.BLACK, this, new Pair<>(5, 0));
        new Bishop(Team.WHITE, this, new Pair<>(2, 7));
        new Bishop(Team.WHITE, this, new Pair<>(5, 7));
        //Knights
        new Knight(Team.BLACK, this, new Pair<>(1, 0));
        new Knight(Team.BLACK, this, new Pair<>(6, 0));
        new Knight(Team.WHITE, this, new Pair<>(1, 7));
        new Knight(Team.WHITE, this, new Pair<>(6, 7));
        //Queens
        new Queen(Team.BLACK, this, new Pair<>(4, 0));
        new Queen(Team.WHITE, this, new Pair<>(4, 7));
        //Kings
        new King(Team.BLACK, this, new Pair<>(3, 0));
        new King(Team.WHITE, this, new Pair<>(3, 7));
    }

    public void print_board(){
        String[][] board_string = new String[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(get_piece(j, i) == null) {
                    board_string[j][i] = "0";
                }else{
                    board_string[j][i] = get_piece(j, i).toString();
                }
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(board_string[j][i]);
            }
            System.out.println();
        }
    }

    public Boolean is_seen(Team team, Pair<Integer, Integer> position){
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                Piece piece = get_piece(i, j);
                if(piece != null && piece.get_team() == team){
                    if(piece.get_moves().stream().anyMatch(temp -> temp.equals(position))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean game_step(Team team, Pair<Integer, Integer> previous, Pair<Integer, Integer> move){
        Piece piece = get_piece(previous.get_val1(), previous.get_val2());
        if(piece != null && piece.get_team() == team){
            if(piece.move(move.get_val1(), move.get_val2())){
                if(check(team)){
                    piece.move(previous.get_val1(), previous.get_val2());
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public Boolean check(Team team){
        if(team == Team.BLACK){
            return is_seen(team, king_black);
        }else{
            return is_seen(team, king_white);
        }
    }

    public Boolean checkmate(Team team){
        LinkedList<Pair<Integer, Integer>> moves;
        if(team == Team.BLACK){
            moves = get_piece(king_black.get_val1(), king_black.get_val2()).get_moves();
            for(Pair<Integer, Integer> i : moves){
                if(!is_seen(Team.WHITE, i)){
                    return false;
                }
            }
        }else{
            moves = get_piece(king_white.get_val1(), king_white.get_val2()).get_moves();
            for(Pair<Integer, Integer> i : moves){
                if(!is_seen(Team.BLACK, i)){
                    return false;
                }
            }
        }
        return true;
    }
    public Team game(){
        Scanner scan = new Scanner(System.in);
        Board board = new Board();
        board.fill_board();
        board.print_board();
        Team player = Team.WHITE;
        do{
            Pair<Integer, Integer> piece = new Pair<>(null, null);
            Pair<Integer, Integer> move = new Pair<>(null, null);
            do {
                piece.set_val1(scan.nextInt());
                piece.set_val2(scan.nextInt());
                move.set_val1(scan.nextInt());
                move.set_val2(scan.nextInt());
            }while(!game_step(player, piece, move));
            System.out.println("valid");
            print_board();
            if(player == Team.BLACK){player = Team.WHITE;}
            else{player = Team.BLACK;}
        }while(!checkmate(player));
        return player == Team.WHITE ? Team.BLACK : Team.WHITE;
    }
}