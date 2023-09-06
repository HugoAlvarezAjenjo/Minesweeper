package es.alumnosupm.hugoalvarezajenjo.view;

import es.alumnosupm.hugoalvarezajenjo.model.Board;
import es.alumnosupm.hugoalvarezajenjo.model.Cell;
import es.alumnosupm.hugoalvarezajenjo.model.CellStatus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardListerner implements ActionListener {

    private Board board;
    private BoardView boardView;

    public BoardListerner(Board board) {
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
            case MARKED, REVEALED -> {}
            case NONE -> {revealCell(row, column);}
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
                // TODO: End Game
            }
        } else {
            int cellNum = board.getCellNum(row, column);
            revealViewCell(row, column, cellNum);

            if (cellNum == 0) {
                for (int i = row - 1; i <= row + 1 ; i++) {
                    for (int j = column - 1; j < column + 1; j++) {
                        if (board.isInBoard(i, j) && cell.getStatus() == CellStatus.NONE) {
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

        // TODO: Frame View Modifications

    }

}
