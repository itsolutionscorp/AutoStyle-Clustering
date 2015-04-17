
/** 
 *  @author Hangpeng Zhao
 */

public class Board {
    private Piece[][] pieces;
    private boolean fireTurn = true;
    private boolean moved = false;
    private boolean selected = false;
    private int s_x = 0;
    private int s_y = 0;
    private boolean alive = true;
    public Board(boolean shouldBeEmpty) {
        if(shouldBeEmpty == true){
        	pieces = new Piece[8][8];
        }
        else{
            //init board 
        	pieces = new Piece[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(i % 2 == 1 && j == 7){
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                     if(i % 2 == 0 && j == 6){
                         this.pieces[i][j] = new Piece(false, this, i, j, "shield");
                     }
                     if(i % 2 == 1 && j == 5){
                         this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
                     }
                     if(i % 2 == 0 && j == 0){
                         this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
                     }
                     if(i % 2 == 1 && j == 1){
                         this.pieces[i][j] = new Piece(true, this, i, j, "shield");
                     }
                     if(i % 2 == 0 && j == 2){
                         this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                }     	
           }
                
        }
    }

    private void drawEmptyBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	paintOriginColor(i, j);
            }
        }
    }
    private void paintOriginColor(int x, int y){
    	if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    }
    private void drawBoard(int N){
        drawEmptyBoard(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	drawPiece(i, j);
            }
        }
    }
    private boolean checkoutBound(int x, int y){
    	if( 0 > x || x > 7 || y > 7 || 0 > y ){
    		return false;
    	}else{
    		return true;
    	}
    	
    }
    private void drawPiece(int i, int j){
        if (pieces[i][j] != null){
        	if(pieces[i][j].isKing() == false){
        		if(pieces[i][j].isFire() == true){
            		if(pieces[i][j].isBomb())
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            		else if(pieces[i][j].isShield()== true)
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            		else
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        		}else{
            		if(pieces[i][j].isBomb())
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            		else if(pieces[i][j].isShield()== true)
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            		else
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
        		}
        	}else{
        		if(pieces[i][j].isFire() == true){
            		if(pieces[i][j].isBomb())
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            		else if(pieces[i][j].isShield()== true)
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            		else
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
        		}else{
            		if(pieces[i][j].isBomb())
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            		else if(pieces[i][j].isShield()== true)
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            		else
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
        		}
        	}
        }
    }
    public Piece pieceAt(int x, int y){
    	if(checkoutBound(x, y))
    		return this.pieces[x][y];
    	else
    		return null;
    }
    //fix bugs!!
    public boolean canSelect(int x, int y){
    	if(checkoutBound(x, y)==false){
    		return false;
    	}
    	if(pieces[x][y] != null && (this.pieces[x][y].isFire() == fireTurn) && this.selected == false){
    		if(this.selected == false && this.moved == false){
    			return true;
    		}else{
    			return false;
    		}
    	}else{
    		if(selected == true && this.moved == false){
        		if(pieces[x][y] != null && (this.pieces[x][y].isFire() == fireTurn))
        			return true;
        		else if(validMove(x, y)){
    				return true;
    			}else{
    				return false;
    			}
    		}else{
    			//need more coding right here
    			if(pieces[s_x][s_y]!= null && validMove(x, y) && this.moved == true && pieces[s_x][s_y].hasCaptured() == true){
    				return true;
    			}else
    				return false;
    		}	
    	}
    	
    }
    //hard method
    private boolean validMove(int x, int y){
    	if(pieceAt(x, y) != null){
    		return false;
    	}else if((Math.abs(x - s_x) == 2 && Math.abs(y - s_y) == 2) ){
    		if(pieces[(x + s_x) / 2][(y + s_y) / 2] != null && 
    				pieces[(x + s_x) / 2][(y + s_y) / 2].isFire() != fireTurn && pieces[s_x][s_y].isKing()== true) //find capture
    			return true;
    		else if(fireTurn == true && (y - s_y) > 0 
    				&& pieces[(x + s_x) / 2][(y + s_y) / 2].isFire() != fireTurn)
    			return true;
    		else if(fireTurn == false && (y - s_y) < 0 
    				&& pieces[(x + s_x) / 2][(y + s_y) / 2].isFire() != fireTurn)
    			return true;
    		else
    			return false;
    	}else if((Math.abs(x - s_x) >= 2 || Math.abs(y - s_y) >= 2) ){
    		return false;
    	}else if((Math.abs(x - s_x) != 1 || Math.abs(y - s_y) != 1) ){
    		return false;
    	}else if(pieces[s_x][s_y]!= null && pieces[s_x][s_y].isKing() == false && fireTurn == true && (y - s_y) < 0){
    		return false;	
    	}else if(pieces[s_x][s_y]!= null && pieces[s_x][s_y].isKing() == false && fireTurn == false && (y - s_y) > 0){
    		return false;	
    	}else{
    		return true;
    	}
    }
    public void select(int x, int y){
    	//need to change the condition to fit the capturing
    	if(selected == false){
    		selected = true;
    		s_x = x;
    		s_y = y;
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    		StdDrawPlus.filledSquare(x+.5, y+.5, .5);
    		drawPiece(x, y);
    	}else if(moved == false && pieces[x][y] != null){
    		paintOriginColor(s_x, s_y);
    		drawPiece(s_x, s_y);
    		s_x = x;
    		s_y = y;
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    		StdDrawPlus.filledSquare(x+.5, y+.5, .5);
    		drawPiece(x, y);		
    	}else{
    		this.pieces[this.s_x][this.s_y].move(x, y);
    		s_x = x;
    		s_y = y;
    		moved = true;
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);			//check
    		StdDrawPlus.filledSquare(s_x+.5, s_y+.5, .5);
    		if(pieces[s_x][s_y]!=null && 
    				pieces[s_x][s_y].hasCaptured() == true && pieces[s_x][s_y].isBomb()==true){
    			alive = false;//leave a message
    			if(pieces[s_x][s_y] != null)
    				remove(s_x, s_y); //self killed
    			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    		}else{
    			drawPiece(s_x, s_y);
    			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    		}
    	}
    }
    public void place(Piece p, int x, int y){
    	if(p!= null){
    		this.pieces[x][y] = p;
    	}
    }
    public Piece remove(int x, int y){
    	Piece temp = pieces[x][y];
    	this.pieces[x][y] = null;
    	paintOriginColor(x, y);
    	return temp;
    }
    public boolean canEndTurn(){
    	if(this.moved == true){
    		return true;
    	}else{
    		return false;
    	}
    }
    public void endTurn(){
		if(fireTurn == true){
			fireTurn = false;
		}else{
			fireTurn = true;
		}
		if(alive == true)
			pieces[s_x][s_y].doneCapturing();
    	this.moved = false;
    	this.selected = false;
    }
	public String winner(){
		int PieceFire = 0;
		int PieceWater = 0;
		for(int i = 0; i < 8; i++){
			for ( int j = 0; j < 8; j++){
				if(pieces[j][i] != null ){
					if(pieces[j][i].isFire()){
						PieceFire++;}
					else{
						PieceWater++;}
				}
			}
		}
		if(PieceFire == 0 && PieceWater > 0){
			return "Water";}
		else if(PieceFire > 0 && PieceWater == 0){
			return "Fire";}
		else if( PieceFire == 0 && PieceWater == 0){
			return "No one";}
		else {
			return null;}
	}
    public static void main(String[] args) {
        int N = 8;
        int x = 0;
        int y = 0;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board newboard = new Board(false);
        newboard.drawBoard(N);
        while(newboard.winner() == null){
        	while(StdDrawPlus.isSpacePressed() == false) {
        		if (StdDrawPlus.mousePressed()) {
	            	x = (int)StdDrawPlus.mouseX();
	            	y = (int)StdDrawPlus.mouseY();
	                if(newboard.canSelect(x, y)){
	                	newboard.select(x, y);
	                }
	                StdDrawPlus.show(25);  
        		}
        	}
        	if(newboard.canEndTurn() == true){
        		StdDrawPlus.isSpacePressed();
        		newboard.endTurn();
        		newboard.drawBoard(N);
        		StdDrawPlus.show(10);
        	}
        }
        System.out.println(newboard.winner());
    }
}