"""Queen Attack"""

BOARD_ROWS, BOARD_COLS = (8, 8)


def board(white, black):
    """Return a chessboard representation."""
    check_queens(white, black)

    white_row, white_col = white
    black_row, black_col = black

    rows = [["0"] * BOARD_COLS for _ in xrange(BOARD_ROWS)]
    rows[white_row][white_col] = "W"
    rows[black_row][black_col] = "B"

    return ["".join(row) for row in rows]


def can_attack(white, black):
    """Return true if two queens can attack each other."""
    check_queens(white, black)

    white_row, white_col = white
    black_row, black_col = black

    return white_row == black_row or white_col == black_col or \
        white_row - white_col == black_row - black_col or \
        white_row + white_col == black_row + black_col


def check_queens(white, black):
    """Check if queens' coordinates are valid."""
    if white == black:
        raise ValueError(white)

    for r, c in (white, black):
        if not (0 <= r < BOARD_ROWS) or not (0 <= c < BOARD_COLS):
            raise ValueError((r, c))
