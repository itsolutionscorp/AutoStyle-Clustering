def valid(func):
    def checker(w, b):
        if any([i > 7 for i in w+b]):
            raise ValueError('Pieces are off-board')
        if w == b:
            raise ValueError('White and Black are in same position')
        return func(w, b)
    return checker

@valid
def construct_board(white_queen, black_queen):
    board = [list(row) for row in ['_'*8,]*8]
    
    board[white_queen[0]][white_queen[1]] = 'W'
    board[black_queen[0]][black_queen[1]] = 'B'
    
    return [''.join(row) for row in board]

@valid
def can_attack(white_queen, black_queen):
    (x1, y1) = white_queen
    (x2, y2) = black_queen
    
    dx = abs(x2 - x1)
    dy = abs(y2 - y1)
    
    return dx == dy or dx == 0 or dy == 0
