package es.alumnosupm.hugoalvarezajenjo.view;

import es.alumnosupm.hugoalvarezajenjo.model.Board;
import es.alumnosupm.hugoalvarezajenjo.model.Cell;
import es.alumnosupm.hugoalvarezajenjo.model.CellStatus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardListerner implements ActionListener {

    private final Board board;
    private final BoardView boardView;

    private SelectionMode selectionMode;

    public BoardListerner(final Board board) {
        this.selectionMode = SelectionMode.DIG_MODE;
        this.board = board;
        this.boardView = new BoardView(board);
        boardView.addActionListerners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boardView.resetButton) {
            resetBoard();
        } else if (e.getSource() == boardView.flagButton) {
            alterateSelectionMode();
        } else {
            for (int i = 0; i < boardView.buttons.length; i++) {
                for (int j = 0; j < boardView.buttons[i].length; j++) {
                    if (e.getSource() == boardView.buttons[i][j]) checkCell(i, j);
                }
            }
        }
    }

    private void alterateSelectionMode() {
        switch (selectionMode) {
            case FLAG_MODE -> {
                this.selectionMode = SelectionMode.DIG_MODE;
                this.boardView.flagButton.setBackground(null);
            }
            case DIG_MODE -> {
                this.selectionMode = SelectionMode.FLAG_MODE;
                this.boardView.flagButton.setBackground(Color.RED);
            }
        }
    }

    private Color getNumberColor(final int num) {
        Color color = Color.BLACK;

        switch (num) {
            case 0 -> {color = Color.WHITE;}
            case 1 -> {color = Color.BLUE;}
            case 2 -> {color = Color.GREEN;}
            case 3 -> {color = Color.RED;}
            case 4 -> {color = Color.MAGENTA;}
            case 5 -> {color = new Color(128, 0, 128);}
            case 6 -> {color = Color.CYAN;}
            case 7 -> {new Color(42, 13, 93);}
            case 8 -> {color = Color.LIGHT_GRAY;}
            default -> {throw new RuntimeException("Invalid getNumberColor num");}
        }

        return color;
    }

    private void resetBoard() {
        boardView.dispose();
        new BoardListerner(new Board());
    }

    private void checkCell(final int row, final int column) {
        Cell cell = board.getCell(row, column);

        switch (selectionMode) {
            case DIG_MODE -> {
                if (cell.getStatus() == CellStatus.NONE) {
                    revealCell(row, column);
                }
            }
            case FLAG_MODE -> {
                toggleCellMark(row, column);
            }
        }
    }

    private void toggleCellMark(final int row, final int column) {
        if (board.getCell(row, column).getStatus() != CellStatus.REVEALED) {
            board.toggleCellMark(row, column);
            int minesLeft = board.getMinesLeft();
            boardView.textField.setText(minesLeft + " Bombs");
            if (minesLeft == 0) {
                boardView.textField.setForeground(Color.GREEN);
            } else if (minesLeft > 0) {
                boardView.textField.setForeground(Color.BLUE);
            } else {
                boardView.textField.setForeground(Color.RED);
            }

            if (board.getCell(row, column).getStatus() == CellStatus.MARKED) {
                boardView.buttons[row][column].setText("|>");
                boardView.buttons[row][column].setBackground(Color.ORANGE);
                boardView.buttons[row][column].setForeground(Color.RED);
            } else if (board.getCell(row, column).getStatus() == CellStatus.NONE) {
                boardView.buttons[row][column].setText("");
                boardView.buttons[row][column].setBackground(null);
                boardView.buttons[row][column].setForeground(null);
            }
        }
    }

    private void revealCell(final int row, final int column) {
        Cell cell = board.getCell(row, column);

        if (cell.hasMine()) {
            if (board.isFirstMove()) {
                board.setFirstMove(false);
                board.moveMine(row, column);
                revealCell(row, column);
            } else {
                gameOver();
            }
        } else {
            int cellNum = board.getCellNum(row, column);
            board.setFirstMove(false);
            revealViewCell(row, column, cellNum);

            if (cellNum == 0) {
                boardView.buttons[row][column].setEnabled(false);
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = column - 1; j <= column + 1; j++) {
                        if (board.isInBoard(i, j) && board.getCell(i, j).getStatus() == CellStatus.NONE) {
                            revealCell(i, j);
                        }
                    }
                }

            }


        }

    }

    private void revealViewCell(final int row, final int column, final int cellNum) {
        Cell cell = board.getCell(row, column);
        cell.setStatus(CellStatus.REVEALED);

        boardView.buttons[row][column].setText(String.valueOf(cellNum));
        boardView.buttons[row][column].setForeground(getNumberColor(cellNum));
        board.setCellsLeft(board.getCellsLeft() - 1);

        checkWinner();

    }

    private void checkWinner() {
        if (board.getCellsLeft() == 0) {
            gameEnd();
        }
    }

    //* Simplificar estas dos funciones
    public void gameOver() {
        boardView.textField.setForeground(Color.RED);
        boardView.textField.setText("Game Over!");

        disableButtons();
    }

    public void gameEnd() {
        boardView.textField.setForeground(Color.GREEN);
        boardView.textField.setText("You won!");

        disableButtons();
    }

    private void disableButtons() {
        for (int i = 0; i < boardView.buttons.length; i++) {
            for (int j = 0; j < boardView.buttons[i].length; j++) {
                boardView.buttons[i][j].setEnabled(false); // Disable the buttons
            }
        }
    }


}