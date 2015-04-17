import java.awt.event.KeyEvent;

/**
 * @author Patrick Lorio
 */
public class Board {
    private final Piece[][] pieces;

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        if (shouldBeEmpty) return;

        // initialize board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (!isTilePosition(x, y)) continue;
                String type = getInitialRowType(y);
                if (type == null) continue;
                new Piece(y < 4, this, x, y, type);
            }
        }
    }

    private String getInitialRowType(int y) {
        switch (y) {
            case 0:
            case 7:
                return "pawn";
            case 1:
            case 6:
                return "shield";
            case 2:
            case 5:
                return "bomb";
            default:
                return null;
        }
    }

    private boolean isTilePosition(int x, int y) {
        return (x + y) % 2 == 0;
    }

    private boolean firePlaying = true;
    private boolean moved;
    private Piece selected;

    private int selectedX;
    private int selectedY;

    public Piece pieceAt(int x, int y) {
        if (!inBounds(x, y)) return null;
        return pieces[x][y];
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < 8 && y < 8 && y >= 0;
    }

    public boolean canSelect(int x, int y) {
        if (!inBounds(x, y)) return false;

        // can we move our selected piece?
        if (selected != null) {
            if (moved) {
                // can we do a multi-capture?
                return selected.hasCaptured() && isValidCapture(selected, selectedX, selectedY, x, y);
            }

            Piece piece = pieceAt(x, y);
            if (piece != null && piece.isFire() == firePlaying) return true; // change selection

            // can we do anything?
            return isValidMove(selected, selectedX, selectedY, x, y) ||
                    isValidCapture(selected, selectedX, selectedY, x, y);
        }

        // is there a piece we can select?
        Piece piece = pieceAt(x, y);
        return piece != null
                && piece.isFire() == firePlaying;
    }

    private boolean isValidMove(Piece piece, int x, int y, int tx, int ty) {
        return isValidDirection(piece, y, ty) &&
                Math.abs(tx - x) == Math.abs(ty - y) // must move diagonally
                && Math.abs(tx - x) == 1 // can only move one step
                && pieceAt(tx, ty) == null; // final location must be empty
    }

    private boolean isValidCapture(Piece piece, int x, int y, int tx, int ty) {
        if (!isValidDirection(piece, y, ty)) return false;
        if (Math.abs(tx - x) != Math.abs(ty - y)) return false; // must move diagonally
        if (Math.abs(tx - x) != 2) return false; // must be a jump
        if (pieceAt(tx, ty) != null) return false; // final location must be empty

        int mx = x + (tx - x) / 2;
        int my = y + (ty - y) / 2;

        Piece obstacle = pieceAt(mx, my);
        return obstacle != null // we must have obstacle to jump over
                && obstacle.isFire() == !piece.isFire(); // obstacle must be other team
    }

    private boolean isValidDirection(Piece piece, int y, int ty) {
        return piece.isKing() // king does whatever
                || piece.isFire() && ty > y // fire goes up
                || !piece.isFire() && ty < y; // not fire goes down
    }

    public void select(int x, int y) {
        selectedX = x;
        selectedY = y;

        if (selected == null) {
            selected = pieceAt(x, y);
        } else {
            Piece piece = pieceAt(x, y);
            if (piece != null) {
                selected = piece; // change selection
            } else {
                moved = true;
                selected.move(x, y);
            }
        }
    }

    public void place(Piece piece, int x, int y) {
        pieces[x][y] = piece;
    }

    public Piece remove(int x, int y) {
        if (!inBounds(x, y)) {
            System.err.println("Out of bounds");
            return null;
        }

        Piece piece = pieces[x][y];
        if (piece == null) {
            System.err.println("Cannot remove pieces that doesn't exist");
            return null;
        }

        pieces[x][y] = null;
        return piece;
    }

    public boolean canEndTurn() {
        return moved;
    }

    public void endTurn() {
        firePlaying = !firePlaying;
        moved = false;
        if (selected != null) selected.doneCapturing();
        selected = null;
    }

    public String winner() {
        int fire = 0;
        int water = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = pieceAt(x, y);
                if (piece == null) continue;

                if (piece.isFire()) {
                    ++fire;
                } else {
                    ++water;
                }

                if (fire > 0 && water > 0) {
                    return null;
                }
            }
        }

        if (fire == 0 && water == 0) return "No one";
        return fire > 0 ? "Fire" : "Water";
    }

    private void render() {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (selected != null && x == selectedX && y == selectedY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                } else if (isTilePosition(x, y)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }

                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[x][y] != null) {
                    StdDrawPlus.picture(x + .5, y + .5, getFilename(pieces[x][y]), 1, 1);
                }
            }
        }
    }

    private String update() {
        if (StdDrawPlus.mousePressed()) {
            int x = (int) StdDrawPlus.mouseX();
            int y = (int) StdDrawPlus.mouseY();

            if (canSelect(x, y)) {
                select(x, y);
            }

            String winner = winner();
            if (winner != null) {
                return winner;
            }
        }

        if (StdDrawPlus.isKeyPressed(KeyEvent.VK_SPACE)) {
            if (canEndTurn()) {
                endTurn();
                render();
            }
        }

        return null;
    }

    private String getFilename(Piece piece) {
        String type = "pawn";
        if (piece.isBomb()) type = "bomb";
        else if (piece.isShield()) type = "shield";

        return "img/" + type + "-" + (piece.isFire() ? "fire" : "water") +
                (piece.isKing() ? "-crowned" : "") + ".png";
    }


    public static void main(String[] args) {
        Board board = new Board(false);

        String winner;
        while (true) {
            winner = board.update();
            board.render();
            StdDrawPlus.show(10);

            if (winner != null) {
                break;
            }
        }

        System.exit(0);
    }
}
