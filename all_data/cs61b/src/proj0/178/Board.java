public class Board {

    private Piece[][] pieces; // Store each piece object in an array
    private int scale;
    private int turn;
    private Piece selection;
    private boolean hasMoved;


    /***********************
     ** Board Constructor **
     ***********************/
    public Board(boolean shouldbeEmpty) {

    	this.scale = 8;
    	this.turn = 0; // Fire plays first
    	this.selection = null;
    	this.hasMoved = false;

        /* Set Pieces on in Board Array */
        if (!shouldbeEmpty) {
            /* Creates all new piece objects in 2d array */
            this.pieces = new Piece[][]{	
        			{new Piece(true, this, 0, 0, "pawn"), null, new Piece (true, this, 0, 2, "bomb"), null, null, null, new Piece(false, this, 0, 6, "shield"), null}, // Column 1 on Board
        			{null, new Piece(true, this, 1, 1, "shield"), null, null, null, new Piece(false, this, 1, 5, "bomb"), null, new Piece(false, this, 1, 7, "pawn")},
        			{new Piece(true, this, 2, 0, "pawn"), null, new Piece (true, this, 2, 2, "bomb"), null, null, null, new Piece(false, this, 2, 6, "shield"), null},
        			{null, new Piece(true, this, 3, 1, "shield"), null, null, null, new Piece(false, this, 3, 5, "bomb"), null, new Piece(false, this, 3, 7, "pawn")},
       				{new Piece(true, this, 4, 0, "pawn"), null, new Piece (true, this, 4, 2, "bomb"), null, null, null, new Piece(false, this, 4, 6, "shield"), null},
       				{null, new Piece(true, this, 5, 1, "shield"), null, null, null, new Piece(false, this, 5, 5, "bomb"), null, new Piece(false, this, 5, 7, "pawn")},
       				{new Piece(true, this, 6, 0, "pawn"), null, new Piece (true, this, 6, 2, "bomb"), null, null, null, new Piece(false, this, 6, 6, "shield"), null},
        			{null, new Piece(true, this, 7, 1, "shield"), null, null, null, new Piece(false, this, 7, 5, "bomb"), null, new Piece(false, this, 7, 7, "pawn")}	}; // Column 8 on Board
        }
        else {

            /* Empty Board if requested */
            this.pieces = new Piece[8][8];
        }
    }


    /************************************
     ** Get the Piece at Position x, y **
     ************************************/
    public Piece pieceAt(int x, int y) {

        /* Check Bounds */
        if (outOfBounds(x, y)) { return null; }

        /* Return Piece from Array */
        return pieces[x][y];
    }


    /***********************************
     ** Places Piece in Position x, y **
     ***********************************/
    public void place(Piece p, int x, int y) {

        /* Check Bounds */
        if (outOfBounds(x, y) || (p == null)) { return; }

        /* Remove Piece from its Current Position */
        int[] coordinates = getCoordinates(p);
        if (coordinates != null) { remove(coordinates[0], coordinates[1]); }

        /* Removes Piece in Destination if Exist */
        if (pieceAt(x, y) != null) { remove(x, y); }

        /* Places Piece in New Position */
        pieces[x][y] = p;
    }


    /************************************
     ** Removes a Piece from the Board **
     ************************************/
    public Piece remove(int x, int y) {

        /* Check Bounds */
        if (outOfBounds(x, y)) {
            System.out.println("Out of Bounds");
            return null;
        }

        /* Check if Piece Exists */
        Piece piece = pieceAt(x, y);
        if (piece == null) {
            System.out.println("No Piece in that Position");
            return null; 
        }

        /* Remove Piece from Board */
        pieces[x][y] = null;
        return piece;
    }

 
    /************************************
     ** Returns the Winner of the Game **
     ************************************/
    public String winner() {

        boolean fireAlive = false;
        boolean waterAlive = false;

        /* Scan for Pieces on Board */
        for (int x = 0; x < this.scale; x++) {
            for (int y = 0; y < this.scale; y++) {
                Piece piece = pieces[x][y];
                if (piece != null) {
                    if (piece.isFire()) {
                        fireAlive = true;
                    }
                    else {
                        waterAlive = true;
                    }
                }
            }
        }

        /* Check for Winner */
        if (fireAlive && !waterAlive) { return "Fire"; }
        if (!fireAlive && waterAlive) { return "Water"; }
        if (!fireAlive && !waterAlive) { return "No One"; }
        return null;
    }


    /*******************************************
     ** Returns True if Selection is Possible **
     *******************************************/
    public boolean canSelect(int x, int y) {

        /* Check Bounds */
        if (outOfBounds(x, y)) { return false; }

        Piece destination = pieceAt(x, y);

        /* Empty Square */
        if (destination == null) {
            if (selection != null) {

                /* Get Coordinates of Active Piece */
                int[] coordinates = getCoordinates(selection);

                /* Check if move is valid */
                boolean validMove = false;
                if (coordinates != null) {
                    validMove = validMove(coordinates[0], coordinates[1], x, y);
                }

                /* Has Not Moved Before */
                if (!hasMoved) {
                    if (validMove) { return true; }
                }

                /* Attempting Multi-Capture */
                if (hasMoved) {
                    if (selection.hasCaptured()) {

                        /* Check if Destination is a Jump */
                        int xDistance = 0;
                        int yDistance = 0;
                        if (coordinates != null) {
                            xDistance = Math.abs(coordinates[0] - x);
                            yDistance = Math.abs(coordinates[1] - y);
                        }

                        if ((validMove) && (xDistance == 2) && (yDistance == 2)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /* Square with Piece */
        if (destination.side() == turn) {
            if (!hasMoved) {
                if (selection == null) {
                    return true;
                }
                else {
                    return true;
                }
            }
        }

        return false;
    }


    /******************************
     ** Select a Square if Valid **
     ******************************/
    public void select(int x, int y) {

        /* Check Bounds */
        if (outOfBounds(x, y)) { return; }

        /* Selecting a Piece */
        if (pieceAt(x, y) != null) {
            selection = pieceAt(x, y);
        }
        else {

            /* Move Piece to Selection */
            selection.move(x, y);
            hasMoved = true;

            /* If Bomb has Captured */
            if (selection.hasCaptured()) {
                if (selection.isBomb()) {
                    selection = null;
                }
            }
        }
    }


    /***************************************
     ** Returns True if Turn can be Ended **
     ***************************************/
    public boolean canEndTurn() {
        return hasMoved;
    }


    /**************************
     ** End the current turn **
     **************************/
    public void endTurn() {

        /* Switch Player Turn */
        turn = (turn == 0) ? 1 : 0;

        /* Reset Variables */
        if (selection != null) { selection.doneCapturing(); }
        selection = null;
        hasMoved = false;
    }


    /*******************
     ** Extra Methods **
     *******************/
    private void drawBoard() {

        /* Set Board Size */
        StdDrawPlus.setXscale(0, scale);
        StdDrawPlus.setYscale(0, scale);

        /* Display blank board */
        for (int x = 0; x < this.scale; x++) {
            for (int y = 0; y < this.scale; y++) {
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            }
        }

        /* Code to Highlight Selected Piece */
        if (selection != null) {
            int[] coordinates = getCoordinates(selection);
            if (coordinates != null) {
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(coordinates[0] + .5, coordinates[1] + .5, .5);
            }
        }

        /* Display Each Peace in Pieces Array */
        for (int x = 0; x < this.scale; x++) {
            for (int y = 0; y < this.scale; y++) {
                Piece current = pieces[x][y];
                if (current != null) {

                    /* Check Piece Type */
                    String type = "pawn";
                    if (current.isBomb()) { type = "bomb"; }
                    if (current.isShield()) { type = "shield"; }
                    boolean king = current.isKing();

                    /* Display Piece */
                    String img = "img/".concat(type).concat("-").concat( (current.isFire() ? "fire" : "water") ).concat( king ? "-crowned" : "").concat(".png");
                    StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
                }
            }
        }
    }
    private boolean outOfBounds(int x, int y) {
        return ((x >= this.scale) || (y >= this.scale) || (x < 0) || (y < 0));
    }
    private int[] getCoordinates(Piece p) {
        if (p == null) { return null; }

        int[] coordinates = new int[2];

        /* Scan Through Board */
        for (int x = 0; x < this.scale; x++) {
            for (int y = 0; y < this.scale; y++) {
                if (p == pieces[x][y]) {
                    coordinates[0] = x;
                    coordinates[1] = y;
                    return coordinates;
                }
            }
        }

        return null;
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {

        Piece piece = pieceAt(xi, yi); // Get Current Piece
        Boolean isKing = piece.isKing(); // Check is piece is king

        /* Direction of Movement */
        boolean moveRight =  xf > xi; // if true, move to right
        boolean moveUp = yf > yi; // if true, move up
        boolean moveLeft = !moveRight;
        boolean moveDown = !moveUp;

        /* Distance of Movement */
        int yDistance = Math.abs(yi - yf);
        int xDistance = Math.abs(xi - xf);

        /* Check Bounds */
        if (outOfBounds(xf, yf)) { return false; }

        /* If Destination is Open */
        if (pieceAt(xf, yf) == null) {

            /* Check standard move */
            if ((yDistance == 1) && (xDistance == 1)) {

                if (isKing) { return true; }

                if (piece.isFire()) {
                    if (moveUp) { return true; } // Fire Only Moves Up
                }
                else { // Water piece
                    if (moveDown) { return true; }
                }
            }       

            /* Check jump move */
            if ((yDistance == 2) && (xDistance == 2)) {

                /* Get Piece Being Jumped Over */
                int yCheck = moveUp ? (yi + 1) : (yi - 1);
                int xCheck = moveRight ? (xi + 1) : (xi - 1);
                Piece jumpPiece = pieceAt(xCheck, yCheck);

                /* Check if Captured Piece is Present & on Opposite Team */
                boolean isPossible = ( (jumpPiece != null) && (piece.isFire() != jumpPiece.isFire()) );
                if (isPossible) {

                    if (isKing) { return true; }

                    if (piece.isFire()) { // Fire Only Moves Up
                        if (moveUp) { return true; }
                    }
                    else { // Water piece
                        if (moveDown) { return true; }
                    }
                }
            }
        }
        return false;
    }


    /********************************
     ** Begins GUI Version of Game **
     ********************************/
    public static void main(String[] args) {

        /* Starting a New Game */
        Board b = new Board(false);

        /* Running the Game */
        while(true) {

            /* Check for Winner */
            if (b.winner() != null) {
                return;
            }

            /* Mouse Click */
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) { b.select(x, y); } 
            }

            /* Spacebar Press */
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }

            /* Refresh Board */
            b.drawBoard();
            StdDrawPlus.show(25);

        }
    } // COMPLETE

}