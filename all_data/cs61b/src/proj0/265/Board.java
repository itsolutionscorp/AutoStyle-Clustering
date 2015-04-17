
public class Board {

	private int player;
	private Piece[][] pieces;
	private Piece selected;
	private int selected_x;
	private int selected_y;
	private boolean moved;
	private int fire_counter;
	private int water_counter;

	public static void main(String args[]) {
		Board board = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		while (board.fire_counter > 0 && board.water_counter > 0) {
			if (StdDrawPlus.mousePressed()) {
				double x_val = StdDrawPlus.mouseX();
				double y_val = StdDrawPlus.mouseY();
				if (board.canSelect( (int) x_val, (int) y_val)) {
					board.select((int) x_val, (int) y_val);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) {
					board.endTurn();
				}
			}
			board.drawBoard();
			StdDrawPlus.show(10);
		}
		System.out.println(board.winner());
	}

	private void drawBoard() {
	    for (int i = 0; i < 8; i += 1) {
	        for (int j = 0; j < 8; j += 1) {
	            if (selected != null && selected_x == i && selected_y == j) {
	            	StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
	            }
	            else if ((i + j) % 2 == 0) {
	                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            } else {              
            		StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	if (pieces[i][j] != null) {
	            	if (pieces[i][j].isFire()) {
	            		if (pieces[i][j].isKing() && pieces[i][j].isBomb()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1); 
	            		}
	            		else if (pieces[i][j].isKing() && pieces[i][j].isShield()){
	            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	            		}
	            		else if (pieces[i][j].isKing()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1); 
	            		}
	            		else if (pieces[i][j].isBomb()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1); 
	            		}
	            		else if (pieces[i][j].isShield()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1); 
	            		} else {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1); 
	            		}
	            	} else {
	            		if (pieces[i][j].isKing() && pieces[i][j].isBomb()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1); 
	            		}
	            		else if (pieces[i][j].isKing() && pieces[i][j].isShield()){
	            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	            		}
	            		else if (pieces[i][j].isKing()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1); 
	            		}
	            		else if (pieces[i][j].isBomb()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1); 
	            		}
	            		else if (pieces[i][j].isShield()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1); 
	            		} else {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1); 
	            		}
	            	}
	            }
            }
        }
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		player = 0;
		selected = null;
		moved = false;
		fire_counter = 0;
		water_counter = 0;
		if (!shouldBeEmpty) {
			for (int x = 0; x < 8; x += 1) {
				for (int y = 0; y < 8; y += 1) {
					if ((y == 0) && (x % 2 == 0)) {
						place(new Piece(true, this, x, y, "pawn"), x, y);
					}
					if ((y == 1) && (x % 2 != 0)) {
						place(new Piece(true, this, x, y, "shield"), x, y);
					}
					if ((y == 2) && (x % 2 == 0)) {
						place(new Piece(true, this, x, y, "bomb"), x, y);
					}
					if ((y == 5) && (x % 2 != 0)) {
						place(new Piece(false, this, x, y, "bomb"), x, y);
					}
					if ((y == 6) && (x % 2 == 0)) {
						place(new Piece(false, this, x, y, "shield"), x, y);
					}
					if ((y == 7) && (x % 2 != 0)) {
						place(new Piece(false, this, x, y, "pawn"), x, y);
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		return pieces[x][y];
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) == null && pieceAt(xi, yi) != null) {
			if (!pieceAt(xi, yi).isKing() && pieceAt(xi, yi).isFire() && yf < yi) {
				return false;
			} 
			else if (!pieceAt(xi, yi).isKing() && !pieceAt(xi, yi).isFire() && yf > yi) {
				return false;
			} 
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) {
				return true;
			} 
			else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
				int temp_x = (xi + xf)/2;
				int temp_y = (yi + yf)/2;
				if (pieceAt(temp_x, temp_y) != null) {
					if (pieceAt(temp_x, temp_y).side() != pieceAt(xi, yi).side()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		if (pieces[x][y] != null && pieces[x][y].side() == player) {
			if (selected == null || (selected != null && moved == false)) {
				return true;
			}
		} 
		else if (selected != null && validMove(selected_x, selected_y, x, y)) {
			if (!moved) {
				return true;
			}
			else if (selected.hasCaptured() && Math.abs(selected_y - y) > 1) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieces[x][y] == null) {
			selected.move(x, y);
			selected_x = x;
			selected_y = y;
			moved = true;
		} else {
			selected = pieces[x][y];
			selected_x = x;
			selected_y = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (p != null && (x >= 0 && x <= 7 && y >= 0 && y <= 7)) {
			for (int i = 0; i < 8; i += 1) {
				for (int j = 0; j < 8; j += 1) {
					if (pieces[i][j] == p) {
						remove(i, j);
					}
				}
			}
			pieces[x][y] = p;
			if (p.isFire()) {
				fire_counter += 1;
			} else {
				water_counter += 1;
			}
		}
	}

	public Piece remove(int x, int y) {
		if ((x < 0 && x > 7) || (y < 0 && y > 7)) {
			System.out.println("out of bounds");
			return null;
		} 
		else if (pieces[x][y] == null) {
			System.out.println("no piece here");
			return null;
		} else {
			Piece pop = pieces[x][y];
			pieces[x][y] = null;
			if (pop.isFire()) {
				fire_counter -= 1;
			} else {
				water_counter -= 1;
			}
			return pop;
		}
	}
 
	public boolean canEndTurn() {
		if (moved) {
			return true;
		}
		return false;
	}

	private void switchPlayer() {
		if (player == 0) {
			player = 1;
		} else {
			player = 0;
		}	
	}

	public void endTurn() {
		switchPlayer();
		selected.doneCapturing();
		selected = null;
		moved = false;
	}

	public String winner() {
		if (fire_counter > 0 && water_counter > 0) {
			return null;
		}
		else if (fire_counter == water_counter) {
			return "No one";
		}
		else if (fire_counter > 0) {
			return "Fire";
		} else {
			return "Water";
		}
	}
}