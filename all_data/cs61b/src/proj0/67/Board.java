public class Board {
	public static void main(String args[]) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        // boolean[][] pieces = new boolean[N][N];
        Board b = new Board(false);
        // b.drawBoard(N);   

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            // if (StdDrawPlus.mousePressed()) {
            //     double x = StdDrawPlus.mouseX();
            //     double y = StdDrawPlus.mouseY();
            //     pieces[(int) x][(int) y] = true;
            // }            
            StdDrawPlus.show(100);
        }

	}

	// private boolean[][] pieces;
	private Piece[][] storedPieces = new Piece[8][8];

	private void drawBoard(int N) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (i%2 == 1 && j == 7) {
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
               	}
               	else if (i%2 == 0 && j == 6) {
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
               	}
               	else if (i%2 == 1 && j == 5) {
               		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
               	}
               	else if (i%2 == 0 && j == 0) {
               		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
               	}
               	else if (i%2 == 1 && j == 1) {
               		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
               	}
               	else if (i%2 == 0 && j == 2) {
               		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
               	}
            }
        }
    }

    private boolean emptyBoard;

	public Board(boolean shouldBeEmpty) {
		emptyBoard = shouldBeEmpty;
		if (emptyBoard == true) {
			/* initialize empty Board */
			boolean[][] boardEmpty = new boolean[8][8];
			storedPieces = new Piece[8][8];
        } else {
			/* initializes Board with default configuration */
			// drawBoard(8);
			boolean[][] defaultBoard = new boolean[8][8];
			storedPieces = new Piece[8][8];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
                	if (i%2 == 1 && j == 7) {
                		storedPieces[i][j] = new Piece(false, this, i, j, "pawn");
               		}
               		else if (i%2 == 0 && j == 6) {
                		storedPieces[i][j] = new Piece(false, this, i, j, "shield");
               		}
               		else if (i%2 == 1 && j == 5) {
               			storedPieces[i][j] = new Piece(false, this, i, j, "bomb");
               		}
               		else if (i%2 == 0 && j == 0) {
               			storedPieces[i][j] = new Piece(true, this, i, j, "pawn");
               		}
               		else if (i%2 == 1 && j == 1) {
               			storedPieces[i][j] = new Piece(true, this, i, j, "shield");	
               		}
               		else if (i%2 == 0 && j == 2) {
               			storedPieces[i][j] = new Piece(true, this, i, j, "bomb");
               		}
               	}
            }
		}
	} 

	public Piece pieceAt(int x, int y) {
		//THIS WORKS. 
		if (x > 8 || x < 0 || y > 8 || y < 0) {
			return null;
		}
		return storedPieces[x][y];
	}

	private boolean validMove(int xi, int yi, int xf, int yf) { //should be private but made public temporarily for testing
		//THIS WORKS SO FAR MAY NEED MORE TESTS!!!
		if (pieceAt(xi, yi).isKing() && pieceAt(xi, yi).isFire() && pieceAt(xf, yf) == null) {
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) {
				return true;
			} else if (pieceAt((xi+xf)/2, (yi+yf)/2) != null && pieceAt((xi+xf)/2, (yi+yf)/2).isFire() == false && Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
				return true;
			}
		} else if (pieceAt(xi, yi).isKing() && pieceAt(xi, yi).isFire() == false && pieceAt(xf, yf) == null) {
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) {
				return true;
			} else if (pieceAt((xi+xf)/2, (yi+yf)/2) != null && pieceAt((xi+xf)/2, (yi+yf)/2).isFire() && Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
				return true;
			}
		} else if (pieceAt(xi, yi).isKing() == false && pieceAt(xi, yi).isFire() && pieceAt(xf, yf) == null) {
			if ((xi - xf == -1 && yi - yf == -1) || (xi - xf == 1 && yi - yf == -1)) {
				return true;
			} else if (pieceAt((xi+xf)/2, (yi+yf)/2) != null && pieceAt((xi+xf)/2, (yi+yf)/2).isFire() == false) {
				if (xi - xf == -2 && yi - yf == -2) {
					return true;
				} else if (xi - xf == 2 && yi - yf == -2) {
					return true;
				}
			}
		} else if (pieceAt(xi, yi).isKing() == false && pieceAt(xi, yi).isFire() == false && pieceAt(xf, yf) == null) {
			if ((xi - xf == -1 && yi - yf == 1) || (xi - xf == 1 && yi - yf == 1)) {
				return true;
			} else if (pieceAt((xi+xf)/2, (yi+yf)/2) != null && pieceAt((xi+xf)/2, (yi+yf)/2).isFire()) {
				if (xi - xf == 2 && yi - yf == 2) {
					return true;
				} else if (xi - xf == -2 && yi - yf == 2) {
					return true;
				}
			}
		}
		return false;
	} 

	private boolean hasMoved = false;
	private boolean hasSelected = false; //should be private

	public boolean canSelect(int x, int y) {
		//STILL WORKING!!!!

		if (pieceAt(x, y) != null) {
			if (hasSelected == false || (hasSelected == true && hasMoved == false)) {
				return true;
			}
		}
		else if (pieceAt(x, y) == null) {
			if (hasSelected == true && hasMoved == false) {
				if (validMove(recentlySelectedX, recentlySelectedY, x, y)) {
					return true;
				}
			}
			else if (hasSelected == true && pieceAt(recentlySelectedX, recentlySelectedY).hasCaptured() && validMove(recentlySelectedX, recentlySelectedY, x, y)) {
				return true;
			}
		}
		return false;
	}

	private int recentlySelectedX;
	private int recentlySelectedY;

	private boolean moreCapture = true;

	public void select(int x, int y) {
		//STILL WORKING!!!!
		if (pieceAt(x, y) != null) {
			recentlySelectedX = x; 
			recentlySelectedY = y;
			Piece selectedPiece = pieceAt(recentlySelectedX, recentlySelectedY);
			//selectedPiece = pieceAt(x, y);
			hasSelected = true;
			//prep piece for movement
		} else if (pieceAt(x, y) == null && moreCapture == true) {
			pieceAt(recentlySelectedX, recentlySelectedY).move(x, y);
			hasMoved = true;
			if (canCaptureMore(x, y) == false) {
				moreCapture = false;
			}
		}
	}

	private boolean canCaptureMore(int x, int y) {
		if (pieceAt(x, y) != null) {
			if (firePlayer && pieceAt(x, y).isKing() == false) {
				if (validMove(x, y, x+2, y+2) || validMove(x, y, x-2, y+2)) {
					return true;
				}
			}
			else if (waterPlayer && pieceAt(x, y).isKing() == false) {
				if (validMove(x, y, x-2, y-2) || validMove(x, y, x+2, y-2)) {
					return true;
				}
			} 
			else if (pieceAt(x, y).isKing() == true) {
				if (validMove(x, y, x+2, y+2) || validMove(x, y, x+2, y-2) || validMove(x, y, x-2, y+2) || validMove(x, y, x-2, y-2)) {
					return true;
				}
			}
		}
		return false;
	}

	public void place(Piece p, int x, int y) {
		//THIS WORKS.
		if ((x < 8 && y < 8) || p != null) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (pieceAt(i, j) == p) {
						storedPieces[i][j] = null;
					}
					storedPieces[x][y] = p;
				}
			}
		}
	}

	public Piece remove(int x, int y) {
		//THIS WORKS.
		if (x > 8 || x < 0 || y > 8 || y < 0) {
			System.out.println("Coordinates out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("There is no piece here.");
			return null;
		} else {
			int tempX = x;
			int tempY = y;
<<<<<<< HEAD
			// storedPieces[x][y] = null;
			//Piece originalPiece = pieceAt(tempX, tempY);
			Piece removedPiece = pieceAt(x, y);
			//originalPiece = null;
=======
			Piece removedPiece = pieceAt(x, y);
>>>>>>> ag/proj0
			storedPieces[tempX][tempY] = null;
			return removedPiece;
		}
	}


	public boolean canEndTurn() {
		if (hasMoved || pieceAt(recentlySelectedX, recentlySelectedY).hasCaptured()) {
			if (canCaptureMore(recentlySelectedX, recentlySelectedY) == false) {
				return true;
			}
		} else if (hasMoved || canCaptureMore(recentlySelectedX, recentlySelectedY) == false) {
			return true;
		}
		return false;
	}

	private boolean firePlayer = true;
	private boolean waterPlayer = false;

	private boolean canEndTurn = false;

	public void endTurn() {
		//if (StdDrawPlus.isSpacePressed() == true) { (do in main method)
		//if recently selected position piece is not null then call done capturing method
			if (firePlayer == true) {
				firePlayer = false;
				waterPlayer = true;
			} else {
				firePlayer = true;
				waterPlayer = false;
			}
			hasSelected = false;
			moreCapture = true;
			canEndTurn = false;
			// ??? Piece.doneCapturing();
			//ends current player's turn
			//switches players
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (storedPieces[i][j].isFire() == true) {
					firePieces += 1;
				} else {
					waterPieces += 1;
				}
			}
		}
		if (waterPieces == 0) {
			return "Fire";
		} else if (firePieces == 0) {
			return "Water";
		} else if (waterPieces == 0 && firePieces == 0) {
			return "No one";
		} else {
			return null;
		}
		//some piece has moved???
	}

}
