public class Board{
	/*Still need to implement:
	1. Kings
	2. Double jumping to take pieces
	3. special bomb explosions
	4. the only way to change x,y of piece is with piece.move method*/

	//private static boolean[][] pieces;
	private static Piece[][] pieces;
	private static Piece currentpiece;
	private int turn = 0, selectedx, selectedy;
	private boolean moved = false, selected = false;
	private final int N = 8;
	//starts a GUI supported version of the game.
	public static void main(String args[]){
		
		Board b = new Board(false);
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		while(b.winner() == null) {
			drawBoard(N);            
            if (StdDrawPlus.mousePressed()) {
            	double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int) x, (int) y)){
                	b.select((int) x, (int) y);
                }

            }
            if(StdDrawPlus.isSpacePressed()){
            	
            	if(b.canEndTurn()){
            		b.endTurn();	
            	}           	
            }    
            StdDrawPlus.show(100);
        }
	}

	/*The constructor for Board. If shouldBeEmpty is true, initializes an empty Board.
	 Otherwise, initializes a Board with the default configuration. Note that an empty Board 
	 could possibly be useful for testing purposes.*/
	public Board(boolean shouldBeEmpty){
		if(shouldBeEmpty){
			
        	pieces = new Piece[N][N];
        }
		else{
        	pieces = new Piece[N][N];
        	for(int i = 0; i<N; i +=2){
        		pieces[i][0] = new Piece(true, this, i, 0, "pawn");
        		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
        		pieces[i][6] = new Piece(false, this, i, 6, "shield");
        	}
        	for(int i = 1; i<N; i +=2){
        		pieces[i][1] = new Piece(true, this, i, 1, "shield");
        		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
        		pieces[i][7] = new Piece(false, this, i, 7, "pawn");
        	}
		}

	}
	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece piece = pieces[i][j];
                
                if(piece != null){
                	if(currentpiece == pieces[i][j]){
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                	String string = "img/";
        			if(piece.isBomb()){
        				string += "bomb-";
        			}
        			else if(piece.isShield()){
        				string += "shield-";
        			} else{
           				string += "pawn-";
        			}		
        			if(piece.isFire()){
            			string += "fire";
        			} else {
            			string += "water";
        			}
        			if(piece.isKing()){
            			string += "-crowned";
        			}
        			string += ".png";
                	
                	StdDrawPlus.picture(i + .5, j + .5, string, 1, 1);
                }     

            }
        }
    }
   

	//* gets piece at x,y on the board, or null of no piece or x,y out of bounds
	public Piece pieceAt(int x, int y){
		if(x<8 && y<8 && x>=0 && y >=0){
			if(pieces[x][y]!=null){
				return pieces[x][y];		
			}			
		}
		return null;
		}

	/*Returns true if there is a piece the piece at (x, y) and it can be selected.

	A piece may be selected if it is the corresponding player’s turn and one of the following is true:

		The player has not selected a piece yet.
		The player has selected a piece, but did not move it.
	An empty square may be selected if one of the following is true:

		During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
		During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. (You may select as many captures in a row as long as they are all valid and from the same piece.)
	*/
	public boolean canSelect(int x, int y){
		Piece temp = pieceAt(x,y);
		if(moved == false){
			if(temp != null && temp.side() == turn && moved == false){
				return true;
			}
		 	else {
		 		if(currentpiece != null && currentpiece.side() == turn){
					if (validMove(selectedx,selectedy,x,y) && moved == false){
						return true;
					}
					else if(currentpiece.hasCaptured() && validMove(selectedx,selectedy,x,y)){
						return true;
					}
					
				}
				return false;
			}
		}else if(currentpiece != null && currentpiece.hasCaptured()){
				if(validMove(selectedx,selectedy,x,y) && currentpiece.side() == turn){
					return true;
				}
				return false;
		}else{
			return false;
		}			
		
	}
	/*Returns true if the piece at (xi, yi) can either move to (xf, yf) or
	 capture to (xf, yf) in a valid fashion compatible with the rules.
	 */
	private boolean validMove(int xi, int yi, int xf, int yf){
		//checks if space is empty
		if(xf<N && yf<N && xf>=0 && yf>=0){
		if(pieces[xf][yf]==null){
			int dx = xf-xi;
			int dy = yf-yi;
			//checks surrounding diagonals
			if(Math.abs(xi-xf) == 1 && Math.abs(yi-yf) == 1 && moved == false){
				if(turn == 0 && dy > 0){
					return true;
				} else if (turn == 1 && dy < 0){
					return true;
				} else if (currentpiece.isKing()){
					return true;
				}
			}
			//if there is 1 jump
			if(Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2){
				//checks if there is a piece in between and if on the same side
				
				Piece diagpiece = pieces[xi + getDirection(xi, xf)][yi+ getDirection(yi,yf)];
				if(diagpiece !=null && diagpiece.side() != pieces[xi][yi].side()){
					if(turn == 0 && dy > 0){
					return true;
				} else if (turn == 1 && dy < 0){
					return true;
				} else if (currentpiece.isKing()){
					return true;
				}
				}
				
			}
		}
		}
		return false;
	}

	private int getDirection(int ci, int cf){
		if(ci>cf){
			return -1;
		}
		return 1;
	}


		
	
	/*Selects the square at (x, y). This method assumes canSelect (x,y) returns true.
	 Optionally, it is recommended to color the background of the selected square white
	 on the GUI via the pen color function. For any piece to perform a capture, 
	 that piece must have been selected first. If you select a square with a piece, 
	 you are prepping that piece for movement. 
	 If you select an empty square (assuming canSelect returns true), 
	 you should move your most recently selected piece to that square.*/
	public void select(int x, int y){
		Piece temp = pieceAt(x,y);
		//System.out.println("Select Called");
		if(temp != null){
			currentpiece = temp;
			this.place(temp, x, y);
			selectedx = x;
			selectedy = y;
			selected = true;
			//System.out.println("Case1");

		} else if(currentpiece != null){
			currentpiece.move(x,y);
			
			if(currentpiece.isBomb() && currentpiece.hasCaptured()){
				remove(selectedx + getDirection(selectedx, x), selectedy+ getDirection(selectedy,y));
				for(int i = x-1; i<x+2; i++){
					for(int j = y-1; j<y+2; j++){
						if(pieceAt(i, j)!= null){
							if(!pieceAt(i,j).isShield()){
								remove(i, j);
								
							}
						}
					}
				}
				pieces[selectedx][selectedy] = null;
				
				moved = true;
				//System.out.println("Case3");
			}else if(currentpiece.hasCaptured()){
				remove(selectedx + getDirection(selectedx, x), selectedy+ getDirection(selectedy,y));
				place(currentpiece, x, y);
				moved = true;
				//System.out.println("Case4");
			}else {
				place(currentpiece, x, y);
				moved = true;
				//System.out.println("Case5");
			}
			pieces[selectedx][selectedy] = null;
			selectedx = x;
			selectedy = y;
			//System.out.println("Case2");

		}
		//System.out.println("None of the cases");	

		
		
	}
	/*Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	 If another piece already exists at (x, y), p will replace that piece.
	 (This method is potentially useful for creating specific test circumstances.)*/
	public void place(Piece p, int x, int y){
		if(x<8 && y<8 && x>=0 && y>=0){
			pieces[x][y] = p;
		}
	}
	/*Executes a remove. Returns the piece that was removed.
	 If the input (x, y) is out of bounds, returns null and prints an appropriate message.
	  If there is no piece at (x, y), returns null and prints an appropriate message.*/
	public Piece remove(int x, int y){
		if(pieces[x][y] == null){
			System.out.println("no piece at input x:" + x + " y: " + y + " or out of bounds");
			return null;
		}else{
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			//System.out.println("Piece was removed?");
			//System.out.println(pieces[x][y]);
			return removed;
		}
	}
	/*Returns whether or not the the current player can end their turn. 
	  To be able to end a turn, a piece must have moved or performed a capture.*/
	public boolean canEndTurn(){
		//System.out.println("endTurn Case1");
		//System.out.println("Moved: " + moved);
		//System.out.println("hasCaptured: " + currentpiece.hasCaptured());
		if (currentpiece != null) {
			//System.out.println("endTurn Case2");
			if(moved || currentpiece.hasCaptured()){
				//System.out.println("endTurn Case3");
				return true;
			}
		}
		//System.out.println("endTurn Case4");
		return false;
	}
	/*Called at the end of each turn. 
	Handles switching of players and anything else that should happen at the end of a turn.*/
	public void endTurn(){
		if(currentpiece != null){
			currentpiece.doneCapturing();
			currentpiece=null;
		}

		turn = 1 - turn;
		moved = false;
		selected= false;
		selectedx = -1;
		selectedy = -1;
				
	}
	/*Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), 
	or null if the game is not yet over. 
	Assume there is no stalemate situation in which the current player has 
	pieces but cannot legally move any of them (In this event, just leave it at null). 
	Determine the winner solely by the number of pieces belonging to each team.*/
	public String winner(){
		//check number of fire and water pieces on the board
		
		int firepieces = 0;
		int waterpieces = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if(pieceAt(i, j) != null){
            		if(pieceAt(i,j).isFire()){
            			firepieces += 1;
            		} else{
            			waterpieces +=1;
            		}
            	}
            }
        }

        if(waterpieces + firepieces == 0){
        	return "No one";
        } else if (waterpieces == 0 && firepieces >0){
        	return "Fire";
        } else if (firepieces == 0 && waterpieces >0){
        	return "Water";
        } else{
        	return null;
        }

		
	} 
}