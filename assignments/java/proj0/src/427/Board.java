 public class Board{
 	private Piece[][] pieceArray = new Piece[8][8];
 	private boolean selected = false;
 	private boolean moved = false;
 	private boolean player1 = true;
 	private int canSelectX;
 	private int canSelectY;
 	private int prevX = 0;
 	private int prevY = 0 ;
  private boolean hasWinner = false;
/** Draw picture for pieceArray[i][j] based on piece's info **/
 	private void drawPicture(int i, int j){
 		  if(pieceArray[i][j] != null){
                if(pieceArray[i][j].isKing()){
                  if(pieceArray[i][j].isFire()){
                    // Draw fire pawn
                    if (pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false ) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    }
                    // Draw fire bomb
                    else if (pieceArray[i][j].isBomb() == true){
                      StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);}
                    // Draw fire shied.
                    else{
                      StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                      }
                }
                //If piece is Water.
                else{
                  //Draw water pawn
                  if (pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false ) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    }
                    //Draw water bomb
                    else if (pieceArray[i][j].isBomb() == true){
                      StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    }
                    //Draw water shield
                    else{
                      StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    }
                  }

                }
                	// If piece is Fire.
	                if(pieceArray[i][j].isFire()){
	                	// Draw fire pawn
		                if (pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false ) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
		                }
		                // Draw fire bomb
		                else if (pieceArray[i][j].isBomb() == true){
		                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);}
		                // Draw fire shied.
		                else{
		                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}
	            	}
	            	//If piece is Water.
	            	else{
	            		//Draw water pawn
	            		if (pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false ) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
		                }
		                //Draw water bomb
		                else if (pieceArray[i][j].isBomb() == true){
		                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		                }
		                //Draw water shield
		                else{
		                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	            			}
	            		}

       			 }

 	}
 /** Draw board **/
    private  void drawBoard(int N, int update, int x, int y) {
        /** For loop draw picture for every piece **/
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawPicture(i,j);                    			
 			}
 		}
 		/** change background to white on clicked piece **/
 		if(update == 1){
 		    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

 			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
 			drawPicture(x,y);

 		}
 	}
        
 /** Set every object in piece array to null **/
  private void setNull(){
  	for(int i = 0; i < 8; i ++)
 			{
 				for(int j = 0; j < 8; j++){
 					pieceArray[i][j] = null;
 				}
 			}
  }
 /** Constructor of Board class **/

 	public Board(boolean shouldBeEmpty){
 		if(shouldBeEmpty){
 			setNull();
 		}
 		/**  Set up default configuration of Board  **/
 		else{ 
 			/** Set null to every object in Array **/
 			setNull();
 			/** Initialize fire side of Array */
 			for(int j = 0; j < 3; j+= 2){
 				for(int i = 0; i < 8; i += 2){
 					if (j == 0){
 					pieceArray[i][j] = new Piece(true,this,i,j,"pawn"); // fire pawn
 					}
 					else{ 
 					pieceArray[i][j] = new Piece(true,this,i,j,"bomb"); // fire bomb
 					}
 				}
 			}
 			for(int j = 1; j < 2; j ++){
 				for (int i = 1; i < 8; i+= 2){
 					pieceArray[i][j] = new Piece(true,this,i,j,"shield"); // fire shield.

 				}
 			}
 			/** Initialize water side of Array */
 			for(int j = 5; j < 8; j += 2){
 				for(int i = 1; i < 8; i += 2){
 					if(j == 5){
 						pieceArray[i][j] = new Piece(false,this,i,j,"bomb");
 					}else{
 					 pieceArray[i][j] = new Piece(false,this,i,j,"pawn");
 					}

 				}
 			}
 			for(int j = 6; j < 7; j++){
 				for(int i = 0; i < 8; i += 2){
 					pieceArray[i][j] = new Piece(false,this,i,j,"shield");
 				}
 			}
 		}	
    }
