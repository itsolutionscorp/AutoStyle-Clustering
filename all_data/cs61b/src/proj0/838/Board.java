public class Board {
	private static Piece[][] pieces;
	private static int turn = 0; // 0 is fire, 1 is water
	private boolean hasSelected = false;
	private boolean hasSelectedLocked = false;
	private boolean hasMoved = false;
	private static int drawx;
	private static int drawy;
	private int deletex;
	private int deletey;
	private int initx;
	private int inity;
	private Piece selectedPiece;

	public static void main(String args[]) { // CHECK OUT LOGIC TO MAKE SURE IT
												// WORKS
		Board game_board = new Board(false);
		while (game_board.winner() == null) {
			if (turn == 0) { // fires turn

				if (StdDrawPlus.mousePressed()) {
					
					int x = (int) StdDrawPlus.mouseX();
					int y = (int) StdDrawPlus.mouseY();
					if (game_board.canSelect(x, y)) {
						game_board.select(x, y);
					}
				}
				if (StdDrawPlus.isSpacePressed() && game_board.canEndTurn()) {
					game_board.endTurn();
				}
				drawBoard();
			}
			if(turn == 1){ // waters turn
   				if (StdDrawPlus.mousePressed()) {
					int x = (int) StdDrawPlus.mouseX();
					int y = (int) StdDrawPlus.mouseY();
					if (game_board.canSelect(x, y)) {
						game_board.select(x, y);
					}
				}
				if (StdDrawPlus.isSpacePressed() && game_board.canEndTurn()) {
					game_board.endTurn();
				}
				drawBoard();
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty) { // dont initialize the pieces
			StdDrawPlus.setXscale(0, 8);
			StdDrawPlus.setYscale(0, 8);
			drawBoard();
		} else {
			StdDrawPlus.setXscale(0, 8);
			StdDrawPlus.setYscale(0, 8);

			Piece fire_pawn_1 = new Piece(true, this, 0, 0, "pawn");
			Piece fire_pawn_2 = new Piece(true, this, 2, 0, "pawn");
			Piece fire_pawn_3 = new Piece(true, this, 4, 0, "pawn");
			Piece fire_pawn_4 = new Piece(true, this, 6, 0, "pawn");

			place(fire_pawn_1, 0, 0);
			place(fire_pawn_2, 2, 0);
			place(fire_pawn_3, 4, 0);
			place(fire_pawn_4, 6, 0);

			Piece fire_shield_1 = new Piece(true, this, 1, 1, "shield");
			Piece fire_shield_2 = new Piece(true, this, 3, 1, "shield");
			Piece fire_shield_3 = new Piece(true, this, 5, 1, "shield");
			Piece fire_shield_4 = new Piece(true, this, 7, 1, "shield");

			place(fire_shield_1, 1, 1);
			place(fire_shield_2, 3, 1);
			place(fire_shield_3, 5, 1);
			place(fire_shield_4, 7, 1);

			Piece fire_bomb_1 = new Piece(true, this, 0, 2, "bomb");
			Piece fire_bomb_2 = new Piece(true, this, 2, 2, "bomb");
			Piece fire_bomb_3 = new Piece(true, this, 4, 2, "bomb");
			Piece fire_bomb_4 = new Piece(true, this, 6, 2, "bomb");

			place(fire_bomb_1, 0, 2);
			place(fire_bomb_2, 2, 2);
			place(fire_bomb_3, 4, 2);
			place(fire_bomb_4, 6, 2);

			Piece water_pawn_1 = new Piece(false, this, 1, 5, "pawn");
			Piece water_pawn_2 = new Piece(false, this, 3, 5, "pawn");
			Piece water_pawn_3 = new Piece(false, this, 5, 5, "pawn");
			Piece water_pawn_4 = new Piece(false, this, 7, 5, "pawn");

			place(water_pawn_1, 1, 7);
			place(water_pawn_2, 3, 7);
			place(water_pawn_3, 5, 7);
			place(water_pawn_4, 7, 7);

			Piece water_shield_1 = new Piece(false, this, 0, 6, "shield");
			Piece water_shield_2 = new Piece(false, this, 2, 6, "shield");
			Piece water_shield_3 = new Piece(false, this, 4, 6, "shield");
			Piece water_shield_4 = new Piece(false, this, 6, 6, "shield");

			place(water_shield_1, 0, 6);
			place(water_shield_2, 2, 6);
			place(water_shield_3, 4, 6);
			place(water_shield_4, 6, 6);

			Piece water_bomb_1 = new Piece(false, this, 1, 7, "bomb");
			Piece water_bomb_2 = new Piece(false, this, 3, 7, "bomb");
			Piece water_bomb_3 = new Piece(false, this, 5, 7, "bomb");
			Piece water_bomb_4 = new Piece(false, this, 7, 7, "bomb");

			place(water_bomb_1, 1, 5);
			place(water_bomb_2, 3, 5);
			place(water_bomb_3, 5, 5);
			place(water_bomb_4, 7, 5);

			drawBoard();
		}
	}

	public Piece pieceAt(int x, int y) {
		if(x>7 || x<0 || y>7 || y<0){return null;}
		else{return pieces[x][y];}
	}

	private static void drawBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				// if(i == drawx && j ==
				// drawy){StdDrawPlus.setPenColor(StdDrawPlus.WHITE);}

				if (pieces[i][j] != null) { // checks to see if piece exists
					if (pieces[i][j].isFire()) { // checks to see if fire
						if (pieces[i][j].isKing()) { // checks to see if king
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/bomb-fire-crowned.png", 1, 1);
							}
							if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/pawn-fire-crowned.png", 1, 1);
							}
						} else if (pieces[i][j].isBomb()) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/bomb-fire.png", 1, 1);
						} else if (pieces[i][j].isShield()) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/shield-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/pawn-fire.png", 1, 1);
						}
					} else { // must be water
						if (pieces[i][j].isKing()) { // checks to see if king
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/bomb-water-crowned.png", 1, 1);
							}
							if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/pawn-water-crowned.png", 1, 1);
							}
						} else if (pieces[i][j].isBomb()) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/bomb-water.png", 1, 1);
						} else if (pieces[i][j].isShield()) {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/shield-water.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									"img/pawn-water.png", 1, 1);
						}
					}
				}
			}
			StdDrawPlus.show(1);
		}
	}

	public boolean canSelect(int x, int y) {
		if (turn == 0) { // fires turn
			if (pieceAt(x, y) != null) {
				if (pieceAt(x, y).isFire()) { // selecting a piece
					selectedPiece = pieceAt(x, y);
					initx = x;
					inity = y;
					hasSelected = true;
					return true;
				}
			} else if (pieceAt(x, y) == null) {
				if (hasSelected == false) {
					return false;
				} else if (validMove(initx, inity, x, y)) {
					hasMoved = true;
					return true;
				}
			}
			return false;
		}

		else if (turn == 1) { // waters turn
			if (pieceAt(x, y) != null) {
				if (pieceAt(x, y).isFire() != true) { // selecting a piece
					selectedPiece = pieceAt(x, y);
					initx = x;
					inity = y;
					hasSelected = true;
					return true;
				}
			} else if (pieceAt(x, y) == null) {
				if (hasSelected == false) {
					return false;
				} else if (validMove(initx, inity, x, y)) {
					hasMoved = true;
					return true;
				}
			}
			return false;
		}
		return false;

	}

	private boolean validMove(int xi, int yi, int xf, int yf) { // in this method
																// check to see
																// if captures
																// are possible,
																// and then
																// remove them
																// using deletex
																// and deletey
		int xdir = xf - xi; // make sure that this method is private
		int ydir = yf - yi;

		if (pieceAt(xi, yi) == null) {
			return false;
		}

		if (xf > 7 || xf < 0 || yf < 0 || yf > 7) {
			return false;
		} // out of board

		boolean topRight = false;
		boolean topLeft = false;
		boolean bottomRight = false;
		boolean bottomLeft = false;

		if (xf == xi + 1 && pieceAt(xf, yf) == null) { // top right or bottom
														// right
			if (yf == yi + 1) {
				topRight = true;
			}
			if (yf == yi - 1) {
				bottomRight = true;
			}
		}
		if (xf == xi - 1 && pieceAt(xf, yf) == null) { // top left or bottom
														// left
			if (yf == yi + 1) {
				topLeft = true;
			}
			if (yf == yi - 1) {
				bottomLeft = true;
			}
		}

		boolean top2leftfire = false;
		boolean top2rightfire = false;
		boolean bottom2leftfire = false;
		boolean bottom2rightfire = false;

		if ((xf == xi + 2 && yf == yi + 2)
				&& (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1)
						.isFire() != true)) {
			top2rightfire = true;
		}

		if ((xf == xi - 2 && yf == yi + 2)
				&& (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1)
						.isFire() != true)) {
			top2leftfire = true;
		}

		if ((xf == xi + 2 && yf == yi - 2)
				&& (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1)
						.isFire() != true)) {
			bottom2rightfire = true;
		}
		if ((xf == xi - 2 && yf == yi - 2)
				&& (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1)
						.isFire() != true)) {
			bottom2leftfire = true;
		}

		boolean top2leftwater = false;
		boolean top2rightwater = false;
		boolean bottom2leftwater = false;
		boolean bottom2rightwater = false;

		if ((xf == xi + 2 && yf == yi + 2)
				&& (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1)
						.isFire() == true)) {
			top2rightwater = true;
		}

		if ((xf == xi - 2 && yf == yi + 2)
				&& (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1)
						.isFire() == true)) {
			top2leftwater = true;
		}

		if ((xf == xi + 2 && yf == yi - 2)
				&& (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1)
						.isFire() == true)) {
			bottom2rightwater = true;
		}
		if ((xf == xi - 2 && yf == yi - 2)
				&& (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1)
						.isFire() == true)) {
			bottom2leftwater = true;
		}

		if (selectedPiece.isFire()) { // fire piece

			if (selectedPiece.isKing()) { // king piece
				if (topRight || topLeft || bottomRight || bottomLeft) {
					return true;
				} // normal moves in all directions
				if (top2leftfire || top2rightfire || bottom2leftfire
						|| bottom2rightfire) {
					return true;
				} // special moves
			} else { // normal piece
				if (topLeft || topRight || top2leftfire || top2rightfire) {
					return true;
				}
			}
		} else if (selectedPiece.isFire() != true) { // water piece
			if (selectedPiece.isKing()) { // king piece
				if (topRight || topLeft || bottomRight || bottomLeft) {
					return true;
				} // normal moves in all directions
				if (top2leftwater || top2rightwater || bottom2leftwater
						|| bottom2rightwater) {
					return true;
				} // special moves
			} else { // normal piece
				if (bottomRight || bottomLeft || bottom2leftwater
						|| bottom2rightwater) {
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) { // save x and y values so you can
		drawx = x;
		drawy = y;
		if (hasSelected && hasMoved == false) { // piece is already selected
			selectedPiece.move(x, y); // move the piece, performing captures and
										// bomb explosions
			place(selectedPiece, x, y);
			remove(initx, inity);
			hasSelectedLocked = true;
			hasMoved = true; // can legally end turn
		}
		
		remove(initx, inity);
		place(selectedPiece, x, y);
		
	}

	public void place(Piece p, int x, int y) {

		if (x > 7 || x < 0 || y < 0 || y > 7 || p == null) {} 
		else if (pieces[x][y] != null) {
			remove(x, y); 
			pieces[x][y] = p;
		} else {
			pieces[x][y] = p;
		}

	}

	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y < 0 || y > 7) {
			System.out.println("That's outside of the board");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("There is no piece there to remove");
			return null;
		}
		Piece tempPiece = pieceAt(x, y);
		pieces[x][y] = null;
		return tempPiece;
	}

	public boolean canEndTurn() {
		System.out.println(hasMoved);
		if (hasMoved){
			hasMoved = false;
			return true;
		}
		//System.out.println("cant change turn stupid");
		return false;
	}

	public void endTurn() {
		//System.out.println("changing turn");
		hasSelected = false;
		hasMoved = false;
		turn = 1 - turn;
		
	}

	public String winner() { // finished
		boolean fire = false;
		boolean water = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						fire = true;
					} else {
						water = true;
					}
				}
			}
		}
		if (fire && water) {
			return null;
		} else if (fire == true && water == false) {
			return "Fire";
		} else {
			return "Water";
		}
	}

}