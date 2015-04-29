public class Board {
	private static final int BOARD_SIZE = 8;
	private static final int SHOW_LENGTH = 15;

	private Piece[][] locations = new Piece[BOARD_SIZE][BOARD_SIZE]; //first [] is y, second is x
	private Piece selectedPiece;
	private static int selectX, selectY;

	private boolean isFireTurn;
	private boolean hasMoved;
	private boolean validCapture;

	public Board(boolean shouldBeEmpty) {
		this.isFireTurn = true;
		this.hasMoved = false;

		if(!shouldBeEmpty) {
			setPieces();
		}
	}

	public static void main(String args[]) {
		Board board = new Board(false);

		StdDrawPlus.setXscale(0, BOARD_SIZE);
		StdDrawPlus.setYscale(0, BOARD_SIZE);
		StdDrawPlus.setPenRadius(0.01);

		play(board);
	}

	private void setPieces() {

		for (int i = 0; i < BOARD_SIZE; i++) { //i is y
			for (int j = 0; j < BOARD_SIZE; j++) { //j is x

				if(i == 0 && j % 2 == 0){
					setPiece(new Piece(true, this, i, j, "pawn"), j, i);
				}
				else if(i == 1 && j % 2 == 1){
					setPiece(new Piece(true, this, i, j, "shield"), j, i);
				}
				else if(i == 2 && j % 2 == 0) {
					setPiece(new Piece(true, this, i, j, "bomb"), j, i);
				}
				else if(i == 5 && j % 2 == 1){
					setPiece(new Piece(false, this, i, j, "bomb"), j, i);
				}
				else if(i == 6 && j % 2 == 0){
					setPiece(new Piece(false, this, i, j, "shield"), j, i);
				}
				else if(i == 7 && j % 2 == 1) {
					setPiece(new Piece(false, this, i, j, "pawn"), j, i);
				}

			}
		}
	}

	private static void play(Board b) {
		while(b.winner() == null) {

			if (StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				//System.out.println(x + " " + y);
				
				if (b.canSelect(x, y)){
					b.select(x, y);
				}
			}	

			if (StdDrawPlus.isSpacePressed()) {
				if(b.canEndTurn()) {
					b.endTurn();
				}
			}

			b.updateBoard(b);		
		}

		System.out.println(b.winner() + " wins");
	}

	private static void updateBoard(Board b) {
		for (int i = 0; i < BOARD_SIZE; i++) { //i is y
			for (int j = 0; j < BOARD_SIZE; j++) { //j is x		
				drawTile(j, i, b);
				drawPiece(j, i, b);
			}
		}

		StdDrawPlus.show(SHOW_LENGTH);
	}

	private static void drawTile(int x, int y, Board b){
		if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		else              StdDrawPlus.setPenColor(StdDrawPlus.RED);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		if(b.selectedPiece != null){
			StdDrawPlus.square(selectX + .5, selectY + .5, .5);
		}
	}

	private static void drawPiece(int x, int y, Board b){
		Piece piece = b.pieceAt(x, y);
		if (piece != null) {
					
			//build image name
			String imgName = "img/";
			if(piece.isBomb()){
				imgName += "bomb";
			}
			else if(piece.isShield()){
				imgName += "shield";
			}
			else {
				imgName += "pawn";
			}
			if(piece.isFire()){
				imgName += "-fire";
			}
			else {
				imgName += "-water";
			}

			if(piece.isKing()){
				imgName += "-crowned";
			}
			imgName += ".png";

			StdDrawPlus.picture(x + .5, y + .5, imgName, 1, 1);
		}
	}

	private boolean outOfBounds(int x, int y) {
		return x < 0 || y < 0 || x >= this.locations[0].length || y >= this.locations.length;
	}

	public Piece pieceAt(int x, int y) {
		if(outOfBounds(x, y)){
			return null;
		}
		else {
			return this.locations[y][x];
		}
	}

	private void setPiece(Piece p, int x, int y) {
		this.locations[y][x] = p;
	}

	public boolean canSelect(int x, int y) {
		Piece piece = pieceAt(x, y);

		if(piece != null && piece.isFire() == this.isFireTurn){
			if(this.selectedPiece == null || this.hasMoved == false){
				return true;
			}
		}
		if(piece == null) {
			if(this.selectedPiece != null && !this.hasMoved 
				&& validMove(this.selectX, this.selectY, x, y)) {
				
				return true;
			}
			if(this.selectedPiece != null && validMove(this.selectX, this.selectY, x, y)
				&& this.validCapture) {

				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		this.validCapture = false;

		Piece piece = pieceAt(xi, yi);
		Piece destPiece = pieceAt(xf, yf);

		if(piece == null) {
			return false;
		}

		if(outOfBounds(xi, yi) || outOfBounds(xf, yf)){
			return false;
		}

		if(this.hasMoved && !piece.hasCaptured()) {
			return false;
		}

		if(piece.isFire() || piece.isKing()){ //fire pieces and king pieces can move upwards
			if((yf - yi) == 1 && Math.abs(xf - xi) == 1 && destPiece == null){ //moving one upwards
				return true;
			}
			if((yf - yi) == 2 && Math.abs(xf - xi) == 2){ //test capture
				Piece killPiece = pieceAt((xf+xi)/2, (yf+yi)/2);
				if(killPiece != null && this.isFireTurn != killPiece.isFire()){
					this.validCapture = true;
					return true;
				}
				return true;
			}
		}
		if(!piece.isFire() || piece.isKing()) { //water pieces and king pieces can move downwards
			if((yi - yf) == 1 && Math.abs(xf - xi) == 1 && destPiece == null) {
				return true;
			}
			if((yi - yf) == 2 && Math.abs(xf - xi) == 2){ //test capture
				Piece killPiece = pieceAt((xf+xi)/2, (yf+yi)/2);
				if(killPiece != null && this.isFireTurn != killPiece.isFire()){
					this.validCapture = true;
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if(pieceAt(x, y) != null){
			//System.out.println("selected");
			this.selectedPiece = pieceAt(x, y);
			this.selectX = x;
			this.selectY = y;
		}
		else {
			pieceAt(selectX, selectY).move(x, y);
			
			this.hasMoved = true;
			this.selectX = x;
			this.selectY = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if(outOfBounds(x, y) || p == null){
			return;
		}

		for(int i = 0; i < this.locations.length; i++){ // i is y
			for(int j = 0; j < this.locations[0].length; j++){ // j is x
				if(pieceAt(j, i) == p){
					setPiece(null, j, i);
				}
			}
		}

		setPiece(p, x, y);
	}

	public Piece remove(int x, int y) {
		if(outOfBounds(x, y)){
			System.out.println("Position you are trying to remove is out of bounds");
			return null;
		}
		else if(pieceAt(x, y) == null) {
			System.out.println("Position has no piece.");
			return null;
		}

		Piece p = pieceAt(x, y);
		setPiece(null, x, y);
		return p;
	}

	public boolean canEndTurn() {
		return this.hasMoved;
	}

	public void endTurn() {
		this.isFireTurn = !this.isFireTurn;
		this.hasMoved = false;
		
		this.selectedPiece.doneCapturing();
		this.selectedPiece = null;
	}

	public String winner() {
		int fireCount = 0;
		int waterCount = 0;

		for (int i = 0; i < this.locations.length; i++){ // i is y
			for (int j = 0; j < this.locations[0].length; j++){ // j is x
				if(pieceAt(j, i) != null){
					if(pieceAt(j, i).isFire()){
						fireCount++;
					}
					else {
						waterCount++;
					}
				}
			}
		}

		if(fireCount == 0 && waterCount == 0){
			return "No one";
		}
		if(fireCount == 0){
			return "Water";
		}
		if(waterCount == 0){
			return "Fire";
		}

		return null;
	}
}