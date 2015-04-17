public class Board {

	private Piece[][] pieces;
	private boolean empty;
	private int player;
	private boolean hasMoved;
	private boolean hasSelected;
	private Piece selectedPiece;
	private int tempX;
	private int tempY;

	//i cry evertim
	//pls y
	//sigh

	private void drawBoard(int N, boolean shouldBeEmpty, Board b) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		if (shouldBeEmpty == true){
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            }
	        }
	    }
	    else{
		    for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                if ((i+j)%2 == 0){
		                if (j == 0) {
		                	pieces[i][j] = new Piece(true, b, i, j, "pawn");
		                	b.drawPiece(pieces[i][j], i, j);
		                }
		                else if (j == 1) {
		                	pieces[i][j] = new Piece(true, b, i, j, "shield");
							b.drawPiece(pieces[i][j], i, j);
						}
		                else if (j == 2) {
		                	pieces[i][j] = new Piece(true, b, i, j, "bomb");	
		                	b.drawPiece(pieces[i][j], i, j);
		                }
		                else if (j == 5) {
		                	pieces[i][j] = new Piece(false, b, i, j, "bomb");	
		                	b.drawPiece(pieces[i][j], i, j);
		                }
		                else if (j == 6) {
		                	pieces[i][j] = new Piece(false, b, i, j, "shield");
		                	b.drawPiece(pieces[i][j], i, j);
		                }
		                else if (j == 7) {
		                	pieces[i][j] = new Piece(false, b, i, j, "pawn");
		                	b.drawPiece(pieces[i][j], i, j);
		                }
		        	}
		        }
	        }
	    }
    }

	public static void main (String args[]){
        Board b = new Board(false);

        while(true){
	        if (StdDrawPlus.mousePressed()) {
	            int x = (int) StdDrawPlus.mouseX();
	            int y = (int) StdDrawPlus.mouseY();
	            if (b.hasMoved == true){
	            	b.turnBackGray(x, y);
	            	b.drawPiece(b.pieces[x][y], x, y);
	            }
	            if (b.canSelect(x, y)){
	            	b.select(x, y);
	            }
	        }            
	        StdDrawPlus.show(100);
	    }

		// if (StdDrawPlus.isSpacePressed() == true){
		// 	if (b.canEndTurn() == true){
		// 		b.endTurn();
		// 	}
		// }
	}

	private void drawSquare(int x, int y){
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}

	private void fillGray(int x, int y){
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		drawSquare(x, y);
	}

	private void drawPiece(Piece p, int x, int y){
		String type = "";
		if (p == null){
			return;
		}
		else if (p.isShield()){
			type = "shield";
		}
		else if (p.isBomb()){
			type = "bomb";
		}
		else {
			type = "pawn";
		}
		if (p.isFire()){
			type = type + "-fire";
		}
		else {
			type = type + "-water";
		}
		if (type == null){return;}
		else {
			StdDrawPlus.picture(x+.5, y+.5, "img/" + type + ".png", 1, 1);
		}

	}

	private void drawWhite(int x, int y){
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	drawSquare(x, y);
   		drawPiece(pieces[x][y], x, y);
    }

    private void turnBackGray(int x, int y){
    	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    	drawSquare(x, y);
   		drawPiece(pieces[x][y], x, y);
    }

	public Board(boolean shouldBeEmpty){
		int N = 8;
		empty = shouldBeEmpty;
		pieces = new Piece[N][N];
        drawBoard(N, empty, this);
	}

	public Piece pieceAt(int x, int y){
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)){
			return null;
		}
		return pieces[x][y];
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if ((xf > 7) || (xf < 0) || (yf > 7) || (yf < 0)){
			return false;
		}
		else if ((((xi + 1 == xf) && (yi + 1 == yf)) || ((xi - 1 == xf) && (yi + 1 == yf))) && (pieceAt(xf, yf) == null)){
			return true;
		}
		else if (((xi + 2 == xf) && (yi + 2 == yf)) && (pieces[xf][yf] == null)){
			if (canCapture(xi + 1, yi + 1, selectedPiece)){
				return true;
			}
			else{
				return false;
			}
		}
		else if (((xi - 2 == xf) && (yi + 2 == yf)) && (pieces[xf][yf] == null)){
			if (canCapture(xi - 1, yi + 1, selectedPiece)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	// private void capture(int x, int y, Piece p){
	// 	if (canCapture(x, y, p)){
	// 		remove(x, y);
	// 	}
	// }

	private boolean canCapture(int x, int y, Piece p){
		if ((p.isFire()) && (pieces[x][y].isFire() == false)){
			return true;
		}
		else if ((p.isFire() == false) && (pieces[x][y].isFire())){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean canSelect(int x, int y){
		if  (pieces[x][y] == null){
			return false;
		}
		else if (hasSelected == false){
			if ((pieces[x][y].isFire() == true) && ((Math.abs(player) % 2) == 0)) {
				return true;
			}
			else if ((pieces[x][y].isFire() == false) && ((Math.abs(player) % 2 == 1))) {
				return true;
			}
			else if (pieces[x][y] == null){
				return false;
			}
			else{
				return false;
			}
		}
		else if (hasSelected == true) {
			if (hasMoved == false) {
				return true;
			}
			else if (hasMoved == true){
				if ((pieces[x][y] == null)){
					return true;
				}
			}
			else if ((pieces[x][y] == null) && (validMove(tempX, tempY, x, y))){
					return true;
			}
			else{
				return false;
			}
		}
		return true;
	}

	// private boolean isEmpty(int x, int y){
	// 	if ((x + y) % 2 == 0){
	// 		return false;
	// 	}
	// 	else if (pieces[x][y] == null){
	// 		return true;
	// 	}
	// 	else{
	// 		return false;
	// 	}
	// }

	public void select(int x, int y){
		// if (canSelect(x, y) == true){
	    // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    // StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	    if ((pieces[x][y] == null) && (hasSelected==true)){
	    	selectedPiece.move(x, y);
	    	fillGray(tempX, tempY);
	    	drawPiece(pieceAt(tempX, tempY), x, y);
	   		hasMoved = true;
	    	hasSelected = false;
	    }
	    else if ((hasSelected == true) && (hasMoved == false)){
	    	hasSelected = true;
			selectedPiece = pieces[x][y];
			turnBackGray(tempX, tempY);
        	tempX = x;
           	tempY = y;
           	drawWhite(tempX, tempY);
		}
		else{
			hasSelected = true;
			selectedPiece = pieces[x][y];
			tempX = x;
           	tempY = y;
           	drawWhite(tempX, tempY);

		}
	}

	public void place(Piece p, int x, int y){
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0) || (p == null)){
			return;
		}
		else{
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){ 
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)){
			System.out.println("Out of Bounds");
			return null;
		}
		else if (pieces[x][y] == null){
			System.out.println("No piece there");
			return null;
		}
		else{
			Piece p = pieces[x][y];
			fillGray(x, y);
			pieces[x][y] = null;
			return p;
		}
	}

	public boolean canEndTurn(){
		if (hasMoved == true){
			return true;
		}
		return false;
	}

	public void endTurn(){
		if (canEndTurn() == true){
			player = player + 1;
		}
		hasMoved = false;
		winner();
	}

	public String winner(){
		int numberFire = 0;
		int numberWater = 0;
		for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            	if (pieces[i][j] != null){
		            	if (pieces[i][j].isFire() == true){
		            		numberFire ++;
		            	}
		            	else if (pieces[i][j].isFire() == false){
		            		numberWater ++;
		            	}
	            	}
	            }
	    }
	    if ((numberFire > 0) && (numberWater > 0)){
	    	return null;
	    }
	    else if ((numberFire > 0) && (numberWater == 0)){
	    	return "Fire";
	    }
	    else if ((numberFire == 0) && (numberWater > 0)){
	    	return "Water";
	    }
		return "No one";
	}

}