def _mkchess():
    x = 1
    dict1 = dict()
    for i in range(1, 65):
        dict1[i] = x
        x = x + x
    return dict1


def on_square(sqr):
    board = _mkchess()
    return board[sqr]


def total_after(x):
    board = _mkchess()
    total = [i for i in board.values()]
    return sum(total[:x])
