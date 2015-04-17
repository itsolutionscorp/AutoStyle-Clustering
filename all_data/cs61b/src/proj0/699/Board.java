import java.awt.Color;
/**
 * @author Steven Wang
 * Acknowledgements:
 * Worked with Chris Lin and Qiqi Huang
 */
public class Board {

    private static final int N = 8;
    private static final Color WALKABLE_COLOR =  Color.GRAY;
    private static final Color UNWALKABLE_COLOR = Color.RED;
    private static final Color FIRE_SELECT_COLOR = new Color(243, 134, 48);
    private static final Color WATER_SELECT_COLOR = new Color(105, 210, 231);
    private static final String IMG_C_FIRE_BOMB = "img/bomb-fire-crowned.png";
    private static final String IMG_FIRE_BOMB = "img/bomb-fire.png";
    private static final String IMG_C_WATER_BOMB = "img/bomb-water-crowned.png";
    private static final String IMG_WATER_BOMB = "img/bomb-water.png";
    private static final String IMG_C_FIRE_SHIELD = "img/shield-fire-crowned.png";
    private static final String IMG_FIRE_SHIELD = "img/shield-fire.png";
    private static final String IMG_C_WATER_SHIELD = "img/shield-water-crowned.png";
    private static final String IMG_WATER_SHIELD = "img/shield-water.png";
    private static final String IMG_C_FIRE_PAWN = "img/pawn-fire-crowned.png";
    private static final String IMG_FIRE_PAWN = "img/pawn-fire.png";
    private static final String IMG_C_WATER_PAWN = "img/pawn-water-crowned.png";
    private static final String IMG_WATER_PAWN = "img/pawn-water.png";

    private Piece[] pieces;
    private int prevX, prevY;

