def saddle_points(matrix):
    if (len(matrix) == 1
        or not all([(len(matrix[0]) == len(row)
                     and len(row) > 1) for row in matrix])):
        raise ValueError('Matrix must be at least two by two.')
    result = set()
    for r in range(len(matrix)):
        row = matrix[r]
        rowmax = max(row)
        for c in range(len(row)):
            col = [row[c] for row in matrix]
            colmin = min(col)
            if colmin == matrix[r][c] == rowmax:
                result.add(tuple([r,c]))
    return result
