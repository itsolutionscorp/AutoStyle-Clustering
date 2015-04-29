public class Board {

    private Piece[][] pieces;
    private int N;
    private int player;
    private Piece selected;
    private int selectedx;
    private int selectedy;
    private boolean moved;
    private boolean bombed;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (selected != null && selectedx == i && selectedy == j) 
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);    
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = pieces[i][j];
                if (p!= null) {
                	String type;
                	String side;
                	if (p.isBomb()) {
                		type = "bomb";
                	} else if (p.isShield()) {
                		type = "shield";
                	} else {
                		type = "pawn";
                	}
                	if (p.isFire()) {
                		side = "fire";
                	} else {
                		side = "water";
                	} if (p.isKing()) {
                		side = side.concat("-crowned");
                	}
                    StdDrawPlus.picture(i + .5, j + .5, "img\\" + type + "-" + side + ".png", 1, 1);
                }
            }
        }
    }


	public static void main(String args[]) {
		Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(b.winner() == null) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x, (int)y)) {
                    b.select((int)x, (int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            b.drawBoard(8);

            StdDrawPlus.show(75); 
        }     
	}

	public Board(boolean shouldBeEmpty) {
		selectedx = 0;
		selectedy = 0;
		this.N = 8;
		this.player = 0;
		pieces = new Piece[N][N];
		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0) {
						if (j == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "pawn");}
						if (j == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield");}
						if (j == 2) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb");}
						if (j == 5) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb");}
						if (j == 6) {
							pieces[i][j] = new Piece(false, this, i, j, "shield");}
						if (j == 7) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn");}
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x >= N || y >= N || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
			if (p != null) {
				if (player == 0 && p.isFire() || player == 1 && !p.isFire()) {
					if (selected == null && !bombed || selected != null && !moved) {
						return true;
					}
				}
			} else {
				if (selected != null && !moved && validMove(selectedx, selectedy, x, y)) {
					return true;
				}
				if (selected != null && selected.hasCaptured() && validMove(selectedx, selectedy, x, y) 
					&& Math.abs(selectedx - x) == 2 && Math.abs(selectedy - y) == 2) {
					return true;
				}
			} 
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece first = pieceAt(xi, yi);
		Piece last = pieceAt(xf, yf);
		if (xf >= N || yf >= N || first == null || last != null) {
			return false;
		}
		if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
			if (first.isKing()) {
				return true;
			}
			if (first.isFire() && (yf - yi) == 1) {
				return true;
			}
			if (!first.isFire() && (yf - yi) == -1) {
				return true;
			} 
		}

		if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
			Piece p = pieceAt((xi + xf)/2, (yi + yf)/2);
			if (p != null && first.isFire() != p.isFire()) {
				if (first.isKing()) {
					return true;
				} else {
					if (first.isFire() && (yf - yi) == 2 || 
						!first.isFire() && (yf -yi) == -2) {
						return true;
					}
				}
			}
		}
		return false;
	}
		


	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selected = pieces[x][y];
			selectedx = x;
			selectedy = y;
		} else {
            if (true) {
        		if (!selected.hasCaptured()) {
        			selected.move(x, y);
        			moved = true;
        			if (selected.isBomb() && selected.hasCaptured()) {
        				pieces[x][y] = null;
        				bombed = true;
        				selected = null;
        			}
        		} else {
        			if (Math.abs(selectedx - x) == 2 && Math.abs(selectedy - y) == 2) {
        				selected.move(x, y);
        				moved = true;
        			}
        		}
        		selectedx = x;
        		selectedy = y;

        	}
    	}
	}

	public void place(Piece p, int x, int y) {
		if (x < N && y < N && p != null) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x >= N || y >= N) {
			System.out.println("Out of bounds");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("No piece located");
			return null;
		}
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		if (selected != null || selected == null && bombed) {
			return moved || selected.hasCaptured(); 	
		}
		return false;
	}

	public void endTurn() {
		if (!bombed) 
			selected.doneCapturing();
		selected = null;
		moved = false;
		bombed = false;
		player = (player + 1) % 2;
	}

	public String winner() {
		String fire = null;
		String water = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null && pieces[i][j].isFire()) {
            		fire = "Fire";
            	}
            	if (pieces[i][j] != null && !pieces[i][j].isFire()) {
            		water = "Water";
            	}
            }
        }
        if (fire == null && water == null) {
        	return "No one";
        } else if (fire == null) {
        	return water;
        } else if (water == null) {
        	return fire;
        } else {
        	return null;
        }
	}
}



