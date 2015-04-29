public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean selected = false;
	private int selectedX;
	private int selectedY;
	private boolean moved = false;
	private boolean fireTurn = true;
	private boolean canMoveAgain = false;

	public static void main (String args[]) {
		Board board = new Board(false);
		while(board.winner() == null) {
            board.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x,y)) {
                	board.select(x,y);
                }
            }
            if ((StdDrawPlus.isSpacePressed()) && board.canEndTurn()) {
            	board.endTurn();
            }
            StdDrawPlus.show(15);
        }
	}

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i+=2) {
				pieces[i][0] = new Piece(true,this,i,0,"pawn");
				pieces[i+1][1] = new Piece(true,this,i+1,1,"shield");
				pieces[i][2] = new Piece(true,this,i,2,"bomb");
				pieces[i+1][5] = new Piece(false,this,i+1,5,"bomb");
				pieces[i][6] = new Piece(false,this,i,6,"shield");
				pieces[i+1][7] = new Piece(false,this,i+1,7,"pawn");
			}
		}
	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (selected && i == selectedX && j == selectedY && pieces[selectedX][selectedY] != null)
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 != 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
	                if (pieces[i][j].isFire()) {
	                	if (pieces[i][j].isShield()) {
	                		if (pieces[i][j].isKing())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                	}
	                	else if (pieces[i][j].isBomb()) {
	                		if (pieces[i][j].isKing())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                	}
	                	else {
	                		if (pieces[i][j].isKing())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                	}
	                }
	                else {
	                	if (pieces[i][j].isShield()) {
	                		if (pieces[i][j].isKing())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                	}
	                	else if (pieces[i][j].isBomb()) {
	                		if (pieces[i][j].isKing())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                	}
	                	else {
	                		if (pieces[i][j].isKing())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                	}
	                }
            	}
            }
        }
	}
	public Piece pieceAt(int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y <8 && pieces[x][y] != null) {
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7)
			return false;
		if (pieceAt(selectedX,selectedY) == null)
			selected = false;
		if (pieceAt(x,y) != null && !moved) {
			if (pieceAt(x,y).isFire() && fireTurn)
				return true;
			else if (!pieceAt(x,y).isFire() && !fireTurn) {
				return true;
			}
		}
		else if (selected && !moved) {
			Piece selectedPiece = pieceAt(selectedX,selectedY);
			if (selectedPiece.isFire()) {
				if (selectedPiece.isKing()) {
					if ((x == selectedX + 1 || x == selectedX - 1) && (y == selectedY + 1 || y == selectedY - 1))
						return true;
					else if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && !pieceAt(selectedX + 1, selectedY + 1).isFire())
						return true;
					else if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && !pieceAt(selectedX + 1, selectedY - 1).isFire())
						return true;
					else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && !pieceAt(selectedX - 1, selectedY + 1).isFire())
						return true;
					else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && !pieceAt(selectedX - 1, selectedY - 1).isFire())
						return true;
				}
				else {
					if ((x == selectedX + 1 || x == selectedX - 1) && y == selectedY + 1)
						return true;
					else if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && !pieceAt(selectedX + 1, selectedY + 1).isFire())
						return true;
					else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && !pieceAt(selectedX - 1, selectedY + 1).isFire())
						return true;
				}
			}
			else if (!selectedPiece.isFire()) {
				if (selectedPiece.isKing()) {
					if ((x == selectedX + 1 || x == selectedX - 1) && (y == selectedY + 1 || y == selectedY - 1))
						return true;
					else if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && pieceAt(selectedX + 1, selectedY + 1).isFire())
						return true;
					else if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && pieceAt(selectedX + 1, selectedY - 1).isFire())
						return true;
					else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && pieceAt(selectedX - 1, selectedY + 1).isFire())
						return true;
					else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && pieceAt(selectedX - 1, selectedY - 1).isFire())
						return true;
				}
				else {
					if ((x == selectedX + 1 || x == selectedX - 1) && y == selectedY - 1)
						return true;
					else if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && pieceAt(selectedX + 1, selectedY - 1).isFire())
						return true;
					else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && pieceAt(selectedX - 1, selectedY - 1).isFire())
						return true;
				}
			}
		}
		else if (selected && moved && pieceAt(x,y) == null && pieceAt(selectedX,selectedY).hasCaptured()) {
			canMoveAgain = (canSelectMovingAgain(selectedX + 2,selectedY + 2)) || (canSelectMovingAgain(selectedX - 2,selectedY + 2)) || (canSelectMovingAgain(selectedX + 2,selectedY - 2)) || (canSelectMovingAgain(selectedX - 2,selectedY - 2)); //every possible place the piece can capture again
			if (canMoveAgain) {
				Piece selectedPiece = pieceAt(selectedX,selectedY);
				if (selectedPiece.isFire()) {
					if (selectedPiece.isKing()) {
						if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && !pieceAt(selectedX + 1, selectedY + 1).isFire())
							return true;
						else if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && !pieceAt(selectedX + 1, selectedY - 1).isFire())
							return true;
						else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && !pieceAt(selectedX - 1, selectedY + 1).isFire())
							return true;
						else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && !pieceAt(selectedX - 1, selectedY - 1).isFire())
							return true;
					}
					else {
						if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && !pieceAt(selectedX + 1, selectedY + 1).isFire())
							return true;
						else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && !pieceAt(selectedX - 1, selectedY + 1).isFire())
							return true;
					}
				}
				else if (!selectedPiece.isFire()) {
					if (selectedPiece.isKing()) {
						if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && pieceAt(selectedX + 1, selectedY + 1).isFire())
							return true;
						else if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && pieceAt(selectedX + 1, selectedY - 1).isFire())
							return true;
						else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && pieceAt(selectedX - 1, selectedY + 1).isFire())
							return true;
						else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && pieceAt(selectedX - 1, selectedY - 1).isFire())
							return true;
					}
					else {
						if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && pieceAt(selectedX + 1, selectedY - 1).isFire())
							return true;
						else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && pieceAt(selectedX - 1, selectedY - 1).isFire())
							return true;
					}
				}
			}
		}
		return false;
	}

	private boolean canSelectMovingAgain(int x, int y) {
		Piece selectedPiece = pieceAt(selectedX,selectedY);
		if (selectedPiece.isFire()) {
			if (selectedPiece.isKing()) {
				if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && !pieceAt(selectedX + 1, selectedY + 1).isFire())
					return true;
				else if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && !pieceAt(selectedX + 1, selectedY - 1).isFire())
					return true;
				else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && !pieceAt(selectedX - 1, selectedY + 1).isFire())
					return true;
				else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && !pieceAt(selectedX - 1, selectedY - 1).isFire())
					return true;
			}
			else {
				if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && !pieceAt(selectedX + 1, selectedY + 1).isFire())
					return true;
				else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && !pieceAt(selectedX - 1, selectedY + 1).isFire())
					return true;
			}
		}
		else if (!selectedPiece.isFire()) {
			if (selectedPiece.isKing()) {
				if (x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && pieceAt(selectedX + 1, selectedY + 1).isFire())
					return true;
				else if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && pieceAt(selectedX + 1, selectedY - 1).isFire())
					return true;
				else if (x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && pieceAt(selectedX - 1, selectedY + 1).isFire())
					return true;
				else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && pieceAt(selectedX - 1, selectedY - 1).isFire())
					return true;
			}
			else {
				if (x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && pieceAt(selectedX + 1, selectedY - 1).isFire())
					return true;
				else if (x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && pieceAt(selectedX - 1, selectedY - 1).isFire())
					return true;
			}
		}
		return false;
	}

	public void select(int x,int y) {
		if (selected && pieceAt(x,y) == null) {
			pieceAt(selectedX,selectedY).move(x,y);
			moved = true;
		}
		else if (pieceAt(x,y) != null) {
			selected = true;
		}
		selectedX = x;
		selectedY = y;
	}

	public void place(Piece p, int x, int y) {
		if (!(p == null) && x >= 0 && x < 8 && y >= 0 && y < 8)
			pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (!(x >= 0 && x < 8 && y >= 0 && y < 8)) {
			System.out.println("Square not on board!");
			return null;
		}
		else if (pieceAt(x,y) == null) {
			System.out.println("No piece there!");
			return null;
		}
		else {
			Piece currentPiece = pieceAt(x,y);
			pieces[x][y] = null;
			return currentPiece;
		}
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		moved = false;
		selected = false;
		canMoveAgain = false;
		if (fireTurn)
			fireTurn = false;
		else
			fireTurn = true;
	}

	public String winner() {
		boolean firePieces = false;
		boolean waterPieces = false;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire())
            			firePieces = true;
            		else
            			waterPieces = true;
            	}
            }
        }
        if (firePieces && waterPieces)
        	return null;
        else if (firePieces)
        	return "Fire";
        else if (waterPieces)
        	return "Water";
        else
        	return "No one";
	}
}