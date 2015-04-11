def saddle_points(matrix):
    rowmaxes = []
    colmins = []
    rowlen = len(matrix)

    for row in xrange(0, rowlen):
        if row >= len(rowmaxes):
            rowmaxes.append(matrix[row][0])

        if row == 0:
            collen = len(matrix[row])
        elif collen != len(matrix[row]):
            raise ValueError, 'irregular matrix'

        for col in xrange(0, collen):
            if col >= len(colmins):
                colmins.append(matrix[0][col])

            if matrix[row][col] > rowmaxes[row]:
                rowmaxes[row] = matrix[row][col]

            if matrix[row][col] < colmins[col]:
                colmins[col] = matrix[row][col]

    saddles = set()
    for row in xrange(0, rowlen):
        for col in xrange(0,collen):
            if matrix[row][col] == rowmaxes[row] and matrix[row][col] == colmins[col]:
                saddles.add((row,col))

    return saddles
