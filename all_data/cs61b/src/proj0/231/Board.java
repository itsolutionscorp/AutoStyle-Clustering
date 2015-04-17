public class Board {
	private static Piece[][] pieces = new Piece[8][8];
	private static boolean shouldBeEmpty;
	private Piece selectedPiece;
	private Piece kingPiece;
	private Piece removedPiece;
	private int turn=0;
	private boolean moved = false;


	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board newBoard = new Board(shouldBeEmpty);
		while(true) {
		    if (StdDrawPlus.mousePressed()) {
		    	int x = (int)StdDrawPlus.mouseX();
		    	int y = (int)StdDrawPlus.mouseY();
		            
		    	/*if ((newBoard.selected) && (newBoard.canSelect(x, y))) {
		      		newBoard.selectedPiece.move(x, y);
		      		newBoard.moved = true;
		      		System.out.println("Moved Succesful");*/

		      	
		    	newBoard.select(x , y);
				

						    	

		      /*if (newBoard.selectedPiece.isFire() && newBoard.selectedPiece.y == 7) {
		      	Piece kingPiece = new Piece(true, newBoard, x, y, "king" );
		      }	
		      else {
		        Piece kingPiece = new Piece(true, newBoard, x, y, "king");
		      }*/
		    }
          
          if ((StdDrawPlus.isSpacePressed()) && (newBoard.canEndTurn())) {
		  	  newBoard.endTurn();
		  	  newBoard.winner();
		  }
		                
		  StdDrawPlus.show(100);
		}
	}




            
	       
    
	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
			drawBoard(8); 
		}
		

	

	public Piece pieceAt(int x, int y) {
		if ((x >= 7) || (y>=7) || (x <= 0) || (y <= 0)) 
			return null;
		else if(pieces[x][y] == null)
			return null;
 		else
			return pieces[x][y];
	}
		

	public boolean canSelect(int x, int y) {
		
		if ((moved == false) && (pieces[x][y] == null)) {
			
			return validMove(x, y);

		}
		else if ((pieces[x][y] != null) && (moved == false)) {
			if (pieces[x][y] == null) {
				return false;
			}	
			if ((x + y) % 2 == 1)
				return false;

			if (turn % 2 == 0) {
				if (pieces[x][y].isFire()) {
					System.out.println("returnig true");
					return true;
				}
				else
					return false;
				
			}
			else { 
				if (!pieces[x][y].isFire()) {
					return true;
				}
				else
					return false;
			}

		}	
		else
			return false;


	}
	
	private boolean validMove(int x, int y) {
		if (pieces[x][y]!= null) {
			return false;
		}

		if ((x + y) % 2 == 1) {
    		return false;
		}
		
	
		else if (selectedPiece.isKing() && (pieces[x][y] == null)) {
			if (((x-1) >= 0) && ((y-1) >= 0)) 
				if ((pieces[x-1][y-1] == selectedPiece)) 
				
				return true;

			if (((x+1) <= 7) && ((y+1) <= 7)) 
				if (pieces[x+1][y-1] == selectedPiece)
				
			
				return true;

			if (((x-1) >= 0) && ((y+1) <= 7)) 
				if (pieces[x-1][y+1] == selectedPiece) 
					
				
				return true;

			if 	(((x+1) <= 7) && ((y-1) >= 0)) 
				if (pieces[x+1][y-1] == selectedPiece)
				
				return true;		

		}	

		else if (!selectedPiece.isFire() && (pieces[x][y] == null)) {
			if (((x+1) <= 7) && ((y+1) <= 7)) 
				if (pieces[x+1][y+1] == selectedPiece) 
			
				
				return true;
			if (((x-1) >= 0) && ((y+1) <= 7)) 
				if (pieces[x-1][y+1] == selectedPiece) 
			
				
				return true;

		}

		else if (selectedPiece.isFire() && (pieces[x][y] == null)) {
			if (((x-1) >= 0) && ((y-1) >= 0)) 
				if (pieces[x-1][y-1] == selectedPiece) 
			
				
				return true;
			if (((x+1) <= 7) && ((y-1) >= 0)) 
				if (pieces[x+1][y-1] == selectedPiece) 
				
				
				return true;
		
		}
		return false;
		}

		
	 

	public void select(int x, int y) {
		if ((pieces[x][y]!=null) && (canSelect(x, y))) {
			selectedPiece = pieces[x][y];
			System.out.println("Selection Successful");
		}
		else if (canSelect(x, y) && (pieces[x][y] == null)) {
			selectedPiece.move(x, y);
			moved = true;
		
		}
	}

	public void place(Piece p, int x, int y) {
		if ((x >= 7) || (y>=7) || (x <= 0) || (y <= 0) || (p==null))
			return;
		else
			pieces[x][y] = p;
		this.moved = true;
	}

	public Piece remove(int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			System.out.println("Out of Bounds");
			return null;
		}	
		if (pieces[x][y] == null) {
			System.out.println("No Piece to Remove");
			return null;
		}
		removedPiece = pieces[x][y];
		pieces[x][y] = null;
		return removedPiece;
	}

	public boolean canEndTurn() {
		if (moved) 
			return true;
		else if (selectedPiece.hasCaptured())
			return true;
		else
			return false;
	}

	public void endTurn() {
		turn= turn+1;
		moved = false;
		System.out.println(turn);
	}

	public String winner() {
		int firestore =0;
		int waterstore =0;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if (pieces[x][y].isFire())
					firestore++;
				if (!pieces[x][y].isFire())
					waterstore++;
		}
			}
		if ((waterstore ==1) && (firestore ==0)) 
			return "Water";
		else if ((waterstore ==0) && (firestore ==1))
			return "Fire"; 
		else if ((waterstore ==0) && (firestore ==0))
			return "No one";
		else
			return null;
		
	}

	
	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                 
					StdDrawPlus.setPenColor(StdDrawPlus.RED);

				StdDrawPlus.filledSquare(i +.5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				if(!shouldBeEmpty && (i + j) % 2 == 0) {

					switch(j) {

						case 0:
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
							break;
						case 1:
							pieces[i][j] = new Piece(true, this, i, j, "shield");
							break;
						case 2:
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
							break;
						case 5:
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
							break;
						case 6:
							pieces[i][j] = new Piece(false, this, i, j, "shield");
							break; 
						case 7:
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
							break;
						default:
							break;
					}
				} 
			}
		}	
	}


		private void fireBoom(int x,int y) {
			pieces[x][y] = null;

			if ((!pieces[x-1][y].isFire()) && (!pieces[x-1][y].isShield())) {
				pieces[x-1][y] = null;
			}	
			else if ((!pieces[x+1][y].isFire()) && (!pieces[x+1][y].isShield())) {
				pieces[x+1][y] = null;
			}	
			else if ((!pieces[x-1][y+1].isFire()) && (!pieces[x-1][y+1].isShield())) {								
				pieces[x-1][y+1] = null;
			}	
			else if ((!pieces[x][y+1].isFire()) && (!pieces[x][y+1].isShield())) {
				pieces[x][y+1] = null;
			}	
			else if ((!pieces[x+1][y+1].isFire()) && (!pieces[x+1][y+1].isShield())) {
				pieces[x+1][y+1] = null;
			}	
			else if ((!pieces[x-1][y-1].isFire()) && (!pieces[x-1][y-1].isShield())) {
				pieces[x-1][y-1] = null;
			}	
			else if ((!pieces[x][y-1].isFire()) && (!pieces[x][y-1].isShield())) {
				pieces[x][y-1] = null;
			}	
			else if ((!pieces[x+1][y-1].isFire()) && (!pieces[x+1][y-1].isShield())) {
				pieces[x+1][y-1] = null;
			}	
			else {
				return;
			}
		}												

		private void waterBoom(int x, int y) {
			pieces[x][y] = null;

			if ((pieces[x-1][y].isFire()) && (!pieces[x-1][y].isShield())) {
				pieces[x-1][y] = null;
			}
			else if ((pieces[x+1][y].isFire()) && (!pieces[x+1][y].isShield())) {
				pieces[x+1][y] = null;
			}
			else if ((pieces[x-1][y+1].isFire()) && (!pieces[x-1][y+1].isShield())) {								
				pieces[x-1][y+1] = null;
			}
			else if ((pieces[x][y+1].isFire()) && (!pieces[x][y+1].isShield())) {
				pieces[x][y+1] = null;
			}	
			else if ((pieces[x+1][y+1].isFire()) && (!pieces[x+1][y+1].isShield())) {
				pieces[x+1][y+1] = null;
			}	
			else if ((pieces[x-1][y-1].isFire()) && (!pieces[x-1][y-1].isShield())) {
				pieces[x-1][y-1] = null;
			}	
			else if ((pieces[x][y-1].isFire()) && (!pieces[x][y-1].isShield())) {
				pieces[x][y-1] = null;
			}	
			else if ((pieces[x+1][y-1].isFire()) && (!pieces[x+1][y-1].isShield())) {
				pieces[x+1][y-1] = null;
			}	
			else {
				return;
			}	
		}
}	
