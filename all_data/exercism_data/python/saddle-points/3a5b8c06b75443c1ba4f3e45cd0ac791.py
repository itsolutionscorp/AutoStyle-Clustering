def saddle_points(mat):
    
    spoints = set()
    
    if mat:
        rowLen = len(mat[0])
        for row in mat:
            if len(row) != rowLen:
                raise ValueError("Irregular matrix. All rows must be the same length.")

        for i, row in enumerate(mat):
            for j in range(rowLen):
                if row[j] == max(row):
                    if row[j] == min([mat[n][j] for n in range(len(mat))]):
                        spoints.add((i, j))

    return spoints
