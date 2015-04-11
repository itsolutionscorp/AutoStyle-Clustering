def saddle_points(matrix):
    # Just renaming
    rows = matrix

    # Raise a ValueError on irregular matrices
    validate_matrix(matrix)

    # Grab the columns of the matrix
    columns = [list(column) for column in zip(*rows)]

    # Iterate over the rows and columns, checking to
    # see whether each value is the max of its row
    # and the min of its column
    return {(i, j)
            for i in range(len(rows))
            for j in range(len(columns))
            if max(rows[i]) == rows[i][j] == min(columns[j])}


def validate_matrix(matrix):
    row_lengths = [len(row) for row in matrix]
    if any(length != row_lengths[0] for length in row_lengths):
        raise ValueError("Irregular matrix: matrices must be rectangular")
