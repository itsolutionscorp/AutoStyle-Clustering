package us.bloch.solver.puzzles;

import org.junit.Test;
import us.bloch.solver.puzzles.SlidingBlockPuzzle.Block;

import static org.junit.Assert.*;
import static us.bloch.solver.puzzles.SlidingBlockPuzzleHeuristic.*;

public class SlidingBlockPuzzleHeuristicTest {

    @Test
    public void testManhattanDistanceHeuristic() throws Exception {
        /*
         ┏━━━━┓
         ┃..!.┃
         ┃....┃
         ┃..▯.┃
         ┃....┃
         ┗━━━━┛
         */
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(4, 4,
                new int[]{ Block.fromCornerSize(2, 2, 1, 1) },
                new int[]{ Block.fromCornerSize(2, 0, 1, 1) });
        assertEquals(2, manhattanDistanceHeuristic(p));

        /*
         ┏━━━━┓
         ┃!...┃
         ┃....┃
         ┃..▯.┃
         ┃....┃
         ┗━━━━┛
         */
        p = new SlidingBlockPuzzle(4, 4,
                new int[] { Block.fromCornerSize(2, 2, 1, 1) },
                new int[] { Block.fromCornerSize(0, 0, 1, 1) });
        assertEquals(4, manhattanDistanceHeuristic(p));

        /*
         ┏━━━━┓
         ┃..┌┐┃
         ┃..└┘┃
         ┃..!!┃
         ┃..!!┃
         ┗━━━━┛
         */
        p = new SlidingBlockPuzzle(4, 4,
                new int[] { Block.fromCornerSize(2, 0, 2, 2) },
                new int[] { Block.fromCornerSize(2, 2, 2, 2) });
        assertEquals(2, manhattanDistanceHeuristic(p));


        /*
         ┏━━━━┓
         ┃..┌┐┃
         ┃..└┘┃
         ┃.!!.┃
         ┃.!!.┃
         ┗━━━━┛
         */
        p = new SlidingBlockPuzzle(4, 4,
                new int[] { Block.fromCornerSize(2, 0, 2, 2) },
                new int[] { Block.fromCornerSize(1, 2, 2, 2) });
        assertEquals(3, manhattanDistanceHeuristic(p));

        /*
         ┏━━━━┓
         ┃!...┃
         ┃....┃
         ┃▯.▯.┃
         ┃....┃
         ┗━━━━┛
         */
        p = new SlidingBlockPuzzle(4, 4,
                new int[] { Block.fromCornerSize(2, 2, 1, 1), Block.fromCornerSize(0, 2, 1, 1)},
                new int[] { Block.fromCornerSize(0, 0, 1, 1) });
        assertEquals(2, manhattanDistanceHeuristic(p));
    }

    @Test
    public void testInterferingBlocksHeuristic() throws Exception {
        /*
         ┏━━━━━┓
         ┃┌┐.▯.┃
         ┃└┘▯.▯┃
         ┃.▯.▯.┃
         ┃▯.▯!!┃
         ┃.▯.!!┃
         ┗━━━━━┛
         */
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(5, 5,
                new int[]{
                        Block.fromCornerSize(0, 0, 2, 2),
                        Block.fromCornerSize(3, 0, 1, 1),
                        Block.fromCornerSize(2, 1, 1, 1),
                        Block.fromCornerSize(4, 1, 1, 1),
                        Block.fromCornerSize(1, 2, 1, 1),
                        Block.fromCornerSize(3, 2, 1, 1),
                        Block.fromCornerSize(0, 3, 1, 1),
                        Block.fromCornerSize(2, 3, 1, 1),
                        Block.fromCornerSize(1, 4, 1, 1)},
                new int[]{ Block.fromCornerSize(3, 3, 2, 2) });
        System.out.println(Block.getRightX(Block.fromCornerSize(3, 3, 2, 2)));
        System.out.println("AAAA");
        System.out.println(p);
        assertEquals(8, interferingBlocksHeuristic(p));

        /*
         ┏━━━━━┓
         ┃┌┐.▯.┃
         ┃└┘▯.▯┃
         ┃.▯.▯.┃
         ┃┌┐▯!!┃
         ┃└┘.!!┃
         ┗━━━━━┛
         */
        p = new SlidingBlockPuzzle(5, 5,
                new int[] {
                        Block.fromCornerSize(0, 0, 2, 2),
                        Block.fromCornerSize(3, 0, 1, 1),
                        Block.fromCornerSize(2, 1, 1, 1),
                        Block.fromCornerSize(4, 1, 1, 1),
                        Block.fromCornerSize(1, 2, 1, 1),
                        Block.fromCornerSize(3, 2, 1, 1),
                        Block.fromCornerSize(2, 3, 1, 1),
                        Block.fromCornerSize(0, 3, 2, 2)
                },
                new int[] { Block.fromCornerSize(3, 3, 2, 2) });
        assertEquals(1, interferingBlocksHeuristic(p));
    }

    @Test
    public void testGoalOccupationHeuristic() throws Exception {
        /*
         ┏━━━━┓
         ┃┌┐!!┃
         ┃└┘!!┃
         ┗━━━━┛
         */
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(4, 2,
                new int[] { Block.fromCornerSize(0, 0, 2, 2) },
                new int[] { Block.fromCornerSize(2, 0, 2, 2) });
        assertEquals(0, goalOccupationHeuristic(p));

        /*
         ┏━━━━┓
         ┃┌┐▯!┃
         ┃└┘!▯┃
         ┗━━━━┛
         */
        p = new SlidingBlockPuzzle(4, 2,
                new int[]{
                        Block.fromCornerSize(0, 0, 2, 2),
                        Block.fromCornerSize(2, 0, 1, 1),
                        Block.fromCornerSize(3, 1, 1, 1)},
                new int[]{ Block.fromCornerSize(2, 0, 2, 2)});
        assertEquals(2, goalOccupationHeuristic(p));

        /*
         ┏━━━┓
         ┃┌┐!┃
         ┃└┘!┃
         ┗━━━┛
         */
        p = new SlidingBlockPuzzle(3, 2,
                new int[] { Block.fromCornerSize(0, 0, 2, 2) },
                new int[] { Block.fromCornerSize(1, 0, 2, 2) });
        assertEquals(0, goalOccupationHeuristic(p));
    }
}