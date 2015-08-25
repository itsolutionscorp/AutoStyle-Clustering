package us.bloch.solver.puzzles;

import org.junit.Test;
import us.bloch.solver.puzzles.SlidingBlockPuzzle.Block;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SlidingBlockPuzzleTest {

    @Test
    public void testGetBlockAt() throws Exception {
        int bigBlock = Block.fromCornerSize(0, 0, 2, 2);
        int littleBlock = Block.fromCornerSize(1, 2, 1, 1);
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(3, 3,
                new int[] {bigBlock, littleBlock}, new int[0]);

        assertEquals(p.getBlockAt(0, 0), bigBlock);
        assertEquals(p.getBlockAt(1, 1), bigBlock);
        assertEquals(p.getBlockAt(1, 2), littleBlock);
        assertEquals(SlidingBlockPuzzle.ABSENT, p.getBlockAt(2, 2));
    }

    @Test
    public void testSuccessors() throws Exception {
        /*
        -----    -----   -----   -----
        |AA |    | AA|   |AA |   |AA |
        |AA | -> | AA| + |AA | + |AA |
        | B |    | B |   |B  |   |  B|
        -----    -----   -----   -----
         */
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(3, 3,
                new int[]{ Block.fromCornerSize(0, 0, 2, 2), Block.fromCornerSize(1, 2, 1, 1) }, new int[0]);
        System.out.println(p);
        List<SlidingBlockPuzzle> successors = p.successors();
        successors.forEach(System.out::println);
        assertEquals(3, successors.size()); // TODO Check better!
        System.out.println("----------------------------------------");

        /*
         ┏━━━━┓  ->  ┏━━━━┓ | ┏━━━━┓ | ┏━━━━┓ | ┏━━━━┓ | ┏━━━━┓ | ┏━━━━┓
         ┃..┌┐┃  ->  ┃┌┐..┃ | ┃.┌┐.┃ | ┃..┌┐┃ | ┃..┌┐┃ | ┃..┌┐┃ | ┃..┌┐┃
         ┃..└┘┃  ->  ┃└┘..┃ | ┃.└┘.┃ | ┃..└┘┃ | ┃..└┘┃ | ┃..└┘┃ | ┃..└┘┃
         ┃..▯.┃  ->  ┃..▯.┃ | ┃..▯.┃ | ┃▯...┃ | ┃.▯..┃ | ┃...▯┃ | ┃....┃
         ┃....┃  ->  ┃....┃ | ┃....┃ | ┃....┃ | ┃....┃ | ┃....┃ | ┃..▯.┃
         ┗━━━━┛  ->  ┗━━━━┛ | ┗━━━━┛ | ┗━━━━┛ | ┗━━━━┛ | ┗━━━━┛ | ┗━━━━┛
         */
        p = new SlidingBlockPuzzle(
                4, 4,
                new int[]{
                        Block.fromCornerSize(2, 0, 2, 2),
                        Block.fromCornerSize(2, 2, 1, 1)
                }, new int[0]);
        System.out.println(p);
        successors = p.successors();
        successors.forEach(System.out::println);
        assertEquals(6, successors.size());
        System.out.println("----------------------------------------");

        /*
         ┏━━━━━┓  ->  ┏━━━━━┓ | ┏━━━━━┓ | ┏━━━━━┓ | ┏━━━━━┓ | ┏━━━━━┓ | ┏━━━━━┓
         ┃┌┐.┌┐┃  ->  ┃...┌┐┃ | ┃.┌┐┌┐┃ | ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃
         ┃└┘.└┘┃  ->  ┃┌┐.└┘┃ | ┃.└┘└┘┃ | ┃└┘.└┘┃ | ┃└┘.└┘┃ | ┃└┘.└┘┃ | ┃└┘.└┘┃
         ┃.....┃  ->  ┃└┘...┃ | ┃.....┃ | ┃.....┃ | ┃..▯..┃ | ┃.....┃ | ┃.....┃
         ┃┌┐.┌┐┃  ->  ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃ | ┃┌┐.┌┐┃
         ┃└┘.└┘┃  ->  ┃└┘.└┘┃ | ┃└┘.└┘┃ | ┃└┘.└┘┃ | ┃└┘.└┘┃ | ┃└┘.└┘┃ | ┃└┘.└┘┃
         ┗━━━━━┛  ->  ┗━━━━━┛ | ┗━━━━━┛ | ┗━━━━━┛ | ┗━━━━━┛ | ┗━━━━━┛ | ┗━━━━━┛
         */
        p = new SlidingBlockPuzzle(
                5, 5,
                new int[] {
                        Block.fromCornerSize(0, 0, 2, 2),
                        Block.fromCornerSize(0, 3, 2, 2),
                        Block.fromCornerSize(3, 0, 2, 2),
                        Block.fromCornerSize(3, 3, 2, 2)
                },
                new int[0]);
        System.out.println(p);
        successors = p.successors();
        successors.forEach(System.out::println);
        assertEquals(8, successors.size());
        System.out.println("----------------------------------------");

        /*
         ┏━━━━━┓
         ┃...┌┐┃
         ┃.┌┐└┘┃
         ┃.└┘..┃
         ┃...┌┐┃
         ┃...└┘┃
         ┗━━━━━┛
         */
        p = new SlidingBlockPuzzle(
                5, 5,
                new int[] {
                        Block.fromCornerSize(3, 0, 2, 2),
                        Block.fromCornerSize(1, 1, 2, 2),
                        Block.fromCornerSize(3, 3, 2, 2)},
                new int[0]);
        System.out.println(p);
        successors = p.successors();
        successors.forEach(System.out::println);
        assertEquals(9, successors.size());
    }

    @Test
    public void testIsWin() throws Exception {
        // board is in win condition
        SlidingBlockPuzzle win = new SlidingBlockPuzzle(3, 3,
                new int[] {Block.fromCornerSize(1, 1, 2, 2), Block.fromCornerSize(0, 0, 1, 1)},
                new int[] {Block.fromCornerSize(1, 1, 2, 2)});
        assertTrue(win.isWin());

        // board is not in win condition
        SlidingBlockPuzzle lose = new SlidingBlockPuzzle(3, 3,
                new int[] {Block.fromCornerSize(1, 1, 2, 2), Block.fromCornerSize(0, 0, 1, 1)},
                new int[] {Block.fromCornerSize(0, 0, 2, 2)});
        assertFalse(lose.isWin());

        // board has other tiles in goal area
        lose = new SlidingBlockPuzzle(3, 3,
                new int[] {Block.fromCornerSize(1, 1, 2, 2), Block.fromCornerSize(0, 0, 1, 1)},
                new int[] {Block.fromCornerSize(0, 0, 2, 2)});
        assertFalse(lose.isWin());
    }

    @Test
    public void testScore() throws Exception {
        // TODO
    }


    @Test
    public void testToString() {
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(6, 6,
                new int[]{
                        Block.fromCornerSize(0, 0, 1, 1),
                        Block.fromCornerSize(1, 0, 2, 2),
                        Block.fromCornerSize(3, 0, 3, 3),
                        Block.fromCornerSize(0, 2, 1, 3),
                        Block.fromCornerSize(0, 5, 4, 1),
                        Block.fromCornerSize(2, 2, 1, 2),
                        Block.fromCornerSize(3, 4, 2, 1)
                },
                new int[0]);
        System.out.println(p);
    }

    @Test
    public void testEquals() {
        assertEquals(new SlidingBlockPuzzle(3, 3, new int[0], new int[0]),
                     new SlidingBlockPuzzle(3, 3, new int[0], new int[0]));

        int bigBlock = Block.fromCornerSize(0, 0, 2, 2);
        int littleBlock = Block.fromCornerSize(1, 2, 1, 1);
        SlidingBlockPuzzle board1 = new SlidingBlockPuzzle(3, 3,
                new int[]{ bigBlock, littleBlock }, new int[0]);
        SlidingBlockPuzzle board2 = board1.successors().get(0);
        assertNotEquals(board1, board2);
        board2 = new SlidingBlockPuzzle(3, 3,
                new int[]{ bigBlock, littleBlock }, new int[0]);
        assertEquals(board1, board2);
    }

    @Test
    public void testMove() {
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(6, 6,
                new int[]{
                        Block.fromCornerSize(0, 0, 1, 1),
                        Block.fromCornerSize(1, 0, 2, 2),
                        Block.fromCornerSize(3, 0, 3, 3),
                        Block.fromCornerSize(0, 2, 1, 3),
                        Block.fromCornerSize(0, 5, 4, 1),
                        Block.fromCornerSize(2, 2, 1, 2),
                        Block.fromCornerSize(3, 4, 2, 1)
                },
                new int[0]);
        System.out.println(p);
        System.out.println(Arrays.toString(p.getBlocks()));
        System.out.println("a");
        SlidingBlockPuzzle p2 = new SlidingBlockPuzzle(p, 0, 1, true);
        System.out.println(p2);
        System.out.println(Arrays.toString(p2.getBlocks()));
        System.out.println(Block.fromCoords(0, 1, 0, 1));
        assertNotEquals(p, p2);
        System.out.println("b");
        p2 = new SlidingBlockPuzzle(p2, 2, -1, true);
        System.out.println(p2);
        System.out.println(Arrays.toString(p2.getBlocks()));
        assertEquals(p, p2);
    }

    @Test
    public void testIsSpaceAbove() {

    }
}