/*
********************
** Mondee Lu - atb**
********************
*/
public class Board {
	//instance variables
	private Piece[][] pieces;
	private Piece pickedUpPiece;
	private int pickedUpPieceX;
	private int pickedUpPieceY;
	private boolean hasPlayerMoved = false;
	private String currentPlayer = "fire";
	private int xCoord = 10;
	private int yCoord = 10;

	//starts the GUI for a board
	public static void main(String[] args) {
		//make the board for a new game
		Board newGame = new Board(false);
		//draw the board
		while(true){
			newGame.drawBoard(8);
          	if (StdDrawPlus.mousePressed()) {
          		double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (newGame.canSelect((int) x , (int) y)) {
					newGame.select((int) x, (int) y);
					newGame.xCoord = (int) x;
					newGame.yCoord = (int) y;
				}
          	}
			if (StdDrawPlus.isSpacePressed()) {
				if (newGame.canEndTurn()) {
					newGame.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
	}
	
	//how to make the board
	private void drawBoard(int n) {
		StdDrawPlus.setXscale(0, n);
		StdDrawPlus.setYscale(0, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//mark the square as white if the coordinates match that of the selected
				if (i == xCoord && j == yCoord) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
				}
				else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				//draw the pieces based on attributes
				Piece current = pieces[i][j];
				if (current != null) { //if there is a piece
					if (current.isFire()) {
						if (current.isKing()) {
							if (current.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else if (current.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}
						else {
							if (current.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
							else if (current.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					}
					//draw water pieces
					else {
						if (current.isKing()) {
							if (current.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							else if (current.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
						else {
							if (current.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
							else if (current.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}
	
	//Board constructor
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == true) {
			pieces = new Piece[8][8];
		}
		else {
			pieces = new Piece[8][8];
			for (int i = 0; i < 8; i = i + 2){
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
			}
			for (int i = 1; i < 8; i = i + 2){
				pieces[i][1] = new Piece(true, this, i, 1, "shield");
			}	
			for (int i = 0; i < 8; i = i + 2){
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");
			}
			for (int i = 1; i < 8; i = i + 2){
				pieces[i][5] = new Piece(false, this, i, 5, "bomb");
			}	
			for (int i = 0; i < 8; i = i + 2){
				pieces[i][6] = new Piece(false, this, i, 6, "shield");
			}
			for (int i = 1; i < 8; i = i + 2){
				pieces[i][7] = new Piece(false, this, i, 7, "pawn");
			} 
		}
	}
	
	//Gets the piece at position (x,y)
	public Piece pieceAt(int x, int y) {
		//check bounds
		if (x > 8) {
			return null;
		}
		else if (y > 8) {
			return null;
		}
		//check lower bounds
		else if (x < 0) {
			return null;
		}
		else if (y < 0) {
			return null;
		}
		//check if there is piece
		else if (pieces[x][y] == null) {
			return null;
		}
		//return piece
		else {
			return pieces[x][y];
		}
	}
	
	//returns True is a position can be selected
	public boolean canSelect(int x, int y) {
		//quick check if off board
		if (x > 7 || y > 7) {
			return false; 
		}
		Piece choice = pieces[x][y];
		//if there is a piece at the coordinates
		if (choice != null) {
			//if the piece is that of the current player - fire
			if (choice.isFire() && currentPlayer == "fire") {
				//if you have either not moved, or have no picked up piece
				if (pickedUpPiece == null || hasPlayerMoved == false) {
					return true;
				}
				else {
					return false;
				}
			}
			//water piece check
			else {
				if (currentPlayer == "water" && !choice.isFire()) {
					if (pickedUpPiece == null || hasPlayerMoved == false) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		//if there is no piece at the coordinates
		else {
			if (pickedUpPiece != null && hasPlayerMoved == false) {
				return validMove(x, y, pickedUpPieceX, pickedUpPieceY, pickedUpPiece);
			}
			//if there is another valid capture after the player has already moved 
			else if (pickedUpPiece != null && pickedUpPiece.hasCaptured()) {
				if (Math.abs(x-pickedUpPieceX) == 2 && Math.abs(y-pickedUpPieceY) == 2) {
					return validMove(x, y, pickedUpPieceX, pickedUpPieceY, pickedUpPiece);
				}
				else {
					return false;
				}	
			}
			else {
				return false;
			}
		}		
	}
	
	//checks if the selection is a valid move
	private boolean validMove(int x, int y, int x2, int y2, Piece p) {
		//test conditions for kings - can move forward and back
		if (p.isKing()) {
			//check if target is out of bounds
			if (x > 7 || y > 7) {
				return false;
			}
			//check if the target has a piece on it already
			else if (pieces[x][y] != null) {
				return false;
			}
			//see if the target is one step diagonal from current spot
			else if (Math.abs(x-x2) == 1 && Math.abs(y-y2) == 1) {
				return true;
			}
			//check if the target is a valid capture move
			else {
				Piece inBetweenPiece = getInbetweenPiece(x, y, x2, y2, p);
				//if there is a piece to jump over
				if (inBetweenPiece != null) {
					if (currentPlayer == "fire") {
						if (inBetweenPiece.isFire()) {
							return false;
						}
						else {
							return true;
						}
					}
					else {
						if (inBetweenPiece.isFire()) {
							return true;
						}
						else {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}
		}
		//test conditions for non-crowned pieces - can only move in one direction
		else {
			if (x > 7 || y > 7) {
				return false;
			}
			else if (p.isFire()) {
				if (y < y2) {
					return false;
				}
				else if (Math.abs(x-x2) == 1 && Math.abs(y-y2) == 1) {
					return true;
				}
				else {
					Piece inBetweenPiece = getInbetweenPiece(x, y, x2, y2, p);
					if (inBetweenPiece != null) {
						if (currentPlayer == "fire") {
							if (inBetweenPiece.isFire()) {
								return false;
							}
							else {
								return true;
							}
						}
						else {
							if (inBetweenPiece.isFire()) {
								return true;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
			}
			//for water pieces 
			else {
				if (y > y2) {
					return false;
				}
				else if (Math.abs(x-x2) == 1 && Math.abs(y-y2) == 1) {
					return true;
				}
				else {
					Piece inBetweenPiece = getInbetweenPiece(x, y, x2, y2, p);
					if (inBetweenPiece != null) {
						if (currentPlayer == "fire") {
							if (inBetweenPiece.isFire()) {
								return false;
							}
							else {
								return true;
							}
						}
						else {
							if (inBetweenPiece.isFire()) {
								return true;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
			}
		}
	}
	
	//method to return what is at the coordinates that are being jumped over
	private Piece getInbetweenPiece(int x, int y, int x2, int y2, Piece p) {
		return pieces[(x+x2)/2][(y+y2)/2];
	}
	
	//places the pieces at the given coordinates	
	public void place(Piece p, int x, int y) {
		if (x < 8 && y < 8) {
			if (x >= 0 && y >= 0) {
				pieces[x][y] = p;
			}	
		}		
	}
	
	//executes a remove
	public Piece remove(int x, int y) {
		if (x > 8 && y > 8) {
			System.out.println("input is out of bounds");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("there is no piece here");
			return null;
		}
		else {
			Piece returnPiece = pieces[x][y];
			pieces[x][y] = null;
			return returnPiece;
		}
	}
	
	//select the coordinates
	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			pickedUpPiece = pieces[x][y];
			pickedUpPieceX = x;
			pickedUpPieceY = y;
			pickedUpPiece.move(x, y);
		}
		else if(pickedUpPiece != null) {
			pickedUpPiece.move(x, y);
			pickedUpPieceX = x;
			pickedUpPieceY = y;
			hasPlayerMoved = true;
		}
	}
	
	//returns true if the current turn can end
	public boolean canEndTurn() {
		if (hasPlayerMoved == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//execute requisite things to end turn
	public void endTurn() {
		if (currentPlayer == "fire") {
			currentPlayer = "water";
		}
		else {
			currentPlayer = "fire";
		}
		pickedUpPiece.doneCapturing();
		pickedUpPiece = null;
		hasPlayerMoved = false;
		xCoord = 10;
		yCoord = 10;
		pickedUpPieceX = 10;
		pickedUpPieceY = 10;
	}
	
	public String winner() {
		int numWaterPieces = 0;
		int numFirePieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece current = pieces[i][j];
				if (current != null) {
					if (current.isFire()) {
						numFirePieces = numFirePieces + 1;
					}
					else {
						numWaterPieces = numWaterPieces + 1;
					}
				}
			}
		}
		if (numWaterPieces == 0 && numFirePieces > 0) {
			return "Fire";
		}
		else if(numWaterPieces > 0 && numFirePieces == 0) {
			return "Water";
		}
		else if(numWaterPieces == 0 && numFirePieces == 0) {
			return "No one";
		}
		else {
			return null;
		}
	}
}