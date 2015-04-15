def saddle_points(matrix):
    for row in matrix:
        if len(row) != len(matrix[0]):
            raise ValueError("irregular matrix")

    saddlePoints = []

    for ridx in range(len(matrix)):
        for cidx in range(len(matrix[0])):
            # Verify cond. 1: >= all in row
            if matrix[ridx][cidx] >= max(matrix[ridx]):
                # Verify cond. 2: <= all in col
                if matrix[ridx][cidx] <= min([matrix[i][cidx] for i in range(len(matrix))]):
                    saddlePoints.append( (ridx, cidx) )

    return set(saddlePoints)
