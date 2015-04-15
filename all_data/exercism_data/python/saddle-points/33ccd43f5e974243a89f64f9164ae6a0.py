def saddle_points(rows):
    columns = [list(x) for x in zip(*rows)]

    if rows:
        len_first = len(rows[0])
        if any(len(row) != len_first for row in rows):
            raise ValueError('irregular matrix')

    result = set()
    for rownr, row in enumerate(rows):
        for posnr, pos in enumerate(row):
            if (pos == max(row) and
               pos == min(columns[posnr])):
                result.add((rownr, posnr))
    return result
