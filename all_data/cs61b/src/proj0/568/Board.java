public class Board {
	private boolean isFireTurn = true;
	private boolean canEndTurn;

	public static void main(String[] args) {
		// StdDrawPlus.picture(.5, .5, "img/board.png", 1, 1);
		// Oops nevermind, I will draw the board instead.
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board game = new Board(false);
		drawGame(game);

		while (true) {
			if (gameOver(game)) {
            	System.out.println(game.winner() + " wins!");
            	return;
            }
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
				if (game.canSelect(x, y)) {
					game.select(x, y);
				}
				drawGame(game);
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (game.canEndTurn()) {
            		game.hasSimpleMoved = false;
            		game.endTurn();
            	}
            	drawGame(game);
            }
            StdDrawPlus.show(100);
		}
	}
	private static void drawGame(Board game) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                }
                if ((i == game.selX) && (j == game.selY)) {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (game.pieceAt(i, j) != null) {
                	StdDrawPlus.picture(i + .5, j + .5, pieceIndex(game.pieceAt(i, j)), 1, 1);
				}
            }
        }
	}
	private static String pieceIndex(Piece p) {
		int n = 1; // Converting piece type to an integer.
		if      (p.isShield()) n += 1;
		else if (p.isBomb())   n += 2;
		if      (p.isKing())   n += 10;
		if      (p.isFire())   n += 100;
		else                   n += 200;
		//---------------------------------------------
		if 		(n == 101) return "img/pawn-fire.png";
		else if (n == 102) return "img/shield-fire.png";
		else if (n == 103) return "img/bomb-fire.png";
		else if (n == 111) return "img/pawn-fire-crowned.png";
		else if (n == 112) return "img/shield-fire-crowned.png";
		else if (n == 113) return "img/bomb-fire-crowned.png";
		else if (n == 201) return "img/pawn-water.png";
		else if (n == 202) return "img/shield-water.png";
		else if (n == 203) return "img/bomb-water.png";
		else if (n == 211) return "img/pawn-water-crowned.png";
		else if (n == 212) return "img/shield-water-crowned.png";
		else if (n == 213) return "img/bomb-water-crowned.png";
		else 			   return "";
		
	}
	private static boolean gameOver(Board b) {
		int firePieces = 0;
		int waterPieces = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                	if (b.pieceAt(i, j).isFire()) {
                		firePieces += 1;
                	} else {
                		waterPieces += 1;
                	}
                } catch (NullPointerException e) {}                
            }
        }
        return (firePieces == 0 || waterPieces == 0);
	}


	/* Separation between static and nonstatic
	   (things used by main vs. attributes of the board). */

	private Piece[][] pieces = new Piece[8][8];
	private boolean pieceSelected = false;
	private Piece selectedPiece;
	private boolean hasSimpleMoved = false;
	private int selX = -1; private int selY = -1;
	private int selectedXPos; private int selectedYPos;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i += 2) {
				this.pieces[i] = new Piece[] {
					new Piece(true, this, i, 0, "pawn"), null, 
					new Piece(true, this, i, 2, "bomb"), null, 
					null, null, 
					new Piece(false, this, i, 6, "shield"), null
				};
			}	
		   	for (int j = 1; j < 9; j += 2) {
				this.pieces[j] = new Piece[] {
					null, new Piece(true, this, j, 1, "shield"), 
					null, null,
					null, new Piece(false, this, j, 5, "bomb"),
					null, new Piece(false, this, j, 7, "pawn")
				};
			}
		}
	}
	public Piece pieceAt(int x, int y) {
		try {
			return this.pieces[x][y];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	public void place(Piece p, int x, int y) {
		if (p != null) {
			try {
				this.pieces[x][y] = p;
			} catch (IndexOutOfBoundsException e) {}
		}
	}
	public Piece remove(int x, int y) {
		if (pieceAt(x, y) == null) {
			System.out.println("No piece there!");
			return null;
		} else {
			try {
				Piece toRet = pieceAt(x, y);
				this.pieces[x][y] = null;
				return toRet;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid location!");
				return null;
			}
		}
	}
	public boolean canSelect(int x, int y) {
		if (hasSimpleMoved) return false;
		if (selectedPiece != null && selectedPiece.hasCaptured()) return validMove(selectedXPos, selectedYPos, x, y);
		
		if (pieceAt(x, y) != null) {
			return (!(isFireTurn ^ pieceAt(x, y).isFire()));
		} else {
			if (!pieceSelected) {
				return false;
			} else {
				return validMove(selectedXPos, selectedYPos, x, y);
			}
		}
	}
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((pieceAt(xi, yi) == null) || (pieceAt(xf, yf) != null)) {
			return false;
		}

		int sign = 1;
		if (!pieceAt(xi, yi).isFire()) {
			sign = -1;
		}

		if (!pieceAt(xi, yi).hasCaptured()) {
			if ((yf - yi == sign) && (Math.abs(xf - xi) == 1)) {
				return true;
			}
			if (this.pieceAt(xi, yi).isKing()) {
				if ((Math.abs(yf - yi) == 1) && (Math.abs(xf - xi) == 1)) {
					return true;
				}
			}
		}

		if ((yf - yi == 2*sign) && (Math.abs(xf - xi) == 2)) {
			return (pieceAt((xi + xf) / 2, yi+sign) != null) && 
				   (pieceAt((xi + xf) / 2, yi+sign).isFire() ^ pieceAt(xi, yi).isFire());
		}

		if (this.pieceAt(xi, yi).isKing()) {
			if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {
			return (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) && 
				   (pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() ^ pieceAt(xi, yi).isFire());
			}
		}
		return false;
	}
	public void select(int x, int y) {
		if (!pieceSelected) {
			selectedPiece = pieceAt(x, y);
			selectedXPos = x; selectedYPos = y;
			pieceSelected = true;
			selX = x; selY = y;
		} else if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
			selectedXPos = x; selectedYPos = y;
			selX = x; selY = y;
		} else {
			if (Math.abs(x - selectedXPos) == 2) {
				this.remove((x + selectedXPos) / 2, (y + selectedYPos) / 2);
				this.captureOccurred(x, y);
			} else {
				hasSimpleMoved = true;
				pieceSelected = false;
				selX = -1; selY = -1;
				selectedPiece.move(x, y);
				selectedXPos = x; selectedYPos = y;
			}
			canEndTurn = true;
		}
	}
	public boolean canEndTurn() {
		return canEndTurn;
	}
	public void endTurn() {
		canEndTurn = false;
		isFireTurn = (!isFireTurn);
		selectedPiece.doneCapturing();
		selX = -1; selY = -1;
	}
	public String winner() {
		// Assumes the game is over
		int firePieces = 0;
		int waterPieces = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                	if (pieceAt(i, j).isFire()) {
                		firePieces += 1;
                	} else {
                		waterPieces += 1;
                	}
                } catch (NullPointerException e) {}                
            }
        }
        if (firePieces > waterPieces) {
        	return "Fire";
        } else if (waterPieces > firePieces) {
        	return "Water";
        } else {
        	return "No one";
        }
	}
	private void captureOccurred(int x, int y) {
		if (selectedPiece.isBomb()) {
			hasSimpleMoved = true;
			pieceSelected = false;
			selX = -1; selY = -1;
			selectedPiece.move(x, y);
			selectedXPos = x; selectedYPos = y;
			return;
		}
		selectedPiece.move(x, y);
		selectedXPos = x; selectedYPos = y;

		if (canSelect(x-2, y-2) || canSelect(x-2, y+2) || canSelect(x+2, y-2) || canSelect(x+2, y+2)) {
			selX = x; selY = y;
		} else {
			selX = -1; selY = -1;
		}
	}
}