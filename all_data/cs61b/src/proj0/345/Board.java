
public class Board {
    //- starts a GUI supported version of the game.
    public boolean shouldBeEmpty;
    private Piece[][] pieces;
    //These variables control a turn and are reset each time a turn occurs:
    private Piece selected;
    private int player;
    private boolean hasmoved;
    

    public static void main(String[] args) {
        Board bee = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        
        while(bee.winner() == null) {
            bee.drawBoard(N);
           if (StdDrawPlus.mousePressed()) {
             double x = StdDrawPlus.mouseX();
              double y = StdDrawPlus.mouseY();
           if (bee.canSelect((int) x,(int) y)) {
               bee.select((int) x, (int) y);
           }
              }
           if (StdDrawPlus.isSpacePressed() && bee.canEndTurn()) {
               bee.endTurn();
           }
            StdDrawPlus.show(50);
           }
System.out.println(bee.winner());
}
    
    //The constructor for Board. 
    public Board(boolean shouldBeEmpty) {
    /*If shouldBeEmpty is true, initializes an empty Board. 
     * Otherwise, initializes a Board with the default configuration. 
     * Note that an empty Board could possibly be useful for testing purposes.*/
    this.shouldBeEmpty = shouldBeEmpty;
    this.player = 0;
    this.hasmoved = false;
    this.selected = null;
    pieces = new Piece[8][8];
    if (!shouldBeEmpty) {
    String[] typesychord = {"pawn", "shield", "bomb", null, null, "bomb", "shield", "pawn"};
    boolean[] isFireychord = {true, true, true, false, false, false, false, false};
    for (int y = 0; (y<8); y+= 1) {
        for (int x = 0; (x<8); x+=1) {
            if ((x%2 == 0) && (y%2 == 0) && (y != 3) && (y != 4)) {
            pieces[x][y] = new Piece(isFireychord[y], this, x, y, typesychord[y]);
        } else {
            if ((x%2 != 0) && (y%2 != 0) && (y != 3) && (y != 4)) {
                pieces[x][y] = new Piece(isFireychord[y], this, x, y, typesychord[y]);
            }
            }
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
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (selected != null) {
                    if ((i == selected.x) && (j == selected.y)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                    }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (this.pieces[i][j] != null) {
                    Piece current = this.pieces[i][j];
                    String clan = "water";
                    if (current.isFire()) {
                        /*makes a string of the team for the picture selection*/
                        clan = "fire";
                    }
                    if (current.isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + current.type + "-" + clan + "-crowned.png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + current.type + "-" + clan + ".png", 1, 1);
                    }
                }
            }
        }
    }
    
    /*- Gets the piece at position (x, y) on the board, or null if there is no piece. 
     * If (x, y) are out of bounds, returns null.*/
    public Piece pieceAt(int x, int y) {
        if ((x >= 8) || (y >= 8) || (y < 0) || (x < 0)) {
            return null;
        } else {
        return pieces[x][y];
        }
    }
    /*- Returns true if the square at (x, y) can be selected. 
     * A square with a piece may be selected if it is the corresponding players turn 
     * and one of the following is true:

        The player has not selected a piece yet.
        The player has selected a piece, but did not move it.
        An empty square may be selected if one of the following is true:

    During this turn, the player has selected a Piece which hasnt moved yet
    and is selecting an empty spot which is a valid move for the previously selected Piece.
    During this turn, the player has selected a Piece, captured, 
    and has selected another valid capture destination. 
    When performing multi-captures, you should only select the active piece once; 
    all other selections should be valid destination points.*/
    public boolean canSelect(int x, int y) {
        System.out.println("canSelect called");
        Piece query = pieceAt(x,y);
        //Checks if the space clicked is a dead square
        if (!isGoodSquare(x, y)) {
            System.out.println("badsquare");
            return false;
        }
        //Checks if the space clicked has a piece, and if it does if it's the players piece.
        if ((query != null) && (query.side() - player == 0) && (hasmoved != true)) {
            return true;
        } 
        //If the space is empty and a piece is selected and the player has not moved returns true
        if ((query == null) && (selected != null) && (validmove(selected, x, y))) {
            //If the player hasn't moved:
            if (hasmoved != true) {
                return true;
            }
            //If the selected piece can capture returns true
            if ((selected.hasCaptured) && (validmove(selected, x, y))) {
                return true;
            }
    }
        System.out.println("default false canselect");
    return false;    
    }
    private boolean validmove(Piece query, int x, int y) {
        int dx = x-query.x;
        int dy = y-query.y;
        int absx = Math.abs(dx);
        int absy = Math.abs(dy);
        //Always move diagonally;
        if ((dx == 0) || (dy == 0)) {
            System.out.println("Not diag");
            return false;
        }
        //Don't land on someone's head;
        if (pieceAt(x,y) != null) {
            System.out.println("Piece there");
            return false;
        }
        //Valid movement coords required;
        if (((absx != 1) && (absy != 1)) && ((absx != 2) && (absy != 2))) {
           System.out.println("Not 1 2");
           return false;
        }
       if (selected.hasCaptured() && validcapture(selected, dx, dy)) {
           return true;
       }
        //}
        //If the query isn't a king don't let it move backwards;
        if (!query.isKing()) {
            if ((query.isFire()) && (dy < 1)) {
                System.out.println("Fire badmove");
                return false;
            }
            if ((!query.isFire()) && (dy > 0)) {
                System.out.println("water badmove");
                return false;
            }
        }
        //Don't capture-move if the query isn't capturing;
        if ((absx == 2) && (absy == 2) && (!validcapture(query, dx, dy))) {
            System.out.println("Not capture");
            return false;
        }
        return true;
    }
    
    private boolean isGoodSquare(int x, int y) {
        if (even(x) && even(y)) {
            return true;
        } else {
            if (!even(x) && !even(y))  {
                return true;
            }
        }
        return false;
    }
    
    private boolean even(int x) {
        if (x%2 == 0) {
            return true;
        }
        return false;
    }
    
    private boolean validcapture(Piece query, int dx, int dy) {
        int victimx = query.x + dx/2;
        int victimy = query.y + dy/2;
        if ((victimx >= 8) || (victimx < 0) || (victimy >= 8) || (victimy < 0)) {
            System.out.println("out of bounds victim");
            return false;
        }
        Piece victim = pieceAt(victimx, victimy);
        //The piece must capture
        if (victim == null) {
            return false;
        };
        //The piece must capture and enemy:
        if (victim.side()-query.side() == 0) {
            return false;
        }
        return true;
    }
    /*- Selects the square at (x, y). This method assumes canSelect (x,y) returns true. 
     * Optionally, it is recommended to color the background of the selected square white
     *  on the GUI via the pen color function. 
     *  
     *  For any piece to perform a capture, that piece must have been selected first. 
     *  If you select a square with a piece, you are prepping that piece for movement. 
     *  If you select an empty square (assuming canSelect returns true), 
     *  you should move your most recently selected piece to that square.
     */
    public void select(int x, int y) {
        System.out.println("Select called");
        if (pieceAt(x,y) != null) {
            selected = pieceAt(x, y);
        }
        if ((pieceAt(x, y) == null) && (selected != null)) {
            selected.move(x, y);
            hasmoved = true;
        }
        
    }
    /*Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
     * If another piece already exists at (x, y), p will replace that piece. 
     * (This method is potentially useful for creating specific test circumstances.)
     */
    public void place(Piece p, int x, int y) {
        if ((x < 8) && (x >= 0) && (p != null) && (y < 8) && (y >= 0)) {
            p.x = x;
            p.y = y;
            pieces[x][y] = p;
        }
    }
    /*Executes a remove. Returns the piece that was removed. 
     * If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
     * If there is no piece at (x, y), returns null and prints an appropriate message.
     */
    public Piece remove(int x, int y) {
        Piece query = pieceAt(x, y);
        pieces[x][y] = null;
        return query; 
    }
    /*Returns whether or not the the current player can end their turn. 
     * To be able to end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn() {
        if (hasmoved) {
        return true;
        }
        return false;
    }
    /* - Called at the end of each turn. Handles switching of players and anything else 
     * that should happen at the end of a turn.*/
    public void endTurn() {
        if (selected != null) {
        selected.doneCapturing();
        selected = null;
        }
        player = 1 - player;
        hasmoved = false;
    }
    /* Returns the winner of the game: "Fire", "Water", "No one", or null. 
     * If only fire pieces remain on the board, fire wins. 
     * If only water pieces remain, water wins. 
     * If no pieces remain (consider an explosion capture), no one wins. 
     * If the game is still in progress (ie there are pieces from both sides still on the 
     * board) return null. Assume there is no stalemate situation in which the current 
     * player has pieces but cannot legally move any of them 
     * (In this event, just leave it at null). Determine the winner 
     * solely by the number of pieces belonging to each team.
     */
    public String winner() {
        boolean firesum = false;
        boolean watersum = false;
        for (Piece[] piece : pieces) {
            for (Piece pice : piece) {
                if (pice != null) {
                if (pice.isFire()) {
                    firesum = true;
                } else {
                    watersum = true;
                }
            }
        }
    }
        if (!watersum && firesum) {
            return "Fire";
        }
        if (!firesum && watersum) {
            return "Water";
        }
        if (!firesum && !watersum) {
                return "No one";
            }
      return null;
        }
    }
    