package chess.game;

import chess.board.*;

import javax.swing.*;
import java.awt.*;

public class GameMain{
    public static void main(String[] args) {
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                Board board = new Board();
                JFrame frame = new JFrame("Chess");
                frame.add(board.get_gui());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.pack();

                frame.setMinimumSize(frame.getSize());
                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }


}