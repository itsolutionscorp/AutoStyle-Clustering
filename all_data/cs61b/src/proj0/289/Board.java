/**
 * Created by Buckley on 2/7/15.
 */

public class Board {

    // Instance variables
    private Piece[][] pieces;
    private int player = 0;
    private boolean canEndTurn = false;
    private Piece selectedPiece;
    private int selectedPieceY;
    private int selectedPieceX;

    //Constructor
    public Board(boolean shouldBeEmpty) {
        //Create and populate array of pieces within the board.
        pieces = new Piece[8][8];
        if (!shouldBeEmpty) {
            for (int i = 0; i < 8; i += 2) {
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
            }
            for (int i = 0; i < 8; i += 2) {
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            }
            for (int i = 0; i < 8; i += 2) {
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            }
        }
    }

    //Drawing Method taken from StdDrawDemo
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImgPath(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String getImgPath(Piece p) {
        String path;
        if (p.isShield()) {
            path = "img/shield";
        } else if (p.isBomb()) {
            path = "img/bomb";
        } else {
            path = "img/pawn";
        }
        if (p.isFire()) {
            path += "-fire";
        } else {
            path += "-water";
        }
        if (p.isKing()) {
            path += "-crowned";
        }
        path += ".png";
        return path;
    }

    //Various Board Methods
    public Piece pieceAt(int x, int y) {
        if ((x > pieces[0].length-1) || (y > pieces[0].length-1) || (x < 0) || (y < 0)) {
            return null;
        } else
        return pieces[x][y];
    }

    // Should be private
    private boolean validMove(Piece p, int x, int y) {
        // Make sure new location is unoccupied
        if (pieces[x][y] != null) {
            return false;
        }
        // Moves for Fire Pieces
        if (p.isFire()) {
            // Normal Upward Moves
            if (((x == selectedPieceX + 1) || (x == selectedPieceX - 1)) && (y == selectedPieceY + 1)) {
                return true;
            }
            // Capture Upward Right Move
            if ((x == selectedPieceX + 2) && (y == selectedPieceY + 2)) {
                if (pieces[selectedPieceX+1][selectedPieceY+1] != null) {
                    if (!pieces[selectedPieceX+1][selectedPieceY+1].isFire()) {
                        return true;
                    }
                }
            }
            // Capture Upward Left Move
            if ((x == selectedPieceX - 2) && (y == selectedPieceY + 2)) {
                if (pieces[selectedPieceX-1][selectedPieceY+1] != null) {
                    if (!pieces[selectedPieceX-1][selectedPieceY+1].isFire()) {
                        return true;
                    }
                }
            }
            // Backward moves Available to Kinged Fire Pieces
            if (p.isKing()) {
                // Normal Downward Moves
                if (((x == selectedPieceX + 1) || (x == selectedPieceX - 1)) && (y == selectedPieceY - 1)) {
                    return true;
                }
                // Capture Downward Right
                if ((x == selectedPieceX + 2) && (y == selectedPieceY - 2)) {
                    if (pieces[selectedPieceX+1][selectedPieceY-1] != null) {
                        if (!pieces[selectedPieceX+1][selectedPieceY-1].isFire()) {
                            return true;
                        }
                    }
                }
                // Capture Downward Left
                if ((x == selectedPieceX - 2) && (y == selectedPieceY - 2)) {
                    if (pieces[selectedPieceX-1][selectedPieceY-1] != null) {
                        if (!pieces[selectedPieceX-1][selectedPieceY-1].isFire()) {
                            return true;
                        }
                    }
                }
            }
        }
        // Moves for Water Pieces ////
        if (!p.isFire()) {
            // Normal Downward Moves
            if (((x == selectedPieceX + 1) || (x == selectedPieceX - 1)) && (y == selectedPieceY - 1)) {
                return true;
            }
            // Capture Downward Right Move
            if ((x == selectedPieceX + 2) && (y == selectedPieceY - 2)) {
                if (pieces[selectedPieceX+1][selectedPieceY-1] != null) {
                    if (pieces[selectedPieceX+1][selectedPieceY-1].isFire()) {
                        return true;
                    }
                }
            }
            // Capture Downward Left Move
            if ((x == selectedPieceX - 2) && (y == selectedPieceY - 2)) {
                if (pieces[selectedPieceX-1][selectedPieceY-1] != null) {
                    if (pieces[selectedPieceX-1][selectedPieceY-1].isFire()) {
                        return true;
                    }
                }
            }
            // Backward moves Available to Kinged Water Pieces
            if (p.isKing()) {
                // Normal Upward Moves
                if (((x == selectedPieceX + 1) || (x == selectedPieceX - 1)) && (y == selectedPieceY + 1)) {
                    return true;
                }
                // Capture Upward Right
                if ((x == selectedPieceX + 2) && (y == selectedPieceY + 2)) {
                    if (pieces[selectedPieceX+1][selectedPieceY+1] != null) {
                        if (pieces[selectedPieceX+1][selectedPieceY+1].isFire()) {
                            return true;
                        }
                    }
                }
                // Capture Upward Left
                if ((x == selectedPieceX - 2) && (y == selectedPieceY + 2)) {
                    if (pieces[selectedPieceX-1][selectedPieceY+1] != null) {
                        if (pieces[selectedPieceX-1][selectedPieceY+1].isFire()) {
                            return true;
                        }
                    }
                }
            }


        }
        // If none of the above are true then move must be invalid.
        return false;
    }

    //Should be private
    private boolean validMultiCaptureMove(Piece p, int x, int y) {
        if (p.hasCaptured()) {
            if (p.isFire()) {
                // Capture Upward Right Move
                if ((x == selectedPieceX + 2) && (y == selectedPieceY + 2)) {
                    if (pieces[selectedPieceX + 1][selectedPieceY + 1] != null) {
                        if (!pieces[selectedPieceX + 1][selectedPieceY + 1].isFire()) {
                            return true;
                        }
                    }
                }
                // Capture Upward Left Move
                if ((x == selectedPieceX - 2) && (y == selectedPieceY + 2)) {
                    if (pieces[selectedPieceX - 1][selectedPieceY + 1] != null) {
                        if (!pieces[selectedPieceX - 1][selectedPieceY + 1].isFire()) {
                            return true;
                        }
                    }
                }
                if (p.isKing()) {
                    // Capture Downward Right
                    if ((x == selectedPieceX + 2) && (y == selectedPieceY - 2)) {
                        if (pieces[selectedPieceX + 1][selectedPieceY - 1] != null) {
                            if (!pieces[selectedPieceX + 1][selectedPieceY - 1].isFire()) {
                                return true;
                            }
                        }
                    }
                    // Capture Downward Left
                    if ((x == selectedPieceX - 2) && (y == selectedPieceY - 2)) {
                        if (pieces[selectedPieceX - 1][selectedPieceY - 1] != null) {
                            if (!pieces[selectedPieceX - 1][selectedPieceY - 1].isFire()) {
                                return true;
                            }
                        }
                    }
                }
                return false;

            }
        }
        if (!p.isFire()) {
            // Capture Downward Right
            if ((x == selectedPieceX + 2) && (y == selectedPieceY - 2)) {
                if (pieces[selectedPieceX + 1][selectedPieceY - 1] != null) {
                    if (pieces[selectedPieceX + 1][selectedPieceY - 1].isFire()) {
                        return true;
                    }
                }
            }
            // Capture Downward Left
            if ((x == selectedPieceX - 2) && (y == selectedPieceY - 2)) {
                if (pieces[selectedPieceX - 1][selectedPieceY - 1] != null) {
                    if (pieces[selectedPieceX - 1][selectedPieceY - 1].isFire()) {
                        return true;
                    }
                }
            }
            if (p.isKing()) {
                // Capture Upward Right Move
                if ((x == selectedPieceX + 2) && (y == selectedPieceY + 2)) {
                    if (pieces[selectedPieceX+1][selectedPieceY+1] != null) {
                        if (pieces[selectedPieceX+1][selectedPieceY+1].isFire()) {
                            return true;
                        }
                    }
                }
                // Capture Upward Left Move
                if ((x == selectedPieceX - 2) && (y == selectedPieceY + 2)) {
                    if (pieces[selectedPieceX-1][selectedPieceY+1] != null) {
                        if (pieces[selectedPieceX-1][selectedPieceY+1].isFire()) {
                            return true;
                        }
                    }
                }

            }
            return false;
        }
        return false;
    }

    public boolean canSelect(int x, int y) {
        //System.out.println("Entered canSelect with parameters" + x + ", " + y);
        if (selectedPiece != null) {
            //System.out.println("canSelect:there is already a selectedPiece at " + selectedPieceX + ", " + selectedPieceY);
        }
        // Valid initial selections of pieces for fire player = 0
        if ((pieces[x][y] != null) && (!canEndTurn())) {
            if (player == 0) {
                if (pieces[x][y].isFire()) {
                    return true;
                }
            }
            if (player == 1) {
                if (!pieces[x][y].isFire()) {
                    return true;
                }
            }
        }


        // Valid move destinations
        if (pieces[x][y] == null) {
            if (selectedPiece != null) {
                if (selectedPiece.hasCaptured()) {
                    return validMultiCaptureMove(selectedPiece, x, y);
                }
                if (!canEndTurn()) {
                    return validMove(selectedPiece, x, y);
                }
            }
        }
        return false;
    }


    public void select(int x, int y) {
        //System.out.println("Entered Select");
        //System.out.println(x + ", " + y);

        if (pieceAt(x, y) != null) {
            // Prepping piece for movement
            selectedPieceX = x;
            selectedPieceY = y;
            selectedPiece = pieces[x][y];
        } else if (selectedPiece != null) {
            selectedPiece.move(x, y);
            selectedPieceX = x;
            selectedPieceY = y;
            canEndTurn = true;
        }
    }

    public void place(Piece p, int x, int y) {
        //System.out.println("Entered Place; placing at: " + x + ", " + y);
        if ((p != null) && (x < 8) && (x >= 0) && (y < 8) && (y >= 0)) {
            pieces[x][y] = p;
        }
    }


    public boolean canEndTurn() {
        /*
        System.out.println("Entered canEndTurn");
        if (selectedPiece != null) {
            System.out.println("canEnd with selectedPiece at " + selectedPieceX + ", " + selectedPieceY + "king? " + selectedPiece.isKing() + " has captured in this turn? " + selectedPiece.hasCaptured());
        }
        else {
            System.out.println("canEnd - no selected piece rightnow");
        }
        System.out.println("Can End? " + canEndTurn);
        */
        return canEndTurn;
    }

    public void endTurn() {
        //System.out.println("Entered endTurn");
       // System.out.println("Player #: " + player);
        if (player == 0) {
            player = 1;
        } else {
            player = 0;
        }
        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
        }
        selectedPiece = null;
        canEndTurn = false;
    }

