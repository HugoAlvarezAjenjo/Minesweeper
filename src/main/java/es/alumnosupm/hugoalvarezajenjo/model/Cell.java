package es.alumnosupm.hugoalvarezajenjo.model;

import static es.alumnosupm.hugoalvarezajenjo.model.CellStatus.*;

public class Cell {

    private CellStatus status;
    private boolean hasMine;

    public Cell() {
        status = NONE;
        hasMine = false;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(final CellStatus cellStatus) {
        this.status = cellStatus;
    }

    public void setMine(final boolean bool) {
        hasMine = bool;
    }

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
