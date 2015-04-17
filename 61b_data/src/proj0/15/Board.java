public class Board {
	private boolean ifEmpty;
	public Piece[][] placement;
	private int current_player = 0;
	private boolean has_selected = false;
	private int selected_x = 0;
	private int selected_y = 0;
	private boolean has_moved = false;

	/* Constructor of a board. */
	public Board(boolean shouldBeEmpty) {
		ifEmpty = shouldBeEmpty;
		placement = new Piece[8][8];
		if (ifEmpty == false) {
			configureBoard();
		}
	}

	/* Places pieces in starting positions. */
	private void configureBoard() {
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if ((j == 0) && (i % 2 == 0)) {
					placement[i][j] = new Piece(true, this, i, j, "pawn");
				}
				if ((j == 1) && (i % 2 != 0)) {
					placement[i][j] = new Piece(true, this, i, j, "shield");
				}
				if ((j == 2) && (i % 2 == 0)) {
					placement[i][j] = new Piece(true, this, i, j, "bomb");
				}
				if ((j == 5) && (i % 2 != 0)) {
					placement[i][j] = new Piece(false, this, i, j, "bomb");
				}
				if ((j == 6) && (i % 2 == 0)) {
					placement[i][j] = new Piece(false, this, i, j, "shield");
				}
				if ((j == 7) && (i % 2 != 0)) {
					placement[i][j] = new Piece(false, this, i, j, "pawn");
				}
			}
		}
	}

	/* Returns the piece at position (x,y), or null if no piece. */
	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			return null;
		}
		else if (placement[x][y] == null) {
			return null;
		}
		else {
			return placement[x][y];
		}
	}

	/* Places piece p at position (x, y). */
	public void place(Piece p, int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
		}
		else if (p == null) {
		}
		else {
		 	placement[x][y] = p;
		}
	}

	/* Removes a piece from input position (x,y) and returns the piece removed. */
	public Piece remove(int x , int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			System.out.println(">>>Error: input (x,y) is out of bounds<<<");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println(">>>Error: no piece at inputted position (x,y)<<<");
			return null;
		}
		else {
			Piece removedPiece = placement[x][y];
			placement[x][y] = null;
			return removedPiece;
		}
	}

	/* Returns true if square at (x,y) can be selected. */
	public boolean canSelect(int x, int y) {
		if (placement[x][y] != null) {
			if (placement[x][y].side() == current_player) {
				if (has_selected == false) {
					return true;
				}
				else if ((has_selected == true) && (has_moved == false)) {
					return true;
				}
			}
		}
		else if (placement[x][y] == null) {
			if ((has_selected == true) && (has_moved == false)) {
				if (validMove(selected_x, selected_y, x, y)) {
					return true;
				}
			}
			else if ((has_selected == true) && (placement[selected_x][selected_y].hasCaptured())) {
				if (validMove(selected_x, selected_y, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/* Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf). */
	public boolean validMove(int xi, int yi, int xf, int yf) {
		if ((placement[xi][yi] != null) && (pieceAt(xf, yf) == null)) {
			if (placement[xi][yi].isKing()) {
				return validMoveKing(xi, yi, xf, yf);
			}
			else if (placement[xi][yi].isFire()) {
				return validMoveFire(xi, yi, xf, yf);
			}
			else if (placement[xi][yi].isFire() == false) {
				return validMoveWater(xi, yi, xf, yf);
			}
		}
		return false;
	}
	
	/* Evaluates valid moves for a king piece. */
	public boolean validMoveKing(int xi, int yi, int xf, int yf) {
		if (((xf == xi + 1) && (yf == yi + 1)) ||
		   ((xf == xi - 1) && (yf == yi + 1)) ||
		   ((xf == xi + 1) && (yf == yi - 1)) ||
		   ((xf == xi - 1) && (yf == yi - 1))) {
			return true;
		}
		if ((xf == xi + 2) && (yf == yi + 2)) {
			if (placement[xi+1][yi+1] != null) {
				if (placement[xi][yi].side() != placement[xi+1][yi+1].side()) {
					return true;
				}
			}
		}
		if ((xf == xi - 2) && (yf == yi + 2)) {
			if (placement[xi-1][yi+1] != null) {
				if (placement[xi][yi].side() != placement[xi-1][yi+1].side()) {
					return true;
				}
			}
		}
		if ((xf == xi + 2) && (yf == yi - 2)) {
			if (placement[xi+1][yi-1] != null) {
				if (placement[xi][yi].side() != placement[xi+1][yi-1].side()) {
					return true;
				}
			}
		}
		if ((xf == xi - 2) && (yf == yi - 2)) {
			if (placement[xi-1][yi-1] != null) {
				if (placement[xi][yi].side() != placement[xi-1][yi-1].side()) {
					return true;
				}
			}
		}
		return false;
	}

	/* Evaluates valid moves for a non-king fire piece. */
	public boolean validMoveFire(int xi, int yi, int xf, int yf) {
		if (((xf == xi + 1) && (yf == yi + 1)) ||
		   ((xf == xi - 1) && (yf == yi + 1))) {
			return true;
		}
		if ((xf == xi + 2) && (yf == yi + 2)) {
			if (placement[xi+1][yi+1] != null) {
				if (placement[xi][yi].side() != placement[xi+1][yi+1].side()) {
					return true;
				}
			}
		}
		if ((xf == xi - 2) && (yf == yi + 2)) {
			if (placement[xi-1][yi+1] != null) {
				if (placement[xi][yi].side() != placement[xi-1][yi+1].side()) {
					return true;
				}
			}
		}
		return false;
	}

	/* Evaluates valid moves for a non-king water piece. */
	public boolean validMoveWater(int xi, int yi, int xf, int yf) {
		if (((xf == xi + 1) && (yf == yi - 1)) ||
		   ((xf == xi - 1) && (yf == yi - 1))) {
			return true;
		}
		if ((xf == xi + 2) && (yf == yi - 2)) {
			if (placement[xi+1][yi-1] != null) {
				if (placement[xi][yi].side() != placement[xi+1][yi-1].side()) {
					return true;
				}
			}
		}
		if ((xf == xi - 2) && (yf == yi - 2)) {
			if (placement[xi-1][yi-1] != null) {
				if (placement[xi][yi].side() != placement[xi-1][yi-1].side()) {
					return true;
				}
			}
		}
		return false;
	}

	/* Selects the square at (x,y), assuming canSelect(x,y) returns true. */
	public void select(int x, int y) {
		if (placement[x][y] != null) {
			selected_x = x;
			selected_y = y;
			has_selected = true;	
		}
		else if (placement[x][y] == null) {
			if (has_selected == true) {
				if (placement[selected_x][selected_y].isBomb()) {
					if (((x == selected_x + 2) && (y == selected_y + 2)) ||
					   ((x == selected_x - 2) && (y == selected_y + 2)) ||
					   ((x == selected_x + 2) && (y == selected_y - 2)) ||
					   ((x == selected_x - 2) && (y == selected_y - 2))) {
						if ((x > selected_x) && (y > selected_y)) {
							if (placement[x-1][y+1] != null) {
								if (placement[x-1][y+1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x-1][y+1].isShield() == false) {
										remove(x-1, y+1);
									}
								}
							}
							if (placement[x+1][y+1] != null) {
								if (placement[x+1][y+1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x+1][y+1].isShield() == false) {
										remove(x+1, y+1);
									}
								}
							}
							if (placement[x+1][y-1] != null) {
								if (placement[x+1][y-1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x+1][y-1].isShield() == false) {
										remove(x+1, y-1);
									}
								}
							}
						}
						if ((x < selected_x) && (y > selected_y)) {
							if (placement[x-1][y-1] != null) {
								if (placement[x-1][y-1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x-1][y-1].isShield() == false) {
										remove(x-1, y-1);
									}
								}
							}
							if (placement[x-1][y+1] != null) {
								if (placement[x-1][y+1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x-1][y+1].isShield() == false) {
										remove(x-1, y+1);
									}
								}
							}
							if (placement[x+1][y+1] != null) {
								if (placement[x+1][y+1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x+1][y+1].isShield() == false) {
										remove(x+1, y+1);
									}
								}
							}
						}
						if ((x > selected_x) && (y < selected_y)) {
							if (placement[x-1][y-1] != null) {
								if (placement[x-1][y-1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x-1][y-1].isShield() == false) {
										remove(x-1, y-1);
									}
								}
							}
							if (placement[x+1][y-1] != null) {
								if (placement[x+1][y-1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x+1][y-1].isShield() == false) {
										remove(x+1, y-1);
									}
								}
							}
							if (placement[x+1][y+1] != null) {
								if (placement[x+1][y+1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x+1][y+1].isShield() == false) {
										remove(x+1, y+1);
									}
								}
							}
						}
						if ((x < selected_x) && (y < selected_y)) {
							if (placement[x-1][y+1] != null) {
								if (placement[x-1][y+1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x-1][y+1].isShield() == false) {
										remove(x-1, y+1);
									}
								}
							}
							if (placement[x-1][y-1] != null) {
								if (placement[x-1][y-1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x-1][y-1].isShield() == false) {
										remove(x-1, y-1);
									}
								}
							}
							if (placement[x+1][y-1] != null) {
								if (placement[x+1][y-1].side() != placement[selected_x][selected_y].side()) {
									if (placement[x+1][y-1].isShield() == false) {
										remove(x+1, y-1);
									}
								}
							}
						}
					}
					placement[selected_x][selected_y].move(x, y);
					selected_x = x;
					selected_y = y;
					if (placement[selected_x][selected_y].hasCaptured()) {
						remove(x, y);
					}
				}
				else if (placement[selected_x][selected_y].isBomb() == false) {
					placement[selected_x][selected_y].move(x, y);
					selected_x = x;
					selected_y = y;
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				}
				has_moved = true;
			}
		}
	}

	/* Returns whether current player can end his/her turn. */
	public boolean canEndTurn() {
		return has_moved;
	}

	/* Handles switching of players and ending of a turn. */
	public void endTurn() {
		if (current_player == 0) {
			current_player = 1;
		}
		else {
			current_player = 0;
		}
		has_selected = false;
		selected_x = 0;
		selected_y = 0;
		has_moved = false;
	}

	/* Returns the winner of the game: "Fire", "Water", "No one", or null. */
	public String winner() {
		int fire_pieces = 0;
		int water_pieces = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (placement[i][j] != null) {
					if (placement[i][j].isFire()) {
						fire_pieces += 1;
					}
					else if (placement[i][j].isFire() == false) {
						water_pieces += 1;
					}
				}
			}
		}
		if (fire_pieces > water_pieces) {
			return "Fire";
		}
		else if (fire_pieces < water_pieces) {
			return "Water";
		}
		else if ((fire_pieces == 0) && (water_pieces == 0)) {
			return "No one";
		}
		else {
			return null;
		}
	}

	/* Draws an N x N board. */
	private void drawBoard(int N) {
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else				  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (placement[i][j] != null) {
					if (pieceAt(i, j).isFire()) {
						if (pieceAt(i, j).isBomb()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
						}
						else if (pieceAt(i, j).isShield()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
						}
					}
					else if (pieceAt(i, j).isFire() == false) {
						if (pieceAt(i, j).isBomb()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
						}
						else if (pieceAt(i, j).isShield()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
		}
		if (has_selected == true) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(selected_x + .5, selected_y + .5, .5);
			if (placement[selected_x][selected_y] != null) {
				if (placement[selected_x][selected_y].isFire()) {
					if (placement[selected_x][selected_y].isBomb()) {
						StdDrawPlus.picture(selected_x + 0.5, selected_y + 0.5, "img/bomb-fire.png", 1, 1);
					}
					else if (placement[selected_x][selected_y].isShield()) {
						StdDrawPlus.picture(selected_x + 0.5, selected_y + 0.5, "img/shield-fire.png", 1, 1);
					}
					else {
						StdDrawPlus.picture(selected_x + 0.5, selected_y + 0.5, "img/pawn-fire.png", 1, 1);
					}
				}
				if (placement[selected_x][selected_y].isFire() == false) {
					if (placement[selected_x][selected_y].isBomb()) {
						StdDrawPlus.picture(selected_x + 0.5, selected_y + 0.5, "img/bomb-water.png", 1, 1);
					}
					else if (placement[selected_x][selected_y].isShield()) {
						StdDrawPlus.picture(selected_x + 0.5, selected_y + 0.5, "img/shield-water.png", 1, 1);
					}
					else {
						StdDrawPlus.picture(selected_x + 0.5, selected_y + 0.5, "img/pawn-water.png", 1, 1);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board checkers = new Board(false);
		while(true) {
			checkers.drawBoard(N);
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
		        	double y = StdDrawPlus.mouseY();
				if (checkers.canSelect((int) x, (int) y)) {
					checkers.select((int) x, (int) y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (checkers.canEndTurn()) {
					checkers.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
	}
}
