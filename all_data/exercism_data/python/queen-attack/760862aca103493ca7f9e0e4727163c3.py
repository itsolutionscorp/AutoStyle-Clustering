def board(white,black):
    if not check_valid(white+black):
        raise ValueError("Invalid queen positions.")
    board_list = [['_' for i in range(8)] for j in range(8)]
    board_list[white[0]][white[1]] = 'W'
    board_list[black[0]][black[1]] = 'B'
    return ["".join(row) for row in board_list]

def can_attack(white,black):
    if not check_valid(white+black):
        raise ValueError("invalid queen positions.")
    return any([white[i] == black[i] for i in [0,1]]) or abs(white[0]-black[0]) == abs(white[1]-black[1])

def check_valid(queens):
    return all([i < 8 and i >= 0 for i in queens]) and queens[0:2] != queens[2:4]
