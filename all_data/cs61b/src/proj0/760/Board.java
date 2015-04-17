public class Board {
    /** Location of pieces. */
//private  Piece[][] pieces = new Piece[8][8];
private   Piece[][] pieces;
private   Piece p;
private   boolean playerTurn; 			        	// true for fire turn and false for water turn
private   boolean pieceSelected;
private   boolean moveMade;
private   int prev_x = -1;
private   int prev_y = -1;
private   boolean canCapture;
private   boolean draw = false;     				/** change to false when send to auto grader */ 
//private   boolean draw = true;
private   boolean hasCaptured;
private   Piece selected;
private   int water;
private   int fire;

private void drawBoard(int N) {
       for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } 						//end if
                else                 { StdDrawPlus.setPenColor(StdDrawPlus.RED);
                } 						// end else 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if ((j == N-1) && (i % 2 == 1)) {
                    StdDrawPlus.picture(i + .5, j+ .5, "img/pawn-water.png", 1, 1);
                } 						//end if
               else if ((j == N-2) && (i % 2 == 0)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                } 						// end if
                else if((j == N-3) && (i % 2 == 1)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                } 						// end if
                else if ((j == 0) && (i % 2 == 0 )) {
                    StdDrawPlus.picture(i + .5, j+ .5, "img/pawn-fire.png", 1, 1);
                }
                else if ((j == 1) && (i % 2 == 1)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                } 						// end if
                else if ((j == 2) && (i % 2 == 0)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                } 						// end if
                 
             }	 						// end for
        } 							// end for 
    } 							        // end draw

    
public Piece pieceAt(int x, int y) {
        System.out.println("pieceAt");
	System.out.print(x);
	   System.out.print(y);
	if ((x<0) || (x>8) || (y<0) || (y>8)){
           return null; 
        }				                        //close if statement
        else {
             return pieces[x][y];
        } 			                                // close else
 } 					                        //close pieceAt method
  
public void place (Piece p, int x, int y) {
        System.out.println("place");
	System.out.print(x);
	System.out.print(y);
	if ((x>=0) && (x<8) && (y>=0) && (y<8)){
             pieces[x][y] = p;
             p.move(x,y);
		 if (draw) {
                        System.out.println("Drawing valid move");
                        if ((p.isFire() == false) && (p.isShield())) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                        }                                               // end if
                        else if((p.isFire() == false) && (p.isBomb())) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                        }
                        else if (p.isFire() == false) {
                                StdDrawPlus.picture(x + .5, y+ .5, "img/pawn-water.png", 1, 1);
                        }                                                 // end if
                        else if ((p.isFire() == true) && (p.isShield())) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                        }                                               // end if
                        else if ((p.isFire() == true) && (p.isBomb())) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                        }
                        else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                    }

                }
        }             			                        //close if statement
}                                                           // close place method     

public Board(boolean shouldBeEmpty) {
       
       playerTurn = true;                           		 // true for fire turn and false for water turn
       int N = 8;
       pieces = new Piece[8][8];
       if (draw) {						//toggle draw for autograder
       		StdDrawPlus.setXscale(0, N);
       		StdDrawPlus.setYscale(0, N);
       		if (shouldBeEmpty) {
           		drawEmptyBoard(N);
           		StdDrawPlus.show(100);
       		}
       		else {
          		drawBoard(N);
          //		StdDrawPlus.show(100);
          	}
       }
       if(!shouldBeEmpty) {
	    for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
                	if ((j == N-1) && (i % 2 == 1)) {
                    		p = new Piece(false, this, i, j, "Pawn");
                    		pieces[i][j] = p; 
               		}	                                                //end if
               		else if ((j == N-2) && (i % 2 == 0)) {
                    		p = new Piece(false, this, i, j, "Shield");
                    		pieces[i][j] = p;
               		 }                                               // end if
                	else if((j == N-3) && (i % 2 == 1)) {
                    		p = new Piece(false, this, i, j, "Bomb");
                    		pieces[i][j] = p;
                	}                                               // end if
                	else if ((j == 0) && (i % 2 == 0 )) {
                    		p = new Piece(true, this, i, j, "Pawn");
                    		pieces[i][j] = p;
                	}
                	else if ((j == 1) && (i % 2 == 1)) {
                        	p = new Piece(true, this, i, j, "Shield");
                      		pieces[i][j] = p;
                 	 }                                               // end if
                  	else if ((j == 2) && (i % 2 == 0)) {
                      		p = new Piece(true, this, i, j, "Bomb");
                     		 pieces[i][j] = p;
                  	}                                               // end if
	  	}
     	    }	 
	}
} 
                                                                // end main					                         //  close Board constructor    
     
