package es.alumnosupm.hugoalvarezajenjo.model;

import java.util.Random;

/**
 * The `Board` class represents the game board for a Minesweeper game.
 * It contains the grid of cells, tracks the game state, and handles various game logic.
 *
 * @author Hugo Alvarez Ajenjo
 * @version 1.0
 */
public class Board {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int MINE_NUMBER = 10;

    private final Cell[][] cells;
    private final int numRows;
    private final int numCols;
    private final int numMines;
    private int minesLeft;

    private int cellsLeft;
    private boolean isFirstMove;

    /**
     * Constructs a new `Board` object with the specified number of rows, columns, and mines.
     *
     * @param numRows  The number of rows in the board.
     * @param numCols  The number of columns in the board.
     * @param numMines The number of mines to be placed on the board.
     */
    public Board(final int numRows, final int numCols, final int numMines) {
        this.cells = new Cell[numRows][numCols];

        this.numRows = numRows;
        this.numCols = numCols;
        this.numMines = numMines;

        this.minesLeft = numMines;
        this.cellsLeft = numRows * numCols - numMines;
        this.isFirstMove = true;

        initCells();
        placeMines(numMines);
    }

    /**
     * Constructs a new `Board` object with default values for rows, columns, and mines.
     * The default values are 9 rows, 9 columns, and 10 mines.
     */
    public Board() {
        this(ROWS, COLUMNS, MINE_NUMBER);
    }

    /**
     * Checks if the current move is the first move of the game.
     *
     * @return `true` if it's the first move, `false` otherwise.
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /**
     * Sets whether the current move is the first move of the game.
     *
     * @param firstMove `true` if it's the first move, `false` otherwise.
     */
    public void setFirstMove(final boolean firstMove) {
        isFirstMove = firstMove;
    }

    /**
     * Initializes all cells in the board with default values.
     * Creates a new `Cell` object for each cell in the grid.
     */
    private void initCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /**
     * Randomly places the specified number of mines on the board.
     *
     * @param numMines The number of mines to be placed on the board.
     */
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

    /**
     * Retrieves the `Cell` object at the specified row and column.
     *
     * @param row    The row index of the cell.
     * @param column The column index of the cell.
     * @return The `Cell` object at the specified location.
     */
    public Cell getCell(final int row, final int column) {
        return cells[row][column];
    }

    /**
     * Gets the number of rows in the board.
     *
     * @return The number of rows in the board.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Gets the number of columns in the board.
     *
     * @return The number of columns in the board.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Gets the number of mines on the board.
     *
     * @return The number of mines on the board.
     */
    public int getNumMines() {
        return numMines;
    }

    /**
     * Moves a mine from the specified location on the board to another random.
     *
     * @param row    The row index of the mine to move.
     * @param column The column index of the mine to move.
     */
    public void moveMine(final int row, final int column) {
        placeMines(1);
        getCell(row, column).setMine(false);
    }

    /**
     * Checks if the specified row and column are within the bounds of the board.
     *
     * @param row    The row index to check.
     * @param column The column index to check.
     * @return `true` if the coordinates are within the board, `false` otherwise.
     */
    public boolean isInBoard(final int row, final int column) {
        return 0 <= row && row < numRows &&
                0 <= column && column < numCols;
    }

    /**
     * Counts the number of neighboring cells that contain mines around the specified cell.
     *
     * @param row    The row index of the cell.
     * @param column The column index of the cell.
     * @return The number of neighboring cells containing mines.
     */
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

    /**
     * Gets the number of cells that are not revealed yet on the board.
     *
     * @return The number of unrevealed cells.
     */
    public int getCellsLeft() {
        return cellsLeft;
    }

    /**
     * Sets the number of cells that are not revealed yet on the board.
     *
     * @param cellsLeft The number of unrevealed cells to set.
     */
    public void setCellsLeft(final int cellsLeft) {
        this.cellsLeft = cellsLeft;
    }

    /**
     * Gets the number of mines that have not been marked by the player.
     *
     * @return The number of unmarked mines.
     */
    public int getMinesLeft() {
        return minesLeft;
    }

    /**
     * Toggles the marking status of a cell (MARKED or NONE) and updates the number of unmarked mines.
     *
     * @param row    The row index of the cell to toggle.
     * @param column The column index of the cell to toggle.
     */
    public void toggleCellMark(final int row, final int column) {
        Cell cell = getCell(row, column);

        if (cell.getStatus() == CellStatus.NONE) {
            minesLeft--;
        } else if (cell.getStatus() == CellStatus.MARKED) {
            minesLeft++;
        }

        cell.toggleMark();
    }
}
