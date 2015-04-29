def saddle_points(rows):

    columns = list(zip(*rows))
    cords = [(x, y) for x in range(len(rows)) for y in range(len(columns))]

    if len(cords) != len(sum(rows, [])):
        raise ValueError('Invalid matrix')

    saddles =  [(x, y) for x, y in cords
                       if rows[x][y] == max(rows[x]) and
                          rows[x][y] == min(columns[y])]
    return set(saddles)
