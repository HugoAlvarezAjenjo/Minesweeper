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

    public BoardListerner(final Board board) {
        this.board = board;
        this.boardView = new BoardView(board);
        boardView.addButtonsActionListerner(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < boardView.buttons.length; i++) {
            for (int j = 0; j < boardView.buttons[i].length; j++) {
                if (e.getSource() == boardView.buttons[i][j]) checkCell(i, j);
            }
        }
    }

    private void checkCell(final int row, final int column) {
        Cell cell = board.getCell(row, column);

        switch (cell.getStatus()) {
            case MARKED, REVEALED -> {
            }
            case NONE -> {
                revealCell(row, column);
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

        boardView.buttons[row][column].setEnabled(false);
        boardView.buttons[row][column].setText(String.valueOf(cellNum));
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

        for (int i = 0; i < boardView.buttons.length; i++) {
            for (int j = 0; j < boardView.buttons[i].length; j++) {
                boardView.buttons[i][j].setEnabled(false); // Disable the buttons
            }
        }
    }

    public void gameEnd() {
        boardView.textField.setForeground(Color.GREEN);
        boardView.textField.setText("You won!");

        for (int i = 0; i < boardView.buttons.length; i++) {
            for (int j = 0; j < boardView.buttons[i].length; j++) {
                boardView.buttons[i][j].setEnabled(false); // Disable the buttons
            }
        }
    }



}