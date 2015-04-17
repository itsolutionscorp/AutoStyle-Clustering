/* Author: Kasden Bunker
*/

public class Board{
	private Piece[][] pieces = new Piece[8][8];
	private boolean isFireTurn = true;
	private boolean playerHasMoved = false;
	private Piece selectedPiece;

	/* Makes a new board, and pieces if shouldBeEmpty is false */
	public Board(boolean shouldBeEmpty){
		if (!shouldBeEmpty){
			makePieces();
		}
	}

	/* Loops forever, listening to mouse and space and redrawing board */
	public static void main(String args[]){
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		while (true){
			if (StdDrawPlus.mousePressed()){
				if (b.canSelect((int)StdDrawPlus.mouseX(), 
							    (int)StdDrawPlus.mouseY())){
						b.select((int)StdDrawPlus.mouseX(),
							 	 (int)StdDrawPlus.mouseY());
				}
			}
			else if(StdDrawPlus.isSpacePressed()){
				if (b.canEndTurn()){
					b.endTurn();
				}
			}
			b.drawBoard();
			StdDrawPlus.show(25); 
		}
	}
	
	/* Returns the piece at board position (x,y) */
	public Piece pieceAt(int x, int y){
		if (x > 7 || x < 0 || y > 7 || y < 0){
			return null;
		}
		return this.pieces[x][y];
	}

	/* Places a piece at (x,y) */
	public void place(Piece p, int x, int y){
		this.pieces[x][y] = p;
	}

	/* Called on mouse click to check whether selected space is valid */
	public boolean canSelect(int x, int y){
		if (x > 7 || x < 0 || y > 7 || y < 0){
			return false;
		}

		//Selecting one of your pieces before you have moved is valid
		if (pieceAt(x,y) != null &&
			pieceAt(x,y).isFire() == this.isFireTurn &&
			!this.playerHasMoved){ 
				return true;
		}
		//Selecting an empty space once you've selected a piece might be valid
		if (this.selectedPiece != null){
			if (validMove(selectedX(), selectedY(), x, y)){
				return true;
			}
		}
		return false;
	}

	/* Draws board, pieces, and highlights selected piece */
	private void drawBoard(){
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if ((i+j) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				} 
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);

				//Highlight the selected piece
				if (pieceAt(i,j) != null){
					if (pieceAt(i,j) == this.selectedPiece){
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
					}
					drawPiece(i, j);
				}
			}
		}
	}

	/* Draws the piece, uses its properties to build filename */
	private void drawPiece(int x, int y){
		String img1;
		String img2;
		String img3;
		Piece p = pieceAt(x,y);

		if (p.isBomb()){
			img1 = "img/bomb";
		}else if(p.isShield()){
			img1 = "img/shield";
		}else{
			img1 = "img/pawn";
		}

		if (p.isFire()){
			img2 = "-fire";
		}else{
			img2 = "-water";
		}

		if (p.isKing()){
			img3 = "-crowned.png";
		}else{
			img3 = ".png";
		}
		StdDrawPlus.picture(x + .5, y + .5, img1+img2+img3, 1, 1);
	}

	/* Instantiate game pieces */
	private void makePieces(){
		boolean isFire;
		String type;
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if ((j != 3) && (j != 4) && ((i+j)%2 == 0)){
					if (j == 0 || j == 7){
						type = "pawn";
					}
					else if (j == 1 || j == 6){
						type = "shield";
					}
					else{
						type = "bomb";
					}
					if (j == 0 || j == 1 || j == 2){
						isFire = true;
					}
					else{
						isFire = false;
					}
					pieces[i][j] = new Piece(isFire, this, i, j, type);
				}
			}
		}
	}

	/* Selects a space, which either selects a piece or moves the selected piece */
	public void select(int x, int y){
		if (pieceAt(x,y) != null){
			this.selectedPiece = pieceAt(x,y);
		}
		else{
			this.selectedPiece.move(x, y);
			this.playerHasMoved = true;
			if (this.selectedPiece.isBomb() && this.selectedPiece.hasCaptured()){
				explosionAt(selectedX(), selectedY());
			}
		}
	}

	/* Removes a piece from the board, and returns it */
	public Piece remove(int x, int y){
		if (x > 7 || x < 0 || y > 7 || y < 0){
			System.out.println("Space [" + x + ", " + y + "] is out of bounds.");
			return null;
		}
		if (pieceAt(x,y) == null){
			System.out.println("There is no piece at [" + x + ", " + y + "].");
			return null;
		}
		Piece returnPiece = pieceAt(x,y);
		if(this.selectedPiece == pieceAt(x,y)){
			this.selectedPiece = null;
		}
		this.pieces[x][y] = null;
		return returnPiece;
	}

	/* Returns whether the player has taken an action so can end turn */
	public boolean canEndTurn(){
		return this.playerHasMoved;
	}

	/* End turn, switch active player, make so no pieces selected */
	public void endTurn(){
		if (this.isFireTurn){
			this.isFireTurn = false;
		}
		else{
			this.isFireTurn = true;
		}
		if(this.selectedPiece != null){
			this.selectedPiece.doneCapturing();
		}
		this.selectedPiece = null;
		this.playerHasMoved = false;
	}

	/* Returns the winner or null if there isn't one */
	public String winner(){
		int fireCount = 0;
		int waterCount = 0;
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (pieceAt(i,j) != null){
					if (pieceAt(i,j).isFire()){
						fireCount += 1;
					}
					else{
						waterCount += 1;
					}
				}
			}
		}
		if(fireCount == 0 && waterCount == 0){
			return "No one";
		}
		if(fireCount == 0 && waterCount != 0){
			return "Water";
		}
		if(fireCount != 0 && waterCount == 0){
			return "Fire";
		}
		return null;
	}

