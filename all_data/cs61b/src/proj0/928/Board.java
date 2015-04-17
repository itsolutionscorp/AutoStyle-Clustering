public class Board { 

	private Piece[][] pArray;
	private boolean haveSelected = false;
	private int selectedPieceX = -1;
	private int selectedPieceY = -1;
	private boolean fireTurn = true;
	private boolean didYouMove = false;
	private boolean didExplode = false;




    private void drawBoard(int N) {
       	int a = 0;
       	int b = 0;
       	while (a < 8) {
		b = 0;
		while (b < 8) {
   			if ((a + b) % 2 == 0) {
 				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			} else {
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
   			}
 			StdDrawPlus.filledSquare(a + .5, b + .5, .5);
 			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
 			b += 1;
		}  			     			  			
		a += 1;
 		}
        // for (int i = 0; i < N; i++) {
        // 	for (int j = 0; j < N; j++) {
        //    		if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        //         else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        //         StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        //         StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        //     }
        // }
    	Piece[][] currArray = pArray;
    	int rowNum = 0;
    	int colNum = 0;
    	while (rowNum < 8) {
    		colNum = 0;
			while (colNum < 8) { 
				if (currArray[rowNum][colNum] == null) {
					int x = 0; // Did not know "else if" was a thing until 7 work hours later...oops
				} else if ((currArray[rowNum][colNum].isFire() == true) && (currArray[rowNum][colNum].isBomb() == true) && (currArray[rowNum][colNum].isKing() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/bomb-fire-crowned.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == true) && (currArray[rowNum][colNum].isShield() == true) && (currArray[rowNum][colNum].isKing() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/shield-fire-crowned.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == true) && (currArray[rowNum][colNum].isKing() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/pawn-fire-crowned.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == false) && (currArray[rowNum][colNum].isBomb() == true) && (currArray[rowNum][colNum].isKing() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/bomb-water-crowned.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == false) && (currArray[rowNum][colNum].isShield() == true) && (currArray[rowNum][colNum].isKing() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/shield-water-crowned.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == false) && (currArray[rowNum][colNum].isKing() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/pawn-water-crowned.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == true) && (currArray[rowNum][colNum].isBomb() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/bomb-fire.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == true) && (currArray[rowNum][colNum].isShield() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/shield-fire.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/pawn-fire.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == false) && (currArray[rowNum][colNum].isBomb() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/bomb-water.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == false) && (currArray[rowNum][colNum].isShield() == true)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/shield-water.png", 1, 1);
				} else if ((currArray[rowNum][colNum].isFire() == false)) {
					StdDrawPlus.picture(rowNum + .5, colNum + .5, "img/pawn-water.png", 1, 1);
				}
				colNum += 1;				
			}
			rowNum += 1;
		}
    }






public static void main(String[] args) {
    int boardSize = 8;
    StdDrawPlus.setXscale(0, boardSize);
    StdDrawPlus.setYscale(0, boardSize);
    Board starter = new Board(false);
    starter.drawBoard(8);
    //starter.pArray[0][0] = new Piece(true, starter, 0, 0, "shield");

    while(true) {
        starter.drawBoard(boardSize);
        if (StdDrawPlus.mousePressed()) {
            int x = (int)StdDrawPlus.mouseX();
            int y = (int)StdDrawPlus.mouseY();
            if (starter.canSelect(x, y) == true) {
    			starter.select(x, y);
    		}
        }         
        if (StdDrawPlus.isSpacePressed() == true) {
    		if (starter.canEndTurn() == true) {
    			starter.endTurn();
   				if (starter.winner() == "Fire") {
   					break;
   				} else if (starter.winner() == "Water"){
   					break;
   				} else if (starter.winner() == "No one") {
   					break;
   				}
   			} 
   		} 
        StdDrawPlus.show(100);
    }
    starter.winner();
}

public Board(boolean shouldBeEmpty) {
	if (shouldBeEmpty == true){
		pArray = new Piece[8][8];
	} else {
	pArray = new Piece[8][8];
	pArray[0][0] = new Piece(true, this, 0, 0, "pawn");
	pArray[2][0] = new Piece(true, this, 2, 0, "pawn");
	pArray[4][0] = new Piece(true, this, 4, 0, "pawn");
	pArray[6][0] = new Piece(true, this, 6, 0, "pawn");

	pArray[1][1] = new Piece(true, this, 1, 1, "shield");
	pArray[3][1] = new Piece(true, this, 3, 1, "shield");
	pArray[5][1] = new Piece(true, this, 5, 1, "shield");
	pArray[7][1] = new Piece(true, this, 7, 1, "shiled");

	pArray[0][2] = new Piece(true, this, 0, 2, "bomb");
	pArray[2][2] = new Piece(true, this, 2, 2, "bomb");
	pArray[4][2] = new Piece(true, this, 4, 2, "bomb");
	pArray[6][2] = new Piece(true, this, 6, 2, "bomb");

	pArray[1][5] = new Piece(false, this, 1, 5, "bomb");
	pArray[3][5] = new Piece(false, this, 3, 5, "bomb");
	pArray[5][5] = new Piece(false, this, 5, 5, "bomb");
	pArray[7][5] = new Piece(false, this, 7, 5, "bomb");
	
	pArray[0][6] = new Piece(false, this, 0, 6, "shield");
	pArray[2][6] = new Piece(false, this, 2, 6, "shield");
	pArray[4][6] = new Piece(false, this, 4, 6, "shield");
	pArray[6][6] = new Piece(false, this, 6, 6, "shield");

	pArray[1][7] = new Piece(false, this, 1, 7, "pawn");
	pArray[3][7] = new Piece(false, this, 3, 7, "pawn");
	pArray[5][7] = new Piece(false, this, 5, 7, "pawn");
	pArray[7][7] = new Piece(false, this, 7, 7, "pawn");
	}
}

public boolean canSelect(int x, int y) {
	if (pieceAt(x, y) != null) {
		if (fireTurn == pieceAt(x, y).isFire()) {
			if ((haveSelected != true)) {
				return true;
			} else if (didYouMove == false) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	} else if (pieceAt(x, y) == null) {
		if (haveSelected == true) {
			if ((validMove(selectedPieceX, selectedPieceY, x, y) == true) && (didYouMove == true)) {
				return true;
			} else if (validMove(selectedPieceX, selectedPieceY, x, y) == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	} else {
		return false;
	}
}

public void select(int x, int y) {
	// if (pieceAt(x, y) != null) {
	// 	System.out.println(pieceAt(x, y).type);
	// }
	if ((pieceAt(x, y) != null)) {
		haveSelected = true;
		selectedPieceX = x;
		selectedPieceY = y;
	} else if (pieceAt(x, y) == null) {
		pieceAt(selectedPieceX, selectedPieceY).move(x, y);
		haveSelected = true;
		selectedPieceX = x;
		selectedPieceY = y;
	}
}


 public Piece pieceAt(int x, int y) {
    if (x > 7 || y > 7 || x < 0 || y < 0) {
    	return null;
    } else {
    	Piece tempP = pArray[x][y];
    	return tempP;
    }
}





public Piece remove(int x, int y) {
	if (x > 7 || y > 7 || y < 0 || x < 0) {
		System.out.println("Out of bounds");
		return null;
	} else if (pArray[x][y] == null) {
		System.out.println("Piece doesn't exist");
		return null;
	} else {
		Piece temp = pArray[x][y];
		pArray[x][y] = null;
		return temp;
	}
}

public void place(Piece p, int x, int y) {
	String itsType;

	if (((x < 8) && (x >= 0) && (y < 8) && (0 <= y)) && (p != null)) {
		

		if (p.isBomb() == true) {
			itsType = "bomb";
		} else if (p.isShield() == true) {
			itsType = "shield";
		} else {
			itsType = "pawn";
		}

		if (p.isKing()) {
			itsType += "king";
		}

		Piece newPiece = new Piece(p.isFire(), this, x, y, itsType);
		pArray[x][y] = newPiece;

		didYouMove = true;
	}

}


private boolean validMove(int xi, int yi, int xf, int yf) {
	if ((xf > 7) || (yf > 7) || (xf < -1) || (yf < -1) || (pieceAt(xf, yf) != null)) { // if the desired space doesnt exist or has a piece on it 
		return false; 
	} else if ( (pieceAt(xf, yf) == null) && (pieceAt(xi, yi) != null) && (pieceAt(xi, yi).isKing() == true) && (Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1) && (didYouMove == false)) { // if it is a king and the desired space is within (1,1)
		return true;
	} else if ((pieceAt(xf, yf) == null) && (pieceAt(xi, yi) != null) && (pieceAt(xi, yi).isKing() == true) && (Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) { //if 
		int xDif = xf + xi;
		int yDif = yf + yi;
		int middleX = (xDif/2);
		int middleY = (yDif/2);
		if ((pieceAt(middleX, middleY) != null) && (pieceAt(middleX, middleY).isFire() != pieceAt(xi, yi).isFire()) ) {
			return true;
		} else {
			return false;
		}
	} else if ((pieceAt(xf, yf) == null) && (pieceAt(xi, yi) != null) &&(pieceAt(xi, yi).isFire() == true) && (((xf - xi) == 1) || ((xi - xf) == 1)) && ((yf - yi) == 1) && (didYouMove == false)){
				return true;
	} else if ((pieceAt(xf, yf) == null)&& (pieceAt(xi, yi) != null) && (pieceAt(xi, yi).isFire() == true) && (((xf - xi) == 2) || ((xi - xf) == 2)) && ((yf - yi) == 2)) {
		int xDi = xf + xi;
		int yDi = yf + yi;
		int midX = (xDi/2);
		int midY = (yDi/2);
		if ((pieceAt(midX, midY) != null) && (pieceAt(midX, midY).isFire() != pieceAt(xi, yi).isFire()) ) {
			return true;
		} else {
			return false;
		}
	} else if ((pieceAt(xf, yf) == null)&& (pieceAt(xi, yi) != null) && (pieceAt(xi, yi).isFire() == false) && (((xf - xi) == 1) || ((xi - xf) == 1)) && ((yi - yf) == 1) && (didYouMove == false)) {
				return true;
	} else if ((pieceAt(xf, yf) == null)&& (pieceAt(xi, yi) != null) && (pieceAt(xi, yi).isFire() == false) && (((xf - xi) == 2) || ((xi - xf) == 2)) && ((yi - yf) == 2)) {
		int xD = xf + xi;
		int yD = yf + yi;
		int miX = (xD/2);
		int miY = (yD/2);
		if ((pieceAt(miX, miY) != null) && (pieceAt(miX, miY).isFire() != pieceAt(xi, yi).isFire()) ) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

public boolean canEndTurn() {
	if (didYouMove == true) {
		return true;
	} else {
		return false;
	}
}

public void endTurn() {
	if (fireTurn == true) {
		fireTurn = false;
	} else {
		fireTurn = true;
	}
	didYouMove = false;
	haveSelected = false;
	selectedPieceY = -1;
	selectedPieceX = -1;
	didExplode = false;
}

public String winner() {
	boolean isThereAFire = false;
	boolean isThereAWater = false;
	boolean noPieces = true;
	for (int rowNum = 0; rowNum < 8; rowNum += 1) {
	 	for (int colNum = 0; colNum < 8; colNum += 1) {
	 		if ((pieceAt(rowNum, colNum) != null) && (pieceAt(rowNum, colNum).isFire() == true)) {
	 			isThereAFire = true;
	 			noPieces = false;
	 		} else if ((pieceAt(rowNum, colNum) != null) && (pieceAt(rowNum, colNum).isFire() == false)) {
	 			isThereAWater = true;
	 			noPieces = false;
	 		}
	 	}
	}
	if ((isThereAWater == true) && (isThereAFire == true)) {
		return null;
	} else if (isThereAWater == true) {
		return "Water";
	} else if (isThereAFire == true) {
		return "Fire";
	} else if (noPieces == true) {
		return "No one";
	} else {
		return null;
	}
}



}


