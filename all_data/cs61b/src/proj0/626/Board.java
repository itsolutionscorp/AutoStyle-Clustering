public class Board{
	private  boolean shouldBeEmpty;
	private static final int size = 8;
	private Piece[][] pieces;
	private boolean fireTurn;
	private boolean completeMove;
	private int firePieces;
	private int waterPieces;

	private Piece selected;
	private int selectedX = -1;
	private int selectedY = -1;
	
	
	public Board(boolean shouldBeEmpty){
		/* 	Board Constructor:
		**	If shouldBeEmpty is true, it initializes an empty
		** 	board. Otherwise, initializes board with default 
		** 	configuration.
		*/
		this.shouldBeEmpty = shouldBeEmpty;
		fireTurn = true;
		completeMove = false;
		selected = null;
		firePieces = 0;
		waterPieces = 0;
		pieces = new Piece[size][size];
		if (!this.shouldBeEmpty){
			firePieces = 12;
			waterPieces = 12;
			for (int i=0; i<size; i++){
				for (int j=0; j<size; j++){
					if (i%2==0 && j==0){
						// Fire Pawns
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					}
					if (i%2==1 && j==1){
						// Fire Shileds
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					}
					if (i%2==0 && j==2){
						// Fire Bombs
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					}
					if (i%2==1 && j==7){
						// Fire Pawns
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
					if (i%2==0 && j==6){
						// Fire Shileds
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					}
					if (i%2==1 && j==5){
						// Fire Bombs
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y){
		/* Gets piece at position (x,y) returning null if 
		** there is no piece at the position or the position
		** is out of bounds.
		*/
		if (x > -1 && x < 8 && y > -1 && y < 8){
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y){
		/* Checks if there is a piece at (x,y) and if it can
		** be selected. Selection of a piece is possible if it is that
		** players turn and either the player has not selected
		** a piece or the piece has not been moved. Selection of 
		** square is possible if player is selecting a valid place
		** to move piece or selected a valid capture position.
		*/

		Piece p = pieceAt(x,y);

		//Checks if piece you want to select is of the same type. 
		if (canSelectPiece(p) && !completeMove){
			return true;
		}

		// Checks if you can move to (x,y) or capture piece at (x,y).
		if (p == null && selected != null){
			return validMove(selectedX, selectedY, x, y) || validCapture(selectedX, selectedY, x, y);
		}

		return false;
	}

	private boolean canSelectPiece(Piece p){
		if (p != null){
			if (p.isFire() && fireTurn){
				return true;	
			}
			if (!p.isFire() && !fireTurn){
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		/* Checks if piece can make a valid move from
		** initial position to final position.*/

		if (pieceAt(xf, yf) == null && !completeMove){
			if (selected.isKing()){
				// Crowned pieces can move in diagonally in any direction
				if (Math.abs(xf-xi)==1 && Math.abs(yf-yi)==1){
					return true;
				}
			}

			if (selected.isFire()){
				// Uncrowned fire pieces can only move up
				if ((Math.abs(xf-xi)==1) && (yf-yi==1)){
					return true;
				}
			}
			else{
				// Uncrowned water pieces can only move down
				if ((Math.abs(xf-xi)==1) && (yi-yf==1)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf){
		int removeX = (xi + xf)/2;
		int removeY = (yi + yf)/2;
		Piece toRemove = pieceAt(removeX, removeY);
		if (toRemove != null && toRemove.isFire() != selected.isFire()){
			if (selected.isKing()){
				// Crowned pieces can move in diagonally in any direction
				if (Math.abs(xf-xi)==2 && Math.abs(yf-yi)==2){
					return true;
				}
			}

			if (selected.isFire()){
				// Uncrowned fire pieces can only move up
				if ((Math.abs(xf-xi)==2) && (yf-yi==2)){
					return true;
				}
			}
			else{
				// Uncrowned water pieces can only move down
				if ((Math.abs(xf-xi)==2) && (yi-yf==2)){
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y){
		/* Selects square at position (x,y) if possible.*/
		if (x > -1 && x < 8 && y > -1 && y < 8){
			Piece p = pieceAt(x,y);

			// Selecting a piece when one hasn't been selected yet
			if (selected == null && p != null){
				selected = p;
				selectedX = x;
				selectedY = y;
			}

			// If you have selected a piece and want to change your selection
			if (selected != null && p != null){
				selected = p;
				selectedX = x;
				selectedY = y;
			}

			// Moving piece to selected position
			if (selected != null && p == null){
				selectedX = x;
				selectedY = y;
				selected.move(x,y);
				completeMove = true;
			}
		}
	}

	public void place(Piece p, int x, int y){
		/* Places piece at (x,y). If (x,y) invalid or p is null
		** the function does nothing. 
		*/
		if (p.isFire()){
			firePieces += 1;
		}
		else{
			waterPieces += 1;
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y){
		/* Removes piece and returns it. If input is out of 
		** bounds, return null and prints message. If piece 
		** is not there returns null and prints appropriate mssage
		*/
		Piece removed = pieceAt(x,y);
		pieces[x][y] = null;
		if (removed != null){
			if (removed.isFire()){
				firePieces -= 1;
			}
			else{
				waterPieces -= 1;
			}	
		}
		if (selectedX == x && selectedY == y){
			selectedX = -1;
			selectedY = -1;
		}
		return removed;
	}

	public boolean canEndTurn(){
		/* Returns whether or not a player can end turn. A piece
		** must have been moved or captured to be able to end turn.
		*/
		if (selected != null){
			return completeMove || selected.hasCaptured();
		}
		return false;
	}

	public void endTurn(){
		/* Called at end of each turn to switch handling of players
		** and any end of turn protocol.
		*/
		if (fireTurn){
			fireTurn = false;
		}
		else{
			fireTurn = true;
		}
		completeMove = false;	
		if (selected != null){
			selected.doneCapturing();
			selected = null;
		}
		selectedX = -1;
		selectedY = -1;
	}

	public String winner(){
		/* Returns winner of game or null if game is not over
		** based on number of pieces belonging to each team.
		*/
		if (firePieces == 0 && waterPieces == 0){
			return "No one";
		}
		if (firePieces == 0){
			return "Water";
		}
		if (waterPieces == 0){
			return "Fire";
		}
		return null;
	}

	private void drawBoard(int n){
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				if ((i+j) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				if (selectedX == i && selectedY == j){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				StdDrawPlus.filledSquare(i+.5, j+.5, .5);
			}
		}
		if (!shouldBeEmpty){
			drawPieces(pieces);
		}
	}
	
	private void drawPieces(Piece[][] pieces){
		for (int i=0; i<size; i++){
			for (int j=0; j<size; j++){
				String imgPath = "img/";
				Piece p = pieceAt(i,j);
				if (p != null){
					if (p.isBomb()){
						imgPath = imgPath + "bomb-";
					}
					else if (p.isShield()){
						imgPath = imgPath + "shield-";
					}
					else{
						imgPath = imgPath + "pawn-";
					}
					if (p.isFire()){
						imgPath = imgPath + "fire";
					}
					else{
						imgPath = imgPath + "water";	
					}
					if (p.isKing()){
						imgPath = imgPath + "-crowned.png";
					}
					else{
						imgPath = imgPath + ".png";
					}
					StdDrawPlus.picture(i+0.5, j+0.5, imgPath, 1, 1);
				}	
			}	
		}
	}
	
	public static void main(String[] args){
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, b.size);
		StdDrawPlus.setYscale(0, b.size);
		
		while(true){
			b.drawBoard(b.size);
			if (StdDrawPlus.mousePressed()){
				// Get mouse position.
				double xDouble = StdDrawPlus.mouseX();
				double yDouble = StdDrawPlus.mouseY();
				int x = (int) xDouble;
				int y = (int) yDouble;

				if (b.canSelect(x,y)){
					b.select(x,y);
				}	
			}
				
			// Check if selected is crowned
			if (b.selected != null){
				b.selected.isKing();
			}

			// Change player turn
			if (StdDrawPlus.isSpacePressed()){
				if (b.canEndTurn()){
					b.endTurn();
				}
			}

			// Reset game board
			if (StdDrawPlus.isNPressed()){
				b = new Board(false);
			}

			StdDrawPlus.show(100);
		}
	}
}