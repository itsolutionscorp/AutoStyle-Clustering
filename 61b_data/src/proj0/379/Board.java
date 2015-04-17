/** 
 *  @author Megan Zhu cs61b-aop
 */

public class Board {
    private Piece[][] pieces;    /** Location of pieces. */
    private final int N = 8;
    private int turn;
    private boolean hasSelected;
    private boolean hasMoved;
    private int selectedX;
    private int selectedY;

    public Board(boolean shouldBeEmpty){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new Piece[N][N];
        turn = 0; //fire starts
        hasSelected = false;
        hasMoved = false;
        selectedX = -1;
        selectedY = -1;

        //set up starting config
        if (!shouldBeEmpty){
        	standardSetup();
        }

    }

    private void standardSetup(){
    	//fire side
        	for(int i = 0; i < N; i += 2){
        		Piece p = new Piece(true, this, i, 0, "pawn");
        		pieces[i][0] = p;
        	}
        	for(int i = 1; i < N; i += 2){
        		Piece p = new Piece(true, this, i, 1, "shield");
        		pieces[i][1] = p;        	
        	}
        	for(int i = 0; i < N; i += 2){
        		Piece p = new Piece(true, this, i, 2, "bomb");
        		pieces[i][2] = p;
        	}

        	//water side
        	for(int i = 1; i < N; i += 2){
        		Piece p = new Piece(false, this, i, 7, "pawn");
        		pieces[i][7] = p;
        	}
        	for(int i = 0; i < N; i += 2){
        		Piece p = new Piece(false, this, i, 6, "shield");
        		pieces[i][6] = p;        	
        	}
        	for(int i = 1; i < N; i += 2){
        		Piece p = new Piece(false, this, i, 5, "bomb");
        		pieces[i][5] = p;
        	}
    }

    private boolean validLoc(int x, int y){
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    /** Returns the piece at location (x, y). Returns null if no piece, or location invalid. */
    public Piece pieceAt(int x, int y){
        if (!validLoc(x, y)){
            return null;
        }
    	return pieces[x][y];
    }

    /* returns if you can select piece or place to move.
     * assumes x & y are valid places on board.
     * NOT YET RIGOROUSLY TESTED - need to implement hasSelected, hasMoved */

    public boolean canSelect(int x, int y){
        Piece p = pieceAt(x, y);

    	if (p != null){ // attempting to select piece
            return (!hasSelected || !hasMoved) && 
                   (turn == p.side()); // can select if haven't moved & correct side
        }else{ // attemptiong to select empty square
            Piece s = pieceAt(selectedX, selectedY);
            if (s == null){//cant select empty square if haven't selected piece!
                return false;
            }else if (hasSelected && !hasMoved){
                return validMove(selectedX, selectedY, x, y, false);
            }else if (hasSelected && s.hasCaptured()){
                return validMove(selectedX, selectedY, x, y, true);
            }else{
                return false;
            }
        }
    }

    /* returns if movement if piece at (xi, yi) can move/capture to (xf, yf)
     * strictly based on geometry, piece type, and piece side. 
     * WELL TESTED!*/
    private boolean validMove(int xi, int yi, int xf, int yf, boolean mustCapture){
    	Piece p1 = pieceAt(xi, yi);
        Piece dest = pieceAt(xf, yf);
        Piece toCapture = pieceBetween(xi, yi, xf, yf);
        boolean canCapture = toCapture != null && p1.side() == 1 - toCapture.side();

        if (p1 == null){//occurs with bombs
            return false;
        }

        if (dest != null){//destination isn't empty
            return false;
        }
        if ((xf + yf)%2 == 1 || !validLoc(xf, yf)){//either out of bounds, or red square
            return false;
        }

        if (p1.isKing()){//if king, then direction is unnecessary;
            if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1){ //adjacent.
                return !mustCapture;
            }else if(Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2
                        && canCapture){ //valid capture
                return true;
            }else{
                return false;
            }
        }else if(p1.isFire()){//can only move upwards
            if (Math.abs(xi - xf) == 1 && yf - yi == 1){ //adjacent and above
                return !mustCapture;
            }else if(Math.abs(xi - xf) == 2 && yf - yi == 2
                        && canCapture){ //valid fire capture
                return true;
            }else{
                return false;
            }

        }else{//is water
            if (Math.abs(xi - xf) == 1 && yf - yi == -1){ //adjacent and below
                return !mustCapture;
            }else if(Math.abs(xi - xf) == 2 && yf - yi == -2
                        && canCapture){ //valid fire capture
                return true;
            }else{
                return false;
            }
        }
    }

