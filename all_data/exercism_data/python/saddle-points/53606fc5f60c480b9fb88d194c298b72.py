def saddle_points(rows):
    columns = [list(x) for x in zip(*rows)]

    if rows:
        len_first = len(rows[0])
        for row in rows:
            if len(row) != len_first:
                raise ValueError('irregular matrix')

    result = set()
    for rownr, row in enumerate(rows):
        for posnr, pos in enumerate(row):
            if (pos == sorted(row)[-1] and
               pos == sorted(columns[posnr])[0]):
                result.add((rownr, posnr))
    return result
