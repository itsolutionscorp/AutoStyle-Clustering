def saddle_points(mtx):
    column_count = set()
    for row in mtx:
        column_count.add(len(row))
    if len(column_count) > 1:
        raise ValueError("matrix is irregular")

    if len(column_count) == 0:
        # empty matrix
        return set()

    cnt = column_count.pop()
    transposed = [ [ mtx[i][j] for i in range(len(mtx)) ] for j in range(cnt) ]

    saddles = set()

    for i, row in enumerate(mtx):
        for j, val in enumerate(row):
            # coordinate is i,j
            if max(row) == val and min(transposed[j]) == val:
                saddles.add((i,j))

    return saddles
