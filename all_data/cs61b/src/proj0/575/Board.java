
public class Board {

    private Piece[][] pieces;

    private int whoseTurn;
    private boolean hasSelected;
    private boolean hasMoved;

    private Piece selectedPiece;
    private int xLoc;
    private int yLoc;

    private boolean[][] selectedTiles;

	public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];

        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
        pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");

        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
        pieces[7][1] = new Piece(true, this, 7, 1, "shield");

        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
        pieces[6][6] = new Piece(false, this, 6, 6, "shield");

        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

        if (shouldBeEmpty) {
            pieces = new Piece[8][8];
        }

        whoseTurn = 0;
        hasSelected = false;
        hasMoved = false;
        selectedPiece = null;
        selectedTiles = new boolean[8][8];
	}

    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            return null;
        }
        return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            return;
        }
        if (p == null) {
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        Piece pointer;
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            System.out.println("Index out of bounds");
            return null;
        }
        if (pieces[x][y] != null) {
            pointer = pieces[x][y];
            pieces[x][y] = null;
            return pointer;
        }
        else {
            System.out.println("There wasn't anything there.");
            return null;
        }
    } 

    public String winner() {
        boolean existenceOfFire = false;
        boolean existenceOfWater = false;

        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        existenceOfFire = true;
                    }
                    else {
                        existenceOfWater = true;
                    }
                }
            }
        }

        if (existenceOfFire && !existenceOfWater) {
            return "Fire";
        }
        else if (existenceOfWater && !existenceOfFire) {
            return "Water";
        }
        else if (!existenceOfFire && !existenceOfWater) {
            return "No one";
        }
        else {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        Piece thePiece = pieceAt(x, y);
        if (thePiece != null && thePiece.side() == whoseTurn) {
            if (!hasSelected || !hasMoved) {
                return true;
            }
        }
        else {
            if (hasSelected && !hasMoved && validMove(xLoc, yLoc, x, y)) {
                return true;
            }
            else if (hasSelected && selectedPiece.hasCaptured() && validMoveToCapture(xLoc, yLoc, x, y)) {
                return true;
            }
        }
        return false;
    }

    private static int difference(int x, int y) {
        if ((x - y) < 0) {
            return (y - x);
        }
        return (x - y);
    }

    private static int average(int x1, int x2) {
        return (int) ((x1 + x2) / 2);
    }

    private boolean canCapture(int xi, int yi, int xf, int yf) {
        return pieceAt(average(xi, xf), average(yi, yf)) != null 
                && pieceAt(average(xi, xf), average(yi, yf)).side() != selectedPiece.side();
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieceAt(xf, yf) != null) {
            return false;
        }
        if (selectedPiece.isKing()) {
            if (difference(xf, xi) == 2 && difference(yf, yi) == 2 
                && canCapture(xi, yi, xf, yf)) {
                return true;
            }
            else if (difference(xf, xi) == 1 && difference(yf, yi) == 1) {
                return true;
            }
        }
        else if (selectedPiece.isFire()) {
            if (difference(xf, xi) == 2 && yf - yi == 2 && canCapture(xi, yi, xf, yf)) {
                return true;
            }
            else if (difference(xf, xi) == 1 && yf - yi == 1) {
                return true;
            }
        }
        else {
            if (difference(xf, xi) == 2 && yi - yf == 2 && canCapture(xi, yi, xf, yf)) {
                return true;
            }
            else if (difference(xf, xi) == 1 && yi - yf == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean validMoveToCapture(int xi, int yi, int xf, int yf) {
        if (pieceAt(xf, yf) != null) {
            return false;
        }
        if (selectedPiece.isKing()) {
            if (difference(xf, xi) == 2 && difference(yf, yi) == 2 
                && canCapture(xi, yi, xf, yf)) {
                return true;
            }
        }
        else if (selectedPiece.isFire()) {
            if (difference(xf, xi) == 2 && yf - yi == 2 && canCapture(xi, yi, xf, yf)) {
                return true;
            }
        }
        else {
            if (difference(xf, xi) == 2 && yi - yf == 2 && canCapture(xi, yi, xf, yf)) {
                return true;
            }
        }
        return false;
    }

    public void select(int x, int y) {
        selectedTiles = new boolean[8][8];
        selectedTiles[x][y] = true;
        if (pieces[x][y] != null) {
            selectedPiece = pieces[x][y];
            xLoc = x;
            yLoc = y;
        }
        else {
            if (selectedPiece.isBomb() && difference(x, xLoc) == 2) {
                selectedTiles = new boolean[8][8];
            }
            selectedPiece.move(x, y);
            hasMoved = true;
            xLoc = x;
            yLoc = y;
            
        }
        hasSelected = true;
    }

    public boolean canEndTurn() {
        if (selectedPiece != null) {
            return hasMoved || selectedPiece.hasCaptured();
        }
        return false;
    }

    public void endTurn() {
        selectedPiece.doneCapturing();
        hasMoved = false;
        selectedTiles = new boolean[8][8];
        selectedPiece = null;
        xLoc = -1;
        yLoc = -1;
        hasSelected = false;
        whoseTurn = 1 - whoseTurn;
    }
    
    private void drawBoard(int N) {
        String source = "";
        Piece thePiece;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selectedTiles[i][j]) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                thePiece = pieces[i][j];
                if (thePiece != null) {
                    source = "img/";
                    if (thePiece.isBomb()) {
                        source = source.concat("bomb");
                    }
                    else if (thePiece.isShield()) {
                        source = source.concat("shield");
                    }
                    else {
                        source = source.concat("pawn");
                    }
                    if (thePiece.isFire()) {
                        source = source.concat("-fire");
                    }
                    else {
                        source = source.concat("-water");
                    }
                    if (thePiece.isKing()) {
                        source = source.concat("-crowned.png");
                    }
                    else {
                        source = source.concat(".png");
                    }
                    StdDrawPlus.picture(i + .5, j + .5, source, 1, 1);
                }
            }
        }
    }

	public static void main(String[] args) {
		int N = 8;
        boolean test = true;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board map = new Board(false);

        while(test) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (map.canSelect((int) x, (int)y)) {
                    map.select((int) x, (int) y);
                }
            }      
            if (StdDrawPlus.isSpacePressed() && map.canEndTurn()) {
                map.endTurn();
            }      

            if (map.winner() != null) {
                map.endTurn();
                test = false;
            }
            map.drawBoard(N);
            StdDrawPlus.show(25);
        }
	}
}
