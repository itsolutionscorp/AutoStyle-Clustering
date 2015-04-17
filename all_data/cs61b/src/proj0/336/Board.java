
public class Board {
	private Piece[][] pieces;
	private int turn; //0 if fire; 1 if water.
	private static final int BOARD_SIZE = 8;
	
	private Piece currentlySelected;
	private int xCurrentlySelected;
	private int yCurrentlySelected;
	private boolean currentPlayerHasMoved;
	
	private boolean guiUpdateNeeded;
	public Board(boolean shouldBeEmpty){
		this.pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
		if (shouldBeEmpty){
			return;
		}
		else {
			for (int i = 0; i < BOARD_SIZE; i++){
				if (i % 2 == 0){
					this.pieces[i][0] = new Piece(true, this, i, 0, "pawn");
					this.pieces[i][2] = new Piece(true, this, i, 2, "bomb");
					this.pieces[i][6] = new Piece(false, this, i, 6, "shield");
				}
				else{
					this.pieces[i][1] = new Piece(true, this, i, 1, "shield");
					this.pieces[i][5] = new Piece(false, this, i, 5, "bomb");
					this.pieces[i][7] = new Piece(false, this, i, 7, "pawn");
				}
			}
		}
		this.turn = 0;
		this.currentlySelected = null;
		this.guiUpdateNeeded = true;
	}
	public Piece pieceAt(int x, int y){
		if ((x < BOARD_SIZE) && (y < BOARD_SIZE) && (x >= 0) && (y >= 0)){
			return this.pieces[x][y];
		}
		return null;
	}
	public boolean canSelect(int x, int y){
		if ((x >= BOARD_SIZE) || (y >= BOARD_SIZE))
			return false;
		Piece potentialSelection = pieceAt(x, y);
		// both cannot be empty.
		if ((potentialSelection == null) && (currentlySelected == null)){
			return false;
		}
		// now, we know that we have a piece at potentialSelection.
		// we must now make sure that the correct turn is selecting the piece.
		if ((potentialSelection != null) && (potentialSelection.side() != turn)){
			return false;
		}
		//if the current player has already moved, then he should only be able to multi capture..
		if (currentPlayerHasMoved){
			// we must guarantee the only possible moves allowed are captures.
			if (Math.abs(xCurrentlySelected -x) != 2 || Math.abs(yCurrentlySelected - y) != 2)
				return false;
			else if (validMove(xCurrentlySelected, yCurrentlySelected, x, y))
				return true;
			else
				return false;
		}
		//if the
		if (currentlySelected != null){
			//we can select a valid empty square.
			if (potentialSelection != null)
				return true;
			if (validMove(xCurrentlySelected, yCurrentlySelected, x, y)){
				return true;
			}
			return false;
		}
		else{
			return true; /* this means that the currently selected space is empty. but, we know that potential selection
			is not null. therefore, we are fine.*/
		}
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece moving = pieceAt(xi, yi);
		if (moving == null)
			return false;
		int x_displacement = xf - xi;
		int y_displacement = yf - yi;
		if (Math.abs(x_displacement) != Math.abs(y_displacement)){
			return false;
		}
		if (Math.abs(x_displacement) == 2){
			Piece potentialCapture = pieceAt((xf+xi)/2, (yf+yi)/2);
			if (potentialCapture == null)
				return false;
			else if (potentialCapture.side() == moving.side())
				return false;
		}
		if (Math.abs(x_displacement) > 2){
			System.out.println("Too far out");
			return false;
		}
		// disqualified horizontal and vertical lines, along with anything farther than 2 away.
		
		if (y_displacement < 0 && moving.isFire() && !moving.isKing())
			return false;
		if (y_displacement > 0 && !moving.isFire() && !moving.isKing())
			return false;
		// confirmed movement is in the right direction.
		return true;
	}
	
