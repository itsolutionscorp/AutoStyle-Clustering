public class Board {
	private Piece[][] pieces;
	private int player_turn = 0;
	private boolean hasMoved;
	private Piece select_piece;
	private int select_x;
	private int select_y;

	public static void main(String args[]) {

		Board temp = new Board(false);
		int N = 8;
		StdDrawPlus.setScale(0, N);
		StdDrawPlus.setScale(0, N);

		while (true) {
			temp.drawBoard(N);
			temp.drawPieces(N);

			if (StdDrawPlus.mousePressed()) {
				double mx = StdDrawPlus.mouseX();
			    double my = StdDrawPlus.mouseY();

			    if (temp.canSelect((int) mx, (int) my)) {
			    	temp.select((int) mx, (int) my);
			    	
			    }
			}

			if (StdDrawPlus.isSpacePressed()) {
			   	if (temp.canEndTurn()) {
			   		temp.endTurn();
			 	}
			}

			if (temp.winner() != null) {
				System.out.println(temp.winner());
				break;
			}
			StdDrawPlus.show(25);

		}

	}
		


	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else { 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (select_piece != null && select_piece == pieces[i][j]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                }
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, .5);
            }
        }
	}


	private void drawPieces(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
            		String temp1;
            		if (pieces[i][j].isFire()) {
            			temp1 = "fire";
            		} else {
            			temp1 = "water";
            		}
            		String temp2;
            		if (pieces[i][j].isBomb()) {
            			temp2 = "bomb";
            		} else if (pieces[i][j].isShield()) {
            			temp2 = "shield";
            		} else {
            			temp2 = "pawn";
            		}
            		if (! pieces[i][j].isKing()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + temp2 + "-" + temp1 + ".png", 1, 1);
            		} else {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + temp2 + "-" + temp1 + "-crowned.png", 1, 1);
            		}
            	}
            }
        }
    }


	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			pieces = new Piece[8][8];
		} else {
			pieces = new Piece[8][8];
			for (int i = 0; i < 8; i += 1) {
				for (int j = 0; j < 8; j += 1) {
					if (i == 7 && j % 2 == 1) {
						pieces[j][i] = new Piece(false, this, j, i, "pawn");
					} else if (i == 6 && j % 2 == 0) {
						pieces[j][i] = new Piece(false, this, j, i, "shield");
					} else if (i == 5 && j % 2 == 1) {
						pieces[j][i] = new Piece(false, this, j, i, "bomb");
					} else if (i == 0 && j % 2 == 0) {
						pieces[j][i] = new Piece(true, this, j, i, "pawn");
					} else if (i == 1 && j % 2 == 1) {
						pieces[j][i] = new Piece(true, this, j, i, "shield");
					} else if (i == 2 && j % 2 == 0) {
						pieces[j][i] = new Piece(true, this, j, i, "bomb");
					}
				}
			}
		}
	}


	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			
			if (pieceAt(x, y) == null) {
				if (select_piece != null && validMove(select_x, select_y, x, y) && hasMoved == false) {
					return true;
				} else if (select_piece != null && validMove(select_x, select_y, x, y) && select_piece.hasCaptured()) {
					return true;
				} else {
					return false;
				}
			} else {
				if (pieceAt(x, y).side() == player_turn) {
					if (select_piece == null && hasMoved == false) {

						return true;
					} else if (select_piece != null && hasMoved == false) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}

	}

	private boolean validMove(int xi, int yi, int xf, int yf) {

		if (xf < 8 && xf >= 0 && yf < 8 && yf >= 0) {
			Piece temp = pieceAt(xi, yi);
			int mid_x = (xf + xi) / 2;
			int mid_y = (yf + yi) / 2;
			if (pieceAt(xf, yf) == null) {
				if (temp != null && temp.isFire()) {
					if (temp.isKing()) {
						if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2 && pieceAt(mid_x, mid_y) != null && !pieceAt(mid_x, mid_y).isFire()) {
							return true;
						} else if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1 && !temp.hasCaptured()) {
							return true;
						} else {
							return false;
						}
					} else if (yf == yi + 1 && Math.abs(xi - xf) == 1 && !temp.hasCaptured()) {
						return true;
					} else if (yf == yi + 2 && Math.abs(yi - yf) == 2 && pieceAt(mid_x, mid_y) != null && !pieceAt(mid_x, mid_y).isFire()) {
						return true;
					} else {
						return false;
					}
				} else if (temp != null && !temp.isFire()) {
					if (temp.isKing()) {
						if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2 && pieceAt(mid_x, mid_y) != null && pieceAt(mid_x, mid_y).isFire()) {
							return true;
						} else if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1 && !temp.hasCaptured()) {
							return true;
						} else {
							return false;
						}
					} else if (yf == yi - 1 && Math.abs(xi - xf) == 1 && !temp.hasCaptured()) {
						return true;
					} else if (yf == yi - 2 && Math.abs(xi - xf) == 2 && pieceAt(mid_x, mid_y) != null && pieceAt(mid_x, mid_y).isFire()) {
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

	}


	public void select(int x, int y) {

        if (pieceAt(x, y) != null) {
        	select_piece = pieceAt(x, y);
        	select_x = x;
        	select_y = y;
        } else if (pieceAt(x, y) == null && select_piece == null) {


        } else if (pieceAt(x, y) == null && select_piece != null) {
        	select_piece.move(x, y);
        	select_x = x;
        	select_y = y;
        	hasMoved = true;
        }
	}

	public void place(Piece p, int x, int y) {
		if (p != null) {
			if (x < 8 || y < 8 || x >= 0 || y >= 0) {
				pieces[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			System.out.println("Index out of bounds");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("There is no piece to remove");
			return null;
		} else {
			Piece temp = this.pieceAt(x, y);
			pieces[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn() {
		player_turn = (player_turn + 1) % 2;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null) {
					pieces[i][j].doneCapturing();					
				}
			}
		}
		hasMoved = false;
		select_piece = null;

	}

	public String winner() {
		int count_fire = 0;
		int count_water = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire() == false) {
						count_water += 1;
					} else {
						count_fire += 1;
					}
				}
			}
		}
		if (count_fire == 0 && count_water == 0) {
			return "No one";
		} else if (count_water > 0 && count_fire == 0) {
			return "Water";
		} else if (count_water == 0 && count_fire > 0) {
			return "Fire";
		} else {
			return null;
		}
	}



}