/** proj0: Exploding Checkers (main file)
  * Author: Zachary Lentz (with guidance of the project page)
  * Modifications:
  * -Different color scheme (too much red made the water pieces feel sad)
  * Extra features:
  * -Uses the "validMove" public method that was crossed out (completed before webpage modified)
  * -Graphical indicators for currently selected piece, tiles you can move to, and whose turn it is
  * -Fully-functional "undo move" option (press backspace), you can take back as many moves as
  *      you'd like, until you officially end your turn.
  * -After a player has won, you may press "n" to start a new game
  * -Stalemates are handled like in real checkers: if you cannot make a legal move, you lose.
  * -Text prompts to end turn or start a new game
  * -Can be played at higher resolutions with a performance tradeoff. Call "java board <resolution>"
  *      where resolution is an integer. Text will not scale because I did not go beyond the
  *      StdDrawPlus drawing commands.
  * -A short animation plays when a player's piece is captured.
  */

import java.awt.event.*; // Use this to use more keystrokes

public class Board{

	/****** SECTION 1: Instance variables and constuctor ******************************************/
	private Piece[][] grid = new Piece[8][8]; // 8x8 grid that stores pointers to Pieces
	private Piece[][] oldGrid = new Piece[8][8]; // used to implement undo
	private boolean fireTurn = true; // is it the fire team's turn?
	private boolean hasMoved = false; // has the player moved?
	private Piece currPiece; // the piece that is currently being selected
	private int[] currLoc = new int[]{-1, -1}; // the last tile selected. Used only for draw steps.
	private Piece currPieceDraw; // the current piece in the draw step of select

	private boolean selectorOn = false; // indicates if our selected-piece-highlight graphic is on

	private int size = 8;   // number of squares on one side of the grid
	private double r = 340; // radius of each square default 0.5, 340
	private double pL = 680; // length of each piece sprite default 1, 680
	private int screenSize = 512; // Resolution e.g. 512 x 512. 980 looks best on my screen.

	private double spacing = r/6; // distance from turn indicator to board
	private double grayBorder = r/12; // size of gray border around turn indicator
	private boolean canEndShown = false; // is the text that promps you to end turn visible?

	public Board(boolean shouldBeEmpty){
		// if true, initializes an empty board. if false, uses default configuration
		if (!shouldBeEmpty){
			grid[0][0] = new Piece(true, this, 0, 0, "pawn");
			grid[2][0] = new Piece(true, this, 2, 0, "pawn");
			grid[4][0] = new Piece(true, this, 4, 0, "pawn");
			grid[6][0] = new Piece(true, this, 6, 0, "pawn");
			grid[1][1] = new Piece(true, this, 1, 1, "shield");
			grid[3][1] = new Piece(true, this, 3, 1, "shield");
			grid[5][1] = new Piece(true, this, 5, 1, "shield");
			grid[7][1] = new Piece(true, this, 7, 1, "shield");
			grid[0][2] = new Piece(true, this, 0, 2, "bomb");
			grid[2][2] = new Piece(true, this, 2, 2, "bomb");
			grid[4][2] = new Piece(true, this, 4, 2, "bomb");
			grid[6][2] = new Piece(true, this, 6, 2, "bomb");
			grid[1][7] = new Piece(false, this, 1, 7, "pawn");
			grid[3][7] = new Piece(false, this, 3, 7, "pawn");
			grid[5][7] = new Piece(false, this, 5, 7, "pawn");
			grid[7][7] = new Piece(false, this, 7, 7, "pawn");
			grid[0][6] = new Piece(false, this, 0, 6, "shield");
			grid[2][6] = new Piece(false, this, 2, 6, "shield");
			grid[4][6] = new Piece(false, this, 4, 6, "shield");
			grid[6][6] = new Piece(false, this, 6, 6, "shield");
			grid[1][5] = new Piece(false, this, 1, 5, "bomb");
			grid[3][5] = new Piece(false, this, 3, 5, "bomb");
			grid[5][5] = new Piece(false, this, 5, 5, "bomb");
			grid[7][5] = new Piece(false, this, 7, 5, "bomb");
		}
	}

