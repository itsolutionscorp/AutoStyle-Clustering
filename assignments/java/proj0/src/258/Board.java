public class Board{
	private final int SIZE = 8;
	private static boolean[][] pieces;
	private Piece[][] checkers = new Piece[SIZE][SIZE];
	private Piece prevSelected = null;
	private boolean ifMoved = false;
	private int player = 0; //0 if fire; 1 if water

	public Board(boolean shouldBeEmpty){
		drawBoard();
		if (!shouldBeEmpty){
			drawPieces();
		}
	}

	private void drawBoard(){
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				if((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, .5);
			}
		}
	}

	private void picture(){
		for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
            	if (pieces[x][y]){
            		if(checkers[x][y].isFire()){
            			if(checkers[x][y].isShield()){
            				if(checkers[x][y].isKing())
            					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
							StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
            			}
						else if(checkers[x][y].isBomb()){
							if(checkers[x][y].isKing())		
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
							StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
						}
						else{
							if(checkers[x][y].isKing())
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
						}
					}
					else{
						if(checkers[x][y].isShield()){
							if(checkers[x][y].isKing())
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
							StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
						}
						else if(checkers[x][y].isBomb()){
							if(checkers[x][y].isKing())		
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
							StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
						}
						else{
							if(checkers[x][y].isKing())
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
						}
					}
				}	
			}
		}	
	}

	private void drawPieces(){
		for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
				if ((y == 0) && (x % 2 == 0)){
					checkers[x][y] = new Piece(true, this, x, y, "pawn");
					pieces[x][y] = true;			
				}
				if ((y == 1) && (x % 2 != 0)){
					checkers[x][y] = new Piece(true, this, x, y, "shield");
					pieces[x][y] = true;			
				}
				if ((y == 2) && (x % 2 == 0)){
					checkers[x][y] = new Piece(true, this, x, y, "bomb");
					pieces[x][y] = true;
				}
				if ((y == 5) && (x % 2 != 0)){
					checkers[x][y] = new Piece(false, this, x, y, "bomb");
					pieces[x][y] = true;
				}
				if ((y == 6) && (x % 2 == 0)){
					checkers[x][y] = new Piece(false, this, x, y, "shield");
					pieces[x][y] = true;
				}
				if ((y == 7) && (x % 2 != 0)){
					checkers[x][y] = new Piece(false, this, x, y, "pawn");
					pieces[x][y] = true;
				}
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if (x > SIZE || y > SIZE || x < 0 || y < 0)
			return null;
		if (pieces[x][y])
			return checkers[x][y];
		return null;
	}

	public boolean canSelect(int x, int y){
		//checks if player is fire and is a piece
		if (player == 0){
			if(pieces[x][y]){
				if(checkers[x][y].isFire()){
					return true;
				}
			}
			else {
				//checks if move forward a squre
				if (validMove(x, y, x + 1, y + 1) || validMove(x, y, x - 1, y + 1))
					return true;
				else if ((pieces[x + 1][y + 1] && !checkers[x + 1][y + 1].isFire()) || (pieces[x - 1][y + 1] && !checkers[x - 1][y + 1].isFire())){
					if (validMove(x, y, x + 2, y + 2) || validMove(x, y, x - 2, y + 2))
						return true;
				}
			}
		}
		//checks if player is water and is a piece
		if (player == 1){
			if(pieces[x][y]){
				if(!checkers[x][y].isFire()){
					return true;
				}
			}
			else {
				if (validMove(x, y, x + 1, y - 1) || validMove(x, y, x - 1, y - 1))
					return true;
				else if ((pieces[x + 1][y - 1] && checkers[x + 1][y - 1].isFire()) || (pieces[x - 1][y - 1] && checkers[x - 1][y - 1].isFire())){
					if (validMove(x, y, x + 2, y - 2) || validMove(x, y, x - 2, y - 2))
						return true;
				}
			}
		}
		return false; 
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (!pieces[xf][yf]){
			if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1)
				return true;
			if (pieces[Math.abs(xi + 1)][Math.abs(yi + 1)]){
				if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2)
					return true;
			}
		}
		return false;
	}

	public void select(int x, int y){
		if (pieces[x][y]){
			prevSelected = pieceAt(x, y);
		}
		else{
			//if player 0 & not king
			if ((player == 0) && (prevSelected != null)) {
				if ((pieces[x - 1][y - 1]) && (!checkers[x - 1][y - 1].isFire())){
					remove(x - 1, y - 1);
					pieces[x - 1][y - 1] = false;
				}
				else{
					if ((pieces[x + 1][y - 1]) && (!checkers[x + 1][y - 1].isFire())){
						remove(x + 1, y - 1);
						pieces[x + 1][y - 1] = false;
					}
				}
			}
			else if ((player == 1) && (prevSelected != null)) {
				if ((pieces[x - 1][y + 1]) && (checkers[x - 1][y + 1].isFire())){
					remove(x - 1, y + 1);
					pieces[x - 1][y + 1] = false;
				}
				else{
					if ((pieces[x + 1][y + 1]) && (!checkers[x + 1][y + 1].isFire())){
						remove(x + 1, y + 1);
						pieces[x + 1][y + 1] = false;
					}
				}
				if ((!pieces[x][y]) && (prevSelected != null)){
					//if not capturing
					prevSelected.move(x, y);
					place(prevSelected, x, y);
					checkers[x][y] = prevSelected;
					checkers[x][y].doneCapturing();
					prevSelected = null;
					ifMoved = true;
					endTurn();
				} 
			}
		}
	}

	public void place(Piece p, int x, int y){
		if ((x > SIZE || y > SIZE || x < 0 || y < 0) || (p == null))
			return;
		checkers[x][y] = p;
		pieces[x][y] = true;
	}

	public Piece remove(int x, int y){
		if (x > SIZE || y > SIZE || x < 0 || y < 0){
			System.out.println("Out of Bounds");
			return null;
		}
		if (!(pieces[x][y])){
			System.out.println("No Piece There");
			return null;
		}
		Piece p = checkers[x][y];
		checkers[x][y] = null;
		pieces[x][y] = false;
		return p;
	}

	public boolean canEndTurn(){
		if(ifMoved){
			ifMoved = false;
			return true;
		}
		return false;
	}

	public void endTurn(){
		if (StdDrawPlus.isSpacePressed()){
			if (canEndTurn()){
				if (player == 0)
					player = 1;
				else
					player = 0;
			}
		}
	}

	public String winner(){
		int fire = firePieces();
		int water = waterPieces();
		String winner = "";
		if(fire == 0 && water != 0)
			winner = "Water";
		if (water == 0 && fire != 0)
			winner = "Fire";
		if (fire == 0 && water == 0)
			winner = "No one";
		if (fire > 0 && water > 0)
			return null;
		return winner;
	}

	private int firePieces(){
		int fire = 0;
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				if (pieces[j][i] && checkers[j][i].isFire())
					fire++;
			}
		}
		return fire;
	}

	private int waterPieces(){
		int water = 0;
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				if (pieces[j][i] && !checkers[j][i].isFire())
					water++;
			}
		}
		return water;
	}
	

	//
	//
	//START OF MAIN METHOD//
	//
	//
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        pieces = new boolean[8][8];

        Board board = new Board(false);

        while(true) {
        	board.drawBoard();
        	board.picture();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y))
                	board.select((int) x, (int) y);
                StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }        
            StdDrawPlus.show(100);
            board.endTurn();
            board.winner();
		}

	}

}	