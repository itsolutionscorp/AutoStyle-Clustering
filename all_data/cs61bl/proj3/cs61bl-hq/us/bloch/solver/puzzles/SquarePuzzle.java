package us.bloch.solver.puzzles;


import us.bloch.solver.PuzzleState;

import java.util.*;

public class SquarePuzzle implements PuzzleState<SquarePuzzle> {
    private static final int EMPTY_TILE = 0;

    private final int edgeLength;
    private final List<Integer> board;
    private final int emptyCol;
    private final int emptyRow;

    private final SquarePuzzle predecessor;

    private int score;
    private int distScoreWeight = 1;
    private int misplacedScoreWeight = 1;
    private boolean scoreCalculated = false;

    public SquarePuzzle(int edgeLength) {
        predecessor = null;
        this.edgeLength = edgeLength;
        board = new ArrayList<>();
        for (int i = 0; i < edgeLength * edgeLength; i++) {
            board.add(i);
        }
        Collections.shuffle(board);
        int emptyPos = board.indexOf(EMPTY_TILE);
        emptyRow = emptyPos / edgeLength;
        emptyCol = emptyPos % edgeLength;
    }

    public SquarePuzzle(SquarePuzzle toCopy) {
        predecessor = toCopy; // ???
        edgeLength = toCopy.edgeLength;
        board = new ArrayList<>(toCopy.board);
        emptyRow = toCopy.emptyRow;
        emptyCol = toCopy.emptyCol;
    }

    public SquarePuzzle(List<Integer> board) {
        predecessor = null;
        if (Math.floor(Math.sqrt(board.size())) != Math.sqrt(board.size()))
            throw new IllegalArgumentException("Invalid board size");
        Set<Integer> inBoard = new HashSet<>();
        for (Integer i : board) {
            if (i < 0 || i >= board.size())
                throw new IllegalArgumentException("Board tile out of range");
            if (inBoard.contains(i))
                throw new IllegalArgumentException("Board contains repeat tile");
            inBoard.add(i);
        }

        int emptyPos = board.indexOf(0);
        this.board = board;
        edgeLength = (int) Math.sqrt(board.size());
        emptyRow = emptyPos / edgeLength;
        emptyCol = emptyPos % edgeLength;

    }

    private SquarePuzzle(SquarePuzzle startingPoint, int swapRow, int swapCol) {
        predecessor = startingPoint;
        if (Math.abs(swapRow - startingPoint.emptyRow) + Math.abs(swapCol - startingPoint.emptyCol) != 1)
            throw new IllegalArgumentException("Bad swap");
        edgeLength = startingPoint.edgeLength;
        board = new ArrayList<>(startingPoint.board);

        int emptyPos = startingPoint.emptyCol * edgeLength + startingPoint.emptyRow;
        int swapPos = swapCol * edgeLength + swapRow;

        Collections.swap(board, emptyPos, swapPos);
        emptyCol = swapCol;
        emptyRow = swapRow;
    }

    public void setScoreWeights(int distScoreWeight, int misplacedScoreWeight) {
        if (scoreCalculated)
            throw new IllegalStateException("Score has already been calculated");
        this.distScoreWeight = distScoreWeight;
        this.misplacedScoreWeight = misplacedScoreWeight;
    }

    private int calculateTotalScore() {
        int totalScore = 0;
        totalScore += distScoreWeight == 0 ? 0 : calculateDistanceScore() * distScoreWeight;
        totalScore += misplacedScoreWeight == 0 ? 0 : calculateMisplacedTilesScore() * misplacedScoreWeight;
        return totalScore;
    }

    int calculateDistanceScore() {
        int distScore = 0;
        for (int row = 0; row < edgeLength; row++) {
            for (int col = 0; col < edgeLength; col++) {
                int tile = tileAt(row, col);
                int correctRow = tile / edgeLength;
                int correctCol = tile % edgeLength;
                distScore += Math.abs(row - correctRow) + Math.abs(col - correctCol);
            }
        }
        return distScore;
    }

    int calculateMisplacedTilesScore() {
        int mispScore = 0;
        for (int col = 0; col < edgeLength; col++) {
            for (int row = 0; row < edgeLength; row++) {
                int tile = tileAt(row, col);
                if (tile != row + col * edgeLength)
                    mispScore++;
            }
        }
        return mispScore;
    }

    public int tileAt(int row, int col) {
        return board.get(row * edgeLength + col);
    }

    @Override
    public List<SquarePuzzle> successors() {
        List<SquarePuzzle> successors = new ArrayList<>(4);
        if (emptyRow > 0) successors.add(new SquarePuzzle(this, emptyRow - 1, emptyCol));
        if (emptyRow < edgeLength - 1) successors.add(new SquarePuzzle(this, emptyRow + 1, emptyCol));
        if (emptyCol > 0) successors.add(new SquarePuzzle(this, emptyRow, emptyCol - 1));
        if (emptyCol < edgeLength - 1) successors.add(new SquarePuzzle(this, emptyRow, emptyCol + 1));
        return successors;
    }

    @Override
    public SquarePuzzle predecessor() {
        return predecessor;
    }

    @Override
    public boolean isWin() {
        for (int i = 1; i < edgeLength * edgeLength; i++) {
            if (board.get(i) > board.get(i - 1))
                return false;
        }
        return true;
    }

    @Override
    public boolean isWinnable() {
        return true; // Game is always winnable FIXME no it's not
    }

    @Override
    public void initialize() {

    }

    @Override
    public int score() {
        if (!scoreCalculated) {
            score = calculateTotalScore();
            scoreCalculated = true;
        }
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SquarePuzzle that = (SquarePuzzle) o;

        return edgeLength == that.edgeLength && board.equals(that.board);

    }

    @Override
    public int hashCode() {
        return board.hashCode(); //TODO: does it make sense to only use the hash code of the board?
    }

    @Override
    public String toString() {
        int digits = (int) Math.ceil(Math.log10(edgeLength * edgeLength));
        int charsPerLine = (digits + 1) * edgeLength + 1;
        String nf = "%0" + digits + "d";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charsPerLine; i++) {
            builder.append('-');
        }
        builder.append('\n');
        for (int row = 0; row < edgeLength; row++) {
            builder.append("|");
            for (int col = 0; col < edgeLength; col++) {
                if (tileAt(col, row) == EMPTY_TILE) {
                    for (int j = 0; j < digits; j++) {
                        builder.append(' ');
                    }
                } else {
                    String numStr = String.format(nf, tileAt(col, row));
                    builder.append(numStr);
                }
                builder.append("|");
            }
            builder.append('\n');
            for (int i = 0; i < charsPerLine; i++) {
                builder.append('-');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
