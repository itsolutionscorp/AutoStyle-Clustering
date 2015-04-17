public class Board {
	private boolean trufal;
	private int size = 8;
	private Piece[][] pieces = new Piece[size][size];
	private Piece selected = null;
	private Piece moved = null;
	private boolean turn = true;
	private int selectedposX;
	private int selectedposY;

	public Board(boolean shouldBeEmpty) {
		trufal = shouldBeEmpty;
		
		if (!trufal) {
		pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		pieces[6][0] = new Piece(true, this, 6, 0, "pawn"); 
		pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		pieces[7][1] = new Piece(true, this, 7, 1, "shield");
		pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
		pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		pieces[3][7] = new Piece(false, this, 3, 7, "pawn"); 
		pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		pieces[7][7] = new Piece(false, this, 7, 7, "pawn");	
		}
	}
	private void drawBoard(int size) {
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (trufal == false) {
                	if (pieceAt(i,j) != null) {
                	StdDrawPlus.picture(i + .5, j + .5, nameimage(pieceAt(i,j)), 1, 1);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Board booard = new Board(false);
		StdDrawPlus.setXscale(0, booard.size);
        StdDrawPlus.setYscale(0, booard.size);
        
		
		while (booard.trufal == false) {
			booard.drawBoard(booard.size);
			if (StdDrawPlus.mousePressed()) {
            	double x = StdDrawPlus.mouseX();
            	double y = StdDrawPlus.mouseY();
            	if (booard.canSelect((int) x, (int) y) ) {
            		booard.select((int) x, (int) y);
            		}
        		}
        	else if (StdDrawPlus.isSpacePressed()) {
        		if (booard.canEndTurn()) {
        			booard.endTurn();
        			}
        		}
			StdDrawPlus.show(25);
		}
		booard.winner();
	}

	public Piece pieceAt(int x, int y) {
		int i = 0;
		if (obcheck(x, y)) {
			return pieces[x][y];
			}
		else {
			return null;}
	}

	private boolean oktochoose(Piece p){
		return (p.isFire() == turn);
	}

	public boolean canSelect(int x, int y) {
		Piece selecting = pieceAt(x,y);
		/* choosing piece */
		if (selecting != null){
			//System.out.println("step for choosing piece");
			if (oktochoose(selecting)){
				//System.out.println("choose piece according to player's turn.");
				
				/*another piece was selected before*/
				if (selected != null) {
					//System.out.println("there is a piece chosen before");
					if (moved == null){
						//System.out.println("the piece already moved");
						return true;
						}
					
					else {
						//System.out.println("no movement before");
						return false;
					}
				}
				/*no piece selected before*/
				else {
					//System.out.println("no piece chosen before, so let's choose this piece");
					return true;
					}
				}
			/* wrong side piece to be chosen for this turn */
			else {
				//System.out.println("it is not your piece.");
				return false;
				}
			}
		/* choosing empty space */
		else {
			//System.out.println("choosing empty space");
			if (selected != null){
				//System.out.println("piece chosen");
				/* player already made move*/
				if (moved != null){
					//System.out.println("it has moved");
			 		/* and that move was capture*/
			 		if (moved.hasCaptured()) {
			 			//System.out.println("and it captured, so ok to capture more if possible");
			 			if ((Math.abs(selectedposX - x) == 2) && (Math.abs(selectedposY-y) == 2)) {
			 				int capX = (selectedposX+x)/2;
							int capY = (selectedposY+y)/2;
							Piece tobecaptured = pieceAt(capX, capY);
			 				
			 				if (tobecaptured != null) {
			 					//System.out.println("valid another capturing");
			 					if ( (selected.isKing()) || ( (selected.isFire()) && ( (y - selectedposY) >0)) || ( (selected.isFire()== false) && ((selectedposY-y) >0) )){
			 						return true;		
			 						}
			 					else {
			 						return false;
			 						}
			 					}
			 				
			 				else {
			 					//System.out.println("nothing to capture again, so no");
			 					return false;
			 					}
			 				}
			 			else {
			 				//System.out.println("not attempting to capture, so no");
			 				return false;
			 				}
			 			}
			 		else {
			 			//System.out.println("moved but not captured, so no");
			 			return false;
			 			}
					}
				/* no previous move */
				
				else {
					//System.out.println("not moved before, so ok to move or capture");
					if ((Math.abs(selectedposX - x) == 1) && (Math.abs(selectedposY-y) == 1) ){
						//System.out.println("trying to move");
						if ( (selected.isKing()) || ( (selected.isFire()) && ((y- selectedposY )> 0) ) || ( (selected.isFire() == false) && ((selectedposY - y) >0) )) {
							//System.out.println("valid move");
							return true;
							}
						else {
							//System.out.println("not a valid move");
							return false;
							}
					}
					else if ((Math.abs(selectedposX - x) == 2) && (Math.abs(selectedposY-y) == 2)){
						//System.out.println("trying to capture");
						int capX = (selectedposX+x)/2;
						int capY = (selectedposY+y)/2;
						Piece tobecaptured = pieceAt(capX, capY);
			 			if (tobecaptured != null){
			 				//System.out.println("piece to be captured, so a valid capture");
			 				if ( (selected.isKing()) || ( (selected.isFire()) && ((y- selectedposY )> 0) ) || ( (selected.isFire() == false) && ((selectedposY - y) >0) )){
			 					return true;
			 					}
			 				else{
			 					return false;
			 					}
			 				}
			 			else {
			 				//System.out.println("no piece to be captured, so not a valid capture attempt");
			 				return false;
			 				}
						}
					/* not a valid move*/
					else {
						//System.out.println("not trying to move or capture, so no");
						return false;
						}
					}
				}
			/* when no piece is selected, has to choose piece first.*/
			else{
				//System.out.println("choose piece first bro");
				return false;
				}
			}
	}
	private boolean obcheck(int x, int y) {
		if ( (x < 0) || (x > 7) || (y<0) || (y>7) ){
			return false;
		}
		else{
		return true;
		}
	}
	public void place(Piece p, int x, int y){
		if ( (obcheck(x,y)== false) || (p == null)) {
			}

		else {
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (pieceAt(i,j) == p) {
            			pieces[i][j] = null;
            		}
            	}
            }
			pieces[x][y] = p;
			selectedposY = y;
			selectedposX = x;
			}
		}

	public void select(int x, int y){
		Piece selecting = pieceAt(x,y);
		if (selecting != null) {
			selected = selecting;
			selectedposX = x;
			selectedposY = y;
			//System.out.println("done selecting piece");
		}
		else {
			selected.move(x,y);
			moved = selected;
			//System.out.println("done selecting space");
		}
		
	}
	
	public Piece remove(int x, int y){
		Piece out = pieceAt(x,y);
		if (out != null) {
			pieces[x][y] = null;
			return out;
		}
		else if (out == null) {
			System.out.println("no Piece to be removed");
			return null;
		}
		else{
			System.out.println("that is not board");
			return null;
		}
		
	}


	public boolean canEndTurn(){
		if (moved != null) {
			return true;
		}
		return false;
	}

	public void endTurn(){
		if (turn) {
			turn = false;}
		else{
			turn = true;
		}
		moved = null;
		selected = null;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieceAt(i,j) != null) {
            		pieceAt(i,j).doneCapturing();
            	}
        	}
		}
		if (winner()!= null) {
			trufal = true;
			}
	}
	public String winner(){
	int fires = 0;
	int waters = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	Piece count = pieceAt(i,j);
            	if (count != null){
            		if (count.isFire()) {
            			fires = fires + 1;
            			}
            		else {
            			waters = waters +1;
            			}
	            	}
	     		}
	    	}
		if ((fires ==0) || (waters == 0)) {
		
			if ((fires ==0) && (waters == 0)) {
				trufal = true;
				return "No One";
				}
			else if (fires > waters) {
				trufal = true;
				return "Fire";
				}
			else{
				trufal = true;
				return "Water";
				}
			}
		else {
			return null;
			}
	}

	private String nameimage(Piece p){
		if (p.isKing()) {
			if (p.isBomb()){
				if (p.isFire()){
					return "img/bomb-fire-crowned.png";
				}
				else {return "img/bomb-water-crowned.png";}
			}
			else if (p.isShield()) {
				if (p.isFire()){
					return "img/shield-fire-crowned.png";
				}
				else {return "img/shield-water-crowned.png";}
			}
			else {
				if (p.isFire()){
					return "img/pawn-fire-crowned.png";
				}
				else {return "img/pawn-water-crowned.png";}
			}
			
		}
		else {
			if (p.isBomb()){
				if (p.isFire()){
					return "img/bomb-fire.png";
				}
				else {return "img/bomb-water.png";}
			}
			else if (p.isShield()) {
				if (p.isFire()){
					return "img/shield-fire.png";
				}
				else {return "img/shield-water.png";}
			}
			else {
				if (p.isFire()){
					return "img/pawn-fire.png";
				}
				else {return "img/pawn-water.png";}
			}
		}	
	}
}