    /** returns the piece between (xi, yi) and (xf, yf). 
     *  assumes valid location for both.
     *  returns null if no piece between, or invalid request. */
    private Piece pieceBetween(int xi, int yi, int xf, int yf){
        if (Math.abs(xi - xf) != 2 || Math.abs(yi - yf) != 2){
            return null;
        }
        int x = Math.min(xi, xf) + 1;
        int y = Math.min(yi, yf) + 1;
        return pieceAt(x, y);

    }

    public void select(int x, int y){
        Piece s = pieceAt(x, y);
    	if (s != null){//selecting piece!
            selectedX = x;
            selectedY = y;
            hasSelected = true;
        }else{//selecting place to move to.
            hasMoved = true;
            Piece p = pieceAt(selectedX, selectedY);
            p.move(x, y);

            selectedX = x;
            selectedY = y;

        }

    }



    public void place(Piece p, int x, int y){
    	//only place p if location is valid
    	if (validLoc(x, y)){
    		pieces[x][y] = p;
    	}
    }

    public Piece remove(int x, int y){
    	if (!validLoc(x, y)){
            System.out.println("Attempting to remove at invalid index!");
            return null;
        }
        Piece p = pieceAt(x, y);
        if (p == null){
            System.out.println("There's no piece there!");
            return null;
        }else{
            pieces[x][y] = null;
            return p;
        }
    }

    public boolean canEndTurn(){
        Piece p = pieceAt(selectedX, selectedY);
    	return hasMoved || (p != null && p.hasCaptured());
    }

    public void endTurn(){
    	turn = 1 - turn;
        hasSelected = false;
        hasMoved = false;
        Piece p = pieceAt(selectedX, selectedY);
        if (p != null){
            p.doneCapturing();
        }
        selectedX = -1;
        selectedY = -1;
    }

    public String winner(){
    	int fireCount = 0;
        int waterCount = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                Piece p = pieceAt(i, j);
                if (p != null && p.isFire()) fireCount++;
                else if (p != null && !p.isFire()) waterCount++;

                if (fireCount != 0 && waterCount != 0){ //there's no winner yet.
                    return null;
                }
            }
        }

        //At this point, at least one side has no pieces left.
        if (fireCount == 0 && waterCount == 0){
            return "No one";
        }else if(fireCount > 0){
            return  "Fire";
        }else{
            return "Water";
        }
    }

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void drawBoard(Board b, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                /* set colors. White for selected. */
                if (b.selectedX == i && b.selectedY == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (b.pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, b.imgPath(b.pieceAt(i, j)), 1, 1);
                }
            }
        }
    }

    /** returns image path to some Piece p */
    private String imgPath(Piece p){
        String type;
        String side;
        String king = "";

        if (p.isBomb()) type = "bomb";
        else if (p.isShield()) type = "shield";
        else type = "pawn";

        if (p.isFire()) side = "-fire";
        else side = "-water";

        if (p.isKing()) king = "-crowned";

        return "img/" + type + side + king + ".png";
    }

    public static void main(String[] args) {
    	//let the games begin!
        Board b = new Board(false);


        while(b.winner() == null) {
            drawBoard(b, b.N);

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)){
                    b.select((int) x, (int) y);
                }
                
            } 
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }                  
            StdDrawPlus.show(20);
        }


    }
}