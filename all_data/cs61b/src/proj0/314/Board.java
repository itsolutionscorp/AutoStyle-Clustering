public class Board {
	private Piece[][] pieces=new Piece[8][8];
	private String player="fire";
	private Piece selected=null;
	private int x1=0;
	private int y1=0;
	private boolean canEnd=false;
	private boolean hasMoved=false;
	private boolean multi=false;

	public static void main (String args[]) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        Board b=new Board(false);
        b.initPieces();
        while(true) {
            drawBoard(b, N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                System.out.println("Mouse click"+x+y);
                
                if (b.canSelect((int) x, (int)y)) {
                	b.select((int)x, (int)y);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(x , y , .5);
                }
        	} 
        	if (StdDrawPlus.isSpacePressed()) {
        		System.out.println("space pressed");
        		if (b.canEndTurn()) {
        			System.out.println("end turn");
        			b.endTurn();
        		}
        	}
            StdDrawPlus.show(100);
            if (b.winner()!=null) {
            	System.out.println(b.winner());
            	break;
            }
        }
	}

	private static void drawBoard(Board b,int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.pieces[i][j]!=null) {
                	if (b.pieces[i][j].isBomb()) {
                		if (b.pieces[i][j].isFire()) {
                			if (b.pieces[i][j].isKing()) 	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			else 						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);					
                		} else {
                			if (b.pieces[i][j].isKing()) 	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			else 						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);		
                		}
                	} else if (b.pieces[i][j].isShield()) {
                		if (b.pieces[i][j].isFire()) {
                			if (b.pieces[i][j].isKing()) 	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			else 						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);		
                		} else {
                			if (b.pieces[i][j].isKing()) 	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			else 						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);		
                		}
                	} else {
                		if (b.pieces[i][j].isFire()) {
                	 		if (b.pieces[i][j].isKing()) 	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			else 						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);		
                		} else {
                			if (b.pieces[i][j].isKing()) 	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			else 						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);		
                		}
                	}
                }
            }
        }
    }

    private void initPieces() {
    	for (int i=0; i<pieces.length; i++) {
    		for (int j=0; j<3; j++) {
    			if ((i+j)%2==0) {
    				if (j==0) pieces[i][j]=new Piece(true, this, i, j, "pawn");
					else if (j==1) pieces[i][j]=new Piece(true, this, i, j, "shield");  
					else   			pieces[i][j]=new Piece(true, this, i, j, "bomb");
    			}
    		}
    		for (int j=5; j<8; j++) {
    			if ((i+j)%2==0) {
					if (j==5) pieces[i][j]=new Piece(false, this, i, j, "bomb");
					else if (j==6) pieces[i][j]=new Piece(false, this, i, j, "shield");  
					else   			pieces[i][j]=new Piece(false, this, i, j, "pawn");
    			}
    		}
    	}
    }

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			initPieces();
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x<0 || x>7 || y<0 || y>7) {
			return null;
		}
		return pieces[x][y];
	}

	private boolean validMove(int x, int y) {
		Piece pc=pieces[x][y];
		if (hasMoved) {
			if (Math.abs(x1-x)<2 || Math.abs(y1-y)<2) return false;
			if (!multi) return multi;
			
		}
		if ((x+y)%2!=0) return false;
		System.out.println("even square");
		if (selected==null || pc!=null) {
			if (pc==null) return false;
			System.out.println("piece selected");
			if (multi && pc!=null) return false;
			else if (pc.isFire() && player!="fire") return false;
			else if (!pc.isFire() && player!="water") return false;
		} else {
			System.out.println("piece selected1");
			if (Math.abs(x1-x)>2 || Math.abs(x1-x)<1) return false;
			if (Math.abs(y1-y)>2 || Math.abs(y1-y)<1) return false;
			System.out.println("good distance");
			if (!selected.isKing()) {
				System.out.println("not king");
				if (selected.isFire()) {
					if (y1>y) return false;
				} else {
					if (y1<y) return false;
				}
			}
			if (Math.abs(x1-x)>1) {
				System.out.println("check capture");
				int xDiff=(x-x1)/2;
				int yDiff=(y-y1)/2;
				Piece cap=pieceAt(x1+xDiff, y1+yDiff);
				if (cap==null) return false;
				if (cap.isFire() && player=="fire") return false;
				if (!cap.isFire() && player=="water") return false;
			}
		}

		
		return true;
	}

	public boolean canSelect(int x, int y) {
		if (x<0 || x>7 || y<0 || y>7) {
			return false;
		}
		if (!validMove(x, y)) {
			System.out.println("invalid move");
			return false;
		}

		System.out.println("valid move");
		return true;
	}

	public void select(int x, int y) {
		Piece pc=pieces[x][y];

		if (pc==null) {
			pieces[x][y]=selected;
			selected.move(x, y);
			pieces[x1][y1]=null;
			if (selected.hasCaptured()) {
				multi=true;
				if (selected.isBomb()) {
					multi=false;
					for (int i=x-1; i<x+2; i++) {
						for (int j=y-1; j<y+2; j++) {
							if (i>-1 && i<8  && j>-1 && j<8) {
							if (pieces[i][j]!=null) {
								if (!pieces[i][j].isShield()) {
									pieces[i][j]=null;
									remove(i, j);
								}
							}
							}
						}
					}
				} else {
					int xDist=(x1-x)/2;
					int yDist=(y1-y)/2;
					pieces[x+xDist][y+yDist]=null;
					remove(x+xDist, y+yDist);
				}
				
			}
			x1=x;
			y1=y;
			canEnd=true;
			hasMoved=true;
		} else {
			System.out.println("selected piece:"+pc);
			selected=pieces[x][y];
			x1=x;
			y1=y;
		}
	}

	public void place(Piece p, int x, int y) {
		if ((p!=null) && x<pieces.length && y<pieces.length) {
			pieces[x][y]=p;
		}
	}

	public Piece remove(int x, int y) {
		if (x<pieces.length && y<pieces.length) {
			Piece removed=pieces[x][y];
			pieces[x][y]=null;
			return removed;
		}
		System.out.println("Coordinates out of bounds!");
		return null;
	}

	public boolean canEndTurn() {
		return canEnd;
	}

	public void endTurn() {
		canEnd=false;
		hasMoved=false;
		selected.doneCapturing();
		selected=null;
		multi=false;
		if (player=="fire") player="water";
		else player="fire";
	}

	public String winner() {
		int f=0;
		int w=0;
		for (int i=0; i<pieces.length; i++) {
			for (int j=0; j<pieces.length; j++) {
				Piece pc=pieces[i][j];
				if (pc!=null) {
					if (pc.isFire()) f=f+1;
					else w=w+1;
				}
			}
		}
		if (w==0 && f>0) return "Fire";
		else if (f==0 & w>0) return "Water";
		else if (w>0 && f>0) return null;
		else return "No one";
	}
}