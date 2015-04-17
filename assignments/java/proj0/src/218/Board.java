

public class Board {

	private Piece board[][] = new Piece[8][8];
	private boolean turn = true;
	private boolean firstmove = false;
	private Piece selected = null;
	private int xselected;
	private int yselected;
	private boolean hasMoved = false;

	public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		b.drawBoard(8);

		while(true) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(x < 8 && x > 0 && y < 8 && y > 0){
                	b.select((int)Math.floor(x/1),(int)Math.floor(y/1));
                }
            }            
            if (StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(100);
            if(b.winner() != null && b.winner() != "No one"){
            	break;
            }
        }
        b.drawBoard(8);

	}


	public Board(boolean shouldBeEmpty){
		if(shouldBeEmpty == true){
			for(int i = 0; i < board.length; i++){
				for(int j = 0; j < board[i].length; j++){
					board[i][j] = null;
				}
			}
		}
		else{
			for(int i = 0; i < 4; i++){
				board[2 * i + 1][7] = new Piece(false, this, 2 * i + 1, 7, "normal"); 
			}
			for(int i = 0; i < 4; i++){
				board[2 * i ][6] = new Piece(false, this, 2 * i, 6, "shield"); 
			}
			for(int i = 0; i < 4; i++){
				board[2 * i + 1][5] = new Piece(false, this, 2 * i + 1, 5, "bomb"); 
			}
			for(int i = 0; i < 4; i++){
				board[2 * i][0] = new Piece(true, this, 2 * i, 0, "normal"); 
			}
			for(int i = 0; i < 4; i++){
				board[2 * i + 1][1] = new Piece(true, this, 2 * i + 1, 1, "shield"); 
			}
			for(int i = 0; i < 4; i++){
				board[2 * i][2] = new Piece(true, this, 2 * i, 2, "bomb"); 
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if(x > 7 || x < 0 || y < 0 || y > 7){
			return null;
		}
		else{
			return board[x][y];
		}
	}

	public boolean canSelect(int x, int y){
		if(board[x][y] != null){
			//selecting a square with a piece on it
			if(selected == null){
				if( board[x][y].isFire() == turn){
					return true;
				}
				else{
					return false;
				}
			}
			else if(hasMoved == false){
				return true;
			}
			else{
				return false;
			}

		}
		else if(selected != null){
			//selecting an empty square with a preselected piece
			if(hasMoved != true && validMove(xselected, yselected, x, y) > 0){
				return true;
			}
			else if(selected.hasCaptured() == true && validMove(xselected, yselected, x, y) == 2){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			//selected an empty square without a piece selected previously
			return false;
		}
	}

	public void select(int x, int y){
		if(firstmove == false && board[x][y] != null){
			turn = board[x][y].isFire();
			firstmove = true;
		}
		if(canSelect(x, y)){
			if(selected == null) {
				selected = pieceAt(x, y);
				xselected = x;
				yselected = y;
			}
			else if(board[x][y] != null){
				selected = pieceAt(x, y);
				xselected = x;
				yselected = y;
			}
			else{
				selected.move(x,y);
				if(board[x][y] == null){
					hasMoved = true;
					return;
				}
				board[x][y] = selected;
				int xtemp = xselected;
				int ytemp = yselected;
				xselected = x;
				yselected = y;
				board[xtemp][ytemp] = null;
				hasMoved = true;
			}
		}
	}

	public void place(Piece p, int x, int y){
		if(x > 7 || x < 0 || y > 7 || y < 0){
			return;
		}
		else{
			board[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if(x > 7 || x < 0 || y > 7 || y < 0){
			System.out.println("Out of bounds");
			return null;
		}
		else if(pieceAt(x,y) == null){
			System.out.println("No piece at coordinates (x,y)");
			return null;
		}
		else{
			Piece temp = board[x][y];
			board[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if (board[i][j] != null){
					if(hasMoved || board[i][j].hasCaptured()){
						return true;
					}
				}
			}
		}
		return false;
	}

	public void endTurn() {
		if(turn){
			turn = false;
		}
		else{
			turn = true;
		}
		if(selected != null){
			selected.doneCapturing();
		}
		selected = null;
		hasMoved = false;
	}


	public String winner(){
		int countFire = 0;
		int countWater = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if (board[i][j] != null){
					if(board[i][j].isFire()){
						countFire ++;
					}
					else{
						countWater++;
					}
				}
			}
		}
		if(countFire == 0 && countWater == 0){
			return "No one";
		}
		if(countFire != 0 && countWater == 0){
			return "Fire";
		}
		if(countWater != 0 && countFire ==0){
			return "Water";
		}
		else{
			return null;
		}


	}


	private int validMove(int xi, int yi, int xf, int yf){
		Piece temp = selected;
		if (board[xf][yf] != null){
			return 0;
		}
		else if (temp.isFire() == turn && temp.isFire()){
			if(temp.isKing()){
				if(Math.abs(yi - yf) == 1 && Math.abs(xi - xf) == 1){
					return 1;
				}
				else if(Math.abs(yi - yf) == 2 && Math.abs(xi - xf) == 2 && board[(xi + xf) >>> 1][(yi + yf) >>> 1] != null ) {
					if(board[(xi + xf) >>> 1][(yi + yf) >>> 1].isFire() == false){	
						return 2;
					}
					else{
						return 0;
					}
				}
				else{
					return 0;
				}
			}
			else{
				if( yi - yf == -1 && Math.abs(xi - xf) == 1){
					return 1;
				}
				else if(yi - yf == -2 && Math.abs(xi - xf) == 2 && board[(xi + xf) >>> 1][(yi + yf) >>> 1] != null ) {
					if(board[(xi + xf) >>> 1][(yi + yf) >>> 1].isFire() == false){
						return 2;
					}
					else{
						return 0;
					}
				}
				else{
					return 0;
				}
			}
		}
		else{
			if(temp.isKing()){
				if(Math.abs(yi - yf) == 1 && Math.abs(xi - xf) == 1){
					return 1;
				}
				else if(Math.abs(yi - yf) == 2 && Math.abs(xi - xf) == 2 && board[(xi + xf) >>> 1][(yi + yf) >>> 1] != null ) {
					if(board[(xi + xf) >>> 1][(yi + yf) >>> 1].isFire() == true){
						return 2;
					}
					else{
						return 0;
					}
				}
				else{
					return 0;
				}
			}
			else{
				if( yi - yf == 1 && Math.abs(xi - xf) == 1){
					return 1;
				}
				else if(yi - yf == 2 && Math.abs(xi - xf) == 2 && board[(xi + xf) >>> 1][(yi + yf) >>> 1].isFire() == true ) {
					if(board[(xi + xf) >>> 1][(yi + yf) >>> 1].isFire() == true){
						return 2;
					}
					else{
						return 0;
					}
				}
				else{
					return 0;
				}
			}
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (board[i][j] != null) {
                	if(board[i][j] == selected){
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                	if(board[i][j].isFire()){
                		if(board[i][j].isKing()){
                			if(board[i][j].isBomb()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		}
	                		else if(board[i][j].isShield()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		}
	                		else{
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		}
                		}
                		else{
	                		if(board[i][j].isBomb()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}
	                		else if(board[i][j].isShield()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}
	                		else{
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
                		}
                	}
                	else{
                		if(board[i][j].isKing()){
                			if(board[i][j].isBomb()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		}
	                		else if(board[i][j].isShield()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		}
	                		else{
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		}
                		}
                		else{
	                		if(board[i][j].isBomb()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}
	                		else if(board[i][j].isShield()){
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                		}
	                		else{
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
                		}
                	}
                    
                }
            }
        }
    }


}