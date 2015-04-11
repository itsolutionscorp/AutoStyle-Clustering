def board(white,black):
    if white == black or any(i > 7 for i in white+black):
        raise ValueError("Check queens positions please.")
    board = [['_']*8 for _ in range(8)]
    board[white[0]][white[1]] = "W"
    board[black[0]][black[1]] = "B"
    return ["".join(line) for line in board]

def can_attack(white,black):
    if white == black or any(i > 7 for i in white+black):
        raise ValueError("Check queens positions please.")
    wx,wy = white
    bx,by = black
    return abs(wx-bx) == abs(wy-by) or wx==bx or wy==by
