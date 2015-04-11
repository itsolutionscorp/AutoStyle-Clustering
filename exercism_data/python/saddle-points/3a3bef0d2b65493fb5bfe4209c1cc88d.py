def saddle_points(matrix):
    if not matrix:
        return set()
    if any(len(row)!=len(matrix[0]) for row in matrix):
        raise ValueError('Matrix does not have uniform size rows')
    mmax = [max(row) for row in matrix]
    mmin = [min(column) for column in zip(*matrix)]
    points = [(i,j) for i in range(len(matrix)) for j in range(len(matrix[0])) if mmax[i] == mmin[j]]

    return set(points)
