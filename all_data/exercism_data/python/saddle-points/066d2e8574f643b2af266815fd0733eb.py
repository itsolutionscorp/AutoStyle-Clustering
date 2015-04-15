def saddle_points(matrix):
    """Finds the location of saddle points in a given matrix"""

    #Eliminate trivial case
    if matrix == []:
        return set()

    #Validate the matrix
    row_len = len(matrix[0])
    if any([len(row) != row_len for row in matrix]):
        raise ValueError("Matrix is irregular and cannot contain saddle points")

    #Construct the transpose matrix
    transpose = list(zip(*matrix))

    #Finally, search for saddle points
    points = set()
    for i in range(len(matrix)):
        for j in range(len(transpose)):
            value = matrix[i][j]
            if value == max(matrix[i]) and value == min(transpose[j]):
                points.add((i,j))

    return points