/**This function is the get the piece at loacatio x, y **/
    public Piece pieceAt(int x, int y){
    	if(x < 0 || x > 7 || y < 0 || y > 7 || pieceArray[x][y] == null){
    		return null;
    	}
    	else{
    		return pieceArray[x][y];
    	}

    }
/**This Function is to place a piece P into a new location(x,y)**/

    public void place(Piece p, int x, int y){
    	if(x < 0 || x > 7 || y < 0 || y > 7){
    		;
    	}else if(pieceArray[x][y] != null){
        remove(x,y); // If there is piece at new location. remove it and place p there.
    		pieceArray[x][y] = p; 
    	}else{
        pieceArray[x][y] = p;
      }
    }
/**Helper functin to check if its a valid move for fire pieces**/
    private boolean validFireMove(int checkX, int checkY){
      if(prevX != -1){
        if((checkX == prevX + 1 && checkY == prevY + 1) || (checkX == prevX - 1 && checkY == prevY + 1))
          {return true;}
        else if((checkX == prevX + 2 && checkY == prevY + 2)){
          if(pieceArray[checkX-1][checkY-1]!=null && pieceArray[checkX -1 ][checkY - 1].isFire() == false){
            return true;
          }else{return false;}
        }
        else if((checkX == prevX - 2 && checkY == prevY + 2)){
          if(pieceArray[checkX+1][checkY - 1]!=null && pieceArray[checkX + 1 ][checkY - 1].isFire() == false){
            return true;
          }else{return false;}
        }
        return false;
      }
      return false;


    }
/**Helper functin to check if its a valid move for water king pieces**/

    private boolean validWaterKingMove(int kingX, int kingY){
      if(prevX != -1){
            if((kingX == prevX + 1 && kingY == prevY - 1) || (kingX == prevX - 1 && kingY == prevY - 1))
        {return true;}
      else if((kingX == prevX + 2 && kingY == prevY - 2)){
        if(pieceArray[kingX-1][kingY+1]!=null && pieceArray[kingX -1 ][kingY + 1].isFire()){
          return true;
        }
      }
      else if((kingX == prevX - 2 && kingY == prevY - 2)){
        if(pieceArray[kingX+1][kingY + 1]!=null && pieceArray[kingX + 1 ][kingY + 1].isFire()){
          return true;
        }
      }
      else if(prevX != -1){
        if((kingX == prevX + 1 && kingY == prevY + 1) || (kingX == prevX - 1 && kingY == prevY + 1))
          {return true;}
        else if((kingX == prevX + 2 && kingY == prevY + 2)){
          if(pieceArray[kingX-1][kingY-1]!=null && pieceArray[kingX -1 ][kingY - 1].isFire()){
            return true;
          }else{return false;}
        }
        else if((kingX == prevX - 2 && kingY == prevY + 2)){
          if(pieceArray[kingX+1][kingY - 1]!=null && pieceArray[kingX + 1 ][kingY - 1].isFire()){
            return true;
          }else{return false;}
        }
        return false;
      }
  }    return false;

    }
