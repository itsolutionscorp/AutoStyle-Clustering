public class Board {

    private Piece[][] pieces;
    private int N;
    private String team;
    private boolean playerMoved;
    private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;
    private int clickX;
    private int clickY;
    private int fireCounter;
    private int waterCounter;
    private String winner;
    

	private void drawBoard(int N) {
        this.N = N;
        
        //Creates the Red/Grey Grid
        for (int xcoord = 0; xcoord < N; xcoord++) {
            for (int ycoord = 0; ycoord < N; ycoord++) {
                if ((xcoord + ycoord) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(xcoord + .5, ycoord + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(this.selectedPiece != null) {
        		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(this.selectedPieceX + .5, this.selectedPieceY + .5, .5);
        }
            }
        }



        //Adds pieces from pieces array
        for (int piecex = 0; piecex < N; piecex++) {
        	for (int piecey = 0; piecey < N; piecey++) { 
        		//Current Piece
        		Piece curr = pieces[piecex][piecey];
        		if(curr != null) {
        			if (curr.isFire() == true) {
        				if(curr.isKing() == false){
        					
        					if(curr.isBomb() == false){
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/pawn-fire.png", 1, 1);
        					}
        					if(curr.isBomb()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/bomb-fire.png", 1, 1);
        					}
        					if(curr.isShield()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/shield-fire.png", 1, 1);
        					} 

        				} else {
        					
        					if(curr.isBomb() == false) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/pawn-fire-crowned.png", 1, 1);
        					}
        					if(curr.isBomb()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/bomb-fire-crowned.png", 1, 1);
        					}
        					if(curr.isShield()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/shield-fire-crowned.png", 1, 1);
        					}

        				}
        			} else {
        				if(curr.isKing() == false) {
        				    
        					if(curr.isBomb() == false){
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/pawn-water.png", 1, 1);
        					}
        				    if(curr.isBomb()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/bomb-water.png", 1, 1);
        					}
        					if(curr.isShield()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/shield-water.png", 1, 1);
        					}

        				} else {
        					
        					if(curr.isBomb() == false) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/pawn-water-crowned.png", 1, 1);
        					}
        					if(curr.isBomb()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/bomb-water-crowned.png", 1, 1);
        					}
        					if(curr.isShield()) {
        						StdDrawPlus.picture(piecex + .5, piecey + .5, "img/shield-water-crowned.png", 1, 1);
        					}

        				}

        			}

        		}
        	}

        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board playingBoard = new Board(false);
        
        playingBoard.team = "Fire";
        playingBoard.selectedPiece = null;
        playingBoard.playerMoved = false;
        // playingBoard.fireCounter = N / 2 * 3;
        // playingBoard.waterCounter = N / 2 * 3;
        playingBoard.drawBoard(N);
        playingBoard.clickX = -1;
        playingBoard.clickY = -1;
        
        boolean hasEndedTurn = false;

        while(playingBoard.winner() == null) {

	        while(hasEndedTurn == false){
	        	if ((StdDrawPlus.mousePressed() == true) && ( playingBoard.canSelect( (int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY())) == true) {
	        		playingBoard.clickX = (int) StdDrawPlus.mouseX();
	        		playingBoard.clickY = (int) StdDrawPlus.mouseY();
	        		playingBoard.select(playingBoard.clickX, playingBoard.clickY);
	        		playingBoard.drawBoard(N);
	        	}

	        	if((StdDrawPlus.isSpacePressed() == true) && (playingBoard.canEndTurn() == true)) {
	        		while(StdDrawPlus.isSpacePressed()){
	        		}
	        		System.out.println("ended turn");
		        		if (playingBoard.selectedPiece != null) {
		        		playingBoard.selectedPiece.doneCapturing();
		        		}
	        		playingBoard.endTurn();
	        		if(playingBoard.winner() != null) {
	        			hasEndedTurn = true;
	        		}
	        	}	
	    	}
	    }
	    System.exit(0);
    }  

    private boolean isInBoundary(int x, int y) {
    	if ((x < 0) || (x >= N)) {
    		return false;
    	}
    	if((y < 0) || (y >= N)) {
    		return false;
    	} else {
    		return true;
    	}
    }

    // Checks if the piece can make a valid capture assuming, 
    // according to it's target
    private boolean captureChecker(Piece target) {

		// If Water team capturing Fire
		if((target.isFire() == true) && (this.team == "Water")) {
			return true;
		}
		
		// If Fire team capturing Fire
		if((target.isFire() == true) && (this.team == "Fire")) {
			return false;
		}

		// If Fire team capturing Water
		if((target.isFire() == false) && (this.team == "Fire")) {
			return true;
		}

		// If Water team capturing Water
		if((target.isFire() == false) && (this.team == "Water")) {
			return false;
		} else {
			return false;
		}
    }

    private boolean isNextMoveValid(int xi, int yi, int xf, int yf) {    	
    	
    	// this.team = team;
    	Piece p = this.pieceAt(xi,yi);

    	// Next Move In the Boundary
    	if (this.isInBoundary(xf, yf)) {
    		
    		// If there is not a piece in the way
    		if (this.pieceAt(xf,yf) == null) {
    			// System.out.println(xi);
    			// System.out.println(yi);
    			// System.out.println(xf);
    			// System.out.println(yf);
	    		// The move is 1 away from the piece
	    		if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
	    			if(this.selectedPiece.hasCaptured() == false) {
			    		// It cannot move backwards
			    		if (p.isKing() == false) {
			    			
				    			// If it's team Fire
				    			if(p.isFire() == true) {

				    				if(this.playerMoved == false){
					    				// Fire can move in the Boundary
					    				if((yf == yi + 1) && ((xf == xi -1) || (xf == xi +1))) {
					    					return true;
					    				} else {
					    					return false;
					    				}
					    			} else {
					    				return false;
					    			}

				    			// If it's team Water
				    			} else {
				    				if(playerMoved == false) {
					    				if((yf == yi - 1) && ((xf == xi -1) || (xf == xi +1))) {
					    					return true;
					    				} else {
					    					return false;
					    				}
					    			} else {
					    				return false;
					    			}	
				    			}
			    		// It can move backwards	
			    		} else {
			    			return true;
			    		}
			    	} else {
			    		return false;
			    	}
		    	}
   				
   				// The move is an attempted capture
				if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) {
					if(playerMoved == false) {
						// Is a King OR a Fire Piece
						if((p.isKing() == true) || (p.isFire() == true)) {
				    		
				    		// Attempted Top Left capture
				    		if ((xf - xi == -2) && (yf - yi == 2)) {
				    			
				    			// There is a piece in the Top Left direction
				    			if(this.pieces[xi - 1][yi + 1] != null) {
				    				Piece target = this.pieces[xi-1][yi+1];
				    				return this.captureChecker(target);
				    			} else {
				    				return false;
				    			}
				    		} 
				    		// Attempted Top Right Capture
				    		if((xf - xi == 2) && (yf - yi == 2)) {
				    			// There is a piece in the Top Right Direction
				    			if(this.pieces[xi + 1][yi + 1] != null) {
				
				    				Piece target = this.pieces[xi + 1][yi + 1];
				    				return this.captureChecker(target);
				    			} else {;
				    				return false;
				    			}
				    		}
				    	} else {
				    	}

				    	// Is a King OR a Water Piece
				    	if((p.isKing() == true) || (p.isFire() == false)) {
				    		
				    		//Attempted Bottom Left Capture
				    		if((xf - xi == -2) && (yf - yi == -2)) {

				    			// There is a piece in the Bottom Left Direction
				    			if(this.pieces[xi - 1][yi - 1] != null) {
				    				Piece target = this.pieces[xi - 1][yi - 1];
				    				return this.captureChecker(target);
				    			} else {
				    				return false;
				    			}
				    		}

				    		//Attempted Bottom Right Capture
				    		if((xf - xi == 2) && (yf - yi == -2)) {

				    			// There is a piece in the Bottom Right Direction
				    			if(this.pieces[xi + 1][yi - 1] != null) {
				    				Piece target = this.pieces[xi + 1][yi - 1];
				    				return this.captureChecker(target);
				    			} else {
				    				return false;
				    			}
				    		}
				    	} else {
				    		return false;
				    	}
				    } else {
				    	if(this.selectedPiece.hasCaptured() == true) {
				    		
				    		//Attempted Bottom Left Capture
				    		if((xf - xi == -2) && (yf - yi == -2)) {

				    			// There is a piece in the Bottom Left Direction
				    			if(this.pieces[xi - 1][yi - 1] != null) {
				    				Piece target = this.pieces[xi - 1][yi - 1];
				    				return this.captureChecker(target);
				    			} else {
				    				return false;
				    			}
				    		}

				    		//Attempted Bottom Right Capture
				    		if((xf - xi == 2) && (yf - yi == -2)) {

				    			// There is a piece in the Bottom Right Direction
				    			if(this.pieces[xi + 1][yi - 1] != null) {
				    				Piece target = this.pieces[xi + 1][yi - 1];
				    				return this.captureChecker(target);
				    			} else {
				    				return false;
				    			}
				    		}

				    		// Attempted Top Left capture
				    		if ((xf - xi == -2) && (yf - yi == 2)) {
				    			
				    			// There is a piece in the Top Left direction
				    			if(this.pieces[xi - 1][yi + 1] != null) {
				    				Piece target = this.pieces[xi-1][yi+1];
				    				return this.captureChecker(target);
				    			} else {
				    				return false;
				    			}
				    		} 
				    		// Attempted Top Right Capture
				    		if((xf - xi == 2) && (yf - yi == 2)) {
				    			// There is a piece in the Top Right Direction
				    			if(this.pieces[xi + 1][yi + 1] != null) {
				
				    				Piece target = this.pieces[xi + 1][yi + 1];
				    				return this.captureChecker(target);
				    			} else {;
				    				return false;
				    			}
				    		}
				    	} else {
				    		return false;
				   		}
				    }
		    		// There isn't a peice to capture
		    	} else {
		    		return false;
		    		}
   			// There is a piece in the way 
			} else {
				return false;
			}
		// Not in the Boundary
    	} else {
    		return this.isInBoundary(xf,yf);
    		}
    	return false;
	}

	public Board(boolean shouldBeEmpty) { 

		this.team = "Fire";
        this.selectedPiece = null;
        this.playerMoved = false;
        this.fireCounter = 0;
        this.waterCounter = 0;
        this.clickX = -1;
        this.clickY = -1;
		this.N = 8;
		this.pieces = new Piece[N][N];
		if(shouldBeEmpty == false){
			 for (int xcoord = 0; xcoord < N; xcoord++) {
	            for (int yFire = 0; yFire < 3; yFire++) {
	                if ((xcoord + yFire) % 2 == 0) {
	                	if(yFire == 0) {
	                		pieces[xcoord][yFire] = new Piece(true, this, xcoord, yFire, "pawn");
	                	}
	                	if(yFire == 1) {
	                		pieces[xcoord][yFire] = new Piece(true, this, xcoord, yFire, "shield");
	                	}
	                	if(yFire == 2) {
	                		pieces[xcoord][yFire] = new Piece(true, this, xcoord, yFire, "bomb");
	                	}
	                }
	             
	             for(int yWater = N-3; yWater < N; yWater++)
	                if ((xcoord + yWater) % 2 ==0 ) {
	                	if(yWater == N-3) {
	                		pieces[xcoord][yWater] = new Piece(false, this, xcoord, yWater, "bomb");
	                	}
	                	if(yWater == N-2) {
	                		pieces[xcoord][yWater] = new Piece(false, this, xcoord, yWater, "shield");
	                	}
	                	if(yWater == N-1) {
	                		pieces[xcoord][yWater] = new Piece(false, this, xcoord, yWater, "pawn");
	                	}
	                }
	            }
	        }
		}
		drawBoard(N);
	}

	public Piece pieceAt(int x, int y) {
		if (this.isInBoundary(x,y)) {
			if (pieces[x][y] != null ) {
				return pieces[x][y];
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if(isInBoundary(x,y)){

			// No selected Piece
			if(this.selectedPiece == null) {
				
				
				// Trying to pick an empty spot
				if(this.pieces[x][y] == null) {
					return false;
				} else {
					if(this.team == "Fire") {
						if(this.pieceAt(x,y).isFire() == false) {
							return false;
						} else {
							return true;
						}
					} else {
						if(this.team == "Water") {
							if(this.pieces[x][y].isFire() == true) {
								return false;
							} else {
								return true;
							}						
						} else {
							return false;
						}
					}
				}
			} else {

				if(selectedPiece.hasCaptured()) {
					//Picks an empty space
					if(this.pieces[x][y] == null) {
						if(this.playerMoved == false) {
							return isNextMoveValid(this.selectedPieceX, this.selectedPieceY, x, y);
						} else {
							if(this.selectedPiece.hasCaptured() == false) {
								return false;
							} else {
								return isNextMoveValid(this.selectedPieceX, this.selectedPieceY, x, y);
							}
						}
					} else {
						return false;
					}				
				} 

				// Have Selected Piece
				Piece curr = this.selectedPiece;
				int currX = this.selectedPieceX;
				int currY = this.selectedPieceY;
				
				// Selecting another Piece
				if(this.pieceAt(x,y) != null) { 
					if((this.pieceAt(x,y).isFire() == true) && (this.team == "Fire")) {
						return true;
					}
					if((this.pieceAt(x,y).isFire() == false) && (this.team == "Water")) {
						return true;
					} else {
						return false;
					}
				}

				//Has selected a piece, but did not move it
				if((this.playerMoved == false) && isNextMoveValid(currX,currY,x,y)) {
					return true;
				}

				//Picks an empty space
				if(this.pieces[x][y] == null) {
					if(this.playerMoved == false) {
						return isNextMoveValid(currX, currY, x, y);
					} else {
						if(curr.hasCaptured() == false) {
							return false;
						} else {
							return isNextMoveValid(currX, currY, x, y);
						}
					}
				} else {
					return false;
				}
			}
		} else {
		return false;	
		}
	}

	public void select(int x, int y) {

		if(this.pieces[x][y] != null) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			this.selectedPiece = this.pieces[x][y];
			this.selectedPieceX = x;
			this.selectedPieceY = y;
		} else {
			if((this.selectedPiece.isBomb() == true) && (Math.abs(this.selectedPieceX - x) == 2)) {
				for(int increaseX = x - 1; increaseX < x + 2; increaseX++){
					for(int increaseY = y - 1; increaseY < y + 2; increaseY++){
						//Accoutns for Edges
						if((increaseX > 0) || (increaseX < 8)) {
							if((increaseY > 0) || (increaseY < 8)) {	
								//Accounts for Blank Spaces
								if(this.pieceAt(increaseX,increaseY) != null) {
									if(this.pieceAt(increaseX, increaseY).isShield()){
										// DO NOTHING!
									} else {
										// Take out NonShields  
										//PROBABLY USELESS
										if((this.team == "Fire") && (this.pieceAt(increaseX,increaseY).isFire() == false)) {

										}
										if((this.team == "Fire") && (this.pieceAt(increaseX,increaseY).isFire() == true)) {

										}
										if((this.team == "Water") && (this.pieceAt(increaseX,increaseY).isFire() == true)) {

										}
										if((this.team == "Water") && (this.pieceAt(increaseX,increaseY).isFire() == false)) {

										}
										//END USELESS
										this.remove(increaseX, increaseY);

									}
								}
							}
						}
					}
				}
				
				//PROBABLY USELESS
				if(this.team == "Fire") {
				}
				if(this.team == "Water") {
				}
				//END USELESS
				this.playerMoved= true;
				this.selectedPiece.move(x,y);


			} else {

				this.selectedPiece.move(x,y);
				this.selectedPiece = this.pieceAt(x,y);
				this.selectedPieceX = x;
				this.selectedPieceY = y;
				this.playerMoved= true;
				if(this.selectedPiece.hasCaptured()) {
					
					//PROBABLY USELESS
					if(this.team == "Fire") {

					}
					if(this.team == "Water") {

					}
					//END USELESS
				}
			}
		}
	
	}

	public void place(Piece p, int x, int y) {

		if (this.isInBoundary(x, y)) {
			pieces[x][y] = p;

		}
	}

	public Piece remove(int x, int y) {

	 	if (this.isInBoundary(x, y)) {
			if (this.pieceAt(x,y) == null) {
				System.out.println("There is no piece here!");
				return null;
			} else {
				Piece temp = pieces[x][y];
				int tempX = x;
				int tempY = y;
				pieces[x][y] = null;
				tempX = -1;
				tempY = -1;
				return temp;
			}
		} else {
			System.out.println("Out of bounds!");
			return null;
		}
	}

	public boolean canEndTurn() {
		return this.playerMoved;

	}

	private void switchTeam() {
		if (this.team == "Fire") {
			this.team = "Water";
		} else {
			this.team = "Fire";
		}
	}

	public void endTurn() {

		this.switchTeam();
		this.playerMoved = false;
	    this.selectedPiece = null;

	}

	public String winner() {
        
        winner = null;

		for (int xcoord = 0; xcoord < N; xcoord++) {
            for (int ycoord = 0; ycoord < N; ycoord++) {
            	if(this.pieceAt(xcoord,ycoord) == null){
            		//DO NOTHING
            	} else {
	            	if(this.pieceAt(xcoord,ycoord).isFire()) {
	            		fireCounter = fireCounter + 1;
	            	}
	            	if(this.pieceAt(xcoord,ycoord).isFire() == false) {
	            		waterCounter = waterCounter + 1;
	            	}
	            }
            }
        }

        if(fireCounter == 0) {
        	winner = "Water";
        }

        if(waterCounter == 0) {
        	winner = "Fire";
        }

        if(fireCounter + waterCounter == 0) {
        	winner = "No one";
        }

        return winner;

        // if((this.fireCounter == 0) && (this.waterCounter == 0)) {
        // 	winner = "No one";
        // 	return winner;
        // }
        // if(this.waterCounter == 0) {
        // 	winner = "Fire";
        // 	return winner;
        // }
        // if(this.fireCounter == 0) {
        // 	winner = "Water";
        // 	return winner;
        // } else {
        // 	return null;
        // }
	}
}