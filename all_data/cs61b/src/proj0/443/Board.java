public class Board {

	private static int N = 8;
	private Piece[][] pieces;  
	private boolean fire = true;
	private boolean water = false;
	private boolean fireTurn = true;
	private boolean moved = false;
	private boolean capturable = false;
	private boolean empty;
	private Piece current = null;
	private int tempX = -1;
	private int tempY = -1;
	private int fireScore;
	private int waterScore;
	private int tele = -1;


    public static void main(String[] args) {
        Board board = new Board(false);
        if (board.empty) {
        	board.draw(0);
        }
        if (!board.empty) {
        	board.draw(1);
        }
		
        while (true) {
	        if (StdDrawPlus.mousePressed()) {
	        	double tempA = StdDrawPlus.mouseX();
	        	double tempB = StdDrawPlus.mouseY();
	        	if (board.canSelect((int)tempA, (int)tempB)) {
	        		board.select((int)tempA, (int)tempB);
	        		board.draw(2);
	        		board.draw(3);
					board.draw(2);
	        	}
	        }
			
	        StdDrawPlus.show(10);
	        if (StdDrawPlus.isSpacePressed()) {
				board.fireScore = 0;
				board.waterScore = 0;
				for (int i = 0; i < N; i++) {
			        for (int j = 0; j < N; j++) {
			            if (board.pieceAt(i, j) != null) {
				            if (board.pieceAt(i, j) .isFire())
				            	board.fireScore += 1;
				            if (!board.pieceAt(i, j).isFire())
				            	board.waterScore += 1;
						}	            
		            }
			    }
			    if (board.fireScore == 0 || board.waterScore == 0)
			    	board.winner(); 

	        	if (board.canEndTurn()) 
	        		board.endTurn();
        	}
        }
    }


	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		pieces = new Piece[N][N];
		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) {			
	                	if (j == 0) {pieces[i][j] = new Piece(fire, this, i, j, "pawn");}
	                	if (j == 1) {pieces[i][j] = new Piece(fire, this, i, j, "shield");}
	                	if (j == 2) {pieces[i][j] = new Piece(fire, this, i, j, "bomb");}
	                	if (j == 5) {pieces[i][j] = new Piece(water, this, i, j, "bomb");}
	                	if (j == 6) {pieces[i][j] = new Piece(water, this, i, j, "shield");}
	                	if (j == 7) {pieces[i][j] = new Piece(water, this, i, j, "pawn");}
					}
				}
			}
		}
    }


	private void draw(int s) {
		if (s == 0) {
	        StdDrawPlus.setXscale(0, N);
	        StdDrawPlus.setYscale(0, N);
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
	                else 				  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
			}
		}

		if (s == 1) {
	        StdDrawPlus.setXscale(0, N);
	        StdDrawPlus.setYscale(0, N);
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
	                else 				  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
			}

			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) {			
	                	if (j == 0) {StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);}
	                	if (j == 1) {StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);}
	                	if (j == 2) {StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);}
	                	if (j == 5) {StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);}
	                	if (j == 6) {StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);}
	                	if (j == 7) {StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);}
	                }
	            } 
	        }
		}

		if (s == 2) {
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	if (pieceAt(i, j) == null) {
	            		if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
		                else 				  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
		                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            	}

	            	if (pieceAt(i, j) != null) {
	            		StdDrawPlus.picture(i + .5, j + .5, pathFinder(pieceAt(i, j)), 1, 1);
	            	}
	            }
	        }              
		}

		if (s == 3) {
	        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(tempX + .5, tempY + .5, .5);
			StdDrawPlus.picture(tempX + .5, tempY + .5, pathFinder(current), 1, 1);			
		}
	}	
	
	
