package es.alumnosupm.hugoalvarezajenjo.view;

import es.alumnosupm.hugoalvarezajenjo.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The `BoardView` class represents the graphical user interface (GUI) for a Minesweeper game board.
 * It displays the game board, buttons, and game information to the player.
 *
 * @author Hugo Alvarez Ajenjo
 * @version 0.0
 */
public class BoardView extends JFrame {

    private final Board board;

    JPanel textPanel;
    JLabel textField;

    JPanel buttonPanel;
    JButton[][] buttons;
    JButton resetButton;
    JButton flagButton;


    /**
     * Constructs a new `BoardView` with the specified game board.
     *
     * @param board The game board to display.
     */
    public BoardView(final Board board) {
        super("Minesweeper");
        this.board = board;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        initScorePanel();
        initButtonPanels();
        initResetButton();
        initFlagButton();

        add(textPanel, BorderLayout.NORTH);
        add(resetButton, BorderLayout.SOUTH);
        add(flagButton, BorderLayout.WEST);
        add(buttonPanel);

        setSize(570, 570);
        revalidate(); // Refresh
        setLocationRelativeTo(null); // Center of the screen
    }

    /**
     * Initializes the score panel at the top of the game board.
     * This panel displays the number of remaining bombs.
     */
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

    /**
     * Initializes the button grid panel where each cell is represented by a button.
     */
    private void initButtonPanels() {
        buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        buttonPanel.setLayout(new GridLayout(board.getNumRows(), board.getNumCols()));

        buttons = new JButton[board.getNumRows()][board.getNumCols()];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFocusable(false);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD,12));
                buttons[i][j].setText("");
                buttonPanel.add(buttons[i][j]);
            }
        }
    }

    /**
     * Initializes the "Reset" button at the bottom of the game board.
     */
    private void initResetButton() {
        resetButton = new JButton();
        resetButton.setForeground(Color.BLUE);
        resetButton.setBackground(Color.WHITE);
        resetButton.setText("Reset");
        resetButton.setFont(new Font("MV Boli", Font.BOLD, 20));
        resetButton.setFocusable(false);

    }

    /**
     * Initializes the "Flag" button on the left side of the game board.
     */
    private void initFlagButton() {
        flagButton = new JButton();
        flagButton.setForeground(Color.ORANGE);
        flagButton.setBackground(Color.WHITE);
        flagButton.setText("|>");
        flagButton.setFont(new Font("MV Boli", Font.BOLD, 20));
        flagButton.setFocusable(false);
    }

    /**
     * Adds action listeners to buttons and controls.
     *
     * @param actionListener The action listener to be added to buttons and controls.
     */
    public void addActionListerners(ActionListener actionListener) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].addActionListener(actionListener);
            }
        }

        resetButton.addActionListener(actionListener);
        flagButton.addActionListener(actionListener);

    }

}