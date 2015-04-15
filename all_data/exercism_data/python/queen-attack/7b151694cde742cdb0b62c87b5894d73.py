import math

def board(w_queen, b_queen):
    check_conditions(w_queen, b_queen)
    board = [['_' for _ in range(8)] for _ in range(8)]
    return_board = [None for _ in range(8)]

    for row in range(0,8):
        for col in range(0,8):
            if (row,col) == w_queen:
                board[row][col] = 'W'
            elif (row,col) == b_queen:
                board[row][col] = 'B'
    for row in range(0,8):
        return_board[row] = ''.join(board[row])
    return return_board

def check_conditions(w_queen, b_queen):
    if w_queen == b_queen:
        raise ValueError
    all_queen = w_queen + b_queen
    for elm in all_queen:
        if elm > 7 or elm < 0:
            raise ValueError

def can_attack(w_queen, b_queen):
    check_conditions(w_queen, b_queen)
    row_w = w_queen[0]
    row_b = b_queen[0]
    col_w = w_queen[1]
    col_b = b_queen[1]
    if row_w == row_b:
        return True
    elif col_w == col_b:
        return True
    elif math.fabs(row_w - row_b) == math.fabs(col_w - col_b):
        return True
    else:
        return False
