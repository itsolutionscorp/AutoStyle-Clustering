def board(w, b):
    ret = []

    valid_board(w, b)

    for i in range(8):
        row = list('_' * 8)
        if w[0] == i:
            row[w[1]] = 'W'
        if b[0] == i:
            row[b[1]] = 'B'
        ret.append(''.join(row))
        
    return ret

def can_attack(w, b):

    valid_board(w, b)

    if (w[0] == b[0] or w[1] == b[1] or sum(w) == sum(b) or
        w[0] - w[1] == b[0] - b[1]):
        return True

    return False

def valid_board(white, black):
    for n in white + black:
        if not 0 <= n < 8:
            raise ValueError('Position is not on the board.')
        if white == black:
            raise ValueError('Queens can not occupy the same space.')
