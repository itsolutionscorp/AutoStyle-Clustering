
from collections import Counter

def _verify(board):
    if len(set(len(row) for row in board)) != 1:
        raise ValueError
    if board[0] != board[-1] != ('+'+'-'*(len(board[0])-2)+'+'):
        raise ValueError
    if any('|' != row[0] != row[-1]
           for row in board[1:-1]):
        raise ValueError
    if set(''.join(board)) - set('+-* |'):
        raise ValueError

def count_neightbors(board, irow, icol):
    assert board[irow][icol] == ' '
    rows = board[irow-1:irow+2]
    return Counter(''.join(row[icol-1:icol+2]
                           for row in rows))['*']

def replace(string, pos, val):
    return string[:pos] + str(val) + string[pos+1:]

def board(board):
    _verify(board)
    for irow in range(1, len(board)-1):
        for icol, char in enumerate(board[irow][1:-1], 1):
            if char == ' ':
                neighbors = count_neightbors(board, irow, icol)
                if neighbors:
                    board[irow] = replace(board[irow], icol, neighbors)
    return board
