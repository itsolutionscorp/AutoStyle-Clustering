import re

def board(arr):
    board = parse_board(arr)

    for i in range(1, len(board) - 1):
        for j in range(1, len(board[0]) - 1):
            if board[i][j] != ' ':
                continue
            m  = 0
            for x in range(i - 1, i + 2):
                for y in range(j - 1, j + 2):
                  if board[x][y] == '*':
                      m += 1
            if m:
                board[i][j] = str(m)

    return [''.join(x) for x in board]

def parse_board(arr):
    err = ''
    board = []

    if not re.match(r'\+-*\+', arr[0]):
        err = 'Invalid top border for Minesweeper'

    for s in arr[1:-1]:
        if not re.match(r'\|[ *]*\|', s):
            err = 'Invalid row for Minesweeper'

    if not re.match(r'\+-*\+', arr[-1]):
        err = 'Invalid bottom border for Minesweeper'

    for s in arr:
        if len(s) != len(arr[0]):
            err = 'Invalid row width for Minesweeper'
        board.append(list(s))

    if err != '':
        raise ValueError(err)

    return board
