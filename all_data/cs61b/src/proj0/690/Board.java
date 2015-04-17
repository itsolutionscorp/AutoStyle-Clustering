public class Board {

    /** Location of pieces. */

    private Piece[][] pieces;
    private static int N = 8;
    private boolean isFireTurn;
    private int selectX;
    private int selectY;
    private boolean hasSelected;
    private boolean hasMoved;
    private int capturedFire;           //captured fire pieces
    private int capturedWater;          //captured water pieces

    // private Piece[][] pieces;
    // private static int N = 8;
    // public boolean isFireTurn;
    // public int selectX;
    // public int selectY;
    // public boolean hasSelected;
    // public boolean hasMoved;
    // public int capturedFire;           //captured fire pieces
    // public int capturedWater;          //captured water pieces

    public static void main(String[] args) {
        /* starts GUI supporterd version of game */

        Board b = new Board(false);
        boolean runGUI = true;

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        b.drawBoard(N);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
                if (b.winner() != null) {
                    System.out.println(b.winner());
                }
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }
            // pieces[(int) x][(int) y] = ;
            StdDrawPlus.show(100);
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(selectX + .5, selectY + .5, .5);
                Piece temp = pieces[i][j];
                if (temp != null) {
                    if (temp.isFire()) {
                        if (temp.isBomb()) {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);    
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        }
                        else if (temp.isShield()) {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);    
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);   
                            }
                        }
                        else {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);    
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else {
                        if (temp.isBomb()) {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);    
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                        else if (temp.isShield()) {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);    
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                        else {
                            if (temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);    
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
        // StdDrawPlus std = new StdDrawPlus();
        // draw.addMouseListener(std);
    }

    public Board(boolean shouldBeEmpty) {
        /* creates empty board if bool is true
        else creates default board
        */
        pieces = new Piece[N][N];
        isFireTurn = true;
        // selectX = 0;
        // selectY = 0;
        hasSelected = false;
        hasMoved = false;
        capturedFire = 0;
        capturedWater = 0;

        if (shouldBeEmpty) {
            return;
        }
        for (int i = 0; i < N; i += 2) {
            pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            pieces[i][6] = new Piece(false, this, i, 6, "shield");
        }

        for (int i = 1; i < N; i += 2) {
            pieces[i][1] = new Piece(true, this, i, 1, "shield");
            pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            pieces[i][7] = new Piece(false, this, i, 7, "pawn");
        }
    }

    public Piece pieceAt(int x, int y) {
        //  gets piece at (x, y)
        // return null if no piece or (x, y) is out of bounds
        if (pieces[x][y] == null || x >= N || y >= N || x < 0 || y < 0) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        /* returns true if piece can be selected
        square with piece can be selected if it's their turn and:
            — player hasn't selected piece yet
            — OR player selected piece but didn't move it
        empty square can be selected if:
            - during this turn, player selected piece and hasn't moved
            yet and selected empty spot is a valid move for that piece
            - OR during this turn, player selected piece, captured,
            selected valid capture destination
            (for multi-captures, select active piece once and all other
            selections are for valid destination points)

        use "boolean validMove(int xi, int yi, int xf, int yf)"
        */
        
        if (pieceAt(x, y) != null) {                                    //to select a square with a piece
            if ((hasSelected == false) || (hasMoved == false)) {        //either hasn't selected or hasn't moved
                if ((isFireTurn) && (pieceAt(x, y).isFire())) {         //if fire turn, select fire piece
                    return true;
                }
                else if ((!isFireTurn) && (!pieceAt(x, y).isFire())) {  //if water turn, select water piece
                    return true;
                }
            }
        }
        else {     //to select an empty square
            if (pieceAt(selectX, selectY) == null) {
                return false;
            }
            else if ((hasSelected == true) && (hasMoved == false) && (validMove(selectX, selectY, x, y))) { //has selected and about to move/capture
                return true;
            }
            else if ((hasSelected == true) && (hasMoved == true) && (pieceAt(selectX, selectY).hasCaptured())
                     && (validMove(selectX, selectY, x, y))) { //has captured and about to capture again
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        /* returns true if (xi, yi) can move to (xf, yf) or capture to (xf, yf),
        strictly from geometry/piece-race point of view
        can take into consideration whose turn and if move has already been made
        */
        if ((xf < N) && (yf < N) && (xf >= 0) && (yf >= 0)) {                                       //checks dest is in bounds
            if ((xf % 2 == yf % 2) && (pieceAt(xi, yi) != null)) {                                  //checks diagonal movement and there exists a piece
                if (!pieceAt(xi, yi).hasCaptured()) {
                if ((xf == xi - 1) || (xf == xi + 1)) {                                             //plain move must be 1 left/right
                    if (!pieceAt(xi, yi).isKing()) {                                                //not king
                        if ((isFireTurn) && (yf == yi + 1) && (pieceAt(xf, yf) == null)) {          //fire must move up 1
                            return true;
                        }
                        else if ((!isFireTurn) && (yf == yi - 1) && (pieceAt(xf, yf) == null)) {    //water must move down 1
                            return true;
                        }
                    }
                    else if (pieceAt(xi, yi).isKing()) {                                            //is king
                        if ((yf == yi + 1) || (yf == yi - 1) && (pieceAt(xf, yf) == null)) {        //fire/water must move up/down 1
                            return true;
                        }
                    }
                }
                }
                if ((xf == xi - 2) || (xf == xi + 2)) {                                             //capture move must be 2 left/right
                    if (isFireTurn) {                                                               //fire turn
                        if (yf == yi + 2) {                                                         //fire moving up (may/maynot be king)
                            if ((xf == xi + 2) && (!pieceAt(xi+1, yi+1).isFire())) {                //go right, check captured is water
                                return true;
                            }
                            else if ((xf == xi - 2) && (!pieceAt(xi-1, yi+1).isFire())) {           //go left, check captured is water
                                return true;
                            }
                        }
                        else if ((yf == yi - 2) && (pieceAt(xi, yi).isKing())) {                    //fire moving down (must be king)
                            if ((xf == xi + 2) && (!pieceAt(xi+1, yi-1).isFire())) {                //go right, check captured is water
                                return true;
                            }
                            else if ((xf == xi - 2) && (!pieceAt(xi-1, yi-1).isFire())) {           //go left, check captured is water
                                return true;
                            }
                        }
                    }
                    else if (!isFireTurn) {                                                        //water turn
                        if (yf == yi - 2) {                                                         //water moving down (may/maynot be king)
                            if ((xf == xi + 2) && (pieceAt(xi+1, yi-1).isFire())) {                 //go right, check captured is fire
                                return true;
                            }
                            else if ((xf == xi - 2) && (pieceAt(xi-1, yi-1).isFire())) {            //go left, check captured is fire
                                return true;
                            }
                        }
                        else if ((yf == yi + 2) && (pieceAt(xi, yi).isKing())) {                    //water moving up (must be king)
                            if ((xf == xi + 2) && (pieceAt(xi+1, yi+1).isFire())) {                 //go right, check captured is fire
                                return true;
                            }
                            else if ((xf == xi - 2) && (pieceAt(xi-1, yi+1).isFire())) {            //go left, check captured is fire
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        //  selects square at (x, y) if possible
        // optional: color selected piece bkgd white
        if (pieceAt(x, y) != null) {                                    //select a piece to move
            selectX = x;
            selectY = y;
            hasSelected = true;
        }
        else if (pieceAt(x, y) == null) {                               //select square to move to
            pieceAt(selectX, selectY).move(x, y);
            selectX = x;
            selectY = y;
            hasMoved = true;
            // if (!pieceAt(selectX, selectY).hasCaptured()) {
            //     endTurn();
            // }
        }
    }

    public void place(Piece p, int x, int y) {
        /* places p at (x, y)
        if (x, y) out of bounds, does nothing
        ??? if another piece already at (x, y), p replaces that piece
        */
        if (x < N && y < N && x >= 0 && y >= 0 && (p != null)) {
            pieces[x][y] = null;
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        /* executes a remove and returns removed piece
        if (x, y) out of bounds, returns null and prints a message
        if no piece at (x, y) returns null and prints a message
        */
        if (x >= N || y >= N || x < 0 || y < 0) {
            System.out.println("Coordinates are out of bounds");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("No piece at these coordinates");
            return null;
        }
        Piece temp = pieces[x][y];
        if (pieces[x][y].isFire()) {
            capturedFire += 1;
        }
        else if (!pieces[x][y].isFire()) {
            capturedWater += 1;
        }
        pieces[x][y] = null;
        return temp;
    } 

    public boolean canEndTurn() {
        /* returns whether current player can end their turn
        to be able to end turn, piece must have moved or performed capture
        */
        if (hasMoved) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        /* called at end of each turn
        handles switching players and anything else that should happen at
        end of each turn
        */
        if (canEndTurn()) {
            //reset all determining variables
            if (winner() == null) {
                hasSelected = false;
                if (pieceAt(selectX, selectY) != null) {
                    pieceAt(selectX, selectY).doneCapturing();
                }
                selectX = 0;
                selectY = 0;
                hasMoved = false;
                isFireTurn = (!isFireTurn);
            }
        }
    }

    public String winner() {
        /* returns winner of game: "Fire", "Water", "No one" (tie/no pieces on board),
        or null if game is not yet over
        determine winner solely by the number of pieces belonging to each team
        */
        int fireLeft = 0;
        int waterLeft = 0;
        for (int x = 0; x < N; x += 1) {
            for (int y = 0; y < N; y += 1) {
                if (pieceAt(x, y) != null) {
                    if (pieceAt(x, y).isFire()) {
                        fireLeft += 1;
                    }
                    else if (!pieceAt(x, y).isFire()) {
                        waterLeft += 1;
                    }
                }
            }
        }
        // if ((capturedFire != 0) || (capturedWater != 0)) {
            if ((fireLeft == 0) && (waterLeft == 0)) {
                return "No one";
            }
            else if (fireLeft == 0) {
                return "Water";
            }
            else if (waterLeft == 0) {
                return "Fire";
            }
        // }
        return null;
    }
}