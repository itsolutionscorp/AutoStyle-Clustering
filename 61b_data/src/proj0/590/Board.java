public class Board {

    private static int n = 8;
    private Piece[][] pieces = new Piece[n][n];
    private boolean[][] validBox = new boolean[n][n];
    // private boolean isAPieceCurrentlySelected = false;
    private Piece selectedPiece;
    private int currentlyPlaying = 1;
    private int movesThisTurn = 0;
    private int selectedX = 0;
    private int selectedY = 0;
    private int selectedPieceX = 0;
    private int selectedPieceY = 0;
    private boolean pieceMovedThisTurn = false;
    private boolean pieceCapturedThisTurn = false;

    public static void main(String[] args) {
        
        Board B = new Board(false);
        B.drawBoard(false);
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.println("pieces[" + i + "][" + j +"] is: " + pieces[i][j]);
        //     }
        // }
        // System.out.println()
        while ((B.winner() != "Fire") && (B.winner() != "Water") && (B.winner() != "No one")) {
            B.mainStuff();
        }
        System.out.println(B.winner() + " won!");
    }

    private void mainStuff() {
        
        if (StdDrawPlus.mousePressed()) {
            int mouseX = (int) StdDrawPlus.mouseX();
            int mouseY = (int) StdDrawPlus.mouseY();
            
            // System.out.println("Can we select this? " + this.canSelect(mouseX, mouseY));
            // System.out.println(this.pieceAt(mouseX, mouseY));
            // if (this.pieceAt(mouseX, mouseY) != null) {
            //     System.out.println("is this fire? " + this.pieceAt(mouseX, mouseY).isFire());
            // }
            // System.out.println("who is currently playing? " + this.currentlyPlaying);
            // System.out.println("mouse x? " + mouseX);
            // System.out.println("mouse y? " + mouseY);

            // if (validBox[mouseX][mouseY]) {
            //     selectDrawingStuff(mouseX, mouseY);
            // }


            // if (this.canSelect(mouseX, mouseY)) {
            //     System.out.println("This one works");
            // }
            // else {
            //     System.out.println("Not this one");   
            // }
            //     this.select(mouseX, mouseX);
            if ((mouseX < 0) || (mouseY < 0) || (mouseX >= n) || (mouseY >= n)) {
                return;
            }

            else if (this.canSelect(mouseX, mouseY)) {
                // System.out.println(this.canSelect(mouseX, mouseY));
                // System.out.println();
                // System.out.println("We can select this!");
                // System.out.println("old selected x? " + this.selectedX);
                // System.out.println("old selected y? " + this.selectedY);
                // System.out.println("old selected piece x? " + this.selectedPieceX);
                // System.out.println("old selected piece y? " + this.selectedPieceY);
                // System.out.println("x-distance: " + Math.abs(this.selectedX - mouseX));
                // System.out.println("y-distance: " + Math.abs(this.selectedY - mouseY));
                this.select(mouseX, mouseY);
                // System.out.println("new selected x? " + this.selectedX);
                // System.out.println("new selected y? " + this.selectedY);
                // System.out.println("new selected piece x? " + this.selectedPieceX);
                // System.out.println("new selected piece y? " + this.selectedPieceY);
                // System.out.println("");
                // System.out.println("");
                // System.out.println("");
                
                this.selectDrawingStuff(mouseX, mouseY);
            }

            //     (pieces[mouseX][mouseY] == null) {
            //     Piece p = new Piece(false, this, mouseX, mouseY, "pawn");
            //     this.place(p, mouseX, mouseY);
            //     this.drawPieces();
            // }

               
            //     // code for making that square go white
                
            // }
        }
        if (StdDrawPlus.isSpacePressed()) {
            // System.out.println("Space pressed!");
            if (this.canEndTurn()) {
                this.endTurn();
            }
        }

        // }
    }

    public Board(boolean shouldBeEmpty) {
        this.pieces = new Piece[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    this.validBox[i][j] = true;
                }
                else {
                    this.validBox[i][j] = false;
                }
            }
        }

        if (!shouldBeEmpty) {

            for (int i = 0; i < n; i++) { // Row number
                for (int j = 0; j < n; j++) { // Column number
                    if (((i + j) % 2) == 0) {
                        if (j == 0) {
                            this.pieces[(int) i][(int) j] = new Piece(true, this, i, j, "pawn");
                        }
                        else if (j == 1) {
                            this.pieces[(int) i][(int) j] = new Piece(true, this, i, j, "shield");
                        }
                        else if (j == 2) {
                            this.pieces[(int) i][(int) j] = new Piece(true, this, i, j, "bomb");
                        }
                        else if (j == (n - 3)) {
                            this.pieces[(int) i][(int) j] = new Piece(false, this, i, j, "bomb");
                        }
                        else if (j == (n - 2)) {
                            this.pieces[(int) i][(int) j] = new Piece(false, this, i, j, "shield");
                        }
                        else if (j == (n - 1)) {
                            this.pieces[(int) i][(int) j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }
        }
        
    }

    private void drawBoard(boolean shouldBeEmpty) {
        StdDrawPlus.setXscale(0, n);
        StdDrawPlus.setYscale(0, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        if (!shouldBeEmpty) {
            this.drawPieces();
        }
        // System.out.println("It let me draw the board!");
        // StdDrawPlus.show(100);
    }

    private void drawPieces() {
        // System.out.println("I just wanted to print something!");
        for (int i = 0; i < n; i++) { // Row number
            for (int j = 0; j < n; j++) { // Column number
                if (pieces[i][j] != null) {
                    String n = "";
                    Piece p = pieces[i][j];
                    if (p.isFire()) {
                        n = "fire";
                    }
                    else {
                        n = "water";
                    }
                    String pieceType = "pawn";
                    if (p.isBomb()) {
                        pieceType = "bomb";
                    }
                    if (p.isShield()) {
                        pieceType = "shield";
                    }
                    String m = "";
                    if (p.isKing()) {
                        m = "-crowned";
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType + "-" + n + m + ".png", 1, 1);
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        // if ((x < 0) || (y < 0) || (x > (n - 1)) || (y > (n - 1))) {
        if ((x < 0) || (y < 0) || (x >= n) || (y >= n)) {
            return null;
        }
        else {
            return pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y) {
        // System.out.println();
        // System.out.println();
        // System.out.println();
        // System.out.println("Can I select (" + x + ", " + y + ")?");
        
        /* We can select any piece as long as we haven't moved
         * any other piece so far.
         * Or, if this is a valid move for the selected piece
         */

        Piece p = pieceAt(x,y);
        // System.out.println("The piece at (" + x + ", " + y + ") is: " + p);
        // System.out.println("In canSelect, Piece p is: " + p);
        // If empty box, check whether there's a selected piece, and 
        // whether it's a valid move for that selected piece.
        
        // System.out.println("1");
        if (p == null) {
            // System.out.println("The piece at the selected spot is null");
            // System.out.println("2");
            if ((selectedPiece == null) || (!pieceCapturedThisTurn && pieceMovedThisTurn) || (pieceCapturedThisTurn && (Math.abs(x - this.selectedPieceX) == 1))) {
                // System.out.println("Either we don't have a piece already selected, we moved without capturing, or we captured and are trying to move one square");
                // System.out.println("3");
                return false;
            }
            // System.out.println("In canSelect, selectedPieceX is: " + this.selectedPieceX);
            else {
                // System.out.println("It's not null, and we have no reason to suspect it's invalid.");
                if (this.validMove(this.selectedPieceX, this.selectedPieceY, x, y)) {
                    // System.out.println("This is a valid move.");
                    // System.out.println("4");
                    return true;
                }
            }
        }
        else {
            // System.out.println("The piece at the selected spot is NOT null");
            // System.out.println("moves this turn: " + this.movesThisTurn);
            // System.out.println("5");
            if (this.movesThisTurn == 0) {
                // System.out.println("We haven't moved any pieces this turn.");
                // System.out.println("5");
                if (((this.currentlyPlaying == 1) && (p.isFire() == true)) || ((this.currentlyPlaying == -1) && (p.isFire() == false))) {
                    // System.out.println("This is the correct side being selected.");
                    // System.out.println("6");
                    return true;
                }
            } 
        }
        // System.out.println("Nothing worked? IDK");
        return false;

        /*
        
        boolean selectingFirstPiece = (pieceAt(x,y) != null) && !isAPieceCurrentlySelected;
        boolean selectingAnotherPiece = (pieceAt(x,y) != null) && isAPieceCurrentlySelected // && Something to see whether the previously selected piece has been moved or not;
        boolean makingAMove = isAPieceCurrentlySelected // && Something to test whether this is a valid move;
        return (selectingFirstPiece || makingAMove);
        
        */
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        // System.out.println("Is it a valid move to move from (" + xi + ", " + yi + "), to (" + xf + ", " + yf + ")?");
        // Returns whether or not a piece is allowed from position i to position f
        // System.out.println("In validMove, xi (x of the piece at the initial point) is: " + xi);
     //    System.out.println("In validMove, yi (y of the piece at the initial point) is: " + yi);
        if ((xi >= n) || (yi >= n)|| (xf >= n) || (yf >= n) || (xi < 0) || (yi < 0) || (xf < 0) || (yf < 0) || (!validBox[xf][yf]) || (pieceAt(xi,yi) == null) || (pieceAt(xf,yf) != null) || (Math.abs(xi-xf) != Math.abs(yi-yf)) || (Math.abs(xi-xf) > 2) || (Math.abs(yi-yf) > 2)) {
            // System.out.println("Out of bounds");
            return false;
        }

        // Additional test, repeated nicely below
        // || (((Math.abs(xi-xf) == 2) || (Math.abs(yi-yf) == 2))) && (pieceAt(((int) (xf + xi)/2), ((int) (yf + yi)/2)) == null)


        // For two-box moves, is there a valid piece in the middle to kill?
        Piece p = pieceAt(xi,yi);
        // System.out.println("The piece at the initial spot is: " + p);

        // System.out.println("In validMove, p (the piece at the initial point) is: " + p);

        boolean moveAllowed;
        if ((Math.abs(xi-xf) == 2) || (Math.abs(yi-yf) == 2)) {
            // System.out.println("Inside the first if!");
            int xAve = (int) ((xi + xf)/2);
            int yAve = (int) ((yi + yf)/2);
            // System.out.println("xAve is: " + xAve);
            // System.out.println("yAve is: " + yAve);
            if ((!p.isKing()) && ((((yAve - yi) > 0) && (!p.isFire())) || (((yAve - yi) < 0) && (p.isFire())))) {
                // System.out.println("Inside the second if!");
                return false;
            }
            Piece middlePiece = pieceAt(xAve, yAve);
            if ((middlePiece != null) && (middlePiece.isFire() != p.isFire())) {
                // System.out.println("Inside the third if!");
                return true;
            }
            else {
                return false;
            }
        }

        if (p.isKing()) {
            moveAllowed = ((xf == (xi - 1)) || (xf == (xi + 1))) && ((yf == (yi + 1)) || (yf == (yi - 1)));
        }

        else if (p.isFire()) {
            // System.out.println("This is a fire piece");

            moveAllowed = ((xf == (xi - 1)) || (xf == (xi + 1))) && (yf == (yi + 1));
        }
        else {
            // System.out.println("This is a water piece");

            moveAllowed = ((xf == (xi - 1)) || (xf == (xi + 1))) && (yf == (yi - 1));
        }
        return moveAllowed;
    }
    
    public void select(int x, int y) {
        // Assume canSelect(x,y) == true
        
        Piece p = this.pieceAt(x,y);
        int oldSelectedX = this.selectedX;
        int oldSelectedY = this.selectedY;
        this.selectedX = x;
        this.selectedY = y;
        this.selectedPieceX = x;
        this.selectedPieceY = y;
        if (p == null) {
            this.movesThisTurn += 1;
            this.pieceMovedThisTurn = true;
            this.selectedPiece.move(x, y);
            if (Math.abs(oldSelectedY - selectedY) == 2) {
                this.pieceCapturedThisTurn = true;
            }

            // if (this.selectedPiece.isFire() && this.selectedY == 7) {
            //     this.selectedPiece.
            // }
        }
        else {
            
            this.selectedPiece = p;
            // this.isAPieceCurrentlySelected = true;
            // this.pieceMovedThisTurn = true;
            // System.out.println("After the select method, selected x is: " + this.selectedX);
            // System.out.println("After the select method, selected y is: " + this.selectedY);
        }
        
    }

    private void selectDrawingStuff(int x, int y) {
        this.drawBoard(true);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        this.drawPieces();
        
        // this.drawBoard(false);
        // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        // StdDrawPlus.filledSquare(x + .5, y + .5, .5);

    }

    public void place(Piece p, int x, int y) {
        if ((x >= n) || (y >= n) || (x < 0) || (y < 0) || (p == null)) {
            return;
        }
        int a = n + 1;
        int b = n + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((pieces[i][j] != null) && (pieces[i][j].equals(p))) {
                    a = i;
                    b = j;
                }
            }
        }
        pieces[x][y] = p;
        if (a != n + 1) {
            this.remove(a, b);
        }
    }

    public Piece remove(int x, int y) {
        Piece temp = pieces[x][y];
        pieces[x][y] = null;
        return temp;
    }

    public boolean canEndTurn() {
        return ((this.movesThisTurn > 0) || this.pieceMovedThisTurn);
        // return pieceMovedThisTurn;
    }

    public void endTurn() {
        // this.isAPieceCurrentlySelected = false;
        this.selectedPiece = null;
        this.currentlyPlaying *= -1;
        this.movesThisTurn = 0;
        this.selectedX = 0;
        this.selectedY = 0;
        this.selectedPieceX = 0;
        this.selectedPieceY = 0;
        this.pieceMovedThisTurn = false;
        this.pieceCapturedThisTurn = false;
        this.drawBoard(true);
        this.drawPieces();
        // this.drawBoard(false);


        return;
    }

    public String winner() {
        boolean anyFirePieces = false;
        boolean anyWaterPieces = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Piece temp = this.pieceAt(i,j);
                if (temp != null) {
                    if (temp.isFire()) {
                        anyFirePieces = true;
                    }
                    else {
                        anyWaterPieces = true;
                    }
                }
            }
        }
        if (anyFirePieces && !anyWaterPieces) {
            return "Fire";
        }
        if (!anyFirePieces && anyWaterPieces) {
            return "Water";
        }
        if (!anyFirePieces && !anyWaterPieces) {
            return "No one";
        }
        return null;
    }
}