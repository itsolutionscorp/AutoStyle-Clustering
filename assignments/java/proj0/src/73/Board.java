/**
 * The Board class implements the board that the piece objects 
 * are stored in.
 * @author Jeff Weng
 * @version 1.0
 */
public class Board{
    
    /**
     * Private variables to keep track of the state of the board
     */
    private Piece[][] pieces;
    // true if it is fire's turn, false if it is water's turn
    private int turn = 0;
    private boolean hasMoved = false;
    private boolean canEnd = false;
    private Piece selectedPiece;
    
    
    /**
     * Constructor for the board class
     * @param shouldBeEmpty
     */
    public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
        if(shouldBeEmpty == false){
            for(int i = 0; i < 8; i += 2){
                // initialize water pieces
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");     
                pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield");
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
                // initialize fire pieces
                pieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb");
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
                pieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn");
            }
        }
    }
    
    /**
     * Gets the piece at position (x, y) on the board. If there is no piece 
     * there or it is out of bounds, then this returns null 
     * @param x - the x coordinate of the piece
     * @param y - the y coordinate of the piece
     * @return the piece at the location
     */
    public Piece pieceAt(int x, int y){
        if(x > 7 || y > 7 || x < 0 || y < 0){
            return null;
        }
        if(pieces[x][y] != null){
            return pieces[x][y];
        }
        return null;
    }
    
    /**
     * Checks if a piece at location (x, y) can be selected
     * @param x - the x coordinate of the piece being checked
     * @param y - the y coordinate of the piece being checked
     * @return true if piece can be selected, false if piece cannot be selected
     */
    public boolean canSelect(int x, int y){
        if(pieces[x][y] != null && pieces[x][y].side() == turn){
            if(selectedPiece == null || !hasMoved){
                return true;
            }
            else{
                return false;
            }
        }
        else if(pieces[x][y]==null){
            if(selectedPiece != null && !hasMoved && validMove(getX(selectedPiece), getY(selectedPiece), x, y)){
                return true;
            }
            else if((selectedPiece != null) && selectedPiece.hasCaptured() && validMove(getX(selectedPiece), getY(selectedPiece), x, y) && (Math.abs(getX(selectedPiece)-x) == 2)){
                return true;
            }
            else{
                return false;
            }   
        }
        else{
            return false;
        }
    }

    /**
     * Returns whether or not a piece at (xi, yi) can either be moved 
     * to (xf, yf) or capture a piece by moving to (xf, yf)
     * @param xi - initial x coordinate
     * @param yi - initial y coordinate
     * @param xf - x coordinate of destination
     * @param yf - y coordinate of destination
     * @return true if piece can move from (xi,yi) to (xf,yf)
     */
    private boolean validMove(int xi, int yi, int xf, int yf){
        if(pieces[xi][yi] == null){
            return false;
        }
        if(xf < 0 || xf > 7 || yf < 0 || xf > 7){
            System.out.println("Out of bounds");
            return false;
        }
        // FOR KINGS
        if(pieces[xi][yi].isKing()){
            // Move into an empty square
            if((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1 ) && (pieces[xf][yf] == null)){
                return true;
            }
            // Capture an opponent piece
            else if((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)){
                Piece center = pieces[xi+(xf-xi)/2][yi+(yf-yi)/2];              
                if(center != null && center.side() != turn){
                    return true;
                }
                return false;
            }
            else{
                return false;
            }
        }
        
        //FOR REGULAR FIRE
        else if(pieces[xi][yi].isFire()){
            // Move to an empty square
            if((Math.abs(xf - xi) == 1) && ((yf - yi) == 1) && (pieces[xf][yf] == null)){
                return true;
            }
            // Capture an opponent piece
            else if((Math.abs(xf - xi) == 2) && ((yf - yi) == 2)){
                Piece center = pieces[xi+(xf-xi)/2][yi+(yf-yi)/2];              
                if(center != null && center.side() != turn){
                    return true;
                }
                return false;
            }
            else{
                return false;
            }
        }
        
        //FOR REGULAR WATER
        else if(!pieces[xi][yi].isFire()){
            // Move to an empty square
            if((Math.abs(xf - xi) == 1) && ((yf - yi) == -1) && (pieces[xf][yf] == null)){
                return true;
            }
            // Capture an opponent piece
            else if((Math.abs(xf - xi) == 2) && ((yf - yi) == -2)){
                Piece center = pieces[xi+(xf-xi)/2][yi+(yf-yi)/2];              
                if(center != null && center.side() != turn){
                    return true;
                }
                return false;
            }
            else{
                return false;
            }
            
        }
        else{
            return false;
        }
    }

    /**
     * Selects the piece at (x, y) if possible. 
     * @param x - the x coordinate of piece to be selected
     * @param y - the y coordinate of piece to be selected
     */
    public void select(int x, int y){
        if(selectedPiece != null){
            if(pieces[x][y] == null){
                selectedPiece.move(x, y);
                canEnd = true;
                hasMoved = true;
            }
            else{
                selectedPiece = pieces[x][y];
            }
        }
        else{
            selectedPiece = pieces[x][y];
        }
    }
            
    /**
     * Places on the board at location (x,y). If (x, y) is out of bounds
     * or if p is doesn't exist, then nothing happens. If p already exists
     * at another location, then this will remove it from the old location
     * and place it in the new location.
     * @param p - piece that is to be placed at coordinate (x, y)
     * @param x - the x coordinate of the desired location
     * @param y - the y coordinate of the desired location
     */
    public void place(Piece p, int x, int y){
        if(x < 8 || y < 8 || x >= 0 || y >= 0 || (p == null)){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(pieces[i][j]==p){
                        pieces[i][j] = null;                    
                    }            
                }
            }
        pieces[x][y] = p;
        }
    }
    
    /**
     * Removes a piece from the board and then returns the piece that
     * was removed. If the arguments that are passed in are out of bounds
     * or there is no piece at the given location, then this returns null 
     * @param x - x location of piece to be removed
     * @param y - y location of piece to be removed
     * @return The piece that is removed
     */
    public Piece remove(int x, int y){
        if(x > 7 || y > 7 || x < 0 || y < 0){
            System.out.println("The input is out of bounds.");
            return null;
        }
        else if(pieceAt(x,y) == null){
            System.out.println("There is no piece at this location");
            return null;
        }
        else{
            Piece p = pieceAt(x,y);
            pieces[x][y] = null;
            return p;
        }
    }
    
    /**
     * Returns if the current player can end their turn.
     * @return true if the current player can end their turn.
     */
    public boolean canEndTurn(){
        return canEnd;
    }
    
    
    /**
     * Handles the end of a turn: changes turn, resets hasMoved and canEnd
     * to false. Checks if there is a winner after ending the turn.
     */
    public void endTurn(){
        turn = (Math.abs(turn - 1));
        hasMoved = false;
        canEnd = false;
        if(selectedPiece != null){
            selectedPiece.doneCapturing();
            winner();
        }
        selectedPiece = null;
    }
    
    /**
     * Gives the winner of the game, or "no one" if no one wins. If the
     * game is still going on, then it returns null.
     * @return String representing the winner of the game
     */
    public String winner(){
        int numW = 0;
        int numF = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j]!= null){
                    if(pieceAt(i,j).isFire()){
                        numF += 1;
                    }
                    else{
                        numW += 1;
                    }
                }
            }
        }
        if((numW == numF) && (numW == 0)){
            return "No one";
        }
        else if(numW == 0){
            return "Fire";
        }
        else if(numF == 0){
            return "Water";
        }
        else{
            return null;
        }
    }


    /**
     * Draws the board with the pieces of the array
     * @param N The dimension of the square board
     */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i+.5, j+.5, .5);
                if(pieces[i][j]!=null){
                    Piece p = pieces[i][j];
                    StdDrawPlus.picture(i+.5, j+.5, chooseImage(p), 1, 1);
                }
                
                
            }
        }
    }
    

    /**
     * Chooses which image file to draw based on a given piece
     * @param p - piece that is given
     * @return the string of the name of the image file
     */
    private static String chooseImage(Piece p){
        String s;
        if(p.isFire()){
            if(p.isBomb()){
                if(p.isKing()){
                    s = "img/bomb-fire-crowned.png";
                }
                else {
                    s = "img/bomb-fire.png";
                }
            }
            else if(p.isShield()){
                if(p.isKing()){
                    s = "img/shield-fire-crowned.png";
                }
                else{
                    s = "img/shield-fire.png";
                }
            }
            else{
                if(p.isKing()){
                    s = "img/pawn-fire-crowned.png";
                }
                else{
                    s = "img/pawn-fire.png";
                }
            }
        }
        else{
            if(p.isBomb()){
                if(p.isKing()){
                    s = "img/bomb-water-crowned.png";
                }
                else{
                    s = "img/bomb-water.png";
                }
            }
            else if(p.isShield()){
                if(p.isKing()){
                    s = "img/shield-water-crowned.png";
                }
                else{
                    s = "img/shield-water.png";
                }
            }
            else{
                if(p.isKing()){
                    s = "img/pawn-water-crowned.png";
                }
                else{
                    s = "img/pawn-water.png";
                }
            }
        }
        return s;
        
    }
    

    /**
     * Private method to get x coordinate of a piece
     * @param p - piece that x coordinate is being looked up on
     * @return the x coordinate of the Piece p
     */
    private int getX(Piece p){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j]==p){
                    return i;
                }            
            }
        }
        return 0;
    }
    
    /**
     * Private method to get y coordinate of a piece
     * @param p - piece that y coordinate is being looked up on
     * @return the y coordinate of the Piece p
     */
    private int getY(Piece p){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j]==p){
                    return j;
                }            
            }
        }
        return 0;
    }
    
    
    
    /**
     * The main method that intializes a board and starts the GUI
     * version of the game.
     * @param args
     */
    public static void main(String[] args){
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);      

//        Piece p = new Piece(false, b, 2, 4, "pawn");
//        Piece p2 = new Piece(false, b, 2, 6, "pawn");
//        Piece p3 = new Piece(false, b, 4, 6, "pawn");
//        Piece p4 = new Piece(true, b, 3, 3, "shield");
//        Piece p5 = new Piece(true, b, 1, 3, "bomb");
//        b.place(p, 2, 4);
//        b.place(p2, 2, 6);
//        b.place(p3, 4, 6);
//        b.place(p4, 3, 3);
//        b.place(p5, 1, 3);
        
        while(true) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int) x, (int) y)){
                    b.select((int) x, (int) y);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(((int) x)+.5, ((int) y) +.5, .5);
                    StdDrawPlus.show();
                }           
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }
            StdDrawPlus.show(25);
        }   
    }
}
