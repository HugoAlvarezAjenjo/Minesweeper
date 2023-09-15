package es.alumnosupm.hugoalvarezajenjo.model;

import static es.alumnosupm.hugoalvarezajenjo.model.CellStatus.*;

/**
 * The `Cell` class represents a cell in a Minesweeper game grid.
 * Each cell can have a status and may or may not contain a mine.
 *
 * @author Hugo Alvarez Ajenjo
 * @version 1.0
 */
public class Cell {

    private CellStatus status;
    private boolean hasMine;

    /**
     * Constructs a new `Cell` object with an initial status of `NONE` and no mine.
     */
    public Cell() {
        status = NONE;
        hasMine = false;
    }

    /**
     * Checks if this cell contains a mine.
     *
     * @return `true` if the cell contains a mine, `false` otherwise.
     */
    public boolean hasMine() {
        return hasMine;
    }

    /**
     * Gets the status of this cell.
     *
     * @return The current status of the cell.
     */
    public CellStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of this cell.
     *
     * @param cellStatus The new status to set for the cell.
     */
    public void setStatus(final CellStatus cellStatus) {
        this.status = cellStatus;
    }

    /**
     * Sets whether this cell contains a mine or not.
     *
     * @param bool `true` if the cell should contain a mine, `false` otherwise.
     */
    public void setMine(final boolean bool) {
        hasMine = bool;
    }

    /**
     * Toggles the marking status of the cell (MARKED or NONE).
     * Throws a RuntimeException if the cell is already revealed.
     */
    public void toggleMark() {
        if (status == NONE) {
            status = MARKED;
        } else if (status == MARKED) {
            status = NONE;
        } else {
            throw new RuntimeException("Toggle Mark in a revealed cell");
        }
    }

}
