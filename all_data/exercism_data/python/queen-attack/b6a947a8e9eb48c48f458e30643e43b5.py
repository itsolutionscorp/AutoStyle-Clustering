from itertools import product


def board(w_queen, b_queen):
    if not validate(w_queen, b_queen):
        raise ValueError('Invalid Position')

    board = [['_' for _ in range(8)] for _ in range(8)]

    x, y = w_queen
    board[x][y] = 'W'
    x, y = b_queen
    board[x][y] = 'B'

    return [''.join(row) for row in board]


def can_attack(w_queen, b_queen):
    if not validate(w_queen, b_queen):
        raise ValueError('Invalid position')

    squares = set(product(range(8), range(8)))
    visible = {(x, y) for x, y in squares
                       if x == w_queen[0] or y == w_queen[1] or # column & row
                          (x+y) == sum(w_queen) or # xdiagonal
                          (x-y) == w_queen[0] - w_queen[1]}  # ydiagonal

    return b_queen in visible


def validate(w_queen, b_queen):
    squares = set(product(range(8), range(8)))

    return b_queen in squares and \
           w_queen in squares and \
           w_queen != b_queen
