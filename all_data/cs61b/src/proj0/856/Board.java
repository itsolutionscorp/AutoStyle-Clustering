public class Board {
	private static int sideN=8;
	private Piece[][] piece_on_board;
	private boolean fireTurn;
	private int xselect;
	private int yselect;
	private boolean isMoved;
	private int nFire;
	private int nWater;
	private int signMove;

	// Constructor method
	public Board(boolean shouldbeEmpty){
		nWater=0;
		nFire=0;
		xselect = -1;
		yselect = -1;
		isMoved = false;
		fireTurn = true;
		signMove=1;
		piece_on_board = new Piece[sideN][sideN];
		// place inital pieces
		if (!shouldbeEmpty){
			setpieces(true);  //fire pieces
			setpieces(false); //water pieces
		}
		//System.out.println("Initial Fire: "+nFire+"Initial Water: "+nWater);
	}

		// set initial piece locations; the follow three are helper methods for Board
		private void setpieces(Boolean isFire){
			int y;
			for (int j = 0; j<3;j++){
				String type = setpiecestype(j); // set pieces type
				// switch lines depending on whether the team is fire or water.
				if (isFire) y = j; 
				else 		y = sideN-j-1;
				// set chess in the same line
				for (int x = 0; x<sideN;x++){
					if ((x+y)%2 ==0){
						place(new Piece(isFire, this, x, y, type), x,y); 
					}						
				}
			}
			return;
		}

		private String setpiecestype(int line){
			switch (line) {
				case 0: return "pawn";
				case 1: return "shield";
				case 2: return "bomb"; 
			}
			return "pawn";
		}

		private String imageurl(Piece p){
			String url;
			// decide piece type
			if (p.isBomb()) 		url = "bomb";
			else if (p.isShield()) 	url = "shield";
			else					url = "pawn";
			// decide piece team
			if (p.isFire())	url = url+"-fire";
			else			url = url+"-water";
			// decide crowned
			if (p.isKing()) url = url + "-crowned";
			// add suffix
			url = "img/"+ url + ".png";
			return url;
		}

	//Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.
	public Piece pieceAt(int x, int y){
		if (isOutofBounds(x,y)) return null;
		return piece_on_board[x][y];
	}

		// is x,y out of bounds of the Board?
		private Boolean isOutofBounds(int x, int y){
			return (x<0||y<0||x>=sideN||y>=sideN);
		}

		//check if we have already selected any location
		private boolean isSelected(){
			return !isOutofBounds(xselect, yselect);
		}

		// is (x,y) an empty space?
		private boolean isEmptyspace(int x, int y){
			return (pieceAt(x, y) == null);
		}

		// validMove series (helps canSelect)
		private boolean validMove(int xi, int yi, int xf, int yf){
			if (!isEmptyspace(xi,yi) && isEmptyspace(xf,yf) ){
				if (isDiagonal(xi, yi, xf, yf, signMove*1,pieceAt(xi,yi).isKing()))return true; // can move
				if (validCapture(xi,yi,xf,yf)) return true; // can capture
			}
			return false;
		} 

		private boolean validCapture(int xi, int yi, int xf, int yf){
			int xcapture = (xi+xf)/2;
			int ycapture = (yi+yf)/2;
			Piece capPiece = pieceAt(xcapture,ycapture);
			Piece movPiece = pieceAt(xi,yi);
			// empty place check
			if (!isEmptyspace(xi,yi)&&isEmptyspace(xf,yf)&&!isEmptyspace(xcapture,ycapture)){
				// team check and diagonal check
				if (capPiece.isFire() != movPiece.isFire() && isDiagonal(xi, yi, xf, yf, signMove*2,movPiece.isKing())){
					return true;	
				}
			}
			return false;
		}

		private boolean isDiagonal(int xi, int yi, int xf, int yf, int dist, boolean isKing){
			if (isKing) 	return (Math.abs(xi-xf)==Math.abs(dist) && Math.abs(yf-yi)==Math.abs(dist));
			else 			return (Math.abs(xi-xf)==Math.abs(dist) && (yf-yi)==dist);
		}

	public boolean canSelect(int x, int y){
		Piece p =pieceAt(x, y);
		// Attempt to select a piece
		if (!isEmptyspace(x,y) && fireTurn == p.isFire()){
			if (isSelected() && isMoved) return false;
			else return true;
		} 
		// Attempt to select an empty space
		if (isEmptyspace(x,y) && !isEmptyspace(xselect,yselect)){
			//case for a move, check if it is valid
			if (!isMoved && validMove(xselect,yselect,x,y)){ //important: note the argument!
				return true;
			}
			//case for 2nd capture: note that it must be a capture... so validMove must adapt
			if (isMoved && pieceAt(xselect,yselect).hasCaptured()&&validCapture(xselect,yselect,x,y)){
				//System.out.print("2nd Capt ");
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y){
		int xinit = xselect;
		int yinit = yselect;
		xselect = x;
		yselect = y;
		if (!isEmptyspace(xinit,yinit) && isEmptyspace(xselect,yselect)){ //basic checks so that the move is okay with manual operation
			pieceAt(xinit,yinit).move(xselect,yselect);
			isMoved=true;
			//System.out.println(" MOVE: F:"+nFire+" W:"+nWater+ " ("+xinit+","+yinit+") TO ("+xselect+","+yselect+")");
		}
		return;
	}
		// outBoundOrNull() and clearPiecefromBoard() are helpers for place()
		private boolean outBoundOrNull(Piece p, int x, int y){
			if (isOutofBounds(x,y)){ 		// out of bounds exception
				System.out.println("Piece out of bounds");
				return true;
			}
			if (p==null){ 					// null piece exception
				System.out.println("Piece does not exist");
				return true;
			}
			return false;
		}

		// supply piece location for place to remove from board
		private void clearPiecefromBoard(Piece p){
			// loop over the array
			for (int i = 0; i<sideN;i++){
				for (int j = 0; j<sideN;j++){
					if (piece_on_board[i][j]==p)  remove(i,j);
				}
			}
			return;
		}

	public void place(Piece p, int x, int y){
		if (outBoundOrNull(p, x, y)) return; // deals with null piece and out of bounds location
		clearPiecefromBoard(p);
		piece_on_board[x][y] = p;
		if (p.isFire()) nFire+=1;
		else			nWater+=1;
		//System.out.println("++ Fire: "+nFire+"Water: "+nWater);
		// Don't you need to set x,y in the piece object? ==> taken cared by piece.move method
		return;
	}


	/*  Executes a remove. Returns the piece that was removed. If 	*
	 *  the input (x, y) is out of bounds, returns null and prints 	*
	 *  an appropriate message. If there is no piece at (x, y),   	*
	 *  returns null and prints an appropriate message.				*/
	public Piece remove(int x, int y){
		Piece temp = pieceAt(x,y);
		// out of bounds or null exception
		if (outBoundOrNull(temp, x, y)) return null;
		piece_on_board[x][y] = null;
		// update counter
		if (temp.isFire()) nFire-=1;
		else			nWater-=1;
		//System.out.println("-- Fire: "+nFire+"Water: "+nWater);
		return temp;
	}

	public boolean canEndTurn(){
		return isMoved;
	}

	public void endTurn(){
		if (!isEmptyspace(xselect,yselect)){
			pieceAt(xselect,yselect).doneCapturing(); 
			//please check that all methods stays selecting the peice in end turn!
		}
		signMove=-signMove;
		isMoved=false;
		fireTurn=!fireTurn;
		xselect= -1;
		yselect= -1;
		return;
	} 

	public String winner(){
		if (nFire==0&&nWater==0){
			return "No one";
		}else if (nFire==0){
			return "Water";
		}else if (nWater==0){
			return "Fire";
		}
		return null;
	}

		// private method drawing the board. Only used by main
		private void drawBoard(){
			for (int i = 0; i<sideN;i++){
				for (int j = 0; j<sideN;j++){
					if (i == xselect && j == yselect){
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					}else if ((i+j)%2 ==0){
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}else{
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(i+.5,j+.5,0.5);
					Piece p = pieceAt(i, j);
					if (p!= null) StdDrawPlus.picture(i+.5,j+.5,imageurl(p),1,1);	
				}
			}
			return;
		}

	public static void main(String[] args) {
		StdDrawPlus.setScale(0,8);
		Board b1= new Board(false); // false to display chess piece
		int xmouse;
		int ymouse;
		String winner=null;
		boolean printWinner =false;
		//b1.place(new Piece(true, b1, 0,0, "pawn"),0,0);  //testing purpose
		//b1.place(new Piece(false, b1, 1,1, "shield"),1,1);

		while (true){
			while (winner==null){
				if (StdDrawPlus.mousePressed()){
					xmouse=(int) StdDrawPlus.mouseX();
					ymouse=(int) StdDrawPlus.mouseY();
					if (b1.canSelect(xmouse,ymouse)){
						b1.select(xmouse,ymouse); // this initiate all actions
					}
				}
				if (StdDrawPlus.isSpacePressed()&&b1.canEndTurn()){
					b1.endTurn();
				}
				b1.drawBoard();
				StdDrawPlus.show(40);
				winner = b1.winner();
				if (StdDrawPlus.isNPressed()){
					b1 = new Board(false);
					winner = null;
				}
			}
			if (printWinner==false){
				System.out.println(winner);
				printWinner=true;
			}
			StdDrawPlus.show(100);
			if (StdDrawPlus.isNPressed()){
				b1 = new Board(false);
				winner = null;
				printWinner=false;
			}
		}
	}
}




