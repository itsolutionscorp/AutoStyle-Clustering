public class Board{
	private Piece[] usedPieces = new Piece[24];
	private String[] types = {"pawn", "shield", "bomb"};
	private Piece[][] board = new Piece[8][8];
	private static int n = 8;
	private boolean isFireTurn = true; 
	private boolean hasMoved = false;
	private Piece selectedPiece = null;
	private static Board b;

	public static void main(String[] args){

		StdDrawPlus.setXscale(0, n);
		StdDrawPlus.setYscale(0, n);
		b = new Board(false);
		Board.makeBoard();
		
		while (true){
			if (StdDrawPlus.mousePressed()){	
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if (b.canSelect(x, y)){
					b.select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed()){
				if (b.canEndTurn()){
					b.endTurn();
					
					if(b.winner() != null) {
						// TODO check for if they win
					}
				}
			}

			Board.makeBoard();
			
		}
	}

	public Board(boolean shouldBeEmpty){
		
		if (!shouldBeEmpty){
			int n = 3;
			for (int i = 0; i < n; i++){
				for (int j = 0; j < board.length; j++){
					// fire pieces
					if ((i + j) % 2 == 0){
						board[j][i] = new Piece(true, this, j, i, types[i]);
					}
					// water pieces
					else{
						board[j][board.length - 1 - i] = new Piece(false, this, j, board.length - 1 - i, types[i]);
					}
				}
			}
		}
		/*else if (shouldBeEmpty){
			b.drawEmpty();
		}*/
	}

	public Piece pieceAt(int x, int y){
		if (validCoords(x, y)){
			return board[x][y];
		}
		return null;
	}

	public void place(Piece p, int x, int y){
		if (!validCoords(x, y)){
			return;
		}
		
		//placing a piece on the board if the spot is null
		int originalX = this.getXcoordinate(p);
		int originalY = this.getYcoordinate(p);
		board[x][y] = p;
		if (originalX != -1 && originalY != -1){
			board[originalX][originalY] = null;
		}
	}

	public Piece remove(int x, int y){
		if ((x >= n) || (y >= n)){
			String message_1 = "Coordinates is out of bounds";
			System.out.println(message_1);
			return null;
		}
		else if ((board[x][y] == null)){
			String message_2 = "No piece at this coordinate";
			System.out.println(message_2);
			return null;
		}
		else {
			Piece removed_piece = board[x][y];
			board[x][y] = null;
			return removed_piece;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece empty = pieceAt(xf, yf);
		if (!validCoords(xi, yi) || !validCoords(xf, yf) || p == null || empty != null) {
			return false;
		}
		//if something is only moving
		if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
			if (p.isKing()) {
				return true;
			}
			if (p.isFire()) {
				return (yf - yi == 1);
			}
			else {
				return (yf - yi == -1);
			}
		}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece empty = pieceAt(xf, yf);
		if (!validCoords(xi, yi) || !validCoords(xf, yf) || p == null || empty != null) {
			return false;
		}
		//if we are actually capturing 
		if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
			Piece captured = pieceAt((xi+xf)/2, (yi+yf)/2);
			if (captured == null || p.isFire() == captured.isFire()) {
				return false;
			}
			if (p.isKing()) {
				return true;
			}
			if (p.isFire()) {
				return (yf - yi == 2);
			}
			else {
				return (yf - yi == -2);
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y){
		//If x and y are out of the coordinate system
		if (!validCoords(x, y)){
			return false;
		}
		Piece piece = board[x][y];
		//if there is a piece on the selected coordinate
		if (piece != null){
			return (isFireTurn == piece.isFire()
				&& (selectedPiece == null || !hasMoved));
		}
		int selectedX = getXcoordinate(selectedPiece);
		int selectedY = getYcoordinate(selectedPiece);

		if (!hasMoved){
			return (selectedPiece != null 
				&& (validMove(selectedX, selectedY, x, y)
					|| validCapture(selectedX, selectedY, x, y)));
		}

		if (selectedPiece.hasCaptured()){
			return (validCapture(selectedX, selectedY, x, y));
		}
		return false;
	}
	
	public void select(int x, int y){
		//selecting a coordiante with a piece
		if (board[x][y] != null){
			selectedPiece = board[x][y];
			int x_for_piece = this.getXcoordinate(selectedPiece);
			int y_for_piece = this.getYcoordinate(selectedPiece);
			System.out.println(x_for_piece);
			System.out.println(y_for_piece);
		}
		//we are selecting a coordinate WITHOUT a piece and preparing to move something there
		//somehow figure out how to check if a piece has been captured and change has_captured
		else {
			int x_for_piece = this.getXcoordinate(selectedPiece);
			int y_for_piece = this.getYcoordinate(selectedPiece);
			System.out.println(x);
			System.out.println(y);
			this.hasMoved = true;
			System.out.println("Calling remove");
			selectedPiece.move(x, y);
		}
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn(){
		//if we captured a piece this turn, we must restart both the has_captured and has_moved booleans.
		if (selectedPiece.hasCaptured()){
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		hasMoved = false;
		isFireTurn = !isFireTurn;
	}

	public String winner(){
		int water_pieces = 0;
		int fire_pieces = 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (board[i][j] != null){
					if (board[i][j].isFire() == true){
						fire_pieces += 1;
					}
					else{
						water_pieces += 1;
					}
				}
			}
		}

		if (water_pieces == 0 && fire_pieces > 0){
			String s = "Fire";
			return s;
		}

		else if (fire_pieces == 0 && water_pieces > 0){
			String s = "Water";
			return s;
		}

		else if (fire_pieces == 0 && water_pieces == 0){
			String s = "No one";
			return s;
		}
		else{
			return null;
		}
	}

	private boolean validCoords(int x, int y){
		return (x < n && y < n && x >= 0 && y >= 0);
	}

	private int getXcoordinate(Piece p){
		int n = 8;
		for (int i =0; i < n; i++){
			for (int j = 0; j < n ; j++){
				if (board[i][j] == p){
					return i;
				}
			}
		}
		return -1;
	}

	private int getYcoordinate(Piece p){
		int n = 8;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (board[i][j] == p){
					return j;
				}
			}
		}
		return -1;
	}

	private static void drawEmpty(){
		int n = 8;
		//while (true){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
		StdDrawPlus.show(100);
	}

	private static void makeBoard(){
		int n = 8;
		//while (true){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				Piece piece = b.pieceAt(i,j);
				if (piece != null){
					//drawing fire pieces 
					if (piece.isFire()){
						if (piece.isBomb()){
							if (piece.isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);									
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
						}
						else if (piece.isShield()){
							if (piece.isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
						}
						else{
							if (piece.isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					}
					else{
						if (piece.isBomb()){
							if (piece.isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);		
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
						}
						else if (piece.isShield()){
							if (piece.isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);		
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
						}
						else{
							if(piece.isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
		StdDrawPlus.show(100);
		//}
	}
}


