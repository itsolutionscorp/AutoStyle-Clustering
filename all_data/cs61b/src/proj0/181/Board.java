/*
 * @author Jennifer Dai
 */
public class Board{

	private Piece[][] pieces = new Piece[8][8];
	private int player;
	private boolean moved;
	private Piece selectedPiece;

	/*
	 * Starts a GUI supported version of the game.
	 */
	public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.winner()==null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int) x, (int) y)){
                	b.select((int)x, (int)y);
                }
            }            
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(25);
        }

	}

	 /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                if(pieces[i][j] != null){
                	if(pieces[i][j].isFire()){
                		if(pieces[i][j].isBomb()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire-crowned.png",1,1);
                			}
                			else{
                				StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire.png",1,1);
                			}
                		}
                		else if(pieces[i][j].isShield()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire-crowned.png",1,1);
                			}  
                			else{              			
                				StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire.png",1,1);
                			}
                		}
                		else{
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png",1,1);
                			}
                			StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire.png",1,1);
                		}
                	}
                	else{
                		if(pieces[i][j].isBomb()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water-crowned.png",1,1);
                			}
                			else{
                				StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water.png",1,1);
                			}
                		}
                		else if(pieces[i][j].isShield()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i+.5, j+.5, "img/shield-water-crowned.png",1,1);
                			}  
                			else{              			
                				StdDrawPlus.picture(i+.5, j+.5, "img/shield-water.png",1,1);
                			}
                		}
                		else{
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water-crowned.png",1,1);
                			}
                			StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water.png",1,1);
                		}
                	}
                }

            }
        }
    }

	/*
	 * The constructor for Board.
	 * If shouldBeEmpty is true, initializes an empty Board.
	 * Otherwise, initializes a Board with the default configuration.
	 */
	public Board(boolean shouldBeEmpty){
		this.pieces = pieces;
		if(shouldBeEmpty==false){
			//initialize a Board with the default configuration
			for(int x=0; x<8; x++){
				for(int y=0; y<8; y++){
					if(y==0 && x%2==0){
						pieces[x][y] = new Piece(true, this, x, y, "pawn");
					}
					if(y==1 && x%2!=0){
						pieces[x][y] = new Piece(true, this, x, y, "shield");
					}
					if(y==2 && x%2==0){
						pieces[x][y] = new Piece(true, this, x, y, "bomb");
					}
					if(y==5 && x%2!=0){
						pieces[x][y] = new Piece(false, this, x, y, "bomb");
					}
					if(y==6 && x%2==0){
						pieces[x][y] = new Piece(false, this, x, y, "shield");
					}
					if(y==7 && x%2!=0){
						pieces[x][y] = new Piece(false, this, x, y, "pawn");
					}
				}
			}
		}
	}

	/*
	 * Gets the piece at position (x, y) on the board, or
	 * null if there is no piece. If (x, y) are out of bounds, returns
	 * null.
	 */
	public Piece pieceAt(int x, int y){
		if(x>7 || x<0 || y>7 || y<0){
			return null;
		}
		return pieces[x][y];
	}

	/*
	 * Returns true if (x,y) can be selected.
	 */
	public boolean canSelect(int x, int y){
		if(pieceAt(x,y)==null){
			if(selectedPiece!=null && moved==false && validMove(selectedPiece, x, y)){
				return true;
			}
			if(selectedPiece!=null && moved && selectedPiece.hasCaptured() && validMove(selectedPiece, x, y)){
				return true;
			}
		}
		else{
			if(pieceAt(x,y).side()!=player){
				return false;
			}
			if(selectedPiece == null || (selectedPiece !=null && moved==false)){
				return true;
			}
		}
		return false;
	}

	private boolean validMove(Piece p, int xf, int yf){
		if(xf>7 || yf>7){
			return false;
		}
		int xi=0;
		int yi=0;
		for(int a=0; a<8; a++){
			for(int b=0; b<8; b++){
				if(pieces[a][b]==p){
					xi = a;
					yi = b;
				}
			}
		}
		if(p.isKing()){
			if(p.hasCaptured()){
				if(Math.abs(yf-yi)==2 && Math.abs(xf-xi)==2 && 
					pieceAt((xi+xf)/2, (yi+yf)/2)!=null && pieceAt((xi+xf)/2, (yi+yf)/2).side()!=player){
					return true;
				}
				return false;
			}
			if((yf==yi+1 || yf==yi-1) && (xf==xi+1 || xf==xi-1)){
				return true;
			}
			if((yf==yi+2 || yf==yi-2) && (xf==xi+2 || xf==xi-2)){
				if(pieceAt((xi+xf)/2, (yi+yf)/2)!=null && pieceAt((xi+xf)/2, (yi+yf)/2).side()!=player){
					return true;
				}
				return false;
			}
		}
		else if(p.isFire()){
			if(p.hasCaptured()){
				if(yf-yi==2 && Math.abs(xf-xi)==2 &&
					pieceAt((xi+xf)/2, (yi+yf)/2)!=null && pieceAt((xi+xf)/2, (yi+yf)/2).side()!=player){
					return true;
				}
				return false;
			}
			if(yf == yi+1 && (xf==xi+1 || xf==xi-1)){
				return true;
			}
			if(yf==yi+2 && (xf==xi+2 || xf==xi-2)){
				if(pieceAt((xi+xf)/2, (yi+yf)/2)!=null && pieceAt((xi+xf)/2, (yi+yf)/2).side()!=player){
					return true;
				}
				return false;
			}
		}
		else{
			if(p.hasCaptured()){
				if(yf-yi==-2 && Math.abs(xf-xi)==2 &&
				   pieceAt((xi+xf)/2, (yi+yf)/2)!=null && pieceAt((xi+xf)/2, (yi+yf)/2).side()!=player){
					return true;
				}
				return false;
			}
			if(yf == yi-1 && (xf==xi+1 || xf==xi-1)){
				return true;
			}
			if(yf==yi-2 && (xf==xi+2 || xf==xi-2)){
				if(pieceAt((xi+xf)/2, (yi+yf)/2)!=null && pieceAt((xi+xf)/2, (yi+yf)/2).side()!=player){
					return true;
				}
				return false;
			}
		}
		return false;
	}

	/*
	 * Selects the piece at (x, y). Assumes canSelect returns true.
	 */
	public void select(int x, int y){
		if(pieceAt(x,y)==null){
			//place(selectedPiece,x,y);
			selectedPiece.move(x,y);
			//place(selectedPiece,x,y);
			this.moved = true;
		}
		else{
			selectedPiece=pieces[x][y];
		}
	}

	/*
	 * Places p at (x, y). If (x, y) is out of bounds, does nothing.
	 * If p already exists in the current Board, first removes it from its initial position.
	 * If another piece already exists at (x, y), p will replace that piece.
	 */
	public void place(Piece p, int x, int y){
		if(x>7 || x<0 || y>7 || y<0 || p == null){
			return;
		}
		// for(int a=0; a<8; a++){	
		// 	for(int b=0; b<8; b++){
		// 		if(pieces[a][b] == p){
		// 			this.remove(a,b);
		// 			pieces[a][b]=null;
		// 		}
		// 	}
		// }
		//this.remove(x,y);
		pieces[x][y] = p;
	}

	/*
	 * Executes a remove. Returns the piece that was removed.
	 * If the input (x, y) is out of bounds, returns null and prints an 
	 * appropriate message. 
	 * If there is no piece at (x, y), returns null and prints an
	 * appropriate message.
	 */
	public Piece remove(int x, int y){
		if(x>7 || x<0 || y>7 || y<0){
			System.out.println("input position out of bounds");
			return null;
		}
		Piece removed = pieces[x][y];
		if(removed == null){
			System.out.println("no piece to remove at input position");
			return null;
		}
		this.pieces[x][y] = null;
		return removed;
	}

	/*
	 * Returns whether or not the current player can end their turn.
	 * To be able to end a turn, a piece must have moved or performed a capture.
	 */
	public boolean canEndTurn(){
		if(moved){
			return true;
		}
		if(selectedPiece!=null && selectedPiece.hasCaptured()){
			return true;
		}
		return false;
	}

	/*
	 * Called at the end of each turn. Handles switching of players and
	 * anything else that should happen at the end of a turn.
	 */
	public void endTurn(){
		player = (player+1)%2;
		moved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
	}

	/*
	 * Returns the winner of the game. "Fire", "Water", "No one" (tie/no pieces on the board),
	 * or null if the game is not yet over.
	 * Determine the winner solely by the number of pieces belonging to each team.
	 */
	public String winner(){
		int firePieces = 0;
		int waterPieces = 0;
		for(int a=0; a<8; a++){
			for(int c=0; c<8; c++){
				if(pieces[a][c]!=null){
					if(pieces[a][c].isFire()){
						firePieces += 1;
					}
					else{
						waterPieces += 1;
					}
				}
			}
		}
		if(firePieces!=0 && waterPieces!=0){
			return null;
		}
		if(firePieces==0 && waterPieces==0){
			return "No one";
		}
		if(firePieces==0){
			return "Water";
		}
		return "Fire";
	}
}