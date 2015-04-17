
public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private boolean[][] if_pieces = new boolean[8][8];
	private String type;
	private String king;
	private boolean fire_turn = true;
	private boolean has_selected = false;
	private int select_x;
	private int select_y;
	private boolean has_moved = false;
	private Piece selected;
	private int fire_count;
	private int water_count;

	public static void main(String args[]) {
		Board b = new Board(false);
		while (true) {
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				int c = (int) x;
				int d = (int) y;
				if (b.canSelect(c, d)) {
					b.select(c, d);
				}
			}
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			b.draw();
		StdDrawPlus.show(100);
		}
	}

	private void draw() {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		for (int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else			  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				if (select_x == i && select_y == j && has_selected) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				StdDrawPlus.filledSquare(i +0.5, j + 0.5, 0.5);
				if (if_pieces[i][j]) {
					pieceDraw(pieces[i][j], i, j);
				}
			}
		}
	}

	private void pieceMaker(Board b) {
		String[] type = {"pawn", "shield", "bomb"};
		for (int i=0;i<3;i++) {
			for (int j=0;j<8;j+=2) {
				if (i%2 ==  0) {
					if_pieces[j][i] = true;
					pieces[j][i] = new Piece(true, b, j, i, type[i]);
					fire_count += 1;
				} else {
					if_pieces[j+1][i] = true;
					pieces[j+1][i] = new Piece(true, b, j+1, i, type[i]);
					fire_count += 1;
				}
			}
		}
		for (int k=7;k>4;k-=1) {
			for (int m=0;m<8;m+=2) {
				if (k%2 == 0) {
					if_pieces[m][k] = true;
					pieces[m][k] = new Piece(false, b, m, k, type[7-k]);
					water_count += 1;
				} else {
					if_pieces[m+1][k] = true;
					pieces[m+1][k] = new Piece(false, b, m+1, k, type[7-k]);
					water_count += 1;
				}
			}
		}
	}

	private void pieceDraw(Piece p, int i, int j) {
		if (p.isFire()) {		
			type = "fire";
		} else {
			type = "water";
		}
		if (p.isKing()) {
			king = "-crowned";
		} else {
			king = "";
		}
		if (p.isBomb()) {
			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-" + type + king + ".png", 1, 1);
		} else if (p.isShield()) {
			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-" + type + king + ".png", 1, 1);							
		} else {
			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-" + type + king + ".png", 1, 1);
		}
	}

	private boolean range(int a) {
		return 0 <= a && a < 8;
	}

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			pieceMaker(this);
		}
	}

	public Piece pieceAt(int x, int y) {
		if (range(x) && range(y)) {
			if (if_pieces[x][y]) {
				return pieces[x][y];
			}
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (!if_pieces[x][y]) {
			if (has_selected && !has_moved) {
				if (selected.hasCaptured() && validMove(select_x, select_y, x, y)) {
					return true;
				} else if (validMove(select_x, select_y, x, y)) {
					return true;
				} else {
					return false;
				}
			}
		} else if ((fire_turn && pieces[x][y].isFire()) || (!fire_turn && !pieces[x][y].isFire())) {
				if (!has_selected) {
					return true;
				} else if (has_selected && !canEndTurn()) {
					return true;
				} else {
					return false;
				}
			}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		boolean x_move_right = (xf == xi + 1) && range(xf);
		boolean x_move_left = (xf == xi - 1) && range(xf);
		boolean x_capture_right = (xf == xi + 2) && range(xf);
		boolean x_capture_left = (xf == xi - 2) && range(xf);
		int x_right = xi + 1;
		int x_left = xi - 1;
		int y_fire = yi + 1;
		int y_water = yi - 1;
		boolean y_move_fire = ((yf == yi + 1)) && range(yf);
		boolean y_move_water = ((yf == yi - 1)) && range(yf);
		boolean y_capture_fire = (yf == yi + 2) && range(yf);
		boolean y_capture_water = (yf == yi - 2) && range(yf);
		if (!if_pieces[xf][yf]) {
			if (pieces[xi][yi].isKing()) {
				if ((xf == x_right) || (xf == x_left) && !pieces[xi][yi].hasCaptured()) {
					if ((x_move_right || x_move_left) && (y_move_fire || y_move_water)) {
						return true;
					} else {
						return false;
					}
				} else if (x_capture_right || x_capture_left) {
					if (y_capture_fire) {
						if (if_pieces[x_right][y_fire]) {
							if (!(pieces[x_right][y_fire].isFire() == pieces[xi][yi].isFire())) {
								return true;
							} else {
								return false;
							}
						} else if (if_pieces[x_left][y_fire]) {
							if (!(pieces[x_left][y_fire].isFire() == pieces[xi][yi].isFire())) {
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else if (y_capture_water) {
						if (if_pieces[x_right][y_water]) {
							if (!(pieces[x_right][y_water].isFire() == pieces[xi][yi].isFire())) {
								return true;
							} else {
								return false;
							}
						} else if (if_pieces[x_left][y_water]) {
							if (!(pieces[x_left][y_fire].isFire() == pieces[xi][yi].isFire())) {
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
						return false;
				}
			} else if ((xf == x_right || xf == x_left) && !pieces[xi][yi].hasCaptured()) {
				if (pieces[xi][yi].isFire() && x_move_right && y_move_fire) {
					return true;
				} else if (pieces[xi][yi].isFire() && x_move_left && y_move_fire) {
					return true;
				} else if (!pieces[xi][yi].isFire() && x_move_right && y_move_water) {
					return true;
				} else if (!pieces[xi][yi].isFire() && x_move_left && y_move_water) {
					return true;
				} else {
					return false;
				}
			} else if (y_capture_fire && fire_turn) {
				if (x_capture_right) {
					if (if_pieces[x_right][y_fire] && pieces[xi][yi].isFire()) {
						if (!pieces[x_right][y_fire].isFire()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (x_capture_left) {
					if (if_pieces[x_left][y_fire] && pieces[xi][yi].isFire() && x_capture_left) {
						if (!pieces[x_left][y_fire].isFire()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if (y_capture_water && !fire_turn) {
				if (x_capture_right) {
					if (if_pieces[x_right][y_water] && !pieces[xi][yi].isFire()) {
						if (pieces[x_right][y_water].isFire()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (x_capture_left) {
					if (if_pieces[x_left][y_water] && !pieces[xi][yi].isFire() && x_capture_left) {
						if (pieces[x_left][y_water].isFire()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void select(int x, int y) {
		select_x = x;
		select_y = y;
		if (if_pieces[select_x][select_y]) {
			selected = pieces[select_x][select_y];
			has_selected = true;
		}
		if (selected != null && !has_moved && !if_pieces[x][y]) {
			selected.move(x, y);
			if (selected.hasCaptured() && if_pieces[x][y]) {
				has_moved = false;
			} else {
				has_moved = true;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		for (int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if (pieces[i][j] == p) {
					pieces[i][j] = null;
					if_pieces[i][j] = false;
					if (p != null) {
						if (p.isFire()) {
							fire_count -= 1;
						} else {
							water_count -= 1;
						}
					}
				}
			}
		}
		if (range(x) && range(y) && p != null) {
			if (if_pieces[x][y]) {
				remove(x, y);
			} else if (p.isFire()) {
				fire_count += 1;
			} else {
				water_count += 1;
			}
			pieces[x][y] = p;
			if_pieces[x][y] = true;
		}
	}

	public Piece remove(int x, int y) {
		if (x < 8 && y < 8) {
			if (if_pieces[x][y]) {
				if (pieces[x][y].isFire()) {
					fire_count -= 1;
				} else {
					water_count -= 1;
				}
				if_pieces[x][y] = false;
				Piece temp = pieces[x][y];
				pieces[x][y] = null;
				if (selected == temp) {
					has_moved = true;
				}
				return temp;
			}
			System.out.println("No piece at coordinates.");
			return null;
		}
		System.out.println("Coordinates out of bounds.");
		return null;
	}

	public boolean canEndTurn() {
		if (has_moved) {
			return true;
		} else if (has_selected) {
			if (!if_pieces[select_x][select_y]) {
				return true;
			} else if (pieces[select_x][select_y].hasCaptured()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void endTurn() {
		fire_turn = !fire_turn;
		has_selected = false;
		has_moved = false;
		selected = null;
		if (if_pieces[select_x][select_y]) {
			pieces[select_x][select_y].doneCapturing();
		}
	}

	public String winner() {
		if ((fire_count == 0) && (water_count == 0)) {
			return "No one";
		} else if ((fire_count > 0) && (water_count == 0)) {
			return "Fire";
		} else if ((fire_count == 0) && (water_count > 0)) {
			return "Water";
		} else {
			return null;
		}
	}

}