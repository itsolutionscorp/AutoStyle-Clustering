public class Board {
	
	private boolean shouldBeEmpty; 

	public Board(boolean rlyEmpty){
		shouldBeEmpty = rlyEmpty; 
		this.initializePieces(8); 
		this.initializePlayer(); 
	}

	/* draws an N x N board. */ 
	private void drawBoard(int N){
		for (int j = 0; j < N; j++){
			for (int i = 0; i < N; i++){
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY); 
				else				  StdDrawPlus.setPenColor(StdDrawPlus.RED); 
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);	
			}
		}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if ((selected != null) && ((selectedX + selectedY) % 2 == 0)) {
            StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
        }
	}


	private Piece[][] pieces; 

	private void initializePieces(int N){
		pieces = new Piece[N][N]; 
		if (this.shouldBeEmpty == false){
			for (int x = 0; x < N; x++){
				for (int y = 0; y < N; y++){
					if ((x + y) % 2 == 0){
						if (y == 0){
							pieces[x][y] = new Piece(true, this, x, y, "pawn"); 
						} else if (y == 1){
							pieces[x][y] = new Piece(true, this, x, y, "shield"); 
						} else if (y == 2){
							pieces[x][y] = new Piece(true, this, x, y, "bomb"); 
						} else if (y == 5){
							pieces[x][y] = new Piece(false, this, x, y, "bomb"); 
						} else if (y == 6){
							pieces[x][y] = new Piece(false, this, x, y, "shield"); 
						} else if (y == 7){
							pieces[x][y] = new Piece(false, this, x, y, "pawn"); 
						} else {
							pieces[x][y] = null; 
						}
					}
				}
			}
		} else {
			for (int x = 0; x < N; x++){
				for (int y = 0; y < N; y++){
					pieces[x][y] = null; 
				}
			}
		}
	}

	private void drawPieces(int N){
		for (int x = 0; x < N; x++){
			for (int y = 0; y < N; y++){
				if ((x + y) % 2 == 0){
					Piece tempPiece = pieces[x][y];  
					if (tempPiece != null){
						if ((tempPiece.isFire() == true) && (tempPiece.isBomb() == false) && (tempPiece.isShield() == false) && (tempPiece.isKing() == false)) {
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1); 
						} else if ((tempPiece.isFire() == true) && (tempPiece.isShield() == true) && (tempPiece.isKing() == false)){
							StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
						} else if ((tempPiece.isFire() == true) && (tempPiece.isBomb() == true) && (tempPiece.isKing() == false)){
							StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1); 
						} else if ((tempPiece.isFire() == false) && (tempPiece.isBomb() == false) && (tempPiece.isShield() == false) && (tempPiece.isKing() == false)) {
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1); 
						} else if ((tempPiece.isFire() == false) && (tempPiece.isShield() == true) && (tempPiece.isKing() == false)){
							StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);  
						} else if ((tempPiece.isFire() == false) && (tempPiece.isBomb() == true) && (tempPiece.isKing() == false)){
							StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1); 
						} else if ((tempPiece.isFire() == true) && (tempPiece.isBomb() == false) && (tempPiece.isShield() == false) && (tempPiece.isKing() == true)) {
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1); 
						} else if ((tempPiece.isFire() == true) && (tempPiece.isShield() == true) && (tempPiece.isKing() == true)) {
							StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
						} else if ((tempPiece.isFire() == true) && (tempPiece.isBomb() == true) && (tempPiece.isKing() == true)) {
							StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1); 
						} else if ((tempPiece.isFire() == false) && (tempPiece.isBomb() == false) && (tempPiece.isShield() == false) && (tempPiece.isKing() == true)) {
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1); 
						} else if ((tempPiece.isFire() == false) && (tempPiece.isShield() == true) && (tempPiece.isKing() == true)){
							StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);  
						} else if ((tempPiece.isFire() == false) && (tempPiece.isBomb() == true) && (tempPiece.isKing() == true)){
							StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1); 
						} else {
							continue; 
						}
					}	
				}
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if ((x < 8) && (y < 8) && (x > -1) && (y > -1)){
			if (pieces[x][y] != null){
				return pieces[x][y]; 
			} else{
				return null; 
			}
		} else {
			return null; 
		}
	}

	public void place(Piece p, int x, int y){
		if ((x < 8) && (y < 8) && (x > -1) && (y > -1)){
			if (p != null){
				pieces[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y){
		if ((x < 8) && (y < 8) && (x > -1) && (y > -1)){
			if (pieces[x][y] != null){

				Piece removedPiece = pieceAt(x,y); 
				pieces[x][y] = null; 
				//System.out.println("reached removedPiece"); 
				return removedPiece; 

			} else{	
				//System.out.println(x + "," + y); 
				System.out.println("There is no piece at location selected."); 
				return null; 
			}
		} else {
			System.out.println("Location selected is out of bounds."); 
			return null; 
		}

	}

	private boolean fireOne(int xi, int yi, int xf, int yf){
		if (((yf-yi) == 1) && (((xf-xi) == 1) || ((xf-xi) == -1))) {
			return true; 
		} else {
			return false; 
		}
	}

	private boolean waterOne(int xi, int yi, int xf, int yf){
		if (((yf-yi) == -1) && (((xf-xi) == 1) || ((xf-xi) == -1))) {
			return true; 
		} else {
			return false; 
		}
	}

	private boolean fireTwo(int xi, int yi, int xf, int yf){
		Piece start = this.pieceAt(xi, yi); 
		int capturedX = ((xi+xf)/2); 
		int capturedY = ((yi+yf)/2); 
		Piece captured = this.pieceAt(capturedX, capturedY); 
		if (captured != null){
			if (((yf-yi) == 2) && (((xf-xi) == 2) || ((xf-xi) == -2)) && (captured.isFire() != start.isFire())) {
				return true; 
			} else {
				return false; 
			}
		} else {
			return false; 
		}
	}

	private boolean waterTwo(int xi, int yi, int xf, int yf){
		Piece start = this.pieceAt(xi, yi); 
		int capturedX = ((xi+xf)/2); 
		int capturedY = ((yi+yf)/2); 
		Piece captured = this.pieceAt(capturedX, capturedY); 
		if (captured != null){
			if (((yf-yi) == -2) && (((xf-xi) == 2) || ((xf-xi) == -2)) && (captured.isFire() != start.isFire())) {
				return true; 
			} else {
				return false; 
			}
		} else {
			return false; 
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){

		Piece start = this.pieceAt(xi, yi); 
		//System.out.println(start); 
		Piece end = this.pieceAt(xf, yf); 
		if ((start != null) & (end == null)){
			if (start.hasCaptured() == false){
				if (start.isKing() == true){
					if (this.fireOne(xi, yi, xf, yf) || this.waterOne(xi, yi, xf, yf) || this.fireTwo(xi, yi, xf, yf) || this.waterTwo(xi, yi, xf, yf)){
						return true; 
					} else {
						return false; 
					}
				} else {
					if (start.isFire() == true){
						if (this.fireOne(xi, yi, xf, yf) || this.fireTwo(xi, yi, xf, yf)) {
							return true; 
						} else {
							return false;  
						}
					} else {
						if (this.waterOne(xi, yi, xf, yf) || this.waterTwo(xi, yi, xf, yf)) {
							return true; 
						} else {
							return false;  
						}
					}
				}
			} else {
				if (start.isKing() == true){
					if (this.fireTwo(xi, yi, xf, yf) || this.waterTwo(xi, yi, xf, yf)){
						return true; 
					} else {
						return false; 
					}
				} else {
					if (start.isFire() == true){
						if (this.fireTwo(xi, yi, xf, yf)) {
							return true; 
						} else {
							return false;  
						}
					} else {
						if (this.waterTwo(xi, yi, xf, yf)) {
							return true; 
						} else {
							return false;  
						}
					}
				}
			}	
		} else {
			return false; 
		}
	}

	/* currPlayer is true if fire and false if water 
	Fire always goes first so start as true*/ 
	private boolean currPlayer; 
	
	private void initializePlayer(){
		currPlayer = true; 
	}

	/* this boolean returns true if the move function has been called. 
	   this gets updated in the select method. 
	   The select method calls on move(). If move is called, hasMoved return true. False otherwise. */ 
	private boolean hasMoved; 

	/* this is the piece selected */ 
	private Piece selected; 
	private int selectedX; 
	private int selectedY; 

	private boolean playerPieceMatch(int x, int y){

		Piece piece = this.pieceAt(x, y); 

		if (piece != null) {
			// System.out.println("isFire: " + piece.isFire()); 
			if (currPlayer == piece.isFire()){
				return true; 
			} else {
				return false; 
			}
		} else {
			return false; 
		}
	}

	public boolean canSelect(int x, int y){
		if (playerPieceMatch(x, y) == true) {
			//System.out.println(this.hasMoved); 
			// original: if (selected == null){
			if ((selected == null) && (this.hasMoved == false)) {
				return true; 
			// original: } else if ((selected != null) && (hasMoved == false)) {
			} else if ((hasMoved == false)) {
				return true; 
			} else {
				return false; 
			}
		} else if (this.pieceAt(x,y) == null) {
			if ((selected != null) && (hasMoved == false) && (this.validMove(selectedX, selectedY, x, y) == true)) {
				return true; 
			} else if ((selected != null) && (selected.hasCaptured() == true) && (this.validMove(selectedX, selectedY, x, y) == true)) {
				return true; 
			} else {
				return false; 
			}
		} else {
			return false; 
		}
	}

	/* right now, if I press on a null square first, (0,0) piece get removed. 
	   i think that, after getting canSelect to work, canSelect will return false 
	   for those cases and I will be ok */ 
	public void select(int x, int y){
		if (this.pieceAt(x, y) != null) {
			selected = this.pieceAt(x,y); 
			selectedX = x; 
			selectedY = y; 
		} else {
			//this.remove(selectedX, selectedY); 
			//this.place(selected, x, y); 
			selected.move(x, y); 
			hasMoved = true; 
			selected = this.pieceAt(x, y); 
			selectedX = x; 
			selectedY = y; 
		}
	}

 	// made some possibly gruesome, untested changes here. here is the original 
 	/* public boolean canEndTurn(){
		System.out.println(selected); 
		if (selected != null){
			if ((hasMoved == true) | (selected.hasCaptured() == true)){
				return true; 
			} else {
				return false; 
			}
		} else {
			return false; 
		}
	} */ 
	public boolean canEndTurn(){
		if (hasMoved == true){
			return true; 
		} else {
			return false; 
		}
	}

	public void endTurn(){
		if (currPlayer == true){
			currPlayer = false; 
		} else{
			currPlayer = true; 
		}

		if (selected != null) {
			selected.doneCapturing(); 
		}
		selected = null; 
		hasMoved = false; 

	}

	public String winner(){
		int fireCount = 0; 
		int waterCount = 0; 
		int nullCount = 0; 
		for (int x = 0; x < 8; x++){
			for (int y = 0; y < 8; y++){
				if (pieces[x][y] != null){
					if (pieces[x][y].isFire() == true){
						fireCount += 1; 
					} else if (pieces[x][y].isFire() == false){
						waterCount += 1; 
					}
				} else{
					nullCount += 1; 
				} 
			}
		}
		if ((fireCount != 0) && (waterCount == 0)) {
			return "Fire"; 
		} else if ((fireCount == 0) && (waterCount != 0)){
			return "Water"; 
		} else if (nullCount == 64) {
			return "No one"; 
		} else {
			return null; 
		}
	}

	private boolean graySquare(int x, int y){
		if (((x < 8) && (y < 8) && (x > -1) && (y > -1)) && ((x + y) % 2 == 0)) {
			return true; 
		} else {
			return false; 
		}
	}

	public static void main(String[] args){
		int N = 8; 
		StdDrawPlus.setXscale(0, N); 
		StdDrawPlus.setYscale(0, N); 
		Board b1 = new Board(false); 
		//Piece test1 = new Piece(true, b1, 2, 2, "pawn");  
		//Piece test2 = new Piece(false, b1, 3, 3, "pawn"); 

		while(true){
			b1.drawBoard(N); 
			b1.drawPieces(N); 
			
			//b1.place(test1, 2, 2); 
			//b1.place(test2, 3, 3); 

			//b1.select(2, 2); 
			
			if (StdDrawPlus.mousePressed()) {

                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                //System.out.println((int) x + "," + (int) y);
				//b1.select((int) x, (int) y); 

                //b1.pieceAt(2,2).move(3, 3); 
                if (b1.graySquare((int) x, (int) y) == true) {
	                if (b1.canSelect((int) x, (int) y) == true){
	                	b1.select((int) x, (int) y); 
	                	//System.out.println(b1.selectedX + "," + b1.selectedY); 
	            	}	

	            } else {
	            	continue; 
	            }
            }       

            if (StdDrawPlus.isSpacePressed()){
            	//System.out.println("canEndTurn: " + b1.canEndTurn()); 
            	if (b1.canEndTurn() == true){
		        	b1.endTurn(); 
		        	//System.out.println("currPlayer: " + b1.currPlayer); 
		        } else {
		        	continue; 
		        }
            }   
			StdDrawPlus.show(100);
		} 
	}

}