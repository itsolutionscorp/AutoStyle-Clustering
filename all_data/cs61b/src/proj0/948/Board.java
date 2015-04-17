
public class Board {
	
	private Piece emptyPiece = new Piece(false, null, 0, 0, null);
	private static boolean shouldBeEmpty;
	private static final int N = 8;
	private int[] locationCoordinates = new int[2];
	private Piece[][] boardPieces = new Piece[8][8];
		
	/** Calls the private method to make the boards! */
	public Board(boolean shouldBeEmpty) {
		Board.shouldBeEmpty = shouldBeEmpty;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		makeBoard(N);
	}
	
	/** Real construction of the board. */
	private void makeBoard(int N) {
		for (int x = 0; x < N; x += 1) {
			for (int y = 0; y < N; y += 1) {
				if ((x + y) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
			}
		 }
		 if (shouldBeEmpty == false) {
	         for (int a = 0; a < N; a += 1) {
	        	 for (int b = 0; b < N; b += 1) {
	        		 if ((a % 2 == 0) && (b == 0)) {
	        			 Piece firePawn = new Piece(true, this, a, b, "pawn");
	                     this.place(firePawn, a, b);
	                 }
	        		 if ((a % 2 != 0) && (b == 1)) {
	        			 Piece fireShield = new Piece(true, this, a, b, "shield");
	                     this.place(fireShield, a, b);
	                 }
	        		 if ((a % 2 == 0) && (b == 2)) {
	        			 Piece fireBomb = new Piece(true, this, a, b, "bomb");
	                     this.place(fireBomb, a, b);
	                 }
	        		 if ((a % 2 != 0) && (b == 5)) {
	        			 Piece waterBomb = new Piece(false, this, a, b, "bomb");
	                     this.place(waterBomb, a, b);
	                 }
	        		 if ((a % 2 == 0) && (b == 6)) {
	        			 Piece waterShield = new Piece(false, this, a, b, "shield");
	                     this.place(waterShield, a, b);
	                 }
	        		 if ((a % 2 != 0) && (b == 7)) {
	        			 Piece waterPawn = new Piece(false, this, a, b, "pawn");
	                     this.place(waterPawn, a, b);
	                 }
	        	 }
	         }
		 }
	}
	

	/** taken from http://stackoverflow.com/questions/23069740/check-if-value-exists-in-a-multidimensional-array-java */
	private boolean checkValue(Piece piece) {
		for (int a = 0; a < N; a += 1) {
			for (int b = 0; b < N; b += 1) {
				if (this.boardPieces[a][b] == piece) {
					locationCoordinates[0] = a;
					locationCoordinates[1] = b;
					return true;
				}
			}
		}
		return false;
	}
	
	//unused useless method
	//private void drawPiece(Piece piece) {
	//	if (piece.isKing() == true) {
	//		if (piece.isFire() == true) {
	//			if (piece.isShield() == true) {
	//				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
	//			}
	//		}
	//	}
	//}
	
	
	/** While x and y are in bounds, return piece at location x, y.
	 *  Else, return piece (null?) 
	 */
	public Piece pieceAt(int x, int y) {
		while (x < N && y < N) {
			return this.boardPieces[x][y];
		}
		return this.boardPieces[x][y];
	}
	
	
	public boolean canSelect(int x, int y) {
		return true;
	}
	
	
	/** Checks for several conditions.
	 *  1. Is the piece a king?
	 *  2. Is the piece a fire or water piece?
	 *  3. Is the piece moving normally, or moving to capture?
	 *  4. Is there a piece where the piece wants to move?
	 *  5. Is there a piece to move over in capture mode? Is it the opponents'?
	 *  6. Is the piece moving up if fire / down if water?
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		/** Not a King */
		if (pieceAt(xi, yi).isKing() == false) {
			/** Fire pieces */
			if (pieceAt(xi, yi).isFire() == true) {
				if (pieceAt(xf, yf) == null) {
					/** Regular movement */
					if (Math.abs(xi - xf) + Math.abs(yi - yf) == 2) {
						if (yf - yi == 1) {
							return true;
						}
					}
				}
				else if (pieceAt(xf, yf) == null) {
					/** Capture movement */
					if (Math.abs(xi - xf) + Math.abs(yi - yf) == 4) {
						if (yf - yi == 2) {
							if (pieceAt(Math.abs(xi - 1), Math.abs(yi - 1)) != null) {
								/** Confirm opponent piece */
								if (pieceAt(Math.abs(xi - 1), Math.abs(yi - 1)).isFire() == false) {
									return true;
								}
							}
						}
					}
				}
			}
			/** Water pieces */
			else if (pieceAt(xi, yi).isFire() == false) {
				if (pieceAt(xf, yf) == null) {
					/** Regular movement */
					if (Math.abs(xi - xf) + Math.abs(yi - yf) == 2) {
						if (yi - yf == 1) {
							return true;
						}
					}
				}
				else if (pieceAt(xf, yf) == null) {
					if (Math.abs(xi - xf) + Math.abs(yi - yf) == 4) {
						if (yi - yf == 2) {
							if (pieceAt(Math.abs(xi - 1), Math.abs(yi - 1)) != null) {
								/** Confirm opponent piece */
								if (pieceAt(Math.abs(xi - 1), Math.abs(yi - 1)).isFire() == true) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		/** Yes a King */
		else if (pieceAt(xi, yi).isKing() == true) {
			if (pieceAt(xf, yf) == null) {
				if (Math.abs(xi - xf) + Math.abs(yi - yf) == 2) {
					return true;
				}
			}
			else if (pieceAt(xf, yf) == null) {
				if (Math.abs(xi - xf) + Math.abs(yi - yf) == 4) {
					if (pieceAt(Math.abs(xi - 1), Math.abs(yi - 1)) != null) {
						return true;
					}
				}
			}
		}
		/** All else */
		return false;
	} 
	
	
	/** Select piece at x, y if piece exists. NEED TO CHECK IF PIECE IS THERE? */
	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			place(boardPieces[x][y], x, y);
            return;
		}
		return;
	}
	
	
	/** If x or y is out of bounds, return.
	 *  If p is emptyPiece (null?), return.
	 *  If p exists elsewhere on the board, remove it and place it at x, y.
	 *  Else, place p at x, y.
	 */
	public void place(Piece p, int x, int y) {
		if (x > N || 0 > x || y > N || 0 > y) {
			return;
		}
		else if (p == emptyPiece) {
			return;
		}
		else if (checkValue(p) == true) {
			remove(locationCoordinates[0], locationCoordinates[1]);
			this.boardPieces[x][y] = p;
			if (this.boardPieces[x][y].isFire() == true) {
				if (this.boardPieces[x][y].isShield() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				}
				else if (this.boardPieces[x][y].isBomb() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
				}
			}
			else if (this.boardPieces[x][y].isFire() == false) {
				if (this.boardPieces[x][y].isShield() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				}
				else if (this.boardPieces[x][y].isBomb() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
				}
			}
		}
		else {
			this.boardPieces[x][y] = p;
			if (this.boardPieces[x][y].isFire() == true) {
				if (this.boardPieces[x][y].isShield() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				}
				else if (this.boardPieces[x][y].isBomb() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
				}
			}
			else if (this.boardPieces[x][y].isFire() == false) {
				if (this.boardPieces[x][y].isShield() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				}
				else if (this.boardPieces[x][y].isBomb() == true) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
				}
			}
		}
	}
	
	
	/** If x or y is out of bounds, return emptyPiece.
	 *  If there is no piece at x, y return null.
	 *  If there is a piece at x, y return piece and set space to null.
	 *  Else, return (space --> could be null).
	 */
	public Piece remove(int x, int y) {
		if (x > N || 0 > x || y > N || 0 > y) {
			System.out.println("The input is out of bounds.");
			return emptyPiece;
		}
		else if (this.boardPieces[x][y] == null) {
			System.out.println("There is no piece at this location.");
			return this.boardPieces[x][y];
		}
		else if (this.boardPieces[x][y] != emptyPiece) {
			Piece[] tempPiece = new Piece[1];
			tempPiece[0] = this.boardPieces[x][y];
			this.boardPieces[x][y] = null;
			if ((x + y) % 2 == 0) {
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			}
			else {
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			}
			return tempPiece[0];
		}
		return this.boardPieces[x][y];
	}
	
	
	public boolean canEndTurn() {
		return true;
	}
	
	public void endTurn() {
		
	}
	
	public String winner() {
		return "o";
	}
	
	public static void main(String[] args) {
		
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		
		Board testBoard = new Board(false);
		//	if (StdDrawPlus.mousePressed()) {
        //        double a = StdDrawPlus.mouseX();
        //        double b = StdDrawPlus.mouseY();
        //        pieces[(int) a][(int) b] = true;
        //    }            
        StdDrawPlus.show(100);
        }
    }
