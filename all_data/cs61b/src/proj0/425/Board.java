import static org.junit.Assert.assertEquals;

/** Author David Fang
*/

public class Board{

	/** constructor for board
	  * if shouldBeEmpty is true, initialized an empty board
	  * otherwise, initializes a Board with the default configuration
	  * empty board is possible	
	*/

    
    private Piece[][] pieces = new Piece[8][8];
    
    
	public Board(boolean shouldBeEmpty){
		
		if (shouldBeEmpty == true){
			return;
			
		} else {
			placed = true;
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
					if (j == 0){
		            	if (i % 2 == 0){
//		            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
		            		pieces[i][j] = new Piece(true, this, i, j, "pawn");
		            	}
		            }
		            if (j == 1){
		            	if (i % 2 != 0){
//		            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		            		pieces[i][j] = new Piece(true, this, i, j, "shield");
		            	}
		            }
		            if (j == 2){
		            	if (i % 2 == 0){
//		            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		            		pieces[i][j] = new Piece(true, this, i, j, "bomb");
		            	}
		            }
		            if (j == 5){
		            	if (i % 2 != 0){
//		            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		            		pieces[i][j] = new Piece(false, this, i, j, "bomb");
		            	}
		            }
		            if (j == 6){
		            	if (i % 2 == 0){
//		            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		            		pieces[i][j] = new Piece(false, this, i, j, "shield");
		            	}
		            }
		            if (j == 7){
		            	if (i % 2 != 0){
//		            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
		            		pieces[i][j] = new Piece(false, this, i, j, "pawn");
		            	}
		            }
			
	            }
			}
		}
	}

	/** Gets the piece at position(x,y) on the board
	  * null if there is no piece
	  * if (x,y) is out of bounds, returns null
	*/
	public Piece pieceAt(int x, int y){
		 if (x > 7 || x < 0 || y > 7 || y < 0){
		 	return null;
		 } else if (this.pieces[x][y] != null){
		 	return this.pieces[x][y];
		 } else {
		 	return null;
		 }
	}

	// * Returns true if the square at (x,y) can be selected
	//   * Piece can be selected if:
	//     * Is the corresponding players turn and 
	//     * The player has not selected a piece yet or
	//     * The player has selected a piece but has not moved it 
	//   * Empty square may be selected if:
	//     * During this turn the player has selected a piece which hasn't moved yet
	//     and is selected an empty spot which is a valid move for the previous
	//     selected Piece or
	//     * During this turn the player has selected a piece, captured, and has 
	//     selected another valid capture destination
	//     	* When performing multiple captures, you should only select the active
	//     	piece once; all other selections should be valid destination points

//	need to still account for captures
	private boolean validMove(int xi, int yi, int xf, int yf){
//		input is out of bounds
		if(pieces[xf][yf] != null){
			return false;
		}
		if (xi == xf){
			return false;
		}
		if (yi == yf){
			return false;
		}
		if(moved && hasCaptured == false){
			return false;
		}
		if(moved && hasCaptured){
			if(Math.abs(xi-xf)==1 || Math.abs(yi-yf)==1){
				return false;
			}
		}
		if (xi < 0 || xi > 7 || yi < 0 || yi >7 || xf < 0 || xf > 7 || yf < 0 || yf > 7){
			return false;
		}
		if (xi == xf && yi == yf){
			return false;
		}
		if (pieces[xi][yi] == null){
			return false;
		}
//		fire pieces moving down and not king
		if(pieces[xi][yi] != null && pieces[xi][yi].isFire() && pieces[xi][yi].isKing() == false && yi > yf){
			System.out.println("not fire king");
			return false;
		}
//		water pieces moving up and not king
		if (pieces[xi][yi] != null && pieces[xi][yi].isFire() == false && pieces[xi][yi].isKing() == false && yi < yf){
			System.out.println("not water king");
			return false;
		}
//		trying to move more than 2 squares away
		if(Math.abs(xi - xf) > 2 || Math.abs(yi - yf) > 2){
			return false;
		}
//		is there something to capture
		Piece middle = pieces[xf+(xi-xf)/2][yf+(yi-yf)/2];
		if(Math.abs(xi - xf) == 2 || Math.abs(yi - yf) == 2){
			if(middle != null){
				if(fireTurn && middle.side()==0){
					canCapture = true;
					return true;
				} else if (fireTurn == false && middle.side() ==1){
					canCapture = true;
					return true;
				}
			}else{
				return false;
			}
		}
//		
		
		return true;
	}
		
	
	private boolean fireTurn = true;
	private boolean selected = false;
	private boolean selectedPiece = false;
	private int selXPos = 0;
	private int selYPos = 0;
	private boolean moved = false;
	private boolean hasCaptured = false;
	private boolean canCapture = false;