//	private String map() {
//		String m = "";
//		for (int i = 0; i < N; i++) {
//	            for (int j = 0; j < N; j++) {
//					Piece curr = pieces[j][7 - i];
//					if (curr == null)
//						m += " - ";
//					else if (curr.isFire()) {
//						if (curr.isBomb())
//							m += " * ";
//						else if (curr.isShield())
//							m += " o ";
//						else if (!curr.isBomb() && !curr.isShield())
//							m += " i "; 
//					}
//					
//					else {
//						if (curr.isBomb())
//							m += " # ";
//						else if (curr.isShield())
//							m += " 0 ";
//						else if (!curr.isBomb() && !curr.isShield())
//							m += " I ";	
//					}
//				}
//				m += "\n";
//		}		
//		System.out.print(m);
//		return m;
//	}


	public Piece pieceAt(int x, int y) {
		if (x >= N || y >= N) 
			return null;
		else {
			return pieces[x][y];
		}
	}


	public boolean canSelect(int x, int y) {
		//System.out.println("Current Piece is " + current + ", and the moved is " + moved);
		if (current == null) {
			if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == fireTurn) 
				return true;
			else {return false;}
		}

		else {
			if (!moved) {
				if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == fireTurn)
					return true;
				if (pieceAt(x, y) == null) 
					return validMove(tempX, tempY, x, y);
			}

			if (moved) {
				//System.out.println("temp axis is " + tempX + ", " + tempY + " and I'm going to " + x + ", " + y);
				if (pieceAt(x, y) == null && tele == 2)
					if (validMove(tempX, tempY, x, y))
						return capturable;
			}
		}

		return false;
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {

		if (xf < 0 || yf < 0 || xf > 7 || yf > 7) {return false;}
		if (pieceAt(xf, yf) != null) {return false;}
		if (pieceAt(xi, yi) == null) {return false;}
		if (pieceAt(xi, yi).isFire()) {
			if (moved) {
				if (xf == xi + 2 && yf == yi + 2) {
					if ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+2, yi+2) == null) && !pieceAt(xi+1, yi+1).isFire()) {
						capturable = true;
						return true;
					}
				}

				else if (xf == xi - 2 && yf == yi + 2) {
					if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-2, yi+2) == null) && !pieceAt(xi-1, yi+1).isFire()) {
						capturable = true;
						return true;
					}
				}		

				
				//capturable = false;
				//return false;
				
			}		
			if (capturable == false) {
				if (xf == xi + 1 && yf == yi + 1) 
					return ((pieceAt(xi+1, yi+1) == null));
				if (xf == xi - 1 && yf == yi + 1)
					return ((pieceAt(xi-1, yi+1) == null));
				if (xf == xi + 2 && yf == yi + 2)
					return ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+2, yi+2) == null) && !pieceAt(xi+1, yi+1).isFire());
				if (xf == xi - 2 && yf == yi + 2)
					return ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-2, yi+2) == null) && !pieceAt(xi-1, yi+1).isFire());	
			}
			if (pieceAt(xi, yi).isKing()) {
				if (moved) {
					if (xf == xi + 2 && yf == yi + 2)
						if ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+2, yi+2) == null) && !pieceAt(xi+1, yi+1).isFire()) {
							capturable = true;
							return true;
						}
					if (xf == xi - 2 && yf == yi + 2)
						if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-2, yi+2) == null) && !pieceAt(xi-1, yi+1).isFire()) {
							capturable = true;
							return true;
						}
					if (xf == xi + 2 && yf == yi - 2)
						if ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+2, yi-2) == null) && !pieceAt(xi+1, yi-1).isFire()) {
							capturable = true;
							return true;
						}					
					if (xf == xi - 2 && yf == yi - 2)
						if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-2, yi-2) == null) && !pieceAt(xi-1, yi-1).isFire()) {
							capturable = true;
							return true;
						}
				}
				if (capturable == false) {
					if (xf == xi + 1 && yf == yi + 1) 
						return ((pieceAt(xi+1, yi+1) == null));
					if (xf == xi - 1 && yf == yi + 1)
						return ((pieceAt(xi-1, yi+1) == null));
					if (xf == xi + 2 && yf == yi + 2)
						return ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+2, yi+2) == null) && !pieceAt(xi+1, yi+1).isFire());
					if (xf == xi - 2 && yf == yi + 2)
						return ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-2, yi+2) == null) && !pieceAt(xi-1, yi+1).isFire());	
					if (xf == xi + 1 && yf == yi - 1) 
						return (pieceAt(xi+1, yi-1) == null);
					if (xf == xi - 1 && yf == yi - 1)
						return (pieceAt(xi-1, yi-1) == null);
					if (xf == xi + 2 && yf == yi - 2)
						return ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+2, yi-2) == null) && !pieceAt(xi+1, yi-1).isFire());
					if (xf == xi - 2 && yf == yi - 2)
						return ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-2, yi-2) == null) && !pieceAt(xi-1, yi-1).isFire());			
				}
			}
		}		

		if (!pieceAt(xi, yi).isFire()) {
			if (moved) {
				if (xf == xi + 2 && yf == yi - 2)
					if ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+2, yi-2) == null) && pieceAt(xi+1, yi-1).isFire()) {
						capturable = true;
						return true;
				}

				if (xf == xi - 2 && yf == yi - 2)
					if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-2, yi-2) == null) && pieceAt(xi-1, yi-1).isFire()) {
						capturable = true;
						return true;
				}

				//capturable = false;
				//return false;
				
			}		
			if (capturable == false) {
				if (xf == xi + 1 && yf == yi - 1) 
					return (pieceAt(xi+1, yi-1) == null);
				if (xf == xi - 1 && yf == yi - 1)
					return (pieceAt(xi-1, yi-1) == null);
				if (xf == xi + 2 && yf == yi - 2)
					return ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+2, yi-2) == null) && pieceAt(xi+1, yi-1).isFire());
				if (xf == xi - 2 && yf == yi - 2)
					return ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-2, yi-2) == null) && pieceAt(xi-1, yi-1).isFire());	
			}
			
			if (pieceAt(xi, yi).isKing()) {
				if (moved) {
					if (xf == xi + 2 && yf == yi + 2)
						if ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+2, yi+2) == null) && pieceAt(xi+1, yi+1).isFire()) {
							capturable = true;
							return true;
						}
					if (xf == xi - 2 && yf == yi + 2)
						if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-2, yi+2) == null) && pieceAt(xi-1, yi+1).isFire()) {
							capturable = true;
							return true;
						}
					if (xf == xi + 2 && yf == yi - 2)
						if ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+2, yi-2) == null) && pieceAt(xi+1, yi-1).isFire()) {
							capturable = true;
							return true;
						}					
					if (xf == xi - 2 && yf == yi - 2)
						if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-2, yi-2) == null) && pieceAt(xi-1, yi-1).isFire()) {
							capturable = true;
							return true;
						}
				}

			if (capturable == false) {
					if (xf == xi + 1 && yf == yi + 1) 
						return ((pieceAt(xi+1, yi+1) == null));
					if (xf == xi - 1 && yf == yi + 1)
						return ((pieceAt(xi-1, yi+1) == null));
					if (xf == xi + 2 && yf == yi + 2)
						return ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+2, yi+2) == null) && pieceAt(xi+1, yi+1).isFire());
					if (xf == xi - 2 && yf == yi + 2)
						return ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-2, yi+2) == null) && pieceAt(xi-1, yi+1).isFire());	
					if (xf == xi + 1 && yf == yi - 1) 
						return (pieceAt(xi+1, yi-1) == null);
					if (xf == xi - 1 && yf == yi - 1)
						return (pieceAt(xi-1, yi-1) == null);
					if (xf == xi + 2 && yf == yi - 2)
						return ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+2, yi-2) == null) && pieceAt(xi+1, yi-1).isFire());
					if (xf == xi - 2 && yf == yi - 2)
						return ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-2, yi-2) == null) && pieceAt(xi-1, yi-1).isFire());
			}
			}	
		}

		return false;
	}


	private String pathFinder(Piece p) {
		if (p.isFire()) {
			if (p.isBomb()) {
				if (!p.isKing())
					return "img/bomb-fire.png";
				if (p.isKing())
					return "img/bomb-fire-crowned.png";
			}

			if (p.isShield()) {
				if (!p.isKing())
					return "img/shield-fire.png";
				if (p.isKing())
					return "img/shield-fire-crowned.png";			
			}

			else {
				if (!p.isKing())
					return "img/pawn-fire.png";
				if (p.isKing())
					return "img/pawn-fire-crowned.png";					
			}
		}

		if (!p.isFire()) {
			if (p.isBomb()) {
				if (!p.isKing())
					return "img/bomb-water.png";
				if (p.isKing())
					return "img/bomb-water-crowned.png";
			}

			if (p.isShield()) {
				if (!p.isKing())
					return "img/shield-water.png";
				if (p.isKing())
					return "img/shield-water-crowned.png";			
			}

			else {
				if (!p.isKing())
					return "img/pawn-water.png";
				if (p.isKing())
					return "img/pawn-water-crowned.png";					
			}			
		}

		return null;
	}


	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			tempX = x;
			tempY = y;
			current = pieceAt(x, y);
			//System.out.println("Select Piece at " + x + ", " + y);
			//map();
		}

		if (pieceAt(x, y) == null && current != null) {			
	
			//map();
			//System.out.println("Move piece to " + x + ", " + y);
			tele = Math.abs(y - tempY);
			current.move(x, y);
			//map();
			tempX = x;
			tempY = y;
			moved = true;

		}

			if (validMove(x, y, x+2, y+2) || validMove(x, y, x-2, y+2) || validMove(x, y, x-2, y-2) || validMove(x, y, x+2, y-2)) {
				//System.out.println("Multicapture detected");
				if (capturable && current.hasCaptured()) {
					tempX = x;
					tempY = y;
					current = pieceAt(x, y);
					capturable = false;
				}
			}				


	}


	
	public void place(Piece p, int x, int y) {
		if (p == null) {return;}
		if (x >= N || y >= N) {return;}

		for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	           	if (pieceAt(i, j) == p) {
	            	pieces[i][j] = null;
	            	break;	           
	            }
	        }
	    }
		//System.out.println("Place Piece at " + x + ", " + y);
	    pieces[x][y] = p;
		//map();
	}


	public Piece remove(int x, int y) {
		if (x >= N || y >= N) {
			//System.out.println("out of bounds");
			return null;
		}

		if (pieceAt(x, y) == null) {
			//System.out.println("no piece found");
			return null;
		}

		Piece temp = pieceAt(x, y);
		pieces[x][y] = null;
		return temp;
	}


	public boolean canEndTurn() {
		return moved;
	}


	public void endTurn() {
		fireTurn = !fireTurn;
		if (current.hasCaptured())
			current.doneCapturing();
		current = null;
		moved = false;
		capturable = false;
		fireScore = 0;
		waterScore = 0;
		tempX = -1;
		tempY = -1;
		tele = -1;

	}


	public String winner() {
		fireScore = 0;
		waterScore = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieceAt(i, j) != null) {
	            	if (pieceAt(i, j) .isFire())
	            		fireScore += 1;
	            	if (!pieceAt(i, j).isFire())
	            		waterScore += 1;
				}	            
            }
	    }

	    if ((fireScore == 0) && (waterScore ==0))
			return "No one";
		else if (fireScore == 0)
			return "Water";
		else if (waterScore == 0)
			return "Fire";

	    return null;
	}

}