public class Board {
	
	private Piece[][] pieces = new Piece[8][8];
	private int activeX = -1;
	private int activeY = -1;
	private boolean FireTurn = true;
	private boolean hasMoved = false;
	private Piece activePiece;
	private int selectX = -1;
	private int selectY = -1;
	
	
	public static void main(String[] args){
		Board b = new Board(false);
		while(true) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if(b.canSelect(x, y)){
					b.select(x, y);
					b.selectX = x;
					b.selectY = y;
				}
			}       
			if (StdDrawPlus.isSpacePressed()){
				if(b.canEndTurn()){
					b.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
	}
	
	private void drawBoard(){
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(i == selectX && j == selectY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null){
					if(pieces[i][j].isFire()){
						if(pieces[i][j].isBomb()) {
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else{
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
						}
						else if(pieces[i][j].isShield()){
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else{
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
						}
						else if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
						}
						else{
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						}
					}
					else{
						if(pieces[i][j].isBomb()) {
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							else{
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
						}
						else if(pieces[i][j].isShield()){
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else{
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
						}
						else if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
						}
						else{
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
		}
	}
	
	public Board(boolean shouldBeEmpty){
		if(!shouldBeEmpty){
			for(int i = 0; i < 8; i += 2){
			pieces[i][0] = new Piece(true, this, i, 0, "normal");
			}
			for(int i = 1; i < 8; i += 2){
			pieces[i][1] = new Piece(true, this, i, 1, "shield");
			}
			for(int i = 0; i < 8; i += 2){
			pieces[i][2] = new Piece(true, this, i, 2, "bomb");
			}
			for(int i = 1; i < 8; i += 2){
			pieces[i][5] = new Piece(false, this, i, 5, "bomb");
			}
			for(int i = 0; i < 8; i += 2){
			pieces[i][6] = new Piece(false, this, i, 6, "shield");
			}
			for(int i = 1; i < 8; i += 2){
			pieces[i][7] = new Piece(false, this, i, 7, "normal");
			}
		}
	}

	private static boolean inBounds(int x, int y){
		if((x > 7) || (x < 0) || (y < 0) || (y > 7)){
			return false;
		}
		return true;
	}
	
	private static boolean oppositeSides(Piece a, Piece b){
		if(a == null || b == null){
			return false;
		}
		if(a.isFire() && !b.isFire()){
			return true;
		}
		if(!a.isFire() && b.isFire()){
			return true;
		}
		return false;
	}
	
	public Piece pieceAt(int x, int y){
		if(!inBounds(x, y)){
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y){
		if(inBounds(x, y)){
			pieces[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y){
		if(!inBounds(x, y)){
			System.out.println("Input is out of bounds.");
			return null;
		}
		if(pieces[x][y] == null){
			System.out.println("There is no piece there.");
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(!inBounds(xi, yi) || !inBounds(xf, yf)){ return false; } //All parameters in bounds
		if(pieces[xi][yi] == null || pieces[xf][yf] != null){ return false; } //No active piece, or selecting occupied space
		int yMovement = yf - yi;
		int xMovement = xf - xi;
		if(yMovement < 0){ //No backwards movement
			if(pieces[xi][yi].isFire() && !pieces[xi][yi].isKing()){
				return false;
			}
		}
		if(yMovement > 0){ //No backwards movement
			if(!pieces[xi][yi].isFire() && !pieces[xi][yi].isKing()){
				return false;
			}
		}
		if(Math.abs(yMovement) == 1 && Math.abs(xMovement) == 1){ //Single space movement whatevs
			return true;
		}
		else if(Math.abs(yMovement) == 2 && Math.abs(xMovement) == 2){ //Make sure there's an intermediate enemy piece
			if(oppositeSides(pieces[xi][yi], pieces[(xi + xf) / 2][(yi + yf) / 2])){
				return true;
			}
		}
		return false;
	}				
		
	
	public boolean canSelect(int x, int y){
		if(pieces[x][y] != null){ //If selecting a piece
			if(!hasMoved){
				if((pieces[x][y].isFire() && FireTurn) || (!pieces[x][y].isFire() && !FireTurn)){
					return true;
				}
			}
			return false; //If you've moved a piece already, can't select one
		}
		if(pieces[x][y] == null){ //If selecting a space
			if(activeX == -1){
				return false; //Did not select a piece yet
			}
			if(hasMoved){
				if(Math.abs(activeX - x) == 2 && activePiece.hasCaptured()){ //doing a double jump
					return validMove(activeX, activeY, x, y);
				}
				else{
					return false;
				}
			}		
			else{ //hasn't moved yet
				return validMove(activeX, activeY, x, y);
			}
		}
		
		return false;
	}
	
	public void select(int x, int y){
		if(pieces[x][y] != null){ //Select a piece
			activeX = x;
			activeY = y;
			activePiece = pieces[x][y];
		}
		else{ //Select a square, presumably to move the active piece to it
			activePiece.move(x, y);
			activeX = x;
			activeY = y;
			hasMoved = true;
		}
	}
	
	public boolean canEndTurn(){
		return hasMoved;
	}
	
	public void endTurn(){
		if(FireTurn){
			FireTurn = false;
		}
		else{
			FireTurn = true;
		}
		hasMoved = false;
		activePiece.doneCapturing();
		activeX = -1;
		activeY = -1;
		selectX = -1;
		selectY = -1;
		activePiece = null;
	}
	
	public String winner(){
		int firepieces = 0;
		int waterpieces = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j] != null){
					if(pieces[i][j].isFire()){
						firepieces += 1;
					}
					if(!pieces[i][j].isFire()){
						waterpieces += 1;
					}
				}
			}
		}
		if(firepieces == 0 && waterpieces > 0){
			return "Water";
		}
		if(firepieces > 0 && waterpieces == 0){
			return "Fire";
		}
		if(firepieces == waterpieces && firepieces == 0){
			return "No one";
		}
		return null;
	}


}