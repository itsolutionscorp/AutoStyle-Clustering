public class Board{

	        private Piece selectedPiece ;
	        private int selectedX ;
	        private int selectedY ;
	        private Piece movedPiece ;
	        private Piece capturedPiece ;
	        private boolean handleFire = true;
	        private Board newGame;
		    private Piece[][] pList = new Piece[8][8];

	public Board(boolean shouldBeEmpty){
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i +j) % 2 == 0) { 
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
					else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);	
					}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
			}
		if ( !shouldBeEmpty ){
			handleFire = true;
			Board b = new Board(true);
		    pList[0][0] = new Piece(true, b, 0, 0, "pawn" );
		    pList[2][0] = new Piece(true, b, 2, 0, "pawn");
		    pList[4][0] = new Piece(true, b, 4, 0, "pawn");
		    pList[6][0] = new Piece(true, b, 6, 0, "pawn");
		    pList[1][1] = new Piece(true, b, 1, 1, "shield");
		    pList[3][1] = new Piece(true, b, 3, 1, "shield");
		    pList[5][1] = new Piece(true, b, 5, 1, "shield");
		    pList[7][1] = new Piece(true, b, 7, 1, "shield");
		    pList[0][2] = new Piece(true, b, 0, 2, "bomb");
		    pList[2][2] = new Piece(true, b, 2, 2, "bomb");
		    pList[4][2] = new Piece(true, b, 4, 2, "bomb");
		    pList[6][2] = new Piece(true, b, 6, 2, "bomb");
		    pList[1][7] = new Piece(false, b, 1, 7, "pawn" );
		    pList[3][7] = new Piece(false, b, 3, 7, "pawn");
		    pList[5][7] = new Piece(false, b, 5, 7, "pawn");
		    pList[7][7] = new Piece(false, b, 7, 7, "pawn");
		    pList[0][6] = new Piece(false, b, 0, 6, "shield");
		    pList[2][6] = new Piece(false, b, 2, 6, "shield");
		    pList[4][6] = new Piece(false, b, 4, 6, "shield");
		    pList[6][6] = new Piece(false, b, 6, 6, "shield");
		    pList[1][5] = new Piece(false, b, 1, 5, "bomb");
		    pList[3][5] = new Piece(false, b, 3, 5, "bomb");
		    pList[5][5] = new Piece(false, b, 5, 5, "bomb");
		    pList[7][5] = new Piece(false, b, 7, 5, "bomb");
			
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					Piece thisPiece = pieceAt(i, j);
				 if (thisPiece != null) {
				 	String imgpath;
				 	if (thisPiece.isFire()){
				 		if (thisPiece.isBomb()) imgpath = "img/bomb-fire.png";
				 		else if (thisPiece.isShield()) imgpath = "img/shield-fire.png";
				 		else imgpath = "img/pawn-fire.png";
				 	}
				 	else {
				 		if (thisPiece.isBomb()) imgpath = "img/bomb-water.png";
				 		else if (thisPiece.isShield()) imgpath = "img/shield-water.png";
				 		else imgpath = "img/pawn-water.png";
				 	}
				 	StdDrawPlus.picture(i + 0.5, j + 0.5, imgpath, 1, 1);
				 }			 
				}
			}


		}
		
	} 
	private void drawPiece(){
		for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					Piece thisPiece = pieceAt(i, j);
				 if (thisPiece != null) {
				 	String imgpath;
				 	if (thisPiece.isFire()){
				 		if (thisPiece.isBomb()) imgpath = "img/bomb-fire.png";
				 		else if (thisPiece.isShield()) imgpath = "img/shield-fire.png";
				 		else imgpath = "img/pawn-fire.png";
				 	}
				 	else {
				 		if (thisPiece.isBomb()) imgpath = "img/bomb-water.png";
				 		else if (thisPiece.isShield()) imgpath = "img/shield-water.png";
				 		else imgpath = "img/pawn-water.png";
				 	}
				 	StdDrawPlus.picture(i + 0.5, j + 0.5, imgpath, 1, 1);
				 }			 
				}
			}

	}

	public Piece pieceAt(int x, int y){
		if ((x > 8) || (y > 8)) return null;
		else return pList[x][y];
	}

	public boolean canSelect(int x, int y){
		//buggy

		Piece wantSelect = pieceAt(x, y);
		if ((wantSelect != null) && (wantSelect.isFire() == handleFire) && ((selectedPiece == null) || (movedPiece == null))) {
			return true;
		}
		else if ((wantSelect == null) && (selectedPiece != null) && (movedPiece == null)){
			  boolean checkvalidmove = validMove(selectedX, selectedY, x, y);
			  if (checkvalidmove) return true;
			  else return false;		
		   }
		else if ((wantSelect == null ) && (movedPiece != null) && (selectedPiece != null) && (selectedPiece.hasCaptured())) {
			  boolean checkvalidmove = validMove(selectedX, selectedY, x, y);
			  if (checkvalidmove) return true;
			  else return false;
		   }
		else return false;
	}

    
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	if ((xf == xi + 1) && (yf == yi + 1)) return true;
    	else if ((xf == xi - 1) && (yf == yi - 1)) return true;
    	else if ((xf == xi + 2) && (yf == yi + 2)) return true;
    	else if ((xf == xi - 2) && (yf == yi - 2)) return true;
    	else return false;
    }

	
	public void select(int x, int y){
		//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		//StdDrawPlus.filledSquare (x + .5, y + .5, .5);
		//Piece instance = pieceAt(x, y);
		//if (instance != null) {
		//	if (instance.isFire){
		//	StdDrawPlus.picture(instance.xposition + 0.5, instance.yposition + 0.5, "img/"+ instance.type + "-fire.png", 1, 1);
		//	else StdDrawPlus.picture(p[i][j].xposition + 0.5, p[i][j].yposition + 0.5, "img/"+ p[i][j].type + "-water.png", 1, 1);
		//}
	//}
		Piece instance = pieceAt(x, y);
		if ((instance == null) && (selectedPiece != null)) {
			selectedPiece.move (x, y);
			movedPiece = selectedPiece;
		}
		else if (selectedPiece == null) {
			selectedX = x;
		    selectedY = y;
			selectedPiece = pieceAt(x, y);
		}
	}

	public void place(Piece p, int x, int y){
		
		if ((x < 8) && (y < 8) && (p != null)){
			Piece currentPlace = pieceAt (x, y);
			String imgpath;
			if (currentPlace != null) {
				remove(x, y);
			}
			if (p.isFire()){
				if (p.isBomb()) imgpath = "img/bomb-fire";
			 		else if (p.isShield()) imgpath = "img/shield-fire";
			 		else imgpath = "img/pawn-fire";
			}
			else {
				if (p.isBomb()) imgpath = "img/bomb-water";
			 		else if (p.isShield()) imgpath = "img/shield-water";
			 		else imgpath = "img/pawn-water";
			}
			//khkhkhkhk
			if (p.isKing()) imgpath = imgpath + "-crowned.png";
			 	else imgpath = imgpath + ".png";
			 	
			 	StdDrawPlus.picture(x + .5, y + .5, imgpath, 1, 1);
			 	pList[x][y] = p;


			//for (int i = 0; i < 8; i++){
			//	for (int j = 0; j < 8; j++){
			//		Piece thisPiece = pieceAt(i, j);
			//	 if (thisPiece != null) {
			//	 	String imgpath;
			//	 	if (p.isFire()){
			//	 		if (p.isBomb()) imgpath = "img/bomb-fire.png";
			//	 		else if (p.isShield()) imgpath = "img/shield-fire.png";
			//	 		else imgpath = "img/pawn-fire.png";
			//	 	}
			//	 	else {
			//	 		if (p.isBomb()) imgpath = "img/bomb-water.png";
			//	 		else if (p.isShield()) imgpath = "img/shield-water.png";
			//	 		else imgpath = "img/pawn-water.png";
			//	 	}
			//	 	StdDrawPlus.picture(i + 0.5, j + 0.5, imgpath, 1, 1);
			//	 }			 
			//	}
			//}
			//movedPiece = currentPlace;
		}
	}
	
	public Piece remove(int x, int y){
		if ((x > 8) || (y > 8)) {
			return null;
		}
		else if (pList[x][y] == null) {
			return null;
		}
		else {
			capturedPiece = pList[x][y];
			return pList[x][y] = null;

		}  
	}

	public boolean canEndTurn(){
		if ((capturedPiece != null) || (movedPiece != null)) return true;
		else return false;
	}
	
	public void endTurn(){
	 handleFire = !handleFire;
	 selectedPiece.doneCapturing();
	 capturedPiece = null;
	 movedPiece = null;
	 selectedPiece = null;

	}

	public String winner(){
		int numberoffire = 0;
		int numberofwater = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++){
				if (pList[i][j] != null) {
				  if (pList[i][j].isFire()){
				    	numberoffire++;
				  }
				  else numberofwater++;
			    }
			}
		}
		if ((numberoffire != 0) && (numberofwater == 0)) return "Fire";
		else if ((numberoffire == 0) && ( numberofwater != 0)) return "Water";
		else if ((numberoffire == 0) && (numberofwater == 0)) return "No one";
		else return null;
	}

	/* Takes all the internal information and portrays it in the GUI */
	private void updateGUI() {
		// loops through your pieces[][] and draws (also somehow handles the highlighting of active piece)
		//newGame = new Board(true);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare (selectedX + .5, selectedY + .5, .5);
		    for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					Piece thisPiece = pieceAt(i, j);
				 if (thisPiece != null) {
				 	String imgpath;
				 	if (thisPiece.isFire()){
				 		if (thisPiece.isBomb()) imgpath = "img/bomb-fire.png";
				 		else if (thisPiece.isShield()) imgpath = "img/shield-fire.png";
				 		else imgpath = "img/pawn-fire.png";
				 	}
				 	else {
				 		if (thisPiece.isBomb()) imgpath = "img/bomb-water.png";
				 		else if (thisPiece.isShield()) imgpath = "img/shield-water.png";
				 		else imgpath = "img/pawn-water.png";
				 	}
				 	StdDrawPlus.picture(i + 0.5, j + 0.5, imgpath, 1, 1);
				 }			 
				}
			}
	}

	public static void main (String args[]){
 	//starts new game
		//kjljkjlijlk
		Board newGame = new Board(false);
		boolean handleFire = true;
		//StdDrawPlus.setXscale(0, 8);
		//StdDrawPlus.setYscale(0, 8);
		//	for (int i = 0; i < 8; i++) {
		//		for (int j = 0; j < 8; j++) {
		//			if ((i +j) % 2 == 0) { 
		//				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		//			}
		//			else {
		//				StdDrawPlus.setPenColor(StdDrawPlus.RED);	
		//			}
		//			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		//			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		//		}
		//	}
			//newGame = new Board(false);

		while ( newGame.winner() == null){
			if (StdDrawPlus.mousePressed()) {
				double currentX = StdDrawPlus.mouseX();
				double currentY = StdDrawPlus.mouseY();
				int x = (int) currentX;
				int y = (int) currentY;
				if (newGame.canSelect(x, y)) {
					newGame.select(x, y);
					StdDrawPlus.show(100);
					newGame.updateGUI();
				}
			}
			if (StdDrawPlus.isSpacePressed()){
				if (newGame.canEndTurn()) {
					newGame.endTurn();
					StdDrawPlus.show(100);
					newGame.updateGUI();
				}
			}
        }
  }
}
