public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean isEmpty = false;
	private int waterPieces = 12;
	private int firePieces = 12;
	private boolean fireTurn = true;
	private boolean moved = false;
	private Piece pieceSelected = null;
	private boolean captureAgain = false;
	private int piecex, piecey;

	/*starts a GUI supported version of the game */
	public static void main(String[] args){
		Board b = new Board(false);
		b.drawInitialBoard(b.isEmpty);
		while(true) {
			b.countNumbers();
			if(b.waterPieces == 0 || b.firePieces == 0){
				break;
			}
			int x,y;
			if(StdDrawPlus.mousePressed()){
				x = (int) StdDrawPlus.mouseX();
				y = (int) StdDrawPlus.mouseY();
				if(b.canSelect(x,y)){
					b.select(x,y);
					b.drawWhitePiece(b.pieceAt(x,y),x,y);
				}
			}
			if(StdDrawPlus.isSpacePressed()){
				b.endTurn();
			}
			StdDrawPlus.show(20);
			b.drawBoard();
		}
		b.drawBoard();
		System.out.println(b.winner());
	}
 	
	private void countNumbers(){
		this.waterPieces = 0;
		this.firePieces = 0;
		for(int i=0; i<8; i++){
			for(int j=0; j< 8; j++){
				if(this.pieces[i][j] != null && this.pieces[i][j].isFire()){
					this.firePieces += 1;
				} else if(this.pieces[i][j] != null){
					this.waterPieces += 1;
				}
			}
		}		
	}

 	/* initiatilizes a board, empty board 
 	if argument is true, and default board 
 	if argument if false */
	public Board(boolean shouldBeEmpty) {
		if(shouldBeEmpty) {
			this.isEmpty = true;
		} else {
			//fire pawns
			for(int i=0; i<8; i+=2) {
				Piece pawn = new Piece(true, this, i, 0, "pawn");
				pieces[i][0] = pawn;
			}
			//fire shields
			for(int i=1; i<8; i+=2) {
				Piece shield = new Piece(true, this, i, 1, "shield");
				pieces[i][1] = shield;
			}
			// fire bombs
			for(int i=0; i<8; i += 2) {
				Piece bomb = new Piece(true, this, i, 2, "bomb");
				pieces[i][2] = bomb;
			}
			// water pawns
			for(int i=1; i<8; i+=2) {
				Piece pawn = new Piece(false, this, i, 7, "pawn");
				pieces[i][7] = pawn;
			}
			// water shields
			for(int i=0; i<8; i+=2) {
				Piece shield = new Piece(false, this, i, 6, "shield");
				pieces[i][6] = shield;
			}
			//water bombs
			for(int i=1; i<8; i += 2) {
				Piece bomb = new Piece(false, this, i, 5, "bomb");
				pieces[i][5] = bomb;
			}
		}


	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        this.drawWhitePiece(this.pieceSelected, this.piecex, this.piecey);
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(this.pieceSelected != null && i == this.piecex && j == this.piecey){

				} else{
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					} else{
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}                  
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            }
			}
		}
		for(int x=0; x<8; x++){
			for(int y=0; y<8; y++){
				if(this.pieceSelected != null && x == this.piecex && y == this.piecey){

				} else {
					Piece p = this.pieceAt(x,y);
					if(p != null) {
						if(p.isFire()){
							if(p.isShield()){
								StdDrawPlus.picture(x + .5, y +.5, "img/shield-fire.png", 1, 1);
							} else if(p.isBomb()){
								StdDrawPlus.picture(x + .5, y +.5, "img/bomb-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y +.5, "img/pawn-fire.png", 1, 1);
							}
						} else {
							if(p.isShield()){
								StdDrawPlus.picture(x + .5, y +.5, "img/shield-water.png", 1, 1);
							} else if(p.isBomb()){
								StdDrawPlus.picture(x + .5, y +.5, "img/bomb-water.png", 1, 1);
							} else {
								StdDrawPlus.picture(x + .5, y +.5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}				
			}
		}		
	}

	/* draws initial board */
	private void drawInitialBoard(boolean isEmpty) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}                  
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
		if(!isEmpty){
			//fire pawns
			for(int i=0; i<8; i+=2) {
				Piece pawn = new Piece(true, this, i, 0, "pawn");
				StdDrawPlus.picture(i + .5, .5, "img/pawn-fire.png", 1,1);
			}
			//fire shields
			for(int i=1; i<8; i+=2) {
				Piece shield = new Piece(true, this, i, 1, "shield");
				StdDrawPlus.picture(i +.5, 1.5, "img/shield-fire.png", 1,1);
			}
			// fire bombs
			for(int i=0; i<8; i += 2) {
				Piece bomb = new Piece(true, this, i, 2, "bomb");
				StdDrawPlus.picture(i +.5, 2.5, "img/bomb-fire.png",1,1);
			}
			// water pawns
			for(int i=1; i<8; i+=2) {
				Piece pawn = new Piece(false, this, i, 7, "pawn");
				StdDrawPlus.picture(i + .5, 7.5, "img/pawn-water.png", 1,1);
			}
			// water shields
			for(int i=0; i<8; i+=2) {
				Piece shield = new Piece(false, this, i, 6, "shield");
				StdDrawPlus.picture(i +.5, 6.5, "img/shield-water.png", 1,1);
			}
			//water bombs
			for(int i=1; i<8; i += 2) {
				Piece bomb = new Piece(false, this, i, 5, "bomb");
				StdDrawPlus.picture(i +.5, 5.5, "img/bomb-water.png",1,1);
			}
		}	
	}

	/* gets the piece at (x,y), if x,y is out
	of bounds, returns null, returns null if
	there is no piece */
	public Piece pieceAt(int x, int y) {
		if(x > 7 || x < 0 || y < 0 || y > 7) {
			return null;
		}
		return this.pieces[x][y];

	}

	private boolean canKingSelect(int x, int y){
		boolean fire = true;
		if(fireTurn == false){
			fire = false;
		}
		if(this.pieceAt(x,y) != null){
			return false;
		} else {
			if(!this.moved && this.pieceSelected != null) {
				return validKingMove(x, y);
			} else if(captureAgain && pieceSelected != null && pieceSelected.hasCaptured()){
				return validKingMove(x, y);
			}
		}
		return false;
	}

	private boolean validKingMove(int x, int y){
		boolean fire = this.pieceSelected.isFire();
		if((x == piecex + 1 || x == piecex - 1) && (y == piecey + 1 || y == piecey - 1)){
			return true;
		} else if((x == this.piecex + 2) && (y == this.piecey + 2)){
			Piece killed = this.pieces[this.piecex + 1][this.piecey + 1];
			if(killed == null){
				return false;
			} else if(killed.isFire() != fire){
				return true;
			} else {
				return false;
			}
		} else if((x == this.piecex - 2) && (y == this.piecey + 2)){
			Piece killed = this.pieces[this.piecex - 1][this.piecey + 1];
			if(killed == null){
				return false;
			} else if(killed.isFire() != fire){
				return true;
			} else {
				return false;
			}
		} else if((x == this.piecex + 2) && (y == this.piecey - 2)){
			Piece killed = this.pieces[this.piecex + 1][this.piecey - 1];
			if(killed == null){
				return false;
			} else if(killed.isFire() != fire){
				return true;
			} else {
				return false;
			}
		} else if((x == this.piecex - 2) && (y == this.piecey - 2)){
			Piece killed = this.pieces[this.piecex - 1][this.piecey - 1];
			if(killed == null){
				return false;
			} else if(killed.isFire() != fire){
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/* returns true is the square at x,y can
	be selected */
	public boolean canSelect(int x, int y) {
		boolean fire = true;
		if(fireTurn == false){
			fire = false;
		}
		if(x>7 || x<0 || y>7 || y<0){
			return false;
		}
		if(this.pieceSelected != null && this.pieceSelected.isKing()){
			return this.canKingSelect(x,y);
		}
		Piece p = this.pieceAt(x,y);
		if(p == null) {
			if(!this.moved && this.pieceSelected != null) {
				return validMove(x, y);
			} else if(captureAgain && pieceSelected != null && pieceSelected.hasCaptured()){
				return validMove(x, y);
			}

		} else {
			if(pieceSelected == null && p.isFire() == fire && !this.moved) {
				return true;
			} else if(p.isFire() == fire && !this.moved){
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int x, int y) {
		if(this.pieceSelected.isFire()){
			if((x == this.piecex + 1 || x == this.piecex -1) && (y == this.piecey + 1)){
				return true;
			} else if((x == this.piecex + 2) && (y == this.piecey + 2)){
				Piece killed = this.pieces[this.piecex + 1][this.piecey + 1];
				if(killed == null){
					return false;
				} else if(killed.isFire() == false){
					return true;
				} else {
					return false;
				}
			} else if((x == this.piecex - 2) && (y == this.piecey + 2)){
				Piece killed = this.pieces[this.piecex - 1][this.piecey + 1];
				if(killed == null){
					return false;
				} else if(killed.isFire() == false){
					return true;
				} else {
					return false;
				}
			}
		} else if(!this.pieceSelected.isFire()){
			if((x == this.piecex + 1 || x == this.piecex -1) && (y == this.piecey - 1)){
				return true;
			} else if((x == this.piecex + 2) && (y == this.piecey - 2)){
				Piece killed = this.pieces[this.piecex + 1][this.piecey - 1];
				if(killed == null){
					return false;
				} else if(killed.isFire() == true){
					return true;
				} else {
					return false;
				}
			} else if((x == this.piecex - 2) && (y == this.piecey - 2)){
				Piece killed = this.pieces[this.piecex - 1][this.piecey - 1];
				if(killed == null){
					return false;
				} else if(killed.isFire() == true){
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	/* selects the square at x,y */
	public void select(int x, int y) {
		if(this.pieceSelected == null){
			this.pieceSelected = this.pieces[x][y];
			this.piecex = x; 
			this.piecey = y;
		} else if(this.pieceAt(x,y) == null) {
			this.pieceSelected.move(x,y);
			this.moved = true;
			if(!this.pieceSelected.isBomb()){
				this.piecex = x;
				this.piecey = y;
				this.canCaptureAgain(x,y);
			} else if(this.pieceSelected.hasCaptured() == true && this.pieceSelected.isBomb() == true) {
				this.pieceSelected = null;
				this.moved = true;
				this.captureAgain = false;
			} else if(this.pieceSelected.isBomb()){
				this.piecex = x;
				this.piecey = y;
			}
		} else {			
			this.pieceSelected = this.pieces[x][y];
			this.piecex = x; 
			this.piecey = y;
		}
	}

	private void drawPiece(Piece p, int x, int y){
		if((x + y) % 2 == 0){
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			} else {
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
			}
		StdDrawPlus.filledSquare(x+.5,y+.5,.5);
		if(p != null) {
			if(p.isFire()){
				if(p.isShield()){
					StdDrawPlus.picture(x + .5, y +.5, "img/shield-fire.png", 1, 1);
				} else if(p.isBomb()){
					StdDrawPlus.picture(x + .5, y +.5, "img/bomb-fire.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y +.5, "img/pawn-fire.png", 1, 1);
				}
			} else {
				if(p.isShield()){
					StdDrawPlus.picture(x + .5, y +.5, "img/shield-water.png", 1, 1);
				} else if(p.isBomb()){
					StdDrawPlus.picture(x + .5, y +.5, "img/bomb-water.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y +.5, "img/pawn-water.png", 1, 1);
				}
			}
		}				
	}

	private void drawWhitePiece(Piece p, int x, int y){
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x+.5,y+.5,.5);
		if(p != null) {
			if(p.isFire()){
				if(p.isShield()){
					StdDrawPlus.picture(x + .5, y +.5, "img/shield-fire.png", 1, 1);
				} else if(p.isBomb()){
					StdDrawPlus.picture(x + .5, y +.5, "img/bomb-fire.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y +.5, "img/pawn-fire.png", 1, 1);
				}
			} else {
				if(p.isShield()){
					StdDrawPlus.picture(x + .5, y +.5, "img/shield-water.png", 1, 1);
				} else if(p.isBomb()){
					StdDrawPlus.picture(x + .5, y +.5, "img/bomb-water.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y +.5, "img/pawn-water.png", 1, 1);
				}
			}
		} 		
	}

	private void canCaptureAgain(int x,int y){
		if(this.pieceSelected.isKing()){
			this.canKingCaptureAgain(x,y);
			return;
		}
		boolean changed = false;
		if(this.pieceSelected.isFire()){
			if(y+2>7){
				this.captureAgain = false;
				changed = true;
				return;
			}
			if((this.pieceAt(x+2, y+2) != null || x+2 > 7) && (this.pieceAt(x-2, y+2) != null || x-2 <0)){
				this.captureAgain = false;
				changed = true;
				return;
			}
			Piece kill = this.pieceAt(x+1, y+1);
			Piece kill2 = this.pieceAt(x-1, y+1);
			if(kill != null && kill.isFire() != true){
				if(this.pieceAt(x+2,y+2) == null && x+2<8 && y+2<8){
					this.captureAgain = true;
					changed = true;
				}
			}
			else if(kill2 != null && kill2.isFire() != true){
				if(this.pieceAt(x-2,y+2) == null && x-2>-1 && y+2<8){
					this.captureAgain = true;
					changed = true;
				}
			} else {
				this.captureAgain = false;
			}
		} else if(!this.pieceSelected.isFire()){
			if(y-2 < 0){
				this.captureAgain = false;
				changed = true;
				return;
			}
			if((this.pieceAt(x+2, y-2) != null || x+2 > 7) && (this.pieceAt(x-2, y-2) != null || x-2 <0)){
				this.captureAgain = false;
				changed = true;
				return;
			}
			Piece kill = this.pieceAt(x+1, y-1);
			Piece kill2 = this.pieceAt(x-1, y-1);
			if(kill != null && kill.isFire() != false){
				if(this.pieceAt(x+2,y-2) == null && x+2<8 && y-2>-1){
						this.captureAgain = true;
						changed = true;
					}
				}
			else if(kill2 != null && kill2.isFire() != false){
				if(this.pieceAt(x-2,y-2) == null && x-2>-1 && y-2>-1){
					this.captureAgain = true;
					changed = true;
				}
			} else{
				this.captureAgain = false;
			}
		}
		// if(changed ==  false){
		// 	this.captureAgain = false;
		// }
	}

	private void canKingCaptureAgain(int x, int y){
		boolean fire = this.pieceSelected.isFire();
		boolean changed = false;
		if((this.pieceAt(x+2, y+2) != null || x+2 > 7) && (this.pieceAt(x-2, y+2) != null || x-2 <0) 
				&& (this.pieceAt(x+2, y-2) != null || x+2 > 7) && (this.pieceAt(x-2, y-2) != null || x-2 <0)){
			this.captureAgain = false;
			changed = true;
			return;
		}
		Piece kill = this.pieceAt(x+1, y+1);
		Piece kill2 = this.pieceAt(x-1, y+1);
		Piece kill3 = this.pieceAt(x+1, y-1);
		Piece kill4 = this.pieceAt(x-1, y-1);
		if(kill != null && kill.isFire() != fire){
			if(this.pieceAt(x+2,y+2) == null && x+2<8 && y+2<8){
				this.captureAgain = true;
				changed = true;
			}
		} 
		else if(kill2 != null && kill2.isFire() != fire){
			if(this.pieceAt(x-2,y+2) == null && x-2>-1 && y+2<8){
				this.captureAgain = true;
				changed = true;
			}
		}
		else if(kill3 != null && kill3.isFire() != fire){
			if(this.pieceAt(x+2,y-2) == null && x+2<8 && y-2>-1){
				this.captureAgain = true;
				changed = true;
			}
		}
		else if(kill4 != null && kill4.isFire() != fire){
			if(this.pieceAt(x-2,y-2) == null && x-2>-1 && y-2>-1){
				this.captureAgain = true;
				changed = true;
			}
		} else{
			this.captureAgain = false;
		}
		// if(changed == false){
		// 	this.captureAgain = false;
		// } 
	}

	/* places piece p at x,y does nothing
	if x,y or p is null */
	public void place(Piece p, int x, int y) {
		if(p == null) {

		} else if(x > 7 || x < 0 || y < 0 || y > 7) {
			
		} else {
			this.pieces[x][y] = p;
		}
	}

	/* removes a piece from x,y and returns the 
	removed piece. returns null if no piece there
	or if x,y out of bounds */
	public Piece remove(int x, int y) {
		Piece removed = this.pieceAt(x, y);
		if(removed == null) {
			return removed;
		} else {
			this.pieces[x][y] = null;
            return removed;
		}
	}

	/* returns whether a player can end his/her turn
	based on whether he/she has made a move */
	public boolean canEndTurn() {
		// if(this.moved == true && this.captureAgain == false){
		if(this.moved == true){
			return true;
		} else if(this.moved == true && this.pieceSelected.hasCaptured() == false){
			return true;
		}
		return false;
	}

	/* called at the end of each turn. switches players
	etc */
	public void endTurn() {
		if(this.canEndTurn()){
			if(this.fireTurn == true){
				this.fireTurn = false;
			} else {
				this.fireTurn = true;
			}
			if(this.pieceSelected != null){	
				this.pieceSelected.doneCapturing();
			}
			this.pieceSelected = null;
			this.piecex = 0;
			this.piecey = 0;
			this.moved = false;
			this.captureAgain = false;
		} 
	}	

	/* returns the winner of the game 
	fire, water, no one, or null is game is not over*/
	public String winner() {
		this.countNumbers();
		String winner = null;
		if(this.waterPieces == 0 && this.firePieces > 0){
			winner = "Fire";
		} else if(this.firePieces == 0 && this.waterPieces > 0){
			winner = "Water";
		} else if(this.firePieces == 0 && this.waterPieces == 0) {
			winner = "No one";
		}
		return winner;
	}
}