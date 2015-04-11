def validate_input((w_y, w_x), (b_y, b_x)): 
    # do input validation
    if (w_y == b_y) and (w_x == b_x):
        raise ValueError("queens cannot occupy same spot")
    if not (-1 < w_y < 8 and -1 < w_x < 8 and -1 < b_y < 8 and -1 < b_x < 8):
        raise ValueError("queen is off the board")

def board((w_y, w_x), (b_y, b_x)):
    validate_input((w_y, w_x), (b_y, b_x))  
    # generate blank board
    ans = ['________' for i in range(8)]
    # place white queen
    ans[w_y] = '_' * (w_x) + 'W' + '_' * (8 - w_x - 1)
    # place black queen
    ans[b_y] = '_' * (b_x) + 'B' + '_' * (8 - b_x - 1)
    return ans

def can_attack((w_y, w_x), (b_y, b_x)):
    validate_input((w_y, w_x), (b_y, b_x))
    # if in same column, row or diagonal
    if w_y == b_y or w_x == b_x or abs(w_y - b_y) == abs(w_x - b_x):
        return True
    else:
        return False
