package chess.board;

import chess.pieces.*;
import chess.utils.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Scanner;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Board implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton button = (AbstractButton) e.getSource();
        if(button.getBackground() == Color.YELLOW){
            move_to.set_val1(Character.getNumericValue(e.getActionCommand().charAt(0)));
            move_to.set_val2(Character.getNumericValue(e.getActionCommand().charAt(1)));
            for(int i = 0; i < tile_buttons.length; i++){
                for(int j = 0; j < tile_buttons.length; j++){
                    if((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)){
                        tile_buttons[i][j].setBackground(Color.WHITE);
                    }else{
                        tile_buttons[i][j].setBackground(Color.BLACK);
                    }
                }
            }
            if(move_to.get_val1() != null && move_to.get_val2() != null){
                game_step(player, move_from, move_to);
                move_from.set_val1(null);
                move_from.set_val2(null);
                move_to.set_val1(null);
                move_to.set_val2(null);
                refresh_board();
                if(checkmate(player) && check(player)){
                    Runtime run = Runtime.getRuntime();
                    System.out.println(player + " lost");
                    run.exit(0);
                }
            }
            return;
        }

        for(int i = 0; i < tile_buttons.length; i++){
            for(int j = 0; j < tile_buttons.length; j++){
                if((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)){
                    tile_buttons[i][j].setBackground(Color.WHITE);
                }else{
                    tile_buttons[i][j].setBackground(Color.BLACK);
                }
            }
        }

        Piece piece = board[Character.getNumericValue(e.getActionCommand().charAt(0))][Character.getNumericValue(e.getActionCommand().charAt(1))].get_piece();
        if(piece != null){
            move_from.set_val1(Character.getNumericValue(e.getActionCommand().charAt(0)));
            move_from.set_val2(Character.getNumericValue(e.getActionCommand().charAt(1)));
            LinkedList<Pair<Integer, Integer>> moves = piece.get_moves();
            for(Pair<Integer, Integer> i : moves){
                tile_buttons[i.get_val2()][i.get_val1()].setBackground(Color.YELLOW);
            }
        }
    }

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
    private final Tile[][] board;
    private final JPanel gui;
    private final JButton[][] tile_buttons;
    private final JPanel board_panel;
    private Pair<Integer, Integer> king_black;
    private Pair<Integer, Integer> king_white;
    private Team player;
    private final Pair<Integer, Integer> move_from;
    private final Pair<Integer, Integer> move_to;

    public Board(){

        board = new Tile[8][8];
        move_from = new Pair<>(null, null);
        move_to = new Pair<>(null, null);
        player = Team.WHITE;
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
        fill_board();

        gui = new JPanel(new BorderLayout(3, 3));

        tile_buttons = new JButton[8][8];
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));

        board_panel = new JPanel(new GridLayout(0, 8));
        board_panel.setBorder(new LineBorder(Color.BLACK));
        gui.add(board_panel);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for(int i = 0; i < tile_buttons.length; i++){
            for(int j = 0; j < tile_buttons.length; j++){
                JButton btn = new JButton();
                btn.setMargin(buttonMargin);
                if((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)){
                    btn.setBackground(Color.WHITE);
                }else{
                    btn.setBackground(Color.BLACK);
                }
                tile_buttons[i][j] = btn;
                tile_buttons[i][j].setActionCommand(j + Integer.toString(i));
                tile_buttons[i][j].addActionListener(this);
            }
        }
        refresh_board();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board_panel.add(tile_buttons[i][j]);
            }
        }
    }

    public void refresh_board(){
        for(int i = 0; i < tile_buttons.length; i++){
            for(int j = 0; j < tile_buttons.length; j++){
                Piece piece = board[i][j].get_piece();
                if(piece != null) {
                    tile_buttons[j][i].setIcon(piece.get_icon());
                }else{
                    tile_buttons[j][i].setIcon(new ImageIcon(new BufferedImage(64, 64, TYPE_INT_ARGB)));
                }
            }
        }
    }

    public final JComponent get_board_panel(){return board_panel;}
    public final JComponent get_gui(){return gui;}

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
        if(previous.get_val1()  == null || previous.get_val2() == null || move.get_val1() == null || move.get_val2() == null){
            return false;
        }
        Piece piece = get_piece(previous.get_val1(), previous.get_val2());
        if(piece != null && piece.get_team() == team){
            if(piece.move(move.get_val1(), move.get_val2())){
                if(check(team)){
                    piece.move(previous.get_val1(), previous.get_val2());
                    return false;
                }
                if (player == Team.WHITE) {player = Team.BLACK;}
                else{player = Team.WHITE;}
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
}