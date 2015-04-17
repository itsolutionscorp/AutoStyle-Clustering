public class Board {

    private Piece pieces[][]; // piece list
    private Piece selectedPiece; //
    private int row;
    private int col;
    private String s = null; //
    private boolean activePiece; // piece that has captured another becomes the active piece
    private static boolean ending; // dictates if turn can be ended
    private int turn; // takes only the values of 0 and 1
                      // dictates fire turn or water turn

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8]; // initialize list
        ending = false; // you can't end turn yet
        turn = 0; // because we start off with fire turn
        if(!shouldBeEmpty) { // if not false
            builder(); // build pieces list
        }
    }

    // create a list of all the piece and their positions
    private void builder() {
        for (int i = 0; i < 8; i += 2) {
            pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
            pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
            pieces[i][6] = new Piece(false, this, i, 6, "shield");
            pieces[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
        }
    }

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (selectedPiece != null && i == row && j == col) { // if selected, and we get the its position
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece a = pieces[i][j];
                String z;
                if (a != null){ // if piece exist
                    if (a.isBomb()) {
                        z = "bomb";
                    }
                    else if (a.isShield()) {
                        z = "shield";
                    }
                    else {
                        z = "pawn";
                    }
                    z = getDirectory(a, z); // get the directory so that we can draw it
                    StdDrawPlus.picture(i + .5, j + .5, z, 1, 1);
                }
            }
        }
    }


    // helper function to get directory
    private String getDirectory(Piece p, String type) {
        String z = new StringBuilder().append("img/").append(type).toString();
        if (p.side() == 0)
            z = new StringBuilder().append(z).append("-fire").toString();
        else
            z = new StringBuilder().append(z).append("-water").toString();
        if (p.isKing())
            z = new StringBuilder().append(z).append("-crowned").toString();
        z = new StringBuilder().append(z).append(".png").toString();
        return z;
    }

    // // gets piece position, return null if no piece or out of bounds
    public Piece pieceAt(int x, int y){
        if (outOfBounds(x,y)) {
            return null;
        }
        else {
            return pieces[x][y];
        }
    }

    // checks if out of bounds
    private boolean outOfBounds(int x, int y) {
        // return false if any of the following conditions are met
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return true;
        }
        else {
            return false;
        }
    }



    private boolean validMove(int i, int j, int x, int y) {
        if (outOfBounds(x, y) || pieceAt(x, y) != null) {
            return false;
        }
        Piece a = pieceAt(i, j); // to reference your selected piece
        boolean crowned = a.isKing();
        int k = Math.abs(x - i); // check the step count for xPos
        int l = y - j; // chec
        int lAbs = Math.abs(l);
        if (k == 1 && !a.hasCaptured()) { // if its a regular move and yet captured
            if(crowned)
                return (lAbs == 1); // column difference can only be 1 in any direction
            if (a.isFire())
                return (l == 1); // move up the board
            else
                return (l == -1); // move down the board
        }
        if (k == 2) { // if its a jump

            if (lAbs != 2) { // this direction also needs to be two, otherwise its not a jump
               return false;
            }
            int x1 = (x + i) / 2; // position of the jumped piece
            int y1 = (y + j) / 2; // position of the jumped piece
            Piece a1 = pieceAt(x1, y1);
            if (a1 == null || a1.side() == a.side()) { // if piece doesn't exist or same side
                return false;
            }
            if (crowned) {// if crowned, you can move any which way
                return true; // lAbs == 2 already tested for above
            }
            if (a.isFire()) {
                return l == 2; // move two spaces up the board
            }
            else {
                return l == -2; // move two spaces down the board
            }
        }
        else {
            return false; // all other cases aren't valid moves
        }
    }


    // NEEDS WORK
    // check if you can position to move to
    public boolean canSelect(int x, int y) {
        if (outOfBounds(x,y)) {
            return false;
        }
        Piece a = pieceAt(x,y); // reference selected piece
        if (selectedPiece == null) { // if have yet to select
            // selected piece is a piece and is the correct turn for player
            return a != null && a.side() == turn;
        }
        if (a == null) { // if no pieces exist in this position
            // if you are the not the active piece or captured a piece
            // and it is a valid move
            return (!activePiece || selectedPiece.hasCaptured()) && validMove(row, col, x, y);
        }
        else {
            // if piece isn't null
            // return active piece and the side
            // this is seeing if you can select the piece for movement
            return !activePiece && a.side() == turn;
        }
    }

    // // select the square
    public void select(int x, int y) {
        if (pieceAt(x,y) == null) { // if empty space
            if (canSelect(x,y)){
                selectedPiece.move(x,y); // move piece
                activePiece = true; // piece is now the active piece
                ending = true; // you can end if you want
            }
        }
        selectedPiece = pieceAt(x, y);
        row = x; // update the current position of the peice after the move
        col = y; // update the current position of the piece after the move
        winner(); // will check for winner
    }

    // method to place the piece
    public void place(Piece p, int x, int y){
        pieces[x][y] = p;
    }

    // removes a piece
    public Piece remove(int x, int y) {
        if (outOfBounds(x,y)) { // check if out of bounds
            return null;
        }
        Piece a = pieces[x][y];
        if (a == null) { // check if piece exists in location
            return null;
        }

        else {
            pieces[x][y] = null; // remove piece from list
            return a; // return the piece
        }

    }

    // // checks if turn can be ended
    public boolean canEndTurn() {
        return ending;
    }



    // // switches player turns
    public void endTurn() {
        turn = (turn + 1) % 2; // update turn
        activePiece = false; // reset active
        ending = false; // reset ending
        if (selectedPiece != null) {
            selectedPiece.doneCapturing(); // finish capture
            selectedPiece = null; // reset selected piece
        }
    }


    // check who wins
    private void checkGameInProgress() {
        boolean flag = true;
        boolean flag2 = false;
        int nullFlag = 0; // check for null
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null){
                    flag = flag && pieces[i][j].isFire(); // if water exist, flag = false
                                                          // if only fire, flag = true
                    flag2 = flag2 || pieces[i][j].isFire(); // if fire exist, flag2 = true
                                                            // if only water, flag2 = false
                    nullFlag += 1; // > 0 if there is still a piece
                }

            }
        }
        if (nullFlag == 0)
            s = "No one";
        else if (flag && flag2)
            s = "Fire";
        else if (!flag2 && !flag)
            s = "Water";
        else
            s = null;
    }

    // // return results of game
    public String winner() {
        checkGameInProgress();
        return s;
    }

    // main method to run the GUI
    public static void main(String args[]) {
        Board b = new Board(false); // initialize board
        StdDrawPlus.setXscale(0, 8); // set scale
        StdDrawPlus.setYscale(0, 8); // set scale
        while(true) {
            b.drawBoard(); // draw board
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX(); //turn mouseclicks into integers
                int y = (int)StdDrawPlus.mouseY(); //turn mouseclicks into integers
                if (b.canSelect(x,y)) { //if you can select
                    b.select(x,y); // do it
                }
            }
            if (ending && StdDrawPlus.isSpacePressed()) { // if you choose to end turn
                b.endTurn(); // carry out endTurn
            }
            StdDrawPlus.show(10);
        }
    }

}