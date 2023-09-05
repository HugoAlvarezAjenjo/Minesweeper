package es.alumnosupm.hugoalvarezajenjo.model;

public class Cell {
    private boolean isRevealed;
    private boolean hasMine;
    private boolean isMarked;

    public Cell() {
        isMarked = false;
        isRevealed = false;
        hasMine = false;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void toggleMark() {
        isMarked = !isMarked;
    }

    public void reveal() {
        isRevealed = true;
    }

    public void setMine(boolean bool) {
        hasMine = bool;
    }

    public void setMine() {
        setMine(true);
    }
}
