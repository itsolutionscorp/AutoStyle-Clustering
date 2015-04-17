
public class Board {
	
	// varibles must be private
	// updated after friday submit grader, if a piece has been removed, a piece has been moved

	
	 private static Piece[][] pieces = new Piece[8][8];
	 private static int pieceselectedxcoord;
	 private static int pieceselectedycoord;
	 private boolean pieceselected;
	 private boolean piecehasmoved;
	 private boolean piecehascaptured;
	 
	 private int turn;
	 
	 
	
	 
	

	
private static void drawBoard() {
	// Draw pieces as well. Call drawBoard after empty board and pieces are initialized
	StdDrawPlus.setXscale(0, 8);
    StdDrawPlus.setYscale(0, 8);
	for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
            if ((i + j) % 2 == 0) {
            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
} 
            else   {               
            	StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }      
        }
	}
	
	if (pieceselectedxcoord != -1 && pieceselectedycoord != -1) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	StdDrawPlus.filledSquare(pieceselectedxcoord + .5, pieceselectedycoord + .5, .5);
	}
	
	
	
	for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
        if (pieces[i][j] != null) {
          
          if (pieces[i][j].isFire() == true) {
          
          if (pieces[i][j].isKing() == true) {
          	if (pieces[i][j].isBomb() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/bomb-fire-crowned.png", 1, 1);
          	}
          	else if (pieces[i][j].isShield() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/shield-fire-crowned.png", 1, 1);
          	}
          	else {
          		StdDrawPlus.picture(i + .5, j + .5,"img/pawn-fire-crowned.png", 1, 1);
          	}
        	}
        	else {
        	if (pieces[i][j].isBomb() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/bomb-fire.png", 1, 1);
          	}
          	else if (pieces[i][j].isShield() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/shield-fire.png", 1, 1);
          	}
          	else {
          		StdDrawPlus.picture(i + .5, j + .5,"img/pawn-fire.png", 1, 1);
          	}
        	}
        	}
        	else {
          
          if (pieces[i][j].isKing() == true) {
          	if (pieces[i][j].isBomb() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/bomb-water-crowned.png", 1, 1);
          	}
          	else if (pieces[i][j].isShield() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/shield-water-crowned.png", 1, 1);
          	}
          	else {
          		StdDrawPlus.picture(i + .5, j + .5,"img/pawn-water-crowned.png", 1, 1);
          	}
        	}
        	else {
        	if (pieces[i][j].isBomb() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/bomb-water.png", 1, 1);
          	}
          	else if (pieces[i][j].isShield() == true) {
          		StdDrawPlus.picture(i + .5, j + .5,"img/shield-water.png", 1, 1);
          	}
          	else {
          		StdDrawPlus.picture(i + .5, j + .5,"img/pawn-water.png", 1, 1);
          	}
        	}
        	}
        	
  
			}
        
    }    
}
}



public Board(boolean shouldBeEmpty) {
	
        
	if (shouldBeEmpty == false) {
			// Construct a new game on a new board
		for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((i + j) % 2 == 0) {
               
            
                	//create new piece s on the board
                	if (j == 0) {
                		pieces[i][j] = new Piece(true,this,i,j,"pawn");
                	}
                	if (j == 1) {
                		pieces[i][j] = new Piece(true,this,i,j,"shield");
                	} 
                	if (j == 2) {
                		pieces[i][j] = new Piece(true,this,i,j,"bomb");
                	}
                	if (j == 5) {
                		pieces[i][j] = new Piece(false,this,i,j,"bomb");
                	} 
                	if (j == 6) {
                		pieces[i][j] = new Piece(false,this,i,j,"shield");
                	} 
                	if (j == 7) {
                		pieces[i][j] = new Piece(false,this,i,j,"pawn");
                	} 
                
                }
                      
            
		}
    }
		// no piece has been considered
		// if no piece is selected, the coordinates of the now piece are -1,-1
		turn = 1;
		pieceselected = false;
		pieceselectedxcoord = -1;
		pieceselectedycoord = -1;
		piecehasmoved = false;
		piecehascaptured = false;
}
	
	
	
	else {
		for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) { 
            	pieces[i][j] = null;
            	}
            }
		turn = 1;
		pieceselected = false;
		pieceselectedxcoord = -1;
		pieceselectedycoord = -1;
		piecehasmoved = false;
		piecehascaptured = false;
			
		}
		
		
		
		
}
	
	
		
	
public Piece pieceAt(int x, int y) {
	if ( x < 0 || y < 0 || 7 < x || 7 < y) {
		return null;
	}
	if (pieces[x][y] == null) {
		return null;
	}
	
		return pieces[x][y];
		
	}
	
