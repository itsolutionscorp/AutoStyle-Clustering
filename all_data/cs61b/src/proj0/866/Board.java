	public class Board {
	
		private Piece[][] pieces = new Piece[8][8];
		private boolean fireTurn = true;
		private boolean hasSelected = false;
		private boolean hasMoved = false;
		private boolean hasCaptured = false;
		private int currentX;
		private int currentY;
		private Piece currentPiece;
		private boolean currentTurn = true;
		private String[] reds = new String[32];
		private boolean exploded = false;
	
		public Board (boolean shouldBeEmpty) {
			if (shouldBeEmpty) {
				drawBoard(8);
			}
			else {
	    		for (int i = 0; i < 8; i = i + 2) {
	    			pieces[i][0] = new Piece (true, this, i, 0, "pawn");
	    		}
	    		for (int i = 1; i < 8; i = i + 2) {
	    			pieces[i][1] = new Piece (true, this, i, 1, "shield");
	    		}
	    		for (int i = 0; i < 8; i = i + 2) {
	    			pieces[i][2] = new Piece (true, this, i, 2, "bomb");
	    		}
	    		for (int i = 1; i < 8; i = i + 2) {
	    			pieces[i][7] = new Piece (false, this, i, 7, "pawn");
	    		}
	    		for (int i = 0; i < 8; i = i + 2) {
	    			pieces[i][6] = new Piece (false, this, i, 6, "shield");
	    		}
	    		for (int i = 1; i < 8; i = i + 2) {
	    			pieces[i][5] = new Piece (false, this, i, 5, "bomb");
	    		}
	    		drawBoard(8);
	        }
		}
	
		public static void main(String[] args) {
			int N = 8;
	        StdDrawPlus.setXscale(0, N);
	        StdDrawPlus.setYscale(0, N);
			// Board board = new Board(true); //for testing
			Board board = new Board(false); 
	
			while(board.winner() == null) {
				board.drawBoard(8);
	            if (StdDrawPlus.mousePressed()) {
	                int x = (int) StdDrawPlus.mouseX();
	                int y = (int) StdDrawPlus.mouseY();
	                System.out.println("Fire turn is " + board.currentTurn);
	                if ((board.canSelect(x, y) && board.fireTurn == board.currentTurn))
	                	board.select(x,y);
	                	System.out.println(board.currentPiece + " is selected.");
	                	System.out.println(board.fireTurn);
	            }
	            if ((StdDrawPlus.isSpacePressed() && board.canEndTurn()) || board.exploded)
	            	board.endTurn();      
	            StdDrawPlus.show(5);
	
	        }
			
			System.exit(0);
	        
	
	
	
		}
	
		private void drawBoard(int N) {
			int count = 0;
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0)
	                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else {
	                	reds[count] = new String(i + " " + j);
	                	count++;
	                	StdDrawPlus.setPenColor(StdDrawPlus.RED); 
	                }
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);               
	                
	            }
	        }
	      for (int x = 0; x < N; x++) {
	      	for (int y = 0; y < N; y++) {
	      		String image = "img/";
	      		if (pieceAt(x, y) == null)
	      			continue;
	      		if (pieceAt(x, y) != null)
	      			if (pieceAt(x, y).isFire()) {
	      				if (pieceAt(x, y).isBomb())
	      					image += "bomb-fire";
	      				if (pieceAt(x, y).isShield())
	      					image += "shield-fire";
	      				if (pieceAt(x, y).isBomb() == false && pieceAt(x, y).isShield() == false)
	      					image += "pawn-fire";
	      			}
	      			else {
	      				if (pieceAt(x, y).isBomb())
	      					image += "bomb-water";
	      				if (pieceAt(x, y).isShield())
	      					image += "shield-water";
	      				if (pieceAt(x, y).isBomb() == false && pieceAt(x, y).isShield() == false)
	      					image += "pawn-water";
	      			}
	      		if (pieceAt(x, y).isKing())
	          	  image += "-crowned";
	      		image += ".png";
	            StdDrawPlus.picture(x + .5, y + .5, image, 1, 1);
				}
	      }
	        StdDrawPlus.show(100);
		}
	
		public Piece pieceAt(int x, int y) {
			if (!(x < 0 || x > 7 || y < 0 || y > 7)) {
				return pieces[x][y];
			}
			else
				return null;
		}
	
		private int average(int x, int y) {
			return ((x + y) / 2);
		}
		
	
		public boolean canSelect(int x, int y) { //========================================================================figure out how to check for current turn
			if (x < 0 || x > 7 || y < 0 || y > 7) {
				return false;
			}
			if (exploded)
				return false;
			if (pieceAt(x, y) != null && pieceAt(x, y).isFire() != fireTurn)
				return false;
			if (pieceAt(x, y) != null) { //if there is a piece at selected space
				if (hasSelected && pieceAt(x, y).isFire() == fireTurn && currentPiece != pieceAt(x, y)) {
					System.out.println("condition 1");
					return true;
				}
				if (pieceAt(x, y).isFire() == fireTurn) {	//checks turn. if both true: fire turn. if both false: water turn.
					if (!hasSelected) {
						hasSelected = true;
						currentPiece = pieceAt(x, y);
						System.out.println("condition 2");
						return true;
					}
					if (hasSelected && !hasMoved) {
						hasSelected = true;
						System.out.println("condition 3");
						return true;
					}
				}	
			}
			else if (pieceAt(x, y) == null) {
				if (hasSelected && !hasMoved) {
					if (hasCaptured) {
						currentX = x;
						currentY = y;
					}
					if (validMove(currentX, currentY, x, y)) { //============================================================need to revise
						System.out.println("condition 4");
						return true;
					}
					else {
						System.out.println("condition 5");
						return false;
					}
	
				}
				if (hasSelected && hasCaptured) {
					
					if (validMove(currentX, currentY, x, y)) {//============================================================need to revise
						System.out.println("condition 6");
						return true;
					}
					else {
						System.out.println("condition 7");
						hasMoved = true;
						return false;
					}
	
				}
			}
			return false;
		}
	
		private boolean validMove(int xi, int yi, int xf, int yf) {
			Piece current_piece = pieceAt(xi, yi);
			System.out.println(current_piece + " is the current piece.");
			boolean typeCapture;
			String current_string = new String(xf + " " + yf);
			for (String s: reds) {
				if (current_string.equals(s))
					return false;
			}
			if (pieceAt(xf, yf) != null) //check if target destination is empty
				return false;
			if ((Math.abs(xf-xi) > 2 && Math.abs(yf-yi) > 2) || (Math.abs(xf-xi) == 0 && Math.abs(yf-yi) == 0)) //trying to move more or less than that which is allowed
				return false;
			if ((hasMoved && hasCaptured) || (!hasMoved)) {
				if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2) { //capture situation
					if (pieceAt(average(xi, xf), average(yi, yf)) != null) { //if piece exists to capture
						boolean type = current_piece.isFire();
						boolean king = current_piece.isKing();
						typeCapture = pieceAt(average(xi, xf), average(yi, yf)).isFire();
						if (typeCapture == false && type) { // fire capturing water
							if (king) { //if king, can move 2 in either +y or -y
								return true;
							}
							else {
								if ((yf-yi) == 2) { //can only move 2 in +y direction
									return true;
								}
								else
									return false;
							}
						}
						if (typeCapture && type == false) { // water capturing fire
							if (king) {
								return true;
							}
							else {
								if ((yf-yi) == -2) { //can only move 2 in -y direction
									return true;
								}
								else
									return false;
							}
						}
					}
				}
			}
			if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1 && !hasMoved) { //simple move
				boolean type = current_piece.isFire();
				boolean king = current_piece.isKing();
				if (king) {
					hasMoved = true;
					return true;
				}
				else {
					if (type) { //checks for fire type
						if ((yf - yi) == 1) { //can only move 1 in +y direction 
							hasMoved = true;
							return true;
						}
					}
					if (!type) { //checks for water type
						if ((yf - yi) == -1) { //can only move 1 in -y direction
							hasMoved = true;
							return true;
						}
					}
					else
						return false;
				}
			}
			hasMoved = false;
			return false;
		}
	
		public void select(int x, int y) {
			System.out.println(x + " " + y);
			currentX = x;
			currentY = y;
			if (pieceAt(x, y) != null) {
				currentPiece = pieceAt(x, y);
				currentX = x;
				currentY = y;
				hasSelected = true;
			}
			else if (pieceAt(x, y) == null) {				
				currentPiece.move(x, y);
				hasMoved = true;
				drawBoard(8);
				if (currentPiece.hasCaptured()) {
					hasCaptured = true;
				}
				if (currentPiece.isBomb() && hasCaptured)
					exploded = true;
			}
		}
	
		public void place(Piece p, int x, int y) {
			if (p == null || x > 7 || y > 7 || x < 0 || y < 0)
				return;
			
			delete(p);
			pieces[x][y] = p;	
		}
		
		private void delete(Piece p) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (p.equals(pieces[i][j])) {
						pieces[i][j] = null;
					}
				}
			}
		}
	
		public Piece remove(int x, int y) {
			if (x > 7 && y > 7) {
				System.out.println("Grid coordinates given are out of bounds.");
				return null;
			}
			if (pieceAt(x, y) == null) {
				System.out.println("There is no piece at given grid coordinates.");
				return null;
			}
			else {
				Piece current_piece = pieceAt(x, y);
				pieces[x][y] = null;
				return current_piece;
			}
		}
	
		public void endTurn() {
			System.out.println("Turn is about to end.");
			fireTurn = !fireTurn;
			currentTurn = !currentTurn;
			hasSelected = false;
			hasMoved = false;
			hasCaptured = false;
			exploded = false;
			if (currentPiece != null) 
				currentPiece.doneCapturing();
			currentPiece = null;
	
		}
	
		public boolean canEndTurn() {
			if (hasMoved)
				return true;
			return false;
		}
	
		public String winner() {
			int numOfFire = 0; //keep track of how many pieces of numOfFire are currently on board.
			int numOfWater = 0;
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            	Piece current_piece = pieceAt(i, j);
	            	if (current_piece != null) {
	            		if (current_piece.isFire())
	            			numOfFire++;
	            		else
	            			numOfWater++;
	            	}
	            }
	        }
	        if (numOfFire == 0 && numOfWater != 0)
	        	return "Water";
	        else if (numOfWater == 0 && numOfFire != 0)
	        	return "Fire";
	        else if (numOfFire == 0 && numOfWater == 0)
	        	return "No one";
	        return null;
		}
	}