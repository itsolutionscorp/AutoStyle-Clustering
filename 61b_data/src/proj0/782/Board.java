
public class Board {
	private static boolean shouldBeEmpty;
	private static Board B;
	private static Piece[][] pieces;
	private static int N;
	private static Piece piece = null;
	private static boolean pieceMoved = false;
	private static int[] selected = new int[]{-1, -1};
	private boolean player = true; //Fire is true

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
	}

	public static void main(String args[]){
		B = new Board(false);
		if(args.length != 0){
			shouldBeEmpty = Boolean.parseBoolean(args[0]); //Debugging to test empty/full Board
		}
		N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		pieces = new Piece[N][N];
		if(!shouldBeEmpty){
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					if(j == 0 && i%2 == 0){
						pieces[i][j] = new Piece(true, B, i, j, "pawn");
					} else if(j == 1 && i%2 == 1){
						pieces[i][j] = new Piece(true, B, i, j, "shield");
					} else if(j == 2 && i%2 == 0){
						pieces[i][j] = new Piece(true, B, i, j, "bomb");
					} else if(j == 5 && i%2 == 1){
						pieces[i][j] = new Piece(false, B, i, j, "bomb");
					} else if(j == 6 && i%2 == 0){
						pieces[i][j] = new Piece(false, B, i, j, "shield");
					} else if(j == 7 && i%2 == 1){
						pieces[i][j] = new Piece(false, B, i, j, "pawn");
					} else {
						pieces[i][j] = null;
					}
				}
			}
		}

		//Main game loop starts here
		while(true){
			runGame();
		}
	}

	private static void drawBoard(){
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}

				if(selected[0] != i && selected[1] != j){
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					//StdDrawPlus.filledSquare(selected[0] + .5, selected[1] + .5, .5);
				}

				if(pieces[i][j] != null){
					String img = "img/";
					if(pieces[i][j].isBomb()) {
						img = img + "bomb";
					} else if(pieces[i][j].isShield()){
						img = img + "shield";
					} else {
						img = img + "pawn";
					}

					if(pieces[i][j].isFire()){
						img = img + "-fire";
					} else {
						img = img + "-water";
					}

					if(pieces[i][j].isKing()){
						img = img + "-crowned";
					}

					StdDrawPlus.picture(i + .5, j + .5, img + ".png", 1, 1);
				}
			}
		}
	}

	private static void runGame(){
		drawBoard();
		if(StdDrawPlus.mousePressed()){
			int x = (int) StdDrawPlus.mouseX();
			int y = (int) StdDrawPlus.mouseY();
			mouseClick(x, y);
		}
		if(StdDrawPlus.isSpacePressed()){
			if(B.canEndTurn()){
				B.endTurn();
			}
		}
		StdDrawPlus.show(25);
	}

	private static void mouseClick(int x, int y){
		if(B.canSelect(x, y)){
			B.select(x,y);
		}
	}

	public Piece pieceAt(int x, int y) {
		if(x >= N || y >= N || x < 0 || y < 0){
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		if(x < N && y < N && x >= 0 && y >= 0){
			if(piece != null){ //If piece selected
				if(pieceMoved == false){
					if(!piece.isKing()){ //Non-king pieces can only move one direction
						if(piece.isFire() && (y - selected[1]) < 0){ //Make sure fire moves up
							return false;
						}
						if(!piece.isFire() && (y - selected[1]) > 0){ //Make sure water moves down
							return false;
						}
					}
					
					if(Math.abs(selected[0] - x) == 2 && Math.abs(selected[1] - y) == 2){ //Test for hopping
						if(B.pieceAt((selected[0] + x)/2, (selected[1] + y)/2) != null && B.pieceAt((selected[0] + x)/2, (selected[1] + y)/2).isFire() != player && x < N && y < N && x > 0 && y > 0 && pieces[x][y] == null){
							return true;
						}
					} else if(Math.abs(selected[0] - x) > 1 || Math.abs(selected[1] - y) > 1){
						return false;
					} else if(pieces[x][y] == null && (y - selected[1] != 0 && x - selected[0] != 0)){ //No moving horizontally or vertically, must be empty spot
						return true;
					} else {
						return false;
					}
				} else {
					return false; //Piece has moved so false
				}
			} else { //If piece not selected
				if(pieces[x][y] != null){
					if(pieces[x][y].isFire() == player){
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public void select(int x, int y) {
		selected[0] = x;
		selected[1] = y;
		if(piece == null){
			piece = pieceAt(x, y);
		} else {
			piece.move(x, y);
			if(piece.hasCaptured() == true){
				if(piece.isFire() || piece.isKing()){
					if(B.pieceAt(x + 1, y + 1) != null && B.pieceAt(x + 1, y + 1).isFire() != player && x + 2 < N && y + 2 < N && x + 2 > 0 && y + 2 > 0 && B.pieceAt(x + 2, y + 2) == null){
						piece.doneCapturing();
					} else if(B.pieceAt(x + -1, y + 1) != null && B.pieceAt(x + -1, y + 1).isFire() != player && x + -2 < N && y + 2 < N && x + -2 > 0 && y + 2 > 0 && B.pieceAt(x + -2, y + 2) == null) {
						piece.doneCapturing();
					} else {
						pieceMoved = true;
					}
				} else if(!piece.isFire() || piece.isKing()) {
					if(B.pieceAt(x + 1, y + -1) != null && B.pieceAt(x + 1, y + -1).isFire() != player && x + 2 < N && y + -2 < N && x + 2 > 0 && y + -2 > 0 && B.pieceAt(x + 2, y + -2) == null) {
						piece.doneCapturing();
					} else if(B.pieceAt(x + -1, y + -1) != null && B.pieceAt(x + -1, y + -1).isFire() != player && x + -2 < N && y + -2 < N && x + -2 > 0 && y + -2 > 0 && B.pieceAt(x + -2, y + -2) == null) {
						piece.doneCapturing();
					} else {
						pieceMoved = true;
					}
				} else {
					pieceMoved = true;
				}
			} else {
				pieceMoved = true;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if(x < N && y < N && p != null){
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if(x >= N || y >= N || x < 0 || y < 0){
			return null;
		} else {
			Piece temp = pieces[x][y];
			if(temp == null){
				return null;
			} else {
				pieces[x][y] = null;
				return temp;
			}
		}
	}

	public boolean canEndTurn(){
		if(pieceMoved == true){
			System.out.println("Turn ended");
			return true;
		} else {
			return false;
		}
	}

	public void endTurn(){
		selected[0] = -1;
		selected[1] = -1;
		if(piece != null){
			piece.doneCapturing();
		}
		pieceMoved = false;
		piece = null;
		player = !player; //Switch sides
	}

	public String winner() {
		boolean fire = false;
		boolean water = false;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(pieces[i][j] != null && pieces[i][j].isFire()){
					fire = true;
				} else if(pieces[i][j] != null && !pieces[i][j].isFire()){
					water = true;
				}
			}
		}

		if(fire == false && water == false){
			return "No one";
		} else if(fire == true && water == true){
			return null;
		} else if(fire == true && water == false){
			return "Water";
		} else if(water == true && fire == false){
			return "Fire";
		}

		return null;
	}
}