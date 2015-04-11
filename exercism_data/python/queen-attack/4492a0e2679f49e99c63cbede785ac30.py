def board(wq, bq):
    b = [['_' for _ in range(8)] for _ in range(8)]
    b[wq[0]][wq[1]], b[bq[0]][bq[1]] = 'W', 'B'
    return [''.join(row) for row in b]

def can_attack(wq, bq):
    return False
