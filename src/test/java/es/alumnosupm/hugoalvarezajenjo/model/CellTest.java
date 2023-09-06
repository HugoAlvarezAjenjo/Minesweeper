package es.alumnosupm.hugoalvarezajenjo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell1;
    Cell cell2;

    @BeforeEach
    void setUp() {
        cell1 = new Cell();
        cell2 = new Cell();
    }

    @Test
    void hasMine() {
        assertFalse(cell1.hasMine());
        assertFalse(cell2.hasMine());
    }

    @Test
    void setMine(){
        cell1.setMine(true);
        assertTrue(cell1.hasMine());

        cell1.setMine(false);
        assertFalse(cell1.hasMine());

        cell2.setMine(false);
        assertFalse(cell2.hasMine());
    }

    @Test
    void getStatus() {
        assertEquals(CellStatus.NONE, cell1.getStatus());
    }

    void setStatus() {
        assertEquals(CellStatus.NONE, cell1.getStatus());
        assertEquals(CellStatus.NONE, cell2.getStatus());

        cell1.setStatus(CellStatus.REVEALED);
        cell2.setStatus(CellStatus.MARKED);

        assertEquals(CellStatus.REVEALED, cell1.getStatus());
        assertEquals(CellStatus.MARKED, cell2.getStatus());

        cell1.setStatus(CellStatus.NONE);
        assertEquals(CellStatus.NONE, cell1.getStatus());
    }

}