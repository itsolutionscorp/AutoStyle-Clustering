public class Board {
	private int N = 8;
	private Piece[][] pieces;
	private boolean hasMoved = false, fireTurn = true;
	private Piece selected = null;
	private int selectedX = -1, selectedY = -1;

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N]; 
		if (shouldBeEmpty) {}
		else {
			for (int i = 0; i < N; i++) {
	        	if (i % 2 == 0) {
	        		pieces[i][6] = new Piece(false, this, i, 6, "shield");
	        		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
	        		pieces[i][0] = new Piece(true, this, i, 0, "pawn");
	        	}
	        	else {
	        		pieces[i][7] = new Piece(false, this, i, 7, "pawn");
	        		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
	        		pieces[i][1] = new Piece(true, this, i, 1, "shield");
	        	}
	        }
		}
	}

	public Piece pieceAt(int x, int y) {
		try {
			return pieces[x][y];
		} catch (ArrayIndexOutOfBoundsException indexError) {
			return null;
		}
	}

	public void place(Piece p, int x, int y) { //fix this and the matching of array indexes and piece indexes
		try {
			pieces[x][y] = p;
		} catch (ArrayIndexOutOfBoundsException indexError) {
		} catch (NullPointerException nullBoardError) {
		}
	}

	public Piece remove(int x, int y) {
		try {
			if (pieces[x][y] == null) {
				System.out.println("RemoveError: attempted to remove from empty coordinates (" + x + "," + y + ")");
				return null;
			}
			else {
				Piece removedPiece = pieces[x][y];
				pieces[x][y] = null;
				return removedPiece;
			}
		} catch (ArrayIndexOutOfBoundsException indexError) {
			System.out.println("IndexError: attempted to remove piece from inappropriate coordinates (" + x + "," + y + ")");
			return null;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		try {
			if (pieces[xi][yi] == null || pieces[xf][yf] != null) return false;
			if (pieces[xi][yi].isKing()) {
				if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
					return true;
				}
				else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) {
					if (pieces[(xf + xi) >>> 1][(yf + yi) >>> 1] != null) {
						if (pieces[(xf + xi) >>> 1][(yf + yi) >>> 1].isFire() != pieces[xi][yi].isFire()) {
							return true;
						}
						else return false;
					}
					else return false;
				}
				else return false;
			}
			else if (pieces[xi][yi].isFire()) {
				if ((Math.abs(xf - xi) == 1) && (yf - yi == 1)) {
					return true;
				}
				else if ((Math.abs(xf - xi) == 2) && (yf - yi == 2)) {
					if (pieces[(xf + xi) >>> 1][(yf + yi) >>> 1] != null) {
						if (pieces[(xf + xi) >>> 1][(yf + yi) >>> 1].isFire() == false) {
							return true;
						}
						else return false;
					}
					else return false;
				}
				else return false;
			}
			else {
				if ((Math.abs(xf - xi) == 1) && (yf - yi == -1)) {
					return true;
				}
				else if ((Math.abs(xf - xi) == 2) && (yf - yi == -2)) {
					if (pieces[(xf + xi) >>> 1][(yf + yi) >>> 1] != null) {
						if (pieces[(xf + xi) >>> 1][(yf + yi) >>> 1].isFire() == true) {
							return true;
						}
						else return false;
					}
					else return false;
				}
				else return false;
			}
		} catch (ArrayIndexOutOfBoundsException indexError) {
			return false;
		}
	}

	public boolean canSelect(int x, int y) {
		try {
			if (pieces[x][y] != null) {
				if (fireTurn == pieces[x][y].isFire()) {
					if (selected == null || !hasMoved) {
						return true;
					}
					else return false;
				}
				else return false;
			}
			else {
				if (selected != null && validMove(selectedX, selectedY, x, y)) {
					if (hasMoved) {
						if (!selected.hasCaptured()) {
							return false;
						}
						else if (Math.abs(selectedX - x) == 1 && Math.abs(selectedY - y) == 1) {
							return false;
						}
						else return true;
					}
					else return true;
				}
				else return false;
			}
		} catch (ArrayIndexOutOfBoundsException indexError) {return false;}
	}

	public void select(int x, int y) {
		if (pieces[x][y] == null) {
			pieces[selectedX][selectedY].move(x, y);
			hasMoved = true;
			if (selected.isBomb() && selected.hasCaptured()) {
				selectedX = -1;
				selectedY = -1;
			}
			else {
				selectedX = x;
				selectedY = y;
			}
		}
		else {
			selected = pieces[x][y];
			selectedX = x;
			selectedY = y;
		}
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		else return false;
	}

	public void endTurn() {
		if (canEndTurn()) {
			selected.doneCapturing();
			hasMoved = false;
			fireTurn = !fireTurn;
			selected = null;
			selectedX = -1;
			selectedY = -1;
		}
	}

	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                	if (pieces[i][j] != null) {
                		if (pieces[i][j].isFire()) {
                			fireCount++;
                		}
                		else waterCount++;
                	}
                }
            }
        if (fireCount == 0 && waterCount == 0) return "No one";
        else if (fireCount == 0) return "Water";
        else if (waterCount == 0) return "Fire";
        else return null;
	}

	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == selectedX && j == selectedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		if (pieces[i][j].isBomb()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
                		else if (pieces[i][j].isShield()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		}
                		else {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	}
                	else {
                		if (pieces[i][j].isBomb()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		}
                		else if (pieces[i][j].isShield()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		}
                		else {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
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

    public static void main(String[] args) {
        Board gameBoard = new Board(false);
        while (true) {
        	gameBoard.drawBoard(gameBoard.N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (gameBoard.canSelect((int) x, (int) y)) {
                gameBoard.select((int) x, (int) y);
        		}
            }
            else if (StdDrawPlus.isSpacePressed()) {
            	gameBoard.endTurn();
            }          
            StdDrawPlus.show(100);
        }
    }
}