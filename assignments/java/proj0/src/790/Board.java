/** Implement the Board class
*@Author: Lichang Xu
*/
public class Board {
    private Piece[][] array; // Piece array for the board
    private boolean[][] white; //for white square effect
    private boolean player1;  // player1 is fire
    private boolean player2; // player2 is water
    private boolean selected; // track whether a piece/empty square has been selected on the board or not
    private boolean[][] table;// boolean array of the board
    private boolean moved; // track whether a piece has moved or not on the board
    private boolean captured; // track whether the current piece has captured or not
    private int xcor; //current piece's x coordinate
    private int ycor; //current piece's y coordinate
    private Piece currentpiece; // the current piece whose location is xcor, ycor
   
    

    // Constructor of the Board class.
    public Board(boolean shouldBeEmpty) {
        this.array = new Piece[8][8];
        this.table = new boolean[8][8];
        this.white = new boolean[8][8];
        if (shouldBeEmpty == true) {
        }
        else {
            this.setBoard(8);
        }

        player1 = true; // fire starts first
        player2 = false; // water next
        currentpiece = pieceAt(xcor, ycor);
    }

    /** Draws a blank 8 x 8 board.
    */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // draw squares
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (white[i][j]) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (this.pieceAt(i, j) != null && table[i][j] == true) {
                    Piece p = pieceAt(i, j);
                    //normal pieces
                    if (p.isKing() == false) {
                        if (p.isFire()) {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }

                        else if (p.isFire() == false) {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }   
                        }    
                    }
                    //king pieces
                    else {
                        if (p.isFire()) {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }

                        else if (p.isFire() == false) {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }   
                        }    
                    }
                }
            }
        }
    }  
      
    // Implement the initial configuration of the 8 x 8 board (only place pieces without images)
    
    private void setBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {               
                    if (j == 0) {
                        if (i == 0 || i == 2 || i == 4 || i == 6) {
                            Piece p1 = new Piece(true, this, i, j, "pawn");
                            place(p1, i, j);
                        }
                    }

                    else if (j == 7) {
                        if (i == 1 || i == 3 || i == 5 || i == 7) { 
                            Piece p2 = new Piece(false, this, i, j, "pawn");
                            place(p2, i, j);
                        }
                    }

                    else if (j == 1) {
                        if (i == 1 || i == 3 || i == 5 || i == 7) {
                            Piece p3 = new Piece(true, this, i, j, "shield");
                            place(p3, i, j);
                        }
                    }

                    else if (j == 2) {
                        if (i == 0 || i == 2 || i == 4 || i == 6) {
                            Piece p4 = new Piece(true, this, i, j, "bomb");
                            place(p4, i, j);   
                        }
                    }


                    else if (j == 5) {
                        if (i == 1 || i == 3 || i == 5 || i == 7) {
                            Piece p5 = new Piece(false, this, i, j, "bomb");
                            place(p5, i, j);
                        }
                    }

                    else if (j == 6) {
                        if (i == 0 || i == 2 || i == 4 || i == 6) {
                            Piece p6 = new Piece(false, this, i, j, "shield");
                            place(p6, i, j);
                        }
                    }
                }
            }
        }          
            
        



    /** Gets the piece at position (x, y) on the board, or null if there is no piece. 
    *If (x, y) are out of bounds, returns null. 
    */
    /** OKAY NOW!!! 
    */

    public Piece pieceAt(int x, int y){
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        }
        return array[x][y];
    }


    /**  Returns true if the square at (x, y) can be selected.
    *A square with a piece may be selected if it is the corresponding player’s turn and one of the following is true:
    1.The player has not selected a piece yet.
    2.The player has selected a piece, but did not move it.

    *An empty square may be selected if one of the following is true:
    1.During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
    2.During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.
    */

    /** OKAY NOW!!! 
    */

    public boolean canSelect(int x, int y) {
        Piece piece = this.pieceAt(x, y);
        if (piece != null && player1 == piece.isFire()) {
            if (currentpiece == null || !moved) {
                return true;
            } // empty square
        } else {
            if (currentpiece!= null && !moved && validMove(xcor, ycor, x, y)) {
                return true;
            }
            if (currentpiece!= null && currentpiece.hasCaptured() && (xcor - x == 2 || x - xcor == 2) && validMove(xcor, ycor, x, y)) {
                table[x][y] = true;
                white[x][y] = true;
                return true;
            }
        }
        return false;
    }


    // determine whether a move is valid or not.
    private boolean validMove(int xi, int yi, int xf, int yf) {
        // fire team
        if (moved == true)
            return false;
        Piece p = pieceAt(xi, yi);
        if (p.isFire() == true) {
           //normal move and capture of king and not king
                if (p.isKing() == true) {
                    if ((abs(xf, xi) == 1) && (abs(yf, yi) == 1) && pieceAt(xf,yf) == null) {
                        // validmove = true;
                        return true;
                    }
                    else if ((abs(xf, xi) == 2) && (abs(yf, yi) == 2) && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2)!= null &&pieceAt(xf, yf) == null && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2).side() != p.side()) {
                        // validmove = true;
                        // captured = true;
                        return true;
                    }
                }

                else if (p.isKing() == false) {
                    if ((abs(xf, xi) == 1) && ((yf - yi) == 1) && pieceAt(xf,yf) == null) {
                        // validmove = true;
                        return true;
                    }
                    else if ((abs(xf, xi) == 2) && ((yf - yi) == 2) && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2)!= null &&pieceAt(xf, yf) == null && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2).side() != p.side()) {
                        // validmove = true;
                        // captured = true;
                        return true;
                    }
                }
            }    


        // water team
        else if (p.isFire() == false) {
            //normal move and capture by king and not king
                if (p.isKing() == true) {
                    if ((abs(xf, xi) == 1) && (abs(yf, yi) == 1) && pieceAt(xf,yf) == null) {
                        // validmove = true;
                        return true;
                    }
                    else if ((abs(xf, xi) == 2) && (abs(yf, yi) == 2) && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2)!= null &&pieceAt(xf, yf) == null && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2).side() != p.side()) {
                        // validmove = true;
                        // captured = true;
                        return true;
                    }
                }

                else if (p.isKing() == false) {
                    if ((abs(xf, xi) == 1) && ((yf - yi) == -1) && pieceAt(xf,yf) == null) {
                        // validmove = true;
                        return true;
                    }
                    else if ((abs(xf, xi) == 2) && ((yf - yi) == -2) && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2)!= null &&pieceAt(xf, yf) == null && pieceAt(xf+(xi-xf)/2, yf+(yi-yf)/2).side() != p.side()) {
                        // validmove = true;
                        // captured = true;
                        return true;
                    }
                }
        }            

        else {    
            // validmove = false;
            // captured = false;            
        }
        return false;
    }           

    // calculate the absolute value of x-y
    private int abs(int x, int y) {
        if (x >= y) {
            return (x - y);
        }
        else {
            return (y - x);
        }
    }


    /** Selects the square at (x, y). This method assumes canSelect (x,y) returns true.
    * For any piece to perform a capture, that piece must have been selected first. If you select a square with a piece, you are prepping that piece for movement. 
    * If you select an empty square (assuming canSelect returns true), you should move your most recently selected piece to that square.
    */
    public void select(int x, int y) {
        // assume canSelect returns true
        Piece p = pieceAt(x, y);
        if (p != null) {
            selected = true;
            p.move(x, y);
            //table[xcor][ycor] = false;
            white[xcor][ycor] = false;
            xcor = x; //update piece's x coordinate
            ycor = y; //update piece's y coordinate
            table[x][y] = true;
            white[x][y] = true;
            currentpiece = pieceAt(x, y);
        }

        // Empty Square
        else if (p == null) {
            selected = true;
            if (abs(xcor, x) ==2)
                captured = true;
            else
                moved = true;
            //call move to move the piece to the selected place and capture any intermediate pieces 
            currentpiece.move(x, y);
            table[xcor][ycor] = false;
            white[xcor][ycor] = false;
            xcor = x;
            ycor = y;
            currentpiece = pieceAt(x, y);
            moved = true;
            table[x][y] = true;
            white[x][y] = true;
        }
    }


    /** Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
    *If another piece already exists at (x, y), p will replace that piece. 
    *(This method is potentially useful for creating specific test circumstances.)
    */

    /** OKAY NOW!!! 
    */
    public void place(Piece p, int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return;
        }

        else if (p == null) {
            return;
        }

        else {
            array[x][y] = p;

            //change the boolean array to be true
            table[x][y] = true;
        }

    }


    /**Executes a remove. Returns the piece that was removed. 
    *If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
    *If there is no piece at (x, y), returns null and prints an appropriate message.
    */

    /** OKAY NOW!!! 
    */
    public Piece remove(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            System.out.println("Out of Bounds!");
            return null;
        }

        else if (array[x][y] == null) {
            System.out.println("No Piece Here!");
            return null;
        }

        else {
            Piece p = array[x][y];
            array[x][y] = null;
            return p;
        }
    }

    /** Returns whether or not the the current player can end their turn. 
    *To be able to end a turn, a piece must have moved or performed a capture.
    */
    public boolean canEndTurn() {
        // a piece must have moved or performed a capture
        if ((moved == true) || (captured == true)) {
            return true;
        }
        else {
            return false;
        }
    }

    /** Called at the end of each turn. 
    * Handles switching of players and anything else that should happen at the end of a turn.
    */
    public void endTurn() {
        if (player1 == true) {
            player1 = false;
            player2 = true;
        }
        else if (player2 == true) {
            player1 = true;
            player2 = false;
        }
        // change the state of the piece;
        moved = false;
        selected = false;
        if (currentpiece != null) {
            currentpiece.doneCapturing();
        }
        
        captured = false;
        currentpiece = null;
        white[xcor][ycor] = false;
    }

    /** Returns the winner of the game: "Fire", "Water", "No one", or null. 
    * If only fire pieces remain on the board, fire wins. 
    * If only water pieces remain, water wins.
    * If no pieces remain (consider an explosion capture), no one wins.
    * If the game is still in progress (ie there are pieces from both sides still on the board) return null.
    * Assume there is no stalemate situation in which the current player has pieces but cannot legally move any of them (In this event, just leave it at null). 
    * Determine the winner solely by the number of pieces belonging to each team.
    */

    public String winner() {
        int watercount = 0;
        int firecount = 0;
        int piececount = 0;

        for(int i = 0; i<=7; i++) {
            for(int j = 0; j<=7; j++) {
                if (array[i][j] != null) {
                    piececount = piececount+1;
                    Piece p = array[i][j];
                    if (p.isFire() == true) {
                        firecount = firecount+1;
                    }
                    else {
                        watercount = watercount+1;
                    }
                }
            }
        }

        if (piececount == 0) {
            return "No one";
        }
        else if (firecount == 0) {
            return "Water";
        }
        else if (watercount == 0) {
            return "Fire";
        }
        else {
            return null;
        }
    }


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        while(b.winner() == null) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                 int xcoord = (int)StdDrawPlus.mouseX();
                 int ycoord = (int)StdDrawPlus.mouseY();
                 if (b.canSelect(xcoord, ycoord)) {
                    b.select(xcoord, ycoord);
                
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                    if (b.canEndTurn()) {
                        b.endTurn();
                    }

            }



            StdDrawPlus.show(100);
        }
        System.out.println(b.winner());

    }
}

