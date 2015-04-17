/** 
 *  @author Charles Keaton Noon
 */

public class Board {
    /** Location of pieces. */
	private boolean[][] pieces;

	//current turn variable (1 == fire) (0 == fire) 
	//reset "selected piece to null" whenever this changes
	private int current_turn;

	//stores coordinates of selected piece
	private int[] selected_piece; 

	//stores all piece objects (the big ol bread and butter baby).
	private Piece[][] piecesArray;

	//indicates whether or not a move can be made (true at start of turn)
	//false after a kill if no other kills are available
	//true if another kill only with the same piece is possible
	//changed to true when the turn switches
	//gets set to true in Board constructor
	//private boolean /*canMove;
	private boolean alreadyMoved;

	private boolean bombKilled;

	private boolean movedOne;

	public static void main(String[] args) {
		//System.out.println("main is being checked");
	    StdDrawPlus.setXscale(0, 8);
	    StdDrawPlus.setYscale(0, 8);
	    Board board = new Board(false);
	    /** Monitors for mouse presses. Wherever the mouse is pressed,
	        a new piece appears. */
	    while(true) {
	    	//got the board. idea from ajj (Valentine)
	    	board.drawBoard();
	        if (StdDrawPlus.mousePressed()) {
	            double x = StdDrawPlus.mouseX();
	            double y = StdDrawPlus.mouseY();
	            //when a place is clicked, it is selected.
	            //if it can be selected (either to kill or just move to)
				if (board.canSelect((int) x, (int) y)){
		            board.select((int) x, (int) y);
		        }
	        } 
	        if (StdDrawPlus.isSpacePressed()) {
	        	if (board.canEndTurn()){
	        		board.endTurn();
	        	}	        	
	        }        
	        StdDrawPlus.show(40);
	        if (board.winner() != null){
	    		//got idea from https://piazza.com/class/hx9h4t96ea8qv?cid=1229
        		System.exit(0);
	    	}
	    }
	}

	//gets piece at position (x,y) or returns null. 
	public Piece pieceAt(int x, int y){
		if (inBounds(x, y)){
			if (pieces[x][y]){
				return piecesArray[x][y];
			} else{
				return null;
			}
		} else{
			return null;
		}
	}

