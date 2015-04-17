public class Board {
	private static final int N = 8;
	private static Piece[][] pieces;

	private boolean fireTurn;
	private Piece selected;
	private boolean moved;
	private boolean captured;
	private int posX = 99;
	private int posY = 99;

	public static void main(String[] args) {
		Board board = new Board(false);
		
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        board.drawBoard();
        board.drawPieces();
        
        while (true) {
        	if (StdDrawPlus.mousePressed()) {
        		int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (board.canSelect(x, y)) {
					board.select(x, y);
					board.drawBoard();
		            board.drawPieces();
				}
        	}
        	
        	if (StdDrawPlus.isSpacePressed()) {
        		System.out.println("pressing");
        		if (board.canEndTurn()) {
        			System.out.println("ending");
        			board.endTurn();
        		}
        	}

        	StdDrawPlus.show(30);
        }
	}
	
	private void drawBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
			}
		}
	}
	
	private void drawPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					Piece piece = pieces[i][j];
					if (piece.isKing()) {
						if (piece.isShield()) {
							if (piece.isFire()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/shield-fire-crowned.png");
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/shield-water-crowned.png");
							}
						} else if (piece.isBomb()) {
							if (piece.isFire()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/bomb-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/bomb-water-crowned.png", 1, 1);
							}
						} else {
							if (piece.isFire()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/pawn-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/pawn-water-crowned.png", 1, 1);
							}
						}
					} else {
						if (piece.isBomb()) {
							if (piece.isFire()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/bomb-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/bomb-water.png", 1, 1);
							}
						}
						if (piece.isShield()) {
							if (piece.isFire()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/shield-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/shield-water.png", 1, 1);
							}
						}
						if (!piece.isShield() && !piece.isBomb()
								&& !piece.isKing()) {
							if (piece.isFire()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/pawn-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5,
										"img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Returns whether or not (x, y) is in bounds.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isInBounds(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}

	/**
	 * The constructor for Board. If shouldBeEmpty is true, initializes an empty
	 * Board. Otherwise, initializes a Board with the default configuration.
	 * 
	 * @param shouldBeEmpty
	 */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		fireTurn = true;
		selected = null;
		moved = false;
		captured = false;

		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j == 0) {
						if (i % 2 == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
						}
					}
					if (j == 2) {
						if (i % 2 == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
						}
					}
					if (j == 1) {
						if (i % 2 == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield");
						}
					}
					if (j == 6) {
						if (i % 2 == 0) {
							pieces[i][j] = new Piece(false, this, i, j,
									"shield");
						}
					}
					if (j == 7) {
						if (i % 2 == 1) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
					if (j == 5) {
						if (i % 2 == 1) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
						}
					}
				}
			}
		}
	}

	/**
	 * Gets the piece at position (x, y) on the board, or null if there is no
	 * piece. If (x, y) are out of bounds, returns null.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece pieceAt(int x, int y) {
		if (isInBounds(x, y)) {
			if (pieces[x][y] != null) {
				return pieces[x][y];
			}
		}
		return null;
	}

	/**
	 * Returns true if the square at (x, y) can be selected.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */





// This part is partially inspirited by someone else//

	public boolean canSelect(int x, int y) {
		Piece piece = pieceAt(x, y);

		System.out.println(fireTurn);

		if (selected == null && piece != null) {
			if (fireTurn == piece.isFire()) {
				System.out.println(1);
				return true;			
			}
		}
		if (selected != null) {
			// potential problem here//
			if (moved == false && piece != null) {
				System.out.println(2);
				return true;
			}
		}
		if (selected == null && piece == null) {
			System.out.println(3);
			return false;
		}
		if (selected != null && piece == null) {
			if (x > (N - 1) || y > (N - 1)) {
				System.out.println(4);
				return false;
			}
			//turn to here    canmoveonestep(pieces,x,y,posx,posy)
			// canmovetwostep 
			if (moved == false) {
				if (selected.isKing()) {
					if (x == posX + 1 || x == posX - 1) {
						if (y == posY + 1 || y == posY- 1) {
							System.out.println(5);
							return true;
						}
					}
				} else {
					if (selected.isFire()) {
						if (y < posY) {
							return false;
						} else {
							if (x == posX + 1 || x == posX - 1) {
								if (y == posY + 1) {
									return true;
								}
							}
						}
					} else {
						if (y > posY) {
							return false;
						} else {
							if (x == posX + 1 || x == posX - 1) {
								if (y == posY - 1) {
									return true;
								}
							}
						}
					}
				}
			}
			if (x == posX+ 2 || x == posX- 2) {
				if (y == posY+ 2 || y == posY- 2) {
					if (moved == false || selected.hasCaptured()) {
						Piece pieceInTheMid = pieceAt((posX+ x) / 2, (posY + y) / 2);
						if (pieceInTheMid != null) {
							if (pieceInTheMid.isFire() != selected.isFire())
								System.out.println(6);
								return true;
						}
					}
				}
			}
		}
		System.out.println(7);
		return false;
	}



	/**
	 * Selects the square at (x, y).
	 * 
	 * @param x
	 * @param y
	 */
	public void select(int x, int y) {
		Piece piece = pieceAt(x, y);
		if (piece == null) {
			remove(posX, posY);
			pieces[x][y] = selected;
			selected.move(x, y);
			posX = x;
			posY = y;
			moved = true;
		} else {
			posX = x;
			posY = y;
			selected = piece;
		}
	}

	/**
	 * Places p at (x, y).
	 * 
	 * @param p
	 * @param x
	 * @param y
	 */
	public void place(Piece p, int x, int y) {
		if (isInBounds(x, y)) {
			pieces[x][y] = p;
		}
	}

	/**
	 * Executes a remove. Returns the piece that was removed.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece remove(int x, int y) {
		if (!isInBounds(x, y)) {
			System.out.println("Error: Out Of Bounds");
			return null;
		} else {
			Piece p = pieceAt(x, y);
			if (p == null) {
				System.out.println("Error: No Piece Available");
				return null;
			} else {
				pieces[x][y] = null;
				return p;
			}
		}
	}

	/**
	 * Returns whether or not the the current player can end their turn
	 * 
	 * @return
	 */
	public boolean canEndTurn() {
		System.out.println("turn ended");
		return (moved || captured);
	}

	/**
	 * Called at the end of each turn. Handles switching of players and anything
	 * else that should happen at the end of a turn
	 */
	public void endTurn() {
		System.out.println("endedededed");
		selected = null;
		moved = false;
		captured = false;
		fireTurn = !fireTurn;
	}

	/**
	 * Returns the winner of the game: "Fire", "Water", "No one", or null
	 * 
	 * @return
	 */
	public String winner() {
		int f = 0;
		int w = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						f++;
					} else {
						w++;
					}
				}
			}
		}

		if (f == 0) {
			if (w == 0) {
				return "No one";
			} else {
				return "Water";
			}
		} else {
			if (w == 0) {
				return "Fire";
			} else {
				return null;
			}
		}
	}
}
