public class Board {
    private static int boardSize = 8;
    private int player = 0;
    private boolean moved = false;
    private Piece selPiece;
    private Piece[][] location;
    private int[] selected = new int[2];

//==========================================DRAW BOARD========================================


    public Board(boolean shouldBeEmpty) {
        location = new Piece[boardSize][boardSize];
        // Resets select - off board
        selected[0] = -1;
        selected[1] = -1;

        if (shouldBeEmpty) {
        } // Creates an empty board if so desired
          // else, fully populated board

        else {
            // int i being the referenced space, counting from the left of the board, where
            // each piece begins to be placed in its row

            // Places Fire pieces
            for (int i = 0; i < boardSize; i += 2) new Piece(true, this, i, 0, "pawn");
            for (int i = 1; i < boardSize; i += 2) new Piece(true, this, i, 1, "shield");
            for (int i = 0; i < boardSize; i += 2) new Piece(true, this, i, 2, "bomb");
            // Places Water Pieces
            for (int i = 1; i < boardSize; i += 2) new Piece(false, this, i, 5, "bomb");
            for (int i = 0; i < boardSize; i += 2) new Piece(false, this, i, 6, "shield");
            for (int i = 1; i < boardSize; i += 2) new Piece(false, this, i, 7, "pawn");
        }
    }

    private void drawBoard() {
        for (int i = 0; i < boardSize; i += 1) {
            for (int j = 0; j < boardSize; j += 1) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }else {               
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawHighlight() {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(selected[0] + 0.5, selected[1] + 0.5, 0.5);
    }

    private void drawPieces() {
        for (int x = 0; x < boardSize; x += 1) {
            for (int y = 0; y < boardSize; y += 1) {
                if (isEmpty(x, y)) continue;
                Piece pieceBeingDrawn = pieceAt(x, y);
                // Sets directory where image files reside
                String imgPath;
                // determines what type pieceBeingDrawn is - directs appropriately
                if (pieceBeingDrawn.isBomb()) {
                    imgPath = "img/bomb";
                } else if (pieceBeingDrawn.isShield()) {
                    imgPath = "img/shield";
                } else {
                    imgPath = "img/pawn";
                }
                // determines what player pieceBeingDrawn belongs to - directs appropriately
                if (pieceBeingDrawn.isFire()) {
                    imgPath += "-fire";
                }
                else {
                    imgPath += "-water";
                }
                // determines if pieceBeingDrawn is a king - directs appropriately
                if (pieceBeingDrawn.isKing()) 
                    imgPath += "-crowned";
                imgPath = imgPath + ".png"; // completes image file path, draws
                StdDrawPlus.picture(x + 0.5, y + 0.5, imgPath, 1, 1);
            }
        }
    }

//=============================================END============================================


//====================================Initialization and Rules================================

    public static void main(String[] args) {
        Board b = new Board(false); //false boolean indicates that board should b epopulated
        StdDrawPlus.setScale(0, 8);
        String winningTeam = b.winner();

        // Loop runs the game until a player wins
        while (winningTeam == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }

            //Ends turn if player desires so
            if (StdDrawPlus.isSpacePressed()) { 
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }

            b.drawBoard();
            b.drawHighlight(); //By drawing highlight after board but before pieces,
            b.drawPieces();    // the highlight white square layer lies below the piece
            StdDrawPlus.show(25);
            winningTeam = b.winner();  // At end of one cycle, checks again if a plyer has won
        }

        // Once game ends (while loop exits), String printed on command line declaring winning game and ends the program
        System.out.println(winningTeam + " Team Wins!" );
        System.exit(0);
    }

    //Rules for Movement and Bounds
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (!(inBounds(xi, yi) && inBounds(xf, yf))) return false;

        if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) { // skip-over capture move
            Piece pieceType = pieceAt(xi, yi);
            // Next 2 if, else if statements returns false if a nonking member of a team tries to go backwards
            if (pieceType.isFire() && !pieceType.isKing()) { 
                if (yf < yi) return false;    
            } else if (!pieceType.isFire() && !pieceType.isKing()) {
                if (yf > yi) return false;    
            }
            // if capturedPiece DNE, a skip movement of 2 diagonal spaces returns false (not a capture)
            Piece capturedPiece = pieceAt((xi + xf) / 2, (yi + yf) / 2);
            return (capturedPiece != null && capturedPiece.side() != pieceType.side());

        } else if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) { // 1-space regular move
            Piece pieceType = pieceAt(xi, yi);
            // Next 2 if, else if statements returns false if a nonking member of a team tries to go backwards
            if (pieceType.isFire() && !pieceType.isKing()) { 
                if (yf < yi) return false;    
            } else if (!pieceType.isFire() && !pieceType.isKing()) {
                if (yf > yi) return false;    
            }
            return isEmpty(xf, yf); //true if final location is empty - can make move
        } else {
            return false; // if space occupied, piece cannot land - invalid move
        }
    }

    private boolean inBounds(int x, int y) {
        return !(x < 0 || x > boardSize - 1 || y < 0 || y > boardSize - 1);
    }

