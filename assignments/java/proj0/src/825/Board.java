
public class Board {
	private Piece[] pieces = new Piece[24];
	private boolean empty = true;
	private int[][] piecesloc = new int[24][2];
	private String imgname;
	private Piece moveable = null;
	private boolean hasmoved = false;
	private int[] moveablecoords = null;
	private boolean firesturn = true;
	private Piece removed = null;


	public Board(boolean shouldBeEmpty) {
		this.empty = shouldBeEmpty;
		if (!empty) {
			createPieces();
		}
	}
	
	public static void main(String[] args) {
		Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        b.drawBoard(8);
        while (true) {
        	StdDrawPlus.show(100);
        	b.drawBoard(8);
	        b.click();
	        b.spacebar();
        }
	}
	
	private void spacebar() {
        if (StdDrawPlus.isSpacePressed()) {
			if (canEndTurn()) {
				endTurn();
			}
        }
	}
		

	private void click() {
        if (StdDrawPlus.mousePressed()) {
	        double x = StdDrawPlus.mouseX();
	        double y = StdDrawPlus.mouseY();
	        int xin = (int) x;
	        int yin = (int) y;
	        if (canSelect(xin, yin)) {
	        	select(xin,yin);
	        }
        }
		
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
             
                } // for all nonempty boards
            }
        if (!this.empty){
        	drawPieces();
        }

    }
	
	private void createPieces() {
		if (empty != true) {
		int index = 0;
		int x = 0;
		int y = 0;
		while (x<7) {	//even columns
			pieces[index] = new Piece(true, this, x, y, "pawn");
			piecesloc[index][0] = x;
			piecesloc[index][1] = y;
			pieces[index+1] = new Piece(true, this, x, y+2, "bomb");
			piecesloc[index+1][0] = x;
			piecesloc[index+1][1] = y+2;
			pieces[index+2] = new Piece(false, this, x, y+6, "shield");
			piecesloc[index+2][0] = x;
			piecesloc[index+2][1] = y+6;
			index +=3;
			x += 2;
		}
		x = 1;
		y = 1;
		while (x<8) {	//odd columns
			pieces[index] = new Piece(true, this, x, y, "shield");
			piecesloc[index][0] = x;
			piecesloc[index][1] = y;
			pieces[index+1] = new Piece(false, this, x, y+4, "bomb");
			piecesloc[index+1][0] = x;
			piecesloc[index+1][1] = y+4;
			pieces[index+2] = new Piece(false, this, x, y+6, "pawn");
			piecesloc[index+2][0] = x;
			piecesloc[index+2][1] = y+6;
			index +=3;
			x += 2;
		}
		} //for all non empty things
	}
	
	private void drawPieces() {		
		for (int i=0; i < pieces.length; i+=1) {
			if (pieces[i] != null) {
			if (pieces[i].isBomb()) {
				imgname = "bomb";
			}
			if (pieces[i].isShield()) {
				imgname = "shield";
			}
			if (!pieces[i].isBomb() && !pieces[i].isShield()) {
				imgname = "pawn";
			}///determines type of piece
			
			if (pieces[i].isFire()) {
				imgname = imgname+"-fire";
			} else { 
				imgname = imgname+"-water";
			}///determines which team
			
			if (pieces[i].isKing()) {
				imgname = imgname+"-crowned";
			} ///adds king status
			
			StdDrawPlus.picture(piecesloc[i][0] + .5, piecesloc[i][1] + .5, imgname+".png", 1, 1);
			}
		}
	}
	
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7) {
			return null;
		}
		for (int i=0; i < pieces.length; i+=1) {
			if (piecesloc[i] != null) {
				if (x == piecesloc[i][0] && y == piecesloc[i][1]) {
					return pieces[i];
				}
			}
		}
		return null;
	}
	
	public void place(Piece p, int x, int y) {
		int[] newpos = new int[2];
		newpos[0] = x;
		newpos[1] = y;
		if (p == null || x > 7 || y > 7) {
			
		} else {
			
		if (pieceAt(x,y) != null) {
			for (int i=0; i < pieces.length; i+=1) {
				if (pieceAt(x,y) == pieces[i]) {
					pieces[i] = p;
					break;
				}
			}
		} else {
		if (p == moveable) {
			moveablecoords = newpos;
			for (int i=0; i < pieces.length; i+=1) {
				if (pieces[i] == p) {
					piecesloc[i] = newpos;
				}
			}
		} else {
		for (int i=0; i < pieces.length; i+=1) {
			if (pieces[i] == null) {
				piecesloc[i] = newpos;
				pieces[i] = p;
				break;
			}
		}
		}///if not moveable thing being placed
		}
		}
	}


	public Piece remove(int x, int y) { 
		if (x > 7 || y > 7) {
			System.out.println("Cannot remove item off of board.");
			return null;
		}
		for (int i=0; i < pieces.length; i+=1) {
			if (piecesloc[i] != null) {
				if (x == piecesloc[i][0] && y == piecesloc[i][1]) {
					removed = pieces[i];
					pieces[i] = null;
					piecesloc[i] = null;		//will this erase both the 0 and 1 of the subarray?
				}
			}
		}
		if (removed != null) {
			return removed;
		}
		if (pieceAt(x,y) == null) {
			System.out.println("No piece to remove");
			return null;
		}
		return null;
	}
	

	public boolean canEndTurn() {
		if (hasmoved) {
			return true;
		}
		return false;
	}
	
	public void endTurn() {
		if (firesturn) {
			firesturn = false;
		} else{
		firesturn = true;
		}	///switches teams
		hasmoved = false; ///resets any pieces that moved
		moveable = null;
		moveablecoords = null;
		removed = null;
		for (int i=0; i < pieces.length; i+=1) {
			if (pieces[i] != null) {
			pieces[i].doneCapturing();
			}
		} ///resets all pieces that have captured
	}
	
	public String winner() {
		int fireleft = 0;
		int waterleft = 0;
		for (int i=0; i < pieces.length; i+=1) {
			if (pieces[i] != null) {
			if (pieces[i].isFire()) {
				fireleft += 1;
			}
			if (!pieces[i].isFire()) {
				waterleft += 1;
			}
			}///not null
		}
		if (waterleft == 0 && fireleft == 0) {
			return("No one");
		}
		if (waterleft == 0) {
			return("Fire");
		}
		if (fireleft == 0) {
			return("Water");
		}
		return null;
	}
	
	public boolean canSelect(int x, int y) {
		if (pieceAt(x,y) != null) {
			if (hasmoved) {
				return false;
			}
			for (int i=0; i < pieces.length; i+=1) {
				if (piecesloc[i] != null) {
					if (x == piecesloc[i][0] && y == piecesloc[i][1]) {
						if ((firesturn && pieces[i].isFire()) || (!firesturn && !pieces[i].isFire())) {
							return true;
	
						}
				}
			}
			}///end for loop
			return false;
		}	//piece on space, checks right color
		
		if (pieceAt(x,y) == null) {
			if (moveable != null && hasmoved && validmove(moveablecoords[0],moveablecoords[1],x,y)) {
				return true;
			}
			if (moveable != null && !hasmoved && validmove(moveablecoords[0],moveablecoords[1],x,y)) {
				return true;
			}
			if (moveable != null && moveable.hasCaptured() && validmove(moveablecoords[0],moveablecoords[1],x,y)) {
				return true;
			} //multijump
		}
		return false;
	}
		///empty space options
		


	
 	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			for (int i=0; i < pieces.length; i+=1) {
				if (pieces[i] == pieceAt(x,y)) {
 					moveable = pieces[i];
 					moveablecoords = piecesloc[i];
 				}
 			}
 		}
		if (pieceAt(x,y) == null) {
			moveable.move(x,y);
			hasmoved = true;
		} //empty space selected
	}
	
	private boolean validmove(int xi, int yi, int xf, int yf) {
		int avgx = (xi + xf) / 2;
		int avgy = (yi + yf) / 2;
		if (!hasmoved) {
		if ((xi+yi)%2 != 0 || (xf+yf)%2 != 0) {
			return false;
		}
		if (((firesturn && yf < yi) || (!firesturn && yf > yi)) && !moveable.isKing()) {
			return false;
		} ///attempt to move backwards
		
		if (pieceAt(avgx, avgy) != null && firesturn && !pieceAt(avgx, avgy).isFire() && abs(xf-xi)==2 && abs(yf-yi)==2) {
			return true;
		}
		if (pieceAt(avgx, avgy) != null && !firesturn && pieceAt(avgx, avgy).isFire() && abs(xf-xi)==2 && abs(yf-yi)==2) {
			return true;
		}
		if (abs(xf-xi)+abs(yf-yi)==2 && xi != xf && yi != yf && pieceAt(xf, yf)==null) {
			return true;
		}
		return false;
		} ///all valid moves if first time moving
		
		else {
			if (pieceAt(avgx, avgy) != null && firesturn && !pieceAt(avgx, avgy).isFire() && abs(xf-xi)==2 && abs(yf-yi)==2) {
				return true;
			}
			if (pieceAt(avgx, avgy) != null && !firesturn && pieceAt(avgx, avgy).isFire() && abs(xf-xi)==2 && abs(yf-yi)==2) {
				return true;
			}
			return false;
		}
	}

	private int abs(int i) {
		if (i<0) {
			return -i;
		}
		return i;
	}	
	
	

}