//	private boolean canMove = true;

	
	public boolean canSelect(int x, int y){
		if (x < 0 || x > 7 || y < 0 || y >7){
			return false;
		}
		
		if (hasCaptured == true && validMove(selXPos, selYPos, x, y) == false){
			System.out.println("cannot select");
			return false;
		}
		if (moved && hasCaptured == false){
			System.out.println("cannot select");
			return false;
		}
		if (fireTurn == true){
			if( pieces[x][y] != null && pieces[x][y].isFire()){
				// The player has not selected a piece yet.
				if (selected == false){
					return true;
				// The player has selected a piece, but did not move it.
				} else if (selectedPiece == true && moved == false){
					return true;
				} else {
					return false;
				}
			} else {
				/**During this turn, the player has selected a Piece which hasn’t moved 
				yet and is selecting an empty spot which is a valid move 
				for the previously selected Piece.*/
				if (selectedPiece && moved == false && validMove(selXPos, selYPos, x, y)){
					return true;
				/**During this turn, the player has selected a Piece, captured, and has selected 
				another valid capture destination. When performing multi-captures, you should 
				only select the active piece once; all other selections should be valid destination points.*/
				} else if (selectedPiece && hasCaptured && validMove(selXPos, selYPos, x, y)){
					return true;
				} else if(validMove(selXPos, selYPos, x, y) == false){
					System.out.println("cannot select");
					return false;
				}
				else {
					return false;
				}
			}
		} else {
			if(pieces[x][y] != null && pieces[x][y].isFire() == false){
				// The player has not selected a piece yet.
				if (selected == false){
					return true;
				// The player has selected a piece, but did not move it.
				} else if (selectedPiece == true && moved == false){
					return true;
				} else {
					return false;
				}
			} else {
				// During this turn, the player has selected a Piece which hasn’t moved 
				// yet and is selecting an empty spot which is a valid move 
				// for the previously selected Piece.
				if (selectedPiece && moved == false && validMove(selXPos, selYPos, x, y)){
					return true;
				} else if (selectedPiece && hasCaptured && validMove(selXPos, selYPos, x, y)){
					return true;
				} else if(validMove(selXPos, selYPos, x, y) == false){
					System.out.println("cannot select");
					return false;
				} 
				else {
					return false;
				}
			}
			
		}
		
		
			
	}
	
	


	
		

	// /** Selects the piece at (x,y if possible)
	//   * Recommended to color the background of the selected square white while
	//   	on the GUI via the pen color function
	//   * For any piece to perform a capture, the piece must be selected first
	// */
	private int captures = 0;

	
	public void select(int x, int y){
		if(x < 8 || x >= 0 || y < 8 || y >= 0){
			// if there is a piece
			System.out.println("test is not null");
			if (pieces[x][y] != null){
				// have not selected a piece yet
				System.out.println("is not null");
				System.out.println("trying to select" + x +"and" + y);
				if(selectedPiece == false){
					selected = true;
					selectedPiece = true;
					selXPos = x;
					selYPos = y;
				} else if (selectedPiece == true && moved == false){
					selXPos = x;
					selYPos = y;
				}
				System.out.println("selected" + selXPos +"and" + selYPos);
			// selecting an empty space
			} else {
				System.out.println("test if valid");
				if(validMove(selXPos, selYPos, x, y)){
					System.out.println("is valid");
					// see if can capture anything
					if(canCapture){
						canCapture = false;
						hasCaptured = true;
						captures += 1;
						// perform the action
						System.out.println("valid from" + selXPos +"and" + selYPos);
						System.out.println("valid to" + x +"and" + y);
						pieces[selXPos][selYPos].move(x, y);
						moved = true;
						selXPos = x;
						selYPos = y;
					// move to an empty space
					} else {
						// perform the action
						System.out.println("regular move from" + selXPos + "and" + selYPos);
						System.out.println("regular move to" + x +"and" + y);
						pieces[selXPos][selYPos].move(x, y);
						moved = true;
						selXPos = x;
						selYPos = y;
					}
				} else {
					System.out.println("not valid from" + selXPos +"and" + selYPos);
					System.out.println("not valid to" + x +"and" + y);
					System.out.println("not a valid move");
				}
			}
		}
	}
	
