public class Board {
	private boolean empty;
	private Piece[][] pieces = new Piece[8][8];
	private String temp;
	private boolean fireTurn = true;
	private Piece selected = null;
	private int selectedX = 10;
	private int selectedY = 10;
	private boolean moved = false;

	public static void main(String args[]){
		Board board = new Board(false);
		while(true){
			board.drawBoard();
			if (StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();

				if (board.canSelect(x, y)){
					board.select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed()){
				if (board.canEndTurn()){
					board.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
	}

	//constructor
	public Board(boolean shouldBeEmpty){
		empty = shouldBeEmpty;
		if (empty == false){
			initialize();
		}
	}

	public Piece pieceAt(int x, int y){
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7){
			return pieces[x][y];
		}
		else{
			return null;
		}
	}

	public boolean canSelect(int x, int y){
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7){
			//unempty square
			if (pieceAt(x, y) != null){
				if (pieceAt(x, y).isFire() == fireTurn && moved == false){
					return true;
				}
			}
			//empty square
			else if ((selected != null && validMove(selectedX, selectedY, x, y) && moved == false) 
				|| selected != null && selected.hasCaptured() && validMove(selectedX, selectedY, x, y)){
				return true;
			}
			else{}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (selected.isKing()){
			if ((selected.hasCaptured() == false) && (xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1)){
				return true;
			}
			//capture upper left
			if (xf == xi - 2 && yf == yi + 2 && pieceAt(xi - 1, yi +1) != null && pieceAt(xi - 1, yi + 1).isFire() != selected.isFire()){
				return true;
			}
			//capture upper right
			if (xf == xi + 2 && yf == yi + 2 && pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() != selected.isFire()){
				return true;
			}
			//capture lower left
			if (xf == xi - 2 && yf == yi - 2 && pieceAt(xi - 1, yi -1) != null && pieceAt(xi - 1, yi - 1).isFire() != selected.isFire()){
				return true;
			}
			//capture lower right
			if (xf == xi + 2 && yf == yi - 2 && pieceAt(xi + 1, yi -1) != null && pieceAt(xi + 1, yi - 1).isFire() != selected.isFire()){
				return true;
			}
		}
		else if (selected.isFire()){
			//basic movement
			if ((selected.hasCaptured() == false) && (xf == xi + 1 || xf == xi - 1) && (yf == yi + 1)){
				return true;
			}
			//capture right
			if (xf == xi + 2 && yf == yi + 2 && pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() == false){
				return true;
			}
			//capture left
			if (xf == xi - 2 && yf == yi + 2 && pieceAt(xi -1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire() == false){
				return true;
			}
		}
		else if (selected.isFire() == false){
			//basic movement
			if ((selected.hasCaptured() == false) && (xf == xi + 1 || xf == xi - 1) && (yf == yi - 1)){
				return true;
			}
			//capture right
			if (xf == xi + 2 && yf == yi - 2 && pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() == true){
				return true;
			}
			//capture left
			if (xf == xi - 2 && yf == yi - 2 && pieceAt(xi - 1, yi -1) != null && pieceAt(xi - 1, yi - 1).isFire() == true){
				return true;
			}
		} 			
		return false;
	}

	public void select(int x, int y){
		if (pieceAt(x, y) == null && selected != null){
			moved = true;
			selected.move(x, y);
			place(selected, x, y);
			remove(selectedX, selectedY);
			selected = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
			if (selected.isBomb() && selected.hasCaptured()){
						explode(selectedX, selectedY);
					}
		}
		else if (pieceAt(x, y) != null && (selected == null || selected.isFire() == pieceAt(x, y).isFire())){
			selected = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
		}
		else{}
	}

	public void place(Piece p, int x, int y){
		if (x <= 7 && y <= 7 && x >= 0 && y >= 0){
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if (x <= 7 && y <= 7 && x >= 0 && y >= 0){
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;	
		}
		return null;
	}

	public boolean canEndTurn(){
		if (moved == true || (selected != null && selected.hasCaptured() == true)){
			return true;
		}
		return false;
	}

	public void endTurn(){
		if (selected != null){
			selected.doneCapturing();
		}
		moved = false;
		fireTurn = !fireTurn;
		selected = null;
		selectedX = 10;
		selectedY = 10;
	}

	public String winner(){
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieces[i][j] != null){
					if (pieces[i][j].isFire()){
						fireCount += 1;
					}
					else {
						waterCount += 1;
					}
				}
			}
		}
		if (fireCount == 0 && waterCount == 0){
			return "No one";
		}
		else if (fireCount == 0){
			return "Water";
		}
		else if (waterCount == 0){
			return "Fire";
		}
		else {
			return null;
		}
	}

	private void explode(int x, int y){
		for (int i = x - 1; i < x + 2; i++){
			for (int j = y - 1; j < y + 2; j++){
				if (i >= 0 && i <= 7 && j >= 0 && j <= 7 && pieces[i][j] != null && pieces[i][j].isShield() == false){
					remove(i, j);
				}
			}
		}
		selected = null;
		selectedX = 10;
		selectedY = 10;
	}

	private void drawBoard(){
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (selectedX == i && selectedY == j){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
				}	
				temp = "img/";
				if (pieces[i][j] != null){
					//check type
					if (pieces[i][j].isShield()){
						temp = temp.concat("shield-");
					}
					else if (pieces[i][j].isBomb()){
						temp = temp.concat("bomb-");
					}				
					else {
						temp = temp.concat("pawn-");
					}

					//check side
					if (pieces[i][j].isFire()){
						temp = temp.concat("fire");
					}
					else {
						temp = temp.concat("water");
					}

					//check royalty
					if (pieces[i][j].isKing()){
						temp = temp.concat("-crowned");
					}
					temp = temp.concat(".png");
					StdDrawPlus.picture(i + .5, j + .5, temp, 1 ,1);
				}
			}
		}
	}

	private void initialize(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				//added i >= 0 to all cases to hopefully appease ag

				//place fire pieces
				if (i >= 0 && i % 2 == 0 && j == 2){
					place(new Piece (true, this, i, j, "bomb"), i, j);
				}
				if (i >= 0 && i % 2 == 1 && j == 1){
					place(new Piece (true, this, i, j, "shield"), i, j);
				}
				if (i >= 0 && i % 2 == 0 && j == 0){
					place(new Piece (true, this, i, j, "pawn"), i, j);
				}

				//place water pieces
				if (i >= 0 && i % 2 == 1 && j == 5){
					place(new Piece (false, this, i, j, "bomb"), i, j);
				}
				if (i >= 0 && i % 2 == 0 && j == 6){
					place(new Piece (false, this, i, j, "shield"), i, j);
				}
				if (i >= 0 && i % 2 == 1 && j == 7){
					place(new Piece (false, this, i, j, "pawn"), i, j);
				}
			}
		}
	}
}