/*_____Private Methods Below - Autograder shield your eyes____*/

	/* Checks whether the piece at [fromX][fromY] can legally move to [toX][toY].
		fromX, fromY, toX, and toY are all between 0 and 7 */
	private boolean validMove(int fromX, int fromY, int toX, int toY){
		if (pieceAt(toX,toY) != null){ //Illegal to move onto another piece
			return false;
		}

		int yStep = -1;
		boolean k = false;
		if (pieceAt(fromX,fromY).isFire()){
			yStep = 1;
		}
		if (pieceAt(fromX,fromY).isKing()){
			k = true;
		}
		//Valid non-capture moves
		if (!this.playerHasMoved &&
		   (toX == fromX+1 || toX == fromX-1) &&
		   (toY == fromY+yStep || (k && (toY == fromY-yStep)))){
				return true;
		}

		//Valid captures
		if((k) &&
		   (toX == fromX+2 || toX == fromX-2) &&
		   (toY == fromY-2 || toY == fromY+2) &&
		   (enemyPieceBetween(fromX, fromY, toX, toY))){
		   		return true;
		}
		if((toX == fromX+2 || toX == fromX-2) &&
		   (toY == fromY+2*yStep) &&
		   (enemyPieceBetween(fromX, fromY, toX, toY))){
		 		return true;
		}
		return false;
	}
	
	/* Returns whether there is an enemy piece between a piece at (fromX, fromY)
		and the empty space (toX, toY) that is two diagonal spaces away */
	private boolean enemyPieceBetween(int fromX, int fromY, int toX, int toY){
		Piece jumper = pieceAt(fromX, fromY);
		Piece target = pieceAt((fromX+toX)/2, (fromY+toY)/2);
		if (target != null && jumper.isFire() != target.isFire()){
			return true;
		}
		return false;
	}

	/* Removes all non-shield pieces in a 3x3 square centered at (X, Y) */
	private void explosionAt(int x, int y){		
		remove(x, y);
		if ((x-1 >= 0) && (y-1 >= 0) &&
			pieceAt(x-1,y-1) != null && 
			!pieceAt(x-1,y-1).isShield()){
				remove(x-1, y-1);
		}
		if ((x-1 >= 0) && (y+1 <= 7) &&
			pieceAt(x-1,y+1) != null && 
			!pieceAt(x-1,y+1).isShield()){
				remove(x-1, y+1);
		}
		if ((x+1 <= 7) && (y-1 >= 0) &&
			pieceAt(x+1,y-1) != null && 
			!pieceAt(x+1,y-1).isShield()){
				remove(x+1, y-1);
		}
		if ((x+1 <= 7) && (y+1 <=7) &&
			pieceAt(x+1,y+1) != null && 
			!pieceAt(x+1,y+1).isShield()){
				remove(x+1, y+1);
		}
	}

	/* Returns the x-coordinate of the currently selected piece */
	private int selectedX(){
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (selectedPiece == pieceAt(i,j)){
					return i;
				} 
			}
		}
		return -1;
	}

	/* Returns the y-coordinate of the currently selected piece */
	private int selectedY(){
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (selectedPiece == pieceAt(i,j)){
					return j;
				} 
			}
		}
		return -1;
	}
}