	/****** SECTION 2: Public game function methods ***********************************************/
	public Piece pieceAt(int x, int y){
		// gets the piece at position (x,y) or returns null if unable
		if (isInBounds(x, y)){
			return grid[x][y];
		}
		return null;
	}
	public void select(int x, int y){
		// selects the piece at (x,y). It is assumed that this can be done.
		if (isPieceAt(x, y)){
			currPiece = pieceAt(x, y);
		} else {
			currPiece.move(x, y);
			hasMoved = true;
		}
	}
	public void place(Piece p, int x, int y){
		// places p at (x,y), replacing a piece if necessary. Does nothing if oob or p == null
		if (isInBounds(x, y) && p != null){
			grid[x][y] = p;
		}
	}
	public Piece remove(int x, int y){
		// removes the piece at (x, y) if possible and returns it, or returns null with an appropriate message.
		if (!isInBounds(x, y)){
			System.out.println("The coordinate(" + x + ", " + y + ") does not exist.");
			return null;
		}
		Piece p = grid[x][y];
		if (p != null){
			grid[x][y] = null;
		} else {
			System.out.println("There is no piece at (" + x + ", " + y + ").");
		}
		return p;
	}
	public void endTurn(){
		// called at the end of the turn, handles switching players and other misc tasks
		hasMoved = false;
		currPiece.doneCapturing();
		currPiece = null;
		fireTurn = !fireTurn;
		copyGridOnto(grid, oldGrid);
	}
	public String winner(){
		// returns the winner of the game, or null if the game is not over.
		int[] counts = countPieces();
		int nFire = counts[0];
		int nWater = counts[1];
		if (nFire == 0 && nWater == 0){
			return "No one";
		}
		if (nFire == 0){
			return "Water";
		}
		if (nWater == 0){
			return "Fire";
		}
		return null;
	}

	/****** SECTION 3: Private game function methods **********************************************/
	private void undoMove(){
		// returns the grid to the state of oldGrid.
		if (hasMoved){
			// extra step at start: remove indicators if they exist (i.e., a multijump could have happened)
			int x = currLoc[0];
			int y = currLoc[1];
			for (int i = -2; i < 5; i += 4){
				for (int j = -2; j < 5; j+=4){
					if (!isPieceAt(x + i, y + j)){
						drawTile(x + i, y + j);
					}
				}
			}
			boolean[][] diff = gridDiff(oldGrid, grid);
			copyGridOnto(oldGrid, grid); // undo by copying our saved data in oldGrid
			for (int i = 0; i < size; i++){
				for (int j = 0; j < size; j++){
					if (diff[i][j]){
						drawTile(i, j);
						if (isPieceAt(i, j)){
							drawPiece(pieceAt(i, j), i, j);
						}
					}
				}
			}
			currLoc[0] = -1;
			currLoc[1] = -1;
			selectorOn = false;
			hasMoved = false;
			hideCanEnd();
		}
	}
	private String endIfNoMoves(String currOutcome){
		// This function runs after the standard "winner()" to check for stalemetes.
		// You lose if you cannot make a move.
		if (currOutcome != null){
			return currOutcome;
		}
		if (!hasMoved && !hasAnyMove(fireTurn)){
			if (fireTurn){
				return "By stalemate, water";
			} else {
				return "By stalemate, fire";
			}
		}
		return null;
	}

