def saddle_points(matrix):
    # validate input
    for i in range(len(matrix) - 1):
        if len(matrix[i]) != len(matrix[i+1]):
            raise ValueError("irregular input matrix")
    
    transposed_matrix = [list (tuple) for tuple in zip(*matrix)]
    result = set([])
    # loop through each row
    for i in range(len(matrix)):
        # loop through each number in row
        for j in range(len(matrix[i])):
            # if number is largest in the row
            if matrix[i][j] >= max(matrix[i]):
                # and if number is smallest in the column
                if transposed_matrix[j][i] <= min(transposed_matrix[j]):
                    # add the position in the matrix to the result set
                    result.add((i, j))
    return result
