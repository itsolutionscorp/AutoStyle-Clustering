import static org.junit.Assert.*;
import org.junit.Test;
public class Board{
	  private  Piece[][] pieces = new Piece[8][8];//changed from boolean array to Piece array
	  private boolean isFire = true;
      private int [] selPos = {-1,-1};
	  private boolean movedPiece = false;
      private boolean capPiece = false;

     public Board(boolean shouldBeEmpty){
     	if (!shouldBeEmpty){
            this.startUp();
     	}
     }
    private void drawBoard(int N) {
        int [] pos = new int [2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece holder = pieces[i][j];
                pos[0] = i;
                pos[1] = j;
                if (holder != null){
                	drawPiece(holder, pos);
                }             
            }
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
    	//need different cases for fire and water pieces - done

        if(xi < 0 || yi < 0 || xf < 0 || yf < 0 || xi>7 || xf > 7 || yi > 7 || yf > 7){
            return false;
        }
        Piece holder = pieces[xi][yi];
    	Piece occupied = pieces[xf][yf];
    	boolean crowned = holder.isKing();
    	if(occupied != null){
    		return false;		
    	}
    	//case for regular move
    	if((Math.abs(xf - xi) == 1)){
            if(crowned && (Math.abs(yf - yi) == 1)){
                return true;
            }
            else if(holder.isFire()){
                if(yf - yi == 1){
                    return true;
                }
            }
            else if (!holder.isFire()){
                if(yf - yi == -1){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validCap(int xi, int yi, int xf, int yf){
        //need different cases for fire and water pieces 

        if(xi < 0 || yi < 0 || xf < 0 || yf < 0 || xi>7 || xf > 7 || yi > 7 || yf > 7){
            return false;
        }
        Piece holder = pieces[xi][yi];
    	Piece occupied = pieces[xf][yf];
    	boolean crowned = holder.isKing();
        int xDiff = xf - xi;
        int yDiff = yf - yi;
        Piece middle = pieces[xi + xDiff/2][yi + yDiff/2];
    	/*
        *Makes sure that the end square is not occupied
        *Also makes sure that the middle square(in the diagonal) is occupied
        *Also makes sure that the piece in the middle square is of the opposite color
        */
        if(occupied != null || middle == null || middle.side() == holder.side()){
    		return false;		
    	}

        //test for crowned capture
        if((Math.abs(xDiff) == 2) && crowned && (Math.abs(yDiff) == 2)){
            return true;
        }


    	//test for regular capture 
    	if(Math.abs(xDiff) == 2){           
            if(holder.isFire() && yDiff == 2){
                return true;
            }
            else if (!holder.isFire() && yDiff == -2){
                return true;
            }
    	}    	
    	return false;
    }

    public boolean canSelect(int x, int y){
        Piece selected = pieceAt(selPos[0], selPos[1]);
       
          if(x < 0 || y < 0 || x > 7 || y > 7){
            return false;
        }
        if(pieces[x][y] != null && pieces[x][y].isFire() == isFire){
    		if(!movedPiece){
    			return true;
    		}
    	}
    	else{
    		if(selected != null && pieces[x][y] == null){
    			if(!movedPiece){
    				return (validMove(selPos[0], selPos[1], x, y) || validCap(selPos[0], selPos[1], x, y));
    			}
    			else if(selected.hasCaptured()){
    				return validCap(selPos[0], selPos[1], x, y);
    			}
    			//if valid move for piece - method for piece or board
    			//need an instance var to determine which piece has been selected - done

    			//also works if valid capture location
    		}    		
    	}

     
    	return false;
    }

    public Piece pieceAt(int x, int y){
    	if(x > 7 || y > 7 || x < 0 || y < 0){
    		return null;
    	}
    	return pieces[x][y];
    }

    public void select(int x, int y){
        Piece selected = pieceAt(selPos[0], selPos[1]);
        Piece holder = pieceAt(x,y);
        //shouldn't move every time, only if an empty square is selected.

        //alternatively use place and remove methods
 	   	if (selected != null){
 	   	 	// pieces[(selPos[0])][(selPos[1])] = null;
 	   	 	// pieces[x][y] = selected;

            if(holder == null){
                selected.move(x,y);
                movedPiece = true;
                if (selected.hasCaptured()){
                    capPiece = true;
                }
            }            

 	   	} 	
    	selPos[0] = x;
    	selPos[1] = y;
    }

    public boolean canEndTurn(){
    	if(movedPiece || capPiece){
    		return true;
    	}
    	return false;
    }

    public void endTurn(){
       Piece selected = pieceAt(selPos[0], selPos[1]);

        // if(canEndTurn()){//dont think this line needs to be here
            isFire = !isFire;
            if(selected != null){
                selected.doneCapturing();
            }
        	selPos[0] = -1;
        	selPos[1] = -1;
        	movedPiece = false;
            capPiece = false;
        // }
    }

    public void place(Piece p, int x, int y){
    	//why do I need this when I have select
    	if(x > 7 || y > 7 || p == null || x < 0 || y < 0){
    		
    	}
    	else{
    		pieces[x][y] = p;
    	}
    }

    public Piece remove(int x, int y){
    	if(x > 7 || y > 7){
    		System.out.println("Tried to remove from an invalid position");
    		return null;
    	}
    	Piece holder = pieces[x][y];
    	pieces[x][y] = null;
    	return holder;
    }

    private void drawPiece(Piece holder, int[] pos){
    	String str_name = "";	
    	//a switch stmnt would have been cleaner	
    	if (holder.isBomb()){
    		str_name = "bomb";
    	}    
    	else if (holder.isShield()) {
    		str_name = "shield";    		
    	}
    	else{
    		str_name = "pawn";
    	}
    	if(holder.isFire()){
    		str_name += "-fire";
    	}
    	else{
    		str_name += "-water";
    	}
    	if(holder.isKing()){
    		str_name += "-crowned";
    	}
        StdDrawPlus.picture(pos[0] + .5, pos[1] + .5, "img/"+str_name+".png", 1, 1);    	
    }

    private void startUp(){
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i< 8; i++) {

				//set up fire pieces
        		if (j == 0){
        			if (i%2 == 0){
        				pieces[i][j] = new Piece(true, this, i, j, "pawn");
        			}
        		}

        		if(j == 1){
        			if (Math.abs(i%2) == 1){
        				pieces[i][j] = new Piece(true, this, i, j, "shield");
        			}
        		} 

				if(j == 2){
        			if (i%2 == 0){
        				pieces[i][j] = new Piece(true, this, i, j, "bomb");
        			}
        		} 

				//set up water pieces
				if (j == 5){
        			if (Math.abs(i%2) == 1){
        				pieces[i][j] = new Piece(false, this, i, j, "bomb");
        			}
        		}

				if (j == 6){
        			if (Math.abs(i%2) == 0){
        				pieces[i][j] = new Piece(false, this, i, j, "shield");
        			}
        		}

				if (j == 7){
        			if (Math.abs(i%2) == 1){
        				pieces[i][j] = new Piece(false, this, i, j, "pawn");
        			}
        		}
            }        
        }
    }

    public String winner(){
    	String result = null;
    	int firePieces = numPieces(0);
    	int waterPieces = numPieces(1);
    	if(firePieces == 0 && waterPieces == 0){
    		result = "No one";
    	}
    	else if (firePieces > 0 && waterPieces == 0) {
    		result = "Fire";
    	}
        else if (waterPieces > 0 && firePieces == 0){
    		result = "Water";
        }
	    return result;
    }

    private int numPieces(int side){
    	int alive = 0;
    	Piece selected;
    	for(int i = 0; i < 8; i++){
    		for(int j = 0; j < 8; j++){
    			selected = pieces[i][j];
    			if((selected != null) && (selected.side() == side)){
    				alive++;
    			}
    		}
    	}
    	return alive;
    }

    public static void main(String[] args) {
        Board starter = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
 

        while(starter.winner() == null) {
            starter.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                if(starter.canSelect((int)x,(int)y)){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare((int)x + .5 , (int)y + .5  , .5);
                    starter.select((int)x,(int)y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(starter.canEndTurn()){
                    starter.endTurn();
                }
            } 
            StdDrawPlus.show(10);

        }            
            starter.drawBoard(N);
            StdDrawPlus.show(10);        
    }
}