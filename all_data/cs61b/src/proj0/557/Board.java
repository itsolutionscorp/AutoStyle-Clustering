public class Board {
	private Piece [][] GameGrid ;
    private int WhosTurn ; 
    private boolean hasSelected ;
    private boolean hasmoved ;
    private int[] SelectedTile ;


	public static void main( String[] args){
		/*initiating the board**/
		StdDrawPlus.setCanvasSize() ;
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);	
		Board GameWorld = new Board(false);

		while(GameWorld.winner() == null){
			if(StdDrawPlus.mousePressed() == true){
				int xClicked = (int)StdDrawPlus.mouseX() ;
				int yClicked = (int)StdDrawPlus.mouseY() ;
				System.out.println("x is " + xClicked + " and y is" + yClicked);
				if(GameWorld.canSelect(xClicked, yClicked) == true){
					GameWorld.select( xClicked, yClicked);
				}
			}
			if(StdDrawPlus.isSpacePressed() == true){
				if (GameWorld.canEndTurn() == true){
					GameWorld.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}






		return;
	}
	public Board(boolean shouldBeEmpty){
		GameGrid = new Piece[8][8]; /** In the format of GameGrid[column][row] */
		WhosTurn = 0 ;  
		hasSelected = false ;
		hasmoved = false;
		SelectedTile = new int[] {50, 50};
		for (int x = 0 ; x < 8 ; x++) {
			for (int y = 0 ; y < 8 ; y++) {
				if ((x+y) % 2 == 0) 
					{StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else 
					{StdDrawPlus.setPenColor(StdDrawPlus.RED);}
					/**Set Pen Colour*/
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5 , 0.5);
				if (shouldBeEmpty == false){
				/**
				 *Enter if-else cases for places the pieces here later.
				 */
					if ((x == 0 && y == 0) || (x == 2 && y == 0) ||
						(x == 4 && y == 0) || (x == 6 && y == 0) ){
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
					}
					if ((x == 1 && y == 7) || (x == 3 && y == 7) ||
						(x == 7 && y == 7) || (x == 5 && y == 7) ){
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
					}
					if ((x == 1 && y == 1) || (x == 3 && y == 1) ||
						(x == 7 && y == 1) || (x == 5 && y == 1) ){
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
					}
					if ((x == 0 && y == 2) || (x == 2 && y == 2) ||
						(x == 4 && y == 2) || (x == 6 && y == 2) ){
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
					}
					if ((x == 0 && y == 6) || (x == 2 && y == 6) ||
						(x == 4 && y == 6) || (x == 6 && y == 6) ){
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
					}
					if ((x == 1 && y == 5) || (x == 5 && y == 5) ||
						(x == 3 && y == 5) || (x == 7 && y == 5) ){
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
					}
					/** Now places pieces in their initial GameGrid[column][row] */
					for (int i = 0 ; i < 8 ; i += 2){
						GameGrid[i][0] = new Piece(true, this, i, 0, "pawn");
					}
					for (int i = 1 ; i < 8 ; i += 2){
						GameGrid[i][1] = new Piece(true, this, i, 1, "shield");
					}
					for (int i = 0 ; i < 8 ; i += 2){
						GameGrid[i][2] = new Piece(true, this, i, 2, "bomb");
					}
					for (int i = 1 ; i < 8 ; i += 2){
						GameGrid[i][7] = new Piece(false, this, i, 7, "pawn");
					}
					for (int i = 0 ; i < 8 ; i += 2){
						GameGrid[i][6] = new Piece(false, this, i, 6, "shield");
					}
					for (int i = 1 ; i < 8 ; i += 2){
						GameGrid[i][5] = new Piece(false, this, i, 5, "bomb");
					}



				}

			}
		}


	}

	public void place(Piece p, int x, int y){
		if ( x > 8 || y > 8){
			return ;
		}
		this.GameGrid[x][y] = p ;
		SelectedTile = new int[] {x,y};
		this.hasmoved = true;
	}

	public Piece pieceAt(int x, int y){
		if ( x > 8 || y > 8){
			return null;
		}
		return this.GameGrid[x][y];
	}

	public boolean canEndTurn() {
		if (this.hasmoved == true){
 /** resets whether the turn can end*/
			return true;
		} 
		return false;
	}

	public void endTurn(){
		this.SelectedPiece().doneCapturing();
		this.WhosTurn = 1 - this.WhosTurn;
		hasSelected = false ;
		hasmoved = false;
	}

    public Piece remove(int x, int y) {
        if ( x < 8 && y < 8){

        	if (this.pieceAt(x,y) == null){
        		System.out.println("There's not a piece there.");
        	}

        	Piece goner = this.pieceAt(x,y);
        	this.GameGrid[x][y] = null;
        	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        	StdDrawPlus.filledSquare(x + 0.5, y + 0.5 , 0.5);

        	return goner ;
        }
        System.out.println("x and y coordinate out of this word(bound)!");
        return null;

    }

    public String winner(){
    	int firePieces = 0 ;
    	int waterPieces = 0 ;
    	for (int i = 0 ; i < 8 ; i++){
    		for (int j = 0 ; j <8 ; j++){
    			if (this.GameGrid[i][j] != null){
    				if (this.GameGrid[i][j].isFire() == true){
    					firePieces += 1;
    				} else {waterPieces += 1;}
    			}
    		}
    	}
    	if (firePieces <= 12){
    		if (waterPieces <= 12){
    			return "no one";
    		} else {return "water";}
    	}
    	if (waterPieces <= 12){
    		return "fire";
    	}

    	return null;
    }

    public boolean canSelect(int x, int y){


    	/** first, handle the case with selecting non empty square*/
    	if( this.GameGrid[x][y] != null){
    		if(this.hasmoved == false) { /** if nothing selected, or hasn't moved*/
    			if((this.GameGrid[x][y].isFire() == true && this.WhosTurn == 0)
    				|| (this.GameGrid[x][y].isFire() == false && this.WhosTurn == 1)){
    				return true ;
    			}
    		}
    	}

    	/** Now handle the case with selecting an empty square*/

    	if (this.pieceAt(x,y) == null && this.hasSelected == true && this.hasmoved == false && this.SelectedPiece().isFire() == true && this.WhosTurn == 0 ){
    		if(((SelectedTile[0]-1) == x && (SelectedTile[1]+1) == y) 
    			||((SelectedTile[0]+1) == x && (SelectedTile[1]+1) == y)){
    			return true;
    		}
    		if(this.SelectedPiece().isKing() == true){
    			if(((SelectedTile[0]-1) == x && (SelectedTile[1]-1) == y) 
    				||((SelectedTile[0]+1) == x && (SelectedTile[1]-1) == y)){
    				return true;
    			}    			
    		}
    	}
    	if (this.pieceAt(x,y) == null && this.hasSelected == true && this.hasmoved == false && this.SelectedPiece().isFire() == false && this.WhosTurn == 1 ){
    		if(((SelectedTile[0]-1) == x && (SelectedTile[1]-1) == y) 
    			||((SelectedTile[0]+1) == x && (SelectedTile[1]-1) == y)){
    			return true;
    		}
    		if(this.SelectedPiece().isKing() == true){
    			if(((SelectedTile[0]-1) == x && (SelectedTile[1]+1) == y) 
    				||((SelectedTile[0]+1) == x && (SelectedTile[1]+1) == y)){
    				return true;
    			}    			
    		}
    	}

    	if ((hasSelected == true) && ((hasmoved == false) ||(hasmoved == true && this.SelectedPiece().hasCaptured() == true))){
    		if (this.SelectedPiece().isFire() == true && this.WhosTurn == 0){
    			if( this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]+1) != null && this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]+1).isFire() == false 
    				&& (SelectedTile[0]+2) == x && (SelectedTile[1]+2) == y){
    				return true ;
    			}
    			if( this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]+1) != null && this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]+1).isFire() == false 
    				&& (SelectedTile[0]-2) == x && (SelectedTile[1]+2) == y){
    				return true ;
    			}
    			if( this.pieceAt(SelectedTile[0],SelectedTile[1]).isKing() == true){
    				if(this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]-1) != null && this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]-1).isFire() == false 
    					&& (SelectedTile[0]+2) == x && (SelectedTile[1]-2) == y){
    					return true ;
    				}
    				if(this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]-1) != null && this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]-1).isFire() == false 
    					&& (SelectedTile[0]-2) == x && (SelectedTile[1]-2) == y){
    					return true ;
    				}    				
    			} 

    		}
    		if (this.pieceAt(SelectedTile[0],SelectedTile[1]).isFire() == false && this.WhosTurn == 1){
    			if(this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]-1) != null && this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]-1).isFire() == true 
    				&& (SelectedTile[0]-2) == x && (SelectedTile[1]-2) == y){
    				return true ;
    			}
    			if(this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]-1) != null && this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]-1).isFire() == true  
    				&& (SelectedTile[0]+2) == x && (SelectedTile[1]-2) == y){
    				return true ;
    			}
    			if(this.pieceAt(SelectedTile[0],SelectedTile[1]).isKing() == true){
    				if(this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]+1) != null && this.pieceAt(SelectedTile[0]+1 ,SelectedTile[1]+1).isFire() == true  
    					&& (SelectedTile[0]+2) == x && (SelectedTile[1]+2) == y){
    					return true ;
    				}
    				if( this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]+1) != null && this.pieceAt(SelectedTile[0]-1 ,SelectedTile[1]+1).isFire() == true  
    					&& (SelectedTile[0]-2) == x && (SelectedTile[1]+2) == y){
    					return true ;
    				}    				
    			} 

    		}
    	}


    	return false ;
    }

    public void select(int x, int y){
    	if(this.pieceAt(x,y) == null){
    		this.SelectedPiece().move(x,y);
    	}
    	else { 
    		if(this.SelectedPiece() == null){
    		this.SelectedTile = new int[] {x, y}; /**select a new piece and highlight the square*/
    		StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
    		StdDrawPlus.filledSquare(x + 0.5, y + 0.5 , 0.5);
    		this.DrawPiece(this.SelectedPiece()); 
    		}  else{
    		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    		StdDrawPlus.filledSquare(this.SelectedTile[0] + 0.5, this.SelectedTile[1] + 0.5 , 0.5);
    		this.DrawPiece(this.SelectedPiece()) ;
    		this.SelectedTile = new int[] {x, y}; /**select a new piece and highlight the square*/
    		StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
    		StdDrawPlus.filledSquare(x + 0.5, y + 0.5 , 0.5);
    		this.DrawPiece(this.SelectedPiece()); }    			
    		} 			
    		 /**un-highlight the previously selected piece*/

    	}





    private void DrawPiece (Piece p){
    	String type  = "pawn";
    	if(p.isShield() == true){
    		type = "shield";
    	}
    	if(p.isBomb() == true){
    		type = "bomb";
    	}

    	String ImgName = "what yo" ;
    	if (p.isFire() == true){
    		if (type == "pawn"){
    			if(p.isKing() == true){
    				ImgName = "img/pawn-fire-crowned.png" ;
    			} else {ImgName = "img/pawn-fire.png" ;}
    		}
    		if (type == "bomb"){
    			if(p.isKing() == true){
    				ImgName = "img/shield-fire-crowned.png" ;
    			} else {ImgName = "img/bomb-fire.png" ;}
    		}
    		if (type == "shield"){
    			if(p.isKing() == true){
    				ImgName = "img/shield-fire-crowned.png" ;
    			} else {ImgName = "img/shield-fire.png" ;}
    		}
    	} else{
    		if (type == "pawn"){
    			if(p.isKing() == true){
    				ImgName = "img/pawn-water-crowned.png" ;
    			} else {ImgName = "img/pawn-water.png" ;}
    		}
    		if (type == "bomb"){
    			if(p.isKing() == true){
    				ImgName = "img/shield-water-crowned.png" ;
    			} else {ImgName = "img/bomb-water.png" ;}
    		}
    		if (type == "shield"){
    			if(p.isKing() == true){
    				ImgName = "img/shield-water-crowned.png" ;
    			} else {ImgName = "img/shield-water.png" ;}
    		}

    	}
    	StdDrawPlus.picture(this.SelectedTile[0] + .5, this.SelectedTile[1] + .5, ImgName, 1, 1);
    }





private Piece SelectedPiece() {
	if(this.SelectedTile[0] > 40){
		return null;
	}
		return this.pieceAt(this.SelectedTile[0], this.SelectedTile[1]);
}

}


  













/**    public void TempTestingMethod001(int fire, int water, Board b){
    	b.fireLostCount += fire ;
    	b.waterLostCount += water;
    }
*/