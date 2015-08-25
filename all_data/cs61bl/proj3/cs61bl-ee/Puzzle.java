import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

class Puzzle {

    private int height;
    private int width;
    private boolean[][] board;   // Identify whether a cell is occupied or not
    private Set<Block> blocks;

    public Puzzle(int height, int width) {
        this.height = height;
        this.width  = width;
        board  = new boolean[height][width];
        blocks = new HashSet<Block>();
    }

    public Puzzle(Puzzle p) {
        this.height = p.height;
        this.width  = p.width;

        // Deep copy
        this.board = new boolean[height][width];
        for (int i = 0; i < p.board.length; i++) {
            this.board[i] = Arrays.copyOf(p.board[i], width);
        }
        this.blocks = new HashSet<Block>(p.blocks);
    }

    public void addBlock(int topY, int topX, int bottomY, int bottomX) {
        Block b = new Block(topY, topX, bottomY, bottomX);
        blocks.add(b);
        updateBoard();
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public boolean containsBlock(Block b) {
        return blocks.contains(b);
    }

    public void updateBoard() {
        board = new boolean[height][width];
        for (Block b : blocks) {
            for (Cell cell : b.getCells()) {
                board[cell.getY()][cell.getX()] = true;
            }
        }
    }

    public boolean canMoveBlockToward(Block b, Move.Direction d) {
        int topLeftY = b.getTopLeftY();
        int topLeftX = b.getTopLeftX();
        int bottomRightY = b.getBottomRightY();
        int bottomRightX = b.getBottomRightX();
        int w = b.getWidth();
        int h = b.getHeight();

        switch(d) {
            case UP:
                if (topLeftY - 1 < 0) return false;
                for (int x = topLeftX; x < topLeftX + w; x++) {
                    if (board[topLeftY - 1][x]) return false;
                }
                break;
            case DOWN:
                if (bottomRightY + 1 >= height) return false;
                for (int x = topLeftX; x < topLeftX + w; x++) {
                    if (board[bottomRightY + 1][x]) return false;
                }
                break;
            case LEFT:
                if (topLeftX - 1 < 0) return false;
                for (int y = topLeftY; y < topLeftY + h; y++) {
                    if (board[y][topLeftX - 1]) return false;
                }
                break;
            case RIGHT:
                if (bottomRightX + 1 >= width) return false;
                for (int y = topLeftY; y < topLeftY + h; y++) {
                    if (board[y][bottomRightX + 1]) return false;
                }
                break;
        }
        return true;
    }

    public void applyMove(Move m) {
        Block b = m.getBlock();
        Move.Direction d = m.getDirection();

        int topLeftY = b.getTopLeftY();
        int topLeftX = b.getTopLeftX();
        int bottomRightY = b.getBottomRightY();
        int bottomRightX = b.getBottomRightX();
        switch (d) {
            case UP:
                topLeftY -= 1;
                bottomRightY -= 1;
                break;
            case DOWN:
                topLeftY += 1;
                bottomRightY += 1;
                break;
            case LEFT:
                topLeftX -= 1;
                bottomRightX -= 1;
                break;
            case RIGHT:
                topLeftX += 1;
                bottomRightX += 1;
                break;
        }
        blocks.remove(b);
        addBlock(topLeftY, topLeftX, bottomRightY, bottomRightX);
    }

    public List<Move> generateValidMoves() {
        List<Move> validMoves = new LinkedList<Move>();
        for (Block b : blocks) {
            for (Move.Direction d : Move.Direction.values()) {
                if (canMoveBlockToward(b, d)) {
                    validMoves.add(new Move(b, d));
                }
            }
        }
        return validMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Puzzle) {
            Puzzle other = (Puzzle) o;
            if (this.blocks.equals(other.blocks)) return true;
            else return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (Block b : blocks) {
            hash = hash * 3 + b.hashCode();
        }
        return hash % Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (board[h][w]) {
                    sb.append(" * ");
                } else {
                    sb.append("   ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Puzzle p = new Puzzle(5, 4);
        p.addBlock(0, 0, 1, 0);
        p.addBlock(0, 3, 1, 3);
        p.addBlock(2, 0, 3, 0);
        p.addBlock(2, 3, 3, 3);
        p.addBlock(1, 1, 2, 2);
        p.addBlock(3, 1, 3, 2);
        p.addBlock(4, 0, 4, 0);
        p.addBlock(4, 1, 4, 1);
        p.addBlock(4, 2, 4, 2);
        p.addBlock(4, 3, 4, 3);

        System.out.println("Create a puzzle");
        System.out.println("---------------");
        System.out.println(p);
        System.out.println("Puzzle hashCode: " + p.hashCode());
        System.out.println("Test equal to itself: " + p.equals(p));
        System.out.println("Generate Valid Moves:");
        for (Move m : p.generateValidMoves()) {
            System.out.println(" -> " + m);
        }
        System.out.println();
        System.out.println("Apply move to the puzzle");
        Move m = p.generateValidMoves().get(0);
        p.applyMove(m);
        System.out.println(p);
    }
}
