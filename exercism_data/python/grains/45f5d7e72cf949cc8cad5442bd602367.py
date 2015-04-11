def on_square(chessboard_square):
    return 2 ** (chessboard_square - 1)


def total_after(chessboard_square):
    return sum([on_square(x) for x in range(1, chessboard_square + 1)])
