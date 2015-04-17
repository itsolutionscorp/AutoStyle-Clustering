public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private int savedX;
	private int savedY;
	private boolean pieceSelected = false;
	private int playerTurn = 0;
	private boolean pieceMoved = false;
	private Piece selectedPiece;

	// private Board b = new Board(true);

	public static void main(String[] args){

	 	int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        b.drawBoard();
        while (true) {

        	b.drawPieces(); 
	        if (StdDrawPlus.mousePressed()) {
	        	b.drawBoard();
	            double xTemp = StdDrawPlus.mouseX();
				double yTemp = StdDrawPlus.mouseY();
				if(b.canSelect((int) xTemp, (int) yTemp)){
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                StdDrawPlus.filledSquare(((int) xTemp)+0.5, ((int) yTemp) + 0.5, .5);
	                b.select((int) xTemp, (int) yTemp);
	            }
	            b.drawPieces();
	        }
	        if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
	        	b.endTurn();
	        	b.drawBoard();
	        	b.drawPieces();
	        }
	        StdDrawPlus.show(100);  
	    }        

	 	
	}

	private void drawBoard(){

	 	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	}
                else{
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
            	}
        	}


	}

	   // private static void drawBoard(int N) {
    //     for (int i = 0; i < N; i++) {
    //         for (int j = 0; j < N; j++) {
    //             if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    //             else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
    //             StdDrawPlus.filledSquare(i + .5, j + .5, .5);
    //             StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

    //         }
    //     }
    // }


	private void drawPieces(){

	 	for(int i = 0; i < 8; i ++){ 
	 		for(int j = 0; j < 8; j ++){
	 			if(pieces[i][j] !=null){
	 				StdDrawPlus.picture(0.5+ i, 0.5 + j, pieceImgString(pieces[i][j]), 1, 1);

	 			}
	 		}
	 	}
	}


	private String pieceImgString(Piece p){
	 	String str = "img/";

	 	if(p.isBomb()){
	 		str = str + "bomb-";
	 	}else if(p.isShield()){
	 		str = str + "shield-";
	 	}else{
	 		str = str + "pawn-";
	 	}

	 	if(p.isFire()){
	 		str = str + "fire";
	 	}else{
	 		str = str + "water";
	 	}

	 	if(p.isKing()){
	 		str = str + "-crowned";
	 	}
	 	str = str + ".png";

	 	return str;
	}

	public Board(boolean shouldBeEmpty){


	 	if(shouldBeEmpty){
	 		shouldBeEmpty = !shouldBeEmpty;

	 	}
	 	else{
	 		for(int i = 0; i < 8; i = i + 2){
	 			pieces[i][0] = new Piece(true, this, i, 0, "pawn");
	 			pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield");
	 			pieces[i][2] = new Piece(true, this, i, 2, "bomb");

	 			pieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn");
	 			pieces[i][6] = new Piece(false, this, i, 6, "shield");
	 			pieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb");
 	 		}

	 	}
	 	
	 	

	}

	private boolean outOfBounds(int x, int y){
	 	if(x > 7 || y > 7){
	 		return true;
	 	}
	 	if(x < 0 || y < 0){
	 		return true;
	 	}

	 	// if(pieces[x][y] == null){
	 	// 	return false;
	 	// }
	 	return false;
	}
	public Piece pieceAt(int x, int y){


	 	if(outOfBounds(x, y)){
	 		return null;
	 	}
	 	return pieces[x][y];


	}

	public boolean canSelect(int x, int y){

	 	if(outOfBounds(x,y)){
	 		return false;
	 	}
	 	if(pieceAt(x,y) != null && playerTurn != pieceAt(x, y).side()) {
	 		return false;
	 	}
	 	if (pieceAt(x,y) != null){
	 			if(pieceSelected && !pieceMoved){
	 				return true;
	 			}else if(!pieceSelected){
	 				return true;
	 			}
	 	}
	 	
	 	if(!pieceMoved && validMove(savedX, savedY, x, y)){
	 			// if(selectedPiece != null && selectedPiece.isKing()){
	 			// 	return true;
	 			// }
	 			// if(selectedPiece != null && selectedPiece.isFire()){

	 			// 	if(y-savedY == 1 || y-savedY == 2){
	 			// 		return true;	
	 			// 	} 
	 			// }

	 			// if(selectedPiece != null && !selectedPiece.isFire()){
	 			// 	if(y-savedY == -1 || y-savedY == -2){
	 			// 		return true;	
	 			// 	} 

	 			// }
	 			return true;
	 		}
	 	
	 	if (pieceSelected && selectedPiece.hasCaptured() && pieceMoved && validMove(savedX, savedY, x, y)){
			if(Math.abs(x-savedX) == 2 && Math.abs(y-savedY) == 2){
				if(pieceAt((x+savedX)/2, (y+savedY)/2) != null && pieceAt((x+savedX)/2, (y+savedY)/2).side() != playerTurn) {

	 			// if(selectedPiece.isKing()){
	 			// 	if((y-savedY) == 2 || (y-savedY) == -2){
	 			// 	return true;
	 			// }
	 			// }
	 			// if(selectedPiece.isFire()){

	 			// 	if((y-savedY) == 2){
	 			// 		return true;	
	 			// 	} 
	 			// }
	 			// if(!selectedPiece.isFire()){
	 			// 	if((y-savedY) == -2){
	 			// 		return true;	
	 			// 	} 

	 			// }
	 		return true;

	 	}
	 }}
	 
	 	
	 	return false;


	}


	private boolean validMove(int xi, int yi, int xf, int yf){

		int diffX = xf- xi;
	 	int diffY = yf- yi;

		if(Math.abs(diffX) == 1 && Math.abs(diffY) == 1){
			if(pieceAt(xf, yf) == null){
				if(selectedPiece != null && selectedPiece.isKing()){
					return true;
				}if(selectedPiece != null && selectedPiece.isFire()){
					if(diffY > 0){
						return true;
					}

				}if(selectedPiece != null && !selectedPiece.isFire()){

					if(diffY < 0){
						return true;
					}
				}
				
			}
		}

		if(Math.abs(diffX) == 2 && Math.abs(diffY) == 2){
			if(pieceAt((xf+xi)/2, (yf+yi)/2) != null && pieceAt((xf+xi)/2, (yf+yi)/2).side() != playerTurn) {
				if(selectedPiece != null && selectedPiece.isKing()){
					return true;
				}if(selectedPiece != null && selectedPiece.isFire()){
					if(diffY > 0){
						return true;
					}

				}if(selectedPiece != null && !selectedPiece.isFire()){

					if(diffY < 0){
						return true;
					}
				}
			}

		}

		return false;

		// if(selectedPiece.isKing()){
		// 	if (Math.abs(xf - xi) <= 2 && Math.abs(yf - yi) <= 2){
		//  		if(Math.abs(xf - xi)== Math.abs(yf - yi)){
		//  			if(xi != xf && yi != yf){
		//  				return true;
		//  			}
		//  		}
		//  	}
		// }


		// if(selectedPiece.isFire()){
		//  	if (Math.abs(xf - xi) <= 2 && (yf - yi) > 0 && (yf - yi) <= 2){
		//  		if(Math.abs(xf - xi)== Math.abs(yf - yi)){
		//  			if(xi != xf){
		//  				return true;
		//  			}
		//  		}
		//  	}
		// }else if(!selectedPiece.isFire()){
		//  	if (Math.abs(xf - xi) <= 2 && (yf - yi) < 0 && (yf - yi) >= -2){
		//  		if(Math.abs(xf - xi)== Math.abs(yf - yi)){
		//  			if(xi != xf){
		//  				return true;
		//  			}		
		//  		}
		//  	}
		// }

	 	

	}

	public void select(int x, int y){
		savedX = x;
	 	savedY = y;

	 	if(!pieceSelected){

	 		if(pieceAt(x,y) != null){

	 			selectedPiece = pieceAt(x,y);
	 			pieceSelected = true;
	 		}
		}
	 	if(pieceSelected && pieceAt(x,y) == null){
	 		selectedPiece.move(x, y);
	 		pieceMoved = true;
	 	}

		// if(!pieceSelected){

	 // 	if(pieceAt(x,y) != null){

	 // 		selectedPiece = pieceAt(x,y);
	 // 		pieceSelected = true;


	 // 	}
	 // }
	 // 	if(pieceSelected && pieceAt(x,y) == null){

	 // 		selectedPiece.move(x,y);
	 // 		place(selectedPiece, x, y);
	 // 		savedX = x;
	 // 		savedY = y;
	 // 		pieceMoved = true;

	 // 	} else {
	 // 		selectedPiece = pieceAt(x,y);
	 // 		savedX = x;
	 // 		savedY = y;
	 // 		pieceSelected = true;
	 // 	}


	}

	public void place(Piece p, int x, int y){

	 	// for(int i = 0; i < 8; i ++){
	 	// 	for(int j = 0; j < 8; j ++){

	 	// 		if(pieces[i][j] == p){

	 	// 			if(pieceCondition(i,j)){
	 	// 				pieces[i][j].x = x;
	 	// 				pieces[i][j].y = y;
	 	// 			}
	 	// 		}
	 	// 	}
	 	// }
	 	if(!outOfBounds(x, y)){

	 		for (int i = 0; i < 8; i ++){
	 			for (int j = 0; j < 8; j ++){
	 				if (pieceAt(i,j) == p){
	 					p = remove(i, j);
	 				}

	 			}

	 		}
	 			pieces[x][y] = p;

	 		}
	 		
	 		
	 	}
	 	
	

	public Piece remove(int x, int y){

	 	if(outOfBounds(x,y)){
	 		System.out.println("It is out of bounce");
	 		return null;
	 	}else if(pieces[x][y] == null){
	 		System.out.println("Piece does not exist at the location");
	 		return null;
	 	}else{

	 	Piece tempPiece = pieceAt(x,y);
	 	pieces[x][y] = null;
	 	return tempPiece;
	 	
	 	}


	}

	public boolean canEndTurn(){
		if(selectedPiece != null){
			return (pieceMoved || selectedPiece.hasCaptured());
		}
		return false;
	}

	public void endTurn(){
		playerTurn = 1 - playerTurn;
	 	pieceMoved = false;
	 	selectedPiece.doneCapturing();
	 	selectedPiece = null;
	 	pieceSelected = false;

	}

	public String winner(){

		int firePieceNum = 0;
		int waterPieceNum = 0;

		for (int i = 0; i < 8; i ++){
			for (int j = 0; j < 8; j ++){

				if (pieces[i][j] != null){
					if(pieces[i][j].isFire()){
						firePieceNum = firePieceNum + 1;
					}else if(!pieces[i][j].isFire()){
						waterPieceNum = waterPieceNum + 1;
					}
				}
			}
		}

		if (firePieceNum == 0 && waterPieceNum == 0){
			return "No one";
		}else if (firePieceNum == 0){
			return "Water";
		}else if (waterPieceNum == 0){
			return "Fire";
		}else{

			return null;
		}

		
	}
}
