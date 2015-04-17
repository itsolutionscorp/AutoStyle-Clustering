/**
 * Created by FelipeMendes on 2/7/15.
 * Class Board: Encapsulates creation of Board its basic functions
 * and has a main function that executes the game.
 */
public class Board {
    private Piece[][] piecesMatrix = new Piece[8][8];
    private boolean currentPlayerIsFire;
    private boolean thereIsAPieceSelected;
    private boolean hasMovedPiece;
    private boolean hasExploded;
    private int selectedPieceX;
    private int selectedPieceY;
    private int pieceThatCapturedX = -1;
    private int pieceThatCapturedY = -1;

    /**
     * @param shouldBeEmpty If it's true initializes an empty board. Otherwise
     *                      initializes the default one.
     */
    public Board(boolean shouldBeEmpty) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                piecesMatrix[i][j] = null;
            }
        }
        if (!shouldBeEmpty) {
            for (int i = 0; i < 7; i += 2) {
                piecesMatrix[i][0] = new Piece(true, this, i, 0, "pawn");
                piecesMatrix[i + 1][1] = new Piece(true, this, i+1, 1, "shield");
                piecesMatrix[i][2] = new Piece(true, this, i, 2, "bomb");
                piecesMatrix[i + 1][5] = new Piece(false, this, i+1, 5, "bomb");
                piecesMatrix[i][6] = new Piece(false, this, i, 6, "shield");
                piecesMatrix[i + 1][7] = new Piece(false, this, i+1, 7, "pawn");
            }
        }
        currentPlayerIsFire = true;
        thereIsAPieceSelected = false;
        hasMovedPiece = false;
        selectedPieceX = 0;//Review best initial value
        selectedPieceY = 0;
        hasExploded = false;
    }

    /**
     * Gets the piece at position (x, y) on the board, or null if there is no piece.
     * If (x, y) are out of bounds, returns null.
     *
     * @param x Position x
     * @param y Position y
     * @return return piece in position (x,y) if there is. Otherwise, returns null.
     */
    public Piece pieceAt(int x, int y) {
        if (x >= 8 || y >= 8 || x<0 || y<0) {
            return null;
        }
        return piecesMatrix[x][y];
    }


    /**
     * Returns true if there is a piece the piece at (x, y) and it can be selected.
     * Also returns true is it's a empty position that can generate
     * @param x Position x
     * @param y Position y
     * @return Returns if the position can be selected
     */
    public boolean canSelect(int x, int y) {
        if(pieceAt(x,y)!=null){
            if(hasMovedPiece){
                return false;
            }
           if(pieceAt(x,y).isFire() == currentPlayerIsFire){
               return true;
           }
        }
        //Test if square can be selected.
        if(thereIsAPieceSelected && (!hasMovedPiece||pieceAt(selectedPieceX,selectedPieceY).hasCaptured())){
            if(validMove(selectedPieceX,selectedPieceY,x,y)){
                return true;
            }
        }
        return false; 
    }

    /**
     * Returns true if the piece at (xi, yi) can either move to (xf, yf)
     * or capture to (xf, yf), strictly from a geometry/piece-race point
     * of view. validMove does not need to take into consideration whose
     * turn it is or if a move has already been made, but rather can.
     * @param xi Initial x
     * @param yi Initial y
     * @param xf Final x
     * @param yf Final y
     * @return If the move is valid returns true.
     */
    private boolean validMove(int xi, int yi, int xf, int yf){
        //out of bounds
        if(xi>7||yi>7||xf>7||yf>7){
            return false;
        }
        //There is no piece at the initial position
        if(pieceAt(xi,yi)==null){
            return false;
        }
        //There is a piece at the final position
        if(pieceAt(xf,yf)!=null){
            return false;
        }
        //First deal with the case that the piece has just captured
        //and is going to capture another one.
        if(pieceAt(xi,yi).hasCaptured()&&!hasExploded){
            if(Math.abs(xf-xi)==2 && pieceAt((xi+xf)/2,(yi+yf)/2)!=null) {
                if (pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() !=
                        pieceAt(xi, yi).isFire()) {
                    if (pieceAt(xi,yi).isKing() && Math.abs(yf-yi)==2) {
                        return true;
                    }
                    if(pieceAt(xi,yi).isFire() && yf-yi == 2){
                        return true;
                    }
                    if(!pieceAt(xi,yi).isFire() && yi-yf == 2){
                        return true;
                    }
                }
            }
            return false;
        }
        if(Math.abs(xf-xi)==1) {
            //Piece is king and moving for any of its diagonals.
            if (pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 1) {
                return true;
            }
            //Fire piece moving up
            if (pieceAt(xi, yi).isFire() && (yf - yi) == 1) {
                return true;
            }
            //Water piece moving down
            return (!pieceAt(xi, yi).isFire() && (yi - yf) == 1);
        }
        if(Math.abs(xf-xi)==2 && pieceAt((xi+xf)/2,(yi+yf)/2)!=null){
            if (pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() !=
                    pieceAt(xi, yi).isFire()) {
                //Piece is king and capturing for any of its diagonals.
                if (pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 2) {
                    return true;
                }
                //Fire piece moving up
                if (pieceAt(xi, yi).isFire() && (yf - yi) == 2) {
                    return true;
                }
                //Water piece moving down
                return (!pieceAt(xi, yi).isFire() && (yi - yf) == 2);
            }
        }
        return false;
    }

    /**
     * Selects the square at (x, y). This method assumes canSelect (x,y)
     * returns true. Optionally, it is recommended to color the background
     * of the selected square white on the GUI via the pen color function.
     * For any piece to perform a capture, that piece must have been selected
     * first. If you select a square with a piece, you are prepping that piece
     * for movement. If you select an empty square (assuming canSelect returns
     * true), you should move your most recently selected piece to that square.
     *
     * @param x Position x
     * @param y Position y
     */
    public void select(int x, int y) {
        if(pieceAt(x,y)!=null){
            thereIsAPieceSelected = true;
            selectedPieceX = x;
            selectedPieceY = y;
        }
        else {
            piecesMatrix[selectedPieceX][selectedPieceY].move(x,y);
            hasMovedPiece = true;
            if(pieceAt(x, y) == null) {
                hasExploded = true;
                thereIsAPieceSelected = false;
            }
            else {
                pieceThatCapturedX = x;
                pieceThatCapturedY = y;
                selectedPieceY = y;
                selectedPieceX = x;
            }
        }
    }


    /**
     * Places p at (x, y). If (x, y) is out of bounds, does nothing.
     * If another piece already exists at (x, y), p will replace that piece.
     * @param p pieced to be placed at (x,y)
     * @param x Coordinate x
     * @param y Coordinate y
     */
    public void place(Piece p, int x, int y) {
        if (x >= 8 || y >= 8) {
            return;
        }
        piecesMatrix[x][y] = p;
    }


    /**
     * Executes a remove. Returns the piece that was removed.
     * If the input (x, y) is out of bounds, returns null and
     * prints an appropriate message. If there is no piece at
     * (x, y), returns null and prints an appropriate message.
     *
     * @param x Position x
     * @param y Position y
     * @return Piece to be removed
     */
    public Piece remove(int x, int y) {
        Piece p;
        if (x >= 8 || y >= 8) {
            System.out.println("(x,y) is out of bounds");
            return null;
        }
        if (piecesMatrix[x][y] == null) {
            System.out.println("No piece at (x,y)");
            return null;
        }
        p = piecesMatrix[x][y];
        piecesMatrix[x][y] = null;
        return p;
    }

    /**
     * Returns whether or not the the current player can end their turn.
     * To be able to end a turn, a piece must have moved or performed a capture.
     *
     * @return If the current player can end turn returns true
     */
    public boolean canEndTurn() {
        return hasMovedPiece;
    }


    /**
     * Called at the end of each turn. Handles switching of players and
     * anything else that should happen at the end of a turn.
     */
    public void endTurn() {
        if(!hasExploded && pieceThatCapturedX!=-1){
            this.pieceAt(pieceThatCapturedX,pieceThatCapturedY).doneCapturing();
        }
        pieceThatCapturedX = -1;
        currentPlayerIsFire = !currentPlayerIsFire;
        hasMovedPiece = false;
        thereIsAPieceSelected = false;
        hasExploded = false;
    }


    /**
     * Returns the winner of the game. "Fire", "Water", "No one"
     * (tie / no pieces on the board), or null if the game is not yet over.
     *
     * @return String with the name of the winner of the game.
     */
    public String winner() {
        int firePieces = 0;
        int waterPieces = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(piecesMatrix[i][j]!=null){
                    if(piecesMatrix[i][j].isFire()){
                        firePieces++;
                    }
                    else{
                        waterPieces++;
                    }
                }
            }
        }
        if(firePieces == 0 && waterPieces == 0){
            return "No one";
        }
        if(firePieces == 0){
            return "Water";
        }
        if(waterPieces == 0){
            return "Fire";
        }
        return null;
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(thereIsAPieceSelected && selectedPieceX == i && selectedPieceY == j){
                    StdDrawPlus.filledSquare(selectedPieceX + .5, selectedPieceY + .5, .5);
                }
                if (this.piecesMatrix[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, this.imageToDisplay(i, j), 1, 1);
                }
            }
        }
    }

    private String imageToDisplay(int i, int j) {
        if (this.piecesMatrix[i][j].isFire()) {
            if (this.piecesMatrix[i][j].isKing()) {
                if (this.piecesMatrix[i][j].isBomb()) {
                    return "img/bomb-fire-crowned.png";
                }
                if (this.piecesMatrix[i][j].isShield()) {
                    return "img/shield-fire-crowned.png";
                }
                return "img/pawn-fire-crowned.png";
            }
            if (this.piecesMatrix[i][j].isBomb()) {
                return "img/bomb-fire.png";
            }
            if (this.piecesMatrix[i][j].isShield()) {
                return "img/shield-fire.png";
            }
            return "img/pawn-fire.png";

        }
        if (this.piecesMatrix[i][j].isKing()) {
            if (this.piecesMatrix[i][j].isBomb()) {
                return "img/bomb-water-crowned.png";
            }
            if (this.piecesMatrix[i][j].isShield()) {
                return "img/shield-water-crowned.png";
            }
            return "img/pawn-water-crowned.png";
        }
        if (this.piecesMatrix[i][j].isBomb()) {
            return "img/bomb-water.png";
        }
        if (this.piecesMatrix[i][j].isShield()) {
            return "img/shield-water.png";
        }
        return "img/pawn-water.png";

    }


    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while (true) {
            b.drawBoard(8);
            if(StdDrawPlus.mousePressed()){
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x, y)){
                    b.select(x, y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }
            if(StdDrawPlus.isNPressed()){
                b = new Board(false);
            }
            if(b.winner()!=null){
                StdDrawPlus.text(4,4,b.winner());
            }
            StdDrawPlus.show(10);
        }
    }

}
