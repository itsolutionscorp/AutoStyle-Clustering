public class Board {
	private Piece[][] table = new Piece[8][8];
	private String player;
	private boolean hasselected;
	private boolean hasmoved;
	private int localm;
	private int localn;


	public static void main(String[] args) {
		int N = 8;
		Board checker = new Board(false);

		while(checker.winner() == null) {
			StdDrawPlus.setXscale(0, N);
			StdDrawPlus.setYscale(0, N);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					if (checker.table[i][j] != null) {
						if (checker.table[i][j].isFire() == true) {
							if (checker.table[i][j].isBomb() == true) {
								if (checker.table[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
								} 
							} else if (checker.table[i][j].isShield() == true) {
								if (checker.table[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
								}
							} else {
								if (checker.table[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
								}
							}
						} else {
							if (checker.table[i][j].isBomb() == true) {
								if (checker.table[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
								}
							} else if (checker.table[i][j].isShield() == true) {
								if (checker.table[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);	
								}
							} else {
								if (checker.table[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
								}
							}
						}
					}
				}
			}
			StdDrawPlus.show(30);

			if (StdDrawPlus.mousePressed() == true) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (checker.canSelect(x, y) == true) {
					checker.select(x, y);
				}
			} else if (StdDrawPlus.isSpacePressed() == true) {
				if (checker.canEndTurn() == true){
					checker.endTurn();
				}
			}
		}
		System.out.println(checker.winner()); /** Help from Jiayuan Hu */
	}
	/** Help from Jiayuan Hu */
	public Board(boolean shouldBeEmpty) {
		player = "player1";
		hasselected = false;
		hasmoved = false;
		if (shouldBeEmpty == false) {			
			for (int x = 0; x < 8; x = x + 2) {
				this.table[x][0] = new Piece(true, this, x, 0, "pawn");
				this.table[(x + 1)][1] = new Piece(true, this, x + 1, 1, "shield");
				this.table[x][2] = new Piece(true, this, x, 2, "bomb");
				this.table[(x + 1)][5] = new Piece(false, this, x + 1, 5, "bomb");
				this.table[x][6] = new Piece(false, this, x, 6, "shield");
				this.table[(x + 1)][7] = new Piece(false, this, x + 1, 7, "pawn");
    		}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			if (table[x][y] != null) {
				return table[x][y];
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			if (p != null) {
				this.table[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y) {
		if (x > 8 || x < 0 || y > 8 || y < 0) {
			System.out.println("Out of bound!");
			return null;
		} else if (table[x][y] == null) {
			System.out.println("No piece exist here!");
			return null;
		}
		Piece temp = table[x][y];
		table[x][y] = null;
		return temp;
	}
	/** Idea help from Jiayuan Hu */
	public boolean canSelect(int x, int y) {
		if (table[x][y] != null) {
			if (((player == "player1") && (table[x][y].isFire() == true)) || ((player == "player2") && (table[x][y].isFire() == false))) {
				if ((hasselected == false) || (hasmoved == false)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (table[x][y] == null) {
			if ((hasselected == true) && (hasmoved == false) && (validmove(localm, localn, x, y) == true)) {
				return true;
			}
			// else if ((hasselected == true) && (table[localm][localn] != null) && (table[localm][localn].hasCaptured() == true) && (validmove(localm, localn, x, y) == true)) {
			else if ((hasselected == true) && (table[localm][localn] != null) && (table[localm][localn].hasCaptured() == true) && (validmove(localm, localn, x, y) == true) && (((x - localm) * (x - localm) + (y - localn) * (y - localn)) == 8)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/** Help from Jiayuan Hu */	
	private boolean validmove(int xi, int yi, int xf, int yf) {
		if (xi >= 0 && xi < 8 && yi >= 0 && yi < 8 && xf >= 0 && xf < 8 && yf >= 0 && yf < 8) {
			if (table[xi][yi].isKing() == true && table[xi][yi] != null) { 
				if (((xi - xf) * (xi - xf) + (yi - yf) * (yi - yf)) == 2) {
					if (table[xf][yf] == null) {
						return true;
					} else {
						return false;
					}
				} else if (((xi - xf) * (xi - xf) + (yi - yf) * (yi - yf)) == 8) {
					if (table[(xi + xf) >>> 1][(yi + yf) >>> 1] != null && table[xi][yi].side() != table[(xi + xf) >>> 1][(yi + yf) >>> 1].side()) {
						if (table[xf][yf] == null) {
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
			} else if (table[xi][yi].side() == 0 && table[xi][yi] != null) {
				if (((xi - xf) * (xi - xf) + (yi - yf) * (yi - yf)) == 2 && yf - yi >= 0) {
					if (table[xf][yf] == null) {
						return true;
					} else {
						return false;
					}
				} else if ((((xi - xf) * (xi - xf) + (yi - yf) * (yi - yf)) == 8) && yf - yi >= 0) {
					if (table[(xi + xf) >>> 1][(yi + yf) >>> 1] != null && table[xi][yi].side() != table[(xi + xf) >>> 1][(yi + yf) >>> 1].side()) {
						if (table[xf][yf] == null) {
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
			} else if (table[xi][yi].side() == 1 && table[xi][yi] != null) {
				if (((xi - xf) * (xi - xf) + (yi - yf) * (yi - yf)) == 2 && yf - yi <= 0) {
					if (table[xf][yf] == null) {
						return true;
					} else {
						return false;
					}
				} else if (((xi - xf) * (xi - xf) + (yi - yf) * (yi - yf)) == 8 && yf - yi <= 0) {
					if (table[(xi + xf) >>> 1][(yi + yf) >>> 1] != null && table[xi][yi].side() != table[(xi + xf) >>> 1][(yi + yf) >>> 1].side()) {
						if (table[xf][yf] == null) {
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
		if (table[x][y] != null) {
			hasselected = true;
		} else {
			hasmoved = true;
			table[localm][localn].move(x, y);
		}
		localm = x;
		localn = y;
	}

	public boolean canEndTurn() {
		if (((table[localm][localn] != null) && (table[localm][localn].hasCaptured() == true)) || hasmoved == true) {
			return true;
		} else {
			return false;
		}
	}
	/** Help from Jiayuan Hu */
	public void endTurn() {
		if (player == "player1") {
			player = "player2";
		} else {
			player = "player1";
		}
		hasmoved = false;
		hasselected = false;
		if (table[localm][localn] != null) {
			table[localm][localn].doneCapturing();
		}
	}

	public String winner() {
		String type = "No one";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (table[i][j] != null) {
					if (table[i][j].side() == 0 && type == "No one") {
						type = "Fire";
					} else if (table[i][j].side() == 0 && type == "Water") {
						type = null;
					} else if (table[i][j].side() == 1 && type == "No one") {
						type = "Water";
					} else if (table[i][j].side() == 1 && type == "Fire") {
						type = null;
					}
				}
			}
		}
		if (type == "Water") {
			return "Water";
		} else if (type == "Fire") {
			return "Fire";
		} else if (type == "No one") {
			return "No one";
		} else {
			return null;
		}
	}
}