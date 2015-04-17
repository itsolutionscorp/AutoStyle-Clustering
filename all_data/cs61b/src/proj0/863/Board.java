public class Board{
	//Instance variables for empty board and piece location tracker
	private Piece[][] pieces = new Piece[8][8];
	private Piece p;
	private int turn = 0;
	private int selectedX = -1;
	private int selectedY = -1;
	private boolean hasMoved = false;

	public Board(boolean isEmpty){
		// Initializes pieces in default configuration if Board is not empty
		if (!isEmpty){
			for (int x = 0; x < 8; x += 2){
				this.pieces[x][0] = new Piece(true, this, x, 0, "pawn");
				this.pieces[x + 1][1] = new Piece(true, this, (x + 1), 1, "shield");
				this.pieces[x][2] = new Piece(true, this, x, 2, "bomb");

				this.pieces[x + 1][5] = new Piece(false, this, (x + 1), 5, "bomb");
				this.pieces[x][6] = new Piece(false, this, x, 6, "shield");
				this.pieces[x + 1][7] = new Piece(false, this, (x+1), 5, "pawn");
			}
		}
	}

	// Draws an N x N board
	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j]!= null){
                	if (pieces[i][j].isFire() && !pieces[i][j].isShield() && !pieces[i][j].isBomb()){
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	}
                	if (pieces[i][j].isFire() && pieces[i][j].isShield() && !pieces[i][j].isBomb()){
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	}
                	if (pieces[i][j].isFire() && !pieces[i][j].isShield() && pieces[i][j].isBomb()){
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	}

                	if (!pieces[i][j].isFire() && !pieces[i][j].isShield() && !pieces[i][j].isBomb()){
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	}
                	if (!pieces[i][j].isFire() && pieces[i][j].isShield() && !pieces[i][j].isBomb()){
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	}
                	if (!pieces[i][j].isFire() && !pieces[i][j].isShield() && pieces[i][j].isBomb()){
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	}

                }
            }
        }
    }

    // Gets the piece at position (x, y) on the board
    public Piece pieceAt(int x, int y){
    	if (x < 0 || x > 7 || y < 0 || y > 7){
    		return null;
    	}
    	return this.pieces[x][y];
    }

    // Places a piece p at position (x, y)
    public void place(Piece p, int x, int y){
    	if (x < 0 || x > 7 || y < 0 || y > 7 || p == null){
    	}
    	else{
    		this.pieces[x][y] = p;
    	}
    }

    // Removes a piece at (x, y) and returns it
    public Piece remove(int x, int y){
    	if (x < 0 || x > 7 || y < 0 || y > 7){
    		System.out.println("Coordinate selected out of bounds.");
    		return null;
    	}
    	if (this.pieces[x][y] == null){
    		System.out.println("No Piece exists at location.");
    		return null;
    	}
    	Piece temp = this.pieces[x][y];
    	this.pieces[x][y] = null;
    	return temp;
    }

    // Returns if current player can end turn
    public boolean canEndTurn(){
    	return hasMoved;
    }

    // Ends a turn and switches the players
    public void endTurn(){
    	if (this.turn == 0){
    		this.turn = 1;
    	}
    	else{
    		this.turn = 0;
    	}
    	hasMoved = false;
    	selectedX = -1;
    	selectedY = -1;
    }

    // Helper method to canSelect to determine a valid move.
    private boolean validMove(int xi, int yi, int xf, int yf){
    	int interX = (xi + xf) >> 1;
    	int interY = (yi + yf) >> 1;

    	if (xf < 0 || yf < 0 || xf > 7 || yf > 7 || this.pieceAt(xf, yf) != null || this.pieceAt(xi, yi) == null){
    		return false;
    	}
    	if (this.pieceAt(xi, yi).isFire() && yf == yi + 1 && Math.abs(xf - xi) == 1){
    		return true;
    	}
    	if (!this.pieceAt(xi, yi).isFire() && yf == yi - 1 && Math.abs(xf - xi) == 1){
    		return true;
    	}
    	if (this.pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1){
    		return true;
    	}

    	if(this.pieceAt(xi, yi).isFire() && yf == yi + 2 && Math.abs(xf - xi) == 2 && !this.pieceAt(interX, interY).isFire()){
    		return true;
    	}
    	if(!this.pieceAt(xi, yi).isFire() && yf == yi - 2 && Math.abs(xf - xi) == 2 && this.pieceAt(interX, interY).isFire()){
    		return true;
    	}
    	if(this.pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2 && Math.abs(this.pieceAt(interX, interY).side() - this.pieceAt(xi, yi).side()) == 1 ){
    		return true;
    	}

    	else{
    		return false;
    	}
    }

    // Returns true if square (x, y) can be selected
    public boolean canSelect(int x, int y){
    	if (x < 0 || y < 0 || x > 7 || y > 7){
    		return false;
    	}
    	if (this.pieceAt(x,y) != null && this.turn != pieceAt(x, y).side()) {
    		return false;
    	}
    	if (this.pieceAt(x, y) != null){
    		if (!hasMoved){
    			return true;
    		}
    	}

    	if (this.pieceAt(x, y) == null){
    		if (!hasMoved && this.validMove(selectedX, selectedY, x, y)){
    			return true;
    		}

    		if (pieceAt(selectedX, selectedY) != null && pieceAt(selectedX, selectedY).hasCaptured()){
    			return true;
    		}

            if (this.validMove(selectedX, selectedY, x, y) && Math.abs(y - selectedY) == 2 && Math.abs(x - selectedX) == 2){
                return true;
            }
    	}
    	return false;
    }

    // Selects a square at (x, y)
    public void select(int x, int y){
    	if (selectedX == x && selectedY == y){
    		selectedX = x;
    		selectedY = x;
    	}
    	else if (this.pieceAt(x, y) != null){
    		selectedX = x;
    		selectedY = y;
    	}
    	else if (this.pieceAt(x, y) == null){
    		this.pieceAt(selectedX, selectedY).move(x, y);
    		selectedX = x;
    		selectedY = y;
    		hasMoved = true;
    	}
    	else{
    		selectedX = x;
    		selectedY = y;
    	}
    }

    // Returns winner
    public String winner(){
    	int waterPieces = 0;
    	int firePieces = 0;
    	for (int i=0; i < 8; i++){
    		for (int j=0; j < 8; j++){
    			if (pieces[i][j] != null && pieces[i][j].isFire()){
    				firePieces +=1;
    			}
    			if (pieces[i][j] != null && !pieces[i][j].isFire()){
    				waterPieces +=1;
    			}
    		}
    	}
    	if (firePieces == 0 && waterPieces == 0){
    		return "None";
    	}
    	if (firePieces == 0){
    		return "Water";
    	}
    	if (waterPieces == 0){
    		return "Fire";
    	}
    	else{
    		return null;
    	}
    }
	public static void main (String args[]){
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Piece temp = null;
        int count = 0;
        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
            	int x = (int) StdDrawPlus.mouseX();
            	int y = (int) StdDrawPlus.mouseY();
            	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	if (b.canSelect(x, y) && b.pieceAt(x, y) != null){
            		System.out.println(count);
            		count ++;
            		b.select(x, y);
            		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            	}
            	else if (b.canSelect(x, y)){
            		b.select(x, y);
            		StdDrawPlus.filledSquare(b.selectedX + .5, b.selectedY + .5, .5);
            		if (b.pieceAt(b.selectedX, b.selectedY) != null){
            			StdDrawPlus.filledSquare(b.selectedX + .5, b.selectedY + .5, .5);
            		}
            		b.hasMoved = true;
            	}
            	else if (b.canEndTurn() && StdDrawPlus.isSpacePressed()){
            		b.endTurn();
            	}
            	else{

            	}
            }
            StdDrawPlus.show(10);
        }
	}
}