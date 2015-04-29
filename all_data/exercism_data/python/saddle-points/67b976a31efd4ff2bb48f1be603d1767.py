def saddle_points(rows):
    if len(set([len(r) for r in rows])) > 1:
        raise ValueError('irregular matrix')

    row_maxes = [_max_elements(col) for col in rows]
    col_mins = [_min_elements(row) for row in zip(*rows)]

    ret = []

    for row_n, maximums in enumerate(row_maxes):
        for row_max, col in maximums:
            if row_n in [row_number for _, row_number in col_mins[col]]:
                ret.append((row_n, col))
    return set(ret)


def _max_elements(seq):
    ''' Return list of tuples (value, position) of largest elements in seq '''
    m = max(seq)
    return [(j, i) for i, j in enumerate(seq) if j == m]


def _min_elements(seq):
    ''' Return list of tuples (value, position) of smallest elements in seq '''
    m = min(seq)
    return [(j, i) for i, j in enumerate(seq) if j == m]
