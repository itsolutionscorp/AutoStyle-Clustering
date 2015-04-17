public class Board {
	private  boolean shouldBeEmpty;
	private  Piece[][] position;
	private  int player = 0;
	private  Piece selP;
	private boolean moved = false;
	private int f = 0;
	private int w = 0;


	

	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j ++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					if (selP != null && selP == position[i][j]) {
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					}
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}				
				StdDrawPlus.filledSquare(i + .5, j +.5, .5);
				
				if (position[i][j] != null && position[i][j].isKing() == false) {
					StdDrawPlus.picture(i + .5, j + .5, "/img/" + moreHelp(position[i][j]) 
						+ "-" + help(position[i][j]) + ".png", 1, 1);
					}
				if (position[i][j] != null && position[i][j].isKing() == true) {
						StdDrawPlus.picture(i + .5, j + .5, "/img/" + moreHelp(position[i][j]
						) + "-" + help(position[i][j]) + "-crowned.png", 1, 1);
					}
					
				}
			}
		}

	
	
	private String help(Piece sad) { 
		if (sad.isFire() == true) {
			return "fire";
		} else {
			return "water";
		}
	}

	private String moreHelp(Piece sad) {
		if (sad.isBomb() == true) {
			return "bomb";
		} else if (sad.isShield() == true) {
			return "shield";
		} else {
			return "pawn";
		}
	}
		
	public Board (boolean shouldBeEmpty) {
			if (shouldBeEmpty == true) {
				int N = 8;
				position = new Piece[N][N];
			} else {
				int N = 8;
				
				position = new Piece[N][N];

				for (int k = 0; k < N; k += 2) {
					position[k][0] = new Piece(true, this, k, 0, "pawn");
				}
				for (int p = 1; p < N; p += 2) {
					position[p][1] = new Piece(true, this, p, 1, "shield");
				}
				for (int k = 0; k < N; k += 2) {
					position[k][2] = new Piece(true, this, k, 2, "bomb");
				}
				for (int k = 1; k < N; k += 2) {
					position[k][5] = new Piece(false, this, k, 5, "bomb");
				}
				for (int k = 0; k < N; k += 2) {
					position[k][6] = new Piece(false, this, k, 6, "shield");
				}

				for (int k = 1; k < N; k += 2) {
					position[k][7] = new Piece(false, this, k, 7, "pawn");
				}
			}

	}

	public Piece pieceAt(int x, int y) {
		if (x >= 8 || y >= 8) {
			return null;
		} else {
			if (position[x][y] != null) {
				return position[x][y];
			} else {
				return null;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0 && p != null) {
			for (int i = 0; i < 8; i += 1) {
				for (int j =0; j < 8; j += 1) {
					if (p == position[i][j]) {
						position[i][j] = null;
					}
				}
			}
			position[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x >= 8 || y >= 8) {
			System.out.println("illegal");
			return null;
		} else if (position[x][y] == null) {
			System.out.println("no pieces at coordinate");
			return null;
		} else {
			Piece rem = position[x][y];
			position[x][y] = null;
			return rem;
		}
	}

		 


	public boolean canSelect(int x, int y) {
		if (position[x][y] == null) {
			if (((x+y) % 2) == 0) {
				if (selP != null) {
				if (player == position[findx(selP)][findy(selP)].side()) {
			
				if (selP != null && moved == false && validMove(findx(selP), findy(selP), x, y) == true) {
					return true;
				} else if (moved == true && selP.hasCaptured() == true && validMove(findx(selP), findy(selP), x, y) == true) {
						return true;
				} else {
					return false;
					}
				}
			} else {
				return false;
			}
		}
		}
		else {

			if (player == position[x][y].side() && (selP == null || (selP != null && moved == false))) {
					return true;

			} else {
				return false;
			}

		} return false;
	}

	private int findx(Piece p) {
		for (int i = 0; i < 8; i += 1) {
			for (int j =0; j < 8; j +=1) {
				if (p == position[i][j]) {
					return i;
				} 
			}
		} 
		return 0;
	}

	private int findy(Piece p) {
		for (int i = 0; i < 8; i += 1) {
			for (int j =0; j < 8; j +=1) {
				if (p == position[i][j]) {
					return j;
				} 
			}
		} 
		return 0;
	}

	public void select(int x, int y) {
		if (position[x][y] == null && selP != null) {
			selP.move(x,y);
			moved = true;
		} else{
			selP = position[x][y];
		}
			
	}

	public boolean canEndTurn() {
		if (selP == null) {
			return false;
		} else
		if (moved == true || selP.hasCaptured() == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		if (canEndTurn() == true) {
				moved = false;
				selP.doneCapturing();
				selP = null;
				if (player == 0) {
					player = 1;
				} else {
					player = 0;
				}
				
			}
		}

	public String winner() {
		if (fireCount() == 0 && waterCount() == 0) {
			return "No One";
		} else if (fireCount() == 0) {
			return "Water";
		} else if (waterCount() == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	private int fireCount() {
		int f = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (position[i][j] != null && position[i][j].isFire() == true) {
					f += 1;
				}
			}
		}
		return f;

	}

	private int waterCount() {
		int w = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (position[i][j] != null && position[i][j].isFire() == false) {
					w += 1;
				}
			}
		}
		return w;

	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf > 8 || yf > 8 || xf < 0 || yf < 0) {
			return false;
		}
		if (selP.isKing() == true && Math.abs(xf-xi) == 1) {
			return true;

		}
		if (selP.isKing() == true && Math.abs(xf-xi) == 2 && position[(xf+xi)/2][(yf+yi)/2] != null && selP.side() != position[(xf+xi)/2][(yf+yi)/2].side()) {
			return true;
		}
		else if (position[xf][yf] == null) {
			if (((yf-yi) == 1) && selP.isFire() == true) {
				return true;
			} 
			else if (((yf-yi) == 2) && selP.isFire() == true) {
				if (position[((xf+xi)/2)][((yf+yi)/2)] != null && position[((xf+xi)/2)][((yf+yi)/2)].side() != position[xi][yi].side()) {
					return true;
				}
			}
			else if (((yf-yi) == -1) && selP.isFire() == false) {
				return true;
			}
			else if (((yf-yi) == -2) && selP.isFire() == false) {
				if (position[((xf+xi)/2)][((yf+yi)/2)] != null && position[((xf+xi)/2)][((yf+yi)/2)].side() != position[xi][yi].side()) {
					return true;
				}
			}
		}
			return false;
	}

	public static void main (String args []) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
	
		Board plzWork = new Board(Boolean.parseBoolean(args[0]));

		

		while (true) {
			plzWork.drawBoard(N);
			if (StdDrawPlus.mousePressed() == true) {
				double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                if (plzWork.canSelect((int) x, (int) y) == true) {
                	plzWork.select((int) x, (int) y);
                }
			}
			if (StdDrawPlus.isSpacePressed() == true) {
				plzWork.endTurn();
			}

			plzWork.winner();
			System.out.println(plzWork.winner());

			StdDrawPlus.show(100);

			// System.out.println(plzWork.selP);
		}
	}
	
	}