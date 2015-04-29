public class Board {
	
	private Piece pieces[][], selected;
	private boolean made_move;
	private int fire, water, player, x, y;

	public static void main(String args[]) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board game = new Board(false);
		game.drawBoard();
		while (true) {
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (game.canSelect((int)x, (int)y)) {
					game.select((int)x, (int)y);
				// 	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				// 	StdDrawPlus.filledSquare((int)x + .5, (int)y + .5, .5);
				}
				if (game.made_move)
					game.drawBoard();
			}
			if (StdDrawPlus.isSpacePressed())	
    	       	if (game.canEndTurn())
	           		game.endTurn();
			StdDrawPlus.show(100);
			if (game.winner() != null) {
				System.out.println(game.winner());
				game.drawBoard();
				return;
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
        Piece temp = null;
        selected = null;
        made_move = false;
        fire = 0;
        water = 0;
        player = 0;
        int i, j;
		if (!shouldBeEmpty) {
			for (i = 0; i < 8; i += 1)
				for (j= 0; j < 8; j += 1)
					if ((i + j) % 2 == 0) {
						if (j == 0)
							temp = new Piece(true, this, i, j, "pawn");
						else if (j == 1)
							temp = new Piece(true, this, i, j, "shield");
						else if (j == 2)
							temp = new Piece(true, this, i, j, "bomb");
						else if (j == 5)
							temp = new Piece(false, this, i, j, "bomb");
						else if (j == 6)
							temp = new Piece(false, this, i, j, "shield");
						else if (j == 7)
							temp = new Piece(false, this, i, j, "pawn");
						if (temp != null)
							place(temp, i, j);
						temp = null;
					}
					else
						pieces[i][j] = null;
		}
		else {
			for (i = 0; i < 8; i += 1)
				for (j= 0; j < 8; j += 1)
					pieces[i][j] = null;
		}
	}

	private void drawBoard() {
		int i, j;
		for (i = 0; i < 8; i += 1) {
			for (j = 0; j < 8; j += 1) {
				if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
			}
		}
		String img;
		for (j = 0; j < 8; j += 1)
			for (i = 0; i < 8; i += 1) {
				if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				img = "img/";
				Piece temp = pieceAt(i, j);
				if (temp != null) {
					if (temp.isBomb())
						img = img.concat("bomb");
					else if (temp.isShield())
						img = img.concat("shield");
					else
						img = img.concat("pawn");
					if (temp.side() == 0)
						img = img.concat("-fire");
					else
						img = img.concat("-water");
					if (temp.isKing())
						img = img.concat("-crowned");
					img = img.concat(".png");
					StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
				}
			}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0)
			return null;
		else 
			return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0 || (x + y) % 2 == 1)
			return false;
		if (made_move && selected != null && !selected.hasCaptured())
			return false;
		Piece temp = pieceAt(x, y);
		if (temp != null) {
			if (temp.side() == player && !made_move)
				return true;
		}
		else if (selected != null) {
			int k = (int)Math.pow(-1, selected.side());
			if (selected.isKing()) {
				if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1 && !selected.hasCaptured())
					return true;
				else if (Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 2 && pieceAt((this.x + x)/2, (this.y + y)/2) != null)
					return pieceAt((this.x + x)/2, (this.y + y)/2).side() != player;
			}
			else if (Math.abs(this.x - x) == 1 && y == this.y + k && !selected.hasCaptured())
				return true;
			else if (Math.abs(this.x - x) == 2 && y == this.y + 2*k && pieceAt((this.x + x)/2, (this.y + y)/2) != null)
				return pieceAt((this.x + x)/2, (this.y + y)/2).side() != player;
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) 
			selected = pieceAt(x, y);
		else {
			selected.move(x, y);
			made_move = true;
		}
		this.x = x;
		this.y = y;
	}

	public void place(Piece p, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			pieces[x][y] = p;
			if (p.isFire())
				fire += 1;
			else
				water += 1;
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			System.out.println("Index out of bounds");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("Empty spot selected");
			return null;
		}
		Piece temp = pieceAt(x, y);
		pieces[x][y] = null;
		if (temp.isFire())
			fire -= 1;
		else
			water -= 1;
		return temp;
	}

	public boolean canEndTurn() {
		return made_move || (selected != null && selected.hasCaptured());
	}

	public void endTurn() {
		if (player == 0)
			player = 1;
		else
			player = 0;
		made_move = false;
		selected.doneCapturing();
		selected = null;
		x = 0;
		y = 1;
	}

	public String winner() {
		if (fire == 0 && water == 0)
			return "No one";
		else if (fire == 0)
			return "Water";
		else if (water == 0)
			return "Fire";
		else
			return null;
	}
}