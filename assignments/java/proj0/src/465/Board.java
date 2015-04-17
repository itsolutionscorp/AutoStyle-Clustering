public class Board {

	private Piece[][] pieces;
    private boolean fireturn = true;
    private boolean selected = false;
    private boolean moved = false;
    private boolean captured = false;
    private Piece selectedpiece;
    private boolean isthisselected;
    private int selectedpiecex;
    private int selectedpiecey;

	public static void main(String args[]) {
        Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare((int)x + 0.5, (int)y + 0.5, 0.5);
            }
            if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) {
                b.endTurn();
            }                        
            StdDrawPlus.show(100);
        }
	}	

	public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        if (!shouldBeEmpty) {
            for (int i = 0; i < 8; i +=2) {
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
            }
            for (int i = 0; i < 8; i += 2) {
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            }
            for (int i = 1; i < 8; i +=2) {
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            }
            for (int i = 0; i < 8; i += 2) {
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            }
        } else {}
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()) {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    } else {
                        if (pieces[i][j].isKing ()) {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        } else {               
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);                    
                            }
                        }
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
        if (pieces[x][y] != null) {
            if (pieces[x][y].isFire() == fireturn) {
                if (!selected || !moved) {
                    return true;
                }
                return false;
            }
            return false;
        } else {
            if (selected && (moved == false)) {
                if (validMove(selectedpiecex, selectedpiecey, x, y)) {
                    return true;
                }
            } else if (captured) {
                if (validMove(selectedpiecex, selectedpiecey, x, y)) {
                    return true;
                }
            }
            return false;
        }
	}

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf < 7 || yf < 7 || xf > 0 || yf > 0) {
            if (pieceAt(xi, yi) != null) {
                if (captured == true) {return false;}
                else if (pieces[xi][yi].isFire()) {
                    if ((xf == xi + 1 || xf == xi - 1) && yf == yi + 1) {
                        return true;
                    } else if (pieces[xi][yi].isKing()) {
                        if ((xf == xi + 1 || xf == xi - 1) && yf == yi - 1) {
                            return true;
                        }
                    } else if ((xf == xi + 2 || xf == xi - 2) && yf == yi + 2) {
                        if ((xf == xi + 2) && (!pieces[xf-1][yf-1].isFire())) {
                            return true;
                        } else if ((xf == xi - 2) && (!pieces[xf+1][yf-1].isFire())) {
                            return true;
                        } else if (pieces[xi][yi].isKing()) {
                            if ((xf == xi + 2 || xf == xi - 2) && yf == yi - 2) {
                                if ((xf == xi + 2) && (!pieces[xf-1][yf+1].isFire())) {
                                    return true;
                                } else if ((xf == xi - 2) && (!pieces[xf+1][yf+1].isFire())) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                } else {
                    if ((xf == xi + 1 || xf == xi - 1) && yf == yi - 1) {
                        return true;
                    } else if (pieces[xi][yi].isKing()) {
                        if ((xf == xi + 1 || xf == xi - 1) && yf == yi + 1) {
                            return true;
                        }
                    } else if ((xf == xi + 2 || xf == xi - 2) && yf == yi - 2) {
                        if ((xf == xi + 2) && (pieces[xf-1][yf+1].isFire())) {
                            return true;
                        } else if ((xf == xi - 2) && (pieces[xf+1][yf+1].isFire())) {
                            return true;
                        } else if (pieces[xi][yi].isKing()) {
                            if ((xf == xi + 2 || xf == xi - 2) && yf == yi + 2) {
                                if ((xf == xi + 2) && (pieces[xf-1][yf-1].isFire())) {
                                    return true;
                                } else if ((xf == xi - 2) && (pieces[xf+1][yf-1].isFire())) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        }
        return false;    
    }

	public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            selectedpiece.move(x, y);
            this.isthisselected = true;
        }
        this.selectedpiece = pieceAt(x, y);
        selectedpiecex = x;
        selectedpiecey = y;
	}

	public void place(Piece p, int x, int y) {
        if (x < 7 && y < 7) {    
            pieces[x][y] = p; 
        }
	}

	public Piece remove(int x, int y) {
        if (x>7 || y>7 || x<0 || y<0) {
            System.out.println("out of bounds");
            return null;
        }
        Piece mem = pieces[x][y];

        if (mem == null) {
            System.out.println("no piece here");
            return null;
        }
        this.pieces[x][y] = null;
        return mem;
	}

	public boolean canEndTurn() {
        if (selected != true) {
            if (moved || captured) {
                return true;
            }
        }
        return false;
	}

	public void endTurn() {
        if (fireturn = true) {
            fireturn = false;
        } else {
            fireturn = true;
        }
        moved = false;
        captured = false;
	}

	public String winner() {
        int firenum = 0;
        int waterum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] == null) {}
                else if (pieces[i][j].isFire()) {
                    firenum += 1;
                } else if (!pieces[i][j].isFire()) {
                    waterum += 1;
                }
            }
        }
        if (firenum > waterum) {
            return "Fire";
        } else if (waterum > firenum) {
            return "Water";
        } else if (waterum == firenum) {
            if (waterum == 0 && firenum == 0) {
                return null;
            } else {
                return "No one";
            }
        }
        return null;
	}
}