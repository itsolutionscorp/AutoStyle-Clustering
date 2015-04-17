public class Board {
    private boolean emptyBoard;
    private Piece[][] pieces;
    private int[] selectedPiece = new int[2];
    private Piece selected;
    private boolean isFireTurn = true;
    private boolean hasSelected = false;
    //boolean hasCaptured = false;
    private boolean hasMoved = false;//need???
    //boolean preparedToMove = false;
    private boolean hasCapturedInBoard;


    /* Constructor for the Board. If shouldBeEmpty is true, initializes an empty
       Board. Otherwise, initializes a Board with the default configuration.
       Note that an empty Board could possibly be useful for testing purposes.
    */
    public Board(boolean shouldBeEmpty) {
        emptyBoard = shouldBeEmpty;
        selectedPiece[0] = 10;
        selectedPiece[1] = 10;
        pieces = new Piece[8][8];
        if (emptyBoard == false) {
            this.getPieces();
        }
    }


    private void getPieces() {
        pieces = new Piece[8][8];
        String pieceType = "";
        boolean firePiece = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j += 1) {
                if ((i + j) % 2 == 0) {
                    if (i == 0 || i == 7) {
                        pieceType = "pawn";
                        if (i == 0) {
                            firePiece = true;
                        }
                        if (i == 7) {
                            firePiece = false;
                        }
                    }
                    if (i == 1 || i == 6) {
                        pieceType = "shield";
                        if (i == 1) {
                            firePiece = true;
                        }
                        if (i == 6) {
                            firePiece = false;
                        }
                    }
                    if (i == 2 || i == 5) {
                        pieceType = "bomb";
                        if (i == 2) {
                            firePiece = true;
                        }
                        if (i == 5) {
                            firePiece = false;
                        }
                    }
                    if (i > 2 && i < 5) {
                        continue;
                    }
                    pieces[i][j] = new Piece(firePiece, this, j, i, pieceType);
                }
            }
        }
    }
    private void drawPieces() {
        String img = "";
        String pic = "";
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece curr = pieces[i][j];
                if (curr != null) {
                    if (curr.isBomb() == true) {
                        img = "bomb".concat(img);
                    }
                    if (curr.isShield() == true) {
                        img = "shield".concat(img);
                    }
                    if (curr.isBomb() == false && curr.isShield() == false) {
                            img = "pawn".concat(img);
                    }
                    if (curr.isFire() == true) {
                        img = img.concat("-fire");
                    }
                    if (curr.isFire() == false) {
                        img = img.concat("-water");
                    }
                    if (curr.isKing() == true) {
                        img = img.concat("-crowned");
                    }
                    img = img.concat(".png");
                    pic = "img/".concat(img);
                    StdDrawPlus.picture(j+0.5, i+0.5, pic, 1.0, 1.0);
                }
                img = "";
                pic = "";
            }
        }
    }

                    
                    

    private void drawBoard() {
        //Board drawing adapted from StdDrawDemo.java by Josh Hug.
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //boolean[][] pieces = new boolean[N][N];
        /** Mouse pressing monitoring. A new piece appears wherever the mouse is presssed. */
        //while(true) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);                   
            }
        }
        if (selectedPiece[0] != 10 && selectedPiece[1] != 10) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selectedPiece[0] + .5, selectedPiece[1] + .5, .5);
        }
        if (this.emptyBoard == false) {
            //this.getPieces();
            this.drawPieces();
        }
        StdDrawPlus.show(100);
    }
   
            
    /* Gets the piece at position (x, y) on the board, or null if there is no 
    piece.  If (x, y) are out of bounds, returns null. */
    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0 || pieces[y][x] == null) {
            return null;
        }
        return pieces[y][x];
    }


    /* Returns true if there is a piece at (x, y) and it can be selected.
     - A piece may be selected if it is the corresponding player's turn and
       one of the following is true:
        - The player has not selected a piece yet.
        - The player has selected a piece, but did not move it.
     - An empty square may be selected if one of the following is true:
        - During this turn, the player has selected a Piece which hasn't moved ye
          and is selecting an empty spot which is a valid move for the previously
          selected Piece.
        - During this turn, the player has selected a Piece, captured, and has 
          selected another valid capture destination. (You may select as many
          captures in a row as long as they are all valid and from the same piece.
    */
    public boolean canSelect(int x, int y) {
         if (selected != null) {
             hasCapturedInBoard = selected.hasCaptured();
         }
         Piece currentPiece = pieces[y][x];
         if (currentPiece == null) {
             if (hasSelected == true && hasMoved == false) {
                 if (validMove(selectedPiece[0], selectedPiece[1], x, y) == true) {
                     return true;
                 }
             }
             else if (hasSelected == true && hasMoved == true && hasCapturedInBoard == true) {
                 System.out.println("hasSelected:" + hasSelected);
                 if (validMove(selectedPiece[0], selectedPiece[1], x, y) == true) {
                     return true;
                 }
             }
         }
         else if (currentPiece.isFire() == isFireTurn) {
             //hasCapturedInBoard = currentPiece.hasCaptured();
             if (hasSelected == false) {
                 return true;
             }
             else if (hasSelected == true && hasMoved == false) {
                 return true;
             }
             //else if (hasSelected == true && hasMoved == true && hasCapturedInBoard == true && validMove(selectedPiece[0], selectedPiece[1], x, y) == true) {
                 //return true;
             //}
         }
         return false;
         
     }
    private boolean inBounds(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return false;
        }
        else {
            return true;
        }
    }

    /* Returns true if the piece at (xi, yi) can either move to (xf, yf) or 
       or capture to (xf, yf) in a valid fashion compatible with the rules.
    */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece currPiece = pieces[yi][xi];
        int currSide = currPiece.side();
        //Checking if moving to an empty square.
        if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && hasCapturedInBoard == false) {
            if (pieceAt(xf, yf) == null && inBounds(xf, yf) == true) {
                if (hasCapturedInBoard == true) {
                    return false;
                }
                if (currPiece.isKing() == true) {
                    return true;
                }
                if (currPiece.isFire() == true && (yf > yi)) {
                    return true;
                }
                if (currPiece.isFire() == false && (yf < yi)) {
                    return true;
                }
            }
        }
        /*Checking if making a capture. For each, I check if the destination is null, the destination is in bounds,
        the "piece" we are capturing isn't empty, and we are capturing an opponent's piece.
        */
        //TOP RIGHT
        if ((xf - xi) > 1 && (yf - yi) > 1) {
            if (pieceAt(xf, yf) == null && inBounds(xf, yf) == true && pieceAt(xi+1, yi+1) != null && pieceAt(xi+1, yi+1).side() != currSide) {
                if (currPiece.isKing() == true) {
                    return true;
                }
                if (currPiece.isFire() == true) {
                    return true;
                }
            }
        }
        //TOP LEFT
        if ((xf - xi) < 1 && (yf - yi) > 1) {
            if (pieceAt(xf, yf) == null && inBounds(xf, yf) == true && pieceAt(xi-1, yi+1) != null && pieceAt(xi-1, yi+1).side() != currSide) {
                if (currPiece.isKing() == true) {
                    return true;
                }
                if (currPiece.isFire() == true) {
                    return true;
                }
            }
        }
        //BOTTOM RIGHT
        if ((xf - xi) > 1 && (yf - yi) < 1) {
            if (pieceAt(xf, yf) == null && inBounds(xf, yf) == true && pieceAt(xi+1, yi-1) != null && pieceAt(xi+1, yi-1).side() != currSide) {
                if (currPiece.isKing() == true) {
                    return true;
                }
                if (currPiece.isFire() == false) {
                    return true;
                }
            }
        }
        //BOTTOM LEFT
        if ((xf - xi) < 1 && (yf - yi) < 1) {
            if (pieceAt(xf, yf) == null && inBounds(xf, yf) == true && pieceAt(xi-1, yi-1) != null && pieceAt(xi-1, yi-1).side() != currSide) {
                if (currPiece.isKing() == true) {
                    return true;
                }
                if (currPiece.isFire() == false) {
                    return true;
                }
            }
        }
        return false;
                          
    }


    /* Selects the piece at (x, y) if possible.  Optionally, it is recommended to
       color the background of the selected square white on the GUI via the pen
       color function. For any piece to perform a capture, that piece must have 
       been selected first.
    */
    public void select(int x, int y) {
        if (hasSelected == true && pieceAt(x, y) == null) {
            pieceAt(selectedPiece[0], selectedPiece[1]).move(x, y);
            hasMoved = true;
        }
        else {
            hasSelected = true;
        }
        selectedPiece[0] = x;
        selectedPiece[1] = y;
        selected = pieceAt(x, y);
        System.out.println("selected x coord:" + selectedPiece[0]);
        System.out.println("selected y coord:" + selectedPiece[1]);
    }
    
    /* Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
       nothing. If p already exists in the current Board, first removes it from 
       its initial position. If another piece already exists at (x, y), p will
       replace that piece. (This method is potentially useful for creating
       specific test circumstances.
    */
    public void place(Piece p, int x, int y) {
        if ((x < 8 && x >= 0) || (y < 8 && y >= 0) || (p != null)) {
            pieces[y][x] = p;
        }
    }
    
    /* Executes a remove. Returns the piece that was removed. If the input (x, y)        is out of bounds, returns null and prints an appropriate message. If there        is no piece at (x, y), returns null and prints an appropriate message.
    */
    public Piece remove(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            System.out.println("The requested position is out of bounds.");
            return null;
        }
        if (pieces[y][x] == null) {
            System.out.println("No piece here.");
            return null;
        }
        else {
            //System.out.println("Remove piece at " + x + ", " + y);
            Piece removedPiece = pieces[y][x];
            pieces[y][x] = null;
            return removedPiece;
        }

    }
    
    /* Returns whether or not the the current player can end their turn. To be 
       able to end a turn, a piece must have moved or performed a capture.
    */
    public boolean canEndTurn() {
        return hasMoved;
    }
    
    /* Called at the end of each turn. Handles switching of players and anything 
       else that should happen at the end of a turn.
    */
    public void endTurn() {
        if (canEndTurn() == true) {
            isFireTurn = !isFireTurn;
            hasSelected = false;
            hasMoved = false;
            hasCapturedInBoard = false;
            if (selected != null) {
               pieceAt(selectedPiece[0], selectedPiece[1]).doneCapturing();
            }
            selectedPiece[0] = 10;
            selectedPiece[1] = 10;
        }
            
    }
    
    /* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces        on the board), or null if the game is not yet over. Assume there is no 
       stalemate situation in which the current player has pieces but cannot 
       legally move any of them (In this event, just leave it at null). 
       Determine the winner solely by the number of pieces belonging to each team.
    */
    public String winner() {
        int scoreFire = 0;
        int scoreWater = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire() == true) {
                        scoreFire += 1;
                    }
                    else {
                        scoreWater += 1;
                    }
                }
            }
        }
        if (scoreFire == 0 && scoreWater > 0) {
            return "Water";
        }
        if (scoreWater == 0 && scoreFire > 0) {
            return "Fire";
        }
        if (scoreWater == 0 && scoreFire == 0) { 
            return "No one";
        }
        else {
            return null;
        }
    }
        

    public static void main(String[] args) {
        Board board = new Board(false);
        //board.getPieces();
        board.drawBoard();
        Piece currentPiece;
        int xCoord;
        int yCoord;
        while(true) {
            if (StdDrawPlus.mousePressed() == true) {
                xCoord = (int) StdDrawPlus.mouseX();
                yCoord = (int) StdDrawPlus.mouseY();
                if (board.canSelect(xCoord, yCoord) == true) {
                    board.select(xCoord, yCoord);
                }
            }
            if (StdDrawPlus.isSpacePressed() == true) {
                if (board.canEndTurn() == true) {
                    board.endTurn();
                }
                String winner = board.winner();
                if (winner != null) {
                    System.out.println(winner);
                }
            }
            board.drawBoard();
            StdDrawPlus.show(10); //after space bar is pressed the screen refreshes
        }        
            
    }
        
    
}


