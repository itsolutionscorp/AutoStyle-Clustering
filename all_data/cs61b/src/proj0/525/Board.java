public class Board {

	private Piece[][] pieces= new Piece[8][8];
	private int selectedX = -1;
	private int selectedY = -1;
	private boolean moved = false;
	private boolean fireTurn = true;

	public static void main(String args[]) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        while (true) {
        	if (StdDrawPlus.mousePressed()) {
        		int x = (int) StdDrawPlus.mouseX();
        		int y = (int) StdDrawPlus.mouseY();
        		if (b.canSelect(x, y)) {
        			b.select(x, y);
        		}
        	}
        	if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) {
        		b.endTurn();
        	}
        	if (StdDrawPlus.isNPressed()) {
        		b = new Board(false);
        	}
           	b.drawBoard();
       		b.winner();
        	StdDrawPlus.show(100);
        }
	}

	/* Ideas drawn from Josh Hugs version in StdDrawDemo*/
	public Board(boolean shouldBeEmpty) {
		for (int i = 0; i < 8; i = i + 1) {
			for (int j = 0; j < 8; j = j + 1) {
				String s = "img/";
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					if (!shouldBeEmpty) {
						if (j < 3) {
							if (j == 0) {
								pieces[i][j] = new Piece(true, this, i, j, "pawn");
								s = s + "pawn";
							} else if (j == 1) {
								pieces[i][j] = new Piece(true, this, i, j, "shield");
								s = s + "shield";
							} else if (j == 2) {
								pieces[i][j] = new Piece(true, this, i, j, "bomb");
								s = s + "bomb";
							}
							s = s + "-fire.png";
							StdDrawPlus.picture(i + .5, j + .5, s, 1, 1);
						}
						if (j > 4) {
							if (j == 5) {
								pieces[i][j] = new Piece(false, this, i, j, "bomb");
								s = s + "bomb";
							} else if (j == 6) {
								pieces[i][j] = new Piece(false, this, i, j, "shield");
								s = s + "shield";
							} else if (j == 7) {
								pieces[i][j] = new Piece(false, this, i, j, "pawn");
								s = s + "pawn";
							}
							s = s + "-water.png";
							StdDrawPlus.picture(i + .5, j + .5, s, 1, 1);

						}
					}
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
			}
		}
	}

	/* adapted from Josh Hugs version ins StdDrawDemo */
	private void drawBoard() {
		for (int i = 0; i < 8; i = i + 1) {
            for (int j = 0; j < 8; j = j + 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i == selectedX && j == selectedY) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieceAt(i, j) != null) {
                	String s = "img/";
                	if (pieceAt(i, j).isBomb()) {
                		s = s + "bomb";
                	} else if (pieceAt(i, j).isShield()) {
                		s = s + "shield";
                	} else {
                		s = s + "pawn";
                	}
                	if (pieceAt(i, j).isFire()) {
                		s = s + "-fire";
                	} else {
                		s = s + "-water";
                	}
                	if (pieceAt(i, j).isKing()) {
                		s = s + "-crowned";
                	} 
                	s = s + ".png";
                    StdDrawPlus.picture(i + .5, j + .5, s, 1, 1);
                }
            }
        }
	}
	
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		return pieces[x][y];
	}

	private boolean validMove(int xi, int yi, int xj, int yj) {
		int multiplier; /* Makes it so that you can test water and fire the same way using the multiplier because they go in opposite directions */
		if (pieces[xi][yi].isFire()) {
			multiplier = 1;
		} else {
			multiplier = -1;
		}
		int xv = xj - xi;
		int yv = yj - yi;
		/* No piece should be able to move horizontally */
		if (xv == 0 || yv == 0) {
			return false;
		}
		/* If the piece is a king, then it should be able to move in any direction it chooses: modify the multipler so it reflects this */ 
		if (pieces[xi][yi].isKing()) {
			if (yv < 0) {
				multiplier = (-1);
			} else {
				multiplier = 1;
			}
		}
		if (xj >= 0 && xj <= 7 && yj >= 0 && yj <= 7 && pieces[xj][yj] == null) {
			/* 	If the magnitude of the tangent is 1, then the piece is moving diagonally */ 
			if (Math.abs(yv / xv) == 1) {
				if ((multiplier * yv) == 1) {
					return true;
				} else if ((multiplier * yv) == 2) {
					int middleX = xv / Math.abs(xv);
					int middleY = yv / Math.abs(yv);
					if ((pieces[xi + middleX][yi + middleY] != null) && (pieces[xi][yi].isFire() != pieces[xi + middleX][yi + middleY].isFire())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void place(Piece p, int x, int y) {
		if (p == null || x > 7 || y > 7 || x < 0 || y < 0) {
			return;
		} else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		Piece p = pieceAt(x, y);
		pieces[x][y] = null;
		return p;
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null && (pieceAt(x, y).isFire() == fireTurn) && !moved) {
			return true;
		} else if (selectedX != -1 && selectedY != -1 && !moved) {
			if (validMove(selectedX, selectedY, x, y)) {
				return true;
			}
		} else if (moved && (pieceAt(selectedX, selectedY) != null)) {
			if (validMove(selectedX, selectedY, x, y)) {
				if (Math.abs(selectedX - x) > 1 && Math.abs(selectedY) > 1) {
					return true;
				}
			}
		} else if (!canEndTurn()) {
			selectedX = -1;
			selectedY = -1;
		}
		return false;
	}

	public void select(int x, int y) {
		if (selectedX != -1 && selectedY != -1 && pieceAt(x, y) == null) {
			pieceAt(selectedX, selectedY).move(x, y);
			moved = true;
		}
		selectedX = x;
		selectedY = y;
	}

	public boolean canEndTurn() {
		if (moved) {
			return true;
		} 
		return false;
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		if (pieceAt(selectedX, selectedY) != null) {
			pieceAt(selectedX, selectedY).doneCapturing();
		}
		moved = false;
		selectedX = -1;
		selectedY = -1;
	}

	public String winner() {
		boolean f = false;
		boolean w = false;
		String s;
		for (int i = 0; i < 8; i = i + 1) {
			for (int j = 0; j < 8; j = j + 1) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						f = true;
					} else {
						w = true;
					}
				}
			}
		}
		if (f && w) {
			return null;
		} else if (f) {
			return "Fire";
		} else if (w) {
			return "Water";
		}
		return "No one";
	}
}