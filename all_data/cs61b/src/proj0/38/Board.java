/*****************************************************
 * Nikhil Mishra, cs61b-abm
 * proj0: Checkers61b
 * 
 * The main game class. Handles GUI and game logic.
 */

public class Board{
    
    private static final int BOARD_SIZE = 8;     // checkers is an 8x8 board
    
    private Piece[][] board;                    // stores piece locations
    
    private int selectedX;                      // (x,y) coordinates of currently selected square
    private int selectedY;                      // (-1,-1) means nothing selected

    private boolean hasMoved;                   // has the current player made a move yet
    private boolean firesTurn;                  // fire's turn or water's turn
    
    /**
     * Creates a board instance.
     * @param shouldBeEmpty: false sets up for a new game, true creates empty board
     */
    public Board(boolean shouldBeEmpty){
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        firesTurn = true;
        hasMoved = false;
        
        selectedX = -1;
        selectedY = -1;
        
        if(!shouldBeEmpty){
            initPieces();
        }
    }
    
    /**
     * Provides a reference to a Piece on the Board.
     * @param x,y is the desired board coordinates
     * @return the Piece object at x,y or null if there isn't any
     */
    public Piece pieceAt(int x, int y){
        if(withinBoard(x,y)){
            return board[x][y];
        } else {
            return null;
        }
    }
    
    /**
     * Remove a Piece from the Board.
     * @param x,y is the desired board coordinates
     * @return the Piece object no longer at (x,y), or null if there isn't any/out of bounds
     */
    public Piece remove(int x, int y){
        if(!withinBoard(x,y)){
            System.out.println("Coordinates not in board.");
            return null;
        } else if(board[x][y] == null){
            System.out.println("No piece at (" + x + "," + y + ")");
            return null;
        } else {
            Piece p = board[x][y];
            board[x][y] = null;
            return p;
        }
    }
    
