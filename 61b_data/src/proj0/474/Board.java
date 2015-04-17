/** 
 *  @author Ian MacGregor
 */

public class Board {

    /** Location of pieces. */
    private boolean hasSelected;
    private boolean hasMoved;
    private int[] selected = new int[2];
    private int N = 8;
    private Piece[][] pieces = new Piece[N][N];
    private int currentPlayer = 0;
    private boolean mustDoubleCapture = false;

    public Board(boolean shouldBeEmpty) {
        this.pieces = pieces;
        this.hasSelected = hasSelected;
        this.hasMoved = hasMoved;
        this.selected = selected;
        this.currentPlayer = currentPlayer;
        this.mustDoubleCapture = mustDoubleCapture;

        if (!shouldBeEmpty) {
            int fireX = 0; 
            int waterX = 0;
            int fireYpawn = 0;
            int fireYshield = 1;
            int fireYbomb = 2;
            int waterYpawn = N - 1;
            int waterYshield = N - 2;
            int waterYbomb = N - 3;
            while (fireX < N) {
                pieces[fireX][fireYpawn] = new Piece(true, this, fireX, fireYpawn, "pawn");
                pieces[fireX][fireYbomb] = new Piece(true, this, fireX, fireYbomb, "bomb");
                fireX += 1;
                pieces[fireX][fireYshield] = new Piece(true, this, fireX, fireYshield, "shield");
                fireX += 1;
            }
            while (waterX < N) {
                pieces[waterX][waterYshield] = new Piece(false, this, waterX, waterYshield, "shield");
                waterX += 1;
                pieces[waterX][waterYpawn] = new Piece(false, this, waterX, waterYpawn, "pawn");
                pieces[waterX][waterYbomb] = new Piece(false, this, waterX, waterYbomb, "bomb");
                waterX += 1;
            }
        }
    }

