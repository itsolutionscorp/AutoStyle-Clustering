def saddle_points(a):
    if len(set([len(r) for r in a])) > 1:
        raise ValueError('irregular matrix')

    if len(a) == 0:
        return set([])

    row_maxes = [maxelements(col) for col in a]
    col_mins = [minelements(row) for row in zip(*a)]

    ret = []

    for row, maximums in enumerate(row_maxes):
        for row_max, col in maximums:
            if row in [row_number for _, row_number in col_mins[col]]:
                ret.append((row, col))
    return set(ret)


def maxelements(seq):
    ''' Return list of tuples (value, position) of largest element '''
    m = max(seq)
    return [(j, i) for i, j in enumerate(seq) if j == m]


def minelements(seq):
    ''' Return list of tuples (value, position) of smallest element '''
    m = min(seq)
    return [(j, i) for i, j in enumerate(seq) if j == m]

if __name__ == '__main__':
    saddle_points([[5, 3, 5, 4], [6, 4, 7, 3], [5, 1, 5, 3]])
