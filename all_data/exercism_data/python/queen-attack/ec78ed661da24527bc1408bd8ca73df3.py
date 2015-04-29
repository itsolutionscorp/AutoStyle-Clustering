def board(white, black):
    _validate_queen_positions(white, black)
    rows = [["0"] * 8 for _ in range(8)]
    rows[white[0]][white[1]] = "W"
    rows[black[0]][black[1]] = "B"
    return ["".join(row) for row in rows]

def can_attack(white, black):
    _validate_queen_positions(white, black)
    row_delta = abs(white[0] - black[0])
    column_delta = abs(white[1] - black[1])
    return row_delta == 0 or column_delta == 0 or row_delta == column_delta

def _validate_queen_positions(white, black):
    if white == black:
        raise ValueError("The queens cannot be on the same board position")
    for position, color in ((white, "white"), (black, "black")):
        if position < (0, 0) or position > (7, 7):
            raise ValueError("%s: invalid position for %s" % (position, color))
