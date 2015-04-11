def saddle_points(matrix):
    rows, columns = range(len(matrix)), range(len(matrix[0]))
    return {(i, j) for i in rows for j in columns
                   if all(matrix[i][n] <= matrix[i][j] for n in columns) and
                      all(matrix[m][j] >= matrix[i][j] for m in rows)}
