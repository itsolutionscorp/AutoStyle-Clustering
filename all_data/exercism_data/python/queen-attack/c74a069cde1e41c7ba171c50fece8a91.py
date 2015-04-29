def board(w, b):
    
    check_board(w, b)
    
    ans = []
    for i in range(8):
        ans.append('')
        for j in range(8):
            ans[-1] += ('W' if w == (i,j) else ('B' if b == (i,j) else '_'))
    return ans

def check_board(w, b):
    
    # Larger than the board
    if w[0] > 7 or w[1] > 7 or b[0] > 7 or b[1] > 7: raise ValueError("Value is larger than the board.")
    
    # Smaller than the board
    elif w[0] < 0 or w[1] < 0 or b[0] < 0 or b[1] < 0: raise ValueError("Value is smaller than the board.")
    
    # Same position on the board
    elif w == b: raise ValueError("Queens cannot be at the same position.")

def can_attack(w, b):
    
    check_board(w, b)

    # Horizontal attack
    if w[0] == b[0]: return True
    
    # Vertical attack
    elif w[1] == b[1]: return True
    
    # Diagonal attack
    elif abs(w[0] - b[0]) == abs(w[1] - b[1]): return True
    
    # No attack
    else: return False
