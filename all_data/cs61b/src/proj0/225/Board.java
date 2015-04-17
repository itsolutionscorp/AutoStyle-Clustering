/**
 * @author: Yidi Wang
 *	I received help from Jiahang Li, who helps debug the CanSelect() method
 */

public class Board {
	private final static int N = 8;
	private Piece[][] pieces;
	private Piece selected;
	private int player;
	private boolean moved;
	private String image;
	private int helperX;
	private int helperY;

	public static void main (String args[]) {

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

		Board yidi = new Board(false);

		yidi.drawBoard();
		while(true) {
            yidi.drawBoard();
            if (StdDrawPlus.mousePressed()) {

                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                
                if (yidi.canSelect((int) x, (int) y)) {
                	yidi.select((int)x, (int)y);
                }
                
                
            } 
            if (StdDrawPlus.isSpacePressed()) {
            	if (yidi.canEndTurn()) {
            		yidi.endTurn();
            		if (yidi.winner() != null) {
            	
            			return;
            		}
            	}
            } 
                      
            StdDrawPlus.show(100);
        }
	}



	public Board(boolean shouldBeEmpty) {

		pieces = new Piece[N][N];
		this.selected = null;
		this.moved = false;
		this.player = 0;
		this.helperX = 0;
		this.helperY = 0;

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (!shouldBeEmpty){
					if (y == 0 && x % 2 == 0) {
						pieces[x][y] = new Piece(true, this, x, y, "pawn");
					} else if (y == 1 && x % 2 == 1) {
						pieces[x][y] = new Piece(true, this, x, y, "shield");
					} else if (y == 2 && x % 2 == 0) {
						pieces[x][y] = new Piece(true, this, x, y, "bomb");
					} else if (y == 5 && x % 2 == 1) {
						pieces[x][y] = new Piece(false, this, x, y, "bomb");
					} else if (y == 6 && x % 2 == 0) {
						pieces[x][y] = new Piece(false, this, x, y, "shield");
					} else if (y == 7 && x % 2 == 1) {
						pieces[x][y] = new Piece(false, this, x, y, "pawn");
					}
					else{
						pieces[x][y] = null;
					}
				}
				else{
					pieces[x][y] = null;
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
			if (pieces[x][y].side() != this.player) {
				return false;
			} 
			if (this.selected == null) {
				return true;
			} 
			else { 
				if (!this.moved) {
					return true;
				} 
				return false;
			}
		} 
		else {

			if (this.selected == null) {
				return false;
			} 
			else {
				if (Math.abs(this.helperY - y) == 1) {
					if (this.moved) {
						return false;
					} 
					return validMove(this.helperX, this.helperY, x, y);
				} 
				else {
					if (this.moved) {
						if (this.selected.hasCaptured()) {
							if (this.selected.isBomb()) {
								return false;
							}
							return validCapture(this.helperX, this.helperY, x, y);
						} 
						return false;
						}
					return validCapture(this.helperX, this.helperY, x, y);						
				}
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
			return false;
		}
		if (Math.abs(xi - xf) != Math.abs(yi - yf)) {
			return false;
		} 
		if (Math.abs(xi - xf) != 1) {
			return false;
		}
		Piece p = pieces[xi][yi];
		int up = yf - yi;
		if (p == null) {
		}
		if (p.isKing()) {
			if (pieces[xf][yf] != null) {
				return false;
			}
			return true;
		}
		else {
			if (up > 0) {
				if (!p.isFire()) {
					return false;
				}
			}
			if (up < 0) {
				if (p.isFire()) {
					return false;
				}
			}
			if (pieces[xf][yf] != null) {
				return false;
			}
			return true;
		}

	}
	

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
			return false;
		}
		if (Math.abs(xi - xf) != Math.abs(yi - yf)) {
			return false;
		} 
		if (Math.abs(xi - xf) != 2) {
			return false;
		}
		Piece p = pieces[xi][yi];
		int up = yf - yi;
		int xz = (xi + xf)/2;
		int yz = (yi + yf)/2;

		if (p.isKing()) {
			if (pieces[xf][yf] != null) {
				return false;
			}
			if (pieces[xz][yz] == null) {
				return false;
			}
			if (pieces[xz][yz].isFire() == p.isFire()) {
				return false;
			}
			
			return true;
		}
		else {
			if (up > 0) {
				if (!p.isFire()) {
					return false;
				}
			}
			if (up < 0) {
				if (p.isFire()) {
					return false;
				}
			}
			
			if (pieces[xf][yf] != null) {
				return false;
			}
			if (pieces[xz][yz] == null) {
				return false;
			} 
			if (pieces[xz][yz].isFire() == p.isFire()) {
				return false;
			}
		
			return true;
		}
	}

	public void select(int x, int y) {
		this.helperY = y;
		this.helperX = x;


		if (pieces[x][y] != null) {
			this.selected = pieces[x][y];
		} else {
			this.selected.move(x, y);
			this.moved = true;

		}
	}

	public void place(Piece p, int x, int y) {

		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
			
			if (pieces[x][y] != null) {
				remove(x, y);
			}
			
			pieces[x][y] = p;
		}

	}

	public Piece remove(int x, int y){

		if ((x < 0)||(y < 0)||(x > 7)||(y > 7)){
			return null;
		}
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		if ((this.selected != null) && (this.moved || this.selected.hasCaptured())) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		this.selected.doneCapturing();
		this.selected = null;
		this.moved = false;
		this.player = 1 - this.player;
		this.helperX = 0;
		this.helperY = 0;
	}

	public String winner() {
		int total0 = 0;
		int total1 = 0;
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						total0 += 1;
					}
					else {
						total1 += 1;
					}
				}
			}
		}
		if (total0==0 && total1==0){
			return "No one";
		}
		if (total0 == 0) {
			return "Water";
		}
		if (total1 == 0) {
			return "Fire";
		}
		return null;

	}

	private String image(int helperX, int helperY) {
		if (pieces[helperX][helperY].isFire()) {
			if (pieces[helperX][helperY].isBomb()) {
				if (pieces[helperX][helperY].isKing()) {
					image = "img/bomb-fire-crowned.png";
				}
				else {
					image = "img/bomb-fire.png";
				}
			} else if (pieces[helperX][helperY].isShield()) {
				if (pieces[helperX][helperY].isKing()) {
					image = "img/shield-fire-crowned.png";
				}
				else {
					image = "img/shield-fire.png";
				}
			}
			else {
				if (pieces[helperX][helperY].isKing()) {
					image = "img/pawn-fire-crowned.png";
				}
				else {
					image = "img/pawn-fire.png";
				}
			}
		}
		else {
			if (pieces[helperX][helperY].isBomb()) {
				if (pieces[helperX][helperY].isKing()) {
					image = "img/bomb-water-crowned.png";
				}
				else {
					image = "img/bomb-water.png";
				}
			} else if (pieces[helperX][helperY].isShield()) {
				if (pieces[helperX][helperY].isKing()) {
					image = "img/shield-water-crowned.png";
				}
				else {
					image = "img/shield-water.png";
				}
			}
			else {
				if (pieces[helperX][helperY].isKing()) {
					image = "img/pawn-water-crowned.png";
				}
				else {
					image = "img/pawn-water.png";
				}
			}
		}
		return image;

	}
	private void drawBoard() {
		
		
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (this.selected != null && pieces[x][y] == this.selected) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((x + y) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {                
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[x][y] != null) {
                	StdDrawPlus.picture(x + .5, y + .5, this.image(x, y), 1, 1);
                }
            }
        }
    }
}
