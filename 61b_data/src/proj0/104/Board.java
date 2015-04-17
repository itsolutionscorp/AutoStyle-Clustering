public class Board {
		private boolean isSelected = false;
		private boolean hasMoved = false;
		private int playerTurn = 0;
		private Piece[][] pieces;
		private int select_x = -1;
		private int select_y = -1;
		private int fire_counter = 12;
		private int water_counter = 12;
		private String winner = null;

/*                _       
  _ __ ___   __ _(_)_ __  
 | '_ ` _ \ / _` | | '_ \ 
 | | | | | | (_| | | | | |
 |_| |_| |_|\__,_|_|_| |_|
*/
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);	
		while (true) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
			    int mouse_x = (int) StdDrawPlus.mouseX();
                int mouse_y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(mouse_x, mouse_y)) {
                	b.select(mouse_x, mouse_y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
            	b.endTurn();
            }

            StdDrawPlus.show(100);

		}
	}

/*
 ____                      _  
| __ )  ___   __ _ _ __ __| | 
|  _ \ / _ \ / _` | '__/ _` | 
| |_) | (_) | (_| | | | (_| | 
|____/ \___/ \__,_|_|  \__,_|
*/

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty) {
			return;
		}
		else {
			//Initialize board with default configuration.
			int counter = 0;
			int x = 0;
			int y = 0;
			while (counter < 4) {
				place(new Piece(true, this, x, y, "pawn"), x, y);
				place(new Piece(true, this, x+1, y+1, "shield"), x+1, y+1);
				place(new Piece(true, this, x, y+2, "bomb"), x, y+2);
				place(new Piece(false, this, x+1, y+5, "bomb"), x+1, y+5);
				place(new Piece(false, this, x, y+6, "shield"), x, y+6);
				place(new Piece(false, this, x+1, y+7, "pawn"), x+1, y+7);
				x += 2;
				counter += 1;
			}
		}
	}
//*******************************************************************//
/*   _                    ____                      _  
  __| |_ __ __ ___      _| __ )  ___   __ _ _ __ __| | 
 / _` | '__/ _` \ \ /\ / /  _ \ / _ \ / _` | '__/ _` | 
| (_| | | | (_| |\ V  V /| |_) | (_) | (_| | | | (_| | 
 \__,_|_|  \__,_| \_/\_/ |____/ \___/ \__,_|_|  \__,_|
*/
    private void drawBoard() {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                if (i == select_x && j == select_y) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {   

				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
	                	if (pieces[i][j].isShield()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire-crowned.png", 1, 1);
	                		}
	                		else {
	                		StdDrawPlus.picture(i+.5,j+.5, "img/shield-fire.png", 1, 1);
	                		}
	                	}
	                		// if (pieces[i][j].isKing()) {
	                		// 	StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire-crowned.png", 1, 1);
	                		// }
	                	else if (pieces[i][j].isBomb()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire-crowned.png", 1, 1);
	                		}
	                		StdDrawPlus.picture(i+.5,j+.5, "img/bomb-fire.png", 1, 1);
	                	}
	                	else {
	                		// if (pieces[i][j].isKing()) {
	                		// 	StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png", 1, 1);
	                		// }
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png", 1, 1);
	                		}
	                		StdDrawPlus.picture(i+.5,j+.5, "img/pawn-fire.png", 1, 1);
	                	}
                	}
 
					else {
	                	if (pieces[i][j].isShield()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i+.5, j+.5, "img/shield-water-crowned.png", 1, 1);
	                		}
	                		StdDrawPlus.picture(i+.5,j+.5, "img/shield-water.png", 1, 1);
	                	}
	                	else if (pieces[i][j].isBomb()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water-crowned.png", 1, 1);
	                		}
	                		StdDrawPlus.picture(i+.5,j+.5, "img/bomb-water.png", 1, 1);
	                	}
	                	else {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water-crowned.png", 1, 1);
	                		}
	                		StdDrawPlus.picture(i+.5,j+.5, "img/pawn-water.png", 1, 1);
                		}  
                	}         		
           		}
			}
		}
    }
    
