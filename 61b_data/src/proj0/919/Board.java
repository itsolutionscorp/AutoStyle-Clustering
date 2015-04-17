public class Board {
	
	// Constructor for Board
	private int bsize = 8;
	private Piece[][] pieces = new Piece[bsize][bsize];
	private int player = 0;
	private Piece selpiece = null;
	private String winnerNote = null;
	private boolean moved = false;
	private boolean captured = false;
	private int[] selSquare = new int[2];

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			for (int yy = 0; yy < bsize; yy++) {
				for (int xx = 0; xx < bsize; xx = xx+2) {
					if (yy == 0) {
						pieces[xx][yy] = new Piece(true, this, xx, yy, "pawn");
					} else if (yy == 1) {
						pieces[xx+1][yy] = new Piece(true, this, xx+1, yy, "shield");
					} else if (yy == 2) {
						pieces[xx][yy] = new Piece(true, this, xx, yy, "bomb");
					} else if (yy == bsize-3) {
						pieces[xx+1][yy] = new Piece(false, this, xx+1, yy, "bomb");
					} else if (yy == bsize-2) {
						pieces[xx][yy] = new Piece(false, this, xx, yy, "shield");
					} else if (yy == bsize-1) {
						pieces[xx+1][yy] = new Piece(false, this, xx+1, yy, "pawn");
					}
				}
			}
		}
		selSquare[0] = -1;
		selSquare[1] = -1;
	}
	

	private void boardDrawer() {
	    StdDrawPlus.setXscale(0, bsize);
        StdDrawPlus.setYscale(0, bsize);
        for (int yy = 0; yy < bsize; yy++) {
			for (int xx = 0; xx < bsize; xx++) {
				if ((selSquare[0] == xx) & (selSquare[1] == yy)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(xx + 0.5, yy + 0.5, 0.5);
				} else if ((xx + yy) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(xx + 0.5, yy + 0.5, 0.5);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(xx + 0.5, yy + 0.5, 0.5);
				}
				if (this.pieces[xx][yy] != null) {
					if (this.pieces[xx][yy].isFire() == true) {
						if (this.pieces[xx][yy].isKing() == true) {
							if (this.pieces[xx][yy].isBomb() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/bomb-fire-crowned.png", 1, 1);	
							} else if (this.pieces[xx][yy].isShield() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}
						} else {
							if (this.pieces[xx][yy].isBomb() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/bomb-fire.png", 1, 1);	
							} else if (this.pieces[xx][yy].isShield() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/shield-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/pawn-fire.png", 1, 1);
							}
						}
					} else {
						if (this.pieces[xx][yy].isKing() == true) {
							if (this.pieces[xx][yy].isBomb() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/bomb-water-crowned.png", 1, 1);	
							} else if (this.pieces[xx][yy].isShield() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/pawn-water-crowned.png", 1, 1);
							}
						} else {
							if (this.pieces[xx][yy].isBomb() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/bomb-water.png", 1, 1);	
							} else if (this.pieces[xx][yy].isShield() == true) {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/shield-water.png", 1, 1);
							} else {
								StdDrawPlus.picture(xx + 0.5, yy + 0.5, "img/pawn-water.png", 1, 1);
							}

						}
					}
				}
			}
		}
	}

	//Gets piece at position (x,y)
	public Piece pieceAt(int x, int y) {
		if ((x>-1) & (x<bsize) & (y>-1) & (y<bsize)) {
			if (this.pieces[x][y] != null) {
				return this.pieces[x][y];
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if ((x >= 0) & (x < bsize) & (y >= 0) & (y < bsize)) {
			if (moved == false) {
				if ((selpiece == null) & (this.pieces[x][y] != null)) {
					if (this.pieces[x][y].side() == player) {
						return true;
					} else {
						return false;
					}
				} else if ((this.pieces[x][y] == null) & (selpiece != null)) {
					return this.validMove(selSquare[0], selSquare[1], x, y);
				} else if (this.pieces[x][y] != null) {
					if (this.pieces[x][y].side() == player) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if (selpiece.hasCaptured() == true) {
					if (this.pieces[x][y] == null) {
						return this.validMove(selSquare[0], selSquare[1], x, y);
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (this.pieces[xf][yf] != null) {
			return false;
		} else {
			if ((selpiece.isFire() == true) & (selpiece.hasCaptured() == true)) {
				if ((yi + 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf))) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return false;
						} else {
							return true;
						}
					} else {
						return false;
					}
				} else if ((yi - 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf)) 
					& (selpiece.isKing() == true)) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return false;
						} else {
							return true;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if ((selpiece.isFire() == true) & (selpiece.hasCaptured() == false)) {
				if ((yi + 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf))) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return false;
						} else {
							return true;
						}
					} else {
						return false;
					}
				} else if ((yi - 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf)) 
					& (selpiece.isKing() == true)) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return false;
						} else {
							return true;
						}
					} else {
						return false;
					}
				} else if ((yi + 1 == yf) & ((xi - 1 == xf) | (xi + 1 == xf))) {
						return true;
				} else if ((yi - 1 == yf) & ((xi - 1 == xf) | (xi + 1 == xf)) 
					& (selpiece.isKing() == true)) {
						return true;
				} else {
					return false;
				}
			} else if ((selpiece.isFire() == false) & (selpiece.hasCaptured() == true)) {
				if ((yi - 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf))) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if ((yi + 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf)) 
					& (selpiece.isKing() == true)) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if ((selpiece.isFire() == false) & (selpiece.hasCaptured() == false)) {
				if ((yi - 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf))) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if ((yi + 2 == yf) & ((xi - 2 == xf) | (xi + 2 == xf)) 
					& (selpiece.isKing() == true)) {
					if (this.pieces[(xi+xf)/2][(yi+yf)/2] != null) {
						if (this.pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if ((yi - 1 == yf) & ((xi - 1 == xf) | (xi + 1 == xf))) {
						return true;
				} else if ((yi + 1 == yf) & ((xi - 1 == xf) | (xi + 1 == xf)) 
					& (selpiece.isKing() == true)) {
						return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public void select(int x, int y) {
		if (selpiece == null) {
				selpiece = this.pieces[x][y];
				selSquare[0] = x;
				selSquare[1] = y;
		} else {
			if (this.pieces[x][y] != null) {
				selpiece = this.pieces[x][y];
				selSquare[0] = x;
				selSquare[1] = y;
			} else {
				selpiece.move(x, y);
				this.captured = selpiece.hasCaptured();
				this.moved = true;
				selSquare[0] = x;
				selSquare[1] = y;
			}
		}
		
	}

	public void place(Piece p, int x, int y) {
		if ((x < bsize) & (y < bsize)) {
			this.pieces[x][y] = p;
		}
	}


	public Piece remove(int x, int y) {
		if ((x >= bsize) | (y >= bsize)) {
			System.out.println("Out of bounds - no piece to be removed");
			return null;
		} else if (this.pieces[x][y] == null) {
			System.out.println("This is an empty square = no piece to be removed");
			return null;
		} else {
			Piece p = this.pieces[x][y];
			this.pieces[x][y] = null;
			return p;
		}
	}

	public boolean canEndTurn() {
		if ((moved == true) | (captured == true)) {
			return true;			
		} else {
			return false;
		}
	}

	public void endTurn() {
		if (this.canEndTurn() == true) {
			if (player == 0) {
				player = 1;
			} else {
				player = 0;
			}
			selpiece.doneCapturing();
			captured = false;
			moved = false;
			selpiece = null;
			selSquare[0] = -1;
			selSquare[1] = -1;
		}
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int yy = 0; yy < bsize; yy++) {
			for (int xx = 0; xx < bsize; xx++) {
				if (this.pieces[xx][yy] != null) {
					if (this.pieces[xx][yy].isFire() == true) {
						firePieces = firePieces + 1;
					} else {
						waterPieces = waterPieces + 1;
					}
				}
			}
		}

		if (firePieces == 0) {
			if (waterPieces == 0) {
				return "No One";
			} else {
				return "Water";
			}
		} else if (waterPieces == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	//starts GUI version of the game
	public static void main(String[] args) {
		double x1;
		double y1;
		int x;
		int y;
		Board goodBoard = new Board(false);
		goodBoard.boardDrawer();
		while (goodBoard.winnerNote == null) {
			if (StdDrawPlus.mousePressed() == true) {
				StdDrawPlus.show(10);
				x1 = StdDrawPlus.mouseX();
				y1 = StdDrawPlus.mouseY();
				x = (int) x1;
				y = (int) y1;
				if (goodBoard.canSelect(x, y) == true) {
					goodBoard.select(x, y);
				}
				goodBoard.boardDrawer();
				
			}

			if (StdDrawPlus.isSpacePressed() == true) {
				if (goodBoard.canEndTurn() == true) {
					goodBoard.endTurn();
					goodBoard.boardDrawer();
					goodBoard.winnerNote = goodBoard.winner();
				}
			}
		}
		System.out.println(goodBoard.winnerNote);
	}
}