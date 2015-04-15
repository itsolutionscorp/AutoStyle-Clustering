from itertools import product

def saddle_points(matrix):
    if len(matrix) == 0 or len(matrix[0]) == 0:
        # empty matrix
        return set([])
    for i in range(1, len(matrix)):
        # irregular matrix
        if len(matrix[i]) != len(matrix[0]):
            raise ValueError
    width = len(matrix[0])
    height = len(matrix)
    columns = [[matrix[i][j] for i in range(height)] for j in range(width)]
    min_of_columns = list(map(min, columns))
    max_of_rows = list(map(max, matrix))
    def is_saddle_point(i, j):
        def is_min_in_column():
            return matrix[i][j] == min_of_columns[j]
        def is_max_in_row():
            return matrix[i][j] == max_of_rows[i]
        return is_min_in_column() and is_max_in_row()
    return set([(i, j) for i, j in product(range(height), range(width)) if is_saddle_point(i, j)])
