public class Board
{
	private final Piece[][] board;

    private int player = 0; //current player, 0=Fire, 1=Water
    private Piece selected;
    private int selectedX;
    private int selectedY;

    private static int N = 8;

    private State state = State.NOACTION;

	public static void main(String[] args)
    {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false);
        while (b.winner() == null)
        {
            b.drawBoard();
            if (StdDrawPlus.mousePressed())
            {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y))
                {
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed())
            {
                if (b.canEndTurn())
                {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(10);
        }
    }

	public Board(boolean shouldBeEmpty)
	{
        board = new Piece[N][N];

        if (!shouldBeEmpty)
        {
            //Setting Fire
            for (int i : new int[] {0, 2, 4, 6})
            {

                place((new Piece(true, this, i, 0, "pawn")), i, 0);
                place((new Piece(true, this, i, 2, "bomb")), i, 2);
            }

            for (int i : new int[] {1, 3, 5, 7})
            {
                place((new Piece(true, this, i, 1, "shield")), i, 1);
            }

            //Setting Water
            for (int i : new int[] {1, 3, 5, 7})
            {
                place((new Piece(false, this, i, 5, "bomb")), i, 5);
                place((new Piece(false, this, i, 7, "pawn")), i, 7);
            }

            for (int i : new int[] {0, 2, 4, 6})
            {
                place((new Piece(false, this, i, 6, "shield")), i, 6);
            }
        }
	}

	public Piece pieceAt(int x, int y)
	{
        return validPoint(x, y) ? board[x][y] : null;
	}

	public boolean canSelect(int x, int y)
	{
        if (!validPoint(x, y))
        {
            return false;
        }
        Piece p = pieceAt(x, y);
        if (p == null)
        {
            if (selected == null)
            {
                return false; //not doing anything here
            }
            else
            {
                return validMove(selectedX, selectedY, x, y);
            }

        }
        if (p.side() != player)
        {
            return false;
        }


        else //p is actually a piece
        {
            if (state == State.MOVED || state == State.CAPTURED)
            {
                return false;
            }
            return true;
        }

	}

	public void select(int x, int y)
	{
//        if (selected != null)
//        {
//            System.out.println("Select:" + selectedX + ", " + selectedY + "->" + x + ", " + y);
//        }
//        else
//        {
//            System.out.println("Select:"+x+", "+y);
//        }
        //dumpState();
        Piece p = pieceAt(x, y);
        if (p != null) //new selection
        {
            //System.out.println("New");
            selected = p;
            selectedX = x;
            selectedY = y;
        }
        else if (selected != null && validMove(selectedX, selectedY, x, y)) //p is null, so empty spot
        {
            //System.out.println("Empty");
            Action action = analyzeMove(selectedX, selectedY, x, y);
            //System.out.println(action);
            selected.move(x,y);
            switch(action)
            {
                case CAPTURE:
                    int removeX = (selectedX + x)/2;
                    int removeY = (selectedY + y)/2;
                    remove(removeX, removeY);

                    state = State.CAPTURED;

                    if (selected.isBomb())
                    {
                        //delete piece
                        selected = null;
                    }
                    break;

                case MOVE:
                    state = State.MOVED;
                    break;
                default:
                    break; //nothing happening
            }
            selectedX = x;
            selectedY = y;
            //dumpState();
        }


	}

	public void place(Piece p, int x, int y)
    {
        //System.out.println("Place:" +pts(dump(p)) + " at " + x + ", " + y);
        //dumpState();
        if(!validPoint(x, y))
        {
            return;
        }
        board[x][y] = p;
        //dumpState();
	}

	public Piece remove(int x, int y)
	{
        if(!validPoint(x, y))
        {
            return null;
        }
        Piece p = board[x][y];
        board[x][y] = null;
        return p;
	}

	public boolean canEndTurn()
    {
        //System.out.println("trying to end turn : "+state + ":"+ isValidActionState(state, Action.END));
        return isValidActionState(state, Action.END);
	}

	public void endTurn()
	{
        //System.out.println("ending turn");
        for (Piece p : getPieces())
        {
            p.doneCapturing();
        }
        state = State.NOACTION;
        player = 1 - player;
        selected = null;
	}

	public String winner()
	{
        int fireCount = 0;
        int waterCount = 0;
        for (Piece p : getPieces())
        {
            fireCount += 1 - p.side();
            waterCount += p.side();
        }
        if (fireCount == 0 && waterCount == 0)
        {
            return "No one";
        }
        else if (fireCount == 0)
        {
            return "Water";
        }
        else if(waterCount == 0)
        {
            return "Fire";
        }
        else
        {
            return null;
        }
	}

    private boolean validPoint(int x, int y)
    {
        //returns whether x,y is actually a valid point on the checkerboard i.e. if it's gray
        if (x < 0 || x >= N || y < 0 || y >= N)
        {
            return false; //out of bounds
        }
        return ((x + y) & 1) == 0; //all gray points have even coordinate sums, i.e. 0,0 -> 0 + 0 = 0
    }


