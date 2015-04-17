public class Board{
	private Piece[][] pieces;
	private boolean selected = false;
	private boolean moved = false;
	private boolean captured = false;
	private Piece previouslySelected = null;
	private int selectedX = 100;
	private int selectedY = 100;
	private boolean turn = true;
	private int numFire, numWater;

	private boolean shouldBeEmpty;
	public Board(boolean x){
		if (x){
			pieces = new Piece[8][8];
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					pieces[i][j] = null;
				}
			}
		}
		else{
			pieces = new Piece[8][8];
			for (int j = 0; j < 8; j++){ // first row all columns
				if (j % 2 == 0){
					pieces[j][0] = new Piece(true, this, j, 0, "pawn");
					pieces[j][2] = new Piece(true, this, j, 2, "bomb");
					pieces[j][6] = new Piece(false, this, j, 6, "shield");
				}
				if (j%2 != 0) {
					pieces[j][1] = new Piece(true, this, j, 1, "shield");
					pieces[j][7] = new Piece(false, this, j, 7, "pawn");
					pieces[j][5] = new Piece(false, this, j, 5, "bomb");
				}
			}

		}
	}

	public Piece pieceAt(int x, int y){
		if (x >7 || y >7){
			return null;
		}
		if (x < 0 || y < 0){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		if (previouslySelected != null){//if you've already selected a piece
			if (captured && pieces[x][y] == null){//if you've alread made a move
				if (turn() == previouslySelected.isFire()){//if its still the same turn
					if (validMove(selectedX, selectedY, x, y)){
						return true;
					}
					return false;
				}
			}
			if (selected){//if youve already selected a piece
				if (turn() == previouslySelected.isFire()){
					if (previouslySelected!= null && validMove(selectedX, selectedY, x, y)){
							return true;
					}
				return false;
				}
			}
		}
		if (pieces[x][y] != null && !captured){//selected a piece
			if (turn() == pieces[x][y].isFire()){//if its the same turn
				if (pieces[x][y] != null){//if theres a piece
					// if (!captured){
						if (previouslySelected == null || (previouslySelected!=null && !moved)){
							return true;
							}
						return false;
						}
				
				else{//if theres no piece here
					if (previouslySelected!= null && validMove(selectedX, selectedY, x, y)){
						return true;
					}

					return false;
				}
			}
		}  
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (pieces[xf][yf] != null){ //check if there's a piece there
			if (pieces[xf][yf].isFire() == pieces[xi][yi].isFire()){
				return true;
			}
			return false;
		}
		//for capturing, check if its king, and then 2 radius
		if (pieces[xi][yi].isKing()){
			if (Math.abs(xi - xf) == 1 && Math.abs(yi-yf) == 1){
				return true;
			}
			else if (Math.abs(xi-xf) == 2 && Math.abs(yi-yf) ==2 && (pieces[xi - (xi - xf)/2][yi - (yi-yf)/2] != null) && (pieces[xi - (xi - xf)/2][yi - (yi-yf)/2].isFire() != pieces[xi][yi].isFire())){
				return true;
			}
			return false;
		}
		else if (pieces[xi][yi].isFire()){
			if (Math.abs(xi - xf) == 1 && yi - yf == -1){
				return true;
			}
			if (Math.abs(xi - xf) == 2 && yi - yf == -2 && (pieces[xi - (xi - xf)/2][yi - (yi-yf)/2] != null) && (pieces[xi - (xi - xf)/2][yi - (yi-yf)/2].isFire() != pieces[xi][yi].isFire())){
				return true;
			}
			return false;
		}
		else {
			if (Math.abs(xi - xf) == 1 && yi - yf == 1){
				return true;
			}
			if (Math.abs(xi - xf) == 2 && yi - yf == 2 && (pieces[xi - (xi - xf)/2][yi - (yi-yf)/2] != null) && (pieces[xi - (xi - xf)/2][yi - (yi-yf)/2].isFire() != pieces[xi][yi].isFire())){
				return true;
			}
			return false;
		}
	}

	public void select(int x, int y){//only selects initial piece. needs more conditions to check if im selecting hte next piece
			// if (previouslySelected != null && captured && pieces[x][y] == null){
			// 	if (Math.abs(x- selectedX) == 2){
			// 		selected = true;
			// 		previouslySelected.move(x, y);
			// 		moved = true;
			// 	}
			// }
			if (captured){
				System.out.println("captured");
				previouslySelected.move(x, y);
				previouslySelected = pieces[x][y];
				selectedX = x;
				selectedY = y;
				moved = true;
			}
			if (selected && previouslySelected != null && (pieces[x][y] == null)){
				System.out.println("firstselect");
				selected = false; 
				previouslySelected.move(x, y);
				if (Math.abs(x - selectedX) == 2 || Math.abs(y - selectedY) == 2){
					captured = true;
				}
				previouslySelected = pieces[x][y];
				selectedY = y;
				selectedX = x;
				moved = true;
				
				System.out.println(captured);
			}

			else{
				System.out.println("base");
				selected = true;
				previouslySelected = pieces[x][y];
				selectedX = x;
				selectedY = y;
				System.out.print(selectedX + "y" + selectedY + ".");
				moved = false;
			}

	}

	public void place(Piece p, int x, int y){
		if (x > 7 || y > 7){
		}
		else{
			pieces[x][y] = null;
			pieces[x][y] = p;
			}
		}
		
	
	public Piece remove(int x, int y){//should work, tested
		if (x > 7 || y > 7){
			System.out.println("Not a valid space.");
			return null;
		}
		else if (x < 0 || y < 0){
			return null;
		}
		else{
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			System.out.println("coordinates " + x + y);
			return temp;
		}
	}

	public boolean canEndTurn(){
		if (previouslySelected != null){
			if (moved || captured){
				return true;
			}
			return false;
		}


		return false;
	}

	public void endTurn(){
		System.out.println("has it ended turn?");
			selected = false;
			moved = false;
			if (previouslySelected != null){
				previouslySelected.doneCapturing();
				System.out.println(previouslySelected.hasCaptured());
			}
			captured = false;
			previouslySelected = null;
			selectedX = 100;
			selectedY = 100;
			if (turn == true){
				turn = false;
			}
			else{
				turn = true;
			}
		
	}

	
	private boolean turn(){
		return turn;
	}

	public String winner(){
		numFire = 0;
		numWater = 0;
        for (int i = 0; i < 8 ; i++){
        	for (int j = 0; j < 8; j ++){
        		if (pieces[i][j] != null){
        			if (pieces[i][j].isFire()){
        				numFire += 1;
        			}
        			else if (pieces[i][j].isFire() == false){
        				numWater += 1;
        			}
        		}
        		
        	}
        }
        System.out.println("Fire = " + numFire);
        System.out.println("water = " + numWater);
        if (numWater == 0 && numFire == 0){
        	return "No one";
        }
        else if (numWater==0){
        	return "Fire";
        }
        else if (numFire==0){
        	return "Water";
        }
        else if (numFire != 0 && numWater != 0){
        	return null;
        }
        else{
        	return "No one";
        }
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

            	if (previouslySelected != null && i == selectedX && j == selectedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        for (int i= 0; i < N; i++){
        	for (int j = 0; j < N; j++){
        		if (pieces[i][j] != null){
        			if (pieces[i][j].isFire()){
        				if (pieces[i][j].isBomb()){
        					if (pieces[i][j].isKing()){
        						StdDrawPlus.picture(i +.5, j+.5, "img/bomb-fire-crowned.png", 1, 1);
        					}
        					StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire.png", 1, 1);
        					
        				}
        				else if (pieces[i][j].isShield()){
        					if (pieces[i][j].isKing()){
        						StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire-crowned.png", 1, 1);
        					}
        					StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire.png", 1, 1);
        				}
        					
        				else {
        					if (pieces[i][j].isKing()){
        						StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png", 1, 1);
        					}
        					StdDrawPlus.picture(i+.5, j+.5,"img/pawn-fire.png", 1, 1);
        				}

        			}
        			else{
        				if (pieces[i][j].isBomb()){
        					if (pieces[i][j].isKing()){
        						StdDrawPlus.picture(i +.5, j+.5, "img/bomb-water-crowned.png", 1, 1);
        					}
        					StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water.png", 1, 1);
        					
        				}
        				else if (pieces[i][j].isShield()){
        					if (pieces[i][j].isKing()){
        						StdDrawPlus.picture(i+.5, j+.5, "img/shield-water-crowned.png", 1, 1);
        					}
        					StdDrawPlus.picture(i+.5, j+.5, "img/shield-water.png", 1, 1);
        				}
        					
        				else {
        					if (pieces[i][j].isKing()){
        						StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water-crowned.png", 1, 1);
        					}
        					StdDrawPlus.picture(i+.5, j+.5,"img/pawn-water.png", 1, 1);
        				}
        				
        				}
        			}
        		}
        	}
        }  
    
	
	public static void main(String args[]){
		Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                Double x = StdDrawPlus.mouseX();
                Double y = StdDrawPlus.mouseY();
                if (b.canSelect(x.intValue(), y.intValue())){
                	b.select(x.intValue(), y.intValue());
                }
            }
            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){
            		b.endTurn();
            		System.out.println("endedturn");

            	}
            }
                        
            StdDrawPlus.show(100);
        }
        }
    }
	

