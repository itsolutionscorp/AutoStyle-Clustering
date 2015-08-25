package us.bloch.solver.puzzles;

import us.bloch.solver.PuzzleState;

import java.util.*;

public class SlidingBlockPuzzle implements PuzzleState<SlidingBlockPuzzle> {
    /** The state that came before this one */
    private final SlidingBlockPuzzle predecessor;

    private final int width;
    private final int height;

    private final int[] goal;
    private final int[] blocks;

    // redundant state
    private  int[] board;
    private final int fromBlock;
    private final int toBlock;

    /**
     * An impossible block with its top left corner below and to the right of its bottom left,
     * to be used as a sentinel value for the board array when there is no piece present.
     */
    static final int ABSENT = Block.fromCoords(1, 1, 0, 0);

    /**
     * Creates a new Sliding Block Puzzle board
     * @param width the width of the board
     * @param height the height of the board
     * @param blocks a set containing all of the blocks on the board
     * @param goal a list of the blocks required for the solution
     */
    public SlidingBlockPuzzle(int width, int height, int[] blocks, int[] goal) {
        predecessor = null; // New board
        fromBlock = ABSENT; // ditto
        toBlock = ABSENT; // ditto
        this.width = width;
        this.height = height;
        this.blocks = Arrays.stream(blocks).map((i) -> i + Integer.MIN_VALUE).sorted().map((i) -> i + Integer.MIN_VALUE).toArray(); // see Integer.compareUnsigned
        this.goal = Arrays.copyOf(goal, goal.length);
        board = new int[width * height];
        for (int i = 0; i < width * height; i++)
            board[i] = ABSENT;
        for (int block : blocks) {
            for (int x = 0; x < Block.getWidth(block); x++)
                for (int y = 0; y < Block.getHeight(block); y++)
                    setTile(Block.getLeftX(block) + x, Block.getTopY(block) + y, block);
        }
    }

    /**
     * Makes a copy of a different board with one block moved.
     * @implNote all blocks that are not moved are the same instances from prev.
     * @param prev the board to start from
     * @param blockMoveIndex the index of the block to move
     * @param moveDist how far to move the block
     * @param vertical whether to move the block vertically. If this is false, the block will be moved horizontally.
     */
    SlidingBlockPuzzle(SlidingBlockPuzzle prev, int blockMoveIndex, int moveDist, boolean vertical) {
        predecessor = prev;
        width = prev.width;
        height = prev.height;
        goal = prev.goal;

        // update blocks
        blocks = Arrays.copyOf(prev.blocks, prev.blocks.length);
        fromBlock = prev.blocks[blockMoveIndex];
        toBlock = vertical ? Block.moveVertical(fromBlock, moveDist) : Block.moveHorizontal(fromBlock, moveDist);
        blocks[blockMoveIndex] = toBlock;
        // Restore order in blocks if required
        if (vertical) {
            if (Integer.compareUnsigned(toBlock, fromBlock) < 0) {
                for (int i = blockMoveIndex - 1; i >= 0 && Integer.compareUnsigned(toBlock, blocks[i]) < 0; i--) {
                    int tmp = blocks[i + 1];
                    blocks[i + 1] = blocks[i];
                    blocks[i] = tmp;
                }
            } else {
                for (int i = blockMoveIndex + 1; (i < (blocks.length - 1)) && (Integer.compareUnsigned(toBlock, blocks[i]) > 0); i++) {
                    int tmp = blocks[i - 1];
                    blocks[i - 1] = blocks[i];
                    blocks[i] = tmp;

                }
            }
        }
    }

    public void initialize() {
        board = Arrays.copyOf(predecessor.board, predecessor.board.length);
        for (int y = Block.getTopY(fromBlock); y <= Block.getBottomY(fromBlock); y++) {
            for (int x = Block.getLeftX(fromBlock); x <= Block.getRightX(fromBlock); x++) {
                setTile(x, y, ABSENT);
            }
        }
        for (int y = Block.getTopY(toBlock); y <= Block.getBottomY(toBlock); y++) {
            for (int x = Block.getLeftX(toBlock); x <= Block.getRightX(toBlock); x++) {
                setTile(x, y, toBlock);
            }
        }
    }

    private void setTile(int x, int y, int block) {
        board[x + y * width] = block;
    }

    /**
     * Gets the block at the given location on the board
     * @param x the x coordinate of the location to check
     * @param y the y coordinate of the location to check
     * @return The block at (x, y) on the board
     */
    public int getBlockAt(int x, int y) {
        return board[x + y * width];
    }

    @Override
    public List<SlidingBlockPuzzle> successors() {
        List<SlidingBlockPuzzle> result = new ArrayList<>();
        for (int i = 0; i < blocks.length; i++) {
            result.addAll(movesForBlock(i));
        }
        return result;
    }