    private boolean validMove(int xi, int yi, int xf, int yf)
    {
        if (!(validPoint(xi, yi) && validPoint(xf, yf)))
        {
            return false; //all points have to be valid
        }


        Piece p = pieceAt(xi, yi);
        Piece destinationPiece = pieceAt(xf, yf);

        int deltaX = xf - xi;
        int deltaY = yf - yi;

        int dir = direction(p.side());

        if(deltaX == 0 && deltaY == 0)
        {
            return true;
        }

        if (destinationPiece != null || p == null)
        {
            return false; //you can't land on another piece, and you can't move a non-existent piece
        }

        Action action = analyzeMove(xi, yi, xf, yf);

        if (action == null)
        {
            return false; //invalid action
        }

        if (deltaY * dir < 0 && !p.isKing())
        {
            //System.out.println("Can't move backwards");
            return false; //deltaY and dir have to have the same sign, so deltaY*dir must be positive
        }
        if (action == Action.CAPTURE)
        {
            Piece captured = pieceAt((xi+xf)/2, (yi + yf)/2);
            if(captured == null || captured.side() == p.side())
            {
                return false; //must be a piece in between, and must be from other side
            }

        }

        return isValidActionState(state, action);
    }

    private int direction(int player)
    {
        return 1 - player * 2; //returns 1 if player is Fire (0) and -1 if player is Water (1)
    }

    private boolean isValidActionState(State state, Action action)
    {
        switch(state)
        {
            case NOACTION:
                return (action != Action.END); //still can do anything you want, but can't end

            case MOVED:
                return (action == Action.END); //if you've moved that's it

            case CAPTURED:
                return (action != Action.MOVE); //can't move after capture

            default:
                return true;
        }
    }

    private Action analyzeMove(int xi, int yi, int xf, int yf)
    {
        //returns the type of Action represented by the transition (xi, yi) -> (xf, yf)
        int deltaX = xf - xi;
        int deltaY = yf - yi;

        if (Math.abs(deltaY) == 1 && Math.abs(deltaX) == 1)
        {
            return Action.MOVE;
        }

        if(Math.abs(deltaY) == 2 && Math.abs(deltaX) == 2)
        {
            return Action.CAPTURE;
        }
        return null;
    }

    private enum State
    {
        NOACTION,
        MOVED,
        CAPTURED
    }

    private enum Action
    {
        MOVE,
        CAPTURE,
        END
    }

    private String getFilename(Piece p)
    {
        String imgfile = "img/";
        if (p.isShield())
        {
            imgfile += "shield";
        }
        else if (p.isBomb())
        {
            imgfile += "bomb";
        }
        else
        {
            imgfile += "pawn";
        }
        imgfile += p.isFire() ? "-fire" : "-water";
        if(p.isKing())
        {
            imgfile += "-crowned";
        }
        imgfile += ".png";
        return imgfile;
    }

    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(selected != null && i == selectedX && j == selectedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                Piece p = pieceAt(i, j);
                if (p == null)
                {
                    continue;
                }

                StdDrawPlus.picture(i + .5, j + .5, getFilename(p), 1, 1);
            }
        }

    }

    //Below are testing/logging methods.

    private Piece[] getPieces()
    {
        int count = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                Piece p = pieceAt(i, j);
                if (p == null)
                {
                    continue;
                }
                count++;
            }
        }
        Piece[] pieces = new Piece[count];
        int counter = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                Piece p = pieceAt(i, j);
                if (p == null)
                {
                    continue;
                }
                pieces[counter] = p;
                counter++;
            }
        }
        return pieces;
    }

    private byte[][] dump()
    {
        //dumps Board as a byte array
        byte[][] output = new byte[8][8];
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Piece p = board[i][j];
                if(p != null)
                {
                    output[i][j] = dump(p);
                }
            }
        }
        return output;
    }

    private byte dump(Piece p)
    {
        ///dumps self
        byte out = 0;
        if (p == null)
        {
            return 0;
        }
        if (!p.isFire())
        {
            out |= 0b1000;
        }
        if (p.isKing())
        {
            out |= 0b0100;
        }
        if (p.isBomb())
        {
            out |= 0b0010;
        }
        else if (p.isShield())
        {
            out |= 0b0011;
        }
        else
        {
            out |= 0b0001;
        }
        return out;

    }

    private String pts(byte c)
    {
        if (c == 0)
        {
            return "---";
        }
        String out = "";
        out += (c & 0b1000) == 0 ? "F" : "W";
        out += (c & 0b0100) == 0 ? "R" : "K";
        switch(c & 0b0011)
        {
            case 0b01:
                return out + "P";
            case 0b10:
                return out + "B";
            case 0b11:
                return out + "S";
        }
        return "   ";
    }

    private byte[][] transpose(byte[][] arr)
    {
        byte[][] out = new byte[arr.length][arr[0].length];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                out[N-1-i][j] = arr[j][i];
            }
        }
        return out;
    }

    private void dumpState()
    {
        byte[][] dumped = transpose(dump());
        for(int i = 0; i < N; i++)
        {
            System.out.print(N-1-i);
            for (int j = 0; j < N; j++)
            {
                System.out.print(pts(dumped[i][j]));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.print("  0   1   2   3   4   5   6   7");
        System.out.print("\n\n---------------------------------\n\n\n");

    }



}