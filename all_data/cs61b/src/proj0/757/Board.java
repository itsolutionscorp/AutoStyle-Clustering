public class Board {

	private Piece[][] pieces;
	private boolean b;
	private int player = 1;
	private int[] current;
	private boolean moved = false;
	private Piece selected = null;
	private Piece canselect;
	private Piece capturing;
	private Board board;
	private Piece selectpiece;


	private void drawBoard(int N) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
                else{                  StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if ((i + j) % 2 == 0) {
                	if (pieces[i][j] != null) {
	                    if (pieces[i][j].isFire() == true && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false && pieces[i][j].isKing() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	              			
	                    }
	                    else if (pieces[i][j].isFire() == true && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false && pieces[i][j].isKing() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                    }

	                    else if (pieces[i][j].isFire() == true && pieces[i][j].isShield() == true && pieces[i][j].isKing() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                 		
	                    }
	                    else if (pieces[i][j].isFire() == true && pieces[i][j].isShield() == true && pieces[i][j].isKing() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                 		
	                    }
	                    else if (pieces[i][j].isFire() == true && pieces[i][j].isBomb() == true && pieces[i][j].isKing() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);

	                    }
	                    else if (pieces[i][j].isFire() == true && pieces[i][j].isBomb() == true && pieces[i][j].isKing() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);

	                    }
	                    else if (pieces[i][j].isFire() == false && pieces[i][j].isBomb() == true && pieces[i][j].isKing() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                  		
	                    }
	                    else if (pieces[i][j].isFire() == false && pieces[i][j].isBomb() == true && pieces[i][j].isKing() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                  		
	                    }
	                    else if (pieces[i][j].isFire() == false && pieces[i][j].isShield() == true && pieces[i][j].isKing() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	         	
	                    }
	                    else if (pieces[i][j].isFire() == false && pieces[i][j].isShield() == true && pieces[i][j].isKing() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	         	
	                    }
	                    else if (pieces[i][j].isFire() == false && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false && pieces[i][j].isKing() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);

	                    }
	                    else if (pieces[i][j].isFire() == false && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false && pieces[i][j].isKing() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);

	                    }
	                }
                }
            }
        }
    }
	
	public static void main(String[] args) {
	        StdDrawPlus.setXscale(0, 8);
	        StdDrawPlus.setYscale(0, 8);
	        int N = 8;
	        boolean hi;
	        Board board = new Board(false);
	        board.drawBoard(N);
			while(true) {
				
				if (StdDrawPlus.mousePressed()) {
	                double x = StdDrawPlus.mouseX();
	                double y = StdDrawPlus.mouseY();
	                hi = board.canSelect((int) x, (int) y);
	                if (hi){
	                	board.select((int) x, (int) y); 
	                	board.drawBoard(N);
	                }
	            }
	            if (StdDrawPlus.isSpacePressed()) {
	            	if (board.canEndTurn() == true) {
	            		board.endTurn();
	            	}
	            	
	            }
	            StdDrawPlus.show(100);

	        }              

    }
    public Board(boolean shouldBeEmpty) {
    	b = shouldBeEmpty;
    		int N = 8;
    		current = new int[2];
    		pieces = new Piece[N][N];
    		if (shouldBeEmpty == false){
	        	for (int i = 0; i < N; i++) {
	            	for (int j = 0; j < N; j++) {
		                if ((i + j) % 2 == 0) {
		                    if (j == 0 && (i + j) % 2 == 0) {
		                    
		              			pieces[i][j] = new Piece(true, this, i, j, "pawn");
		                    }
		                    else if (j == 1 && (i + j) % 2 == 0) {
		          
		                 		pieces[i][j] = new Piece(true, this, i, j, "shield");
		                    }
		                    else if (j == 2 && (i + j) % 2 == 0) {
		        
		              			pieces[i][j] = new Piece(true, this, i, j, "bomb");
		                    }
		                    else if (j == 5 && (i + j) % 2 == 0) {
		                    	
		                  		pieces[i][j] = new Piece(false, this, i, j, "bomb");
		                    }
		                    else if (j == 6 && (i + j) % 2 == 0) {
		                    
		         				pieces[i][j] = new Piece(false, this, i, j, "shield");
		                    }
		                    else if (j == 7 && (i + j) % 2 == 0) {
		                    	
		            			pieces[i][j] = new Piece(false, this, i, j, "pawn");
		                    }
		                }
		            }
		        }
		    }
		
    }

	public Piece pieceAt(int x, int y) {
		if ((x > 8) || (y > 8) || (x < 0) || (y < 0)){
			return null;
		}
		else if (pieces[x][y] == null){
			return null;
		}
		else if (pieces[x][y] != null){
			return pieces[x][y];
		}
		else {
			return null;
		}
	}

	public void place(Piece p, int x, int y){
		if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0) || p == null){
		}
		else if (p != null){
			pieces[x][y] = p;
		}

	}

	private boolean validMove(Piece selected, int xi, int yi, int xf, int yf){
		if (player == 1){ //can only move up
		 	if (selected.isKing() == false){
		 		if (Math.abs(xf - xi) == 1 && yf - yi == 1){
					return true;
				}
				else{
					return false;
				}
		}
			else if (selected.isKing() == true){
				if (Math.abs(yi - yf) == 1 && Math.abs(xf - xi) == 1){
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else if (player == -1){
			if (selected.isKing() == false){ //can only move down
		 		if (Math.abs(xf - xi) == 1 && yi - yf == 1){
					return true;
				}
				else {
					return false;
				}
		}
			else if (selected.isKing() == true){
				if (Math.abs(yi - yf) == 1 && Math.abs(xf -xi) == 1){					
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	
	}

	public boolean canSelect(int x, int y) {
		canselect = pieces[x][y];
		if (player == 1){ //can only move fire pieces
			//there is a piece at pieces[x][y] and its a fire piece
			if (canselect != null && canselect.isFire() == true && moved == false){
				if (selected != null && selected.hasCaptured() == false){
					return true;
				}
				else if (selected != null && selected.hasCaptured() == true){
					return false;
				}
				else {				
					return true;
				}
			}
			else if (canselect != null && canselect.isFire() == false) {
				return false; // cant select nonfire piece
			}
			//empty space but you have a piece selected && valid move
			else if (canselect == null && selected != null && validMove(selected, current[0], current[1], x, y) == true && moved == false){
				return true;
			}

			//empty space but you have a piece selected - check if can capture
			else if (canselect == null && selected != null && validMove(selected, current[0], current[1], x, y) == false){				//if there is a piece in between that you can capture called capturing
				int xi = ((current[0] + x) / 2);
				int yi = ((current[1] + y) / 2);
				capturing = pieces[xi][yi];
				//piece to capture & not king
				if (capturing != null && capturing.isFire() == false && 
							selected.isKing() == false && (Math.abs(x - current[0]) == 2 && current[1] - y == -2)){

					if (selected != null && selected.hasCaptured() == false){
						return true;
					}
					else if (selected != null && selected.hasCaptured() == true){
						return true;
					}
					else {				
						return true;
					}
				}
				//piece to capture & king
				else if (capturing != null && capturing.isFire() == false && 
							selected.isKing() == true && (Math.abs(x - xi) == 1)){
					if (selected != null && selected.hasCaptured() == false){
						return true;
					}
					else if (selected != null && selected.hasCaptured() == true){
						return true;
					}
					else {				
						return true;
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else if (player == -1){
			//there is a piece and its a water piece
			if (canselect != null && canselect.isFire() == false && moved == false){
				if (selected != null && selected.hasCaptured() == false){
					return true;
				}
				else if (selected != null && selected.hasCaptured() == true){
					return false;
				}
				else {				
					return true;
				}
			}
			else if (canselect != null && canselect.isFire() == false) {
				return false; //cant select fire piece
			}
			else if (canselect == null && selected != null && validMove(selected, current[0], current[1], x, y) == true && moved == false){
				return true; //valid move

			}
			//empty space but you have a piece selected
			else if (canselect == null && selected != null && validMove(selected, current[0], current[1], x, y) == false && moved == false){
				//if there is a piece in between that you can capture called capturing
				int xi = ((current[0] + x) / 2);
				int yi = ((current[1] + y) / 2);
				Piece capturing = pieces[xi][yi];
				//piece to capture & not king
				if (capturing != null && capturing.isFire() == true && 
							selected.isKing() == false && (Math.abs(x - current[0]) == 2 && y - current[1] == -2)){
					if (selected != null && selected.hasCaptured() == false){
						return true;
					}
					else {				
						return true;
					}
				}
				//piece to capture & king
				else if (capturing != null && capturing.isFire() == true && 
							selected.isKing() == true  && (Math.abs(x - xi) == 1)){
					if (selected != null && selected.hasCaptured() == false){
						return true;
					}
					else if (selected != null && selected.hasCaptured() == true){
						return true;
					}
					else {				
						return true;
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	public void select(int x, int y) {
		selectpiece = pieces[x][y];
		if (selected != null && selected.hasCaptured() == false && moved == false && selectpiece == null){
			selected.move(x, y);
			current[0] = x;
			current[1] = y; 
			selected = pieces[x][y];
			moved = true;
		}
		else if (selected != null && selectpiece == null && selected.hasCaptured() == true){
			selected.move(x, y);
			current[0] = x;
			current[1] = y; 
			selected = pieces[x][y];
			moved = true;
		}
		else if (selected != null && selected.hasCaptured() == false && moved == false && selectpiece != null) {
			current[0] = x;
			current[1] = y; 
			selected = pieces[x][y];
		}
		else if (selected == null){
			current[0] = x;
			current[1] = y; 
			selected = pieces[x][y];
		}
	}

	public Piece remove(int x, int y){
		if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)){
			System.out.println("out of bounds");
			return null;
		}
		else if (pieces[x][y] != null){
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
		else if (pieces[x][y] == null) {
			System.out.println("no piece");
			return null;
		}
		else {
			return null;
		}
	}


	public boolean canEndTurn() {
		if (selected == null) {
			return false;
		}
		else if (moved == true || selected.hasCaptured() == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn(){
		selected.doneCapturing();
		player = player * -1;
		selected = null;
	 	moved = false;


	}

	public String winner() {
		boolean water = false;
		boolean fire = false;
		if (pieces != null) {
			for (int i = 0; i < 8; i++) {
	            	for (int j = 0; j < 8; j++) {
	            		if (pieces[i][j] != null) {
			                if (pieces[i][j].isFire() == true){
			                	fire = true;
			                }
			                else if (pieces[i][j].isFire() == false) {
			                	water = true;
			                }
			            }

					}
				}
			if (fire == true && water == false) {
				return "Fire";
			}
			else if (fire == false && water == true) {
				return "Water";
			}
			else if (fire == false && water == false) {
				return "No one";
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}


}