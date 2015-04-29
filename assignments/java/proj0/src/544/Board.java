public class Board{

	private Piece[][] pieces;
	private boolean selected = false;
	private int selected_piece_x;
	private int selected_piece_y;
	private int selected_x;
	private int selected_y;
	private int fire_left = 0;
	private	int water_left = 0;
	private boolean selected_empty_space = false;
	private boolean moved = false;
	private int turn = 0;
	private Captured[][] captured_pieces;
	private Captured_graveyard graveyard;
	private String new_img;

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, returnTypePic(pieces[i][j]), 1, 1);

	                }
	            }
	        }
	    }
    private void emptyBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                 }
            }
        }
    

    private String returnTypePic(Piece p){
    	if (p.isFire()){
	    	if (p.isKing()){
	    		if (p.isBomb())
	    		return "img/bomb-fire-crowned.png";
	    	if (p.isShield())
	    		return "img/shield-fire-crowned.png";
	    	else 
	    		return "img/pawn-fire-crowned.png";
	    	}
	    		
	    	if (p.isBomb())
	    		return "img/bomb-fire.png";
	    	if (p.isShield())
	    		return "img/shield-fire.png";
	    	else 
	    		return "img/pawn-fire.png";
	    }
	    else {
	    	if (p.isKing()){
	    		if (p.isBomb())
	    		return "img/bomb-water-crowned.png";
	    	if (p.isShield())
	    		return "img/shield-water-crowned.png";
	    	else 
	    		return "img/pawn-water-crowned.png";
	    	}
	    	if (p.isBomb())
	    		return "img/bomb-water.png";
	    	if (p.isShield())
	    		return "img/shield-water.png";
	    	else 
	    		return "img/pawn-water.png";
	    }

    }
    private static void setScale(){
    	StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);
    }

	public Board(boolean shouldBeEmpty){
		
		

		pieces = new Piece[8][8]; //where pieces are stored
		captured_pieces  = new Captured[8][8]; //where captured pieces are before end of turn
		graveyard = new Captured_graveyard(); //where captured pieces go to die
		if (shouldBeEmpty != true){
		for (int i = 0; i < 8; i += 1){
			if (i % 2 == 0){
			place(new Piece(true, this, i, 0, "pawn" )  , i , 0 );		
			place(new Piece(true, this, i, 2, "bomb")   , i , 2 );
			place(new Piece(false, this, i, 6, "shield"), i , 6 );
			
			}
			else {
				place(new Piece(true, this, i, 1, "shield") , i , 1 );
				place(new Piece(false, this, i, 5, "bomb") , i , 5 );
				place(new Piece(false, this, i, 7, "pawn" ), i , 7 );

				}

		
			}
			
		}
	}

	public Piece pieceAt(int x, int y){
		if (x >= 0 && y >= 0 && x < 8 && y <8){
			if (pieces[x][y] != null){
				return pieces[x][y];
			}
		}	
		else{
			return null;
		}
		return null;
		}
	public void place(Piece p, int x, int y) {
		boolean is_it_old = false;
		if (p != null || x < 8 || y < 8){
			for(int i = 0; i < 8; i += 1){
				for(int j = 0; j < 8; j += 1){
					if (this.pieces[i][j] == p){
						is_it_old = true;

						pieces[i][j] = null;

					
					}
				}
			}
			if (is_it_old == false){
				if (p.side() == 0){
					fire_left += 1;
					}
				if (p.side() == 1){
					water_left += 1;
					}
				}				
			pieces[x][y] = p;
			p.move(x,y);

		}
	}

	public Piece remove(int x, int y){
		if (pieceAt(x, y) != null){
			Piece returned = pieces[x][y];
			if (pieces[x][y].side() == 0){
				fire_left -= 1;
				}
			if (pieces[x][y].side() == 1){
				water_left -= 1;
				}			
			pieces[x][y] = null;
			return returned;
		}
		else{
			System.out.println( "(" + x + "," + y + ") is already null");
			return null;
		}

	}

	public boolean canSelect(int x, int y){
		if (x < 8 && y < 8 && x >= 0 && y >= 0) {
			if (pieceAt(x,y) != null && pieceAt(x,y).side() == turn){

				if ( moved == false){
					return true;
				}
				
			}
			if (moved == false){
				 	return validMove(selected_x, selected_y, x, y);
				 	}
				
			if (pieceAt(selected_x,selected_y) != null && pieceAt(selected_x,selected_y).hasCaptured() == true){
					return validMove(selected_x, selected_y, x , y);
					}	
			}	
			return false;

		}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (pieceAt( xi, yi) == null){
			return false;
		}

		if (pieceAt( xi, yi).isKing() == true){
			if (xi != xf && yi != yf && xf <= xi + 1 && xf >= xi - 1 && yf <= yi + 1 && yf >= yi - 1  && pieceAt( xi, yi).hasCaptured() == false){
				if (pieceAt(xf, yf) == null){
					return true;
					}	
				
				}
			for (int i = -1; i <= 1; i += 2){
				for (int j = -1; j <= 1; j += 2){
					if (pieceAt(xi + i, yi + j) != null){
						if (xi + 2 * i == xf && yi + 2 * j == yf){
							return true;
						}
						

					}
				}
			}
			}
		if (pieceAt(xi, yi).side() == 0){
			if (xi != xf && yi != yf && xf <= xi + 1 && xf >= xi - 1 && yf <= yi + 1 && yf > yi && pieceAt( xi, yi).hasCaptured() == false){
				if (pieceAt(xf, yf) == null){
					return true;
					}
				
				}
			for (int i = -1; i <= 1; i += 2){
				int j = 1;
				if (pieceAt(xi + i, yi + 1) != null){
					if (xi + 2 * i == xf && yi + 2 == yf){
						return true;
						}
					

					}
					
				}				
			}
		if (pieceAt(xi, yi).side() == 1){

			if (xi != xf && yi != yf && xf <= xi + 1 && xf >= xi - 1 && yf < yi && yf >= yi - 1  && pieceAt( xi, yi).hasCaptured() == false){
				if (pieceAt(xf, yf) == null){
					return true;
					}
				
				}
			for (int i = -1; i <= 1; i += 2){
				int j = -1;
				if (pieceAt(xi + i, yi - 1) != null){
					if (xi + 2 * i == xf && yi - 2 == yf){
						return true;
						}
					

					}
					
				}
			}


		


		 

		return false;
		}

	public void select(int x, int y){

			
		if (pieceAt(x, y) != null){

			selected_x = x;
			selected_y = y;
			selected = true;
				
			}
		if (pieceAt(x, y) == null) {
			if (selected == true){

				
				pieces[x][y] = pieces[selected_x][selected_y];
				pieces[selected_x][selected_y] = null;
				selected_x = x;
				selected_y = y;
				moved = true;
				if (pieceAt(selected_x, selected_y) != null){
					place(pieceAt(selected_x, selected_y), x, y); //we change x and y of piece here, and hasCaptured here, but we change array position in the end turn.
					}
				}
			}
			

 
		}



	private static void coloring_select(Board b, int x, int y){
		if (b.canSelect(x,y) == true){
			StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
			StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
			}	
		}


	private static void unselect(Board b, int x, int y){
			if (b.selected == true && b.pieceAt(x,y) != null){
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				StdDrawPlus.filledSquare(b.selected_x + 0.5, b.selected_y + 0.5, 0.5);
				StdDrawPlus.picture( b.selected_x + 0.5, b.selected_y + 0.5, b.returnTypePic(b.pieceAt(b.selected_x, b.selected_y)), 1, 1);
					}
		}



	public boolean canEndTurn(){
		if (moved == false){
			return false;
		}
		return true;
	}

	public void endTurn(){
		if (selected_x >= 0 && selected_x < 8 && 
			selected_y >= 0 && selected_y < 8){

			if (turn == 0){
				turn = 1;
			}
			else {
				turn = 0;
			}
			moved = false;
			selected = false; 
			if (pieces[selected_x][selected_y] != null){
				pieces[selected_x][selected_y].doneCapturing(); 
				}									//resetting
			selected_x = 1000;
			selected_y = 1000;
			selected_piece_x = 1000;
			selected_piece_y = 1000;
			new_img = "img/duck_PNG5023.png";
			
			
		}
	}
	
	public String winner() {

		String win;
		if (fire_left == 0 && water_left == 0){
			return "No one";
			}
		if (fire_left == 0){
			return "Water";
			}
		if (water_left == 0){
			return "Fire";
			}
		else {
			
			return null;
			}



		}
		

	public static void main(String[] args){
		StdDrawPlus.setCanvasSize();
		Board theBoard = new Board(false);
		boolean game_is_on = true;
		setScale();
		theBoard.drawBoard(8);
		while(game_is_on) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                unselect(theBoard, (int) x, (int) y);
                coloring_select(theBoard, (int) x, (int) y);
                if (theBoard.canSelect( (int) x, (int) y) == true){
                	theBoard.select((int) x, (int) y);
	                }
	            } 
            if (StdDrawPlus.isSpacePressed()){
            	if (theBoard.canEndTurn() == true){
            		theBoard.endTurn();
            		theBoard.drawBoard(8);
	            	}
	            } 
            
            if (theBoard.winner() != null){
            	theBoard.winner(); 
            	break;
            	}

            StdDrawPlus.show(100);
        	}

		
		}

	}
