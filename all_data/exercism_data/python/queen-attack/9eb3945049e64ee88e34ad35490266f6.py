def board((wx, wy), (bx, by)):
    _check_coordinates(wx,wy,bx,by)
    brd = [ ['_' for x in range(8)] for y in range(8) ]
    brd[wx][wy] = 'W'
    brd[bx][by] = 'B'
    return [''.join(l) for l in brd ]

def can_attack((wx, wy), (bx, by)):
    _check_coordinates(wx,wy,bx,by)
    if wx == bx or wy == by: return True
    if abs(wx-bx) == abs(wy-by): return True
    return False

def _check_coordinates(*c):
    if c[0] == c[2] and c[1] == c[3]: raise ValueError
    if any(e<0 or e>7 for e in c): raise ValueError
    return
