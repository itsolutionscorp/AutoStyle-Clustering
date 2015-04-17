import org.junit.Test;
import static org.junit.Assert.*;


public class Board {

	private boolean[][] boolPieces = new boolean [8][8];
	private Piece[][] pieces = new Piece [8][8];
	private boolean[][] boolSelected = new boolean [8][8];
	private int turn = 1; 
	private boolean hasMoved = false;
	private  boolean pieceSelected = false;
	private  Piece selectedPiece;
	private int numFireCaptured = 0;
	private int numWaterCaptured = 0;
	private int selectedX;
	private int selectedY;

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
       	StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        b.turn = 1;
        b.startGame(b);
    }

    public Board(boolean shouldBeEmpty) {
    	int N = 8;
    	// for(int i = 0; i<8; i++) {
    	// 	for(int j = 0; j<8; j++) {
    	// 		if (boolSelected[i][j]!=true) {
    	// 			boolSelected[i][j] = false;
    	// 		} 
    	// 	}
    	// }
        
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        if (shouldBeEmpty == true) {
        	drawBoard(8);
        } else {
        	drawBoard(8);	
        	for(int i=0; i < 8; i++) {
        		for (int j=0; j < 8; j++) {
        			if ((i + j) % 2 == 0) {
        				if (j == 0) {
        					boolPieces[i][j]=true;
        					place(new Piece(true, this, i, j, "pawn"),i,j);
        					//drawPiece(pieces[i][j], i , j);
        				} else if (j == 1) {
        					boolPieces[i][j]=true;
        					place(new Piece(true, this, i, j, "shield"),i,j);
        					//drawPiece(pieces[i][j], i , j);
        				} else if (j == 2) {
        					boolPieces[i][j]=true;
        					place(new Piece(true, this, i, j, "bomb"),i,j);
        					//drawPiece(pieces[i][j], i , j);
        				} else if (j == 7) {
        					boolPieces[i][j]=true;
        					place(new Piece(false, this, i, j, "pawn"),i,j);
        					//drawPiece(pieces[i][j], i , j);
        				} else if (j == 6) {
        					boolPieces[i][j]=true;
        					place(new Piece(false, this, i, j, "shield"),i,j);
        					//drawPiece(pieces[i][j], i , j);
        				} else if (j == 5) {
        					boolPieces[i][j]=true;
        					place(new Piece(false, this, i, j, "bomb"),i,j);
        					//drawPiece(pieces[i][j], i , j);
        				}
        			}
        		}
        	}
        }
    }

    public Piece pieceAt(int x, int y) {
    	/*Gets the piece at position (x, y) on the 
		*board, or null if there is no piece. If (x, y) are 
		*out of bounds, returns null. */
    	return pieces[x][y];
    }

	public boolean canSelect(int x, int y) {
		//returns true if square can be selected.
		if (hasMoved == true) {
			return false;
		}
		if (pieceSelected == false) {
			if (validPieceSelection(x,y))  {
				return true;
			} else {
				return false;
			}
		// } else if (selectedPiece == pieceAt(x,y)) {
		// 	deSelect(x, y);
		// 	return false;
		}else if (selectMakesValidMove(x,y)) {
			return true;
		} else if (hasMoved == false && selectedPiece.hasCaptured()==false && pieceAt(x,y).side() == turn){
			return true;
		}else{
			return false;
		}
	}
	// public void highlightSelection(int x, int y) {
	// 	if (hasPiece(x,y)) {
	// 		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
 //        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
 //        	drawPiece(pieceAt(x,y),x,y);
	// 	} else {
	// 		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
 //        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	// 	}
	// }

	// public void deSelect(int x, int y) {
	// 	pieceSelected = false;
	// 	boolSelected = new boolean[8][8];
	// 	selectedPiece = null;
	// }

	public void select(int x, int y) {
		if (pieceSelected == false) {
			selectedX = x;
			selectedY = y;
			selectedPiece = pieceAt(x,y);
			pieceSelected = true;
		} else {
			if (hasPiece(x,y) == false && validCapture(selectedPiece,x,y)==false) {
				selectedPiece.move(x,y);
				hasMoved = true;
				selectedX = x;
				selectedY = y;
			} else if (validCapture(selectedPiece,x,y)) {
				selectedPiece.move(x,y);
				selectedX = x;
				selectedY = y;
	
				if (selectedPiece.isBomb()) {
					afterMath(selectedPiece,x,y);
				}

			} else if (hasMoved == false && selectedPiece.hasCaptured()==false && pieceAt(x,y).side() == turn) {
				selectedX = x;
				selectedY = y;
				selectedPiece = pieceAt(x,y);
			}
		}
	}

    public void place(Piece p, int x, int y) {
    	if (((x > 8) && (y > 8)) || (p == null)) {
    	} else {
    		placeInBoardArrays(p,x,y);
    		drawPiece(p,x,y);
    	}
    }
	
	private String typer(int x, int y) {
		if (pieceAt(x,y).isBomb()) {
			return "bomb";
		} else if (pieceAt(x,y).isShield()) {
			return "shield";
		}else {
			return "pawn";
		}
	}

	public Piece remove(int x, int y) {
		//Executes a remove
		Piece save = new Piece(pieceAt(x,y).isFire(),this, x, y, typer(x,y));
		removePiecePicture(x,y);
		removePieceFromArrays(x,y);
		return save;
	}

	public boolean canEndTurn() {
		/*Returns whether or not the the current player 
		*can end their turn.*/
		if (selectedPiece==null) {
			return false;
		}
		if (hasMoved || (selectedPiece.hasCaptured())) {
			// if (canStillCapture(selectedPiece))  
					return true;
				} else {
					return false;
				}
			// } else {
			// 	while (StdDrawPlus.isSpacePressed() != true) {

			// 	}
			// 	return true;
			// }
			// } else {
			// 	return false;
			// }
	}

	public void endTurn() {
		/*Called at the end of each turn. Handles switching
		 of players and anything else that should happen at 
		 the end of a turn.*/
		 nextTurn();
		 selectedX = 10;
		 selectedY = 10;
		 // boolSelected = new boolean[8][8];
		 selectedPiece.doneCapturing();
		 hasMoved = false; 
		 pieceSelected = false;
		 selectedPiece = null;
	}
	
	public String winner() {
		//Returns the winner of the game
		if (numOnBoard(1)==0) {
			return "Water";
		}else if (numOnBoard(0)==0) {
			return "Fire";
		}else {
			return null;
		}
	}




















	private void afterMath(Piece p, int bombx, int bomby) {
		for(int i = bombx-1; i <= bombx+1; i++) {
			for(int j = bomby-1; j <= bomby+1; j++) {
				if ((hasPiece(i,j)) && (pieceAt(i,j).isShield()==false)) {
					remove(i,j);
				} 
			}
		}
		place(p,bombx,bomby);
		remove(bombx,bomby);
	}


	private void nextTurn(){
		if (turn == 1) {
			turn = 0;
		} else {
			turn = 1;
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	if (selectedX ==i && selectedY ==j) {
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	} else {
                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	}
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	if (boolPieces[i][j]) {
                    drawPiece(pieceAt(i,j),i, j);
                }
            }
        }
    }
    private void drawPiece(Piece p, int i, int j) {
    	if (p.isKing()) {
	    	if (p.isFire()) {
	    		if (p.isShield()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	    		} else if (p.isBomb()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	    		}else {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	    		}
	    	} else {
	    		if (p.isShield()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	    		} else if (p.isBomb()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	    		}else {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	    		}
	    	}
    	} else {
			if (p.isFire()) {
	    		if (p.isShield()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	    		} else if (p.isBomb()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	    		}else {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	    		}
	    	} else {
	    		if (p.isShield()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	    		} else if (p.isBomb()) {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	    		}else {
	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	    		}
	    	}
    	}	
    	
    }

 
    private void placeInBoardArrays(Piece p, int x, int y) {
    	pieces[x][y] = p;
    	boolPieces[x][y]= true; 
    }
    // private Piece setPieceHome(Piece p, int oldx, int oldy, int newx, int newy) {
    // 	return new Piece(p.isFire(), this, newx, newy, typer(selectedX,selectedY));
    // }
    


    private boolean hasPiece(int x, int y) {
    	if (boolPieces[x][y] == true) {
    		return true;
       	} else {
       		return false;
       	}
    }
    private String startGame(Board b){
    	while(b.winner()==null) {
            while(StdDrawPlus.isSpacePressed()==false) {
            	if (StdDrawPlus.mousePressed()) {
                	double x = StdDrawPlus.mouseX();
               		double y = StdDrawPlus.mouseY();	
            		if (canSelect((int) x,(int) y)){
            			b.select((int) x, (int) y);
            		}
            	}
            	b.drawBoard(8);
            	StdDrawPlus.show(100);
            }
            if (b.canEndTurn()) {
            	b.endTurn();
            }
        }
        return b.winner();
    }


    		
            // StdDrawPlus.show(100);

    private boolean validTraverse(Piece p, int x, int y) {
    	if (p.isKing()) {
    		if ((((selectedX-x) == 1) || ((selectedX-x)==(-1))) && (((selectedY-y)==1) || ((selectedY-y) ==(-1))) && (y < 8) && (x < 8) && (y >= 0) && (x >= 0) && (hasPiece(x,y)!=true)){
    			return true;
    		} else {
    			return false;
    		}
    	} else if (p.isFire()) {
    		if ((((selectedX-x) == 1) || ((selectedX-x)==(-1))) && ((selectedY-y) ==(-1)) && (y < 8) && (x < 8) && (y >= 0) && (x >= 0) && (hasPiece(x,y)!=true)){
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		if ((((selectedX-x) == 1) || ((selectedX-x)==(-1))) && ((selectedY-y) ==(1)) && (y < 8) && (x < 8) && (y >= 0) && (x >= 0) && (hasPiece(x,y)!=true)){
    			return true;
    		} else {
    			return false;
    		}
    	}
    		
    }

    private Piece pieceToCapture(Piece p, int x, int y) {
    	if (p.isKing()) {
	    	if ((selectedX - x == 2) && (selectedY-y==-2) && (x>=0) && (x<8) && hasPiece(x+1, y-1) && pieceAt(x+1, y-1).isFire()!=p.isFire()) {
	    		return pieceAt(x+1, y-1);
	    	} else if ((selectedX-x ==- 2) && (selectedY -y ==-2) && (x>=0) && (x<8) && hasPiece(x-1, y-1) && pieceAt(x-1,y-1).isFire()!=p.isFire()) {
	    		return pieceAt(x-1,y-1);
	    	} else if ((selectedX-x == 2) && (selectedY -y == 2) && (x>=0) && (x<8) && hasPiece(x+1, y+1) && pieceAt(x+1, y+1).isFire()!=p.isFire()) {
	    		return pieceAt(x+1, y+1);
	    	} else if ((selectedX-x == -2) && (selectedY -y == 2) && (x>=0) && (x<8) && hasPiece(x-1, y+1) && pieceAt(x-1, y+1).isFire()!=p.isFire()) {
	    		return pieceAt(x-1,y+1);
	    	} else {
	    		return null;
	    	}
	    }else if (p.isFire()) {
	    	if ((selectedX-x == 2) && (selectedY-y ==-2) && (x>=0) && (x<8) && hasPiece(x+1, y-1) && pieceAt(x+1, y-1).isFire() != p.isFire()) {
	    		return pieceAt(x+1, y-1);
	    	} else if ((selectedX-x ==- 2) && (selectedY-y ==-2) && (x>=0) && (x<8) && hasPiece(x-1, y-1) && pieceAt(x-1, y-1).isFire() != p.isFire()) {
	    		return pieceAt(x-1,y-1);
	    	} else {
	    		return null;
	    	}
		} else {
			if ((selectedX-x == 2) && (selectedY-y ==2) && (x>=0) && (x<8) && hasPiece(x+1, y+1) && pieceAt(x+1, y+1).isFire() != p.isFire()) {
	    		return pieceAt(x+1, y+1);
	    	} else if ((selectedX-x ==- 2) && (selectedY-y ==2) && (x>=0) && (x<8) && hasPiece(x-1, y+1) && pieceAt(x-1, y+1).isFire() != p.isFire()) {
	    		return pieceAt(x-1,y+1);
	    	} else {
	    		return null;
	    	}
		}
	}

    private boolean validCapture(Piece p, int x, int y) {
		if (pieceToCapture(p,x,y) != null) {
			return true;
		} else {
			return false;
		}
    }

    private boolean validPieceSelection(int x, int y) {
    	if (hasPiece(x,y) && (pieceAt(x,y).side() == turn)){
    		return true;
    	} else {
    		return false;
    	}
    }

    private boolean selectMakesValidMove(int selectX, int selectY) {
    	if (validTraverse(selectedPiece, selectX, selectY) || (validCapture(selectedPiece, selectX, selectY))) {
    		return true;
    	} else {
    		return false;
    	}
    }




	// private void select(int x, int y) {
	// 	if (canEndTurn()==false) {
	// 		if (canSelect(x,y)) {
	// 			if (pieceSelected) {
	// 				boolSelected = new boolean[8][8];
	// 				boolSelected[x][y] = true;
	// 				selectedPiece.move(x,y);
	// 				hasMoved = true; 
	// 			} else {
	// 				selectedPiece = pieceAt(x,y);
	// 				pieceSelected = true;
	// 				boolSelected[x][y] = true;
	// 			}
	// 		}
	// 	}
	// }

	private void removePiecePicture(int x, int y) {
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	}

	private void removePieceFromArrays(int x, int y) {
		pieces[x][y] = null;
		boolPieces[x][y] = false;
		// if (pieceAt(x,y) == selectedPiece) {
		// 	boolSelected = new boolean[8][8];
		// }
	}





	private boolean canStillCapture(Piece p) {
		if (validCapture(p, selectedX+2, selectedY+2) || validCapture(p, selectedX-2, selectedY+2) || validCapture(p, selectedX+2, selectedY-2) || validCapture(p, selectedX+2, selectedY-2)) {
			return true;
		} else {
			return false;
		}
	}

	private int numOnBoard(int oneIsFire) {
		int num = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (hasPiece(i,j) && (oneIsFire==pieceAt(i,j).side())) {
            		num++;
            	}
			}
		}
		return num;
	}


	