    /**
     * Checks whether a move is valid.
     * @param xi,yi initial coordinates of the Piece we want to move
     * @param xf,xf intended coordinates to move to
     * @return legal move or not
     */
    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece p = pieceAt(xi,yi);
        if(withinBoard(xi,yi) && withinBoard(xf,yf) && p != null){
            int dx = xf - xi, dy = yf - yi;            
            if(Math.abs(dx) == 1 && Math.abs(dy) == 1){
                if(!hasMoved){
                    if(p.isKing()) return pieceAt(xf,yf) == null;
                    else if(p.isFire()) return dy == 1 && pieceAt(xf,yf) == null;
                    else return dy == -1 && pieceAt(xf,yf) == null;
                }
            } else if(Math.abs(dx) == 2 && Math.abs(dy) == 2){
                int cx = (xi + xf)/2, cy = (yi + yf)/2;
                if(p.isKing()) return pieceAt(xf,yf) == null && pieceAt(cx,cy) != null && pieceAt(cx,cy).isFire() != p.isFire();
                else if(p.isFire()) return dy == 2 && pieceAt(xf,yf) == null && pieceAt(cx,cy) != null && !pieceAt(cx,cy).isFire();
                else return dy == -2 && pieceAt(xf,yf) == null && pieceAt(cx,cy) != null && pieceAt(cx,cy).isFire();
            }   
        }
        return false;
    }
    
    /**
     * Inserts a Piece to the Board. If there is a piece at that location, removes it.
     * @param p the Piece to insert
     * @param x,y coordinates to put at
     */
    public void place(Piece p, int x, int y){
        if(p != null && withinBoard(x,y)){
            board[x][y] = p;
        }
    }
    
    /**
     * Counts number of pieces left on each side, and uses that determine if game is over.
     * @return null means not over yet, else String of result
     */
    public String winner(){
        int firePiecesLeft = 0;
        int waterPiecesLeft = 0;
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                Piece p = pieceAt(i,j);
                if(p != null){
                    if(p.isFire()){
                        firePiecesLeft++;
                    } else {
                        waterPiecesLeft++;
                    }
                }
            }
        }
        
        if(firePiecesLeft == 0 && waterPiecesLeft == 0){
            return "No one";
        }
        if(firePiecesLeft == 0){
            return "Water";
        }
        if(waterPiecesLeft == 0){
            return "Fire";
        }
        return null;
    }
    
    /**
     * Process a click on the board. Should move only if canSelect() returns true
     * @param x,y the coordinates clicked
     * Either selecting a piece to move, or moving it.
     */
    public void select(int x, int y){
        Piece p = pieceAt(x,y);
        if(selectedX == -1 && selectedY == -1){
            selectedX = x;
            selectedY = y; 
        } else {
            if(p == null){
                Piece selected = pieceAt(selectedX, selectedY);
                selected.move(x,y);
                hasMoved = true;
            }
            selectedX = x;
            selectedY = y;
        }
    }
    
    /**
     * Checks if a square is selectable.
     * @param x,y the coordinates clicked.
     * @return whether the click is valid and select() needs to be called.
     * If 'selectedPiece' is null (current player hasn't picked yet) then select the piece at x,y
     * If they have picked a piece but haven't moved it yet, then allow reselect or check if move is valid (using validMove())
     * If they've moved and captured a piece, allow recaptures.
     */
    public boolean canSelect(int x, int y){
        Piece p = pieceAt(x,y);
        Piece selected = pieceAt(selectedX, selectedY);
        if(selectedX == -1 && selectedY == -1){
            return p != null && p.isFire() == firesTurn;
        } else if(!hasMoved){
            return validMove(selectedX, selectedY, x, y) || (p != null && p.isFire() == firesTurn);
        } else if(selected != null && selected.hasCaptured()){
            return validMove(selectedX, selectedY, x, y);
        }
        return false;
    }
    
    /**
     * Checks whether the current player has made a complete move.
     * @return whether they are allowed to end their turn (they must have moved). endTurn() will be called if true.
     */
    public boolean canEndTurn(){
        return hasMoved;
    }
    
    /**
     * Handles switching turns.
     * Resets selectedPiece, capturedPiece, hasMoved, and negates firesTurn
     */
    public void endTurn(){
        Piece p = pieceAt(selectedX,selectedY);
        if(p != null){
            p.doneCapturing();
        }
        selectedX = -1;
        selectedY = -1;
        hasMoved = false;
        firesTurn = !firesTurn;
    }
    
    public static void main(String[] args){
        Board b = new Board(false);
        while(b.winner() == null){
            b.drawBoard();
            if(StdDrawPlus.mousePressed()){
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x,y)){
                    b.select(x,y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }
            b.drawPieces();
            if(StdDrawPlus.isNPressed()){
                b = new Board(false);
                System.out.println("Starting new game...");
            }
            StdDrawPlus.show(50);
        }
        System.out.println(b.winner() + " wins.");
        b.drawBoard();
        b.drawPieces();
        StdDrawPlus.show(5000);
        System.exit(0);
    }
    
    /**
     * Helper method to check if xi,yi are within the Board.
     */
    private boolean withinBoard(int xi, int yi){
        return xi < BOARD_SIZE && xi >= 0 && yi < BOARD_SIZE && yi >= 0;
    }
    
    /**
     * Provides image filenames for a Piece. Called by drawPieces().
     * @param the piece we want a filename for
     * @return the filename of its image file, or null if piece is null
     */
    private String getImgName(Piece p){
        if(p != null){
            String img = "img/";
            
            if(p.isBomb()) img += "bomb-";
            else if(p.isShield()) img += "shield-";
            else img += "pawn-";
            
            if(p.isFire()){
                img += "fire";
            } else {
                img += "water";
            }
            
            if(p.isKing()) img += "-crowned";
            
            return img + ".png";
        }
        return null;
    }
    
    /**
     * Draws a red/gray checkerboard. If a piece is selected, the square it is on gets drawn as white.
     */
    private void drawBoard() {
        StdDrawPlus.setXscale(0, BOARD_SIZE);
        StdDrawPlus.setYscale(0, BOARD_SIZE);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if((i + j) % 2 == 1){
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                } else if(selectedX == i && selectedY == j){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }
    
    /**
     * Draws the pieces in their current. Should be called after drawBoard().
     */
    private void drawPieces(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece p = pieceAt(i,j);
                if(p != null){
                    String img = getImgName(p);
                    StdDrawPlus.picture(i+0.5,j+0.5,img,1,1);
                }
            }
        }
    }
    
    /**
     * Initialize the pieces in board[][] for a new game. Called once by Board().
     * Doesn't draw anything, drawPieces() should be called afterwards.
     */
    private void initPieces(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece p = null;
                if(j==0 && i%2==0){
                    p = new Piece(true, this, i, j, "pawn");
                }
                if(j==1 && i%2==1){
                    p = new Piece(true, this, i, j, "shield");
                }
                if(j==2 && i%2==0){
                    p = new Piece(true, this, i, j, "bomb");
                }
                
                if(j==5 && i%2==1){
                    p = new Piece(false, this, i, j, "bomb");
                }
                if(j==6 && i%2==0){
                    p = new Piece(false, this, i, j, "shield");
                }
                if(j==7 && i%2==1){
                    p = new Piece(false, this, i, j, "pawn");
                }
            }
        }
    }
    
    
}