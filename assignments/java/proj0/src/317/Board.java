public class Board {
	private static int bound = 8, player = 0;
	private static Piece[][] pieces;
	private int selected_x = -1, selected_y = -1;
	private static boolean moved = false;

	public static void main (String	args[]) {
		StdDrawPlus.setXscale(0, bound);
        StdDrawPlus.setYscale(0, bound);
        Board current = new Board(false);
		while (true) {
			current.drawBoard(bound);
            if (current.winner() != null) {
            	System.out.println(current.winner());
            	return;
            }
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int a = (int)Math.floor(x);
                int b = (int)Math.floor(y);
                if ((a > (bound - 1)) || (b > (bound - 1)) || (a < 0) || (b < 0)) {
                	System.out.println("Cr0ssnig tHe buondray iS A b&d hibat");
	            } else {
	            	if (current.canSelect(a, b)) {
	            		System.out.println(current.canSelect(a, b));
	            		current.select(a, b);
	                	System.out.println("selected " + a + " " + b);
		            }   
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	System.out.println("space pressed");
            	System.out.println(moved);
            	if (current.canEndTurn()) {
            		current.endTurn();
            	}
            }
            StdDrawPlus.show(10);
		}
	}

	private void drawBoard(int bound) {
		for (int i = 0; i < bound; i++) {
			for (int j = 0; j < bound; j++) {
				if ((i == selected_x) && (j == selected_y)) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	String location = image(this.pieces[i][j]);
                	StdDrawPlus.picture(i + .5, j + .5, location, 1, 1);
                }
            }
        }
	}

	private static String image(Piece p) {
		String image = "";
		if (p.isBomb()) {
			image = "bomb-";
		} else if (p.isShield()) {
			image = "shield-";
		} else {
			image = "pawn-";
		} 
		if (p.isFire()) {
			image += "fire";
		} else {
			image += "water";
		}
		if (p.isKing()) {
			image += "-crowned";
		}
		return "img/" + image + ".png";
	}

	public Piece pieceAt(int x, int y) {
		if (outBounds(x, y)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			Piece select = pieceAt(x, y);
			if (select.side() == player) {
				if (moved) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			if (selected_x != -1) 
				return (validMove(selected_x, selected_y, x, y));
			return false;
		}
	}

	public void select(int x, int y) {
		if ((pieces[x][y]) == null) {
			pieces[x][y] = pieces[selected_x][selected_y];
			pieces[selected_x][selected_y].move(x, y);
			moved = true;
		}
		selected_x = x;
		selected_y = y;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieces[xf][yf] != null) {
			return false;
		} else {
			int hori = Math.abs(xf - xi);
			int verti = Math.abs(yf - yi);
			if ((hori != verti) || (hori > 2) || (verti > 2)) {
				return false;
			} else if (hori == 1) {
				if (moved == true)
					return false;
				return valid_1(pieces[xi][yi], ((yf - yi) > 0));
			} else if (hori == 2) {
				Piece middle = pieces[(xi + xf) / 2][(yi + yf) / 2];
				if (middle == null) 
					return false;
				return valid_2(pieces[xi][yi], middle.isFire(), (yf - yi) > 0);
			} else {
				return false;
			}
		}
	}

	private boolean valid_1(Piece current, boolean verti_posi) {
		if (current.isKing()) {
			return true;
		} else {
			boolean is_fire = current.isFire();
			if (is_fire == verti_posi)
				return true;
			return false;
		}
	}

	private boolean valid_2(Piece current, boolean middle_fire, boolean verti_posi) {
		boolean is_fire = current.isFire();
		if (current.isKing()) {
			if (middle_fire != is_fire) 
				return true;
			return false;
		} else {
			if ((is_fire == verti_posi) && (middle_fire != is_fire))
				return true;
			return false;
		}
	}

	public void place(Piece p, int x, int y) {
		if (outBounds(x, y) || (p == null)) {
			return;
		} else {
			pieces[x][y] = p;
		}
	}


	public Piece remove(int x, int y) {
		if (outBounds(x, y)) {
			System.out.println("remove position" + x + "," + y + "out of bound");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("no piece to remove at " + x + "," + y);
			return null;
		} else {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}

	private boolean outBounds(int x, int y) {
		if ((x >= bound) || (y >= bound) || (x < 0) || (y < 0)) return true;
		else                            return false;
	}

	public boolean canEndTurn() {
		if (moved == true)
			return true;
		return false;
	}

	public void endTurn() {
		player = 1 - player;
		moved = false;
		selected_x = -1;
		selected_y = -1;
		for (int i = 0; i < bound; i++) {
			for (int j = 0; j < bound; j++) {
				if (pieces[i][j] != null) {
					pieces[i][j].doneCapturing();
				}
			}
		}
	}

	public String winner() {
		int record = 61;
		for (int i = 0; i < bound; i++) {
			for (int j = 0; j < bound; j++) {
				if (pieces[i][j] != null) {
					int current = pieces[i][j].side();
					if (record == 61) {
						record = current;
					} else {
						if (record != current) {
							return null;
						}
					}
				}
			}
		}
		if (record == 1) {
			return "Water";
		} else if (record == 0) {
			return "Fire";
		} else {
			return "No one";
		}
	}


	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[bound][bound];
		this.moved = false;
		if (shouldBeEmpty != true) {
			Piece water_pawn_1 = new Piece(false, this, 1, 7, "pawn");
			Piece water_pawn_2 = new Piece(false, this, 3, 7, "pawn");
			Piece water_pawn_3 = new Piece(false, this, 5, 7, "pawn");
			Piece water_pawn_4 = new Piece(false, this, 7, 7, "pawn");
			Piece fire_pawn_1 = new Piece(true, this, 0, 0, "pawn");
			Piece fire_pawn_2 = new Piece(true, this, 2, 0, "pawn");
			Piece fire_pawn_3 = new Piece(true, this, 4, 0, "pawn");
			Piece fire_pawn_4 = new Piece(true, this, 6, 0, "pawn");
			Piece water_shield_1 = new Piece(false, this, 0, 6, "shield");
			Piece water_shield_2 = new Piece(false, this, 2, 6, "shield");
			Piece water_shield_3 = new Piece(false, this, 4, 6, "shield");
			Piece water_shield_4 = new Piece(false, this, 6, 6, "shield");
			Piece fire_shield_1 = new Piece(true, this, 1, 1, "shield");
			Piece fire_shield_2 = new Piece(true, this, 3, 1, "shield");
			Piece fire_shield_3 = new Piece(true, this, 5, 1, "shield");
			Piece fire_shield_4 = new Piece(true, this, 7, 1, "shield");
			Piece water_bomb_1 = new Piece(false, this, 1, 5, "bomb");
			Piece water_bomb_2 = new Piece(false, this, 3, 5, "bomb");
			Piece water_bomb_3 = new Piece(false, this, 5, 5, "bomb");
			Piece water_bomb_4 = new Piece(false, this, 7, 5, "bomb");
			Piece fire_bomb_1 = new Piece(true, this, 0, 2, "bomb");
			Piece fire_bomb_2 = new Piece(true, this, 2, 2, "bomb");
			Piece fire_bomb_3 = new Piece(true, this, 4, 2, "bomb");
			Piece fire_bomb_4 = new Piece(true, this, 6, 2, "bomb");
			place(water_pawn_1, 1, 7);
			place(water_pawn_2, 3, 7);
			place(water_pawn_3, 5, 7);
			place(water_pawn_4, 7, 7);
			place(fire_pawn_1, 0, 0);
			place(fire_pawn_2, 2, 0);
			place(fire_pawn_3, 4, 0);
			place(fire_pawn_4, 6, 0);
			place(water_shield_1, 0, 6);
			place(water_shield_2, 2, 6);
			place(water_shield_3, 4, 6);
			place(water_shield_4, 6, 6);
			place(fire_shield_1, 1, 1);
			place(fire_shield_2, 3, 1);
			place(fire_shield_3, 5, 1);
			place(fire_shield_4, 7, 1);
			place(water_bomb_1, 1, 5);
			place(water_bomb_2, 3, 5);
			place(water_bomb_3, 5, 5);
			place(water_bomb_4, 7, 5);
			place(fire_bomb_1, 0, 2);
			place(fire_bomb_2, 2, 2);
			place(fire_bomb_3, 4, 2);
			place(fire_bomb_4, 6, 2);
		}
	}
}