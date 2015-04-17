import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private int player;
	private Piece selected;
	private int selected_x;
	private int selected_y;
	private boolean canMove;

	private void drawBoard(int N) {
		//draw board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
		//draw pieces
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				Piece piece = pieces[x][y];
				if (piece != null) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, imageGet(piece), 1, 1);
				}
			}
		}
		//color selected square
		if (selected != null) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(selected_x + 0.5, selected_y + 0.5, 0.5);
			StdDrawPlus.picture(selected_x + 0.5, selected_y + 0.5, imageGet(pieceAt(selected_x,selected_y)), 1, 1);
		}
	}

	private String imageGet(Piece p) {
		if (p.isBomb()) {
			if (p.isKing()) {
				if (p.isFire()) {
					return "img/bomb-fire-crowned.png";
				}
				return "img/bomb-water-crowned.png";
			} else {
				if (p.isFire()) {
					return "img/bomb-fire.png";
				}
				return "img/bomb-water.png";
			}

		} else if (p.isShield()) {
			if (p.isKing()) {
				if (p.isFire()) {
					return "img/shield-fire-crowned.png";
				}
				return "img/shield-water-crowned.png";
			} else {
				if (p.isFire()) {
					return "img/shield-fire.png";
				}
				return "img/shield-water.png";
			}

		} else {
			if (p.isKing()) {
				if (p.isFire()) {
					return "img/pawn-fire-crowned.png";
				}
				return "img/pawn-water-crowned.png";
			} else {
				if (p.isFire()) {
					return "img/pawn-fire.png";
				}
				return "img/pawn-water.png";
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i += 2) {
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
				pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");

				pieces[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
				pieces[i][6] = new Piece(false, this, i, 6, "shield");
				pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
			}
		}
		player = 0;
		selected = null;
		canMove = true;
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		} else if (pieces[x][y] == null) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	private boolean myPiece(Piece p) {
		return player == p.side();
	}

	private boolean moveTypeChecker(int xi, int yi, int xf, int yf, int a, Piece p) {
		//can't capture own pieces
		if (a == 2) {
			Piece captured = pieceAt((xf + xi)/2, (yf + yi)/2);
			if ((p.isFire() && captured.isFire()) ||
				(!p.isFire() && !captured.isFire())) {
				return false;
			}
		}
		if (p.isKing()) {
			return (Math.abs(yi - yf) == a);
		} else if (p.isFire()) {
			return (yf - yi == a);
		} else {
			return (yi - yf == a);
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf, Piece p) {
		if (xf >= 0 && xf <= 7 && yf >= 0 && yf <= 7) {
			//regular movement
			//a piece that has captured can only capture for the rest of the turn
			if (Math.abs(xi - xf) == 1 && !p.hasCaptured()) {
				return moveTypeChecker(xi, yi, xf, yf, 1, p);

			//capturing
			} else if (Math.abs(xi - xf) == 2) {
				if (pieceAt((xf + xi)/2, (yf + yi)/2) != null) {
					return moveTypeChecker(xi, yi, xf, yf, 2, p);
				}
			}
		}
		return false;
	}
	
	public boolean canSelect(int x, int y) {
		if (canMove) {
			//selecting a piece
			if (pieceAt(x,y) != null) {
				if ((selected != null && !selected.hasCaptured()) || 
					selected == null) {
					return myPiece(pieceAt(x,y));
				}
			//selecting empty space
			} else {
				if (selected != null) {
					return validMove(selected_x, selected_y, x, y, selected);
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x,y);
		//select a piece
		if (p != null) {
			selected = p;
		} else {
		//select empty space/move piece
			selected.move(x,y);
			if (!selected.hasCaptured()) {
				canMove = false;
			}
		}
		selected_x = x;
		selected_y = y;

		//bombCaptures
		if (selected.hasCaptured() && selected.isBomb()) {
			int[][] collateral_dmg = new int[4][2];
			int left = selected_x - 1;
			int right = selected_x + 1;
			int up = selected_y + 1;
			int down = selected_y - 1;


			collateral_dmg[0][0] = left;
			collateral_dmg[0][1] = up;
			collateral_dmg[1][0] = right;
			collateral_dmg[1][1] = up;
			collateral_dmg[2][0] = left;
			collateral_dmg[2][1] = down;
			collateral_dmg[3][0] = right;
			collateral_dmg[3][1] = down;

			for (int i = 0; i < 4; i++) {
				Piece q = pieceAt(collateral_dmg[i][0], collateral_dmg[i][1]);
				if (q != null && !q.isShield()) {
					remove(collateral_dmg[i][0], collateral_dmg[i][1]);
				}
			}
			remove(selected_x, selected_y);
			selected = null;
			canMove = false;
		}
	}


	public void place(Piece p, int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Coordinates not on grid");
			return null;
		} else if (pieceAt(x,y) == null) {
			System.out.println("No piece at coordinates (" + Integer.toString(x) +", " + Integer.toString(y) + ")");
			return null;
		}
		Piece goodbye = pieceAt(x,y);
		pieces[x][y] = null;
		return goodbye;
	}

	public boolean canEndTurn() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece p = pieceAt(x,y);
				if (p != null && p.hasCaptured()) {
					return true;
				}
			}
		}
		return !canMove;
	}

	public void endTurn() {
		if (canEndTurn()) {
			if (selected != null) {
				selected.doneCapturing();
				selected = null;
			}
			canMove = true;
			switchPlayer();
		}
	}

	private void switchPlayer() {
		player = (player + 1) % 2;
	}

	public String winner() {
		//false = no pieces, true = pieces left
		boolean fire = false;
		boolean water = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = pieceAt(i,j);
				if (p != null && p.side() == 0) {
					fire = true;
				} else if (p != null && p.side() == 1) {
					water = true;
				}
				if (fire && water) {
					return null;
				}
			}
		}
		if (fire) {
			return "Fire";
		} else if (water) {
			return "Water";
		} else {
			return "No one";
		}
	}


	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board board = new Board(false);

		while(board.winner() == null) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                Piece p = board.pieceAt((int) x, (int) y);
                //clicking on a piece
                if (p != null) {
                	if (board.canSelect((int) x, (int) y)) {
                		board.select((int) x, (int) y);
                	}
               	//clicking on a space
                } else {
                	if (board.selected != null) {
                		if (board.canSelect((int) x, (int) y)) {
                			board.select((int) x, (int) y);
                		}
                	}
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	board.endTurn();
            }
            StdDrawPlus.show(10);
        }
        board.selected = null;
        board.drawBoard(N);
        StdDrawPlus.show(1);
        System.out.println(board.winner());
	}



	//J-Unit Tests
	public static class BoardTester {
		Board board;
		Piece pawn;
		Piece shield;
		Piece bomb;

		@Before
		public void setUp() {
			board = new Board(true);
			pawn = new Piece(true, board, 0, 2, "pawn");
			shield = new Piece(false, board, 5, 5, "shield");
			bomb = new Piece(true, board, 7, 3, "bomb");
			board.place(pawn, 0, 2);
			board.place(shield, 5, 5);
			board.place(bomb, 7, 3);
		}

		@Test
		public void test_pieceAt() {
			Piece doe = board.pieceAt(0,2);
			Piece re = board.pieceAt(5,5);
			Piece me = board.pieceAt(7,3);
	
			assertEquals(doe, pawn);
			assertEquals(doe.isBomb(), false);
			assertEquals(doe.isShield(), false);
			assertEquals(doe.isFire(), true);
			
			assertEquals(re, shield);
			assertEquals(re.isShield(), true);
			assertEquals(re.isFire(), false);
			
			assertEquals(me, bomb);
			assertEquals(me.isBomb(), true);

			assertEquals(board.pieceAt(2,2), null);
			assertEquals(board.pieceAt(42,0), null);
		}

		@Test
		public void test_place() {
			Piece fa = new Piece(true, board, 6, 6, "shield");
			board.place(fa, 6, 10);
			assertEquals(board.pieceAt(6,6), null);
			assertEquals(board.pieceAt(6,10), null);
			board.place(fa, 6, 6);
			assertEquals(board.pieceAt(6,6), fa);
			board.place(fa, 1, 7);
			assertEquals(board.pieceAt(1,7), fa);
			board.place(fa, 2, 2);
			assertEquals(board.pieceAt(2,2), fa);
		}

		@Test
		public void test_remove() {
			Piece fa = board.pieceAt(0,2);
			assertEquals(fa.isKing(), false);
			board.remove(0, 2);
			assertEquals(board.pieceAt(0,2), null);
			board.remove(99,99);
		}

		@Test
		public void test_canSelect() {
			assertEquals(board.canSelect(3,3), false);
			assertEquals(board.canSelect(0,2), true);
			assertEquals(board.canSelect(0,2), true);
			assertEquals(board.canSelect(0,2), true);
			assertEquals(board.canSelect(42,42), false);
			
			board.select(7,3);
			assertEquals(board.canSelect(6,4), true);
			assertEquals(board.canSelect(7,4), false);
			assertEquals(board.canSelect(6,2), false);
		}

		@Test
		public void test_select() {
			//movement
			board.select(0, 2);
			assertEquals(board.selected_x, 0);
			assertEquals(board.selected_y, 2);
			assertEquals(board.selected, pawn);
			board.select(1, 3);
			assertEquals(board.selected_x, 1);
			assertEquals(board.selected_y, 3);
			assertEquals(board.selected, pawn);
			assertEquals(board.pieceAt(1, 3), pawn);
			assertEquals(board.pieceAt(0, 2), null);
			board.select(99, 99);
			assertEquals(board.selected, pawn);

			//capture
			board.canMove = true;
			Piece captureMe = new Piece(false, board, 2, 1, "pawn");
			board.select(1, 3);
			assertEquals(board.selected_x, 1);
			assertEquals(board.selected_y, 3);
			assertEquals(board.selected, pawn);
			board.select(0, 4);
			assertEquals(board.selected_x, 0);
			assertEquals(board.selected_y, 4);
			assertEquals(board.selected, pawn);
			assertEquals(board.pieceAt(2, 1), null);
			assertEquals(board.pieceAt(1, 3), null);
		}

		@Test
		public void test_bombCapture() {
			Board board3 = new Board(true);
	        Piece bomb = new Piece(true, board3, 0, 0, "bomb");
	        Piece shield = new Piece(false, board3, 1, 1, "shield");
	        Piece pawn = new Piece(true, board3, 1, 3, "pawn");
	        board3.place(bomb, 0, 0);
	        board3.place(shield, 1, 1);
	        board3.place(pawn, 1, 3);
	   
			board3.select(0, 0);
			assertEquals(board3.selected_x, 0);
			assertEquals(board3.selected_y, 0);
			board3.select(2, 2);
			assertEquals(board3.selected_x, 2);
			assertEquals(board3.selected_y, 2);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					assertEquals(board3.pieceAt(i, j), null);
				}
			}
		}

		@Test
		public void test_canEndTurn() {
			assertEquals(board.canEndTurn(), false);
			board.select(0,2);
			board.select(1,3);
			assertEquals(board.canEndTurn(), true);

			Piece pig = new Piece(false, board, 2, 4, "pawn");
			board.place(pig, 2, 4);
			board.canMove = true;
			board.select(1,3);
			board.select(3,5);
			assertEquals(board.canEndTurn(), true);
			assertEquals(board.canMove, true);
		}

		@Test
		public void test_endTurn() {
			board.select(0,2);
			board.select(1,3);
			assertEquals(board.canEndTurn(), true);
			assertEquals(board.canMove, false);
			assertEquals(board.player, 0);
			assertEquals(board.selected, board.pieceAt(1,3));

			board.endTurn();
			
			assertEquals(board.canEndTurn(), false);
			assertEquals(board.canMove, true);
			assertEquals(board.player, 1);
			assertEquals(board.selected, null);			
		}

		@Test
		public void test_winner() {
			Board board2 = new Board(true);
			assertEquals(board2.winner(), "No one");

			Piece firebomb = new Piece(true, board2, 0, 2, "bomb");
			Piece watershield = new Piece(false, board2, 1, 3, "shield");
			Piece firepawn = new Piece(true, board2, 3, 3, "pawn");
			board2.place(firebomb, 0, 2);
			board2.place(watershield, 1, 3);
			board2.place(firepawn, 3, 3);
			assertEquals(board2.winner(), null);

			board2.select(0,2);
			board2.select(2,4);
			assertEquals(board2.winner(), "No one");
			board2.endTurn();

			firepawn = new Piece(true, board2, 2, 2, "pawn");
			Piece waterpawn = new Piece(false, board2, 3, 3, "pawn");
			board2.place(firepawn, 2, 2);
			board2.place(waterpawn, 3, 3);
			board2.select(3,3);
			board2.select(1,1);
			assertEquals(board2.winner(), "Water");
			board2.endTurn();

			firepawn = new Piece(true, board2, 0, 0, "pawn");
			board2.place(firepawn, 0, 0);
			board2.place(waterpawn, 1, 1);
			board2.select(0,0);
			board2.select(2,2);
			assertEquals(board2.winner(), "Fire");

			//ag test 4
			Board board4 = new Board(true);
			assertEquals(board4.winner(), "No one");
			firepawn = new Piece(true, board4, 3, 5, "pawn");
			waterpawn = new Piece(false, board4, 2, 6, "pawn");
			board4.place(firepawn, 3, 5);
			board4.place(waterpawn, 2, 6);
			assertEquals(board4.winner(), null);
			board4.select(3,5);
			board4.select(1,7);
			board4.endTurn();
			assertEquals(board4.winner(), "Fire");

			board4.remove(1,7);
			firepawn = new Piece(true, board4, 2, 2, "pawn");
			waterpawn = new Piece(false, board4, 3, 3, "pawn");
			board4.place(firepawn, 2, 2);
			board4.place(waterpawn, 3, 3);
			assertEquals(board4.winner(), null);
			board4.select(3,3);
			board4.select(1,1);
			assertEquals(board4.winner(), "Water");
			board4.endTurn();
			assertEquals(board4.winner(), "Water");
		}

		public static void runTests() {
	        jh61b.junit.textui.runClasses(BoardTester.class);
	    }
	}

}