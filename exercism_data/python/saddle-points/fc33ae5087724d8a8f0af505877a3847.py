__author__ = 'nebur1989'


def saddle_points(matrix):
    if len(matrix) == 0:
        return set()

    row_len = len(matrix[0])
    for row in matrix:
        if len(row) != row_len:
            raise ValueError

    saddle = set([])
    max_row = [max(elements) for elements in [elem for elem in matrix]]
    min_col = [min(elements) for elements in [elem for elem in zip(*matrix)]]
    for row in range(len(matrix)):
        for col in range(len(matrix[row])):
            element = matrix[row][col]
            if (max_row[row] == element) and (min_col[col] == element):
                saddle.add((row, col))
    return saddle