public Piece remove(int x, int y) { 
	System.out.println("Remove");
	System.out.print(x);
	   System.out.print(y);
	if ((x<0) || (x>8) || (y<0) || (y>8)) {
            System.out.println(" There is no piece at this point. "); 
            return null;	
        }
        else {
             Piece removed = pieces[x][y];
             pieces[x][y] = null;
             if (draw) {
                  if ((x + y) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                  StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                  }                                               //end if
                  else                 { StdDrawPlus.setPenColor(StdDrawPlus.RED);
                  StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                   
             }
      	     return removed;
             }
	return removed;
	}     
}  
private void drawEmptyBoard(int N) {
      for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } 						//end if
                else                 { StdDrawPlus.setPenColor(StdDrawPlus.RED);
                } 						// end else 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
    
        }							// end inner for loop
    } 								// end for loop 
} 								// end drawEmptyBoard method

public boolean canEndTurn() {
     if (moveMade || hasCaptured)  {
         return true;
     }
     {
          return false;
     } 	
}

public void endTurn() {
	if (canEndTurn()) {
	        System.out.println("Now ending turn");
		if (playerTurn == true) {
			playerTurn = false;
		}		
        	else {
			playerTurn = true;
		}
	}
}   
 
public void select(int x, int y) {
	System.out.println("Select");
	System.out.print(x);
	System.out.print(y);
	if (validMove(prev_x, prev_y, x, y)) {	
		if ((pieceAt(prev_x, prev_y) != null) && (selected == pieceAt(prev_x,prev_y))) {
			p = pieceAt(prev_x, prev_y);
			place(p, x, y);
			moveMade = true;		
			remove(prev_x, prev_y);
			System.out.println("Perform Capture");
                	hasCaptured = true;
			endTurn();
		}

		else if (null == pieceAt(x,y)) {
             		p = pieceAt(prev_x,prev_y);
  	     		place(p, x, y);
             		moveMade = true;
                	remove(prev_x, prev_y);
			endTurn();
 		if (draw) {
               		System.out.println("Drawing valid move");
			if ((p.isFire() == false) && (p.isShield())) {
                    		StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                	}                                               // end if
                	else if((p.isFire() == false) && (p.isBomb())) {
                    		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                	}
			else if (p.isFire() == false) {
                                StdDrawPlus.picture(x + .5, y+ .5, "img/pawn-water.png", 1, 1);
                        }                                                 // end if
                	else if ((p.isFire() == true) && (p.isShield())) {
                    		StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                	}                                               // end if
                	else if ((p.isFire() == true) && (p.isBomb())) {
                    		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                	}     
                        else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                    }
			
                }
	     }
        }
        if  (null != pieceAt(x,y)) {
            System.out.println("We have selected a piece!");
	    pieceSelected = true;
            selected = pieceAt(x,y);
             if ((x != prev_x) && (y != prev_y)) { // need to put in lower level in case someone changes their mind
                prev_x = x;
                prev_y = y;
       	      }
         }
}   
public boolean canSelect (int x, int y) {
  	System.out.println("canSelect");
	System.out.print(x);
	System.out.print(y);
	if ((prev_x == -1) && (prev_y == -1) && (pieceAt(x, y) != null)) {
		if (pieceAt(x,y).isFire()) {
			return true;
		}
	} 
	if (pieceAt(prev_x, prev_y) != null) {
		if ((pieceSelected == false) && (pieceAt(prev_x, prev_y).isFire() != playerTurn)) {
			return true;
		}
		if ((pieceSelected) && (!moveMade) && (pieceAt(prev_x, prev_y).isFire() == playerTurn)) {
			return true;
		}
	}
	if (pieceAt(x,y) == null) { 
             if (((pieceSelected) && (!moveMade) && (validMove(prev_x, prev_y, x, y))) || ((pieceSelected) && (hasCaptured) && (validMove(prev_x, prev_y, x, y)))) {
		System.out.println("true for canSelect");
		return true;
	    	}
        }
                System.out.println("Cannot select null to move xP or Wrong player's turn");
                return false; 

}
                     
     
private boolean validMove(int xi, int yi, int xf, int yf) {
	System.out.println("validMove");
	System.out.print(xi);
	System.out.print(xf);
	System.out.print(yi);
	System.out.print(yf);
	p = pieceAt(xi, yi);
        if ((p != null) && (pieceAt(xf, yf) == null)) {
       	 	if (((Math.abs(xf - xi) == 2) || (Math.abs(xf - xi) == 1)) && ((Math.abs(yf - yi) == 2) || Math.abs(yf - yi) == 1)) { 
     			  if (p.isFire()) { 	
				if (xi < xf) {
				        Piece capture = pieceAt(xi + 1, yi + 1);
					if (p.isKing()) {
						if ((capture == null) && (Math.abs(yf-yi) == 1)) {
							return true;
						}
						else if (capture != null) {
							if ((capture.isFire() != playerTurn) && (Math.abs(yf-yi)  == 2) && (Math.abs(xf - xi) == 2)){ 
								return true;
						         }
                           			}
					}
					else if (!p.isKing()) {
						if ((capture == null) && ((yf-yi) == 1)) {
                                                       return true;
                                              }
                                               else if (capture != null) {
                                                       if ((capture.isFire() != playerTurn) && ((yf-yi)  == 2) && (Math.abs(xf - xi) == 2)){
                                                               return true;
                                                        }
                                               }
                                       } 
                           		
				 }		
				else {									      
				 	Piece capture = pieceAt(xi - 1, yi + 1);
					if (p.isKing()) {
                                                if ((capture == null) && (Math.abs(yf-yi) == 1)) {
                                                        return true;
                                                }
                                                else if (capture != null) {
                                                        if ((capture.isFire() != playerTurn) && (Math.abs(yf-yi)  == 2) && (Math.abs(xf - xi) == 2)){
                                                                return true;
                                                         }
                                                }
                                        }
                                        else if (!p.isKing()) {
                                                if ((capture == null) && ((yf-yi) == 1)) {
                                                       return true;
                                              }
                                               else if (capture != null) {
                                                       if ((capture.isFire() != playerTurn) && ((yf-yi)  == 2) && (Math.abs(xf - xi) == 2)){
                                                               return true;
                                                        }
                                               }
                                       }
                               
                                 }     // close 322 else 
	                  } 			/** close isFire */				
				if (xi < xf) {
                                        Piece capture = pieceAt(xi + 1, yi - 1);
                                        if (p.isKing()) {
                                                if ((capture == null) && (Math.abs(yf-yi) == 1)) {
                                                        return true;
                                                }
                                                else if (capture != null) {
                                                        if ((capture.isFire() != playerTurn) && (Math.abs(yf-yi)  == 2) && (Math.abs(xf - xi) == 2)){
                                                                return true;
                                                         }
                                                }
                                        }
                                        else if (!p.isKing()) {
                                                if ((capture == null) && ((yf-yi) == -1)) {
                                                       return true;
                                              }
                                               else if (capture != null) {
                                                       if ((capture.isFire() != playerTurn) && ((yf-yi)  == -2) && (Math.abs(xf - xi) == 2)){
                                                               return true;
                                                        }
                                               }
                                       }

                                 }
                                else {
                                        Piece capture = pieceAt(xi - 1, yi - 1);
                                                if (p.isKing()) {
                                                	if ((capture == null) && (Math.abs(yf-yi) == 1)) {
                                                        	return true;
                                                	}
                                                	else if (capture != null) {
                                                        	if ((capture.isFire() != playerTurn) && (Math.abs(yf-yi)  == 2) && (Math.abs(xf - xi) == 2)){
                                                                	return true;
                                                         	}
                                                	}
                                        	}
                                        else if (!p.isKing()) {
                                                if ((capture == null) && ((yf-yi) == -1)) {
                                                       return true;
						}

    						else if (capture != null) {
							if ((capture.isFire() != playerTurn) && ((yf - yi) == -2) && (Math.abs(xf - xi) == -2)) {
								return true;
							 }
						}
					}
		                }
		
			}
		}
	   	System.out.println("Invalid move");
		return false;   

}

