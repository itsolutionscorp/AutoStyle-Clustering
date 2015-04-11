def saddle_points(rows):
    if rows == []:
        return set()

    rowNb = len(rows)
    colNb = len(rows[0])
    for r in rows:
        if len(r) != colNb:
            raise ValueError

    columns = [[rows[i][j] for i in range(0, rowNb)] for j in range(0, colNb)]

    result = set()

    for i in range(0, rowNb):
        for j in range(0, colNb):
            if rows[i][j] == max(rows[i]) and rows[i][j] == min(columns[j]):
                result.add((i, j))

    return result