public boolean canSelect(int x, int y) {

		if (pieceAt(x,y) != null ) {
			
			if (turn % 2 != 0) {
				if (pieceAt(x,y).isFire() == true) {
			
			  if (pieceselected == false) {
			  return true;
			  }
			  
			  if (pieceselected == true) {
			  	if (piecehasmoved == false) {
			  return true;
			  }
			  }
			   
			  
		}
			}
			
			if (turn % 2 == 0) {
				if (pieceAt(x,y).isFire() == false) {
			
			  if (pieceselected == false) {
			  return true;
			  }
			  
			  if (pieceselected == true) {
			  	if (piecehasmoved == false) {
			  return true;
			  }
			  }
			   
			  
		}
			}
		}
		
	  else {
		     if (pieceselected == false) {
			  return false;
			  }
			  if (pieceselected == true) {
			    if (piecehasmoved == false) {
			  			if (validMove(pieceselectedxcoord,pieceselectedycoord,x,y) == true)	{		
			  				return true;																
			  			}	
			    }
			  																								    
			  		if (piecehascaptured == true) {
			  			if (validMove(pieceselectedxcoord,pieceselectedycoord,x,y) == true)	{			
			  			return true;																			
			  			}			  
			  		}
			  }
		}
			  
			
		
		return false;
}

private boolean validMove(int xi,int yi,int xf,int yf) {
	if (pieceAt(xi,yi).isKing() == true) {
		if (xf == xi+1 || xf == xi-1) {
			if (yf == yi+1 || yf == yi-1) {
				if (pieces[xf][yf] == null) {
					if (pieces[xi][yi].hasCaptured() == false) {
					return true;
					}
				}
			}
		}
		if (xf == xi+2 || xf == xi-2) {
			if (yf == yi+2 || yf == yi-2) {
				if (pieces[xf][yf] == null) {
					if (pieces[xi+(xf-xi)/2][yi+(yf-yi)/2] != null) {
					if (pieces[xi+(xf-xi)/2][yi+(yf-yi)/2].side() != pieces[xi][yi].side()) {
					return true;
					}
					}
				}
			}
		}
	}
		
	
	if (pieceAt(xi,yi).isFire() == true) {
		if (xf == xi+1 || xf == xi-1) {
			if (yf == yi+1) {
				if (pieces[xf][yf] == null) {
					if (pieces[xi][yi].hasCaptured() == false) {
					return true;
					}
				}
			}
		}
		if (xf == xi+2 || xf == xi-2) {
			if (yf == yi+2) {
				if (pieces[xf][yf] == null) {
					if (pieces[xi+(xf-xi)/2][yi+(yf-yi)/2] != null) {
					if (pieces[xi+(xf-xi)/2][yi+(yf-yi)/2].side() != pieces[xi][yi].side()) {
					return true;
					}
					}
				}
			}
		}
	}
	
	if (pieceAt(xi,yi).isFire() == false) {
		if (xf == xi+1 || xf == xi-1) {
			if (yf == yi-1) {
				if (pieces[xf][yf] == null) {
					if (pieces[xi][yi].hasCaptured() == false) {
					return true;
					}
				}
			}
		}
		if (xf == xi+2 || xf == xi-2) {
			if (yf == yi-2) {
				if (pieces[xf][yf] == null) {
					if (pieces[xi+(xf-xi)/2][yi+(yf-yi)/2] != null) {
					if (pieces[xi+(xf-xi)/2][yi+(yf-yi)/2].side() != pieces[xi][yi].side()) {
					return true;
					}
					}
				}
			}
		}
	}
	
	return false;
		
}