/////////////////////     JUNIT TESTS        /////////////////////////////////////////////////
	

	public static class BoardTester {

	@Test
	public void testNextTurn() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board t = new Board(false);
		Piece newPiece =  new Piece(true,t,0,0,"pawn");
		t.place(newPiece, 5, 5);
		assertEquals(1, t.turn);
		t.nextTurn();
		assertEquals(0, t.turn);
		t.nextTurn();
		assertEquals(1,t.turn);
	}


	@Test
	public void testPieceAt() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board t = new Board(false);
		Piece newPiece =  new Piece(true,t,0,0,"pawn");
		t.place(newPiece, 5, 5);
		assertEquals(newPiece, t.pieceAt(5,5));
		assertEquals(null, t.pieceAt(5,6));
	}
	@Test
	public void testPlace() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board v = new Board(false);
		Piece newPiece =  new Piece(true,v,0,0,"pawn");
		v.place(newPiece, 5, 5);
		assertEquals(newPiece, v.pieceAt(5,5));
		assertEquals(5, v.selectedX);
		assertEquals(5, v.selectedY);
	}
	@Test
	public void testHasPiece() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece newPiece =  new Piece(true,b,0,0,"pawn");
		b.place(newPiece,0, 0);
		assertEquals(true,b.hasPiece(0,0));
	}

	@Test
	public void testRemove() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board w = new Board(false);
		Piece newPiece =  new Piece(true,w,0,0,"pawn");
		w.remove(0,0);
		assertEquals(null, w.pieceAt(0,0));
	}

	@Test 
	public void testSelect() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece firePiece =  new Piece(true,b,0,0,"pawn");
		Piece waterPiece =  new Piece(false,b,1,1,"pawn");
		b.place(firePiece,0,0);
		b.place(waterPiece,1,1);
		b.select(0,0);
		assertEquals(true,b.pieceSelected);
		assertEquals(firePiece,b.selectedPiece);
		assertEquals(false,b.canSelect(0,0));
		assertEquals(false,b.canSelect(1,1));
		assertEquals(true,b.canSelect(2,2));
		b.select(2,2);
		assertEquals(firePiece,b.pieceAt(2,2));
	}

	@Test
	public void testValidPieceSelection() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece firePiece =  new Piece(true,b,0,0,"pawn");
		Piece waterPiece =  new Piece(false,b,1,1,"pawn");
		b.place(firePiece,0,0);
		b.place(waterPiece,1,1);
		assertEquals(true, b.validPieceSelection(0,0));
		assertEquals(false, b.validPieceSelection(1,1));
		assertEquals(false, b.validPieceSelection(2,2));
	}
	@Test 
	public void testValidTraverse(){
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece firePiece =  new Piece(true,b,0,0,"pawn");
		b.place(firePiece,0,0);
		b.selectedPiece = b.pieceAt(0,0);
		assertEquals(true, b.validTraverse(b.selectedPiece, 1,1));
		assertEquals(false, b.validTraverse(b.selectedPiece,1,2));
		assertEquals(false, b.validTraverse(b.selectedPiece,2,2));
		assertEquals(false, b.validTraverse(b.selectedPiece,-1,0));
	}
	@Test
	public void testPieceToCapture() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece firePiece =  new Piece(true,b,0,0,"pawn");
		Piece waterPiece =  new Piece(false,b,1,1,"pawn");
		b.place(firePiece,0,0);
		b.place(waterPiece,1,1);
		b.selectedPiece = firePiece;
		assertEquals(null,b.pieceToCapture(b.selectedPiece,1,1));
		assertEquals(null,b.pieceToCapture(b.selectedPiece,2,1));
		assertEquals(b.pieceAt(1,1),b.pieceToCapture(b.selectedPiece,2,2));
	}
	@Test
	public void testValidCapture() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece firePiece =  new Piece(true,b,0,0,"pawn");
		Piece waterPiece =  new Piece(false,b,1,1,"pawn");
		b.place(firePiece,0,0);
		b.place(waterPiece,1,1);
		b.selectedPiece = firePiece;
		assertEquals(false, b.validCapture(b.selectedPiece, 1, 2));
		assertEquals(false, b.validCapture(b.selectedPiece, 0, 0));
		assertEquals(false, b.validCapture(b.selectedPiece, 1,1));
		assertEquals(true, b.validCapture(b.selectedPiece, 2,2));
	}

	@Test
	public void testSelectMakesValidMove() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece firePiece =  new Piece(true,b,0,0,"pawn");
		Piece waterPiece =  new Piece(false,b,1,1,"pawn");
		b.place(firePiece,0,0);
		b.selectedPiece = b.pieceAt(0,0); 
		assertEquals(true, b.selectMakesValidMove(1,1));
		b.place(waterPiece,1,1);
		assertEquals(true, b.selectMakesValidMove(2,2));
	}

	@Test
	public void testCanSelect() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece firePiece =  new Piece(true,b,0,0,"pawn");
		Piece waterPiece =  new Piece(false,b,1,1,"pawn");
		b.place(firePiece,0,0);
		b.place(waterPiece,1,1);
		assertEquals(true,b.canSelect(0,0));
		assertEquals(false,b.canSelect(1,1));
		assertEquals(false,b.canSelect(2,2));
		// b.select(0,0); //should trigger a change to second select
		// assertEquals(true, b.canSelect(2,2));
 	}

        public static void runTests() {
            jh61b.junit.textui.runClasses(BoardTester.class);
        }
    }
}