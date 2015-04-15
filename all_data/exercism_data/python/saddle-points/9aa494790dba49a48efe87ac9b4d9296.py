__author__ = 'emiller42'

import numpy as np


def saddle_points(array):
    array = np.array(array)

    if len(array) == 0:
        return set()

    if len(array.shape) != 2:
        raise ValueError

    min_cols = np.amin(array, axis=0)
    max_rows = np.amax(array, axis=1)

    return set([(x, y) for x in range(0, array.shape[0])
                for y in range(0, array.shape[1])
                if array[x, y] == min_cols[y]
                and array[x, y] == max_rows[x]])