    @Override
    public SlidingBlockPuzzle predecessor() {
        return predecessor;
    }

    /**
     * Makes a list of possible moves for the given block
     * @apiNote for now this only considers single-space moves
     * @param blockIdx the index of the block to list moves for
     * @return a list of moves for the given block
     */
    private List<SlidingBlockPuzzle> movesForBlock(int blockIdx) {
        List<SlidingBlockPuzzle> result = new ArrayList<>();
        int block = blocks[blockIdx];
        // check up
        for (int i = 1, spaceAbove = spaceAbove(block); i <= spaceAbove; i++)
            result.add(new SlidingBlockPuzzle(this, blockIdx, -i, true));

        // check down
        for (int i = 1, spaceBelow = spaceBelow(block); i <= spaceBelow; i++)
            result.add(new SlidingBlockPuzzle(this, blockIdx, i, true));
        // check left
        for (int i = 1, spaceToLeft = spaceToLeft(block); i <= spaceToLeft; i++)
            result.add(new SlidingBlockPuzzle(this, blockIdx, -i, false));

        // check right
        for (int i = 1, spaceToRight = spaceToRight(block); i <= spaceToRight; i++)
            result.add(new SlidingBlockPuzzle(this, blockIdx, i, false));
        return result;
    }

    public int spaceAbove(int block) {
        int result = 0;
        for (int y = Block.getTopY(block) - 1; y >= 0; y--) {
            for (int x = Block.getLeftX(block), right = Block.getRightX(block); x <= right; x++) {
                if (getBlockAt(x, y) != ABSENT) {
                    return result;
                }
            }
            result++;
        }
        return result;
    }

    public int spaceBelow(int block) {
        int result = 0;
        for (int y = Block.getBottomY(block) + 1; y < height; y++) {
            for (int x = Block.getLeftX(block), right = Block.getRightX(block); x <= right; x++) {
                if (getBlockAt(x, y) != ABSENT)
                    return result;
            }
            result++;
        }
        return result;
    }

    public int spaceToLeft(int block) {
        int result = 0;
        for (int x = Block.getLeftX(block) - 1; x >= 0; x--) {
            for (int y = Block.getTopY(block), bottom = Block.getBottomY(block); y <= bottom; y++) {
                if (getBlockAt(x, y) != ABSENT)
                    return result;
            }
            result++;
        }
        return result;
    }

    public int spaceToRight(int block) {
        int result = 0;
        for (int x = Block.getRightX(block) + 1; x < width; x++) {
            for (int y = Block.getTopY(block), bottom = Block.getBottomY(block); y <= bottom; y++) {
                if (getBlockAt(x, y) != ABSENT)
                    return result;
            }
            result++;
        }
        return result;
    }

    @Override
    public boolean isWin() {
        for (int target : goal)
            if (target != getBlockAt(Block.getLeftX(target), Block.getTopY(target)))
                return false;
        return true;
    }

    @Override
    public boolean isWinnable() {
        // If not all goal blocks are present, the puzzle is unsolvable
        for (int goalBlock : goal) {
            if (Arrays.stream(blocks).noneMatch(
                    block -> Block.getWidth(goalBlock) == Block.getWidth(block) &&
                             Block.getHeight(goalBlock) == Block.getHeight(block)))
                return false;
        }
        return true;
    }

    private int score = 0;
    private boolean scored = false;

