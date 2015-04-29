def board(w, b):
    bo = list()
    if not (isvalidposition(w) and isvalidposition(b)) or w == b:
        raise ValueError
    for i in range(0, 8):
        row = ''
        for j in range(0, 8):
            if (i, j) == w:
                row += 'W'
            elif (i, j) == b:
                row += 'B'
            else:
                row += '_'
        bo.append(row)
    return bo


def can_attack(w, b):
    if not (isvalidposition(w) and isvalidposition(b)) or w == b:
        raise ValueError
    return (w[0] == b[0]) or (w[1] == b[1])\
        or (abs(w[0] - b[0]) == abs(w[1] - b[1]))


def isvalidposition(p):
    return 0 <= p[0] < 8 and 0 <= p[1] < 8
