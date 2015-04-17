/** 
 *  @author Jason Sellers
 */

public class Board {
    /** Location of pieces. */
    private static Piece[][] pieces;
    private static boolean shouldBeEmpty, validMove, inBounds, canEndTurn, hasMoved;
    private static int N = 8;
    private static int fPieces, wPieces, turn;
    private static int[] sPArray = new int[2];
    private static Piece sPiece;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    public Board(boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;
        pieces = new Piece[N][N];
        turn = 0;
        hasMoved = false;
        if (!shouldBeEmpty) {
            for (int i = 0; i < N; i+=2) {
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            }
            for (int i = 1; i < N; i+=2) {
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
            }
            for (int i = 0; i < N; i+=2) {
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            }
            for (int i = 1; i < N; i+=2) {
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            }
            for (int i = 0; i < N; i+=2) {
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
            }
            for (int i = 1; i < N; i+=2) {
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            }
            fPieces = 12;
            wPieces = 12; 
        }       
        
    }
    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {              
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (sPiece != null) {
                    if ((i == sPArray[0]) && (j == sPArray[1])) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                }  
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece piece = pieces[i][j];
                if (piece != null) {
                    String type;
                    if (piece.isBomb()) {
                        type = "bomb";
                    } else if (piece.isShield()) {
                        type = "shield";
                    } else type = "pawn";
                    if ((piece.isFire()) && (piece.isKing())) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-fire-crowned.png", 1, 1);
                    }
                    if (!(piece.isFire()) && (piece.isKing())) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-water-crowned.png", 1, 1);
                    }                    
                    if (piece.isFire()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-fire.png", 1, 1);
                    }
                    if (!piece.isFire()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-water.png", 1, 1);
                    }
                }
            }
        }
    }
    public Piece pieceAt(int x, int y) {
        if (inBounds(x, y)) {
            return pieces[x][y];
        } return null;
    }
    
    public boolean canSelect(int x, int y) {        
        // handle piece selection
        Piece p = pieceAt(x, y);
        if ((p != null) && (p.side() == turn)) { 
            if ((sPiece == null) || !hasMoved) {
                return true;
            } return false;
        
        // handle piece placement
        } else if ((p == null) && (sPiece != null)) {
            if (!hasMoved && (validMove(sPArray[0], sPArray[1], x, y))) {
                return true;
            } else if (sPiece.hasCaptured() && (validMove(sPArray[0], sPArray[1], x, y))) {
                return true;
            } return false;
        } return false;
    } 

    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            sPiece = pieceAt(x, y);
            sPArray[0] = x;
            sPArray[1] = y;
        } else if ((pieceAt(x, y) == null) && (sPiece != null)) {
            sPiece.move(x, y);
            hasMoved = true;
            canEndTurn = true;
        }
    }
    public void place(Piece p, int x, int y) {
        if (inBounds(x, y)) {
            pieces[x][y] = p;
            sPArray[0] = x;
            sPArray[1] = y;
            sPiece = p;
        }
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi, yi);        
        if (p != null) {
            if (((p.isFire() && (yf < yi) && !p.isKing()) || 
                 (!p.isFire() && (yf > yi) && !p.isKing()))) {           
                return false; 
            }
        }
        if (!hasMoved &&
            (Math.abs(xi - xf) == 1) &&
            (Math.abs(yi - yf) == 1)) { 
            return true;
        } else if ((Math.abs(xi - xf) == 2) &&
                   (Math.abs(yi - yf) == 2)) {
            int x = Math.abs(xi + xf) / 2;
            int y = Math.abs(yi + yf) / 2;
            Piece c = pieceAt(x, y);
            if ((c != null) && 
                (c.isFire() != p.isFire())) {                
                return true;
            } return false;
        } return false;
    }

    private boolean inBounds(int x, int y) {
        return (((0 <= x) && (x < 8)) && ((0 <= y) && (y < 8)));
    }
    public Piece remove(int x, int y) {
        Piece r = pieceAt(x, y);
        if (!inBounds(x, y)) {
            System.out.println("input is out of bounds");
            return null;
        }
        if (r == null) {
            System.out.println("no piece at this location");
            return null;
        }   
        if (r.isFire()) {
            fPieces = fPieces - 1;
        } wPieces = wPieces - 1;  
        pieces[x][y] = null;
        return r;
    }
    public boolean canEndTurn() {
        return canEndTurn;
    }
    public void endTurn() {
        if (sPiece != null) {
            sPiece.doneCapturing();
            sPiece = null;
        }
        canEndTurn = false;
        hasMoved = false;
        if (turn == 1) {
            turn = 0;
        } else {
            turn = 1;
        }
        
    }
    public String winner() {
        if ((fPieces == 0) && (wPieces == 0)) {
            return "No one";
        } else if (fPieces == 0) {
            return "Water";
        } else if (wPieces == 0) {
            return "Fire";
        } return null;
    }

    public static void main(String[] args) {
        N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        
        while (b.shouldBeEmpty) {
            drawBoard(N);
            StdDrawPlus.show(100);
        }
        while (!b.shouldBeEmpty) {
            drawBoard(N);
            /** Monitors for mouse presses. Wherever the mouse is pressed,
                a new piece appears. */            
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            } 
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }     
            StdDrawPlus.show(100);
        }
    }
}