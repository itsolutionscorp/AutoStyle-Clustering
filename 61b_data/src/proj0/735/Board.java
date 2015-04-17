public class Board {
    // Global variables here!
    private Piece[][] pieces = new Piece[8][8];
    //private String[][] imgArray = new String[8][8];
    //private static boolean[][] piecesBoolean = new boolean[8][8];
    private int turnCounter = 0;
    private int[] pieceLastSelectedCoord = new int[]{-1, -1};
    private int[] spaceLastSelectedCoord = new int[]{-1, -1};
    private int[] pieceToRemoveCoord = new int[2];
    private boolean hasMoved = false;
    private boolean stayHighlighted = false;

    // Main method.  Starts GUI.
    public static void main(String args[]) {
        //while(board1.winner() == null) {
        Board board1 = new Board(false);
        String winnerString = null;
        while (winnerString == null) {
            board1.drawBoard();
            
            double x;
            double y;
            
            if (StdDrawPlus.mousePressed()) {
                x = StdDrawPlus.mouseX();
                y = StdDrawPlus.mouseY();
                if (board1.canSelect((int) x, (int) y)) {
                    board1.select((int) x, (int) y);
                }
            }  
            /*  
            if (stayHighlighted == true) {
                highlight(x, y);
            }    
            */    

            if (StdDrawPlus.isSpacePressed()) {
                if (board1.canEndTurn()) {
                    board1.endTurn();
                }
            }
            
            StdDrawPlus.show(100);

            winnerString = board1.winner();
        }
        //System.out.println(board1.winner());

       
        // End game
    }

 /*********************************************************
 *** GUI METHODS ******************************************
 *********************************************************/

    // GUI.  Draws board.
    
    private void drawBoard() {
        StdDrawPlus.setScale(0, 8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                    } else {
                        StdDrawPlus.setPenColor(StdDrawPlus.RED);
                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                } else {
                    if (j % 2 == 0) {
                        StdDrawPlus.setPenColor(StdDrawPlus.RED);
                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                    } else {
                        StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                }
                // Draw pieces
                // If there's a piece
                if (pieces[i][j] != null) {
                    //  If it's a fire piece
                    if (pieces[i][j].isFire()) {
                        if (getType(pieces[i][j]) == "bomb") {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
                        } else if (getType(pieces[i][j]) == "shield") {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
                        } else { // is pawn
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
                        }
                    } else { // It's a water piece
                        if (getType(pieces[i][j]) == "bomb") {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
                        } else if (getType(pieces[i][j]) == "shield") {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
                        } else { // is pawn
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

    // highlight square at x, y
    private void highlight(int x, int y) {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
    }
    
  
/**********************************************************
 *** GAME METHODS *****************************************
 **********************************************************/

    // private static boolean isEmpty;

    // Constructor.  If shouldBeEmpty is true,
    // initializes an empty board.  Else,
    // initializes default configuration.
    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty) {
            // Do nothing!
        } else {
            // Initializes pieces
            
            int i = 0;
            for (int j = 0; j < 8; j = j + 2) {
                // pieces[j][i] = "img/pawn-fire.png";
                pieces[j][i] = new Piece(true, this, j, i, "pawn");
            }

            i = i + 1; // i = 1
            for (int j = 1; j < 8; j = j + 2) {
                // pieces[j][i] = "img/shield-fire.png";
                pieces[j][i] = new Piece(true, this, j, i, "shield");
            }

            i = i + 1; // i = 2
            for (int j = 0; j < 8; j = j + 2) {
                // pieces[j][i] = "img/bomb-fire.png";
                pieces[j][i] = new Piece(true, this, j, i, "bomb");
            }

            i = i + 3; // i = 5
            for (int j = 1; j < 8; j = j + 2) {
                // pieces[j][i] = "img/bomb-water.png";
                pieces[j][i] = new Piece(false, this, j, i, "bomb");
            }

            i = i + 1; // i = 6
            for (int j = 0; j < 8; j = j + 2) {
                // pieces[j][i] = "img/shield-water.png";
                pieces[j][i] = new Piece(false, this, j, i, "shield");
            }

            i = i + 1; // i = 7
            for (int j = 1; j < 8; j = j + 2) {
                // pieces[j][i] = "img/pawn-water.png";
                pieces[j][i] = new Piece(false, this, j, i, "pawn");
            }
            
            // for (int i = 1; i < 8; i = i + 2) {
            //     // pieces[j][i] = "img/pawn-water.png";
            //     pieces[i][i] = new Piece(false, this, i, i, "pawn");
            // }
            // pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
            // pieces[5][5] =null;
            // pieces[3][5] = new Piece(false, this, 3, 5, "pawn");
        }
    }    
    
    // Returns piece at position (x,y).  Else
    // (eg. no piece present, out of bounds coordinates)
    // returns null.

    public Piece pieceAt(int x, int y) {
        if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            return null;
        }
        if (pieces[x][y] != null) {
            return pieces[x][y];
        } else {
            return null;
        }
    } 

    // Returns true if piece at (x,y) can be selected.
    public boolean canSelect(int x, int y) {
        System.out.println(x);
        System.out.println(y);
    // if (piece at xy != null) {
    //     if (((even turn) && (fire team)) || ((odd number) && (water team))){
        boolean tempBool = false;
        if ((turnCounter % 2) == 0) { //even # turn = fire's turn.
            if (pieces[x][y] == null) {
                // if another piece has not been already selected
                if (pieceLastSelectedCoord[0] == -1) {
                    tempBool = false;
                } else { // else if another piece has been already selected
                    if (hasMoved && (pieces[pieceLastSelectedCoord[0]][pieceLastSelectedCoord[1]] != null) &&
                         !pieces[pieceLastSelectedCoord[0]][pieceLastSelectedCoord[1]].hasCaptured()) {
                        tempBool = false;
                    } else {
                        tempBool = validMove(pieceLastSelectedCoord[0], pieceLastSelectedCoord[1], x, y);
                    }
                }
            } else {
                if (pieces[x][y].isFire() != true) {
                    tempBool = false;
                } else if (pieces[x][y].isFire() == true) {
                    if (hasMoved) {
                        tempBool = false;
                    } else {
                        tempBool = true;
                    }
                } 
            }
        } else { // water's allowed to select things
            if (pieces[x][y] == null) {
                // if another piece has not been already selected
                if (pieceLastSelectedCoord[0] == -1) {
                    tempBool = false;
                } else { // else if another piece has been already selected
                    if (hasMoved && (pieces[pieceLastSelectedCoord[0]][pieceLastSelectedCoord[1]] != null) && 
                        !pieces[pieceLastSelectedCoord[0]][pieceLastSelectedCoord[1]].hasCaptured()) {
                        tempBool = false;
                    } else {
                        tempBool = validMove(this.pieceLastSelectedCoord[0], this.pieceLastSelectedCoord[1], x, y);
                    }
                    // if (pieces[pieceLastSelectedCoord[0]][pieceLastSelectedCoord[1]] != null) {
                    //     return validMove(pieceLastSelectedCoord[0], pieceLastSelectedCoord[1], x, y);
                    // } else {
                    //     return false;
                    // }
                }
            } else {
                if (pieces[x][y].isFire() == true) {
                    tempBool = false;
                } else if (pieces[x][y].isFire() != true) {
                    if (hasMoved) {
                        tempBool = false;
                    } else {
                        tempBool = true;
                    }
                } 
            }

            // if (pieces[x][y].isFire() == true) {
            //     return false;
            // } else if (pieces[x][y].isFire() != true) {
            //     return true;
            // }
        }
        System.out.println(tempBool);
        return tempBool;
        // return false; // TODO:  VERIFY IF THIS IS A PROPER RETURN
    }


    // [OPTIONAL, BUT RECOMMENDED]
    // Returns true if move is valid.
    private boolean validMove (int xi, int yi, int xf, int yf) {
        // int xiCoord = xi;
        // int xfCoord = xf;
        // int yiCoord = yi;
        // int yfCoord = yf;

        // if difference in x coord or y coord > 2, return false
        // if xf < 0 || xf > 7 or yf < 0 or yf > 7, return false

        // if king
        //      return validMoveHelperPresence(xi, yi, xf, yf)
        // else
        //      if yf-yi > 0
        //          return validMoveHelperPresence(xi, yi, xf, yf)
        //      else
        //          return false

        // if ((Math.abs(xf - xi) > 2) || (Math.abs(yf - yi) > 2)) {
        //     return false;
        // }
        
        if ((xf < 0) || (xf > 7) || (yf < 0) || (xf > 7)) {
            return false;
        }
        Piece thisPiece = pieces[xi][yi];
        if (thisPiece == null) {
            return false;
        }
        Piece enemyPiece = pieces[(xf+xi) / 2][(yf+yi) / 2];
        boolean enemyExists = (enemyPiece != null);
        // If is king
        if (thisPiece.isKing()) {
            // If moves along diagonal
            if ((Math.abs(xf - xi)) == (Math.abs(yf - yi))) {
                // If moving magnitude 2
                if (Math.abs(xf - xi) == 2) {
                    // If pieces are enemies
                    //if ((thisPiece.getType()) != (pieces[(xf-xi) / 2][(yf-yi) / 2].getType())) {
                    if (enemyExists) {
                        if (((thisPiece.isFire()) != (enemyPiece.isFire()))) {
                            // hasMoved = true;
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else if (Math.abs(xf - xi) == 1) {
                    if (hasMoved) {
                        return false;
                    } 
                    // hasMoved = true;
                    return true;
                }
            }
        } else { // Is not a king
            if ((Math.abs(xf - xi)) == (Math.abs(yf - yi))) {
                //If moving magnitude 2
                if (Math.abs(xf - xi) == 2) {
                    if (enemyExists) {
                        if (((thisPiece.isFire()) != (enemyPiece.isFire()))) {
                            if ((thisPiece.isFire()) && (yf - yi > 0)) {
                                // hasMoved = true;
                                return true;
                            } else if (!(thisPiece.isFire()) && (yf - yi < 0)) {
                                // hasMoved = true;
                                return true;
                            }
                        }
                    } else {
                        return false;
                    }
                } else if (Math.abs(xf - xi) == 1) {
                    if ((thisPiece.isFire()) && (yf - yi > 0)) {
                        if (hasMoved) {
                            return false;
                        } 
                        // hasMoved = true;
                        return true;
                    } else if (!(thisPiece.isFire()) && (yf - yi < 0)) {
                        if (hasMoved) {
                            return false;
                        }
                        // hasMoved = true;
                        return true;
                    }
                }
            }
        }
        return false;

        //     if ((yf - yi) > 0) {
        //         return validMoveHelperPresence(xi, yi, xf, yf);
        //     } else {
        //         return false;
        //     }
        // }
    }
   

    
    // Stores piece a x, y last selected  Is null by default,
    // and returns null upon ending a turn.
    // TODO: PUT CLEAR STORE LAST SELECTED IN END TURN METHOD
    private void storeLastSelectedPiece(int x, int y) {
        // int xCoord = x;
        // int yCoord = y;
        pieceLastSelectedCoord[0] = x;
        pieceLastSelectedCoord[1] = y;
    }
    

    private void storeLastSelectedSpace (int x, int y) {
        this.pieceLastSelectedCoord[0] = x;
        this.pieceLastSelectedCoord[1] = y;
    }
    
    // Selects square at (x,y).  
    // Assumes canSelect(x,y) returns true.
    public void select(int x, int y) {
        if (pieces[x][y] == null) { //if no piece at x,y
            Piece p = pieces[pieceLastSelectedCoord[0]][pieceLastSelectedCoord[1]];
            hasMoved = true;
            p.move(x,y);
            if (pieces[x][y] == null) {
                pieceLastSelectedCoord = new int [] {-1, -1};
            }
        }
        storeLastSelectedPiece(x,y);
        // if (pieces[x][y] == null) { // empty space selected 
        //     storeLastSelectedSpace(x, y);
        // } else { // piece space selected
        //     storeLastSelectedPiece(x, y);
        // }
        // while (stayHighlighted == true) {
        // highlight(x, y);
        // }
    }


    // Places piece p at position (x,y).
    // If (x,y) out of bounds, or if p is null, does nothing.
    // If piece already exists at (x,y), replaces that piece.
    
    public void place(Piece p, int x, int y) {
        pieces[x][y] = p;
        System.out.printf("Type:  %b %s %d %d\n", p.isFire(), getType(p), x, y);
    }
      
    // Returns piece removed from position (x,y).
    // If (x,y) out of bounds, or if p is null 
    // (ie. no piece present), return null and appropriate message.
    public Piece remove(int x, int y) {
        // If x,y are invalid coordinates.
        if ((x < 0) || (x > 7) || (y < 0) || (x > 7)) {
            System.out.println("Cannot remove piece--invalid coordinates.");
           return null;
        } else if (pieces[x][y] == null) {// If there's no piece at x,y
            System.out.println("Cannot remove piece--piece not found.");
            return null;
        } else {
        // else store piece to be removed
        //pieceToRemoveCoord[0] = x;
        //pieceToRemoveCoord[1] = y;

            Piece pieceToBeRemoved = pieces[x][y];
            // Remove the piece
            pieces[x][y] = null;
            return pieceToBeRemoved;
        }   
    }
  
    // :  CLEAR HASMOVED AT END TURN PHASE
    // IF A MOVE IS MADE (INCLUDING CAPTURING), SET TO TRUE.
    
    // Returns true if player can end turn--
    // ie. when a piece has either moved or performed capture.
    public boolean canEndTurn() {
        if (hasMoved == true) {
            return true;
        } else {
            return false;
        }
    }
    
    // Handles end-turn semantics.  (Eg. switching players.)
    // Called at the end of each turn.
    public void endTurn() {
        System.out.print("Ending Turn: ");
        System.out.println(turnCounter);
        // Set pieceLastSelected and pieceToRemove to null;
        // Set hasMoved to false
        // Increment turnCounter by 1
        Piece p = pieces[pieceLastSelectedCoord[0]][pieceLastSelectedCoord[1]];
        if (p != null) {
            p.doneCapturing();
        }
        pieceLastSelectedCoord[0] = -1;
        pieceLastSelectedCoord[1] = -1;
        // pieceToRemove[0] = null;
        pieceLastSelectedCoord[0] = -1;
        pieceLastSelectedCoord[1] = -1;
        pieceToRemoveCoord[0] = -1;
        pieceToRemoveCoord[1] = -1;
        hasMoved = false;
        stayHighlighted = false;
        turnCounter = turnCounter + 1;
    }

    private int pieceCount(boolean isFireBoolean) {
        int pieceCounter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((pieces[i][j] != null) && (pieces[i][j].isFire() == isFireBoolean)) {
                    pieceCounter = pieceCounter + 1;
                }
            }
        }
        return pieceCounter;
    }

    private int pieceQuantityOfTeam(String pieceTeam) {
        if (pieceTeam.equals("fire")) {
            return pieceCount(true);
        } else {
            return pieceCount(false);
        }
    }
    // Returns string stating winner of game.  
    // Either "Fire" "Water" or "No one". 
    // Or null if game is not yet over.
    // Winner determined by number of pieces remaining.
    public String winner() {
        boolean boolGameInProgress = false;
        boolean boolTieGame = false;

        // if ((pieceQuantityOfTeam("fire") != 0) && (pieceQuantityOfTeam("water") != 0)) {
        //     boolGameInProgress = true;
        // }
        if ((pieceQuantityOfTeam("fire") == 0) && (pieceQuantityOfTeam("water") == 0)) {
            boolTieGame = true;
        }

        if (boolTieGame) {
            return "No One";
        } else if ((pieceQuantityOfTeam("fire") != 0) && (pieceQuantityOfTeam("water") == 0)) {
            return "Fire";
        } else if ((pieceQuantityOfTeam("fire") == 0) && (pieceQuantityOfTeam("water") != 0)) {
            return "Water";
        } else {
            return null;
        }
    }

    private String getType(Piece p){
        if (p.isShield()) {
            return "shield";
        }
        if (p.isBomb()) {
            return "bomb";
        }
        return "pawn";
    }
}