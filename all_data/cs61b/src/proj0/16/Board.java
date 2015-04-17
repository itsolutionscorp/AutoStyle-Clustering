public class Board {
	private static Piece[][] myboard = new Piece[8][8];
	private static String type, elem, king, imgfile;
	private Piece selectedPiece;
	private int selx, sely;
	private boolean FireTurn = true;
	private boolean moved = false;

	public Board(boolean shouldBeEmpty){
			for (int k = 0; k < 8; k++) {
				for (int l = 0; l < 8; l++) {
					myboard[k][l] = null;
				}
			}
		// System.out.print("Should be empty: ");
		// System.out.println(shouldBeEmpty);
		if (!shouldBeEmpty){
			for (int i = 0; i < 8; i += 2){
					// System.out.println("I GOT here");
				myboard[i][0] = new Piece(true, this, i, 0, "pawn");
				myboard[i][2] = new Piece(true, this, i, 2, "bomb");
				myboard[i][6] = new Piece(false, this, i, 6, "shield");	
			}

			for (int i = 1; i < 8; i += 2){
				myboard[i][1] = new Piece(true, this, i, 1, "shield");
				myboard[i][5] = new Piece(false, this, i, 5, "bomb");
				myboard[i][7] = new Piece(false, this, i, 7, "pawn");	
			}	
		}

	}



	public Piece pieceAt(int x, int y){
		if (x>7 || y>7 || x<0 || y<0){
			return null;
		}
		return myboard[x][y];
	}



	public boolean canSelect(int x, int y){
		Piece dest = pieceAt(x , y);
		if (x>7 || y>7 || x<0 || y<0){
			return false;
		}
		if (dest == null){
				if (selectedPiece == null) {return false;} // unneccesary
				if (!moved && validMove(selx, sely, x, y)) {return true;}
				if (selectedPiece.hasCaptured() && validMove(selx, sely, x, y)) {return true;}
				return false;

			}	

			if (dest.isFire() != FireTurn){ return false;} //checks if piece is same color as turn
			if (!moved) {return true;} //they say also selectedPiece != null
			return false;

		}


		private boolean validMove(int xi, int yi, int xf, int yf){
			Piece cur = myboard[xi][yi];
			if (cur == null) {return false;}
			if (cur.isFire() != FireTurn) {return false;}
			if (xf < 0 || xf > 7 || yf < 0 || yf > 7){ return false;}
			if (Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2){return false;}
			if ((xf + yf) % 2 != 0) {return false;}
			if (xf - xi == 0 || yf - yi == 0){return false;}
			if (pieceAt(xf, yf) != null){return false;}

			int ymov = (cur.isFire() ? 1 : -1);
			if (yi + ymov == yf && Math.abs(xf-xi) == 1 && !selectedPiece.hasCaptured()) {return true;}
			if (yi + 2* ymov == yf && Math.abs(xf - xi) == 2){
				Piece midpiece = myboard[(xf + xi) / 2][yi + ymov];
				if (midpiece == null) {return false;}
				return (cur.side() != midpiece.side());
			}

			if (cur.isKing()) {
				if (yi - ymov == yf && Math.abs(xf-xi) == 1 && !selectedPiece.hasCaptured()) {return true;}
				if (yi - 2* ymov == yf && Math.abs(xf - xi) == 2){
					Piece midpiece = myboard[(xf + xi) / 2][yi - ymov];
					if (midpiece == null) {return false;}
					return (cur.side() != midpiece.side());
				}
			}
			return false;
		}


		public void place(Piece p, int x, int y){
			// System.out.println("placing stuff at: " + x + " " + y);
			if (x < 0 || x > 7 || y < 0 || y > 7){
				return ;
			}
			Piece cur = pieceAt(x, y);
			if (cur != null) {remove(x,y);}
			myboard[x][y] = p;

		}

		public Piece remove(int x, int y){
			if (x < 0 || x > 7 || y < 0 || y > 7){
				return null;
			}
			Piece removed = myboard[x][y];
			myboard[x][y] = null;
			return removed;
		}

		public void select(int x, int y){
			// for (int i = 0; i < 8; i++) {
			// 	System.out.println();
			// 	for (int j = 0; j < 8; j++) {
			// 		Piece cur = pieceAt(i, j);
			// 		if (cur == null){
			// 			System.out.print("null ");
			// 		}
			// 		else{
			// 			System.out.print(cur.isFire() + " ");
			// 		}
			// 		}
			// 	}

			// for (int i = 0; i < 8; i++) {
			// 	System.out.println();
			// 	for (int j = 0; j < 8; j++) {
			// 		Piece cur = pieceAt(i, j);
			// 			System.out.print(cur == null);
			// 		}
			// 	}
			// if (pieceAt(0,2) != null) {
			// 	System.out.println("0,2 exists");
			// 	System.out.println(pieceAt(0,2).isFire());}
			// if (pieceAt(1,3) != null) {
			// 	System.out.println("1,3 exists");
			// 	System.out.println(pieceAt(1,3).isFire());
			// }

			// System.out.println("selx and y are: " + selx + " and " + sely);
			// System.out.println("selecting: " + x + " and " + y);
			Piece dest = pieceAt(x, y);
			// if (dest != null) {System.out.println(dest.isFire());}
			if (dest == null && selectedPiece != null){
				moved = true;
				// System.out.println("moving");

				selectedPiece.move(x,y);
				selx = x;
				sely = y;
				selectedPiece = pieceAt(x, y);	
			}
			else if (dest != null){
				selx = x;
				sely = y;
				selectedPiece = pieceAt(x, y);				
			}
		}

		public boolean canEndTurn(){
			// if (selectedPiece == null) { return moved;}
			// return moved || selectedPiece.hasCaptured();
			System.out.println("can end turn");
			return moved;
		}

		public void endTurn(){
			FireTurn = !FireTurn;

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					Piece cur = pieceAt(i, j);
					if (cur != null){
						cur.doneCapturing();
					}
				}
			}
			if (selectedPiece != null){
				// selectedPiece.doneCapturing();
				selectedPiece = null;
			}
			moved = false;
			selx = -1;
			sely = -1;
		}

		public String winner(){
			int fpiece = 0;
			int wpiece = 0;
			Piece cur;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					cur = pieceAt(i, j);
					if (cur != null){
						if (cur.isFire()) {fpiece++;}
						else {wpiece++;}
					}
				}
			}
			if (fpiece == 0 && wpiece != 0){ return "Water";}
			if (fpiece != 0 && wpiece == 0){ return "Fire";}
			if (fpiece == 0 && wpiece == 0){ return "No one";}
			return null;
		}



		private static void drawBoard(int xval, int yval){
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
					if (i == xval && j == yval) {StdDrawPlus.setPenColor(StdDrawPlus.WHITE);}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);

					if (myboard[i][j] != null){
						Piece curPiece = myboard[i][j];

						if (curPiece.isBomb()){
							type = "bomb";
						}
						else {if (curPiece.isShield()){
							type = "shield";
						}
						else{
							type = "pawn";
						}}

						elem = (curPiece.isFire() ? "fire" : "water");
						king = (curPiece.isKing() ? "fire" : "water");

						if (curPiece.isKing()){
							imgfile = String.format("img/%s-%s-crowned.png", type, elem);
						}
						else{
							imgfile = String.format("img/%s-%s.png", type, elem);
						}
						StdDrawPlus.picture(i + .5, j + .5, imgfile, 1, 1);

					}
				}
			}

		}

		public static void main(String args[]) {
			StdDrawPlus.setXscale(0, 8);
			StdDrawPlus.setYscale(0, 8);
			Board field = new Board(false);
			// Board field = new Board(true);
			// field.place(new Piece(false, field, 6, 6, "pawn"), 6, 6);
			// field.place(new Piece(false, field, 4, 4, "pawn"), 4, 4);
			// field.place(new Piece(true, field, 3, 3, "pawn"), 3, 3);

			field.drawBoard(-1, -1);	

			while (field.winner() == null){
				StdDrawPlus.show(10);

				if(StdDrawPlus.isSpacePressed()){
					if(field.canEndTurn()){ field.endTurn();}
					field.drawBoard(-1,-1);
				}


				if (StdDrawPlus.mousePressed()) {
					int curx = (int) StdDrawPlus.mouseX();
					int cury = (int) StdDrawPlus.mouseY();
					Piece curPiece = field.pieceAt(curx,cury);


					if(field.canSelect(curx, cury)) {
						field.select(curx, cury);
						field.drawBoard(field.selx,field.sely);
					}
				}
			}
			StdDrawPlus.show(10);
			field.drawBoard(-1,-1);
			System.out.println(field.winner() + "won");
		}

	}       