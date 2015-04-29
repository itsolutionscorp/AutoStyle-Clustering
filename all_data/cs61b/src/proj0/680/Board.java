import static java.lang.Math.abs;

public class Board {

	private String turn;
	private boolean moveHappened;
	private Piece selectedPiece;
	private Piece[][] Pieces;




	public Board(boolean shouldBeEmpty) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		this.drawBoard(N);
		this.moveHappened = false;
		if (shouldBeEmpty) {
			this.nullPieceCreator();
			this.turn = "fire";
			return;
		}
		if (!shouldBeEmpty) {
			this.originalPieceCreator();
			this.turn = "fire";
		}
	}
		
	public static void main(String[] args) {
		
		Board checkerboard = new Board(false);

		checkerboard.drawPieces();

		int clickX;
		int clickY;
		while (true) {
			if (StdDrawPlus.mousePressed()) {

				clickX = checkerboard.clickX();
				clickY = checkerboard.clickY();

				if (checkerboard.canSelect(clickX, clickY)) {
					checkerboard.select(clickX, clickY);
				}
			}

			if (StdDrawPlus.isSpacePressed()) {
				if (checkerboard.canEndTurn()) {
					checkerboard.endTurn();
				}
			}
			if (checkerboard.winner() != null) {
				checkerboard.winner();
			}
		}
	}

	private void nullPieceCreator() {
		this.Pieces = new Piece[8][8];
		for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
            	this.Pieces[i][j] = null;
            }
        }
	}

	// private void originalPieceCreator() {
	// 	this.Pieces = new Piece[8][8];
	// 	for (int j = 0; j < 8; j++) {
 //            for (int i = 0; i < 8; i++) {
 //            	if ((i + j) % 2 == 0) {
	//                 	if (j == 7) {
	//                 		Pieces[i][j] = new Piece(false, this, i, j, "pawn");
	//                 	}
	//                 	if (j == 6) {
	//                 		Pieces[i][j] = new Piece(false, this, i, j, "shield");
	//                 	}
	//                 	if (j == 5) {
	//                 		Pieces[i][j] = new Piece(false, this, i, j, "bomb");
	//                 	}
	//                 	if (j == 2) {
	//                 		Pieces[i][j] = new Piece(true, this, i, j, "bomb");
	//                 	}
	//                 	if (j == 1) {
	//                 		Pieces[i][j] = new Piece(true, this, i, j, "shield");
	//                 	}
	//                 	if (j == 0) {
	//                 		Pieces[i][j] = new Piece(true, this, i, j, "pawn");
	//                 	}
	//                 	else {
	//                 		Pieces[i][j] = null;
	//                 	}
 //                }
 //            }
 //        }
	// }

	private void originalPieceCreator() {
		this.Pieces = new Piece[8][8];
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				Pieces[i][j] = null;
			}
		}
		Pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		Pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		Pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		Pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
		Pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		Pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		Pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		Pieces[7][1] = new Piece(true, this, 7, 1, "shield");
		Pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		Pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		Pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		Pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
		Pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		Pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		Pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		Pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		Pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		Pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		Pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		Pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		Pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		Pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		Pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		Pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
	}



	public boolean canSelect(int X, int Y) {
		Piece currentPiece;

		if ((X % 2) != (Y % 2)) {
			return false;
		}
		if ((pieceAt(X, Y) != null) && (selectedPiece == null)) {
			currentPiece = pieceAt(X, Y);
			if (((currentPiece.isFire()) && (this.turn == "fire")) || ((!currentPiece.isFire()) && (this.turn == "water"))){
				return true;
			}
			else {
				return false;
			}
		}
		if ((pieceAt(X, Y) != null) && (selectedPiece.hasCaptured())) {
			currentPiece = pieceAt(X, Y);
			if (((currentPiece.isFire()) && (this.turn == "fire")) || ((!currentPiece.isFire()) && (this.turn == "water"))) {
				return true;
			}
			else if (((currentPiece.isFire()) && (this.turn == "water")) || ((!currentPiece.isFire()) && (this.turn == "fire"))) {
				return false;
			}
		}	
		if (pieceAt(X, Y) == null) {
			if (this.selectedPiece != null) {
				if (isValidMove(X, Y)) {
					return true;
				}
			}
			else if (this.selectedPiece == null) {
				return false;
			}
		}
		return false;
	}

	public Piece pieceAt(int X, int Y) {
		if ((X > 7) || (X < 0) || (Y > 7) || (Y < 0)) {
			return null;
		}
		if (this.Pieces[X][Y] != null) {
			return this.Pieces[X][Y];
		}
		return null;
	}


	public void select(int X, int Y) {

		highlight(X, Y);

		if (pieceAt(X, Y) != null) {
			this.selectedPiece = pieceAt(X, Y);
			System.out.println("This is now the selected piece: " + this.selectedPiece);
		}
		else if ((this.selectedPiece != null) && (pieceAt(X, Y) == null)) {
			if (isValidMove(X, Y)) {
				// this.place(selectedPiece, X, Y);
				int oldX = XselectedPieceIsolator();
				int oldY = YselectedPieceIsolator();
				System.out.println("This is what I think the old X is: " + oldX);
				System.out.println("This is what I think the old Y is: " + oldY);
				this.moveHappened = true;
				System.out.println("A move has happened.");
				if (!((abs(oldX - X) == 2) && (this.selectedPiece.isBomb()))) {
					Pieces[X][Y] = this.selectedPiece;
				}
				this.selectedPiece.move(X, Y);
				System.out.println("This is the selected piece: " + this.selectedPiece);
				System.out.println("And this is the old piece: " + pieceAt(oldX, oldY));

				if ((abs(X - oldX) == 1) && (abs(Y - oldY) == 1)) {
					if (canEndTurn()) {
						System.out.println("I'm going to try and end the turn now.");
						endTurn();
						System.out.println("Did I end the turn? Is the turn water? " + this.turn);
					}
				}
			}
		}
	}

	private int XselectedPieceIsolator() {
		int N = 8;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	System.out.println("I'm looking for " + i + ", " + j);
            	if (pieceAt(i, j) != null) {
            		if (pieceAt(i, j) == this.selectedPiece) {
            			return i;
            		}
            	}
            }
        }
        return 0;
	}

	private int YselectedPieceIsolator() {
		int N = 8;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	System.out.println("I'm looking for " + i + ", " + j);
            	if (pieceAt(i, j) != null) {
            		if (pieceAt(i, j) == this.selectedPiece) {
            			return j;
            		}
            	}
            }
        }
        return 0;
	}

	private void drawBoard(int N) {
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	private void highlight(int X, int Y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(X + 0.5, Y + 0.5, 0.5);
		this.drawPieces();
	}

	private void revert(int X, int Y) {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.filledSquare(X, Y, 0.5);
		this.drawPieces();
	}


	private void drawPieces() {
		for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
            	if ((i + j) % 2 == 0) {
            		if (this.Pieces[i][j] != null) {
						if (this.Pieces[i][j].isFire()) {

							if (this.Pieces[i][j].isBomb()) {

								if (this.Pieces[i][j].isKing()) {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1.0, 1.0);
								}
								else {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1.0, 1.0);
								}
							}

							else if (this.Pieces[i][j].isShield()) {

								if (this.Pieces[i][j].isKing()) {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1.0, 1.0);
								}
								else {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1.0, 1.0);
								}
							}

							else if (this.Pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1.0, 1.0);
							}
							}

						else {

							if (this.Pieces[i][j].isBomb()) {

								if (this.Pieces[i][j].isKing()) {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned", 1.0, 1.0);
								}
								else {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1.0, 1.0);
								}
							}

							else if (this.Pieces[i][j].isShield()) {

								if (this.Pieces[i][j].isKing()) {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1.0, 1.0);
								}
								else {
									StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1.0, 1.0);
								}
							}

							else if (this.Pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1.0, 1.0);
							}
						}
					}
				}
			}
		}
	}

	public String winner() {
		int checkfire = 0;
		int checkwater = 0;
		for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
            	if ((i + j) % 2 == 0) {
            		if (this.Pieces[i][j] != null) {
            			if (this.Pieces[i][j].isFire()) {
            				checkfire += 1;
            			}
            			else {
            				checkwater += 1;
            			}

            		}
            	}
            }
        }
        if ((checkwater == 0) && (checkfire == 0)) {
        	return "No one";
        }
        else if (checkwater == 0) {
        	return "Fire";
        }
        else if (checkfire == 0) {
        	return "Water";
        }
        return null;
	}

	private int clickX() {
		return (int) StdDrawPlus.mouseX();
	}

	private int clickY() {
		return (int) StdDrawPlus.mouseY();
	}

	private boolean isValidMove(int X, int Y) {
		int originalX = 0;
		int originalY = 0;
		for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
            	if ((i + j) % 2 == 0) {
            		if ((this.pieceAt(i, j) != null) && ((this.pieceAt(i, j)) == this.selectedPiece)) {
            			originalX = i;
            			originalY = j;
            		}
            	}
            }
        }
		int selectedX = X;
		int selectedY = Y;
		if (((selectedX > 7) || (selectedX < 0)) || ((selectedY > 7) || (selectedY < 0))) {
			return false;
		}

		if ((abs(selectedY - originalY) > 2) || (abs(selectedY - originalY) < 1)) {
			return false;
		}

		if (this.pieceAt(X, Y) != null) {
			return false;
		}

		if (abs(selectedX - originalX) == 0) {
			return false;
		}

		if (abs(selectedY - originalY) == 2) {

			if (this.selectedPiece.isFire()) {

				if (selectedY - 2 == originalY) {
					if (selectedX - 2 == originalX) {
						if (this.pieceAt(selectedX - 1, selectedY - 1) != null) {
							if (!this.pieceAt(selectedX - 1, selectedY - 1).isFire()){
								return true;
							}
						}
						return false;
					}
					else if (selectedX + 2 == originalX) {
						if (this.pieceAt(selectedX + 1, selectedY - 1) != null) {
							if (!this.pieceAt(selectedX + 1, selectedY - 1).isFire()){
								return true;
							}
						}
						return false;
					}
					else {
						return false;
					}
				}

				if (this.selectedPiece.isKing()) {

					if (selectedY + 2 == originalY) {
						if (selectedX - 2 == originalX) {
							if (this.pieceAt(selectedX - 1, selectedY + 1) != null) {
								if (!this.pieceAt(selectedX - 1, selectedY + 1).isFire()){
									return true;
								}
							}
							return false;
					}
						else if (selectedX + 2 == originalX) {
							if (this.pieceAt(selectedX + 1, selectedY + 1) != null) {
								if (!this.pieceAt(selectedX + 1, selectedY + 1).isFire()){
									return true;
								}
							}
							return false;
						}
						else {
							return false;
						}
					}
				}
			}

			else {

				if ((selectedY + 2) - originalY == 0) {
					if ((selectedX - 2) - originalX == 0) {
						if (this.pieceAt(selectedX - 1, selectedY + 1) != null) {
							if (this.pieceAt(selectedX - 1, selectedY + 1).isFire()){
								return true;
							}
						}
						return false;
					}
					else if ((selectedX + 2) - originalX == 0) {
						if (this.pieceAt(selectedX + 1, selectedY + 1) != null) {
							if (this.pieceAt(selectedX + 1, selectedY + 1).isFire()){
								return true;
							}
						}
						return false;
					}
					else {
						return false;
					}
				}

				else if (this.selectedPiece.isKing()) {

					if ((selectedY - 2) - originalY == 0) {
						if ((selectedX - 2) - originalX == 0) {
							if (this.pieceAt(selectedX - 1, selectedY - 1) != null) {
								if (this.pieceAt(selectedX - 1, selectedY - 1).isFire()){
									return true;
								}
							}
							return false;
					}
						else if ((selectedX + 2) - originalX == 0) {
							if (this.pieceAt(selectedX + 1, selectedY - 1) != null) {
								if (this.pieceAt(selectedX + 1, selectedY - 1).isFire()){
									return true;
								}
							}
							return false;
						}
						else {
							return false;
						}
					}
				}
			}
		}
		else if (abs(originalY - selectedY) == 1) {

			if (this.selectedPiece.hasCaptured()) {
				return false;
			}

			// Have to check which team, determines which direction. Takes into account isCrowned as well. 

			if (!this.selectedPiece.isFire()) {

				if (this.selectedPiece.isKing()) {
					return true;
				}
				else {
					if ((selectedY + 1) - originalY == 0) {
						return true;
					}
					return false;
				}
				}	

			else {

				if (this.selectedPiece.isKing()) {
					return true;
				}
				else {
					if ((selectedY - 1) - originalY == 0) {
						return true;
					}
					return false;
				}
			}
			}
		return false;
		}

	public void place(Piece piece, int X, int Y) {
		if (this.pieceAt(X, Y) != null) {
			this.remove(X, Y);
		}
		Pieces[X][Y] = piece;
		this.moveHappened = true;
	}

	public Piece remove(int X, int Y) {
		System.out.println("I'm trying to remove something at " + X + ", " + Y);

		if ((X > 7) || (X < 0) || (Y > 7) || (Y < 0)) {
			System.out.println("You selected a space out of bounds. Nothing was removed.");
			return null;
		}
		else if (this.pieceAt(X, Y) == null) {
			System.out.println("There is no piece there. Nothing was removed.");
			return null;
		}
		else if (this.pieceAt(X, Y) != null) {
			System.out.println("There's something here... But not for long!");
			System.out.println(pieceAt(X, Y));
			Piece thisPiece = pieceAt(X, Y);
			Pieces[X][Y] = null;
			return thisPiece;
		}
		return null;
	}


	public boolean canEndTurn() {
		if (this.moveHappened) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (this.canEndTurn()) {
			this.selectedPiece = null;
			if (turn == "fire") {
				turn = "water";
			}
			else if (turn == "water") {
				turn = "fire";
			}
			int boardint = 8;
			this.drawBoard(boardint);
			this.drawPieces();
			this.moveHappened = false;
		}
	}
	}


