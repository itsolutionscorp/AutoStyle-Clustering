def saddle_points(rows):
    cols = zip(*rows)
    if len(rows) > 0 and len(cols) < max(len(r) for r in rows):
        raise ValueError('Irregular matrix')
    saddle = set()
    for r_index in range(len(rows)):
        for c_index in range(len(cols)):
            candidate = rows[r_index][c_index]
            if candidate == max(rows[r_index]) and candidate == min(cols[c_index]):
                saddle.add((r_index, c_index))
    return saddle
