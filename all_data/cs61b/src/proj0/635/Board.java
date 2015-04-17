public class Board{
	private boolean isEmpty;
	private Piece[][] pieces;
	private static int boardLength = 8;
	private int fireLeft;
	private int waterLeft;
	private Piece pieceSelected;
	private int pieceSelectedX;
	private int pieceSelectedY;
	private boolean pieceMoved;
	private int turnTracker;




	public Board(boolean shouldBeEmpty){
		this.pieces = new Piece[boardLength][boardLength];
		this.fireLeft = 0;
		this.waterLeft = 0;
		this.pieceSelected = null;
		this.pieceSelectedX = -1;
		this.pieceSelectedY = -1;
		this.turnTracker = 0;
		this.pieceMoved = false;
		this.isEmpty = shouldBeEmpty;
		if (!this.isEmpty){
			for (int i=0; i<boardLength; i++){
				if (i%2 == 0){
					pieces[i][0] = new Piece(true, this, i, 0, "pawn");
					pieces[i][boardLength-2] = new Piece(false, this, i, boardLength-2, "shield");
					pieces[i][2] = new Piece(true, this, i, 2, "bomb");
					this.fireLeft += 2;
					this.waterLeft += 1;
				}
			else{
					pieces[i][1] = new Piece(true, this, i, 1, "shield");
					pieces[i][boardLength-1] = new Piece(false, this, i, boardLength-1, "pawn");
					pieces[i][boardLength-3] = new Piece(false, this, i, boardLength-3, "bomb");
					this.fireLeft += 1;
					this.waterLeft += 2;
				}
			}
		}
	}
	
	public static void main(String[] args){
        StdDrawPlus.setXscale(0, boardLength);
        StdDrawPlus.setYscale(0, boardLength);
        Board b = new Board(false);
        b.drawBoard(boardLength);
        while (b.winner() == null){
        	b.drawBoard(boardLength);
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x,(int) y)){
               		b.select((int) x,(int) y);
               	}
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()){
            		b.endTurn();
            	}

            }            
        	StdDrawPlus.show(30);
    	}
    }


    public boolean canSelect(int x, int y){
		if (this.pieceAt(x, y) == null){ //No piece at (x,y)
			if ((this.pieceSelected != null) && (this.pieceMoved == false)){ //There is a piece selected that hasn't been moved
				if (this.validMove(this.pieceSelectedX, this.pieceSelectedY, x, y)){  //Valid move
					return true;
				}
			}
			else if (this.pieceSelected != null && this.pieceSelected.hasCaptured()) { //pieceSelected and the piece has captured
				if (this.validMove(this.pieceSelectedX, this.pieceSelectedY, x, y)){ // Valid move
					return true;
				}
			}
		}
		else if (this.turnTracker == this.pieceAt(x, y).side()) {
			if (this.pieceSelected == null || (this.pieceSelected != null && this.pieceMoved == false)) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y){
		if (this.pieceAt(x,y) != null){
			this.pieceSelected = this.pieceAt(x,y);
			this.pieceSelectedX = x;
			this.pieceSelectedY = y;
		}
		else{
			this.pieces[x][y] = this.pieceAt(pieceSelectedX, pieceSelectedY);
			this.pieces[pieceSelectedX][pieceSelectedY] = null;
			this.pieceSelected.move(x,y);
			this.pieceSelectedX = x;
			this.pieceSelectedY = y;
			this.pieceMoved = true;
		}
	}

	public boolean canEndTurn(){
		if (this.pieceMoved){
			return true;
		}
		else{
			return false;
		}
	}

	public void endTurn(){
		if (canEndTurn()){
			this.pieceSelected.doneCapturing();
			this.pieceSelected = null;
			this.pieceSelectedX = -1;
			this.pieceSelectedY = -1;
			this.pieceMoved = false;
			if (turnTracker == 1){
				turnTracker = 0;
			}
			else{
				turnTracker = 1;
			}
		}
	}



 	private boolean validMove(int xi, int yi, int xf, int yf){
 		Piece curPiece = this.pieceAt(xi, yi);
 		boolean pieceKinged = curPiece.isKing();
 		boolean canMoveUp = pieceKinged || this.pieceAt(xi,yi).isFire();
 		boolean canMoveDown = pieceKinged || !this.pieceAt(xi,yi).isFire();
 		boolean validEndPos = !(xf > boardLength-1 || xf < 0 || yf > boardLength-1 || yf < 0 || (this.pieceAt(xf, yf) != null));
    	if (validEndPos){
    		if (canMoveUp){
    			if (((yi + 1) == yf) && (((xi + 1) == xf) || ((xi - 1) == xf)) && !curPiece.hasCaptured()){
    				return true;
    			}
    			else if ((yi + 2) == yf){
    				if (((xi + 2) == xf) && (this.pieceAt(xi + 1, yi + 1) != null) && this.pieceAt(xi + 1, yi + 1).side() != this.turnTracker) {
    					return true;
    				}
    				else if (((xi - 2) == xf) && (this.pieceAt(xi - 1, yi + 1) != null) && this.pieceAt(xi - 1, yi + 1).side() != this.turnTracker) {
						return true;
    				}
    			}
    		}
    		if (canMoveDown) {
    			if (((yi - 1) == yf) && (((xi + 1) == xf) || ((xi - 1) == xf)) && !curPiece.hasCaptured()){
    				return true;
    			}
    			else if ((yi - 2) == yf) {
    				if (((xi + 2) == xf) && (this.pieceAt(xi+1, yi-1) != null) && this.pieceAt(xi + 1, yi - 1).side() != this.turnTracker) {
    					return true;
    				}
    				else if (((xi - 2) == xf) && (this.pieceAt(xi - 1, yi - 1) != null) && this.pieceAt(xi - 1, yi - 1).side() != this.turnTracker) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
	}


    public void place(Piece p, int x, int y){
    	if (p != null && x<boardLength && y<boardLength){
    		pieces[x][y] = p;
    		if (p.isFire()){
    			this.fireLeft += 1;
    		}
    		else{
    			this.waterLeft += 1;
    		}
    	}
    }

    public Piece pieceAt(int x, int y){
    	if (x>boardLength-1 || y>boardLength-1 || x<0 || y<0){
    		return null;
    	}
    	return pieces[x][y];
    }
	
	public Piece remove(int x, int y){
		if (x>boardLength-1 || y>boardLength-1){
			System.out.println("Input out of bounds");
			return null;		
		}
		else if (this.pieceAt(x, y) == null){
			System.out.println("No piece at " + x + ", " + y);
			return null;
		}
		else{
			Piece temp = this.pieceAt(x, y);
			if (temp.isFire()){
				this.fireLeft -= 1;
			}
			else{
				this.waterLeft -= 1;
			}
			pieces[x][y] = null;
			return temp;
		}
	}

	public String winner(){
		if ((this.waterLeft>0 && this.fireLeft>0)){
			return null;
		}
		else if (this.waterLeft>0){
			return "Water";
		}
		else if (this.fireLeft>0){
			return "Fire";
		}
		else{
			return "No one";
		}
	
	}

	private void drawBoard(int N){
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }  
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j]!=null){
                	if (pieces[i][j].isFire()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/" + typeHelp(pieces[i][j]) + "-fire" + kingHelp(pieces[i][j]) + ".png", 1, 1);
					}
                	else{
                		StdDrawPlus.picture(i + .5, j + .5, "img/" + typeHelp(pieces[i][j]) + "-water" + kingHelp(pieces[i][j]) +".png", 1, 1);               		
                	}              
   				}         
 			} 
 		}
 	}     

	private String typeHelp(Piece x){
		if (x.isBomb()){
			return "bomb";
		}
		else if (x.isShield()){
			return "shield";
		}
		else{
			return "pawn";
		}
	}

	private String kingHelp(Piece x){
		if (x.isKing()){
			return "-crowned";
		}
		return "";
	}
}	

