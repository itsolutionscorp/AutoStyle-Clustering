public class Board {
    private Piece[][] pieces;
    private boolean firesTurn;

    private Piece selectedPiece;
    //Stores the coordinates for selectedPiece.
    private int selectX, selectY;
    //Keeps track if the current piece has moved.
    private boolean hasMoved;
    //Keeps tract if the current piece just captured.
    private boolean hasCaptured;

    public Board(boolean shouldBeEmpty) {
       // Dim of board.
        int N = 8;
        firesTurn = true;

        //StdDrawPlus.setXscale(0, N);
        //StdDrawPlus.setYscale(0, N);
        
        pieces = new Piece[N][N];
        if (shouldBeEmpty) {
        }
        else {
            setUpPieces();
        }
    }
    
    /** Given a coordinate, determines if it is out of bounds of the
     * checkerboard.
     */
    private boolean outOfBounds(int x, int y) {
        return x > 7 || x < 0 || y > 7 || y < 0;
    }

    public Piece pieceAt(int x, int y) {
        if (outOfBounds(x,y)) {
            //Out of bounds, should return null.
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (outOfBounds(x,y)) {
            return false;
        }
        if (pieceAt(x,y) != null) {
            if (selectedPiece == null || !hasMoved) {
                if (pieceAt(x,y).isFire() == firesTurn) {
                    selectedPiece = pieceAt(x,y);
                    selectX = x;
                    selectY = y;
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            if (selectedPiece != null && !hasMoved) {
                if (validMove(selectX, selectY, x, y)) {
                    return true;
                }
            }
            else if (selectedPiece != null && hasCaptured) {
                if (validMove(selectX, selectY, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Calculates a component of a jump.
     * Does not check if the jump is actually valid.
     */
    private int jumpLength(int ai, int af) {
        return af - ai;
    }

    /** Helper function to determine if a number is equal to the positive
     * or negative of another number.
     */
    private boolean posOrNeg(int a, int b) {
        return a == b || a == -b;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieceAt(xf, yf) != null) {
            return false;
        }

        int sign = 1;
        if (!selectedPiece.isFire()) {
            sign = -1;
        }

        int dx = jumpLength(xi, xf);
        int dy = jumpLength(yi, yf);
        //Move up left or right.
        if (dy == 1*sign && posOrNeg(dx, 1) && !hasMoved) {
            return true;
        }
        if (dy == 2*sign && dx == 2) {
            if (hasMoved && !hasCaptured) {
                return false;
            }
            if (pieceAt(xi+1,yi+1*sign).isFire() == selectedPiece.isFire()) {
                return false;
            }
            return true;
        }
        if (dy == 2*sign && dx == -2) {
            if (hasMoved && !hasCaptured) {
                return false;
            }
            if (pieceAt(xi-1,yi+1*sign).isFire() == selectedPiece.isFire()) {
                return false;
            }
            return true;
        }
        if (dy == -1*sign && posOrNeg(dx, 1) 
            && !hasMoved && selectedPiece.isKing()) {  
            return true;
        }
        if (dy == -2*sign && dx == 2 && selectedPiece.isKing()) {
            if (hasMoved && !hasCaptured) {
                return false;
            }
            if (pieceAt(xi+1, yi-1*sign).isFire() == selectedPiece.isKing()) {
                return false;
            }
            return true;
        }
        if (dy == -2*sign && dx == -2 && selectedPiece.isKing()) {
            if (hasMoved && !hasCaptured) {
                return false;
            }
            if (pieceAt(xi-1, yi-1*sign).isFire() == selectedPiece.isKing()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void select(int x, int y) {
        Piece p = pieceAt(x,y);
        if (p == null) {
            if (!hasMoved || hasCaptured) {
                if (selectedPiece != null) {
                    selectedPiece.move(x,y);
                    selectX = x;
                    selectY = y;
                    hasCaptured = selectedPiece.hasCaptured();
                    hasMoved = true;
                }
            }
        }
        else {
            if (!hasMoved) {
                if (pieceAt(x,y).isFire() == firesTurn) {
                    selectedPiece = p;
                    selectX = x;
                    selectY = y;
                }
            }
        }
    }

    public void place(Piece p, int x, int y) {
        if (p == null) {
            // If p is null, don't do anything.
            return;
        }
        if (outOfBounds(x,y)) {
            return;
        }
        pieces[x][y] = p; 
    }

    public Piece remove(int x, int y) {
        if (outOfBounds(x,y)) {
            StdOut.println("Out of bounds.");
            return null;
        }
        if (pieces[x][y] == null) {
            StdOut.println("No piece to remove");
            return null;
        }
        Piece p = pieces[x][y];
        pieces[x][y] = null;
        return p;
    }

    public boolean canEndTurn() {
        if (hasMoved || hasCaptured) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        firesTurn = !firesTurn;
        selectedPiece = null;
        hasMoved = false;
        hasCaptured = false;
    }

    /**Counts the number of pieces on the board of a given side.
     * @param fireOrWater true for counting fire, false for counting water.
     */
    private int countSide(boolean fireOrWater) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] == null) {
                }
                else if (pieces[i][j].isFire() == fireOrWater) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public String winner() {
        int fireCount = countSide(true);
        int waterCount = countSide(false);
        if (fireCount == 0 && waterCount == 0) {
            return "No one";
        }
        if (fireCount == 0) {
            return "Water";
        }
        else if (waterCount == 0) {
            return "Fire";
        }
        return null;
    }
    
    /** Returns string interpetation of a pieces type */
    private String getType(Piece p) {
        if (p.isBomb()) {
            return "bomb";
        }
        else if (p.isShield()) {
            return "shield";
        }
        else {
            return "pawn";
        }
    }

    /** Returns string interpretation of a pieces side */
    private String getSide(Piece p) {
        if (p.isFire()) {
            return "fire";
        }
        else {
            return "water";
        }
    }
    
    /** Returns string interpretation of whether a piece is
     * crowned or not.
     */
    private String getCrown(Piece p) {
        if (p.isKing()) {
            return "-crowned";
        }
        return "";

    }

    /** Helper function for drawBoard, draws a piece if one exists
     * at x,y
     */
    private void drawPiece(int x, int y) {
        Piece p = pieceAt(x,y);
        if (p != null) {
            String type = getType(p);
            String side = getSide(p);
            String crown = getCrown(p);
            StdDrawPlus.picture(x + .5, y + .5, 
            "img/" + type + "-" + side + crown + ".png",
            1, 1);
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawPiece(i,j);
            }
        }
    }

    /** Helper to setUpPieces, inserts a row of pieces.
     * @param offset index of column we start at.
     * @param row index of row used to start at.
     * @param side which side should the piece be on?
     * @param type what kind of piece it should be
     */
    private void addToRow(int offset, int row, boolean side, String type) {
        for (int i = offset; i < 8; i += 2) {
            Piece p = new Piece(side, this, i, row, type);
            place(p, i, row);
        }
    }

    private void setUpPieces() {
        //Fire side
        addToRow(0, 0, true, "pawn");
        addToRow(1, 1, true, "shield");
        addToRow(0, 2, true, "bomb");
        //Water side
        addToRow(1, 5, false, "bomb");
        addToRow(0, 6, false, "shield");
        addToRow(1, 7, false, "pawn");
    }

    private void handleMousePress() {
        if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            if (canSelect((int) x, (int) y)) {
                select((int) x, (int) y);
            }
        }
    }

    private void handleKeyPress() {
        if (StdDrawPlus.isSpacePressed()) {
            if (canEndTurn()) {
                endTurn();
            }
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while (b.winner() == null) {
            b.drawBoard(8);
            b.handleMousePress();
            b.handleKeyPress();
            StdDrawPlus.show(100);
        }
    }
}
