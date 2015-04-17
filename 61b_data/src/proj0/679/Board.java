import java.lang.Math;

public class Board {
	//Variables
	private Piece[][] pieces = new Piece[8][8];
	private boolean fireTurn = true;
	private Piece selectedPiece;
	private int selectX, selectY;
	private boolean canEnd = false;
	private boolean canMove = true;
	private boolean mustJump = false;
	private boolean canSelectOther = true;

	/*	Constructor of the board, that starts off empty 
	*/
	public Board(boolean shouldBeEmpty) {
		if(shouldBeEmpty) {
			drawEmptyBoard();
		}
		else {
			fireTurn = true;
			startingPieces();
			drawAll();
		}
	}


	/*	Draws the 8x8 board with red and gray squares 
	*/
	private void drawEmptyBoard() {
		for(int row = 0; row < 8; row +=1) {
			for(int col = 0; col < 8; col += 1) {
				if((row + col) % 2 == 0) 	{ StdDrawPlus.setPenColor(StdDrawPlus.GRAY); }
				else						{ StdDrawPlus.setPenColor(StdDrawPlus.RED); }
				StdDrawPlus.filledSquare(row + .5, col + .5, .5);

			}
		}
	}

	private void drawAll() {
		drawEmptyBoard();
		for(int x = 0; x < 8; x+=1) {
			for(int y = (x%2); y < 8; y += 2) {
				Piece eachPos = pieces[x][y];
				drawPiece(eachPos, x, y);	
			}
			
		}
	}

	private void drawPiece(Piece eachPos, int x, int y){
		if(eachPos != null) {
			String pictureFile = "img/";

			//WHAT TYPE OF PIECE?
			if(eachPos.isBomb()) {
				pictureFile += "bomb";
			}
			else if(eachPos.isShield()) {
				pictureFile += "shield";
			}
			else {
				pictureFile += "pawn";
			}


			//FIRE or WATER?
			if(eachPos.isFire()) {
				pictureFile += "-fire";
			}
			else {
				pictureFile += "-water";
			}


			//CROWNED?
			if(eachPos.isKing()) {
				pictureFile += "-crowned";
			}

			pictureFile += ".png";
			StdDrawPlus.picture(x + .5, y + .5, pictureFile, 1, 1);
		}
	}

	private void startingPieces() {
		//Fill in fire's team
		fillRow(1,7,false,"pawn");
		fillRow(0,6,false,"shield");
		fillRow(1,5,false,"bomb");
		//Fill in fire's team
		fillRow(0,2,true,"bomb");
		fillRow(1,1,true,"shield");
		fillRow(0,0,true,"pawn");
	}
	//method used in startingPieces. Simplifies so i don't have 6 for loops
	private void fillRow(int x, int y, boolean isFire, String type) {
		for(int pos = x; pos < 8; pos += 2) {
			place(new Piece(isFire, this, pos, y, type), pos, y);
		}
	}

	public Piece pieceAt(int x, int y) {
		if(x>7 || y>7 || x<0 || y<0) {
			return null;
		}
		return pieces[x][y];
	}
	

	public boolean canSelect(int x, int y) {
		if(x<0 || y<0 || x>7 || y>7) {
			return false;
		}
		if(canMove == false) {
			return false;

		}

		Piece posPiece = pieceAt(x,y);
		if(selectedPiece == null && posPiece == null) {
			return false;
		}


		//Already selected a piece, now selecting a place to move or a different piece
		if(selectedPiece != null && posPiece == null) { 
				return validMove(selectX, selectY, x, y);
		}

		//Selecting the piece to move.
		else if(canSelectOther) {
			if(	(fireTurn && posPiece.isFire()) || 
				(!fireTurn && !posPiece.isFire())) {
				return true;
			}
		}

		return false;
	}


