public class Board {
    // for reds, moving up and left not deleting, for blues moving down and right not deleting --> fixed when i added in kings... idk why
    // if its a king, then we need to allow the pieces to move the other way --> make this --> implemented
    // bomb capturing --> ** see if you need to fix how it updates
    // shields are immune to those --> added 
    // make winner work 
    // multicapture

    private Piece[][] pieces;
    private boolean boardBool;
    private Piece selectedPiece;
    private int currentPlayer; 
    private boolean hasMoved; 
    private boolean hasSelected; 
    private int xi;
    private int yi;
    private int selectedPieceX;
    private int selectedPieceY;
    private int mousePressedCount;
    private int fireCount; 
    private int waterCount; 
    private int totalPieceCount; 
    private boolean enableMultiCapture; 
    // private int xVal;
    // private int yVal; 
    // private int x2;
    // private int y2;

    public Board(boolean shouldbeEmpty) {

        boardBool = shouldbeEmpty;
        currentPlayer = 0; 
        selectedPiece = null;
        hasMoved = false;
        hasSelected = false;
        

        if (boardBool) {
            initializeEmptyArray();
            }
        else {
            initializeFullArray();
        }
   
        }

    private void initializeFullArray() {
        pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // draw fire pawns 
                if (j == 0) {
                    if (i % 2 == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                }  
                // draw fire shields
                if (j == 1) {
                    if (i % 2 != 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                }
                // draw fire bombs
                if (j == 2) {
                    if (i % 2 == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                }
                // draw water pawns
                if (j == 7) {
                    if (i % 2 != 0) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
                // draw water shields
                if (j == 6) {
                    if (i % 2 == 0) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                }
                // draw water bombs
                if (j == 5) {
                    if (i % 2 != 0) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                }
            }
      }

    }

    private void initializeEmptyArray() {
        pieces = new Piece[8][8];
    }


    private void drawBoard() {
//        hasMoved = false; 
        if (!boardBool) { 
            StdDrawPlus.setXscale(0, 8);
            StdDrawPlus.setYscale(0, 8);
            drawFullBoard(8);
        }
        else {
            StdDrawPlus.setXscale(0, 8);
            StdDrawPlus.setYscale(0, 8);
            drawEmptyBoard(8);
            }


    }

    private void drawEmptyBoard(int N) { 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
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
    }

    private void drawFullBoard(int N) { 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {


                if (selectedPiece != null) {
                    if (!selectedPiece.isBomb() || !selectedPiece.hasCaptured()) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(selectedPieceX + .5, selectedPieceY + .5, .5);
                        StdDrawPlus.picture(selectedPieceX + .5, selectedPieceY + .5, getImgPath(selectedPiece), 1, 1);
                    }
                }

                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (pieceAt(i,j) !=  null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImgPath(pieceAt(i,j)), 1, 1);
                }
            }
      }

    }

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        }
        if (pieces[x][y] == null) {
            return null;
        }
        else { 
            return pieces[x][y];
        }
        
            
    }

    public boolean canSelect(int x, int y) { 
        if (pieceAt(x, y) == null) {
            if (pieceAt(selectedPieceX, selectedPieceY) != null && hasSelected && pieceAt(selectedPieceX, selectedPieceY).hasCaptured() && validMove(selectedPieceX, selectedPieceY, x, y) && enableMultiCapture){
                pieceAt(selectedPieceX, selectedPieceY).doneCapturing();
                return true;
            } 
            if (!hasSelected || hasMoved || !validMove(selectedPieceX, selectedPieceY, x, y)) {

                return false; 
            }
            
                    
        }
        // IF HAS CAPTURED, RETURN TRUE

        else {
            if (currentPlayer != pieceAt(x,y).side()) {
                return false; // checks if you're selecting piece on right side
            }
            if (hasMoved == true) {
                return false; 
            }
            if (hasSelected == true && (hasMoved == true || pieceAt(x,y).hasCaptured())) {
                return false;
            }
        }
        return true;
    }

    private boolean validMove(int currentX, int currentY, int x, int y) {
        // for pieces that are not kings
        if (!enableMultiCapture) {
            if (pieceAt(currentX, currentY).isKing() == false) {
                if (pieceAt(currentX, currentY).side() == 0) {
                    if (currentX+2 == x && currentY+2 == y) {
                        if (pieceAt(currentX + 1, currentY + 1) != null && pieceAt(currentX + 1, currentY + 1).side() != currentPlayer ) {
                            enableMultiCapture = true;
                            return true;
                        }
                    }
                    if (currentX-2 == x && currentY+2 == y) {
                        if (pieceAt(currentX - 1, currentY + 1) != null && pieceAt(currentX - 1, currentY + 1).side() != currentPlayer) {
                            enableMultiCapture = true;
                            return true;
                        }
                    }  
                    if ((currentX+1 == x && currentY+1 == y) || (currentX-1 == x && currentY+1 == y)) {
                        return true;
                    }
                    if (pieceAt(x, y) != null) {
                        return false; 
                    }
                }
                if (pieceAt(currentX, currentY).side() == 1) {
                    if (currentX+2 == x && currentY-2 == y) {
                        if (pieceAt(currentX + 1, currentY - 1) != null && pieceAt(currentX + 1, currentY - 1).side() != currentPlayer) {
                            enableMultiCapture = true;
                            return true;
                        }
                    }
                    if (currentX-2 == x && currentY-2 == y) {
                        if (pieceAt(currentX - 1, currentY - 1) != null && pieceAt(currentX - 1, currentY - 1).side() != currentPlayer) {
                            enableMultiCapture = true;
                            return true;
                        }
                    }
                    if ((currentX-1 == x && currentY-1 == y) || (currentX+1 == x && currentY-1 == y)) {
                        return true;
                    }
                    if (pieceAt(x, y) != null) {
                        return false; 
                    }
                }
            }
        // for pieces that are kings
            if (pieceAt(currentX, currentY).isKing()) {  
                if (currentX+2 == x && currentY+2 == y) {
                    if (pieceAt(currentX + 1, currentY + 1) != null && pieceAt(currentX + 1, currentY + 1).side() != currentPlayer ) {
                        enableMultiCapture = true;
                        return true;
                    }
                }
                if (currentX-2 == x && currentY+2 == y) {
                    if (pieceAt(currentX - 1, currentY + 1) != null && pieceAt(currentX - 1, currentY + 1).side() != currentPlayer) {
                        enableMultiCapture = true;
                        return true;
                    }
                }  
                if ((currentX+1 == x && currentY+1 == y) || (currentX-1 == x && currentY+1 == y)) {
                    return true;
                }
                if (pieceAt(x, y) != null) {
                    return false; 
                }
                if (currentX+2 == x && currentY-2 == y) {
                    if (pieceAt(currentX + 1, currentY - 1) != null && pieceAt(currentX + 1, currentY - 1).side() != currentPlayer) {
                        enableMultiCapture = true;
                        return true;
                    }
                }
                if (currentX-2 == x && currentY-2 == y) {
                    if (pieceAt(currentX - 1, currentY - 1) != null && pieceAt(currentX - 1, currentY - 1).side() != currentPlayer) {
                        enableMultiCapture = true;
                        return true;
                    }
                }
                if ((currentX-1 == x && currentY-1 == y) || (currentX+1 == x && currentY-1 == y)) {
                    return true;
                }
                if (pieceAt(x, y) != null) {
                    return false; 
                }
            }

            return false;


       }


       if (enableMultiCapture) {
        
        if (pieceAt(currentX, currentY).isKing() == false) {
                if (pieceAt(currentX, currentY).side() == 0) {
                    if (currentX+2 == x && currentY+2 == y) {
                        if (pieceAt(currentX + 1, currentY + 1) != null && pieceAt(currentX + 1, currentY + 1).side() != currentPlayer ) {
                            pieceAt(currentX, currentY).doneCapturing();
                            return true;
                        }
                    }
                    if (currentX-2 == x && currentY+2 == y) {
                        if (pieceAt(currentX - 1, currentY + 1) != null && pieceAt(currentX - 1, currentY + 1).side() != currentPlayer) {
                            pieceAt(currentX, currentY).doneCapturing();
                            return true;
                        }
                    }  
        
                    if (pieceAt(x, y) != null) {
                        return false; 
                    }
                }
                if (pieceAt(currentX, currentY).side() == 1) {
                    if (currentX+2 == x && currentY-2 == y) {
                        if (pieceAt(currentX + 1, currentY - 1) != null && pieceAt(currentX + 1, currentY - 1).side() != currentPlayer) {
                           pieceAt(currentX, currentY).doneCapturing();
                            return true;
                        }
                    }
                    if (currentX-2 == x && currentY-2 == y) {
                        if (pieceAt(currentX - 1, currentY - 1) != null && pieceAt(currentX - 1, currentY - 1).side() != currentPlayer) {
                            pieceAt(currentX, currentY).doneCapturing();
                            return true;
                        }
                    }
                    
                    if (pieceAt(x, y) != null) {
                        return false; 
                    }
                }
                return false;
            }
        // for pieces that are kings
            if (pieceAt(currentX, currentY).isKing()) {  
                if (currentX+2 == x && currentY+2 == y) {
                    if (pieceAt(currentX + 1, currentY + 1) != null && pieceAt(currentX + 1, currentY + 1).side() != currentPlayer ) {
                        pieceAt(currentX, currentY).doneCapturing();
                        return true;
                    }
                }
                if (currentX-2 == x && currentY+2 == y) {
                    if (pieceAt(currentX - 1, currentY + 1) != null && pieceAt(currentX - 1, currentY + 1).side() != currentPlayer) {
                        pieceAt(currentX, currentY).doneCapturing();
                        return true;
                    }
                }  
                
                if (pieceAt(x, y) != null) {
                    return false; 
                }
                if (currentX+2 == x && currentY-2 == y) {
                    if (pieceAt(currentX + 1, currentY - 1) != null && pieceAt(currentX + 1, currentY - 1).side() != currentPlayer) {
                        pieceAt(currentX, currentY).doneCapturing();
                        return true;
                    }
                }
                if (currentX-2 == x && currentY-2 == y) {
                    if (pieceAt(currentX - 1, currentY - 1) != null && pieceAt(currentX - 1, currentY - 1).side() != currentPlayer) {
                        pieceAt(currentX, currentY).doneCapturing();
                        return true;
                    }
                }
                
                if (pieceAt(x, y) != null) {
                    return false; 
                }
            }
            enableMultiCapture = false;
            return false; 
        
       }

        return false; 
    }


    public void select(int x, int y) {
        // make sure that the piece being selected is of the right side      
            if (pieceAt(x, y) == null) {
                if (selectedPiece != null) {
                    selectedPiece.move(x, y);
                    hasMoved = true;

                }             
            }
            else {

                selectedPiece = pieceAt(x,y);
                hasSelected = true;
            }
        selectedPieceX = x;
        selectedPieceY = y;
    }

    private String getImgPath(Piece p) { 

        String img = "img/";

        if (p.isBomb()) {
            img = img + "bomb-";
        }
        if (p.isShield()) {
            img = img + "shield-";
        }

        if (!p.isBomb() && !p.isShield()) {
            img = img + "pawn-";
        }
        if (p.isFire()) {

            img = img + "fire";
        }
        if (!p.isFire()) {

            img = img + "water";
        }
        if (p.isKing()) {

            img = img + "-crowned";
        }

        img = img + ".png";

        return img;

    }

    public void place(Piece p, int x, int y) {

        if (x < 8 || y < 8) {
         

            if (pieceAt(x, y) != null) {
                remove(x,y);
            }

        pieces[x][y] = p;

        }
    }

    public Piece remove(int x, int y) {

        if (x > 8 || y > 8) {
            System.out.println("X and Y parameters out of bounds!");
            return null;
        }

        if (pieceAt(x,y) == null) {
            System.out.println("No piece at this location!");
            return null;
        }
        else {
            Piece saved = pieceAt(x, y);
            pieces[x][y] = null;
            hasMoved = true;

            return saved; 
        }

    }

    public boolean canEndTurn() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { 
                if (pieces[i][j] != null) {
                    if (pieces[i][j].hasCaptured() || hasMoved) {
                        return true; 
                    }   
                
                }
            }
        }
        return false; 
    }

    public void endTurn() {
        //Switch players
        if (currentPlayer == 0) 
        {
            currentPlayer = 1;
        }
        else {
            currentPlayer = 0;
        }
        // call doneCapturing, and set hasMoved to false; 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { 
                if (pieceAt(i,j) != null) {
                    if (pieceAt(i,j).hasCaptured()) {
                    pieceAt(i,j).doneCapturing(); 
                    }

                    if (hasMoved) { 
                        hasMoved = false; 
                    }
                }   
            }
        }
        
        enableMultiCapture = false;
        selectedPiece = null;
        hasSelected = false; 
        winner();
    } 

    public String winner() { // another issue could be that you have to select a piece before winner is called (related to below issue)
        fireCount = 0; 
        waterCount = 0; 
        totalPieceCount = 0; 
        // if winner test isnt passing due to the "no one wins" condition, see if fixing the bomb capture only self destructing after pressing spacebar is an issue..
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++){
                if (pieceAt(x,y) != null){
                    if (pieceAt(x,y).isFire()) {
                        
                        fireCount += 1;
                    }
                    if (!pieceAt(x,y).isFire()) {
                        
                        waterCount += 1;
                    }
                    totalPieceCount += 1;

                }
            }
        }

        if (totalPieceCount == 0) { // potential area to fix the above problem is it arises by adding a case where we can let one Bomb piece be on the board after a capture and make it a no one wins
            return "No one";
        }
        if (fireCount == 0) {
            return "Water";
        }
        if (waterCount == 0) {
            return "Fire";
        }

        
        
        return null;
    }

    public static void main(String[] args) {
        Board board = new Board(false);
        board.drawBoard();
        board.currentPlayer = 0; 

       while(true) {

            if (StdDrawPlus.mousePressed()) {

                int xVal = (int)StdDrawPlus.mouseX();
                int yVal = (int)StdDrawPlus.mouseY();
                if (board.canSelect(xVal, yVal)) {
                    board.select(xVal, yVal);

                }
            }

            board.drawBoard();
            StdDrawPlus.show(10);

            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }

            }



            if (board.winner() != null) {
                System.out.println(board.winner());
                return ;
            }
            }            
            
            
            
    }
}
