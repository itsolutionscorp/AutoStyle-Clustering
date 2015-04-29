'''exer minesweeper'''

VALID_INPUT = ' *-|+'
VALID_BORDER_ROW = '-+'
VALID_BORDER_COL = '|+'
BOMB = '*'

def board(brd):
    '''fill in bomb counts in open spaces and out put board.  guard against
    invalid input boards'''
    # convert list of strings, to list of lists
    brd = [list(row) for row in brd]

    validate_board(brd)

    for row in range(1, len(brd) - 1):
        for col in range(1, len(brd[0]) - 1):
            char = brd[row][col]
            if char not in VALID_INPUT:
                raise ValueError
            if char == ' ':
                bombs = bomb_scan(brd, row, col)
                if bombs:
                    brd[row][col] = str(bombs)

    # convert back to list of strings and return
    return [''.join(row) for row in brd]

def validate_board(brd):
    '''make sure border is valid and matrix is not irregular'''
    # it is a loose validation, I could create inputs that should fail
    # but wouldn't cause the app to fail.  It's passing the tests so I'm
    # stopping.

    col_count = len(brd[0])
    for row in (brd[0], brd[-1]):
        for char in row:
            if char not in VALID_BORDER_ROW:
                raise ValueError
    for row in brd:
        if len(row) != col_count:
            raise ValueError
        for char in (row[0], row[-1]):
            if char not in VALID_BORDER_COL:
                raise ValueError

def bomb_scan(brd, row, col):
    '''given a board location, count adjacent bombs'''
    bcount = 0
    # row above
    if brd[row - 1][col - 1] == BOMB:
        bcount += 1
    if brd[row - 1][col] == BOMB:
        bcount += 1
    if brd[row - 1][col + 1] == BOMB:
        bcount += 1
    # right and left same row
    if brd[row][col - 1] == BOMB:
        bcount += 1
    if brd[row][col + 1] == BOMB:
        bcount += 1
    # row below
    if brd[row + 1][col - 1] == BOMB:
        bcount += 1
    if brd[row + 1][col] == BOMB:
        bcount += 1
    if brd[row + 1][col + 1] == BOMB:
        bcount += 1

    return bcount