//	public void select(int x, int y){
//		//		inbounds
//				if (x < 8 || x >= 0 || y < 8 || y >= 0){
//		//			have not selected a piece yet
//					if(selectedPiece == false){
//						selected = true;
//						selXPos = x;
//						selYPos = y;
//		
//		//				if you select a spot with a piece
//						if(pieces[x][y] != null){
//							selectedPiece = true;
//						}
//		//			have selected a piece already
//					} else {
//		//				valid move 
//						if(validMove(selXPos, selYPos, x, y)){
//		//					if a capture can be performed
//							if(canCapture){
//								hasCaptured = true;
//							} 
//							moved = true;
//		//					perform action
//							
//							pieces[selXPos][selYPos].move(x, y);
//							selXPos = x;
//							selYPos = y;
//						}
//					}
//				}
//			}
//	


	// * Places p at (x,y)
	//   * If (x,y) is out of bounds, does nothing
	//   * If another piece already exists at (x,y), p will replace that piece
	//   (may be useful for specific test circumstances)
	
	public void place(Piece p, int x, int y){
		if (x < 8 && x >= 0 && y < 8 && y >= 0){	
			placed = true;
			pieces[x][y] = p;

		}
		
	}

	// /** Executes a remove. Returns the piece that was removed
	//   * If the input (x,y) is out of bounds, returns null and prints an 
	//   appropriate message
	//   * If there is no piece at (x,y), returns null and prints an 
	//   appropriate message
	// */
	public Piece remove(int x, int y){
//		System.out.println("used");
		if (x > 7 || x < 0 || y > 7 || y < 0){
			System.out.println("Input (x,y) is out of bounds");
			return null;
		} else if (pieces[x][y] == null){
			System.out.println("There is no piece at (x,y)");
			return null;
		} else {
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
	}

	// /** Returns whether or not the current player can end their turn
	//   * To be able to end a turn, a piece must have moved or performed 
	//   a capture
	// */
//	private boolean ableToEndTurn = false;
	public boolean canEndTurn(){
		if (moved){
			System.out.println("can end turn");
		} else {
			System.out.println("cannot end turn");
		}
		return moved;

	}

	// /** Called at the end of a turn and handles switching players and anything
	// 	else that should happen at the end of a turn.
	// */
	public void endTurn(){
		System.out.println(fireTurn);
		
		if (moved){
			System.out.println("turn ended");
			pieces[selXPos][selYPos].doneCapturing();
			selected = false;
			selectedPiece = false;
			selXPos = 0;
			selYPos = 0;
			moved = false;
			hasCaptured = false;
			canCapture = false;
			captures = 0;
			if (fireTurn == true){
				fireTurn = false;
			} else {
				fireTurn = true;
			}
			
		}
		
		
		
		
	}

	// /** Returns the winner of the game
	//   * 'Fire' 'Water' or 'No One' (tie/no pieces on the board)
	//   * returns null if game is not over yet 
	//   * Assume there is no stalemate situation in which the current player
	//   has pieces but cannot legally move any of them (just leave null in this case)
	//   * Determine the winner soley by the number of pieces belonging to each team 
	// */
	private boolean placed = false;
	private boolean fireAlive = false;
	private boolean waterAlive = false;
	
	public String winner(){
		fireAlive = false;
		waterAlive = false;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null){
            		if (pieces[i][j].isFire()){
                		fireAlive = true;
                	} else {
                		waterAlive = true;
                	}
            	}
            }
		}

		// fresh board
		if (placed == false){
			return "No One";
		} else if (fireAlive == true && waterAlive == false){
			return "Fire";
		} else if (waterAlive == true && fireAlive == false){
			return "Water";
		} else if (fireAlive == false && waterAlive == false){
			return "No One";
//		} else if (fireAlive && waterAlive && placed){
//			return "No One"
		}
		else {
			return null;
		}
}


	private static boolean[][] piecesBoolean;

	// starts a GUI supported version of the game
	public static void main(String [] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        piecesBoolean = new boolean[N][N];
        
        Board b = new Board(false);
//        b.drawBoard(8);
//        Piece pawn = new Piece (true, b, 6, 6, "pawn");
//        b.place(pawn, 6,6);
//        Piece pawn2 = new Piece (false, b, 1, 1, "pawn");
//        b.place(pawn2, 1, 1);
//        Piece pawn3 = new Piece (false, b, 5, 5, "pawn");
//        b.place(pawn3, 4, 4);
//        Piece pawn4 = new Piece (false, b, 5, 7, "pawn");
//        b.place(pawn4, 5, 7);
//        Piece fire = new Piece(true, b, 1, 1, "pawn");
//		Piece water = new Piece(false, b, 3, 3, "pawn");
//		b.place(fire, 1, 1);
//		b.place(water, 3, 3);
//        Board b = new Board(true);
//		Piece king = new Piece(true, b, 3, 7, "pawn");
//		b.place(king, 3, 7);
        
//		Piece king = new Piece(true, b, 2, 6, "pawn");
//		b.place(king, 2, 6);
//		Piece dead = new Piece(false, b, 5, 7, "pawn");
//		Piece dead2 = new Piece(false, b, 4, 4, "pawn");
//		b.place(dead, 5, 7);
//		b.place(dead2, 4, 4);
        
//		Piece dead = new Piece(false, b, 1, 3, "pawn");
//		Piece dead2 = new Piece(true, b, 0, 2, "pawn");
//		Piece dead3 = new Piece(false, b, 3, 5, "pawn");
//		b.place(dead, 1, 3);
//		b.place(dead2, 0, 2);
//		b.place(dead3, 3, 5);
        
        
//  	king multicapture
//		Piece king = new Piece(true, b, 0, 6, "pawn");
//		b.place(king, 0, 6);
//		Piece dead = new Piece(false, b, 0, 0, "shield");
//		Piece dead2 = new Piece(true, b, 1, 1, "shield");
//		Piece notdead = new Piece(false, b, 4, 4, "shield");
//		b.place(dead, 0, 0);
//		b.place(dead2, 1, 1);
//		b.place(notdead, 4, 4);
//		
        
//        multicapture normal
//        Piece king = new Piece(true, b, 0, 2, "pawn");
//		b.place(king, 0, 2);
//		Piece dead = new Piece(false, b, 1, 3, "pawn");
//		Piece dead2 = new Piece(false, b, 3, 5, "pawn");
//		b.place(dead, 1, 3);
//		b.place(dead2, 3, 5);
		
//        Piece thing = new Piece(true, b, 0, 2, "shield");
//        b.place(thing, 0, 2);
//        Piece thing2 = new Piece(false, b, 1, 3, "shield");
//        b.place(thing, 0, 2);
//        b.place(thing2, 1, 3);
//        b.select(0, 2);
//        b.select(2, 4);
//        System.out.println(b.hasCaptured);
//        System.out.println(b.pieceAt(2, 4).hasCaptured());
//        b.endTurn();
//        System.out.println(b.pieceAt(2, 4).hasCaptured());
//        
//        
        
        
        
        

        
        while(true) {
        	b.updateGUI();
        	for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                	if (b.pieces[i][j] != null){
                		if (b.pieces[i][j].isFire()){
                    		if (b.pieces[i][j].isBomb()){
                    			if (b.pieces[i][j].isKing()){
                    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    			} else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    		} else if (b.pieces[i][j].isShield()){
                    			if (b.pieces[i][j].isKing()){
                    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    			} else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    		} else if (b.pieces[i][j] != null){
                    			if (b.pieces[i][j].isKing()){
                    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    			} else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    		}
                    	}else {
                    		if (b.pieces[i][j].isBomb()){
                    			if (b.pieces[i][j].isKing()){
                    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    			} else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    		} else if (b.pieces[i][j].isShield()){
                    			if (b.pieces[i][j].isKing()){
                    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    			} else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    		} else if (b.pieces[i][j] != null){
                    			if (b.pieces[i][j].isKing()){
                    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    			} else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    		}
                    	}
                	}
                	
                }
        	}
//        	if (b.selected == true){
//        		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
//    			StdDrawPlus.filledSquare(b.selXPos + .5, b.selYPos + .5, .5);
//        	} else {
//        		
//        	}
        	/** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */

            if (StdDrawPlus.mousePressed()) {
            	double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
//                piecesBoolean[(int) x][(int) y] = true;
                if (b.canSelect((int) x, (int) y)){
                	 b.select((int) x, (int) y);
                }
          
               
            }          
            if (StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            	
            }
            StdDrawPlus.show(100);
            
        }
		
    }

	private void updateGUI(){
		drawBoard(8);
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null){
            		if (pieces[i][j].isFire()){
                		if (pieces[i][j].isBomb()){
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                		} else if (pieces[i][j].isShield()){
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                		} else if (pieces[i][j] != null){
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			} else
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                	}else {
                		if (pieces[i][j].isBomb()){
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                		} else if (pieces[i][j].isShield()){
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		} else if (pieces[i][j] != null){
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			} else
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}
                	}
            	}
            }
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                // click on board shows this piece below
//                if (piecesBoolean[i][j]) {
//                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
//                }
            }
        }
    }
}

	
