package es.alumnosupm.hugoalvarezajenjo.model;

import java.util.Random;

public class Board {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int MINE_NUMBER = 10;

    private final Cell[][] cells;
    private final int numRows;
    private final int numCols;
    private final int numMines;

    private int cellsLeft;
    private boolean isFirstMove;

    public Board(final int numRows, final int numCols, final int numMines) {
        this.cells = new Cell[numRows][numCols];

        this.numRows = numRows;
        this.numCols = numCols;
        this.numMines = numMines;

        this.cellsLeft = numRows * numCols - numMines;
        this.isFirstMove = true;

        initCells();
        placeMines(numMines);
    }

    public Board() {
        this(ROWS, COLUMNS, MINE_NUMBER);
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(final boolean firstMove) {
        isFirstMove = firstMove;
    }

    private void initCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void placeMines(final int numMines) {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < numMines) {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numCols);

            if (!cells[row][col].hasMine()) {
                cells[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    public Cell getCell(final int row, final int column) {
        return cells[row][column];
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumMines() {
        return numMines;
    }

    public void moveMine(final int row, final int column) {
        placeMines(1);
        getCell(row, column).setMine(false);
    }

    public boolean isInBoard(final int row, final int column) {
        return 0 <= row && row < numRows &&
                0 <= column && column < numCols;
    }

    public int getCellNum(final int row, final int column) {
        int cellNum = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (isInBoard(i, j) && !(i == row && j == column) && getCell(i, j).hasMine()) {
                    cellNum++;
                }
            }

        }


        return cellNum;
    }

    public int getCellsLeft() {
        return cellsLeft;
    }

    public void setCellsLeft(final int cellsLeft) {
        this.cellsLeft = cellsLeft;
    }
}
