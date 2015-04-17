public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private int turn = 0;
	private Piece selected = null;
	private int selectedx = -1;
	private int selectedy = -1;
	private boolean moved = false;
	private int empty = 0;
	private int outofbound = -1;
	private int numberoffire = 0;
	private int numberofwater = 0;
	private boolean bombboolean = false;
    public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		int x = -2;
		int y = -2;
		while(true) {
            if (StdDrawPlus.mousePressed()) {
                x = (int)StdDrawPlus.mouseX();
                y = (int)StdDrawPlus.mouseY();
				if(b.canSelect(x, y))	{
					b.select(x, y);
				}
            } 
			if(StdDrawPlus.isSpacePressed())	{
				if(b.canEndTurn())	{
					b.endTurn();
				}
			}
			b.drawBoard();
            StdDrawPlus.show(15);
			if(b.winner() != null)	{
				System.out.println(b.winner());
				break;
			}	
        }
    }
	public Board(boolean shouldBeEmpty)		{

		if(!shouldBeEmpty)	{
			boardInit();
		}
		//drawBoard();
	}
	
	private void boardInit()	{
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
				if	(j == 0 && i % 2 == 0)	{
					place(new Piece(true, this, i, j, "pawn"), i , j);
				}
				else if (j == 1 && i % 2 == 1) 	{
					place(new Piece(true, this, i, j, "shield"), i , j);
				}
				else if (j == 2 && i % 2 == 0)	{
					place(new Piece(true, this, i, j, "bomb"), i , j);
				}
				else if (j == 5 && i % 2 == 1)	{
					place(new Piece(false, this, i, j, "bomb"), i , j);
				}
				else if (j == 6 && i % 2 == 0)	{
					place(new Piece(false, this, i, j, "shield"), i , j);
				}
				else if (j == 7 && i % 2 == 1)	{
					place(new Piece(false, this, i, j, "pawn"), i , j);
				}
            }
        }
	}
	private void drawBoard()	{
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if(i == selectedx && j == selectedy) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + .5, j + 0.5, 0.5);
				}
				if(pieces[i][j] != null)	{
					draw(pieces[i][j], i, j);
				}
			}
		}
		StdDrawPlus.show();
	}
	private void draw(Piece P, int i, int j)	{
		String pic;
		if(P.isShield())	{
			pic = "shield-";
		}
		else if(P.isBomb())	{
			pic = "bomb-";
		}
		else {
			pic = "pawn-";
		}
		if(P.isFire())	{
			pic = pic + "fire";
		}
		else{
			pic = pic + "water";
		}
		if(P.isKing())	{
			pic = pic + "-crowned";
		}
		StdDrawPlus.picture(i + .5, j + .5, "img/" + pic +".png",1 , 1);
	}

		
	public Piece pieceAt(int x, int y)	{
		if (x < 0 || x >= 8 || y < 0 || y >= 8)	{
			return null;
		}
		return pieces[x][y];
	}
	private int isEmpty(int x, int y)	{
		if (x < 0 || x >= 8 || y < 0 || y >= 8)	{
			return -1;
		}
		if (( x % 2 == 1 && y % 2 == 0) || (x % 2 == 0 && y % 2 == 1))	{
			return -1;
		}
		if (pieces[x][y] == null)	{
			return empty;
		}
		return 1;
	}
	public boolean canSelect(int x, int y)	{
		if(isEmpty(x, y) == outofbound || bombboolean) return false;
		if(isEmpty(x, y) != empty && (pieceAt(x, y).side() == (turn % 2)))	{
			if ((selected == null) ||((selected != null) && !moved))	{
				return true;
			}
		}
		else if(isEmpty(x, y) == empty )	{
			if((selected != null) && validMove(selectedx, selectedy, x, y))	{
					if (!moved || (selected.hasCaptured() && moved && 
					Math.abs(selectedx - x)== 2 && Math.abs(selectedy - y) == 2))	{
						return true;
					}
			}
		}
		return false;
	}
	private boolean sameSide(Piece a, Piece b)	{
		return a.side() == b.side();
	}
	private boolean validMove(int xi, int yi, int x, int y)	{
		int xmidpt = (int)((x + xi)/2);
		int ymidpt = (int)((y + yi)/2);
		int yinc;
		if(isEmpty(x, y) != empty)	{
			return false;
		}
		if(pieceAt(xi, yi).isFire())	 yinc = 1;
		else	yinc = -1;
		
		if((xi + 1 == x || xi - 1 == x) && (yi + yinc == y))	{
			return true;
		}
		if(Math.abs(xi- x) == 2	&& (yi + yinc*2 == y))	{
			if(isEmpty(xmidpt, ymidpt) != empty) 	{
				if(!sameSide(pieceAt(xi, yi), pieceAt(xmidpt, ymidpt)))	{
					return true;
				}
			}
		}
		if(pieceAt(xi, yi).isKing())	{
			if(Math.abs(xi - x) == 1 && Math.abs(yi - y) == 1)	{
				return true;
			}
			if(Math.abs(xi - x) == 2 && (yi - yinc*2 == y)) {
				if(isEmpty(xmidpt, ymidpt) != empty) 		{
					if(!sameSide(pieceAt(xi, yi), pieceAt(xmidpt, ymidpt)))	{
						return true;
					}
				}
			}
		}
	return false;	
	}
	
	public void select(int x, int y)	{
		if(isEmpty(x, y) == outofbound)	{
			return;
		}
		else if(isEmpty(x,y) == 0)	{
			place(pieceAt(selectedx, selectedy), x ,y);
		}
		else 	{
			selected = pieceAt(x, y);
			selectedx = x;
			selectedy = y;
			
		}
	}
	private boolean existOnBoard(Piece P)	{
		for (int i = 0; i < 8 ; i++) 	{
			for(int j = 0; j < 8; j++)	{
				if(P == pieces[i][j])	{
					return true;
				}
			}
		}
		return false;
	}
		
	public void place(Piece P, int x, int y)	{
		if(isEmpty(x, y) == outofbound)	{
			return;
		}
		else {
			boolean check = false;
			for (int i = 0; i < 8 ; i++) {
				for(int j = 0; j < 8; j++)	{
					if(P == pieces[i][j])		{
						check = true;
						pieces[i][j] = null;
					}
				}	
			}
			
			//pieces[selectedx][selectedy] = null;
			pieces[x][y] = P;
			if(!check)	{
				if(P.isFire())	{
					numberoffire++;
				}
				else{
					numberofwater++;
				}
			}
			else{
				P.move(x, y);
				moved = true;
				selected = P;
				selectedx = x;
				selectedy = y;
				if(P.isBomb() && P.hasCaptured())	{
					bombboolean = true;
					 selected = null;
					 selectedx = -1;
					 selectedy = -1;
				 }
			}
		}
	}
	public Piece remove(int x, int y)	{
		if(isEmpty(x, y) == outofbound)	{
			System.out.println("Not a valid position");
			return null;
		}
		else if(isEmpty(x, y) == empty)	{
			System.out.println("Nothing to remove");
			return null;
		}
		else	{
			Piece copy = pieces[x][y];
			if(copy.isFire())	{
				numberoffire--;
			}
			else{
				numberofwater--;
			}
			pieces[x][y] = null;
			return copy;
		}
	}
	public boolean canEndTurn()	{
		return moved;
	}
	public void endTurn()	{
		if(isEmpty(selectedx, selectedy) == 1)	{
			pieceAt(selectedx, selectedy).doneCapturing();
		}
		turn ++;
		selected = null;
		selectedx = -1;
		selectedy = -1;
		moved = false;
		bombboolean = false;
			
		
	}
	public String winner()	{
		if (numberoffire + numberofwater == 0)	{
			return "No one";
		}
		else if(numberoffire == 0)	{
			return "Water";
		}
		else if(numberofwater == 0)	{
			return "Fire";
		}
		return null;
	}
}
