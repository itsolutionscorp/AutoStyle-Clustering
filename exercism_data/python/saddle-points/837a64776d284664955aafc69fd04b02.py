from itertools import product

def saddle_points(matrix):
    if not matrix:
        return set()
    rows = len(matrix)
    cols = len(matrix[0])
    if not all(len(row) == cols for row in matrix):
        raise ValueError

    points = set()
    for row, col in product(range(rows), range(cols)):
        element = matrix[row][col]
        row_elements = [e for e in matrix[row]]
        col_elements = [matrix[r][col] for r in range(rows)]
        if all(element >= e for e in row_elements) and \
          all(element <= e for e in col_elements):
            points.add((row, col))
    return points
