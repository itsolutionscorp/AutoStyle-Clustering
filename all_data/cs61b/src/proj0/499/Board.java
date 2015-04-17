public class Board {

	private Piece[][] pieces;
	private static final String[] TYPES = new String[]{"pawn", "shield", "bomb"};
	private static final String[][] IMG = new String[][]{{"img/pawn-fire.png", "img/shield-fire.png", "img/bomb-fire.png"},
														 {"img/pawn-water.png", "img/shield-water.png", "img/bomb-water.png"},
														 {"img/pawn-fire-crowned.png", "img/shield-fire-crowned.png", "img/bomb-fire-crowned.png"},
														 {"img/pawn-water-crowned.png", "img/shield-water-crowned.png", "img/bomb-water-crowned.png"}
														};
	private Piece selected;
	private int selectedX, selectedY;
	private boolean selectedMoved;
	private boolean turn;
	private boolean canEndTurn;

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		selected = null;
		turn = true;
		canEndTurn = false;
		selectedX = 0;
		selectedY = 0;
		selectedMoved = false;

		if (!shouldBeEmpty) {
			for (int j = 0; j < 3; j++) {
				for (int i = 0; i < 8; i++) {
					if ((i + j) % 2 == 0) 
						pieces[i][j] = new Piece(true, this, i, j, TYPES[j]);
				}	
			}
			for (int j = 7; j > 4; j--) {
				for (int i = 0; i < 8; i++) {
					if ((i + j) % 2 == 0)
						pieces[i][j] = new Piece(false, this, i, j, TYPES[7 - j]);
				}	
			}

		}
		// pieces[1][5] = new Piece(true,this, 1, 5, "shield");
		// pieces[2][6] = new Piece(false,this, 2, 6, "shield");
		// pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		// pieces[0][0] = new Piece(false, this, 0, 0, "shield");
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7 || x < 0) || (y > 7 || y < 0))
			return null;
		return pieces[x][y];
	}
	
	public void place(Piece p , int x, int y) {
		if (x <= 7 && x >= 0 && y <= 7 && y >= 0) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if ((x > 7 || x < 0) || (y > 7 || y < 0)) {
			System.out.println("remove() Input Out of Bounds");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("No pieces at " + x + ", " + y);
			return null;
		} else {
			Piece returnPiece = pieces[x][y];
			pieces[x][y] = null;
			return returnPiece;
		}
	}

	public boolean canSelect(int x, int y) {
		if (selectedMoved) {
			return false;
		}

		if (pieces[x][y] != null && turn == pieces[x][y].isFire() 
						&& (selected == null || !selected.hasCaptured())) {
			return true;
		} else if (pieces[x][y] == null && selected != null && turn == selected.isFire() && validMove(x, y)) {
			return true;
		}
		
		return false;
	}

	private boolean validMove(int xf, int yf) {
		if (selected.isKing()) {
			if (!selected.hasCaptured() && (yf == selectedY + 1 || yf == selectedY - 1) && (xf == selectedX + 1 || xf == selectedX - 1)) {
				selectedMoved = true;
				return true;
			} else if ((yf == selectedY + 2 || yf == selectedY - 2) && (xf == selectedX + 2 || xf == selectedX - 2)) {
				Piece capturedPiece = pieces[selectedX + ((xf - selectedX) / 2)][selectedY + ((yf - selectedY) / 2)];
				if (capturedPiece != null && capturedPiece.isFire() != selected.isFire())
					return true;
			}
		} else {
			int side = selected.isFire() ? 1 : -1;
			if (!selected.hasCaptured() && (yf == selectedY + side) && (xf == selectedX + 1 || xf == selectedX - 1)) {
				selectedMoved = true;
				return true;
			} else if ((yf == selectedY + (2 * side)) && (xf == selectedX + 2 || xf == selectedX - 2)) {
				Piece capturedPiece = pieces[selectedX + ((xf - selectedX) / 2)][selectedY + side];
				if (capturedPiece != null && capturedPiece.isFire() != selected.isFire())
					return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selected = pieces[x][y];
			selectedX = x;
			selectedY = y;

		} else {
			if (selected != null) {
				if (selected.isBomb()) 
					selectedMoved = true;
				selected.move(x, y);
				selectedX = x;
				selectedY = y;
				canEndTurn = true;
			}
		}
	}

	public void endTurn() {
		turn = !turn;
		canEndTurn = false;
		selected.doneCapturing();
		selectedMoved = false;
	}

	public boolean canEndTurn() {
		return canEndTurn;
	}

	public String winner() {
		boolean winFire = false;
		boolean winWater = false;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) 
						winFire = true;
					else
						winWater = true;
				}
			}
		}
		if (winWater && winFire) {
			return null;
		} else if (!winFire && !winWater) {
			return "No one";
		}
		return winFire ? "Fire" : "Water";
	}

	private void drawBoard() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				
				if (pieces[i][j] != null) {
					String img = "";
					int side = pieces[i][j].side();

					if (pieces[i][j].isShield()) {
						if (pieces[i][j].isKing()) 
							img = IMG[side+2][1];
						else
							img = IMG[side][1];
					} else if (pieces[i][j].isBomb()) {
						if (pieces[i][j].isKing()) 
							img = IMG[side+2][2];
						else
							img = IMG[side][2];
					} else {
						if (pieces[i][j].isKing()) 
							img = IMG[side+2][0];
						else
							img = IMG[side][0];
					}

                    StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
			}
		}

	}

	public static void main(String args[]) {
		Board b = new Board(false);

		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);

		while (b.winner() == null) {
			b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) 
                	b.select(x, y);

            } else if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn())
            		b.endTurn();

            }           
            StdDrawPlus.show(25);
		}
		System.out.println("The winner is " + b.winner());
	}

}