def saddle_points(matrix):
    if len(matrix) == 0 or len(matrix[0]) == 0:
        return set()

    if any(len(row) != len(matrix[0]) for row in matrix):
        raise ValueError("Matrix must be rectangular")

    saddle_pnts = set()

    max_per_row = [idx_max(row) for row in matrix]
    min_per_col = [idx_min(col) for col in transponsed(matrix)]

    for row_idx, row_maximums in enumerate(max_per_row):
        for row_maximum in row_maximums:
            if row_idx in min_per_col[row_maximum]:
                saddle_pnts.add((row_idx, row_maximum))

    return saddle_pnts


def transponsed(matrix):
    """Transposes the given matrix"""
    tr_matrix = [[] for _ in range(len(matrix[0]))]

    for row in matrix:
        for idx, val in enumerate(row):
            tr_matrix[idx].append(val)

    return tr_matrix


def idx_max(values):
    """Returns the set of indices where values is maximal"""
    return idx_min(-a for a in values)


def idx_min(values):
    """Returns the set of indices where values in minimal"""
    it = iter(values)
    indices, best = {0}, next(it)

    for i, val in enumerate(it, 1):
        if val < best:
            indices, best = {i}, val
        elif val == best:
            indices.add(i)

    return indices
