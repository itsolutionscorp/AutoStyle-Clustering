def board((wx, wy), (bx, by)):
    _check_coordinates((wx, wy), (bx, by))
    brd = [ ['_' for x in range(8)] for y in range(8) ]
    brd[wx][wy] = 'W'
    brd[bx][by] = 'B'
    return [''.join(l) for l in brd ]

def can_attack((wx, wy), (bx, by)):
    _check_coordinates((wx, wy), (bx, by))
    return wx == bx or wy == by or abs(wx-bx) == abs(wy-by)

def _check_coordinates(w, b):
    if w==b or not all(0 <= e <= 7 for e in w+b): raise ValueError
