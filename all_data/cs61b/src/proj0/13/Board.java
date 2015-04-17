/** 
 *  Name: Felix Su
 *  Login: cs61b-aan
 * 	Final
 */
public class Board {
	private Piece[][] grid = new Piece[8][8];
	private int player = 0;
	private int pieceX = 0;
	private int pieceY = 0;
	private boolean moved;
	private Piece pCurrent;
	private boolean hasCap;
	private int fireTot = 0;
	private int waterTot = 0;
	private boolean[][] selected = new boolean[8][8];
	private boolean beforeKing = false;
	private boolean afterKing = false;
	private boolean kingCantSelect = false;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == true) {
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            	selected[i][j] = false;
	            }
	        }
		}
		else {
		    for (int i = 0; i < 8; i+=2) {
		    	place(new Piece(true, this, i, 0, "pawn"), i, 0);
		    	place(new Piece(true, this, i+1, 1, "shield"), i+1, 1);
		    	place(new Piece(true, this, i, 2, "bomb"), i, 2);
		    	place(new Piece(false, this, i+1, 5, "bomb"), i+1, 5);
		    	place(new Piece(false, this, i, 6, "shield"), i, 6);
		    	place(new Piece(false, this, i+1, 7, "pawn"), i+1, 7);
        	}
    	}
	}

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selected[i][j]) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            	if (grid[i][j] != null) {
	                drawImage(i, j, grid[i][j]);
	            }
            }
        }
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7 || x < 0) || (y > 7 || y < 0)) {
			return null;
		}
		if (grid[x][y] != null) {
			return grid[x][y];
		}
		else {
			return null;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (grid[xf][yf] == null) {
			if (grid[xi][yi].isKing()) {
				if (yf == yi + 1 || yf == yi - 1) {
					if (xf == xi + 1 || xf == xi - 1) {
						return true;
					}
				}
			}
			else if (grid[xi][yi].isFire()){
				if (yf == yi + 1) {
					if (xf == xi + 1 || xf == xi - 1) {
						return true;
					}
				}
				else {
					return false;
				}
			}
			else if (!grid[xi][yi].isFire()){
				if (yf == yi - 1) {
					if (xf == xi + 1 || xf == xi - 1) {
						return true;
					}
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	private boolean validJump(int xf, int yf) {
		if (grid[xf][yf] == null) {
			if (pCurrent.isKing()) {
				if (yf == pieceY + 2 ) {
					if (pCurrent.isFire()) {
						if (grid[pieceX+1][pieceY+1] != null && (xf == pieceX + 2 && !grid[pieceX+1][pieceY+1].isFire())) {
							return true;
						}
						if (grid[pieceX-1][pieceY+1] != null && (xf == pieceX - 2 && !grid[pieceX-1][pieceY+1].isFire())) {
							return true;
						}
					}
					if (!pCurrent.isFire()) {
						if (grid[pieceX+1][pieceY+1] != null && (xf == pieceX + 2 && grid[pieceX+1][pieceY+1].isFire())) {
							return true;
						}
						if (grid[pieceX-1][pieceY+1] != null && (xf == pieceX - 2 && grid[pieceX-1][pieceY+1].isFire())) {
							return true;
						}
					}
				}
				if (yf == pieceY - 2) {
					if (pCurrent.isFire()) {
						if (grid[pieceX+1][pieceY-1] != null && (xf == pieceX + 2 && !grid[pieceX+1][pieceY-1].isFire())) {
							return true;
						}
						if (grid[pieceX-1][pieceY-1] != null && (xf == pieceX - 2 && !grid[pieceX-1][pieceY-1].isFire())) {
							return true;
						}
					}
					if (!pCurrent.isFire()) {
						if (grid[pieceX+1][pieceY-1] != null && (xf == pieceX + 2 && grid[pieceX+1][pieceY-1].isFire())) {
							return true;
						}
						if (grid[pieceX-1][pieceY-1] != null && (xf == pieceX - 2 && grid[pieceX-1][pieceY-1].isFire())) {
							return true;
						}
					}
				}
			}
			else if (pCurrent.isFire()){
				if (yf == pieceY + 2) {
					if (grid[pieceX+1][pieceY+1] != null && (xf == pieceX + 2 && !grid[pieceX+1][pieceY+1].isFire())) {
						return true;
					}
					if (grid[pieceX-1][pieceY+1] != null && (xf == pieceX - 2 && !grid[pieceX-1][pieceY+1].isFire())) {
						return true;
					}
				}
				else {
					return false;
				}
			}
			else if (!pCurrent.isFire()){
				if (yf == pieceY - 2) {
					if (grid[pieceX+1][pieceY-1] != null && (xf == pieceX + 2 && grid[pieceX+1][pieceY-1].isFire())) {
						return true;
					}
					if (grid[pieceX-1][pieceY-1] != null && (xf == pieceX - 2 && grid[pieceX-1][pieceY-1].isFire())) {
						return true;
					}
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	private boolean hasSelected() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (selected[i][j] == true) {
            		return true;
            	}
            }
        }
        return false;
	}

	public boolean canSelect(int x, int y) {
		if (kingCantSelect == true) {
			return false;
		}
		if (grid[x][y] != null) {
			if (!hasSelected()) {
				if (grid[x][y].isFire() && player == 0) {
					return true;
				}
				if (!grid[x][y].isFire() && player == 1) {
					return true;
				}
			}
			else if (!moved) {
				if (grid[x][y].isFire() && player == 0) {
					return true;
				}
				if (!grid[x][y].isFire() && player == 1) {
					return true;
				}
			}
			else {
				return false;
			}
		}
		else {
			if (grid[x][y] == null && grid[pieceX][pieceY] != null) {
				if (validMove(pieceX, pieceY, x, y) || validJump(x, y)) {
					if (!moved) {
						return true;
					}
					if (hasCap) {
						if (validJump(x, y)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (grid[x][y] != null) {
			pCurrent = grid[x][y];
			pieceX = x;
			pieceY = y;
			resetSelected();
			selected[x][y] = true;
			drawImage(x, y, grid[x][y]);
		}
		else {
			remove(pieceX, pieceY);
			if ((x - pieceX == 2 || pieceX - x == 2) || (y - pieceY == 2 || pieceY - y == 2)) {
				hasCap = true;
				if (y == pieceY + 2 ) {
					if (pCurrent.isFire()) {
						if (x == pieceX + 2) {
							remove(pieceX+1, pieceY+1);
						}
						if (x == pieceX - 2) {
							remove(pieceX-1, pieceY+1);
						}
					}
					if (!pCurrent.isFire()) {
						if (x == pieceX + 2) {
							remove(pieceX+1, pieceY+1);
						}
						if (x == pieceX - 2) {
							remove(pieceX-1, pieceY+1);
						}
					}
				}
				if (y == pieceY - 2) {
					if (pCurrent.isFire()) {
						if (x == pieceX + 2) {
							remove(pieceX+1, pieceY-1);
						}
						if (x == pieceX - 2) {
							remove(pieceX-1, pieceY-1);
						}
					}
					if (!pCurrent.isFire()) {
						if (x == pieceX + 2) {
							remove(pieceX+1, pieceY-1);
						}
						if (x == pieceX - 2) {
							remove(pieceX-1, pieceY-1);
						}
					}
				}
			}
			beforeKing = pCurrent.isKing();
			place(pCurrent, x, y);
			pCurrent.move(x, y);
			afterKing = pCurrent.isKing();
			if (!hasCap && (!beforeKing && afterKing)) {
				kingCantSelect = true;
			}
			moved = true;
			resetSelected();
			selected[x][y] = true;
			if (pCurrent.isBomb() && hasCap) {
				bombCapture(x, y);
			}
		}
	}

		private void bombCapture(int x, int y) {
		int begRow = x - 1;
		int endRow = x + 1;
		int begCol = y - 1;
		int endCol = y + 1;
		if (x == 0 && y == 0) {
			begRow = 0;
			begCol = 0;
		}
		if (x == 0) {
			begRow = 0;
		}
		if (y == 0) {
			begCol = 0;
		}
		if (x == 7 && y == 7) {
			endRow = 7;
			endCol = 7;
		}
		if (x == 7) {
			endRow = 7;
		}
		if (y == 7) {
			endCol = 7;
		}
		for (int i = begRow; i <= endRow; i+=1) {
			for (int j = begCol; j <= endCol; j+=1) {
				if (grid[i][j] !=null && (!grid[i][j].isShield())) {
					remove(i, j);
				}
			}
		}
		remove(pieceX, pieceY);
	}

	private void resetSelected() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	selected[i][j] = false;
            }
        }
	}

	public void place(Piece p, int x, int y) {
		if (p != null && ((x < 8 && x >= 0) && (y < 8 && y >= 0))) {
			pieceX = x;
			pieceY = y;
			pCurrent = p;
			grid[x][y] = p;
			if (p.isFire()) {
				fireTot += 1;
			}
			if (!p.isFire()) {
				waterTot += 1;
			}
		}
	}

	private void drawImage(int x, int y, Piece p) {
		if (!p.isKing()) {
			if (p.isShield()) {
				if (p.isFire()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				}
			}
			else if (p.isBomb()) {
				if (p.isFire()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				}
			}
			else {
				if (p.isFire()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
				}
			}
		}
		else if (p.isKing()) {
			if (p.isShield()) {
				if (p.isFire()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
				}
			}
			else if (p.isBomb()) {
				if (p.isFire()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
				}
			}
			else {
				if (p.isFire()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
				}
			}
		}
	}

	public boolean canEndTurn() {
		if (moved || hasCap) {
			return true;
		}
		return false;
	}

	public Piece remove(int x, int y) {
		if (grid[x][y] != null) {
			if (grid[x][y].isFire()) {
				fireTot -= 1;
			}
			if (!grid[x][y].isFire()) {
				waterTot -= 1;
			}
			Piece temp = grid[x][y];
			grid[x][y] = null;
			return temp;
		}
		if (grid[x][y] == null) {
			System.out.println("The is no piece on this square.");
			return null;
		}
		else {
			System.out.println("Error: Index out of Bounds. Please click a valid square.");
			return null;
		}
	}

	public void endTurn() {
		if (player == 0) {
			this.player = 1;
		}
		else if (player == 1) {
			this.player = 0;
		}
		resetSelected();
		moved = false;
		hasCap = false;
		pCurrent.doneCapturing();
		kingCantSelect = false;
		beforeKing = false;
		afterKing = false;
	}

	public String winner() {
		if (fireTot == 0 && waterTot > 0) {
			return "Water";
		}
		else if (waterTot == 0 && fireTot > 0) {
			return "Fire";
		}
		else if (fireTot == 0 && waterTot == 0) {
			return "No one";
		}
		return null;
	}

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);

        while(true) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int a = (int) x;
                int b = (int) y;
                if (board.canSelect(a, b)) {
	               	board.select(a, b);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            	}
            }
            if (board.fireTot == 0 || board.waterTot == 0) {
            	System.out.println(board.winner());
            	break;
            }
            StdDrawPlus.show(100);
        }
	}
}