public class Board {
 
	private boolean isEmpty, isFireTurn, hasSelected, hasMoved;
	private int fPieces, wPieces, xSelected, ySelected;
	private Piece[][] pieces;

	public Board(boolean shouldBeEmpty) {
		isEmpty = shouldBeEmpty;
		isFireTurn = true;
		hasSelected = false;
		hasMoved = false;
		fPieces = 0;
		wPieces = 0;
		xSelected = -1;
		ySelected = -1;
		pieces = new Piece[8][8];
		if (!isEmpty) {
			placePieces();
		}
	}

	private void placePieces() {
		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				if (x % 2 == 0) {
					if (y == 0) {
						place(new Piece(true, this, x, y, "pawn"), x, y);
					} else if (y == 2) {						
						place(new Piece(true, this, x, y, "bomb"), x, y);
					} else if (y == 6) {
						place(new Piece(false, this, x, y, "shield"), x, y);
					}
				} else {
					if (y == 1) {
						place(new Piece(true, this, x, y, "shield"), x, y);
					} else if (y == 5) {
						place(new Piece(false, this, x, y, "bomb"), x, y);
					} else if (y == 7) {
						place(new Piece(false, this, x, y, "pawn"), x, y);
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			return pieces[x][y];
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (isFireTurn) {
				if (pieceAt(x, y) != null && pieceAt(x, y).isFire()) {
					if (!hasSelected || (hasSelected && !hasMoved)) {
						return true;
					} 
				} else if (pieceAt(x, y) == null) {
					if (hasSelected && pieceAt(xSelected, ySelected) != null && validMove(xSelected, ySelected, x, y)) {
						if (!hasMoved || pieceAt(xSelected, ySelected).hasCaptured() && canCapture(x, y)) {
							return true;
						} 
					}
				}
			} else if (!isFireTurn) {		
				if (pieceAt(x, y) != null && !pieceAt(x, y).isFire()) {
					if (!hasSelected || (hasSelected && !hasMoved)) {
						return true;
					} 
				} else if (pieceAt(x, y) == null) {
					if (hasSelected && pieceAt(xSelected, ySelected) != null && validMove(xSelected, ySelected, x, y)) {
						if (!hasMoved || pieceAt(xSelected, ySelected).hasCaptured() && canCapture(x, y)) {
							return true;
						}
					} 
				}
			} 
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi != xf && yi != yf) {
			if ((yf - yi) / (xf - xi) == 1 || (yf - yi) / (xf - xi) == -1) {
				if (pieceAt(xi, yi).isFire()) {
					if (pieceAt(xi, yi).isKing()) {			
						if ((yf - yi == 2) || (yf - yi == -2)) {
							return canCapture(xf, yf);
						}
						return ((yf - yi == 1) || (yf - yi == -1));
					} else {
						if (yf - yi == 2) {
							return canCapture(xf, yf);
						}
						return (yf - yi == 1);
					}
				} else if (!pieceAt(xi, yi).isFire()) {	
					if (pieceAt(xi, yi).isKing()) {			
						if ((yf - yi == 2) || (yf - yi == -2)) {
							return canCapture(xf, yf);
						}
						return ((yf - yi == 1) || (yf - yi == -1));
					} else {	
						if (yf - yi == -2) {
							return canCapture(xf, yf);
						}
						return (yf - yi == -1);
					}
				} 
			}
		}
		return false;
	}

	private boolean canCapture(int x, int y) {
		Piece p = pieceAt((xSelected + x) / 2, (ySelected + y) / 2);
		if (isFireTurn) {
			if (p != null && !p.isFire()) {
				if (pieceAt(xSelected, ySelected).isKing()) {
					if (y < ySelected) {
						return true;
					}
				}			
				if (y > ySelected) {
					return true;
				}				
			}
		} else if (!isFireTurn) {
			if (p != null && p.isFire()) {
				if (pieceAt(xSelected, ySelected).isKing()) {
					if (y > ySelected) {
						return true; 
					}
				}
				if (y < ySelected) {
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			hasSelected = true;
			xSelected = x;
			ySelected = y;
		} 
		else {
			hasMoved = true; 
			Piece p = pieceAt(xSelected, ySelected);
			p.move(x, y);
			xSelected = x;
			ySelected = y;
		}
	}

	private void makeWhite(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}

	private void draw() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                if (x == xSelected && y == ySelected) {
                	makeWhite(x, y);
                }
                Piece p = pieceAt(x, y);
                if (p != null) {
                	if (p.isFire()) {
						if (p.isBomb()) {
							if (p.isKing()) {
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
							}		
						} else if (p.isShield()) {
							if (p.isKing()) {
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
							}
						} else {
							if (p.isKing()) {
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
							}				
						}
					} else {
						if (p.isBomb()) {
							if (p.isKing()) {
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
							}				
						} else if (p.isShield()) {
							if (p.isKing()) {
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
							}				
						} else {
							if (p.isKing()) {
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
							}	
						} 
					}
                }
            }
        }     		
	}

	public void place(Piece p, int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && p != null) {
			pieces[x][y] = p;
			if (p.isFire()) {
				fPieces += 1;
			} else {
				wPieces += 1;
			}
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7) {
			System.out.println("Input Out Of Bounds!");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("No Piece To Remove!");
			return null;
		} else {
			Piece p = pieceAt(x, y);
			pieces[x][y] = null;
			if (p.isFire()) {
				fPieces -= 1;
			} else {
				wPieces -= 1;
			}			
			return p;
		}
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn() {
		isFireTurn = !isFireTurn;
		hasSelected = false;
		hasMoved = false;
		Piece p = pieceAt(xSelected, ySelected);
		if (p != null) {		
			if (p.hasCaptured()) {
				p.doneCapturing();
			}
		}
		xSelected = -1;
		ySelected = -1;
	}

	public String winner() {
		if (fPieces == 0 && wPieces == 0) {
			return "No one";
		} else if (fPieces == 0) {
			return "Water";
		} else if (wPieces == 0) {
			return "Fire";
		} else {
			return null;
		}
	}


	public static void main(String[] args) {
		Board b = new Board(false);
		b.draw();
		while (b.fPieces != 0 && b.wPieces != 0) {
			StdDrawPlus.show(10);
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
					b.draw();
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
					b.draw();
				}
			}			
		}
		StdDrawPlus.show(10);
		System.out.println(b.winner());
	}
}