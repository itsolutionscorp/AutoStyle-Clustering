__author__ = 'Flavio Miranda'


def sieve(mx):
    matrix = [i for i in range(2, mx + 1)]
    index, stop = 0, len(matrix)

    while index < stop:
        act = matrix[index]
        matrix = [matrix[x] for x in range(0, stop) if matrix[x] % act != 0 or matrix[x] == act]
        stop = len(matrix)
        index += 1

    return matrix
