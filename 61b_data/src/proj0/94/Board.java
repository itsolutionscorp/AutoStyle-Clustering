public class Board {

    private Piece[][] pieces;
    private int turn = 0;
    private Piece selected;
    private int selectedX;
    private int selectedY;
    private boolean hasMoved = false;

    /*
     * The constructor for Board. If shouldBeEmpty is true, initializes an empty
     * Board. Otherwise, initializes a Board with the default configuration.
     * Empty board is handy for testing!
     */
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        
        if (!shouldBeEmpty) {
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if ((i + j) % 2 == 0 && j == 0)
                        place(new Piece(true, this, i, j, "pawn"), i, j);
                    else if ((i + j) % 2 == 0 && j == 1)
                        place(new Piece(true, this, i, j, "shield"), i, j);
                    else if ((i + j) % 2 == 0 && j == 2)
                        place(new Piece(true, this, i, j, "bomb"), i, j);
                    else if ((i + j) % 2 == 0 && j == 5)
                        place(new Piece(false, this, i, j, "bomb"), i, j);
                    else if ((i + j) % 2 == 0 && j == 6)
                        place(new Piece(false, this, i, j, "shield"), i, j);
                    else if ((i + j) % 2 == 0 && j == 7)
                        place(new Piece(false, this, i, j, "pawn"), i, j);
                }
            }
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i == selectedX && j == selectedY && selected()) // Highlights a square if selected
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                Piece current = pieces[i][j];
                if (current != null) {
                    String element = "";
                    if (current.isKing()) 
                        element = "-crowned";
                    if (current.isFire())
                        element = "fire" + element;
                    else
                        element = "water" + element;
                    if (current.isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-" + element + ".png", 1, 1);
                    }
                    else if (current.isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-" + element + ".png", 1, 1);
                    }
                    else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-" + element + ".png", 1, 1);
                    }
                }
            }
        }
    }
        
    


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
     
        while (true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y))
                    b.select(x, y);
            }        
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn())
                    b.endTurn();
            }
            StdDrawPlus.show(50);
        }
    }
    
    /**
     * Gets the piece at location x, y on the board, but null if there is not
     * piece. If out of bounds returns null.
     * @param The x coordinate of the location on the board
     * @param The y coordinate of the location on the board
     * @return The piece at (x, y) if there is, null if otherwise.
     */
    public Piece pieceAt(int x, int y) {
        if (x < 8 && y < 8) {
            if (pieces[x][y] != null) {
                return pieces[x][y];
            }
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        if (occupied(x, y)) {
            if (turn%2 == pieceAt(x, y).side()) {
                if (selected == null)  {
                    selected = pieceAt(x, y);
                    return true;
                } else if (selected() && !hasMoved) {
                    return true;
                }
                return false;
            }
        }
        else {
            if (selected() && !hasMoved && !occupied(x, y) && validMove(selectedX, selectedY, x, y)) {
                return true;
            }
            else if (selected() && selected.hasCaptured() && validMove(selectedX, selectedY, x, y))
                return true;
            return false;
        }
        return false;
    }
    
    /**
     * Checks to see if a movement from initial coordinates (xi, yi) to (xf, yf) is valid
     * @param xi X coordinate of initial position
     * @param yi Y coordinate of initial position
     * @param xf X coordinate of final position
     * @param yf Y coordinate of final position
     * @return Boolean true if the movement is valid, false otherwise
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (selected.isKing()) {
            if ((Math.abs(yf - yi) == 1) && (Math.abs(xf - xi) == 1)) {
                return true;
            }
            else if (yf - yi == 2)  {
                if (xf - xi == 2) {
                    if (occupied(xi + 1, yi + 1) && pieceAt(xi + 1, yi + 1).side() != selected.side())
                        return true;
                }
                else if (xf - xi == -2) {
                    if (occupied(xi - 1, yi + 1) && pieceAt(xi - 1, yi + 1).side() != selected.side())
                        return true;
                }
            }
            else if (yf - yi == -2) {
                if (xf - xi == 2) {
                    if (occupied(xi + 1, yi - 1) && pieceAt(xi + 1, yi - 1).side() != selected.side())
                        return true;
                }
                else if (xf - xi == -2) {
                    if (occupied(xi - 1, yi - 1) && pieceAt(xi - 1, yi - 1).side() != selected.side())
                        return true;
                }
            }
        }
        else if (selected.isFire()) {
            if ((yf - yi == 1) && (Math.abs(xf - xi) == 1)) {
                return true;
            }
            else if (yf - yi == 2) {
                if (xf - xi == 2) {
                    if (occupied(xi + 1, yi + 1) && pieceAt(xi + 1, yi + 1).side() != selected.side())
                        return true;
                }
                else if (xf - xi == -2) {
                    if (occupied(xi - 1, yi + 1) && pieceAt(xi - 1, yi + 1).side() != selected.side())
                        return true;
                }

            }
        }
        else {
            if ((yf - yi == -1) && (Math.abs(xf - xi) == 1)) {
                return true;
            }
            else if (yf - yi == -2) {
                if (xf - xi == 2) {
                    if (occupied(xi + 1, yi - 1) && pieceAt(xi + 1, yi - 1).side() != selected.side())
                        return true;
                }
                else if (xf - xi == -2) {
                    if (occupied(xi - 1, yi - 1) && pieceAt(xi - 1, yi - 1).side() != selected.side())
                        return true;
                }
            }
        }
        return false;
    }


    /**
     * Selects the piece at (x, y) if possible.
     * @param x The row to find the piece
     * @param y The column to to find the piece
     */
    public void select(int x, int y) {
        
        if (occupied(x, y)) {
            selected = pieceAt(x, y); 
            selectedX = x;
            selectedY = y;
        } else {
            selected.move(x, y);
            remove(selectedX, selectedY);
            selectedX = x;
            selectedY = y;
            hasMoved = true;
        }
    }

    /**
     * Places piece p at position (x, y) if position is empty. If (x, y) out of
     * bounds, nothing happens. If another piece exists at (x, y), p will
     * replace it.
     * 
     * @param p The piece to place in position (x, y)
     * @param x The row of the position to place the new piece on
     * @param y The column of the position to place the new piece on
     */
    public void place(Piece p, int x, int y) {
        if (x < 8 && y < 8) {
            pieces[x][y] = p;
        }
    }

    /**
     * Removes the piece at position (x, y) and returns it. If (x, y) is out of
     * bounds, return null and print message. If no piece exists at (x, y),
     * return null and print message.
     * 
     * @param The row of the intended piece to be removed
     * @param The column of the intended piece to be removed
     * @return The removed piece if applicable
     */
    public Piece remove(int x, int y) {
        Piece temp = pieceAt(x, y);
        place(null, x, y);
        return temp;
    }

    /**
     * Returns whether the current player can end their turn, valid only if
     * piece was moved or capture was performed.
     * 
     * @return Whether player can end turn or not
     */
    public boolean canEndTurn() {
        if (selected())
                if (selected.hasCaptured() || hasMoved) {
                    return true;
        }
        return false;
    }

    /**
     * Called at end of each turn and ties up loose ends.
     */
    public void endTurn() {
        turn += 1;
        selected.doneCapturing();
        hasMoved = false;
        selected = null;
    }

    /**
     * *Private Helper* Returns whether or not a given square has a piece.
     * @param x The row of the given position.
     * @param y The column of the given position.
     * @return True if there is a piece, false otherwise.
     */
    private boolean occupied(int x, int y) {
        if (pieceAt(x, y) != null)
            return true;
        return false;
    }
    
    /**
     * *Private Helper* Returns if a piece has been selected or not by checking the selected variable.
     * @ True if there is a piece being selected, false otherwise.
     */
    private boolean selected() {
        return selected != null;
    }
    
    /**
     * Returns the winner of the game be it "Fire", "Water", "No one", or null
     * if game is not over.
     * 
     * @return String of who the winner (or not) is.
     */
    public String winner() {
        int water = 0;
        int fire = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (occupied(i, j)) {
                    if (pieceAt(i, j).isFire())
                        fire++;
                    else
                        water++;
                }
            }
        }
        if (water == 0 && fire == 0)
            return "No one";
        else if (fire == 0)
            return "Water";
        else if (water == 0)
            return "fire";
        else
            return null;
    }
}
