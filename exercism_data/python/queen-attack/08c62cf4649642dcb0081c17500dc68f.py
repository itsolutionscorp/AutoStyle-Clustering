
def _check(queen1, queen2):
    if (queen1 == queen2 or
        not all(0 <= val <= 7 for val in queen1 + queen2)):
        raise ValueError
    return queen1, queen2

def can_attack(*queens):
    (q1y, q1x), (q2y, q2x) = _check(*queens)
    return q1x == q2x or (q1y - q2y)/(q1x - q2x) in (-1, 0, 1)

def _replace(string, pos, val):
    return string[:pos] + str(val) + string[pos+1:]

def board(*queens):
    board = ['_'*8]*8
    for (irow, icol), val in zip(_check(*queens), 'WB'):
        board[irow] = _replace(board[irow], icol, val)
    return board