    /***Static methods and helpers ****/
    public static void main(String args[]) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        boolean done = false;
        while(!done) {
            double mouseX = StdDrawPlus.mouseX();
            double mouseY = StdDrawPlus.mouseY();
            int x = (int) mouseX;
            int y = (int) mouseY;
            if (StdDrawPlus.mousePressed()) {
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                    done = b.winner() != null;
                } else {
                    //System.out.println("invalid move, n00b");
                }
            } else if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }


            draw(b);
            StdDrawPlus.show(20);
        }
    }

    private static void draw(Board b) {
        Color selectColor = b.isFireTurn ? FIRE_SELECT_COLOR : WATER_SELECT_COLOR;
        Color selectableColor = selectColor.darker();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //selected square
                if (b.pieceActive && i == b.prevX && j == b.prevY) {
                    StdDrawPlus.setPenColor(selectColor);
                    StdDrawPlus.filledSquare(b.prevX + 0.5, b.prevY + 0.5, 0.5);
                }
                //selectable squares
                else if (b.canSelect(i, j)) StdDrawPlus.setPenColor(selectableColor);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(WALKABLE_COLOR);
                else                  StdDrawPlus.setPenColor(UNWALKABLE_COLOR);

                //fill square
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);

                //blit piece image
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = b.pieceAt(i, j);
                if (p != null) {
                String imgPath = getImagePath(p);
                StdDrawPlus.picture(i + 0.5, j + 0.5, imgPath, 1, 1);
                }
            }
        }
    }

    private static String getImagePath(Piece p) {
        boolean crowned = p.isKing();
        boolean fire = p.isFire();
        if (p.isBomb()) {
            if (fire) {
                return crowned ? IMG_C_FIRE_BOMB : IMG_FIRE_BOMB;
            } else {
                return crowned ? IMG_C_WATER_BOMB : IMG_WATER_BOMB;
            }
        } else if (p.isShield()) {
            if (fire) {
                return crowned ? IMG_C_FIRE_SHIELD : IMG_FIRE_SHIELD;
            } else {
                return crowned ? IMG_C_WATER_SHIELD : IMG_WATER_SHIELD;
            }
        } else { //pawn case
            if (fire) {
                return crowned ? IMG_C_FIRE_PAWN : IMG_FIRE_PAWN;
            } else {
                return crowned ? IMG_C_WATER_PAWN : IMG_WATER_PAWN;
            }
        }
    }

    /****** Constructor and instance methods *******/
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N*N];
        if (!shouldBeEmpty) {
            initRow(0, true, "pawn");
            initRow(1, true, "shield");
            initRow(2, true, "bomb");
            initRow(N - 1, false, "pawn");
            initRow(N - 2, false, "shield");
            initRow(N - 3, false, "bomb");
        }
    }

    private void initRow(int row, boolean isFirePiece, String type) {
        for (int i = 0; i < N; i++) {
            if (validSpace(row, i)) {
                Piece p = new Piece(isFirePiece, this, i, row, type);
                place(p, i, row);
            }
        }
    }

    private boolean validSpace(int x, int y) {
        return inBounds(x, y) && (x + y) % 2 == 0;
    }


    private boolean inBounds(int x, int y) {
        boolean overBound = x >= N || y >= N;
        boolean underBound = x < 0 || y < 0;
        return !overBound && !underBound;
    }

    private int getIndex(int x, int y) {
        return x + N*y;
    }

    public Piece pieceAt(int x, int y) {
        if (!inBounds(x, y)) {
            return null;
        } else {
            return pieces[getIndex(x, y)];
        }
    }

    public void place(Piece p, int x, int y) {
        if (!inBounds(x, y) || p == null) {
            return;
        }
        //Check if Piece has already been placed
        COLLARD_GREENS:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceAt(i, j) == p) {
                    remove(i, j);
                    break COLLARD_GREENS;
                }
            }
        }

        pieces[getIndex(x, y)] = p;
    }

    /**
     * Returns true if p is currently placed on the Board
     */
    private boolean placed(Piece p) {
        if (p == null) {
            throw new RuntimeException("p cannot be null!");
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceAt(i, j) == p) {
                    return true;
                }
            }
        }
        return false;
    }

    public Piece remove(int x, int y) {
        if (!inBounds(x, y)) {
            return null;
        }
        Piece p = pieceAt(x, y);
        if (p == null) {
            System.out.printf("board.remove(%d, %d) targeted a position with "
                    + "no Piece!%n", x, y);
            //throw new RuntimeException();
        } else {
            pieces[getIndex(x, y)] = null;
        }
        return p;
    }

    private boolean isValidMove(int xi, int yi, int xf, int yf) {
        if (!inBounds(xi, yi) || !inBounds(xf, yf)) {
            return false;
        }
        Piece p = pieceAt(xi, yi);
        if (p == null) {
            return false;
        }
        if (pieceAt(xf, yf) != null) {
            return false;
        }
        int dx = Math.abs(xf - xi);
        int dy = Math.abs(yf - yi);
        if (Math.max(dx, dy) > 2 || dx != dy)
            return false;
        if (!p.isKing()) {
            if (yf - yi < 0 && isFireTurn) {
                return false;
            } 
            if (yf - yi > 0 && !isFireTurn) {
                return false;
            }
        }
        if (dx == 2) {
            //capturing move
            int captureX = (xf + xi) / 2;
            int captureY = (yf + yi) / 2;
            Piece q = pieceAt(captureX, captureY);
            return q != null && isFireTurn != q.isFire();
        } else { //dx == 1 
            return pieceAt(xf, yf) == null;
        }
    }

    private boolean isValidCapture(int xi, int yi, int xf, int yf) {
        int dx = Math.abs(xf - xi);
        int dy = Math.abs(yf - yi);
        return dx == dy && dx == 2 && isValidMove(xi, yi, xf, yf);
    }

    private boolean isFireTurn = true;
    private boolean pieceActive = false;
    private boolean pieceMoved = false;
    private boolean pieceCaptured = false;
    private boolean bombExploded = false;
    private Piece elCaptor = null;

    public void select(int x, int y) {
        Piece selectedPiece = pieceAt(x, y);
        //Three cases is valid selection:
        //1. Move selected piece to empty space.
        //2. Select a piece
        if (selectedPiece == null) {
            Piece p = pieceAt(prevX, prevY);
            p.move(x, y);
            pieceMoved = true;
            //Capture case
            if (p.hasCaptured() && !pieceCaptured) {
                //TODO: special bomb case, remove selection focus
                elCaptor = p;
                if (p.isBomb()) {
                    bombExploded = true;
                }
                pieceCaptured = true;
            }
        } else {

        }
        prevX = x;
        prevY = y;
        pieceActive = true;
    }

    public boolean canSelect(int x, int y) {
        if (bombExploded) {
            return false;
        }
        Piece p = pieceAt(x, y);
        if (p != null && !pieceMoved) {
            return isFireTurn == p.isFire();
        } else if (pieceActive) {
            if (pieceMoved && !pieceCaptured) {
                return false;
            } else if (pieceMoved && pieceCaptured) {
                return isValidCapture(prevX, prevY, x, y);
            } else {
                return isValidMove(prevX, prevY, x, y);
            }
        } else {
            return false;
        }
    }

    public void endTurn() {
        if (pieceCaptured) {
            elCaptor.doneCapturing();
            elCaptor = null;
        }
        isFireTurn = !isFireTurn;
        pieceActive = false;
        pieceMoved = false;
        pieceCaptured = false;
        bombExploded = false;
    }
        
    public boolean canEndTurn() {
        return pieceMoved;
    }

    public String winner() {
        boolean existsFire = false;
        boolean existsWater = false;
        BOK_CHOY:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece p = pieceAt(i, j);
                if (p != null) {
                    if (p.isFire()) {
                        existsFire = true;
                    } else {
                        existsWater = true;
                    }
                    if (existsFire && existsWater) {
                        break BOK_CHOY;
                    }
                }
            }

        }

        if (existsFire && !existsWater) {
            return "Fire";
        } else if (!existsFire && existsWater) {
            return "Water";
        } else if (existsFire && existsWater) {
            return null;
        } else {
            return "No one";
        }
    }


}
