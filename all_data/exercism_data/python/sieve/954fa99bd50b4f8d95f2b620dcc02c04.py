from itertools import compress


def sieve(limit):
    candidates = range(2, limit+1)
    marks = [1 for i in candidates]
    for i, n in enumerate(candidates):
        for j in range(i+n, limit-1, n):
            marks[j] = 0
    return list(compress(candidates, marks))
