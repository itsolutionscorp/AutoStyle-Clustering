def saddle_points(matrix):
    if len(matrix) == 0:
        return set()

    rows, columns = range(len(matrix)), range(len(matrix[0]))
    if any(len(matrix[i]) != len(matrix[0]) for i in rows):
        raise ValueError("Wrong Column Number!")

    return {(i, j) for i in rows for j in columns
                   if all(matrix[i][n] <= matrix[i][j] for n in columns) and
                      all(matrix[m][j] >= matrix[i][j] for m in rows)}