//*******************************************************************//
    public Piece pieceAt(int x, int y) {
    	if(isInbounds(x,y)) return pieces[x][y];
    	return null;
    }

    public boolean canSelect(int x, int y) {
    	if (isInbounds(x, y) && (pieces[x][y] != null) && pieces[x][y].side() == playerTurn) {
    		if(!isSelected) return true;
    		else if(!hasMoved) return true;
		}


		
    	else if (isInbounds(x,y) && (pieces[x][y] == null) && isSelected) { //IF SQUARE IS EMPTY
	    	if(!hasMoved && validMove(select_x,select_y,x,y)) return true;
	    	else if(hasMoved && pieces[select_x][select_y] != null && pieces[select_x][select_y].hasCaptured()){
	    		int delta_x = Math.abs(select_x-x);
	    		if(delta_x == 2){
	    			if(validMove(select_x,select_y,x,y)) {
	    				return true;
	    			}
	    		}
	    	}
	    }
	   	return false;
	}

    public void select(int x, int y) {

    	if (isSelected == true && validMove(select_x, select_y, x, y)) {
    		pieces[select_x][select_y].move(x, y);

    		hasMoved = true;
    		int delta_x = Math.abs(select_x-x);
    		select_x = x;
    		select_y = y;
    		isSelected = true;
       		if (delta_x==2 && pieces[select_x][select_y].isBomb()) {
    			isSelected = false;
    			select_x = -1;
    			select_y = -1;
    			explode(x, y, pieces[x][y].side());
    		}
    	}
    	else{
    		select_x = x;
    		select_y = y;
    		isSelected = true;
    		hasMoved = false;
    	}	
    }

    public void place(Piece p, int x, int y) {
		if (isInbounds(x,y) && p != null ) {
			pieces[x][y] = p;
		}
    	
    }

    public Piece remove(int x, int y) {
    	Piece save;
    	if (!isInbounds(x, y)) {
    		System.out.println("x and y must be between 0 and 7!");
    		return null;
    	}
    	else if (pieces[x][y] == null) {
    		System.out.println("There's no piece at (" + x + ", " + y + ")!");
    		return null;
    	}
    	else {
    		save = pieces[x][y];
    		pieces[x][y] = null;
    		return save;
    	}
    }

    public boolean canEndTurn() { 
    	return hasMoved;
    }

    public void endTurn() {
    	if (canEndTurn()) {
            	winner(); 
    	
    		isSelected = false;
    		hasMoved = false;
    		if(isInbounds(select_x,select_y) && pieces[select_x][select_y] != null){
    			pieces[select_x][select_y].doneCapturing();
    		}
    		playerTurn = (playerTurn + 1) % 2;
    		select_y = -1;
    		select_x = -1;
    	}
    }

    private void countNumPieces(){

    	fire_counter = 0;
    	water_counter = 0;
    	for(int i=0; i<8;i++){
    		for(int j=0; j<8;j++){
    			if(pieces[i][j] != null){
    				if(pieces[i][j].side() == 0){
    					fire_counter ++;
    				}
    				else{
    					water_counter ++;
    				}
    			}
    		}
    	}

    }
    public String winner() {////////////////////////////////////
    	countNumPieces();
    	if (fire_counter == 0 && water_counter == 0) {
            		winner = "No one";
        }
    	else if (fire_counter == 0) {
    		winner = "Water";
    	}
    	else if (water_counter == 0) {
    		winner = "Fire";
    	}
    	else{
    		winner = null;
    	}
    	System.out.println(winner);
    	return winner;
    }

    private boolean isInbounds(int x, int y) {
    	if (x > 7 || x < 0 || y > 7 || y < 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
/////////////////////////
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	if (isInbounds(xf, yf) && isInbounds(xi, yi) && pieces[xi][yi] != null && pieces[xf][yf] == null) {
    		if (pieces[xi][yi].side() == 0) {
	    		if (pieces[xi][yi].isKing()) {
	    			if ((xf == xi + 1) && (yf == yi + 1)) {
	    				return true;
	    			}
	    			if ((xf == xi - 1) && (yf == yi + 1)) {
	    				return true;
	    			}
	    			if ((xf == xi + 2) && (yf == yi + 2)) {
	    				if (pieces[xi+1][yi+1] != null && (pieces[xi][yi].side() != pieces[xi+1][yi+1].side())) {
	    					return true;
	    				} 
	    			}
	    			if ((xf == xi - 2) && (yf == yi + 2)) {
	    				if (pieces[xi-1][yi+1] != null && (pieces[xi][yi].side() != pieces[xi-1][yi+1].side())) {
	    					return true;
	    				} 
	    			}
	    			// SPECIAL KING CASES
	    			if ((xf == xi - 1) && (yf == yi - 1)) {
	    				return true;
	    			}
	    			if ((xf == xi + 1) && (yf == yi - 1)) {
	    				return true;
	    			}
	    			if ((xf == xi - 2) && (yf == yi - 2)) {
	    				if (pieces[xi-1][yi-1] != null && (pieces[xi][yi].side() != pieces[xi-1][yi-1].side())) {
	    					return true;
	    				} 
	    			}
	    			if ((xf == xi + 2) && (yf == yi - 2)) {
	    				if (pieces[xi+1][yi-1] != null && (pieces[xi][yi].side() != pieces[xi+1][yi-1].side())) {
	    					return true;
	    				} 
	    			}
	    		}
	    		else {
	    			if ((xf == xi + 1) && (yf == yi + 1)) {
	    				return true;
	    			}
	    			if ((xf == xi - 1) && (yf == yi + 1)) {
	    				return true;
	    			}
	    			if ((xf == xi + 2) && (yf == yi + 2)) {
	    				if (pieces[xi+1][yi+1] != null && (pieces[xi][yi].side() != pieces[xi+1][yi+1].side())) {
	    					return true;
	    				} 
	    			}
	    			if ((xf == xi - 2) && (yf == yi + 2)) {
	    				if (pieces[xi-1][yi+1] != null && (pieces[xi][yi].side() != pieces[xi-1][yi+1].side())) {
	    					return true;
	    				} 
	    			}
	    		}
	    	}
	    	else {
	    			 if (pieces[xi][yi].isKing()) {
	    			if ((xf == xi + 1) && (yf == yi - 1)) {
	    				return true;
	    			}
	    			if ((xf == xi - 1) && (yf == yi - 1)) {
	    				return true;
	    			}
	    			if ((xf == xi + 2) && (yf == yi - 2)) {
	    				if (pieces[xi+1][yi-1] != null && (pieces[xi][yi].side() != pieces[xi+1][yi-1].side())) {
	    					return true;
	    				} 
	    			}
	    			if ((xf == xi - 2) && (yf == yi - 2)) {
	    				if (pieces[xi-1][yi-1] != null && (pieces[xi][yi].side() != pieces[xi-1][yi-1].side())) {
	    					return true;
	    				} 
	    			}
	    			// SPECIAL KING CASES
	    			if ((xf == xi - 1) && (yf == yi + 1)) {
	    				return true;
	    			}
	    			if ((xf == xi + 1) && (yf == yi + 1)) {
	    				return true;
	    			}
	    			if ((xf == xi - 2) && (yf == yi + 2)) {
	    				if (pieces[xi-1][yi+1] != null && (pieces[xi][yi].side() != pieces[xi-1][yi+1].side())) {
	    					return true;
	    				} 
	    			}
	    			if ((xf == xi + 2) && (yf == yi + 2)) {
	    				if (pieces[xi+1][yi+1] != null && (pieces[xi][yi].side() != pieces[xi+1][yi+1].side())) {
	    					return true;
	    				} 
	    			}
	    		}
	    		else {
	    			if ((xf == xi + 1) && (yf == yi - 1)) {
	    				return true;
	    			}
	    			if ((xf == xi - 1) && (yf == yi - 1)) {
	    				return true;
	    			}
	    			if ((xf == xi + 2) && (yf == yi - 2)) {
	    				if (pieces[xi+1][yi-1] != null && (pieces[xi][yi].side() != pieces[xi+1][yi-1].side())) {
	    					return true;
	    				} 
	    			}
	    			if ((xf == xi - 2) && (yf == yi - 2)) {
	    				if (pieces[xi-1][yi-1] != null && (pieces[xi][yi].side() != pieces[xi-1][yi-1].side())) {
	    					return true;
	    				} 
	    			}
	    		}
	    	}

    	}

    	return false;
    }

private void explode(int x, int y, int side) {
	for(int i=x-1;i<=x+1; i++) {
		for(int j=y-1; j<=y+1; j++) {
			if (isInbounds(i,j) && pieces[i][j] != null && !pieces[i][j].isShield() && side != pieces[i][j].side()) {
				remove(i, j);
			}
		}
	}
	remove(x,y);
}
}


