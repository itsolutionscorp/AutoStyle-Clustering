public class Board {
	private Piece[][] pieces;
	private Piece actor;
	private boolean takeAction;
	private boolean hasMoved;
	private boolean highlight;
	private int[] h;
	private int[] s; // record currently selected gird
	private int turn;

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		Board game = new Board(false);

		while(true) {
			// re-draw board
			game.drawBoard();
			
			// check end turn
			if (StdDrawPlus.isSpacePressed())
				if (game.canEndTurn())
					game.endTurn();
			
			// check selection
			if (StdDrawPlus.mousePressed()) {
				// extract coordinates
				int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                // check if can select
                if (game.canSelect(x, y)) {
                	// highlight the selected grid
                	game.h[0] = x;
	                game.h[1] = y;
	                game.highlight = true;
                	// select the coordinates
                	game.select(x, y);
            	}	
            }
            
			// show
			StdDrawPlus.show(10);

			// check winner
			if (game.winner() != null) {
				game.drawBoard();
				StdDrawPlus.show(10);
				break;
			}
		}
		System.out.println(game.winner() + " wins!");
	}

	public Board(boolean shouldBeEmpty) {
		// initialize pieces
		pieces = new Piece[8][8];
		if (!(shouldBeEmpty)) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 3; j++) {
					if ((i + j) % 2 == 0){
						if (j == 0)
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
						else if (j == 1)
							pieces[i][j] = new Piece(true, this, i, j, "shield");
						else
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
					}
				}
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 5; j < 8; j++) {
					if ((i + j) % 2 == 0){
						if (j == 8 - 1)
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
						else if (j == 8 - 2)
							pieces[i][j] = new Piece(false, this, i, j, "shield");
						else
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
				}
			}
		}
		actor = null;
		takeAction = false;
		hasMoved = false;
		highlight = false;
		h = new int[2]; // highlight grid {x, y}
		s = new int[2]; // record selected grid {x, y}
		turn = 0;
	}

	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (highlight && i == h[0] && j == h[1])
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				else if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else 				  	   
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, path(pieces[i][j]), 1, 1);
                }
			}
		}
	}

	private static String path(Piece p) {
		String s = "img/";

		if (p.isBomb())
			s += "bomb";
		else if (p.isShield())
			s += "shield";
		else
			s += "pawn";

		if (p.isFire())
			s += "-fire";
		else
			s += "-water";

		if (p.isKing())
			s += "-crowned.png";
		else
			s += ".png";

		return s;
	}

	public Piece pieceAt(int x, int y) {
		if (x >= 8 || y >= 8) {
			System.out.println("PieceAt: x, y out of bounds!");
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			System.out.println("Selection out of board");
			return false;
		}
		Piece toSelect = pieces[x][y];
		/* --- check selecting an empty grid --- */
		if (toSelect == null) {
			if (!(hasMoved) && actor != null && 
				validMove(s[0], s[1], x, y))
				return true;
			if (actor != null && actor.hasCaptured() &&
				validMove(s[0], s[1], x, y))
				return true;
		}

		/* --- check selecting a own-team piece --- */
		else if (toSelect.side() == turn) {
			// The player has not selected a piece yet
			if (actor == null)
				return true;
			// The player has selected a piece, but didn't move it
			if (!(hasMoved))
				return true;
		}

		/* --- base case --- */
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece curr = pieces[xi][yi];
		// check null case
		if (curr == null)
			return false;
		// check backward move
		if (!(curr.isKing())) {
			if (curr.isFire() && yi >= yf)
				return false;
			if (!(curr.isFire()) && yi <= yf)
				return false;
		}
		// check moving distance & direction
		int dx = xf - xi;
		int dy = yf - yi;
		if (Math.abs(dx) == Math.abs(dy)) {
			if (Math.abs(dx) == 1 && !(hasMoved))
				return true;
			// capture case
			if (Math.abs(dx) == 2) {
				if (dx < 0 && dy < 0) {
					if (pieces[xi - 1][yi - 1] != null &&
						pieces[xi - 1][yi - 1].side() != turn)
						return true;
				}
				if (dx < 0 && dy > 0) {
					if (pieces[xi - 1][yi + 1] != null &&
						pieces[xi - 1][yi + 1].side() != turn)
						return true;
				}
				if (dx > 0 && dy < 0) {
					if (pieces[xi + 1][yi - 1] != null &&
						pieces[xi + 1][yi - 1].side() != turn)
						return true;
				}
				if (dx > 0 && dy > 0) {
					if (pieces[xi + 1][yi + 1] != null &&
						pieces[xi + 1][yi + 1].side() != turn)
						return true;
				}
			}
		}
		// base case
		return false;
	}

	public void select(int x, int y) {
		s[0] = x;
		s[1] = y;
		Piece toSelect = pieces[x][y];
		if (toSelect != null) 
			actor = toSelect;
		else
			takeAction = true;

		// check action
		if (takeAction) {
			actor.move(s[0], s[1]);
			takeAction = false;
			hasMoved = true;
			// check the special case in which a bomb explodes
			if (pieces[s[0]][s[1]] == null)
				highlight = false;
		}		
	}

	public void place(Piece p, int x, int y) {
		if (x >= 8 || y >= 8 || p == null) {
			System.out.println("Invalid Placement!");
			return;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece curr = pieces[i][j];
				if (p == curr) {
					remove(i, j);
					break;
				}
			}
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			System.out.println("Invalid Removement: coordinates out of bound");
			return null;
		}

		Piece toRemove = pieces[x][y];
		if (toRemove == null) {
			System.out.println("Invalid Removement: no piece selected");
			return null;
		}

		pieces[x][y] = null;
		return toRemove;
	}

	public boolean canEndTurn() {
		boolean captured = false;
		if (actor != null)
			captured = actor.hasCaptured();
		if (hasMoved || captured)
			return true;
		return false;
	}
	
	public void endTurn() {
		// reset current actor's didCapture attribute
		actor.doneCapturing();

		// reset game-wide variables
		actor = null;
		takeAction = false;
		hasMoved = false;
		highlight = false;

		// Switch player. Fire moves on 0. Water moves on 1;
		if (turn == 0)
			turn = 1;
		else
			turn = 0;
	}

	public String winner() {
		int fire_count = 0;
		int water_count = 0;
		Piece p;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				p = pieces[i][j];
				if (p != null) {
					if (p.isFire())
						fire_count += 1;
					else
						water_count += 1;
				}
				if (fire_count > 0 && water_count > 0)
					break;
			}
		}
		if (fire_count == 0) {
			if (water_count == 0)
				return "No one";
			return "Water";
		}
		if (water_count == 0)
			return "Fire";
		return null;
	}
}