	public void select(int x, int y){
		Piece selected = pieceAt(x, y);
		if (selected != null){	
			this.currentlySelected = selected;
			this.xCurrentlySelected = x;
			this.yCurrentlySelected = y;
		}
		else{
			if (currentlySelected != null){
				currentlySelected.move(x, y);
				this.currentPlayerHasMoved = true;
				this.xCurrentlySelected = x;
				this.yCurrentlySelected = y;
			}
			else{
				this.currentlySelected = null;
			}
		}
		this.guiUpdateNeeded = true;
	}
	private void switchTurn(){
		this.turn = 1 - this.turn;
	}
	public void place(Piece p, int x, int y){
		if ((x >= BOARD_SIZE) || (y >= BOARD_SIZE) || (p == null)){
			return;
		}
		this.pieces[x][y] = p;
		guiUpdateNeeded = true;
	}
	public Piece remove(int x, int y){
		if ((x >= BOARD_SIZE) || (y >= BOARD_SIZE)){
			System.out.println("Can't remove piece at this position. Position out of bounds");
			return null;
		}
		Piece deleted = pieceAt(x, y);
		if (deleted == null){
			System.out.println("No piece here.");
		}
		this.pieces[x][y] = null;
		guiUpdateNeeded = true;
		return deleted;
	}
	
	private void turnWhite(int x, int y){
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x+0.5, y+0.5, .5);
		drawPiece(pieceAt(x, y), x, y);
	}
	
	public boolean canEndTurn(){
		
		return this.currentPlayerHasMoved;
	}
	public void endTurn(){
		if (canEndTurn()){
			this.currentlySelected.doneCapturing();
			this.currentlySelected = null;
			this.currentPlayerHasMoved = false;
			this.xCurrentlySelected = -1;
			this.yCurrentlySelected = -1;
			switchTurn();
		}
	}
	public String winner(){
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < pieces.length; i++){
			for (int j = 0; j < pieces[i].length; j++){
				Piece p = pieceAt(i,j);
				if (p == null)
					continue;
				if (p.isFire())
					fireCount++;
				else
					waterCount++;
			}
		}
		if ((fireCount == 0) && (waterCount == 0)){
			return "No one";
		}
		else if (fireCount == 0)
			return "Water";
		else if (waterCount == 0)
			return "Fire";
		else
			return null;
	}
	private void drawBoard(){
		int N = BOARD_SIZE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        drawAllPieces();
	}
	private void drawAllPieces() {
		int N = BOARD_SIZE;
        for (int k = 0; k < N;k++){
        	for (int l = 0; l < N; l++){
        		Piece toDraw = pieceAt(k, l);
        		if (toDraw == null){
        			continue;
        		}
        		drawPiece(toDraw, k, l);
        	}
        }
	}
	
	private void drawPiece(Piece p, int x, int y){
		Piece toDraw = pieceAt(x,y);
		StringBuilder imgFile = new StringBuilder("img/");
		// What type of piece?
		if (toDraw.isBomb())
			imgFile.append("bomb");
		else if (toDraw.isShield())
			imgFile.append("shield");
		else
			imgFile.append("pawn");
		// Which team?
		if (toDraw.isFire())
			imgFile.append("-fire");
		else
			imgFile.append("-water");
		// Crowned or not?
		if(toDraw.isKing())
			imgFile.append("-crowned");
		imgFile.append(".png");
		StdDrawPlus.picture(x+0.5, y+0.5, imgFile.toString(), 1, 1);
	}
	public static void main(String[] args){
        StdDrawPlus.setXscale(0, BOARD_SIZE);
        StdDrawPlus.setYscale(0, BOARD_SIZE);
		Board newBoard = new Board(false);
		System.out.println("starting");
		newBoard.drawBoard();
		int mouseXPos = -1;
		int mouseYPos = -1;
		while (true){
			newBoard.drawBoard(); 
			if (StdDrawPlus.mousePressed()){
				System.out.println("click log");
				StdDrawPlus.show(100);
				double rawMouseX = StdDrawPlus.mouseX();
				double rawMouseY = StdDrawPlus.mouseY();
				mouseXPos = (int) rawMouseX;
				mouseYPos = (int) rawMouseY;
				System.out.println(rawMouseX +" " + rawMouseY);
				if (newBoard.canSelect(mouseXPos, mouseYPos))
					newBoard.select(mouseXPos, mouseYPos);
					if (newBoard.guiUpdateNeeded)
						newBoard.drawBoard();
			}
			if (StdDrawPlus.isSpacePressed())
				newBoard.endTurn();
			StdDrawPlus.show(10);
			if (newBoard.winner() != null){
				System.out.println("The winner is " + newBoard.winner());
				newBoard.drawBoard();
				break;
			}
		}
	}
}
