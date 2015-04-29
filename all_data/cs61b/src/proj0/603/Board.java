 public class Board {
	private Piece[][] piecesOnBoard = new Piece[8][8];
	private Piece currentPiece=null;
	private boolean turn = true; //True is Fire's turn and False is Water's turn.
    private boolean has_moved = false;
    private boolean shouldBeEmpty; //Used to store value of shouldBeEmpty argument of constructor.
    private int curX = 10;
    private int curY = 10;

	public Board (boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
        if(!shouldBeEmpty){
            initialConfiguration();
        }
	}

	//Draws the Board. HELPER to drawBoard().
	private void drawBoardHelper(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if( i == curX && j == curY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                       StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }
    // Draws the Board. INVOKED BY main().
    private void drawBoard(int N){
        int i = 0;
        while(i < N) {
            drawBoardHelper(N);           
            i++;
        }
    }

    private void placeOnBoard (){
        String side;
        String type;
        Piece temp; 
        for(int i =0; i<8; i++){
            for(int j=0; j<8; j++){
                if(pieceAt(i,j) != null){ //There is a piece at (i,j) 
                    temp = pieceAt(i,j);
                    if(temp.side() == 1) {side = "fire";}
                    else { side = "water";}

                    if(temp.isBomb()) {
                        type = "bomb";
                        if(temp.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-"+side+"-crowned.png", 1, 1);
                        }
                        StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-"+side+".png", 1, 1);
                    }
                    else if(temp.isShield()) {
                        type = "shield"; 
                        if(temp.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-"+side+"-crowned.png", 1, 1);
                        }
                        StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-"+side+".png", 1, 1);
                    }
                    else {
                        type = "pawn";
                        if(temp.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-"+side+"-crowned.png", 1, 1);
                        }
                        StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-"+side+".png", 1, 1);
                    }
                    
                }
            }

        }
    }
    //Puts the initial configuration on the Board. INVOKED BY main().
    private  void initialConfiguration() { //Matrix initializer
    	for(int j = 0; j<8; j++) {
    		if (j%2 == 0) { // That is j is even
    			for(int i = 0; i<8; i+=2) {
    				if(j == 0) { //Pawn Fire
    					piecesOnBoard[i][j] = new Piece(true, this, i, j, "pawn");
    				}
    				else if (j == 6) { //Shield Water
    					piecesOnBoard[i][j] = new Piece(false, this, i, j, "shield");
    				}
    				else if (j == 2){ //Bomb Fire
    					piecesOnBoard[i][j] = new Piece(true, this, i, j, "bomb");
    				}
    			}
    		}
    		else { // That is j is odd
    			for (int i = 1; i<8; i+=2) {
    				if(j == 7) { //Pawn Water
    					piecesOnBoard[i][j] = new Piece(false, this, i, j, "pawn");
    				}
    				else if (j == 1) { //Shield Fire
    					piecesOnBoard[i][j] = new Piece(true, this, i, j, "shield");
    				}
    				else if (j == 5){ //Bomb Water
    					piecesOnBoard[i][j] = new Piece(false, this, i, j, "bomb");
    				}
    			}
    		}

    	}
    }
 
    public Piece pieceAt(int x, int y) {
    	if (x > 7 || x < 0 || y > 7 || y < 0) {
    		return null;
    	}
    	if (this.piecesOnBoard[x][y] == null) {
    		return null;
    	}
    	return this.piecesOnBoard[x][y];
    }
    

	public boolean canSelect (int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
            return false;
        }
        Piece p = pieceAt(x,y);
        if(turn){ //Fire's turn
            if(p == null){ // If we select an empty space.
                if(currentPiece != null && !currentPiece.hasCaptured()){
                    return !has_moved && validMove(curX, curY, x, y);
                }
                if(curX == 10 && curY == 10){
                    return false;
                }
                if(currentPiece != null && currentPiece.hasCaptured()){
                    if(this.validMove(curX, curY, x, y) && Math.abs(y - curY) == 2){ //This is checking if allowed to move there.
                        return true;
                    }
                }
            }
            else{ //If we select another Piece
                if(p.isFire()){
                    if(!has_moved){
                        return true;
                    }
                    else{
                        return false;
                    }
                } 
            }

        }
        else{ //Water's turn
            if(p == null){ // If we select an empty space.
                if(currentPiece != null && !currentPiece.hasCaptured()){
                    return !has_moved && validMove(curX, curY, x, y);
                }
                if(curX == 10 && curY == 10){
                    return false;
                }
                if(currentPiece != null && currentPiece.hasCaptured()){
                    if(this.validMove(curX, curY, x, y) && Math.abs(y - curY) == 2){ //This is checking if allowed to move there.
                        return true;
                    }
                }
            }   
            else{ //If we select another Piece
                if(!p.isFire()){
                    if(!has_moved){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }

    return false;

	}

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece destination_piece = pieceAt(xf,yf);
        Piece initial_piece = pieceAt(xi, yi);
        int xm, ym;
        if(turn) { //Fire's turn 
            if(Math.abs(xf - xi) == 1 && yf - yi == 1) { 
                if(destination_piece == null){
                    return true;
                }
            } else if(Math.abs(xf - xi) == 2 && yf - yi == 2){
                xm = (xf + xi)/2;
                ym = (yf +yi)/2;
                if(pieceAt(xm,ym) != null){
                    if(pieceAt(xm,ym).side() != initial_piece.side() && destination_piece == null){
                        return true;
                    }
                }
            } else if(initial_piece.isKing()) {
                if(Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) { 
                    if(destination_piece == null){
                        return true;
                    }
                } else if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
                    xm = (xf + xi)/2;
                    ym = (yf +yi)/2;
                    if(pieceAt(xm,ym).side() != initial_piece.side() && destination_piece == null) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        } else { //Water's turn
            if(Math.abs(xf - xi) == 1 && yf - yi == -1) {
                if(destination_piece == null){
                    return true;
                }
            } else if(Math.abs(xf - xi) == 2 && yf - yi == -2){
                xm = (xf + xi)/2;
                ym = (yf +yi)/2;
                if(pieceAt(xm,ym).side() != initial_piece.side() && destination_piece == null){
                    return true;
                }
            } else if(initial_piece.isKing()){
                if(Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) { 
                    if(destination_piece == null){
                        return true;
                    }
                } else if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2){
                    xm = (xf + xi)/2;
                    ym = (yf +yi)/2;
                    if(pieceAt(xm,ym).side() != initial_piece.side() && destination_piece == null){
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

	public void select(int x, int y) {
        Piece destination_piece = pieceAt(x,y);        
        if (destination_piece != null) {
            currentPiece = destination_piece;
        }
        else {
            currentPiece.move(x, y);
            has_moved = true;
        }
        curX = x;
        curY = y;
        currentPiece = pieceAt(x, y);
	}

	public Piece remove(int x, int y) {
		Piece removed_piece;
		if (x > 8 || x < 0 || y > 8 || y < 0) { 
    		System.out.println("Co-ordinates are out of bounds.");
    		return null;
    	}

    	if(this.pieceAt(x,y) == null){ //No piece at (x,y)
    		System.out.println("No piece at given position.");
    		return null;
    	}
    	removed_piece = piecesOnBoard[x][y];
    	piecesOnBoard[x][y] = null;
    	return removed_piece;
	}

    public void place(Piece p ,int x, int y){ //Check
        if (x > 8 || x < 0 || y > 8 || y < 0) { 
            System.out.println("Co-ordinates are out of bounds.");
        }

        if(p == null){ //No piece 
            System.out.println("No piece.");
        }
        piecesOnBoard[x][y] = p;
    }

	public boolean canEndTurn() { 
        return has_moved;
	}

	public void endTurn() {
		turn = !turn; // Alternates the turn. True corresponds to fire and False to Water.
        has_moved = false;
        if (currentPiece != null)
            currentPiece.doneCapturing();
        currentPiece = null;
        curX = 10;
        curY = 10;
	}

    public String winner(){
        int numFire = 0;
        int numWater = 0;
        for(int i = 0; i <8; i++){
            for (int j = 0; j<8; j++) {
                if(piecesOnBoard[i][j] != null){
                   if(piecesOnBoard[i][j].isFire()) { //Fire Piece
                        numFire += 1;
                    }
                    if(!piecesOnBoard[i][j].isFire()) { //Water Piece
                        numWater+=1;
                    }
                }
            }
        }
        if(numFire == 0 && numWater == 0){
            return "No one";
        }
        else if (numFire == 0 && numWater != 0) {
            return "Water";
        }
        else if (numFire != 0 && numWater == 0) {
            return "Fire";
        }
        else{
            return null; // Game still progressing
            }
    }

	public static void main(String[] args) {
        Board currentBoard = new Board(false);
        /* Makes the initial Board*/
        if (currentBoard.shouldBeEmpty) {
            /* Initializes an empty Board
             * This part of the Function draws the blank board. Uses drawBoard().
             */
            currentBoard.drawBoard(8);
            }
        else {
            /* Initializes the default Board
             * This part of the Function draws the blank board. Uses drawBoard().
             */
            currentBoard.drawBoard(8);
            // Puts the initial configuration on the Board
            currentBoard.placeOnBoard();

        }

        while(currentBoard.winner() == null){ //While the game continues.
            //Monitors for  mouse click.
            if(StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xi = (int) x;
                int yi = (int) y;
                if(currentBoard.canSelect(xi,yi)){
                    currentBoard.select(xi,yi);
                    }
            }

            if(StdDrawPlus.isSpacePressed()) {
                if(currentBoard.canEndTurn()) {
                    currentBoard.endTurn(); 
                }
            }
            StdDrawPlus.show(10);
            currentBoard.drawBoard(8);
            currentBoard.placeOnBoard();
        }
        System.out.println(currentBoard.winner());
    }
}


