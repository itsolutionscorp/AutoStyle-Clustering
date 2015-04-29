def saddle_points(rows):
    if not rows:
        return set([])
    if any(len(row) != len(rows[0]) for row in rows):
        raise ValueError('Invalid matrix')
    maxs = [max(row) for row in rows]
    mins = [min(col) for col in zip(*rows)]
    return set((i, j) for i in range(len(maxs)) for j in range(len(mins))
               if maxs[i] == mins[j])
