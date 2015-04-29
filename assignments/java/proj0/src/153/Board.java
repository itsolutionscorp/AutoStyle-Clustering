import java.awt.*;

/**
 * The board containing all the pieces for the checkers61b game.
 */
public class Board {

    private Piece[][] board;
    private int selectX = 0;
    private int selectY = 0;
    private boolean hasSelected = false;
    private boolean hasMoved = false;
    private Piece selectedPiece = null;
    private int player = 0;

    public Board(boolean shouldBeEmpty) {
        board = new Piece[8][8];

        if (!shouldBeEmpty) {
            buildBoard();
        }
    }

    private void buildBoard() {
        for (int x = 0; x < 8; x += 2) {
            board[x][0] = new Piece(true, this, x, 0, "pawn");
            board[x + 1][1] = new Piece(true, this, x + 1, 1, "shield");
            board[x][2] = new Piece(true, this, x, 2, "bomb");
            board[x + 1][7] = new Piece(false, this, x + 1, 7, "pawn");
            board[x][6] = new Piece(false, this, x, 6, "shield");
            board[x + 1][5] = new Piece(false, this, x + 1, 5, "bomb");
        }
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    private boolean isEmpty(int x, int y) {
        return inBounds(x, y) && pieceAt(x, y) == null;
    }

    /**
     * Places a piece on the board at a particular location.
     */
    public void place(Piece p, int x, int y) {
        if (!inBounds(x, y)) {
            return;
        }

        if (p != null) {
            board[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (!inBounds(x, y)) {
            System.out.println("Position out of bound: (" + x + ", " + y + ")");
            return null;
        }

        Piece p = pieceAt(x, y);
        if (p == null) {
            System.out.println("Cannot remove piece from empty position.");
            return null;
        }

        board[x][y] = null;
        return p;
    }

    /**
     * Returns the piece at a particular coordinate, or null if there is no
     * piece there or the coordinates are out of bounds.
     */
    public Piece pieceAt(int x, int y) {
        if (!inBounds(x, y)) {
            return null;
        }

        return board[x][y];
    }

    /**
     * Returns if the current player can select a square;
     */
    public boolean canSelect(int x, int y) {
        if (isEmpty(x, y)) {
            return (hasSelected && !hasMoved && validMove(x, y)) ||
                    (hasSelected && selectedPiece.hasCaptured() && validMove(x, y));
        } else if (inBounds(x, y)) {
            return pieceAt(x, y).side() == player && (!hasSelected || !hasMoved);
        }

        return false;
    }

    private boolean validMove(int x, int y) {
        if (!isEmpty(x, y)) {
            return false;
        }

        int dx = selectX - x;
        int dy = selectY - y;
        int side = selectedPiece.side();
        boolean flag = false;
        Piece p = null;

        if (selectedPiece.isFire() || selectedPiece.isKing()) {
            if (dy == -1 && (dx == -1 || dx == 1)) {
                flag = !hasMoved;
            } else {
                if (dy == -2 && dx == 2) {
                    p = pieceAt(x + 1, y - 1);
                }  else if (dy == -2 && dx == -2) {
                    p = pieceAt(x - 1, y - 1);
                }
                flag = p != null && p.side() != side;
            }
        }

        if (!selectedPiece.isFire() || selectedPiece.isKing()) {
            if (dy == 1 && (dx == -1 || dx == 1)) {
                flag = !hasMoved;
            } else {
                if (dy == 2 && dx == 2) {
                    p = pieceAt(x + 1, y + 1);
                }  else if (dy == 2 && dx == -2) {
                    p = pieceAt(x - 1, y + 1);
                }
                flag = flag || (p != null && p.side() != side);
            }
        }

        return flag;
    }

    /**
     * Selects a square on the game board.
     */
    public void select(int x, int y) {
        hasSelected = true;
        selectX = x;
        selectY = y;

        if (isEmpty(x, y)) {
            selectedPiece.move(x, y);
            hasMoved = true;
        } else {
            selectedPiece = pieceAt(x, y);
        }
    }

    /**
     * True if a player has made a move or capture, false otherwise.
     */
    public boolean canEndTurn() {
        return hasMoved;
    }

    /**
     * Called at the end of each turn.
     */
    public void endTurn() {
        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
        }
        hasSelected = false;
        hasMoved = false;
        selectedPiece = null;
        player = 1 - player;
    }

    /**
     * Returns the winner of the game, "Fire" or "Water", "No one" if a tie,
     * null if the game is not over yet.
     * @return
     */
    public String winner() {
        int nFire = 0;
        int nWater = 0;

        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                Piece p = pieceAt(x, y);
                if (p != null) {
                    if (pieceAt(x, y).isFire()) {
                        nFire += 1;
                    } else {
                        nWater += 1;
                    }
                }
            }
        }

        if (nFire == 0) {
            if (nWater == 0) {
                return "No one";
            } else {
                return "Water";
            }
        } else {
            if (nWater == 0) {
                return "Fire";
            } else {
                return null;
            }
        }
    }

    private void render() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                float selectOffset = 0.0f;
                if (x == selectX && y == selectY && hasSelected) {
                    selectOffset = -0.20f;
                }

                if ((x + y) % 2 == 0) {
                    StdDrawPlus.setPenColor(Color.getHSBColor(0, 0.35f + selectOffset, 0.8f));
                } else {
                    StdDrawPlus.setPenColor(Color.getHSBColor(0, 0.75f + selectOffset, 0.8f));
                }

                StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece p = pieceAt(x, y);
                if (p != null) {
                    String img = pieceImage(p);
                    StdDrawPlus.picture(x + 0.5, y + 0.5, img, 1, 1);
                }
            }
        }
    }

    private static String pieceImage(Piece p) {
        StringBuilder sb = new StringBuilder("img/");

        if (p.isBomb()) {
            sb.append("bomb");
        } else if (p.isShield()) {
            sb.append("shield");
        } else {
            sb.append("pawn");
        }

        if (p.isFire()) {
            sb.append("-fire");
        } else {
            sb.append("-water");
        }

        if (p.isKing()) {
            sb.append("-crowned");
        }

        return sb.append(".png").toString();
    }

    /**
     * Starts a GUI version of the game.
     */
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        Board board = new Board(false);

        while (true) {
            board.render();

            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();

                if (board.canSelect(x, y)) {
                    board.select(x, y);
                }
            }

            if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
                board.endTurn();

                String winner = board.winner();
                if (winner != null) {
                    System.out.println(winner);
                }
            }

            if (StdDrawPlus.isNPressed()) {
                board = new Board(false);
            }

            StdDrawPlus.show(25);
        }
    }

}