public void select(int x, int y) {
	
		if (pieceselected == true) {
			if (pieces[x][y] == null) {
			// time to move the selected piece
				
			pieces[pieceselectedxcoord][pieceselectedycoord].move(x,y);
			pieceselectedxcoord = x;
			pieceselectedycoord = y;
			piecehasmoved = true;
			if (pieces[x][y] != null) {
			if (pieces[x][y].hasCaptured() == true) {
				piecehascaptured = true;
			}
			}
			}
		}
		if (pieceselected == false) {
			// select first new piece
			pieceselectedxcoord = x;
			pieceselectedycoord = y;
			pieceselected = true;
			
		}
		
		if (pieceselected == true) {
			if (piecehasmoved == false) {
			// select new piece
				pieceselectedxcoord = x;
				pieceselectedycoord = y;
				pieceselected = true;
			 
			}
		
	}
	
}

public void place(Piece p, int x, int y) {
	if (p == null) {
		return;
	}
	if ( x < 0 || y < 0 ) {
		if (7 < x || 7 < y) {
			return;
		}
	}
	
	pieces[x][y] = p;
	
	

}

public Piece remove(int x, int y) {
	if ( x <= 0 || y <= 0 ) {
		if (7 < x || 7 < y) {
			System.out.println("trying to remove from out of bounds");
			return null;
		}
	}
	
	if (pieces[x][y] == null) {
		return null;
	}
	Piece returnkilled = pieces[x][y];
	pieces[x][y] = null;
	piecehasmoved = true;
	return returnkilled;
}



public boolean canEndTurn() {
	
	if (piecehasmoved == true) {
		return true;
	}
	if (piecehascaptured == true) {
		return true;
	}
	return false;
}


public void endTurn() {
	
	turn = turn + 1;
	pieceselected = false;
	pieceselectedxcoord = -1;
	pieceselectedycoord = -1;
	piecehasmoved = false;
	piecehascaptured = false;
	for (int i = 0; i <= 7; i++) {
	    for (int j = 0; j <= 7; j++) {
	    	if (pieces[i][j] != null) {
	    	pieces[i][j].doneCapturing();
	    	}
	    }
	}
	    
	// done with iteration of while loop
}

public String winner() {

int firetally = 0;
int watertally = 0;

	for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
        if (pieces[i][j] != null) {
          
          if (pieces[i][j].isFire() == true) {
        	  firetally = firetally + 1;
          }
          else {
        	  watertally = watertally + 1;
          }
        }
        }
	}
	
	if (watertally > firetally) {
		return "Water";
	}
	if (firetally > watertally) {
		return "Fire";
	}
	if (firetally == 0 && watertally == 0) {
	return "No one";
	}
	return null;
}
	
private boolean continuegame() {
	
	
	int firecounter = 0;
	int watercounter = 0;
	
for (int i = 0; i <= 7; i++) {
    for (int j = 0; j <= 7; j++) {
    	if (pieces[i][j] != null) {
    	 if (pieces[i][j].side() == 0) {
    		 firecounter = firecounter+1;
    	 }
    	 if (pieces[i][j].side() == 1) {
    		 watercounter = watercounter+1;
    	 }
    	} 
    }
    }

	if (firecounter == 0 | watercounter == 0 ) {
	return false;
	}
	return true;
}

	

	
	
	 public static void main(String args[]) {
	
 /* Idea waddle mouse around and going though process of selecting square.
  * Will keep doing this until a move is called. End turn sometime after this point
  * Ending turn switches access between fire and water pieces.
  */
		 int canselectthisx = 0;
		 int canselectthisy = 0;
		 
		 
		 
		
		 
        Board b = new Board(false);
        drawBoard();
        StdDrawPlus.show();
        
        
        
        while(b.continuegame() == true) {
      
     
        if (StdDrawPlus.mousePressed()) {
        	
        	double realxcoord = StdDrawPlus.mouseX();
        	double realycoord = StdDrawPlus.mouseY();
        	 canselectthisx = (int)(realxcoord);
        	 canselectthisy = (int)(realycoord);
        	 
        	 if (b.canSelect(canselectthisx, canselectthisy) == true) {
        		 b.select(canselectthisx, canselectthisy);
        		 drawBoard();
        		 StdDrawPlus.show();
        		 
        	 }
        
        }
        
        if (StdDrawPlus.isSpacePressed() == true) {
        	if (b.canEndTurn() == true) {
        		b.endTurn();
        	    }
        			
        	}
        	
        }
        System.out.println(b.winner());
        
        }
   
       

}
	 
	 
	 

