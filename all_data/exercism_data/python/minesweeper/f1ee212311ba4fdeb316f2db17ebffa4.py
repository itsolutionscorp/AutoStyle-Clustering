from itertools import product


def board(inp):

    inp = [list(row) for row in inp]
    columns = list(zip(*inp))
    cords = set(product(range(len(inp)), range(len(columns))))

    if not _valid_board(inp, columns, cords):
        raise ValueError('Invalid Board')
    
    # filter out the borders on the board
    cords = {(x, y) for x, y in cords
                    if x != 0 and x != len(inp) - 1 and
                       y != 0 and y != len(columns) - 1}

    adjacents = _adjacent_cells(1, 1, cords)

    for x, y in cords:
        if inp[x][y] != '*':
            inp[x][y] = str(sum(inp[xa][ya] == '*'
                            for xa, ya in _adjacent_cells(x, y, cords)))

    return [''.join(row).replace('0', ' ') for row in inp]


def _adjacent_cells(x, y, cords):
    '''Given an (x, y) cordinate returns a set of its adjacent cords'''
    adj = {(x - 1, y - 1), (x + 1, y - 1), (x - 1, y + 1), (x + 1, y + 1),
           (x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)}

    return cords.intersection(adj)


def _valid_board(board, columns, cords):
    # Validate row lengths
    if any(len(row) != len(board[0]) for row in board):
        return False

    # Validate corners
    if columns[ 0][ 0] != '+' or \
       columns[ 0][-1] != '+' or \
       columns[-1][ 0] != '+' or \
       columns[-1][-1] != '+':
           return False

    # Validate left, right borders
    if any(columns[ 0][i] != '|' for i in range(1, len(columns[0]) - 1)) or \
       any(columns[-1][i] != '|' for i in range(1, len(columns[0]) - 1)):
            return False

    # Validate top bottom borders
    if any(board[ 0][i] != '-' for i in range(1, len(board[0]) - 1)) or \
       any(board[-1][i] != '-' for i in range(1, len(board[0]) - 1)):
            return False

    # Validate inner board
    if any(board[x][y] not in '*|+- ' for x, y in cords):
        return False

    return True
