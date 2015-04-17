public class Board {

	private Piece[][] pieces;
	private static int N = 8;
	private boolean fireTurn = true;
	private boolean hasMoved = false;
	private boolean hasSelectedPiece = false;
	private Piece selectedPiece = null;
	private boolean hasCaptured = false;

	public Board(boolean shouldBeEmpty) {

        pieces = new Piece[N][N];

		if (!shouldBeEmpty) {
			//fill pieces up with pieces
			for (int x = 0; x < 8; x++) {
				if (x%2 == 0) {

					/* this was inspired by a friend, who kindly pointed out to me 
					*  that the board was actually set up very nicely in terms of
					*  a vertical pattern going across the board. 
					*/
					pieces[x][6] = new Piece(false, this, x, 6, "shield");
					pieces[x][0] = new Piece(true, this, x, 0, "pawn");
					pieces[x][2] = new Piece(true, this, x, 2, "bomb");
				} else {

					pieces[x][7] = new Piece(false, this, x, 7, "pawn");
					pieces[x][5] = new Piece(false, this, x, 5, "bomb");
					pieces[x][1] = new Piece(true, this, x, 1, "shield");
				}
			}
		}
	}


	public static void main(String[] args) {

		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board board = new Board(false);
        board.drawBoard(N);

		while(true) {
            // drawBoard(N);
            if (board.winner() != null) {
            	break;
            }

			if (StdDrawPlus.mousePressed()) {
				int x = (int) Math.floor(StdDrawPlus.mouseX());
        		int y = (int) Math.floor(StdDrawPlus.mouseY());
        		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
	        		if (board.canSelect(x, y)) {
	        			board.select(x, y);
	        			board.drawBoard(N);
	        			board.colorSelectedPiece();
	        		}
				}
        	}

        	if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
        		board.endTurn();
        		board.drawBoard(N);
        	}

            StdDrawPlus.show(100);
        }

        System.out.println(board.winner());
	}


	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawPiece(i, j);
        	}
		}
	}


	private void drawPiece(int i, int j) {
		if (pieces[i][j] != null) {
        	Piece tentative = pieces[i][j];
     		// check what type the piece is

     		if (tentative.isBomb() && tentative.isFire()){
     			if (!tentative.isKing()) {
     				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
     			} else {
     				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
     			}


     		} else if (tentative.isBomb() && !tentative.isFire()) {
     			if (!tentative.isKing()) {
     				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
     			} else {
     				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
     			}

     		} else if (tentative.isShield() && tentative.isFire()) {
     			if (!tentative.isKing()) {
     				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
     			} else {
     				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
     			}

     		} else if (tentative.isShield() && !tentative.isFire()) {
     			if (!tentative.isKing()) {
     				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
     			} else {
     				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
     			}

     		} else {
     			if (tentative.isFire()) {
	     			if (!tentative.isKing()) {
	     				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	     			} else {
	     				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	     			}
     			} else {
	     			if (!tentative.isKing()) {
	     				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	     			} else {
	     				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	     			}
     			}
     		} 
    	}
	}


	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {

		//if a piece has already been selected
		if(hasSelectedPiece) {

			//if player selects the same spot as the previously selected piece
			if (pieces[x][y] == selectedPiece && !hasMoved) {

				return true;
			} else if (pieces[x][y] == selectedPiece && hasMoved) {
				return false;
			}
			//if a piece has not been moved yet
			if(!hasMoved) {

				//if player tries to make the piece move
				if (pieces[x][y] == null) {
					//check if it's a valid move
					return checkValidMove(selectedPiece, x, y);

				//if selected spot has a piece and selected the turn player's piece
				} else if (pieces[x][y] != null && (fireTurn == pieces[x][y].isFire())) {

					return true;
				}

			//if a piece has been moved already
			} else if (hasMoved) {
				//if the piece has captured already
				if (selectedPiece.hasCaptured()) {

					//check if it's a valid move
					return checkValidMove(selectedPiece, x, y);
				}
			}		

		//if a piece has not already been selected
		} else if (!hasSelectedPiece) {
			//if spot selected has a piece
			if (pieces[x][y] != null) {

				//if the turn player selects his own piece
				if (fireTurn == pieces[x][y].isFire()) {
					return true;
				}
			//otherwise if the spot selected does not have a piece or the player did not select the right piece
			} else {
				return false;
			}
		}

		return false;
	}

	private void colorSelectedPiece() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (selectedPiece == pieces[i][j]) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
					drawPiece(i, j);
				}
			}
		}
	}

	private boolean checkValidMove(Piece p, int x, int y) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (p == pieces[i][j]) {
					return validMove(i, j, x, y);
				}
			}
		}
		return false;
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		//moving one space to the right
		if (xf == xi + 1) {
			//if moving up one, must be fire or king and space cannot contain a piece
			if (yf == yi + 1 && pieces[xf][yf] == null && (pieces[xi][yi].isKing() || pieces[xi][yi].isFire())) {
				//if piece is a king
				if (pieces[xi][yi].isKing()) {
					//if king has captured a piece already
					if (pieces[xi][yi].hasCaptured()) {
						return false;

					//if king has not captured a piece already
					} else if (pieces[xi][yi].hasCaptured() == false) {
						return true;
					}

				//if piece isn't a king, then return true
				} else {
					if (hasCaptured) {
						return false;
					}
					return true;
				}
			//if moving down one, must be water or king and space cannot contain a piece
			} else if (yf == yi - 1 && pieces[xf][yf] == null && (pieces[xi][yi].isKing() || !pieces[xi][yi].isFire())) {
				//if piece is a king
				if (pieces[xi][yi].isKing()) {
					//if king has captured a piece already
					if (pieces[xi][yi].hasCaptured()) {
						return false;

					//if king has not captured a piece already
					} else if (pieces[xi][yi].hasCaptured() == false) {
						return true;
					}

				//if piece isn't a king, then return true
				} else {
					if (hasCaptured) {
						return false;
					}
					return true;
				}
			//otherwise if it moves more than one space vertically, returns false
			} else if (!(yf == yi + 1 || yf == yi - 1)) {
				return false;
			}
		//moving one space to the left
		} else if (xf == xi - 1) {

			//if moving up one, must be fire or king, and space cannot contain a piece
			if (yf == yi + 1 && pieces[xf][yf] == null && (pieces[xi][yi].isKing() || pieces[xi][yi].isFire())) {
				//if piece is a king
				if (pieces[xi][yi].isKing()) {
					//if king has captured a piece already
					if (pieces[xi][yi].hasCaptured()) {
						return false;

					//if king has not captured a piece already
					} else if (pieces[xi][yi].hasCaptured() == false) {
						return true;
					}

				//if piece isn't a king, then return true
				} else {
					if (hasCaptured) {
						return false;
					}
					return true;
				}

			//if moving down one, must be water or king, and space cannot contain a piece
			} else if (yf == yi - 1 && pieces[xf][yf] == null && (pieces[xi][yi].isKing() || !pieces[xi][yi].isFire())) {
				//if piece is a king
				if (pieces[xi][yi].isKing()) {
					//if king has captured a piece already
					if (pieces[xi][yi].hasCaptured()) {
						return false;

					//if king has not captured a piece already
					} else if (pieces[xi][yi].hasCaptured() == false) {
						return true;
					}

				//if piece isn't a king, then return true
				} else {
					if (hasCaptured) {
						return false;
					}
					return true;
				}

			//otherwise if not moving one space vertically, returns false
			} else if (!(yf == yi + 1 || yf == yi - 1)) {
				return false;
			}

	//trying to capture
		//if moves two spaces to the right
		} else if (xf == xi + 2) {

			//if space between jump contains a piece and the two pieces are not controlled by the same person and the piece is either fire or a king
			if (yf == yi + 2 && (pieces[xi][yi].isFire() || pieces[xi][yi].isKing()) && (pieces[xi + 1][yi + 1] != null && (pieces[xi + 1][yi + 1].isFire() != pieces[xi][yi].isFire()))) {
				return true;

			//if space between jump contains a piece and the two pieces are not controlled by the same person and the pieces is either water or a king
			} else if (yf == yi - 2  && (!pieces[xi][yi].isFire() || pieces[xi][yi].isKing()) && (pieces[xi + 1][yi - 1] != null && (pieces[xi + 1][yi - 1].isFire() != pieces[xi][yi].isFire()))) {
				return true;

			//otherwise if the jump is not two spaces vertically, returns false
			} else if (!(yf == yi + 2 || yf == yi - 2)) {
				return false;
			}

		//if moves two spaces to the left
		} else if (xf == xi - 2) {

			//if space between jump contains a piece and the pieces are not controlled by the same person and the piece is either fire or a king
			if (yf == yi + 2 && (pieces[xi][yi].isFire() || pieces[xi][yi].isKing()) && (pieces[xi - 1][yi + 1] != null && (pieces[xi - 1][yi + 1].isFire() != pieces[xi][yi].isFire()))) {
				return true;

			//if space between jump contains a piece and the two pieces are not controlled by the same person and the pieces is either water or a king
			} else if (yf == yi - 2  && (!pieces[xi][yi].isFire() || pieces[xi][yi].isKing()) && (pieces[xi - 1][yi - 1] != null && (pieces[xi - 1][yi - 1].isFire() != pieces[xi][yi].isFire()))) {
				return true;

			//otherwise if the jump is not two spaces vertically, returns false
			} else if (!(yf == yi + 2 || yf == yi - 2)) {
				return false;
			}

		//otherwise if piece tries to move more than one or two spaces, is not a valid move
		} else if (!(xf == xi + 2 || xf == xi - 2 || xf == xi + 1 || xf == xi - 1)) {
			return false;
		}

		return false;
	}


	public void select(int x, int y) {
		//player has not already selected a piece
		if (!hasSelectedPiece) {
				//highlight background white
				selectedPiece = pieceAt(x, y);
		        hasSelectedPiece = true;
		        
		} else if (hasSelectedPiece) {

			//switching the selected piece
			if (pieces[x][y] != null && fireTurn == pieces[x][y].isFire()) {

				// drawBoard(N);
				selectedPiece = pieceAt(x, y);
				// colorSelectedPiece();
			} else if (pieces[x][y] == null) {
				selectedPiece.move(x, y);
				hasMoved = true;
			}
		}
	}


	public void place(Piece p, int x, int y) {
		if (p == null) {

		} else if (p != null && x < 8 && y < 8 && x >= 0 && y >= 0) {
			//find and remove the piece from the old location
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (pieces[i][j] == p) {
						pieces[i][j] = null;
					} 
				}
			}

			//put piece in new location
			pieces[x][y] = p;
		}
	}


	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y < 0 || y > 7) {
			System.out.println("Location out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("No piece at location.");
			return null;
		} else if (pieceAt(x, y) != null) {
			Piece tentative = pieceAt(x, y);
			pieces[x][y] = null;
			hasCaptured = true;
			return tentative;
		}
		return null;
	}


	public boolean canEndTurn() {
		return (hasMoved || hasCaptured);
	}


	public void endTurn() {
		selectedPiece.doneCapturing();
		// for (int i = 0; i < 8; i++) {
		// 	for (int j = 0; j < 8; j++) {
		// 		if (pieces[i][j] != null) {
		// 			pieces[i][j].doneCapturing();
		// 		}
		// 	}
		// }
		if (fireTurn) {
			fireTurn = false;
			hasMoved = false;
			selectedPiece = null;
			hasSelectedPiece = false;
			hasCaptured = false;

			System.out.println("It's water's turn!");

		} else {
			fireTurn = true;
			hasMoved = false;
			selectedPiece = null;
			hasSelectedPiece = false;
			hasCaptured = false;

			System.out.println("It's fire's turn!");
		}
	}


	public String winner() {

		int fire = 0;
		int water = 0;

		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						fire += 1;
					} else if (pieces[i][j].isFire() == false) {
						water += 1;
					}
				}
			}
		}

		if (fire > 0 && water > 0) {
			return null;
		} else if (water == 0 && fire > 0) {
			return "Fire";
		} else if (fire == 0 && water > 0) {
			return "Water";
		} else if (water == 0 && fire == 0) {
			return "No one";
		}

	return null;
	}

}