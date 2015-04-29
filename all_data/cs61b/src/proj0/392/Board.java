/** 
 *  @author Ray Chen
 */
import java.awt.Color;
import java.lang.Math;

public class Board {
    /** Location of pieces. */
    private static Board b;
    private Piece[][] pieces;
    private final Color DARK = new Color(100, 100, 100);
    private final Color LIGHT = new Color(200, 200, 255);
    private int selectX = -1;
    private int selectY = -1;
    private boolean firePlayer = true;
    private boolean pieceMoved;
    private static final int N = 8;

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];
        if (!shouldBeEmpty) {
            initBoard();
            // initJumps();
        }
    }

    private void initBoard() {
        // Fire side
        place(new Piece(true, this, 0, 0, "pawn"), 0, 0);
        place(new Piece(true, this, 2, 0, "pawn"), 2, 0);
        place(new Piece(true, this, 4, 0, "pawn"), 4, 0);
        place(new Piece(true, this, 6, 0, "pawn"), 6, 0);
        place(new Piece(true, this, 1, 1, "shield"), 1, 1);
        place(new Piece(true, this, 3, 1, "shield"), 3, 1);
        place(new Piece(true, this, 5, 1, "shield"), 5, 1);
        place(new Piece(true, this, 7, 1, "shield"), 7, 1);
        place(new Piece(true, this, 0, 2, "bomb"), 0, 2);
        place(new Piece(true, this, 2, 2, "bomb"), 2, 2);
        place(new Piece(true, this, 4, 2, "bomb"), 4, 2);
        place(new Piece(true, this, 6, 2, "bomb"), 6, 2);

        // Water side
        place(new Piece(false, this, 1, 7, "pawn"), 1, 7);
        place(new Piece(false, this, 3, 7, "pawn"), 3, 7);
        place(new Piece(false, this, 5, 7, "pawn"), 5, 7);
        place(new Piece(false, this, 7, 7, "pawn"), 7, 7);
        place(new Piece(false, this, 0, 6, "shield"), 0, 6);
        place(new Piece(false, this, 2, 6, "shield"), 2, 6);
        place(new Piece(false, this, 4, 6, "shield"), 4, 6);
        place(new Piece(false, this, 6, 6, "shield"), 6, 6);
        place(new Piece(false, this, 1, 5, "bomb"), 1, 5);
        place(new Piece(false, this, 3, 5, "bomb"), 3, 5);
        place(new Piece(false, this, 5, 5, "bomb"), 5, 5);
        place(new Piece(false, this, 7, 5, "bomb"), 7, 5);
    }

    private void initJumps() {
        // Fire side
        place(new Piece(true, this, 4, 1, "shield"), 4, 1);

        // Water side
        place(new Piece(false, this, 3, 2, "bomb"), 3, 2);
        place(new Piece(false, this, 3, 4, "bomb"), 3, 4);
        place(new Piece(false, this, 3, 6, "bomb"), 3, 6);
        place(new Piece(false, this, 1, 2, "bomb"), 1, 2);
        place(new Piece(false, this, 1, 4, "bomb"), 1, 4);
        place(new Piece(false, this, 1, 6, "bomb"), 1, 6);
    }

    /** Draws an N x N board. */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(DARK);
                else                  StdDrawPlus.setPenColor(LIGHT);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(Color.WHITE);
                if ((i == b.selectX) && (j == b.selectY))
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (b.pieceAt(i, j) != null) {
                    b.drawPiece(b.pieceAt(i, j), i, j);
                }
            }
        }
    }

    private void drawPiece(Piece p, int i, int j) {
        String imgpath = null;
        if (p.isFire()){
            if (p.isKing()) {
                if (p.isShield()) {
                    imgpath = "img/shield-fire-crowned.png";
                }
                else if (p.isBomb()) {
                    imgpath = "img/bomb-fire-crowned.png";
                }
                else {
                    imgpath = "img/pawn-fire-crowned.png";
                }
            }
            else {
                if (p.isShield()) {
                    imgpath = "img/shield-fire.png";
                }
                else if (p.isBomb()) {
                    imgpath = "img/bomb-fire.png";
                }
                else {
                    imgpath = "img/pawn-fire.png";
                }
            }
        }
        else {
            if (p.isKing()) {
                if (p.isShield()) {
                    imgpath = "img/shield-water-crowned.png";
                }
                else if (p.isBomb()) {
                    imgpath = "img/bomb-water-crowned.png";
                }
                else {
                    imgpath = "img/pawn-water-crowned.png";
                }
            }
            else {
                if (p.isShield()) {
                    imgpath = "img/shield-water.png";
                }
                else if (p.isBomb()) {
                    imgpath = "img/bomb-water.png";
                }
                else {
                    imgpath = "img/pawn-water.png";
                }
            }
        }
        StdDrawPlus.picture(i + .5, j + .5, imgpath, 1, 1);
    }

    public Piece pieceAt(int x, int y){
        try {
            return pieces[x][y];
        }
        catch(Exception e) {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(selectX, selectY);
        Piece p2 = pieceAt(x, y);

        if (pieceMoved) {
            // After capturing with a bomb
            if (p == null) {
                return false;
            }
            //shield and pawn pieces doing chains
            else {
                if (p.hasCaptured()) {
                    if (p.isKing()) {
                        return checkHopFire(p, x, y, selectX, selectY) || checkHopWater(p, x, y, selectX, selectY);
                    }
                    else if (p.isFire()) {
                        return checkHopFire(p, x, y, selectX, selectY);
                    }
                    else {
                        return checkHopWater(p, x, y, selectX, selectY);
                    }
                }
                else {
                    return false;
                }
            }        
        }
        else {
            // First Selection
            if (p == null) {
                return p2 != null && p2.isFire() == firePlayer;
            }
            else if (!p.hasCaptured()) {
                // Making a move to empty space
                if (p2 == null) {
                    return validMove(p, x, y, selectX, selectY);
                }
                // Switching pieces
                else {
                    return p2.isFire() == firePlayer;
                }
            }
            else {
                return false;
            }
        }
    }

    private boolean checkHopFire(Piece p, int x1, int y1, int x2, int y2) {
        Piece p2;
        boolean target = false;
        boolean openSpot = false;
        if (x1 - x2 == 2 && y1 - y2 == 2) {
            p2 = pieceAt(x2 + 1, y2 + 1);
            target = p2 != null && p2.isFire() != p.isFire();
            openSpot = pieceAt(x2 + 2, y2 + 2) == null;
        } 
        else if (x1 - x2 == -2 && y1 - y2 == 2) {
            p2 = pieceAt(x2 - 1, y2 + 1);
            target = p2 != null  && p2.isFire() != p.isFire();
            openSpot = pieceAt(x2 - 2, y2 + 2) == null;
        }
        return target && openSpot;

    }

    private boolean checkHopWater(Piece p, int x1, int y1, int x2, int y2) {
        Piece p2;
        boolean target = false;
        boolean openSpot = false;;
        if (x1 - x2 == 2 && y1 - y2 == -2) {
            p2 = pieceAt(x2 + 1, y2 - 1); 
            target = p2 != null && p2.isFire() != p.isFire();
            openSpot = pieceAt(x2 + 2, y2 - 2) == null;
        }
        else if (x1 - x2 == -2 && y1 - y2 == -2) {
            p2 = pieceAt(x2 - 1, y2 - 1);
            target = p2 != null && p2.isFire() != p.isFire();
            openSpot = pieceAt(x2 - 2, y2 - 2) == null;
        }
        return target && openSpot;

    }

    private boolean checkFire(Piece p, int x1, int y1, int x2, int y2) {
        boolean hop = checkHopFire(p, x1, y1, x2, y2);
        if (hop) {
            return true;
        }
        else {
            if (x1 - x2 == 1 && y1 - y2 == 1) {
                return pieceAt(x2 + 1, y2 + 1) == null;
            }
            else if (x1 - x2 == -1 && y1 - y2 == 1) {
                return pieceAt(x2 - 1, y2 + 1) == null;
            }
            else {
                return false;
            }
        }
    }

    private boolean checkWater(Piece p, int x1, int y1, int x2, int y2) {
        boolean hop = checkHopWater(p, x1, y1, x2, y2);
        if (hop) {
            return true;
        }
        else {
            if (x1 - x2 == 1 && y1 - y2 == -1) {
                return pieceAt(x2 + 1, y2 - 1) == null;
            }
            else if (x1 - x2 == -1 && y1 - y2 == -1) {
                return pieceAt(x2 - 1, y2 - 1) == null;
            }
            else {
                return false;
            }
        }
    }

    private boolean validMove(Piece p, int x1, int y1, int x2, int y2) {
        if (p.isKing()) {
            return (checkFire(p, x1, y1, x2, y2)) || (checkWater(p, x1, y1, x2, y2));
        }
        else if (p.isFire()) {
            return checkFire(p, x1, y1, x2, y2);
        }
        else {
            return checkWater(p, x1, y1, x2, y2);
        }
    }

    public void select(int x, int y) {
        // Selecting a piece if no prior selection
        if (pieceAt(selectX, selectY) == null) {
            selectX = x;
            selectY = y;
        }
        // Selecting a piece if prior piece has no captures 
        // and previous piece has not moved
        else if (!pieceAt(selectX, selectY).hasCaptured() && !pieceMoved) {
            if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == pieceAt(selectX, selectY).isFire()) {
                selectX = x;
                selectY = y;
            }
            // Moving a prior piece to a new location
            else {
                if (pieceAt(selectX, selectY).isBomb() && Math.abs(selectX - x) == 2 && Math.abs(selectY - y) == 2) {
                    pieceAt(selectX, selectY).move(x, y);
                    selectX = -1;
                    selectY = -1;
                }
                else {
                    pieceAt(selectX, selectY).move(x, y);
                    selectX = x;
                    selectY = y;
                }
                pieceMoved = true;    
            }
        }
        else if (pieceAt(selectX, selectY).hasCaptured() && Math.abs(selectX - x) == 2 && Math.abs(selectY - y) == 2) {
            pieceAt(selectX, selectY).move(x, y);
            selectX = x;
            selectY = y;
            pieceMoved = true;
        }
    }

    public void place(Piece p, int x, int y) {
        if (p != null) {
            try {
                pieces[x][y] = p;
            }
            catch (Exception e) {}
        }
    }

    public Piece remove(int x, int y) {
        Piece temp = pieceAt(x, y);
        try {
            pieces[x][y] = null;
        }
        catch (Exception e) {}
        return temp;
    }

    public boolean canEndTurn() {
        return pieceMoved;
    }

    public void endTurn() {
        if (canEndTurn()) {
            firePlayer = ! firePlayer;
            pieceMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (pieceAt(i, j) != null) {
                        pieceAt(i, j).doneCapturing();
                    }
                }
            }
            selectX = -1;
            selectY = -1;
        }
    }

    public String winner() {
        int fire = 0;
        int water = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece p = pieceAt(i, j);
                if (p != null) {
                    if (p.isFire())
                        fire++;
                    else
                        water++;
                }
            }
        }
        if (fire == 0 && water == 0) {
            return "No One";
        }
        else if (water == 0) {
            return "Fire";
        }
        else if (fire == 0) {
            return "Water";
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                // System.out.println(b.canSelect(x, y));
                if (b.canSelect(x, y)) {
                    b.select(x, y);
               }
            }
            if (StdDrawPlus.isSpacePressed()) {
                b.endTurn();
            }
            StdDrawPlus.show(100);
        }
    }
}