__author__ = 'tracyrohlin'

from matrix import Matrix

def saddle_points(array):
    if not array:
        return set(array)
    matrix_obj = Matrix(array)
    rows = matrix_obj.rows
    columns = matrix_obj.columns
    n = max([len(r) for r in rows])
    m = max([len(c) for c in columns])


    saddle_points_list = []
    for column in columns:
        for row in rows:
            if len(row) != n:
                raise ValueError("One row is too short")
            if max(row) == min(column):
                saddle_points_list.append((rows.index(row), columns.index(column)))

    return set(saddle_points_list)