/**Helper functin to check if its a valid move for fire king pieces**/

    private boolean validFireKingMove(int kingX, int kingY){
    if(prevX != -1) {
      if((kingX == prevX + 1 && kingY == prevY - 1) || (kingX == prevX - 1 && kingY == prevY - 1))
        {return true;}
      else if((kingX == prevX + 2 && kingY == prevY - 2)){
        if(pieceArray[kingX-1][kingY+1]!=null && pieceArray[kingX -1 ][kingY + 1].isFire() == false){
          return true;
        }
      }
      else if((kingX == prevX - 2 && kingY == prevY - 2)){
        if(pieceArray[kingX+1][kingY + 1]!=null && pieceArray[kingX + 1 ][kingY + 1].isFire() == false){
          return true;
        }
      }
      else if(prevX != -1){
        if((kingX == prevX + 1 && kingY == prevY + 1) || (kingX == prevX - 1 && kingY == prevY + 1))
          {return true;}
        else if((kingX == prevX + 2 && kingY == prevY + 2)){
          if(pieceArray[kingX-1][kingY-1]!=null && pieceArray[kingX -1 ][kingY - 1].isFire() ==false ){
            return true;
          }else{return false;}
        }
        else if((kingX == prevX - 2 && kingY == prevY + 2)){
          if(pieceArray[kingX+1][kingY - 1]!=null && pieceArray[kingX + 1 ][kingY - 1].isFire() == false ){
            return true;
          }else{return false;}
        }
        return false;
      }
  }
      return false;

    }
    /**Helper functin to check if its a valid move for water pieces**/

    private boolean validWaterMove(int checkWaterX, int checkWaterY){
    if(prevX != -1){
      if((checkWaterX == prevX + 1 && checkWaterY == prevY - 1) || (checkWaterX == prevX - 1 && checkWaterY == prevY - 1))
        {return true;}
      else if((checkWaterX == prevX + 2 && checkWaterY == prevY - 2)){
        if(pieceArray[checkWaterX-1][checkWaterY+1]!=null && pieceArray[checkWaterX -1 ][checkWaterY + 1].isFire()){
          return true;
        }
      }
      else if((checkWaterX == prevX - 2 && checkWaterY == prevY - 2)){
        if(pieceArray[checkWaterX+1][checkWaterY + 1]!=null && pieceArray[checkWaterX + 1 ][checkWaterY + 1].isFire()){
          return true;
        }
      }
    }
    return false;

    }
    /**This function is to check if I can select the piece I clicked on**/

    public boolean canSelect(int x, int y){
    	if(player1){ // If its player1's turn
        if(pieceArray[x][y] != null && pieceArray[x][y].isFire()){
  	    	if(selected == false){
  	    		return true;
  	    	}
  	    	else if (moved == false){ 
  	    		return true;
  	    	}
  	    	else{return false;}
  	    	}
  	   else if(pieceArray[x][y] == null){ 
  	    	if(selected && moved == false){
            if(pieceArray[prevX][prevY]!=null && pieceArray[prevX][prevY].isKing()){ 
                return validFireKingMove(x,y);
            }else{
            return validFireMove(x,y);
  	    	  }
          }
          else if(moved == true){
            if(pieceArray[prevX][prevY] != null && pieceArray[prevX][prevY].hasCaptured()){

              if(( x == prevX + 2 && y == prevY + 2) && pieceArray[prevX+1][prevY+1]!= null && pieceArray[prevX+1][prevY+1].isFire()==false)
                {return true;}
              else if(( x == prevX - 2 && y == prevY + 2) && pieceArray[prevX-1][prevY+1]!= null && pieceArray[prevX-1][prevY+1].isFire()==false){
                  return true;}
              else if(( x == prevX + 2 && y == prevY - 2) && pieceArray[prevX+1][prevY-1]!= null && pieceArray[prevX+1][prevY-1].isFire() == false)
                {return true;}
              else if(( x == prevX - 2 && y == prevY - 2) && pieceArray[prevX-1][prevY-1]!= null && pieceArray[prevX-1][prevY-1].isFire() == false){
                  return true;}
              }
            }
          }
        } 
          //player's two/       
      else{

        if(pieceArray[x][y] != null && pieceArray[x][y].isFire()==false){
          if(selected == false){
            return true;
          }
          else if (moved == false){ 
            return true;
          }
          else{return false;}
          }
       else if(pieceArray[x][y] == null){
          if(selected && moved == false){
             if(pieceArray[prevX][prevY]!=null && pieceArray[prevX][prevY].isKing()){
                return validWaterKingMove(x,y);
            }else{
              return validWaterMove(x,y);
            }
          }
          else if(moved){
            if(pieceArray[prevX][prevY]!= null && pieceArray[prevX][prevY].hasCaptured()){
              if(( x == prevX + 2 && y == prevY - 2) && pieceArray[prevX+1][prevY-1]!= null && pieceArray[prevX+1][prevY-1].isFire())
                {return true;}
              else if(( x == prevX - 2 && y == prevY - 2) && pieceArray[prevX-1][prevY-1]!= null && pieceArray[prevX-1][prevY-1].isFire()){
                  return true;}
              else if(( x == prevX + 2 && y == prevY + 2) && pieceArray[prevX+1][prevY+1]!= null && pieceArray[prevX+1][prevY+1].isFire())
                {return true;}
              else if(( x == prevX - 2 && y == prevY + 2) && pieceArray[prevX-1][prevY+1]!= null && pieceArray[prevX-1][prevY+1].isFire()){
                  return true;}
          }
        }
      }
    }
                   return false;

  }
  /**End turn function if I can end turn**/
    public void endTurn(){
    		if(player1 == true){
    			player1 = false;
    		}
    		else { player1 = true;}
      selected = false;
      moved = false;
      if(pieceArray[prevX][prevY] != null){
        pieceArray[prevX][prevY].doneCapturing();
      }
    }
  /**Check if I can end turn**/

    public boolean canEndTurn(){
    	if(moved || (pieceArray[canSelectX][canSelectY] != null && pieceArray[canSelectX][canSelectY].hasCaptured())){
          return true;
      }else{return false;}
    }
    public void select(int x, int y){
       	canSelectX = x;
       	canSelectY = y;

       	selected = true;                       
         	if(pieceArray[x][y] == null && prevX != -1 ){
            if(pieceArray[prevX][prevY]!=null) {
         		   pieceArray[prevX][prevY].move(x,y);
               pieceArray[prevX][prevY] = null;
         		moved = true;
          }
         }
          prevX = x;
          prevY = y;
         
     }
  /**Remove the piece at location(x,y)**/   
    public Piece remove(int x, int y){
      if(x < 0 || x > 7 || y < 0 || y > 7){
        System.out.println("Out of Bound!");
        return null;
      }
      if(pieceArray[x][y] == null){
        System.out.println("There is no piece here was removed!");
        return null;
      }else{ 
        Piece temp = pieceArray[x][y];
        pieceArray[x][y] = null;
        return temp;}
    }
    /**Determine who is winner**/
    public String winner(){
      int water = 0; 
      int fire = 0;
      for(int i = 0; i < 8; i++){
        for (int j = 0; j < 8; j++)
          if(pieceArray[i][j]!=null && pieceArray[i][j].isFire()){
            fire += 1;
          }else if(pieceArray[i][j]!=null && pieceArray[i][j].isFire() == false){water += 1;}
      }
      if(water > 0 && fire == 0){
        hasWinner = true;
        return "water";
      }
      else if(fire > 0 && water == 0){
        hasWinner = true;
        return "fire";
      }
      else if(fire == 0 && water == 0){
        return "no one";
      }else{
        return null;
      }
      }

 public static void main(String[] args) {
 		int N = 8;
 	
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board test = new Board(false);
        test.drawBoard(N,0,0,0);

        while(!test.hasWinner) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(test.canSelect((int) x, (int) y)){
                  test.drawBoard(N,1,(int) x, (int) y);
                  test.select((int) x, (int) y); 
                  test.drawBoard(N,1,test.canSelectX,test.canSelectY);
                  test.prevX = (int) x;
                  test.prevY = (int) y;
                  }   
 				          }
                        StdDrawPlus.show(100);              
            if(StdDrawPlus.isSpacePressed()){
                if(test.canEndTurn()){
                  test.endTurn();
                  test.drawBoard(N,0,0,0);
                  System.out.println(test.winner());
                      }
                    }
  
          }

       }
    }          
        



	



