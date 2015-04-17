/**
 * A board for the Checkers61b game.
 *
 * @author Bryan Nguyen
 */
public class Board
{
    private Piece[][] pieces;
    private boolean isFiresTurn;

    /* The number of fire or water pieces currently on the board. */
    private int firePieces;
    private int waterPieces;

    /* If selected is true, piece[selX][selY] is being selected. */
    private boolean selected;
    private int selX;
    private int selY;

    /* Set to true when a piece is moved. Set to false when a turn ends. */
    private boolean pieceMoved;

    /**
     * The constructor for Board. If shouldBeEmpty is true, initializes an empty
     * Board. Otherwise, initializes a Board with the default configuration.
     */
    public Board(boolean shouldBeEmpty)
    {
        pieces = new Piece[8][8];
        isFiresTurn = true;
        firePieces = 0;
        waterPieces = 0;
        selected = false;
        pieceMoved = false;

        if (!shouldBeEmpty)
        {
            initializeFire();
            initializeWater();
        }
    }

    /** Starts a GUI supported version of the game. */
    public static void main(String[] args)
    {
        playBoard();
    }

    /** Plays one game. */
    private static void playBoard()
    {
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while (true)
        {
            board.drawBoard();

            if (StdDrawPlus.mousePressed())
            {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y))
                {
                    board.select((int) x, (int) y);
                }
            }

            if (StdDrawPlus.isSpacePressed() && board.canEndTurn())
            {
                board.endTurn();
                if (board.winner() != null)
                {
                    System.out.println(board.winner());
                    return;
                }
            }

