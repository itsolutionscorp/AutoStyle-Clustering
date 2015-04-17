public class Board {
	private int boardSize = 8;
	private Piece[][] pieces = new Piece[boardSize][boardSize];
	private boolean fireTurn = true;
	private int[] selected = new int[]{-1,-1};
	private boolean moved = false;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == true) {return;}
		for (int i = 0; i<boardSize/2; i++) {
			pieces[2*i  ][0] = new Piece(true , this, 2*i  , 0, "pawn");
			pieces[2*i+1][1] = new Piece(true , this, 2*i+1, 1, "shield");
			pieces[2*i  ][2] = new Piece(true , this, 2*i  , 2, "bomb");
			pieces[2*i+1][5] = new Piece(false, this, 2*i+1, 5, "bomb");
			pieces[2*i  ][6] = new Piece(false, this, 2*i  , 6, "shield");
			pieces[2*i+1][7] = new Piece(false, this, 2*i+1, 7, "pawn");				
		}
	}

	public static void main (String args[]) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        boolean game = true;
        while(game==true) {
        	b.drawBoard(N);
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x,(int) y)) {
                	b.select((int) x,(int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            if(b.winner()!=null) {
            	System.out.println(b.winner());
            	game=false;
            }
        	StdDrawPlus.show(100);
        }
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i==selected[0]&&j==selected[1]) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] !=null) {
                	String type = "";
                	String fire = "";
                	String crown = "";
                	if(pieces[i][j].isBomb()) {type = "img/bomb";}
                	else if(pieces[i][j].isShield()){type = "img/shield";}
                	else {type = "img/pawn";}
                	if(pieces[i][j].isFire()) {fire = "-fire";}
                	else {fire = "-water";}
                	if(pieces[i][j].isKing()) {crown = "-crowned.png";}
                	else {crown = ".png";}
           			
                    StdDrawPlus.picture(i + .5, j + .5, type + fire + crown, 1, 1);
                }
            }
        }
    }

	public Piece pieceAt (int x, int y) {
		if ( x >= boardSize || y >= boardSize || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect (int x, int y) {
		if (!( x<boardSize && y<boardSize && x > -1 && y > -1)) {
			return false;
		}
		Piece p = pieces[x][y];
		if( p != null) {
			if (p.isFire()==fireTurn) {
				if (selected[0] == -1 || moved == false) {
					return true;
				}
			}
			return false;
		}
		else {
			int n = -1;
			int k = -1;
			if (fireTurn==true){
				n = 1;
				k = 1;
			}
			int dx = x - selected[0];
			int dy = y - selected[1];
			if (selected[0] != -1) {
				if (pieces[selected[0]][selected[1]]==null) {
					return false;
				}
				if (pieces[selected[0]][selected[1]].isKing()) {
					k = -k;
				}
				if ( Math.abs(dx)==2 && (dy == 2*n || dy == 2*k)) {
					if (pieces[x-dx][y-dy].hasCaptured() || !moved) {
						if ( pieces[(int)(x-dx/2+.5)][(int)(y-dy/2+.5)] != null && pieces[(int)(x-dx/2+.5)][(int)(y-dy/2+.5)].isFire() != fireTurn ){
							return true;
						}
					}
				}
				else if ( (dx == 1 || dx == -1) && ( dy == n || dy == k)) {
					if (!moved) {
						return true;
					}	
				}
			}
			return false;
		}
	}

	public void select (int x, int y) {
		
			if (selected[0] != -1) {
				Piece p = pieces[selected[0]][selected[1]];
				Piece n = pieces[x][y];
				if (n==null) {
					p.move(x,y);
					moved = true;
				}
				selected[0] = x;
				selected[1] = y;
			}
			else {
				selected[0] = x;
				selected[1] = y;
			}
	}

	public void place (Piece p, int x, int y) {
		if ( x<boardSize && y<boardSize && x > -1 && y > -1 && p !=null ) {
			pieces[x][y] = p;
			selected[0] = x;
			selected[1] = y;
		}
	}
	
	public Piece remove (int x, int y) {
		if ( x>=boardSize || y>=boardSize || y < 0 || x < 0 || pieces[x][y]==null ) {
			System.out.println(x+""+y);
			System.out.println("cannot remove invalid piece");
			return null;
		}
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn () {
		return moved;
	}

	public void endTurn () {
		if (canEndTurn()) {
			Piece p= pieces[selected[0]][selected[1]];
			if (p!=null) {
				p.doneCapturing();
			}
			moved = false;
			fireTurn = !fireTurn;
			selected[0] = -1;
			selected[1] = -1;
		}
	}

	public String winner () {
		int f = 0;
		int w = 0;
		for (int i=0; i<boardSize; i++) {
			for (int j=0; j<boardSize; j++) {
				if (pieces[i][j]!=null) {
					if ( pieces[i][j].isFire() == false ) {
						w +=1;
					}
					else {
						f +=1;
					}
				}
			}
		}
		if (f==0) {
			if (w==0) {
				return "No one";
			}
			else return "Water";
		}
		else if (w==0) {
			return "Fire";
		}
		return null;
	}


}