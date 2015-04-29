def saddle_points(inp):
    if not len({len(row) for row in inp}) < 2:
        raise ValueError("Input matrix irregular.")
    ans = set()
    T = zip(*inp)
    for i, row in enumerate(inp):
        for j, el in enumerate(row):
            if el == max(row) and el == min(T[j]):
                ans.add((i, j))
    return ans
