public class Board{
	private static final int SIZE = 8;
	private boolean shouldBeEmpty, fireTurn;
	private boolean hasMoved;
	private Piece selectedPiece;
	private int selectedPieceX, selectedPieceY;
	private Piece[][] pieces;

	private static final String
	FIRE_BOMB_KING = "img/bomb-fire-crowned.png",
	FIRE_BOMB      = "img/bomb-fire.png",
	FIRE_SHIELD_KING = "img/shield-fire-crowned.png",
	FIRE_SHIELD      = "img/shield-fire.png",
	FIRE_PAWN_KING = "img/pawn-fire-crowned.png",
	FIRE_PAWN     = "img/pawn-fire.png",

	WATER_BOMB_KING = "img/bomb-water-crowned.png",
	WATER_BOMB      = "img/bomb-water.png",
	WATER_SHIELD_KING = "img/shield-water-crowned.png",
	WATER_SHIELD      = "img/shield-water.png",
	WATER_PAWN_KING = "img/pawn-water-crowned.png",
	WATER_PAWN     = "img/pawn-water.png";

	public static void main(String[] args){
		//scale the gui
		StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);

    	//create a default board
		Board b = new Board(false);

		//game loop
		while(true){

			//if mouse is pressed try to select
			if (StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)){
					b.select(x, y);
				}
			}

			//if space is pressed try to end turn
			if (StdDrawPlus.isSpacePressed()){
				if (b.canEndTurn()){
					b.endTurn();
					String winner = b.winner();
					if (winner != null){
						System.out.println(winner + " Wins!");
						return;
					}
				}
			}

			//redraw the board and wait 20 milliseconds
			b.drawBoard();
			StdDrawPlus.show(20);
		}
	}

	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		fireTurn = true;
		initBoard();
	}

	public Piece pieceAt(int x, int y){
		if (inBounds(x, y))
			return pieces[x][y];
		return null;
	}

	public boolean canSelect(int x, int y){

		//impossible for the player to select out of bounds but can't hurt to double check
		if (!inBounds(x, y))
			return false;

		Piece p = pieceAt(x, y);

		// if the selected square has a piece, 
		// return true if the player has not selected a piece or has yet to move it
		// checeking hasSelected is redundant but makes the logic more clear
		if(p != null){
			if (!hasSelected() || !hasMoved){

				//check that the piece belongs to the current player
				return p.isFire() == fireTurn;
			}
		}

		// if the square is empty
		else{

			// if there is a selected piece that can be moved (correct player, has not moved or has captured)
			if (hasSelected() && (!hasMoved || selectedPiece.hasCaptured()) && selectedPiece.isFire() == fireTurn){

				// special handling for kings (can move in 4 directions)
				if (selectedPiece.isKing()){

					// if it is moving +- 1 in both x and y, return true if the piece has not moved yet
					if (Math.abs(x - selectedPieceX) == 1 && Math.abs(y - selectedPieceY) == 1){
						return !hasMoved;
					}

					// if it is moving +- 2, make sure it is sucessfully capturing a piece
					else{ 
						if (Math.abs(x - selectedPieceX) == 2 && Math.abs(y - selectedPieceY) == 2){
							Piece captured = pieceAt((x + selectedPieceX) / 2, (y + selectedPieceY) / 2);
							if (captured != null && captured.isFire() != selectedPiece.isFire()){
								return true;
							}
						}
					}
					
				}

				// if the piece is not a king
				else {

					// if its moving +- 1 in the proper direction return true if the piece has not moved yet
					if (Math.abs(x - selectedPieceX) == 1){
						if ((selectedPiece.isFire() && (y - selectedPieceY) == 1) 
							|| (!selectedPiece.isFire() && (y - selectedPieceY) == -1 )){

							return !hasMoved;
						}
					}

					// if it is moving +- 2 in the proper direction check that it is capturing a piece
					if (Math.abs(x - selectedPieceX) == 2){
						if ((selectedPiece.isFire() && (y - selectedPieceY) == 2) 
							|| (!selectedPiece.isFire() && (y - selectedPieceY) == -2 )){

							Piece captured = pieceAt((x + selectedPieceX) / 2, (y + selectedPieceY) / 2);
							if (captured != null && captured.isFire() != selectedPiece.isFire()){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y){

		//if the square is empty
		if (pieceAt(x, y) != null){

			//select the piece
			selectedPiece = pieceAt(x, y);
			selectedPieceX = x;
			selectedPieceY = y;
		}

		else {

			//move the piece to the new coordinates and select it again
			selectedPiece.move(x, y);
			selectedPieceX = x;
			selectedPieceY = y;
			hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y){
		if (p != null && inBounds(x, y)){
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if (!inBounds(x, y)){
			System.out.println("index out of bounds: " + x + ", " + y);
			return null;
		}

		Piece p = pieceAt(x, y);
		if (p == null){
			System.out.println("no piece at: " + x + ", " + y);
			return null;
		}

		pieces[x][y] = null;
		return p;

	}

	public boolean canEndTurn(){
		return hasMoved;
	}	

	public void endTurn(){

		//reset the selected piece and the hasMoved variable, and swap fireturn
		hasMoved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedPieceX = -1;
		selectedPieceY = -1;
		fireTurn = !fireTurn;
	}

	public String winner(){

		//look at every piece and check if it has any water or fire pieces
		boolean hasWater = false, hasFire = false;
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				Piece p = pieceAt(i, j);
				if (p != null)
					if (p.isFire())
						hasFire = true;
					else
						hasWater = true;
			}
		}
		if (hasWater && hasFire)
			return null;
		if (hasWater)
			return "Water";
		if (hasFire)
			return "Fire";
		return "No one";

	}

	private void initBoard(){

        //initialize a piece array to store the state of the board
        pieces = new Piece[SIZE][SIZE];

        // if the board shouldn't be empty, add the pieces to it.
        if (!shouldBeEmpty){
        	for (int i = 0; i < SIZE; i++){
        		for (int j = 0; j < SIZE; j++){
        			if((i + j) % 2 == 0){
        				switch (j){
        					case 0: place(new Piece(true, this, i, j, "pawn"), i, j);
        							break;
        					case 1: place(new Piece(true, this, i, j, "shield"), i, j);
        							break;
        					case 2: place(new Piece(true, this, i, j, "bomb"), i, j);
        							break;
        					case 5: place(new Piece(false, this, i, j, "bomb"), i, j);
        							break;
        					case 6: place(new Piece(false, this, i, j, "shield"), i, j);
        							break;
        					case 7: place(new Piece(false, this, i, j, "pawn"), i, j);
        							break;
        					default:
        							break;
        				}
        			}
        		}
        	}
        }
	}

	//returns the appropirate image path for a Piece p
	private String getImage(Piece p){

		if (p.isFire()){
			if (p.isKing()){
				if (p.isBomb())
					return FIRE_BOMB_KING;
				if (p.isShield())
					return FIRE_SHIELD_KING;
				else
					return FIRE_PAWN_KING;
			}
			else{
				if (p.isBomb())
					return FIRE_BOMB;
				if (p.isShield())
					return FIRE_SHIELD;
				else
					return FIRE_PAWN;
			}
		}
		else{
			if (p.isKing()){
				if (p.isBomb())
					return WATER_BOMB_KING;
				if (p.isShield())
					return WATER_SHIELD_KING;
				else
					return WATER_PAWN_KING;
			}
			else{
				if (p.isBomb())
					return WATER_BOMB;
				if (p.isShield())
					return WATER_SHIELD;
				else
					return WATER_PAWN;
			}
		}
	}

	private boolean hasSelected(){
		return (selectedPiece != null);
	}

	private boolean pieceExists(int i, int j){
		return (inBounds(i, j) && (pieceAt(i, j) != null));
	}


	private void drawBoard(){
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j< SIZE; j++){
				if ((i + j) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.PINK);
				}
				if (selectedPiece != null && i == selectedPieceX && j == selectedPieceY){
					StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (pieceExists(i, j))
					StdDrawPlus.picture(i + .5, j + .5, getImage(pieceAt(i, j)), 1, 1);
			}
		}
	}
	private boolean inBounds(int x, int y){
		return (0 <= x && x < SIZE && 0 <= y && y < SIZE);
	}
}