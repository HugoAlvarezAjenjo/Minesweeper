package es.alumnosupm.hugoalvarezajenjo.view;

import es.alumnosupm.hugoalvarezajenjo.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame {

    Board board;

    JPanel textPanel;
    JLabel textField;

    JPanel buttonPanel;
    JButton[][] buttons;


    public BoardView(Board board) {
        super("Minesweeper");
        this.board = board;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        initScorePanel();
        initButtonPanels();

        add(textPanel, BorderLayout.NORTH);
        add(buttonPanel);

        setSize(570, 570);
        revalidate(); // Refresh
        setLocationRelativeTo(null); // Center of the screen
    }

    private void initScorePanel() {
        textPanel = new JPanel();
        textPanel.setVisible(true);
        textPanel.setBackground(Color.BLACK);

        textField = new JLabel();
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setFont(new Font("MV Boli", Font.BOLD, 20));
        textField.setForeground(Color.BLUE);
        textField.setText(board.getNumMines() + " Bombs");

        textPanel.add(textField);
    }

    private void initButtonPanels() {
        buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        buttonPanel.setLayout(new GridLayout(board.getNumRows(), board.getNumCols()));

        buttons = new JButton[board.getNumRows()][board.getNumCols()];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(null); // TODO: Add ActionListerner
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD,12));
                buttons[i][j].setText("");
                buttonPanel.add(buttons[i][j]);
            }
        }
    }
}
