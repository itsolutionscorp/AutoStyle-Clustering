/**
 * @author Matthew Mussomele
 */

public class Board {
    /*
        * DONE: Write main
        * DONE: Write Constructor  ***commented***
        * DONE: Write pieceAt      ***commented***
        * DONE: Write canSelect    ***commented***
        * DONE: Write select       ***commented***
        * DONE: Write place        ***commented***
        * DONE: Write remove       ***commented***
        * DONE: Write canEndTurn   ***commented***
        * DONE: Write endTurn      ***commented***
        * DONE: Write winner       ***commented***
        
        
        HELPER METHODS:                                                 USED BY:
            private boolean validMove(int xi, int yi, int xf, int yf)       canSelect(...)
            private void draw()                                             main(...)    
            private String buildPath(Piece p)                               draw()
            private void initBoard()                                        Board(...)
            private boolean outOfBounds(int x, int min, int max)            canSelect(...), validMove(...), place(...), remove(...)
            private boolean canTake(int x, int y)                           canSelect(...), validMove(...)
            private boolean sameTeam(Piece p)                               canSelect(...), validMove(...), canTake(...)
            private int[] findPiece(Piece p)                                canSelect(...)
            private int[] countPiecesLeft()                                 winner()

     */
    
    private int boardSize = 8;
    private Piece[][] gameBoard;
    private boolean who, movedThisTurn, capturedThisTurn;
    private Piece selected;
    
    /*
     * Starts a GUI supported version of the game.
     */
    public static void main(String[] args) {
        Board board = new Board(false);
        while(true){
            if(StdDrawPlus.mousePressed()){
                int xChoice = (int)StdDrawPlus.mouseX();
                int yChoice = (int)StdDrawPlus.mouseY();
                if(board.canSelect(xChoice, yChoice)){
                    board.select(xChoice, yChoice);
                }
            }
            else if(StdDrawPlus.isSpacePressed()){
                if(board.canEndTurn())
                    board.endTurn();
            }
            else if(board.winner() != null){
                System.out.println(board.winner());
                return;
            }
            StdDrawPlus.show(15);
            board.draw();
        }
    }
    
    /*
     * The constructor for Board. 
     * If shouldBeEmpty is true, initializes an empty Board. 
     * Otherwise, initializes a Board with the default configuration. 
     * Note that an empty Board could possibly be useful for testing purposes.
     */
    public Board(boolean shouldBeEmpty){
        gameBoard = new Piece[boardSize][boardSize];                                              //initializes the array of Pieces
        who = true;                                                                               //fire goes first
        movedThisTurn = false;                                                                    //haven't moved
        capturedThisTurn = false;                                                                 //havent't captured
        if(!shouldBeEmpty){                                                                       //check if we should fill gameBoard, used for testing
            initBoard();                                                                          //fills gameBoard with proper pieces
        }
    }

    /*
     * Gets the piece at position (x, y) on the board, or null if there is no piece. 
     * If (x, y) are out of bounds, returns null.
     */
    public Piece pieceAt(int x, int y){
        if(outOfBounds(x, 0, boardSize) || outOfBounds(y, 0, boardSize)) return null;
        return gameBoard[x][y];                                                                   //piece object at x, y
    }

    /*
     * Returns true if the square at (x, y) can be selected.
     *
     * A square with a piece may be selected if it is the corresponding player’s turn 
     * and one of the following is true:
     *   
     *   -The player has not selected a piece yet.
     *   -The player has selected a piece, but did not move it.
     *
     * 
     * An empty square may be selected if one of the following is true:
     * 
     *   -During this turn, the player has selected a Piece which hasn’t moved yet and 
     *      is selecting an empty spot which is a valid move for the previously selected Piece.
     *   -During this turn, the player has selected a Piece, captured, and has selected another 
     *      valid capture destination. When performing multi-captures, you should only select the 
     *      active piece once; all other selections should be valid destination points.
     *
     */
    public boolean canSelect(int x, int y){
        if(outOfBounds(x, 0, boardSize) || outOfBounds(y, 0, boardSize)) return false;            //ensures off-board locations can't be selected
        Piece mightSelect = pieceAt(x, y);
        if(mightSelect == null && selected != null){                                              //selected empty square and has selected piece (move attempt)
            int[] sLoc = findPiece(selected);
            if(sLoc == null)
                return false;
            boolean baseChecks = validMove(sLoc[0], sLoc[1], x, y);                               //piece is on the board (i.e. is not exploded bomb) and can move to x, y
            int dX = x - sLoc[0];
            if(movedThisTurn){
                return baseChecks && capturedThisTurn && Math.abs(dX) == 2;                       //piece can multicapture
            }
            else{
                return baseChecks;
            }
        }
        else if(sameTeam(mightSelect)){                                                           //can't choose opponent's pieces and 
            return selected == null || !movedThisTurn;                                              //haven't chosen a piece or haven't move selected piece
        }
        return false;
    }

