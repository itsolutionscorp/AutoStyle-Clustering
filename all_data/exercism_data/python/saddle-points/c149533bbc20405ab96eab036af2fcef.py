def saddle_points(mat):
    out = set()
    if not mat:
        return out
    for i in xrange(len(mat)):
        if len(mat[i]) != len(mat[0]):
            raise ValueError("Input is an irregular matrix.")
    # Find maximums of rows and minimums of columns
    row_maxs = [max(row) for row in mat]
    col_mins = [min([mat[i][j] for i in xrange(len(mat))]) for j in xrange(len(mat[0]))]
    for i in xrange(len(row_maxs)):
        for j in xrange(len(col_mins)):
            if mat[i][j] == row_maxs[i] and mat[i][j] == col_mins[j]:
                out.add((i,j))
    return out
