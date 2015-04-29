public class Board {

	private boolean movePiece;
	private Piece [][] pieces=new Piece[8][8];
	// private Piece [][] temp;
	private Piece selectedPiece;
	private int player;
	private int [] selectedPieceCoor=new int [2];

	/*starts a GUI supported version of the game*/
	public static void main(String[] args) {
		Board game=new Board(false);
		while (game.winner()==null) {
			if (StdDrawPlus.isNPressed()==true) game=new Board(false);
			StdDrawDemo.drawEmptyBoard(8);
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (game.pieces[x][y]!=null) {
						if (game.pieces[x][y]==game.selectedPiece) {
							StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
							StdDrawPlus.filledSquare(x+0.5, y+0.5, 0.5);
						}
						StdDrawDemo.drawPiece(game.pieces[x][y],x,y);
					}		
				}
			}
			StdDrawPlus.show(50);
			if (StdDrawPlus.mousePressed()==true) {
				int x=(int)(StdDrawPlus.mouseX());
				int y=(int)(StdDrawPlus.mouseY());
				if (game.canSelect(x,y)==true) {
					// game.temp=game.pieces;
					game.select(x,y);
				}
			}
			if (StdDrawPlus.isSpacePressed()==true) {
				if (game.canEndTurn()==true)
					game.endTurn();
			}
			// else if (StdDrawPlus.isKeyPressed(StdDrawPlus.KeyEvent.VK_U)==true &&
			// 	game.movePiece==true) game.pieces=game.temp;
		}
		System.out.print(game.winner());
	}

	/*The constructor for Board. If shouldBeEmpty is true, initializes an 
	empty Board. Otherwise, initializes a Board with the default configuration.
	Note that an empty Board could possibly be useful for testing purposes.*/
	public Board(boolean shouldBeEmpty) {
		this.player=0;
		this.selectedPiece=null;
		this.movePiece=false;
		if (shouldBeEmpty==false) {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (y==0 && x%2==0)
						pieces[x][y]=new Piece(true, this, x, y, "pawn");
					else if (y==1 && x%2!=0)
						pieces[x][y]=new Piece(true, this, x, y, "shield");
					else if (y==2 && x%2==0)
						pieces[x][y]=new Piece(true, this, x, y, "bomb");
					else if (y==5 && x%2!=0)
						pieces[x][y]=new Piece(false, this, x, y, "bomb");
					else if (y==6 && x%2==0)
						pieces[x][y]=new Piece(false, this, x, y, "shield");
					else if (y==7 && x%2!=0)
						pieces[x][y]=new Piece(false, this, x, y, "pawn");
				}
			}
			
		}
	}

	/*Gets the piece at position (x, y) on the board, or null if there is no 
	piece. If (x, y) are out of bounds, returns null.*/
	public Piece pieceAt(int x, int y) {
		if (x>=8 || y>=8 || x<0 || y<0) return null;
		else if (pieces[x][y]!=null) return pieces[x][y];
		else return null;
	}

	/*Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
	nothing. If p already exists in the current Board, first removes it
	from its initial position. If another piece already exists at (x, y),
	p will replace that piece. (This method is potentially useful for creating 
	specific test circumstances.)*/
	public void place(Piece p, int x, int y) {
		if (x>=0 && y>=0 && x<8 && y<8 && p!=null) {
			if (pieces[x][y]!=null) remove(x,y);
			p.move(x,y);
			if (p.hasCaptured()==true && p.isBomb()==true) pieces[x][y]=null;
			else pieces[x][y]=p;
		}
	}

	/*Executes a remove. Returns the piece that was removed. If the 
	input (x, y) is out of bounds, returns null and prints an appropriate
	message. If there is no piece at (x, y), returns null
	and prints an appropriate message.*/
	public Piece remove(int x, int y) {
		if (x<0 || y<0 || x>=8 || y>=8) {
			System.out.println("coordinate out of range");
			return null;
		}
		else if (pieces[x][y]==null) {
			System.out.println("no piece at current coordinate");
			return null;
		}
		else {
			Piece removed=pieces[x][y];
			pieces[x][y]=null;
			return removed;
		}
	}

	/*Returns true if there is a piece the piece at (x, y) and it
	can be selected.*/
	public boolean canSelect(int x, int y) {
		if (x>=0 && x<8 && y>=0 && y<8) {
			if (pieces[x][y]!=null && pieces[x][y].side()==player) {
				if (movePiece==false) return true;
			}
			else if (pieces[x][y]==null) {
				if (selectedPiece!=null &&
					validMove(selectedPieceCoor[0],selectedPieceCoor[1],x,y)) {
					if (movePiece==false) return true;
				    else if (selectedPiece.hasCaptured()==true &&
				    	validCapture(selectedPieceCoor[0],selectedPieceCoor[1],x,y))
				    	return true;
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi>=0 && xi<8 && yi>=0 && yi<8 &&
			xf>=0 && xf<8 && yi>=0 && yi<8) {
			Piece a=pieces[xi][yi];
		    Piece b=pieces[xf][yf];
		    if (a!=null && b==null) {
		    	if (xi+1==xf || xi-1==xf) {
		    		if (a.isKing()==true && (yi+1==yf || yi-1==yf))
		    			return true;
		    		else if ((a.isFire()==true && yi+1==yf) ||
		    			(a.isFire()==false && yi-1==yf))
		    			return true;
		    	}
		    	else if (xi+2==xf || xi-2==xf) {
		    		if (a.isKing()==true && (yi+2==yf || yi-2==yf)) {
		    			int c=(xi+xf)>>>1;
		    			int d=(yi+yf)>>>1;
		    			if (pieces[c][d]!=null && pieces[c][d].side()!=a.side())
		    				return true;
		    		}
		    		else if ((a.isFire()==true && yi+2==yf) ||
		    			(a.isFire()==false && yi-2==yf)) {
		    			int e=(xi+xf)>>>1;
		    		    int f=(yi+yf)>>>1;
		    		    if (pieces[e][f]!=null && pieces[e][f].side()!=a.side())
		    		    	return true;
		    		}
		    	}
		    }
		}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		if (xi>=0 && xi<8 && yi>=0 && yi<8 &&
			xf>=0 && xf<8 && yi>=0 && yi<8) {
			Piece a=pieces[xi][yi];
		    Piece b=pieces[xf][yf];
		    if (a!=null && b==null) {
		    	if (xi+2==xf || xi-2==xf) {
		    		if (a.isKing()==true && (yi+2==yf || yi-2==yf)) {
		    			int c=(xi+xf)>>>1;
		    			int d=(yi+yf)>>>1;
		    			if (pieces[c][d]!=null && pieces[c][d].side()!=a.side())
		    				return true;
		    		}
		    		else if ((a.isFire()==true && yi+2==yf) ||
		    			(a.isFire()==false && yi-2==yf)) {
		    			int e=(xi+xf)>>>1;
		    		    int f=(yi+yf)>>>1;
		    		    if (pieces[e][f]!=null && pieces[e][f].side()!=a.side())
		    		    	return true;
		    		}
		    	}
		    }
		}
		return false;
	}

	/*Selects the piece at (x, y) if possible. Optionally, it is recommended
	to color the background of the selected square white on the GUI
	via the pen color function. For any piece to perform a capture,
	that piece must have been selected first.*/
	public void select(int x, int y) {
		if (pieces[x][y]==null) {
			place(selectedPiece,x,y);
			movePiece=true;
		}
		selectedPiece=pieces[x][y];
		selectedPieceCoor[0]=x;
		selectedPieceCoor[1]=y;
	}

	/*Returns whether or not the the current player can end their turn. 
	To be able to end a turn, a piece must have moved or performed a capture.*/
	public boolean canEndTurn() {
		if (movePiece==true)
			return true;
		return false;
	}

	/*Called at the end of each turn. Handles switching of players
	and anything else that should happen at the end of a turn.*/
	public void endTurn() {
		if (player==0) player=1;
		else player=0;
		if (selectedPiece!=null) selectedPiece.doneCapturing();
		selectedPiece=null;
		selectedPieceCoor=new int [2];
		movePiece=false;
	}

	/*Returns the winner of the game. "Fire", "Water", "No one"
	(tie / no pieces on the board), or null if the game is not yet over.
	Assume there is no stalemate situation in which the current player
	has pieces but cannot legally move any of them (In this event,
	just leave it at null). Determine the winner solely by
	the number of pieces belonging to each team.*/
	public String winner() {
		int fire=0;
		int water=0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (pieces[x][y]!=null) {
					if (pieces[x][y].isFire()==true) fire+=1;
					else water+=1;
				}
			}
		}
		if (fire==0 && water==0) return "No one";
		else if (fire==0) return "Water";
		else if (water==0) return "Fire";
		else return null;
	}
}