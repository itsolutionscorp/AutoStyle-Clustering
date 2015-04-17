
public class Board {

	private int boardSize;

	private Piece[][] pieces;

	private boolean whoseTurn; //True-fire, False-water

	private boolean canEndTurn;

	private int selectedX, selectedY;

	public Board(boolean shouldBeEmpty){
		boardSize = 8;
		pieces = new Piece[boardSize][boardSize];
		if(!shouldBeEmpty){
			for(int i = 0; i < 8; i += 2){
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
				pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");
				pieces[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
				pieces[i][6] = new Piece(false, this, i, 6, "shield");
				pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
			}
		}
		whoseTurn = true;
		canEndTurn = false;
		selectedX = -1;
		selectedY = -1;
	}

	public Piece pieceAt(int x, int y){
		if(x >= boardSize || y >= boardSize || x < 0 || y < 0) return null;
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x, y);
		Piece selected = pieceAt(selectedX, selectedY);
		if(p != null){
			if(p.isFire() == whoseTurn){
				if(!canEndTurn){
					return true;
				}
			}
		}
		else if(selected != null){
			if(!canEndTurn){
				if(Math.abs(selectedX - x) == 1 && Math.abs(selectedY - y) == 1){
					if(selected.isKing()){
						return true;
					}
					else if(whoseTurn){
						if(y - selectedY == 1){
							return true;
						}
					}
					else{
						if(y - selectedY == -1){
							return true;
						}
					}
				}
				else if(Math.abs(selectedX - x) == 2 && Math.abs(selectedY - y) == 2){
					if(catpureTurn(x, y)){
						return true;
					}
				}
			}
			else if(selected.hasCaptured()){
				if(catpureTurn(x, y)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean catpureTurn(int x, int y){
		Piece selected = pieceAt(selectedX, selectedY);
		if(selected.isKing()){
			if(isCapturing(x, y)) return true;
		}
		else if(whoseTurn){
			if(y - selectedY == 2){
				if(isCapturing(x, y)) return true;
			}
		}
		else{
			if(y - selectedY == -2){
				if(isCapturing(x, y)) return true;
			}
		}
		return false;
	}

	private boolean isCapturing(int x, int y){
		Piece p = pieceAt((selectedX + x) / 2, (selectedY + y) / 2);
		if(p != null && p.isFire() != whoseTurn){
			return true;
		}
		return false;
	}

	public void select(int x, int y){
		Piece selected = pieceAt(selectedX, selectedY);
		if(selected != null && pieceAt(x, y) == null){	
			selected.move(x, y);
    		if(selected.isBomb() && selected.hasCaptured()){
    			selectedX = -1;
    			selectedY = -1;
    			selected = null;
    		}
    		else{
    			selectedX = x;
	    		selectedY = y;
	    		selected = pieceAt(x, y);
    		}
    		canEndTurn = true;
    	}
    	else{
			selectedX = x;
	    	selectedY = y;
	    	selected = pieceAt(x, y);
		}
	}

	public void place(Piece p, int x, int y){
		if(p == null || x >= boardSize || y >= boardSize || x < 0 || y < 0){
			return;
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y){
		if(x >= boardSize || y >= boardSize || x < 0 || y < 0){
			System.out.println("ERROR: OUT OF BOUNDS");
			return null;
		}
		Piece p = pieceAt(x, y);
		if(p == null){
			System.out.println("ERROR: NO PIECE AT LOCATION (" + x + ", " + y + ")");
			return null;
		}
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn(){
		return canEndTurn;
	}

	public void endTurn(){
		Piece selected = pieceAt(selectedX, selectedY);
		whoseTurn = !whoseTurn;
		canEndTurn = false;
		if(selected != null) selected.doneCapturing();
		selectedX = -1;
		selectedY = -1;
	}

	public String winner(){
		String possibleWinner = "No one";
		for(int i = 0; i < boardSize; i++){
			for(int j = i % 2; j < boardSize; j++){
				Piece p = pieceAt(i, j);
				if(p != null){
					if(possibleWinner.equals("No one")){
						if(p.isFire()) possibleWinner = "Fire";
						else possibleWinner = "Water";
					}
					else{
						if((p.isFire() && possibleWinner == "Water") || (!p.isFire() && possibleWinner == "Fire")){
							return null;
						}
					}
				}
			}
		}
		return possibleWinner;
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == selectedX && j == selectedY) StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = pieceAt(i, j);
                if (p != null) {
                	String type = "pawn";
                	if(p.isBomb()) type = "bomb";
                	else if(p.isShield()) type = "shield";
                	String side = "-fire";
                	if(!p.isFire()) side = "-water";
                	String crown = "";
                	if(p.isKing()) crown = "-crowned";
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + type + side + crown + ".png", 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(b.winner() == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
            	int x = (int) StdDrawPlus.mouseX();
            	int y = (int) StdDrawPlus.mouseY();
            	if(b.canSelect(x, y)){
            		b.select(x, y);
            	}
            }
            if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
            	b.endTurn();
            }
            StdDrawPlus.show(10);
        }
    }

}