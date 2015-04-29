from itertools import product


def validate(function):
    board = set(product(range(8), range(8)))
    def wrapper(white, black):
        if black not in board or white not in board or \
           white == black:
               raise ValueError
        return function(white, black)

    return wrapper


@validate
def board(white, black):
    board = [['_' for _ in range(8)] for _ in range(8)]
    board[white[0]][white[1]] = 'W'
    board[black[0]][black[1]] = 'B'

    return [''.join(row) for row in board]


@validate
def can_attack(white, black):
    squares = set(product(range(8), range(8)))
    visible = {(x, y) for x, y in squares
                       if x == white[0] or y == white[1] or # column & row
                          (x+y) == sum(white) or (x-y) == white[0] - white[1]}  # diagonals

    return black in visible
