import itertools
import numpy as np


def saddle_points(matrix):
    if not matrix:
        return set()

    row_length = len(matrix[0])
    if any(len(row) != row_length for row in matrix):
        raise ValueError

    matrix = np.asarray(matrix)
    shape = matrix.shape

    saddles = set()
    for x, y in itertools.product(range(shape[0]), range(shape[1])):
        value = matrix[x, y]
        if np.all(value >= matrix[x, :]) and np.all(value <= matrix[:, y]):
            saddles.add((x, y))

    return saddles
