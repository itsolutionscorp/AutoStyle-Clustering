package us.bloch.solver.puzzles;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class SquarePuzzleTest {

    @Test
    public void testCalculateDistanceScore() throws Exception {
        SquarePuzzle board = new SquarePuzzle(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(0, board.calculateDistanceScore());
        board = new SquarePuzzle(Arrays.asList(1, 0, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(2, board.calculateDistanceScore());
        board = new SquarePuzzle(Arrays.asList(3, 1, 2, 0, 4, 5, 6, 7, 8));
        assertEquals(2, board.calculateDistanceScore());
        board = new SquarePuzzle(Arrays.asList(4, 1, 2, 3, 0, 5, 6, 7, 8));
        assertEquals(4, board.calculateDistanceScore());
        board = new SquarePuzzle(Arrays.asList(1, 4, 2, 3, 0, 5, 6, 7, 8));
        assertEquals(4, board.calculateDistanceScore());
    }

    @Test
    public void testCalculateMisplacedTilesScore() throws Exception {

    }

    @Test
    public void testTileAt() throws Exception {
        SquarePuzzle board = new SquarePuzzle(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(0, board.tileAt(0, 0));
        assertEquals(1, board.tileAt(0, 1));
        assertEquals(2, board.tileAt(0, 2));
        assertEquals(3, board.tileAt(1, 0));
        assertEquals(4, board.tileAt(1, 1));
        assertEquals(5, board.tileAt(1, 2));
        assertEquals(6, board.tileAt(2, 0));
        assertEquals(7, board.tileAt(2, 1));
        assertEquals(8, board.tileAt(2, 2));
    }
}