	/****** SECTION 4: Public boolean methods *****************************************************/
	public boolean canSelect(int x, int y){
		// returns true if there is a piece at (x,y) that can be selected or if the empty space can be selected
		if (isPieceAt(x, y)){
			Piece p = pieceAt(x, y);
			if (fireTurn != p.isFire()){
				return false; // checks that it's the correct turn for this piece
			}
			if (hasMoved){
				return false; // makes sure the player hasn't already moved a piece
			}
			return true; // otherwise return true
		}
		// now we're handling the empty space: can be selected if we've selected a piece and this is a valid move
		if (currPiece == null){
			return false;
		}
		int[] xy = getCoords(currPiece);
		if (validMove(xy[0], xy[1], x, y)){
			return true;
		}
		return false;
	}
	public boolean canEndTurn(){
		// returns true if the player has made a move
		return hasMoved;
	}

	/****** SECTION 5: Private boolean methods ****************************************************/
	private boolean isInBounds(int x, int y){
		// returns true if the input coordinates are on the board
		return (x < size && y < size && x >= 0 && y >= 0);
	}
	private boolean isPieceAt(int x, int y){
		// returns true if there is a piece at input coordinates
		if (pieceAt(x, y) == null){
			return false;
		}
		return true;
	}
	private boolean[][] gridDiff(Piece[][] gridA, Piece[][] gridB){
		// returns a 2D array of booleans that is true whereever gridA and gridB are different
		boolean[][] diff = new boolean[size][size];
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if ((gridA[i][j] == null) ^ (gridB[i][j] == null)){
					diff[i][j] = true;
				}
			}
		}
		return diff;
	}
	private boolean hasAMove(int x, int y){
		// returns true if the piece at (x,y) has a move.
		if (isPieceAt(x, y)){
			for (int i = -2; i < 3; i++){
				for (int j = -2; j < 3; j++){
					if (validMove(x, y, x + i, y + j)){
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean hasAnyMove(boolean fireSide){
		// returns true if any of the current player's pieces has a move
		Piece p;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if (isPieceAt(i, j)){
					p = pieceAt(i, j);
					if (p.isFire() == fireSide){
						if (hasAMove(i, j)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		// returns true if piece at (xi,yi) can move to (xf,yf) normally or by capturing
		int dx = xf - xi;
		int dy = yf - yi;
		if (Math.abs(dx) > 2 || Math.abs(dy) > 2){
			return false; // can never possibly move more than two away
		}
		if ((Math.abs(dx + dy)) % 2 == 1){
			return false; // must stay on the same-colored tiles
		}
		if (dx == 0 || dy == 0){
			return false; // cannot move in a straight line
		}
		if (!isInBounds(xf, yf)){
			return false; // cannot move off of the board
		}
		if (isPieceAt(xf, yf)){
			return false; // cannot move to a space that's already occupied
		}
		if (!isPieceAt(xi, yi)){
			return false; // cannot move a piece that doesn't exist
		}
		// we've eliminated all moves that are always impossible, and allowed non-capture moves
		// next thing to do is disallow capture moves if the adjacent tile is free or has our own piece in it
		for (int x = -1; x < 2; x += 2){
			for (int y = -1; y < 2; y += 2){
				if (dx == 2*x && dy == 2*y){
					if (!isPieceAt(xi + x, yi + y)){
						return false;
					} else if (pieceAt(xi + x, yi + y).isFire() == fireTurn){
						return false; // we can't capture our own pieces!
					}
				}
			}
		}
		// the last thing to do is enforce directionality of pieces that aren't kings
		Piece p = pieceAt(xi, yi);
		if (!p.isKing()){
			if (p.isFire()){
				if (dy < 0){
					return false; // fire types cannot move down
				}
			} else {
				if (dy > 0){
					return false; // water types cannot move up
				}
			}
		}
		// this bit of code disallows adjacent moves if we've already captured a piece
		if (p.hasCaptured()){
			if (Math.abs(dx) == 1){
				return false;
			}
		}
		// this prevents pieces from moving more than once on a turn without capturing
		if (!p.hasCaptured() && hasMoved){
			return false;
		}
		return true; //otherwise return true
	}

	/****** SECTION 6: Private data-management methods ********************************************/
	private int[] getCoords(Piece p){
		// Finds the coordinates of p. Returns an array with out[0] = x and out[1] = y
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				if (grid[x][y] == p){
					return new int[]{x, y};
				}
			}
		}
		return new int[]{-1, -1}; // returns negative values if piece doesn't exist
	}
	private void copyGridOnto(Piece[][] fromGrid, Piece[][] toGrid){
		// Takes the contents of fromGrid and makes pointers to duplicate objects in toGrid.
		// Afterwards, the grids will store pointers to different sets of objects.
		Piece fromPiece;
		Piece toPiece;
		String type;
		boolean side;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				fromPiece = fromGrid[i][j];
				if (fromPiece != null){
					side = fromPiece.isFire();
					if (fromPiece.isBomb()){
						type = "bomb";
					} else if (fromPiece.isShield()){
						type = "shield";
					} else {
						type = "pawn";
					}
					toPiece = new Piece(side, this, i, j, type);
					toGrid[i][j] = toPiece;
					/** If the piece is a king we need to internally move it to the king-me row and then back.
					  * Put it on the off-tiles so we don't screw anything up... feels hacky but we can't add
					  * a public makeKing method or start off our pieces as kings.
					  * We can only move a piece without taking an error if it's on the main grid (not oldGrid)
					  * When we hit this bit of code, our two grids agree that there is a piece on this tile
					  * because we just synced the grids at the point using the data in fromGrid. We care about
					  * the case where fromGrid has a kinged piece and toGrid does not.
					  * If fromGrid is oldGrid, then newGrid has a normal piece in the tile. We simply need to
					  * move this piece to the back row and back without any fancy tricks.
					  * If fromGrid is grid, then toGrid is oldGrid. We need to temporarily store the piece
					  * on grid, place our unKinged piece on grid in that spot, move it to the back row and
					  * back to it's spot, then swap the correct piece back into grid.
					  */
					if (fromPiece.isKing()){
						Piece placeholder = null;
						if (fromGrid == grid){
							// oldGrid has the piece that needs to be crowned, placeholder needed!
							placeholder = grid[i][j];
							grid[i][j] = toPiece;
						}
						if (side){
							toPiece.move(0, 7);
							toPiece.move(i, j);
						} else {
							toPiece.move(1, 0);
							toPiece.move(i, j);
						}
						if (fromGrid == grid){
							// replace placeholder stuff if we did it
							grid[i][j] = placeholder;
						}
					}
				} else {
					toGrid[i][j] = null;
				}
			}
		}
	}
	private int[] countPieces(){
		// Tallies up the number of fire and water pieces.
		int nFire = 0;
		int nWater = 0;
		Piece p;
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				p = grid[x][y];
				if (p != null){
					if (p.isFire()){
						nFire += 1;
					} else {
						nWater += 1;
					}
				}
			}
		}
		return new int[]{nFire, nWater};
	}

	/****** SECTION 7: Critical draw functions ****************************************************/
	private void initWindow(){
		// Sets basic size, etc. of game window
		StdDrawPlus.setCanvasSize(screenSize, screenSize);
		StdDrawPlus.setXscale(0, 2 * size * r);
		StdDrawPlus.setYscale(0, 2 * size * r);
	}
	private void setupBoard(){
		// Draws the intial board and sets up the oldGrid
		this.drawBoard();
		this.drawAllPieces();
		drawRedGray();
		drawBlueGray();
		copyGridOnto(grid, oldGrid);
	}
	private void drawSquare(int x, int y, double size){
		// Draws a single square of the board at the current color
		if (!isInBounds(x, y)){
			return;
		}
		StdDrawPlus.filledSquare(2*x*r + r, 2*y*r + r, size);
	}
	private void drawTile(int x, int y){
		// Draws a single square of the board at the board color
		if ((x + y) % 2 == 0){
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		}
		drawSquare(x, y, r);
	}
	private void drawBoard(){
		// Draws a blank board using the StdDrawPlus library.
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				drawTile(x, y);
			}
		}
	}
	private void drawPiece(Piece p, int x, int y){
		// Draws a single piece at (x, y)
		if (!isInBounds(x, y)){
			return;
		}
		// Now we need to go through a decision tree to decide which png to use
		String img = "img/";
		if (p.isBomb()){
			img = img + "bomb-";
		} else if (p.isShield()){
			img = img + "shield-";
		} else {
			img = img + "pawn-";
		}
		if (p.isFire()){
			img = img + "fire";
		} else {
			img = img + "water";
		}
		if (p.isKing()){
			img = img + "-crowned";
		}
		img = img + ".png";
		StdDrawPlus.picture(2*x*r + r, 2*y*r + r, img, pL, pL);

	}
	private void drawAllPieces(){
		// Calls drawPiece on every piece on the board.
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				if (grid[x][y] != null){
					drawPiece(grid[x][y], x, y);
				}
			}
		}
	}
	private void clearScreen(){
		// Turns all of the screen's contents to white.
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(8*r, 8*r, 18*r);
	}
	private void drawMove(int xi, int yi, int xf, int yf){
		// draws what needs to be drawn during a move command. To be called BEFORE Piece.move(xf, yf).
		Piece p = pieceAt(xi, yi);
		StdDrawPlus.show(0);
		drawRemove(xi, yi);
		drawPlace(p, xf, yf);
		StdDrawPlus.show();
		if ((Math.abs(xf-xi) == 2 && Math.abs(yf - yi) == 2)){
			int xHalf = (xf + xi)/2;
			int yHalf = (yf + yi)/2;
			drawDeath(pieceAt(xHalf, yHalf), xHalf, yHalf); // remove captured piece
			if (p.isBomb()){
				// loop through surrounding pieces and draw deaths when not shield
				Piece boomTarget;
				for (int i = -1; i < 2; i += 2){
					for (int j = -1; j < 2; j +=2){
						if (!((xf + i == xHalf) && (yf + j == yHalf))){
							if (isPieceAt(xf + i, yf + j)){
								boomTarget = pieceAt(xf + i, yf + j);
								if (!boomTarget.isShield()){
									drawDeath(boomTarget, xf + i, yf + j);
								}
							}
						}
					}
				}
				drawDeath(p, xf, yf); // remove exploded bomb
			}
		}
	}
	private void drawSelectPre(int x, int y){
		// draws what needs to be drawn during a call to select. To be called BEFORE select.
		int xOld = currLoc[0];
		int yOld = currLoc[1];
		currLoc[0] = x;
		currLoc[1] = y;
		if (isPieceAt(x, y)){
			removeSelector(currPieceDraw, xOld, yOld);
			currPieceDraw = pieceAt(x, y);
			drawSelector(currPieceDraw, x, y);
		} else {
			removeAllIndicators(xOld, yOld);
			drawMove(xOld, yOld, x, y);
			selectorOn = false;
			showCanEnd();
		}
	}
	private void drawSelectPost(int x, int y){
		// draws what needs to be drawn during a call to select. To be called AFTER select.
		// highlights the piece's box if a double jump is possible.
		if (hasMoved){
			for (int i = -2; i < 3; i += 4){
				for (int j = -2; j < 3; j += 4){
					if ((validMove(x, y, x + i, y + j)) && currPieceDraw.hasCaptured()){
						drawSelector(currPiece, x, y);
						break;
					}
				}
			}
		}
	}
	private void drawPlace(Piece p, int x, int y){
		// draws p at (x, y), replacing whatever was drawn in that tile initially, unless place will not run.
		if (isInBounds(x, y) && p != null){
			drawTile(x, y);
			drawPiece(p, x, y);
		}
	}
	private void drawRemove(int x, int y){
		// if remove will run, draws over the piece at (x, y). To be run BEFORE remove.
		if (!isInBounds(x, y)){
			return;
		}
		Piece p = grid[x][y];
		if (p != null){
			drawTile(x, y);
		}
	}
	private void drawEndTurn(){
		// draws what needs to be drawn at the end of the turn. To be run AFTER end turn.
		if (selectorOn){
			removeSelector(currPiece, currLoc[0], currLoc[1]);
		}
		currLoc[0] = -1;
		currLoc[1] = -1;
		currPieceDraw = null;
		hideCanEnd();
		drawTurnIndicator();
	}

	/****** SECTION 8: Bonus draw functions *******************************************************/
	private void drawSelector(Piece p, int x, int y){
		// highlights the selected piece
		if (fireTurn){
			StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		drawSquare(x, y, 14*r/15);
		drawPiece(p, x, y);
		selectorOn = true;
		drawAllIndicators(x, y);
	}
	private void removeSelector(Piece p, int x, int y){
		// removes a piece's highlight
		drawTile(x, y);
		if (p != null){
			drawPiece(p, x, y);
		}
		selectorOn = false;
		removeAllIndicators(x, y);
	}
	private void drawIndicator(int x, int y){
		// highlights an empty space
		StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
		drawSquare(x, y, 4*r/5);
	}
	private void drawAllIndicators(int x, int y){
		// shows where you can make a move
		for (int dx = -2; dx < 3; dx++){
			for (int dy = -2; dy < 3; dy++){
				if (validMove(x, y, x + dx, y + dy)){
					drawIndicator(x + dx, y + dy);
				}
			}
		}
	}
	private void removeAllIndicators(int x, int y){
		// removes highlights that showed where you could move starting at (x, y)
		for (int i = -2; i < 3; i++){
			for (int j = -2; j < 3; j++){
				if ((isInBounds(x + i, y + j)) && (grid[x + i][y + j] == null)){
					drawTile(x + i, y + j);
				}
			}
		}
	}
	private void drawRedTurn(){
		// Indicates that it's fire's turn
		StdDrawPlus.setPenColor(StdDrawPlus.RED);
		StdDrawPlus.filledSquare(4*r, -(6*r + spacing), 6*r - grayBorder);
		StdDrawPlus.filledSquare(-(2*r + spacing), 0, 2*r - grayBorder);
	}
	private void drawRedGray(){
		// Background of the turn indicater on fire's side. Draw after turn is over.
		StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
		StdDrawPlus.filledSquare(4*r, -(6*r + spacing), 6*r);
		StdDrawPlus.filledSquare(-(2*r + spacing), 0, 2*r);
	}
	private void drawBlueTurn(){
		// Indicates that it's water's turn
		StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
		StdDrawPlus.filledSquare(12*r, 16*r + 6*r + spacing, 6*r - grayBorder);
		StdDrawPlus.filledSquare(16*r + 2*r + spacing, 16*r, 2*r - grayBorder);
	}
	private void drawBlueGray(){
		// Background of the turn indicator on water's side. Draw after turn is over.
		StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
		StdDrawPlus.filledSquare(12*r, 16*r + 6*r + spacing, 6*r);
		StdDrawPlus.filledSquare(16*r + 2*r + spacing, 16*r, 2*r);
	}
	private void drawTurnIndicator(){
		// draws indicators that show whose turn it is
		if (fireTurn){
			drawRedTurn();
			drawBlueGray();
		} else {
			drawBlueTurn();
			drawRedGray();
		}
	}
	private void showCanEnd(){
		// Shows text indicating that the player can end the turn or undo their turn
		if (!canEndShown){
			StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
			StdDrawPlus.text(2*r + r/2, 16*r + r/3, "Press space to end turn");
			StdDrawPlus.text(16*r - 2*r - r/2, -r/2, "Press backspace to undo");
			canEndShown = true;
		}
	}
	private void hideCanEnd(){
		// Removes text that indicated the player could end their turn or undo
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(2*r + r/2, 16*r + 3*r, 3*r);
		StdDrawPlus.filledSquare(16*r - 2*r - r/2, -3*r - r/10, 3*r + r/10);
		canEndShown = false;
	}
	private void showCanNewGame(){
		// Displays text that indicates the player can start a new game by pressing n
		hideCanEnd();
		StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
		StdDrawPlus.text(2*r + r/2, 16*r + r/3, "Press n for new game");
	}
	private void drawDeath(Piece p, int x, int y){
		// plays the piece death animation
		int deathBlinks = 3;
		int blinkLength = 250;
		drawTile(x, y);
		for (int i = 0; i < deathBlinks; i ++){
			StdDrawPlus.show(blinkLength);
			drawPiece(p, x, y);
			StdDrawPlus.show(blinkLength);
			drawTile(x, y);
		}
		StdDrawPlus.show();
	}

	/****** SECTION 9: main ***********************************************************************/
	public static void main(String[] args){
		// Starts a GUI-supported version of the game
		Board gameBoard = new Board(false); // create Board object with pieces
		if (args.length > 0){
			gameBoard.screenSize = Integer.parseInt(args[0]); // use inputted resolution if applicable
			if (gameBoard.screenSize < 512){
				gameBoard.screenSize = 512;
			}
		}
		gameBoard.initWindow(); // Set up the window
		StdDrawPlus.show(0);
		gameBoard.setupBoard(); // Draw the board
		gameBoard.drawTurnIndicator(); // Show that it's fire's turn first
		StdDrawPlus.show();
		String outcome = null; // Initialize outcome
		while (true){
		// outer loop in case we play a second game
			while (outcome == null){
			// Inner game-flow loop until we have a winner
				if (StdDrawPlus.mousePressed()){
					// Select the tile or piece we clicked on
					double xReal = StdDrawPlus.mouseX();
					double yReal = StdDrawPlus.mouseY();
					double r = gameBoard.r;
					int x = (int) (xReal/(2*r));
					int y = (int) (yReal/(2*r));
					if (gameBoard.canSelect(x, y)){
						boolean drawAfter = false;
						if (gameBoard.currLoc[0] != x || gameBoard.currLoc[1] != y){
							gameBoard.drawSelectPre(x, y);
							drawAfter = true;
						}
						gameBoard.select(x, y);
						if (drawAfter){
							gameBoard.drawSelectPost(x, y);
							drawAfter = false;
						}
					}
				}
				if (StdDrawPlus.isSpacePressed()){
					// End the turn if we press space
					if (gameBoard.canEndTurn()){
						gameBoard.endTurn();
						gameBoard.drawEndTurn();
					}
				}
				if (StdDrawPlus.isKeyPressed(KeyEvent.VK_BACK_SPACE)){
					// Undo our move we press backspace
					gameBoard.undoMove();
					StdDrawPlus.show(0);
					gameBoard.drawBoard();
					gameBoard.drawAllPieces();
					StdDrawPlus.show();
				}
				outcome = gameBoard.winner(); // Check if either side has ran out of pieces
				outcome = gameBoard.endIfNoMoves(outcome); // Make sure current player has a legal move
			}
			System.out.println(outcome + " has won!"); // Victory message
			gameBoard.showCanNewGame(); // Indicate that we can start a new game
			while (true){
				if (StdDrawPlus.isNPressed()){
					// When n is pressed, start a new game.
					gameBoard.clearScreen(); // Make sure we're drawing on a blank canvas
					gameBoard = new Board(false); // Create a new board with the initial piece configuration
					gameBoard.setupBoard(); // Draw the board
					gameBoard.drawTurnIndicator(); // Show that it's fire's turn first
					outcome = null; // Reset winner
					break; // Proceed back to game-flow loop
				}
			}
		}
	}
}