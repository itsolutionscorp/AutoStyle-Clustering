public class Board{
	
	private boolean piece_locs[][] = new boolean[8][8];
	private Piece pieces[][] = new Piece[8][8];

	private String d;
	private String e;
	private String f;
	private boolean turn_player;
	private boolean has_moved = false;
	private boolean bomb_blew = false;

	private int xmidpoint;
	private int ymidpoint;
	private Piece temp;

	private int water_count; 
	private int fire_count;

	private int xBomb;
	private int yBomb; 

	private boolean selected = false;
	private int[] selected_spot = new int[2];

	public static void main(String args[]){
		Board b = new Board(false);
		b.drawPieces();

		while (b.winner() == null){
			if (StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX(); 
				int y = (int) StdDrawPlus.mouseY();
				StdDrawPlus.show(1);

				if (b.canSelect(x, y)){
					b.select(x, y);	
				}
			}

			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){
				b.endTurn();
			}
		}

		System.out.println(b.winner());
	}

	public Board(boolean shouldBeEmpty){
		if (!shouldBeEmpty){
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if ((i + j) % 2 == 0){
						if (j ==  0){
							this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
							this.piece_locs[i][j] = true;
						} else if (j == 1){
							this.pieces[i][j] = new Piece(true, this, i, j, "shield");
							this.piece_locs[i][j] = true;
						} else if (j == 2){
							this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
							this.piece_locs[i][j] = true;
						} else if (j == 5){
							this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
							this.piece_locs[i][j] = true;
						} else if (j == 6){
							this.pieces[i][j] = new Piece(false, this, i, j, "shield");
							this.piece_locs[i][j] = true;
						} else if (j == 7){
							this.pieces[i][j] = new Piece(false, this, i, j, "pawn");
							this.piece_locs[i][j] = true;
						}
					} else {
						this.piece_locs[i][j] = false;
					}
				}
			}
		}

		this.turn_player = true; 
		drawBoard(8);
	}

	private void drawPieces(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
				if (this.piece_locs[i][j]){
					this.place(this.pieces[i][j], i, j);
				}
			}
        }
    }

    private void drawBoard(int N) {
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

	public Piece pieceAt(int x, int y){
		if (this.piece_locs[x][y]) {
			return this.pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y){
		if (selected == true){ /*determines whether the player is changing the a selected piece or making a move*/
			if (this.piece_locs[x][y] && !this.has_moved){ /*test is the piece change is valid*/
				if(this.pieces[x][y].isFire() ^ this.pieces[this.selected_spot[0]][this.selected_spot[1]].isFire()){
					return false;
				}

				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				StdDrawPlus.filledSquare(this.selected_spot[0] + .5, this.selected_spot[1] + .5, .5);
				this.place(this.pieces[this.selected_spot[0]][this.selected_spot[1]], this.selected_spot[0], this.selected_spot[1]);
				selected = false;
				return true;
			} else {/* tests is the selected move is valid*/
				return this.validMove(this.selected_spot[0], this.selected_spot[1], x, y);
			}
		} else if (this.piece_locs[x][y]){
			/*this is for selecting the first piece*/
			return !(this.pieces[x][y].isFire() ^ this.turn_player);
		} else {
			return false;
		}

	}

	private boolean validMove(int x1, int y1, int x2, int y2){
		this.xmidpoint = (x1 + x2) / 2;
		this.ymidpoint = (y1 + y2) / 2;

		if ((x2 + y2) % 2 != 0 || (Math.abs(x2 - x1) > 2) || (Math.abs(y2 - y1) > 2)){ /*all valid squares have his property*/
			return false;
		} else if (this.has_moved && (Math.abs(x2 - x1) != 2)) {
			return false; 
		} else if (this.pieces[x1][y1].isKing()){
			return validKingMove(x1, y1, x2, y2);
		} else if (((y2 < y1) && this.pieces[x1][y1].isFire() ) || ((y2 > y1) && !this.pieces[x1][y1].isFire())) { /*backwards moves invalid for nonking*/
			return false;
		} else if ((Math.abs(x2 - x1) == 2)) { /*tests if skip is available*/			
			if (this.piece_locs[this.xmidpoint][this.ymidpoint]){ 
				return (this.pieces[x1][y1].isFire() ^ this.pieces[this.xmidpoint][this.ymidpoint].isFire()); /*tests is skipped piece is valid ornot*/
			}
			return false; 
		} else if ((Math.abs(x2 - x1) == 2) || (Math.abs(y2 - y1)) == 2){
			return false;
		} else {
			return true;
		}
	}

	private boolean validKingMove(int x1, int y1, int x2, int y2){
		if ((Math.abs(x2 - x1) == 2)) { /*tests if skip is available*/			
			if (this.piece_locs[this.xmidpoint][this.ymidpoint]){ 
				return (this.pieces[x1][y1].isFire() ^ this.pieces[this.xmidpoint][this.ymidpoint].isFire()); /*tests is skipped piece is valid ornot*/
			}
			return false; 
		} else if ((Math.abs(x2 - x1) == 2) || (Math.abs(y2 - y1)) == 2){
			return false;
		} else {
			return true;
		}
	}

	public void select(int x, int y){
		if (selected){ /*performs the move*/
			this.move(x, y);
		} else { /*selects a new piece*/
			this.selected_spot[0] = x;
			this.selected_spot[1] = y;
			this.selected = true;
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);

			this.place(pieces[x][y], x, y);
		}
	}

	private void move(int x, int y){
		this.pieces[this.selected_spot[0]][this.selected_spot[1]].move(x, y);

		/*bomb explosion*/

		this.pieces[x][y] = this.pieces[this.selected_spot[0]][this.selected_spot[1]];
		this.pieces[this.selected_spot[0]][this.selected_spot[1]] = null;
		this.piece_locs[this.selected_spot[0]][this.selected_spot[1]] = false;
		this.piece_locs[x][y] = true;

		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.filledSquare(this.selected_spot[0] + .5, this.selected_spot[1] + .5, .5);

		this.place(pieces[x][y], x, y);
		this.selected_spot[0] = x;
		this.selected_spot[1] = y;

		this.has_moved = true; 

		if (this.pieces[x][y].isBomb() && this.pieces[x][y].hasCaptured()){
			this.bombTake(x, y);
		}
	}

	private void bombTake(int x, int y){
		for (int n = -1; n < 2; n = n + 2){
			for (int m = -1; m < 2; m = m +2){
				if (n < 0){
					this.xBomb = Math.max(0, x + n);
				} else {
					this.xBomb = Math.min(7, x + n);
				}

				if (m < 0){
					this.yBomb = Math.max(0, y + m);
				} else {
					this.yBomb = Math.min(7, y + m);
				}

				if (this.piece_locs[xBomb][yBomb]){
					if (!this.pieces[xBomb][yBomb].isShield()){
						this.remove(xBomb, yBomb);
					}
				}
			}
		}

		this.bomb_blew = true;
		this.remove(x, y);
	}

	public void place(Piece p, int x, int y){
		this.pieces[x][y] = p;
		this.piece_locs[x][y] = true;
		if (p.isFire()){
				d = "fire";
			} else {
				d = "water";
			}

			if (p.isBomb()){
				f = "bomb-";
			} else if(p.isShield()){
				f = "shield-";
			} else {
				f = "pawn-";
			}

			if (p.isKing()){
				e = "-crowned";
			} else {
				e = "";
			}
		StdDrawPlus.picture(x + .5, y + .5, "img/" + f + d + e + ".png", 1, 1);
    }

	public Piece remove(int x, int y){
		if ((x > 7) | (x < 0) | (y > 7) | (y < 0)){
			return null;
		}

		this.piece_locs[x][y] = false;
		temp = this.pieces[x][y];
		this.pieces[x][y] = null;

		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);

		return temp; 
	}

	public boolean canEndTurn(){
		return (this.has_moved || this.bomb_blew);
	}

	public void endTurn(){
		this.has_moved = false; 
		this.selected = false;

		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.filledSquare(this.selected_spot[0] + .5, this.selected_spot[1] + .5, .5);
		if (!this.bomb_blew){
			this.place(this.pieces[this.selected_spot[0]][this.selected_spot[1]], this.selected_spot[0], this.selected_spot[1]);
		}

		this.bomb_blew = false;
		this.turn_player = !this.turn_player;
	}

	public String winner(){
		this.fire_count = 0;
		this.water_count = 0;

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (this.piece_locs[i][j]){
					if (this.pieces[i][j].isFire()){
						this.fire_count++;
					} else {
						this.water_count++;
					}
				}
			}
		}

		if ((this.fire_count == 0) && (this.water_count == 0)){
			return "No One";
		} else if (this.fire_count == 0){
			return "Water";
		} else if (this.water_count ==0){
			return "Fire";
		} else {
			return null;
		}

	}
}