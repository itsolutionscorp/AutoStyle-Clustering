def saddle_points(matrix):
    if matrix == []: return set()
    rowlengths = map(lambda r: len(r), matrix)
    if max(rowlengths) != min(rowlengths): raise ValueError

    row = lambda m, r: m[r]
    col = lambda m, c: map(lambda r: r[c], m)

    return { (x,y) for y in range(len(matrix[0]))
                   for x in range(len(matrix))
                   if is_saddle(row(matrix, x),
                                col(matrix, y),
                                matrix[x][y])  }

def is_saddle(row, col, val):
    return val == max(row) and val == min(col)
