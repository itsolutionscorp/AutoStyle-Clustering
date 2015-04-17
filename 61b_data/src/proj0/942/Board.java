import static java.lang.Math.abs;

/** Class that creates a checkers board and runs the game GUI
 *  @author Simon Maude
 */

public class Board{
    private  Piece[][] pieces;  
    private  boolean[][] highlighted;  
    private  Piece selected;
    private  boolean hasMoved; 
    private boolean turn; 

    public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8]; 
        if (shouldBeEmpty == false) {
            startingPieces();
        }
    }   

    private void startingPieces(){
        for(int i = 0; i < 8; i+=2){
            pieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn");
            pieces[i][6] = new Piece(false, this, i, 6, "shield");
            pieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb");
            pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield");
            pieces[i][0] = new Piece(true, this, i, 0, "pawn");    
        }              
    }

    private  void drawBoard(int N) {
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
                if (highlighted[i][j]) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
              
                    Piece a = pieceAt(i, j);
                    if (a != null) {
                        StringBuilder sb = new StringBuilder("img/");
                        if (a.isBomb()){
                            sb.append("bomb");   
                        } else if (a.isShield()){
                            sb.append("shield");   
                        } else {
                            sb.append("pawn");   
                        }

                        if (a.isFire()){
                            sb.append("-fire");
                        } else {
                            sb.append("-water");   
                        }

                        if (a.isKing()){
                            sb.append("-crowned");
                        }
                        sb.append(".png");
                        StdDrawPlus.picture(i + .5, j + .5, sb.toString(), 1, 1);
                        
                        
                }
            }
        }
    }


    /* Returns a piece if it exists given coordinates, returns null if not */
    public  Piece pieceAt(int x, int y) {
        // if piece is in boundaries
        if (!(x < 0 || y < 0 || x >= 8 || y >= 8)){ 
            Piece p = pieces[x][y];
            // if piece exists
            if (p != null){   
                return p;
            }
        } 
        return null;
    }
    
    // /* Finds index of piece in the Piece[][] */ 
    private int[] findPiece(Piece p){
    int[] output = {-1, -1};
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (p == pieces[i][j]){
                    output[0] = i;
                    output[1] = j;
                } 
            }       
        }
    if (output[1] == -1) System.out.println("did not find the piece");
    return output;
    }


    /* Tests to see if a piece belongs to the current player */
    private boolean isPlayersPiece(Piece p){
        return ((!turn) && (p.isFire()) || ((turn) && (!p.isFire())));
    }



    private   boolean validMove(int xi, int yi, int xf, int yf){
        /* FALSE if you're not a king, you can't move backwards*/
        if ((turn && (!selected.isKing()) && (yi < yf)) || (!turn && (!selected.isKing() && (yi > yf)))) return false;  
        /* TRUE if you're moving 2 squares and taking an apposing piece */
        if (abs(xi - xf) == 2){
            Piece j = (mark(xi, yi, xf, yf)); 
            if ((j != null) && (!isPlayersPiece(j))) return true;         
        }
        /* TRUE if you haven't moved already, you're moving 1 square and it's empty*/ 
        if ((!hasMoved) && (abs(xi - xf) == 1) && (pieceAt(xf, yf) == null)){
            return true;
        }
        /* ELSE FALSE*/
        return false;
    }

    public boolean canSelect(int x, int y){
        Piece p = pieceAt(x,y);
        /* R FALSE If it's not a gray square, can't select*/
        if ((x + y) % 2 != 0) return false;
        /* R FALSE if it's out of bounds, can't select*/
        if (x < 0 || y < 0 || x >= 8 || y >= 8) return false;
        
        /* OPTIONS FOR IF YOU HAVE MOVED ALREADY...*/
        if (hasMoved){
            /* R FALSE ... but didn't capture, can't select anything*/
            if (!selected.hasCaptured()) return false; 
            /* CHECK VALID MOVE ... and you DID capture*/
            else {
                int[] selectedCoords = findPiece(selected);
                return validMove(selectedCoords[0], selectedCoords[1], x, y);     
            }
        }
        /* OPTIONS FOR IF YOU HAVEN'T MOVED ALREADY*/
        /* if theres a piece here...  */
        if (p != null) {
            /* R FALSE ... and it's not yours, can't select */
            if (!isPlayersPiece(p)) return false;
            /* R TRUE ... and it is yours, can select*/
            else return true;
        /* CHECK No piece here, but you've already selected and it's a VALID MOVE */
        } else {
            int[] selectedCoords = findPiece(selected);
            if ((selected != null) && validMove(selectedCoords[0], selectedCoords[1], x, y)) return true;
        }
    /* all other cases, you can't select */     
    return false;
    }

    public void select(int x, int y){
        highlighted = new boolean[8][8];
        highlighted[x][y] = true;
        
        Piece p = pieceAt(x, y);    
        /* if theres a piece here replace selected with this piece */
        if (p != null) selected = p;
        /* no piece here, move selected and update hasMoved */
        else {
            /* tell the piece to move */
            selected.move(x, y);
            hasMoved = true;
        }
    }

    /* Places p at given coordinates */
    public void place(Piece p, int x, int y){   
        /* if (x, y) is out of bounds or if p is null, does nothing. */
        if (p == null || x < 0 || y < 0 || x >= 8 || y >= 8) return;
        /* places p */
        pieces[x][y] = p;
    }

    /* Returns a piece that can be taken */
    private Piece mark(int xi, int yi, int xf, int yf){
        int middleX = 1;
        int middleY = 1;
        if (xi > xf) middleX = -middleX;    
        if (yi > yf) middleY = -middleY;    
        middleX += xi;
        middleY += yi;
        return pieceAt(middleX, middleY);
    } 

    public  Piece remove(int x, int y){
        if (x < 0 || y < 0 || x >= 8 || y >= 8) {
            System.out.println(x + ", " + y + " is out of bounds");
            return null;
        }
        Piece r = pieceAt(x, y);
        if (r == null) {
            System.out.println("there is no piece at " + x + ", " + y);
            return null;
        }
        // Piece rTemp = r;
        pieces[x][y] = null;
        return r;
    }

    public  boolean canEndTurn(){
        return (hasMoved);      
    }

    public  void endTurn(){
        hasMoved = false;
        if (selected != null) { 
            selected.doneCapturing();
            selected = null;
        }
        turn = !turn;
        highlighted = new boolean[8][8];
    }

    public String winner(){
        String s = null;
        int fire = 0;
        int water = 0;
        
        /* Cycle through the Piece[] and count remaining pieces */
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Piece p = pieces[i][j]; 
                if (p != null){
                    if (p.isFire()) {
                        fire += 1;
                    } else {
                        water += 1;
                    }
                }    
            }
        }
    
        /* Declares the winner if either side is at 0 pieces. Tie if both are*/
        if (fire == 0) {
            if (water == 0) {
                s = "No one";
            } else {
                s = "Water";
            }
        } else if (water == 0){
            s = "Fire";
        }
        return s;
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        b.highlighted = new boolean[8][8];
        String testWin = b.winner();
        
        /** Monitors for mouse presses. Wherever the mouse is pressed,
         a new piece appears. */
        while(testWin == null) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) b.select(x,y);
            }

            if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) b.endTurn(); 
            
            testWin = b.winner(); 
            if (testWin != null){
                b.highlighted = new boolean[8][8];
                b.drawBoard(8);
                System.out.println(testWin);
            }
            StdDrawPlus.show(25);
            
        }
    }
}

