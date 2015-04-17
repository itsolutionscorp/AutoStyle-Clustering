/**
 * Created by riyana on 2/7/2015.
 */

public class Board
{
    private static final String pawn = "pawn";
    private static final String shield = "shield";
    private static final String bomb = "bomb";
    private Piece pieces[][];
    private int playingSide;
    private Piece activePiece;
    private int activeX;
    private int activeY;
    private boolean activePieceMoved;
    private static final int boardSize = 8;


    private static final int Y_DISP_UP_1 = 1;
    private static final int Y_DISP_DN_1 = -1;
    private static final int Y_DISP_UP_2 = 2;
    private static final int Y_DISP_DN_2 = -2;
    private static final int DISP_JMP = 2;
    private static final int DISP_NO_JMP = 1;

    public static void main(String[] args) {
        Board newBoard = new Board(false);
        drawBoard(newBoard);
    }

    public Board(boolean shouldBeEmpty) {
        playingSide = 0;
        pieces = new Piece[boardSize][boardSize];
        if (!shouldBeEmpty) {
            for (int i=0; i<boardSize; i+=2) {
                pieces[i][0]   = new Piece(true, this, i, 0, pawn);
                pieces[i+1][7] = new Piece(false, this, i+1, 7, pawn);

                pieces[i+1][1] = new Piece(true, this, i+1, 1, shield);
                pieces[i][6]   = new Piece(false, this, i, 6, shield);

                pieces[i][2]   = new Piece(true, this, i, 2, bomb);
                pieces[i+1][5] = new Piece(false, this, i+1, 5, bomb);
            }
        }
    }

    private static void drawBoard(Board b) {
        StdDrawPlus.setXscale(0, boardSize);
        StdDrawPlus.setYscale(0, boardSize);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = b.pieceAt(i, j);
                if (p != null) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + getPieceImage(p), 1, 1);
                }
            }
        }
    }

    private static String getPieceImage(Piece p) {
        if (p.isBomb()) {
            if (p.isFire()) {
                if (p.isKing()) {
                    return "bomb-fire-crowned.png";
                }
                return "bomb-fire.png";
            } else {
                if (p.isKing()) {
                    return "bomb-water-crowned.png";
                }
                return "bomb-water.png";
            }
        } else if (p.isShield()) {
            if (p.isFire()) {
                if (p.isKing()) {
                    return "shield-fire-crowned.png";
                }
                return "shield-fire.png";
            } else {
                if (p.isKing()) {
                    return "shield-water-crowned.png";
                }
                return "shield-water.png";
            }
        } else {
            if (p.isFire()) {
                if (p.isKing()) {
                    return "pawn-fire-crowned.png";
                }
                return "pawn-fire.png";
            } else {
                if (p.isKing()) {
                    return "pawn-water-crowned.png";
                }
                return "pawn-water.png";
            }
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= boardSize || y < 0 || y >= boardSize;
    }

    public Piece pieceAt(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return false;
        }

        //if (activePiece.isBomb() && activePiece.hasCaptured()) {
          //  return false;
        //}

        Piece selectedPosPiece = pieceAt(x, y);
        if (selectedPosPiece != null) {
            if (selectedPosPiece.side() == playingSide) {
                if (activePiece == null || !activePieceMoved) {
                    return true;
                }
            }
        } else {
            if ((activePiece != null) && !activePieceMoved && validMove(activeX, activeY, x, y)) {
                return true;
            }
            if ((activePiece != null) && activePiece.hasCaptured() && validMove(activeX, activeY, x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (isOutOfBounds(xf, yf) || pieceAt(xf, yf) != null) {
            return false;
        }
        int yDisp = yf - yi;
        int xDispAbs = Math.abs(xf - xi);
        int yDispAbs = Math.abs(yf - yi);
        Piece piece = pieceAt(xi, yi);
        if (piece == null) { // only a non-null initial position piece can move
            return false;
        }
        boolean isKing = piece.isKing();
        if (xDispAbs == DISP_NO_JMP && !piece.hasCaptured()) { // cannot move only 1 step after capture
            if (isKing) {
                return yDispAbs == DISP_NO_JMP;
            } else {
                if (piece.isFire()) {
                    return yDisp == Y_DISP_UP_1;
                } else {
                    return yDisp == Y_DISP_DN_1;
                }
            }
        }
        if ((xDispAbs == DISP_JMP) && (yDispAbs == DISP_JMP)) {
            Piece jumped = pieceAt((xf + xi) / 2, (yf + yi) / 2);
            // jump needs other side present to be jumped over
            if (jumped != null && jumped.side() != piece.side()) {
                if (isKing) {
                    return true;
                } else {
                    if (piece.isFire()) {
                        return yDisp == Y_DISP_UP_2;
                    } else {
                        return yDisp == Y_DISP_DN_2;
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            activePiece.move(x, y);
            activePieceMoved = true;
        }
        activePiece = pieceAt(x, y);
        activeX = x;
        activeY = y;
    }

    public void place(Piece p, int x, int y) {
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (isOutOfBounds(x, y)) {
            System.out.println("Location out of bounds!");
            return null;
        }
        Piece piece = pieces[x][y];
        if (piece == null) {
            System.out.println("No piece here!");
            return null;
        } else {
            pieces[x][y] = null;
            return piece;
        }
    }

    public boolean canEndTurn() {
        if (activePieceMoved) {
            return true;
        } else if ((activePiece != null) && activePiece.hasCaptured()) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        playingSide = (playingSide + 1) % 2;
        activePieceMoved = false;
        if (activePiece != null) {
            activePiece.doneCapturing();
            activePiece = null;
        }
    }

    public String winner() {
        int numFirePieces = 0;
        int numWaterPieces = 0;
        Piece piece;
        for (int i=0; i< boardSize; i++) {
            for (int j=0; j< boardSize; j++) {
                piece = pieces[i][j];
                if (piece != null) {
                    if (piece.isFire()) {
                        numFirePieces++;
                    } else {
                        numWaterPieces++;
                    }
                }
            }
        }
        if (numFirePieces > 0) {
            if (numWaterPieces == 0)
                return "Fire";
            else {
                return null;
            }
        } else {
            if (numWaterPieces > 0) {
                return "Water";
            } else {
                return "No one";
            }
        }
    }
}
