import javax.swing.*;
import java.awt.*;

public class Board{

/** Location of pieces. */
    private Piece[][] pieces;
    private Color[][][] colors;
    private int[][] animationIndices; // The index in colors[i][j] to look at for square color, 0 if it should not increment after
    
    private static final int SIDE_LEN = 8; // Board side length.
    private static final int MAX_ANIMATION_LENGTH = 20; // Maximum length of board color animations
    private static final int NO_COORD = -1; // Used to indicate a nonexistent coordinate.
    private static Color BLACK;
    private static Color WHITE;
    
    private int turn; // Whose turn is it, 0 or 1?
    private int selectedX;
    private int selectedY;
    private boolean canMove;
    private boolean didMove;
    
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, SIDE_LEN);
        StdDrawPlus.setYscale(0, SIDE_LEN);
        WHITE = StdDrawPlus.WHITE;
        BLACK = StdDrawPlus.BLACK;
        Board board = new Board(false);
        while(true) {
            board.drawBoard(SIDE_LEN);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
            }            
            checkInteraction(board);
            if (board.winner()!=null) return;
            StdDrawPlus.show(100);
        }
    }
    
    private static void checkInteraction(Board b){
        if (StdDrawPlus.isSpacePressed()){
            if (b.canEndTurn()) b.endTurn();
        }
        if (StdDrawPlus.mousePressed()) {
            int x = (int) StdDrawPlus.mouseX();
            int y = (int) StdDrawPlus.mouseY();
            //System.out.println("Clicked at ("+x+","+y+")");
            //if (!b.canMove) return;
            if (b.canSelect(x, y)){
                b.select(x,y);
            }
            //else System.out.println("Can't select there.");
        }
    }

    public Board(boolean shouldBeEmpty){
        selectedX = NO_COORD;
        selectedY = NO_COORD;
        turn = 0;
        pieces = new Piece[SIDE_LEN][SIDE_LEN];
        colors = new Color[SIDE_LEN][SIDE_LEN][MAX_ANIMATION_LENGTH];
        animationIndices = new int[SIDE_LEN][SIDE_LEN];
        Piece piece = null;
        didMove = false;
        canMove = true;
        if (shouldBeEmpty) return;
        for (int i = 0; i < SIDE_LEN; i++){
            for (int j = 0; j < SIDE_LEN; j++){
                if      (j==SIDE_LEN-1 && i%2==1) piece = new Piece(false, this, i, j, "pawn");
                else if (j==SIDE_LEN-2 && i%2==0) piece = new Piece(false, this, i, j, "shield");
                else if (j==SIDE_LEN-3 && i%2==1) piece = new Piece(false, this, i, j, "bomb");
                
                else if (j==0 && i%2==0) piece = new Piece(true, this, i, j, "pawn");
                else if (j==1 && i%2==1) piece = new Piece(true, this, i, j, "shield");
                else if (j==2 && i%2==0) piece = new Piece(true, this, i, j, "bomb");
                
                else piece = null;
                pieces[i][j] = piece;
            }
        }
    }
    
    private boolean pieceIsSelected(){
        return (isOnBoard(selectedX, selectedY) && pieces[selectedX][selectedY]!=null);
    }
    
    private Piece selectedPiece(){
        if (!pieceIsSelected()) return null;
        return pieceAt(selectedX, selectedY);
    }
    
    private boolean isOnBoard(int x, int y){
        return (x >= 0 && x < SIDE_LEN && y >= 0 && y < SIDE_LEN);
    }
    
    private void setAnimation(Color[] animation, int x, int y){
        animationIndices[x][y] = 1;
        for (int i = 0; i<animation.length; i++){
            this.colors[x][y][i+1] = animation[i];
        }
    }
    
    private void setStaticColor(Color color, int x, int y){
        animationIndices[x][y] = 0;
        colors[x][y][0] = color;
    }
    
    private void resetColor(int x, int y){
        animationIndices[x][y] = 1;
        colors[x][y][1] = null;
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Color squareColor = colors[i][j][animationIndices[i][j]];
                if (squareColor!=null){ 
                    if (animationIndices[i][j]!=0) animationIndices[i][j] = animationIndices[i][j]+1;
                }
                else{
                    resetColor(i,j);
                    if ((i + j) % 2 == 0) squareColor = StdDrawPlus.GRAY;
                    else{
                        float red = (float)Math.min(1, (1 - (float)j/N) + Math.random()/3);
                        float blue = (float)Math.min(1, (float)j/N + Math.random()/3);
                        squareColor = new Color(red, (float)0, blue);
                    }
                }
                StdDrawPlus.setPenColor(squareColor);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(WHITE);
                Piece piece = pieces[i][j];
                if (piece != null){
                    String pieceName;
                    String pieceAllegiance;
                    if (piece.isFire()) pieceAllegiance = "fire";
                    else pieceAllegiance = "water";
                    if (piece.isBomb()) pieceName = "bomb";
                    else if (piece.isShield()) pieceName = "shield";
                    else pieceName = "pawn";
                    String filename = "img/" + pieceName + "-" + pieceAllegiance;
                    if (piece.isKing()) filename = filename + "-crowned";
                    filename = filename+".png";
                    StdDrawPlus.picture(i + .5, j + .5, filename, 1, 1);
                }
            }
        }
    }
    
    public Piece pieceAt(int x, int y){
        if (!isOnBoard(x, y)){ 
            //System.out.println("Tried to access piece at invalid position.");
            return null;
        }
        return pieces[x][y];
    }
    
    /*
    Selects the square at (x, y). This method assumes canSelect (x,y) returns true. Optionally, it is recommended to color the background of the selected square white on the GUI via the pen color function. For any piece to perform a capture, that piece must have been selected first. If you select a square with a piece, you are prepping that piece for movement. If you select an empty square (assuming canSelect returns true), you should move your most recently selected piece to that square.
    */
    public void select(int x, int y){
        if (pieceIsSelected()) resetColor(selectedX, selectedY);
        if (pieceIsSelected() && pieceAt(x,y)==null){ 
            Piece p = selectedPiece();
            p.move(x, y);
            canMove = p.hasCaptured();
            didMove = true;
        }
        setStaticColor(WHITE, x, y);
        selectedX = x;
        selectedY = y;
    }
    
    /*
    A piece may be selected if it is the corresponding player’s turn and one of the following is true:
    - The player has not selected a piece yet.
    - The player has selected a piece, but did not move it.
    
    An empty square may be selected if one of the following is true:
    - During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
    - During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. (You may select as many captures in a row as long as they are all valid and from the same piece.)
    */
    public boolean canSelect(int x, int y){
        if (!isOnBoard(x, y)) return false;
        if (pieceAt(x,y)==null){
            return (pieceIsSelected() && validMove(selectedX, selectedY, x, y));
        }
        
        return (turn == pieceAt(x, y).side() && canMove);
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece piece = pieceAt(xi, yi);
        if (xi==xf || yi==yf){
             return false;
        }
        if (didMove && !piece.hasCaptured()) return false;
        if (piece.hasCaptured() && !willCapture(piece, xi, yi, xf, yf)) return false;
        if (piece.isFire()){
            if (yf < yi && !piece.isKing()) return false;
        }
        else{
            if (yf > yi && !piece.isKing()) return false;
        }
        if (Math.abs(xi - xf) != 1 || Math.abs(yi - yf) != 1){
            return willCapture(piece, xi, yi, xf, yf);
        }
        return true;
    }
    
    private boolean willCapture(Piece p, int xi, int yi, int xf, int yf){ //Checks if this move will cause a capture
        if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) != 2) return false;
        int jumpedSpaceX = (xf + xi)/2;
        int jumpedSpaceY = (yf + yi)/2;
        Piece piece = pieceAt(jumpedSpaceX, jumpedSpaceY);
        if (piece==null) return false;
        return piece.side()!=p.side();
    }
    
    private boolean isKingSpace(Piece p, int x, int y){
        if (p.isFire() && y == SIDE_LEN-1) return true;
        if (!p.isFire() && y == 0) return true;
        return false;
    }
    
    /*
    Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. If p already exists in the current Board, first removes it from its initial position. If another piece already exists at (x, y), p will replace that piece. (This method is potentially useful for creating specific test circumstances.)
    */
    public void place(Piece p, int x, int y){
        if (p==null || !isOnBoard(x, y)) return;
        for (int i = 0; i < SIDE_LEN; i++){
            for (int j = 0; j < SIDE_LEN; j++){
                if (pieces[i][j] == p){ 
                    pieces[i][j] = null;
                    i = SIDE_LEN; // to break out of the outer loop
                    break;
                }
            }
        }
        pieces[x][y] = p;
        //System.out.println("Placed piece at ("+x+","+y+")");
    }
    
    /*
    Color[] bombAnimation = {StdDrawPlus.RED, StdDrawPlus.ORANGE, StdDrawPlus.RED, StdDrawPlus.ORANGE, StdDrawPlus.RED, StdDrawPlus.ORANGE, StdDrawPlus.RED, StdDrawPlus.ORANGE, StdDrawPlus.RED, StdDrawPlus.ORANGE, WHITE, BLACK, WHITE, BLACK, WHITE, BLACK};
    */
    
    public Piece remove(int x, int y){
        if (!isOnBoard(x,y)){
            System.out.println("ERROR: Attempted to remove a piece at invalid coordinates ("+x+", "+y+")");
            return null;
        }
        if (pieces[x][y]==null){
            System.out.println("ERROR: Attempted to remove a nonexistent piece at coordinates ("+x+", "+y+")");
            return null;
        }
        Color[] animation = {WHITE, BLACK, WHITE, BLACK, WHITE, BLACK, WHITE, BLACK, WHITE, BLACK, WHITE, BLACK};
            setAnimation(animation, x, y);
        Piece ret = pieces[x][y];
        pieces[x][y] = null;
        return ret;
    }
    
    public boolean canEndTurn(){
        return didMove;
    }
    
    public void endTurn(){
        turn = 1 - turn;
        canMove = true;
        didMove = false;
        if (pieceIsSelected()){
            resetColor(selectedX, selectedY);
            selectedPiece().doneCapturing();
            selectedX = NO_COORD;
            selectedY = NO_COORD;
        }
        //System.out.println("Ended turn for player "+ (1 - turn) +".");
    }
    
    /*
    Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over. Assume there is no stalemate situation in which the current player has pieces but cannot legally move any of them (In this event, just leave it at null). Determine the winner solely by the number of pieces belonging to each team.
    */
    public String winner(){
        int player0 = 0;
        int player1 = 0;
        Piece piece = null;
        for (int i = 0; i < SIDE_LEN; i++){
            for (int j = 0; j < SIDE_LEN; j++){
                piece = pieces[i][j];
                if (piece!=null){
                    if (piece.side()==0) player0++;
                    else player1++;
                }
            }
        }
        if (player0==0 && player1==0) return "No one";
        if (player1==0) return "Fire";
        if (player0==0) return "Water";
        return null;
    }
}



