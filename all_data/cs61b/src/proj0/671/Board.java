public class Board{
	private static int N = 8;
    private int selectedX, selectedY;
    private int pieceSelectedX, pieceSelectedY;
    private boolean movePiece = false;
	private Piece[][] pieces = new Piece[N][N];
    private Piece pieceSelected;
    private boolean hasMoved = false;
    private boolean isFireTurn = true;
	
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board gameBoard = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(gameBoard.winner() == null) {
            gameBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                gameBoard.selectedX = (int)StdDrawPlus.mouseX();
                gameBoard.selectedY = (int)StdDrawPlus.mouseY();
                
                if (gameBoard.selectedX>=0 && gameBoard.selectedX<N && gameBoard.selectedY>=0 && gameBoard.selectedY<N){
                    if(gameBoard.canSelect(gameBoard.selectedX, gameBoard.selectedY)){
                        gameBoard.select(gameBoard.selectedX, gameBoard.selectedY);
                    }    
                }
                
            }
            if (StdDrawPlus.isSpacePressed()){
                if (gameBoard.canEndTurn())
                    gameBoard.endTurn();
            }

            StdDrawPlus.show(20);
        }
        System.out.println(gameBoard.winner());
    }
	
	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty != true){
			//create fire pieces
			for(int i = 0; i <3; i++){
				for (int j = 0; j<8; j++){
					if((i+j)%2 == 0){
						if(i == 0)
							pieces[j][i] = new Piece(true, this, j, i, "pawn");
						else if (i == 1)
							pieces[j][i] = new Piece(true, this, j, i, "shield");
						else
							pieces[j][i] = new Piece(true, this, j, i, "bomb");
					}
				}
			}
			//create water pieces
			for(int i = 5; i <8; i++){
				for (int j = 0; j<8; j++){
					if ((i+j) % 2 ==0){
						if(i == 7)
							pieces[j][i] = new Piece(false, this, j, i, "pawn");
						else if (i == 6)
							pieces[j][i] = new Piece(false, this, j, i, "shield");
						else
							pieces[j][i] = new Piece(false, this, j, i, "bomb");					
					}
				}
			}
		}	
	}

    //referred to jacky liang to implement better way to find which image file to use
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceSelected != null && pieces[i][j] != null && pieces[i][j].equals(pieceSelected))
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) 
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] instanceof Piece) {
                    String phrase = "img/";
                    if (pieces[i][j].isBomb())
                        phrase += "bomb";
                    else if (pieces[i][j].isShield())
                        phrase+= "shield";
                    else
                        phrase += "pawn";
                    if(pieces[i][j].isFire())
                        phrase += "-fire";
                    else
                        phrase += "-water";
                    if(pieces[i][j].isKing())
                        phrase += "-crowned";
                    phrase += ".png";
                    StdDrawPlus.picture(i + .5, j + .5, phrase, 1, 1);
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
    	if (isValidRange(x, y)){
    	   if (pieces[x][y] instanceof Piece)
    	       return pieces[x][y];
        }

    	return null;
    }

    public void place(Piece p, int x, int y){
    	if (p != null){
            if (isValidRange(x, y)){
                for (int i = 0; i < N; i ++){
                    for (int j = 0; j < N; j++){
                        if (p == pieces[i][j]){
                            remove(i, j);
                        }
                    }
                }
                if(pieces[x][y] != null)
                    remove(x, y);
                pieces[x][y] = p;
                pieceSelectedX = x;
                pieceSelectedY = y;
            }            
        }
    }

    public Piece remove(int x, int y){
    	if (!isValidRange(x, y)){
    		System.out.println("Index out of bounds.");
    		return null;
    	}
    	if (this.pieceAt(x, y) == null){
    		System.out.println("Position is currently empty.");
    		return null;
    	} else{
    		Piece removed = this.pieceAt(x, y);
    		pieces[x][y] = null;
    		return removed;
    	}
    }

    //selects the piece if you can
    public void select(int x, int y){
        Piece checkPiece = pieceAt(x, y);
        if(checkPiece != null){
            pieceSelected = checkPiece;
            pieceSelectedX = x;
            pieceSelectedY = y;            
        } else{
            if(pieceSelected != null)
                pieceSelected.move(x, y);
                hasMoved = true;
        }        
    }

    public boolean canSelect(int x, int y){
        if (isValidRange(x, y)){
            Piece temp = pieceAt(x, y);
            if(temp == null){

                if(pieceSelected != null){              
                    if(hasMoved == false && validMove(pieceSelectedX, pieceSelectedY, x, y))
                        return true;
                    if(pieceSelected.hasCaptured() && validMove(pieceSelectedX, pieceSelectedY, x, y))
                        return true;
                }
            } else{
                if((temp.isFire() == isFireTurn) && (hasMoved == false)){
                    return true;
                }
            }   
        }
        
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        if(pieceSelected.isKing())
            return validKingMove(xi, yi, xf, yf);
        if(isFireTurn){
            //checks case of multiply jump
            if(pieceSelected.hasCaptured()){
                if ((xf - xi == 2) && (yf - yi == 2)){
                    if((pieces[xi+1][yi+1] instanceof Piece) && (pieces[xi+1][yi+1].isFire() == false)){
                        return true;
                    }
                }
                if ((xf - xi == -2) && (yf - yi == 2)){
                    if((pieces[xi-1][yi+1] instanceof Piece) && (pieces[xi-1][yi+1].isFire() == false)){
                        return true;
                    }                
                }
                return false;
            }
            //checks case of first move
            if ((xf - xi == 2) && (yf - yi == 2)){
                if((pieces[xi+1][yi+1] instanceof Piece) && (pieces[xi+1][yi+1].isFire() == false)){
                    return true;
                }
            }
            if ((xf - xi == -2) && (yf - yi == 2)){
                if((pieces[xi-1][yi+1] instanceof Piece) && (pieces[xi-1][yi+1].isFire() == false)){
                    return true;
                }                
            }
            if ((xf - xi == 1) && (yf - yi == 1))
                return true;
            if ((xf - xi == -1) && (yf - yi == 1))
                return true;
        } else{
            //checks cases for multiple jump
            if(pieceSelected.hasCaptured()){
                if ((xf - xi == 2) && (yf - yi == -2)){
                    if((pieces[xi+1][yi-1] instanceof Piece) && (pieces[xi+1][yi-1].isFire() == true)){
                        return true;
                    }
                }
                if ((xf - xi == -2) && (yf - yi == -2)){
                    if((pieces[xi-1][yi-1] instanceof Piece) && (pieces[xi-1][yi-1].isFire() == true)){
                        return true;
                    }                
                }
                return false;
            }
            //checks cases for first move
            if ((xf - xi == 2) && (yf - yi == -2)){
                if((pieces[xi+1][yi-1] instanceof Piece) && (pieces[xi+1][yi-1].isFire() == true)){
                    return true;
                }
            }
            if ((xf - xi == -2) && (yf - yi == -2)){
                if((pieces[xi-1][yi-1] instanceof Piece) && (pieces[xi-1][yi-1].isFire() == true)){
                    return true;
                }                
            }
            if ((xf - xi == 1) && (yf - yi == -1))
                return true;
            if ((xf - xi == -1) && (yf - yi == -1))
                return true;
        }
        
        return false;
    }

    private boolean validKingMove(int xi, int yi, int xf, int yf){
        if(pieceSelected.hasCaptured()){
                if ((xf - xi == 2) && (yf - yi == 2)){
                    if((pieces[xi+1][yi+1] instanceof Piece) && (pieces[xi+1][yi+1].isFire() != pieceSelected.isFire())){
                        return true;
                    }
                }
                if ((xf - xi == -2) && (yf - yi == 2)){
                    if((pieces[xi-1][yi+1] instanceof Piece) && (pieces[xi-1][yi+1].isFire() != pieceSelected.isFire())){
                        return true;
                    }                
                }
                if ((xf - xi == 2) && (yf - yi == -2)){
                    if((pieces[xi+1][yi-1] instanceof Piece) && (pieces[xi+1][yi-1].isFire() != pieceSelected.isFire())){
                        return true;
                    }                
                }
                if ((xf - xi == -2) && (yf - yi == -2)){
                    if((pieces[xi-1][yi-1] instanceof Piece) && (pieces[xi-1][yi-1].isFire() != pieceSelected.isFire())){
                        return true;
                    }                
                }
                return false;
            }
            //checks case of first move
            if ((xf - xi == 2) && (yf - yi == 2)){
                if((pieces[xi+1][yi+1] instanceof Piece) && (pieces[xi+1][yi+1].isFire() != pieceSelected.isFire())){
                    return true;
                }
            }
            if ((xf - xi == -2) && (yf - yi == 2)){
                if((pieces[xi-1][yi+1] instanceof Piece) && (pieces[xi-1][yi+1].isFire() != pieceSelected.isFire())){
                    return true;
                }                
            }
            if ((xf - xi == 2) && (yf - yi == -2)){
                if((pieces[xi+1][yi-1] instanceof Piece) && (pieces[xi+1][yi-1].isFire() != pieceSelected.isFire())){
                    return true;
                }                
            }
            if ((xf - xi == -2) && (yf - yi == -2)){
                if((pieces[xi-1][yi-1] instanceof Piece) && (pieces[xi-1][yi-1].isFire() != pieceSelected.isFire())){
                    return true;
                }                
            }
            if ((xf - xi == 1) && (yf - yi == 1))
                return true;
            if ((xf - xi == -1) && (yf - yi == 1))
                return true;
            if ((xf - xi == 1) && (yf - yi == -1))
                return true;
            if ((xf - xi == -1) && (yf - yi == -1))
                return true;
        return false;
    }


    public boolean canEndTurn(){
        if (hasMoved == true){
            return true;
        }
        return false;
    }

    public void endTurn(){
        if(isFireTurn)
            isFireTurn = false;
        else
            isFireTurn = true;
        if(pieceSelected != null)
            pieceSelected.doneCapturing();
        pieceSelected = null;
        hasMoved = false;
        movePiece = false;
    }

    public String winner(){
        int fireTeam = 0;
        int waterTeam = 0;
        for (int i = 0; i < N; i ++){
            for (int j = 0; j < N; j++){
                if (pieces[i][j] != null){
                    if (pieces[i][j].isFire())
                        fireTeam+=1;
                    else
                        waterTeam +=1;
                }
            }
        }
        if(fireTeam > 0 && waterTeam == 0)
            return "Fire";
        else if (fireTeam == 0 && waterTeam>0)
            return "Water";
        else if (fireTeam == 0 && waterTeam == 0)
            return "No one";
        else
            return null;
    }

    private boolean isValidRange(int x, int y){
        if(x>= 0 && x<N && y>=0 && y<N){
            return true;
        }
        return false;
    }



}

