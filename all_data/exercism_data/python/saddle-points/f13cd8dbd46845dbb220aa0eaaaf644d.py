def saddle_points(matrix):
    if len(matrix) == 0:
        return set()

    for row in matrix:
        if len(row) != len(matrix[0]):
            raise ValueError('irregular matrix')

    row_max = [max(row) for row in matrix]
    col_min = [min(col) for col in zip(*matrix)]

    results = [(i, j) for i in range(len(matrix)) for j in range(len(matrix[0])) if row_max[i] == col_min[j]]
    return set(results)
