public class Board {
	private Piece[][] pieces;
	private int[] selectedPiece;  //array that gives location of selected piece
	private boolean hasMovedPiece;//whether player has moved a piece yet
	private int currentPlayer; 	  //0 for fire's turn, 1 for water's turn;
	private boolean hasCaptured;  //if given player has captured a piece in THIS turn
	private int sideFactor = 1;   //toggles between -1 and 1 so that we know what direction
								  //the pieces can move in
								  //should be 1 for fire, -1 for water

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];

		selectedPiece = new int[2];
		selectedPiece[0] = -1;
		selectedPiece[1] = -1;

		hasMovedPiece = false;
		currentPlayer = 0;
		if (!shouldBeEmpty) {
			this.initializeFirePieces();
			this.initializeWaterPieces();
		}
	}

	public static void main (String args []) {
		boolean boardEmpty = false;
		Board b = new Board(boardEmpty);

		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        b.drawBoard();
        while(b.winner() == null) {
        	b.drawBoard();
        	if (StdDrawPlus.mousePressed()) {
        		double x = StdDrawPlus.mouseX();
        		double y = StdDrawPlus.mouseY();
        		if (b.canSelect((int)x,(int)y))
        			b.select((int)x,(int)y);
        		//pieces[(int) x][(int) y] = true;
        	}
        	if (StdDrawPlus.isSpacePressed()) {
        		if (b.canEndTurn()) {
        			b.endTurn();
        		}
        	}
        	StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
	}

	//called everytime new move is made and re-draws board
	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece pieceToDraw = this.pieceAt(i,j);
                if (pieceToDraw !=null) {
                	if (pieceToDraw.side() == 0) { //for fire pieces
                		if (pieceToDraw.isKing()) {
                			if (pieceToDraw.isBomb())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png",1,1);
                			else if (pieceToDraw.isShield())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png",1,1);
                			else
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png",1,1);
                		}
                		else {
                			if (pieceToDraw.isBomb())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png",1,1);
                			else if (pieceToDraw.isShield())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png",1,1);
                			else
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png",1,1);
                		}
                	}
                	else { //for water pieces
                		if (pieceToDraw.isKing()) {
                			if (pieceToDraw.isBomb())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png",1,1);
                			else if (pieceToDraw.isShield())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png",1,1);
                			else
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png",1,1);
                		}
                		else {
                			if (pieceToDraw.isBomb())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png",1,1);
                			else if (pieceToDraw.isShield())
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png",1,1);
                			else
                				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png",1,1);
                		}
                	}
                }
            }
        }
	}

	//adds water pieces to board when called
	private void initializeWaterPieces() {
		//Initializes four water pawns at top of board
		for (int i = 1; i < 8; i += 2) {
			Piece p = new Piece(false, this, i, 7, "pawn");
			pieces[i][7] = p;
			//StdDrawPlus.picture(i + 0.5, 7.5, "img/pawn-water.png", 1, 1);
		}

		//Initializes the four shield pieces
		for (int i = 0; i < 8; i += 2) {
			Piece p = new Piece(false, this, i, 6, "shield");
			pieces[i][6] = p;
			//StdDrawPlus.picture(i + 0.5, 6.5, "img/shield-water.png", 1, 1);
		}

		//Initializes the four bomb pieces
		for (int i = 1; i < 8; i += 2) {
			Piece p = new Piece(false, this, i, 5, "bomb");
			pieces[i][5] = p;
			//StdDrawPlus.picture(i + 0.5, 5.5, "img/bomb-water.png", 1, 1);
		}
	}

	private void initializeFirePieces() {
		//Initializes four fire pawns at bottom of board
		for (int i = 0; i < 7; i += 2) {
			Piece p = new Piece(true, this, i, 0, "pawn");
			pieces[i][0] = p;
			//StdDrawPlus.picture(i + 0.5 , 0.5 ,"img/pawn-fire.png", 1, 1);
		}

		//Initializes the four shield pieces
		for (int i = 1; i < 8; i += 2) {
			Piece p = new Piece(true, this, i, 1, "shield");
			pieces[i][1] = p;
			//StdDrawPlus.picture(i + 0.5, 1.5, "img/shield-fire.png", 1, 1);
		}

		//Initializes the four bomb pieces
		for (int i = 0; i < 8; i += 2) {
			Piece p = new Piece(true, this, i, 2, "bomb");
			pieces[i][2] = p;
			//StdDrawPlus.picture(i + 0.5, 2.5, "img/bomb-fire.png", 1, 1);
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0))
			return null;
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece presentPiece = pieceAt(x,y);
		if ((selectedPiece[0] == -1) && (selectedPiece[1] == -1)) {
			//we know no piece has been selected
			if (presentPiece != null)
				return presentPiece.side() == currentPlayer;
			return false;
		}
		else {
			//a piece was already selected
			
			//handles re-selecting own piece
			if (hasCaptured && canMultiCapture()) {
				if (this.pieceAt(x,y) == null) {
					return canCapture(selectedPiece[0],x,selectedPiece[1],y);
				}
				return false;
			}
		 	if ((presentPiece != null) && (this.pieceAt(selectedPiece[0],selectedPiece[1]) != null) && ((presentPiece.side() == this.pieceAt(selectedPiece[0],selectedPiece[1]).side()))) {
		 		if (!hasCaptured) {
		 			selectedPiece[0] = x;
		 			selectedPiece[1] = y;
		 			return true;
		 		}
		 		return false;
		 	}
			if ((!hasMovedPiece) || ((hasMovedPiece) && (!hasCaptured))) {
				return isValidMove(selectedPiece[0],selectedPiece[1],x,y);
			}
			return false;
		}
	}

	private boolean isValidMove(int xfrom, int yfrom, int xto, int yto) {
		//Piece p = this.pieceAt(xfrom, yfrom);
		Piece pieceFrom = this.pieceAt(xfrom,yfrom);
		// System.out.println(selectedPiece[0]);
		// System.out.println(selectedPiece[1]);

		//can't go to a piece at a location where there's already a piece
		if ((this.pieceAt(xto, yto)) != null)
			return false;
		if (pieceFrom == null)
			return false;
		if (!pieceFrom.isKing()) {
			return ((canCapture(xfrom,xto,yfrom,yto)) || (((yto - yfrom) == sideFactor) && (Math.abs(xto-xfrom) == 1)));
		}
		if (((Math.abs(xto - xfrom) == 2)) && (Math.abs(xto - xfrom) == Math.abs(yto - yfrom))) {
			int x_ave = (xto + xfrom)/2;
			int y_ave = (yto + yfrom)/2;
			return this.pieceAt(x_ave,y_ave).side() != currentPlayer;
		}
		return ((Math.abs(yto - yfrom) == 1) && (Math.abs(xto-xfrom) == 1));
	}

	private boolean canCapture(int xfrom, int xto, int yfrom, int yto) {
		Piece p = this.pieceAt(xfrom,yfrom);
		if (p == null)
			return false;
		if (p.isKing()) {
			if (((Math.abs(xto - xfrom) == 2)) && (Math.abs(xto - xfrom) == Math.abs(yto - yfrom))) {
				int x_ave = (xto + xfrom)/2;
				int y_ave = (yto + yfrom)/2;
				return this.pieceAt(x_ave,y_ave).side() != currentPlayer;
			}
			return false;
		}
		if (((yto - yfrom) == (sideFactor*2)) && (Math.abs(xto - xfrom) == 2)) {
			int xfrom_ave = (xto + xfrom)/2;
			Piece pieceinBetween = this.pieceAt(xfrom_ave,yfrom+sideFactor);
			if (pieceinBetween == null)
				return false;
			return pieceinBetween.side() != currentPlayer;
		}
		return false;
	}

	public void select(int x, int y) {
		//do stuff
		if ((selectedPiece[0] == -1) && (selectedPiece[1] == -1)) {
			selectedPiece[0] = x;
			selectedPiece[1] = y;

			//we do this to reset the hasCaptured after every successful capture
			hasCaptured = false;
		}
		else {
			Piece pieceToMove = this.pieceAt(selectedPiece[0],selectedPiece[1]);
			if (this.pieceAt(x,y) != null) {
				Piece pieceToSelect = this.pieceAt(x,y);
				if ((pieceToMove.side() == pieceToSelect.side()) && (!canMultiCapture())) {
					selectedPiece[0] = x;
					selectedPiece[1] = y;
				}
			}
			else {
				this.place(pieceToMove,x,y);
				if (pieceToMove == null)
					return;
				hasMovedPiece = true;
				if (((Math.abs(x-selectedPiece[0]))==2) && ((Math.abs(y-selectedPiece[1])) == 2)) {
					//handles captures
					int pieceBetweenx = (selectedPiece[0] + x)/2;
					int pieceBetweeny = (selectedPiece[1] + y)/2;
					Piece pieceinBetween = this.pieceAt(pieceBetweenx,pieceBetweeny);
					this.remove(pieceBetweenx,pieceBetweeny);
					hasCaptured = true;
					hasMovedPiece = true;
					// if (!canMultiCapture()) {
					// 	selectedPiece[0] = -1;
					// 	selectedPiece[1] = -1;
					// }
				}
				pieceToMove.move(x,y);
				if (hasCaptured) {
					selectedPiece[0] = x;
					selectedPiece[1] = y;
				}
			}
		}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
	}

	//assumed capture has already happened
	private boolean canMultiCapture() {
		Piece performingCapture = this.pieceAt(selectedPiece[0],selectedPiece[1]);
		if (performingCapture == null)
			return false;
		//checks each diagonal location
		if (performingCapture.isFire() || (performingCapture.isKing())) { //moving up
			Piece pieceToCapture = this.pieceAt(selectedPiece[0]+1,selectedPiece[1]+1);
			if ((pieceToCapture != null) && (pieceToCapture.side() != performingCapture.side())) {
				if (this.pieceAt(selectedPiece[0]+2,selectedPiece[1]+2) == null)
					return true;
			}
			pieceToCapture = this.pieceAt(selectedPiece[0]-1,selectedPiece[1]+1);
			if ((pieceToCapture != null) && (pieceToCapture.side() != performingCapture.side())) {
				if (this.pieceAt(selectedPiece[0]-2,selectedPiece[1]+2) == null)
					return true;
			}
		}
		if (!performingCapture.isFire() || (performingCapture.isKing())) { //moving down
			Piece pieceToCapture = this.pieceAt(selectedPiece[0]+1,selectedPiece[1]-1);
			if ((pieceToCapture != null) && (pieceToCapture.side() != performingCapture.side())) {
				if (this.pieceAt(selectedPiece[0]+2,selectedPiece[1]-2) == null)
					return true;
			}
			pieceToCapture = this.pieceAt(selectedPiece[0]-1,selectedPiece[1]-1);
			if ((pieceToCapture != null) && (pieceToCapture.side() != performingCapture.side())) {
				if (this.pieceAt(selectedPiece[0]-2,selectedPiece[1]-2) == null)
					return true;
			}		
		}
		return false;
	}

	public void place(Piece p, int x, int y) {

		//searches through whole board if p already exists
		//and removes if it does exist
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] == p)
					this.remove(i,j);
			}
		}

		if ((x >= 0) && (y >= 0) && (x < 8) && (y < 8) && (p != null)) {
			pieces[x][y] = p;
		}
	}

	public Piece remove (int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			System.out.println("Position (" + x + ", " + y + ") out of bounds");
			return null;
		}

		Piece toBeRemoved = pieceAt(x,y);
		if (toBeRemoved == null) {
			//System.out.println("No piece at position (" + x + ", " + y + ")");
		}
		this.pieces[x][y] = null;
		// Piece newRemove = null;

		// if (toBeRemoved.isBomb()) {
		// 	for (int i = -1; i <= 1; i++) {
		// 		for (int j = -1; j <= 1; j++) {
		// 			if ((i != 0) && (j != 0) && ((x+i) > -1) && ((x+i) < 8) && ((y+j) > -1) && ((y+j) < 8)) { //doesn't want to re-remove itself
		// 				newRemove = pieces[x+i][y+j];
		// 				if (newRemove != null) {
		// 					if (!newRemove.isShield()) {
		// 						//we checked for out of bounds/null, so no need
		// 						//to use remove
		// 						this.pieces[x+i][y+j] =null;
		// 					}
		// 				}
		// 			}
		// 		}
		// 	}
		// }
		return toBeRemoved;
	}

	public boolean canEndTurn() {
		//do stuff
		return hasMovedPiece || hasCaptured;
	}

	public void endTurn() {
		//do stuff
		hasMovedPiece = false;
		hasCaptured = false;
		Piece p = this.pieceAt(selectedPiece[0],selectedPiece[1]);
		if (p != null)
			p.doneCapturing();
		selectedPiece[0] = -1;
		selectedPiece[1] = -1;
		currentPlayer = 1 - currentPlayer;
		sideFactor = (-1) * sideFactor;
	}

	public String winner() {
		int fires = 0;
		int waters = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].side() == 0)
						fires++;
					else
						waters++;
				}
			}
		}
		if ((fires == 0) && (waters == 0))
			return "No one";
		if (fires == 0)
			return "Water";
		if (waters == 0)
			return "Fire";
		return null;
	}

}