	private boolean validMove(int startX, int startY, int targetX, int targetY) {
		Piece posPiece = pieceAt(startX, startY);
		int xDist = Math.abs(startX - targetX);
		int yDist = Math.abs(startY - targetY);
		if(targetX<0 || targetY<0 ||targetX>7 || targetY>7){
			return false;
		}

		if((targetX + targetY) %2 != 0) { // Invalid target i.e. red space
			return false;
		}

		//No capture moves
		if( xDist == 1 && yDist == 1 && (!mustJump)) {

			//CROWNED. this piece can move up and down
			if(posPiece.isKing()) {
				return true;
			}

			//NOT CROWNED. if fire piece, must move up, water moves down
			else if(posPiece.isFire()) {
				return (targetY - startY == 1);
			}
			else if(!posPiece.isFire()) {
				return (targetY - startY == -1);
			}
			else{
				return false;
			}
		}

		//Capture moves
		else if(xDist== 2 && yDist == 2 && canMove) { 

			int capturedX = (startX + targetX) /2;
			int capturedY = (startY + targetY) /2;
			Piece capturedPiece = pieceAt(capturedX, capturedY);

			//Ensures that the capture piece exists and is enemy
			if(	capturedPiece == null || 
				capturedPiece.isFire() == posPiece.isFire()) {
				return false;
			}
			else if(posPiece.isKing()) {
				return true;
			}
			else if(posPiece.isFire() && targetY - startY == 2) {
				return true;
			}
			else if(!posPiece.isFire() && targetY - startY == -2) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if(selectedPiece == null) {
			changeSelected(x,y);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y +.5, .5);
			drawPiece(selectedPiece,x,y);
		}
		else if(pieceAt(x,y) != null) {
			changeSelected(x,y);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y +.5, .5);
			drawPiece(selectedPiece,x,y);

		}
		else if(pieceAt(x,y) == null) {
			selectedPiece.move(x,y);
			selectX = x;
			selectY = y;
			drawAll();
			canEnd = true;
			if(selectedPiece.hasCaptured() && !selectedPiece.isBomb()) {
				canSelectOther = false;
				for(int xf = -2; xf <=2 ; xf += 4) {
					for(int yf = -2; yf <=2 ; yf += 4){
						if(canSelect(x+xf, y+yf)) {
							canMove = true;
							mustJump = true;
							return;
						}
					}
				}
				canMove = false;
			}

			else {
				canMove = false;
				drawAll();
			}
		}
	}

	private void changeSelected(int x, int y) {
		selectedPiece = pieceAt(x,y);
		selectX = x;
		selectY = y;
		drawAll();
	}

	private void deselect() {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.filledSquare(selectX + .5, selectY +.5, .5);
		selectedPiece = null;
		drawAll();

	}

	public void place(Piece p, int x, int y) {
		if(x>7 || y>7 || x<0 || y<0) {
			return;
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if(x>7 || y >7 || x<0 || y<0) {
			System.out.println("INVALID REMOVE");
			return null;
		}
		Piece removedPiece = pieceAt(x,y);
		if (removedPiece == null) {
			System.out.println("PIECE WAS NULL");
			return null;
		}
		else{
			place(null,x,y);
			return removedPiece;
		}
	}
	

	public boolean canEndTurn() {
		return canEnd;
	}

	public void endTurn() {
		canEnd = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		fireTurn = !fireTurn;
		canMove = true;
		mustJump = false;
		canSelectOther = true;
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for(int x=0; x <8; x+=1) {
			for(int y=0; y<8; y +=1) {
				Piece eachPos = pieceAt(x,y);
				if(!(eachPos == null)) {
					if(eachPos.isFire()){
						firePieces +=1;
					}
					else{
						waterPieces +=1;
					}
				}
			}
		}

		if((waterPieces + firePieces) == 0) {
			return "No One";
		}
		else if (waterPieces == 0) {
			return "Fire";
		}
		else if (firePieces == 0){
			return "Water";
		}
		else {return null;}
	}





	/* 	Starts the game and the GUI, doesn't return anything
	**	until the game has ended and a play has won or quit
	*/
	public static void main(String[] args) {
		StdDrawPlus.setScale(0, 8);
		Board b = new Board(false);

		while(b.winner() == null) {
			int xPress;
			int yPress;
			if(StdDrawPlus.mousePressed()) {
				
				xPress = (int) StdDrawPlus.mouseX();
				yPress = (int) StdDrawPlus.mouseY();
				if(b.canSelect(xPress, yPress)) {
					b.select(xPress,yPress);
				}
			}
			
			if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}

			StdDrawPlus.show(25);
		}
		System.out.println(b.winner());
	}



}