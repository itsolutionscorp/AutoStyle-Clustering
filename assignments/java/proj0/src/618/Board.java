public class Board {

	private static Piece[][] pieces;
	private static int N;
	private static final String firePlayer = "Fire";
	private static final String waterPlayer = "Water";
	private int numberOfFirePieces;
	private int numberOfWaterPieces;
	private int currentPlayer;
	private boolean hasSelected;
	private boolean hasMoved;
	private Piece selected;
	private static int selectedX;
	private static int selectedY;


	public Board(boolean shouldBeEmpty) {
		N = 8;
        pieces = new Piece[N][N];
        numberOfWaterPieces = 0;
        numberOfFirePieces = 0;
        refresh();
		if (!shouldBeEmpty) {
	        int j = 0;
	        boolean isFire = true;
	        for (int i = 0; i < N; i += 2) {
	        	place(new Piece(isFire, this, i, j, "pawn"), i, j);
	        }
	        j = 1;
	        for (int i = 1; i < N; i += 2) {
	        	place(new Piece(isFire, this, i, j, "shield"), i, j);
	        }
	        j = 2;
	        for (int i = 0; i < N; i += 2) {
	        	place(new Piece(isFire, this, i, j, "bomb"), i, j);
	        }
	        j = 5;
	        isFire = false;
	        for (int i = 1; i < N; i += 2) {
	        	place(new Piece(isFire, this, i, j, "bomb"), i, j);
	        }
	        j = 6;
	        for (int i = 0; i < N; i += 2) {
	        	place(new Piece(isFire, this, i, j, "shield"), i, j);
	        }
	        j = 7;
	        for (int i = 1; i < N; i += 2) {
	        	place(new Piece(isFire, this, i, j, "pawn"), i, j);
	        }
	    }
	    currentPlayer = 0;
	}

	private void refresh() {
		hasSelected = false;
        hasMoved = false;
        selected= null;
        selectedX = -1;
        selectedY = -1;
	}

	public Piece pieceAt(int x, int y) {
		if (isInBounds(x, y)) {
			return pieces[x][y];
		} else {
			return null;
		}
	}

	private boolean isInBounds(int x, int y) {
		return ((x >= 0) && (x < N) && (y >= 0) && (y < N));
	}

	public boolean canSelect(int x, int y) {
		Piece pieceAtSquare = pieceAt(x, y);
		if (pieceAtSquare == null) {
			if (selected != null && !hasMoved) {
				if (validMove(selectedX, selectedY, x, y, selected)) {
					return true;
				} else {
					return false;
				}
			} else if (selected != null && selected.hasCaptured()) {
				if (validMove(selectedX, selectedY, x, y, selected)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} else {
			if (selected == null || !hasMoved) {
				if (pieceAtSquare.side() == currentPlayer)
					return true;
			}
			return false;
		}


	}
	private boolean validMove(int xi, int yi, int xf, int yf, Piece p) {
		if(isInBounds(xf, yf) && isDiagonal(xi, yi, xf, yf)) {
			if (abs(xf - xi) == 1 && abs(yf - yi) == 1) {
				if (pieceAt(xf, yf) == null && !p.hasCaptured()) {
					if (selected.side() == 1 && !selected.isKing()) {
						if (yf > yi) {
							return false;
						}
					} else if (selected.side() == 0 && !selected.isKing()) {
						if (yf < yi) {
							return false;
						}
					}
					return true;
				}
				return false;
			} else if (abs(xf - xi) == 2 && abs(yf - yi) == 2) {
				Piece middle = pieceAt((xi + ((xf - xi) / 2)), (yi + ((yf - yi) / 2)));
				if (middle != null && pieceAt(xf, yf) == null) {
					if (p.isFire() != middle.isFire()) {
						if (selected.side() == 1 && !selected.isKing()) {
							if (yf > yi) {
								return false;
							}
						} else if (selected.side() == 0 && !selected.isKing()) {
							if (yf < yi) {
								return false;
							}
						}
						return true;
					}
				}
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean isDiagonal(int xi, int yi, int xf, int yf) {
		if (abs(xf - xi) == abs(yf - yi)) {
			return true;
		} else {
			return false;
		}
	}

	private int abs(int x) {
		if (x < 0) {
			return -x;
		} else {
			return x;
		}
	}

	public void select(int x, int y) {
		if (selected == null || pieceAt(x, y) != null) {
			selected = pieceAt(x, y);
		} else {
			selected.move(x, y);
			hasMoved = true;
		}
		selectedY = y;
		selectedX = x;
	}

	public void place(Piece p, int x, int y) {
		if (p != null && isInBounds(x, y)) {
			if (pieces[x][y] != null) {
				remove(x, y);
			}
			pieces[x][y] = p;
			if (p.isFire()) {
				numberOfFirePieces++;
			} else {
				numberOfWaterPieces++;
			}
		}
	}

	public Piece remove(int x, int y) {
		if (!isInBounds(x, y)) {
			System.out.println("remove(x, y) Error OutOfBounds: " + x + " or " + y + " is not less than " + N + ".");
			return null;
		} else {
			Piece removed = pieces[x][y];
			if (removed == null) {
				System.out.println("remove(x, y) Error EmptySquare");
			} else {
				if (removed.isFire()) {
					numberOfFirePieces--;
				} else {
					numberOfWaterPieces--;
				}
				pieces[x][y] = null;
			}
			return removed;
		}
	}


	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		} else  if (selected != null && selected.hasCaptured()) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		if (currentPlayer == 1) {
			currentPlayer = 0;
		} else {
			currentPlayer = 1;
		}
		selected.doneCapturing();
		refresh();
	}

	public String winner() {
		if (numberOfWaterPieces == 0 && numberOfFirePieces == 0) {
			return "No one";
		} else if (numberOfFirePieces == 0) {
			return waterPlayer;
		} else if (numberOfWaterPieces == 0) {
			return firePlayer;
		} else {
			return null;
		}
	}


	private static void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == selectedX && j == selectedY) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else { 
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece holder = pieces[i][j];
                if(holder != null) {
	                if (holder.isFire()) {
	                	if (holder.isBomb()) {
	                		if (holder.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}
	                	} else if (holder.isShield()) {
	                		if (holder.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}
	                	} else {
	                		if (holder.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
	                	}
	                } else {
	                	if (holder.isBomb()) {
	                		if (holder.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}
	                	} else if (holder.isShield()) {
	                		if (holder.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                		}
	                	} else {
	                		if (holder.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
	                	}
	                }
	            }
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board(false);
        drawBoard(N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int)x, (int)y)) {
                	board.select((int)x, (int)y);
                }
            } else if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            	}
            }
            if (board.winner() != null) {
            	return;
            }     
            StdDrawPlus.show(100);
        }
    }
}