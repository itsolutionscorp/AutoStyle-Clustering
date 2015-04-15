def saddle_points(m):
    result = set()

    if len(m) < 1:
        return result
    if len(m[0]) < 1:
        return result

    rmax = []
    cmin = [10] * len(m[0])

    for row in m:
        if len(row) != len(m[0]):
            raise ValueError('%d is not a valid row length.' % (len(m[i])))
        rmax.append(max(row))

        for i, n in enumerate(row):
             if n < cmin[i]:
                 cmin[i] = n

    for r in range(len(m)):
        for c in range(len(m[0])):
            if m[r][c] == cmin[c] == rmax[r]:
               result.add((r,c)) 

    return result
