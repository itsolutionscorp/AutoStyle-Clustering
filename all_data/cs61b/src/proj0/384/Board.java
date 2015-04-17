public class Board {

	private boolean shouldBeEmpty;
	private int board_num;
	private Piece[][] pieces;
	private boolean fire_turn; 
	private Piece selected_piece;
	private int spx;
	private int spy;
	private int num_moves;
	private int[] winner_check;


	public Board (boolean shouldBeEmpty_a) {
		shouldBeEmpty = shouldBeEmpty_a;
		board_num = 8;
		pieces = new Piece[board_num][board_num];
		fire_turn = true;
		num_moves = 0; 
		winner_check = new int[2];
		if (!shouldBeEmpty) {
			pieceInitializer(board_num);
		}
	}

	public static void main (String [] args) {
		Board b = new Board(false);
		while(true) {
			b.drawBoard(8);
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (((int) x < 8) && ((int) y < 8) && x >= 0 && y >= 0) {
					if (b.canSelect((int) x, (int) y)) {
						b.select((int) x, (int) y);
					}
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
					b.endTurn();
			}
			StdDrawPlus.show(10);
		}
	}

	public String winner() {
		winner_check[0] = 0;
		winner_check[1] = 0;
		for (int i = 0; i < board_num; i++) {
			for (int j = 0; j < board_num; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire() && winner_check[0] == 0) {
						winner_check[0] = 1;
					} else if ((!(pieceAt(i, j).isFire())) && winner_check[1] == 0) {
						winner_check[1] = 1;
					}

				}
			}
		}
		if (winner_check[0] == 0 && winner_check[1] == 0) {
			return "No one";
		}
		if (winner_check[0] == 1 && winner_check[1] == 0) {
			winner_check[0] = 0;
			return "Fire";
		}
		if (winner_check[0] == 0 && winner_check[1] == 1) {
			winner_check[1] = 0;
			return "Water";
		}
 		return null;
	}

	public void endTurn() {
		if (canEndTurn()) {
			selected_piece.doneCapturing();
			selected_piece = null;
			fire_turn = !(fire_turn);
			num_moves = 0;
		} else {
			return;
		}
	}

	public boolean canEndTurn() {
		return (num_moves > 0);
	}

	public void select(int x, int y) {
		if ((pieceAt(x, y) != null) && (num_moves == 0)) {
			selected_piece = pieceAt(x, y);
			spx = x;
			spy = y;
		} else {
			selected_piece.move(x, y);
			spx = x;
			spy = y;
			num_moves = num_moves + 1;
		}
	}

	public boolean canSelect(int x, int y) {
		//checks if out of bounds
		if (x < 0 || x >= board_num || y < 0 || y >= board_num) {
			return false;
		}
		//checks if piece has been selected and proposed spot to move is valid, not including capture
		if (pieces[x][y] == null && selected_piece != null && (!(selected_piece.isKing()))) {
			//fire
			if (selected_piece.isFire()) {
				return ((((x == spx + 1 || x == spx - 1) && 
					(y == spy + 1) && num_moves == 0)) || checkForCapture(x, y));
			//water
			} else {
				return ((((x == spx + 1 || x == spx - 1) && 
					(y == spy - 1) && num_moves == 0)) || checkForCapture(x, y));			
			}
		}
		//checks for kings movement
		if (pieces[x][y] == null && selected_piece != null && (selected_piece.isKing())) {
			return (((x == spx + 1 || x == spx - 1) && 
				(y == spy - 1 || y == spy + 1) && num_moves == 0) || checkForCapture(x, y));
		} 
		//checks if piece is selected
		return (pieces[x][y] != null && pieceAt(x, y).isFire() == fire_turn && num_moves == 0);
	}

	private boolean checkForCapture(int x, int y) {
		if (!(selected_piece.hasCaptured()) && (num_moves > 0)) {
			return false;
		}
		int victim_x = (int) ((spx + x)/2);
		int victim_y = (int) ((spy + y)/2);
		//checks if player is choosing a nonvalid spot to move
		if (!(selected_piece.isKing())) {
			if (selected_piece.isFire()) {
				if (!((spx + 2 == x || spx - 2 == x) && spy + 2 == y)) {
					return false;
				}
			} else if (!(selected_piece.isFire())) {
				if (!((spx + 2 == x || spx - 2 == x) && spy - 2 == y)) {
					return false;
				}	
			}
		}
		//checks for non-King pieces attempting to move backwards.
		if (!(selected_piece.isKing())) {
			if (selected_piece.isFire()) {
				if (victim_y < spy) {
					return false;
				}
			} else {
				if (victim_y > spy) {
					return false;
				}
			}
		}
		//checks if potential capture spot is empty or the piece is on the same side as selected piece
		if (pieceAt(victim_x, victim_y) == null || pieceAt(victim_x, victim_y).isFire() == fire_turn) {
			return false;
		} else {
			return true; 
		}
	}

	public Piece remove(int x, int y) {
		if (x < 0 || x > (board_num - 1) || y < 0 || y > (board_num - 1)) {
			System.out.println("Coordinates are out of range."); 
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("No piece exists on this coordinate.");
			return null;
		}
		Piece removed_piece = pieces[x][y];
		pieces[x][y] = null; 
		return removed_piece;
	}

	public void place (Piece p, int x, int y) {
		if (x < 0 || x > (board_num - 1) || y < 0 || y > (board_num - 1)) {
			return; 
		}		
		if (pieceAt(x, y) != p && pieceAt(x, y) != null) {
			remove(x, y);
		}
		for (int i = 0; i < board_num; i++) {
			for (int j = 0; j < board_num; j++) {
				Piece temp = pieces[i][j];
				if (p == temp) {
					pieces[i][j] = null;
				}
			}
		}
		pieces[x][y] = p;
	}

	public Piece pieceAt (int x, int y) {
		if (x < board_num && y < board_num && x >= 0 && y >= 0) {
			return pieces[x][y];
		} 
		return null;
	}

	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, board_num);
		StdDrawPlus.setYscale(0, board_num);
		//2D loop scans columns 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null && !shouldBeEmpty) {
					Piece p = pieceAt(i, j);
					if (p.isFire()) {
						if (p.isKing()) {
							if (p.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							} else if (p.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
						} else {
							if (p.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							} else if (p.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}	
						}
					} else {
						if (p.isKing()) {
							if (p.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							} else if (p.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
						} else {
							if (p.isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							} else if (p.isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}	
						}
					}

				}
			}
		}
	}

	private void pieceInitializer(int N) {
		//fire pawns
		for (int i = 0; i < N; i += 2) {
			pieces[i][0] = new Piece(true, this, i, 0, "pawn"); 
		}
		//fire shields
		for (int i = 1; i <= N; i += 2) {
			pieces[i][1] = new Piece(true, this, i, 1, "shield");
		}
		//fire bombs
		for (int i = 0; i < N; i += 2) {
			pieces[i][2] = new Piece(true, this, i, 2, "bomb");
		}
		//water pawns
		for (int i = 1; i <= N; i += 2) {
			pieces[i][N - 1] = new Piece(false, this, i, N - 1, "pawn");
		}
		//water shields
		for (int i = 0; i < N; i += 2) {
			pieces[i][N - 2] = new Piece(false, this, i, N - 2, "shield");
		}
		//water bombs
		for (int i = 1; i <= N; i += 2) {
			pieces[i][N - 3] = new Piece(false, this, i, N - 3, "bomb");
		}
	}
	
}