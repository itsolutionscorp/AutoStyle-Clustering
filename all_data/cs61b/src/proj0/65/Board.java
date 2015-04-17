import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Board {

	private Piece[][] pieces;
	private Piece selected;
	private boolean running, hasMoved, mouseClick, mouseRefreshed, gameEnd;
	private int currentX, currentY, selectX, selectY, player, timer;

	private static Board currentBoard;
	private static int FPS = 40;
	private static Clip loop;

	private static final URL MUSIC = Board.class.getResource("audio/music.wav");
	private static final URL PIECEUP = Board.class
			.getResource("audio/pieceUp.wav");
	private static final URL PIECEDOWN = Board.class
			.getResource("audio/pieceDown.wav");
	private static final URL WIN = Board.class.getResource("audio/win.wav");
	private static final int FIRE = 0, WATER = 1;
	private static final Color BACKGROUND = new Color(0.2F, 0.25F, 0.2F);
	private static final Color FOREGROUND = new Color(0.2F, 0.7F, 0.3F,
			FPS <= 5 ? 0.75F : 5F / FPS);
	private static final Color SILHOUETTE = new Color(0.2F, 0.5F, 0.25F,
			FPS <= 10 ? 0.75F : 10F / FPS);
	private static final Color HIGHLIGHT = new Color(0.8F, 0.7F, 0.8F,
			FPS <= 10 ? 0.75F : 10F / FPS);
	private static final Color SELECT = new Color(1F, 1F, 0.8F,
			FPS <= 20 ? 0.75F : 20F / FPS);
	private static final Color SMOVES = new Color(0.1F, 1F, 0.1F,
			FPS <= 20 ? 0.75F : 20F / FPS);
	private static final Color MOVES = new Color(1F, 0.8F, 0.7F,
			FPS <= 20 ? 0.75F : 20F / FPS);
	private static final Object[][] PIECES = new Object[][] {
			new Object[] { 0, 0, "pawn" }, new Object[] { 1, 1, "shield" },
			new Object[] { 0, 2, "bomb" } };

	/**
	 * Starts a GUI supported version of the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		restart();
	}

	/**
	 * The constructor for Board. If <b>shouldBeEmpty</b> is true, initializes
	 * an empty Board. Otherwise, initializes a Board with the default
	 * configuration.
	 * 
	 * @param shouldBeEmpty Whether or not to initialize an empty board.
	 */
	public Board(boolean shouldBeEmpty) {
		this.running = false;
		this.mouseClick = false;
		this.mouseRefreshed = true;
		this.gameEnd = false;
		this.pieces = new Piece[8][8];
		this.currentX = this.currentY = this.selectX = this.selectY = -1;
		this.player = FIRE;
		this.timer = FPS;

		if (!shouldBeEmpty)
			initializePieces(this);
	}

	/**
	 * Gets the piece at position (x, y) on the board, or null if there is no
	 * piece.<br>
	 * If (x, y) are out of bounds, returns null.
	 * 
	 * @param x Position on board.
	 * @param y Position on board.
	 * @return Piece at x, y position.
	 */
	public Piece pieceAt(int x, int y) {
		if (isValidPosition(x, y))
			return pieces[x][y];
		return null;
	}

	/**
	 * Returns true if the square at (x, y) can be selected.
	 * <p>
	 * A square with a piece may be selected if it is the corresponding player's
	 * turn and one of the following is true:
	 * </p>
	 * <ul>
	 * <li>The player has not selected a piece yet.</li>
	 * <li>The player has selected a piece, but did not move it.</li>
	 * </ul>
	 * <p>
	 * An empty square may be selected if one of the following is true:
	 * </p>
	 * <ul>
	 * <li>During this turn, the player has selected a Piece which hasn't moved
	 * yet and is selecting an empty spot which is a valid move for the
	 * previously selected Piece.</li>
	 * <li>During this turn, the player has selected a Piece, captured, and has
	 * selected another valid capture destination.</li>
	 * </ul>
	 * </p> <br>
	 * 
	 * @param x Position on board.
	 * @param y Position on board.
	 * @return Whether x, y can be selected.
	 */
	public boolean canSelect(int x, int y) {
		if (!isValidPosition(x, y))
			return false;
		Piece piece = pieces[x][y];
		if (piece != null)
			return piece.side() == player && (selected == null || !hasMoved);
		else if (selected != null) {
			int[][] moves = getPossibleMoves(selectX, selectY);
			for (int[] pos : moves)
				if (pos[0] == x && pos[1] == y)
					return true;
		}
		return false;
	}

	/**
	 * Selects the square at (x, y). This method assumes canSelect (x,y) returns
	 * true.
	 * 
	 * @param x Position on board.
	 * @param y Position on board.
	 */
	public void select(int x, int y) {
		Piece piece = pieces[x][y];
		if (piece != null && (selected == null || !hasMoved))
			selected = piece;
		else {
			piece = remove(selectX, selectY);
			place(piece, x, y);
			hasMoved = true;
		}
		selectX = x;
		selectY = y;
		selected = piece;
	}

	/**
	 * Places p at (x, y). If (x, y) is out of bounds or if p is null, does
	 * nothing. If p already exists in the current Board, first removes it from
	 * its initial position. If another piece already exists at (x, y), p will
	 * replace that piece.
	 * 
	 * @param p Piece to place at x, y.
	 * @param x Position on board.
	 * @param y Position on board.
	 */
	public void place(Piece p, int x, int y) {
		if (isValidPosition(x, y)) {
			if (pieces[x][y] != null)
				remove(x, y);
			pieces[x][y] = p;
			p.move(x, y);
		}
	}

	/**
	 * Executes a remove. Returns the piece that was removed. If the input (x,
	 * y) is out of bounds, returns null and prints an appropriate message. If
	 * there is no piece at (x, y), returns null and prints an appropriate
	 * message.
	 * 
	 * @param x Position on board.
	 * @param y Position on board.
	 * @return Piece removed from x, y.
	 */
	public Piece remove(int x, int y) {
		if (!isValidPosition(x, y)) {
			System.out.println("Out of bounds!");
			return null;
		}
		Piece piece = pieces[x][y];
		pieces[x][y] = null;
		if (piece == null)
			System.out.printf("No object at %d, %d%n", x, y);
		else if (piece == selected)
			selected = null;
		return piece;
	}

	/**
	 * Returns whether or not the the current player can end their turn.
	 * 
	 * @return Whether current turn can end.
	 */
	public boolean canEndTurn() {
		return hasMoved;
	}

	/**
	 * Called at the end of each turn.
	 */
	public void endTurn() {
		hasMoved = false;
		selected.doneCapturing();
		deselect();
		player = 1 - player;
		String winner = winner();
		if (winner != null) {
			System.out.printf("%s is the winner!%n", winner);
			gameEnd = true;
		}
	}

	/**
	 * Returns the winner of the game. "Fire", "Water", "No one" (tie / no
	 * pieces on the board), or null if the game is not yet over.
	 * 
	 * @return The winner.
	 */
	public String winner() {
		boolean firePieces = false, waterPieces = false;
		int fireMoves = 0, waterMoves = 0;
		for (int xTile = 0; xTile < 8; xTile += 2)
			for (int yTile = 0; yTile < 8; yTile += 2)
				for (int x = xTile, y = yTile; x < xTile + 2; x++, y++)
					if (pieces[x][y] != null)
						if (pieces[x][y].isFire()) {
							firePieces = true;
							fireMoves += getPossibleMoves(x, y).length;
						} else {
							waterPieces = true;
							waterMoves += getPossibleMoves(x, y).length;
						}
		if (!firePieces && !waterPieces)
			return "No one";
		else if (!firePieces || fireMoves == 0 && player == FIRE)
			return "Water";
		else if (!waterPieces || waterMoves == 0 && player == WATER)
			return "Fire";
		return null;
	}

	/**
	 * Starts a new game.
	 */
	private static void restart() {
		if (currentBoard != null)
			currentBoard.running = false;
		if (loop != null)
			loop.close();
		currentBoard = new Board(false);
		initializeBoard();
		loopSound(MUSIC);
		run(currentBoard);
	}

	/**
	 * Sets up the board and draws the background.
	 */
	private static void initializeBoard() {
		int size = 8;
		StdDrawPlus.setScale(0, size);
		StdDrawPlus.picture(4, 4, "img/background.png", 8.8, 8.8);
		StdDrawPlus.setPenColor(BACKGROUND);
		StdDrawPlus.filledSquare(4, 4, 4);
	}

	/**
	 * Adds all the pieces to the board.
	 * 
	 * @param b Board to initialize pieces in.
	 */
	private static void initializePieces(Board b) {
		for (int column = 0; column < 4; column++)
			for (boolean isFire : new boolean[] { true, false })
				for (Object[] template : PIECES) {
					int x = (int) template[0] + 2 * column;
					int y = (int) template[1];
					if (!isFire) {
						x = 7 - x;
						y = 7 - y;
					}
					String type = (String) template[2];
					Piece piece = new Piece(isFire, b, x, y, type);
					b.pieces[x][y] = piece;
				}
	}

	/**
	 * Runs the main drawing loop.
	 * 
	 * @param b Board to use for GUI.
	 */
	private static void run(Board b) {
		b.running = true;
		while (b.running) {
			b.currentX = (int) (StdDrawPlus.mouseX());
			b.currentY = (int) (StdDrawPlus.mouseY());
			if (b.mouseClick)
				b.mouseClick = false;
			if (StdDrawPlus.mousePressed()) {
				if (b.mouseRefreshed) {
					b.mouseClick = true;
					b.mouseRefreshed = false;
				}
			} else
				b.mouseRefreshed = true;
			if (b.mouseClick)
				if (b.canSelect(b.currentX, b.currentY)) {
					if (b.pieces[b.currentX][b.currentY] != null)
						playSound(PIECEUP);
					else
						playSound(PIECEDOWN);
					b.select(b.currentX, b.currentY);
				} else if (!b.hasMoved)
					b.deselect();
			if (b.canEndTurn() && StdDrawPlus.isSpacePressed())
				b.endTurn();
			if (b.timer-- < 0 && StdDrawPlus.isNPressed())
				restart();
			if (b.gameEnd) {
				switch (b.winner()) {
					case "Fire":
						StdDrawPlus.picture(4, 4, "img/win-fire.png", 8.8, 8.8);
						break;
					case "Water":
						StdDrawPlus
								.picture(4, 4, "img/win-water.png", 8.8, 8.8);
						break;
					case "No one":
						StdDrawPlus
								.picture(4, 4, "img/win-noone.png", 8.8, 8.8);
						break;
					default:
						System.out.println("This should never happen!");
				}
				playSound(WIN);
				b.running = false;
			}
			paint(b);
			StdDrawPlus.show(1000 / FPS);
		}
		while (true)
			if (StdDrawPlus.isNPressed())
				restart();
	}

	/**
	 * Draws the current board.
	 * 
	 * @param b Board to use for GUI.
	 */
	private static void paint(Board b) {
		StdDrawPlus.setPenColor(FOREGROUND);
		for (int xTile = 0; xTile < 8; xTile += 2)
			for (int yTile = 0; yTile < 8; yTile += 2)
				for (int x = xTile, y = yTile; x < xTile + 2; x++, y++)
					drawSquare(b, x, y);
		if (isValidPosition(b.currentX, b.currentY)
				&& b.pieces[b.currentX][b.currentY] != null)
			drawPossibleMoves(b, b.currentX, b.currentY, MOVES);
		if (b.selected != null)
			drawPossibleMoves(b, b.selectX, b.selectY, SMOVES);
	}

	/**
	 * Draws the correct components of square at x, y
	 * 
	 * @param b Board to use for GUI.
	 * @param x Position on board.
	 * @param y Position on board.
	 */
	private static void drawSquare(Board b, int x, int y) {
		if (x != b.currentX || y != b.currentY)
			if (b.selectX != -1) {
				StdDrawPlus.setPenColor(SILHOUETTE);
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
			} else
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
		if (x == b.selectX && y == b.selectY && b.selectX != -1) {
			StdDrawPlus.setPenColor(SELECT);
			StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
			StdDrawPlus.setPenColor(FOREGROUND);
		}
		if (x == b.currentX && y == b.currentY) {
			StdDrawPlus.setPenColor(HIGHLIGHT);
			StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
			StdDrawPlus.setPenColor(FOREGROUND);
		}
		if (b.pieces[x][y] != null)
			if (b.pieces[x][y] == b.selected)
				StdDrawPlus.picture(x + 0.5, y + 0.5,
						Board.getImage(b.pieces[x][y]), 1, 1,
						15 * Math.sin(4 * Math.PI * b.timer / FPS));
			else if (x == b.currentX && y == b.currentY
					&& b.pieces[x][y].side() == b.player && !b.hasMoved)
				StdDrawPlus.picture(x + 0.5, y + 0.5,
						Board.getImage(b.pieces[x][y]), 1, 1,
						10 * Math.random() - 5);
			else
				StdDrawPlus.picture(x + 0.5, y + 0.5,
						Board.getImage(b.pieces[x][y]), 1, 1);
	}

	/**
	 * Draws all possible moves for piece at <b>pieceX, pieceY</b>
	 * 
	 * @param b Board to use for GUI.
	 * @param pieceX Position on board.
	 * @param pieceY Position on board.
	 * @param color Color to draw in
	 */
	private static void drawPossibleMoves(Board b, int pieceX, int pieceY,
			Color color) {
		StdDrawPlus.setPenColor(color);
		for (int[] pos : b.getPossibleMoves(pieceX, pieceY))
			StdDrawPlus.filledSquare(pos[0] + 0.5, pos[1] + 0.5, 0.5);
		StdDrawPlus.setPenColor(FOREGROUND);
	}

	/**
	 * Gets all possible moves for piece at <b>pieceX, pieceY</b>
	 * 
	 * @param pieceX Position on board.
	 * @param pieceY Position on board.
	 * @return List of coordinate pairs in the form [x, y] of each possible
	 *         position to move to from <b>pieceX, pieceY</b>
	 */
	private int[][] getPossibleMoves(int pieceX, int pieceY) {
		Piece piece = pieces[pieceX][pieceY];
		if (piece == null)
			return new int[0][2];
		int side = piece.side();
		ArrayList<int[]> moves = new ArrayList<int[]>();
		int direction = piece.isFire() ? 1 : -1;
		for (int range : new int[] { -1, 1 }) {
			int x = pieceX + range;
			if (x >= 0 && x < 8) {
				int y = pieceY + direction;
				if (y >= 0 && y < 8)
					if (pieces[x][y] == null) {
						if (!hasMoved)
							moves.add(new int[] { x, y });
					} else if ((!hasMoved || piece.hasCaptured())
							&& pieces[x][y].side() != side
							&& isValidPosition(x + range, y + direction)
							&& pieces[x + range][y + direction] == null)
						moves.add(new int[] { x + range, y + direction });
				if (piece.isKing()) {
					y = pieceY - direction;
					if (y >= 0 && y < 8)
						if (pieces[x][y] == null) {
							if (!hasMoved)
								moves.add(new int[] { x, y });
						} else if ((!hasMoved || piece.hasCaptured())
								&& pieces[x][y].side() != side
								&& isValidPosition(x + range, y - direction)
								&& pieces[x + range][y - direction] == null)
							moves.add(new int[] { x + range, y - direction });
				}
			}
		}
		return moves.toArray(new int[0][2]);
	}

	/**
	 * Tests whether or not the position can be accessed
	 * 
	 * @param x Position on board.
	 * @param y Position on board.
	 * @return Whether position is valid.
	 */
	private static boolean isValidPosition(int x, int y) {
		return (x + y) % 2 == 0 && x >= 0 && y >= 0 && x < 8 && y < 8;
	}

	/**
	 * Assembles the image path of a piece.
	 * 
	 * @param piece Piece to assemble image path of.
	 * @return A string <i>path</i> containing location of image file.
	 */
	private static String getImage(Piece piece) {
		String path = "img/";
		path += piece.isBomb() ? "bomb" : piece.isShield() ? "shield" : "pawn";
		path += piece.isFire() ? "-fire" : "-water";
		path += piece.isKing() ? "-crowned" : "";
		return path + ".png";
	}

	/**
	 * Sets currently selected tile to nothing.
	 */
	private void deselect() {
		selected = null;
		selectX = selectY = -1;
	}

	/**
	 * Plays a sound.
	 * 
	 * @param url Sound file to play.
	 */
	private static void playSound(URL url) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			clip.open(audio);
			clip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Loops a sound.
	 * 
	 * @param url Sound file to loop.
	 */
	private static void loopSound(URL url) {
		try {
			loop = AudioSystem.getClip();
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			loop.open(audio);
			loop.start();
			loop.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
