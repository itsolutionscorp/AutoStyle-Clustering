public class Board {

    private Piece[][] pieces;
    private int turn, N, selectedX, selectedY, numFirePieces, numWaterPieces;
    private boolean hasSelected, hasMoved, hasStepped;
    private Piece selectedPiece;

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board gameboard = new Board(false);
        gameboard.drawBoard(gameboard.N);
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                if (gameboard.canSelect((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY())) {
                    gameboard.select((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY());
                    gameboard.drawBoard(gameboard.N);
                    StdDrawPlus.show(100);
                }
            }
            else if (StdDrawPlus.isSpacePressed()) {
                if (gameboard.canEndTurn()) {
                    gameboard.endTurn();
                    gameboard.drawBoard(gameboard.N);
                    StdDrawPlus.show(100);
                }
            }
            else if(gameboard.winner() != null) {
                System.out.println(gameboard.winner() + " wins!");
                System.exit(0);
            }
        }
    }

    public Board(boolean shouldBeEmpty) {
        N = 8; //board size
        selectedPiece = null;
        turn = 0; // fire goes first
        hasSelected = false;
        hasMoved = false;
        hasStepped = false;
        pieces = new Piece[N][N];
        numFirePieces = 0;
        numWaterPieces = 0;
        if (!shouldBeEmpty) {
            numFirePieces = 12;
            numWaterPieces = 12;
            for (int x = 0; x < N; x += 2)
                pieces[x][0] = new Piece(true, this, x, 0, "pawn");
            for (int x = 0; x < N; x += 2)
                pieces[x+1][1] = new Piece(true, this, x+1, 1, "shield");
            for (int x = 0; x < N; x += 2)
                pieces[x][2] = new Piece(true, this, x, 2, "bomb");
            for (int x = 0; x < N; x += 2)
                pieces[x+1][5] = new Piece(false, this, x+1, 5, "bomb");
            for (int x = 0; x < N; x += 2)
                pieces[x][6] = new Piece(false, this, x, 6, "shield");
            for (int x = 0; x < N; x += 2)
                pieces[x+1][7] = new Piece(false, this, x+1, 7, "pawn");
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (this.pieces[i][j] != null) {
                    if (this.pieces[i][j] == selectedPiece) {
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, imgFile(this.pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String imgFile(Piece p) {
        String filename = "img/";
        if (p.isBomb())
            filename += "bomb-";
        else if (p.isShield())
            filename += "shield-";
        else
            filename += "pawn-";
        if (p.isFire())
            filename += "fire";
        else
            filename += "water";
        if (p.isKing())
            filename += "-crowned";
        return filename + ".png";
    }

    public Piece pieceAt(int x, int y) {
        if (x >= pieces.length || y >= pieces[0].length || x < 0 || y < 0)
            return null;
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (x >= pieces.length || y >= pieces[0].length || x < 0 || y < 0)
            return false; //clicked out of bounds
        if (pieces[x][y] != null && turn == pieces[x][y].side())
            if (!hasSelected || !hasMoved)
                return true;
        if (pieces[x][y] == null && hasSelected && !hasStepped && isValid(x, y))
            return true;
        return false;
    }

    private boolean isValid(int x, int y) {
        if (pieceAt(x, y) != null)
            return false; //can't move onto another piece
        else if ((x == selectedX+1 || x == selectedX-1) && !hasMoved) {
            if (selectedPiece.isKing()) {
                if (y == selectedY+1 || y == selectedY-1) {
                    hasStepped = true;
                    return true;
                }
            }
            else { //not a king: fire can only move up
                if (selectedPiece.isFire() && y == selectedY+1) {
                    return true;
                } // water can only move down
                else if (!selectedPiece.isFire() && y == selectedY-1) {
                    return true;
                }
            }
        }
        else if ((x == selectedX+2 || x == selectedX-2) && (y == selectedY+2 || y == selectedY-2) && pieceAt(((selectedX+x)/2),((selectedY+y)/2)) != null) {
            if (pieceAt((selectedX+x)/2, (selectedY+y)/2).side() == selectedPiece.side())
                return false; //cannot capture own piece
            if (selectedPiece.isKing()) {
                return true;
            }
            else { //not a king: fire can only move up
                if (selectedPiece.isFire() && y == selectedY+2) {
                    return true;
                } // water can only move down
                else if (!selectedPiece.isFire() && y == selectedY-2) {
                    return true;
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            selectedPiece = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
            hasSelected = true;
        }
        else {
            selectedPiece.move(x, y);
            selectedX = x;
            selectedY = y;
            hasMoved = true;
        }
    }

    public void place(Piece p, int x, int y) {
        if (x >= pieces.length || y >= pieces[0].length || x < 0 || y < 0)
            return;
        if (p.isFire())
            numFirePieces++;
        else
            numWaterPieces++;
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (x >= pieces.length || y >= pieces[0].length || x < 0 || y < 0) {
            System.out.println("Out of bounds");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("No piece at " + x + " " + y);
            return null;
        }
        Piece removed = pieces[x][y];
        pieces[x][y] = null;
        if (removed.isFire())
            numFirePieces--;
        else
            numWaterPieces--;
        return removed;
    }

    public boolean canEndTurn() {
        return hasMoved;
    }

    public void endTurn() {
        if (turn == 0)
            turn = 1;
        else
            turn = 0;
        hasSelected = false;
        hasMoved = false;
        hasStepped = false;
        selectedPiece.doneCapturing();
        selectedPiece = null;
    }

    public String winner() {
        if (numFirePieces == 0 && numWaterPieces == 0)
            return "No one";
        else if (numFirePieces == 0)
            return "Water";
        else if (numWaterPieces == 0)
            return "Fire";
        return null;
    }
}