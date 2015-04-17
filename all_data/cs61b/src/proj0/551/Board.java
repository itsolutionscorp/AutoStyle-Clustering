public class Board {

    private static int N = 8;

    private Piece[][] pieces = new Piece[N][N];
    private boolean empty;

    private boolean fireTurn = true;
    private Piece selected = null;
    private int selectedX = -1;
    private int selectedY = -1;
    private boolean playerMoved = false;
    private boolean selectedWasBomb = false;

    private int numFirePieces;
    private int numWaterPieces;


    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false);                     // initialize default board

        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {          // get player's coordinates
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);        // select desired piece
                }
            }            
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();                       // end turn
                }
            }
            if (b.winner() != null) break;             // end game if there's a winner
            StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
    }


    /** Board constructor */
    public Board(boolean shouldBeEmpty) {
        empty = shouldBeEmpty;
        if (!empty) {                                 // initialize board with pieces
            numFirePieces = (N/2)*3;            
            numWaterPieces = (N/2)*3;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (((i + j) % 2 == 0) && (j<3 || j>(N-4))){
                        if (j == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        } else if (j == 1) {
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        } else if (j == 2) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        } else if (j == (N-3)) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        } else if (j == (N-2)) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        } else if (j == (N-1)) {
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    } else {
                        pieces[i][j] = null;
                    }
                }
            }
        }
        else {
            numFirePieces = 0;
            numWaterPieces = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    pieces[i][j] = null;
                }
            }
        }
    }


    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j]!=null && selected!=null && pieces[i][j]==selected) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j]!=null) {
                    Piece current = pieces[i][j];     // loop through each piece and draw on board

                    if (!current.isShield() && !current.isBomb()) {   // draw pawn
                        if (current.isFire()) {
                            if (current.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        } else {
                            if (current.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    } else if (current.isShield()) {  // draw shield
                        if (current.isFire()) { 
                            if (current.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        } else {
                            if (current.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                    } else if (current.isBomb()) {    // draw bomb
                        if (current.isFire()) {
                            if (current.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        } else {
                            if (current.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

    /** Gets piece at position (x,y) */
    public Piece pieceAt(int x, int y) {
        // coordinates in bounds and there is a piece
        if ((x<N && y<N && x>-1 && y>-1) && pieces[x][y]!=null) {
            return pieces[x][y];
        }
        return null;
    }

    /** Return true if piece at (x,y) can be selected */
    public boolean canSelect(int x, int y) {
        if (x<N && y<N && x>-1 && y>-1) {                  // in bounds of board
            if (pieces[x][y]!=null &&                      // selected place has a piece  
                ((pieces[x][y].isFire() && fireTurn) ||     // piece corresponds with player's side
                (!pieces[x][y].isFire() && !fireTurn))) {
                
                if (!playerMoved) return true;    // player hasn't moved yet, and can select different piece
    
            // there is a piece already selected and empty square is being selected
            } else if ((pieces[x][y]==null) && (selected != null) && pieces[selectedX][selectedY]!=null) {
                if (validMove(selectedX, selectedY, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }
  
    /** Returns true if piece at (xi,yi) can move to (xf,yf) */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (!this.playerMoved) {            // selected piece is unmoved
            return validOneSpaceMove(xi, yi, xf, yf) || validCapture(xi, yi, xf, yf);
        // Multicapture: another valid capture destination is being selected
        } else if (this.playerMoved && selected.hasCaptured()) { 
            return validCapture(xi, yi, xf, yf);
        } else {
            return false;
        }
    }

    /** Returns true if piece can move one space diagonally */
    private boolean validOneSpaceMove(int xi, int yi, int xf, int yf) {
        int diffX = xi-xf;        // if diffX < 0, moving right; if diffX > 0, moving left
        int diffY = yi-yf;        // if diffY < 0, moving up (fire); if diffY > 0, moving down (water)
        
        // can only move 1 space diagonally into an open space
        if ((diffX == 1 || diffX == -1) && (diffY == 1 || diffY == -1)) {
            if (selected.isKing() ||                      // king can move up or down
               (selected.isFire() && diffY == -1) ||      // fire piece moving up 
               (!selected.isFire() && diffY == 1)) {      // water piece moving down
                    return true;
            }
        }
        return false;
    }

    /** Returns true if piece can capture (move two space diagonally) */
    private boolean validCapture(int xi, int yi, int xf, int yf) {
        int diffX = xi-xf;        // if diffX < 0, moving right; if diffX > 0, moving left
        int diffY = yi-yf;        // if diffY < 0, moving up (fire); if diffY > 0, moving down (water)

        if ((diffX == 2 || diffX == -2) && (diffY == 2 || diffY == -2)) {   // jump and move 2 spaces diagonally
            int midX = xi - (diffX/2);        // X coordinate of captured piece
            int midY = yi - (diffY/2);        // Y coordinate of captured piece

            if (pieces[xf][yf]==null && pieces[midX][midY]!=null) {   // piece in between, but not in final position
                Piece middle = pieces[midX][midY];
                // fire piece capturing a water piece, that is moving up and/or is a king
                if ((selected.isFire() && !middle.isFire() && (selected.isKing() || diffY == -2)) ||                  
                   // water piece capturing a fire piece, that is moving down and/or is a king
                   (!selected.isFire() && middle.isFire() && (selected.isKing() || diffY == 2))) {                 
                        if (selected.isBomb()) selectedWasBomb = true;
                        return true;
                }
            }
        }
        return false;
    }


    /** Selects piece at (x,y) */
    public void select(int x, int y) {
        if (pieces[x][y]==null) {          // move if no piece in final coordinates 
            selected.move(x, y);
            playerMoved = true;
        }
        // update fields to reflect selected piece
        selected = pieces[x][y];
        this.selectedX = x;
        this.selectedY = y;
    }

    /** Places piece p at (x,y) */
    public void place(Piece p, int x, int y) {
        if (x<N && y<N && x>-1 && y>-1) {      // in bounds
            pieces[x][y] = p;                  // update piece collection
            if (p.isFire()) numFirePieces++;   // increment num fire pieces
            else numWaterPieces++;             // increment num water pieces
        }
    }

    /** Removes piece and returns it */
    public Piece remove(int x, int y) {
        if (x>(N-1) || y>(N)-1 || x<0 || y<0) {    // cannot remove if coordinates out of bounds
            System.out.println("Input (" + x + ", " + y +") out of bounds.");
            return null;
        }

        Piece delete = pieceAt(x, y);
        if (delete == null) {               // cannot remove if there is no piece
            System.out.println("No piece found at (" + x + ", " + y + ").");
            return null;
        } else {                            // update fields to remove piece from board 
            if (pieces[x][y].isFire()) numFirePieces--;    // decrement num fire pieces
            else numWaterPieces--;                         // decrement num water pieces
            pieces[x][y] = null;
            return delete;
        }
    }

    /** Returns whether current player can end their turn */
    public boolean canEndTurn() {
        // can end turn if a piece has been selected and moved
        if (playerMoved) return true;
        else return false;
    }

    /** At end of each turn, switches players and resets fields */
    public void endTurn() {
        selectedX = -1;
        selectedY = -1;
        playerMoved = false;
        if (!selectedWasBomb && selected!=null) selected.doneCapturing();
        selected = null;
        fireTurn = !fireTurn;
        selectedWasBomb = false;
    }

    /** Returns the winner */
    public String winner() {
        if (numFirePieces==0 && numWaterPieces==0) return "No one";
        else if (numFirePieces == 0) return "Water";
        else if (numWaterPieces == 0) return "Fire";
        else return null;
    }


}
