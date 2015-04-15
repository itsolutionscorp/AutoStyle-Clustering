def check_position(w, b):
    for p in (w, b):
        if not (0 <= p[0] <= 7 and 0 <= p[1] <= 7):
            raise ValueError()

    if w == b:
        raise ValueError()

def board(w, b):
    check_position(w, b)

    brd = [['_'] * 8 for i in range(8)]
    brd[w[0]][w[1]] = 'W'
    brd[b[0]][b[1]] = 'B'

    return [''.join(row) for row in brd]

def can_attack(w, b):
    check_position(w, b)

    if w[0] == b[0] or w[1] == b[1]:
        return True

    if abs(w[0] - b[0]) == abs(w[1] - b[1]):
        return True

    return False
