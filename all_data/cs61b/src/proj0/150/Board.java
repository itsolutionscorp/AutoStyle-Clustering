public class Board {
	 /** Location of pieces. */
    private Piece selected;
    private Piece[][] board;
    private int size = 8;
    private String turn = "fire";
    private int selectedX;
    private int selectedY;
    private boolean hasMoved = false;
    private int firePieces;
    private int waterPieces;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i == selectedX && j == selectedY && selected != null){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece curPiece = this.pieceAt(i,j);
                if (curPiece != null){
	  				this.draw(i, j, curPiece);
	            }
            }
        }
    }

    private void draw(int i, int j, Piece piece){
        //draw water-shield
        if (piece.isShield() == true && piece.isFire() == false && piece.isKing() == false){
        	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png",1, 1);
        }
        //draw water-bombs
        else if (piece.isBomb() == true && piece.isFire() == false && piece.isKing() == false){
        	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png",1, 1);
        }
        //draw fire-shields
        else if (piece.isShield() == true && piece.isFire() == true && piece.isKing() == false){
        	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png",1, 1);
        }
        // draw fire-bombs
        else if (piece.isBomb() == true && piece.isFire() == true && piece.isKing() == false){
        	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png",1, 1);
        }
        // draw water-pawn
        else if (piece.isFire() == false && piece.isKing() == false) {
        	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
        }
        // draw fire-pawn
    	else if (piece.isFire() == true && piece.isKing() == false) {
        	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        }
        //draw kinged water-shield
        if (piece.isShield() == true && piece.isFire() == false && piece.isKing() == true){
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png",1, 1);
        }
        //draw kinged water-bombs
        else if (piece.isBomb() == true && piece.isFire() == false && piece.isKing() == true){
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png",1, 1);
        }
        //draw kinged fire-shields
        else if (piece.isShield() == true && piece.isFire() == true && piece.isKing() == true){
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png",1, 1);
        }
        // draw kinged fire-bombs
        else if (piece.isBomb() == true && piece.isFire() == true && piece.isKing() == true){
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png",1, 1);
        }
        // draw kinged water-pawn
        else if (piece.isFire() == false && piece.isKing() == true) {
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
        }
        // draw kinged fire-pawn
        else if (piece.isFire() == true && piece.isKing() == true) {
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
        }
    }


    public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == true){
			board = new Piece[size][size];
		}
		else{
			board = new Piece[size][size];
            startPiece(this);
		}
	}

	public void place(Piece p, int x, int y){
		if((x < size && x >= 0) || (y < size && y >= 0)){
            if(p != null){
                if(p.isFire() == true){
                    firePieces += 1;
                }
                else{
                    waterPieces +=1;
                }
            }
			board[x][y] = p;
		}
	}

	public Piece pieceAt(int x, int y){
		if (x >= size || y >= size || x < 0 || y < 0 || board[x][y] == null){
			return null;
		}
		else{
			return board[x][y];
		}
	}

    public boolean canEndTurn(){
        // if(hasMoved == true || selected.hasCaptured() == true){
        if(hasMoved == true){
            return true;
        }
        else{
            return false;
        }
    }

    public void endTurn(){
        if (turn == "fire"){
            turn = "water";
        }
        else{
            turn = "fire";
        }
        if (selected != null){
            selected.doneCapturing();
        }
        hasMoved = false;
        selected = null;
        selectedX = 0;
        selectedY = 0;
    }

	public Piece remove(int x, int y){
		if(x >= size || y >= size){
			System.out.println("The inputs are outside of the size. Your x is: " + Integer.toString(x) + " and your y is: " + Integer.toString(y));
			return null;
		}
		Piece deadPiece = board[x][y];
		board[x][y] = null;
		return deadPiece;
	}

    private void makeAndPlace(boolean team, Board b, int i, int j, String type){
        Piece piece = new Piece(team, b, i, j, type);
        b.place(piece, i, j);
    }

    private void startPiece(Board board){
        for (int i = 0; i < size; i ++){
            for (int j = 0; j < size; j++){
                // create water-pawn
                if (j == 7 && i % 2 == 1){
                    makeAndPlace(false, board, i, j, "pawn");
                }
                // create water-shield
                if (j == 6 && i % 2 == 0){
                    makeAndPlace(false, board, i, j, "shield");
                }
                // create water-bombs
                if (j == 5 && i % 2 == 1){
                    makeAndPlace(false, board, i, j, "bomb");
                }
                // create fire-pawn
                if (j == 0 && i % 2 == 0){
                    makeAndPlace(true, board, i, j, "pawn");
                }
                // create fire-shields
                if (j == 1 && i % 2 == 1){
                    makeAndPlace(true, board, i, j, "shield");
                }
                // create fire-bombs
                if (j == 2 && i % 2 == 0){
                    makeAndPlace(true, board, i, j, "bomb");
                }
            }
        }
    }

    private boolean checkSideFP(String turn, Boolean isFire){
        //If it is fire's turn, check if the piece is a fire piece.
        if (turn == "fire" && isFire == true){
            return true;
        }
        //If it is a water's turn check if the piece is a water piece.
        else if(turn == "water" && isFire == false){
            return true;
        }
        //If neither conditions are met, return false.
        else{
            return false;
        }
    }

    public boolean canSelect(int x, int y){
        // In the case that x and y are outside the possible selection box.
        if(checkSize(x, y) == false){
            return false;
        }
        Piece curPiece = pieceAt(x,y);
        if(curPiece!=null && hasMoved == false){
            boolean isFire = curPiece.isFire();
            return checkSideFP(turn, isFire);
        }
        if (selected != null){
            boolean selectedIsFire = selected.isFire();
            boolean isKing = selected.isKing();
            return validMove(selectedX, selectedY, x, y, selectedIsFire, isKing);
        }
        
        return false;
    }

    

    private boolean validMove(int xi, int yi, int xf, int yf, boolean isFire, boolean isKing){

        //for fire side
        if (isFire == true){
            // normal moves
            if((xi+1 == xf || xi-1 == xf) && yi+1 == yf && hasMoved == false){
                return true;
            }
            //can capture?
            else if(xi+2 == xf && yi+2 == yf){
                return checkFireEnemy(xi+1, yi+1);
            }
            else if(xi-2 == xf && yi+2 == yf){
                return checkFireEnemy(xi-1, yi+1);
            }

            // king special cases
            else if(isKing == true){       
                //normal king move
                if((xi+1 == xf || xi-1 == xf) && yi-1 ==yf && hasMoved == false){
                    return true;
                }
                //king capture
                else if(xi+2 == xf && yi-2 == yf){
                    return checkFireEnemy(xi+1, yi-1);
                }
                else if(xi-2 == xf && yi-2 == yf){
                    return checkFireEnemy(xi-1, yi-1);
                }
                else{
                    return false;
                }
            }

            //no other possible cases
            else{
                return false;
            }
        }
        //for water side
        else{
            //normal move
            if((xi+1 == xf || xi-1 == xf) && yi-1 == yf && hasMoved == false){
                return true;
            }
            //can capture? 
            else if(xi+2 == xf && yi-2 ==yf){
                return checkWaterEnemy(xi+1,yi-1);
            }
            else if(xi-2 == xf && yi-2 == yf){
                return checkWaterEnemy(xi-1, yi-1);
            }
            // king special cases
            else if(isKing == true){
                //normal king move
                if((xi+1 == xf || xi-1 == xf) && yi+1 ==yf && hasMoved == false){
                    return true;
                }
                //king capture
                else if(xi+2 == xf && yi+2 == yf){
                    return checkFireEnemy(xi+1, yi+1);
                }
                else if(xi-2 == xf && yi+2 == yf){
                    return checkFireEnemy(xi+1, yi+1);
                }
                else{
                    return false;
                }
            }
            // If not then it is not a valid move.
            else{
                return false;
            }
        }
    }

    private boolean isCapture(int yi, int xi, int yf, int xf){
        // if (yi + 2 == yf || yi - 2 == yf){
        //     return true;
        // }
        if(yi+2 == yf && xi +2 ==xf){
            if(null != pieceAt(xi+1, yi+1)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(yi-2 == yf && xi +2 ==xf){
            if(null != pieceAt(xi+1, yi-1)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(yi +2 ==yf && xi -2==xf){
            if(null != pieceAt(xi-1, yi+1)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(xi-2 == xf && yi-2 == yf) {
            if(null != pieceAt(xi-1, yi-1)){
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

    private void checkAndKill(int x, int y, String location){
        Piece topLeft = pieceAt(x-1, y+1);
        Piece topRight = pieceAt(x+1, y+1);
        Piece bottomLeft = pieceAt(x-1, y-1);
        Piece bottomRight = pieceAt(x+1, y-1);
        //kills topLeft
        if (topLeft != null && topLeft.isShield() == false && (!location.equals("bottomRight"))){
            killPiece(x-1, y+1);
        }
        //kills topRight
        if (topRight != null && topRight.isShield() == false && (!location.equals("bottomLeft"))){
            killPiece(x+1, y+1);
        }
        //kills bottomLeft
        if (bottomLeft != null && bottomLeft.isShield() == false && (!location.equals("topRight"))){
            killPiece(x-1, y-1);
        }
        //kills bottomRight
        if (bottomRight != null && bottomRight.isShield() == false && (!location.equals("topLeft"))){
            killPiece(x+1, y-1);
        }
    }

    private void reduceNumbers(Piece deadPiece){
        if(deadPiece.isFire()){
            firePieces -= 1;
        }
        else{
            waterPieces -= 1;
        }
    }

    private void killPiece(int x, int y){
        Piece dead = remove(x, y);
        reduceNumbers(dead);
    }

    public void select(int x, int y){
        Piece targetPlace = pieceAt(x,y);
        //If I have a selected piece and the target place doesn't have a piece.
        if (selected != null && targetPlace == null){
            //if this is a capture move
            if(isCapture(selectedY, selectedX, y, x)){

                //if it is toward the right
                if(selectedX+2 == x){
                    //if it is toward the top
                    if(selectedY+2 == y){
                        //if the current piece is a bomb and it is moving toward top right
                        if(selected.isBomb() == true){
                            //kill all pieces from bomb explosion except the piece that was jumped over.
                            checkAndKill(x,y, "topRight");
                            //kill the bomb
                            killPiece(selectedX, selectedY);
                        }
                            // kill the piece that was captured
                            killPiece(selectedX+1, selectedY+1);
                    }
                    else{
                        //goes to the bottom right
                        if(selected.isBomb() == true){
                            checkAndKill(x,y, "bottomRight");
                            killPiece(selectedX, selectedY);
                        }
                            killPiece(selectedX+1, selectedY-1); 
                    }
                }
                else{      
                    if(selectedY+2 == y){
                        //goes to the top left
                        if(selected.isBomb() == true){
                            checkAndKill(x,y, "topLeft");
                            killPiece(selectedX, selectedY);
                        }                        
                            killPiece(selectedX-1, selectedY+1);                            
                    }
                    else{
                        //goes to the bottom left
                        if(selected.isBomb() == true){
                            checkAndKill(x,y, "bottomLeft");
                            killPiece(selectedX, selectedY);
                        }                                    	
                            killPiece(selectedX-1, selectedY-1);     
                    }
                }
            }
            //If this is a normal move
            // I need to use selected x and y to figure out whether or not it is a capture.
            selected.move(x,y);
            hasMoved = true;
            Piece movingPiece = remove(selectedX, selectedY);
            place(movingPiece, x, y);
        }

        //If I am just selecting a piece.
        else{
            selected = pieceAt(x,y);
        }
        selectedX = x;
        selectedY = y;
    }

    private boolean checkFireEnemy(int x, int y){
        Piece enemyPiece = pieceAt(x,y);
        if(enemyPiece == null){
            return false;
        }
        boolean isFire = enemyPiece.isFire();
        if (isFire == true){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkWaterEnemy(int x, int y){
        Piece enemyPiece = pieceAt(x,y);
        if(enemyPiece == null){
            return false;
        }
        boolean isFire = enemyPiece.isFire();
        if (isFire == false){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkSize(int x, int y){
        return (x < size && x >= 0) || (y < size && y >= 0);
    }

    public String winner(){
        if (waterPieces == 0 && firePieces == 0){
            return "No one";
        }
        else if(waterPieces == 0){
            return "Fire";
        }
        else if (firePieces == 0){
            return "Water";
        }
        else{
            return null;
        }

    }

    public static void main(String[] args) {
    	int size = 8;
        StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);
        boolean player1 = true;
        Board gameBoard = new Board(true);
        gameBoard.startPiece(gameBoard);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            gameBoard.drawBoard(size);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int tempx = (int)x;
                int tempy = (int)y;
                if(gameBoard.canSelect(tempx, tempy)){
                    gameBoard.select(tempx,tempy);
                }
            }            
            if (StdDrawPlus.isSpacePressed()){
                if(gameBoard.canEndTurn() == true){
                    gameBoard.endTurn();
                    gameBoard.winner();

                }
            }
            StdDrawPlus.show(100);
        }
    }
}