public String winner() {
	System.out.println("Call Winner");
	for (int i = 0; i<8; i++) {	
		for (int j = 0; j<8; j++) {
			if (pieces[i][j] != null ) {
				if (pieces[i][j].isFire()) {
					fire += 1;
			 	}
				 else {
					water +=1;
				}
			}
		}
	}
        if ((fire > water) || ((water == 0) && (fire != 0))) {
	      return "Fire";
	}
	else if ((fire < water) || ((water!=0) && (fire==0))) {
	      return "Water";
	}
        else if ((water == 0) && (fire ==0)) {
	       return "No One";
	}
	else {
		return null;
        }
}
      			
				 
public static void main(String[] args) {
    Board b = new Board(true);             			 // false to test default board, true to test empty
    //Board b = new Board(true);      
    /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
    //Boolean autoGrader = true; 
    /** for auto grader */
    while (true) { // make another while loop to keep track of no autograder :) easy workaround
           if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            b.prev_x = (int) Math.floor(x);
            b.prev_y = (int) Math.floor(y);
            if ((x < 8) && (x>=0) && (y<8) && (y>=0)) {System.out.println("Selected: ");
            System.out.println(b.prev_x);
            System.out.println(b.prev_y);
            	if (b.canSelect(b.prev_x, b.prev_x)) {
			System.out.println("now run select");
			b.select(b.prev_x, b.prev_y);
		}
     	    }
            if (StdDrawPlus.isSpacePressed()) {
	       if (b.canEndTurn()) {
   		    b.endTurn();
		}
            }   				       		  // end if            
        
        }    
     }
  }  
}
