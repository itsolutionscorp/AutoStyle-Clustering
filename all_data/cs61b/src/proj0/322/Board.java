public class Board{
	private Piece[][] pieces = new Piece[8][8];
	private int curPlayer = 0;
	private boolean[][] selectedAndMoved = {{false, false}, {false, false}}; //whether a player ([0] and [1] respectively) has selected a piece [0] or moved a piece [1]
	private int[] selectedPiece = {-1, -1}; //coordinates of the selected piece
	private boolean bombCapture = false;
	private boolean gameStart = false;
	private int numFire = 0;
	private int numWater = 0;

	public Board(boolean shouldBeEmpty){
		if (!shouldBeEmpty){
			fillBoard();
		}
	}

	private void drawBoard(){
		String[] fireImages = {"img/pawn-fire.png", "img/shield-fire.png", "img/bomb-fire.png"};
		String[] fireCrownImages = {"img/pawn-fire-crowned.png", "img/shield-fire-crowned.png", "img/bomb-fire-crowned.png"};

		String[] waterImages ={"img/bomb-water.png", "img/shield-water.png", "img/pawn-water.png"};
		String[] waterCrownImages = {"img/bomb-water-crowned.png", "img/shield-water-crowned.png", "img/pawn-water-crowned.png"};

		for (int x = 0; x < 8; x ++){
			for (int y = 0; y < 8; y ++){
				int remainder = (x + y) % 2;

				if( remainder == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);

				Piece p = pieces[x][y];
				String img = ""; //forces img to be inilizaed--it will automatically be assigned to something else though

				if (p != null && p.isFire()){
					if(p.isBomb()){
						if (p.isKing()){
							img = fireCrownImages[2];
						} else{
							img = fireImages[2];
						}
					}
					else if(p.isShield()){
						if (p.isKing()){
							img = fireCrownImages[1];
						} else{
							img = fireImages[1];
						}
					} 
					else{
						if (p.isKing()){
							img = fireCrownImages[0];
						} else{
							img = fireImages[0];
						}
					}
				}
				else if(p != null && !p.isFire()){
					if(p.isBomb()){
						if (p.isKing()){
							img = waterCrownImages[0];
						} else{
							img = waterImages[0];
						}
					}
					else if(p.isShield()){
						if (p.isKing()){
							img = waterCrownImages[1];
						} else{
							img = waterImages[1];
						}
					} else{
						if (p.isKing()){
							img = waterCrownImages[2];
						} else{
							img = waterImages[2];
						}
					}					
				}

				StdDrawPlus.picture( x + 0.5, y + 0.5, img, 1, 1);
			}
		}
	}

	private void fillBoard(){
		gameStart = true;

		String[] firePiece = {"pawn", "shield", "bomb"};
		String[] waterPiece = {"bomb", "shield", "pawn"};

		for (int x = 0; x < 8; x ++){
			for (int y = 0; y < 8; y ++){
				int remainder = (x + y) % 2;

				if ((y <= 2) && (remainder == 0)){
				 	pieces[x][y] = new Piece(true, this, x, y, firePiece[y % 5]);
				 	numFire ++;
				}

				if ( (y >= 5) && (remainder == 0)){
				 	pieces[x][y] = new Piece(false, this, x, y, waterPiece[y % 5]);
				 	numWater ++;
				}
			}
		}				
	}

	public Piece pieceAt(int x, int y){
		if (x >= pieces.length || y >= pieces[1].length){
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y){
		gameStart = true;

		if (x >= pieces.length || y >= pieces[0].length || p == null){ //ends if there is no piece or the placement is out of bounds
			return;
		} 

		for (int xCord = 0; xCord < pieces.length; xCord ++){
			for (int yCord = 0; yCord < pieces[0].length; yCord ++){

				if (pieces[xCord][yCord] == p){
					remove(xCord, yCord); //takes piece out of old spot and places in new spot

					selectedAndMoved[curPlayer][1] = true;

					selectedPiece[0] = x; //updates position of selected piece
					selectedPiece[1] = y;

				}
			}
		}
		pieces[x][y] = p;

		if(p.isFire()){ //adds one to numFire/numWater if new piece was placed/counteract remove
			numFire ++;
		} else{
			numWater ++;
		}
	}

	public Piece remove(int x, int y){
		Piece toReturn = pieceAt(x, y);
		if (toReturn.isFire()){
			numFire --;
		} else{
			numWater --;
		}

		pieces[x][y] = null;
		return toReturn;
	}

	public void select(int x, int y){
		if (pieceAt(x, y) != null){
			selectedAndMoved[curPlayer][0] = true; //select new piece
			selectedPiece[0] = x; //reassigns the selected piece
			selectedPiece[1] = y;
		}

		else{
			Piece selPiece = pieceAt(selectedPiece[0], selectedPiece[1]);
			if (selPiece.isBomb() && validCapture(selectedPiece[0], selectedPiece[1], x, y)){
				bombCapture = true;
			}
			selPiece.move(x, y);
		}
	}

	public boolean canSelect(int x, int y){
		if (x >= pieces.length || y >= pieces[0].length){ //returns false if x or y is out of bounds
			return false;
		}

		if(bombCapture){ //returns false if a bomb has already exploded
			return false;
		}

		Piece p = pieceAt(x, y);

		boolean selected = selectedAndMoved[curPlayer][0]; //true if a piece has already been selected
		boolean moved = selectedAndMoved[curPlayer][1]; //true if a piece has already been moved

		if ( (p != null) && (curPlayer != p.side())){ //returns false if a player tries to select opponent's piece		
			return false;
		}

		if ( (p != null) && (!selected || !moved)){ //Selecting a piece: returns true if a piece was not selected
			return true;							//or a piece was selected but not moved
		}

		if (p == null){ //Selecting a square
			if (!selected){ //returns false if a piece has not been selected yet
				return false;
			}

			int xi = selectedPiece[0]; //x coordinates of selected piece
			int yi = selectedPiece[1]; //y coordinates of selected piece

			boolean isValCap = validCapture(xi, yi, x, y);

			if (selected && !moved &&						//returns true if piece was selected but not moved
				( validMove(xi, yi, x, y) || isValCap ) ) { //and selected square is a valid move or capture
				return true;
			}

			if (selected &&	pieceAt(xi, yi).hasCaptured() && isValCap){
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){ //checks to see if a piece can move to new position

		if ( (xf > pieces.length) || (yf > pieces[0].length)){ //return false if final placement is out of bounds
			return false;
		}

		if (pieceAt(xf, yf) != null){ //return false if there is a piece in final position
			return false;
		}

		Piece p = pieceAt(xi, yi);
		if (p != null){//if a piece is selected
			if ( (p.isFire() || p.isKing()) && (yf == yi + 1) && ( (xf == xi + 1) || (xf == xi - 1) ) ){//return true if piece is fire/king and is moving up 1 & to the left/right 1 (normal move)
					return true;
			}
			if ( (!p.isFire() || p.isKing()) && (yf == yi - 1) && ( (xf == xi + 1) || (xf == xi - 1) ) ){//return true if piece is water/king and is moving down 1 & to the left/right 1 (normal move)
					return true;
			}
		}

		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf){ //checks to see if a piece can make a capture
		if ( (xf > pieces.length) || (yf > pieces[0].length)){ //return false if final placement is out of bounds
			return false;
		}

		if (pieceAt(xf, yf) != null){ //return false if there is a piece in final position
			return false;
		}
		
		Piece p = pieceAt(xi, yi);
		if (p != null){//if a piece is selected

			if ( (p.isFire() || p.isKing()) && (yf == yi + 2) && (xf == xi + 2)){ //return true if piece is fire/king and is moving up 2 & to the left/right 2 (capture)
				Piece toCap = pieceAt(xi + 1, yi + 1);
				if (toCap == null){ //returns false if there is no piece to jump over
					return false;
				}
				if (p.side() == toCap.side()){//returns false for jumping over same-side piece
					return false;
				}
				return true;
			}
			if ( (p.isFire() || p.isKing()) && (yf == yi + 2) && (xf == xi - 2)){
				Piece toCap = pieceAt(xi - 1, yi + 1);
				if (toCap == null){ //returns false if there is no piece to jump over
					return false;
				}
				if (p.side() == toCap.side()){///returns false for jumping over same-side piece
					return false;
				}
				return true;
			}


			if ( (!p.isFire() || p.isKing()) && (yf == yi - 2) && (xf == xi + 2)){//return true if piece is water/king and is moving down 2 & to the left/right 2 (capture)
				Piece toCap = pieceAt(xi + 1, yi - 1);
				if (toCap == null){ //returns false if there is no piece to jump over
					return false;
				}
				if (p.side() == toCap.side()){///returns false for jumping over same-side piece
					return false;
				}				
				return true;
			}
			if ( (!p.isFire() || p.isKing()) && (yf == yi - 2) && (xf == xi - 2)){
				Piece toCap = pieceAt(xi - 1, yi - 1);
				if (toCap == null){ //returns false if there is no piece to jump over
					return false;
				}
				if (p.side() == toCap.side()){///returns false for jumping over same-side piece
					return false;
				}				
				return true;
			}

		}

		return false;
	}

	public boolean canEndTurn(){
		if (selectedAndMoved[curPlayer][1]){ //returns true if a piece was moved or captured
			return true;
		}
		return false;
	}

	public void endTurn(){
		//reset selected and moved variables
		selectedAndMoved[curPlayer][0] = false;
		selectedAndMoved[curPlayer][1] = false;
		
		//resets selectedPiece
		if (!bombCapture){
			pieceAt(selectedPiece[0], selectedPiece[1]).doneCapturing();
		}
		selectedPiece[0] = -1;
		selectedPiece[1] = -1;

		//reset bombCapture
		bombCapture = false;

		//switch players
		if (curPlayer == 0){
			curPlayer ++;
		} else{
			curPlayer = 0;
		}
	}

	public String winner(){
		if (numFire == 0 && numWater == 0){
			return "No one";
		}
		if (numWater == 0 && gameStart){
			return "Fire";
		}
		if (numFire == 0 && gameStart){
			return "Water";
		}
		return null;
	}

	public static void main (String[] args){ 
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

		while(true){
	        b.drawBoard();
 
			if(StdDrawPlus.mousePressed()){
				//extract coordinates
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();

				if (b.canSelect((int) x, (int) y)) {

					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare((int) x + 0.5, (int) y + 0.5, 0.5);

					b.select((int) x, (int) y);
				}
			}
			StdDrawPlus.show(25);

			if(b.winner() != null){
				return;
			}

			if(StdDrawPlus.isSpacePressed()){
				if(b.canEndTurn()){
					b.endTurn();
				}
			}
		}
	}
}