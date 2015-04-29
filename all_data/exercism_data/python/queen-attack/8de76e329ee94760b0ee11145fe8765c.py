ROW = 8
COL = 8

def board(rcW, rcB):
    check(rcW, rcB)
    brd = [['_'] * COL for i in range(ROW)]
    i,j = rcW
    brd[i][j] = 'W'
    i,j = rcB
    brd[i][j] = 'B'
    return [''.join(brd[i]) for i in range(COL)]
            
def can_attack(rcW, rcB):
    check(rcW, rcB)
    r = rcW[0] - rcB[0]
    c = rcW[1] - rcB[1]
    if r == 0 or c == 0:
        return True
    if abs(r) == abs(c):
        return True
    return False

def check(rcW, rcB):
    if rcW == rcB:
        raise ValueError('Queens cannot occupy same space.')
    if min(rcW + rcB) < 0:
        raise ValueError('Negative coordinate given.')
    if rcW[0] >= ROW or rcB[0] >= ROW:
        raise ValueError('Max row is %d' % (ROW-1))
    if rcW[1] >= COL or rcB[1] >= COL:
        raise ValueError('Max col is %d' % (COL-1))
