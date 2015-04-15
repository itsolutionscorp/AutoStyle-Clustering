from itertools import chain

def saddle_points(matrix):
    points = []
    if matrix:
        check_if_valid(matrix)
        points = get_saddle_points(matrix)
    return set(points)

def check_if_valid(matrix):
    length = len(matrix[0])

    for row in matrix[1:]:
        if len(row) != length:
            raise ValueError("irregular matrix")

def get_saddle_points(matrix):
    columns = list(chain(zip(*matrix)))
    points = []

    for i, row in enumerate(matrix):
        for j, value in enumerate(row):
            if greater(value, row) and less(value, columns[j]):
                points.append((i,j))
    return points

def greater(el, row):
    for value in row:
        if value > el:
            return False
    return True

def less(el, column):
    for value in column:
        if value < el:
            return False
    return True