    @Override
    public int score() {
        if (!scored) {
            score = SlidingBlockPuzzleHeuristic.evaluate(this);
            scored = true;
        }
        return score;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    int[] getBlocks() {
        return blocks;
    }

    public int[] getGoal() { //HACK
        return goal;
    }


    public int getFromBlock() {
        return fromBlock;
    }

    public int getToBlock() {
        return toBlock;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return  /* o instanceof SlidingBlockPuzzle && */ Arrays.equals(blocks, ((SlidingBlockPuzzle) o).blocks);
    }

    @Override
    public int hashCode() {
        // this assumes that boards will only be compared to other boards of the same size, with the same goal
        // which is true for the purposes of this project
        return Arrays.hashCode(blocks);
//        int result = width;
//        result = 31 * result + height;
//        result = 31 * result + Arrays.hashCode(blocks)
//        result = 31 * result + goal.hashCode();
//        return result;
    }

    @Override
    public String toString() {
        int length = (width + 3) * (height + 2);
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            //start by filling the whole buffer with dots
            builder.append('.');
        }

        for (int i = 0; i < height + 2; i++) {
            // make newline chars every width+3 to make a (width + 2) * (height + 2) box of spaces
            builder.setCharAt((width + 3) * i + width + 2, '\n');
        }

        int strRowWidth = width + 3;

        for (int i = 1; i < width + 1; i++) {
            builder.setCharAt(i, '━'); // Put '━' across the top
            builder.setCharAt((height + 1) * strRowWidth + i, '━'); // Put '━' across the bottom
        }
        for (int i = 1; i < height + 1; i++) {
            builder.setCharAt(i * strRowWidth, '┃'); // Put '┃' at the beginning of each line
            builder.setCharAt((i + 1) * strRowWidth - 2, '┃'); // Put '┃' at the end of each line
        }

        // Add corners
        builder.setCharAt(0, '┏');
        builder.setCharAt(width + 1, '┓');
        builder.setCharAt(strRowWidth * (height + 1), '┗');
        builder.setCharAt(strRowWidth * (height + 1) + width + 1, '┛');

        for (int block : blocks) {
            if (Block.getWidth(block) == 1 && Block.getHeight(block) == 1) {
                // small block
                builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + Block.getLeftX(block) + 1, '▯');
            } else if (Block.getWidth(block) == 1) {
                // tall block
                // ends
                builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + Block.getLeftX(block) + 1, '╥');
                builder.setCharAt((Block.getBottomY(block) + 1) * strRowWidth + Block.getLeftX(block) + 1, '╨');

                // middle
                for (int y = Block.getTopY(block) + 2; y < Block.getBottomY(block) + 1; y++)
                    builder.setCharAt(y * strRowWidth + Block.getLeftX(block) + 1, '║');
            } else if (Block.getHeight(block) == 1) {
                // wide block
                // ends
                builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + Block.getLeftX(block) + 1, '╞');
                builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + Block.getRightX(block) + 1, '╡');

                // middle
                for (int x = Block.getLeftX(block) + 2; x < Block.getRightX(block) + 1; x++)
                    builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + x, '═');
            } else {
                // big block
                // Corners
                builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + Block.getLeftX(block) + 1, '┌');
                builder.setCharAt((Block.getBottomY(block) + 1) * strRowWidth + Block.getLeftX(block) + 1, '└');
                builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + Block.getRightX(block) + 1, '┐');
                builder.setCharAt((Block.getBottomY(block) + 1) * strRowWidth + Block.getRightX(block) + 1, '┘');

                // edges
                for (int x = Block.getLeftX(block) + 2; x < Block.getRightX(block) + 1; x++) {
                    builder.setCharAt((Block.getTopY(block) + 1) * strRowWidth + x, '─');
                    builder.setCharAt((Block.getBottomY(block) + 1) * strRowWidth + x, '─');
                }
                for (int y = Block.getTopY(block) + 2; y < Block.getBottomY(block) + 1; y++) {
                    builder.setCharAt(y * strRowWidth + Block.getLeftX(block) + 1, '│');
                    builder.setCharAt(y * strRowWidth + Block.getRightX(block) + 1, '│');
                }

                // center
                for (int x = Block.getLeftX(block) + 2; x < Block.getRightX(block) + 1; x++) {
                    for (int y = Block.getTopY(block) + 2; y < Block.getBottomY(block) + 1; y++) {
                        builder.setCharAt(y * strRowWidth + x, ' ');
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * A single int stores the entire state of a block:
     * byte 0: top Y
     * byte 1: left X
     * byte 2: bottom Y
     * byte 3: right X
     *
     * This class contains methods to work with blocks.
     */
    public static class Block {
        public static int getTopY(int description) {
            return description >>> 24;
        }

        public static int getLeftX(int description) {
            return (description >>> 16) & 255;
        }

        public static int getBottomY(int description) {
            return (description >>> 8) & 255;
        }

        public static int getRightX(int description) {
            return description & 255;
        }

        public static int getWidth(int description) {
            return ((description) - (description >>> 16) + 1) & 255;
        }

        public static int getHeight(int description) {
            return ((description >>> 8) - (description >>> 24) + 1) & 255;
        }


        public static int moveVertical(int block, int distance) {
            return block + distance * ((1 << 24) + (1 << 8));
        }

        public static int moveHorizontal(int block, int distance) {
            return block + distance * ((1 << 16) + 1);
        }

        public static int fromCoords(int leftX, int topY, int rightX, int bottomY) {
            assert leftX >= 0 && leftX < 256;
            assert topY >= 0 && topY < 256;
            assert rightX >= 0 && rightX < 256;
            assert bottomY >= 0 && bottomY < 256;
            return (topY << 24) + (leftX << 16) + (bottomY << 8) + rightX;
        }

        public static int fromCornerSize(int leftX, int topY, int width, int height) {
            assert leftX >= 0 && leftX < 256;
            assert topY >= 0 && topY < 256;
            assert width > 0 && width <= 256;
            assert height > 0 && height <= 256;
            return (topY << 24) + (leftX << 16) + ((topY + height - 1) << 8) +  (leftX + width - 1);
        }
    }
}