            StdDrawPlus.show(10);
        }
    }

    /** Returns the path to the image file for the given piece. */
    private static String imagePath(Piece piece)
    {
        if (piece == null)
        {
            return null;
        }
        else
        {
            StringBuilder imagePath = new StringBuilder("img/");
            if (piece.isBomb())
            {
                imagePath.append("bomb");
            }
            else if (piece.isShield())
            {
                imagePath.append("shield");
            }
            else
            {
                imagePath.append("pawn");
            }
            if (piece.isFire())
            {
                imagePath.append("-fire");
            }
            else
            {
                imagePath.append("-water");
            }
            if (piece.isKing())
            {
                imagePath.append("-crowned");
            }
            imagePath.append(".png");
            return imagePath.toString();
        }
    }

    /** Draws the current state of the board. */
    private void drawBoard()
    {
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                if ((x + y) % 2 == 0)
                {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else
                {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (selected && x == selX && y == selY)
                {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }

                StdDrawPlus.filledSquare(x + .5, y + .5, .5);

                if (pieces[x][y] != null)
                {
                    StdDrawPlus.picture(x + .5, y + .5, imagePath(pieces[x][y]),
                            1, 1);
                }
            }
        }
    }

    /** Initializes the fire pieces. */
    private void initializeFire()
    {
        for (int y = 0; y <= 2; y += 1)
        {
            String type;
            switch (y)
            {
            case 1:
                type = "shield";
                break;
            case 2:
                type = "bomb";
                break;
            default:
                type = "pawn";
                break;
            }
            for (int x = y % 2; x <= 7; x += 2)
            {
                place(new Piece(true, this, x, y, type), x, y);
            }
        }
    }

    /** Initializes the water pieces. */
    private void initializeWater()
    {
        for (int y = 5; y <= 7; y += 1)
        {
            String type;
            switch (y)
            {
            case 6:
                type = "shield";
                break;
            case 5:
                type = "bomb";
                break;
            default:
                type = "pawn";
                break;
            }
            for (int x = y % 2; x <= 7; x += 2)
            {
                place(new Piece(false, this, x, y, type), x, y);
            }
        }
    }

    /**
     * Gets the piece at position (x, y) on the board, or null if there is
     * no piece. If (x, y) are out of bounds, returns null.
     */
    public Piece pieceAt(int x, int y)
    {
        try
        {
            return pieces[x][y];
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    /**
     * Returns true if there is a piece the piece at (x, y) and it can be
     * selected.
     */
    public boolean canSelect(int x, int y)
    {
        Piece piece = pieceAt(x, y);
        if (piece != null)
        {
            /* A piece may be selected if it is the corresponding player's turn
               and the player has not already moved or captured. */
            return (((isFiresTurn && piece.isFire()) ||
                    !isFiresTurn && !piece.isFire()) && !pieceMoved);
        }
        else
        {
            /* An empty square may be selected (a move may be made) if a piece
               is already selected and the square is a valid move for it.
               Note that logic for checking if a piece has been moved is
               incorporated into validMove. */
            return selected && validMove(selX, selY, x, y);
        }
    }

    /**
     * Returns true if the piece at (xi, yi) can either move to (xf, yf) or
     * capture to (xf, yf), which is when
     * 1. The piece and the destination are in bounds.
     * 2. The piece exists.
     * 3. The destination square is empty.
     * 4. The move is in the forward direction, unless the piece is a king.
     * 5. The difference in position is (1, 1) for a move or (2, 2) for a
     *    capture.
     * 6. (for moving) The piece has not already moved.
     * 7. (for capturing) The piece has already captured this turn or has not
     *    already moved.
     */
    private boolean validMove(int xi, int yi, int xf, int yf)
    {
        Piece piece = pieceAt(xi, yi);
        Piece destination = pieceAt(xf, yf);

        /* Check that the piece and destination are in bounds. */
        if (xi < 0 || xi > 7 || yi < 0 || yi > 7 ||
                xf < 0 || xf > 7 || yf < 0 || yf > 7)
        {
            return false;
        }
        /* Check that the piece exists. */
        else if (pieceAt(xi, yi) == null)
        {
            return false;
        }
        /* Check that destination square is empty. */
        else if (destination != null)
        {
            return false;
        }
        /* Check direction of move: only kings may move backward. */
        else if (!piece.isKing() && ((piece.isFire() && yf < yi) ||
                                    (!piece.isFire() && yf > yi)))
        {
            return false;
        }
        else
        {
            /* Move when dx = dy = 1. Capture when dx = dy = 2. */
            int dx = Math.abs(xi - xf);
            int dy = Math.abs(yi - yf);

            /* Once a piece has moved or captured, it cannot move again. */
            if (dx == 1 && dy == 1 && !pieceMoved)
            {
                return true;
            }
            /* A piece may capture if it has not moved or if it has previously
               captured. */
            else if (dx == 2 && dy == 2 && (!pieceMoved || piece.hasCaptured()))
            {
                int capturex = (xi + xf) / 2;
                int capturey = (yi + yf) / 2;
                Piece capture = pieceAt(capturex, capturey);
                /* Captured piece must exist and be of the opposite color. */
                return (capture != null &&
                        ((capture.isFire() && !piece.isFire()) ||
                                (!capture.isFire() && piece.isFire())));
            }
            else
            {
                return false;
            }
        }
    }

    /** Selects the piece at (x, y) if possible. */
    public void select(int x, int y)
    {
        if (pieceAt(x, y) == null)
        {
            pieceAt(selX, selY).move(x, y);
            pieceMoved = true;
            if (pieceAt(x, y) == null)
            {
                selected = false;
            }
            else
            {
                selX = x;
                selY = y;
            }
        }
        else
        {
            selected = true;
            selX = x;
            selY = y;
        }
    }

    /**
     * Places p at (x, y). If (x, y) is out of bounds or if p is null, does
     * nothing. If another piece already exists at (x, y), p will replace that
     * piece.
     */
    public void place(Piece p, int x, int y)
    {
        if (!(p == null || x < 0 || x > 7 || y < 0 || y > 7))
        {
            if (pieceAt(x, y) != null)
            {
                remove(x, y);
            }
            pieces[x][y] = p;
            if (p.isFire())
            {
                firePieces += 1;
            }
            else
            {
                waterPieces += 1;
            }
        }
    }

    /**
     * Executes a remove. Returns the piece that was removed. If the input
     * (x, y) is out of bounds, returns null and prints an appropriate
     * message. If there is no piece at (x, y), returns null and prints an
     * appropriate message.
     */
    public Piece remove(int x, int y)
    {
        if (x < 0 || x > 7 || y < 0 || y > 7)
        {
            System.out.println("Attempted to remove piece at out-of-bounds " +
                    "location (" + x + ", " + y + ")");
            return null;
        }
        else
        {
            Piece piece = pieceAt(x, y);
            if (piece == null)
            {
                System.out.println("Attempted to remove an empty piece at (" +
                        x + ", " + y + ")");
                return null;
            }
            else
            {
                pieces[x][y] = null;
                if (piece.isFire())
                {
                    firePieces -= 1;
                }
                else
                {
                    waterPieces -= 1;
                }
                return piece;
            }
        }
    }

    /**
     * Returns whether or not the the current player can end their turn. To
     * be able to end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn()
    {
        return pieceMoved;
    }

    /**
     * Called at the end of each turn. Handles switching of players and
     * anything else that should happen at the end of a turn.
     */
    public void endTurn()
    {
        if (canEndTurn())
        {
            if (selected)
            {
                pieceAt(selX, selY).doneCapturing();
                selected = false;
            }
            isFiresTurn = !isFiresTurn;
            pieceMoved = false;
        }
    }

    /**
     * Returns the winner of the game. "Fire", "Water", "No one" (tie / no
     * pieces on the board), or null if the game is not yet over. Assume
     * there is no stalemate situation in which the current player has pieces
     * but cannot legally move any of them (In this event, just leave it at
     * null). Determine the winner solely by the number of pieces belonging
     * to each team.
     */
    public String winner()
    {
        if (firePieces > 0 && waterPieces == 0)
        {
            return "Fire";
        }
        else if (firePieces == 0 && waterPieces > 0)
        {
            return "Water";
        }
        else if (firePieces == 0 && waterPieces == 0)
        {
            return "No one";
        }
        else
        {
            return null;
        }
    }
}
