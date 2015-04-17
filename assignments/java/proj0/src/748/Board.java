public class Board{
    private boolean[][] pieces;
    private Piece[][] pieceGrid;

    // private static boolean[][] pieces = new boolean[8][8];
    // private static Piece[][] pieceGrid = new Piece[8][8];

    private int playerTurn = 0;
    private boolean playerHasMoved = false;

    private Piece activePiece = null;
    private int activePieceX = 10;
    private int activePieceY = 10;

    private static void drawBoard(Board b) {
        int N = 8;
        String drawType;
        String drawSide;
        String drawImagePath;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.pieces[i][j]) {
                    // Determine what image to draw based on piece properties.
                    if (b.pieceGrid[i][j].side() == 0){
                        drawSide = "fire";
                    } else {
                        drawSide = "water";
                    }

                    if (b.pieceGrid[i][j].isBomb()){
                        drawType = "bomb";
                    } else if (b.pieceGrid[i][j].isShield()){
                        drawType = "shield";
                    } else {
                        drawType = "pawn";
                    }

                    if (b.pieceGrid[i][j].isKing()){
                        drawImagePath = "img/" + drawType + "-" + drawSide + "-crowned.png";
                    } else {
                        drawImagePath = "img/" + drawType + "-" + drawSide + ".png";
                    }
                    StdDrawPlus.picture(i + .5, j + .5, drawImagePath, 1, 1);
                }
            }
        }
    }

    public Board(boolean shouldBeEmpty){
        /*3. Create Board.java so that when you type java Board, it displays a blank 8x8
        board. (You will be implementing parts of your board as you go along). See
        StdDrawDemo.java for inspiration. You do not need to cite this assistance in
        your source files.*/
        int N = 8;
        this.pieces = new boolean[N][N];
        this.pieceGrid = new Piece[N][N];

        // If should be empty is false, then create the standard layout.
        if (!shouldBeEmpty){
            for (int i = 0; i <= 7; i += 2){
                this.pieceGrid[i][0] = new Piece(true, this, i, 0, "pawn");
                this.pieces[i][0] = true;
            }
            for (int i = 1; i <= 7; i += 2){
                this.pieceGrid[i][1] = new Piece(true, this, i, 1, "shield");
                this.pieces[i][1] = true;
            }
            for (int i = 0; i <= 7; i += 2){
                this.pieceGrid[i][2] = new Piece(true, this, i, 2, "bomb");
                this.pieces[i][2] = true;
            }
            for (int i = 1; i <= 7; i += 2){
                this.pieceGrid[i][5] = new Piece(false, this, i, 5, "bomb");
                this.pieces[i][5] = true;
            }
            for (int i = 0; i <= 7; i += 2){
                this.pieceGrid[i][6] = new Piece(false, this, i, 6, "shield");
                this.pieces[i][6] = true;
            }
            for (int i = 1; i <= 7; i += 2){
                this.pieceGrid[i][7] = new Piece(false, this, i, 7, "pawn");
                this.pieces[i][7] = true;
            }
        }
    }

    public void select(int x, int y){
        Piece thisPiece = pieceAt(x, y);
        // Highlight (or rather flash) the selection as white.
        // This is disabled for the autgarder submission because
        // it fucks up the autograder like no other.
        // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        // StdDrawPlus.filledSquare(x + .5, y + .5, .5);

        if (thisPiece == null && activePiece != null){
            // If the selected space is empty, and there is a preselected piece
            if (validMove(activePieceX, activePieceY, x, y)){
                if (playerHasMoved == false || (playerHasMoved && activePiece.hasCaptured()) && moveIsCapture(activePieceX, activePieceY, x, y)){
                    place(activePiece, x, y);
                    remove(activePieceX, activePieceY);
                    if (activePiece.hasCaptured() && activePiece.isBomb()){
                        remove(x, y);
                        activePiece.doneCapturing();
                        activePiece = null;
                        playerHasMoved = true;
                    } else {
                        activePieceX = x;
                        activePieceY = y;
                        playerHasMoved = true;
                    }
                }
            }
        } else {
            activePiece = thisPiece;
            activePieceX = x;
            activePieceY = y;
        }
    }

    public Piece pieceAt(int x, int y){
        // Returns the piece at this point. If no piece, return null.
        if (x > 7 || y > 7){
            return null;
        } else {
            return pieceGrid[x][y];
        }
    }

    public Piece remove(int x, int y){
        if (x > 7 || y > 7){
            return null;
        }
        Piece toBeRemoved = pieceAt(x,y);
        if (toBeRemoved == null){
            pieceGrid[x][y] = null;
            pieces[x][y] = false;
            return null;
        }
        pieceGrid[x][y] = null;
        pieces[x][y] = false;
        return toBeRemoved;
    }

    public void place(Piece p, int x, int y){
        if (p != null && x < 8 && y < 8){
            p.move(x, y);
            pieceGrid[x][y] = p;
            pieces[x][y] = true;
        }
    }

    private boolean moveIsCapture(int xi, int yi, int xf, int yf){
        int dx = xf - xi;
        int dy = yf - yi;
        Piece pieceBeingMoved = pieceAt(xi, yi);
        if (!correctDirection(xi, yi, xf, yf)){
            return false;
        }
        Piece pieceDestination = pieceAt(xf, yf);
        Piece intermediatePiece = pieceAt((xi + xf) / 2, (yi + yf) / 2);
        if (Math.abs(dx) != 2 || Math.abs(dy) != 2 || pieceDestination != null || pieceBeingMoved == null){
            return false;
        } else {
            if (intermediatePiece == null || intermediatePiece.side() == pieceBeingMoved.side()){
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean canMoveAgain(int xi, int yi, int xf, int yf){
        int dx = xf - xi;
        int dy = yf - yi;
        if (Math.abs(dx) != 2 || Math.abs(dy) != 2){
            return false;
        }
        Piece pieceBeingMoved = pieceAt(xi, yi);

        if (pieceBeingMoved.hasCaptured() && moveIsCapture(xi, yi, xf, yf)){
            return true;
        } else {
            return false;
        }
    }

    private boolean correctDirection(int xi, int yi, int xf, int yf){
        /*Determines if the current move is in the correct direction*/
        int dy = yf - yi;
        Piece originPiece = pieceAt(xi, yi);
        if (originPiece == null){
            return false;
        } else if (originPiece.isKing()){
            return true;
        } else {
            if (originPiece.side() == 0 && dy > 0){
                return true;
            } else if (originPiece.side() == 1 && dy < 0){
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        // Returns if the move is valid from a geometric/piece race point
        // of view. 
        Piece pieceBeingMoved = pieceAt(xi, yi);
        int dx = xf - xi;
        int dy = yf - yi;
        // We can only move diagonally.
        if (Math.abs(dx) != Math.abs(dy)){
            return false;
        }
        // We must move. Cannot move 0 units.
        if (dx == 0 && dy == 0){
            return false;
        }
        // You can only move again if the last move was a capture, and you're capturing again.
        if (playerHasMoved){
            return canMoveAgain(xi, yi, xf, yf);
        }
        // If we are jumping two units, we are capturing.
        // For this to be valid, there needs to be an opponent's piece
        // in the intermediate square.
        if (Math.abs(dx) == 2){
            // Piece intermediatePiece = pieceAt((xi + xf) / 2, (yi + yf) / 2);
            // if (intermediatePiece == null || intermediatePiece.side() == pieceBeingMoved.side()){
            if (!moveIsCapture(xi, yi, xf, yf)){
                return false;
            }
        }
        // Cannot move into a occupied space.
        if (pieceAt(xf, yf) != null){
            return false;
        }
        // Kings can move in any direction.
        if (pieceBeingMoved.isKing()){
            if (Math.abs(dx) <= 2 && Math.abs(dy) <= 2 && dx != 0 && dy != 0){
                return true;
            } else {
                return false;
            }
        } else if (!correctDirection(xi, yi, xf, yf)){
            return false;
        } else {
            if (pieceBeingMoved.side() == 0){
                // This is then a fire piece, and can only move up.
                if (Math.abs(dx) <= 2 && dx != 0 && dy <= 2 && dy > 0){
                    return true;
                } else {
                    return false;
                }
            } else {
                // This is then a water piece, and can only move down.
                if (Math.abs(dx) <= 2 && dx != 0 && dy >= -2 && dy < 0){
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public boolean canSelect(int x, int y){
        Piece playerSelection = pieceAt(x, y);
        // If an empty space is selected, it is only valid if the player
        // - the player has selected a Piece which hasn’t moved yet and is
        // selecting an empty spot which is a valid move for the previously
        // selected Piece.
        // - selected a Piece, captured, and has selected another valid
        // capture destination. When performing multi-captures, you should
        // only select the active piece once; all other selections should
        // be valid destination points.
        if (playerSelection == null){
            if (activePiece != null && playerHasMoved == false){
                return true;
            } else if (activePiece != null && activePiece.hasCaptured() && moveIsCapture(activePieceX, activePieceY, x, y)) {
                return true;
            } else {
                return false;
            }
        // If a piece space is selected, it is only valid if the player
        // - has not yet selected a piece
        // - has selcted a piece, but did not move it
        } else {
            if (playerSelection.side() == playerTurn && playerHasMoved == false){
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean canEndTurn(){
        // A player can only end his turn after he's made a move.
        if (playerHasMoved){
            return true;
        } else {
            return false;
        }
    }

    public void endTurn(){
        // Upon ending the turn, set the movement tracker back to false
        // reset the activePiece, and switch turns.
        playerHasMoved = false;
        if (activePiece != null){
            activePiece.doneCapturing();
            activePiece = null;
        }
        if (playerTurn == 0){
            playerTurn = 1;
        } else {
            playerTurn = 0;
        }
    }

    public String winner(){
        Piece currPiece;
        int firePieceCount = 0;
        int waterPieceCount = 0;
        // Walk through the entire grid, and count number of pieces
        // for each side to determine the winner.
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                currPiece = pieceAt(i,j);
                if (currPiece != null){
                    if (currPiece.side() == 0){
                        firePieceCount++;
                    } else {
                        waterPieceCount++;
                    }
                }
            }
        }
        if (firePieceCount == 0 && waterPieceCount == 0){
            return "No one";
        } else if (firePieceCount == 0){
            return "Water";
        } else if (waterPieceCount == 0){
            return "Fire";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        // Initialize all the pieces
        // int N = 8;
        // pieces = new boolean[N][N];
        // pieceGrid = new Piece[N][N];

        Board b = new Board(false);
        // b.playGame();

        // Let's try reimplementing playGame() below.
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        // Loop while there's no winner.
        while(b.winner() == null) {
            drawBoard(b);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xInt = (int) x;
                int yInt = (int) y;
                if (b.canSelect(xInt, yInt)){
                    b.select(xInt, yInt);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                // Space is supposed to switch turns. If the player can end his
                // turn, then switch turns.
                if (b.canEndTurn()){
                    b.endTurn();
                }
            }       
            StdDrawPlus.show(100);
        }
    }
}