    public Piece remove(int x, int y) {
        if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
            System.out.println("Attempted remove was out of bounds");
            return null;
        }
        if (pieceAt(x, y) == null) {
            System.out.println("No piece at attempted remove location");
            return null;
        }
        Piece removed = pieces[x][y];
        pieces[x][y] = null;
        return removed;

    }


    public String winner() {

        int countWater = 0;
        int countFire = 0;
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece != null) {
                    if (piece.isFire()) {
                        countFire += 1;
                    } else {
                        countWater += 1;
                    }
                }
            }
        }

        if ((countFire == 0) && (countWater == 0)) {
            System.out.println("No One");
            return "No One";
        } else if (countFire == 0) {
            System.out.println("Water");
            return "Water";
        } else if (countWater == 0) {
            System.out.println("Fire");
            return "Fire";
        } else {
            return null;
        }
    }





    // Main Method
    public static void main(String args[]) {
        // Board Object
        Board b = new Board(true);

        //Test Configurations
        //Fire King Situation
/*
        b.pieces[0][5] = new Piece(true, b, 0, 5, "bomb");
        b.pieces[1][6] = new Piece(false, b, 1, 6, "pawn");
        b.pieces[3][7] = new Piece(false, b, 3, 7, "pawn");
        b.pieces[3][6] = new Piece(false, b, 3, 6, "shield");
*/
        // Water King Situation

        b.player = 1;
        b.pieces[2][2] = new Piece(false, b, 2, 2, "shield");
        b.pieces[3][1] = new Piece(true, b, 3, 1, "pawn");
        b.pieces[5][1] = new Piece(true, b, 5, 1, "shield");


        // Drawing
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //pieces = new boolean[N][N];

        /** Monitors for mouse presses.*/
        int counter = 0;
        boolean clicked;
        while(true) {
            b.drawBoard(N);
            while (StdDrawPlus.mousePressed()) {
                double x1 = StdDrawPlus.mouseX();
                int x = (int) x1;
                double y1 = StdDrawPlus.mouseY();
                int y = (int) y1;
                clicked = true;
                if ((!StdDrawPlus.mousePressed()) && (clicked)) {
                    if (b.canSelect(x,y)) {
                        b.select(x, y);
                        counter += 1;
                        System.out.println("Counter: " + counter);
                        clicked = false;
                        if (b.winner() != null) {
                            System.out.println(b.winner() + " wins. GAME OVER.");
                        }
                        if (b.selectedPiece != null) {
                            System.out.println("Selected piece at " + b.selectedPieceX + ", " + b.selectedPieceY);
                        }
                        counter += 1;
                        System.out.println("Counter: " + counter);
                        clicked = false;
                        if (b.winner() != null) {
                            System.out.println(b.winner() + " wins. GAME OVER.");
                        }
                        if (b.selectedPiece != null) {
                            System.out.println("Selected piece at " + b.selectedPieceX + ", " + b.selectedPieceY);
                        }
                    }
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn) {
                    b.endTurn();
                    System.out.println("Switched player turn");
                }
            }
            StdDrawPlus.show(70);
        }
    }
}

