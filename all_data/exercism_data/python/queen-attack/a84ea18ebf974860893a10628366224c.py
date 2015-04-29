def board(wq, bq):
    checkpos(wq, bq)
    b = [['_' for _ in range(8)] for _ in range(8)]
    b[wq[0]][wq[1]], b[bq[0]][bq[1]] = 'W', 'B'
    return [''.join(row) for row in b]

def can_attack(wq, bq):
    checkpos(wq, bq)
    return (wq[0] - bq[0]) * (wq[1] - bq[1]) == 0 or abs((wq[0] - bq[0])) == abs((wq[1] - bq[1]))

def checkpos(wq, bq):
    if any(i > 7 or i < 0 for i in wq + bq) or wq == bq:
        raise ValueError('Wrong position!')
