
from collections import Counter

def _verify(board):
    if ((len(set(len(row) for row in board)) != 1) or                   # uniform rows
        (board[0] != board[-1] != ('+'+'-'*(len(board[0])-2)+'+')) or   # first and last row
        (any('|' != row[0] != row[-1] for row in board[1:-1])) or       # first and last col
        (set(''.join(board)) - set('+-* |'))):                          # illegal characters
        raise ValueError

def count_neightbors(board, irow, icol):
    rows = board[irow-1:irow+2]
    neighbors =  Counter(''.join(row[icol-1:icol+2]
                                 for row in rows))['*']
    if neighbors:
        return str(neighbors)
    return ' '

def board(board):
    _verify(board)
    return [''.join((count_neightbors(board, irow, icol)
                     if char == ' '
                     else char)
                    for icol, char in enumerate(row))
            for irow, row in enumerate(board)]