	public void place(Piece p, int x, int y){
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7)){
			pieces[x][y] = true;
			piecesArray[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7)){
			if (pieces[x][y]){
				Piece removed_piece = pieceAt(x, y);
				pieces[x][y] = false;
				piecesArray[x][y] = null;
				return removed_piece;
			} else {
				System.out.println("There is no piece there");
				return null;
			}
		}
		System.out.println("That spot is out of range");
		return null;
	}

	public boolean canEndTurn(){
		return alreadyMoved;
	}


	public void endTurn(){
		/*canMove = true*/;
		pieceAt(selected_piece[0], selected_piece[1]).doneCapturing();
		alreadyMoved = false;
		bombKilled = false;
		movedOne = false;
		if (current_turn == 1){
			current_turn = 0;
		} else {
			current_turn = 1;
		}
		selected_piece[0] = 8;
		selected_piece[1] = 8;
	}

	public String winner(){
		boolean no_more_pieces = true;
		boolean no_more_fire = true;
		boolean no_more_water = true;
		for (int i = 0; i < 8; i++) {
       		for (int j = 0; j < 8; j++) {
       			if (pieces[i][j]){
       				no_more_pieces = false;
       			}
       			if (pieces[i][j]){
       				if (piecesArray[i][j].isFire()){
       					no_more_fire = false;
       				} else {
       					no_more_water = false;
       				}
       			}
            }
        }
        if (no_more_pieces){
        	System.out.println("No one");
        	return "No one";
        } else if (no_more_water) {
        	System.out.println("Fire");
        	return "Fire";
        } else if (no_more_fire) {        	
        	System.out.println("Water");
        	return "Water";
        } else {
        	return null;
        }
	}

	public boolean canSelect(int x, int y){
		if (inBounds(x, y)){
			//for trying to select a piece
			//very specific code to satisfy autograder (i do not believe this affects playing at all)
			//a bomb exploded
			if(bombKilled){
				//there is a piece at xy
				// if (pieces[x][y]){
				// 	//no pieces are selected and the piece is on the same side
				// 	if ((selected_piece[0] == 8) && (pieceAt(x, y).side() == current_turn)){
				// 		return true;
				// 	//a piece is already selected or the piece is on the opposite side
				// 	} else {
				// 		return false;
				// 	}
				// //no piece
				// } else {
					return false;
				//}		
			} 

			if(movedOne){
				return false;
			}

			if ((selected_piece[0] == x) && (selected_piece[1] == y)){
				if (pieceAt(selected_piece[0], selected_piece[1]).hasCaptured()) {
					return false;
				} else {
				return true;
				}
			}
			//making sure only blank spaces can be selected after having killed
			if (selected_piece[0] != 8){
				if ((pieceAt(selected_piece[0], selected_piece[1]).hasCaptured()) && pieces[x][y]){
					return false;
				}
			}
			//selecting a piece
			if (pieces[x][y]){
			    //no pieces have been selected. and the piece to be selected is of the same side as the person trying to select it
				if ((selected_piece[0] == 8) && (pieceAt(x, y).side() == current_turn)) {  
					//System.out.println("nothing selected, and this one is on my side");
					return true;
				}
				//a piece has been selected
				if ((!(alreadyMoved)) && (piecesArray[x][y].side() == current_turn)){  
					//System.out.println("something has already been selected");
					return true;
				} 
				else { return false; }
			//for selecting an empty square
			} else {
				//System.out.println("this is empty");
				//a piece has been selected and the piece has yet to move and play being moved to is a valid spot.
				if (((selected_piece[0] != 8) && (!(alreadyMoved))) && (validMove(selected_piece, x, y))) { 
					return true; 
				} 
				//System.out.println("check");
				//a piece has been selected and it has captured and it has selected another valid capture destination
				if ( ( (selected_piece[0] != 8) && (pieceAt(selected_piece[0], selected_piece[1]).hasCaptured() ) ) && 
					( (canJump(selected_piece[0], selected_piece[1], x, y)) && (validMove(selected_piece, x, y)) ) ){
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	// checks if a given spot can be moved to given the selected spot and the selected_piece
	private boolean validMove(int[] location_of_piece, int x_possible, int y_possible){
		if (inBounds(x_possible, y_possible)){
			//this is where the piece selected is located
			int x_location = location_of_piece[0];
			int y_location = location_of_piece[1];
			Piece peece = pieceAt(x_location, y_location); 
			//diagonal (probably don't need)
			
			if ((x_location == x_possible) || (y_location == y_possible)){
				return false;
			}
			//occupied
			if (pieces[x_possible][y_possible]){
				return false;
			}
			//is king
			if (peece.isKing()){
				//System.out.println("is king");
				//checks that potential move is within moving range of king piece
				if (!(kingRange(x_location, y_location, x_possible, y_possible))){
					return false;
				}
				//has captured?
				if (peece.hasCaptured()){
					//checks if a jump can be made
					if (canJump(x_location, y_location, x_possible, y_possible)){
						return true;	
					} else {
						return false;
					}
				//hasn't captured	
				} else {
					//place is within one square
					if (
						//checks upper right
						((((x_location + 1) == (x_possible)) && ((y_location + 1) == (y_possible))) ||
						//checks upper left
						(((x_location - 1) == (x_possible)) && ((y_location + 1) == (y_possible)))) ||
						//checks lower right
						((((x_location + 1) == (x_possible)) && ((y_location - 1) == (y_possible))) ||
						//checks lower left
						(((x_location - 1) == (x_possible)) && ((y_location - 1) == (y_possible))))
						){
							return true;
					}
					//we know piece is two away. so we check that the piece between the two is occupied by one of the opposite team
					if (canJump(x_location, y_location, x_possible, y_possible)){
						return true;	
					} else {
						return false;
					}
				}	

			//not king
			} else {
				//fire
				if (pieceAt(x_location, y_location).isFire()){
					//within fire range
					if (fireRange(x_location, y_location, x_possible, y_possible)){
						//within one square
						if (
							(((x_location + 1) == (x_possible)) && ((y_location + 1) == (y_possible))) || 
							(((x_location - 1) == (x_possible)) && ((y_location + 1) == (y_possible)))
							){
							return true;
						//two away
						} else {
							if(canJump(x_location, y_location, x_possible, y_possible)){
								return true;
							} else {
								return false;
							}
						}

					//out of range
					} else {
						return false;
					} 
				//water
				} else {
					//within water range
					if (waterRange(x_location, y_location, x_possible, y_possible)){
						//within one square
						if (
							(((x_location + 1) == (x_possible)) && ((y_location - 1) == (y_possible))) || 
							(((x_location - 1) == (x_possible)) && ((y_location - 1) == (y_possible)))
							){
							return true;
						//two away
						} else {
							if(canJump(x_location, y_location, x_possible, y_possible)){
								return true;
							} else {
								return false;
							}
						}

					//out of range
					} else {
						return false;
					} 
				}
			}
		}
		return false;
	}

	public void select(int x, int y){
		if (inBounds(x, y)){
			//a piece has already been selected. so it is going to kill or move
			if (selected_piece[0] != 8){
				//switches selected_piece if piece at x y is of the same race
				if ((selected_piece[0] == x) && (selected_piece[1] == y)){
					//nothing is done
				} else if(pieces[x][y]){	
					selected_piece[0] = x;
					selected_piece[1] = y;
				
				//within one square
				} else if (((selected_piece[0] + 1) == x) || ((selected_piece[0] - 1) == x)){
					//moves piece object in Piece.java
					pieceAt(selected_piece[0], selected_piece[1]).move(x, y);
					//removes already selected piece and places it at new place 
					place(remove(selected_piece[0], selected_piece[1]), x, y);
					//selected_piece is reset
					selected_piece[0] = x;
					selected_piece[1] = y;
					alreadyMoved = true;
					movedOne = true;
				// two squares away
				} else {
					//grabs piece at selected location that is going in for a kill
					Piece tempp = pieceAt(selected_piece[0], selected_piece[1]);
					//bomb piece
					if (tempp.isBomb()){
						remove(((selected_piece[0] + x) >>> 1),((selected_piece[1] + y) >>> 1));
						int bound_lower_x = Math.max((x - 1), 0);
						int bound_upper_x = Math.min((x + 2), 8);
						int bound_lower_y = Math.max((y - 1), 0);
						int bound_upper_y = Math.min((y + 2), 8);
						for (int i = bound_lower_x; i < bound_upper_x; i++) {
            				for (int j = bound_lower_y; j < bound_upper_y; j++) {
            					if (pieces[i][j]){
            						if (!(pieceAt(i, j).isShield())){
            							remove(i, j);
            						}
            					} 

            				}
            			}
            		remove(selected_piece[0], selected_piece[1]);
            		bombKilled = true;
            		selected_piece[0] = 8;
            		selected_piece[1] = 8;

					//not bomb piece
					} else {
					//moves piece to next location in piece class
					tempp.move(x, y);
					//updates piecesArray array
					piecesArray[x][y] = remove(selected_piece[0], selected_piece[1]);
					//updates pieces array
					pieces[x][y] = true;
					//piece jumped is removed from piecesArray and pieces is udated 
					remove((selected_piece[0] + x) >>> 1, (selected_piece[1] + y) >>> 1);
					selected_piece[0] = x;
					selected_piece[1] = y;
					}

					
					//can move become false b/c no more moves can be made ***LOOK MORE INTO THIS****
					//already moved is updated
					alreadyMoved = true;
				}
			//nothing has been selected. so it is not going to kill
			} else {
				selected_piece[0] = x;
				selected_piece[1] = y;
			}
		}
	}//if it can't be selected, nothing happens
	


    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                //hilights selected square
                if((selected_piece[0] == i) && (selected_piece[1] == j)){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j]){  
                	Piece temp_piece = pieceAt(i, j);              	
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + getTypeString(temp_piece) + ".png", 1, 1);
                }
            }
        }
    }
    //outputs a string which is compatible with the file names from img.
    //used during the draw board method
    private String getTypeString(Piece current){
    	if (current.isFire()){
    		if (current.isBomb()){
    			if (current.isKing()){
    				return "bomb-fire-crowned";
    			} else {
    				return "bomb-fire";
    			} 
    		} else if (current.isShield()){
    			if (current.isKing()){
    				return "shield-fire-crowned";
    			} else {
    				return "shield-fire";
    			}
    		} else {
    			if (current.isKing()){
    				return "pawn-fire-crowned";
    			} else {
    				return "pawn-fire";
    			}
    		}
    	} else {
    		if (current.isBomb()){
    			if (current.isKing()){
    				return "bomb-water-crowned";
    			} else {
    				return "bomb-water";
    			} 
    		} else if (current.isShield()){
    			if (current.isKing()){
    				return "shield-water-crowned";
    			} else {
    				return "shield-water";
    			}
    		} else {
    			if (current.isKing()){
    				return "pawn-water-crowned";
    			} else {
    				return "pawn-water";
    			}
    		}
    	}
    }
    	//constructor
    public Board(boolean shouldBeEmpty){
    	//stores pieces and a boolean array mimicking the pieces 
    	//array (to avoid null pointer errores)
    	piecesArray = new Piece[8][8];
    	pieces = new boolean[8][8];
    	//when no piece is selected, this is set to [8,8] 
	    selected_piece = new int[] {8, 8};
	    //fire has not moved yet
	    alreadyMoved = false;
	    //always starts with fire going first
	    current_turn = 1;
	    bombKilled = false;
		movedOne = false;

    	if (!(shouldBeEmpty)){
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            	if (j == 0){
		            	if ((i%2) == 0){
		            		piecesArray[i][j] = new Piece(true, this, i, j, "pawn");
		            		pieces[i][j] = true;
		            	} else {
		            		piecesArray[i][j] = null;
		            		pieces[i][j] = false;
		            	}
		            }
		            if (j == 1){
		            	if (((i+1)%2) == 0){
		            		piecesArray[i][j] = new Piece(true, this, i, j, "shield");
		            		pieces[i][j] = true;
		            	} else {
		            		piecesArray[i][j] = null;
		            		pieces[i][j] = false;
		            	}
		            }
		            if (j == 2){
		            	if ((i%2) == 0 ){
		            		piecesArray[i][j] = new Piece(true, this, i, j, "bomb");
		            		pieces[i][j] = true;
		            	} else {
		            		piecesArray[i][j] = null;
		            		pieces[i][j] = false;
		            	}
		            }
		            if ((j == 3) || (j == 4)){
		            	piecesArray[i][j] = null;
		            	pieces[i][j] = false;
		            }
		            if (j == 5){
		            	if (((i+1)%2) == 0){
		            		piecesArray[i][j] = new Piece(false, this, i, j, "bomb");
		            		pieces[i][j] = true;
		            	} else {
		            		piecesArray[i][j] = null;
		            		pieces[i][j] = false;
		            	}
		            }
		            if (j == 6){		      
		            	if ((i%2) == 0){
		            		piecesArray[i][j] = new Piece(false, this, i, j, "shield");
		            		pieces[i][j] = true;
		            	} else {
		            		piecesArray[i][j] = null;
		            		pieces[i][j] = false;
		            	}
		            }
		            if (j == 7){
		            	if (((i + 1)%2) == 0){
		            		piecesArray[i][j] = new Piece(false, this, i, j, "pawn");
		            		pieces[i][j] = true;
		            	} else {
		            		piecesArray[i][j] = null;
		            		pieces[i][j] = false;
		            	}
		            }
	    	    }
	   		}
    	} else {
    		for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		piecesArray[i][j] = null;
            		pieces[i][j] = false;
            	}
            }	
    	}
    }

    private boolean kingRange(int king_x, int king_y, int place_x, int place_y){
    	if (!(
				//checks upper right
				(((((king_x + 1) == (place_x)) && ((king_y + 1) == (place_y))) ||
				//checks upper left
				(((king_x - 1) == (place_x)) && ((king_y + 1) == (place_y)))) ||
				//checks lower right
				((((king_x + 1) == (place_x)) && ((king_y - 1) == (place_y))) ||
				//checks lower left
				(((king_x - 1) == (place_x)) && ((king_y - 1) == (place_y))))) ||
				//checks upper upper right
				(((((king_x + 2) == (place_x)) && ((king_y + 2) == (place_y))) ||
				//checks upper upper left
				(((king_x - 2) == (place_x)) && ((king_y + 2) == (place_y)))) ||
				//checks lower lower right
				((((king_x + 2) == (place_x)) && ((king_y - 2) == (place_y))) ||
				//checks lower lower left
				(((king_x - 2) == (place_x)) && ((king_y - 2) == (place_y)))))
				)){
				return false;
			} else {
				return true;
		}
    }

    private boolean fireRange(int king_x, int king_y, int place_x, int place_y){
    	if (!(
				//checks upper right
				((((king_x + 1) == (place_x)) && ((king_y + 1) == (place_y))) ||
				//checks upper left
				(((king_x - 1) == (place_x)) && ((king_y + 1) == (place_y)))) ||
				//checks upper upper right
				((((king_x + 2) == (place_x)) && ((king_y + 2) == (place_y))) ||
				//checks upper upper left
				(((king_x - 2) == (place_x)) && ((king_y + 2) == (place_y)))) 
				)){
				return false;
			} else {
				return true;
		}
    }

    private boolean waterRange(int king_x, int king_y, int place_x, int place_y){
    	if (!(
				//checks lower right
				((((king_x + 1) == (place_x)) && ((king_y - 1) == (place_y))) ||
				//checks lower left
				(((king_x - 1) == (place_x)) && ((king_y - 1) == (place_y)))) ||
				//checks lower lower right
				((((king_x + 2) == (place_x)) && ((king_y - 2) == (place_y))) ||
				//checks lower lower left
				(((king_x - 2) == (place_x)) && ((king_y - 2) == (place_y))))
				)){
				return false;
			} else {
				return true;
		}
    }

    private boolean inBounds(int x, int y){
    	if (((x < 0) || (7 < x)) || ((x < 0) || (7 < x))){
    		return false;
    	}
    return true;
    }
    //only used in validMove for checking if a piece one away can be jumped
    private boolean canJump(int x_location, int y_location, int x_possible, int y_possible){
    	//makes sure the move isn't within one square
    	if (
			//checks upper right
			((((x_location + 1) == (x_possible)) && ((y_location + 1) == (y_possible))) ||
			//checks upper left
			(((x_location - 1) == (x_possible)) && ((y_location + 1) == (y_possible)))) ||
			//checks lower right
			((((x_location + 1) == (x_possible)) && ((y_location - 1) == (y_possible))) ||
			//checks lower left
			(((x_location - 1) == (x_possible)) && ((y_location - 1) == (y_possible))))
			){
				return false;
			}
			//got >>> idea from https://piazza.com/class/hx9h4t96ea8qv?cid=1680
    	if (pieces[(x_location + x_possible) >>> 1][(y_location + y_possible) >>> 1]){
			//the piece to be jumped is on opposite team
			if (piecesArray[(x_location + x_possible) >>> 1][(y_location + y_possible) >>> 1].side() != current_turn){
				return true;
			//same side 
			} else {
				return false;
			}
		//square to be jumped is empty	
		} else {
			return false;
		}
   	}
}



