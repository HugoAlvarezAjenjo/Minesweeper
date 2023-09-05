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

    public void setStatus(CellStatus cellStatus) {
        this.status = cellStatus;
    }

    public void reveal() {
        setStatus(REVEALED);
    }

    public void setMine(boolean bool) {
        hasMine = bool;
    }

    public void setMine() {
        setMine(true);
    }
}