    /*
     * Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), 
     * strictly from a geometry/piece-race point of view. 
     * validMove does not need to take into consideration whose turn it is or if a move has already 
     * been made, but rather can.
     * 
     * Update (2/6): validMove is not required, and will not be tested. 
     * You may implement this however you want. 
     * It is suggested you use this method to simplify your implementation of canSelect. 
     * However, keep this method must be private.
     */
    private boolean validMove(int xi, int yi, int xf, int yf){
        if(outOfBounds(xf, 0, boardSize) || outOfBounds(yf, 0, boardSize))                        //ensures off-board locations can't be moved to
            return false;
        Piece moving = pieceAt(xi, yi);
        if(!sameTeam(moving))            return false;                                            //can't move opponent's pieces
        int perspective = 1 - 2*moving.side();                                                    //water moves down, fire moves up
        int dX = xf - xi, dY = yf - yi;                       
        if(Math.abs(dX) != Math.abs(dY)) return false;                                            //must move diagonally
        if(dX == 0)                      return false;                                            //can't move nowhere
        boolean baseChecks = pieceAt(xf, yf) == null;                                             //can't move onto another piece
        if(dY*perspective < 0){                                                                   //trying to move relatively backwards
            baseChecks = baseChecks && moving.isKing();                                             //must be kinged
        }
        if(Math.abs(dX) == 1)            return baseChecks;                                       //regular move
        else if(Math.abs(dX) == 2)       return baseChecks && canTake(xi + dX / 2, yi + dY / 2);  //must be capturing to move 2
        else                             return false;                                            //must move 1 or 2 spaces

    }

    /*
     * Selects the square at (x, y). 
     * This method assumes canSelect (x,y) returns true. 
     * Optionally, it is recommended to color the background of the selected square white on the GUI 
     * via the pen color function. 
     * For any piece to perform a capture, that piece must have been selected first. 
     * If you select a square with a piece, you are prepping that piece for movement. 
     * If you select an empty square (assuming canSelect returns true), you should move your most 
     * recently selected piece to that square.
     */
    public void select(int x, int y){
        Piece chosen = pieceAt(x, y);                                                             
        if(chosen == null){                                                                       //choosing empty square
            selected.move(x, y);                                                                  
            movedThisTurn = true;
            capturedThisTurn = selected.hasCaptured();
        }
        else{                                                                                     //choosing a new piece
            selected = chosen;
        }
    }                                                                                               

    /*
     * Places p at (x, y). 
     * If (x, y) is out of bounds or if p is null, does nothing. 
     * If another piece already exists at (x, y), p will replace that piece. 
     * (This method is potentially useful for creating specific test circumstances.)
     */
    public void place(Piece p, int x, int y){
        if(outOfBounds(x, 0, boardSize) || outOfBounds(y, 0, boardSize))                          //ensures off-board locations can't be moved to
            return;
        else if(p == null)                                                                        //can't move nothing
            return;
        gameBoard[x][y] = p;                                                                      //places p at x, y
    }

    /*
     * Executes a remove. 
     * Returns the piece that was removed. 
     * If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
     * If there is no piece at (x, y), returns null and prints an appropriate message.
     */
    public Piece remove(int x, int y){
        if(outOfBounds(x, 0, boardSize) || outOfBounds(y, 0, boardSize)){                         //ensures off-board locations can't be moved to
            System.out.println("Error: removal coordinates out of bounds");
            return null;
        }
        else if(pieceAt(x, y) == null){                                                           //ensures that we are actually removing a piece
            System.out.println("Error: removal coordinates empty");
            return null;
        }
        Piece toReturn = pieceAt(x, y);
        gameBoard[x][y] = null;                                                                   //deletes the piece at x, y
        return toReturn;
    }

    /*
     *  Returns whether or not the the current player can end their turn. 
     *  To be able to end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn(){
        return movedThisTurn || capturedThisTurn;                                                 //must have moved or captured
    }

    /*
     * Called at the end of each turn. 
     * Handles switching of players and anything else that should happen at the end of a turn.
     */
    public void endTurn(){
        who = !who;                                                                               //switch players
        movedThisTurn = false;                                                                    //haven't moved
        capturedThisTurn = false;                                                                 //haven't captured
        selected.doneCapturing();                                                                 
        selected = null;                                                                          //haven't selected
    }