//=============================================END============================================


//=================================References to Location x,y ================================


    private boolean isEmpty(int x, int y) {
        return location[x][y] == null;
    }

    public Piece pieceAt(int x, int y) {
        if (inBounds(x, y)) {
            return location[x][y];
        } else return null;
    }

    public void place(Piece p, int x, int y) {
        if (!inBounds(x, y)) {
            System.out.println("Not Valid Square - Out of Bounds");
        } else {
            removePiece(p);
            location[x][y] = p;
        }
    }

//=============================================END============================================


//=======================================Select Methods=======================================
    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p != null) { // if location (x,y) contains a Piece
            return (!moved && p.side() == player); // must belong to current playing player, not have moved
        } else {
            if (selPiece != null) {
                if (moved) { // can select tile if already moved piece captured on last move, is valid move
                    return (selPiece.hasCaptured() && Math.abs(selected[0] - x) == 2 && validMove(selected[0], selected[1], x, y));
                } else {
                    return validMove(selected[0], selected[1], x, y);
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if (selPiece != null && pieceAt(x, y) == null) { // selecting an empty tile with prev selected
            selPiece.move(x, y);                         // piece moves the piece
            moved = true;
        } else {
            selPiece = pieceAt(x, y);   // New selected piece, x,y location bounded to coordinates [][]
        }
        selected[0] = x;
        selected[1] = y;
    }

//=============================================END============================================


//=========================Remove Methods for Board and Piece Classes=========================
    public Piece remove(int x, int y) {
        if (!inBounds(x, y)) {
            System.out.println("Index out of bounds");
            return null;
        } else if (location[x][y] == null) {
            return null;
        } else {
            Piece returnPiece = location[x][y];
            location[x][y] = null;
            return returnPiece;
        }
    }

    private void removePiece(Piece p) {
        for (int i = 0; i < boardSize; i += 1) { // finds if piece references is on the board
            for (int j = 0; j < boardSize; j += 1) {
                if (location[i][j] == p) location[i][j] = null; //replace piece with null
            }
        }
    }

//=============================================END============================================


//===================================After Piece Movement=====================================
    public boolean canEndTurn() {  //A player must move if it is their turn
        return moved;
    }

    public void endTurn() {
        if (selPiece != null) {
            selPiece.doneCapturing();
        }
        selPiece = null;
        player = Math.abs(player - 1);
        moved = false;
        selected[0] = -1;
        selected[1] = -1;
    }

    private int countPieces(int side) {
        int count = 0;
        for (int i = 0; i < boardSize; i += 1) {
            for (int j = 0; j < boardSize; j += 1) {
                Piece p = pieceAt(i, j);
                if (p != null) {
                    if (p.side() == side) {
                        count += 1;
                    }
                }

            }
        }
        return count;
    }

    public String winner() {
        int firePieces = countPieces(0);
        int waterPieces = countPieces(1);
        if (firePieces == 0 && waterPieces == 0) {
            return "No one";
        } else if (firePieces == 0) {
            return "Water";
        } else if (waterPieces == 0) {
            return "Fire";
        }
        return null;  // if both players still have pieces, continue loop found in main
    }

//=============================================END============================================

}