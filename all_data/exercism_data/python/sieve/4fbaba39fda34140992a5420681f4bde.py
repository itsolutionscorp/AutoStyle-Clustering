from math import ceil, sqrt

def sieve(limit):
    marks = [0b1] * (limit + 1)
    marks[0] = marks[1] = 0b0

    for i in range(2, int(ceil(sqrt(limit)))):
        for mark in range(2 * i, limit + 1, i):
            marks[mark] = 0b0

    return [i for i in range(len(marks)) if marks[i]]
