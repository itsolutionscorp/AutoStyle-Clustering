def make_board():
    board = []
    for i in range(64):
        if i == 0:
            board.append(i+1)
        else:
            board.append(board[i-1] * 2)
    return board

def on_square(num):
    board = make_board()
    return board[num - 1]

def total_after(num):
    board = make_board()
    return sum(board[:num:])
