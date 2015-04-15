def saddle_points(matrix):
    if len(set(len(row) for row in matrix)) > 1:
        raise ValueError("irregular matrix")

    transpose = list(list(col) for col in zip(*matrix))

    results = set()

    for i, row in enumerate(matrix):
        for j, x in enumerate(row):

            if x == max(row) and x == min(transpose[j]):
                results.add((i, j))

    return results
