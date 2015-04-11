def validate_input(white, black): 
    if white == black:
        raise ValueError("queens cannot occupy same spot")
    if not all(0 <= val <= 7 for val in white + black):
        raise ValueError("queen is off the board")

def board(white, black):
    validate_input(white, black)
    (w_y, w_x), (b_y, b_x) = white, black
    # generate blank board
    ans = ['_' * 8 for i in range(8)]
    # place white queen
    ans[w_y] = '_' * (w_x) + 'W' + '_' * (8-w_x-1)
    # place black queen
    ans[b_y] = '_' * (b_x) + 'B' + '_' * (8-b_x-1)
    return ans

def can_attack(white, black):
    validate_input(white, black)
    (w_y, w_x), (b_y, b_x) = white, black
    # if in same column, row or diagonal
    if w_y == b_y or w_x == b_x or abs(w_y - b_y) == abs(w_x - b_x):
        return True
    else:
        return False
