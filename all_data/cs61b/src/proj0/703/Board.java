public class Board {
	private static Board currboard;
	private Piece[][] pieces;
	private int turn = 0;
	private boolean canend = false;
	private boolean moved = false;
	private Piece currSelected = null;
	private int currx;
	private int curry;
	private boolean hascaptured = false;
	private int numFire = 0;
	private int numWater = 0;

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8]; 
				if (shouldBeEmpty == false) {
					for (int a = 0; a < 8; a++) {
						for (int b = 0; b < 8; b++) {
							if ((b == 0) && (a%2 == 0)) {
								this.pieces[a][b] = new Piece(true, this, a, b, "pawn");
								numFire = numFire + 1;
							}
							else if ((b == 1) && (a%2 != 0)) {
								this.pieces[a][b] = new Piece(true, this, a, b, "shield");
								numFire = numFire + 1;
							}
							else if ((b == 2) && (a%2 == 0)) {
								this.pieces[a][b] = new Piece(true, this, a, b, "bomb");
								numFire = numFire + 1;
							}
							else if ((b == 5) && (a%2 != 0)) {
								this.pieces[a][b] = new Piece(false, this, a, b, "bomb");
								numWater = numWater + 1;
							}
							else if ((b == 6) && (a%2 == 0)) {
								this.pieces[a][b] = new Piece(false, this, a, b, "shield");
								numWater = numWater + 1;
							}
							else if ((b == 7) && (a%2 != 0)) {
								this.pieces[a][b] = new Piece(false, this, a, b, "pawn");
								numWater = numWater + 1;
							}
						}
					}
					drawBoard(false);
				}
				else {
					drawBoard(true);
				}

	}
	
	private void drawBoard(boolean empty) {
		StdDrawPlus.setXscale(0, 8);
	    StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        if (empty == false) {
        	for (int a = 0; a < 8; a++) {
				for (int b = 0; b < 8; b++) {
					if (pieces[a][b] != null) {
						String imagename = piecename(pieces[a][b]);
						StdDrawPlus.picture(a + .5, b + .5, imagename, 1, 1);
					}
				}
			}
        }
	}

	private String piecename(Piece p) {
		String imagename = ".png";
		if (p.isKing() == true) {
			imagename = "-crowned" + imagename;
		}
		if (p.isFire() == true) {
			imagename = "-fire" + imagename;
		} else {
			imagename = "-water" + imagename;
		}
		if (p.isBomb() == true) {
			imagename = "img/bomb" + imagename;
		}
		else if (p.isShield() == true) {
			imagename = "img/shield" + imagename;
		} else {
			imagename = "img/pawn" + imagename;
		}
		return imagename;
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 8) || (y > 8)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}
	public boolean canSelect(int x, int y) {
		if (turn == 0) { 
			if (currSelected == null) {
				if ((pieces[x][y] != null) && (pieces[x][y].isFire() == true)) {
					return true;
				} else {
					return false;
				}
			}
			else if ((pieces[x][y] != null) && (pieces[x][y].isFire() == true) && (moved == false)) {
				return true;
			}
			else if (hascaptured == true) {
				if (validmove(currx, curry, x, y) && isCapture(currx, curry, x, y)) {
					return true;
				}	
				else {
					return false;
				}
			}		
			else if (moved == false && validmove(currx, curry, x, y)) {
				return true;
			}
			else {
				return false;
			}

		} 
		else {
			if (currSelected == null) {
				if ((pieces[x][y] != null) && (pieces[x][y].isFire() == false)) {
					return true;
				} else {
					return false;
				}
			}
			else if ((pieces[x][y] != null) && (pieces[x][y].isFire() == false) && (moved == false)) {
				return true;
			}
			else if (hascaptured == true) {
				if (validmove(currx, curry, x, y) && isCapture(currx, curry, x, y)) {
					return true;
				}	
				else {
					return false;
				}
			}		
			else if ((moved == false) && (validmove(currx, curry, x, y))) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	private boolean validmove(int x, int y, int xf, int yf) {
		if (pieces[x][y] != null) {
			if (pieces[x][y].isFire() == true) {
				if (pieces[x][y].isKing() == true) {
					if ((nAwayUp(x, y, xf, yf, 1) || nAwayUp(xf, yf, x, y, 1)) && pieces[xf][yf] == null) {
						return true;
					}
					else if ((nAwayUp(x, y, xf, yf, 2) || nAwayUp(xf, yf, x, y, 2)) && otherPieceInbetween(x, y, xf, yf, true) && pieces[xf][yf] == null) {
						return true;
					} else {
						return false;
					}
				}
				else {
					if (nAwayUp(x, y, xf, yf, 1) && pieces[xf][yf] == null) {
						return true;
					}
					else if (nAwayUp(x, y, xf, yf, 2) && otherPieceInbetween(x, y, xf, yf, true) && pieces[xf][yf] == null) {
						return true;
					} else {
						return false;
					}
				}
			} 
			else {
				if (pieces[x][y].isKing() == true) {
					if ((nAwayUp(x, y, xf, yf, 1) || nAwayUp(xf, yf, x, y, 1)) && pieces[xf][yf] == null) {
						return true;
					}
					else if ((nAwayUp(x, y, xf, yf, 2) || nAwayUp(xf, yf, x, y, 2)) && otherPieceInbetween(x, y, xf, yf, false) && pieces[xf][yf] == null) {
						return true;
					} else {
						return false;
					}
				}
				else {
					if (nAwayUp(xf, yf, x, y, 1) && pieces[xf][yf] == null) {
						return true;
					}
					else if (nAwayUp(xf, yf, x, y, 2) && otherPieceInbetween(x, y, xf, yf, false) && pieces[xf][yf] == null) {
						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
	}

	private boolean nAwayUp(int x, int y, int xf, int yf, int n) {
		if (((yf - y) == n) && ((xf - x == n) || (x - xf == n))) {
			return true;
		} else {
			return false;
		}
	}	
	private boolean isCapture(int x, int y, int xf, int yf) {
		if ((nAwayUp(x, y, xf, yf, 2) == true) || (nAwayUp(xf, yf, x, y, 2) == true)) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean otherPieceInbetween(int x, int y, int xf, int yf, boolean fire) {
		if (fire == true) {
			if ((pieces[(x+xf)>>>1][(y+yf)>>>1]) != null && (pieces[(x+xf)>>>1][(y+yf)>>>1].isFire() == false)) {
				return true;
			} 
			else {
				return false;
			}
		} else if ((pieces[(x+xf)>>>1][(y+yf)>>>1]) != null && (pieces[(x+xf)>>>1][(y+yf)>>>1].isFire() == true)) {
				return true;
			} 
			else {
				return false;
			}
	}

	public void select(int x, int y) {
		if (currSelected == null) {
			currSelected = pieces[x][y];
			currx = x;
			curry = y;
			highlight(x, y);
		}
		else if ((pieceAt(x, y) != null) && (currSelected.isFire() == pieceAt(x, y).isFire())) {
			currSelected = pieceAt(x, y);
			currx = x;
			curry = y;
			drawBoard(false);
			highlight(x, y);
		}
		else {
			if ((nAwayUp(currx, curry, x, y, 2) == true) || (nAwayUp(x, y, currx, curry, 2) == true)) {
				hascaptured = true;
			}
			currSelected.move(x, y);
			currx = x;
			curry = y;
			moved = true;
			canend = true;
			drawBoard(false);
		}
	}

	public void place(Piece p, int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return;
		} else if (p == null) {
			return;
		} else {
			String imagename = piecename(p);
				StdDrawPlus.picture(x + .5, y + .5, imagename, 1, 1);
			pieces[x][y] = p;
			if (p.isFire()) {
				numFire = numFire + 1;
			}
			else {
				numWater = numWater + 1;
			}

		}
	}

	public Piece remove(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			System.out.println("Coordinate is out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("There is no piece here.");
			return null;
		} else {
			Piece temp = pieceAt(x, y);
			if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else   StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			this.pieces[x][y] = null;
			if (temp.isFire() == true) {
				numFire = numFire - 1;
			} else {
				numWater = numWater - 1;
			}
			return temp;
		}
	}

	public boolean canEndTurn() {
		if (canend == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		turn = ((turn + 1) % 2);
		moved = false;
		hascaptured = false;
		currSelected = null;
		if (pieces[currx][curry] != null) {
			pieces[currx][curry].doneCapturing();
		}
		currx = 100;
		curry = 100;
		canend = false;
		drawBoard(false);

	}

	public String winner() {
		if (numWater == 0 && numFire == 0) {
			return "No one";
		}
		else if (numFire == 0) {
			return "Water";
		}
		else if (numWater == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}

	private void highlight(int x, int y) {
		String imagename = piecename(pieces[x][y]);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		StdDrawPlus.picture(x + .5, y + .5, imagename, 1, 1);
	}

	public static void main(String[] args) {
			currboard = new Board(false);
			StdDrawPlus.setXscale(0, 8);
	    	StdDrawPlus.setYscale(0, 8);
			while(currboard.winner() == null) {
            	if (StdDrawPlus.mousePressed()) {
                	double x = StdDrawPlus.mouseX();
                	double y = StdDrawPlus.mouseY();
                	if (x < 8 && x > -1 && y < 8 && y > -1) {
	                	if (currboard.canSelect((int) x, (int) y)) {
	                		currboard.select((int) x, (int) y);
	                	}
                	}
            	}
            	if (StdDrawPlus.isSpacePressed()) {
            		if (currboard.canEndTurn()) {
            			currboard.endTurn();
            		}
            	}            
            	StdDrawPlus.show(10);
    		}
    		currboard.winner();
    }
}












