def saddle_points(table):
    if any(len(line) != len(table[0]) for line in table):
        raise ValueError("Wrong Dimension : table must be a rectangular matrix")

    lines = table
    colms = list(zip(*table))

    out = set()
    for c in range(len(colms)):
        for l in range(len(lines)):
            s = table[l][c]
            if s == max(lines[l]) and s == min(colms[c]):
                out.add((l, c))
    return out
