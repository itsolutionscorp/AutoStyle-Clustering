def board(white, black):
    _check(white, black)
    _board = [['0'] * 8 for _ in range(8)]
    (wi, wj), (bi, bj) = white, black
    _board[wi][wj] = 'W'
    _board[bi][bj] = 'B'
    return [''.join(row) for row in _board]


def can_attack(white, black):
    _check(white, black)
    (wi, wj), (bi, bj) = white, black
    return (wi == bi) or (wj == bj) or abs(wi - bi) == abs(wj - bj)


def _check(white, black):
    if white == black or max(white + black) > 7 or min(white + black) < 0:
        raise ValueError("Bad positions")
