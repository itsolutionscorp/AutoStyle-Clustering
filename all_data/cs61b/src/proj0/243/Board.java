/* Collaborated with Neil Shankar, discussed ideas, etc. Specifically for example gave me idea for figuring out what to do for a bomb
*  explosion capture where no one wins. 
*/

public class Board {

    private Piece[][] pieces;
    private Board b; 
    private String winner; 

    private boolean fireTurn;
    private boolean alreadyMoved; 

    private Piece selectedPiece; 
    private int xCoord; 
    private int yCoord; 

    private boolean isGameOver;  

    public static void main(String args[]) { 
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false); 

        while(!b.isGameOver) {
            b.drawBoard(N); 
            b.drawSelected(N); 
            b.drawPieces(N);
            b.updatePieces(); 
            StdDrawPlus.show(10);
        }
        b.winner(); 
        System.out.println(b.winner); 
    }

    private void updateIsGameOver() {
        if (winner() != null) {
            isGameOver = true; 
        }
    }

    private void updatePieces() {
        if (StdDrawPlus.mousePressed()) {
            double a = StdDrawPlus.mouseX();
            double b = StdDrawPlus.mouseY();

            int x = (int) a; 
            int y = (int) b; 

            if (canSelect(x, y)) {
                select(x, y); 
                updateIsGameOver(); 
            }
        } 
        StdDrawPlus.show(100); 
        if (StdDrawPlus.isSpacePressed()) {
            if (canEndTurn()) {
                endTurn(); 
            }
        }
        StdDrawPlus.show(10); 

    }

    private void drawPieces(int N) {
        String whichType; 
        String isFire; 
        String isCrowned = ""; 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    Piece temp = pieceAt(i, j);
                    if (temp != null) { 
                        if (temp.isBomb() == false) {
                            if (temp.isShield() == false) {
                                whichType = "pawn-"; 
                            } else {
                                whichType = "shield-"; 
                            }
                        } else {
                            whichType = "bomb-"; 
                        }
                        if (temp.isFire() == true) {
                            isFire = "fire"; 
                        } else {
                            isFire = "water"; 
                        }

                        if (temp.isKing()) {
                            isCrowned = "-crowned"; 
                        } else {
                            isCrowned = ""; 
                        }
                        StdDrawPlus.picture(i+.5, j+.5, "img/" + whichType + isFire + isCrowned + ".png", 1, 1);
                    }
                }
            }
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
    } 

    private void drawSelected(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    Piece temp = pieceAt(i, j); 
                    if (temp != null && selectedPiece != null) {
                        if (selectedPiece.equals(temp)) {
                            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                        }
                    }
                }
            }
        }
    }

    private void initialGameState() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    if (j == 7) {
                        Piece p = new Piece(false, b, i, j, "pawn"); 
                        place(p, i, j); 
                    }
                    else if (j == 6) {
                        Piece p = new Piece(false, b, i, j, "shield"); 
                        place(p, i, j); 
                    }
                    else if (j == 5) {
                        Piece p = new Piece(false, b, i, j, "bomb"); 
                        place(p, i, j); 
                    }
                    else if (j == 2) {
                        Piece p = new Piece(true, b, i, j, "bomb"); 
                        place(p, i, j); 
                    }
                    else if (j == 1) {
                        Piece p = new Piece(true, b, i, j, "shield"); 
                        place(p, i, j); 
                    }
                    else if (j == 0) {
                        Piece p = new Piece(true, b, i, j, "pawn"); 
                        place(p, i, j); 
                    }
                    else {
                        pieces[i][j] = null; 
                    }
                } else {
                    pieces[i][j] = null; 
                }
            }
        }
    }

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8]; 
        winner = null; 
        fireTurn = true;
        alreadyMoved = false; 
        selectedPiece = null;
        b = this; 

        isGameOver = false; 

        if(shouldBeEmpty == false) {
            this.initialGameState(); 
        } 
    }

    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7)) {
            return null; 
        } else {
            return pieces[x][y]; 
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece temp = pieceAt(xi, yi);
        int xDiff = xf - xi; 
        int yDiff = yf - yi; 
        int absX = Math.abs(xDiff); 
        int absY = Math.abs(yDiff); 


        if ((absX != absY) || ((absX != 1) && (absX != 2))) {
            return false; 
        } 
        else if (pieceAt(xf, yf) != null) {
            return false; 
        } 
        else if (absX == 1) {
            if (temp != null) {
                if (temp.hasCaptured()) {
                    return false; 
                } else if (alreadyMoved) {
                    return false; 
                } else if (temp.isKing()) {
                    if (temp.hasCaptured()) {
                        return false; 
                    } else if (alreadyMoved) {
                        return false; 
                    }
                    return true; 
                } else if (yDiff < 0) {

                    if (temp.isFire() == false) {
                        return true; 
                    }
                } else {
                    if (temp.isFire()) {
                        return true; 
                    }
                }
            }
        } else if (absX == 2) {
            Piece captured = pieceAt(Math.abs((xf + xi)/2), Math.abs((yf + yi)/2)); 
            if (captured != null && captured.isFire() != fireTurn) {
                if (temp.isKing()) {
                    return true; 
                } else {
                    if (yDiff < 0) {
                        if (temp.isFire() == false) {
                            return true; 
                        }
                    } else {
                        if (temp.isFire()) {
                            return true; 
                        }
                    }
                }
            }
        }
        return false; 
    }

    public boolean canSelect(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false; 
        }
        Piece temp = pieceAt(x, y); 
        if (temp == null) {
            if (selectedPiece != null) { 
                if (validMove(xCoord, yCoord, x, y)) {
                    return true; 
                } 
            }
        } else {
            if (fireTurn == temp.isFire()) {
                if (alreadyMoved == false) {
                    return true; 
                }
            }
        }
        return false; 
    }

    public boolean canEndTurn() {
        if (alreadyMoved) {
            return true; 
        }
        return false; 
    }

    public void endTurn() {
        if (fireTurn == true) {
            fireTurn = false; 
        } else {
            fireTurn = true; 
        }
        alreadyMoved = false; 
        if (selectedPiece != null) {
            selectedPiece.doneCapturing(); 
        }
        selectedPiece = null; 
        xCoord = -1; 
        yCoord = -1; 
    }

    public String winner() {
        boolean anyPieces = false; 
        boolean anyWaterPieces = false; 
        boolean anyFirePieces = false; 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece temp = pieceAt(i, j); 
                if (temp != null) {
                    anyPieces = true; 
                    if (temp.isFire()) {
                        anyFirePieces = true; 
                    } else {
                        anyWaterPieces = true; 
                    }
                }
            }
        }

        if (anyPieces == false) {
            winner = "No one"; 
        } else if (anyFirePieces == true && anyWaterPieces == false) {
            winner = "Fire"; 
        } else if (anyWaterPieces == true && anyFirePieces == false) {
            winner = "Water"; 
        } else if (anyWaterPieces == true && anyFirePieces == true) {
            winner = null; 
        }
        return winner; 
    }

    public void select(int x, int y) {
        if (selectedPiece == null) {
            xCoord = x; 
            yCoord = y; 
            selectedPiece = pieceAt(xCoord, yCoord); 
        } else {
            alreadyMoved = true; 
            selectedPiece.move(x, y); 
        }
    }

    public void place(Piece p, int x, int y) {
        if ((x < 8) && (y < 8)) {
            if (pieceAt(x, y) != null) {
                remove(x, y); 
            }
            xCoord = x; 
            yCoord = y;
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        Piece temp = pieceAt(x, y); 
        if (temp != null) {
            pieces[x][y] = null; 
        } else if (x > 8 || y > 8) {
            System.out.println("remove: out of bounds"); 
        } else if (temp == null) {
            System.out.println("remove: no piece there"); 
        }
        System.out.println("remove: removed piece temp = " + temp); 
        return temp; 
    }
}
