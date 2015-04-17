public class Board{
	private boolean shouldBeEmpty; 
	private Piece[][] locationArray;
	private boolean playerturn;
	private boolean moved = false;
	private int[] curr = new int[2];
	private Piece p; 
	private Piece[] track = new Piece[1];
	

	public static void main(String [] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		// b.createEmptyBoard(8);
		// b.createFullBoard(8);
		
		// StdDrawPlus.show(20);
		String win = b.winner();
		
		while (true) {
			b.createEmptyBoard(8);
			b.createFullBoard(8);



			if (StdDrawPlus.mousePressed()) {
            	int x = (int) StdDrawPlus.mouseX();
            	int y = (int) StdDrawPlus.mouseY();
           		if (b.canSelect(x, y)) {
           			b.select(x, y);
            	}
            }
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
			}
		}
			// b.createEmptyBoard(8);
			// b.createFullBoard(8);
		
			StdDrawPlus.show(20);
			win = b.winner();

	}
		
	}


	
	

	/** I am using the methods provided in StdDrawDemo.java
	 */
	private void createEmptyBoard(int N) {
		for (int i = 0; i < N; i+=1) {
            for (int j = 0; j < N; j+=1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
               


			}
		}
	}
	/** Again using aspects from StdDrawDemo.java
		Also checking what type is every piece and updating the pathway 
		accordingly to assing the appropriate image.
	 */
	private void createFullBoard(int N) {
		// String imagePath = "img/";
		for (int i = 0; i < N; i+=1){
			for (int j = 0; j < N; j+=1){
				if (locationArray[i][j] != null) {
				String imagePath = "img/";

				if (locationArray[i][j].isShield()) {
					imagePath += "shield-";
				}
				else if (locationArray[i][j].isBomb()) {
					imagePath += "bomb-";
				}
				else if (!locationArray[i][j].isShield() && !locationArray[i][j].isBomb()) {
					imagePath += "pawn-";
				}
				if (locationArray[i][j].isFire() && !locationArray[i][j].isKing()) {
					imagePath += "fire.png";
				}
				else if (!locationArray[i][j].isFire() && !locationArray[i][j].isKing()) {
					imagePath += "water.png";
				}
				if (locationArray[i][j].isFire() && locationArray[i][j].isKing()) {
					imagePath += "fire-crowned.png";
				}
				else if (!locationArray[i][j].isFire() && locationArray[i][j].isKing()) {
					imagePath += "water-crowned.png";
				}


				
				StdDrawPlus.picture(i + .5, j + .5, imagePath, 1, 1);
			}
		

		}
			
		}
	}

	
	/** Creating the initial board placing all pieces in the correct position
		and having the correct type.
	 */
	public Board(boolean shouldBeEmpty) {
		playerturn = true;
		locationArray = new Piece[8][8];
		if (shouldBeEmpty != true) {
			locationArray[0][0] = new Piece(true, this, 0, 0, "pawn");
			locationArray[2][0] = new Piece(true, this, 2, 0, "pawn");
			locationArray[4][0] = new Piece(true, this, 4, 0, "pawn");
			locationArray[6][0] = new Piece(true, this, 6, 0, "pawn");
			locationArray[1][1] = new Piece(true, this, 1, 1, "shield");
			locationArray[3][1] = new Piece(true, this, 3, 1, "shield");
			locationArray[5][1] = new Piece(true, this, 5, 1, "shield");
			locationArray[7][1] = new Piece(true, this, 7, 1, "shield");
			locationArray[0][2] = new Piece(true, this, 0, 2, "bomb");
			locationArray[2][2] = new Piece(true, this, 2, 2, "bomb");
			locationArray[4][2] = new Piece(true, this, 4, 2, "bomb");
			locationArray[6][2] = new Piece(true, this, 6, 2, "bomb");
			locationArray[1][5] = new Piece(false, this, 1, 5, "bomb");
			locationArray[3][5] = new Piece(false, this, 3, 5, "bomb");
			locationArray[5][5] = new Piece(false, this, 5, 5, "bomb");
			locationArray[7][5] = new Piece(false, this, 7, 5, "bomb");
			locationArray[0][6] = new Piece(false, this, 0, 6, "shield");
			locationArray[2][6] = new Piece(false, this, 2, 6, "shield");
			locationArray[4][6] = new Piece(false, this, 4, 6, "shield");
			locationArray[6][6] = new Piece(false, this, 6, 6, "shield");
			locationArray[1][7] = new Piece(false, this, 1, 7, "pawn");
			locationArray[3][7] = new Piece(false, this, 3, 7, "pawn");
			locationArray[5][7] = new Piece(false, this, 5, 7, "pawn");
			locationArray[7][7] = new Piece(false, this, 7, 7, "pawn");


		}

	}
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7){
			return null;
		}
		else {
			return locationArray[x][y];
		}
	}


	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Out of Bounds!");
			return null;
		}
		if (locationArray[x][y] == null) {
			System.out.println("There is no piece on the specified coordinates!");
			return null;
		}
		else {
			Piece p = locationArray[x][y];
			locationArray[x][y] = null;
			return p;
		}
	}

	public void place(Piece p, int x, int y) {
		if (!(x < 0 || x > 7 || y < 0 || y > 7)) {
			if (p != null) {
				if (locationArray[x][y] == null) {
					locationArray[x][y] = p;
					}	
				if (locationArray[x][y] != null) {
					remove(x, y);
					locationArray[x][y] = p;
					}
				}
			}	


	}

	/** Creating the optional validMove in order to check if any potential move
		is allowed by the game rules.
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((xi < 0 || xi > 7 || yi < 0 || yi > 7) && (xf < 0 || xf > 7 || yf < 0 || yf > 7)) {
			return false;
		}

		int distanceX = Math.abs(xi - xf);
		int distanceY = Math.abs(yi - yf);
		// int avDistanceX = ((xi + xf) / 2);
		// int avDistanceY = ((yi + yf) / 2);
		while (distanceY == 1 && distanceX == 1) {
			Piece p = locationArray[xi][yi];
			if (p.isFire() && !p.isKing()) {
				if (yf < yi) return false;
				if (yf > yi) return true;
			}
			if (!p.isFire() && !p.isKing()) {
				if (yf > yi) return false;
				if (yf < yi) return true;
			}

			if (locationArray[xf][yf] == null){
				return true;
			}
		}

		while (distanceY == 2 && distanceX == 1) {
			Piece p = locationArray[xi][yi];
			Piece opponent = locationArray[xi + 1][yi + 1];
			if (p.isFire() && !p.isKing()) {
				if (yf < yi) return false;
				if (yf > yi) return true;
			}
			if (!p.isFire() && !p.isKing()) {
				if (yf > yi) return false;
				if (yf < yi) return true;
			}

			if (opponent != null && opponent.side() != p.side()) {
				return true;
			}
			if (locationArray[xf][yf] == null) {
				return true;
			}
		}
		return false;
	}

	/** canSelect determines if a piece in the specified position can be selected.
	 */
	public boolean canSelect(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return false;
		}
		if (locationArray[x][y] != null) {
			Piece peace = locationArray[x][y];
			if (moved == true) {
				return false;
			}
			if (peace.isFire() == playerturn) { 
				if ((track[0] != null)) { 
					return true;
				}
			return true;
			}
		}

		else if (locationArray[x][y] == null){
			Piece peace = locationArray[curr[0]][curr[1]];
			if (peace != null) {
				if (peace.isFire() == playerturn) {
					if ((moved == true) && (validMove(curr[0], curr[1], x, y)) && (Math.abs(x - curr[0]) == 2)) {
						return true;
					}
					if ((!moved) && (track[0] != null) && (validMove(curr[0], curr[1], x, y)))  {
						return true;
					}
					
				}
			}	
		}
			return false;
	}

    public void select(int x, int y){
    /**	// Piece peace = pieceAt(curr[0], curr[1]);
    	if (curr[0] != -1 && curr[1] != -1 && locationArray[x][y] == null && peace != null){
    		// Piece peace = pieceAt(curr[0], curr[1]);
    		peace.move(x, y);
    		moved = true;
    	}
    	else {
    		peace = pieceAt(x, y);
    		// curr[0] = x;
    		// curr[1] = y;
    	}
    	curr[0] = x;
    	curr[1] = y;
    	// StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	// StdDrawPlus.filledSquare(curr[0] + 0.5, curr[1] + 0.5, 0.5);
  	 }
	 */

	 if (locationArray[x][y] != null){
	 	curr[0] = x;
	 	curr[1] = y;
	 	track[0] = locationArray[x][y];
	 }
	 else{
	 	track[0].move(x, y);
	 	curr[0] = x;
	 	curr[1] = y;
	 	if (validMove(curr[0], curr[1], x, y) && (Math.abs(curr[0]-x) == 2)) {
			track[0].move(x, y);
		}
	 	moved = true;
	 }
	}





	public boolean canEndTurn(){
		if (moved == false) {
			return false;
		}
		else {
			return true;
		}
	}



	
	public void endTurn() {
		Piece peace = pieceAt(curr[0], curr[1]);
		if (peace != null) {
			peace.doneCapturing();
		}
		peace = null;
		playerturn = !playerturn;
		moved = false;
		curr[0] = -1;
		curr[0] = -1;

}

	public String winner() {
		if (counterFire() == 0 && counterWater() == 0) {
			return "No one";
		}
		if (counterFire() == 0){
			return "Water";
		}
		if (counterWater() == 0) {
			return "Fire";
		}
		return null;
	}

	

	private int counterFire() {
		int counter = 0;
		for (int i = 0; i < 8; i+=1) {
			for (int j = 0; j < 8; j+=1) {
				Piece countF = locationArray[i][j];
				if (countF != null) {
					if(countF.isFire() == true) {
						counter += 1;
					}
				}
				
			}
		}
		return counter;
			
		
	}

	private int counterWater() {
		int counter = 0;
		for (int i = 0; i < 8; i+=1) {
			for (int j = 0; j < 8; j+=1) {
				Piece countW = locationArray[i][j];
				if (countW != null) {
					if(countW.isFire() == false) {
						counter += 1;
					}
				}
				
			}
		}
		return counter;
			
		
	}	




	








}




