    private void drawBoard() {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                boolean isTheSelectedSpace = (((hasSelected) && ((i == selected[0]) && (j == selected[1]))) && (pieceAt(i, j) != null));
                if (isTheSelectedSpace) {
                    StdDrawPlus.filledSquare(selected[0] + .5, selected[1] + .5, .5);
                }
                Piece pieceIJ = pieces[i][j];
                if (pieceIJ != null) {
                    if (pieceIJ.isFire()) {
                        if (pieceIJ.isBomb()) {
                            if (pieceIJ.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else if (pieceIJ.isShield()) {
                            if (pieceIJ.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        } else {
                            if (pieceIJ.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    } else if (!pieceIJ.isFire()) {
                        if (pieceIJ.isBomb()) {
                            if (pieceIJ.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            
                        } else if (pieceIJ.isShield()) {
                            if (pieceIJ.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        } else {
                            if (pieceIJ.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        boolean withinBounds = (((x < N) && (y < N)) && ((x >= 0) && (y >= 0)));
        if (withinBounds) {
            return pieces[x][y];
        } else {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        if (!mustDoubleCapture) {
            if (pieceAt(x, y) == null) {
                if (pieceAt(selected[0], selected[1]) == null) {
                    return false;
                }
                if ((hasSelected) && (!hasMoved)) {
                    if (validMove(selected[0], selected[1], x, y)) {
                        return true;
                    }
                }
            } else {
                boolean playerPieceMatch = (currentPlayer == pieceAt(x, y).side());
                if (playerPieceMatch) {
                    if ((!hasSelected) && (!hasMoved)) {
                        return true;
                    } else if ((hasSelected) && (!hasMoved)) {
                        return true;
                    }
                }
            }
        } else if (mustDoubleCapture) {
            if (!hasSelected) {
                if ((selected[0] == x) && (selected[1] == y)) {
                    return true;
                }
            } else {
                Piece initialPiece = pieceAt(selected[0], selected[1]);
                int diffXdouble = x - selected[0];
                int diffYdouble = y - selected[1];
                if ((diffXdouble == 2) || (diffXdouble == -2)) {
                    Piece middlePiece = pieceAt(selected[0] + diffXdouble/2, selected[1] + diffYdouble/2);
                    if (middlePiece != null) {
                        if (currentPlayer != middlePiece.side()) {
                            if (!initialPiece.isKing()) {
                                boolean moveDiagonally = ((diffXdouble == -2) || (diffXdouble == 2));
                                if (moveDiagonally) {
                                    if (currentPlayer == 0) {
                                        if (diffYdouble == 2) {
                                            return true;
                                        }
                                    } else if (currentPlayer == 1) {
                                        if (diffYdouble == -2) {
                                            return true;
                                        }
                                    }
                                }
                            } else if (initialPiece.isKing()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece initialPiece = pieceAt(xi, yi);
        int diffX = xf - xi;
        int diffY = yf - yi;
        if (initialPiece == null) {
            return false;
        }
        if ((diffX == 1) || (diffX == -1)) {
            if (!initialPiece.isKing()) {
                boolean moveDiagonally = ((diffX == -1) || (diffX == 1));
                if (moveDiagonally) {
                    if (currentPlayer == 0) {
                        if (diffY == 1) {
                            return true;
                        }
                    } else if (currentPlayer == 1) {
                        if (diffY == -1) {
                            return true;
                        }
                    }
                }
            } else if (initialPiece.isKing()) {
                return true;
            }
        } else if ((diffX == 2) || (diffX == -2)) {
            Piece middlePiece = pieceAt(xi + diffX/2, yi + diffY/2);
            if (middlePiece != null) {
                if (currentPlayer != middlePiece.side()) {
                    if (!initialPiece.isKing()) {
                        boolean moveDiagonally = ((diffX == -2) || (diffX == 2));
                        if (moveDiagonally) {
                            if (currentPlayer == 0) {
                                if (diffY == 2) {
                                    return true;
                                }
                            } else if (currentPlayer == 1) {
                                if (diffY == -2) {
                                    return true;
                                }
                            }
                        }
                    } else if (initialPiece.isKing()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        Piece piece01 = pieceAt(selected[0], selected[1]);
        if (pieceAt(x, y) == null) {
            piece01.move(x, y);
            if (piece01.hasCaptured()) {
                mustDoubleCapture = true;
                hasSelected = true;
            } else {
                hasSelected = false;
            }
            selected[0] = x;
            selected[1] = y;
            hasMoved = true;
        } else {
            selected[0] = x;
            selected[1] = y;
            hasSelected = true;
        }
    }

    public void place(Piece p, int x, int y) {
        if ((((x >= N) || (y >= N)) || ((x < 0) || (y < 0))) || (p == null)) {
            return ;
        } else {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (((x < N) && (y < N)) && ((x >= 0) && (y >= 0))) {
            Piece pieceToReturn = pieces[x][y];
            pieces[x][y] = null;
            return pieceToReturn;
        } else {
            if (((x < N) && (y < N)) && !((x >= 0) && (y >= 0))) {
                System.out.println("X is out of range.");
                return null;
            } else if (!((x < N) && (y < N)) && ((x >= 0) && (y >= 0))) {
                System.out.println("Y is out of range.");
                return null;
            } else {
                System.out.println("Both X and Y are out of range.");
                return null;
            }
            
        }
    }

    public boolean canEndTurn() {
        if (hasMoved) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        Piece selectedPieceDone = pieceAt(selected[0], selected[1]);
        if (selectedPieceDone != null) {
            selectedPieceDone.doneCapturing();
        }
        hasSelected = false;
        hasMoved = false;
        mustDoubleCapture = false;
        // Change Player. 
        // if (currentPlayer == 0) {
        //     System.out.print("Player changed from Fire to ");
        // } else if (currentPlayer == 1) {
        //     System.out.print("Player changed from Water to ");
        // }
        if (currentPlayer == 1) {
            currentPlayer = 0;
        } else {
            currentPlayer = 1;
        }
        // if (currentPlayer == 0) {
        //     System.out.println("Fire. ");
        // } else if (currentPlayer == 1) {
        //     System.out.println("Water.");
        // }
    }

    public String winner() {
        boolean isFireWinner = false;
        boolean isWaterWinner = false;
        for (int g = 0; g < N; g += 1) {
            for (int h = 0; h < N; h += 1) {
                if (pieceAt(g, h) != null) {
                    if (pieceAt(g, h).isFire()) {
                        isFireWinner = true;
                    } else {
                        isWaterWinner = true;
                    }
                }
            }
        }
        if ((!isFireWinner) && (!isWaterWinner)) {
            return "No one";
        } else if ((!isFireWinner) && (isWaterWinner)) {
            return "Water";
        } else if ((isFireWinner) && (!isWaterWinner)) {
            return "Fire";
        } else {
            return null;
        }
    }

// DONT CALL StdDraw in PUBLIC METHOD< MUST BE IN MAIN ????????///////////////////////////

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x,(int) y)) {
                    b.select((int) x,(int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if (b.winner() != null) {
                System.out.print(b.winner());
                b.drawBoard();
                return ;
            }
            StdDrawPlus.show(100);
        }
    }
}