    /*
     * Returns the winner of the game: "Fire", "Water", "No one", or null. 
     * If only fire pieces remain on the board, fire wins. 
     * If only water pieces remain, water wins. 
     * If no pieces remain (consider an explosion capture), no one wins. 
     * If the game is still in progress (ie there are pieces from both sides still on the board) return null. 
     * Assume there is no stalemate situation in which the current player has pieces but cannot legally move 
     * any of them (In this event, just leave it at null). Determine the winner solely by the number of pieces 
     * belonging to each team.
     */
    public String winner(){
        int[] pieceCount = countPiecesLeft();
        if(pieceCount[0] == boardSize*boardSize)   return "No one";                               //if the board is filled with null, it is a tie
        else if(pieceCount[1] == 0)                return "Water";                                //no fire pieces but still pieces left, water wins
        else if(pieceCount[2] == 0)                return "Fire";                                 //no water pieces but still pieces left, fire wins
        else                                       return null;                                   //pieces of both teams left, game isn't over
    }

    /*
            Private helper methods
     */
    

    /*
     * Draws the current state of the board, highlighting the selected piece if there is one.
     */
    private void draw(){
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                if(selected != null && gameBoard[i][j] == selected)
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) 
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(gameBoard[i][j] != null){
                    StdDrawPlus.picture(i + .5, j + .5, buildPath(gameBoard[i][j]), 1, 1);
                }
            }
        }
    }

    /*
     * Builds a string representing the file path from the proj0 folder to the image of the piece.
     */
    private String buildPath(Piece p){
        String path = null;
        
        if(p.isShield())     path = "img/shield";
        else if(p.isBomb())  path = "img/bomb";
        else                 path = "img/pawn";

        if(p.isFire())       path += "-fire";
        else                 path += "-water";
        
        if(p.isKing())       path += "-crowned";

        return path+".png";
    }

    /*
     * Initialized the board with all 24 pieces that normal games start out with.
     */
    private void initBoard(){
        StdDrawPlus.setXscale(0, boardSize);
        StdDrawPlus.setYscale(0, boardSize);
        for(int i = 0; i < boardSize / 2; i++){
            gameBoard[2*i][0]             = new Piece(true, this, 2*i, 0, "pawn");
            gameBoard[2*i+1][1]           = new Piece(true, this, 2*i + 1, 1, "shield");
            gameBoard[2*i][2]             = new Piece(true, this, 2*i, 2, "bomb");
            gameBoard[2*i+1][boardSize-1] = new Piece(false, this, 2*i + 1, boardSize - 1, "pawn");
            gameBoard[2*i][boardSize-2]   = new Piece(false, this, 2*i, boardSize - 2, "shield");
            gameBoard[2*i+1][boardSize-3] = new Piece(false, this, 2*i + 1, boardSize - 3, "bomb");
        }
    }

    /*
     * returns true if x is less than min or greater than or equal to max. False otherwise.
     */
    private boolean outOfBounds(int x, int min, int max){
        if(x < min || x >= max)
            return true;
        return false;
    }

    /*
     * Returns true if the piece at x, y can be taken by the current player.
     * This method assumes that the player has a piece in an appropriate position for capturing
     * and that by jumping the player's piece ends up in a valid location.
     */
    private boolean canTake(int x, int y){
        Piece jumping = pieceAt(x, y);
        return jumping != null && !sameTeam(jumping);
    }

    /*
     * Returns true if the piece belongs to the current player, false otherwise.
     */
    private boolean sameTeam(Piece p){
        if(p == null) return false;
        return p.isFire() == who;
    }

    /*
     * Returns the position of p as an array or null if p does not exist on the game board.
     */
    private int[] findPiece(Piece p){
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                if(pieceAt(i, j) == p) return new int[]{i, j};
            }
        }
        return null;
    }

    /*
     * Returns the number of pieces left in an array. 
     * The array holds values as follows: {nullCount, fireCount, waterCount}
     */
    private int[] countPiecesLeft(){
        int[] piecesLeft = new int[3];
        for(Piece[] row: gameBoard){
            for(Piece p: row){
                if(p == null)
                    piecesLeft[0] += 1;
                else if(p.isFire())
                    piecesLeft[1] += 1;
                else
                    piecesLeft[2] += 1;
            }
        }
        return piecesLeft;
    }

}